package Model;

public class LekKlasa {
	public LekKlasa(String sifra, String ime, String proizvodjac, boolean recept, float cena) {
		super();
		this.sifra = sifra;
		this.ime = ime;
		this.proizvodjac = proizvodjac;
		this.recept = recept;
		this.cena = cena;
		this.obrisan = true;
	}

	private String sifra;
	private String ime;
	private String proizvodjac;
	private Boolean recept;
	private float cena;
	private Boolean obrisan = true;

	public LekKlasa() {
		// TODO Auto-generated constructor stub
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public Boolean isRecept() {
		return recept;
	}

	public void setRecept(Boolean recept) {
		this.recept = recept;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public Boolean getObrisan() {
		return obrisan;
	}

	public void setObrisan(Boolean obrisan) {
		this.obrisan = obrisan;
	}

	public Boolean getRecept() {
		return recept;
	}

}
