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
import javax.swing.UIManager;
import javax.swing.ButtonGroup;

public class PrikazSvihLekova  extends JDialog {

	private final JPanel contentPanelLogovanje = new JPanel();
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PrikazSvihLekova dialog = new PrikazSvihLekova();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PrikazSvihLekova() {
		setBounds(100, 100, 764, 364);
		getContentPane().setLayout(null);
		contentPanelLogovanje.setBackground(new Color(102, 205, 170));
		contentPanelLogovanje.setBounds(0, 0, 746, 279);
		contentPanelLogovanje.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanelLogovanje);
		contentPanelLogovanje.setLayout(null);
		
		JPanel panelSortiranje = new JPanel();
		panelSortiranje.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sortiranje", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSortiranje.setBackground(new Color(102, 205, 170));
		panelSortiranje.setBounds(40, 31, 149, 126);
		contentPanelLogovanje.add(panelSortiranje);
		panelSortiranje.setLayout(null);
		
		JRadioButton rdbtnPoImenu = new JRadioButton("po imenu leka");
		rdbtnPoImenu.setBounds(8, 23, 109, 25);
		buttonGroup.add(rdbtnPoImenu);
		rdbtnPoImenu.setBackground(new Color(102, 205, 170));
		panelSortiranje.add(rdbtnPoImenu);
		
		JRadioButton rdbtnPoProizvodjacu = new JRadioButton("po proizvo\u0111a\u010Du");
		rdbtnPoProizvodjacu.setBounds(8, 53, 115, 25);
		buttonGroup.add(rdbtnPoProizvodjacu);
		rdbtnPoProizvodjacu.setBackground(new Color(102, 205, 170));
		panelSortiranje.add(rdbtnPoProizvodjacu);
		
		JRadioButton rdbtnPoCeni = new JRadioButton("po ceni");
		rdbtnPoCeni.setBounds(8, 83, 69, 25);
		buttonGroup.add(rdbtnPoCeni);
		panelSortiranje.add(rdbtnPoCeni);
		rdbtnPoCeni.setBackground(new Color(102, 205, 170));
		
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
		
		JButton btnPrikazi = new JButton("Prika\u017Ei");
		btnPrikazi.setBounds(40, 181, 81, 25);
		contentPanelLogovanje.add(btnPrikazi);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(40, 219, 109, 25);
		contentPanelLogovanje.add(btnOdustani);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(102, 205, 170));
			buttonPane.setBounds(0, 276, 746, 41);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
		}
	}
}