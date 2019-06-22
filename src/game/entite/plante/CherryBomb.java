package game.entite.plante;

import java.awt.Color;
import java.util.ArrayList;

import game.entite.EntiteInterface;
import game.view.HorizontalMovingElement;
import game.view.SimpleGameView;

public class CherryBomb implements PlanteInterface {
	private int x;
	private int y;
	private Color color;
	private int size;
	private int health;
	private int damage;
	private int cooldown;
	private int price;

	private CherryBomb(int x, int y, int health, int damage, int size, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.health = health;
		this.damage = damage;
		this.size = size;
		this.cooldown = 35;
		this.price=150;
	}

	public static CherryBomb SpawnCherryBomb(int x, int y) {
		return new CherryBomb(x, y, 90000, 1800, 150, new Color(255, 51, 153));
	}

	@Override
	public String toString() {
		return "CherryBomb [health=" + health + " coordonne : " + x + "   " + y + "]";
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
		return SpawnCherryBomb(this.x, this.y);
	}

	@Override
	public boolean enemyNearBy(ArrayList<EntiteInterface> zombieInData,int time) {
		return true;
	}

	@Override
	public HorizontalMovingElement pattern(SimpleGameView view) {
		return new HorizontalMovingElement((int) view.xFromI(y - 1), (int) view.yFromJ(x - 1) - 1, 0, Color.red,
				view.getSquareSize() * 3, haveSame());
	}

	@Override
	public void shoot(ArrayList<EntiteInterface> zombieInData, SimpleGameView view, int time,
			ArrayList<HorizontalMovingElement> projectile) {
		if (time % 20 == 0) {
			projectile.add(pattern(view));
		}

	}

	@Override
	public boolean explode(int time, ArrayList<EntiteInterface> zombieInData,
			ArrayList<HorizontalMovingElement> zombieInVue) {
		if (time % shootrate == 0) {
			
			for (int ligne = -1; ligne < 2; ligne++) {
				for (int colonne = -1; colonne < 2; colonne++) {
					//System.out.println((x+ligne) + "  "+(y+colonne));

					for (int z = 0; z < zombieInData.size(); z++) {
						if (zombieInData.get(z).getX()==x+ligne && zombieInData.get(z).getY()==y+colonne ) {
							zombieInData.get(z).takeDamage(damage);
							System.out.println(zombieInData.get(z));
							
						}
					}
				}

			}
			return true;
		}
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
		cooldown=50;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public int getRange() {
		// TODO Auto-generated method stub
		return 0;
	}

}
