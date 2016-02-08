package game;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import utils.MathHelper;
import utils.PlanetryBody;
import utils.State;

public class Galaxy extends JPanel {

	private static final long serialVersionUID = 1L;
	private PlanetryBody[] galaxy;
	private PlanetryBody[][] solarSystems;
	private PlanetryBody[][] planets;
	private final int numOfStars = 4096, maxNumOfPlanets = 16, maxNumOfSattelites = 8;
	private int currentStar, currentPlanet, currentSattelite;

	public Galaxy(long seed) {
		MathHelper.setRandomSeed(seed);
		galaxy = new PlanetryBody[numOfStars];
		solarSystems = new PlanetryBody[numOfStars][maxNumOfPlanets];
		planets = new PlanetryBody[maxNumOfPlanets][maxNumOfSattelites];
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
			galaxy[i] = new Star(MathHelper.random.nextDouble() * 16384, MathHelper.random.nextDouble() * 360, MathHelper.random.nextDouble() * 5, color);
		}
	}

	public void update() {
		switch (State.state) {
		case GALACTIC:
			for (int i = 0; i < numOfStars; i++) {
				galaxy[i].update();
			}
			break;
		case SOLAR:
			for (int i = 0; i < solarSystems[currentStar].length; i++) {
				if (solarSystems[currentStar][i] != null) {
					solarSystems[currentStar][i].update();
				}
			}
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
		return currentSattelite;
	}

	public void setCurrentSattelite(int currentSattelite) {
		this.currentSattelite = currentSattelite;
	}

}
