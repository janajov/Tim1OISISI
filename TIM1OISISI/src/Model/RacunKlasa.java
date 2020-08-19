package Model;

import java.util.Calendar;
import java.util.HashMap;

public class RacunKlasa {

	public RacunKlasa(int sifra, String apotekar, String kupac, Calendar datum,
			HashMap<String, LekRecept> lekovi, float ukupno, Boolean obrisan) {
		super();
		this.sifra = sifra;
		this.apotekar = apotekar;
		this.kupac = kupac;
		this.datum = datum;
		this.lekovi = lekovi;
		this.ukupno = ukupno;
		this.obrisan = obrisan;
	}
	
	private int sifra;
	private String apotekar;
	private String kupac;
	private Calendar datum;
	private HashMap<String,LekRecept> lekovi;
	private float ukupno;
	private Boolean obrisan = true;
	public RacunKlasa() {
		// TODO Auto-generated constructor stub
	}
	public String getApotekar() {
		return apotekar;
	}
	public void setApotekar(String apotekar) {
		this.apotekar = apotekar;
	}
	public Calendar getDatum() {
		return datum;
	}
	public void setDatum(Calendar datum) {
		this.datum = datum;
	}
	public HashMap<String, LekRecept> getLekovi() {
		return lekovi;
	}
	public void setLekovi(HashMap<String, LekRecept> lekovi) {
		this.lekovi = lekovi;
	}
	public float getUkupno() {
		return ukupno;
	}
	public void setUkupno(float ukupno) {
		this.ukupno = ukupno;
	}
	public Boolean getObrisan() {
		return obrisan;
	}
	public void setObrisan(Boolean obrisan) {
		this.obrisan = obrisan;
	}
	public int getSifra() {
		return sifra;
	}
	public void setSifra(int sifra) {
		this.sifra = sifra;
	}
	public String getKupac() {
		return kupac;
	}
	public void setKupac(String kupac) {
		this.kupac = kupac;
	}

}
