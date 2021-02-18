package bruker;

import utils.BilFactory;

public class Klient {
	
	private static final Brukergrensesnitt bg = new Brukergrensesnitt();

	public static void main(String[] args) {
		
		BilFactory.lagBiler();
		
		bg.start();

	}

}
