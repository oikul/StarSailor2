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
	private boolean hasHyper = false;

	public Star(double distance, double angle, double size, Color color, long seed) {
		super(distance, angle, size, color, seed);
	}

	@Override
	public void update() {
		switch (State.state) {
		case GALACTIC:
			incrementAngle(0.0001);
			getXAndY();
			break;
		case SOLAR:
			if(!discovered){
				discovered = true;
				name = noiseGen.generateName(MathHelper.random.nextInt(2) + 2);
				noise = noiseGen.getPerlinNoise((int)size*10, (int)size*10, 8, 10);
				System.out.println(name);
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
			if (destination != null && getHyper()) {
				g2d.drawLine((int) (position.x), (int) (position.y), (int) (destination.x), (int) (destination.y));
			}
			g2d.setColor(color);
			g2d.fillOval((int) (position.x), (int) (position.y), (int) size, (int) size);
			break;
		case SOLAR:
			//g2d.setColor(color);
			//g2d.fillOval((int) (InputHandler.midPoint.x - (size * 5)), (int) (InputHandler.midPoint.y - (size * 5)),
				//	(int) size * 10, (int) size * 10);
			for(int i = 0; i < noise.length; i++){
				for(int j = 0; j < noise[0].length; j++){
					if(bounds.contains(new Point2D.Double(i + InputHandler.midPoint.x - (size*5), j + InputHandler.midPoint.y - (size*5)))){
						g2d.setColor(new Color((int) (color.getRed() * noise[i][j]), (int) (color.getGreen() * noise[i][j]), (int) (color.getBlue() * noise[i][j])));
						g2d.fillRect((int) (i + InputHandler.midPoint.x - (size*5)), (int) (j + InputHandler.midPoint.y - (size*5)), 1, 1);
					}
				}
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
	
	public void setHyper(boolean hyper){
		hasHyper = hyper;
	}
	
	public boolean getHyper(){
		return hasHyper;
	}

}
