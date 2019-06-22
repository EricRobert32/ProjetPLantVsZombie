package menu;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.ScreenInfo;
import game.controlleur.SimpleGameController;
import game.data.SimpleGameData;
import game.entite.EntiteInterface;
import game.entite.plante.CherryBomb;
import game.entite.plante.Peashooter;
import game.entite.plante.PlanteInterface;
import game.entite.plante.PuffShroom;
import game.entite.plante.Repeater;
import game.entite.plante.Seashroom;
import game.entite.plante.WallNut;
import game.entite.zombie.BucketZombie;
import game.entite.zombie.ConeHeadZombie;
import game.entite.zombie.DuckyTubeZombie;
import game.entite.zombie.FlagZombie;
import game.entite.zombie.Zombie;
import game.entite.zombie.ZombieQuarterBack;
import game.view.HorizontalMovingElement;
import game.view.SimpleGameView;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.Event.Action;

public class MenuSelection {
	static Point2D.Float location = null;

	static void menuSelection(ApplicationContext context) {

		ScreenInfo screenInfo = context.getScreenInfo();
		float width = screenInfo.getWidth();
		float height = screenInfo.getHeight();

		// SELECT LEVEL
		SimpleGameData data = new SimpleGameData(3, 2);
		data.setRandomMatrix();
		SimpleGameView view = SimpleGameView.initGameGraphics((int) width / 10, (int) height / 10, width / 2, data);
		view.draw2(context, data, Color.blue, Color.DARK_GRAY);

		view.drawString(context, (int) view.xFromI(0) + view.getSquareSize() / 4,
				(int) view.yFromJ(0) + view.getSquareSize() / 2, "GARDEN DAY");
		view.drawString(context, (int) view.xFromI(1) + view.getSquareSize() / 4,
				(int) view.yFromJ(0) + view.getSquareSize() / 2, "GARDEN NIGHT");
		view.drawString(context, (int) view.xFromI(0) + view.getSquareSize() / 4,
				(int) view.yFromJ(1) + view.getSquareSize() / 2, "POOL DAY");
		view.drawString(context, (int) view.xFromI(1) + view.getSquareSize() / 4,
				(int) view.yFromJ(1) + view.getSquareSize() / 2, "POOL NIGHT");
		view.drawString(context, (int) view.xFromI(0) + view.getSquareSize() / 4,
				(int) view.yFromJ(2) + view.getSquareSize() / 2, "ROOFTOP");
		view.drawString(context, (int) view.xFromI(1), (int) view.yFromJ(2) + view.getSquareSize() / 2,
				"1. Choisir 3 plantes et 3 zombies");
		view.drawString(context, (int) view.xFromI(1), (int) (view.yFromJ(2) + view.getSquareSize() / 1.5),
				"2. Choisir un niveau");

		// SELECT PLANT
		SimpleGameData dataPlante = new SimpleGameData(2, 5);
		dataPlante.setRandomMatrix();
		SimpleGameView viewPlante = SimpleGameView.initGameGraphics((int) (width - width / 2), (int) height / 10,
				width / 5, dataPlante);
		viewPlante.draw2(context, dataPlante, Color.blue, Color.DARK_GRAY);

		Peashooter p1 = Peashooter.SpawnPeashooter(0, 0);
		dataPlante.spawnEntite(p1);
		HorizontalMovingElement pl1 = new HorizontalMovingElement((int) viewPlante.xFromI(p1.getY()),
				(int) viewPlante.yFromJ(p1.getX()), p1.getSpeed(), p1.getColor(), p1.getSize(), p1);

		WallNut w1 = WallNut.SpawnWallNut(0, 1);
		dataPlante.spawnEntite(w1);
		HorizontalMovingElement wl1 = new HorizontalMovingElement((int) viewPlante.xFromI(w1.getY()),
				(int) viewPlante.yFromJ(w1.getX()), w1.getSpeed(), w1.getColor(), w1.getSize(), w1);

		CherryBomb c1 = CherryBomb.SpawnCherryBomb(0, 2);
		dataPlante.spawnEntite(c1);
		HorizontalMovingElement cl1 = new HorizontalMovingElement((int) viewPlante.xFromI(c1.getY()),
				(int) viewPlante.yFromJ(c1.getX()), c1.getSpeed(), c1.getColor(), c1.getSize(), c1);

		Repeater rp1 = Repeater.SpawnRepeater(0, 3);
		dataPlante.spawnEntite(rp1);
		HorizontalMovingElement rpl1 = new HorizontalMovingElement((int) viewPlante.xFromI(rp1.getY()),
				(int) viewPlante.yFromJ(rp1.getX()), rp1.getSpeed(), rp1.getColor(), rp1.getSize(), rp1);
		Seashroom sh1 = Seashroom.SpawnPeashooter(0, 4);
		dataPlante.spawnEntite(sh1);
		HorizontalMovingElement shl1 = new HorizontalMovingElement((int) viewPlante.xFromI(sh1.getY()),
				(int) viewPlante.yFromJ(sh1.getX()), sh1.getSpeed(), sh1.getColor(), sh1.getSize(), sh1);
		
		//PuffShroom pshr = PuffShroom.SpawnPuffShroom(1, 0);
//		dataPlante.spawnEntite(pshr);
//		HorizontalMovingElement pshr1 = new HorizontalMovingElement((int) viewPlante.xFromI(pshr.getY()),
//				(int) viewPlante.yFromJ(pshr.getX()), pshr.getSpeed(), pshr.getColor(), pshr.getSize(), pshr);

		ArrayList<HorizontalMovingElement> plant2draw = new ArrayList<HorizontalMovingElement>();
		plant2draw.add(rpl1);
		plant2draw.add(cl1);
		plant2draw.add(wl1);
		plant2draw.add(pl1);
		plant2draw.add(shl1);
//		plant2draw.add(pshr1);

		viewPlante.drawAllRoundElement(context, dataPlante, plant2draw);

		viewPlante.drawString(context, (int) viewPlante.xFromI(0) + viewPlante.getSquareSize() / 4,
				(int) (viewPlante.yFromJ(0) + viewPlante.getSquareSize() / 3) + viewPlante.getSquareSize() / 2,
				"PeaShooter");

		viewPlante.drawString(context, (int) viewPlante.xFromI(1) + viewPlante.getSquareSize() / 4,
				(int) (viewPlante.yFromJ(0) + viewPlante.getSquareSize() / 3) + viewPlante.getSquareSize() / 2,
				"WallNut");
		viewPlante.drawString(context, (int) viewPlante.xFromI(2) + viewPlante.getSquareSize() / 4,
				(int) (viewPlante.yFromJ(0) + viewPlante.getSquareSize() / 3) + viewPlante.getSquareSize() / 2,
				"CherryBomb");
		viewPlante.drawString(context, (int) viewPlante.xFromI(3) + viewPlante.getSquareSize() / 4,
				(int) (viewPlante.yFromJ(0) + viewPlante.getSquareSize() / 3) + viewPlante.getSquareSize() / 2,
				"Repeater");
		viewPlante.drawString(context, (int) viewPlante.xFromI(4) + viewPlante.getSquareSize() / 4,
				(int) (viewPlante.yFromJ(0) + viewPlante.getSquareSize() / 3) + viewPlante.getSquareSize() / 2,
				"SeaShroom");
//		viewPlante.drawString(context, (int) viewPlante.xFromI(0) + viewPlante.getSquareSize() / 4,
//				(int) (viewPlante.yFromJ(1) + viewPlante.getSquareSize() / 3) + viewPlante.getSquareSize() / 2,
//				"PuffShroom");

		ArrayList<PlanteInterface> plantChoice = new ArrayList<PlanteInterface>();

		// Zombie Select

		SimpleGameData dataZombie = new SimpleGameData(2, 4);
		dataZombie.setRandomMatrix();
		SimpleGameView viewZombie = SimpleGameView.initGameGraphics((int) (width - width / 2), (int) height / 2,
				width / 5, dataZombie);
		viewZombie.draw2(context, dataZombie, Color.blue, Color.DARK_GRAY);

		Zombie z = Zombie.spawnZombie(0, 0);
		ConeHeadZombie chz = ConeHeadZombie.spawnConeHeadZombie(0, 1);
		BucketZombie bkz = BucketZombie.spawnBucketZombie(0, 2);
		ZombieQuarterBack qbz = ZombieQuarterBack.spawnQuarterBackZombie(0, 3);
		DuckyTubeZombie dtz = DuckyTubeZombie.spawnZombie(1, 0);
		dataZombie.spawnEntite(z);
		dataZombie.spawnEntite(chz);
		dataZombie.spawnEntite(bkz);
		dataZombie.spawnEntite(qbz);
		dataZombie.spawnEntite(dtz);

		HorizontalMovingElement z1 = new HorizontalMovingElement((int) viewZombie.xFromI(z.getY()),
				(int) viewZombie.yFromJ(z.getX()), z.getSpeed(), z.getColor(), z.getSize(), z);

		HorizontalMovingElement chz1 = new HorizontalMovingElement((int) viewZombie.xFromI(chz.getY()),
				(int) viewZombie.yFromJ(chz.getX()), chz.getSpeed(), chz.getColor(), chz.getSize(), chz);
		HorizontalMovingElement bkz1 = new HorizontalMovingElement((int) viewZombie.xFromI(bkz.getY()),
				(int) viewZombie.yFromJ(bkz.getX()), bkz.getSpeed(), bkz.getColor(), bkz.getSize(), bkz);
		HorizontalMovingElement qbz1 = new HorizontalMovingElement((int) viewZombie.xFromI(qbz.getY()),
				(int) viewZombie.yFromJ(qbz.getX()), qbz.getSpeed(), qbz.getColor(), qbz.getSize(), qbz);
		HorizontalMovingElement dtz1 = new HorizontalMovingElement((int) viewZombie.xFromI(dtz.getY()),
				(int) viewZombie.yFromJ(dtz.getX()), dtz.getSpeed(), dtz.getColor(), dtz.getSize(), dtz);

		ArrayList<HorizontalMovingElement> zombie2draw = new ArrayList<HorizontalMovingElement>();
		zombie2draw.add(z1);
		zombie2draw.add(chz1);
		zombie2draw.add(bkz1);
		zombie2draw.add(qbz1);
		zombie2draw.add(dtz1);

		viewZombie.drawAllRectangleElement(context, dataZombie, zombie2draw);

		viewZombie.drawString(context, (int) viewZombie.xFromI(0) + viewZombie.getSquareSize() / 4,
				(int) (viewZombie.yFromJ(0) + viewZombie.getSquareSize() / 3) + viewZombie.getSquareSize() / 2,
				"Zombie");
		viewZombie.drawString(context, (int) viewZombie.xFromI(1) + viewZombie.getSquareSize() / 4,
				(int) (viewZombie.yFromJ(0) + viewZombie.getSquareSize() / 3) + viewZombie.getSquareSize() / 2,
				"ConeHead");
		viewZombie.drawString(context, (int) viewZombie.xFromI(2) + viewZombie.getSquareSize() / 4,
				(int) (viewZombie.yFromJ(0) + viewZombie.getSquareSize() / 3) + viewZombie.getSquareSize() / 2,
				"Bucket");
		viewZombie.drawString(context, (int) viewZombie.xFromI(3) + viewZombie.getSquareSize() / 4,
				(int) (viewZombie.yFromJ(0) + viewZombie.getSquareSize() / 3) + viewZombie.getSquareSize() / 2,
				"QuaterBack");
		viewZombie.drawString(context, (int) viewZombie.xFromI(0) + viewZombie.getSquareSize() / 4,
				(int) (viewZombie.yFromJ(1) + viewZombie.getSquareSize() / 3) + viewZombie.getSquareSize() / 2,
				"DuckyTu...");

		ArrayList<EntiteInterface> zombieChoice = new ArrayList<EntiteInterface>();

		while (true) {
			Event event = context.pollOrWaitEvent(50);

			if (event == null) {
				continue;
			}

			Action action = event.getAction();
			if (action == Action.KEY_PRESSED) {
				context.exit(0);
			}

			if (action == Action.POINTER_DOWN) {
				location = event.getLocation();
				
				System.out.println(viewPlante.lineFromY(location.y) + "   "+viewPlante.columnFromX(location.x ));


				if (dataPlante.getNbLines() > viewPlante.lineFromY(location.y)
						&& dataPlante.getNbColumns() > viewPlante.columnFromX(location.x)
						&& viewPlante.lineFromY(location.y) >= 0 && viewPlante.columnFromX(location.x) >= 0
						&& plantChoice.size() < 3) {

					switch (viewPlante.columnFromX(location.x)) {

					case 0:
						if (plantChoice.contains(p1) == false) {
							System.out.println("Ajout de Peashooter");
							plantChoice.add(p1);
						}
						break;

					case 1:
						if (plantChoice.contains(w1) == false) {
							System.out.println("Ajout de WallNut");
							plantChoice.add(w1);
						}
						break;
					case 2:
						if (plantChoice.contains(c1) == false) {
							System.out.println("Ajout de CherryBomb");
							plantChoice.add(c1);
						}
						break;
					case 3:
						if (plantChoice.contains(rp1) == false) {
							System.out.println("Ajout de Repeater");
							plantChoice.add(rp1);
						}
						break;

					case 4:
						if (plantChoice.contains(sh1) == false) {

							System.out.println("Ajout de SeaShroom");
							plantChoice.add(sh1);
						}
					}

				}


				if (dataZombie.getNbLines() > viewZombie.lineFromY(location.y)
						&& dataZombie.getNbColumns() > viewZombie.columnFromX(location.x)
						&& viewZombie.lineFromY(location.y) >= 0 && viewZombie.columnFromX(location.x) >= 0
						&& zombieChoice.size() < 3) {
					
					switch (viewPlante.columnFromX(location.x)) {

					case 0:
						switch (viewPlante.columnFromX(location.y)) {

						case -1:
							if (zombieChoice.contains(z) == false) {
								System.out.println("Ajout du zombie ");
								zombieChoice.add(z);
							}
							break;

						case 0:
							if(zombieChoice.contains(dtz) == false){
								System.out.println("Ajout du DuckyTubeZombie");
								zombieChoice.add(dtz);
							}
						}
						break;

					case 1:
						if (zombieChoice.contains(chz) == false) {
							System.out.println("Ajout du zombiePlot ");
							zombieChoice.add(chz);
						}
						break;
					case 2:
						if (zombieChoice.contains(bkz) == false) {
							System.out.println("Ajout du zombieSeau ");
							zombieChoice.add(bkz);
						}
						break;
					case 3:
						if (zombieChoice.contains(qbz) == false) {
							System.out.println("Ajout du zombie QUATERBACK ");
							zombieChoice.add(qbz);
						}
						break;

					}

				}

				if (data.getNbLines() > view.lineFromY(location.y) && data.getNbColumns() > view.columnFromX(location.x)
						&& view.lineFromY(location.y) >= 0 && view.columnFromX(location.x) >= 0
						&& plantChoice.size() == 3 && zombieChoice.size() == 3) {
					// switch en fonction de la map
					switch (view.lineFromY(location.y)) {
					case 0:
						switch (view.columnFromX(location.x)) {
						case 0:
							SimpleGameController.main(null, 0, plantChoice, zombieChoice);
							break;
						case 1:
							SimpleGameController.main(null, 1, plantChoice, zombieChoice);
						}
					case 1:
						switch (view.columnFromX(location.x)) {
						case 0:
							SimpleGameController.main(null, 2, plantChoice, zombieChoice);
							break;
						case 1:
							SimpleGameController.main(null, 3, plantChoice, zombieChoice);
						}

					}

				}
			}
		}

	}

	public static void main(String[] args) {
		Application.run(Color.black, MenuSelection::menuSelection);
	}

}
