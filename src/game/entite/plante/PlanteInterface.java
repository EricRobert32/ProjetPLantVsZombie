package game.entite.plante;

import java.util.ArrayList;

import game.entite.EntiteInterface;
import game.view.HorizontalMovingElement;
import game.view.SimpleGameView;

public interface PlanteInterface extends EntiteInterface {

	// Avoir la zone des degat a afficher
	public HorizontalMovingElement pattern(SimpleGameView view);

	// Renvoie true ou false si un ennemy est a porté de l entite
	public boolean enemyNearBy(ArrayList<EntiteInterface> zombieInData,int time);

	// inflige des degat a l ennemy a porter
	public void shoot(ArrayList<EntiteInterface> zombieInData, SimpleGameView view, int time,
			ArrayList<HorizontalMovingElement> projectile);

	//Cooldown
	public int getCooldawn();
	public void cooldawnDown();
	public void setCooldawn();
	public int getPrice();
	
	//
	

}
