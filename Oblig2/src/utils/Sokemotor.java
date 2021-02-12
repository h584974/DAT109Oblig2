package utils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import aktorer.Bil;
import aktorer.Leiekontor;

public class Sokemotor {
	
	public static void sokOgVis(Leiekontor utleiekontor, Leiekontor leveringskontor, Date dato, long tidspunkt, int antallDager) {
		
		List<Bil> alleBiler = AktivUtleieselskap.selskap.getBiler();
		Utleiegruppe[] ledigeGrupper = (Utleiegruppe[]) alleBiler.stream().filter(b -> b.isLedig()).map(b -> b.getUtleiegruppe()).distinct().collect(Collectors.toList()).toArray();
		
		System.out.println("-- LEDIGE GRUPPER OG BEREGNET PRIS --");
		for(int i = 0; i < ledigeGrupper.length; i++) {
			int pris = RegnUtleiePris.regnPris(ledigeGrupper[i],utleiekontor,leveringskontor,antallDager);
			System.out.println("Gruppe: " + ledigeGrupper[i] + "\nPris: " + pris + "kr\n");
		}
		
	}

}
