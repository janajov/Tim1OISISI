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

public class frmIzvestajPoProizvodjacu extends JDialog {

	private final JPanel contentPanelIzvestajPoProizvodjacu = new JPanel();
	private JTextField textFieldImeProizvodjaca;
	private JTable table;
	private JTable tableIzvestajPoProizvodjacu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmIzvestajPoProizvodjacu dialog = new frmIzvestajPoProizvodjacu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmIzvestajPoProizvodjacu() {
		setTitle("Izve\u0161taj svih prodatih lekova izabranog proizvo\u0111a\u010Da");
		setBounds(100, 100, 821, 421);
		getContentPane().setLayout(new BorderLayout());
		contentPanelIzvestajPoProizvodjacu.setBackground(new Color(102, 205, 170));
		contentPanelIzvestajPoProizvodjacu.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanelIzvestajPoProizvodjacu, BorderLayout.CENTER);
		contentPanelIzvestajPoProizvodjacu.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 785, 201);
		contentPanelIzvestajPoProizvodjacu.add(scrollPane);
		
		tableIzvestajPoProizvodjacu = new JTable();
		tableIzvestajPoProizvodjacu.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sifra", "Ime", "Proizvodjac", "Ide na recept", "Cena jednog leka", "Prodata kolicina", "Ukupna zarada"
			}
		));
		scrollPane.setViewportView(tableIzvestajPoProizvodjacu);
		
		
		
		
		
		JLabel lblImeProizvodjaca = new JLabel("Ime proizvodjaca:");
		lblImeProizvodjaca.setBounds(10, 332, 118, 14);
		contentPanelIzvestajPoProizvodjacu.add(lblImeProizvodjaca);
		
		textFieldImeProizvodjaca = new JTextField();
		textFieldImeProizvodjaca.setBounds(134, 329, 151, 20);
		contentPanelIzvestajPoProizvodjacu.add(textFieldImeProizvodjaca);
		textFieldImeProizvodjaca.setColumns(10);
		
		JButton btnPrikazi = new JButton("Prikazi");
		btnPrikazi.setBounds(542, 328, 89, 23);
		contentPanelIzvestajPoProizvodjacu.add(btnPrikazi);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(706, 328, 89, 23);
		contentPanelIzvestajPoProizvodjacu.add(btnOdustani);
		
		JLabel lblUkupnaZarada = new JLabel("Ukupna zarada apoteke:");
		lblUkupnaZarada.setBounds(542, 223, 139, 14);
		contentPanelIzvestajPoProizvodjacu.add(lblUkupnaZarada);
		
		JLabel lblSaldo = new JLabel("--------------");
		lblSaldo.setBounds(719, 223, 76, 14);
		contentPanelIzvestajPoProizvodjacu.add(lblSaldo);
	}
}
