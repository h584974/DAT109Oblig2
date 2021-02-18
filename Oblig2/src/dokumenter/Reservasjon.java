package dokumenter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import aktorer.Bil;
import aktorer.Leiekontor;
import utils.Kundeliste;
import utils.RegnUtleiePris;

public class Reservasjon {
	
	private Bil bil;
	private Date utleieDato;
	private long utleieTidspunkt;
	private int antallDager;
	private Leiekontor utleiekontor;
	private Leiekontor leveringkontor;
	private int pris;
	
	public Reservasjon(Bil bil, Date utleieDato, long utleieTidspunkt, int antallDager, Leiekontor utleiekontor, Leiekontor leveringskontor) {
		super();
		this.bil = bil;
		this.utleieDato = utleieDato;
		this.utleieTidspunkt = utleieTidspunkt;
		this.antallDager = antallDager;
		this.utleiekontor = utleiekontor;
		this.leveringkontor = leveringskontor;
		this.pris = RegnUtleiePris.regnPris(bil,utleiekontor,leveringskontor,antallDager);
	}
	
	public Bil getBil() {
		return bil;
	}

	public Date getUtleieDato() {
		return utleieDato;
	}

	public long getUtleieTidspunkt() {
		return utleieTidspunkt;
	}

	public int getAntallDager() {
		return antallDager;
	}

	public Leiekontor getUtleiekontor() {
		return utleiekontor;
	}

	public Leiekontor getLeveringkontor() {
		return leveringkontor;
	}

	public int getPris() {
		return pris;
	}
	
	public static List<Reservasjon> getAlleReservasjoner() {
		
		return Kundeliste.kundeliste.stream().filter(k -> k.harReservasjon()).map(k -> k.getReservasjon()).collect(Collectors.toList());
		
	}
	
}
