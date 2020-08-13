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

	private final JPanel contentPanelLogovanje = new JPanel();
	private JTextField txtKorisnickoIme;
	private JPasswordField LozinkaField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ApotekaRegistracija dialog = new ApotekaRegistracija();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ApotekaRegistracija() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanelLogovanje.setBackground(new Color(102, 205, 170));
		contentPanelLogovanje.setBounds(0, 0, 434, 228);
		contentPanelLogovanje.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanelLogovanje);
		contentPanelLogovanje.setLayout(null);
		
		JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
		lblKorisnickoIme.setBounds(52, 59, 94, 25);
		contentPanelLogovanje.add(lblKorisnickoIme);
		
		txtKorisnickoIme = new JTextField();
		txtKorisnickoIme.setBounds(152, 61, 86, 20);
		contentPanelLogovanje.add(txtKorisnickoIme);
		txtKorisnickoIme.setColumns(10);
		
		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setBounds(52, 121, 46, 14);
		contentPanelLogovanje.add(lblLozinka);
		
		LozinkaField = new JPasswordField();
		LozinkaField.setBounds(152, 118, 86, 20);
		contentPanelLogovanje.add(LozinkaField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(102, 205, 170));
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}