package Model;
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
	
	
	// promenjive za rad sa bazom
		private static Connection connect = null;
		private static PreparedStatement preparedStatement = null;
		private static ResultSet resultSet = null;

		// podaci za bazu
		private static String url = "jdbc:mysql://localhost:3306/apoteka";
		private static String username = "root";
		private static String password = "root";

		// kolekcije sa kojima radimo u program
		// bolja i brža varijanta u odnosu na listu, kljuè da bude ID Racuna!
		public static HashMap<Integer, RacunKlasa> racunHash = new HashMap<Integer, RacunKlasa>();

		/************************** GLAVNE METODE **************************/

		public static void start() throws Exception {
			Class.forName("com.mysql.jdbc.Driver"); // loadovanje drivera za bazu
			connect = DriverManager.getConnection(url, username, password); // uspostavljanje
																			// konekcije
																			// na
																			// bazu
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
}