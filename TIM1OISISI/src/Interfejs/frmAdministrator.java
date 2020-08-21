package Interfejs;

import java.awt.BorderLayout;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmAdministrator extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private static final long serialVersionUID = 1L;
	int prikaz;
	


	public frmAdministrator(int prikaz) {
		setTitle("Administrator");
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(102, 205, 170));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.prikaz = prikaz;
		{
			JMenuBar menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 434, 21);
			contentPanel.add(menuBar);
			{
				JMenu mnLekovi = new JMenu("Lekovi");
				menuBar.add(mnLekovi);
				{
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
					mnLekovi.add(mntmPrikazLekova);
				}
				{
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
					mnLekovi.add(mntmPretragaLekova);
				}
				{
					JMenuItem mntmDodajLek = new JMenuItem("Dodaj lek");
					mntmDodajLek.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							DodajLek frm = new DodajLek();
							frm.setVisible(true);
						}
					});
					mnLekovi.add(mntmDodajLek);
				}
				{
					JMenuItem mntmIzmeniLek = new JMenuItem("Izmeni lek");
					mntmIzmeniLek.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							IzmenaLekova frm = new IzmenaLekova();
							frm.setVisible(true);
						}
					});
					mnLekovi.add(mntmIzmeniLek);
				}
			}
			{
				JMenu mnKorisnik = new JMenu("Korisnik");
				menuBar.add(mnKorisnik);
				{
					JMenuItem mntmPrikazKorisnika = new JMenuItem("Prikaz korisnika");
					mntmPrikazKorisnika.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							PrikazKorisnika frm = new PrikazKorisnika();
							frm.setVisible(true);
						}
					});
					mntmPrikazKorisnika.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							PrikazKorisnika frm = new PrikazKorisnika();
							frm.setVisible(true);
						}
					});
					mnKorisnik.add(mntmPrikazKorisnika);
				}
				{
					JMenuItem mntmRegistracijaNovihKorisnika = new JMenuItem("Registracija novih korisnika");
					mntmRegistracijaNovihKorisnika.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
								RegistracijaNovihKorisnika frm = new RegistracijaNovihKorisnika();
							frm.setVisible(true);
						}
					});
					mnKorisnik.add(mntmRegistracijaNovihKorisnika);
				}
			}
			{
				JMenu mnRecepti = new JMenu("Recepti");
				menuBar.add(mnRecepti);
				{
					JMenuItem mntmPrikazSvihRecepaa = new JMenuItem("Prikaz svih recepata");
					mntmPrikazSvihRecepaa.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							PrikazSvihRecepata frm = new PrikazSvihRecepata();
							frm.setVisible(true);
						}
					});
					mnRecepti.add(mntmPrikazSvihRecepaa);
				}
				{
					JMenuItem mntmPretragaRecepata = new JMenuItem("Pretraga recepata");
					mntmPretragaRecepata.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							PretragaRecepata frm = new PretragaRecepata();
							frm.setVisible(true);
						}
					});
					mnRecepti.add(mntmPretragaRecepata);
				}
			}
			{
				JMenu mnIzvestaj = new JMenu("Izve\u0161taj");
				menuBar.add(mnIzvestaj);
				{
					JMenuItem mntmUkupnaProdajaSvih = new JMenuItem("Ukupna prodaja svih lekova");
					mntmUkupnaProdajaSvih.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frmIzvestajLekova frm = new frmIzvestajLekova();
							frm.setVisible(true);
						}
					});
					mnIzvestaj.add(mntmUkupnaProdajaSvih);
				}
				{
					JMenuItem mntmUkupnaProdajaSvih_1 = new JMenuItem("Ukupna prodaja svih lekova odabranog proizvo\u0111a\u010Da");
					mntmUkupnaProdajaSvih_1.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							frmIzvestajPoProizvodjacu frm = new frmIzvestajPoProizvodjacu();
							frm.setVisible(true);
						}
					});
					mntmUkupnaProdajaSvih_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frmIzvestajPoProizvodjacu frm = new frmIzvestajPoProizvodjacu();
							frm.setVisible(true);
						}
					});
					mnIzvestaj.add(mntmUkupnaProdajaSvih_1);
				}
				{
					JMenuItem mntmUkupnaProdajaSvih_2 = new JMenuItem("Ukupna prodaja svih lekova odabranog apotekara");
					mntmUkupnaProdajaSvih_2.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							frmIzvestajPoApotekaru frm = new frmIzvestajPoApotekaru();
							frm.setVisible(true);
						}
					});
					mntmUkupnaProdajaSvih_2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frmIzvestajPoApotekaru frm = new frmIzvestajPoApotekaru();
							frm.setVisible(true);
						}
					});
					mnIzvestaj.add(mntmUkupnaProdajaSvih_2);
				}
			}
		}
		{
			JButton btnOdustani = new JButton("Odustani");
			btnOdustani.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnOdustani.setBounds(289, 193, 89, 23);
			contentPanel.add(btnOdustani);
		}
	}

}

