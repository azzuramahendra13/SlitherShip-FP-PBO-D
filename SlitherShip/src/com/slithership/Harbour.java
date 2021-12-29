package com.slithership;

import java.awt.Graphics2D;

/*
 * Kelas ini berfungsi untuk memunculkan pelabuhan di map game dan membuat pelabuhan memiliki fungsinya. 
 */

public class Harbour extends GameObject {
	private Textures textures;
	private Ship ship;
	
	public Harbour(Textures textures, Ship ship) {
		this.textures = textures;
		this.ship = ship;
	}
	
	public void drawObject(int screenOffset, Graphics2D g2D) {
		g2D.drawImage(textures.getHarbour(), 0, screenOffset, null);
	}
	
	public void objectCollision() {
		//kapal menabrak pelabuhan bagian kiri
		if(ship.getXHead() < GPanel.UNIT_SIZE) {
			GPanel.running = false;
		}else if(ship.getXHead() == GPanel.UNIT_SIZE) {
			
			int initY1 = -6*GPanel.UNIT_SIZE;
			int initY2 = -5*GPanel.UNIT_SIZE;
			int offset = 10*GPanel.UNIT_SIZE;
			
			for(int i = 0; i <= 6; i++) {
				if(ship.getYHead() == initY1 || ship.getYHead() == initY2) {
					GPanel.running = false;
				}
				
				initY1 += offset;
				initY2 += offset;
			}
		}
		
		//kapal menabrak pelabuhan bagian kanan
		else if(ship.getXHead() >= GPanel.SCREEN_WIDTH - GPanel.UNIT_SIZE) {
			GPanel.running = false;
		}else if(ship.getXHead() == GPanel.SCREEN_WIDTH - 2*GPanel.UNIT_SIZE) {
			int initY1 = -6*GPanel.UNIT_SIZE;
			int initY2 = -5*GPanel.UNIT_SIZE;
			int offset = 10*GPanel.UNIT_SIZE;
			
			for(int i = 0; i <= 6; i++) {
				if(ship.getYHead() == initY1 || ship.getYHead() == initY2) {
					GPanel.running = false;
				}
				
				initY1 += offset;
				initY2 += offset;
			}
		}
		
		//kapal menabrak pelabuhan bagian atas
		if(ship.getYHead() < GPanel.UNIT_SIZE) {
			GPanel.running = false;
		}
		
		//kapal menabrak pelabuhan bagian bawah
		if(ship.getYHead() >= 3*GPanel.SCREEN_HEIGHT - GPanel.UNIT_SIZE) {
			GPanel.running = false;
		}
	}
}
