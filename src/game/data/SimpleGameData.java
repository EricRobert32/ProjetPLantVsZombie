package game.data;

import java.awt.Color;
import java.util.ArrayList;

import game.entite.EntiteInterface;
import game.entite.plante.PlanteInterface;

public class SimpleGameData {
	private final Cell[][] matrix;
	private Coordinates selected;

	public SimpleGameData(int nbLines, int nbColumns) {
		matrix = new Cell[nbLines][nbColumns];

	}

	/**
	 * The number of lines in the matrix contained in this GameData.
	 * 
	 * @return the number of lines in the matrix.
	 */
	public int getNbLines() {
		return matrix.length;
	}

	/**
	 * The number of columns in the matrix contained in this GameData.
	 * 
	 * @return the number of columns in the matrix.
	 */
	public int getNbColumns() {
		return matrix[0].length;
	}

	/**
	 * The color of the cell specified by its coordinates.
	 * 
	 * @param i the first coordinate of the cell.
	 * @param j the second coordinate of the cell.
	 * @return the color of the cell specified by its coordinates
	 */
	public Color getCellColor(int i, int j) {
		return matrix[i][j].getColor();
	}

	/**
	 * The value of the cell specified by its coordinates.
	 * 
	 * @param i the first coordinate of the cell.
	 * @param j the second coordinate of the cell.
	 * @return the value of the cell specified by its coordinates
	 */
	public String getCellValue(int i, int j) {
		return matrix[i][j].getValue();
	}

	/**
	 * The coordinates of the cell selected, if a cell is selected.
	 * 
	 * @return the coordinates of the selected cell; null otherwise.
	 */

	public ArrayList<EntiteInterface> getEntiteFromCell(int i, int j) {
		return matrix[i][j].getEntite();
	}
	
	public PlanteInterface getPlantFromCell(int i,int j) {
		if (matrix[i][j].getEntite().get(0).isPlante()) {
			return (PlanteInterface) matrix[i][j].getEntite().get(0);
		}
		return null;
		
		
	}

	public Coordinates getSelected() {
		return selected;
	}

	/**
	 * Tests if at least one cell is selected.
	 * 
	 * @return true if and only if at least one cell is selected; false otherwise.
	 */
	public boolean hasASelectedCell() {
		return selected != null;
	}

	/**
	 * Selects, as the first cell, the one identified by the specified coordinates.
	 * 
	 * @param i the first coordinate of the cell.
	 * @param j the second coordinate of the cell.
	 * @throws IllegalStateException if a first cell is already selected.
	 */
	public void selectCell(int i, int j) {
		if (selected != null) {
			throw new IllegalStateException("First cell already selected");
		}
		selected = new Coordinates(i, j);
	}

	/**
	 * Unselects both the first and the second cell (whether they were selected or
	 * not).
	 */
	public void unselect() {
		selected = null;
	}

	// initialise chaque cellule de la matrice
	public void setRandomMatrix() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = new Cell(Color.gray, new ArrayList<EntiteInterface>());
			}
		}
	}

	/**
	 * Updates the data contained in the GameData.
	 */

	public void spawnEntite(EntiteInterface e) {
		matrix[e.getX()][e.getY()].addEntite(e);
	}

	public void addPlante(int x, int y, PlanteInterface p) {
		matrix[x][y].addPlante(p);
	}

	public boolean isPlante(int x, int y) {
		return matrix[x][y].isPlante();
	}

	public void removeEntite(int x, int y) {
		matrix[x][y].RemoveEntite();
	}

	public void setPool() {
		for (int ligne = 2; ligne <= 3; ligne++) {
			for (int colonne = 0; colonne < getNbColumns(); colonne++) {
				matrix[ligne][colonne].changefloor();

			}

		}
	}

	public boolean isFloor(int x, int y) {
		return matrix[x][y].isFloor();
	}

	public void setLawnmower() {
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][0].setLawnmower();

		}
	}

	public boolean isLawnmover(int ligne,int colonne) {
		return matrix[ligne][colonne].isLawnmower();
	}

	public void removeLawnmover(int x) {
		matrix[x][0].setLawnmower();
	}

}
