package Interfejs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import Model.KorisnikBaza;
import Model.KorisnikKlasa;

public class RegistracijaNovihKorisnika  extends JDialog {
	private JTextField ime;
	private JTextField prezime;
	private JPasswordField password;
	private JLabel lblKorisnickoIme;
	private JLabel lblLozinka;
	private JLabel lblTipKorisnika;
	private JLabel lblIme;
	private JLabel lblPrezime;
	private JComboBox comboBox;
	private JButton btnUnesi;
	
	
	KorisnikKlasa korisnik;
	KorisnikBaza korisnici = new KorisnikBaza();

	
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
		setTitle("REGISTRCIJA KORISNIKA");
		setModal(true);
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
		
		lblKorisnickoIme = new JLabel("Korisnicko ime");
		lblKorisnickoIme.setBounds(20, 29, 116, 22);
		getContentPane().add(lblKorisnickoIme);
		
		lblLozinka = new JLabel("Lozinka");
		lblLozinka.setBounds(20, 63, 56, 16);
		getContentPane().add(lblLozinka);
		
		lblIme = new JLabel("Ime");
		lblIme.setBounds(20, 97, 56, 16);
		getContentPane().add(lblIme);
		
		lblPrezime = new JLabel("Prezime");
		lblPrezime.setBounds(20, 131, 84, 16);
		getContentPane().add(lblPrezime);
		
		password = new JPasswordField();
		password.setBounds(131, 63, 116, 22);
		getContentPane().add(password);
		
		lblTipKorisnika = new JLabel("Tip korisnika");
		lblTipKorisnika.setBounds(20, 165, 84, 16);
		getContentPane().add(lblTipKorisnika);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Lekar", "Apotekar"}));
		comboBox.setBounds(131, 165, 116, 22);
		getContentPane().add(comboBox);
		
		btnUnesi = new JButton("Unesi korisnika");
		btnUnesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				korisnik = new KorisnikKlasa();
				try {

					String korIme = korisnickoime.getText();
					
				
					if (korisnici.PostojiKorisnik(korIme)) {

						JOptionPane
								.showMessageDialog(null,
										"Korisnik sa unetim korisnièkim imenom  postoji!");
						return;
						}
					String lozinka = password.getText();

					korisnik.setUsername(korIme);
					korisnik.setLozinka(password.getText());
					korisnik.setIme(ime.getText());
					korisnik.setPrezime(prezime.getText());
					korisnik.setTip((String)comboBox.getSelectedItem());
					korisnici.dodajKorisnika(korisnik);

					
					JOptionPane
					.showMessageDialog(null,
							"Korisnik je dodat!");
					


				} catch (Exception ex) {
					
				}

				
			}
		});
			
			
			
		btnUnesi.setBounds(187, 215, 125, 25);
		getContentPane().add(btnUnesi);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnOdustani.setBounds(324, 215, 97, 25);
		getContentPane().add(btnOdustani);
	}
}