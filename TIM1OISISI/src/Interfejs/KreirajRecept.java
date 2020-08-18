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

public class KreirajRecept  extends JDialog {
	private JTable table;
	private JTextField txtJMBG;
	private JTextField txtDatum;
	private JTextField txtSifra;
	private JTextField txtKolicina;
	private JTextField txtCena;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			KreirajRecept dialog = new KreirajRecept();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public KreirajRecept() {
		setTitle("KREIRAJ RECEPT");
		getContentPane().setBackground(new Color(102, 205, 170));
		setBounds(100, 100, 867, 612);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(269, 45, 489, 226);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Ime", "Proizvo\u0111a\u010D", "Ide na recept", "Cena"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnPrikai = new JButton("Dodaj u recept");
		btnPrikai.setBounds(53, 246, 141, 25);
		getContentPane().add(btnPrikai);
		
		txtJMBG = new JTextField();
		txtJMBG.setBounds(118, 50, 116, 22);
		getContentPane().add(txtJMBG);
		txtJMBG.setColumns(10);
		
		txtDatum = new JTextField();
		txtDatum.setText("");
		txtDatum.setBounds(118, 85, 116, 22);
		getContentPane().add(txtDatum);
		txtDatum.setColumns(10);
		
		txtSifra = new JTextField();
		txtSifra.setText("");
		txtSifra.setBounds(118, 120, 116, 22);
		getContentPane().add(txtSifra);
		txtSifra.setColumns(10);
		
		txtKolicina = new JTextField();
		txtKolicina.setBounds(118, 155, 116, 22);
		getContentPane().add(txtKolicina);
		txtKolicina.setColumns(10);
		
		txtCena = new JTextField();
		txtCena.setText("");
		txtCena.setBounds(118, 190, 116, 22);
		getContentPane().add(txtCena);
		txtCena.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("JMBG pacijenta");
		lblNewLabel.setBounds(12, 53, 94, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Datum");
		lblNewLabel_1.setBounds(12, 88, 56, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblifra = new JLabel("\u0160ifra");
		lblifra.setBounds(12, 123, 56, 16);
		getContentPane().add(lblifra);
		
		JLabel lblKoliina = new JLabel("Koli\u010Dina");
		lblKoliina.setBounds(12, 158, 56, 16);
		getContentPane().add(lblKoliina);
		
		JLabel lblCena = new JLabel("Cena");
		lblCena.setBounds(12, 193, 56, 16);
		getContentPane().add(lblCena);
		
		JScrollPane scrollKorpa = new JScrollPane();
		scrollKorpa.setBounds(30, 323, 503, 229);
		getContentPane().add(scrollKorpa);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Ime", "Proizvo\u0111a\u010D", "Ide na recept", "Cena", "Koli\u010Dina"
			}
		));
		scrollKorpa.setViewportView(table_1);
		
		JLabel lblKorpa = new JLabel("Sadr\u017Eaj recepta");
		lblKorpa.setBounds(30, 294, 105, 16);
		getContentPane().add(lblKorpa);
		
		JButton btnSauvajRecept = new JButton("Sa\u010Duvaj recept");
		btnSauvajRecept.setBounds(592, 506, 154, 25);
		getContentPane().add(btnSauvajRecept);
	}
}