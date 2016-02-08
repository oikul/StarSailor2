package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import utils.InputHandler;
import utils.ResourceLoader;

public class LoadGameMenu extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel load;
	private JList<String> loadList;
	private JButton loadGame, cancel;
	public boolean cancelGame, startGame;

	public LoadGameMenu() {
		load = new JLabel("Load: ");
		load.setForeground(Color.white);
		load.setFont(new Font("Arial", Font.BOLD, 20));
		load.setBounds((int) InputHandler.midPoint.x / 2, (int) InputHandler.midPoint.y / 5, InputHandler.screenSize.width / 3, InputHandler.screenSize.height / 16);
		loadList = new JList<String>(new String[]{"this", "is", "where", "saved", "games", "will", "go"});
		loadList.setBounds((int) (2 *  InputHandler.midPoint.x) / 3, (int) InputHandler.midPoint.y / 5, InputHandler.screenSize.width / 2, InputHandler.screenSize.height / 2);
		loadGame = new JButton("Load Game");
		loadGame.addActionListener(e -> startGame = true);
		loadGame.setBounds((int) InputHandler.midPoint.x / 3, (int) (3 * InputHandler.screenSize.height) / 4, InputHandler.screenSize.width / 6, InputHandler.screenSize.height / 16);
		cancel = new JButton("Cancel");
		cancel.addActionListener(e -> cancelGame = true);
		cancel.setBounds((int) (4 * InputHandler.midPoint.x) / 3, (int) (3 * InputHandler.screenSize.height) / 4, InputHandler.screenSize.width / 6, InputHandler.screenSize.height / 16);
		setLayout(null);
		add(load);
		add(loadList);
		add(loadGame);
		add(cancel);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height);
		g.drawImage(ResourceLoader.getImage("backgrounds/space.png"), 0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height, null);
	}

}
