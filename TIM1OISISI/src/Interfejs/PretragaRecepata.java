package Interfejs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Model.LekBaza;
import Model.ReceptBaza;
import Model.ReceptKlasa;

@SuppressWarnings("serial")
public class PretragaRecepata extends JDialog {

	private JTable table;
	private final ButtonGroup buttonPretraga = new ButtonGroup();
	DefaultTableModel model;

	ReceptBaza recepti = new ReceptBaza();
	private JTextField txtSifraUpit;

	JPanel panelSifra;
	String customQuery = "";

	JRadioButton rdbtnPoSifri;
	JRadioButton rdbtnPoLekaru;
	JRadioButton rdbtnPoJMBG;
	JRadioButton rdbtnPoJednomLeku;
	private JPanel panelLekar;
	private JTextField txtLekarPretraga;
	private JLabel lblIme;
	private JPanel panelJMBG;
	private JLabel labelJmbg;
	private JTextField txtJmbg;
	private JPanel panelLek;
	private JLabel lblLek;
	private JTextField txtLek;
	private JButton btnOdustani;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PretragaRecepata dialog = new PretragaRecepata();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PretragaRecepata() {
		getContentPane().setBackground(new Color(102, 205, 170));
		setModal(true);
		setBounds(100, 100, 891, 388);
		getContentPane().setLayout(null);
		recepti.importRecept();

		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 205, 170));

		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Pretraga",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel.setBounds(10, 33, 181, 169);
		getContentPane().add(panel);

		rdbtnPoSifri = new JRadioButton("po \u0161ifri");
		rdbtnPoSifri.setBackground(new Color(102, 205, 170));
		rdbtnPoSifri.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnPoSifri.isSelected()) {

					panelSifra.setVisible(true);
					panelLekar.setVisible(false);

					panelJMBG.setVisible(false);
					panelLek.setVisible(false);

				}
			}
		});

		buttonPretraga.add(rdbtnPoSifri);
		rdbtnPoSifri.setBounds(0, 31, 109, 23);
		panel.add(rdbtnPoSifri);

		rdbtnPoLekaru = new JRadioButton("po lekaru");
		rdbtnPoLekaru.setBackground(new Color(102, 205, 170));
		rdbtnPoLekaru.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnPoLekaru.isSelected()) {

					panelLekar.setVisible(true);
					panelSifra.setVisible(false);
					panelJMBG.setVisible(false);
					panelLek.setVisible(false);
				}
			}
		});
		buttonPretraga.add(rdbtnPoLekaru);
		rdbtnPoLekaru.setBounds(0, 57, 109, 23);
		panel.add(rdbtnPoLekaru);

		rdbtnPoJMBG = new JRadioButton("po JMBG pacijenta");
		rdbtnPoJMBG.setBackground(new Color(102, 205, 170));
		rdbtnPoJMBG.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnPoJMBG.isSelected()) {

					panelLekar.setVisible(false);
					panelSifra.setVisible(false);
					panelJMBG.setVisible(true);
					panelLek.setVisible(false);
				}
			}
		});
		buttonPretraga.add(rdbtnPoJMBG);
		rdbtnPoJMBG.setBounds(0, 83, 132, 23);
		panel.add(rdbtnPoJMBG);

		rdbtnPoJednomLeku = new JRadioButton("po jednom leku");
		rdbtnPoJednomLeku.setBackground(new Color(102, 205, 170));
		rdbtnPoJednomLeku.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnPoJednomLeku.isSelected()) {

					panelLekar.setVisible(false);
					panelSifra.setVisible(false);
					panelJMBG.setVisible(false);
					panelLek.setVisible(true);
				}
			}
		});
		buttonPretraga.add(rdbtnPoJednomLeku);
		rdbtnPoJednomLeku.setBounds(0, 109, 162, 23);
		panel.add(rdbtnPoJednomLeku);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(232, 33, 582, 214);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		String[] header = new String[] { "Sifra", "Lekar", "Pacijent", "Datum",
				"Ukupna cena" };

		model = new DefaultTableModel(new Object[][] {}, header);

		table.setModel(model);

		JButton btnPrikazi = new JButton("Prika\u017Ei");
		btnPrikazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
				List<List<Object>> lista = null;
				try {
					if (rdbtnPoSifri.isSelected() || rdbtnPoLekaru.isSelected()
							|| rdbtnPoJMBG.isSelected()
							|| rdbtnPoJednomLeku.isSelected()) {
						model.setRowCount(0);
						if (rdbtnPoSifri.isSelected()) {
							if (!txtSifraUpit.getText().isEmpty()) {
								String s = txtSifraUpit.getText().trim();

								customQuery = "SELECT  * from recept where sifra =  "
										+ s;
								lista = recepti.SelectQueryList(customQuery);
								for (List<Object> obj : lista) {
									String date = format1.format(obj.get(3));

									Object[] obj1 = new Object[] { obj.get(0),
											obj.get(1), obj.get(2), date, obj.get(5) };
									model.addRow(obj1);

								}

							} else {
								JOptionPane
										.showMessageDialog(panel,
												"Morate uneti vrednost za pretragu!!!!");
								return;
							}
						} else if (rdbtnPoLekaru.isSelected()) {

							if (!txtLekarPretraga.getText().isEmpty()) {

								customQuery = "SELECT  * from recept where lekar LIKE  '%"
										+ txtLekarPretraga.getText() + "%'";
								lista = recepti.SelectQueryList(customQuery);
								for (List<Object> obj : lista) {
									String date = format1.format(obj.get(3));

									Object[] obj1 = new Object[] { obj.get(0),
											obj.get(1), obj.get(2), date, obj.get(5) };
									model.addRow(obj1);

								}
							} else {
								JOptionPane
										.showMessageDialog(panel,
												"Morate uneti vrednost za pretragu!!!!");
								return;
							}
						} else if (rdbtnPoJMBG.isSelected()) {

							if (!txtJmbg.getText().isEmpty()) {

								customQuery = "SELECT  * from recept where jmbg LIKE  '%"
										+ txtJmbg.getText() + "%'";
								lista = recepti.SelectQueryList(customQuery);
								for (List<Object> obj : lista) {
									String date = format1.format(obj.get(3));

									Object[] obj1 = new Object[] { obj.get(0),
											obj.get(1), obj.get(2), date, obj.get(5) };
									model.addRow(obj1);

								}
							} else {
								JOptionPane
										.showMessageDialog(panel,
												"Morate uneti vrednost za pretragu!!!!");
								return;
							}
						} else if (rdbtnPoJednomLeku.isSelected()) {

							if ((txtLek.getText().isEmpty())) {
								JOptionPane
										.showMessageDialog(panel,
												"Morate uneti vrednost za pretragu!!!!");
								return;
							} else {

								List <ReceptKlasa>lista1 = new ArrayList<ReceptKlasa>();
								
								for (Integer i : recepti.receptHash.keySet())
								
									if (recepti.receptHash.get(i).getLekovi()
											.containsKey(txtLek.getText())){
									
										lista1.add( recepti.receptHash.get(i));}
								for (ReceptKlasa obj : lista1) {
								String date = format1.format(obj.getDatum().getTime());
							
									Object[] obj1 = new Object[] { obj.getSifra(),
											obj.getLekar(), obj.getJmbg(), date, obj.getUkupno() };
									model.addRow(obj1);

								}

							}
						}

					

						

						table.setModel(model);
						table.setFillsViewportHeight(true);
					} else
						JOptionPane.showMessageDialog(null,
								"Morate selektovati izbor pretrage!!!!");
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

			}
		});
		btnPrikazi.setBounds(608, 282, 89, 23);
		getContentPane().add(btnPrikazi);

		panelSifra = new JPanel();
		panelSifra.setBounds(10, 241, 181, 87);
		getContentPane().add(panelSifra);
		panelSifra.setLayout(null);

		JLabel labelSifra = new JLabel("Unesi \u0161ifru za pretragu:");
		labelSifra.setBounds(10, 11, 136, 14);
		panelSifra.add(labelSifra);

		txtSifraUpit = new JTextField();

		txtSifraUpit.setColumns(10);
		txtSifraUpit.setBounds(32, 36, 114, 20);
		panelSifra.add(txtSifraUpit);

		panelLekar = new JPanel();
		panelLekar.setBounds(10, 241, 181, 87);
		getContentPane().add(panelLekar);
		panelLekar.setLayout(null);

		lblIme = new JLabel("Unesi ime lekara za pretragu:");
		lblIme.setBounds(10, 11, 123, 14);
		panelLekar.add(lblIme);

		txtLekarPretraga = new JTextField();
		txtLekarPretraga.setBounds(24, 36, 119, 20);
		panelLekar.add(txtLekarPretraga);
		txtLekarPretraga.setColumns(10);

		panelJMBG = new JPanel();
		panelJMBG.setBounds(10, 241, 181, 87);
		getContentPane().add(panelJMBG);
		panelJMBG.setLayout(null);

		labelJmbg = new JLabel("Unesi JMBG pacijenta za pretragu:");
		labelJmbg.setBounds(10, 11, 161, 14);
		panelJMBG.add(labelJmbg);

		txtJmbg = new JTextField();
		txtJmbg.setColumns(10);
		txtJmbg.setBounds(24, 36, 119, 20);
		panelJMBG.add(txtJmbg);

		panelLek = new JPanel();
		panelLek.setBounds(10, 241, 181, 87);
		getContentPane().add(panelLek);
		panelLek.setLayout(null);

		lblLek = new JLabel("Unesi ime leka za pretragu:");
		lblLek.setBounds(10, 11, 129, 14);
		panelLek.add(lblLek);

		txtLek = new JTextField();
		txtLek.setBounds(85, 36, 86, 20);
		panelLek.add(txtLek);
		txtLek.setColumns(10);
		
		btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOdustani.setBounds(725, 282, 89, 23);
		getContentPane().add(btnOdustani);
		panelLekar.setVisible(false);
		panelSifra.setVisible(false);
		panelJMBG.setVisible(false);
		panelLek.setVisible(false);
	}

}
