package game;

import java.awt.Graphics2D;

import utils.Entity;

public class Player extends Entity {

	public Player(String name, String spritePath, int xPos, int yPos, int width, int height) {
		super(name, spritePath, xPos, yPos, width, height);
	}
	
	@Override
	public void update(){
		
	}
	
	@Override
	public void draw(Graphics2D g2d){
		if(!isDead){
			g2d.drawImage(sprites[currentISprite][currentJSprite], xPos, yPos, width, height, null);
		}
	}

}
