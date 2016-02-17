package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

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
	private final int numOfStars = 8192, maxNumOfPlanets = 16, maxNumOfSatellites = 8;
	private int currentStar, currentPlanet, currentSatellite;
	private InputHandler input;
	private double zoom = 1.0;
	private AffineTransform at;

	public Galaxy(long seed, InputHandler input) {
		MathHelper.setRandomSeed(seed);
		this.input = input;
		at = new AffineTransform();
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
			galaxy[i] = new Star(MathHelper.random.nextDouble() * 32768, MathHelper.random.nextDouble() * 360,
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
		currentStar = MathHelper.random.nextInt(numOfStars);
	}

	public void update() {
		switch (State.state) {
		case GALACTIC:
			if(input.isMouseDown(MouseEvent.BUTTON1)){
				for(int i = 0; i < numOfStars; i++){
					if(new Rectangle((int)galaxy[i].getPosition().x - 3, (int)galaxy[i].getPosition().y - 3, 6, 6).contains(input.getMousePositionOnScreen())){
						galaxy[i].setSelected(true);
						currentStar = i;
					}else{
						galaxy[i].setSelected(false);
					}
				}
			}
			if(input.getMouseWheelUp()){
				if(zoom < 3.0){
					zoom += 0.1;
				}else{
					State.state = State.STATE.SOLAR;
				}
				input.stopMouseWheel();
			}
			if(input.getMouseWheelDown()){
				if(zoom > 0.2){
					zoom -= 0.1;
				}
				input.stopMouseWheel();
			}
			for (int i = 0; i < numOfStars; i++) {
				galaxy[i].update();
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
			at = new AffineTransform();
			at.scale(zoom, zoom);
			double xTrans = - (InputHandler.midPoint.x - InputHandler.screenSize.width/(zoom * 2));
			double yTrans = - (InputHandler.midPoint.y - InputHandler.screenSize.height/(zoom * 2));
			at.translate(xTrans, yTrans);
			g2d.transform(at);
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

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}
	
	public void incrementZoom(double amount){
		zoom += amount;
	}

}
