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
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DodajLek  extends JDialog {

	private final JPanel contentPanelLogovanje = new JPanel();
	private JTextField txtNazivLeka;
	private JTextField txtSifra;
	private JTextField txtCena;
	private JTextField txtProizvodjac;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DodajLek dialog = new DodajLek();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DodajLek() {
		setBounds(100, 100, 764, 364);
		getContentPane().setLayout(null);
		contentPanelLogovanje.setBackground(new Color(102, 205, 170));
		contentPanelLogovanje.setBounds(0, 0, 746, 279);
		contentPanelLogovanje.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanelLogovanje);
		contentPanelLogovanje.setLayout(null);
		
		JLabel lblSifraLeka = new JLabel("\u0160ifra:");
		lblSifraLeka.setBounds(24, 40, 94, 14);
		contentPanelLogovanje.add(lblSifraLeka);
		
		txtNazivLeka = new JTextField();
		txtNazivLeka.setBounds(147, 35, 116, 25);
		contentPanelLogovanje.add(txtNazivLeka);
		txtNazivLeka.setColumns(10);
		
		JLabel lblProizvodjacLeka = new JLabel("Proizvo\u0111a\u010D:");
		lblProizvodjacLeka.setBounds(24, 96, 88, 14);
		contentPanelLogovanje.add(lblProizvodjacLeka);
		
		JLabel lblNaziv = new JLabel("Naziv:");
		lblNaziv.setBounds(24, 67, 56, 16);
		contentPanelLogovanje.add(lblNaziv);
		
		txtSifra = new JTextField();
		txtSifra.setBounds(147, 93, 116, 22);
		contentPanelLogovanje.add(txtSifra);
		txtSifra.setColumns(10);
		
		txtCena = new JTextField();
		txtCena.setBounds(147, 149, 116, 22);
		contentPanelLogovanje.add(txtCena);
		txtCena.setColumns(10);
		
		JLabel lblCena = new JLabel("Cena:");
		lblCena.setBounds(24, 152, 56, 16);
		contentPanelLogovanje.add(lblCena);
		
		JLabel lblIzdavanjeNaRecept = new JLabel("Izdavanje na recept:");
		lblIzdavanjeNaRecept.setBounds(24, 123, 138, 16);
		contentPanelLogovanje.add(lblIzdavanjeNaRecept);
		
		txtProizvodjac = new JTextField();
		txtProizvodjac.setBounds(147, 65, 116, 22);
		contentPanelLogovanje.add(txtProizvodjac);
		txtProizvodjac.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(147, 120, 116, 23);
		contentPanelLogovanje.add(comboBox);
		{
			JButton AddButton = new JButton("Add");
			AddButton.setBounds(132, 222, 79, 25);
			contentPanelLogovanje.add(AddButton);
			AddButton.setActionCommand("OK");
			getRootPane().setDefaultButton(AddButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(223, 222, 71, 25);
			contentPanelLogovanje.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(306, 40, 428, 215);
		contentPanelLogovanje.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Ime", "Cena", "Proizvo\u0111a\u010D", "Ide na recept"
			}
		));
		table.getColumnModel().getColumn(4).setPreferredWidth(93);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(102, 205, 170));
			buttonPane.setBounds(0, 276, 746, 41);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
		}
	}
}