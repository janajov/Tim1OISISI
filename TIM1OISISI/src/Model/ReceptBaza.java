/**
 * 
 */
/**
 * @author Sladojevic
 *
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ReceptBaza {
	
	private static Connection connect = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	private static String url = "jdbc:mysql://localhost:3306/apoteka";
	private static String username = "root";
	private static String password = "root";

	
	public static HashMap<Integer, ReceptKlasa> receptHash = new HashMap<Integer, ReceptKlasa>();

	public static void start() throws Exception {
		Class.forName("com.mysql.jdbc.Driver"); 
		connect = DriverManager.getConnection(url, username, password); 
	}

	private static void close() {
		try {

			if (resultSet != null) {
				resultSet.close();
			}

			if (connect != null) {
				connect.close();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Greška prilikom zatvaranja konekcije!");
		}
	}


	public boolean importRecept() {
		boolean res = false;

		try {
			start(); 

			preparedStatement = connect
					.prepareStatement("select * from recept"); 
			resultSet = preparedStatement.executeQuery(); 

	
			int i = 0;
			while (resultSet.next()) {
				i++;

				ReceptKlasa p = new ReceptKlasa();
				p.setSifra(resultSet.getInt("sifra"));
				p.setLekar(resultSet.getString("lekar"));
				p.setJmbg(resultSet.getString("jmbg"));
				Calendar datum = Calendar.getInstance();
				datum.setTime(resultSet.getDate("datum"));
				p.setDatum(datum);

				String lekS = resultSet.getString("lekovi");
				String[] lista = lekS.split("-");
				HashMap<String, LekRecept> lekovi = new HashMap<String, LekRecept> ();
				
				for (String spisak : lista) {
				
					String[] lek = spisak.split("\\?");
					LekRecept l = new LekRecept();
					l.setIme(lek[0]);
					l.setKolicina(Integer.parseInt(lek[1]));
					
					lekovi.put(l.getIme(), l);
				}
				p.setLekovi(lekovi);
				p.setUkupno(resultSet.getFloat("ukupno"));
				p.setObrisan(resultSet.getBoolean("obrisan"));
				receptHash.put(p.getSifra(), p);
			}

			res = true; 

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Desila se greška pri importu Recept!");
		} finally {
			close(); 
		}

		return res;
	}

	public boolean dodajRecept(ReceptKlasa p) {

		try {
			start();

			preparedStatement = connect
					.prepareStatement("insert into recept(sifra,lekar,jmbg,datum,lekovi,ukupno,obrisan) values (? ,? ,?, ?, ?, ?, ?)");

			preparedStatement.setInt(1, p.getSifra());
		

			preparedStatement.setString(2, p.getLekar());
		
			preparedStatement.setString(3, p.getJmbg());
		

			java.sql.Date d = new java.sql.Date(p.getDatum().getTimeInMillis());
			preparedStatement.setDate(4, d);
		
			String listaLekova = "";
			for (String i : p.getLekovi().keySet()) {
				listaLekova +=  p.getLekovi().get(i).getIme()+"?"+p.getLekovi().get(i).getKolicina()+"?-";
				
			}
		
			preparedStatement.setString(5, listaLekova);	
			preparedStatement.setFloat(6, p.getUkupno());
			preparedStatement.setBoolean(7, p.getObrisan());
			preparedStatement.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Desila se greška pri dodavanju Recepta!");
			return false;
		} finally {
			close();
		}

		receptHash.put(p.getSifra(), p);
		return true;
	}

	public boolean izmeniRecept(ReceptKlasa p) {
		try {
			start();

			preparedStatement = connect
					.prepareStatement("update recept set  lekar = ?, jmbg = ?, datum  = ?,lekovi = ?, obrisan = ?  where sifra = ?");

			preparedStatement.setString(1, p.getLekar());
			preparedStatement.setString(2, p.getJmbg());

			java.sql.Date d = new java.sql.Date(p.getDatum().getTimeInMillis());
			preparedStatement.setDate(3, d);
		    String listaLekova = "";
			for (String i : p.getLekovi().keySet()) {
				listaLekova +=  p.getLekovi().get(i).getIme()+"?"+p.getLekovi().get(i).getKolicina()+"?-";
				
			}
			preparedStatement.setString(4, listaLekova);
			preparedStatement.setFloat(5, p.getUkupno());
			preparedStatement.setBoolean(6, p.getObrisan());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Desila se greška pri izmeni Recepta!");
			return false;
		} finally {
			close();
		}

		receptHash.remove(p.getSifra());
		receptHash.put(p.getSifra(), p);

		return true;
	}

	public void refresh() {
		try {
			start();

			preparedStatement = connect
					.prepareStatement("select * from recept"); 
			resultSet = preparedStatement.executeQuery(); 
			receptHash.clear();


			while (resultSet.next()) {
				ReceptKlasa p = new ReceptKlasa();
				p.setSifra(resultSet.getInt("sifra"));
				p.setLekar(resultSet.getString("lekar"));
				p.setJmbg(resultSet.getString("jmbg"));
				Calendar datum = Calendar.getInstance();
				datum.setTime(resultSet.getDate("datum"));
				p.setDatum(datum);

				String lekS = resultSet.getString("lekovi");
				String[] lista = lekS.split("-");
				HashMap<String, LekRecept> lekovi = new HashMap<String, LekRecept> ();
			

				for (String spisak : lista) {
				
					String[] lek = spisak.split("\\?");
					LekRecept l = new LekRecept();
					l.setIme(lek[0]);
					l.setKolicina(Integer.parseInt(lek[1]));
				
					lekovi.put(l.getIme(), l);
				}
				p.setLekovi(lekovi);
				p.setUkupno(resultSet.getFloat("ukupno"));
				p.setObrisan(resultSet.getBoolean("obrisan"));
				receptHash.put(p.getSifra(), p);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Desila se greška pri osvezavanju prikaza!");
		} finally {
			close();
		}
	}

	public boolean ukloniRecept(Integer id) {
		boolean res = false;

		try {
			start();
			preparedStatement = connect
					.prepareStatement("DELETE FROM recept WHERE sifra=?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

			receptHash.remove(id);
			res = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Desila se greška pri uklanjanju Recepta!");
			e.printStackTrace();
		} finally {
			close();
		}

		return res;
	}

	public List<HashMap<String, Object>> SelectQuery(String query) {
		List<HashMap<String, Object>> res = new ArrayList<HashMap<String, Object>>();
		int j = 0;
		try {
			start();
		
			preparedStatement = connect.prepareStatement(query);
			

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				j++;
				ReceptKlasa p = new ReceptKlasa();
				HashMap<String, Object> row = new HashMap<String, Object>();
				row.put("sifra", rs.getInt("sifra"));
				row.put("lekar", rs.getString("lekar"));
				row.put("jmbg", rs.getString("jmbg"));
				row.put("datum", rs.getDate("datum"));
				row.put("lekovi", rs.getString("lekovi"));
				row.put("ukupno", rs.getFloat("ukupno"));
				row.put("obrisan", rs.getBoolean("obrisan"));
				res.add(row);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Desila se greška prilikom upita u bazu Recept!");
			e.printStackTrace();
		} finally {
			close();
		}

		if (j == 0)
			return null;
		else

			return res;
	}

	public List<List<Object>> SelectQueryList(String query) {
		List<List<Object>> res = new ArrayList<List<Object>>();
		int j = 0;
		try {
			start();
		
			preparedStatement = connect.prepareStatement(query);
		

			ResultSet rs = preparedStatement.executeQuery();
			
			int columns = rs.getMetaData().getColumnCount();

			while (rs.next()) {

				j++;
	
				List<Object> row = new ArrayList<Object>(columns);
				for (int i = 1; i <= columns; i++) {
					row.add(rs.getObject(i));
					
				}
				res.add(row);
			

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Desila se greška prilikom upita u bazu Recept!");
			e.printStackTrace();
		} finally {
			close();
		}

		if (j == 0)
			return null;
		else

			return res;
	}

	public boolean PostojiRecept(Integer id) {
		return receptHash.containsKey(id);
	}

	public ReceptKlasa GetRecept(Integer id) {
		if (receptHash.containsKey(id))
			return receptHash.get(id);

		return null;
	}

	public HashMap<Integer, ReceptKlasa> GetRecepti() {
		return receptHash;
	}

	public ReceptBaza() {
		

	}

}
