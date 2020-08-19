package Interfejs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class RegistracijaNovihKorisnika  extends JDialog {
	private JTextField ime;
	private JTextField prezime;
	private JPasswordField password;

	
	public static void main(String[] args) {
		try {
			RegistracijaNovihKorisnika dialog = new RegistracijaNovihKorisnika();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public RegistracijaNovihKorisnika() {
		getContentPane().setBackground(new Color(102, 205, 170));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JTextField korisnickoime = new JTextField();
		korisnickoime.setBounds(131, 29, 116, 22);
		getContentPane().add(korisnickoime);
		korisnickoime.setColumns(10);
		
		ime = new JTextField();
		ime.setBounds(131, 97, 116, 22);
		getContentPane().add(ime);
		ime.setColumns(10);
		
		prezime = new JTextField();
		prezime.setBounds(131, 131, 116, 22);
		getContentPane().add(prezime);
		prezime.setColumns(10);
		
		JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
		lblKorisnickoIme.setBounds(20, 29, 116, 22);
		getContentPane().add(lblKorisnickoIme);
		
		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setBounds(20, 63, 56, 16);
		getContentPane().add(lblLozinka);
		
		JLabel lblIme = new JLabel("Ime");
		lblIme.setBounds(20, 97, 56, 16);
		getContentPane().add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setBounds(20, 131, 84, 16);
		getContentPane().add(lblPrezime);
		
		password = new JPasswordField();
		password.setBounds(131, 63, 116, 22);
		getContentPane().add(password);
		
		JLabel lblTipKorisnika = new JLabel("Tip korisnika");
		lblTipKorisnika.setBounds(20, 165, 84, 16);
		getContentPane().add(lblTipKorisnika);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(131, 165, 116, 22);
		getContentPane().add(comboBox);
		
		JButton btnUnesi = new JButton("Unesi korisnika");
		btnUnesi.setBounds(187, 215, 125, 25);
		getContentPane().add(btnUnesi);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(324, 215, 97, 25);
		getContentPane().add(btnOdustani);
	}
}