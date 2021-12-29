package com.slithership;

import java.awt.Graphics2D;

/*
 * Kelas abstrak ini berfungsi sebagai parent dari seluruh kelas dari objek yang muncul di game 
 * (Harbour, Garage, Passengers, Ship, UI, dan Zoin). Kelas ini mewajibkan seluruh childnya untuk
 * mengimplementasi method drawObject dan objectCollision.
 */

public abstract class GameObject {
	public abstract void drawObject(int screenOffset, Graphics2D g2D);
	public abstract void objectCollision();
}
