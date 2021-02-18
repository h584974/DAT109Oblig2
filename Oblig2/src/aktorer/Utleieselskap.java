package aktorer;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import dokumenter.Reservasjon;
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
		return kunder.stream().filter(k -> k.getFornavn().equalsIgnoreCase(navn) && k.getEtternavn().equalsIgnoreCase(etternavn)).findFirst().get();
	}
	
	public List<Bil> sokBil(Leiekontor utleiekontor, Leiekontor leveringskontor, Date dato, long tidspunkt, int antallDager) {
		
		List<Bil> ledigeBiler = Bil.getLedigeBiler(utleiekontor,leveringskontor,dato,tidspunkt,antallDager);
;		List<Utleiegruppe> ledigeGrupper = ledigeBiler.stream().map(b -> b.getUtleiegruppe()).distinct().collect(Collectors.toList());
		
		System.out.println("-- LEDIGE GRUPPER OG BEREGNET PRIS --\n");
		
		ledigeGrupper.forEach(g -> {
			
			int pris = Reservasjon.regnPris(g,utleiekontor,leveringskontor,antallDager);
			System.out.println("Gruppe: " + g + "\nPris: " + pris + "kr\n");
			
		});
		
		return ledigeBiler;
		
	}
	
	public boolean reserverBil(Bil bil, Leiekontor utleiekontor, Leiekontor leveirngskontor, Date dato, long tidspunkt, int antallDager) {
		
		// TODO
		
		return false;
	}

}
