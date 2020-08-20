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

import Model.KorisnikKlasa;

public class KorisnikBaza {

	
	private static Connection connect = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	
	private static String url = "jdbc:mysql://localhost:3306/apoteka";
	private static String username = "root";
	private static String password = "root";

	
	
	public static HashMap<String, KorisnikKlasa> korisnikHash = new HashMap<String, KorisnikKlasa>();

	

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

	

	public boolean importKorisnik() {
		boolean res = false;

		try {
			start(); 

			preparedStatement = connect
					.prepareStatement("select * from korisnik"); 
																	
			resultSet = preparedStatement.executeQuery(); 
															
															

			
			int i = 0;
			while (resultSet.next()) {
				i++;

				KorisnikKlasa p = new KorisnikKlasa();
				p.setUsername(resultSet.getString("username"));
				p.setLozinka(resultSet.getString("lozinka"));
				p.setIme(resultSet.getString("ime"));

				p.setPrezime(resultSet.getString("prezime"));
				p.setTip(resultSet.getString("tip"));
				p.setObrisan(resultSet.getBoolean("obrisan"));
				korisnikHash.put(p.getUsername(), p);
			}

			res = true; 
						
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Desila se greška pri importu Korisnik!");
		} finally {
			close(); 
		}

		return res;
	}

	public boolean dodajKorisnika(KorisnikKlasa p) {

		try {
			start();

			preparedStatement = connect
					.prepareStatement("insert into korisnik(username,lozinka,ime,prezime,tip,obrisan) values (? ,? ,?, ?, ?, ?)");

			preparedStatement.setString(1, p.getUsername());

			preparedStatement.setString(2, p.getLozinka());
			preparedStatement.setString(3, p.getIme());
			preparedStatement.setString(4, p.getPrezime());
			preparedStatement.setString(5, p.getTip());
			preparedStatement.setBoolean(6, p.getObrisan());

			preparedStatement.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Desila se greška pri dodavanju Korisnika!");
			return false;
		} finally {
			close();
		}

		korisnikHash.put(p.getUsername(), p);
		return true;
	}

	public boolean izmeniKorisnika(KorisnikKlasa p) {
		try {
			start();

			preparedStatement = connect
					.prepareStatement("update korisnik set  lozinka = ?, ime = ?, prezime  = ?,tip = ?, obrisan = ?  where username = ?");

			preparedStatement.setString(1, p.getLozinka());
			preparedStatement.setString(2, p.getIme());
			preparedStatement.setString(3, p.getPrezime());
			preparedStatement.setString(4, p.getTip());
			preparedStatement.setBoolean(5, p.getObrisan());
			preparedStatement.setString(6, p.getUsername());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Desila se greška pri izmeni Korisnika!");
			return false;
		} finally {
			close();
		}

		korisnikHash.remove(p.getUsername());
		korisnikHash.put(p.getUsername(), p);

		return true;
	}

	public void refresh() {
		try {
			start();

			preparedStatement = connect
					.prepareStatement("select * from korisnik"); 
																	
			resultSet = preparedStatement.executeQuery(); 
															
			korisnikHash.clear();
			
			while (resultSet.next()) {
				KorisnikKlasa p = new KorisnikKlasa();
				p.setUsername(resultSet.getString("username"));

				p.setLozinka(resultSet.getString("lozinka"));

				p.setIme(resultSet.getString("ime"));

				p.setPrezime(resultSet.getString("prezime"));

				p.setTip(resultSet.getString("tip"));
				p.setObrisan(resultSet.getBoolean("obrisan"));

				korisnikHash.put(p.getUsername(), p);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Desila se greška pri osvezavanju prikaza!");
		} finally {
			close();
		}
	}

	public boolean ukloniKorisnika(String id) {
		boolean res = false;

		try {
			start();
			preparedStatement = connect
					.prepareStatement("DELETE FROM korisnik WHERE username=?");
			preparedStatement.setString(1, id);
			preparedStatement.execute();

			korisnikHash.remove(id);
			res = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Desila se greška pri uklanjanju Korisnik!");
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
			
			ResultSetMetaData md = rs.getMetaData();
	
			int columns = rs.getMetaData().getColumnCount();

			while (rs.next()) {

				j++;
				
				HashMap<String, Object> row = new HashMap<String, Object>(
						columns);
				for (int i = 1; i <= columns; i++) {
					row.put(md.getColumnName(i), rs.getObject(i));
				
				}
				res.add(row);
			
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Desila se greška prilikom upita u bazu Korisnik!");
			e.printStackTrace();
		} finally {
			close();
		}

		if (j == 0)
			return null;
		else

			return res;
	}
	public List<List< Object>> SelectQueryList(String query) {
		List<List<Object>> res = new ArrayList<List< Object>>();
		int j = 0;
		try {
			start();
			
			preparedStatement = connect.prepareStatement(query);
		

			ResultSet rs = preparedStatement.executeQuery();
			
			ResultSetMetaData md = rs.getMetaData();
		
			int columns = rs.getMetaData().getColumnCount();

			while (rs.next()) {

				j++;
			
				List< Object> row = new ArrayList<Object>(columns);
				for (int i = 1; i <= columns; i++) {
					row.add( rs.getObject(i));
					
				}
				res.add(row);
			

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Desila se greška prilikom upita u bazu Korisnik!");
			e.printStackTrace();
		} finally {
			close();
		}

		if (j == 0)
			return null;
		else

			return res;
	}
	public boolean PostojiKorisnik(String id) {
		return korisnikHash.containsKey(id);
	}

	public KorisnikKlasa GetKorisnik(String id) {
		if (korisnikHash.containsKey(id))
			return korisnikHash.get(id);

		return null;
	}

	public HashMap<String, KorisnikKlasa> GetKonstruktori() {
		return korisnikHash;
	}

	public KorisnikBaza() {

	}

}
