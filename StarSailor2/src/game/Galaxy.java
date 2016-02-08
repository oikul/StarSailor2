package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import utils.MathHelper;
import utils.NoiseGenerator;
import utils.PlanetryBody;
import utils.State;

public class Galaxy extends JPanel {

	private static final long serialVersionUID = 1L;
	private Star[] galaxy;
	private PlanetryBody[][] solarSystems;
	private PlanetryBody[][] planets;
	private BufferedImage spaaace;
	private final int numOfStars = 4096, maxNumOfPlanets = 16, maxNumOfSatellites = 8;
	private int currentStar, currentPlanet, currentSatellite;

	public Galaxy(long seed) {
		MathHelper.setRandomSeed(seed);
		NoiseGenerator gen = new NoiseGenerator(seed);
		/*this.spaaace = new BufferedImage(16384 / 6, 16384 / 6, BufferedImage.TYPE_INT_ARGB);
		double[][] red = gen.getPerlinNoise(16384 / 6, 16384 / 6, MathHelper.random.nextInt(16) + 2, MathHelper.random.nextInt(16) + 2);
		double[][] green = gen.getPerlinNoise(16384 / 6, 16384 / 6, MathHelper.random.nextInt(16) + 2, MathHelper.random.nextInt(16) + 2);
		double[][] blue = gen.getPerlinNoise(16384 / 6, 16384 / 6, MathHelper.random.nextInt(16) + 2, MathHelper.random.nextInt(16) + 2);
		Graphics g = spaaace.getGraphics();
		for(int i = 0; i < 16384 / 6; i++){
			for(int j = 0; j < 16384 / 6; j++){
				g.setColor(new Color((int) (red[i][j] * 150),(int) (green[i][j] * 100),(int) (blue[i][j] * 150)));
				g.fillRect(i * 3, j * 3, 3, 3);
			}
		}*/
		galaxy = new Star[numOfStars];
		solarSystems = new PlanetryBody[numOfStars][maxNumOfPlanets];
		planets = new PlanetryBody[maxNumOfPlanets][maxNumOfSatellites];
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
		for(int i = 0; i < galaxy.length; i++){
			double closest = 2000;
			for(int j = 0; j < galaxy.length; j++){
				if(MathHelper.getDistance(galaxy[i].getPosition(), galaxy[j].getPosition()) < closest && i != j){
					galaxy[i].addHyperSpaceLane(galaxy[j].getPosition(), j);
					closest = MathHelper.getDistance(galaxy[i].getPosition(), galaxy[j].getPosition());
				}
			}
		}
	}

	public void update() {
		switch (State.state) {
		case GALACTIC:
			for (int i = 0; i < numOfStars; i++) {
				galaxy[i].update();
			}
			for (int i = 0; i < numOfStars; i++) {
				galaxy[i].updateHyperSpaceLane(galaxy[galaxy[i].getIndexOfDestination()].getPosition());
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
			for (int i = 0; i < planets[currentPlanet].length; i++) {
				if (planets[currentPlanet][i] != null) {
					planets[currentPlanet][i].update();
				}
			}
			break;
		case SURFACE_PLANETRY:
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
			g2d.drawImage(spaaace, 0, 0, null);
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
			for (int i = 0; i < planets[currentPlanet].length; i++) {
				if (planets[currentPlanet][i] != null) {
					planets[currentPlanet][i].update();
				}
			}
			break;
		case SURFACE_PLANETRY:
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
