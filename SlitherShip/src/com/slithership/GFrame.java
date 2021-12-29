package com.slithership;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/*
 * Kelas ini berfungsi untuk mengatur JFrame.
 */

public class GFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7001005733084119312L;

	GFrame() {
		this.add(new GPanel());
		
		this.setTitle("Slither Ship");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		if((System.getProperty("os.name")).equals("Windows 10") || (System.getProperty("os.name")).equals("Windows 11")){
			this.setSize(976, 645);
		}else {
			this.setSize(960, 632);
		}
		
		this.setVisible(true);
		this.setIconImage(new ImageIcon(new File("src/com/slithership/assets/LogoFIX.png").getAbsolutePath()).getImage());		
		this.setLocationRelativeTo(null);
		System.out.println(new File("").getAbsolutePath());
	}
}
