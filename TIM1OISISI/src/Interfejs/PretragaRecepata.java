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
	private JTextField txtSifra;

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
		setTitle("PRETRAGA LEKOVA");
		getContentPane().setBackground(new Color(102, 205, 170));
		setBounds(100, 100, 839, 455);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 205, 170));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pretraga", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(25, 35, 161, 184);
		getContentPane().add(panel);
		
		JRadioButton rdbtnPoi = new JRadioButton("po \u0161ifri");
		rdbtnPoi.setBackground(new Color(102, 205, 170));
		panel.add(rdbtnPoi);
		
		JRadioButton rdbtnPoLekaru = new JRadioButton("po lekaru");
		rdbtnPoLekaru.setBackground(new Color(102, 205, 170));
		panel.add(rdbtnPoLekaru);
		
		JRadioButton rdbtnPoDatumu = new JRadioButton("po JMBG pacijenta");
		rdbtnPoDatumu.setBackground(new Color(102, 205, 170));
		panel.add(rdbtnPoDatumu);
		
		JRadioButton rdbtnPoJednomLeku = new JRadioButton("po jednom leku");
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
		
		JButton btnPrikai = new JButton("Prika\u017Ei");
		btnPrikai.setBounds(519, 327, 97, 25);
		getContentPane().add(btnPrikai);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(628, 327, 97, 25);
		getContentPane().add(btnOdustani);
		
		JLabel lblUnesiifruZa = new JLabel("Unesi \u0161ifru za pretragu:");
		lblUnesiifruZa.setBounds(38, 249, 161, 16);
		getContentPane().add(lblUnesiifruZa);
		
		txtSifra = new JTextField();
		txtSifra.setBounds(38, 278, 116, 22);
		getContentPane().add(txtSifra);
		txtSifra.setColumns(10);
	}
}