package Model;

public class VipkorisniciKlasa {
	public VipkorisniciKlasa(String username, String ime, String prezime, Boolean obrisan) {
		super();
		this.username = username;
		this.ime = ime;
		this.prezime = prezime;
		this.obrisan = obrisan;
	}

	private String username;

	private String ime;
	private String prezime;

	private Boolean obrisan = true;

	public VipkorisniciKlasa() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Boolean getObrisan() {
		return obrisan;
	}

	public void setObrisan(Boolean obrisan) {
		this.obrisan = obrisan;
	}

}

