package dokumenter;

import java.util.Date;

public class Utleie {
	
	private int kredittkort;
	private int kilometerstand;
	private Date utleieDato;
	private Date forventetReturdato;
	private long utleieTidspunkt;
	private long forventetReturTidspunkt;
	
	public Utleie(int kredittkort, int kilometerstand, Date forventetReturdato, long forventetReturTidspunkt) {
		super();
		this.kredittkort = kredittkort;
		this.kilometerstand = kilometerstand;
		this.utleieDato = new Date();
		this.forventetReturdato = forventetReturdato;
		this.utleieTidspunkt = System.currentTimeMillis();
		this.forventetReturTidspunkt = forventetReturTidspunkt;
	}

	public int getKredittkort() {
		return kredittkort;
	}

	public int getKilometerstand() {
		return kilometerstand;
	}

	public Date getUtleieDato() {
		return utleieDato;
	}

	public Date getForventetReturdato() {
		return forventetReturdato;
	}

	public long getUtleieTidspunkt() {
		return utleieTidspunkt;
	}

	public long getForventetReturTidspunkt() {
		return forventetReturTidspunkt;
	}

}
