package Model;

public class LekRecept {
	public LekRecept(String ime, int kolicina) {
		super();
		this.ime = ime;
		this.kolicina = kolicina;
	}

	private String ime;
	private int kolicina;

	public LekRecept() {
		// TODO Auto-generated constructor stub
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

}
