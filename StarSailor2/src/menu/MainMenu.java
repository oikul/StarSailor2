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
	public static boolean isNewGame, isLoadGame, isConnect, userSelected;
	
	public MainMenu(){
		title = new JLabel("Star Sailor 2");
		title.setFont(new Font("Helvetica", Font.BOLD, 50));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setForeground(Color.WHITE);
		title.setBounds((int) InputHandler.midPoint.x - 200, (int) InputHandler.midPoint.y - 400, 400, 75);
		newGame = new JButton("New Game");
		newGame.setBounds((int) InputHandler.midPoint.x - 100, (int) InputHandler.midPoint.y - 200, 200, 75);
		newGame.addActionListener(e -> isNewGame = true);
		loadGame = new JButton("Load Game");
		loadGame.setBounds((int) InputHandler.midPoint.x - 100, (int) InputHandler.midPoint.y - 100, 200, 75);
		loadGame.addActionListener(e -> isLoadGame = true);
		connect = new JButton("Connect");
		connect.setBounds((int) InputHandler.midPoint.x - 100, (int) InputHandler.midPoint.y, 200, 75);
		connect.addActionListener(e -> isConnect = true);
		exit = new JButton("Exit");
		exit.setBounds((int) InputHandler.midPoint.x - 100, (int) InputHandler.midPoint.y + 100, 200, 75);
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
		g.drawImage(ResourceLoader.getImage("backgrounds/space.png"), 0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height, null);
	}

}
