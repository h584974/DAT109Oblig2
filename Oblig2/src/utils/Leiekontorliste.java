package utils;

import java.util.Arrays;
import java.util.List;
import aktorer.Leiekontor;

public class Leiekontorliste {
	
	/**
	 * Liste over alle leiekontorene til utleieselskapet.
	 */
	public static List<Leiekontor> leiekontorliste = Arrays.asList(
			new Leiekontor(1,21212121,new Addresse("Fleslandsveien", 2, 5400,"BERGEN")),
			new Leiekontor(2,31313131,new Addresse("Oslogaten", 50, 2300,"OSLO")),
			new Leiekontor(3,41414141,new Addresse("Troskialleen", 27, 8545,"TRONDHEIM")),
			new Leiekontor(4,51515151,new Addresse("Gæten", 89, 9999,"TROMSØ")));

}
