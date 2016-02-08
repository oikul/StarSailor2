package game;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.PlanetryBody;
import utils.State;

public class Star extends PlanetryBody{

	public Star(double distance, double angle, double size, Color color) {
		super(distance, angle, size, color);
	}
	
	@Override
	public void update(){
		switch(State.state){
		case GALACTIC:
			incrementAngle(0.001);
			getXAndY();
			break;
		case SOLAR:
			break;
		case PLANETRY:
			break;
		case SURFACE_PLANETRY:
			break;
		case SURFACE_MOON:
			break;
		case SURFACE_ASTEROID:
			break;
		case SPACE_STATION:
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
	public void draw(Graphics2D g2d){
		switch(State.state){
		case GALACTIC:
			g2d.setColor(color);
			g2d.fillOval((int) position.x, (int) position.y, (int) size, (int) size);
			break;
		case SOLAR:
			break;
		case PLANETRY:
			break;
		case SURFACE_PLANETRY:
			break;
		case SURFACE_MOON:
			break;
		case SURFACE_ASTEROID:
			break;
		case SPACE_STATION:
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
