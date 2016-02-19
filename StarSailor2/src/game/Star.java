package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import utils.InputHandler;
import utils.MathHelper;
import utils.PlanetaryBody;
import utils.State;

public class Star extends PlanetaryBody {

	private Point2D.Double destination;
	private int indexOfDestination;
	private boolean hasHyper = false, created = false;

	public Star(double distance, double angle, double size, Color color, long seed) {
		super(distance, angle, size, color, seed);
	}

	@Override
	public void update() {
		switch (State.state) {
		case GALACTIC:
			incrementAngle(0.000001);
			getXAndY();
			break;
		case SOLAR:
			if (!discovered) {
				discovered = true;
				name = noiseGen.generateName(MathHelper.random.nextInt(2) + 2);
				System.out.println("star name: " + name);
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
			/*
			 * if (destination != null && getHyper()) { g2d.drawLine((int)
			 * (position.x), (int) (position.y), (int) (destination.x), (int)
			 * (destination.y)); }
			 */
			if (selected) {
				g2d.setColor(Color.cyan);
				g2d.drawRect((int) (position.x - size / 2) - 5, (int) (position.y - size / 2) - 5, (int) size + 10,
						(int) size + 10);
			}
			g2d.setColor(color);
			g2d.fillOval((int) (position.x - size / 2), (int) (position.y - size / 2), (int) size, (int) size);
			break;
		case SOLAR:
			g2d.setColor(color);
			g2d.fillOval((int) (InputHandler.midPoint.x - (size * 25)), (int) (InputHandler.midPoint.y - (size * 25)),
					(int) size * 50, (int) size * 50);
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

	public void setHyper(boolean hyper) {
		hasHyper = hyper;
	}

	public boolean getHyper() {
		return hasHyper;
	}

	public boolean isCreated() {
		return created;
	}

	public void setCreated(boolean created) {
		this.created = created;
	}

}
