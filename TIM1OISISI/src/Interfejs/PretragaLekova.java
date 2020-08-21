package Interfejs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Model.LekBaza;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class PretragaLekova extends JDialog {
	private JTable table;
	private final ButtonGroup buttonPretraga = new ButtonGroup();
	DefaultTableModel model;

	LekBaza lekovi = new LekBaza();
	private JTextField txtSifraUpit;

	JPanel panelSifra;
	String customQuery = "";

	JRadioButton rdbtnSifra;
	JRadioButton rdbtnIme;
	JRadioButton rdbtnProizvodjac;
	JRadioButton rdbtnCena;
	private JPanel panelIme;
	private JTextField txtImePretraga;
	private JLabel lblIme;
	private JPanel panelProizvodjac;
	private JLabel lblProizvodjac;
	private JTextField txtProizvodjac;
	private JPanel panelCena;
	private JLabel lblCena;
	private JTextField txtC1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtC2;
	private JButton btnOdustani;
	int prikaz;

	
	/*public static void main(String[] args) {
		try {
			PretragaLekova dialog = new PretragaLekova();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/

	public PretragaLekova(int prikaz) {
		getContentPane().setBackground(new Color(102, 205, 170));
		setModal(true);
		setBounds(100, 100, 891, 388);
		getContentPane().setLayout(null);
		this.prikaz = prikaz;

		JPanel panelPretraga = new JPanel();
		panelPretraga.setBackground(new Color(102, 205, 170));

		panelPretraga.setLayout(null);
		panelPretraga.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Pretraga",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelPretraga.setBounds(10, 33, 181, 161);
		getContentPane().add(panelPretraga);

		rdbtnSifra = new JRadioButton("po \u0161ifri");
		rdbtnSifra.setBackground(new Color(102, 205, 170));
		rdbtnSifra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnSifra.isSelected()) {

					panelSifra.setVisible(true);
					panelIme.setVisible(false);

					panelProizvodjac.setVisible(false);
					panelCena.setVisible(false);

				}
			}
		});

		buttonPretraga.add(rdbtnSifra);
		rdbtnSifra.setBounds(0, 31, 109, 23);
		panelPretraga.add(rdbtnSifra);

		rdbtnIme = new JRadioButton("po imenu");
		rdbtnIme.setBackground(new Color(102, 205, 170));
		rdbtnIme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnIme.isSelected()) {

					panelIme.setVisible(true);
					panelSifra.setVisible(false);
					panelProizvodjac.setVisible(false);
					panelCena.setVisible(false);
				}
			}
		});
		buttonPretraga.add(rdbtnIme);
		rdbtnIme.setBounds(0, 57, 109, 23);
		panelPretraga.add(rdbtnIme);

		rdbtnProizvodjac = new JRadioButton("po proizvo\u0111a\u010Du");
		rdbtnProizvodjac.setBackground(new Color(102, 205, 170));
		rdbtnProizvodjac.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnProizvodjac.isSelected()) {

					panelIme.setVisible(false);
					panelSifra.setVisible(false);
					panelProizvodjac.setVisible(true);
					panelCena.setVisible(false);
				}
			}
		});
		buttonPretraga.add(rdbtnProizvodjac);
		rdbtnProizvodjac.setBounds(0, 83, 148, 23);
		panelPretraga.add(rdbtnProizvodjac);

		rdbtnCena = new JRadioButton("po opsegu cene");
		rdbtnCena.setBackground(new Color(102, 205, 170));
		rdbtnCena.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnCena.isSelected()) {

					panelIme.setVisible(false);
					panelSifra.setVisible(false);
					panelProizvodjac.setVisible(false);
					panelCena.setVisible(true);
				}
			}
		});
		buttonPretraga.add(rdbtnCena);
		rdbtnCena.setBounds(0, 109, 148, 23);
		panelPretraga.add(rdbtnCena);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(232, 33, 582, 214);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		String[] header = new String[] { "Sifra", "Ime", "Proizvodjaè",
				"Ide na recept", "Cena" };

		model = new DefaultTableModel(new Object[][] {}, header);

		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sifra", "Ime", "Proizvodjac", "Ide na recept", "Cena"
			}
		));

		JButton btnPrikazi = new JButton("Prika\u017Ei");
		btnPrikazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (rdbtnSifra.isSelected() || rdbtnIme.isSelected()
							|| rdbtnProizvodjac.isSelected()
							|| rdbtnCena.isSelected()) {
						model.setRowCount(0);
						if (rdbtnSifra.isSelected()) {
							if (!txtSifraUpit.getText().isEmpty()) {
								String s = txtSifraUpit.getText().trim();

								customQuery = "SELECT  * from lek where sifra =  "
										+ s;
							} else {
								JOptionPane
										.showMessageDialog(panelPretraga,
												"Morate uneti vrednost za pretragu!!!!");
								return;
							}
						} else if (rdbtnIme.isSelected()) {

							if (!txtImePretraga.getText().isEmpty()) {

								customQuery = "SELECT  * from lek where ime LIKE  '%"
										+ txtImePretraga.getText() + "%'";
							} else {
								JOptionPane
										.showMessageDialog(panelPretraga,
												"Morate uneti vrednost za pretragu!!!!");
								return;
							}
						} else if (rdbtnProizvodjac.isSelected()) {

							if (!txtProizvodjac.getText().isEmpty()) {

								customQuery = "SELECT  * from lek where proizvodjac LIKE  '%"
										+ txtProizvodjac.getText() + "%'";
							} else {
								JOptionPane
										.showMessageDialog(panelPretraga,
												"Morate uneti vrednost za pretragu!!!!");
								return;
							}
						} else if (rdbtnCena.isSelected()) {

							if ((txtC1.getText().isEmpty() || txtC2.getText()
									.isEmpty())) {
								JOptionPane
										.showMessageDialog(panelPretraga,
												"Morate uneti vrednost za pretragu!!!!");
								return;
							} else {
								customQuery = "SELECT  * from lek where cena >= "
										+ txtC1.getText()
										+ " AND cena <= "
										+ txtC2.getText();

							}
						}

						List<List<Object>> lista = lekovi
								.SelectQueryList(customQuery);

						for (List<Object> obj : lista) {
							if(prikaz==1)
								model.addRow(new Object[] { obj.get(0), obj.get(1),
										obj.get(2),
										obj.get(3) ,
										obj.get(4)  });
								else {
									
								if( Integer.parseInt(obj.get(5).toString()) == 1)
								{
									model.addRow(new Object[] { obj.get(0), obj.get(1),
										obj.get(2),
										obj.get(3) ,
										obj.get(4)  });}
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
		btnPrikazi.setBounds(557, 280, 89, 23);
		getContentPane().add(btnPrikazi);

		panelSifra = new JPanel();
		panelSifra.setBackground(new Color(102, 205, 170));
		panelSifra.setBounds(10, 241, 181, 87);
		getContentPane().add(panelSifra);
		panelSifra.setLayout(null);

		JLabel lblSifra = new JLabel("Unesi \u0161ifru za pretragu:");
		lblSifra.setBounds(10, 11, 136, 14);
		panelSifra.add(lblSifra);

		txtSifraUpit = new JTextField();

		txtSifraUpit.setColumns(10);
		txtSifraUpit.setBounds(32, 36, 114, 20);
		panelSifra.add(txtSifraUpit);

		panelIme = new JPanel();
		panelIme.setBackground(new Color(102, 205, 170));
		panelIme.setBounds(10, 241, 181, 87);
		getContentPane().add(panelIme);
		panelIme.setLayout(null);

		lblIme = new JLabel("Unesi ime za pretragu:");
		lblIme.setBounds(10, 11, 123, 14);
		panelIme.add(lblIme);

		txtImePretraga = new JTextField();
		txtImePretraga.setBounds(24, 36, 119, 20);
		panelIme.add(txtImePretraga);
		txtImePretraga.setColumns(10);

		panelProizvodjac = new JPanel();
		panelProizvodjac.setBackground(new Color(102, 205, 170));
		panelProizvodjac.setBounds(10, 241, 181, 87);
		getContentPane().add(panelProizvodjac);
		panelProizvodjac.setLayout(null);

		lblProizvodjac = new JLabel("Unesi proizvo\u0111a\u010Da za pretragu:");
		lblProizvodjac.setBounds(10, 11, 161, 14);
		panelProizvodjac.add(lblProizvodjac);

		txtProizvodjac = new JTextField();
		txtProizvodjac.setColumns(10);
		txtProizvodjac.setBounds(24, 36, 119, 20);
		panelProizvodjac.add(txtProizvodjac);

		panelCena = new JPanel();
		panelCena.setBounds(10, 241, 181, 87);
		getContentPane().add(panelCena);
		panelCena.setLayout(null);

		lblCena = new JLabel("Unesi opseg cena:");
		lblCena.setBounds(10, 11, 129, 14);
		panelCena.add(lblCena);

		txtC1 = new JTextField();
		txtC1.setBounds(85, 36, 86, 20);
		panelCena.add(txtC1);
		txtC1.setColumns(10);

		lblNewLabel = new JLabel("Od:");
		lblNewLabel.setBounds(10, 39, 46, 14);
		panelCena.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Do:");
		lblNewLabel_1.setBounds(10, 64, 46, 14);
		panelCena.add(lblNewLabel_1);

		txtC2 = new JTextField();
		txtC2.setBounds(85, 61, 86, 20);
		panelCena.add(txtC2);
		txtC2.setColumns(10);
		
		btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOdustani.setBounds(700, 280, 89, 23);
		getContentPane().add(btnOdustani);
		panelIme.setVisible(false);
		panelSifra.setVisible(false);
		panelProizvodjac.setVisible(false);
		panelCena.setVisible(false);
	}
}
