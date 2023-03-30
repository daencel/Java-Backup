package net.tfobz.funktion;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Wurzel implements Funktion {

	@Override
	public double compute(double x) {
		return Math.sqrt(x);
	}
}
