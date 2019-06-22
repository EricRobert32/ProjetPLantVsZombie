package game.entite;

import java.awt.Color;
import java.util.ArrayList;

import game.entite.plante.PlanteInterface;
import game.view.HorizontalMovingElement;

public class Lawnmower implements EntiteInterface {
	private int x;
	private int y;
	private int speed;
	private Color color;
	private int size;
	private int health;
	private int damage;

	
	
	

	private Lawnmower(int x, int y, Color color, int size, int health, int damage,int speed) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
		this.size = size;
		this.health = health;
		this.damage = damage;
		this.speed = speed;
	}
	
	public static Lawnmower SpawnLawnmover(int x, int y) {
		return new Lawnmower(x, y, new Color(176, 20, 105 ), 150, 0, 999999,0);
	}
	

	@Override
	public void setCoordonne(int x, int y) {
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public int getSpeed() {
		
		return speed;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void move() {
		x+=speed;
	}

	@Override
	public boolean isPlante() {
		return false;
	}

	@Override
	public void takeDamage(int dmg) {

	}



	@Override
	public PlanteInterface haveSame() {
		return null;
	}

	@Override
	public boolean explode(int time, ArrayList<EntiteInterface> zombieInData,
			ArrayList<HorizontalMovingElement> zombieInVue) {
		return false;
	}

	@Override
	public int getRange() {
		return 0;
	}

	@Override
	public boolean isAquaticEntitie() {
		return false;
	}

}
