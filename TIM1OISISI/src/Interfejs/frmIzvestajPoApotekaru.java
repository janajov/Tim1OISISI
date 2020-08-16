package Interfejs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class frmIzvestajPoApotekaru extends JDialog {

	private final JPanel contentPanelIzvestajPoApotekaru = new JPanel();
	private JTextField textField;
	private JTable tableIzvestajPoApotekaru;

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
		setTitle("Izve\u0161taj svih prodatih lekova izabranog apotekara");
		setBounds(100, 100, 821, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanelIzvestajPoApotekaru.setBackground(new Color(102, 205, 170));
		contentPanelIzvestajPoApotekaru.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanelIzvestajPoApotekaru, BorderLayout.CENTER);
		contentPanelIzvestajPoApotekaru.setLayout(null);
		
		JScrollPane scrollPaneIzvestajPoApotekaru = new JScrollPane();
		scrollPaneIzvestajPoApotekaru.setBounds(10, 11, 785, 204);
		contentPanelIzvestajPoApotekaru.add(scrollPaneIzvestajPoApotekaru);
		
		tableIzvestajPoApotekaru = new JTable();
		tableIzvestajPoApotekaru.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sifra", "Ime", "Proizvodjac", "Ide na recept", "Cena jednog leka", "Prodata kolicina", "Ukupna zarada"
			}
		));
		tableIzvestajPoApotekaru.getColumnModel().getColumn(2).setPreferredWidth(89);
		tableIzvestajPoApotekaru.getColumnModel().getColumn(3).setPreferredWidth(83);
		tableIzvestajPoApotekaru.getColumnModel().getColumn(4).setPreferredWidth(102);
		tableIzvestajPoApotekaru.getColumnModel().getColumn(5).setPreferredWidth(96);
		tableIzvestajPoApotekaru.getColumnModel().getColumn(6).setPreferredWidth(105);
		scrollPaneIzvestajPoApotekaru.setViewportView(tableIzvestajPoApotekaru);
		
		JLabel lblImeApotekara = new JLabel("Ime apotekara:");
		lblImeApotekara.setBounds(45, 316, 124, 14);
		contentPanelIzvestajPoApotekaru.add(lblImeApotekara);
		
		textField = new JTextField();
		textField.setBounds(141, 313, 165, 20);
		contentPanelIzvestajPoApotekaru.add(textField);
		textField.setColumns(10);
		
		JLabel lblUkupnaZaradaApoteke = new JLabel("Ukupna zarada apoteke:");
		lblUkupnaZaradaApoteke.setBounds(517, 226, 124, 14);
		contentPanelIzvestajPoApotekaru.add(lblUkupnaZaradaApoteke);
		
		JLabel labelCifraZarade = new JLabel("---------------");
		labelCifraZarade.setBounds(708, 226, 74, 14);
		contentPanelIzvestajPoApotekaru.add(labelCifraZarade);
		
		JButton btnPrikazi = new JButton("Prikazi");
		btnPrikazi.setBounds(576, 312, 89, 23);
		contentPanelIzvestajPoApotekaru.add(btnPrikazi);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(706, 312, 89, 23);
		contentPanelIzvestajPoApotekaru.add(btnOdustani);
	}
}
