package Interfejs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProdajaLekova extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDatum;
	private JTextField txtApotekar;
	private final ButtonGroup buttonProdaja = new ButtonGroup();
	private String apotekar;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			frmProdajaLeka dialog = new frmProdajaLeka();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ProdajaLekova (String apotekar) {
		setTitle("PRODAJA LEKA");
		setModal(true);
		setBounds(100, 100, 891, 388);
		getContentPane().setLayout(null);
		this.apotekar = apotekar;
		
		JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setBounds(35, 39, 89, 14);
		getContentPane().add(lblDatum);
		
		txtDatum = new JTextField();
		txtDatum.setColumns(10);
		txtDatum.setBounds(145, 36, 221, 20);
		getContentPane().add(txtDatum);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar date = Calendar.getInstance();
		
		txtDatum.setText(dateFormat.format(date.getTime()).toString());
		
		JLabel lblApotekar = new JLabel("Apotekar:");
		lblApotekar.setBounds(35, 70, 89, 14);
		getContentPane().add(lblApotekar);
		
		txtApotekar = new JTextField();
		txtApotekar.setColumns(10);
		txtApotekar.setBounds(145, 67, 106, 20);
		getContentPane().add(txtApotekar);
		txtApotekar.setText(apotekar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Izbor prodaje", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(95, 142, 293, 169);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnSlobodnaProdaja = new JRadioButton("Slobodna prodaja");
		rdbtnSlobodnaProdaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		buttonProdaja.add(rdbtnSlobodnaProdaja);
		rdbtnSlobodnaProdaja.setBounds(71, 45, 165, 23);
		panel.add(rdbtnSlobodnaProdaja);
		
		JRadioButton rdbtnRecept = new JRadioButton("Recept");
		rdbtnRecept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		buttonProdaja.add(rdbtnRecept);
		rdbtnRecept.setBounds(71, 72, 165, 23);
		panel.add(rdbtnRecept);
		
		JButton btnKreniProdaju = new JButton("Prodaja");
		
		getContentPane().add(btnKreniProdaju);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnOdustani.setBounds(586, 218, 89, 23);
		getContentPane().add(btnOdustani);
		}
}