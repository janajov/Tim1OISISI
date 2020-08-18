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

public class PrikazSvihRecepata  extends JDialog {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PrikazSvihRecepata dialog = new PrikazSvihRecepata();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PrikazSvihRecepata() {
		getContentPane().setBackground(new Color(102, 205, 170));
		setBounds(100, 100, 867, 612);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 205, 170));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sortiranje", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(25, 35, 144, 158);
		getContentPane().add(panel);
		
		JRadioButton rdbtnPoi = new JRadioButton("po \u0161ifri");
		rdbtnPoi.setBackground(new Color(102, 205, 170));
		panel.add(rdbtnPoi);
		
		JRadioButton rdbtnPoLekaru = new JRadioButton("po lekaru");
		rdbtnPoLekaru.setBackground(new Color(102, 205, 170));
		panel.add(rdbtnPoLekaru);
		
		JRadioButton rdbtnPoDatumu = new JRadioButton("po datumu");
		rdbtnPoDatumu.setBackground(new Color(102, 205, 170));
		panel.add(rdbtnPoDatumu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(191, 45, 567, 240);
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
		btnPrikai.setBounds(53, 246, 97, 25);
		getContentPane().add(btnPrikai);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(53, 300, 97, 25);
		getContentPane().add(btnOdustani);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 205, 170));
		panel_1.setBorder(new TitledBorder(null, "Spisak lekova", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(191, 334, 567, 218);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 24, 543, 181);
		panel_1.add(scrollPane_1);
	}
}