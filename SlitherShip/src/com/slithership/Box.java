package com.slithership;

import java.awt.Graphics2D;
import java.util.Random;

/*
 * Kelas ini berfungsi untuk memunculkan box di map game dan membuat box memiliki fungsinya.
 */

public class Box extends GameObject {
	private Textures textures;
	private Ship ship;
	private Random random;
	private int totalBox = 50;
	private int init = 2*GPanel.UNIT_SIZE;
	private int xArea = GPanel.SCREEN_WIDTH - 4*GPanel.UNIT_SIZE;
	private int yArea = 3*GPanel.SCREEN_HEIGHT - 4*GPanel.UNIT_SIZE;
	private int[] x = new int[xArea];
	private int[] y = new int[yArea];
	
	public Box(Textures textures, Ship ship) {
		this.textures = textures;
		this.ship = ship;
		random = new Random();
		generate();
	}
	
	public void drawObject(int screenOffset, Graphics2D g2D) {
		for(int i=0; i<totalBox; i++) {
			g2D.drawImage(textures.getBox(), x[i], y[i] + screenOffset, null);
		}
	}
	
	public void objectCollision() {
		for(int i=0; i<totalBox; i++) {
			if(ship.getXHead() == x[i] && ship.getYHead() == y[i]) {
				GPanel.running = false;
				break;
			}
		}
	}
	
	//untuk meng-generate box
	public void generate() {
		int tempX;
		int tempY;
		
		for(int i=0; i<totalBox; i++) {
			
			do {
				tempX = random.nextInt(xArea/GPanel.UNIT_SIZE)*GPanel.UNIT_SIZE + init;
				tempY = random.nextInt(yArea/GPanel.UNIT_SIZE)*GPanel.UNIT_SIZE + init;
			}
			
			//meng-generate posisi box selain di sekitar garage, titik awal kapal, dan dermaga
			while( ((tempX >= 11*GPanel.UNIT_SIZE && tempX <= 18*GPanel.UNIT_SIZE ) && 
					(tempY >= GPanel.SCREEN_HEIGHT + 8*GPanel.UNIT_SIZE && tempY <= GPanel.SCREEN_HEIGHT + 11*GPanel.UNIT_SIZE)) ||
					(tempX == ship.getXHead() && (tempY >= ship.getYHead() - 5*GPanel.UNIT_SIZE && tempY <= ship.getYHead() + 5*GPanel.UNIT_SIZE)) ||
					((tempX == 2*GPanel.UNIT_SIZE || tempX == GPanel.SCREEN_WIDTH - 3*GPanel.UNIT_SIZE) && (tempY == 4*GPanel.UNIT_SIZE || tempY == 14*GPanel.UNIT_SIZE || tempY == 24*GPanel.UNIT_SIZE ||
					tempY == 34*GPanel.UNIT_SIZE || tempY == 44*GPanel.UNIT_SIZE || tempY == 54*GPanel.UNIT_SIZE))
					);
			
			x[i] = tempX;
			y[i] = tempY;
		}
	}
	
	public int[] getX() {
		return x;
	}
	
	public int[] getY() {
		return y;
	}
	
	public int getTotalBoX() {
		return totalBox;
	}
}
