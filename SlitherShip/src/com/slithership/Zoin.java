package com.slithership;

import java.awt.Graphics2D;
import java.util.Random;

/*
 * Kelas ini berfungsi untuk memunculkan zoin di map game dan membuat zoin memiliki fungsinya.
 */

public class Zoin extends GameObject {
	private Textures textures;
	private Music soundEffects;
	private Ship ship;
	private Box box;
	private Random random;
	private int init = 3*GPanel.UNIT_SIZE;
	private int xArea = GPanel.SCREEN_WIDTH - 6*GPanel.UNIT_SIZE;
	private int yArea = 3*GPanel.SCREEN_HEIGHT - 6*GPanel.UNIT_SIZE;
	private int x;
	private int y;
	
	public Zoin(Textures textures, Ship ship, Box box, Music soundEffects) {
		this.textures = textures;
		this.soundEffects = soundEffects;
		this.ship = ship;
		this.box = box;
		random = new Random();
		generate();
	}
	
	public void drawObject(int screenOffset, Graphics2D g2D) {
		g2D.drawImage(textures.getZoin(), x, y + screenOffset, null);
	}
	
	//mengecek jika kapal mengambil zoin
	public void objectCollision() {
		if(ship.getXHead() == x && ship.getYHead() == y) {
			soundEffects.zoin(0.5);
			GPanel.zoin += 10;
			generate();
		}
	}
	
	//meng-generate zoin
	public void generate() {
		int tempX;
		int tempY;
		int[] boxX = box.getX();
		int[] boxY = box.getY();
		boolean checkBox = true;
		
		do {
			tempX = random.nextInt(xArea/GPanel.UNIT_SIZE)*GPanel.UNIT_SIZE + init;
			tempY = random.nextInt(yArea/GPanel.UNIT_SIZE)*GPanel.UNIT_SIZE + init;
			
			for(int i=0; i<box.getTotalBoX(); i++) {
				if(tempX == boxX[i] && tempY == boxY[i]) {
					break;
				}else if(i == box.getTotalBoX() - 1) {
					checkBox = false;
				}
			}
			
		//meng-generate posisi zoin selain pada box, dermaga, dan sekitar garasi
		}while(checkBox ||
			((tempX >= 11*GPanel.UNIT_SIZE && tempX <= 18*GPanel.UNIT_SIZE ) && 
			(tempY >= GPanel.SCREEN_HEIGHT + 8*GPanel.UNIT_SIZE && tempY <= GPanel.SCREEN_HEIGHT + 11*GPanel.UNIT_SIZE)) );
		
		x = tempX;
		y = tempY;
	}
}
