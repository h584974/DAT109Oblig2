package utils;

public class Addresse {
	
	private String gatenavn;
	private int gatenummer;
	private int postnummer;
	private String poststed;
	
	public Addresse(String gatenavn, int gatenummer, int postnummer, String poststed) {
		super();
		this.gatenavn = gatenavn;
		this.gatenummer = gatenummer;
		this.postnummer = postnummer;
		this.poststed = poststed;
	}

	public String getGatenavn() {
		return gatenavn;
	}

	public void setGatenavn(String gatenavn) {
		this.gatenavn = gatenavn;
	}

	public int getGatenummer() {
		return gatenummer;
	}

	public void setGatenummer(short gatenummer) {
		this.gatenummer = gatenummer;
	}

	public int getPostnummer() {
		return postnummer;
	}

	public void setPostnummer(int postnummer) {
		this.postnummer = postnummer;
	}

	public String getPoststed() {
		return poststed;
	}

	public void setPoststed(String poststed) {
		this.poststed = poststed;
	}

}
