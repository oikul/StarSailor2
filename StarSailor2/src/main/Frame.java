package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import game.Galaxy;
import menu.ConnectMenu;
import menu.LoadGameMenu;
import menu.MainMenu;
import menu.NewGameMenu;
import utils.AbstractMain;
import utils.InputHandler;
import utils.MathHelper;
import utils.State;

public class Frame extends AbstractMain {

	private static final long serialVersionUID = 1L;
	private MainMenu menu;
	private NewGameMenu newGameMenu;
	private LoadGameMenu loadGameMenu;
	private ConnectMenu connectMenu;
	private Galaxy galaxy;
	private InputHandler input;

	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.run();
	}

	@Override
	public void initialise() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Star Sailor 2");
		setSize(InputHandler.screenSize);
		input = new InputHandler(this);
		menu = new MainMenu();
		newGameMenu = new NewGameMenu();
		loadGameMenu = new LoadGameMenu();
		connectMenu = new ConnectMenu();
		add(menu);
		setVisible(true);
		running = true;
		State.state = State.STATE.MAIN_MENU;
	}

	@Override
	public void update() {
		switch (State.state) {
		case MAIN_MENU:
			if (menu.isNewGame) {
				remove(menu);
				add(newGameMenu);
				repaint();
				setVisible(true);
				State.state = State.STATE.NEW_GAME_MENU;
				menu.isNewGame = false;
			}
			if (menu.isLoadGame) {
				remove(menu);
				add(loadGameMenu);
				repaint();
				setVisible(true);
				State.state = State.STATE.LOAD_GAME_MENU;
				menu.isLoadGame = false;
			}
			if (menu.isConnect) {
				remove(menu);
				add(connectMenu);
				repaint();
				setVisible(true);
				State.state = State.STATE.CONNECT_MENU;
				menu.isConnect = false;
			}
			break;
		case NEW_GAME_MENU:
			if (newGameMenu.createGame) {
				if(!newGameMenu.seedText.equals("")){
					galaxy = new Galaxy(Long.parseLong(newGameMenu.seedText, Character.MAX_RADIX), input);
				}else{
					galaxy = new Galaxy(MathHelper.random.nextLong(), input);
				}
				remove(newGameMenu);
				add(galaxy);
				repaint();
				setVisible(true);
				State.state = State.STATE.GALACTIC;
				newGameMenu.createGame = false;
			}
			if (newGameMenu.cancelGame) {
				remove(newGameMenu);
				add(menu);
				repaint();
				setVisible(true);
				State.state = State.STATE.MAIN_MENU;
				newGameMenu.cancelGame = false;
			}
			break;
		case LOAD_GAME_MENU:
			if(loadGameMenu.startGame){
			}
			if(loadGameMenu.cancelGame){
				remove(loadGameMenu);
				add(menu);
				repaint();
				setVisible(true);
				State.state = State.STATE.MAIN_MENU;
				loadGameMenu.cancelGame = false;
			}
			break;
		case CONNECT_MENU:
			if(connectMenu.connectGame){
			}
			if(connectMenu.cancelGame){
				remove(connectMenu);
				add(menu);
				repaint();
				setVisible(true);
				State.state = State.STATE.MAIN_MENU;
				connectMenu.cancelGame = false;
			}
			break;
		case GALACTIC:
			galaxy.update();
			break;
		case SOLAR:
			galaxy.update();
			break;
		case PLANETARY:
			galaxy.update();
			break;
		case SURFACE_PLANETARY:
			galaxy.update();
			break;
		case SATELLITE:
			galaxy.update();
			break;
		case DUNGEON:
			break;
		case SHIP:
			break;
		case SPACE_BATTLE:
			break;
		default:
			break;
		}
	}

	@Override
	public void draw() {
		Graphics2D g2d = (Graphics2D) this.getGraphics();
		Image offImage = this.createImage(InputHandler.screenSize.width, InputHandler.screenSize.height);
		Graphics2D offGraphics = (Graphics2D) offImage.getGraphics();
		switch (State.state) {
		case MAIN_MENU:
			break;
		case NEW_GAME_MENU:
			break;
		case LOAD_GAME_MENU:
			break;
		case CONNECT_MENU:
			break;
		case GALACTIC:
			offGraphics.setColor(Color.BLACK);
			offGraphics.fillRect(0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height);
			galaxy.draw(offGraphics);
			g2d.drawImage(offImage, 0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height, null);
			break;
		case SOLAR:
			offGraphics.setColor(Color.BLACK);
			offGraphics.fillRect(0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height);
			galaxy.draw(offGraphics);
			g2d.drawImage(offImage, 0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height, null);
			break;
		case PLANETARY:
			offGraphics.setColor(Color.BLACK);
			offGraphics.fillRect(0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height);
			galaxy.draw(offGraphics);
			g2d.drawImage(offImage, 0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height, null);
			break;
		case SURFACE_PLANETARY:
			offGraphics.setColor(Color.BLACK);
			offGraphics.fillRect(0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height);
			galaxy.draw(offGraphics);
			g2d.drawImage(offImage, 0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height, null);
			break;
		case SATELLITE:
			offGraphics.setColor(Color.BLACK);
			offGraphics.fillRect(0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height);
			galaxy.draw(offGraphics);
			g2d.drawImage(offImage, 0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height, null);
			break;
		case DUNGEON:
			break;
		case SHIP:
			break;
		case SPACE_BATTLE:
			break;
		default:
			break;
		}
	}

}
