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

	public Kunde loggInn(String fornavn, String etternavn) {
		
		Kunde kunde = null;
		
		try {
			return kunder.stream().filter(k -> k.getFornavn().equalsIgnoreCase(fornavn) && k.getEtternavn().equalsIgnoreCase(etternavn)).findFirst().get();
		}
		catch(NoSuchElementException e) {}
		
		return kunde;
		
	}
	
	public boolean reserverBil(Kunde kunde, Bil bil, Leiekontor utleiekontor, Leiekontor leveringskontor, LocalDate dato, LocalTime tidspunkt, int antallDager) {
		
		if(kunde.harReservasjon()) {
			return false;
		}
		
		Reservasjon reservasjon = new Reservasjon(bil, dato, tidspunkt, antallDager, utleiekontor, leveringskontor);
		kunde.setReservasjon(reservasjon);
		
		return true;
		
	}
	
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
