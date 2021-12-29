package com.slithership;

import java.util.Timer;

/*
 * Kelas ini dibuat agar game dapat menggunakan java.util.Timer karena akan terjadi overlap jika
 * package ini di-import pada kelas GPanel.
 */

public class GameTimer extends Timer {
	
	public GameTimer() {
		
	}
	
}
