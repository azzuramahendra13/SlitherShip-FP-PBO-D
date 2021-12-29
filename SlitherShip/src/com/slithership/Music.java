package com.slithership;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * Kelas ini berfungsi untuk memutar musik dan efek suara pada game
 */

public class Music {
	private File menu;
	private File game;
	private File click;
	private File take;
	private File drop;
	private File notEnoughCapacity;
	private File zoin;
	private File buy;
	private File garageAdd;
	private File garageSub;
	private File impact1;
	private File impact2;
	private ArrayList<File> water;
	private Clip clip;
	
	private Clip clickClip;
	private AudioInputStream clickAIS;
	
	private Clip takeClip;
	private AudioInputStream takeAIS;
	
	private Clip dropClip;
	private AudioInputStream dropAIS;
	
	private Clip notEnoughCapacityClip;
	private AudioInputStream notEnoughCapacityAIS;
	
	private Clip zoinClip;
	private AudioInputStream zoinAIS;
	
	private Clip buyClip;
	private AudioInputStream buyAIS;
	
	private Clip garageAddClip;
	private AudioInputStream garageAddAIS;
	
	private Clip garageSubClip;
	private AudioInputStream garageSubAIS;
	
	private Clip impact1Clip;
	private AudioInputStream impact1AIS;
	
	private Clip impact2Clip;
	private AudioInputStream impact2AIS;
	
	private ArrayList<Clip> waterClip;
	private ArrayList<AudioInputStream> waterAIS;
	private int waterIndex = 0;
	
	private Random random;
	
	public Music() {
		menu = new File(new File("src/com/slithership/assets/Menu.wav").getAbsolutePath());
		game = new File(new File("src/com/slithership/assets/Gameplay.wav").getAbsolutePath());
		click = new File(new File("src/com/slithership/assets/soundeffects/switch_002.wav").getAbsolutePath());
		take = new File(new File("src/com/slithership/assets/soundeffects/confirmation_001.wav").getAbsolutePath());
		drop = new File(new File("src/com/slithership/assets/soundeffects/drop_002.wav").getAbsolutePath());
		notEnoughCapacity = new File(new File("src/com/slithership/assets/soundeffects/error_006.wav").getAbsolutePath());
		zoin = new File(new File("src/com/slithership/assets/soundeffects/coin_9.wav").getAbsolutePath());
		buy = new File(new File("src/com/slithership/assets/soundeffects/sell_buy_item.wav").getAbsolutePath());
		garageAdd = new File(new File("src/com/slithership/assets/soundeffects/impactMining_001.wav").getAbsolutePath());
		garageSub = new File(new File("src/com/slithership/assets/soundeffects/impactMining_002.wav").getAbsolutePath());
		impact1 = new File(new File("src/com/slithership/assets/soundeffects/rumble.wav").getAbsolutePath());
		impact2 = new File(new File("src/com/slithership/assets/soundeffects/lava.wav").getAbsolutePath());
		
		water = new ArrayList<File>();
		waterClip = new ArrayList<Clip>();
		waterAIS = new ArrayList<AudioInputStream>();
		
		water.add(new File(new File("src/com/slithership/assets/soundeffects/wave_01.wav").getAbsolutePath()));
		water.add(new File(new File("src/com/slithership/assets/soundeffects/wave_02.wav").getAbsolutePath()));
		water.add(new File(new File("src/com/slithership/assets/soundeffects/wave_03.wav").getAbsolutePath()));
		water.add(new File(new File("src/com/slithership/assets/soundeffects/wave_04.wav").getAbsolutePath()));
		
		random = new Random();
		
		try {
			clickAIS = AudioSystem.getAudioInputStream(click);
			clickClip = AudioSystem.getClip();
			clickClip.open(clickAIS);
			
			takeAIS = AudioSystem.getAudioInputStream(take);
			takeClip = AudioSystem.getClip();
			takeClip.open(takeAIS);
			
			dropAIS = AudioSystem.getAudioInputStream(drop);
			dropClip = AudioSystem.getClip();
			dropClip.open(dropAIS);
			
			notEnoughCapacityAIS = AudioSystem.getAudioInputStream(notEnoughCapacity);
			notEnoughCapacityClip = AudioSystem.getClip();
			notEnoughCapacityClip.open(notEnoughCapacityAIS);
			
			zoinAIS = AudioSystem.getAudioInputStream(zoin);
			zoinClip = AudioSystem.getClip();
			zoinClip.open(zoinAIS);
			
			buyAIS = AudioSystem.getAudioInputStream(buy);
			buyClip = AudioSystem.getClip();
			buyClip.open(buyAIS);
			
			garageAddAIS = AudioSystem.getAudioInputStream(garageAdd);
			garageAddClip = AudioSystem.getClip();
			garageAddClip.open(garageAddAIS);
			
			garageSubAIS = AudioSystem.getAudioInputStream(garageSub);
			garageSubClip = AudioSystem.getClip();
			garageSubClip.open(garageSubAIS);
			
			impact1AIS = AudioSystem.getAudioInputStream(impact1);
			impact1Clip = AudioSystem.getClip();
			impact1Clip.open(impact1AIS);
			
			impact2AIS = AudioSystem.getAudioInputStream(impact2);
			impact2Clip = AudioSystem.getClip();
			impact2Clip.open(impact2AIS);
			
			waterAIS.add(AudioSystem.getAudioInputStream(water.get(0)));
			waterAIS.add(AudioSystem.getAudioInputStream(water.get(1)));
			waterAIS.add(AudioSystem.getAudioInputStream(water.get(2)));
			waterAIS.add(AudioSystem.getAudioInputStream(water.get(3)));
			waterClip.add(AudioSystem.getClip());
			waterClip.add(AudioSystem.getClip());
			waterClip.add(AudioSystem.getClip());
			waterClip.add(AudioSystem.getClip());
			waterClip.get(0).open(waterAIS.get(0));
			waterClip.get(1).open(waterAIS.get(1));
			waterClip.get(2).open(waterAIS.get(2));
			waterClip.get(3).open(waterAIS.get(3));
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		test();
		
	}
	
	public void test() {
		click(0);
		take(0);
		drop(0);
		notEnoughCapacity(0);
		zoin(0);
		buy(0);
		garageAdd(0);
		garageSub(0);
		impact1(0);
		impact2(0);
	}
	
	public void play(GPanel.State state) {
		AudioInputStream audioStream;
		
		try {
			if(state.equals(GPanel.State.GAME)) {
				audioStream = AudioSystem.getAudioInputStream(game);
			}else {
				audioStream = AudioSystem.getAudioInputStream(menu);

			}
			
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			setVolume(clip, 0.5);
			
		}catch(UnsupportedAudioFileException e) {
			
		}catch(IOException e) {
			
		}catch(LineUnavailableException e) {
			
		}
	}
	
	public void stop() {
		clip.close();
	}
	
	public void click(double volume) {
		if(clickClip != null) {
			clickClip.stop();
			clickClip.setMicrosecondPosition(0);
		}
		
		clickClip.start();
		setVolume(clickClip, volume);
	}
	
	public void take(double volume) {
		if(takeClip != null) {
			takeClip.stop();
			takeClip.setMicrosecondPosition(0);
		}
		
		takeClip.start();
		setVolume(takeClip, volume);
	}
	
	public void drop(double volume) {
		if(dropClip != null) {
			dropClip.stop();
			dropClip.setMicrosecondPosition(0);
		}
		
		dropClip.start();
		setVolume(dropClip, volume);
	}
	
	public void notEnoughCapacity(double volume) {
		if(notEnoughCapacityClip != null) {
			notEnoughCapacityClip.stop();
			notEnoughCapacityClip.setMicrosecondPosition(0);
		}
		
		notEnoughCapacityClip.start();
		setVolume(notEnoughCapacityClip, volume);
	}
	
	public void zoin(double volume) {
		if(zoinClip != null) {
			zoinClip.stop();
			zoinClip.setMicrosecondPosition(0);
		}
		
		zoinClip.start();
		setVolume(zoinClip, volume);
	}
	
	public void buy(double volume) {
		if(buyClip != null) {
			buyClip.stop();
			buyClip.setMicrosecondPosition(0);
		}
		
		buyClip.start();
		setVolume(buyClip, volume);
	}
	
	public void garageAdd(double volume) {
		if(garageAddClip != null) {
			garageAddClip.stop();
			garageAddClip.setMicrosecondPosition(0);
		}
		
		garageAddClip.start();
		setVolume(garageAddClip, volume);
	}
	
	public void garageSub(double volume) {
		if(garageSubClip != null) {
			garageSubClip.stop();
			garageSubClip.setMicrosecondPosition(0);
		}
		
		garageSubClip.start();
		setVolume(garageSubClip, volume);
	}
	
	public void impact1(double volume) {
		if(impact1Clip != null) {
			impact1Clip.stop();
			impact1Clip.setMicrosecondPosition(0);
		}
		
		impact1Clip.start();
		setVolume(impact1Clip, volume);
	}
	
	public void impact2(double volume) {
		if(impact2Clip != null) {
			impact2Clip.stop();
			impact2Clip.setMicrosecondPosition(0);
		}
		
		impact2Clip.start();
		setVolume(impact2Clip, volume);
	}
	
	public void water(double volume) {
		if(waterClip.get(waterIndex) != null) {
			waterClip.get(waterIndex).stop();
			waterClip.get(waterIndex).setMicrosecondPosition(0);
		}
		
		waterIndex = random.nextInt(4);
		
		waterClip.get(waterIndex).start();
		setVolume(waterClip.get(waterIndex), volume);
	}
	
	private void setVolume(Clip clip, double percent1) {
		FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	    double percent = percent1;   
	    float dB = (float) (Math.log(percent) / Math.log(10.0) * 20.0);
	    volume.setValue(dB);
	}
}
