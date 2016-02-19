package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

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
	private long seed;
	private InputHandler input;
	private double zoomGalactic = 1.0, zoomSolar = 0.2, zoomPlanetary = 1.0;
	private AffineTransform at, reverseat;

	public Galaxy(long seed, InputHandler input) {
		requestFocus();
		this.seed = seed;
		MathHelper.setRandomSeed(this.seed);
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
					MathHelper.random.nextDouble() * 10 + 1, color, seed + i);
		}
		for (int i = 0; i < galaxy.length; i++) {
			double closest = 5000;
			for (int j = 0; j < galaxy.length; j++) {
				if (MathHelper.getDistance(galaxy[i].getPosition(), galaxy[j].getPosition()) < closest && i != j) {
					galaxy[i].addHyperSpaceLane(galaxy[j].getPosition(), j);
					galaxy[i].setHyper(true);
					closest = MathHelper.getDistance(galaxy[i].getPosition(), galaxy[j].getPosition());
				}
			}
		}
		currentStar = MathHelper.random.nextInt(numOfStars);
		galaxy[currentStar].setSelected(true);
		if (galaxy[currentStar].getPosition().x > InputHandler.midPoint.x) {
			galaxy[currentStar].panRight(galaxy[currentStar].getPosition().x - InputHandler.midPoint.x);
		}
		if (galaxy[currentStar].getPosition().x < InputHandler.midPoint.x) {
			galaxy[currentStar].panLeft(InputHandler.midPoint.x - galaxy[currentStar].getPosition().x);
		}
		if (galaxy[currentStar].getPosition().y > InputHandler.midPoint.y) {
			galaxy[currentStar].panDown(galaxy[currentStar].getPosition().y - InputHandler.midPoint.y);
		}
		if (galaxy[currentStar].getPosition().y < InputHandler.midPoint.y) {
			galaxy[currentStar].panUp(InputHandler.midPoint.y - galaxy[currentStar].getPosition().y);
		}
	}

	public void update() {
		switch (State.state) {
		case GALACTIC:
			for (int i = 0; i < numOfStars; i++) {
				galaxy[i].update();
			}
			/*for (int i = 0; i < numOfStars; i++) {
				int index = galaxy[i].getIndexOfDestination();
				Point2D.Double newPos = galaxy[galaxy[i].getIndexOfDestination()].getPosition();
				if (index != 0 && galaxy[i].getHyper() && newPos != null) {
					galaxy[i].updateHyperSpaceLane(newPos);
				}
			}*/
			/*if (at != null) {
				Point2D.Double point = galaxy[currentStar].getPosition();
				at.transform(point, point);
				if (point.x > InputHandler.midPoint.x) {
					galaxy[currentStar].panRight(1);
				}
				if (point.x < InputHandler.midPoint.x) {
					galaxy[currentStar].panLeft(1);
				}
				if (point.y > InputHandler.midPoint.y) {
					galaxy[currentStar].panDown(1);
				}
				if (point.y < InputHandler.midPoint.y) {
					galaxy[currentStar].panUp(1);
				}
			}*/
			Point2D.Double point = galaxy[currentStar].getPosition();
			at.transform(point, point);
			if (point.x > InputHandler.midPoint.x) {
				galaxy[currentStar].panRight(point.x - InputHandler.midPoint.x);
			}
			if (point.x < InputHandler.midPoint.x) {
				galaxy[currentStar].panLeft(InputHandler.midPoint.x - point.x);
			}
			if (point.y > InputHandler.midPoint.y) {
				galaxy[currentStar].panDown(point.y - InputHandler.midPoint.y);
			}
			if (point.y < InputHandler.midPoint.y) {
				galaxy[currentStar].panUp(InputHandler.midPoint.y - point.y);
			}
			if (input.isMouseDown(MouseEvent.BUTTON1)) {
				Point2D.Double point1 = new Point2D.Double(input.getMousePositionOnScreen().getX(),
						input.getMousePositionOnScreen().getY());
				reverseat.transform(point1, point1);
				for (int i = 0; i < numOfStars; i++) {
					if (new Rectangle((int) galaxy[i].getPosition().x - 5, (int) galaxy[i].getPosition().y - 5, 10, 10)
							.contains(point1)) {
						galaxy[i].setSelected(true);
						currentStar = i;
					} else {
						galaxy[i].setSelected(false);
					}
				}
			}
			if (input.getMouseWheelUp()) {
				if (zoomGalactic < 2.0) {
					zoomGalactic += 0.1;
				} else {
					State.state = State.STATE.SOLAR;
					galaxy[currentStar].update();
				}
				input.stopMouseWheel();
			}
			if (input.getMouseWheelDown()) {
				if (zoomGalactic > 0.2) {
					zoomGalactic -= 0.1;
				}
				input.stopMouseWheel();
			}
			break;
		case SOLAR:
			createSystem();
			galaxy[currentStar].update();
			for (int i = 0; i < solarSystems[currentStar].length; i++) {
				if (solarSystems[currentStar][i] != null) {
					solarSystems[currentStar][i].update();
				}
			}
			if (input.getMouseWheelUp()) {
				if (zoomSolar < 2.0) {
					zoomSolar += 0.1;
				} else {
					State.state = State.STATE.PLANETARY;
					solarSystems[currentStar][currentPlanet].update();
				}
				input.stopMouseWheel();
			}
			if (input.getMouseWheelDown()) {
				if (zoomSolar > 0.2) {
					zoomSolar -= 0.1;
				} else {
					State.state = State.STATE.GALACTIC;
					update();
				}
				input.stopMouseWheel();
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
		double xTrans, yTrans;
		switch (State.state) {
		case GALACTIC:
			at = new AffineTransform();
			at.scale(zoomGalactic, zoomGalactic);
			xTrans = -(InputHandler.midPoint.x - galaxy[currentStar].getxDif()
					- InputHandler.screenSize.width / (zoomGalactic * 2));
			yTrans = -(InputHandler.midPoint.y - galaxy[currentStar].getyDif()
					- InputHandler.screenSize.height / (zoomGalactic * 2));
			at.translate(xTrans, yTrans);
			try {
				reverseat = at.createInverse();
			} catch (NoninvertibleTransformException e) {
				e.printStackTrace();
			}
			g2d.transform(at);
			for (int i = 0; i < numOfStars; i++) {
				galaxy[i].draw(g2d);
			}
			break;
		case SOLAR:
			at = new AffineTransform();
			at.scale(zoomSolar, zoomSolar);
			xTrans = -(InputHandler.midPoint.x - InputHandler.screenSize.width / (zoomSolar * 2));
			yTrans = -(InputHandler.midPoint.y - InputHandler.screenSize.height / (zoomSolar * 2));
			at.translate(xTrans, yTrans);
			g2d.transform(at);
			galaxy[currentStar].draw(g2d);
			for (int i = 0; i < solarSystems[currentStar].length; i++) {
				if (solarSystems[currentStar][i] != null
						&& new Rectangle(0, 0, InputHandler.screenSize.width, InputHandler.screenSize.height)
								.contains(solarSystems[currentStar][i].getPosition())) {
					solarSystems[currentStar][i].draw(g2d);
				}
			}
			break;
		case PLANETARY:
			for (int i = 0; i < planets[currentPlanet].length; i++) {
				if (planets[currentPlanet][i] != null) {
					planets[currentPlanet][i].draw(g2d);
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

	private void createSystem() {
		if (!galaxy[currentStar].isCreated()) {
			for (int i = 0; i < MathHelper.random.nextInt(maxNumOfPlanets); i++) {
				double distance = MathHelper.random.nextDouble() * InputHandler.screenSize.height / 2
						+ galaxy[currentStar].getSize() * 25;
				double size = MathHelper.random.nextDouble() * 5;
				solarSystems[currentStar][i] = new Planet(distance, MathHelper.random.nextDouble() * 360, size,
						new Biome(distance, size), galaxy[currentStar].getSeed() + i);
			}
			galaxy[currentStar].setCreated(true);
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

	public double getZoomGalactic() {
		return zoomGalactic;
	}

	public void setZoomGalactic(double zoomGalactic) {
		this.zoomGalactic = zoomGalactic;
	}

	public void incrementZoomGalactic(double amount) {
		zoomGalactic += amount;
	}

	public int getCurrentSatellite() {
		return currentSatellite;
	}

	public void setCurrentSatellite(int currentSatellite) {
		this.currentSatellite = currentSatellite;
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public double getZoomSolar() {
		return zoomSolar;
	}

	public void setZoomSolar(double zoomSolar) {
		this.zoomSolar = zoomSolar;
	}

	public double getZoomPlanetary() {
		return zoomPlanetary;
	}

	public void setZoomPlanetary(double zoomPlanetary) {
		this.zoomPlanetary = zoomPlanetary;
	}

	public int getNumOfStars() {
		return numOfStars;
	}

	public int getMaxNumOfPlanets() {
		return maxNumOfPlanets;
	}

	public int getMaxNumOfSatellites() {
		return maxNumOfSatellites;
	}

}
