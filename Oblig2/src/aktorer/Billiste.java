package aktorer;

import java.util.Arrays;
import java.util.List;

import utils.Leiekontorliste;
import utils.Utleiegruppe;

public class Billiste {
	
	public static List<Bil> billiste = Arrays.asList(
			new Bil(11111,400,Utleiegruppe.A,"Wolswagen","Svart",Leiekontorliste.leiekontorliste.get(0)),
			new Bil(22222,1600,Utleiegruppe.B,"Mercedes","Grå",Leiekontorliste.leiekontorliste.get(1)),
			new Bil(33333,5670,Utleiegruppe.C,"Landrover","Grønn",Leiekontorliste.leiekontorliste.get(2)),
			new Bil(4444,3560,Utleiegruppe.D,"Toyota","Rød",Leiekontorliste.leiekontorliste.get(3)));

}
