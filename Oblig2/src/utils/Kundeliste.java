package utils;

import java.util.Arrays;
import java.util.List;

import aktorer.Kunde;

public class Kundeliste {
	
	public static List<Kunde> kundeliste = Arrays.asList(new Kunde("Oliver","Oloughlin",47332092,new Addresse("�rstadveien",18,5009,"BERGEN")), new Kunde("Anders","Aars�ther",68686868,new Addresse("Sotrag�rden",1,5656,"SOTRA")), 
			new Kunde("Elias","Storaas",32453245,new Addresse("Storhaugen",69,5232,"PARADIS")));

}
