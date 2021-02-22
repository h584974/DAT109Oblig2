package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import aktorer.Bil;
import aktorer.Leiekontor;
import utils.Leiekontorliste;
import utils.Utleiegruppe;

public class LeiekontorTest {
	
	@Test
	public void testLeggTilOgFjernBil() {
		
		Bil bil = new Bil(0, 0, Utleiegruppe.A, "0", "0");
		Leiekontor kontor = Leiekontorliste.leiekontorliste.get(0);
		
		Assertions.assertFalse(kontor.getBiler().contains(bil));
		
		kontor.leggTilBil(bil);
		Assertions.assertTrue(kontor.getBiler().contains(bil));
		
		kontor.fjernBil(bil);
		Assertions.assertFalse(kontor.getBiler().contains(bil));
		
	}

}
