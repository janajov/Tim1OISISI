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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class SlobodnaProdaja  extends JDialog {
	private JTable table;
	private JTextField txtJMBG;
	private JTextField txtDatum;
	private JTextField txtSifra;
	private JTable table_1;
	private JTextField txtIme;
	private JTextField txtPrezime;
	private JTextField txtUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SlobodnaProdaja dialog = new SlobodnaProdaja();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SlobodnaProdaja() {
		setTitle("KREIRAJ RECEPT");
		getContentPane().setBackground(new Color(102, 205, 170));
		setBounds(100, 100, 867, 672);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(269, 45, 535, 220);
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
		
		JButton btnPrikai = new JButton("Dodaj u korpu");
		btnPrikai.setBounds(64, 179, 141, 25);
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
		
		JLabel lblifra = new JLabel("\u0160ifra");
		lblifra.setBounds(12, 53, 56, 16);
		getContentPane().add(lblifra);
		
		JLabel lblKoliina = new JLabel("Koli\u010Dina");
		lblKoliina.setBounds(12, 88, 56, 16);
		getContentPane().add(lblKoliina);
		
		JLabel lblCena = new JLabel("Cena");
		lblCena.setBounds(12, 123, 56, 16);
		getContentPane().add(lblCena);
		
		JScrollPane scrollKorpa = new JScrollPane();
		scrollKorpa.setBounds(30, 323, 506, 180);
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
		
		JLabel lblKorpa = new JLabel("Sadr\u017Eaj korpe");
		lblKorpa.setBounds(30, 294, 105, 16);
		getContentPane().add(lblKorpa);
		
		JLabel lblTrenutniIznosRauna = new JLabel("Trenutni iznos ra\u010Duna:");
		lblTrenutniIznosRauna.setBounds(40, 516, 154, 16);
		getContentPane().add(lblTrenutniIznosRauna);
		
		JPanel panelNapraviRacun = new JPanel();
		panelNapraviRacun.setBackground(new Color(102, 205, 170));
		panelNapraviRacun.setBounds(553, 354, 284, 240);
		getContentPane().add(panelNapraviRacun);
		panelNapraviRacun.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ime kupca");
		lblNewLabel.setBounds(12, 24, 74, 16);
		panelNapraviRacun.add(lblNewLabel);
		
		JLabel lblPrezimeKupca = new JLabel("Prezime kupca");
		lblPrezimeKupca.setBounds(12, 60, 97, 16);
		panelNapraviRacun.add(lblPrezimeKupca);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(12, 98, 74, 16);
		panelNapraviRacun.add(lblUsername);
		
		txtIme = new JTextField();
		txtIme.setBounds(111, 21, 116, 22);
		panelNapraviRacun.add(txtIme);
		txtIme.setColumns(10);
		
		txtPrezime = new JTextField();
		txtPrezime.setText("");
		txtPrezime.setBounds(111, 57, 116, 22);
		panelNapraviRacun.add(txtPrezime);
		txtPrezime.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(111, 95, 116, 22);
		panelNapraviRacun.add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnDodajVipKupca = new JButton("Dodaj VIP kupca");
		btnDodajVipKupca.setBounds(121, 130, 138, 25);
		panelNapraviRacun.add(btnDodajVipKupca);
		
		JCheckBox chckbxVipKorisnik = new JCheckBox("VIP korisnik");
		chckbxVipKorisnik.setBackground(new Color(102, 205, 170));
		chckbxVipKorisnik.setEnabled(false);
		chckbxVipKorisnik.setBounds(12, 164, 113, 25);
		panelNapraviRacun.add(chckbxVipKorisnik);
		
		JButton btnNewButton = new JButton("Sa\u010Duvaj ra\u010Dun");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(64, 202, 138, 25);
		panelNapraviRacun.add(btnNewButton);
		
		JButton btnNapraviRaun = new JButton("Napravi ra\u010Dun");
		btnNapraviRaun.setBounds(526, 278, 127, 25);
		getContentPane().add(btnNapraviRaun);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(359, 569, 97, 25);
		getContentPane().add(btnOdustani);
	}
}