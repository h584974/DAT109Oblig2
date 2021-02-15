package utils;

import java.util.Date;

import aktorer.Leiekontor;
import aktorer.Utleieselskap;

public class Test {
	
	public static void main(String...strings) {
		
		Utleieselskap selskap = AktivUtleieselskap.selskap;
		Leiekontor utleiekontor = selskap.getLeiekontorer().get(0);
		Leiekontor leveringskontor = selskap.getLeiekontorer().get(1);
		Date dato = new Date();
		long tidspunkt = 1000000L;
		int antallDager = 7;
		
		Sokemotor.sokOgVis(utleiekontor, leveringskontor, dato, tidspunkt, antallDager);
		
	}
}
