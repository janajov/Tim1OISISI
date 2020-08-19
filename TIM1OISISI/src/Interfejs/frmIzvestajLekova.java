package Interfejs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class frmIzvestajLekova extends JDialog {

	private final JPanel contentPanelIzvestajLekova = new JPanel();
	private JTable tableIzvestajLekova;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmIzvestajLekova dialog = new frmIzvestajLekova();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmIzvestajLekova() {
		setTitle("Izvestaj svih prodatih lekova");
		setBounds(100, 100, 821, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanelIzvestajLekova.setBackground(new Color(102, 205, 170));
		contentPanelIzvestajLekova.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanelIzvestajLekova, BorderLayout.CENTER);
		contentPanelIzvestajLekova.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 785, 280);
			contentPanelIzvestajLekova.add(scrollPane);
			
			tableIzvestajLekova = new JTable();
			tableIzvestajLekova.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Sifra", "Ime", "Proizvodjac ", "Ide na recept", "Cena jednog leka", "Prodata kolicina", "Ukupna zarada"
				}
			));
			scrollPane.setViewportView(tableIzvestajLekova);
		}
		{
			JButton btnPrikazi = new JButton("Prikazi");
			btnPrikazi.setBounds(23, 348, 89, 23);
			contentPanelIzvestajLekova.add(btnPrikazi);
		}
		{
			JButton btnOdustani = new JButton("Odustani");
			btnOdustani.setBounds(227, 348, 89, 23);
			contentPanelIzvestajLekova.add(btnOdustani);
		}
		{
			JLabel lblUkupnaZaradaApoteke = new JLabel("Ukupna zarada apoteke:");
			lblUkupnaZaradaApoteke.setBounds(534, 302, 121, 14);
			contentPanelIzvestajLekova.add(lblUkupnaZaradaApoteke);
		}
		{
			JLabel lblSaldo = new JLabel("---------------");
			lblSaldo.setBounds(711, 302, 46, 14);
			contentPanelIzvestajLekova.add(lblSaldo);
		}
	}
}
