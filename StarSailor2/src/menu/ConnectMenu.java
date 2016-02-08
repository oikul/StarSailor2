package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.InputHandler;
import utils.ResourceLoader;

public class ConnectMenu extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel name, ip, servers;
	private JTextField serverName, serverIP;
	private JButton connect, cancel;
	private JList<String> serversList;
	public boolean cancelGame, connectGame;

	public ConnectMenu() {
		name = new JLabel("Server Name: ");
		name.setForeground(Color.white);
		name.setFont(new Font("Arial", Font.BOLD, 20));
		name.setBounds((int) InputHandler.midPoint.x / 4, (int) InputHandler.midPoint.y / 5, InputHandler.screenSize.width / 3, InputHandler.screenSize.height / 16);
		ip = new JLabel("IP address: ");
		ip.setForeground(Color.white);
		ip.setFont(new Font("Arial", Font.BOLD, 20));
		ip.setBounds((int) InputHandler.midPoint.x / 4, (int) (2 * InputHandler.midPoint.y) / 5, InputHandler.screenSize.width / 3, InputHandler.screenSize.height / 16);
		servers = new JLabel("Servers: ");
		servers.setForeground(Color.white);
		servers.setFont(new Font("Arial", Font.BOLD, 20));
		servers.setBounds((int) (5 * InputHandler.midPoint.x) / 4, (int) InputHandler.midPoint.y / 5, InputHandler.screenSize.width / 3, InputHandler.screenSize.height / 16);
		serverName = new JTextField();
		serverName.setBounds((int) (2 * InputHandler.midPoint.x) / 4, (int) InputHandler.midPoint.y / 5, InputHandler.screenSize.width / 3, InputHandler.screenSize.height / 16);
		serverIP = new JTextField();
		serverIP.setBounds((int) (2 * InputHandler.midPoint.x) / 4, (int) (2 * InputHandler.midPoint.y) / 5, InputHandler.screenSize.width / 3, InputHandler.screenSize.height / 16);
		serversList = new JList<String>();
		serversList.setBounds((int) (5 * InputHandler.midPoint.x) / 4, (int) (2 * InputHandler.midPoint.y) / 6, InputHandler.screenSize.width / 3, InputHandler.screenSize.height / 2);
		connect = new JButton("Connect");
		connect.addActionListener(e -> connectGame = true);
		connect.setBounds((int) InputHandler.midPoint.x / 3, (int) (3 * InputHandler.screenSize.height) / 4, InputHandler.screenSize.width / 6, InputHandler.screenSize.height / 16);
		cancel = new JButton("Cancel");
		cancel.addActionListener(e -> cancelGame = true);
		cancel.setBounds((int) (4 * InputHandler.midPoint.x) / 3, (int) (3 * InputHandler.screenSize.height) / 4, InputHandler.screenSize.width / 6, InputHandler.screenSize.height / 16);
		setLayout(null);
		add(name);
		add(ip);
		add(serverName);
		add(serverIP);
		add(servers);
		add(serversList);
		add(connect);
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
