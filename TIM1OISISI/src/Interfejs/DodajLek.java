package Interfejs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.LekBaza;
import Model.LekKlasa;

public class DodajLek  extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNazivLeka;
	private JTextField txtSifra;
	private JTextField txtCena;
	private JTextField txtProizvodjac;
	private JLabel lblSifraLeka;
	private JLabel lblProizvodjacLeka;
	private JLabel lblNaziv;
	private JLabel lblCena;
	private JLabel lblIzdavanjeNaRecept;
	private JComboBox comboRecept;
	private JButton btnDodaj;
	private JButton btnOdustani;
	
	LekKlasa lek;
	LekBaza lekovi = new LekBaza();
	
	private Vector<Boolean> comboBoxItems;
	private JTable table;
	DefaultTableModel model;
	String[] header = new String[] { "Sifra", "Ime", "Proizvodjaè", "Ide na recept", "Cena" };
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DodajLek dialog = new DodajLek();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DodajLek() {
		setTitle("Dodaj lek");
		setBounds(100, 100, 764, 364);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(102, 205, 170));
		contentPanel.setBounds(0, 0, 746, 279);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setModal(true);
		
		lekovi.importLek();
		
		
		lblSifraLeka = new JLabel("\u0160ifra:");
		lblSifraLeka.setBounds(24, 40, 94, 14);
		contentPanel.add(lblSifraLeka);
		
		txtNazivLeka = new JTextField();
		txtNazivLeka.setBounds(147, 63, 116, 25);
		contentPanel.add(txtNazivLeka);
		txtNazivLeka.setColumns(10);
		
		lblProizvodjacLeka = new JLabel("Proizvo\u0111a\u010D:");
		lblProizvodjacLeka.setBounds(24, 96, 88, 14);
		contentPanel.add(lblProizvodjacLeka);
		
		lblNaziv = new JLabel("Naziv:");
		lblNaziv.setBounds(24, 67, 56, 16);
		contentPanel.add(lblNaziv);
		
		txtSifra = new JTextField();
		txtSifra.setBounds(147, 35, 116, 25);
		contentPanel.add(txtSifra);
		txtSifra.setColumns(10);
		
		txtCena = new JTextField();
		txtCena.setBounds(147, 149, 116, 22);
		contentPanel.add(txtCena);
		txtCena.setColumns(10);
		
		lblCena = new JLabel("Cena:");
		lblCena.setBounds(24, 152, 56, 16);
		contentPanel.add(lblCena);	
		
		
		lblIzdavanjeNaRecept = new JLabel("Izdavanje na recept:");
		lblIzdavanjeNaRecept.setBounds(24, 123, 138, 16);
		contentPanel.add(lblIzdavanjeNaRecept);
		
		txtProizvodjac = new JTextField();
		txtProizvodjac.setBounds(147, 92, 116, 22);
		contentPanel.add(txtProizvodjac);
		txtProizvodjac.setColumns(10);
		
		comboBoxItems = new Vector<Boolean>();
		comboBoxItems.add(Boolean.TRUE);
		comboBoxItems.add(Boolean.FALSE);
		comboRecept = new JComboBox(comboBoxItems);
		
		comboRecept.setSelectedIndex(0);		
		comboRecept.setBounds(147, 120, 116, 23);
		contentPanel.add(comboRecept);
		
			btnDodaj = new JButton("Dodaj");
			btnDodaj.setBounds(102, 222, 79, 25);
			
			btnDodaj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					lek = new LekKlasa();
					try {

						String sifra = txtSifra.getText().trim();

						if (lekovi.PostojiLek(sifra)) {

							JOptionPane.showMessageDialog(null, "Lek sa unetom sifrom   postoji!");
							return;
						}
						//String ime = txtNazivLeka.getText();

						lek.setSifra(sifra);
						lek.setIme(txtNazivLeka.getText());
						lek.setProizvodjac(txtProizvodjac.getText());
						if ((Boolean) comboRecept.getSelectedItem() == Boolean.TRUE)
							lek.setRecept(true);
						else
							lek.setRecept(false);
						lek.setRecept((Boolean) comboRecept.getSelectedItem());
						lek.setCena(Float.parseFloat(txtCena.getText()));
						lekovi.dodajLek(lek);
						JOptionPane.showMessageDialog(null, "Lek je dodat!");

						List<List<Object>> lista = lekovi.SelectQueryList("SELECT  * from lek order by ime");
						model = new DefaultTableModel(new Object[][] {}, header);
						for (List<Object> obj : lista) {
							model.addRow(new Object[] { obj.get(0), obj.get(1), obj.get(2), obj.get(3), obj.get(4) });

						}

						table.setModel(model);

					} catch (Exception ex) {
						// SetFieldsToDefault();
					}

				}
			});
			
			contentPanel.add(btnDodaj);		
			
		
		
		
			
			btnOdustani = new JButton("Odustani");
			btnOdustani.setBounds(193, 222, 101, 25);
			btnOdustani.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			contentPanel.add(btnOdustani);
	
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(306, 40, 428, 215);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		
		List<List<Object>> lista = lekovi.SelectQueryList("SELECT  * from lek order by ime");
		model = new DefaultTableModel(new Object[][] {}, header);
		for (List<Object> obj : lista) {
			model.addRow(new Object[] { obj.get(0), obj.get(1), obj.get(2), obj.get(3), obj.get(4) });

		}
		
		table.setModel(model);
		scrollPane.setViewportView(table);		
		table.getColumnModel().getColumn(4).setPreferredWidth(93);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
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
			txtNazivLeka.setText(rec.getIme());
			txtProizvodjac.setText(rec.getProizvodjac());
			txtNazivLeka.setText(rec.getIme());
			if (rec.getRecept() == Boolean.TRUE)
				comboRecept.setSelectedItem(true);
			else
				comboRecept.setSelectedItem(false);

			txtCena.setText(Float.toString(rec.getCena()));

		} catch (Exception e) {
			System.out.println("Greska!!!!!" + e.getMessage());
		}
	}
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(102, 205, 170));
			buttonPane.setBounds(0, 276, 746, 41);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
		}
	}
