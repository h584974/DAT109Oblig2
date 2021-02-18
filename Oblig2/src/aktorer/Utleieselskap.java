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
		
		List<Reservasjon> reservasjoner = getAlleReservasjoner();
		List<Bil> ledigeBiler = getLedigeBiler(utleiekontor,leveringskontor,dato,tidspunkt,antallDager);
;		List<Utleiegruppe> ledigeGrupper = ledigeBiler.stream().map(b -> b.getUtleiegruppe()).distinct().collect(Collectors.toList());
		
		System.out.println("-- LEDIGE GRUPPER OG BEREGNET PRIS --\n");
		
		ledigeGrupper.forEach(g -> {
			int pris = RegnUtleiePris.regnPris(g,utleiekontor,leveringskontor,antallDager);
			System.out.println("Gruppe: " + g + "\nPris: " + pris + "kr\n");
		});
		
		return ledigeBiler;
		
	}
	
	public List<Reservasjon> getAlleReservasjoner() {
		
		return kunder.stream().filter(k -> k.harReservasjon()).map(k -> k.getReservasjon()).collect(Collectors.toList());
		
	}
	
	private List<Bil> getLedigeBiler(Leiekontor utleiekontor, Leiekontor leveringskontor, Date dato, long tidpunkt, int antallDager) {
		
		List<Bil> biler = Billiste.billiste;
		List<Bil> ledigeBiler = new ArrayList<Bil>();
		List<Reservasjon> reservasjoner = getAlleReservasjoner();
		
		reservasjoner.forEach(r -> {
			
			int datoforskjell = r.getUtleieDato().compareTo(dato);

			if(r.getUtleieDato().before(dato)) {
				
				if(datoforskjell > r.getAntallDager()) {
					
					if(r.getLeveringkontor().getKontornummer() == utleiekontor.getKontornummer()) {
						ledigeBiler.add(r.getBil());
					}
					
				}
				
			}
			else if(r.getUtleieDato().after(dato)) {
				
				if(datoforskjell > antallDager) {
					
					if(r.getUtleiekontor().getKontornummer() == leveringskontor.getKontornummer()) {
						ledigeBiler.add(r.getBil());
					}
					
				}
				
			}
			
		});
		
		biler.stream().filter(b -> !b.erReservert()).forEach(b -> ledigeBiler.add(b));
		
		return ledigeBiler;
		
	}

}







