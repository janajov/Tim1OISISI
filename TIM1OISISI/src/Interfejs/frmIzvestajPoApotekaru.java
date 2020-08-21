package Interfejs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.LekBaza;
import Model.LekKlasa;
import Model.LekRecept;
import Model.RacunBaza;
import Model.ReceptBaza;
import java.awt.Color;



public class frmIzvestajPoApotekaru extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	DefaultTableModel model;

	LekBaza lekovi = new LekBaza();
	ReceptBaza recepti = new ReceptBaza();
	RacunBaza racuni = new RacunBaza();
	private JTextField txtApotekar;
	JLabel lblSaldo;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmIzvestajPoApotekaru dialog = new frmIzvestajPoApotekaru();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmIzvestajPoApotekaru() {
		getContentPane().setBackground(new Color(102, 205, 170));
		setTitle("IZVE\u0160TAJ SVI PRODATIH LEKOVA IZABRANOG APOTEKARA");
		setModal(true);
		setBounds(100, 100, 841, 419);
		getContentPane().setLayout(null);
		lekovi.importLek();
		recepti.importRecept();
		racuni.importRacun();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 805, 214);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		String[] header = new String[] { "Sifra", "Ime", "Proizvodjaè",
				"Ide na recept", "Cena jednog leka", "Prodata kolicina",
				"Ukupna zarada" };

		model = new DefaultTableModel(new Object[][] {}, header);

		table.setModel(model);

		JButton btnPrikazi = new JButton("Prikazi");
		btnPrikazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtApotekar.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null,
							"Niste uneli ime apotekara za pretragu!");
					return;
				}

				try {
					float ukupnaZaradaApoteke = 0;
					int kolicina = 0;
					float ukupno = 0;
					model.setRowCount(0);
					List<LekKlasa> lista1 = new ArrayList<LekKlasa>();
					for (String i : lekovi.lekHash.keySet()) {
						kolicina = 0;
						LekKlasa obj = lekovi.lekHash.get(i);
						for (Integer r : racuni.racunHash.keySet())

							if (racuni.racunHash
									.get(r)
									.getLekovi()
									.containsKey(lekovi.lekHash.get(i).getIme())) {
								if (racuni.racunHash
										.get(r).getApotekar().compareToIgnoreCase(
										txtApotekar.getText()) == 0) {
									LekRecept l = racuni.racunHash
											.get(r)
											.getLekovi()
											.get(lekovi.lekHash.get(i).getIme());
									kolicina += l.getKolicina();
								}

							}
						if (kolicina > 0) {
							ukupno = kolicina * lekovi.lekHash.get(i).getCena();
							ukupnaZaradaApoteke += ukupno;
							lista1.add(lekovi.lekHash.get(i));
						
							Object[] obj1 = new Object[] { obj.getSifra(),
									obj.getIme(), obj.getProizvodjac(),
									obj.getRecept(), obj.getCena(), kolicina,
									ukupno, };
							model.addRow(obj1);
							// }

						}else {
							JOptionPane.showMessageDialog(null,
								"Izabrani apotekar nije u evidenciji ili nema prodaju!");
							return;
						}	
							
					}

					lblSaldo.setText(Float.toString(ukupnaZaradaApoteke)+ "  dinara");

					table.setFillsViewportHeight(true);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btnPrikazi.setBounds(584, 323, 89, 23);
		getContentPane().add(btnPrikazi);

		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOdustani.setBounds(715, 323, 89, 23);
		getContentPane().add(btnOdustani);

		JLabel lblApotekar = new JLabel("Ime apotekara:");
		lblApotekar.setBounds(39, 310, 151, 14);
		getContentPane().add(lblApotekar);

		txtApotekar = new JTextField();
		txtApotekar.setBounds(175, 307, 151, 20);
		getContentPane().add(txtApotekar);
		txtApotekar.setColumns(10);

		JLabel lblUkupnaZarada = new JLabel("Ukupna zarada apoteke:");
		lblUkupnaZarada.setBounds(470, 236, 173, 14);
		getContentPane().add(lblUkupnaZarada);

		lblSaldo = new JLabel("--------------------------");
		lblSaldo.setBounds(701, 236, 114, 14);
		getContentPane().add(lblSaldo);
		contentPanel.setLayout(null);

		}
	

}
