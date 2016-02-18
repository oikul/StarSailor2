package game;

import java.awt.Graphics2D;

import utils.InputHandler;
import utils.MathHelper;
import utils.PlanetaryBody;
import utils.State;

public class Planet extends PlanetaryBody {

	public Planet(double distance, double angle, double size, Biome biome, long seed) {
		super(distance, angle, size, biome.getColor(), seed);
	}

	@Override
	public void update() {
		switch(State.state){
		case GALACTIC:
			break;
		case SOLAR:
			incrementAngle(0.00001);
			getXAndY();
			break;
		case PLANETARY:
			if(!discovered){
				discovered = true;
				noise = noiseGen.getPerlinNoise((int) size*10, (int) size*10, 4, 5);
				name = noiseGen.generateName(MathHelper.random.nextInt(2) + 2);
				System.out.println("planet name: " + name);
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
			g2d.fillOval((int)position.x, (int)position.y, (int)size * 10, (int)size * 10);
			break;
		case PLANETARY:
			g2d.setColor(color);
			g2d.fillOval((int) (InputHandler.midPoint.x - (size*25)), (int) (InputHandler.midPoint.y - (size*25)), (int) size*50, (int) size*50);
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
