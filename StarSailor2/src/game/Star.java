package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import utils.PlanetryBody;
import utils.State;

public class Star extends PlanetryBody {
	
	private Point2D.Double destination;
	private int indexOfDestination;

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
	public void draw(Graphics2D g2d){
		switch(State.state){
		case GALACTIC:
			g2d.setColor(Color.cyan);
			g2d.drawLine((int) destination.x, (int) destination.y, (int) position.x, (int) position.y);
			g2d.setColor(color);
			g2d.fillOval((int) position.x, (int) position.y, (int) size, (int) size);
			break;
		case SOLAR:
			break;
		case PLANETRY:
			break;
		case SURFACE_PLANETRY:
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
	
	public void addHyperSpaceLane(Point2D.Double destination, int index){
		this.destination = destination;
		this.indexOfDestination = index;
	}
	
	public int getIndexOfDestination(){
		return indexOfDestination;
	}
	
	public void updateHyperSpaceLane(Point2D.Double destination){
		this.destination = destination;
	}

}
