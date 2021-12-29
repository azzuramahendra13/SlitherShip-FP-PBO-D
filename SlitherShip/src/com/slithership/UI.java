package com.slithership;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/*
 * Kelas ini berfungsi untuk memunculkan jumlah zoin, pewaktu, dan skor.
 */

public class UI extends GameObject {
	private Textures textures;
	private GPanel gPanel;
	
	public UI(Textures textures, GPanel gPanel) {
		this.textures = textures;
		this.gPanel = gPanel;
	}
	
	public void drawObject(int screenOffset, Graphics2D g2D) {
		FontMetrics metrics = gPanel.getFontMetrics(g2D.getFont());
		
		//jumlah zoin
		g2D.setColor(Color.BLACK);
		g2D.setFont(new Font("Noto Sans", Font.BOLD, 14));
		g2D.drawImage(textures.getZoinCounter(), GPanel.SCREEN_WIDTH / 2 - 158, 5/2 * GPanel.UNIT_SIZE, null);
		g2D.drawString(String.valueOf(GPanel.zoin), GPanel.SCREEN_WIDTH / 2 - 103 - metrics.stringWidth(String.valueOf(GPanel.zoin))/2, 86);
		
		//pewaktu
		g2D.setColor(Color.WHITE);
		g2D.setFont(new Font("Noto Sans", Font.BOLD, 14));
		g2D.drawImage(textures.getTimer(), GPanel.SCREEN_WIDTH / 2 - 30, 5/2 * GPanel.UNIT_SIZE, null);
		g2D.drawString(String.valueOf(GPanel.gameTime), GPanel.SCREEN_WIDTH / 2 - 15 - metrics.stringWidth(String.valueOf(GPanel.gameTime))/2, 85);
		
		//skor
		g2D.setColor(Color.BLACK);
		g2D.setFont(new Font("Noto Sans", Font.BOLD, 14));
		g2D.drawImage(textures.getScore(), GPanel.SCREEN_WIDTH / 2 + 32, 5/2 * GPanel.UNIT_SIZE, null);
		g2D.drawString(String.valueOf(GPanel.score), GPanel.SCREEN_WIDTH / 2 + 112 - metrics.stringWidth(String.valueOf(GPanel.score))/2, 86);
	}
	
	public void objectCollision() {
		
	}
}
