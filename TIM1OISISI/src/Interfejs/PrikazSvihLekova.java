package Interfejs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Model.KorisnikBaza;
import Model.LekBaza;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.awt.Color;

public class PrikazSvihLekova extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private final ButtonGroup buttonSortiranje = new ButtonGroup();
	DefaultTableModel model;
	int prikaz;

	LekBaza lekovi = new LekBaza();

	
	public PrikazSvihLekova(int prikaz) {
		getContentPane().setBackground(new Color(102, 205, 170));
		
		setTitle("Prikaz svih lekova");
		setModal(true);
		setBounds(100, 100, 764, 364);
		getContentPane().setLayout(null);
		this.prikaz = prikaz;

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(230, 13, 486, 233);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		String[] header = new String[] { "Sifra", "Ime", "Proizvodjaè", "Ide na recept", "Cena" };

		model = new DefaultTableModel(new Object[][] {}, header);

		table.setModel(model);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 205, 170));
		panel.setBorder(new TitledBorder(null, "Sortiranje", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 36, 168, 161);
		getContentPane().add(panel);
		panel.setLayout(null);

		JRadioButton rdbtIme = new JRadioButton("po imenu leka");
		rdbtIme.setBackground(new Color(102, 205, 170));
		buttonSortiranje.add(rdbtIme);
		rdbtIme.setBounds(0, 31, 109, 23);
		panel.add(rdbtIme);

		JRadioButton rdbtProizvodjac = new JRadioButton("po proizvodja\u010Du");
		rdbtProizvodjac.setBackground(new Color(102, 205, 170));
		buttonSortiranje.add(rdbtProizvodjac);
		rdbtProizvodjac.setBounds(0, 57, 139, 23);
		panel.add(rdbtProizvodjac);

		JRadioButton rdbtCena = new JRadioButton("po ceni");
		rdbtCena.setBackground(new Color(102, 205, 170));
		buttonSortiranje.add(rdbtCena);
		rdbtCena.setBounds(0, 83, 109, 23);
		panel.add(rdbtCena);

		JButton btnPrikazi = new JButton("Prikazi");
		btnPrikazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// lekovi.importLek();
				String customQuery = "";
				try {

					if (rdbtIme.isSelected())
						customQuery = "SELECT  * from lek order by ime";
					else if (rdbtProizvodjac.isSelected())
						customQuery = "SELECT  * from lek order by proizvodjac";
					else if (rdbtCena.isSelected())
						customQuery = "SELECT  * from lek order by cena";
					else {
						JOptionPane.showMessageDialog(btnPrikazi, "Morate selektovati izbor sortiranja!!!!");
						return;
					}

					model.setRowCount(0);

					List<List<Object>> lista = lekovi.SelectQueryList(customQuery);

					for (List<Object> obj : lista) {

						if (prikaz == 1)
							model.addRow(new Object[] { obj.get(0), obj.get(1), obj.get(2), obj.get(3), obj.get(4) });
						else {

							if (Integer.parseInt(obj.get(5).toString()) == 1) {
								model.addRow(
										new Object[] { obj.get(0), obj.get(1), obj.get(2), obj.get(3), obj.get(4) });
							}
						}
					}

					table.setModel(model);
					table.setFillsViewportHeight(true);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btnPrikazi.setBounds(10, 213, 89, 23);
		getContentPane().add(btnPrikazi);

		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOdustani.setBounds(10, 249, 89, 23);
		getContentPane().add(btnOdustani);
		contentPanel.setLayout(null);

	}
}
