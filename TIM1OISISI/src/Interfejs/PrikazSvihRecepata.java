package Interfejs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import Model.LekBaza;
import Model.LekRecept;
import Model.ReceptBaza;
import Model.ReceptKlasa;

import javax.swing.UIManager;

import java.awt.Color;

public class PrikazSvihRecepata extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private final ButtonGroup buttonSortiranje = new ButtonGroup();
	String[] header = new String[] { "Sifra", "Lekar", "Pacijent", "Datum",
	"Ukupna cena" };
	DefaultTableModel model =new DefaultTableModel(new Object[][] {}, header);;
	DefaultTableModel model1;

	ReceptBaza recepti = new  ReceptBaza();
	
	private JTable tableLekovi;

	JPanel panel_1;
	JScrollPane scrollPane_1;
	JRadioButton rdbtSifra;
	JRadioButton rdbtLekar;
	JRadioButton rdbtDatum;
	private JButton btnOdustani;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			PrikazSvihRecepata dialog = new PrikazSvihRecepata();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PrikazSvihRecepata() {
		getContentPane().setBackground(new Color(102, 205, 170));
		setTitle("PRIKAZ SVIH RECEPATA");
		setModal(true);
		recepti.importRecept();
		setBounds(100, 100, 849, 571);

		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(158, 36, 616, 214);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	

		table.setModel(model);
	
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 205, 170));
		panel.setBorder(new TitledBorder(null, "Sortiranje",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 36, 138, 161);
		getContentPane().add(panel);
		panel.setLayout(null);

		rdbtSifra = new JRadioButton("po sifri");
		rdbtSifra.setBackground(new Color(102, 205, 170));
		buttonSortiranje.add(rdbtSifra);
		rdbtSifra.setBounds(0, 31, 109, 23);
		panel.add(rdbtSifra);

		rdbtLekar = new JRadioButton("po lekaru");
		rdbtLekar.setBackground(new Color(102, 205, 170));
		buttonSortiranje.add(rdbtLekar);
		rdbtLekar.setBounds(0, 57, 109, 23);
		panel.add(rdbtLekar);

		rdbtDatum = new JRadioButton("po datumu");
		rdbtDatum.setBackground(new Color(102, 205, 170));
		buttonSortiranje.add(rdbtDatum);
		rdbtDatum.setBounds(0, 83, 109, 23);
		panel.add(rdbtDatum);

		JButton btnPrikazi = new JButton("Prikazi");
		btnPrikazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				panel_1.setVisible(false);
				String customQuery = "";
				SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
				try {

					if (rdbtSifra.isSelected())
						customQuery = "SELECT  * from recept order by sifra";
					else if (rdbtLekar.isSelected())
						customQuery = "SELECT  * from recept order by lekar";
					else if (rdbtDatum.isSelected())
						customQuery = "SELECT  * from recept order by datum";
					else {
						JOptionPane.showMessageDialog(btnPrikazi,
								"Morate selektovati izbor sortiranja!!!!");
						return;
					}

				model = new DefaultTableModel(new Object[][] {}, header);

					List<List<Object>> lista = recepti
							.SelectQueryList(customQuery);
					
					for (List<Object> obj : lista) {
						String date = format1.format(obj.get(3));

						Object[] obj1 = new Object[] { obj.get(0), obj.get(1),
								obj.get(2), date, obj.get(5) };
						model.addRow(obj1);

					}

					
					table.setModel(model);
					table.setFillsViewportHeight(true);
				} catch (Exception ex) {
					System.out.println("kod  "+ex.getMessage());
				}
			}
		});
		btnPrikazi.setBounds(10, 213, 89, 23);
		getContentPane().add(btnPrikazi);

		
		
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						
						tableSelect(event);
					}
				});

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 205, 170));
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"Spisak lekova izabranog recepta", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(158, 298, 616, 223);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 28, 584, 184);
		panel_1.add(scrollPane_1);

		tableLekovi = new JTable();
		scrollPane_1.setViewportView(tableLekovi);
		
		btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOdustani.setBounds(10, 260, 89, 23);
		getContentPane().add(btnOdustani);

		panel_1.setVisible(false);

	

	//	contentPanel.setLayout(null);
	}

	private void tableSelect(ListSelectionEvent lse) {
		if (table.getSelectedRow() <0)
			return;
			
		panel_1.setVisible(true);
		try {
			String[] header1 = new String[] { "Lek", "Kolièina" };
			model1 = new DefaultTableModel(new Object[][] {}, header1);
			//recepti1.importRecept();

			Object polje = model.getValueAt(table.getSelectedRow(), 0);
			int tableID = Integer.parseInt(polje.toString());
			ReceptKlasa rec = recepti.GetRecept(tableID);
			
			
			for (String i : rec.getLekovi().keySet()) {
				
				model1.addRow(new Object[] { rec.getLekovi().get(i).getIme(), rec.getLekovi().get(i).getKolicina() });
			}

			tableLekovi.setModel(model1);
			scrollPane_1.setViewportView(tableLekovi);

		} catch (Exception e) {
			System.out.println("Greska!!!!!"+e.getMessage());
		}
	}
}
