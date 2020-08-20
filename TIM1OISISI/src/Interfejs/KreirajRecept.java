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


public class KreirajRecept extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblifra;
	private JTextField txtSifra;
	private JLabel lblKolicicina;
	private JTextField txtKolicina;

	LekKlasa lek;
	LekBaza lekovi = new LekBaza();

	private JTable table;
	DefaultTableModel model;
	DefaultTableModel modelRecept;
	String[] header = new String[] { "Sifra", "Ime", "Proizvodjaè",
			"Ide na recept", "Cena" };
	String[] header1 = new String[] { "Sifra", "Ime", "Proizvodjaè",
			"Ide na recept", "Cena", "Kolièina" };
	private JButton btnDodajUrecept;
	private JLabel lblCena;
	private JTextField txtCena;
	private JTable tableKorpa;
	private JLabel lblSadrzajRecepta;

	HashMap<String, LekRecept> lekoviDodeljeni = new HashMap<String, LekRecept>();

	float trenutniRacun = 0.0F;
	ReceptKlasa recept;
	ReceptBaza recepti = new ReceptBaza();
	LekRecept lekoviRecept = new LekRecept();
	String lekar;
	Calendar datum;
	private JButton btnSacuvajRecept;
	
	private JTextField txtJMBG;
	private JTextField txtDatum;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public KreirajRecept(String lekar) {
		setModal(true);
		setBounds(100, 100, 925, 621);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		lekovi.importLek();
		
		this.lekar = lekar;
		DateFormat dateFormat = new SimpleDateFormat(
				"dd-MM-yyyy");

		Date currentDate = new Date();
		Calendar datum = Calendar.getInstance();
		datum.setTime(currentDate);
		

		lblifra = new JLabel("\u0160ifra");
		lblifra.setBounds(10, 111, 89, 14);
		contentPanel.add(lblifra);

		txtSifra = new JTextField();
		txtSifra.setColumns(10);
		txtSifra.setBounds(120, 108, 106, 20);
		contentPanel.add(txtSifra);

		lblKolicicina = new JLabel("Kolicina");
		lblKolicicina.setBounds(10, 142, 46, 14);
		contentPanel.add(lblKolicicina);

		txtKolicina = new JTextField();
		txtKolicina.setColumns(10);
		txtKolicina.setBounds(120, 139, 106, 20);
		contentPanel.add(txtKolicina);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(273, 25, 616, 214);
		contentPanel.add(scrollPane);

		table = new JTable();

		List<List<Object>> lista = lekovi
				.SelectQueryList("SELECT  * from lek order by ime");
		model = new DefaultTableModel(new Object[][] {}, header);
		for (List<Object> obj : lista) {

			 {

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
		btnOdustani.setBounds(745, 535, 89, 23);
		contentPanel.add(btnOdustani);

		btnDodajUrecept = new JButton("Dodaj u recept");
		btnDodajUrecept.addActionListener(new ActionListener() {
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

				modelRecept.addRow(new Object[] { lek.getSifra(), lek.getIme(),
						lek.getProizvodjac(), lek.getRecept(), lek.getCena(),
						kolicina });

				tableKorpa.setModel(modelRecept);

				trenutniRacun += kolicina * Float.parseFloat(txtCena.getText());

			
				LekRecept l = new LekRecept();
				l.setIme(lek.getIme());
				l.setKolicina(kolicina);

				lekoviDodeljeni.put(l.getIme(), l);

			}
		});
		btnDodajUrecept.setBounds(10, 216, 183, 23);
		contentPanel.add(btnDodajUrecept);

		lblCena = new JLabel("Cena");
		lblCena.setBounds(10, 173, 46, 14);
		contentPanel.add(lblCena);

		txtCena = new JTextField();
		txtCena.setEditable(false);
		txtCena.setColumns(10);
		txtCena.setBounds(120, 170, 106, 20);
		contentPanel.add(txtCena);

		JScrollPane scrollKorpa = new JScrollPane();
		scrollKorpa.setBounds(20, 299, 493, 196);
		contentPanel.add(scrollKorpa);

		tableKorpa = new JTable();
		scrollKorpa.setViewportView(tableKorpa);
		modelRecept = new DefaultTableModel(new Object[][] {}, header1);
		tableKorpa.setModel(modelRecept);

		lblSadrzajRecepta = new JLabel("Sadr\u017Eaj recepta");
		lblSadrzajRecepta.setBounds(20, 274, 146, 14);
		contentPanel.add(lblSadrzajRecepta);

	
		

		btnSacuvajRecept = new JButton("Sa\u010Duvaj recept");
		btnSacuvajRecept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recept = new ReceptKlasa();
				recept.setLekar(lekar);
				recept.setDatum(datum);
				recept.setJmbg(txtJMBG.getText());
				recept.setUkupno(trenutniRacun);
				
				recept.setLekovi(lekoviDodeljeni);
				
				
				recepti.dodajRecept(recept);
				JOptionPane.showMessageDialog(null,
						"Recept je sacuvan i iznosi  " + trenutniRacun);
				dispose();
			}
		});
		btnSacuvajRecept.setBounds(509, 535, 167, 23);
		contentPanel.add(btnSacuvajRecept);
		
		JLabel lblJmbgPacijenta = new JLabel("JMBG pacijenta");
		lblJmbgPacijenta.setBounds(10, 32, 89, 14);
		contentPanel.add(lblJmbgPacijenta);
		
		txtJMBG = new JTextField();
		txtJMBG.setColumns(10);
		txtJMBG.setBounds(120, 29, 106, 20);
		contentPanel.add(txtJMBG);
		
		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setBounds(10, 68, 89, 14);
		contentPanel.add(lblDatum);
		
		txtDatum = new JTextField();
		txtDatum.setColumns(10);
		txtDatum.setBounds(120, 65, 106, 20);
		contentPanel.add(txtDatum);
		
		txtDatum.setText(dateFormat.format(datum.getTime()).toString());

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
