/**
 * 
 */
/**
 * @author Sladojevic
 *
 */
package Model;

import java.util.Calendar;
import java.util.HashMap;

public class ReceptKlasa {
	
	public ReceptKlasa(int sifra, String lekar, String jmbg, Calendar datum,
		HashMap<String,LekRecept> lekovi,float ukupno) {
			super();
			this.sifra=sifra;
			this.lekar=lekar;
			this.jmbg=jmbg;
			this.datum=datum;
			this.lekovi=lekovi;
			this.ukupno=ukupno;
			this.obrisan=true;
		}
	
	private int sifra;	
	
	private String lekar;
	private String jmbg;
	private Calendar datum;
	private HashMap<String,LekRecept> lekovi;
	private float ukupno;
	private Boolean obrisan=true;
	
	
	public ReceptKlasa(){
		//TODO Auto-generated constructor
	}	
	public int getSifra() {
		return sifra;
	}

	public void setSifra(int sifra) {
		this.sifra = sifra;
	}
	public String getLekar() {
		return lekar;
	}
	public void setLekar(String lekar) {
		this.lekar = lekar;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public Calendar getDatum() {
		return datum;
	}
	public void setDatum(Calendar datum) {
		this.datum = datum;
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
	
	public void setLekovi(HashMap<String, LekRecept> lekovi) {
		this.lekovi = lekovi;
	}
	public HashMap<String, LekRecept> getLekovi() {
		return lekovi;
	}

}