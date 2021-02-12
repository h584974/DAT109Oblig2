package aktorer;

import utils.Utleiegruppe;

public class Bil {
	
	private int registreringsnummer;
	private int kilometerstand;
	private Utleiegruppe utleiegruppe;
	private String merke;
	private String farge;
	private boolean ledig;
	
	public Bil(int registreringsnummer, int kilometerstand, Utleiegruppe utleiegruppe, String merke, String farge) {
		super();
		this.registreringsnummer = registreringsnummer;
		this.kilometerstand = kilometerstand;
		this.utleiegruppe = utleiegruppe;
		this.merke = merke;
		this.farge = farge;
		this.ledig = true;
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

}
