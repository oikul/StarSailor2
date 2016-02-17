package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import utils.InputHandler;
import utils.MathHelper;
import utils.NoiseGenerator;
import utils.PlanetaryBody;
import utils.State;

public class Planet extends PlanetaryBody {

	public Planet(double distance, double angle, double size, Color color, long seed) {
		super(distance, angle, size, color, seed);
	}

	@Override
	public void update() {
		switch(State.state){
		case GALACTIC:
			break;
		case SOLAR:
			incrementAngle(0.0001);
			getXAndY();
			break;
		case PLANETARY:
			if(!discovered){
				discovered = true;
				noise = noiseGen.getPerlinNoise((int) size*10, (int) size*10, 4, 5);
				name = NoiseGenerator.generateName(MathHelper.random.nextInt(2) + 2);
				System.out.println(name);
			}
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
			//g2d.setColor(color);
			//g2d.fillOval((int) (InputHandler.midPoint.x - (size*5)), (int) (InputHandler.midPoint.y - (size*5)), (int) size*10, (int) size*10);
			for(int i = 0; i < noise.length; i++){
				for(int j = 0; j < noise[0].length; j++){
					if(bounds.contains(new Point2D.Double(i + InputHandler.midPoint.x - (size*5), j + InputHandler.midPoint.y - (size*5)))){
						g2d.setColor(new Color((int) (color.getRed() * noise[i][j]), (int) (color.getGreen() * noise[i][j]), (int) (color.getBlue() * noise[i][j])));
						g2d.fillRect((int) (i + InputHandler.midPoint.x - (size*5)), (int) (j + InputHandler.midPoint.y - (size*5)), 1, 1);
					}
				}
			}
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
