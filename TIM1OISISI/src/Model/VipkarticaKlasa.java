package Model;

import java.util.Calendar;

public class VipkarticaKlasa {
	public VipkarticaKlasa(int sifra, String username, Calendar datum,
			float iznos, Boolean obrisan) {
		super();
		this.sifra = sifra;
		this.username = username;
		this.datum = datum;
		this.iznos = iznos;
		this.obrisan = obrisan;
	}

	
	private int sifra;
	private String username;
	private Calendar datum;
	private float iznos;
	
	private Boolean obrisan = true;

	public VipkarticaKlasa() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Calendar getDatum() {
		return datum;
	}

	public void setDatum(Calendar datum) {
		this.datum = datum;
	}

	public float getIznos() {
		return iznos;
	}

	public void setIznos(float iznos) {
		this.iznos = iznos;
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

}
