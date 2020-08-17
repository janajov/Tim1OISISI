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
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class IzmenaLekova  extends JDialog {
	private JTextField txtPolje1;
	private JTextField textPolje3;
	private JTextField textPolje4;
	private JPasswordField passwordField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			IzmenaLekova dialog = new IzmenaLekova();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public IzmenaLekova() {
		getContentPane().setBackground(new Color(102, 205, 170));
		setBounds(100, 100, 723, 300);
		getContentPane().setLayout(null);
		
		txtPolje1 = new JTextField();
		txtPolje1.setBounds(131, 29, 116, 22);
		getContentPane().add(txtPolje1);
		txtPolje1.setColumns(10);
		
		textPolje3 = new JTextField();
		textPolje3.setBounds(131, 97, 116, 22);
		getContentPane().add(textPolje3);
		textPolje3.setColumns(10);
		
		textPolje4 = new JTextField();
		textPolje4.setBounds(131, 165, 116, 22);
		getContentPane().add(textPolje4);
		textPolje4.setColumns(10);
		
		JLabel lblKorisnickoIme = new JLabel("\u0160ifra");
		lblKorisnickoIme.setBounds(20, 29, 116, 22);
		getContentPane().add(lblKorisnickoIme);
		
		JLabel lblNewLabel = new JLabel("Ime");
		lblNewLabel.setBounds(20, 63, 56, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblIme = new JLabel("Proizvo\u0111a\u010D");
		lblIme.setBounds(20, 97, 56, 16);
		getContentPane().add(lblIme);
		
		JLabel lblNewLabel_1 = new JLabel("Na recept");
		lblNewLabel_1.setBounds(20, 131, 84, 16);
		getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(131, 63, 116, 22);
		getContentPane().add(passwordField);
		
		JLabel lblTipKorisnika = new JLabel("Cena");
		lblTipKorisnika.setBounds(20, 165, 84, 16);
		getContentPane().add(lblTipKorisnika);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(131, 131, 116, 22);
		getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Izmeni");
		btnNewButton.setBounds(430, 215, 117, 25);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Odustani");
		btnNewButton_1.setBounds(596, 215, 97, 25);
		getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(273, 29, 420, 161);
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
	}
}