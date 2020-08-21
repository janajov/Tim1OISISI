package Interfejs;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Model.LekBaza;
import Model.LekKlasa;

import java.util.List;
import java.util.Vector;
import java.awt.Color;

public class IzmenaLekova extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblifra;
	private JTextField txtSifra;
	private JLabel lblIme;
	private JLabel lblProizvoda;
	private JTextField txtProizvodjac;
	private JLabel lblNaRecept;
	private JTextField txtIme;
	private JTextField txtCena;
	private JComboBox<Boolean> comboRecept;
	private JLabel lblCena;
	private JButton btnIzmeni;
	
	LekKlasa lek;
	LekBaza lekovi = new LekBaza();
	 private Vector<Boolean> comboBoxItems;
	 private JTable table;
		DefaultTableModel model;
		String[] header = new String[] { "Sifra", "Ime", "Proizvodjaè", "Ide na recept" , "Cena"  };


	public static void main(String[] args) {
		try {
			IzmenaLekova dialog = new IzmenaLekova();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public IzmenaLekova() {
		setModal(true);
		setBounds(100, 100, 925, 372);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(102, 205, 170));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		lekovi.importLek();
		
		lblifra = new JLabel("\u0160ifra");
		lblifra.setBounds(20, 40, 89, 14);
		contentPanel.add(lblifra);
		
		txtSifra = new JTextField();
		txtSifra.setColumns(10);
		txtSifra.setBounds(120, 40, 106, 20);
		contentPanel.add(txtSifra);
		
		lblIme = new JLabel("Ime");
		lblIme.setBounds(20, 80, 46, 14);
		contentPanel.add(lblIme);
		
		lblProizvoda = new JLabel("Proizvo\u0111a\u010D");
		lblProizvoda.setBounds(20, 120, 76, 14);
		contentPanel.add(lblProizvoda);
		
		txtProizvodjac = new JTextField();
		txtProizvodjac.setColumns(10);
		txtProizvodjac.setBounds(120, 120, 106, 20);
		contentPanel.add(txtProizvodjac);
		
		lblNaRecept = new JLabel("Na recept");
		lblNaRecept.setBounds(20, 160, 89, 14);
		contentPanel.add(lblNaRecept);
		
		txtIme = new JTextField();
		txtIme.setColumns(10);
		txtIme.setBounds(120, 80, 106, 20);
		contentPanel.add(txtIme);
		
		txtCena = new JTextField();
		txtCena.setColumns(10);
		txtCena.setBounds(120, 200, 106, 20);
		contentPanel.add(txtCena);
		
		
		comboBoxItems = new Vector<Boolean>();
        comboBoxItems.add(Boolean.TRUE);
        comboBoxItems.add(Boolean.FALSE);
        comboRecept = new JComboBox<Boolean>(comboBoxItems);

		comboRecept.setSelectedIndex(0);
		comboRecept.setBounds(120, 160, 106, 20);
		contentPanel.add(comboRecept);
		
		lblCena = new JLabel("Cena");
		lblCena.setBounds(20, 200, 89, 14);
		contentPanel.add(lblCena);
		
		btnIzmeni = new JButton("Izmeni");
		btnIzmeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() <0){
					JOptionPane
					.showMessageDialog(null,
							"Morate selektovati lek u tabeli koji zelite da menjate!");
					return;
				}
					
				lek = new LekKlasa();
				try {

					String sifra = txtSifra.getText().trim();
					
					
					lek.setSifra(sifra);
					
					lek.setIme(txtIme.getText());
					
					lek.setProizvodjac(txtProizvodjac.getText());
				
					if((Boolean)comboRecept.getSelectedItem() == Boolean.TRUE)lek.setRecept(true);
					else lek.setRecept(false);
				
				
				    lek.setCena(Float.parseFloat(txtCena.getText()));
				  
					lekovi.izmenaLeka(lek);
					JOptionPane
					.showMessageDialog(null,
							"Lek je izmenjen!");
					
					List<List<Object>> lista = lekovi
							.SelectQueryList("SELECT  * from lek order by ime");
					model = new DefaultTableModel(new Object[][] {}, header);
					for (List<Object> obj : lista) {
						model.addRow(new Object[] { obj.get(0), obj.get(1),
								obj.get(2),
								obj.get(3) ,
								obj.get(4)  });

					}

					table.setModel(model);

					/*
					 * textIme.setText("" + radnici.getIme());
					 * textPrezime.setText("" + radnici.getPrezime());
					 */

					// SetFieldsToDefault();

				} catch (Exception ex) {
					// SetFieldsToDefault();
				}

			}
		});
		btnIzmeni.setBounds(660, 277, 89, 23);
		contentPanel.add(btnIzmeni);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(273, 25, 616, 214);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		
		List<List<Object>> lista = lekovi
				.SelectQueryList("SELECT  * from lek order by ime");
		model = new DefaultTableModel(new Object[][] {}, header);
		for (List<Object> obj : lista) {
			model.addRow(new Object[] { obj.get(0), obj.get(1),
					obj.get(2),
					obj.get(3) ,
					obj.get(4)  });

		}

		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOdustani.setBounds(800, 277, 89, 23);
		contentPanel.add(btnOdustani);
		
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						
						tableSelect(event);
					}
				});
	}
	private void tableSelect(ListSelectionEvent lse) {
		if (table.getSelectedRow() <0)
			return;
			
		
		try {
			
			Object polje = model.getValueAt(table.getSelectedRow(), 0);
			//String tableID = Integer.parseInt(polje.toString());
			LekKlasa rec = lekovi.GetLek(polje.toString());
			
			txtSifra.setText(rec.getSifra() );
			txtIme.setText(rec.getIme());
			txtProizvodjac.setText(rec.getProizvodjac());
			txtIme.setText(rec.getIme());
			if(rec.getRecept() == Boolean.TRUE)comboRecept.setSelectedItem(true);
			else comboRecept.setSelectedItem(false);
			
			
			txtCena.setText(Float.toString(rec.getCena()));
			
		} catch (Exception e) {
			System.out.println("Greska!!!!!"+e.getMessage());
		}
	}
}