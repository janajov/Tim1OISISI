package Interfejs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class Lekar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String lekar;
	int prikaz;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			Lekar dialog = new Lekar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Create the dialog.
	 */
	public Lekar(String username,int prikaz) {
		getContentPane().setBackground(new Color(102, 205, 170));
		setTitle("LEKAR");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		this.lekar = username;
		this.prikaz = prikaz;
		
	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 21);
		getContentPane().add(menuBar);
		
		JMenu mnPrikaz = new JMenu("Lekovi");
		
		menuBar.add(mnPrikaz);
		
		JMenuItem mntmPrikazLekova = new JMenuItem("Prikaz lekova");
		mntmPrikazLekova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrikazSvihLekova frm = new PrikazSvihLekova(prikaz);
				frm.setVisible(true);
			}
		});
		mntmPrikazLekova.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PrikazSvihLekova frm = new PrikazSvihLekova(prikaz);
				frm.setVisible(true);
				
			}
		});
		mnPrikaz.add(mntmPrikazLekova);
		
		JMenuItem mntmPretragaLekova = new JMenuItem("Pretraga lekova");
		mntmPretragaLekova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PretragaLekova frm = new PretragaLekova(prikaz);
				frm.setVisible(true);
			}
		});
		mntmPretragaLekova.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PretragaLekova frm = new PretragaLekova(prikaz);
				frm.setVisible(true);
			}
		});
		mnPrikaz.add(mntmPretragaLekova);
		
		JMenu mnNewMenu_2 = new JMenu("Recepti");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Prikaz svih recepata");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrikazSvihRecepata frm = new PrikazSvihRecepata();
				frm.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Pretraga recepata");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PretragaRecepata frm = new PretragaRecepata();
				frm.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Kreiraj recept");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				KreirajRecept frm = new KreirajRecept(lekar);
				frm.setVisible(true);
			}
		});
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				KreirajRecept frm = new KreirajRecept(lekar);
				frm.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOdustani.setBounds(292, 198, 89, 23);
		getContentPane().add(btnOdustani);
	}


}
