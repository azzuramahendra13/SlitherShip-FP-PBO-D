package com.slithership;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/*
 * Kelas ini berfungsi untuk menampilkan tekstur setiap objek di game.
 */

public class Textures {
	private ArrayList<BufferedImage> harbour;
	private int harbourIndex = 0;
	
	private BufferedImage garageAdd;
	private BufferedImage garageSub;
	private BufferedImage garageDoorTop;
	private BufferedImage garageDoorBottom;
	
	private ArrayList<BufferedImage> shipU;
	private ArrayList<BufferedImage> shipR;
	private ArrayList<BufferedImage> shipD;
	private ArrayList<BufferedImage> shipL;
	private ArrayList<BufferedImage> shipTailU;
	private ArrayList<BufferedImage> shipTailR;
	private ArrayList<BufferedImage> shipTailD;
	private ArrayList<BufferedImage> shipTailL;
	private int shipIndex = 0;
	
	private BufferedImage score;
	private BufferedImage timer;
	private BufferedImage zoinCounter;
	
	private ArrayList<BufferedImage> box;
	private int boxIndex = 0;
	
	private ArrayList<BufferedImage> zoin;
	private int zoinIndex = 0;
	
	private BufferedImage destinationSign;
	
	private ArrayList<ArrayList<BufferedImage>> passengersL;
	private ArrayList<ArrayList<BufferedImage>> passengersR;
	private int passengersIndex = 0;
	
	private ArrayList<BufferedImage> menu;
	private int menuIndex = 0;
	
	private BufferedImage menuTitle;
	private BufferedImage menuPlay;
	private BufferedImage menuInstruction;
	private BufferedImage menuShop;
	private BufferedImage menuCredit;
	
	private BufferedImage instruction;
	private ArrayList<BufferedImage> credit;
	private int creditIndex = 0;
	
	private BufferedImage back;
	
	private ArrayList<BufferedImage> loadingScreen;
	private int loadingScreenIndex = 0;
	
	private ArrayList<BufferedImage> gameOver;
	
	private ArrayList<ArrayList<BufferedImage>> buyPassengers;
	private int buyPassengersIndex = 0;
	private int buyPassengersCount = 1;
	
	private ArrayList<BufferedImage> shopButton;
	
	private BufferedImage highScore;
		
	public Textures() {
		try {
			setHarbour();
			setGarage();
			setShipU();
			setShipR();
			setShipD();
			setShipL();
			setShipTailU();
			setShipTailR();
			setShipTailD();
			setShipTailL();
			setScore();
			setTimer();
			setZoinCounter();
			setBox();
			setZoin();
			setDestinationSign();
			setPassengers();
			setMenu();
			setMenuTitle();
			setMenuPlay();
			setMenuInstruction();
			setMenuShop();
			setMenuCredit();
			setInstruction();
			setCredit();
			setBack();
			setGameOver();
			setLoadingScreen();
			setBuyPassengers();
			setShopButton();
			setHighScore();
		}catch (IOException e) {}
		
	}
	
	private void setHarbour() throws IOException {
		harbour = new ArrayList<BufferedImage>();
		harbour.add(ImageIO.read(new File(new File("src/com/slithership/assets/harbour/InGamePel1.png").getAbsolutePath())));
		harbour.add(ImageIO.read(new File(new File("src/com/slithership/assets/harbour/InGamePel2.png").getAbsolutePath())));
		harbour.add(ImageIO.read(new File(new File("src/com/slithership/assets/harbour/InGamePel3.png").getAbsolutePath())));
		harbour.add(ImageIO.read(new File(new File("src/com/slithership/assets/harbour/InGamePel4.png").getAbsolutePath())));
		harbour.add(ImageIO.read(new File(new File("src/com/slithership/assets/harbour/InGamePel5.png").getAbsolutePath())));
		harbour.add(ImageIO.read(new File(new File("src/com/slithership/assets/harbour/InGamePel6.png").getAbsolutePath())));
		harbour.add(ImageIO.read(new File(new File("src/com/slithership/assets/harbour/InGamePel7.png").getAbsolutePath())));
		harbour.add(ImageIO.read(new File(new File("src/com/slithership/assets/harbour/InGamePel8.png").getAbsolutePath())));
		harbour.add(ImageIO.read(new File(new File("src/com/slithership/assets/harbour/InGamePel9.png").getAbsolutePath())));
		harbour.add(ImageIO.read(new File(new File("src/com/slithership/assets/harbour/InGamePel10.png").getAbsolutePath())));
		harbour.add(ImageIO.read(new File(new File("src/com/slithership/assets/harbour/InGamePel11.png").getAbsolutePath())));
		harbour.add(ImageIO.read(new File(new File("src/com/slithership/assets/harbour/InGamePel12.png").getAbsolutePath())));
		harbour.add(ImageIO.read(new File(new File("src/com/slithership/assets/harbour/InGamePel13.png").getAbsolutePath())));
		harbour.add(ImageIO.read(new File(new File("src/com/slithership/assets/harbour/InGamePel14.png").getAbsolutePath())));
		harbour.add(ImageIO.read(new File(new File("src/com/slithership/assets/harbour/InGamePel15.png").getAbsolutePath())));
	}
	
	public BufferedImage getHarbour() {
		if(harbourIndex > harbour.size() - 1) {
			harbourIndex = 0;
		}
		
		BufferedImage temp = harbour.get(harbourIndex);
		harbourIndex++;
		return temp;
	}
	
	private void setGarage() throws IOException {
		garageAdd = ImageIO.read(new File(new File("src/com/slithership/assets/garage/Garage_1.png").getAbsolutePath()));
		garageSub = ImageIO.read(new File(new File("src/com/slithership/assets/garage/Garage_0.png").getAbsolutePath()));
		garageDoorTop = ImageIO.read(new File(new File("src/com/slithership/assets/garage/PenutupGarageBawah.png").getAbsolutePath()));
		garageDoorBottom = ImageIO.read(new File(new File ("src/com/slithership/assets/garage/PenutupGarageAtas.png").getAbsolutePath()));
	}
	
	public BufferedImage getGarageAdd() {
		return garageAdd;
	}
	
	public BufferedImage getGarageSub() {
		return garageSub;
	}
	
	public BufferedImage getGarageDoorTop() {
		return garageDoorTop;
	}
	
	public BufferedImage getGarageDoorBottom(){
		return garageDoorBottom;
	}
	
	public void setShipU() throws IOException {
		shipU = new ArrayList<BufferedImage>();
		shipU.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_0u.png").getAbsolutePath())));
		shipU.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_1u.png").getAbsolutePath())));
		shipU.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_2u.png").getAbsolutePath())));
		shipU.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_3u.png").getAbsolutePath())));
	}
	
	public BufferedImage getShipU() {
		if(shipIndex > shipU.size() - 1) {
			shipIndex = 0;
		}
		
		BufferedImage temp = shipU.get(shipIndex);
		return temp;
	}
	
	public void setShipR() throws IOException {
		shipR = new ArrayList<BufferedImage>();
		shipR.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_0r.png").getAbsolutePath())));
		shipR.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_1r.png").getAbsolutePath())));
		shipR.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_2r.png").getAbsolutePath())));
		shipR.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_3r.png").getAbsolutePath())));
	}
	
	public BufferedImage getShipR() {
		if(shipIndex > shipR.size() - 1) {
			shipIndex = 0;
		}
		
		BufferedImage temp = shipR.get(shipIndex);
		return temp;
	}
	
	public void setShipD() throws IOException {
		shipD = new ArrayList<BufferedImage>();
		shipD.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_0d.png").getAbsolutePath())));
		shipD.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_1d.png").getAbsolutePath())));
		shipD.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_2d.png").getAbsolutePath())));
		shipD.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_3d.png").getAbsolutePath())));
	}
	
	public BufferedImage getShipD() {
		if(shipIndex > shipD.size() - 1) {
			shipIndex = 0;
		}
		
		BufferedImage temp = shipD.get(shipIndex);
		return temp;
	}
	
	public void setShipL() throws IOException {
		shipL = new ArrayList<BufferedImage>();
		shipL.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_0l.png").getAbsolutePath())));
		shipL.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_1l.png").getAbsolutePath())));
		shipL.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_2l.png").getAbsolutePath())));
		shipL.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Kapal_3l.png").getAbsolutePath())));
	}
	
	public BufferedImage getShipL() {
		if(shipIndex > shipL.size() - 1) {
			shipIndex = 0;
		}
		
		BufferedImage temp = shipL.get(shipIndex);
		return temp;
	}
	
	public void setShipTailU() throws IOException {
		shipTailU = new ArrayList<BufferedImage>();
		shipTailU.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_0u.png").getAbsolutePath())));
		shipTailU.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_1u.png").getAbsolutePath())));
		shipTailU.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_2u.png").getAbsolutePath())));
		shipTailU.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_3u.png").getAbsolutePath())));
	}
	
	public BufferedImage getShipTailU() {
		if(shipIndex > shipTailU.size() - 1) {
			shipIndex = 0;
		}
		
		BufferedImage temp = shipTailU.get(shipIndex);
		return temp;
	}
	
	public void setShipTailR() throws IOException {
		shipTailR = new ArrayList<BufferedImage>();
		shipTailR.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_0r.png").getAbsolutePath())));
		shipTailR.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_1r.png").getAbsolutePath())));
		shipTailR.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_2r.png").getAbsolutePath())));
		shipTailR.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_3r.png").getAbsolutePath())));
	}
	
	public BufferedImage getShipTailR() {
		if(shipIndex > shipTailR.size() - 1) {
			shipIndex = 0;
		}
		
		BufferedImage temp = shipTailR.get(shipIndex);
		return temp;
	}
	
	public void setShipTailD() throws IOException {
		shipTailD = new ArrayList<BufferedImage>();
		shipTailD.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_0d.png").getAbsolutePath())));
		shipTailD.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_1d.png").getAbsolutePath())));
		shipTailD.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_2d.png").getAbsolutePath())));
		shipTailD.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_3d.png").getAbsolutePath())));
	}
	
	public BufferedImage getShipTailD() {
		if(shipIndex > shipTailD.size() - 1) {
			shipIndex = 0;
		}
		
		BufferedImage temp = shipTailD.get(shipIndex);
		return temp;
	}
	
	public void setShipTailL() throws IOException {
		shipTailL = new ArrayList<BufferedImage>();
		shipTailL.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_0l.png").getAbsolutePath())));
		shipTailL.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_1l.png").getAbsolutePath())));
		shipTailL.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_2l.png").getAbsolutePath())));
		shipTailL.add(ImageIO.read(new File(new File("src/com/slithership/assets/ship/Gerbong_3l.png").getAbsolutePath())));
	}
	
	public BufferedImage getShipTailL() {
		if(shipIndex > shipTailL.size() - 1) {
			shipIndex = 0;
		}
		
		BufferedImage temp = shipTailL.get(shipIndex);
		return temp;
	}
	
	public void incrementShipIndex() {
		shipIndex++;
	}
	
	public void setScore() throws IOException {
		score = ImageIO.read(new File(new File("src/com/slithership/assets/UI/JumlahScore.png").getAbsolutePath()));
	}
	
	public BufferedImage getScore() {
		return score;
	}
	
	public void setTimer() throws IOException{
		timer = ImageIO.read(new File(new File("src/com/slithership/assets/UI/Timer.png").getAbsolutePath()));
	}
	
	public BufferedImage getTimer() {
		return timer;
	}
	
	public void setZoinCounter() throws IOException {
		zoinCounter = ImageIO.read(new File(new File("src/com/slithership/assets/UI/JumlahZoin.png").getAbsolutePath()));
	}
	
	public BufferedImage getZoinCounter() {
		return zoinCounter;
	}
	
	public void setBox() throws IOException {
		box = new ArrayList<BufferedImage>();
		box.add(ImageIO.read(new File(new File("src/com/slithership/assets/box/Box_0.png").getAbsolutePath())));
		box.add(ImageIO.read(new File(new File("src/com/slithership/assets/box/Box_1.png").getAbsolutePath())));
		box.add(ImageIO.read(new File(new File("src/com/slithership/assets/box/Box_2.png").getAbsolutePath())));
	}
	
	public BufferedImage getBox() {
		if(boxIndex > box.size() - 1) {
			boxIndex = 0;
		}
		
		BufferedImage temp = box.get(boxIndex);
		boxIndex++;
		return temp;
	}
	
	public void setZoin() throws IOException {
		zoin = new ArrayList<BufferedImage>();
		zoin.add(ImageIO.read(new File(new File("src/com/slithership/assets/zoin/Zoin_0.png").getAbsolutePath())));
		zoin.add(ImageIO.read(new File(new File("src/com/slithership/assets/zoin/Zoin_1.png").getAbsolutePath())));
		zoin.add(ImageIO.read(new File(new File("src/com/slithership/assets/zoin/Zoin_2.png").getAbsolutePath())));
		zoin.add(ImageIO.read(new File(new File("src/com/slithership/assets/zoin/Zoin_3.png").getAbsolutePath())));
	}
	
	public BufferedImage getZoin() {
		if(zoinIndex > zoin.size() - 1) {
			zoinIndex = 0;
		}
		
		BufferedImage temp = zoin.get(zoinIndex);
		zoinIndex++;
		return temp;
	}
	
	public void setDestinationSign() throws IOException {
		destinationSign = ImageIO.read(new File(new File("src/com/slithership/assets/passengers/Arah_kosong.png").getAbsolutePath()));
	}
	
	public BufferedImage getDestinationSign() {
		return destinationSign;
	}
	
	public void setPassengers() throws IOException {
		passengersL = new ArrayList<ArrayList<BufferedImage>>();
		passengersR = new ArrayList<ArrayList<BufferedImage>>();

		for(int i=1; i<=2; i++) {
			ArrayList<BufferedImage> temp = new ArrayList<BufferedImage>();
			
			temp.add(ImageIO.read(new File(new File("src/com/slithership/assets/passengers/Manusia" + i + "r_0.png").getAbsolutePath())));
			temp.add(ImageIO.read(new File(new File("src/com/slithership/assets/passengers/Manusia" + i + "r_1.png").getAbsolutePath())));
			temp.add(ImageIO.read(new File(new File("src/com/slithership/assets/passengers/Manusia" + i + "r_2.png").getAbsolutePath())));
			passengersL.add(temp);
		}
		
		for(int i=1; i<=2; i++) {
			ArrayList<BufferedImage> temp = new ArrayList<BufferedImage>();
			
			temp.add(ImageIO.read(new File(new File("src/com/slithership/assets/passengers/Manusia" + i + "l_0.png").getAbsolutePath())));
			temp.add(ImageIO.read(new File(new File("src/com/slithership/assets/passengers/Manusia" + i + "l_1.png").getAbsolutePath())));
			temp.add(ImageIO.read(new File(new File("src/com/slithership/assets/passengers/Manusia" + i + "l_2.png").getAbsolutePath())));
			passengersR.add(temp);
		}
	}
	
	public BufferedImage getPassengersL(int type) {
		if(passengersIndex > passengersL.get(0).size() - 1) {
			passengersIndex = 0;
		}
		
		BufferedImage temp = passengersL.get(type).get(passengersIndex);
		return temp;
	}
	
	public BufferedImage getPassengersR(int type) {
		if(passengersIndex > passengersR.get(0).size() - 1) {
			passengersIndex = 0;
		}
		
		BufferedImage temp = passengersR.get(type).get(passengersIndex);
		return temp;
	}
	
	public void incrementPassengersIndex() {
		passengersIndex++;

	}
	
	public void setMenu() throws IOException{
		menu = new ArrayList<BufferedImage>();
		menu.add(ImageIO.read(new File(new File("src/com/slithership/assets/menu/Menu_0.png").getAbsolutePath())));
		menu.add(ImageIO.read(new File(new File("src/com/slithership/assets/menu/Menu_1.png").getAbsolutePath())));
		menu.add(ImageIO.read(new File(new File("src/com/slithership/assets/menu/Menu_2.png").getAbsolutePath())));
		menu.add(ImageIO.read(new File(new File("src/com/slithership/assets/menu/Menu_3.png").getAbsolutePath())));
		menu.add(ImageIO.read(new File(new File("src/com/slithership/assets/menu/Menu_4.png").getAbsolutePath())));
		menu.add(ImageIO.read(new File(new File("src/com/slithership/assets/menu/Menu_5.png").getAbsolutePath())));
		menu.add(ImageIO.read(new File(new File("src/com/slithership/assets/menu/Menu_6.png").getAbsolutePath())));
		menu.add(ImageIO.read(new File(new File("src/com/slithership/assets/menu/Menu_7.png").getAbsolutePath())));

	}
	
	public BufferedImage getMenu() {
		if(menuIndex > menu.size() - 1) {
			menuIndex = 0;
		}
		
		BufferedImage temp = menu.get(menuIndex);
		menuIndex++;
		return temp;	
	}
	
	public void setMenuTitle() throws IOException {
		menuTitle = ImageIO.read(new File(new File("src/com/slithership/assets/menu/SlitherShip.png").getAbsolutePath()));
	}
	
	public BufferedImage getMenuTitle() {
		return menuTitle;
	}
	
	public void setMenuPlay() throws IOException {
		menuPlay = ImageIO.read(new File(new File("src/com/slithership/assets/menu/Play.png").getAbsolutePath()));
	}
	
	public BufferedImage getMenuPlay() {
		return menuPlay;
	}
	
	public void setMenuInstruction() throws IOException {
		menuInstruction = ImageIO.read(new File(new File("src/com/slithership/assets/menu/Instruction.png").getAbsolutePath()));
	}
	
	public BufferedImage getMenuInstruction() {
		return menuInstruction;
	}
	
	public void setMenuShop() throws IOException {
		menuShop = ImageIO.read(new File(new File("src/com/slithership/assets/menu/Shop.png").getAbsolutePath()));
	}
	
	public BufferedImage getMenuShop() {
		return menuShop;
	}
	
	public void setMenuCredit() throws IOException {
		menuCredit = ImageIO.read(new File(new File("src/com/slithership/assets/menu/Credit.png").getAbsolutePath()));
	}
	
	public BufferedImage getMenuCredit() {
		return menuCredit;
	}
	
	public void setInstruction() throws IOException {
		instruction = ImageIO.read(new File(new File("src/com/slithership/assets/menu/INSTRUKSI-export.png").getAbsolutePath()));
	}
	
	public BufferedImage getInstruction() {
		return instruction;
	}
	
	public void setCredit() throws IOException {
		credit = new ArrayList<BufferedImage>();
		credit.add(ImageIO.read(new File(new File("src/com/slithership/assets/menu/Credit_0.png").getAbsolutePath())));
		credit.add(ImageIO.read(new File(new File("src/com/slithership/assets/menu/Credit_1.png").getAbsolutePath())));
		credit.add(ImageIO.read(new File(new File("src/com/slithership/assets/menu/Credit_2.png").getAbsolutePath())));
		credit.add(ImageIO.read(new File(new File("src/com/slithership/assets/menu/Credit_3.png").getAbsolutePath())));
	}
	
	public BufferedImage getCredit() {
		if(creditIndex > credit.size() - 1) {
			creditIndex = 0;
		}
		
		BufferedImage temp = credit.get(creditIndex);
		creditIndex++;
		return temp;
	}
	
	public void setBack() throws IOException {
		back = ImageIO.read(new File(new File("src/com/slithership/assets/menu/Back.png").getAbsolutePath()));
	}
	
	public BufferedImage getBack() {
		return back;
	}
	
	public void setGameOver() throws IOException {
		gameOver = new ArrayList<BufferedImage>();
		gameOver.add(ImageIO.read(new File(new File("src/com/slithership/assets/gameover/GameOver.png").getAbsolutePath())));
		gameOver.add(ImageIO.read(new File(new File("src/com/slithership/assets/gameover/MenuButton.png").getAbsolutePath())));
		gameOver.add(ImageIO.read(new File(new File("src/com/slithership/assets/gameover/RestartButton.png").getAbsolutePath())));
		gameOver.add(ImageIO.read(new File(new File("src/com/slithership/assets/gameover/Score.png").getAbsolutePath())));
	}
	
	public BufferedImage getGameOver(int index) {
		BufferedImage temp = gameOver.get(index);
		return temp;
	}
	
	public void setLoadingScreen() throws IOException {
		loadingScreen = new ArrayList<BufferedImage>();
		loadingScreen.add(ImageIO.read(new File(new File("src/com/slithership/assets/loadingscreen/Loading_0.png").getAbsolutePath())));
		loadingScreen.add(ImageIO.read(new File(new File("src/com/slithership/assets/loadingscreen/Loading_1.png").getAbsolutePath())));
		loadingScreen.add(ImageIO.read(new File(new File("src/com/slithership/assets/loadingscreen/Loading_2.png").getAbsolutePath())));
		loadingScreen.add(ImageIO.read(new File(new File("src/com/slithership/assets/loadingscreen/Loading_3.png").getAbsolutePath())));
		loadingScreen.add(ImageIO.read(new File(new File("src/com/slithership/assets/loadingscreen/Loading_4.png").getAbsolutePath())));
		loadingScreen.add(ImageIO.read(new File(new File("src/com/slithership/assets/loadingscreen/Loading_5.png").getAbsolutePath())));
		loadingScreen.add(ImageIO.read(new File(new File("src/com/slithership/assets/loadingscreen/Loading_6.png").getAbsolutePath())));
		loadingScreen.add(ImageIO.read(new File(new File("src/com/slithership/assets/loadingscreen/Loading_7.png").getAbsolutePath())));
		loadingScreen.add(ImageIO.read(new File(new File("src/com/slithership/assets/loadingscreen/Loading_8.png").getAbsolutePath())));
		loadingScreen.add(ImageIO.read(new File(new File("src/com/slithership/assets/loadingscreen/Loading_9.png").getAbsolutePath())));
		loadingScreen.add(ImageIO.read(new File(new File("src/com/slithership/assets/loadingscreen/Loading_10.png").getAbsolutePath())));
		loadingScreen.add(ImageIO.read(new File(new File("src/com/slithership/assets/loadingscreen/Loading_10.png").getAbsolutePath())));
		loadingScreen.add(ImageIO.read(new File(new File("src/com/slithership/assets/loadingscreen/Loading_10.png").getAbsolutePath())));
		loadingScreen.add(ImageIO.read(new File(new File("src/com/slithership/assets/loadingscreen/Loading_10.png").getAbsolutePath())));
	}
	
	public BufferedImage getLoadingScreen() {
		BufferedImage temp = loadingScreen.get(loadingScreenIndex);
		return temp;
	}
	
	public void incrementLoadingScreen() {
		loadingScreenIndex++;
	}
	
	public int getLoadingScreenIndex() {
		return loadingScreenIndex;
	}
	
	public void resetLoadingScreenIndex() {
		loadingScreenIndex = 0;
	}
	
	public void setBuyPassengers() throws IOException {
		buyPassengers = new ArrayList<ArrayList<BufferedImage>>();
		
		for(int i=1; i<=buyPassengersCount; i++) {
			ArrayList<BufferedImage> temp = new ArrayList<BufferedImage>();
			
			temp.add(ImageIO.read(new File(new File("src/com/slithership/assets/shop/MenuSHOP_0.png").getAbsolutePath())));
			temp.add(ImageIO.read(new File(new File("src/com/slithership/assets/shop/MenuSHOP_1.png").getAbsolutePath())));
			temp.add(ImageIO.read(new File(new File("src/com/slithership/assets/shop/MenuSHOP_2.png").getAbsolutePath())));
			
			buyPassengers.add(temp);
		}
	}
	
	public BufferedImage getBuyPassengers(int type) {
		if(buyPassengersIndex > buyPassengers.get(0).size() - 1) {
			buyPassengersIndex = 0;
		}
		
		BufferedImage temp = buyPassengers.get(type).get(buyPassengersIndex);
		buyPassengersIndex++;
		return temp;
	}
	
	public void setShopButton() throws IOException {
		shopButton = new ArrayList<BufferedImage>();
		
		shopButton.add(ImageIO.read(new File(new File("src/com/slithership/assets/shop/Back.png").getAbsolutePath())));
		shopButton.add(ImageIO.read(new File(new File("src/com/slithership/assets/shop/Buy.png").getAbsolutePath())));
		shopButton.add(ImageIO.read(new File(new File("src/com/slithership/assets/shop/Zoin120px.png").getAbsolutePath())));
	}
	
	public BufferedImage getShopButton(int index) {
		BufferedImage temp = shopButton.get(index);
		return temp;
	}
	
	public void setHighScore() throws IOException {
		highScore = ImageIO.read(new File(new File("src/com/slithership/assets/menu/HighScore.png").getAbsolutePath()));
	}
	
	public BufferedImage getHighScore() {
		return highScore;
	}
}
