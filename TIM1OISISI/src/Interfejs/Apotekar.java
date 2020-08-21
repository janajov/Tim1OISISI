package Interfejs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

public class Apotekar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String lekar;
	int prikaz;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Apotekar dialog = new Apotekar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Apotekar() {
		setTitle("Apotekar");
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(102, 205, 170));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 21);
		contentPanel.add(menuBar);
		
		JMenu mnLekovi = new JMenu("Lekovi");
		menuBar.add(mnLekovi);
		
		JMenuItem mntmPrikazLekova = new JMenuItem("Prikaz lekova");
		mntmPrikazLekova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrikazSvihLekova frm = new PrikazSvihLekova();
				frm.setVisible(true);
			}
		});
		mntmPrikazLekova.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PrikazSvihLekova frm = new PrikazSvihLekova();
				frm.setVisible(true);
				
			}
		});
		mnLekovi.add(mntmPrikazLekova);
		
		JMenuItem mntmPretragaLekova = new JMenuItem("Pretraga lekova");
		mntmPretragaLekova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PretragaLekova frm = new PretragaLekova();
				frm.setVisible(true);
			}
		});
		mntmPretragaLekova.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PretragaLekova frm = new PretragaLekova();
				frm.setVisible(true);
			}
		});
		
		mnLekovi.add(mntmPretragaLekova);
		
		/*JMenuItem mntmDodajLek = new JMenuItem("Dodaj lek");
		
		mnLekovi.add(mntmDodajLek);
		
		JMenuItem mntmIzmeniLek = new JMenuItem("Izmeni lek");
		mnLekovi.add(mntmIzmeniLek);*/
		
		JMenu mnRecepti = new JMenu("Recepti");
		menuBar.add(mnRecepti);
		
		JMenuItem mntmPrikazSvihRecepata = new JMenuItem("Prikaz svih recepata");
		
		mntmPrikazSvihRecepata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrikazSvihRecepata frm = new PrikazSvihRecepata();
				frm.setVisible(true);
			}
		});
		mnRecepti.add(mntmPrikazSvihRecepata);
		
		JMenuItem mntmPretragaRecepata = new JMenuItem("Pretraga recepata");
		mntmPretragaRecepata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PretragaRecepata frm = new PretragaRecepata();
				frm.setVisible(true);
			}
		});
		mnRecepti.add(mntmPretragaRecepata);
		JMenuItem mntmNewMenuItem = new JMenuItem("Kreiraj recept");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				KreirajRecept frm = new KreirajRecept();
				frm.setVisible(true);
			}
		});
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			KreirajRecept frm = new KreirajRecept();
				frm.setVisible(true);
			}
		});
		mnRecepti.add(mntmNewMenuItem);
		
		JMenu mnProdajaLekova = new JMenu("Prodaja lekova");
		
		menuBar.add(mnProdajaLekova);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOdustani.setBounds(277, 199, 89, 23);
		contentPanel.add(btnOdustani);
	}
}

