package aktorer;

import java.util.List;

import dokumenter.Reservasjon;
import utils.AktivUtleieselskap;
import utils.Utleiegruppe;

public class Bil {
	
	private int registreringsnummer;
	private int kilometerstand;
	private Utleiegruppe utleiegruppe;
	private String merke;
	private String farge;
	private boolean ledig;
	private Leiekontor utleiekontor;
	
	public Bil(int registreringsnummer, int kilometerstand, Utleiegruppe utleiegruppe, String merke, String farge, Leiekontor utleiekontor) {
		super();
		this.registreringsnummer = registreringsnummer;
		this.kilometerstand = kilometerstand;
		this.utleiegruppe = utleiegruppe;
		this.merke = merke;
		this.farge = farge;
		this.ledig = true;
		this.utleiekontor = utleiekontor;
	}

	public int getRegistreringsnummer() {
		return registreringsnummer;
	}

	public void setRegistreringsnummer(int registreringsnummer) {
		this.registreringsnummer = registreringsnummer;
	}

	public int getKilometerstand() {
		return kilometerstand;
	}

	public void setKilometerstand(int kilometerstand) {
		this.kilometerstand = kilometerstand;
	}

	public Utleiegruppe getUtleiegruppe() {
		return utleiegruppe;
	}

	public void setUtleiegruppe(Utleiegruppe utleiegruppe) {
		this.utleiegruppe = utleiegruppe;
	}

	public String getMerke() {
		return merke;
	}

	public void setMerke(String merke) {
		this.merke = merke;
	}

	public String getFarge() {
		return farge;
	}

	public void setFarge(String farge) {
		this.farge = farge;
	}

	public boolean isLedig() {
		return ledig;
	}

	public void setLedig(boolean ledig) {
		this.ledig = ledig;
	}

	public Leiekontor getUtleiekontor() {
		return utleiekontor;
	}

	public void setUtleiekontor(Leiekontor utleiekontor) {
		this.utleiekontor = utleiekontor;
	}
	
	public boolean erReservert() {
		
		List<Reservasjon> reservasjoner = Reservasjon.getAlleReservasjoner();
		
		return reservasjoner.stream().anyMatch(r -> r.getBil().getRegistreringsnummer() == registreringsnummer);
		
	}
	
}
