package game.data;



import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public interface MovingElement {
	
	public void move();

	public Rectangle2D.Float drawRectangle();
	public Ellipse2D.Float drawEllipse();
	
}
