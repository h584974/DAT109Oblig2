package aktorer;

import java.util.Arrays;
import java.util.List;

import utils.Utleiegruppe;

public class Billiste {
	
	public static List<Bil> billiste = Arrays.asList(
			new Bil(11111,400,Utleiegruppe.A,"Wolswagen","Svart"),
			new Bil(22222,1600,Utleiegruppe.B,"Mercedes","Gr�"),
			new Bil(33333,5670,Utleiegruppe.C,"Landrover","Gr�nn"),
			new Bil(4444,3560,Utleiegruppe.D,"Toyota","R�d"));

}
