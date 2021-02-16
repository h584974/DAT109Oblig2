package aktorer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import dokumenter.Reservasjon;
import utils.Kundeliste;
import utils.Leiekontorliste;
import utils.RegnUtleiePris;
import utils.Utleiegruppe;
import utils.Addresse;

public class Utleieselskap {
	
	private String navn;
	private int telefonnummer;
	private Addresse firmaaddresse;
	private List<Kunde> kunder = Kundeliste.kundeliste;
	private List<Leiekontor> leiekontorer = Leiekontorliste.leiekontorliste;
	private List<Bil> biler = Billiste.billiste;
	
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
	
	public List<Bil> getBiler() {
		return biler;
	}

	public Kunde loggInn(String fornavn, String etternavn) {
		return kunder.stream().filter(k -> k.getFornavn().equalsIgnoreCase(navn) && k.getEtternavn().equalsIgnoreCase(etternavn)).findFirst().get();
	}
	
	public List<Utleiegruppe> sokLedigeBilgrupperOgVis(Leiekontor utleiekontor, Leiekontor leveringskontor, Date dato, long tidspunkt, int antallDager) {
		
		List<Reservasjon> reservasjoner = getAlleReservasjoner();
		List<Bil> relevanteBiler = getBilerFraLeiekontor(utleiekontor);
		List<Utleiegruppe> ledigeGrupper = new ArrayList<Utleiegruppe>();
		
		biler.forEach(b -> {
			
			List<Bil> 
			
		});
		
		System.out.println("-- LEDIGE GRUPPER OG BEREGNET PRIS --\n");
		
		ledigeGrupper.forEach(g -> {
			int pris = RegnUtleiePris.regnPris(g,utleiekontor,leveringskontor,antallDager);
			System.out.println("Gruppe: " + g + "\nPris: " + pris + "kr\n");
		});
		
		return ledigeGrupper;
		
	}
	
	private List<Reservasjon> getAlleReservasjoner() {
		
		return kunder.stream().filter(k -> k.harReservasjon()).map(k -> k.getReservasjon()).collect(Collectors.toList());
		
	}
	
	private List<Bil> getBilerFraLeiekontor(Leiekontor leiekontor) {
		
		// rot
		
		
	}

}
