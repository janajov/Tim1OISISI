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

public class ApotekaRegistracija  extends JDialog {
	private JTextField txtKorisnickoIme;
	private JPasswordField LozinkaField;


	public static void main(String[] args) {
		try {
			ApotekaRegistracija dialog = new ApotekaRegistracija();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public ApotekaRegistracija() {
		getContentPane().setBackground(new Color(102, 205, 170));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(282, 215, 49, 25);
			getContentPane().add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		
		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setBounds(53, 127, 46, 14);
		getContentPane().add(lblLozinka);
		
		LozinkaField = new JPasswordField();
		LozinkaField.setBounds(165, 124, 86, 20);
		getContentPane().add(LozinkaField);
		
		txtKorisnickoIme = new JTextField();
		txtKorisnickoIme.setBounds(165, 75, 86, 20);
		getContentPane().add(txtKorisnickoIme);
		txtKorisnickoIme.setColumns(10);
		
		JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
		lblKorisnickoIme.setBounds(53, 72, 94, 25);
		getContentPane().add(lblKorisnickoIme);
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(343, 215, 71, 25);
			getContentPane().add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
	}
}