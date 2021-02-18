package dokumenter;

import java.time.LocalDate;
import java.time.LocalTime;

public class Utleie {
	
	private int kredittkort;
	private int kilometerstand;
	private LocalDate utleieDato;
	private LocalDate forventetReturdato;
	private LocalTime utleieTidspunkt;
	private LocalTime forventetReturTidspunkt;
	
	public Utleie(int kredittkort, int kilometerstand, LocalDate forventetReturdato, LocalTime forventetReturTidspunkt) {
		super();
		this.kredittkort = kredittkort;
		this.kilometerstand = kilometerstand;
		this.utleieDato = LocalDate.now();
		this.forventetReturdato = forventetReturdato;
		this.utleieTidspunkt = LocalTime.now();
		this.forventetReturTidspunkt = forventetReturTidspunkt;
	}

	public int getKredittkort() {
		return kredittkort;
	}

	public int getKilometerstand() {
		return kilometerstand;
	}

	public LocalDate getUtleieDato() {
		return utleieDato;
	}

	public LocalDate getForventetReturdato() {
		return forventetReturdato;
	}

	public LocalTime getUtleieTidspunkt() {
		return utleieTidspunkt;
	}

	public LocalTime getForventetReturTidspunkt() {
		return forventetReturTidspunkt;
	}

}
