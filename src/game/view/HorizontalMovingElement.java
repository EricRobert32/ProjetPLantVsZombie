package game.view;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import game.data.MovingElement;
import game.entite.EntiteInterface;
import game.entite.plante.PlanteInterface;

import java.awt.geom.Rectangle2D;


public class HorizontalMovingElement implements MovingElement{
	
	private int x;
	private int y;
	private double speed;
	private Color color;
	private int size;
	private EntiteInterface entite;
	

	public HorizontalMovingElement(int x, int y, double speed,Color blue,int size,EntiteInterface entite) {
		this.x=x;
		this.y=y;
		this.speed = speed;
		this.color=blue;
		this.size = size;
		this.entite=entite;
	}
	
	@Override
	public String toString() {
		return "hme " +entite.toString();
	}
	
	@Override
	public void move() {
		x+=speed;
	}
	
	public Color getColor() {
		return color;
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public int getSize() {
		return size;
	}
	public EntiteInterface getEntite() {
		return entite;
	}
	
	public void setspeed(double speed) {
		this.speed=speed;

	}
	
	
	@Override
	public Rectangle2D.Float drawRectangle(){
		//C est ici on modifie la taille des moving elm
		return new Rectangle2D.Float(x,y, size,size);
		
	}
	
	
	@Override
	public Ellipse2D.Float drawEllipse() {
		
		return new Ellipse2D.Float(x, y, size, size);
	}
}
