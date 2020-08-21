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
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class PretragaRecepata  extends JDialog {
	private JTable table;
	private JTextField textLek;
	private JTextField txtJMBG;
	private JTextField txtSifra;
	private JTextField textField;

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
		setTitle("PRETRAGA RECEPATA");
		getContentPane().setBackground(new Color(102, 205, 170));
		setBounds(100, 100, 839, 455);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 205, 170));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pretraga", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(25, 35, 161, 184);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnPoSifri = new JRadioButton("po \u0161ifri");
		rdbtnPoSifri.setBounds(0, 27, 67, 25);
		rdbtnPoSifri.setBackground(new Color(102, 205, 170));
		panel.add(rdbtnPoSifri);
		
		JRadioButton rdbtnPoLekaru = new JRadioButton("po lekaru");
		rdbtnPoLekaru.setBounds(0, 53, 81, 25);
		rdbtnPoLekaru.setBackground(new Color(102, 205, 170));
		panel.add(rdbtnPoLekaru);
		
		JRadioButton rdbtnPoDatumu = new JRadioButton("po JMBG pacijenta");
		rdbtnPoDatumu.setBounds(0, 83, 133, 25);
		rdbtnPoDatumu.setBackground(new Color(102, 205, 170));
		panel.add(rdbtnPoDatumu);
		
		JRadioButton rdbtnPoJednomLeku = new JRadioButton("po jednom leku");
		rdbtnPoJednomLeku.setBounds(0, 113, 117, 25);
		rdbtnPoJednomLeku.setBackground(new Color(102, 205, 170));
		panel.add(rdbtnPoJednomLeku);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(241, 45, 517, 243);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Lekar", "Pacijent", "Datum", "Ukupna cena"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnPrikazi = new JButton("Prika\u017Ei");
		btnPrikazi.setBounds(519, 327, 97, 25);
		getContentPane().add(btnPrikazi);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(628, 327, 97, 25);
		getContentPane().add(btnOdustani);
		
		JPanel panelSifra = new JPanel();
		panelSifra.setBounds(25, 273, 182, 94);
		getContentPane().add(panelSifra);
		panelSifra.setLayout(null);
		
		txtSifra = new JTextField();
		txtSifra.setColumns(10);
		txtSifra.setBounds(22, 49, 116, 22);
		panelSifra.add(txtSifra);
		
		JLabel lblIme = new JLabel("Unesi sifru za pretragu:");
		lblIme.setBounds(12, 20, 161, 16);
		panelSifra.add(lblIme);
		
		JPanel panelLekar = new JPanel();
		panelLekar.setLayout(null);
		panelLekar.setBounds(25, 273, 182, 94);
		getContentPane().add(panelLekar);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(22, 49, 116, 22);
		panelLekar.add(textField);
		
		JLabel label = new JLabel("Unesi ime lekara za pretragu:");
		label.setBounds(12, 20, 161, 16);
		panelLekar.add(label);
		
		JPanel panelLek = new JPanel();
		panelLek.setBounds(25, 273, 182, 94);
		getContentPane().add(panelLek);
		panelLek.setLayout(null);
		
		textLek = new JTextField();
		textLek.setBounds(22, 49, 116, 22);
		panelLek.add(textLek);
		textLek.setColumns(10);
		
		JLabel lblLek = new JLabel("Unesi ime leka za pretragu:");
		lblLek.setBounds(12, 20, 161, 16);
		panelLek.add(lblLek);
		
		JPanel panelJMBG = new JPanel();
		panelJMBG.setBounds(25, 273, 182, 94);
		getContentPane().add(panelJMBG);
		panelJMBG.setLayout(null);
		
		txtJMBG = new JTextField();
		txtJMBG.setColumns(10);
		txtJMBG.setBounds(22, 49, 116, 22);
		panelJMBG.add(txtJMBG);
		
		JLabel lblJMBG = new JLabel("Unesi JMBG za pretragu:");
		lblJMBG.setBounds(12, 20, 161, 16);
		panelJMBG.add(lblJMBG);
	}
}