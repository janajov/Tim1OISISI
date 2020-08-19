package Interfejs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;

public class PretragaLekova  extends JDialog {

	private final JPanel contentPanelLogovanje = new JPanel();
	private JTextField txtSifra;
	private JTable table;
	private JTextField txtIme;
	private JTextField txtCena;
	private JTextField txtProizvodjac;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PretragaLekova dialog = new PretragaLekova();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PretragaLekova() {
		setBounds(100, 100, 764, 364);
		getContentPane().setLayout(null);
		contentPanelLogovanje.setBackground(new Color(102, 205, 170));
		contentPanelLogovanje.setBounds(0, 0, 746, 279);
		contentPanelLogovanje.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanelLogovanje);
		contentPanelLogovanje.setLayout(null);
		
		JPanel panelSifra = new JPanel();
		panelSifra.setBackground(new Color(102, 205, 170));
		panelSifra.setBounds(32, 201, 174, 78);
		contentPanelLogovanje.add(panelSifra);
		
		JLabel lblUnesiSifru = new JLabel("Unesi \u0161ifru za pretragu:");
		panelSifra.add(lblUnesiSifru);
		
		txtSifra = new JTextField();
		panelSifra.add(txtSifra);
		txtSifra.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(230, 13, 486, 233);
		contentPanelLogovanje.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(102, 205, 170));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ide na recept", "\u0160ifra", "Ime", "Cena", "Proizvo\u0111a\u010D"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(88);
		scrollPane.setViewportView(table);
		
		JPanel panelIme = new JPanel();
		panelIme.setBackground(new Color(102, 205, 170));
		panelIme.setBounds(32, 201, 174, 78);
		contentPanelLogovanje.add(panelIme);
		
		JLabel labelIme = new JLabel("Unesi ime za pretragu:");
		panelIme.add(labelIme);
		
		txtIme = new JTextField();
		panelIme.add(txtIme);
		txtIme.setColumns(10);
		
		JPanel panelCena = new JPanel();
		panelCena.setBackground(new Color(102, 205, 170));
		panelCena.setBounds(32, 201, 174, 78);
		contentPanelLogovanje.add(panelCena);
		
		JLabel lblCena = new JLabel("Unesi opseg cena");
		panelCena.add(lblCena);
		
		txtCena = new JTextField();
		txtCena.setColumns(10);
		panelCena.add(txtCena);
		
		JPanel panelProizvodjac = new JPanel();
		panelProizvodjac.setBackground(new Color(102, 205, 170));
		panelProizvodjac.setBounds(32, 201, 174, 78);
		contentPanelLogovanje.add(panelProizvodjac);
		
		JLabel lblUnesiProizvoaa = new JLabel("Unesi proizvo\u0111a\u010Da");
		panelProizvodjac.add(lblUnesiProizvoaa);
		
		txtProizvodjac = new JTextField();
		txtProizvodjac.setColumns(10);
		panelProizvodjac.add(txtProizvodjac);
		
		JPanel panelPretraga = new JPanel();
		panelPretraga.setBorder(new TitledBorder(null, "Pretraga", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPretraga.setBackground(new Color(102, 205, 170));
		panelPretraga.setBounds(28, 13, 143, 171);
		contentPanelLogovanje.add(panelPretraga);
		panelPretraga.setLayout(null);
		
		JRadioButton rdbtnSifra = new JRadioButton("po \u0161ifri");
		rdbtnSifra.setBounds(8, 22, 67, 25);
		panelPretraga.add(rdbtnSifra);
		rdbtnSifra.setBackground(new Color(102, 205, 170));
		buttonGroup.add(rdbtnSifra);
		
		JRadioButton rdbtnIme = new JRadioButton("po imenu");
		rdbtnIme.setBounds(8, 53, 81, 25);
		panelPretraga.add(rdbtnIme);
		rdbtnIme.setBackground(new Color(102, 205, 170));
		buttonGroup.add(rdbtnIme);
		
		JRadioButton rdbtnProizvodjac = new JRadioButton("po proizvo\u0111a\u010Du");
		rdbtnProizvodjac.setBounds(8, 83, 115, 25);
		panelPretraga.add(rdbtnProizvodjac);
		rdbtnProizvodjac.setBackground(new Color(102, 205, 170));
		buttonGroup.add(rdbtnProizvodjac);
		
		JRadioButton rdbtnCena = new JRadioButton("po opsegu cena");
		rdbtnCena.setBounds(8, 112, 119, 25);
		panelPretraga.add(rdbtnCena);
		rdbtnCena.setBackground(new Color(102, 205, 170));
		buttonGroup.add(rdbtnCena);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(102, 205, 170));
			buttonPane.setBounds(0, 276, 746, 41);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			
			JButton btnPrikazi = new JButton("Prika\u017Ei");
			buttonPane.add(btnPrikazi);
			
			JButton btnOdustani = new JButton("Odustani");
			buttonPane.add(btnOdustani);
		}
	}
}