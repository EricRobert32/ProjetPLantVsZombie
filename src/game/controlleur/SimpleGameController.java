package game.controlleur;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;


import game.data.SimpleGameData;
import game.data.deroulementJeu.DeroulementJeu;
import game.entite.EntiteInterface;
import game.entite.plante.CherryBomb;
import game.entite.plante.Peashooter;
import game.entite.plante.PlanteInterface;
import game.entite.plante.WallNut;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import game.view.HorizontalMovingElement;
import game.view.SimpleGameView;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;

public class SimpleGameController {// initialise une localisation ( d un clic)
	private static Point2D.Float location = null;
	private static boolean pool;
	private static boolean day;
	private static ArrayList<PlanteInterface> plantChoice;
	private static ArrayList<EntiteInterface> zombieChoice;
	static void simpleGame(ApplicationContext context) {

		// debug
		boolean debug = false;

		// Fera office de chrono
		int time = 0;

		// get the size of the screen
		ScreenInfo screenInfo = context.getScreenInfo();
		float width = screenInfo.getWidth();
		float height = screenInfo.getHeight();
		// System.out.println("size of the screen (" + width + " x " + height + ")");

		////////////// DATA GAME////////////////////////////

		DeroulementJeu gameModel = new DeroulementJeu(pool, day, width, height, context);

		///////////// LES PLANTES
		
		gameModel.initialisePlanteSelection(plantChoice,time);
		
		//////////
		gameModel.initialiseCompteurInterface();
		
		///////////
		while (true) {
			gameModel.drawCompteurInterface();
			gameModel.manageSun(time);
			gameModel.drawCooldown(plantChoice,context);

			

			if (debug) {
				time += 2;
			} else {
				time += 1;
			}

			// System.out.println(debug);

			// System.out.println(time);
			if (time % 10 == 0) {


				// Dessine la matrice leplacer lors du clic pour afficher la zone clique ?
				gameModel.drawGameBoard(time);
			}
			gameModel.manageGame(time,zombieChoice);
			gameModel.drawEntities();

			Event event = context.pollOrWaitEvent(10); // modifier pour avoir un affichage fluide

			/////////////////////////// EVENEMENT /////////////////////
			if (event == null) { // no event
				continue;
			}
			Action action = event.getAction();

			if (action == Action.KEY_PRESSED) {

				// quite la fenetre
				if (event.getKey() == KeyboardKey.D) {
					System.out.println("D");
					if (debug == false) {
						debug = true;
					} else {
						debug = false;
					}
				} else {
					context.exit(0);
					return;
				}
			}
			if (debug) {
				gameModel.addPlantedebug();
				continue;

			} else if (action == Action.POINTER_DOWN) {

				if (action != Action.KEY_PRESSED) {
					location = event.getLocation();
					
					gameModel.actionFromClickSun(location);

					
					//Soleil
					
					
					

					// Verif si j cliquer dans la matrice
					if (gameModel.getDataPlante().getNbLines() > gameModel.getViewPlante().lineFromY(location.y)
							&& gameModel.getDataPlante().getNbColumns() > gameModel.getViewPlante()
									.columnFromX(location.x)) {
						// Erreur possible : clique en -1 :/
						if (gameModel.getViewPlante().lineFromY(location.y) >= 0
								&& gameModel.getViewPlante().columnFromX(location.x) >= 0) {

							gameModel.selectPlante(location);

						}

					}

					// si une plante a été selectioné et que on a clique sur la matrice
					else if ((gameModel.getData().getNbLines() > gameModel.getView().lineFromY(location.y)
							&& gameModel.getData().getNbColumns() > gameModel.getView().columnFromX(location.x)
							&& gameModel.getChoice() != null)) {
						// on peut cliquer sur du -1 :/
						if (gameModel.getView().lineFromY(location.y) >= 0
								&& gameModel.getView().columnFromX(location.x) >= 0) {
							// si la case actuelle n a pas de plante
							if (gameModel.getData().isPlante(gameModel.getView().lineFromY(location.y),
									gameModel.getView().columnFromX(location.x)) == false) {

								gameModel.addPlante(location);
							}

						}
					}
				}
			}
		}
	}

	public static void main(String[] args, int i,ArrayList<PlanteInterface> plantChoices,ArrayList<EntiteInterface> zombieChoices) {
		// pour changer de jeu, remplacer stupidGame par le nom de la mÃ©thode de jeu
		// (elle doit avoir extaement la mieme en-tÃªte).

		switch (i) {
		case 0:
			pool = false;
			day = true;
			plantChoice = plantChoices;
			zombieChoice = zombieChoices;
			Application.run(new Color(4, 127, 15), SimpleGameController::simpleGame); // attention, utilisation d'une
			break;
		case 1:
			pool = false;
			day = false;
			plantChoice = plantChoices;
			zombieChoice = zombieChoices;
			Application.run(Color.DARK_GRAY, SimpleGameController::simpleGame);
			break;
		case 2:
			pool = true;
			day = true;
			plantChoice = plantChoices;
			zombieChoice = zombieChoices;
			Application.run(new Color(4, 127, 15), SimpleGameController::simpleGame); // attention, utilisation d'une
			break;
		case 3:
			pool = true;
			day = false;
			plantChoice = plantChoices;
			zombieChoice = zombieChoices;
			Application.run(Color.DARK_GRAY, SimpleGameController::simpleGame); // attention, utilisation d'une
			break;
		}
	}
}