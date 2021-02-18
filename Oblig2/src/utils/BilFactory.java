package utils;

import java.util.List;

import aktorer.Bil;
import aktorer.Leiekontor;

public class BilFactory {
	
	public static void lagBiler() {
		
		Bil bil1 = new Bil(11111,400,Utleiegruppe.A,"Wolswagen","Svart");
		Bil bil2 = new Bil(22222,1600,Utleiegruppe.B,"Mercedes","Grå");
		Bil bil3 = new Bil(33333,5670,Utleiegruppe.C,"Landrover","Grønn");
		Bil bil4 = new Bil(4444,3560,Utleiegruppe.D,"Toyota","Rød");
		
		List<Leiekontor> kontorer = AktivUtleieselskap.selskap.getLeiekontorer();
		
		kontorer.get(0).leggTilBil(bil1);
		kontorer.get(1).leggTilBil(bil2);
		kontorer.get(2).leggTilBil(bil3);
		kontorer.get(3).leggTilBil(bil4);

	}

}
