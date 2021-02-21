package dokumenter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import aktorer.Bil;
import aktorer.Leiekontor;
import utils.Kundeliste;
import utils.Utleiegruppe;

public class Reservasjon {
	
	private static final int returgebyr = 1000;
	
	private Bil bil;
	private LocalDate utleieDato;
	private LocalTime utleieTidspunkt;
	private int antallDager;
	private Leiekontor utleiekontor;
	private Leiekontor leveringkontor;
	private int pris;
	
	public Reservasjon(Bil bil, LocalDate utleieDato, LocalTime utleieTidspunkt, int antallDager, Leiekontor utleiekontor, Leiekontor leveringskontor) {
		super();
		this.bil = bil;
		this.utleieDato = utleieDato;
		this.utleieTidspunkt = utleieTidspunkt;
		this.antallDager = antallDager;
		this.utleiekontor = utleiekontor;
		this.leveringkontor = leveringskontor;
		this.pris = regnPris(bil.getUtleiegruppe(),utleiekontor,leveringskontor,antallDager);
	}
	
	public Bil getBil() {
		return bil;
	}

	public LocalDate getUtleieDato() {
		return utleieDato;
	}

	public LocalTime getUtleieTidspunkt() {
		return utleieTidspunkt;
	}

	public int getAntallDager() {
		return antallDager;
	}

	public Leiekontor getUtleiekontor() {
		return utleiekontor;
	}

	public Leiekontor getLeveringkontor() {
		return leveringkontor;
	}

	public int getPris() {
		return pris;
	}
	
	/**
	 * Henter alle eksisterende reservasjoner. Går gjennom liste av alle kunder og sjekker om de
	 * har en reservasjon og samler så alle reservasjoner i en liste som returneres.
	 * @return Returnerer en liste av reservasjoner.
	 */
	public static List<Reservasjon> getAlleReservasjoner() {
		
		return Kundeliste.kundeliste.stream().filter(k -> k.harReservasjon()).map(k -> k.getReservasjon()).collect(Collectors.toList());
		
	}
	
	/**
	 * Regner ut utleiepris for en gitt bilgruppe og et gitt antall dager for utleie, pluss et gebyr om bilen
	 * skal leveres på et annet leiekontor enn den befinner seg ved utleie.
	 * @param gruppe Utleiegruppe som pris beregnes for.
	 * @param utleiekontor Leiekontoret bilen skal hentes fra.
	 * @param leveringskontor Leiekontoret bilen skal leveres til.
	 * @param antallDager Antall dager bilen skal leies.
	 * @return Returnerer den beregnete prisen for utleie.
	 */
	public static int regnPris(Utleiegruppe gruppe, Leiekontor utleiekontor, Leiekontor leveringskontor, int antallDager) {
		
		int totalpris = 0;
		int dagspris = 0;
			
		switch(gruppe) {
			
			case A: dagspris = 250; break;
				
			case B: dagspris = 500; break;
				
			case C: dagspris = 750; break;
				
			case D: dagspris = 1000; break;
				
			default: dagspris = 0; break;
				
		}
		
		totalpris += dagspris * antallDager;
			
		if(utleiekontor.getKontornummer() == leveringskontor.getKontornummer()) {
			return totalpris;
		}
		else {
			return totalpris + returgebyr;
		}
			
	}
	
	/**
	 * Skriver ut beregnet pris for hver av gruppene som er gitt ved parameter.
	 * @param ledigeBiler Liste over ledige biler som kan leies.
	 * @param utleiekontor Leiekontoret bilen skal hentes fra.
	 * @param leveringskontor Leiekontoret bilen skal leveres til.
	 * @param antallDager Antall dager bilen skal leies.
	 */
	public static void skrivPris(List<Bil> ledigeBiler, Leiekontor utleiekontor, Leiekontor leveringskontor, int antallDager) {
		
		List<Utleiegruppe> ledigeGrupper = ledigeBiler.stream().map(b -> b.getUtleiegruppe()).distinct().collect(Collectors.toList());
		
		System.out.println("-- LEDIGE GRUPPER OG BEREGNET PRIS --");
		
		ledigeGrupper.forEach(g -> {
			
			int pris = Reservasjon.regnPris(g,utleiekontor,leveringskontor,antallDager);
			System.out.println("Gruppe: " + g + "\nPris: " + pris + "kr\n");
			
		});
		
	}
	
}
