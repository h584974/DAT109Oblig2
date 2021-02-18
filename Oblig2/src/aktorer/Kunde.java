package aktorer;

import java.util.ArrayList;
import java.util.List;
import dokumenter.Reservasjon;
import dokumenter.Retur;
import dokumenter.Utleie;
import utils.Addresse;

public class Kunde {
	
	private String fornavn;
	private String etternavn;
	private int telefonnummer;
	private Addresse addresse;
	private Reservasjon reservasjon;
	private Utleie utleie;
	private List<Retur> returer;
	
	public Kunde(String fornavn, String etternavn, int telefonnummer, Addresse addresse) {
		super();
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.telefonnummer = telefonnummer;
		this.addresse = addresse;
		this.returer = new ArrayList<Retur>();
	}
	
	public String getFornavn() {
		return fornavn;
	}
	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}
	public String getEtternavn() {
		return etternavn;
	}
	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
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

	public Reservasjon getReservasjon() {
		return reservasjon;
	}

	public void setReservasjon(Reservasjon reservasjon) {
		this.reservasjon = reservasjon;
	}

	public Utleie getUtleie() {
		return utleie;
	}

	public void setUtleie(Utleie utleie) {
		this.utleie = utleie;
	}

	public List<Retur> getReturer() {
		return returer;
	}

	public void leggTilRetur(Retur retur) {
		returer.add(retur);
	}
	
	public boolean harReservasjon() {
		return reservasjon != null;
	}
	
	public boolean harUtleie() {
		return utleie != null;
	}

}
