package dokumenter;

import java.util.Date;

public class Retur {
	
	private Date returdato;
	private long tidspunkt;
	private int kilometerstand;
	
	public Retur(Date returdato, int kilometerstand) {
		super();
		this.returdato = returdato;
		this.tidspunkt = System.currentTimeMillis();
		this.kilometerstand = kilometerstand;
	}

	public Date getReturdato() {
		return returdato;
	}

	public long getTidspunkt() {
		return tidspunkt;
	}

	public int getKilometerstand() {
		return kilometerstand;
	}

}
