package utils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import aktorer.Bil;
import aktorer.Leiekontor;

public class Sokemotor {
	
	public static void sokOgVis(Leiekontor utleiekontor, Leiekontor leveringskontor, Date dato, long tidspunkt, int antallDager) {
		
		List<Bil> alleBiler = AktivUtleieselskap.selskap.getBiler();
		List<Utleiegruppe> ledigeGrupper = alleBiler.stream().filter(b -> b.isLedig()).map(b -> b.getUtleiegruppe()).distinct().collect(Collectors.toList());
		
		System.out.println("-- LEDIGE GRUPPER OG BEREGNET PRIS --\n");
		
		ledigeGrupper.forEach(g -> {
			int pris = RegnUtleiePris.regnPris(g,utleiekontor,leveringskontor,antallDager);
			System.out.println("Gruppe: " + g + "\nPris: " + pris + "kr\n");
		});
		
	}
	
}
