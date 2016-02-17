package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import utils.InputHandler;
import utils.MathHelper;
import utils.NoiseGenerator;
import utils.PlanetaryBody;
import utils.State;

public class Star extends PlanetaryBody {

	private Point2D.Double destination;
	private int indexOfDestination;

	public Star(double distance, double angle, double size, Color color) {
		super(distance, angle, size, color);
	}

	@Override
	public void update() {
		switch (State.state) {
		case GALACTIC:
			incrementAngle(0.001);
			getXAndY();
			break;
		case SOLAR:
			if(!discovered){
				discovered = true;
				name = NoiseGenerator.generateName(MathHelper.random.nextInt(2) + 2);
			}
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
		switch (State.state) {
		case GALACTIC:
			g2d.setColor(Color.cyan);
			if (destination != null) {
				g2d.drawLine((int) (position.x), (int) (position.y), (int) (destination.x), (int) (destination.y));
			}
			g2d.setColor(color);
			g2d.fillOval((int) (position.x), (int) (position.y), (int) size, (int) size);
			break;
		case SOLAR:
			g2d.setColor(color);
			g2d.fillOval((int) (InputHandler.midPoint.x - (size * 5)), (int) (InputHandler.midPoint.y - (size * 5)),
					(int) size * 10, (int) size * 10);
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

	public void addHyperSpaceLane(Point2D.Double destination, int index) {
		this.destination = destination;
		this.indexOfDestination = index;
	}

	public int getIndexOfDestination() {
		return indexOfDestination;
	}

	public void updateHyperSpaceLane(Point2D.Double destination) {
		this.destination = destination;
	}

}
