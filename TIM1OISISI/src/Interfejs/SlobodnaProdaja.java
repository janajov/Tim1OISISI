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
	private JTextField txtSifra;
	private JTextField txtKolicina;
	private JTextField txtCena;
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
		
		JButton btnDodajUkorpu = new JButton("Dodaj u korpu");
		btnDodajUkorpu.setBounds(64, 179, 141, 25);
		getContentPane().add(btnDodajUkorpu);
		
		txtSifra = new JTextField();
		txtSifra.setBounds(118, 50, 116, 22);
		getContentPane().add(txtSifra);
		txtSifra.setColumns(10);
		
		txtKolicina = new JTextField();
		txtKolicina.setText("");
		txtKolicina.setBounds(118, 85, 116, 22);
		getContentPane().add(txtKolicina);
		txtKolicina.setColumns(10);
		
		txtCena = new JTextField();
		txtCena.setText("");
		txtCena.setBounds(118, 120, 116, 22);
		getContentPane().add(txtCena);
		txtCena.setColumns(10);
		
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
		
		JLabel lblSadrzajKorpe = new JLabel("Sadr\u017Eaj korpe");
		lblSadrzajKorpe.setBounds(30, 294, 105, 16);
		getContentPane().add(lblSadrzajKorpe);
		
		JLabel lblTrenutniIznosRacuna = new JLabel("Trenutni iznos ra\u010Duna:");
		lblTrenutniIznosRacuna.setBounds(40, 516, 154, 16);
		getContentPane().add(lblTrenutniIznosRacuna);
		
		JPanel panelNapraviRacun = new JPanel();
		panelNapraviRacun.setBackground(new Color(102, 205, 170));
		panelNapraviRacun.setBounds(553, 354, 284, 240);
		getContentPane().add(panelNapraviRacun);
		panelNapraviRacun.setLayout(null);
		
		JLabel lblImeKupca = new JLabel("Ime kupca");
		lblImeKupca.setBounds(12, 24, 74, 16);
		panelNapraviRacun.add(lblImeKupca);
		
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
		
		JButton btnNapraviRacun = new JButton("Napravi ra\u010Dun");
		btnNapraviRacun.setBounds(526, 278, 127, 25);
		getContentPane().add(btnNapraviRacun);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(359, 569, 97, 25);
		getContentPane().add(btnOdustani);
	}
}