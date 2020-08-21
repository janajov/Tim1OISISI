package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RacunBaza {
	
	
	
		private static Connection connect = null;
		private static PreparedStatement preparedStatement = null;
		private static ResultSet resultSet = null;

		
		private static String url = "jdbc:mysql://localhost:3306/apoteka";
		private static String username = "root";
		private static String password = "root";

		
		public static HashMap<Integer, RacunKlasa> racunHash = new HashMap<Integer, RacunKlasa>();

		

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

	

		public boolean importRacun() {
			boolean res = false;

			try {
				start(); // da se nakaèi na bazu

				preparedStatement = connect
						.prepareStatement("select * from racun"); 
				resultSet = preparedStatement.executeQuery(); 
																
				
				int i = 0;
				while (resultSet.next()) {
					i++;

					RacunKlasa p = new RacunKlasa();
				
					p.setSifra(resultSet.getInt("sifra"));
					
			
					p.setApotekar(resultSet.getString("apotekar"));
					p.setKupac(resultSet.getString("kupac"));
				
					Calendar datum = Calendar.getInstance();
					
					p.setDatum(datum);
					
					String lekS = resultSet.getString("listaproizvoda");
					
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
					
					racunHash.put(p.getSifra(), p);
				}

				res = true; // ako nigde do sada nije pukao znaèi da se uspešno
							// izvršilo
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Desila se greška pri importu Racuna!");
			} finally {
				close(); // da se otkaèi sa baze
			}

			return res;
		}

		public boolean dodajRacun(RacunKlasa p) {

			try {
				start();

				preparedStatement = connect
						.prepareStatement("insert into racun(sifra,apotekar,kupac,datum,listaproizvoda,ukupno,obrisan) values (? ,? ,?, ?, ?, ?, ?)");

				preparedStatement.setInt(1, p.getSifra());

				preparedStatement.setString(2, p.getApotekar());
				preparedStatement.setString(3, p.getKupac());
			

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
						"Desila se greška pri dodavanju Racuna!");
				return false;
			} finally {
				close();
			}

			racunHash.put(p.getSifra(), p);
			return true;
		}

		public boolean izmeniRacun(RacunKlasa p) {
			try {
				start();

				preparedStatement = connect
						.prepareStatement("update racun set apotekar = ?,kupac = ?,  datum  = ?,listaproizvoda = ?, ukupno = ?,obrisan = ?  where sifra = ?");

				preparedStatement.setString(1, p.getApotekar());
				preparedStatement.setString(2, p.getKupac());
			

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
						"Desila se greška pri izmeni Racuna!");
				return false;
			} finally {
				close();
			}

			racunHash.remove(p.getSifra());
			racunHash.put(p.getSifra(), p);

			return true;
		}

		public void refresh() {
			try {
				start();

				preparedStatement = connect
						.prepareStatement("select * from racun"); // priprema
																	// sql upita
				resultSet = preparedStatement.executeQuery(); // izvršavanje
																// pripremljenog
																// sql upita
				racunHash.clear();
				// kupimo rezultate sql upita i dodajemo ih u kolekciju
				while (resultSet.next()) {
			

					RacunKlasa p = new RacunKlasa();
					p.setSifra(resultSet.getInt("sifra"));
					p.setApotekar(resultSet.getString("apotekar"));
					p.setKupac(resultSet.getString("kupac"));
				
					Calendar datum = Calendar.getInstance();
					datum.setTime(resultSet.getDate("datum"));
					p.setDatum(datum);

					String lekS = resultSet.getString("listaproizvoda");
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
					racunHash.put(p.getSifra(), p);
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Desila se greška pri osvezavanju prikaza!");
			} finally {
				close();
			}
		}

		public boolean ukloniRacun(Integer id) {
			boolean res = false;

			try {
				start();
				preparedStatement = connect
						.prepareStatement("DELETE FROM racun WHERE sifra=?");
				preparedStatement.setInt(1, id);
				preparedStatement.execute();

				racunHash.remove(id);
				res = true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Desila se greška pri uklanjanju Racunaa!");
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
					RacunKlasa p = new RacunKlasa();
					HashMap<String, Object> row = new HashMap<String, Object>();
					row.put("sifra", rs.getInt("sifra"));
					row.put("apotekar", rs.getString("apotekar"));
					row.put("kupac", rs.getString("kupac"));
					
					row.put("datum", rs.getDate("datum"));
					row.put("lekovi", rs.getString("listaproizvoda"));
					row.put("ukupno", rs.getFloat("ukupno"));
					row.put("obrisan", rs.getBoolean("obrisan"));
					res.add(row);

				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Desila se greška prilikom upita u bazu Racun!");
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
						"Desila se greška prilikom upita u bazu Racun!");
				e.printStackTrace();
			} finally {
				close();
			}

			if (j == 0)
				return null;
			else

				return res;
		}

		public boolean PostojiRacun(Integer id) {
			return racunHash.containsKey(id);
		}

		public RacunKlasa GetRacun(Integer id) {
			if (racunHash.containsKey(id))
				return racunHash.get(id);

			return null;
		}

		public HashMap<Integer, RacunKlasa> GetRacuni() {
			return racunHash;
		}

		

	public RacunBaza() {
		// TODO Auto-generated constructor stub
	}

}