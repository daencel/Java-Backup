package net.tfobz.funktion;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Quadrat implements Funktion {

	@Override
	public double compute(double x) {
		return x * x;
	}
}
