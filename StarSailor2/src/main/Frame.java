package main;

import menu.MainMenu;
import menu.NewGameMenu;
import utils.AbstractMain;
import utils.InputHandler;

public class Frame extends AbstractMain{

	private static final long serialVersionUID = 3250471534239363877L;
	MainMenu menu;
	NewGameMenu newGameMenu;

	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.run();
	}

	@Override
	public void initialise() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Star Sailor 2");
		setSize(InputHandler.screenSize);
		menu = new MainMenu();
		this.add(menu);
		setVisible(true);
		running = true;
	}

	@Override
	public void update() {
		if(MainMenu.isNewGame){
			this.remove(menu);
			newGameMenu = new NewGameMenu();
			this.add(newGameMenu);
			this.repaint();
			this.setVisible(true);
		}
	}

}
