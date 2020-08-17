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
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;

public class PretragaLekova  extends JDialog {

	private final JPanel contentPanelLogovanje = new JPanel();
	private JTextField textField;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PretragaLekova dialog = new PretragaLekova();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PretragaLekova() {
		setBounds(100, 100, 764, 364);
		getContentPane().setLayout(null);
		contentPanelLogovanje.setBackground(new Color(102, 205, 170));
		contentPanelLogovanje.setBounds(0, 0, 746, 279);
		contentPanelLogovanje.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanelLogovanje);
		contentPanelLogovanje.setLayout(null);
		
		JPanel panelPretraga = new JPanel();
		panelPretraga.setBorder(new TitledBorder(null, "Pretraga", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPretraga.setBackground(new Color(102, 205, 170));
		panelPretraga.setBounds(32, 13, 149, 175);
		contentPanelLogovanje.add(panelPretraga);
		
		JRadioButton rdbtnPoImenu = new JRadioButton("po imenu");
		buttonGroup.add(rdbtnPoImenu);
		rdbtnPoImenu.setBackground(new Color(102, 205, 170));
		panelPretraga.add(rdbtnPoImenu);
		
		JRadioButton rdbtnPoSifri = new JRadioButton("po \u0161ifri");
		buttonGroup.add(rdbtnPoSifri);
		panelPretraga.add(rdbtnPoSifri);
		rdbtnPoSifri.setBackground(new Color(102, 205, 170));
		
		JRadioButton rdbtnPoOpsegu = new JRadioButton("po opsegu cene");
		buttonGroup.add(rdbtnPoOpsegu);
		panelPretraga.add(rdbtnPoOpsegu);
		rdbtnPoOpsegu.setBackground(new Color(102, 205, 170));
		
		JRadioButton rdbtnPoProizvodjacu = new JRadioButton("po proizvo\u0111a\u010Du");
		buttonGroup.add(rdbtnPoProizvodjacu);
		rdbtnPoProizvodjacu.setBackground(new Color(102, 205, 170));
		panelPretraga.add(rdbtnPoProizvodjacu);
		
		JPanel panelSifra = new JPanel();
		panelSifra.setBackground(new Color(102, 205, 170));
		panelSifra.setBounds(32, 201, 174, 78);
		contentPanelLogovanje.add(panelSifra);
		
		JLabel lblUnesiSifru = new JLabel("Unesi \u0161ifru za pretragu:");
		panelSifra.add(lblUnesiSifru);
		
		textField = new JTextField();
		panelSifra.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(230, 13, 486, 233);
		contentPanelLogovanje.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(102, 205, 170));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ide na recept", "\u0160ifra", "Ime", "Cena", "Proizvo\u0111a\u010D"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(88);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(32, 202, 174, 77);
		contentPanelLogovanje.add(panel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(102, 205, 170));
			buttonPane.setBounds(0, 276, 746, 41);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			
			JButton btnPrikazi = new JButton("Prika\u017Ei");
			buttonPane.add(btnPrikazi);
			
			JButton btnOdustani = new JButton("Odustani");
			buttonPane.add(btnOdustani);
		}
	}
}