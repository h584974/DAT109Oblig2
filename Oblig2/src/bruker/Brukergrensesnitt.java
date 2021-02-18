package bruker;

import java.util.Scanner;
import aktorer.Kunde;
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
		
	}

}
