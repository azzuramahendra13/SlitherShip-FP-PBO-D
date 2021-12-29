package com.slithership;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/*
 * Kelas ini berfungsi untuk memunculkan penumpang di map game dan membuat penumpang memiliki fungsinya.
 */

public class Passengers extends GameObject {
	private Textures textures;
	private Ship ship;
	private GPanel gPanel;
	private int i = 2;
	private int j = 1;
	private int dropX;
	private int dropY;
	private boolean isRight;
	
	public Passengers(Textures textures, Ship ship, GPanel gPanel) {
		this.textures = textures;
		this.ship = ship;
		this.gPanel = gPanel;
	}
	
	public void drawObject(int screenOffset, Graphics2D g2D) {
		FontMetrics metrics = gPanel.getFontMetrics(g2D.getFont());
		
		if(ship.getIsRightSide()) {
			g2D.drawImage(textures.getDestinationSign(), ship.getDestinationX() + GPanel.UNIT_SIZE, ship.getDestinationY() - GPanel.UNIT_SIZE + screenOffset, null);
			
			if(ship.getIsTakingPassengers()) {
				g2D.setColor(Color.RED);
				g2D.setFont(new Font("Noto Sans", Font.BOLD, 15));
				g2D.drawString(String.valueOf(ship.getPassengersCount()), ship.getDestinationX() + GPanel.UNIT_SIZE + 14 - metrics.stringWidth(String.valueOf(ship.getPassengersCount()))/2 , ship.getDestinationY() - 11 + screenOffset);
				
				g2D.drawImage(textures.getPassengersR(GPanel.passengersType), ship.getDestinationX() + (2 + i)*GPanel.UNIT_SIZE + 6, ship.getDestinationY() + screenOffset, null);
				g2D.drawImage(textures.getPassengersR(GPanel.passengersType), ship.getDestinationX() + (2 + i)*GPanel.UNIT_SIZE + 6, ship.getDestinationY() + GPanel.UNIT_SIZE + screenOffset, null);
				g2D.drawImage(textures.getPassengersR(GPanel.passengersType), ship.getDestinationX() + (2 + i)*GPanel.UNIT_SIZE - GPanel.UNIT_SIZE, ship.getDestinationY() + screenOffset, null);
				g2D.drawImage(textures.getPassengersR(GPanel.passengersType), ship.getDestinationX() + (2 + i)*GPanel.UNIT_SIZE - GPanel.UNIT_SIZE, ship.getDestinationY() + GPanel.UNIT_SIZE + screenOffset, null);
				g2D.drawImage(textures.getPassengersR(GPanel.passengersType), ship.getDestinationX() + (2 + i)*GPanel.UNIT_SIZE + 6, ship.getDestinationY() - GPanel.UNIT_SIZE + screenOffset, null);
				g2D.drawImage(textures.getPassengersR(GPanel.passengersType), ship.getDestinationX() + (2 + i)*GPanel.UNIT_SIZE + 6, ship.getDestinationY() + 2*GPanel.UNIT_SIZE + screenOffset, null);
				g2D.drawImage(textures.getPassengersR(GPanel.passengersType), ship.getDestinationX() + (2 + i)*GPanel.UNIT_SIZE + 6, ship.getDestinationY() - 2*GPanel.UNIT_SIZE + screenOffset, null);
				g2D.drawImage(textures.getPassengersR(GPanel.passengersType), ship.getDestinationX() + (2 + i)*GPanel.UNIT_SIZE + 6, ship.getDestinationY() + 3*GPanel.UNIT_SIZE + screenOffset, null);
				
				if(i != 0) i--;				
			}else {
				i = 2;
			}
		
		}else if(!ship.getIsRightSide()) {
			g2D.drawImage(textures.getDestinationSign(), ship.getDestinationX() - GPanel.UNIT_SIZE, ship.getDestinationY() - GPanel.UNIT_SIZE + screenOffset, null);

			if(ship.getIsTakingPassengers()) {
				g2D.setColor(Color.RED);
				g2D.setFont(new Font("Noto Sans", Font.BOLD, 15));
				g2D.drawString(String.valueOf(ship.getPassengersCount()), ship.getDestinationX() - GPanel.UNIT_SIZE + 14 - metrics.stringWidth(String.valueOf(ship.getPassengersCount()))/2 , ship.getDestinationY() - 11 + screenOffset);
				
				g2D.drawImage(textures.getPassengersL(GPanel.passengersType), ship.getDestinationX() - (2 + i)*GPanel.UNIT_SIZE - 6, ship.getDestinationY() + screenOffset, null);
				g2D.drawImage(textures.getPassengersL(GPanel.passengersType), ship.getDestinationX() - (2 + i)*GPanel.UNIT_SIZE - 6, ship.getDestinationY() + GPanel.UNIT_SIZE + screenOffset, null);
				g2D.drawImage(textures.getPassengersL(GPanel.passengersType), ship.getDestinationX() - (2 + i)*GPanel.UNIT_SIZE + GPanel.UNIT_SIZE, ship.getDestinationY() + screenOffset, null);
				g2D.drawImage(textures.getPassengersL(GPanel.passengersType), ship.getDestinationX() - (2 + i)*GPanel.UNIT_SIZE + GPanel.UNIT_SIZE, ship.getDestinationY() + GPanel.UNIT_SIZE + screenOffset, null);
				g2D.drawImage(textures.getPassengersL(GPanel.passengersType), ship.getDestinationX() - (2 + i)*GPanel.UNIT_SIZE - 6, ship.getDestinationY() - GPanel.UNIT_SIZE + screenOffset, null);
				g2D.drawImage(textures.getPassengersL(GPanel.passengersType), ship.getDestinationX() - (2 + i)*GPanel.UNIT_SIZE - 6, ship.getDestinationY() + 2*GPanel.UNIT_SIZE + screenOffset, null);
				g2D.drawImage(textures.getPassengersL(GPanel.passengersType), ship.getDestinationX() - (2 + i)*GPanel.UNIT_SIZE - 6, ship.getDestinationY() - 2*GPanel.UNIT_SIZE + screenOffset, null);
				g2D.drawImage(textures.getPassengersL(GPanel.passengersType), ship.getDestinationX() - (2 + i)*GPanel.UNIT_SIZE - 6, ship.getDestinationY() + 3*GPanel.UNIT_SIZE + screenOffset, null);
				
				if(i != 0) i--;				
			}else {
				i = 2;
			}
			
		}
		
		if(!ship.getIsPassengersDropped()) {
			dropX = ship.getDestinationX();
			dropY = ship.getDestinationY();
			isRight = ship.getIsRightSide();
		}
		
		if(ship.getIsPassengersDropped()) {
			if(isRight) {
				if(j>0) {
					g2D.drawImage(textures.getPassengersL(GPanel.passengersType), dropX + j*GPanel.UNIT_SIZE, dropY + GPanel.UNIT_SIZE + screenOffset, null);
					g2D.drawImage(textures.getPassengersL(GPanel.passengersType), dropX + j*GPanel.UNIT_SIZE, dropY + screenOffset, null);
				}
				
				if(j>1) {
					g2D.drawImage(textures.getPassengersL(GPanel.passengersType), dropX + (j-1)*GPanel.UNIT_SIZE, dropY + GPanel.UNIT_SIZE + screenOffset, null);
					g2D.drawImage(textures.getPassengersL(GPanel.passengersType), dropX + (j-1)*GPanel.UNIT_SIZE, dropY + screenOffset, null);
				}
				
				if(j>2) {
					g2D.drawImage(textures.getPassengersL(GPanel.passengersType), dropX + (j-2)*GPanel.UNIT_SIZE, dropY + GPanel.UNIT_SIZE + screenOffset, null);
					g2D.drawImage(textures.getPassengersL(GPanel.passengersType), dropX + (j-3)*GPanel.UNIT_SIZE, dropY + screenOffset, null);
				}
				
			}else if(!isRight) {
				if(j>0) {
					g2D.drawImage(textures.getPassengersR(GPanel.passengersType), dropX - j*GPanel.UNIT_SIZE, dropY + GPanel.UNIT_SIZE + screenOffset, null);
					g2D.drawImage(textures.getPassengersR(GPanel.passengersType), dropX - j*GPanel.UNIT_SIZE, dropY + screenOffset, null);
				}
				
				if(j>1) {
					g2D.drawImage(textures.getPassengersR(GPanel.passengersType), dropX - (j-1)*GPanel.UNIT_SIZE, dropY + GPanel.UNIT_SIZE + screenOffset, null);
					g2D.drawImage(textures.getPassengersR(GPanel.passengersType), dropX - (j-1)*GPanel.UNIT_SIZE, dropY + screenOffset, null);
				}
				
				if(j>2) {
					g2D.drawImage(textures.getPassengersR(GPanel.passengersType), dropX - (j-2)*GPanel.UNIT_SIZE, dropY + GPanel.UNIT_SIZE + screenOffset, null);
					g2D.drawImage(textures.getPassengersR(GPanel.passengersType), dropX - (j-2)*GPanel.UNIT_SIZE, dropY + screenOffset, null);
				}

			}
			
			j++;
			if(j == 5) {
				j = 1;
				ship.setIsPassengersDropped();
			}
		}
		
		textures.incrementPassengersIndex();
	}
	
	public void objectCollision() {
		
	}
	
}
