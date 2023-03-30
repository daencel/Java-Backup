package spaceinvaders;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Scores {

	private String user;
	private int digit;

	public Scores(String user, int digit) {
		this.setDigit(digit);
		this.setUser(user);
	}

	public Scores(String info) {
		this.setInfo(info);
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getDigit() {
		return this.digit;
	}

	public void setDigit(int digit) {
		this.digit = digit;
	}

	public int compareTo(Scores score) {
		String a = this.getDigit() + "";
		String b = score.getDigit() + "";
		return a.compareTo(b);
	}
	
	public String toString() {
		return this.getUser() + ";" + this.getDigit();
	}

	public void setInfo(String info) {
		String[] parts = info.split(";");
		this.setUser(parts[0]);
		this.setDigit(Integer.parseInt(parts[1]));
	}
}
