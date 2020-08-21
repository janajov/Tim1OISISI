package Interfejs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

import Model.KorisnikBaza;

public class PrikazKorisnika  extends JDialog {
	private JTable table;
	
	DefaultTableModel model;
	KorisnikBaza korisnici = new KorisnikBaza();
	
	JRadioButton rdbtnPoImenu;
	JRadioButton rdbtnPoPrezimenu;
	JRadioButton rdbtnTipKorisnika;
	private final ButtonGroup buttonKorisniciSort = new ButtonGroup();
	private JButton btnOdustani;
	
	
	private JButton btnPrikazi;
	
	private JPanel panel_sortiranje;
	
	private JScrollPane scrollPane;
	
	
	
	

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
		setModal(true);
		getContentPane().setBackground(new Color(102, 205, 170));
		setBounds(100, 100, 885, 369);
		getContentPane().setLayout(null);
		{
			panel_sortiranje = new JPanel();
			panel_sortiranje.setBorder(new TitledBorder(null, "Sortiranje", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_sortiranje.setBackground(new Color(102, 205, 170));
			panel_sortiranje.setBounds(17, 32, 183, 160);
			getContentPane().add(panel_sortiranje);
			panel_sortiranje.setLayout(null);
								
								
			{
				rdbtnPoImenu = new JRadioButton("po imenu ");
				rdbtnPoImenu.setHorizontalAlignment(SwingConstants.LEFT);
				rdbtnPoImenu.setBackground(new Color(102, 205, 170));
				buttonKorisniciSort.add(rdbtnPoImenu);
				rdbtnPoImenu.setBounds(6, 30, 109, 23);
				panel_sortiranje.add(rdbtnPoImenu);//ili je panel_sortiranje
															
			}
			{
				rdbtnPoPrezimenu = new JRadioButton("po prezimenu");
				buttonKorisniciSort.add(rdbtnPoPrezimenu);
				rdbtnPoPrezimenu.setBounds(6, 58, 107, 25);
				rdbtnPoPrezimenu.setHorizontalAlignment(SwingConstants.LEFT);
				rdbtnPoPrezimenu.setBackground(new Color(102, 205, 170));
				panel_sortiranje.add(rdbtnPoPrezimenu);
												
				
			}
			{
				rdbtnTipKorisnika = new JRadioButton("po tipu korisnika");
				buttonKorisniciSort.add(rdbtnTipKorisnika);
				rdbtnTipKorisnika.setHorizontalAlignment(SwingConstants.LEFT);
				rdbtnTipKorisnika.setBounds(6, 88, 121, 25);
				rdbtnTipKorisnika.setBackground(new Color(102, 205, 170));
				panel_sortiranje.add(rdbtnTipKorisnika);
			
			}
		}
		
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(212, 32, 616, 214);
			getContentPane().add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		String[] header = new String[] { "Korisnièko ime", "Ime", "Prezime", "Tip korisnika"  };

		model = new DefaultTableModel(new Object[][] {}, header);

		table.setModel(model);
		
				{
		btnPrikazi = new JButton("Prikazi");
		btnPrikazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String customQuery = "";
				try {

					if (rdbtnPoImenu.isSelected())
						customQuery = "SELECT  * from korisnik order by ime";
					else if (rdbtnPoPrezimenu.isSelected())
						customQuery = "SELECT  * from korisnik order by prezime";
					else if (rdbtnTipKorisnika.isSelected())
						customQuery = "SELECT  * from korisnik order by tip";
					else {
						JOptionPane.showMessageDialog(btnPrikazi,
								"Morate selektovati izbor sortiranja!!!!");
						return;
					}

					model.setRowCount(0);

					List<List<Object>> lista = korisnici
							.SelectQueryList(customQuery);

					for (List<Object> obj : lista) {
						model.addRow(new Object[] { obj.get(0), 
								obj.get(2),
								obj.get(3) ,
								obj.get(4)  });

					}

					table.setModel(model);
					table.setFillsViewportHeight(true);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btnPrikazi.setBounds(32, 261, 97, 25);
		getContentPane().add(btnPrikazi);
	}
	{
		btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOdustani.setBounds(141, 261, 97, 25);
		getContentPane().add(btnOdustani);
		}
}
}