package aktorer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import dokumenter.Reservasjon;
import dokumenter.Retur;
import dokumenter.Utleie;
import utils.Kundeliste;
import utils.Leiekontorliste;
import utils.Addresse;

public class Utleieselskap {
	
	private String navn;
	private int telefonnummer;
	private Addresse firmaaddresse;
	private List<Kunde> kunder = Kundeliste.kundeliste;
	private List<Leiekontor> leiekontorer = Leiekontorliste.leiekontorliste;
	
	public Utleieselskap(String navn, int telefonnummer, Addresse firmaaddresse) {
		super();
		this.navn = navn;
		this.telefonnummer = telefonnummer;
		this.firmaaddresse = firmaaddresse;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public int getTelefonnummer() {
		return telefonnummer;
	}

	public void setTelefonnummer(int telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

	public Addresse getFirmaaddresse() {
		return firmaaddresse;
	}

	public void setFirmaaddresse(Addresse firmaaddresse) {
		this.firmaaddresse = firmaaddresse;
	}
	
	public List<Leiekontor> getLeiekontorer() {
		return leiekontorer;
	}

	/**
	 * Logger inn som en kunde ved bruk av fornavn og etternavn.
	 * @param fornavn Kundens fornavn
	 * @param etternavn Kundens etternavn
	 * @return Returnerer et Kunde-objekt som tilsvarer navnene, ellers null.
	 */
	public Kunde loggInn(String fornavn, String etternavn) {
		
		Kunde kunde = null;
		
		try {
			return kunder.stream().filter(k -> k.getFornavn().equalsIgnoreCase(fornavn) && k.getEtternavn().equalsIgnoreCase(etternavn)).findFirst().get();
		}
		catch(NoSuchElementException e) {}
		
		return kunde;
		
	}
	
	/**
	 * Reserverer en bil for en kunde, ved gitte detaljer.
	 * @param kunde Kunden som reserverer en bil.
	 * @param bil Bilen som kunden reserverer.
	 * @param utleiekontor Leiekontoret bilen skal hentes fra.
	 * @param leveringskontor Leiekontoret bilen skal leveres til.
	 * @param dato Datoen bilen hentes på.
	 * @param tidspunkt Tidspunktet bilen hentes på.
	 * @param antallDager Antall dager bilen skal leies.
	 * @return Returnerer true om bilen ble reservert vellykket, false ellers.
	 */
	public boolean reserverBil(Kunde kunde, Bil bil, Leiekontor utleiekontor, Leiekontor leveringskontor, LocalDate dato, LocalTime tidspunkt, int antallDager) {
		
		if(kunde.harReservasjon()) {
			return false;
		}
		
		Reservasjon reservasjon = new Reservasjon(bil, dato, tidspunkt, antallDager, utleiekontor, leveringskontor);
		kunde.setReservasjon(reservasjon);
		
		return true;
		
	}
	
	/**
	 * Utfører utleie av en bil for en kunde ved henting av reservert bil.
	 * @param kunde Kunden som henter sin reserverte bil.
	 * @param kredittkort Kredittkortnummeret til kunden oppgis og lagres.
	 * @param forventetReturdato Forventet dato for retur.
	 * @param forventetReturtidspunkt Forventet tidspunkt for retur.
	 * @return Returnerer true om bilen ble utleid vellykket, false ellers.
	 */
	public boolean hentBil(Kunde kunde, int kredittkort, LocalDate forventetReturdato, LocalTime forventetReturtidspunkt) {
		
		if(!kunde.harReservasjon()) {
			return false;
		}
		
		Reservasjon reservasjon = kunde.getReservasjon();
		Bil bil = reservasjon.getBil();
		Utleie utleie = new Utleie(kredittkort,bil.getKilometerstand(),forventetReturdato,forventetReturtidspunkt);
		kunde.setUtleie(utleie);
		reservasjon.getUtleiekontor().fjernBil(bil);
		bil.setLedig(false);
				
		return true;
		
	}
	
	/**
	 * Utfører retur av en utleid bil for en kunde.
	 * @param kunde Kunde som skal levere bil.
	 * @return Returnerer true om bilen ble returnert vellykket, false ellers.
	 */
	public boolean returnerBil(Kunde kunde) {
		
		if(!kunde.harUtleie()) {
			return false;
		}
		
		Reservasjon reservasjon = kunde.getReservasjon();
		Bil bil = reservasjon.getBil();
		Retur retur = new Retur(bil.getKilometerstand());
		kunde.leggTilRetur(retur);
		reservasjon.getLeveringkontor().leggTilBil(bil);
		bil.setLedig(true);
		kunde.setUtleie(null);
		kunde.setReservasjon(null);
		
		return true;
		
	}

}
