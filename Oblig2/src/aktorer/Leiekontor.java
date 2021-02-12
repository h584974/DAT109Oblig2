package aktorer;

import utils.Addresse;

public class Leiekontor {
	
	private int kontornummer;
	private int telefonnummer;
	private Addresse addresse;
	
	public Leiekontor(int kontornummer, int telefonnummer, Addresse addresse) {
		super();
		this.kontornummer = kontornummer;
		this.telefonnummer = telefonnummer;
		this.addresse = addresse;
	}

	public int getKontornummer() {
		return kontornummer;
	}

	public void setKontornummer(int kontornummer) {
		this.kontornummer = kontornummer;
	}

	public int getTelefonnummer() {
		return telefonnummer;
	}

	public void setTelefonnummer(int telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

	public Addresse getAddresse() {
		return addresse;
	}

	public void setAddresse(Addresse addresse) {
		this.addresse = addresse;
	}

}
