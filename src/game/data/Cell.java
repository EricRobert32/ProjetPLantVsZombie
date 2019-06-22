package game.data;

import java.awt.Color;
import java.util.ArrayList;

import game.entite.EntiteInterface;
import game.entite.plante.PlanteInterface;

public class Cell {
	private Color color;
	private ArrayList<EntiteInterface> value;
	private boolean isFloor;
	private boolean isLawnmower;

	public Cell(Color color, ArrayList<EntiteInterface> value) {
		super();
		this.color = color;
		this.value = value;
		this.isFloor=true;
		this.isLawnmower = false;
	}

	public Color getColor() {
		return color;
	}

	public String getValue() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < value.size(); i++) {
			str.append(value.get(i).toString());
		}
		return str.toString();

	}
	
	
	public boolean isLawnmower() {
		return isLawnmower;
	}
	
	public boolean isFloor() {
		return isFloor;
	}

	public ArrayList<EntiteInterface> getEntite() {
		
		return value;
	}

	
	

	@Override
	public String toString() {
		return color.toString();
	}

	public void addEntite(EntiteInterface e) {
		value.add(e);
	}

	public void addPlante(PlanteInterface p) {
		// on ajoute la plante en index 0 pour ne pas a voir dans toute l arraylist
		value.add(0, p);
	}

	public boolean isPlante() {
		if (value.size()>0) {
			
		
		return value.get(0).isPlante();
		}
		return false;
	}

	public void RemoveEntite() {
		if (value.size() <= 0) {
			throw new IllegalStateException(" celulle vide");

		} else {
			value.remove(0);
		}

	}
	
	public void changefloor() {
		isFloor=false;
	}
	
	public void setLawnmower() {
		isLawnmower= !isLawnmower;
	}
	
	
}
