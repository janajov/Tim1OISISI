package Interfejs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import Model.KorisnikBaza;
import Model.KorisnikKlasa;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ApotekaRegistracija {

	KorisnikBaza korisnici;
	KorisnikKlasa korisnik;
	int brojPristupa = 0;

	private JFrame frmApoteka;
	private JTextField txtKorisnickoIme;
	private JPasswordField pasLozinka;
	int prikaz =1;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApotekaRegistracija window = new ApotekaRegistracija();
					window.frmApoteka.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ApotekaRegistracija() {
		korisnici = new KorisnikBaza();
		korisnici.importKorisnik();
		initialize();
	}

	
	private void initialize() {
		frmApoteka = new JFrame();
		frmApoteka.getContentPane().setBackground(new Color(102, 205, 170));
		frmApoteka.setTitle("APOTEKA");
		frmApoteka.setBounds(100, 100, 450, 300);
		frmApoteka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmApoteka.getContentPane().setLayout(null);

		JLabel lblKorisnickoIme = new JLabel("Korisni\u010Dko ime");
		lblKorisnickoIme.setBounds(63, 51, 108, 14);
		frmApoteka.getContentPane().add(lblKorisnickoIme);

		txtKorisnickoIme = new JTextField();
		txtKorisnickoIme.setBounds(159, 48, 86, 20);
		frmApoteka.getContentPane().add(txtKorisnickoIme);
		txtKorisnickoIme.setColumns(10);

		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setBounds(63, 102, 46, 14);
		frmApoteka.getContentPane().add(lblLozinka);

		pasLozinka = new JPasswordField();
		pasLozinka.setEchoChar('*');
		pasLozinka.setBounds(159, 99, 86, 20);
		frmApoteka.getContentPane().add(pasLozinka);

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					String korIme = txtKorisnickoIme.getText();
					@SuppressWarnings("deprecation")
					String lozinka = pasLozinka.getText();

					if (!korisnici.PostojiKorisnik(korIme)) {

						JOptionPane
								.showMessageDialog(btnOK,
										"Korisnik sa unetim korisnièkim imenom  ne postoji!");
						brojPristupa++;
						if (brojPristupa >= 3) {
							JOptionPane.showMessageDialog(btnOK,
									"Pokusali ste vise od tri puta!");
							System.exit(0);
						}

					}

					korisnik = korisnici.GetKorisnik(korIme);
					if (!(korisnik.getLozinka().compareTo(lozinka) == 0)) {

						JOptionPane.showMessageDialog(btnOK,
								"Lozinka nije dobra!Pokusajte ponovo!!");
						brojPristupa++;
						if (brojPristupa >= 3) {
							JOptionPane.showMessageDialog(btnOK,
									"Pokusali ste vise od tri puta! Dovidjenja!!!!");
							System.exit(0);
						}
					} else {
						JOptionPane.showMessageDialog(
								btnOK,
								"Ulogovani ste sa ulogom  "
										+ korisnik.getTip());

						if (korisnik.getTip().compareToIgnoreCase("Administrator") == 0) {
							frmAdministrator frm = new frmAdministrator(prikaz);
							frm.setVisible(true);

						}else if (korisnik.getTip().compareToIgnoreCase("Apotekar") == 0) {
							prikaz = 0;
							Apotekar frm = new Apotekar(korisnik.getUsername(),prikaz);
							frm.setVisible(true);}
						else if (korisnik.getTip().compareToIgnoreCase("Lekar") == 0) {
							prikaz = 0;
							Lekar frm = new Lekar(korisnik.getUsername(),prikaz);
							frm.setVisible(true);}
					}

				

				} catch (Exception ex) {
					
				}

			}
		});
		btnOK.setBounds(161, 217, 108, 23);
		frmApoteka.getContentPane().add(btnOK);

		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(btnOK, "Dovidjenja!!!!");
				System.exit(0);
			}
		});
		btnOdustani.setBounds(299, 217, 108, 23);
		frmApoteka.getContentPane().add(btnOdustani);
	}
}
