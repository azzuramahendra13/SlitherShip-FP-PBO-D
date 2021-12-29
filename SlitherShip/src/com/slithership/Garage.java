package com.slithership;

import java.awt.Graphics2D;

/*
 * Kelas ini berfungsi untuk memunculkan garage di map game dan membuat garage memiliki fungsinya.
 */

public class Garage extends GameObject {
	private Textures textures;
	private Ship ship;
	
	public Garage(Ship ship, Textures textures) {
		this.ship = ship;
		this.textures = textures;
	}
	
	public void drawObject(int screenOffset, Graphics2D g2D) {
		g2D.drawImage(textures.getGarageAdd(), 11*GPanel.UNIT_SIZE , GPanel.SCREEN_HEIGHT + 9*GPanel.UNIT_SIZE + screenOffset, null);
		g2D.drawImage(textures.getGarageSub(), 16*GPanel.UNIT_SIZE , GPanel.SCREEN_HEIGHT + 9*GPanel.UNIT_SIZE + screenOffset, null);
		
		if(!ship.getIsTakingPassengers()) {
			g2D.drawImage(textures.getGarageDoorTop(), 17*GPanel.UNIT_SIZE, GPanel.SCREEN_HEIGHT + 8*GPanel.UNIT_SIZE + screenOffset, null);
			g2D.drawImage(textures.getGarageDoorBottom(), 17*GPanel.UNIT_SIZE, GPanel.SCREEN_HEIGHT + 11*GPanel.UNIT_SIZE + screenOffset, null);
		}
	}
	
	public void objectCollision() {
		//kapal menabrak garage penambah gerbong
		if(ship.getXHead() == 11*GPanel.UNIT_SIZE || ship.getXHead() == 13*GPanel.UNIT_SIZE) {
			if(ship.getYHead() == GPanel.SCREEN_HEIGHT + 9*GPanel.UNIT_SIZE || ship.getYHead() == GPanel.SCREEN_HEIGHT + 10*GPanel.UNIT_SIZE) {
				GPanel.running = false;
			}
		}
		
		//kapal menabrak garage pengurang gerbong
		else if(ship.getXHead() == 16*GPanel.UNIT_SIZE || ship.getXHead() == 18*GPanel.UNIT_SIZE) {
			if(ship.getYHead() == GPanel.SCREEN_HEIGHT + 9*GPanel.UNIT_SIZE || ship.getYHead() == GPanel.SCREEN_HEIGHT + 10*GPanel.UNIT_SIZE) {
				GPanel.running = false;
			}
		}
		
		//kapal menabrak penutup garage pengurang gerbong
		else if(!ship.getIsTakingPassengers()) {
			if(ship.getXHead() == 17*GPanel.UNIT_SIZE && (ship.getYHead() == GPanel.SCREEN_HEIGHT + 8*GPanel.UNIT_SIZE || ship.getYHead() == GPanel.SCREEN_HEIGHT + 11*GPanel.UNIT_SIZE)) {
				GPanel.running = false;
			}
		}
	}
}
