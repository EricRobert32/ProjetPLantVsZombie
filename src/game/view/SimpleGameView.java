package game.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import fr.umlv.zen5.ApplicationContext;
import game.data.Coordinates;
import game.data.MovingElement;
import game.data.SimpleGameData;

public class SimpleGameView implements GameView {
	private final int xOrigin;
	private final int yOrigin;
	private final int width;
	private final int length;
	private final int squareSize;

	private SimpleGameView(int xOrigin, int yOrigin, double d, int width, int squareSize) {
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
		this.length = (int) d;
		this.width = width;
		this.squareSize = squareSize;
	}

	// 2eme constructeur
	public static SimpleGameView initGameGraphics(int xOrigin, int yOrigin, double d, SimpleGameData data) {
		// Recupaire la taille d'un carre en divisant la taille total par le nombre de
		// ligne
		int squareSize = (int) (d * 1.0 / data.getNbLines());
		// nouvelle matrice de x,y,taille reel de la matrice (ligne), taille reel en
		// colonne et la taille du carre
		return new SimpleGameView(xOrigin, yOrigin, d, data.getNbColumns() * squareSize, squareSize);
	}

	public int getSquareSize() {
		return squareSize;
	}

	private int indexFromReaCoord(float coord, int origin) { // attention, il manque des test de validité des
																// coordonnées!
		return (int) ((coord - origin) / squareSize);
	}

	/**
	 * Transforms a real y-coordinate into the index of the corresponding line.
	 * 
	 * @param y a float y-coordinate
	 * @return the index of the corresponding line.
	 * @throws IllegalArgumentException if the float coordinate doesn't fit in the
	 *                                  game board.
	 */
	public int lineFromY(float y) {
		return indexFromReaCoord(y, yOrigin);
	}

	/**
	 * Transforms a real x-coordinate into the index of the corresponding column.
	 * 
	 * @param x a float x-coordinate
	 * @return the index of the corresponding column.
	 * @throws IllegalArgumentException if the float coordinate doesn't fit in the
	 *                                  game board.
	 */
	public int columnFromX(float x) {
		return indexFromReaCoord(x, xOrigin);
	}

	////////////////////////////////////////////////////////////////
	private float realCoordFromIndex(int index, int origin) {
		return origin + index * squareSize;
	}

	public float xFromI(int i) {
		return realCoordFromIndex(i, xOrigin);
	}

	public float yFromJ(int j) {
		return realCoordFromIndex(j, yOrigin);
	}

	/////////////////////////////////////////////////////////////////
	private RectangularShape drawCell(int i, int j) {
		return new Rectangle2D.Float(xFromI(j) + 2, yFromJ(i) + 2, squareSize - 4, squareSize - 4);
	}

	private RectangularShape drawSelectedCell(int i, int j) {
		return new Rectangle2D.Float(xFromI(j), yFromJ(i), squareSize, squareSize);
	}

	/**
	 * Draws the game board from its data, using an existing Graphics2D object.
	 * 
	 * @param graphics a Graphics2D object provided by the default method
	 *                 {@code draw(ApplicationContext, GameData)}
	 * @param data     the GameData containing the game data.
	 */
//	@Override
//	public void draw(Graphics2D graphics, SimpleGameData data) {
//		// example
//		graphics.setColor(Color.LIGHT_GRAY);
//		graphics.fill(new Rectangle2D.Float(xOrigin, yOrigin, width, length));
//
//		graphics.setColor(Color.WHITE);
//		for (int i = 0; i <= data.getNbLines(); i++) {
//			graphics.draw(
//					new Line2D.Float(xOrigin, yOrigin + i * squareSize, xOrigin + width, yOrigin + i * squareSize));
//		}
//
//		for (int i = 0; i <= data.getNbColumns(); i++) {
//			graphics.draw(
//					new Line2D.Float(xOrigin + i * squareSize, yOrigin, xOrigin + i * squareSize, yOrigin + length));
//		}
//
//		Coordinates c = data.getSelected();
//		if (c != null) {
//			graphics.setColor(Color.BLACK);
//			graphics.fill(drawSelectedCell(c.getI(), c.getJ()));
//		}
//
//		for (int i = 0; i < data.getNbLines(); i++) {
//			for (int j = 0; j < data.getNbColumns(); j++) {
//				//couleur case
//				graphics.setColor(new Color(5, 64,10));
//				graphics.fill(drawCell(i, j));
//				graphics.setColor(data.getCellColor(i, j));
//			}
//		}
//	}

	@Override
	public void draw2(Graphics2D graphics, SimpleGameData data, Color colorLine, Color colorIn) {
		// example
		graphics.setColor(colorLine);
		graphics.fill(new Rectangle2D.Float(xOrigin, yOrigin, width, length));

		graphics.setColor(colorLine);
		for (int i = 0; i <= data.getNbLines(); i++) {
			graphics.draw(
					new Line2D.Float(xOrigin, yOrigin + i * squareSize, xOrigin + width, yOrigin + i * squareSize));
		}

		for (int i = 0; i <= data.getNbColumns(); i++) {
			graphics.draw(
					new Line2D.Float(xOrigin + i * squareSize, yOrigin, xOrigin + i * squareSize, yOrigin + length));
		}

		for (int i = 0; i < data.getNbLines(); i++) {
			for (int j = 0; j < data.getNbColumns(); j++) {
				// couleur case
				graphics.setColor(colorIn);
				graphics.fill(drawCell(i, j));
				graphics.setColor(data.getCellColor(i, j));
			}
		}
	}

	/**
	 * Draws only the cell specified by the given coordinates in the game board from
	 * its data, using an existing Graphics2D object.
	 * 
	 * @param graphics a Graphics2D object provided by the default method
	 *                 {@code draw(ApplicationContext, GameData)}
	 * @param data     the GameData containing the game data.
	 * @param x        the float x-coordinate of the cell.
	 * @param y        the float y-coordinate of the cell.
	 */

	@Override
	public void drawPool(Graphics2D graphics, SimpleGameData data, Color colorLine, Color colorIn) {
		graphics.setColor(colorLine);
		graphics.fill(new Rectangle2D.Float(xOrigin, yOrigin, width, length));
		for (int i = 0; i <= data.getNbLines(); i++) {
			if (i == 2 || i == 3) {
				graphics.setColor(Color.blue);
			} else {
				graphics.setColor(colorLine);
			}
			graphics.draw(
					new Line2D.Float(xOrigin, yOrigin + i * squareSize, xOrigin + width, yOrigin + i * squareSize));
		}
		for (int i = 0; i <= data.getNbColumns(); i++) {
			graphics.draw(
					new Line2D.Float(xOrigin + i * squareSize, yOrigin, xOrigin + i * squareSize, yOrigin + length));
		}
		for (int i = 0; i < data.getNbLines(); i++) {
			
			for (int j = 0; j < data.getNbColumns(); j++) {
				if (i == 2 || i == 3) {
					graphics.setColor(Color.blue);
				} else {
					graphics.setColor(colorIn);
				}
				// couleur case
				graphics.fill(drawCell(i, j));
				graphics.setColor(data.getCellColor(i, j));
			}
		}
	}

	@Override
	public void drawOnlyOneCell(Graphics2D graphics, SimpleGameData data, int x, int y) {
		graphics.setColor(Color.BLACK);
		graphics.fill(new Rectangle2D.Float(x, y, 10, 10));
	}

	/**
	 * Draws only the cell specified by the given coordinates in the game board from
	 * its data, using an existing Graphics2D object.
	 * 
	 * @param graphics a Graphics2D object provided by the default method
	 *                 {@code draw(ApplicationContext, GameData)}
	 * @param data     the GameData containing the game data.
	 * @param moving   the movings element.
	 */
	public void drawRectangleElement(Graphics2D graphics, SimpleGameData data, MovingElement moving, Color color) {
		graphics.setColor(graphics.getBackground());
		graphics.fill(moving.drawRectangle());
		moving.move();
		graphics.setColor(color);
		graphics.fill(moving.drawRectangle());
	}

	public void drawAllRectangleElement(ApplicationContext context, SimpleGameData data,
			ArrayList<HorizontalMovingElement> entite) {
		for (int i = 0; i < entite.size(); i++) {
			drawRectangleElement(context, data, entite.get(i), entite.get(i).getColor());
		}
	}

	@Override
	public void drawRoundElement(Graphics2D graphics, SimpleGameData data, MovingElement moving, Color color) {
		graphics.setColor(graphics.getBackground());
		graphics.fill(moving.drawEllipse());
		moving.move();
		graphics.setColor(color);
		graphics.fill(moving.drawEllipse());

	}

	public void drawAllRoundElement(ApplicationContext context, SimpleGameData data,
			ArrayList<HorizontalMovingElement> entite) {
		for (int i = 0; i < entite.size(); i++) {
			drawRoundElement(context, data, entite.get(i), entite.get(i).getColor());
		}
	}

	@Override
	public void drawString(Graphics2D graphics, int x, int y, String txt) {
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("TimesRoman", Font.BOLD, 20));
		graphics.drawString(txt, x, y);

	}

}
