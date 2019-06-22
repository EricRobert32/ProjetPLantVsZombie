package game.view;

import java.awt.Color;
import java.awt.Graphics2D;

import fr.umlv.zen5.ApplicationContext;
import game.data.MovingElement;
import game.data.SimpleGameData;

/**
 * The GameView class is in charge of the graphical view of a clicky game.
 */
public interface GameView {
	/**
	 * Transforms a real y-coordinate into the index of the corresponding line.
	 * 
	 * @param y a float y-coordinate
	 * @return the index of the corresponding line.
	 * @throws IllegalArgumentException if the float coordinate doesn't fit in the
	 *                                  game board.
	 */
	public int lineFromY(float y);

	/**
	 * Transforms a real x-coordinate into the index of the corresponding column.
	 * 
	 * @param x a float x-coordinate
	 * @return the index of the corresponding column.
	 * @throws IllegalArgumentException if the float coordinate doesn't fit in the
	 *                                  game board.
	 */
	public int columnFromX(float x);

	/**
	 * Draws the game board from its data, using an existing Graphics2D object.
	 * 
	 * @param graphics a Graphics2D object provided by the default method
	 *                 {@code draw(ApplicationContext, GameData)}
	 * @param data     the GameData containing the game data.
	 */
//	public void draw(Graphics2D graphics, SimpleGameData data);
//
//	/**
//	 * Draws the game board from its data, using an existing
//	 * {@code ApplicationContext}.
//	 * 
//	 * @param context the {@code ApplicationContext} of the game
//	 * @param data    the GameData containing the game data.
//	 */
//	public default void draw(ApplicationContext context, SimpleGameData data) {
//		context.renderFrame(graphics -> draw(graphics, data));
//	}
//	

	public void draw2(Graphics2D graphics, SimpleGameData data, Color colorLine, Color colorIn);

	public default void draw2(ApplicationContext context, SimpleGameData data, Color colorLine, Color colorIn) {
		context.renderFrame(graphics -> draw2(graphics, data, colorLine, colorIn));
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

	public void drawPool(Graphics2D graphics, SimpleGameData data, Color colorLine, Color colorIn);

	public default void drawPool(ApplicationContext context, SimpleGameData data, Color colorLine, Color colorIn) {
		context.renderFrame(graphics -> drawPool(graphics, data, colorLine, colorIn));
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
	public void drawOnlyOneCell(Graphics2D graphics, SimpleGameData data, int x, int y);

	/**
	 * Draws only the cell specified by the given coordinates in the game board from
	 * its data, using an existing {@code ApplicationContext}.
	 * 
	 * @param context the {@code ApplicationContext} of the game
	 * @param data    the GameData containing the game data.
	 * @param x       the float x-coordinate of the cell.
	 * @param y       the float y-coordinate of the cell.
	 */
	public default void drawOnlyOneCell(ApplicationContext context, SimpleGameData data, int x, int y) {
		context.renderFrame(graphics -> drawOnlyOneCell(graphics, data, x, y));
	}

	/**
	 * Draws only only the specified moving element in the game board from its data,
	 * using an existing Graphics2D object.
	 * 
	 * @param graphics a Graphics2D object provided by the default method
	 *                 {@code draw(ApplicationContext, GameData)}
	 * @param data     the GameData containing the game data.
	 * @param moving   the moving element.
	 */
	public void drawRectangleElement(Graphics2D graphics, SimpleGameData data, MovingElement moving, Color color);

	/**
	 * Draws only the specified moving element in the game board from its data,
	 * using an existing {@code ApplicationContext}.
	 * 
	 * @param context the {@code ApplicationContext} of the game
	 * @param data    the GameData containing the game data.
	 * @param moving  the moving element.
	 */
	public default void drawRectangleElement(ApplicationContext context, SimpleGameData data, MovingElement moving,
			Color color) {
		context.renderFrame(graphics -> drawRectangleElement(graphics, data, moving, color));
	}

	public void drawRoundElement(Graphics2D graphics, SimpleGameData data, MovingElement moving, Color color);

	public default void drawRoundElement(ApplicationContext context, SimpleGameData data, MovingElement moving,
			Color color) {
		context.renderFrame(graphics -> drawRoundElement(graphics, data, moving, color));
	}

	public void drawString(Graphics2D graphics, int x, int y, String txt);

	public default void drawString(ApplicationContext context, int x, int y, String txt) {
		context.renderFrame(graphics -> drawString(graphics, x, y, txt));
	}

}
