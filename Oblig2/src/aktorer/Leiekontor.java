package aktorer;

import java.util.ArrayList;
import java.util.List;
import utils.Addresse;

public class Leiekontor {
	
	private int kontornummer;
	private int telefonnummer;
	private Addresse addresse;
	private List<Bil> biler;
	
	public Leiekontor(int kontornummer, int telefonnummer, Addresse addresse) {
		super();
		this.kontornummer = kontornummer;
		this.telefonnummer = telefonnummer;
		this.addresse = addresse;
		this.biler = new ArrayList<Bil>();
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
	
	public List<Bil> getBiler() {
		return biler;
	}
	
	/**
	 * Legger til en bil i dette leiekontoret.
	 * @param bil Bil som skal legges til.
	 */
	public void leggTilBil(Bil bil) {
		biler.add(bil);
	}
	
	/**
	 * Fjerner en bil fra dette leiekontoret.
	 * @param bil Bil som skal fjernes.
	 */
	public void fjernBil(Bil bil) {
		
		int bilNr = bil.getRegistreringsnummer();
		
		for(int i = 0; i < biler.size(); i++) {
			
			if(biler.get(i).getKilometerstand() == bilNr) {
				biler.remove(i);
				break;
			}
			
		}
		
	}

}
