package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import aktorer.*;

public class BilTestSetup {
	
	private Kunde ledigKunde;
	private Kunde reservertKunde;
	private Kunde levertKunde;
	private Bil ledigBil;
	private Bil reservertBil;
	private Bil levertBil;
	
	public BilTestSetup() {
		
		reservertKunde = Kundeliste.kundeliste.get(0);
		levertKunde = Kundeliste.kundeliste.get(1);
		ledigKunde = Kundeliste.kundeliste.get(2);
		
		ledigBil = AktivUtleieselskap.selskap.getLeiekontorer().get(0).getBiler().get(0);
		
		int antallDager = 7;
		
		Leiekontor kontorReservertBil = Leiekontorliste.leiekontorliste.get(1);
		reservertBil = kontorReservertBil.getBiler().get(0);
		LocalDate utleiedatoReservertBil = LocalDate.of(2021, 1, 1);
		LocalTime tidspunktReservertBil = LocalTime.of(12, 0);
		AktivUtleieselskap.selskap.reserverBil(reservertKunde, reservertBil, kontorReservertBil, kontorReservertBil, utleiedatoReservertBil, tidspunktReservertBil, antallDager);
		
		Leiekontor kontorLevertBil = Leiekontorliste.leiekontorliste.get(2);
	    levertBil = kontorLevertBil.getBiler().get(0);
		LocalDate utleiedatoLevertBil = LocalDate.of(2021, 1, 1);
		LocalTime tidspunktLevertBil = LocalTime.of(12, 0);
		AktivUtleieselskap.selskap.reserverBil(levertKunde, levertBil, kontorLevertBil, kontorLevertBil, utleiedatoLevertBil, tidspunktLevertBil, antallDager);
		AktivUtleieselskap.selskap.hentBil(levertKunde, 1000, utleiedatoLevertBil.plusDays(antallDager), tidspunktLevertBil);
		AktivUtleieselskap.selskap.reserverBil(levertKunde, levertBil, kontorLevertBil, kontorLevertBil, utleiedatoLevertBil, tidspunktLevertBil, antallDager);
		
	}

	public Kunde getReservertKunde() {
		return reservertKunde;
	}

	public Kunde getLevertKunde() {
		return levertKunde;
	}

	public Bil getLedigBil() {
		return ledigBil;
	}

	public Bil getReservertBil() {
		return reservertBil;
	}

	public Bil getLevertBil() {
		return levertBil;
	}
	
	public Kunde getLedigKunde() {
		return ledigKunde;
	}

}
