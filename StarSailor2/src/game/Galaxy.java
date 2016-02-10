package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import utils.InputHandler;
import utils.MathHelper;
import utils.PlanetaryBody;
import utils.State;

public class Galaxy extends JPanel {

	private static final long serialVersionUID = 1L;
	private Star[] galaxy;
	private PlanetaryBody[][] solarSystems;
	private PlanetaryBody[][] planets;
	private final int numOfStars = 4096, maxNumOfPlanets = 16, maxNumOfSatellites = 8;
	private int currentStar, currentPlanet, currentSatellite;
	private InputHandler input;

	public Galaxy(long seed, InputHandler input) {
		MathHelper.setRandomSeed(seed);
		this.input = input;
		galaxy = new Star[numOfStars];
		solarSystems = new PlanetaryBody[numOfStars][maxNumOfPlanets];
		planets = new PlanetaryBody[maxNumOfPlanets][maxNumOfSatellites];
		for (int i = 0; i < numOfStars; i++) {
			Color color = Color.white;
			int c = MathHelper.random.nextInt(14);
			switch (c) {
			case 0:
				color = new Color(255, 255, 255);
				break;
			case 1:
				color = new Color(255, 255, 255);
				break;
			case 2:
				color = new Color(255, 255, 255);
				break;
			case 3:
				color = new Color(255, 255, 255);
				break;
			case 4:
				color = new Color(255, 255, 255);
				break;
			case 5:
				color = new Color(255, 255, 255);
				break;
			case 6:
				color = new Color(220, 255, 0);
				break;
			case 7:
				color = new Color(220, 255, 0);
				break;
			case 8:
				color = new Color(220, 255, 0);
				break;
			case 9:
				color = new Color(255, 100, 0);
				break;
			case 10:
				color = new Color(255, 100, 0);
				break;
			case 11:
				color = new Color(255, 0, 0);
				break;
			case 12:
				color = new Color(255, 0, 255);
				break;
			case 13:
				color = new Color(0, 255, 255);
				break;
			}
			galaxy[i] = new Star(MathHelper.random.nextDouble() * 16384, MathHelper.random.nextDouble() * 360,
					MathHelper.random.nextDouble() * 5, color);
		}
		for (int i = 0; i < galaxy.length; i++) {
			double closest = 500;
			for (int j = 0; j < galaxy.length; j++) {
				if (MathHelper.getDistance(galaxy[i].getPosition(), galaxy[j].getPosition()) < closest && i != j) {
					galaxy[i].addHyperSpaceLane(galaxy[j].getPosition(), j);
					closest = MathHelper.getDistance(galaxy[i].getPosition(), galaxy[j].getPosition());
				}
			}
		}
	}

	public void update() {
		switch (State.state) {
		case GALACTIC:
			boolean up, left, right, down;
			if (input.isKeyDown(KeyEvent.VK_W)) {
				System.out.println("pan up");
				up = true;
			} else {
				up = false;
			}
			if (input.isKeyDown(KeyEvent.VK_A)) {
				left = true;
			} else {
				left = false;
			}
			if (input.isKeyDown(KeyEvent.VK_S)) {
				down = true;
			} else {
				down = false;
			}
			if (input.isKeyDown(KeyEvent.VK_D)) {
				right = true;
			} else {
				right = false;
			}
			for (int i = 0; i < numOfStars; i++) {
				galaxy[i].update();
				if (up && left) {
					galaxy[i].panUL(1 / MathHelper.root2);
				} else if (up && right) {
					galaxy[i].panUR(1 / MathHelper.root2);
				} else if (down && left) {
					galaxy[i].panDL(1 / MathHelper.root2);
				} else if (down && right) {
					galaxy[i].panDR(1 / MathHelper.root2);
				}else if(up){
					galaxy[i].panUp(1);
				}else if(left){
					galaxy[i].panLeft(1);
				}else if(down){
					galaxy[i].panRight(1);
				}else if(right){
					galaxy[i].panDown(1);
				}
			}
			for (int i = 0; i < numOfStars; i++) {
				if (galaxy[i].getIndexOfDestination() != 0) {
					galaxy[i].updateHyperSpaceLane(galaxy[galaxy[i].getIndexOfDestination()].getPosition());
				}
			}
			break;
		case SOLAR:
			for (int i = 0; i < solarSystems[currentStar].length; i++) {
				if (solarSystems[currentStar][i] != null) {
					solarSystems[currentStar][i].update();
				}
			}
			break;
		case PLANETARY:
			for (int i = 0; i < planets[currentPlanet].length; i++) {
				if (planets[currentPlanet][i] != null) {
					planets[currentPlanet][i].update();
				}
			}
			break;
		case SURFACE_PLANETARY:
			solarSystems[currentStar][currentPlanet].update();
			break;
		case SATELLITE:
			planets[currentPlanet][currentSatellite].update();
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

	public void draw(Graphics2D g2d) {
		switch (State.state) {
		case GALACTIC:
			for (int i = 0; i < numOfStars; i++) {
				galaxy[i].draw(g2d);
			}
			break;
		case SOLAR:
			for (int i = 0; i < solarSystems[currentStar].length; i++) {
				if (solarSystems[currentStar][i] != null) {
					solarSystems[currentStar][i].draw(g2d);
				}
			}
			break;
		case PLANETARY:
			for (int i = 0; i < planets[currentPlanet].length; i++) {
				if (planets[currentPlanet][i] != null) {
					planets[currentPlanet][i].update();
				}
			}
			break;
		case SURFACE_PLANETARY:
			solarSystems[currentStar][currentPlanet].draw(g2d);
			break;
		case SATELLITE:
			planets[currentPlanet][currentSatellite].draw(g2d);
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

	public int getCurrentStar() {
		return currentStar;
	}

	public void setCurrentStar(int currentStar) {
		this.currentStar = currentStar;
	}

	public int getCurrentPlanet() {
		return currentPlanet;
	}

	public void setCurrentPlanet(int currentPlanet) {
		this.currentPlanet = currentPlanet;
	}

	public int getCurrentSattelite() {
		return currentSatellite;
	}

	public void setCurrentSattelite(int currentSattelite) {
		this.currentSatellite = currentSattelite;
	}

}
