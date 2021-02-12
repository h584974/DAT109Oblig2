package utils;

import aktorer.Bil;
import aktorer.Leiekontor;

public class RegnUtleiePris {
	
	private static final int returgebyr = 1000;

	public static int regnPris(Bil bil, Leiekontor utleiekontor, Leiekontor leveringskontor, int antallDager) {
		
		int totalpris = 0;
		int dagspris = 0;
			
		switch(bil.getUtleiegruppe()) {
			
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
	
}