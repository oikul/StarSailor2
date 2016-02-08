package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.InputHandler;
import utils.ResourceLoader;

public class MainMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JButton newGame, loadGame, connect, exit;
	public boolean isNewGame, isLoadGame, isConnect, userSelected;
	
	public MainMenu(){
		title = new JLabel("Star Sailor 2");
		title.setFont(new Font("Helvetica", Font.BOLD, 50));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setForeground(Color.WHITE);
		title.setBounds((int) InputHandler.midPoint.x / 2, (int) InputHandler.midPoint.y / 6, InputHandler.screenSize.width / 2, InputHandler.screenSize.height / 8);
		newGame = new JButton("New Game");
		newGame.setBounds((int) (3 * InputHandler.midPoint.x) / 4, (int) (3 * InputHandler.midPoint.y) / 6, InputHandler.screenSize.width / 4, InputHandler.screenSize.height / 8);
		newGame.addActionListener(e -> isNewGame = true);
		loadGame = new JButton("Load Game");
		loadGame.setBounds((int) (3 * InputHandler.midPoint.x) / 4, (int) (5 * InputHandler.midPoint.y) / 6, InputHandler.screenSize.width / 4, InputHandler.screenSize.height / 8);
		loadGame.addActionListener(e -> isLoadGame = true);
		connect = new JButton("Connect");
		connect.setBounds((int) (3 * InputHandler.midPoint.x) / 4, (int) (7 * InputHandler.midPoint.y) / 6, InputHandler.screenSize.width / 4, InputHandler.screenSize.height / 8);
		connect.addActionListener(e -> isConnect = true);
		exit = new JButton("Exit");
		exit.setBounds((int) (3 * InputHandler.midPoint.x) / 4, (int) (9 * InputHandler.midPoint.y) / 6, InputHandler.screenSize.width / 4, InputHandler.screenSize.height / 8);
		exit.addActionListener(e -> System.exit(0));
		this.setLayout(null);
		this.add(title);
		this.add(newGame);
		this.add(loadGame);
		this.add(connect);
		this.add(exit);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height);
		g.drawImage(ResourceLoader.getImage("backgrounds/space.png"), 0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height, null);
	}

}
