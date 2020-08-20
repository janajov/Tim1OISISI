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

public class LekBaza {

	
	private static Connection connect = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	
	private static String url = "jdbc:mysql://localhost:3306/apoteka";
	private static String username = "root";
	private static String password = "root";

	
	public static HashMap<String, LekKlasa> lekHash = new HashMap<String, LekKlasa>();

	

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
			JOptionPane.showMessageDialog(new JFrame(), "Greška prilikom zatvaranja konekcije!");
		}
	}

	

	public boolean importLek() {
		boolean res = false;

		try {
			start(); 
			preparedStatement = connect.prepareStatement("select * from lek"); 
			resultSet = preparedStatement.executeQuery(); 

			
			int i = 0;
			while (resultSet.next()) {
				i++;

				LekKlasa p = new LekKlasa();
				p.setSifra(resultSet.getString("sifra"));

				p.setIme(resultSet.getString("ime"));

				p.setProizvodjac(resultSet.getString("proizvodjac"));

				p.setRecept(resultSet.getBoolean("recept"));

				p.setCena(resultSet.getFloat("cena"));
				p.setObrisan(resultSet.getBoolean("obrisan"));

				lekHash.put(p.getSifra(), p);
			}

			res = true; 
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Desila se greška pri importu Lek!");
		} finally {
			close(); 
		}

		return res;
	}

	public boolean dodajLek(LekKlasa p) {

		try {
			start();

			preparedStatement = connect.prepareStatement(
					"insert into lek(sifra,ime,proizvodjac,recept,cena,obrisan) values (? ,? ,?, ?, ?, ?)");

			preparedStatement.setString(1, p.getSifra());

			preparedStatement.setString(2, p.getIme());
			preparedStatement.setString(3, p.getProizvodjac());
			preparedStatement.setBoolean(4, p.isRecept());
			preparedStatement.setFloat(5, p.getCena());
			preparedStatement.setBoolean(6, p.getObrisan());

			preparedStatement.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Desila se greška pri dodavanju Leka!");
			return false;
		} finally {
			close();
		}

		lekHash.put(p.getSifra(), p);
		return true;
	}

	public boolean izmenaLeka(LekKlasa p) {
		try {
			start();

			preparedStatement = connect.prepareStatement(
					"update lek set  ime = ?, proizvodjac = ?, recept  = ?,cena = ?, obrisan = ?  where sifra = ?");

			preparedStatement.setString(1, p.getIme());
			preparedStatement.setString(2, p.getProizvodjac());
			preparedStatement.setBoolean(3, p.isRecept());
			preparedStatement.setFloat(4, p.getCena());
			preparedStatement.setBoolean(5, p.getObrisan());
			preparedStatement.setString(6, p.getSifra());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Desila se greška pri izmeni Leka!");
			return false;
		} finally {
			close();
		}

		lekHash.remove(p.getSifra());
		lekHash.put(p.getSifra(), p);

		return true;
	}

	public void refresh() {
		try {
			start();

			preparedStatement = connect.prepareStatement("select * from lek"); 
			resultSet = preparedStatement.executeQuery(); 
			lekHash.clear();
			
			while (resultSet.next()) {
				LekKlasa p = new LekKlasa();
				p.setSifra(resultSet.getString("sifra"));

				p.setIme(resultSet.getString("ime"));

				p.setProizvodjac(resultSet.getString("proizvodjac"));

				p.setRecept(resultSet.getBoolean("recept"));

				p.setCena(resultSet.getFloat("cena"));
				p.setObrisan(resultSet.getBoolean("obrisan"));

				lekHash.put(p.getSifra(), p);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Desila se greška pri osvezavanju prikaza!");
		} finally {
			close();
		}
	}

	public boolean ukloniLeka(String id) {
		boolean res = false;

		try {
			start();
			preparedStatement = connect.prepareStatement("DELETE FROM lek WHERE sifra=?");
			preparedStatement.setString(1, id);
			preparedStatement.execute();

			lekHash.remove(id);
			res = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Desila se greška pri uklanjanju Lek!");
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

				HashMap<String, Object> row = new HashMap<String, Object>(columns);
				for (int i = 1; i <= columns; i++) {
					row.put(md.getColumnName(i), rs.getObject(i));

				}
				res.add(row);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Desila se greška prilikom upita u bazu Lek!");
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

			ResultSetMetaData md = rs.getMetaData();

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
			JOptionPane.showMessageDialog(new JFrame(), "Desila se greška prilikom upita u bazu Lek!");
			e.printStackTrace();
		} finally {
			close();
		}

		if (j == 0)
			return null;
		else

			return res;
	}

	public boolean PostojiLek(String id) {

		return lekHash.containsKey(id);
	}

	public LekKlasa GetLek(String id) {
		if (lekHash.containsKey(id))
			return lekHash.get(id);

		return null;
	}

	public HashMap<String, LekKlasa> GetKonstruktori() {
		return lekHash;
	}

	public LekBaza() {
		
	}

}
