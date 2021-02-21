package aktorer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import dokumenter.Reservasjon;
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
	
	public boolean erReservert() {
		
		List<Reservasjon> reservasjoner = Reservasjon.getAlleReservasjoner();
		
		return reservasjoner.stream().anyMatch(r -> r.getBil().getRegistreringsnummer() == registreringsnummer);
		
	}
	
	@Override public String toString() {
		return farge + " " + merke;
	}
	
	public static List<Bil> getLedigeBiler(Leiekontor utleiekontor, Leiekontor leveringskontor, LocalDate dato, LocalTime tidspunkt, int antallDager) {
		
		List<Bil> ledigeBiler = new ArrayList<Bil>();
		List<Reservasjon> reservasjoner = Reservasjon.getAlleReservasjoner();
			
		utleiekontor.getBiler().stream().filter(b -> !b.erReservert()).forEach(b -> ledigeBiler.add(b));
		
		// Logikken er basert på antagelsen at utleietidspunkt er samme som leveringstidspunkt, ettersom vi ikke vet leveringstidspunkt
		// før bilen faktisk blir hentet og Utleie-objektet blir opprettet.
		reservasjoner.forEach(r -> {
			
			int datoforskjell = Math.abs(r.getUtleieDato().compareTo(dato));

			if(r.getUtleieDato().isBefore(dato)) {
				
				if(r.getLeveringkontor().getKontornummer() == utleiekontor.getKontornummer()) {
					
					if(datoforskjell > r.getAntallDager()) {
						ledigeBiler.add(r.getBil());
					}
					else if(datoforskjell == r.getAntallDager()) {
						
						if(r.getUtleieTidspunkt().isBefore(tidspunkt)) {
							ledigeBiler.add(r.getBil());
						}
						
					}
					
				}
				
			}
			else if(r.getUtleieDato().isAfter(dato)) {
				
				if(r.getUtleiekontor().getKontornummer() == leveringskontor.getKontornummer()) {
					
					if(datoforskjell > antallDager) {
						ledigeBiler.add(r.getBil());
					}
					else if(datoforskjell == antallDager) {
						
						if(r.getUtleieTidspunkt().isAfter(tidspunkt)) {
							ledigeBiler.add(r.getBil());
						}
						
					}
					
				}
				
			}
			
		});
		
		return ledigeBiler;
		
	}
	
}
