package game;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.InputHandler;
import utils.PlanetaryBody;
import utils.State;

public class Planet extends PlanetaryBody {

	public Planet(double distance, double angle, double size, Color color) {
		super(distance, angle, size, color);
	}

	@Override
	public void update() {
		switch(State.state){
		case GALACTIC:
			break;
		case SOLAR:
			incrementAngle(0.001);
			getXAndY();
			break;
		case PLANETARY:
			break;
		case SURFACE_PLANETARY:
			break;
		case SATELLITE:
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
	public void draw(Graphics2D g2d) {
		switch(State.state){
		case GALACTIC:
			break;
		case SOLAR:
			g2d.setColor(color);
			g2d.fillOval((int)position.x, (int)position.y, (int)size, (int)size);
			break;
		case PLANETARY:
			g2d.setColor(color);
			g2d.fillOval((int) (InputHandler.midPoint.x - (size*5)), (int) (InputHandler.midPoint.y - (size*5)), (int) size*10, (int) size*10);
			break;
		case SURFACE_PLANETARY:
			break;
		case SATELLITE:
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
