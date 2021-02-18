package aktorer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import dokumenter.Reservasjon;
import dokumenter.Retur;
import dokumenter.Utleie;
import utils.Kundeliste;
import utils.Leiekontorliste;
import utils.Utleiegruppe;
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
	
	public List<Bil> sokBil(Leiekontor utleiekontor, Leiekontor leveringskontor, LocalDate dato, LocalTime tidspunkt, int antallDager) {
		
		List<Bil> ledigeBiler = Bil.getLedigeBiler(utleiekontor,leveringskontor,dato,tidspunkt,antallDager);
;		List<Utleiegruppe> ledigeGrupper = ledigeBiler.stream().map(b -> b.getUtleiegruppe()).distinct().collect(Collectors.toList());
		
		System.out.println("-- LEDIGE GRUPPER OG BEREGNET PRIS --\n");
		
		ledigeGrupper.forEach(g -> {
			
			int pris = Reservasjon.regnPris(g,utleiekontor,leveringskontor,antallDager);
			System.out.println("Gruppe: " + g + "\nPris: " + pris + "kr\n");
			
		});
		
		return ledigeBiler;
		
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
		Utleie utleie = new Utleie(kredittkort, reservasjon.getBil().getKilometerstand(),forventetReturdato,forventetReturtidspunkt);
		kunde.setUtleie(utleie);
		reservasjon.getUtleiekontor().fjernBil(reservasjon.getBil());
				
		return true;
		
	}
	
	public boolean returnerBil(Kunde kunde) {
		
		if(!kunde.harUtleie()) {
			return false;
		}
		
		Reservasjon reservasjon = kunde.getReservasjon();
		Retur retur = new Retur(reservasjon.getBil().getKilometerstand());
		kunde.leggTilRetur(retur);
		reservasjon.getLeveringkontor().leggTilBil(reservasjon.getBil());
		
		return true;
		
	}

}
