package tests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import aktorer.Bil;
import aktorer.Leiekontor;
import utils.AktivUtleieselskap;
import utils.TestSetup;

public class BilTest {
	
	private TestSetup setup;
	
	@BeforeEach
	public void setup() {
		setup = new TestSetup();
	}
	
	@Test
	public void testErReservert() {
		
		Bil ledigBil = setup.getLedigBil();
		Bil reservertBil = setup.getReservertBil();
		Assertions.assertTrue(reservertBil.erReservert());
		Assertions.assertFalse(ledigBil.erReservert());
		
	}
	
	@Test
	public void testGetLedigeBiler() {
		
		Bil ledigBil = setup.getLedigBil();
		Bil reservertBil = setup.getReservertBil();
		Bil levertBil = setup.getLevertBil();
		int antallDager = 7;
		List<Leiekontor> kontorer = AktivUtleieselskap.selskap.getLeiekontorer();
		
		Leiekontor leiekontor1 = kontorer.get(0);
		Leiekontor leiekontor2 = kontorer.get(1);
		Leiekontor leiekontor3 = kontorer.get(2);
		
		List<Bil> ledigeBiler1 = Bil.getLedigeBiler(leiekontor1, leiekontor1, LocalDate.of(2021, 1, 1), LocalTime.of(12, 0), antallDager);
		
		List<Bil> ledigeBiler2 = Bil.getLedigeBiler(leiekontor2, leiekontor2, LocalDate.of(2021, 1, 1), LocalTime.of(12, 0), antallDager);
		List<Bil> ledigeBiler3 = Bil.getLedigeBiler(leiekontor2, leiekontor2, LocalDate.of(2021, 1, 8), LocalTime.of(13, 0), antallDager);
		List<Bil> ledigeBiler4 = Bil.getLedigeBiler(leiekontor3, leiekontor2, LocalDate.of(2021, 1, 6), LocalTime.of(13, 0), antallDager);
		
		List<Bil> ledigeBiler5 = Bil.getLedigeBiler(leiekontor3, leiekontor3, LocalDate.of(2021, 1, 10), LocalTime.of(12, 0), antallDager);
		List<Bil> ledigeBiler6 = Bil.getLedigeBiler(leiekontor3, leiekontor3, LocalDate.of(2021, 1, 8), LocalTime.of(13, 0), antallDager);
		List<Bil> ledigeBiler7 = Bil.getLedigeBiler(leiekontor3, leiekontor3, LocalDate.of(2021, 1, 6), LocalTime.of(13, 0), antallDager);
		
		Assertions.assertTrue(ledigeBiler1.contains(ledigBil));
		
		Assertions.assertFalse(ledigeBiler2.contains(reservertBil));
		Assertions.assertTrue(ledigeBiler3.contains(reservertBil));
		Assertions.assertFalse(ledigeBiler4.contains(reservertBil));
		
		Assertions.assertTrue(ledigeBiler5.contains(levertBil));
		Assertions.assertTrue(ledigeBiler6.contains(levertBil));
		Assertions.assertFalse(ledigeBiler7.contains(levertBil));
		
	}

}
