package com.slithership;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

/*
 * Kelas ini berfungsi untuk memunculkan kapal di map game dan membuat kapal memiliki fungsinya.
 */

public class Ship extends GameObject {
	private int shipCount = 3;
	private int passengersMax = 30;
	private int passengersCount;
	private int shipCapacity = shipCount * 10;
	private int destinationX;
	private int destinationY;
	private char direction = 'D';
	private char prevDirection = 'D';
	private boolean isRightSide = true;
	private boolean isTakingPassengers = true;
	private boolean isPassengersDropped = false;
	private final int x[] = new int[GPanel.GAME_UNITS];
	private final int y[] = new int[GPanel.GAME_UNITS];
	private Random random;
	private ArrayList<Character> drawDirection;
	private Textures textures;
	private Music soundEffects;
	
	public Ship(int initX, int initY, Textures textures, Music soundEffects) {
		random = new Random();
		this.textures = textures;
		this.soundEffects = soundEffects;
		x[0] = initX;
		y[0] = initY;
		drawDirection = new ArrayList<Character>();
		
		for(int i=0; i<=shipCount; i++) {
			drawDirection.add(direction);
		}

	}
	
	//menentukan jumlah penumpang yang akan dijemput kapal
	public void newPassengers() {
		if(GPanel.score != 0 && (GPanel.score % 300 == 0)) {
			passengersMax += 10;
		}
		passengersCount = random.nextInt(passengersMax);
		if(passengersCount == 0) {
			passengersCount++;
		}
	}
	
	//menentukan tujuan kapal
	public void newDestination() {
		int place = random.nextInt(3);
		
		if(isRightSide) {
			destinationX = GPanel.SCREEN_WIDTH - 3*GPanel.UNIT_SIZE;
			if(isTakingPassengers) {
				switch(place) {
					case 0:
						destinationY = 4 * GPanel.UNIT_SIZE;
						break;
					case 1:
						destinationY = 24 * GPanel.UNIT_SIZE;
						break;
					case 2:
						destinationY = 44 * GPanel.UNIT_SIZE;
						break;
				}
				newPassengers();
			}else {
				switch(place) {
					case 0:
						destinationY = 14 * GPanel.UNIT_SIZE;
						break;
					case 1:
						destinationY = 34 * GPanel.UNIT_SIZE;
						break;
					case 2:
						destinationY = 54 * GPanel.UNIT_SIZE;
						break;
				}
			}
			
		}else {
			destinationX = 2 * GPanel.UNIT_SIZE;
			if(isTakingPassengers) {
				switch(place) {
					case 0:
						destinationY = 4 * GPanel.UNIT_SIZE;
						break;
					case 1:
						destinationY = 24 * GPanel.UNIT_SIZE;
						break;
					case 2:
						destinationY = 44 * GPanel.UNIT_SIZE;
						break;
				}
				newPassengers();
			}else {
				switch(place) {
					case 0:
						destinationY = 14 * GPanel.UNIT_SIZE;
						break;
					case 1:
						destinationY = 34 * GPanel.UNIT_SIZE;
						break;
					case 2:
						destinationY = 54 * GPanel.UNIT_SIZE;
						break;
				}
			}
			
		}
	}
	
	//mengecek apakah kapal sudah sampai tujuan
	public void checkDestination() {
		if(x[0] == destinationX && (y[0] == destinationY || y[0] == destinationY + GPanel.UNIT_SIZE)) {
			if(isTakingPassengers) {
				if(shipCapacity >= passengersCount) {
					soundEffects.take(0.5);
					GPanel.gameTime = 20;
					isRightSide = !isRightSide;
					isTakingPassengers = !isTakingPassengers;
					newDestination();
					isPassengersDropped = false;
				}else {
					soundEffects.notEnoughCapacity(0.5);
				}
			}else if(!isTakingPassengers) {
				soundEffects.drop(0.5);
				GPanel.gameTime = 20;
				isRightSide = random.nextBoolean();
				isTakingPassengers = !isTakingPassengers;
				newDestination();
				GPanel.score += 100;
				isPassengersDropped = true;
			}
		}
	}
	
	//mengubah ukuran kapal saat melewati garasi
	public void changeShipCount() {
		if(x[0] == 12*GPanel.UNIT_SIZE && y[0] == GPanel.SCREEN_HEIGHT + 10*GPanel.UNIT_SIZE) {
			soundEffects.garageAdd(0.75);
			shipCount++;
			drawDirection.add(direction);
		}else if(x[0] == 17*GPanel.UNIT_SIZE && y[0] == GPanel.SCREEN_HEIGHT + 10*GPanel.UNIT_SIZE) {
			soundEffects.garageSub(1);
			shipCount--;
		}
		shipCapacity = shipCount * 10;

	}
	
	//pergerakan kapal
	public void move() {
		for(int i = shipCount; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
			drawDirection.set(i, drawDirection.get(i-1));
		}
		
		switch(direction) {
			case 'U':
				y[0] = y[0] - GPanel.UNIT_SIZE;
				drawDirection.set(0, direction);
				break;
			case 'D':
				y[0] = y[0] + GPanel.UNIT_SIZE;
				drawDirection.set(0, direction);
				break;
			case 'L':
				x[0] = x[0] - GPanel.UNIT_SIZE;
				drawDirection.set(0, direction);
				break;
			case 'R':
				x[0] = x[0] + GPanel.UNIT_SIZE;
				drawDirection.set(0, direction);
				break;
			}
	}
	
	public void drawObject(int screenOffset, Graphics2D g2D) {
		for(int i=0; i<getShipCount(); i++) {
			ArrayList<Character> drawDirection = getDrawDirection();
			
			if(x[i] == 0) {
				x[i] = x[0];
			}
			
			if(y[i] == 0) {
				y[i] = y[0];
			}
			
			if(i == 0) {
				switch(drawDirection.get(i)) {
				case 'U':
					g2D.drawImage(textures.getShipU(), x[i], y[i] + screenOffset, null);
					break;
				case 'R':
					g2D.drawImage(textures.getShipR(), x[i], y[i] + screenOffset, null);
					break;
				case 'D':
					g2D.drawImage(textures.getShipD(), x[i], y[i] + screenOffset, null);
					break;
				case 'L':
					g2D.drawImage(textures.getShipL(), x[i], y[i] + screenOffset, null);
					break;
				}
			}else {
				switch(drawDirection.get(i)) {
				case 'U':
					g2D.drawImage(textures.getShipTailU(), x[i], y[i] + screenOffset, null);
					break;
				case 'R':
					g2D.drawImage(textures.getShipTailR(), x[i], y[i] + screenOffset, null);
					break;
				case 'D':
					g2D.drawImage(textures.getShipTailD(), x[i], y[i] + screenOffset, null);
					break;
				case 'L':
					g2D.drawImage(textures.getShipTailL(), x[i], y[i] + screenOffset, null);
					break;
				}
			}
			
		}
		
		textures.incrementShipIndex();
	}
	
	//tabrakan kapal dengan dirinya sendiri
	public void objectCollision() {
		for(int i = shipCount; i>0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])){
				GPanel.running = false;
			}
		}
	}
	
	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	public char getDirection() {
		return direction;
	}
	
	public void setPrevDirection(char prevDirection) {
		this.prevDirection = prevDirection;
	}
	
	public char getPrevDirection() {
		return prevDirection;
	}
	
	public ArrayList<Character> getDrawDirection(){
		return drawDirection;
	}
	
	public boolean getIsRightSide() {
		return isRightSide;
	}
	
	public boolean getIsTakingPassengers() {
		return isTakingPassengers;
	}
	
	public void setIsPassengersDropped() {
		isPassengersDropped = false;
	}
	
	public boolean getIsPassengersDropped() {
		return isPassengersDropped;
	}

	public int getShipCount() {
		return shipCount;
	}

	public int getPassengersMax() {
		return passengersMax;
	}

	public int getPassengersCount() {
		return passengersCount;
	}

	public int getShipCapacity() {
		return shipCapacity;
	}

	public int getDestinationX() {
		return destinationX;
	}

	public int getDestinationY() {
		return destinationY;
	}

	public int getXHead() {
		return x[0];
	}

	public int getYHead() {
		return y[0];
	}
}
