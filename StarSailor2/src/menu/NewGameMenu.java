package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.InputHandler;
import utils.ResourceLoader;

public class NewGameMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel galaxyName, seed;
	private JTextField galaxyNameBox, seedBox;
	private JButton create, cancel;

	public NewGameMenu() {
		galaxyName = new JLabel("Galaxy Name: ");
		galaxyName.setBounds((int) InputHandler.midPoint.x - 300, 100, 200, 40);
		galaxyName.setForeground(Color.white);
		galaxyName.setFont(new Font("Arial", Font.BOLD, 20));
		seed = new JLabel("Seed: ");
		seed.setBounds((int) InputHandler.midPoint.x - 300, 200, 200, 40);
		seed.setForeground(Color.white);
		seed.setFont(new Font("Arial", Font.BOLD, 20));
		galaxyNameBox = new JTextField();
		galaxyNameBox.setBounds((int) InputHandler.midPoint.x - 100, 100, 400, 40);
		seedBox = new JTextField();
		seedBox.setBounds((int) InputHandler.midPoint.x - 100, 200, 400, 40);
		create = new JButton("Create Game");
		create.setBounds((int) InputHandler.midPoint.x - 500, (int) InputHandler.midPoint.y + 300, 200, 40);
		cancel = new JButton("Cancel");
		cancel.setBounds((int) InputHandler.midPoint.x + 300, (int) InputHandler.midPoint.y + 300, 200, 40);
		this.setLayout(null);
		this.add(galaxyName);
		this.add(seed);
		this.add(galaxyNameBox);
		this.add(seedBox);
		this.add(create);
		this.add(cancel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourceLoader.getImage("backgrounds/space.png"), 0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height, null);
	}

}
