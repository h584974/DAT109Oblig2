package bruker;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import aktorer.Kunde;
import aktorer.Leiekontor;
import aktorer.Utleieselskap;
import utils.AktivUtleieselskap;

public class Brukergrensesnitt {
	
	private final Scanner scanner = new Scanner(System.in);
	private final Utleieselskap selskap = AktivUtleieselskap.selskap;
	private Kunde kunde = null;
	
	public void start() {
		
		loggInn();
		
	}
	
	private void loggInn() {
		
		String fornavn = null;
		String etternavn = null;
		System.out.println("Logg inn som kunde, skriv først fornavn: ");
		
		while(kunde == null) {
			
			while(fornavn == null) {
				
				fornavn = scanner.next();
				
				if(fornavn == null) {
					System.out.println("Prøv igjen, skriv fornavn: ");
				}
				
			}
			
			System.out.println("Skriv etternavn: ");
			
			while(etternavn == null) {
				
				etternavn = scanner.next();
				
				if(etternavn == null) {
					System.out.println("Prøv igjen, skriv etternavn: ");
				}
				
			}
			
			kunde = selskap.loggInn(fornavn, etternavn);
			
			if(kunde == null) {
				System.out.println("Kunde med dette navnet ble ikke funnet. Program avbrytes.");
				System.exit(1);
			}
			
		}
		
		System.out.println("-- Du har logget inn som '" + kunde.getFornavn() + " " + kunde.getEtternavn() + "' --\n");
		 
	}
	
	private void sokBil() {
		
		List<Leiekontor> kontorer = selskap.getLeiekontorer();
		
		// Tar inn brukerinput for utleiekontor
		System.out.println("Skriv tallet på ønsket leiekontor for henting: ");
		
		int valg = -1;
		while(valg < 0) {
			
			for(int i = 0; i < kontorer.size(); i++) {
				System.out.println((i + 1) + ": " + kontorer.get(i).getAddresse().getPoststed());
			}
			
			try {
				valg = Integer.parseInt(scanner.next()) - 1;
			}
			catch(NumberFormatException e) {}
			
			if(valg < 0 || valg >= kontorer.size()) {
				System.out.println("Ugyldig valg, prøv igjen: ");
			}
			
		}
		
		Leiekontor leiekontor = kontorer.get(valg);
		
		// Tar inn brukerinput for leveringskontor
		System.out.println("Skriv tallet på ønsket leiekontor for levering: ");
		
		valg = -1;
		while(valg < 0) {
			
			for(int i = 0; i < kontorer.size(); i++) {
				System.out.println((i + 1) + ": " + kontorer.get(i).getAddresse().getPoststed());
			}
			
			try {
				valg = Integer.parseInt(scanner.next()) - 1;
			}
			catch(NumberFormatException e) {}
			
			if(valg < 0 || valg >= kontorer.size()) {
				System.out.println("Ugyldig valg, prøv igjen: ");
			}
			
		}
		
		Leiekontor leveringskontor = kontorer.get(valg);
		
		
		
	}

}
