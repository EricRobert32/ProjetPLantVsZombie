package game.entite.plante;

import java.awt.Color;
import java.util.ArrayList;

import game.entite.EntiteInterface;
import game.view.HorizontalMovingElement;
import game.view.SimpleGameView;

public class WallNut implements PlanteInterface {
	private int x;
	private int y;
	private Color color;
	private int size;
	private int health;
	private int damage;
	private int cooldown;
	private int price;

	public WallNut(int x, int y, int health, int damage, Color color, int size) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.size = size;
		this.health = health;
		this.damage = damage;
		this.cooldown = 20;
		this.price = 50;
	}

	public static WallNut SpawnWallNut(int x, int y) {
		return new WallNut(x, y, 4000, 0, new Color(153, 102, 51), 150);
	}

	@Override
	public String toString() {
		return "WallNut [health=" + health + " coordonne : " + x + "   " + y + "]";
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
	public int getSpeed() {
		return 0;
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
		x += 0;

	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isPlante() {
		return true;
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
	public PlanteInterface haveSame() {
		return SpawnWallNut(this.x, this.y);
	}

	@Override
	public boolean enemyNearBy(ArrayList<EntiteInterface> zombieInData,int time) {

		return false;
	}

	@Override
	public HorizontalMovingElement pattern(SimpleGameView view) {
		return null;
	}

	@Override
	public void shoot(ArrayList<EntiteInterface> zombieInData, SimpleGameView view, int time,
			ArrayList<HorizontalMovingElement> projectile) {
		
	}


	@Override
	public boolean explode(int time, ArrayList<EntiteInterface> zombieInData,
			ArrayList<HorizontalMovingElement> zombieInVue) {
		return false;
	}
	
	@Override
	public boolean isAquaticEntitie() {
		return false;
	}

	@Override
	public int getCooldawn() {
		return cooldown;
	}
	
	@Override
	public void cooldawnDown() {
		cooldown-=1;
		
	}
	
	@Override
	public void setCooldawn() {
		cooldown=20;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public int getRange() {
		return 0;
	}

	
}
