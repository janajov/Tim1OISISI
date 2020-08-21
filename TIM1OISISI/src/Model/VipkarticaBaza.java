
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

public class VipkarticaBaza {
	
		private static Connection connect = null;
		private static PreparedStatement preparedStatement = null;
		private static ResultSet resultSet = null;

		
		private static String url = "jdbc:mysql://localhost:3306/apoteka";
		private static String username = "root";
		private static String password = "root";

		
		public static HashMap<Integer, VipkarticaKlasa> karticaHash = new HashMap<Integer, VipkarticaKlasa>();

		

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
						.prepareStatement("select * from vipkartica"); 
																		
				resultSet = preparedStatement.executeQuery(); 
				int i = 0;
				while (resultSet.next()) {
					i++;

					VipkarticaKlasa p = new VipkarticaKlasa();
					p.setSifra(resultSet.getInt("sifra"));
					p.setUsername(resultSet.getString("kupac"));
					Calendar datum = Calendar.getInstance();
					datum.setTime(resultSet.getDate("datum"));
					p.setDatum(datum);
					p.setIznos(resultSet.getFloat("iznos"));
					

				
				
					p.setObrisan(resultSet.getBoolean("obrisan"));
					karticaHash.put(p.getSifra(), p);
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

		public boolean dodajKorisnika(VipkarticaKlasa p) {

			try {
				start();

				preparedStatement = connect
						.prepareStatement("insert into vipkartica(sifra,kupac,datum,iznos,obrisan) values (? , ?, ?, ?,?)");

				preparedStatement.setInt(1, p.getSifra());
				preparedStatement.setString(2, p.getUsername());
				java.sql.Date d = new java.sql.Date(p.getDatum().getTimeInMillis());
				preparedStatement.setDate(3, d);
			
				preparedStatement.setFloat(4, p.getIznos());
			
				preparedStatement.setBoolean(5, p.getObrisan());

				preparedStatement.execute();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Desila se greška pri dodavanju Vip kartici!");
				return false;
			} finally {
				close();
			}

			karticaHash.put(p.getSifra(), p);
			return true;
		}

		public boolean izmeniKorisnika(VipkarticaKlasa p) {
			try {
				start();

				preparedStatement = connect
						.prepareStatement("update vipkartica set  kupac = ?, datum  = ?, iznos = ?, obrisan = ?  where sifra = ?");

				
				
				preparedStatement.setString(1, p.getUsername());
				java.sql.Date d = new java.sql.Date(p.getDatum().getTimeInMillis());
				preparedStatement.setDate(2, d);
				preparedStatement.setFloat(3, p.getIznos());
				preparedStatement.setBoolean(4, p.getObrisan());
				preparedStatement.setInt(5, p.getSifra());
			
				preparedStatement.executeUpdate();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Desila se greška pri izmeni Korisnika!");
				return false;
			} finally {
				close();
			}

			karticaHash.remove(p.getSifra());
			karticaHash.put(p.getSifra(), p);

			return true;
		}

		public void refresh() {
			try {
				start();

				preparedStatement = connect
						.prepareStatement("select * from vipkartica"); 
				resultSet = preparedStatement.executeQuery(); 
				karticaHash.clear();
				
				while (resultSet.next()) {
					VipkarticaKlasa p = new VipkarticaKlasa();
					p.setSifra(resultSet.getInt("sifra"));
					p.setUsername(resultSet.getString("kupac"));
					Calendar datum = Calendar.getInstance();
					datum.setTime(resultSet.getDate("datum"));
					p.setDatum(datum);
					p.setIznos(resultSet.getFloat("iznos"));
					

					karticaHash.put(p.getSifra(), p);
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
						.prepareStatement("DELETE FROM vipkartica WHERE sifra=?");
				preparedStatement.setString(1, id);
				preparedStatement.execute();

				karticaHash.remove(id);
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
			return karticaHash.containsKey(id);
		}

		public VipkarticaKlasa GetKorisnik(String id) {
			if (karticaHash.containsKey(id))
				return karticaHash.get(id);

			return null;
		}

		public HashMap<Integer, VipkarticaKlasa> GetKonstruktori() {
			return karticaHash;
		}

		

		


	public VipkarticaBaza() {
		
	}

}
