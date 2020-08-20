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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Lekar  extends JDialog {

	private final JPanel contentPanelLogovanje = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Lekar dialog = new Lekar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Lekar() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanelLogovanje.setBackground(new Color(102, 205, 170));
		contentPanelLogovanje.setBounds(0, 0, 434, 253);
		contentPanelLogovanje.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanelLogovanje);
		contentPanelLogovanje.setLayout(null);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(299, 165, 97, 25);
		contentPanelLogovanje.add(btnOdustani);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(102, 205, 170));
		setJMenuBar(menuBar);
		
		JMenu mnLekovi = new JMenu("Lekovi");
		mnLekovi.setBackground(new Color(139, 0, 139));
		menuBar.add(mnLekovi);
		
		JMenuItem mntmPrikazLekova = new JMenuItem("Prikaz lekova");
		mnLekovi.add(mntmPrikazLekova);
		
		JMenuItem mntmPretragaLekova = new JMenuItem("Pretraga lekova");
		mnLekovi.add(mntmPretragaLekova);
		
		JMenu mnRecepti = new JMenu("Recepti");
		menuBar.add(mnRecepti);
		
		JMenuItem mntmPrikazSvihRecepata = new JMenuItem("Prikaz svih recepata");
		mnRecepti.add(mntmPrikazSvihRecepata);
		
		JMenuItem mntmPretragaRecepata = new JMenuItem("Pretraga recepata");
		mnRecepti.add(mntmPretragaRecepata);
		
		JMenuItem mntmKreirajRecept = new JMenuItem("Kreiraj recept");
		mnRecepti.add(mntmKreirajRecept);
	}
}