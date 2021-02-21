package bruker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import aktorer.Bil;
import aktorer.Kunde;
import aktorer.Leiekontor;
import aktorer.Utleieselskap;
import dokumenter.Reservasjon;
import utils.AktivUtleieselskap;
import utils.Utleiegruppe;

public class Brukergrensesnitt {
	
	private final Scanner scanner = new Scanner(System.in);
	private final Utleieselskap selskap = AktivUtleieselskap.selskap;
	private Kunde kunde = null;
	
	public void start() {
		
		loggInn();
		
		reserverBil();
		
		hentBil();
		
		kjorBil();

		returnerBil();
		
		scanner.close();
		
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
	
	private void reserverBil() {
		
		List<Leiekontor> kontorer = selskap.getLeiekontorer();
		
		System.out.println("Skriv tallet på ønsket leiekontor for henting: ");
		Leiekontor utleiekontor = null;
		int valg = -1;
		while(utleiekontor == null) {
			
			for(int i = 0; i < kontorer.size(); i++) {
				System.out.println((i + 1) + ": " + kontorer.get(i).getAddresse());
			}
			
			try {
				valg = Integer.parseInt(scanner.next()) - 1;
			}
			catch(NumberFormatException e) {}
			
			if(valg < 0 || valg >= kontorer.size()) {
				System.out.println("Ugyldig valg, prøv igjen: ");
			}
			else {
				utleiekontor = kontorer.get(valg);
			}
			
		}
		
		System.out.println("Skriv tallet på ønsket leiekontor for levering: ");
		Leiekontor leveringskontor = null;
		valg = -1;
		while(leveringskontor == null) {
			
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
			else {
				leveringskontor = kontorer.get(valg);
			}
			
		}
		
		System.out.println("Skriv dato for utleie");
		LocalDate utleiedato = null;
		while(utleiedato == null) {
			
			try {
				System.out.println("År: ");
				int aar = Integer.parseInt(scanner.next());
				System.out.println("Månde: ");
				int maande = Integer.parseInt(scanner.next());
				System.out.println("Dag: ");
				int dag = Integer.parseInt(scanner.next());
				
				utleiedato = LocalDate.of(aar, maande, dag);
			}
			catch(Throwable e) {}
			
		}
		
		System.out.println("Velg klokkeslett for henting");
		LocalTime tidspunkt = null;
		while(tidspunkt == null) {
			
			try {
				System.out.println("Time: ");
				int time = Integer.parseInt(scanner.next());
				System.out.println("Minutt: ");
				int minutt = Integer.parseInt(scanner.next());
				
				tidspunkt = LocalTime.of(time, minutt);
			}
			catch(Throwable e) {}
			
		}
		
		System.out.println("Skriv antall dager for leie: ");
		int antallDager = 0;
		while(antallDager < 1) {
			
			try {
				antallDager = Integer.parseInt(scanner.next());
			}
			catch(NumberFormatException e) {}
			
			if(antallDager < 1) {
				System.out.println("Ugyldig valg av antall dager, prøv igjen: ");
			}
			
		}
		
		List<Bil> biler = Bil.getLedigeBiler(utleiekontor, leveringskontor, utleiedato, tidspunkt, antallDager);
		Reservasjon.skrivPris(biler, utleiekontor, leveringskontor, antallDager);
		Bil bil = velgBil(biler);
		
		if(selskap.reserverBil(kunde, bil, utleiekontor, leveringskontor, utleiedato, tidspunkt, antallDager)) {
			System.out.println("Bil (" + bil + ") ble reservert.");
		}
		else {
			System.out.println("Det skjedde en feil, bil kunne ikke reserveres");
		}
		
	}
	
	private Bil velgBil(List<Bil> biler) {
		
		List<Utleiegruppe> ledigeGrupper = biler.stream().map(b -> b.getUtleiegruppe()).distinct().collect(Collectors.toList());
		
		System.out.println("Skriv tall på ønsket bilgruppe: ");
		for(int i = 0; i < ledigeGrupper.size(); i++) {
			System.out.println((i + 1) + ": " + ledigeGrupper.get(i));
		}
		
		Utleiegruppe valgtGruppe = null;
		while(valgtGruppe == null) {
			
			try {
				int valg = Integer.parseInt(scanner.next()) - 1;
				valgtGruppe = ledigeGrupper.get(valg);
			}
			catch(Throwable e) {}
			
		}
		
		Bil bil = null;
		for(int i = 0; i < biler.size(); i++) {
			
			if(biler.get(i).getUtleiegruppe() == valgtGruppe) {
				bil = biler.get(i);
				break;
			}
			
		}
		
		return bil;
		
	}
	
	public void hentBil() {
		
		System.out.println("\n--HENTER BIL --\nSkriv inn kredittkortnummer: ");
		int kredittkort = -1;
		while(kredittkort < 0) {
			
			try {
				kredittkort = Integer.parseInt(scanner.next());
			}
			catch(NumberFormatException e) {}
			
			if(kredittkort < 0) {
				System.out.println("Ugyldig kredittkortnummer, prøv igjen: ");
			}
			
		}
		
		System.out.println("Velg klokkeslett for forventet returtidspunkt");
		LocalTime forventetReturtidspunkt = null;
		while(forventetReturtidspunkt == null) {
			
			try {
				System.out.println("Time: ");
				int time = Integer.parseInt(scanner.next());
				System.out.println("Minutt: ");
				int minutt = Integer.parseInt(scanner.next());
				
				forventetReturtidspunkt = LocalTime.of(time, minutt);
			}
			catch(Throwable e) {}
			
		}
		
		if(selskap.hentBil(kunde, kredittkort, kunde.getReservasjon().getUtleieDato().plusDays(kunde.getReservasjon().getAntallDager()), forventetReturtidspunkt)) {
			System.out.println("Bil ble hentet.");
		}
		else {
			System.out.println("Det skjedde en feil, bil kunne ikke hentes.");
		}
		
	}
	
	private void returnerBil() {
		
		if(selskap.returnerBil(kunde)) {
			System.out.println("Bil ble levert.");
		}
		else {
			System.out.println("Det skjedde en feil, kunne ikke levere bil.");
		}
		
	}
	
	private void kjorBil() {
		
		if(kunde.harUtleie()) {
			
			Random r = new Random();
			int kilometer = r.nextInt(1801) + 200;
			Bil bil = kunde.getReservasjon().getBil();
			bil.setKilometerstand(bil.getKilometerstand() + kilometer);
			
			System.out.print("Kjører bil ");
			
			for(int i = 0; i < 5; i++) {
				
				try {
					Thread.sleep(350);
				} catch (InterruptedException e) {}
				
				for(int n = 0; n < 3; n++) {
					
					try {
						Thread.sleep(120);
					} catch (InterruptedException e) {}
					
					System.out.print(".");
					
				}
				
				System.out.print("VROOM");
				
			}
			
			System.out.println();
			
		}
		
	}

}









