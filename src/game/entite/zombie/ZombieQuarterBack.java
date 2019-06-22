package game.entite.zombie;

import java.awt.Color;
import java.util.ArrayList;

import game.entite.EntiteInterface;
import game.view.HorizontalMovingElement;

public class ZombieQuarterBack implements EntiteInterface {
	private int x;
	private int y;
	private int speed;
	private int size;
	private Color color;
	private int health;
	private int damage;

	private ZombieQuarterBack(int x, int y, int speed, int health, int damage, int size) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.color = new Color(237, 113, 14);
		this.health = health;
		this.damage = damage;
		this.size = size;
	}

	public static ZombieQuarterBack spawnQuarterBackZombie(int x_data, int y_data) {
		return new ZombieQuarterBack(x_data, y_data, -2, 1600, 20, 150);
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
	public String toString() {
		return "ZombieQuarterBack [health=" + health + " coordonnee : " + x + "   " + y + " ]";
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
		x += speed;

	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isPlante() {
		return false;
	}

	@Override
	public void takeDamage(int dmg) {

		health -= dmg;

	}

	@Override
	public void setCoordonne(int x, int y) {
		this.x = x;
		this.y = y;

	}



	@Override
	public EntiteInterface haveSame() {
		return spawnQuarterBackZombie(x, y);
	}

	@Override
	public boolean explode(int time, ArrayList<EntiteInterface> zombieInData,
			ArrayList<HorizontalMovingElement> zombieInVue) {
		// TODO Auto-generated method stub
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
