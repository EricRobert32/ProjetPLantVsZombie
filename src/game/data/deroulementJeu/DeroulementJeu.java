package game.data.deroulementJeu;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Set;

import fr.umlv.zen5.ApplicationContext;
import game.data.SimpleGameData;
import game.entite.EntiteInterface;
import game.entite.Lawnmower;
import game.entite.plante.CherryBomb;
import game.entite.plante.Peashooter;
import game.entite.plante.PlanteInterface;
import game.entite.plante.Repeater;
import game.entite.plante.WallNut;
import game.entite.zombie.ConeHeadZombie;
import game.entite.zombie.FlagZombie;
import game.entite.zombie.Zombie;
import game.view.HorizontalMovingElement;
import game.view.SimpleGameView;

public class DeroulementJeu {
	// Plateau de jeu
	private SimpleGameData data;
	private SimpleGameView view;
	// Platau presentation des plante
	private SimpleGameData dataPlante;
	private SimpleGameView viewPlante;
	// Les entite
	private ArrayList<EntiteInterface> zombieInData;
	private ArrayList<PlanteInterface> planteInData;

	// Les affichage
	private ArrayList<HorizontalMovingElement> zombieInView;
	private ArrayList<HorizontalMovingElement> planteInView;
	private ArrayList<HorizontalMovingElement> planteInViewPlante;
	private ArrayList<HorizontalMovingElement> projectile;
	private ArrayList<HorizontalMovingElement> lawnmower;
	private ArrayList<HorizontalMovingElement> sunInterface;

	// soleil

	// Utilitaire
	private boolean pool;
	private boolean day;
	private PlanteInterface choice;
	private ApplicationContext context;
	private int moment;
	private Random rand;
	private int compteurSoleil;
	private float dataPlanteX;
	private float dataPlanteY;
	//////////////////////////////////////// :

	// ---------------------------> MAIN METHODS
	// >------------------------------------------------------------------------------------
	// //

	/////

	public void actionFromClickSun(Point2D.Float location) {
		for (int i = 2; i < sunInterface.size(); i++) {
			if (location.x >= sunInterface.get(i).getX()
					&& location.x <= sunInterface.get(i).getX() + view.getSquareSize() / 2
					&& location.y >= sunInterface.get(i).getY()
					&& location.y <= sunInterface.get(i).getY() + view.getSquareSize() / 2) {
				compteurSoleil += 50;
				sunInterface.remove(i);

			}
		}

	}

	// initialisation d'une partie

	public DeroulementJeu(boolean pool, boolean day, float width, float height, ApplicationContext context) {

		if (pool == true) {
			this.data = new SimpleGameData(6, 8);
			data.setRandomMatrix();
			data.setPool();

		} else {
			this.data = new SimpleGameData(5, 8);
			data.setRandomMatrix();
		}
		this.view = SimpleGameView.initGameGraphics((int) width / 5, (int) height / 8, (int) width / 2.2, data);

		this.dataPlante = new SimpleGameData(3, 1);
		dataPlante.setRandomMatrix();
		this.viewPlante = SimpleGameView.initGameGraphics(0, (int) height / 4, 600, dataPlante);
		viewPlante.draw2(context, dataPlante, Color.red, new Color(96, 16, 16));

		this.zombieInData = new ArrayList<EntiteInterface>();
		this.planteInData = new ArrayList<PlanteInterface>();

		this.zombieInView = new ArrayList<HorizontalMovingElement>();
		this.planteInView = new ArrayList<HorizontalMovingElement>();
		this.planteInViewPlante = new ArrayList<HorizontalMovingElement>();
		this.projectile = new ArrayList<HorizontalMovingElement>();

		this.lawnmower = new ArrayList<HorizontalMovingElement>();
		setLawnmower();
		data.setLawnmower();
		this.sunInterface = new ArrayList<HorizontalMovingElement>();

		this.pool = pool;
		this.day = day;
		this.choice = null;
		this.context = context;
		this.moment = 100;
		this.rand = new Random();
		this.compteurSoleil = 0;

	}

	public void initialisePlanteSelection(ArrayList<PlanteInterface> plant, int time) {

		for (int i = 0; i < plant.size(); i++) {
			plant.get(i).setCoordonne(i, 0);
			dataPlante.spawnEntite(plant.get(i));
			planteInViewPlante.add(new HorizontalMovingElement((int) viewPlante.xFromI(plant.get(i).getY()),
					(int) viewPlante.yFromJ(plant.get(i).getX()), plant.get(i).getSpeed(), plant.get(i).getColor(),
					plant.get(i).getSize(), plant.get(i)));
		}
		viewPlante.drawAllRoundElement(context, dataPlante, planteInViewPlante);

	}

	public void drawGameBoard(int time) {
		if (time % 10 == 0) {
			// Dessine la matrice leplacer lors du clic pour afficher la zone clique ?

			if (pool == true) {
				view.drawPool(context, data, Color.LIGHT_GRAY, new Color(5, 64, 10));
			} else {

				view.draw2(context, data, Color.LIGHT_GRAY, new Color(5, 64, 10));
			}
		}

	}

	public void drawEntities() {
		view.drawAllRoundElement(context, data, planteInView);
		view.drawAllRectangleElement(context, data, zombieInView);
		view.drawAllRectangleElement(context, data, projectile);
		view.drawAllRectangleElement(context, data, lawnmower);

	}

	public void manageGame(int time, ArrayList<EntiteInterface> zombieChoice) {
		spawnZombie(time, zombieChoice);
		deplaceZombie();
		entiteTakeDammage(time);
	}
	// -------------> SOLEIL <----------------- //
	// SOLEIL //

	public void initialiseCompteurInterface() {
		HorizontalMovingElement cn1 = new HorizontalMovingElement(0, 0, 0, Color.darkGray, 150, null);
		HorizontalMovingElement s2 = new HorizontalMovingElement(40, 50, 0, Color.yellow, view.getSquareSize() / 2,
				null);
		sunInterface.add(cn1);
		sunInterface.add(s2);

	}

	public void drawCompteurInterface() {
		view.drawAllRectangleElement(context, data, sunInterface);
		view.drawString(context, 30, 30, Integer.toString(compteurSoleil));
	}

	public void manageSun(int time) {
		float x = view.xFromI(0) + rand.nextInt(view.getSquareSize() * (data.getNbColumns() - 1));
		float y = view.yFromJ(0) + rand.nextInt(view.getSquareSize() * (data.getNbLines() - 1));
		HorizontalMovingElement s3 = new HorizontalMovingElement((int) x, (int) y, 0, Color.yellow,
				view.getSquareSize() / 2, null);

		if (time % 50 == 0) {
			sunInterface.add(s3);
		}
	}

	public void drawCooldown(ArrayList<PlanteInterface> plantChoice, ApplicationContext context) {
		for (int p = 0; p < plantChoice.size(); p++) {
			view.drawString(context, (int) viewPlante.xFromI(0),
					(int) viewPlante.yFromJ(p) + (viewPlante.getSquareSize()),
					Integer.toString(plantChoice.get(p).getCooldawn()));
			if (plantChoice.get(p).getCooldawn() > 0) {
				plantChoice.get(p).cooldawnDown();
				viewPlante.draw2(context, dataPlante, Color.red, new Color(96, 16, 16));
				viewPlante.drawAllRoundElement(context, dataPlante, planteInViewPlante);

			}

		}
	}
	// -------------> DEBUG <------------------ //

	public int addPlantedebug() {

		int y = rand.nextInt(data.getNbColumns());

		int x = rand.nextInt(data.getNbLines());

		while (pool == true && (x == 3 || x == 2)) {
			x = rand.nextInt(data.getNbLines());
		}

		if (data.isPlante(x, y)) {
			return 0;
		}

		choice = Peashooter.SpawnPeashooter(x, y);

		data.addPlante(x, y, choice);
		data.getCellValue(x, y);
		System.out.println(data.getCellValue(x, y));
		// System.out.println(x+" "+y+" "+ choice.getSpeed()+" "+choice.getColor()+" "+
		// choice.getSize());

		planteInData.add(choice);

		planteInView.add(new HorizontalMovingElement((int) view.xFromI(y), (int) view.yFromJ(x), choice.getSpeed(),
				choice.getColor(), choice.getSize(), choice));

		choice = null;
		return 1;

	}

	public void selectPlante(Point2D.Float location) {
		// on stocke la plante selectionner dans la variable choice

		PlanteInterface plant = (PlanteInterface) dataPlante
				.getEntiteFromCell(viewPlante.lineFromY(location.y), viewPlante.columnFromX(location.x)).get(0);

		if (plant.getCooldawn() == 0 && compteurSoleil >= plant.getPrice()) {
			choice = (PlanteInterface) dataPlante
					.getEntiteFromCell(viewPlante.lineFromY(location.y), viewPlante.columnFromX(location.x)).get(0);
			dataPlanteY = location.y;
			dataPlanteX = location.x;
		} else {
			System.out.println("Laisser reposer vos plante");
		}

		// get 0 car toute les plante sont en indice 0

	}

	public void addPlante(Point2D.Float location) {
		if (!(data.isFloor(view.lineFromY(location.y), view.columnFromX(location.x)))
				&& (choice.isAquaticEntitie() == false)) {

			System.out.println("Impossible de poser une plante terrestre sur cette surface");
			choice = null;

		} else {

			if (choice
					.isAquaticEntitie() == !(data.isFloor(view.lineFromY(location.y), view.columnFromX(location.x)))) {

				dataPlante.getPlantFromCell(viewPlante.lineFromY(dataPlanteY), viewPlante.columnFromX(dataPlanteX))
						.setCooldawn();
				compteurSoleil -= choice.getPrice();

				// ajout dans la data
				// set pour changer les coordonne de data plante a data game
				choice.setCoordonne(view.lineFromY(location.y), view.columnFromX(location.x));
				System.out.println(choice);
				// Have same permet d avoir une autre meme plante que celle de la data plante (
				// de ne pas avoir la même se qui crée des erreur de verification)
				choice = (PlanteInterface) choice.haveSame();
				data.addPlante(view.lineFromY(location.y), view.columnFromX(location.x), choice);

				// ajout dans la vue
				planteInView.add(new HorizontalMovingElement((int) view.xFromI(view.columnFromX(location.x)),
						(int) view.yFromJ(view.lineFromY(location.y)), choice.getSpeed(), choice.getColor(),
						choice.getSize(), choice));

				// ajouter dans une liste pour s assurer de sa durer de vie
				planteInData.add(choice);

				// je vide la variable qui contenais la plante
				choice = null;
				// System.out.println(data.getCellValue(view.lineFromY(location.y),view.columnFromX(location.x)));

			}

		}
	}

	// ----------------------------> GETTEUR >--------------------- //

	public SimpleGameData getData() {
		return data;
	}

	public SimpleGameView getView() {
		return view;
	}

	public SimpleGameData getDataPlante() {

		return dataPlante;
	}

	public SimpleGameView getViewPlante() {
		return viewPlante;
	}

	public PlanteInterface getChoice() {
		return choice;
	}

	// --------------------------> SUB-METHODS
	// >-------------------------------------------------------- //
	private void createMoment() {

		moment = 200 + rand.nextInt(400 - 200);
//		moment= allMoment.get(rand.nextInt(allMoment.size()));
	}

	private void zombieWave(ArrayList<EntiteInterface> zombieChoice) {

		int ligne = rand.nextInt(data.getNbLines());
		while (pool == true && (ligne == 3 || ligne == 2)) {
			ligne = rand.nextInt(data.getNbLines());
		}

		FlagZombie fzombie = FlagZombie.spawnZombie(ligne, data.getNbColumns() - 1);
		zombieInData.add(fzombie);
		data.spawnEntite(fzombie);
		HorizontalMovingElement hmeFlagZombie = new HorizontalMovingElement((int) view.xFromI(fzombie.getY() + 2),
				(int) view.yFromJ(fzombie.getX()), fzombie.getSpeed(), fzombie.getColor(), fzombie.getSize(), fzombie);
		zombieInView.add(hmeFlagZombie);
		System.out.println(fzombie);
		int nbZombie = 7 + rand.nextInt(13 - 7);

		for (int i = 0; i < nbZombie; i++) {

			ligne = rand.nextInt(data.getNbLines());
			while (pool == true && (ligne == 3 || ligne == 2)) {
				ligne = rand.nextInt(data.getNbLines());
			}

			switch (rand.nextInt(2)) {
			case 0:
				if (zombieChoice.get(0).isAquaticEntitie() == true) {
					ligne = 2 + rand.nextInt(3 - 2);
				}
				zombieChoice.get(0).setCoordonne(ligne, data.getNbColumns() - 1);
				zombieInData.add(zombieChoice.get(0).haveSame());
				data.spawnEntite(zombieChoice.get(0).haveSame());
				zombieInView.add(new HorizontalMovingElement((int) view.xFromI(zombieChoice.get(0).getY() + 2),
						(int) view.yFromJ(zombieChoice.get(0).getX()), zombieChoice.get(0).getSpeed(),
						zombieChoice.get(0).getColor(), zombieChoice.get(0).getSize(), zombieChoice.get(0)));

				System.out.println(zombieChoice.get(0));
				break;

			case 1:
				if (zombieChoice.get(1).isAquaticEntitie() == true) {
					ligne = 2 + rand.nextInt(3 - 2);
				}
				zombieChoice.get(1).setCoordonne(ligne, data.getNbColumns() - 1);
				zombieInData.add(zombieChoice.get(1).haveSame());
				data.spawnEntite(zombieChoice.get(1).haveSame());
				zombieInView.add(new HorizontalMovingElement((int) view.xFromI(zombieChoice.get(0).getY() + 2),
						(int) view.yFromJ(zombieChoice.get(1).getX()), zombieChoice.get(1).getSpeed(),
						zombieChoice.get(1).getColor(), zombieChoice.get(1).getSize(), zombieChoice.get(1)));

				System.out.println(zombieChoice.get(1));

				break;
			}
		}

	}

	private void spawnZombie(int time, ArrayList<EntiteInterface> zombieChoice) {

		if (time % 900 == 0) {
			zombieWave(zombieChoice);
		}

		if (time < 1500 && time % moment == 0) {
			createMoment();
			// cree zombie et ajt a la matrice
			int ligne = rand.nextInt(data.getNbLines());

			while (pool == true && (ligne == 3 || ligne == 2)) {
				ligne = rand.nextInt(data.getNbLines());
			}

			if (zombieChoice.get(0).isAquaticEntitie() == true) {
				ligne = 2 + rand.nextInt(3 - 2);
			}

			zombieChoice.get(0).setCoordonne(ligne, data.getNbColumns() - 1);

			zombieInData.add(zombieChoice.get(0).haveSame());
			data.spawnEntite(zombieChoice.get(0).haveSame());
			zombieInView.add(new HorizontalMovingElement((int) view.xFromI(zombieChoice.get(0).getY() + 2),
					(int) view.yFromJ(zombieChoice.get(0).getX()), zombieChoice.get(0).getSpeed(),
					zombieChoice.get(0).getColor(), zombieChoice.get(0).getSize(), zombieChoice.get(0)));

			System.out.println(zombieChoice.get(0));
		} else {

			if (time % moment == 0) {
				int ligne = rand.nextInt(data.getNbLines());
				while (pool == true && (ligne == 3 || ligne == 2)) {
					ligne = rand.nextInt(data.getNbLines());
				}

				switch (rand.nextInt(2)) {
				case 0:
					if (zombieChoice.get(0).isAquaticEntitie() == true) {
						ligne = 2 + rand.nextInt(3 - 2);
					}
					zombieChoice.get(0).setCoordonne(ligne, data.getNbColumns() - 1);
					zombieInData.add(zombieChoice.get(0).haveSame());
					data.spawnEntite(zombieChoice.get(0).haveSame());
					zombieInView.add(new HorizontalMovingElement((int) view.xFromI(zombieChoice.get(0).getY() + 2),
							(int) view.yFromJ(zombieChoice.get(0).getX()), zombieChoice.get(0).getSpeed(),
							zombieChoice.get(0).getColor(), zombieChoice.get(0).getSize(), zombieChoice.get(0)));

					System.out.println(zombieChoice.get(0));
					break;

				case 1:
					if (zombieChoice.get(1).isAquaticEntitie() == true) {
						ligne = 2 + rand.nextInt(3 - 2);
					}
					zombieChoice.get(1).setCoordonne(ligne, data.getNbColumns() - 1);
					zombieInData.add(zombieChoice.get(1).haveSame());
					data.spawnEntite(zombieChoice.get(1).haveSame());
					zombieInView.add(new HorizontalMovingElement((int) view.xFromI(zombieChoice.get(0).getY() + 2),
							(int) view.yFromJ(zombieChoice.get(1).getX()), zombieChoice.get(1).getSpeed(),
							zombieChoice.get(1).getColor(), zombieChoice.get(1).getSize(), zombieChoice.get(1)));

					System.out.println(zombieChoice.get(1));

					break;
				}
			}
		}

	}

	//////////////////////////////////////////////////////////////////////
	private void deplaceZombie() {
		for (int i = 0; i < zombieInData.size(); i++) {
			// System.out.println(zombie_in_data.get(i).getY());
			if (zombieInView.get(i).getX() <= (int) view.xFromI(zombieInData.get(i).getY()) + 20
					&& data.isLawnmover(zombieInData.get(i).getX(), zombieInData.get(i).getY())) {
				if (zombieInData.get(i).getY() - 1 < 0) {
					System.out.println("activate");
					activateLawnmower(zombieInView.get(i).getY());
					

				}
			}

			if (zombieInView.get(i).getX() <= (int) view.xFromI(zombieInData.get(i).getY()) + 0) {
				if (zombieInData.get(i).getY() - 1 < 0
						&& data.isLawnmover(zombieInData.get(i).getX(), zombieInData.get(i).getY()) == false) {
					System.out.println("GAME OVER");
					context.exit(5);

				}
				// System.out.println("traverse");

				// On fait avancer le zombie, on en rajoute a la case d apres
				data.spawnEntite(Zombie.spawnZombie(zombieInData.get(i).getX(), zombieInData.get(i).getY() - 1));
				// je la retire de la la case d avant
				data.removeEntite(zombieInData.get(i).getX(), zombieInData.get(i).getY());

				zombieInData.get(i).setCoordonne(zombieInData.get(i).getX(), zombieInData.get(i).getY() - 1);
				zombieInData.set(i, zombieInData.get(i));

				// je met a jour la liste de zombie pour avoir un constat sur de vie

				System.out.println(zombieInData.get(i));

			}

		}
	}

	private void entiteTakeDammage(int time) {
		// degat des zombie sur plante
		for (int p = 0; p < planteInView.size(); p++) {

			for (int z = 0; z < zombieInView.size(); z++) {

				// si la plante et le zombie entre en colision, le zombie s arrete
				if (planteInView.size() > 0
						&& planteInView.get(p).getX() + planteInView.get(p).getSize() >= zombieInView.get(z).getX()
						&& planteInView.get(p).getX() <= zombieInView.get(z).getX()
						&& planteInView.get(p).getY() == zombieInView.get(z).getY()) {
					zombieInView.get(z).setspeed(0.00);

					// tt les 5 tic, le zombie inflige des degat (passer par data.getEntiteFromCell

					if (time % 5 == 0) {

						planteInView.get(p).getEntite().takeDamage(zombieInView.get(z).getEntite().getDamage());

//						data.getEntiteFromCell(view.lineFromY(planteInVue.get(p).getY()),
//								view.columnFromX(planteInVue.get(p).getX()))
//								.takeDamage(zombieInVue.get(z).getEntite().getDamage());
						System.out.println(data.getEntiteFromCell(view.lineFromY(planteInView.get(p).getY()),
								view.columnFromX(planteInView.get(p).getX())).get(0));

						if (planteInView.get(p).getEntite().getHealth() <= 0) {
							for (int zb = 0; zb < zombieInView.size(); zb++) {
								if (planteInView.size() > 0
										&& planteInView.get(p).getX() + planteInView.get(p).getSize() >= zombieInView
												.get(zb).getX()
										&& planteInView.get(p).getX() <= zombieInView.get(zb).getX()
										&& planteInView.get(p).getY() == zombieInView.get(zb).getY()) {
									System.out.println(1000);
									zombieInView.get(zb).setspeed(zombieInView.get(zb).getEntite().getSpeed());
								}

							}
							zombieInView.get(z).setspeed(zombieInView.get(z).getEntite().getSpeed());
							data.removeEntite(view.lineFromY(planteInView.get(p).getY()),
									view.columnFromX(planteInView.get(p).getX()));

							planteInView.remove(p);
							planteInData.remove(p);
							//
							break;

						}
					}

//					System.out.println(data.getEntiteFromCell(view.lineFromY(planteInVue.get(p).getY()),
//							view.columnFromX(planteInVue.get(p).getX())));

				}
				// System.out.println(zombieInVue.get(i).getX());
			}
		}

		// --------------> PLANTE <-------------- //
		// tir
		for (int p = 0; p < planteInData.size(); p++) {

			planteInData.get(p).shoot(zombieInData, view, time, projectile);

			// explosion
			if (planteInData.get(p).explode(time, zombieInData, zombieInView) == true) {

				data.removeEntite(planteInData.get(p).getX(), planteInData.get(p).getY());
				planteInData.remove(p);
				planteInView.remove(p);

			}
		}

		// gestion projectile
		for (int p = 0; p < projectile.size(); p++) {

			// TONDEUSE
			if (projectile.get(p).getEntite().getHealth() == 0
					&& projectile.get(p).getX() > view.getSquareSize() * (data.getNbColumns() + 10)) {
				data.removeLawnmover(view.lineFromY(projectile.get(p).getY()));
				projectile.remove(p);
				break;
			}

			// Explosif
			if (projectile.get(p).getEntite().explode(time, zombieInData, zombieInView) == true) {
				projectile.remove(p);
				break;
			}

			for (int z = 0; z < zombieInView.size(); z++) {

				if (projectile.get(p).getX() >= zombieInView.get(z).getX()
						&& projectile.get(p).getY() == zombieInView.get(z).getY()) {
					zombieInData.get(z).takeDamage(projectile.get(p).getEntite().getDamage());
					System.out.println(zombieInData.get(z));

					if (!(projectile.get(p).getEntite().getHealth() == 0)
							|| (projectile.get(p).getX() > (view.getSquareSize()
									* (data.getNbColumns() + projectile.get(p).getEntite().getRange())))) {
						projectile.remove(p);
						break;
					}

				}

			}

		}

		// verif de la vie zombie
		for (int z = 0; z < zombieInData.size(); z++) {

			if (zombieInData.get(z).getHealth() <= 0) {
				data.removeEntite(zombieInData.get(z).getX(), zombieInData.get(z).getY());
				zombieInData.remove(z);
				zombieInView.remove(z);
			}
		}

	}

	// -------------> TONDEUSE <------------- //
	private void setLawnmower() {
		for (int i = 0; i < data.getNbLines(); i++) {
			Lawnmower t = Lawnmower.SpawnLawnmover(0, i);
			lawnmower.add(new HorizontalMovingElement((int) view.xFromI(t.getX() - 1), (int) view.yFromJ(t.getY()),
					t.getSpeed(), t.getColor(), t.getSize(), t));

		}
	}

	private void activateLawnmower(int y) {

		for (int lawn = 0; lawn < lawnmower.size(); lawn++) {
			if (lawnmower.get(lawn).getY() == y) {
				lawnmower.get(lawn).setspeed(20.00);
				projectile.add(lawnmower.get(lawn));
				lawnmower.remove(lawn);

				break;

			}

		}

	}

}