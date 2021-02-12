package utils;

import java.util.Arrays;
import java.util.List;

import aktorer.Kunde;

public class Kundeliste {
	
	public static List<Kunde> kundeliste = Arrays.asList(new Kunde("Oliver","Oloughlin",47332092,new Addresse("Årstadveien",18,5009,"BERGEN")), new Kunde("Anders","Aarsæther",68686868,new Addresse("Sotragården",1,5656,"SOTRA")), 
			new Kunde("Elias","Storaas",32453245,new Addresse("Storhaugen",69,5232,"PARADIS")));

}
