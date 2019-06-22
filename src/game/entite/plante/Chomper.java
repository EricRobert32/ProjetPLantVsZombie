package game.entite.plante;

import java.awt.Color;
import java.util.ArrayList;

import game.entite.EntiteInterface;
import game.view.HorizontalMovingElement;
import game.view.SimpleGameView;

public class Chomper implements PlanteInterface {
	private int x;
	private int y;
	private Color color;
	private int size;
	private int health;
	private int damage;
	private int time2Eat;
	private int time2Swallow;
	private boolean canEat;

	private Chomper(int x, int y, int health, int damage, int size, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.health = health;
		this.damage = damage;
		this.size = size;
		this.time2Swallow=150;
		this.canEat =true;

	}

	public static Chomper SpawnPeashooter(int x, int y) {
		return new Chomper(x, y, 300, 20,150, new Color(135, 54, 138));
	}

	@Override
	public String toString() {
		return "Chomper [health=" + health + " coordonne : " + x + "   " + y + "]";
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
		return SpawnPeashooter(this.x, this.y);
	}

	@Override
	public boolean enemyNearBy(ArrayList<EntiteInterface> zombieInData,int time) {
		for (int z = 0; z < zombieInData.size(); z++) {
			if (zombieInData.get(z).getX() == x && zombieInData.get(z).getY()==y+1) {
				return true;
			}

		}
		return false;
	}

	@Override
	public HorizontalMovingElement pattern(SimpleGameView view) {
		return new HorizontalMovingElement((int)view.xFromI(y),(int)view.yFromJ(x), 20, new Color(212, 239, 223  ), 30, haveSame());
	}

	@Override
	public void shoot (ArrayList<EntiteInterface> zombieInData,SimpleGameView view,int time,ArrayList<HorizontalMovingElement> projectile) {
		if (enemyNearBy(zombieInData, 0)== true && canEat==true) {
			projectile.add(pattern(view));
			
		}
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void cooldawnDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCooldawn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRange() {
		// TODO Auto-generated method stub
		return 0;
	}
	


}
