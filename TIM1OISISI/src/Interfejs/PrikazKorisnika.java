package Interfejs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

public class PrikazKorisnika  extends JDialog {
	private JTable table;


	public static void main(String[] args) {
		try {
			PrikazKorisnika dialog = new PrikazKorisnika();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public PrikazKorisnika() {
		getContentPane().setBackground(new Color(102, 205, 170));
		setBounds(100, 100, 885, 369);
		getContentPane().setLayout(null);
		
		JButton btnPrikazi = new JButton("Prikazi");
		btnPrikazi.setBounds(32, 261, 97, 25);
		getContentPane().add(btnPrikazi);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnOdustani.setBounds(141, 261, 97, 25);
		getContentPane().add(btnOdustani);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Sortiranje", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(new Color(102, 205, 170));
		panel_1.setBounds(44, 51, 183, 160);
		getContentPane().add(panel_1);
		
		JRadioButton rdbtnPoImenu = new JRadioButton("po imenu");
		rdbtnPoImenu.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnPoImenu.setBackground(new Color(102, 205, 170));
		rdbtnPoImenu.setBounds(0, 31, 109, 23);
		panel_1.add(rdbtnPoImenu);
		
		JRadioButton rdbtnPoPrezimenu = new JRadioButton("po prezimenu");
		rdbtnPoPrezimenu.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnPoPrezimenu.setBackground(new Color(102, 205, 170));
		panel_1.add(rdbtnPoPrezimenu);
		
		JRadioButton rdbtnTipKorisnika = new JRadioButton("po tipu korisnika");
		rdbtnTipKorisnika.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnTipKorisnika.setBackground(new Color(102, 205, 170));
		panel_1.add(rdbtnTipKorisnika);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(281, 51, 540, 235);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Korisnicko ime", "Ime", "Prezime", "Tip korisnika"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(27, 29, 255, 203);
	}
}