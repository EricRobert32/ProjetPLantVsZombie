package game.entite;

import java.awt.Color;
import java.util.ArrayList;

import game.entite.plante.PlanteInterface;
import game.view.HorizontalMovingElement;

public interface EntiteInterface {

	public static int shootrate = 25;

	public void setCoordonne(int x, int y);

	public int getX();

	public int getY();

	public int getSize();

	public int getSpeed();

	public int getHealth();

	public int getDamage();

	public Color getColor();
	
	public int getRange();


	public void move();

	public boolean isPlante();
	
	//indique si la plante est aquatique ou non
	public boolean isAquaticEntitie();


	// reduis la vie de l entite
	public void takeDamage(int dmg);
	
	// renvoie la même entite (remet les caracteristique de base)
	public EntiteInterface haveSame();

	public boolean explode(int time, ArrayList<EntiteInterface> zombieInData,
			ArrayList<HorizontalMovingElement> zombieInVue);

}
