package Interfejs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.LekBaza;
import Model.LekKlasa;
import Model.LekRecept;
import Model.RacunBaza;
import Model.RacunKlasa;
import Model.ReceptKlasa;





import javax.swing.JCheckBox;

public class SlobodnaProdaja  extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JLabel lblifra;
	private JTextField txtSifra;
	private JLabel lblKolicicina;
	private JTextField txtKolicina;

	LekKlasa lek;
	LekBaza lekovi = new LekBaza();
	private Vector<Boolean> comboBoxItems;
	private JTable table;
	DefaultTableModel model;
	DefaultTableModel modelKorpa;
	String[] header = new String[] { "Sifra", "Ime", "Proizvodjaè",
			"Ide na recept", "Cena" };
	String[] header1 = new String[] { "Sifra", "Ime", "Proizvodjaè",
			"Ide na recept", "Cena", "Kolièina" };
	private JButton btnDodajUkorpu;
	private JLabel lblCena;
	private JTextField txtCena;
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
	

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { frmSlobodnaProdaja dialog
	 * = new frmSlobodnaProdaja();
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	public SlobodnaProdaja(String apotekar, Calendar datum) {
		setTitle("PRODAJA BEZ RECEPTA");
		setModal(true);
		setBounds(100, 100, 925, 621);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		lekovi.importLek();
		
		this.apotekar = apotekar;
		this.datum = datum;

		lblifra = new JLabel("\u0160ifra");
		lblifra.setBounds(10, 26, 89, 14);
		contentPanel.add(lblifra);

		txtSifra = new JTextField();
		txtSifra.setColumns(10);
		txtSifra.setBounds(120, 23, 106, 20);
		contentPanel.add(txtSifra);

		lblKolicicina = new JLabel("Kolicina");
		lblKolicicina.setBounds(10, 57, 46, 14);
		contentPanel.add(lblKolicicina);

		txtKolicina = new JTextField();
		txtKolicina.setColumns(10);
		txtKolicina.setBounds(120, 54, 106, 20);
		contentPanel.add(txtKolicina);

		comboBoxItems = new Vector<Boolean>();
		comboBoxItems.add(Boolean.TRUE);
		comboBoxItems.add(Boolean.FALSE);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(273, 25, 616, 214);
		contentPanel.add(scrollPane);

		table = new JTable();

		List<List<Object>> lista = lekovi
				.SelectQueryList("SELECT  * from lek order by ime");
		model = new DefaultTableModel(new Object[][] {}, header);
		for (List<Object> obj : lista) {

			if ((Integer) obj.get(3) == 0) {

				model.addRow(new Object[] { obj.get(0), obj.get(1), obj.get(2),
						obj.get(3), obj.get(4) });
			}

		}

		table.setModel(model);
		scrollPane.setViewportView(table);

		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOdustani.setBounds(436, 535, 89, 23);
		contentPanel.add(btnOdustani);

		btnDodajUkorpu = new JButton("Dodaj u korpu");
		btnDodajUkorpu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null,
							"Morate selektovati lek  koji zelite da dodate!");
					return;
				}
				String sifra = txtSifra.getText();
				lek = lekovi.GetLek(sifra);
				if (txtKolicina.getText().isEmpty()) {
					JOptionPane
							.showMessageDialog(null,
									"Morate uneti kolicinu  leka  koji zelite da dodate!");
					return;
				}
				int kolicina = Integer.parseInt(txtKolicina.getText());

				modelKorpa.addRow(new Object[] { lek.getSifra(), lek.getIme(),
						lek.getProizvodjac(), lek.getRecept(), lek.getCena(),
						kolicina });

				tableKorpa.setModel(modelKorpa);

				trenutniRacun += kolicina * Float.parseFloat(txtCena.getText());

				lblTrenutniIznosRacuna.setText("Trenutni iznos racuna:   "
						+ trenutniRacun);

				LekRecept l = new LekRecept();
				l.setIme(lek.getIme());
				l.setKolicina(kolicina);

				lekoviKupljeni.put(l.getIme(), l);

			}
		});
		btnDodajUkorpu.setBounds(10, 143, 183, 23);
		contentPanel.add(btnDodajUkorpu);

		lblCena = new JLabel("Cena");
		lblCena.setBounds(10, 88, 46, 14);
		contentPanel.add(lblCena);

		txtCena = new JTextField();
		txtCena.setEditable(false);
		txtCena.setColumns(10);
		txtCena.setBounds(120, 85, 106, 20);
		contentPanel.add(txtCena);

		JScrollPane scrollKorpa = new JScrollPane();
		scrollKorpa.setBounds(20, 299, 493, 196);
		contentPanel.add(scrollKorpa);

		tableKorpa = new JTable();
		scrollKorpa.setViewportView(tableKorpa);
		modelKorpa = new DefaultTableModel(new Object[][] {}, header1);
		tableKorpa.setModel(modelKorpa);
		
		lblSadrzajKorpe = new JLabel("Sadr\u017Eaj korpe");
		lblSadrzajKorpe.setBounds(20, 253, 146, 14);
		contentPanel.add(lblSadrzajKorpe);

		lblTrenutniIznosRacuna = new JLabel("Trenutni iznos racuna:  ");
		lblTrenutniIznosRacuna.setBounds(20, 528, 206, 14);
		contentPanel.add(lblTrenutniIznosRacuna);

		panelNapraviRacun = new JPanel();
		panelNapraviRacun.setBounds(577, 332, 322, 226);
		contentPanel.add(panelNapraviRacun);
		panelNapraviRacun.setLayout(null);
		panelNapraviRacun.setVisible(false);
		buttonSacuvaj = new JButton("Sa\u010Duvaj ra\u010Dun");
		buttonSacuvaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				racun = new RacunKlasa();
				racun.setApotekar(apotekar);
				racun.setDatum(datum);
				
				racun.setLekovi(lekoviKupljeni);
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
				if(modelKorpa.getRowCount()>0)
				panelNapraviRacun.setVisible(true);
				else
					JOptionPane.showMessageDialog(null,
							"Korpa je prazna!!!");
			}
		});
		btnNapraviRacun.setBounds(577, 266, 167, 23);
		contentPanel.add(btnNapraviRacun);

		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {

						tableSelect(event);
					}
				});
	}

	private void tableSelect(ListSelectionEvent lse) {
		if (table.getSelectedRow() < 0)
			return;

		try {

			Object polje = model.getValueAt(table.getSelectedRow(), 0);
	
			LekKlasa rec = lekovi.GetLek(polje.toString());

			txtSifra.setText(rec.getSifra());
		
			txtCena.setText(Float.toString(rec.getCena()));

		} catch (Exception e) {
			System.out.println("Greska!!!!!" + e.getMessage());
		}
	}
}