package com.slithership;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * Kelas ini berfungsi untuk mengatur JPanel, tampilan menu, dan navigasi menu. 
 */

public class GPanel extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1576194300470587189L;
	
	static final int SCREEN_WIDTH = 960;
	static final int SCREEN_HEIGHT = 640;
	static final int UNIT_SIZE = 32;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int MENUDELAY = 220;
	static final int DELAY = 150;
	static int gameTime, score, zoin, highscore, passengersType;
	static boolean running = false;
	static Timer timer;
	static TimerTask decrementTime, water;
	static GameTimer gameTimer, waterTimer;
	
	private int screenOffset;
	private boolean atCenter;
	private BufferedImage bf;
	private FileWriter writer;
	private Music music, soundeffects;
	private Ship ship;
	private Textures textures;
	private Box box;
	private ArrayList<GameObject> gameObjects;
	private State state, nextState;
	private int[] isPassengerBought;
	private int[] isPassengerUsed;
		
	public enum State {
		MENU,
		INSTRUCTION,
		CREDIT,
		SHOP,
		GAME,
		LOADINGSCREEN;
	}
	
	public GPanel() {
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, 3*SCREEN_HEIGHT));
		this.setBackground(Color.CYAN);
		this.setFocusable(true);
		this.addKeyListener(new Keyboard());
		this.addMouseListener(new Mouse());
		
		textures = new Textures();
		music = new Music();
		soundeffects = new Music();
		
		int i=0;
		int[] data = new int[10];
		isPassengerBought = new int[4];
		isPassengerUsed = new int[4];
		
		try {
			Scanner scanner = new Scanner(new File(new File("src/com/slithership/assets/data.txt").getAbsolutePath()));
			
			while(scanner.hasNextInt()) {
				data[i] = scanner.nextInt();
				i++;
			}
			
			zoin = data[0];
			highscore = data[1];
			isPassengerBought[0] = data[2];
			isPassengerBought[1] = data[3];
			isPassengerBought[2] = data[4];
			isPassengerBought[3] = data[5];
			isPassengerUsed[0] = data[6];
			isPassengerUsed[1] = data[7];
			isPassengerUsed[2] = data[8];
			isPassengerUsed[3] = data[9];
			
			scanner.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		state = State.MENU;
		
		start();
	}
	
	public void start() {
		if(state == State.MENU) {
			music.play(state);
			running = true;
			
			water = new TimerTask() {
				
				@Override
				public void run() {
					music.water(0.2);
				}
			};
			
			waterTimer = new GameTimer();
			waterTimer.scheduleAtFixedRate(water, 0, 4000);
			
			timer = new Timer(MENUDELAY, this);
			timer.start();
			
		}else if(state == State.GAME) {
			music.play(state);
			
			gameTime = 20;
			score = 0;
			screenOffset = 0;
			atCenter = false;
			running = true;
			
			if(isPassengerUsed[1] == 0) passengersType = 0;
			else if(isPassengerUsed[1] == 1) passengersType = 1;
			
			ship = new Ship(2 * UNIT_SIZE, 4 * UNIT_SIZE, textures, soundeffects);
			box = new Box(textures, ship);
			
			gameObjects = new ArrayList<GameObject>();
			
			gameObjects.add(new Harbour(textures, ship));
			gameObjects.add(new Passengers(textures, ship, this));
			gameObjects.add(ship);
			gameObjects.add(new Garage(ship, textures));
			gameObjects.add(box);
			gameObjects.add(new Zoin(textures, ship, box, soundeffects));
			gameObjects.add(new UI(textures, this));
			
			ship.newDestination();
			
			water = new TimerTask() {
				
				@Override
				public void run() {
					music.water(0.2);
				}
			};
			
			waterTimer = new GameTimer();
			waterTimer.scheduleAtFixedRate(water, 0, 4000);
			
			decrementTime = new TimerTask() {
				
				@Override
				public void run() {
					gameTime--;
				}
			};
			
			gameTimer = new GameTimer();
			gameTimer.scheduleAtFixedRate(decrementTime, 0, 1000);
			
			timer = new Timer(DELAY, this);
			timer.start();
			
		}else if(state == State.LOADINGSCREEN) {
			decrementTime = new TimerTask() {
				
				@Override
				public void run() {
					textures.incrementLoadingScreen();
				}
			};
			
			gameTimer = new GameTimer();
			gameTimer.scheduleAtFixedRate(decrementTime, 0, MENUDELAY);
			
			timer = new Timer(MENUDELAY / 4, this);
			timer.start();
		}
		
		System.gc();
	}
	
	public void update(Graphics g) {
		paintComponent(g);
	}
	
	public void paintComponent(Graphics g) {
		bf = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
		draw(bf.getGraphics());
		g.drawImage(bf, 0, 0, null);
	}
	
	public void draw(Graphics g) {
		if((System.getProperty("os.name")).equals("Linux")){
			 Toolkit.getDefaultToolkit().sync();
		}
		
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D)g;
		
		//menggambar tampilan menu
		if(state == State.MENU) {
			g2D.drawImage(textures.getMenu(), 0, 0, null);
			g2D.drawImage(textures.getMenuTitle(), SCREEN_WIDTH/2 - textures.getMenuTitle().getWidth()/2, 2*UNIT_SIZE - 25, null);	
			g2D.drawImage(textures.getMenuPlay(), SCREEN_WIDTH/2 - textures.getMenuPlay().getWidth()/2 + 5, 11*UNIT_SIZE - 40, null);
			g2D.drawImage(textures.getMenuInstruction(), SCREEN_WIDTH/2 - textures.getMenuInstruction().getWidth()/2, 14*UNIT_SIZE - 40, null);
			g2D.drawImage(textures.getMenuCredit(), SCREEN_WIDTH/2 - textures.getMenuCredit().getWidth()/2, 16*UNIT_SIZE - 35, null);
			g2D.drawImage(textures.getMenuShop(), SCREEN_WIDTH - textures.getMenuShop().getWidth() - UNIT_SIZE , 18*UNIT_SIZE - 35, null);
			g2D.drawImage(textures.getHighScore(), SCREEN_WIDTH/2 - textures.getHighScore().getWidth()/2, 18*UNIT_SIZE - 35, null);
			
			FontMetrics metrics = getFontMetrics(g2D.getFont());
			g2D.setColor(Color.DARK_GRAY);
			g2D.setFont(new Font("Noto Sans", Font.BOLD, 30));
			g2D.drawString(String.valueOf(highscore), 17*UNIT_SIZE - 10 - metrics.stringWidth(String.valueOf(highscore)), 18*UNIT_SIZE - 3);
		
		//menggambar tampilan instruksi
		}else if(state == State.INSTRUCTION) {
			g2D.drawImage(textures.getInstruction(), 0, 0, null);
			g2D.drawImage(textures.getBack(), UNIT_SIZE/2, UNIT_SIZE/2, null);
			
		//menggambar tampilan kredit
		}else if(state == State.CREDIT) {
			g2D.drawImage(textures.getCredit(), 0, 0, null);
			g2D.drawImage(textures.getBack(), UNIT_SIZE/2, UNIT_SIZE/2, null);
		
		//menggambar tampilan toko
		}else if(state == State.SHOP) {
			FontMetrics metrics = getFontMetrics(g2D.getFont());
			g2D.setColor(Color.BLACK);
			g2D.setFont(new Font("Noto Sans", Font.BOLD, 14));
			g2D.drawImage(textures.getBuyPassengers(0), 0, 0, null);
			g2D.drawImage(textures.getBack(), UNIT_SIZE/2, UNIT_SIZE/2, null);
			g2D.drawImage(textures.getZoinCounter(), SCREEN_WIDTH - 4*UNIT_SIZE, UNIT_SIZE/2 + UNIT_SIZE/2, null);
			g2D.drawString(String.valueOf(zoin), SCREEN_WIDTH - 4*UNIT_SIZE + 55 - metrics.stringWidth(String.valueOf(zoin))/2, UNIT_SIZE/2 + UNIT_SIZE/2 + 21);
			
			if(isPassengerBought[1] == 0) {
				g2D.setFont(new Font("Noto Sans", Font.BOLD, 60));
				g2D.drawString("2000", SCREEN_WIDTH/2 + 6*UNIT_SIZE - 20, SCREEN_HEIGHT / 2 - UNIT_SIZE + 20);
				g2D.drawImage(textures.getShopButton(2), SCREEN_WIDTH/2 + 2*UNIT_SIZE - 25, SCREEN_HEIGHT / 2 - 3*UNIT_SIZE + 5, null);
				g2D.setColor(Color.GRAY);
				g2D.fillRect(SCREEN_WIDTH/2 + 2*UNIT_SIZE, SCREEN_HEIGHT / 2 + UNIT_SIZE + 60, 8*UNIT_SIZE, 3*UNIT_SIZE);
				g2D.drawImage(textures.getShopButton(1), SCREEN_WIDTH/2 + 2*UNIT_SIZE, SCREEN_HEIGHT / 2 - UNIT_SIZE + 20, null);
				
			}else if(isPassengerBought[1] == 1) {
				if(isPassengerUsed[1] == 0) {
					g2D.setColor(Color.GRAY);
					g2D.fillRect(SCREEN_WIDTH/2 + 2*UNIT_SIZE, SCREEN_HEIGHT / 2 + UNIT_SIZE + 60, 8*UNIT_SIZE, 3*UNIT_SIZE);
					g2D.drawImage(textures.getShopButton(0), SCREEN_WIDTH/2 + 2*UNIT_SIZE, SCREEN_HEIGHT / 2 - UNIT_SIZE + 20, null);
					
				}else if(isPassengerUsed[1] == 1){
					g2D.drawImage(textures.getShopButton(0), SCREEN_WIDTH/2 + 2*UNIT_SIZE, SCREEN_HEIGHT / 2 - UNIT_SIZE + 35, null);
					
				}
			}
			
		}else if(state == State.GAME) {
			if(running) {
				//menentukan nilai pergeseran layar
				if(ship.getYHead() >= SCREEN_HEIGHT - 7*UNIT_SIZE && ship.getYHead() <= 3*SCREEN_HEIGHT - 8*UNIT_SIZE) {
					atCenter = true;
					
					if(ship.getDirection() == 'U') {
						screenOffset += UNIT_SIZE;
					}else if(ship.getDirection() == 'D') {
						screenOffset += -UNIT_SIZE;
					}
					
				}else if(atCenter) {
					atCenter = false;
					
					if(ship.getDirection() == 'U') {
						screenOffset += UNIT_SIZE;
					}else if(ship.getDirection() == 'D') {
						screenOffset += -UNIT_SIZE;
					}
					
				}
				
				//menggambar semua objek
				for(GameObject go : gameObjects) {
					go.drawObject(screenOffset, g2D);
				}
				
			}else {
				gameOver(g2D);
				
			}
		}else if(state == State.LOADINGSCREEN) {
			g2D.drawImage(textures.getLoadingScreen(), 0, 0, null);
			
		}
		
	}
	
	//menggambar tampilan gameover
	public void gameOver(Graphics g) {
		g.drawImage(textures.getGameOver(0), 0, 0, null);
		g.drawImage(textures.getGameOver(1), 8*UNIT_SIZE, SCREEN_HEIGHT - 5*UNIT_SIZE + 30, null);
		g.drawImage(textures.getGameOver(2), 15*UNIT_SIZE, SCREEN_HEIGHT - 5*UNIT_SIZE + 30, null);
		g.drawImage(textures.getGameOver(3), SCREEN_WIDTH/2  - textures.getGameOver(3).getWidth()/2 - 20, SCREEN_HEIGHT - 7*UNIT_SIZE + 30, null);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Noto Sans", Font.BOLD, 14));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString(String.valueOf(score), SCREEN_WIDTH/2  - textures.getGameOver(3).getWidth()/2 + 65 - metrics.stringWidth(String.valueOf(score))/2, SCREEN_HEIGHT - 7*UNIT_SIZE + 51);
		
		//menulis data saat gameover
		try {
			writer = new FileWriter(new File("src/com/slithership/assets/data.txt").getAbsolutePath());
			writer.write(zoin + " " + highscore + " " + isPassengerBought[0] + " " + isPassengerBought[1] + " " + isPassengerBought[2] +
					" " + isPassengerBought[3] + " " + isPassengerUsed[0] + " " + isPassengerUsed[1] + " " + isPassengerUsed[2]  + " " + isPassengerUsed[3]);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void checkColission() {
		//mengecek tabrakan kapal dengan seluruh objek di map game
		for(GameObject go : gameObjects) {
			go.objectCollision();
		}
		
		//menghentikan game jika kapal menabrak sesuatu
		if(!running) {
			soundeffects.impact1(0.5);
			soundeffects.impact2(0.5);
			music.stop();
			timer.stop();
			gameTimer.cancel();
			waterTimer.cancel();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(state == State.GAME) {
			if(running) {
				ship.move();
				ship.checkDestination();
				checkColission();
				ship.changeShipCount();
				
				if(score > highscore) highscore = score;
				
				//menghentikan game jika waktu dan jumlah kapal habis
				if(gameTime == 0 || ship.getShipCount() == 0) {
					music.stop();
					timer.stop();
					gameTimer.cancel();
					waterTimer.cancel();
					running = false;
				}
			}
			
		}else if(state == State.LOADINGSCREEN) {
			if(textures.getLoadingScreenIndex() == 11) {
				textures.resetLoadingScreenIndex();
				gameTimer.cancel();
				state = nextState;
				music.stop();
				timer.stop();
				start();
			}
			
		}
		
		repaint();
	}
	
	private class Keyboard extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if(state == State.GAME) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						if(ship.getDirection() != 'R') {
							ship.setDirection('L');
						}
						break;
					case KeyEvent.VK_RIGHT:
						if(ship.getDirection() != 'L') {
							ship.setDirection('R');
						}
						break;
					case KeyEvent.VK_UP:
						if(ship.getDirection() != 'D') {
							ship.setDirection('U');
						}
						break;
					case KeyEvent.VK_DOWN:
						if(ship.getDirection() != 'U') {
							ship.setDirection('D');
						}
						break;
				}
			}
			
		}
	}
	
	private class Mouse extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(state == State.MENU) {
				//mouse listener untuk tombol pada menu
				//play
				if((e.getX() > SCREEN_WIDTH/2 - textures.getMenuPlay().getWidth()/2 + 5 && e.getX() < SCREEN_WIDTH/2 - textures.getMenuPlay().getWidth()/2 + 5 + textures.getMenuPlay().getWidth()) &&	
						(e.getY() > 11*UNIT_SIZE - 40 && e.getY() < 11*UNIT_SIZE - 40 + textures.getMenuPlay().getHeight())) {
					soundeffects.click(0.5);
					timer.stop();
					waterTimer.cancel();
					nextState = State.GAME;
					state = State.LOADINGSCREEN;
					start();
				}
				//instruction
				else if((e.getX() > SCREEN_WIDTH/2 - textures.getMenuInstruction().getWidth()/2 && e.getX() < SCREEN_WIDTH/2 - textures.getMenuInstruction().getWidth()/2 + textures.getMenuInstruction().getWidth()) &&
						(e.getY() > 14*UNIT_SIZE - 40 && e.getY() < 14*UNIT_SIZE - 40 + textures.getMenuInstruction().getHeight())){
					soundeffects.click(0.5);
					state = State.INSTRUCTION;
				}
				//credit
				else if((e.getX() > SCREEN_WIDTH/2 - textures.getMenuCredit().getWidth()/2 && e.getX() < SCREEN_WIDTH/2 - textures.getMenuCredit().getWidth()/2 + textures.getMenuCredit().getWidth()) && 
						(e.getY() > 16*UNIT_SIZE - 35 && e.getY() < 16*UNIT_SIZE - 35 + textures.getMenuCredit().getHeight())) {
					soundeffects.click(0.5);
					state = State.CREDIT;
				}
				//shop
				else if((e.getX() > SCREEN_WIDTH - textures.getMenuShop().getWidth() - UNIT_SIZE && e.getX() < SCREEN_WIDTH - textures.getMenuShop().getWidth() - UNIT_SIZE + textures.getMenuShop().getWidth()) && 
						(e.getY() > 18*UNIT_SIZE - 35 && e.getY() < 18*UNIT_SIZE - 35 + textures.getMenuShop().getHeight())) {
					soundeffects.click(0.5);
					state = State.SHOP;
				}
				
			}else if(state == State.INSTRUCTION) {
				//mouse listener untuk tombol pada instruksi
				//back
				if((e.getX() > UNIT_SIZE/2 && e.getX() < UNIT_SIZE/2 + textures.getBack().getWidth()) && (e.getY() > UNIT_SIZE/2 && e.getY() < UNIT_SIZE/2 + textures.getBack().getHeight())){
					soundeffects.click(0.5);
					state = State.MENU;
				}
				
			}else if(state == State.CREDIT) {
				//mouse listener untuk tombol pada kredit
				//back
				if((e.getX() > UNIT_SIZE/2 && e.getX() < UNIT_SIZE/2 + textures.getBack().getWidth()) && (e.getY() > UNIT_SIZE/2 && e.getY() < UNIT_SIZE/2 + textures.getBack().getHeight())){
					soundeffects.click(0.5);
					state = State.MENU;
				}
			}else if(state == State.SHOP) {
				//mouse listener untuk tombol pada toko
				//back
				if((e.getX() > UNIT_SIZE/2 && e.getX() < UNIT_SIZE/2 + textures.getBack().getWidth()) && (e.getY() > UNIT_SIZE/2 && e.getY() < UNIT_SIZE/2 + textures.getBack().getHeight())){
					soundeffects.click(0.5);
					state = State.MENU;
				}
				
				//buy and select
				if((e.getX() > SCREEN_WIDTH/2 + 2*UNIT_SIZE && e.getX() < SCREEN_WIDTH/2 + 2*UNIT_SIZE + textures.getShopButton(1).getWidth()) &&
						(e.getY() > SCREEN_HEIGHT / 2 - UNIT_SIZE + 20 && e.getY() < SCREEN_HEIGHT / 2 - UNIT_SIZE + 20 + textures.getShopButton(1).getHeight())) {
					if(isPassengerBought[1] == 0) {
						if(zoin >= 2000) {
							soundeffects.buy(0.5);
							zoin -= 2000;
							isPassengerBought[1] = 1;
						}else if(zoin < 2000) {
							soundeffects.notEnoughCapacity(0.5);
						}
						
					}else if(isPassengerBought[1] == 1) {
						if(isPassengerUsed[1] == 0) {
							soundeffects.click(0.5);
							isPassengerUsed[1] = 1;
						}else if(isPassengerUsed[1] == 1) {
							soundeffects.click(0.5);
							isPassengerUsed[1] = 0;
						}
					}
					
					//menulis data setelah membeli sebuah barang di toko
					try {
						writer = new FileWriter(new File("src/com/slithership/assets/data.txt").getAbsolutePath());
						writer.write(zoin + " " + highscore + " " + isPassengerBought[0] + " " + isPassengerBought[1] + " " + isPassengerBought[2] +
								" " + isPassengerBought[3] + " " + isPassengerUsed[0] + " " + isPassengerUsed[1] + " " + isPassengerUsed[2]  + " " + isPassengerUsed[3]);
						writer.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				
			}else if(state == State.GAME) {
				if(!running) {
					//mouse listener untuk tombol saat gameover
					//menu
					if((e.getX() > 8*UNIT_SIZE && e.getX() < 8*UNIT_SIZE + textures.getGameOver(1).getWidth()) &&
							(e.getY() > SCREEN_HEIGHT - 5*UNIT_SIZE + 30 && e.getY() < SCREEN_HEIGHT - 5*UNIT_SIZE + 30 + textures.getGameOver(1).getHeight())) {
						soundeffects.click(0.5);
						nextState = State.MENU;
						state = State.LOADINGSCREEN;
						start();
					}
					
					//restart
					else if((e.getX() > 15*UNIT_SIZE && e.getX() < 15*UNIT_SIZE + textures.getGameOver(2).getWidth()) && 
							(e.getY() > SCREEN_HEIGHT - 5*UNIT_SIZE + 30 && e.getY() < SCREEN_HEIGHT - 5*UNIT_SIZE + 30 + textures.getGameOver(2).getHeight())) {
						soundeffects.click(0.5);
						nextState = State.GAME;
						state = State.LOADINGSCREEN;
						start();
					}
				}
				
			}
		}
	}
}
