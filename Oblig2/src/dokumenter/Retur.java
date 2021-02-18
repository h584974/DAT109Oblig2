package dokumenter;

import java.time.LocalDate;
import java.time.LocalTime;

public class Retur {
	
	private LocalDate returdato;
	private LocalTime tidspunkt;
	private int kilometerstand;
	
	public Retur(int kilometerstand) {
		super();
		this.returdato = LocalDate.now();
		this.tidspunkt = LocalTime.now();
		this.kilometerstand = kilometerstand;
	}

	public LocalDate getReturdato() {
		return returdato;
	}

	public LocalTime getTidspunkt() {
		return tidspunkt;
	}

	public int getKilometerstand() {
		return kilometerstand;
	}

}
