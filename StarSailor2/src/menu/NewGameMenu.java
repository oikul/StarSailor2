package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	public String name, seedText;
	public boolean createGame = false, cancelGame = false;

	public NewGameMenu() {
		galaxyName = new JLabel("Galaxy Name: ");
		galaxyName.setBounds((int) InputHandler.midPoint.x / 2, (int) InputHandler.midPoint.y / 5, InputHandler.screenSize.width / 3, InputHandler.screenSize.height / 16);
		galaxyName.setForeground(Color.white);
		galaxyName.setFont(new Font("Arial", Font.BOLD, 20));
		seed = new JLabel("Seed: ");
		seed.setBounds((int) InputHandler.midPoint.x / 2, (int) (2 * InputHandler.midPoint.y) / 5, InputHandler.screenSize.width / 3, InputHandler.screenSize.height / 16);
		seed.setForeground(Color.white);
		seed.setFont(new Font("Arial", Font.BOLD, 20));
		galaxyNameBox = new JTextField();
		galaxyNameBox.setBounds((int) InputHandler.screenSize.width / 2, (int) InputHandler.midPoint.y / 5, InputHandler.screenSize.width / 3, InputHandler.screenSize.height / 16);
		galaxyNameBox.addActionListener(e -> name = galaxyNameBox.getText());
		seedBox = new JTextField();
		seedBox.setBounds((int) InputHandler.screenSize.width / 2, (int) (2 * InputHandler.midPoint.y) / 5, InputHandler.screenSize.width / 3, InputHandler.screenSize.height / 16);
		seedBox.addActionListener(e -> seedText = seedBox.getText());
		create = new JButton("Create Game");
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				name = galaxyNameBox.getText();
				seedText = seedBox.getText();
				createGame = true;
			}
			
		});
		create.setBounds((int) InputHandler.midPoint.x / 3, (int) (3 * InputHandler.screenSize.height) / 4, InputHandler.screenSize.width / 6, InputHandler.screenSize.height / 16);
		cancel = new JButton("Cancel");
		cancel.addActionListener(e -> cancelGame = true);
		cancel.setBounds((int) (4 * InputHandler.midPoint.x) / 3, (int) (3 * InputHandler.screenSize.height) / 4, InputHandler.screenSize.width / 6, InputHandler.screenSize.height / 16);
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
		g.setColor(Color.black);
		g.fillRect(0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height);
		g.drawImage(ResourceLoader.getImage("backgrounds/space.png"), 0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height, null);
	}

}
