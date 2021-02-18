package utils;

import java.time.LocalDate;
import aktorer.Leiekontor;
import aktorer.Utleieselskap;

public class Test {
	
	public static void main(String...strings) {
		
		LocalDate d1 = LocalDate.of(1999, 4, 17);
		
		LocalDate d2 = LocalDate.of(1999, 4, 20);
		
		System.out.println(d1.compareTo(d2));
		
	}
}
