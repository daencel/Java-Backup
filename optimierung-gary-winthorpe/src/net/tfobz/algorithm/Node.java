package net.tfobz.algorithm;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * 
 * @author Daniel Lechner, Noah Abeerham
 * 
 *         Diese Klasse implementiert die Nodes
 */
public class Node implements Comparable<Node> {

	// Parent Node
	private Node parent;
	// Koordinaten
	private int x, y;
	// Wert zurueckgelegter Weg und noch zurueckzulegender Weg
	private double g, h;

	/**
	 * Konstruktor fuer eine Node
	 * 
	 * @param parent Parent Node
	 * @param x      X Koordinate
	 * @param y      Y Koordinate
	 * @param g      G Wert (Zurueckgelegter Weg)
	 * @param h      H Wert (noch zurueckzulegender Weg)
	 */
	public Node(Node parent, int x, int y, double g, double h) {
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.g = g;
		this.h = h;
	}

	@Override
	public int compareTo(Node n) {
		return Double.compare(this.getF(), n.getF());
	}

	/**
	 * Gibt den F Wert (G + H) zurueck
	 * 
	 * @return F Wert
	 */
	public double getF() {
		return (this.h + this.g);
	}

	/**
	 * Zeichnet den kuerzesten Weg zu Ziel Node wenn dieser gefunden wurde
	 * 
	 * @param g  Graphics
	 * @param sc Scale
	 */
	public void printPath(Graphics g, int sc) {
		ArrayList<Node> path = this.getPath();
		for (Node node : path) {
			g.setColor(Color.BLUE);
			g.fillRect(node.getX() * sc, node.getY() * sc, sc, sc);
		}
	}

	/**
	 * Gibt den kuerzesten Weg zum Ziel zurueck
	 * 
	 * @return kuerzester Weg
	 */
	public ArrayList<Node> getPath() {
		ArrayList<Node> ret = new ArrayList<Node>();
		if (this.getParent() != null) {
			Node p = this.getParent();
			while (p.getParent() != p) {
				ret.add(p);
				p = p.getParent();
			}
		}
		return ret;
	}

	/**
	 * @return the parent
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the g
	 */
	public double getG() {
		return g;
	}

	/**
	 * @param g the g to set
	 */
	public void setG(double g) {
		this.g = g;
	}

	/**
	 * @return the h
	 */
	public double getH() {
		return h;
	}

	/**
	 * @param h the h to set
	 */
	public void setH(double h) {
		this.h = h;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
}
