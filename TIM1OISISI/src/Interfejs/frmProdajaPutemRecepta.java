package Interfejs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.LekBaza;
import Model.LekKlasa;
import Model.LekRecept;
import Model.RacunBaza;
import Model.RacunKlasa;
import Model.ReceptBaza;
import Model.ReceptKlasa;
import Model.VipkarticaBaza;
import Model.VipkarticaKlasa;
import Model.VipkorisniciBaza;
import Model.VipkorisniciKlasa;


public class frmProdajaPutemRecepta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblifra;
	private JTextField txtSifra;

	LekKlasa lek;
	LekBaza lekovi = new LekBaza();
	
	DefaultTableModel modelKorpa;
	String[] header = new String[] { "Sifra", "Ime", "Proizvodjaè",
			"Ide na recept", "Cena" };
	String[] header1 = new String[] { "Sifra",  "Kolièina" };
	private JButton btnDodajUkorpu;
	private JTable tableKorpa;
	private JLabel lblSadrzajKorpe;
	private JLabel lblTrenutniIznosRacuna;

	HashMap<String, LekRecept> lekoviKupljeni = new HashMap<String, LekRecept>();

	float trenutniRacun = 0.0F;
	RacunKlasa racun;
	RacunBaza racuni = new RacunBaza();
	LekRecept lekoviRacun = new LekRecept();
	String apotekar;
	Calendar datum;
	private JPanel panelNapraviRacun;
	private JButton buttonSacuvaj;
	private JButton btnNapraviRacun;
	private JLabel lblImeKupca;
	private JTextField txtIme;
	private JLabel lblPrezimeKupca;
	private JTextField txtPrezime;
	private JLabel lblUsername;
	private JTextField txtUsername;
	private JButton btnDodajVipKupca;
	JCheckBox chbxVipKorisnik;
	VipkarticaBaza vipKartica = new VipkarticaBaza();
	VipkorisniciBaza vipKorisnici = new VipkorisniciBaza();
	VipkorisniciKlasa vp = new VipkorisniciKlasa();
	
	
	ReceptBaza recepti = new ReceptBaza();
	ReceptKlasa rec;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			frmProdajaPutemRecepta dialog = new frmProdajaPutemRecepta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Create the dialog.
	 */
	public frmProdajaPutemRecepta(String apotekar, Calendar datum) {
		getContentPane().setBackground(new Color(102, 205, 170));
		setTitle("IZDAVANJE RECEPTA");
		setModal(true);
		setBounds(100, 100, 925, 621);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(102, 205, 170));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		lekovi.importLek();
		vipKartica.importKorisnik();
		vipKorisnici.importKorisnik();
		recepti.importRecept();
		this.apotekar = apotekar;
		this.datum = datum;

		lblifra = new JLabel("\u0160ifra recepta");
		lblifra.setBounds(10, 26, 89, 14);
		contentPanel.add(lblifra);

		txtSifra = new JTextField();
		txtSifra.setColumns(10);
		txtSifra.setBounds(120, 23, 106, 20);
		contentPanel.add(txtSifra);

		

	

		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOdustani.setBounds(26, 521, 89, 23);
		contentPanel.add(btnOdustani);

		btnDodajUkorpu = new JButton("Dodaj sva lekve sa recepta u korpu");
		btnDodajUkorpu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtSifra.getText().isEmpty())  {
					JOptionPane.showMessageDialog(null,
							"Morate sifru recepta  koji zelite da izdate!");
					return;
				}
				String sifra = txtSifra.getText();
				
				
				try {
					//String[] header1 = new String[] { "Lek", "Kolièina" };
					modelKorpa = new DefaultTableModel(new Object[][] {}, header1);
			
					int tableID = Integer.parseInt(sifra);
					 rec = recepti.GetRecept(tableID);
					
					float cena=0;
					for (String i : rec.getLekovi().keySet()) {
						
						modelKorpa.addRow(new Object[] { rec.getLekovi().get(i).getIme(), rec.getLekovi().get(i).getKolicina() });
						for(String lek :lekovi.lekHash.keySet())
							if(lekovi.lekHash.get(lek).getIme().compareTo(rec.getLekovi().get(i).getIme())==0)cena = lekovi.lekHash.get(lek).getCena();
						
						trenutniRacun += rec.getLekovi().get(i).getKolicina() *cena; ;
						
					}

					tableKorpa.setModel(modelKorpa);
					

				} catch (Exception e) {
					System.out.println("Greska!!!!!"+e.getMessage());
				}
				
				lblTrenutniIznosRacuna.setText("Trenutni iznos racuna:   "
						+ trenutniRacun);

			}
		});
		btnDodajUkorpu.setBounds(328, 22, 236, 23);
		contentPanel.add(btnDodajUkorpu);

		JScrollPane scrollKorpa = new JScrollPane();
		scrollKorpa.setBounds(10, 137, 493, 196);
		contentPanel.add(scrollKorpa);

		tableKorpa = new JTable();
		scrollKorpa.setViewportView(tableKorpa);
		modelKorpa = new DefaultTableModel(new Object[][] {}, header1);
		tableKorpa.setModel(modelKorpa);

		lblSadrzajKorpe = new JLabel("Sadr\u017Eaj korpe");
		lblSadrzajKorpe.setBounds(10, 94, 146, 14);
		contentPanel.add(lblSadrzajKorpe);

		lblTrenutniIznosRacuna = new JLabel("Trenutni iznos racuna:  ");
		lblTrenutniIznosRacuna.setBounds(10, 378, 206, 14);
		contentPanel.add(lblTrenutniIznosRacuna);

		panelNapraviRacun = new JPanel();
		panelNapraviRacun.setBounds(577, 195, 322, 226);
		contentPanel.add(panelNapraviRacun);
		panelNapraviRacun.setLayout(null);
		panelNapraviRacun.setVisible(false);
		buttonSacuvaj = new JButton("Sa\u010Duvaj ra\u010Dun");
		buttonSacuvaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				racun = new RacunKlasa();
				racun.setApotekar(apotekar);
				racun.setDatum(datum);
				racun.setKupac(vp.getUsername());
				racun.setLekovi(rec.getLekovi());
				float konacanRacun = trenutniRacun;
				if (chbxVipKorisnik.isSelected()) {
					float zbir = 0;

					DateFormat dateFormat = new SimpleDateFormat(
							"dd-MM-yyyy HH:mm:ss");

					Date currentDate = new Date();
					Calendar c = Calendar.getInstance();
					c.setTime(currentDate);
					c.add(Calendar.MONTH, -1);
					System.out.println(dateFormat.format(c.getTime())
							.toString());
					for (Integer i : vipKartica.karticaHash.keySet()) {
						if (vipKartica.karticaHash.get(i).getUsername()
								.compareTo(vp.getUsername()) == 0) {
							if (vipKartica.karticaHash.get(i).getDatum()
									.after(c))

								zbir += vipKartica.karticaHash.get(i)
										.getIznos();

						}

					}
					float popust =0;
					if (zbir > 5000){
						popust = konacanRacun * 20F / 100;
						konacanRacun = konacanRacun - popust;
						JOptionPane.showMessageDialog(null,
								"Korisnik ima pravo na 20%popusta i iznosi " + popust+ "  Ukupan racun je "+konacanRacun );
					}
						
					else if (zbir > 1000)
						
					{
						popust = konacanRacun * 10F / 100;
						konacanRacun = konacanRacun - popust;
						JOptionPane.showMessageDialog(null,
								"Korisnik ima pravo na 10%popusta i iznosi " + popust+ "  Ukupan racun je "+konacanRacun );
					}
					else{
						popust = konacanRacun * 5F / 100;
						konacanRacun = konacanRacun - popust;
						JOptionPane.showMessageDialog(null,
								"Korisnik ima pravo na 5%popusta i iznosi " + popust+ "  Ukupan racun je "+konacanRacun );
					}
						
					VipkarticaKlasa p = new VipkarticaKlasa();
					c.add(Calendar.MONTH, 1);
					p.setDatum(datum);
					p.setIznos(konacanRacun);
					p.setUsername(vp.getUsername());
					vipKartica.dodajKorisnika(p);

				}
				racun.setUkupno(konacanRacun);
				racuni.dodajRacun(racun);
				JOptionPane.showMessageDialog(null,
						"Racun je sacuvan i iznosi  " + konacanRacun);
				dispose();

			}
		});
		buttonSacuvaj.setBounds(40, 192, 169, 23);
		panelNapraviRacun.add(buttonSacuvaj);

		lblImeKupca = new JLabel("Ime kupca");
		lblImeKupca.setBounds(10, 11, 96, 14);
		panelNapraviRacun.add(lblImeKupca);

		txtIme = new JTextField();
		txtIme.setColumns(10);
		txtIme.setBounds(132, 8, 106, 20);
		panelNapraviRacun.add(txtIme);

		lblPrezimeKupca = new JLabel("Prezime kupca");
		lblPrezimeKupca.setBounds(10, 39, 96, 14);
		panelNapraviRacun.add(lblPrezimeKupca);

		txtPrezime = new JTextField();
		txtPrezime.setColumns(10);
		txtPrezime.setBounds(132, 36, 106, 20);
		panelNapraviRacun.add(txtPrezime);

		lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 67, 96, 14);
		panelNapraviRacun.add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(132, 64, 106, 20);
		panelNapraviRacun.add(txtUsername);

		btnDodajVipKupca = new JButton("Dodaj vip kupca");
		btnDodajVipKupca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chbxVipKorisnik.setSelected(false);
				String ime = txtIme.getText();
				String prezime = txtPrezime.getText();
				String username = txtUsername.getText();

				if (vipKorisnici.PostojiKorisnik(username)) {
					chbxVipKorisnik.setSelected(true);
					vp.setUsername(username);
					JOptionPane.showMessageDialog(null,
							"Korisnik je clan Vip kluba!");
				}

				else {
					if (txtIme.getText().isEmpty()
							|| txtPrezime.getText().isEmpty()
							|| txtUsername.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"Morate uneti sva polja!");
						return;

					}
					vp.setIme(ime);
					vp.setPrezime(prezime);
					vp.setUsername(username);
					chbxVipKorisnik.setSelected(true);
					vipKorisnici.dodajKorisnika(vp);
					JOptionPane.showMessageDialog(null,
							"Korisnik je sada upisan  Vip klub!");
				}

			}
		});
		btnDodajVipKupca.setBounds(192, 103, 120, 23);
		panelNapraviRacun.add(btnDodajVipKupca);

		chbxVipKorisnik = new JCheckBox("Vip korisnik");
		chbxVipKorisnik.setEnabled(false);
		chbxVipKorisnik.setBounds(10, 135, 97, 23);
		panelNapraviRacun.add(chbxVipKorisnik);

		btnNapraviRacun = new JButton("Napravi racun");
		btnNapraviRacun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNapraviRacun.setVisible(true);
			}
		});
		btnNapraviRacun.setBounds(593, 140, 167, 23);
		contentPanel.add(btnNapraviRacun);
	}

	
}

