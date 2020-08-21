package Interfejs;

import java.awt.BorderLayout;
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
		setTitle("IZDAVANJE RECEPTA");
		setModal(true);
		setBounds(100, 100, 925, 621);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		lekovi.importLek();
		
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
				
				racun.setLekovi(rec.getLekovi());
				float konacanRacun = trenutniRacun;
			
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

