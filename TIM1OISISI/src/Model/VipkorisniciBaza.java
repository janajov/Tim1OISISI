package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VipkorisniciBaza {

	
	private static Connection connect = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	
	private static String url = "jdbc:mysql://localhost:3306/apoteka";
	private static String username = "root";
	private static String password = "root";

	
	public static HashMap<String, VipkorisniciKlasa> korisnikHash = new HashMap<String, VipkorisniciKlasa>();

	

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
					.prepareStatement("select * from vipkorisnici"); 
			resultSet = preparedStatement.executeQuery(); 
			int i = 0;
			while (resultSet.next()) {
				i++;

				VipkorisniciKlasa p = new VipkorisniciKlasa();
				p.setUsername(resultSet.getString("username"));
			
				p.setIme(resultSet.getString("ime"));

				p.setPrezime(resultSet.getString("prezime"));
			
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

	public boolean dodajKorisnika(VipkorisniciKlasa p) {

		try {
			start();

			preparedStatement = connect
					.prepareStatement("insert into vipkorisnici(username,ime,prezime,obrisan) values (? , ?, ?, ?)");

			preparedStatement.setString(1, p.getUsername());

			
			preparedStatement.setString(2, p.getIme());
			preparedStatement.setString(3, p.getPrezime());
		
			preparedStatement.setBoolean(4, p.getObrisan());

			preparedStatement.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Desila se greška pri dodavanju Vip Korisnika!");
			return false;
		} finally {
			close();
		}

		korisnikHash.put(p.getUsername(), p);
		return true;
	}

	public boolean izmeniKorisnika(VipkorisniciKlasa p) {
		try {
			start();

			preparedStatement = connect
					.prepareStatement("update korisnik set  ime = ?, prezime  = ?, obrisan = ?  where username = ?");

			
			preparedStatement.setString(1, p.getIme());
			preparedStatement.setString(2, p.getPrezime());
			preparedStatement.setBoolean(3, p.getObrisan());
			preparedStatement.setString(4, p.getUsername());
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
					.prepareStatement("select * from vipkorisnici"); 
			resultSet = preparedStatement.executeQuery(); 
			korisnikHash.clear();
			
			while (resultSet.next()) {
				VipkorisniciKlasa p = new VipkorisniciKlasa();
				p.setUsername(resultSet.getString("username"));

				

				p.setIme(resultSet.getString("ime"));

				p.setPrezime(resultSet.getString("prezime"));

				
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
					.prepareStatement("DELETE FROM vipkorisnici WHERE username=?");
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
					"Desila se greška prilikom upita u bazu Vipkorisnici!");
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
					"Desila se greška prilikom upita u bazu Vipkorisnici!");
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

	public VipkorisniciKlasa GetKorisnik(String id) {
		if (korisnikHash.containsKey(id))
			return korisnikHash.get(id);

		return null;
	}

	public HashMap<String, VipkorisniciKlasa> GetKonstruktori() {
		return korisnikHash;
	}

	

	public VipkorisniciBaza() {
		// TODO Auto-generated constructor stub
	}

}
