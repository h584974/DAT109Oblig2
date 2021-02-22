package utils;

import java.util.List;

import aktorer.Bil;
import aktorer.Leiekontor;

public class BilFactory {
	
	/**
	 * Produserer alle bilene til utleieselskapet, som lagres i de forskjellige spesifiserte utleiekontorene.
	 */
	public static void lagBiler() {
		
		Bil bil1 = new Bil(11111,400,Utleiegruppe.A,"Volkswagen","Svart");
		Bil bil2 = new Bil(22222,1600,Utleiegruppe.B,"Mercedes","Grå");
		Bil bil3 = new Bil(33333,5670,Utleiegruppe.C,"Landrover","Grønn");
		Bil bil4 = new Bil(4444,3560,Utleiegruppe.D,"Toyota","Rød");
		Bil bil5 = new Bil(5555,23400,Utleiegruppe.A,"Toyota","Blå");
		Bil bil6 = new Bil(6666,56700,Utleiegruppe.B,"Volvo","Hvit");
		Bil bil7 = new Bil(7777,34600,Utleiegruppe.C,"Tesla","Sølv");
		Bil bil8 = new Bil(8888,12400,Utleiegruppe.D,"Koenigsegg","HOT");
		Bil bil9 = new Bil(9999,67800,Utleiegruppe.A,"Volvo","Svart");
		Bil bil10 = new Bil(1010,2300,Utleiegruppe.B,"Porche","DARK");
		Bil bil11 = new Bil(1100,34500,Utleiegruppe.C,"Dacia","Rusten");
		Bil bil12 = new Bil(6666,56700,Utleiegruppe.D,"Lambo","BEDAZZLE");
		
		List<Leiekontor> kontorer = AktivUtleieselskap.selskap.getLeiekontorer();
		
		kontorer.get(0).leggTilBil(bil1);
		kontorer.get(0).leggTilBil(bil7);
		kontorer.get(0).leggTilBil(bil12);
		
		kontorer.get(1).leggTilBil(bil2);
		kontorer.get(1).leggTilBil(bil11);
		kontorer.get(1).leggTilBil(bil5);
		
		kontorer.get(2).leggTilBil(bil3);
		kontorer.get(2).leggTilBil(bil9);
		kontorer.get(2).leggTilBil(bil6);
		
		kontorer.get(3).leggTilBil(bil4);
		kontorer.get(3).leggTilBil(bil8);
		kontorer.get(3).leggTilBil(bil10);

	}

}
