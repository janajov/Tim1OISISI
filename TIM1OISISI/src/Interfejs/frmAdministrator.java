package Interfejs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class frmAdministrator extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmAdministrator dialog = new frmAdministrator();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmAdministrator() {
		setTitle("Administrator");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(102, 205, 170));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JMenuBar menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 434, 21);
			contentPanel.add(menuBar);
			{
				JMenu mnLekovi = new JMenu("Lekovi");
				menuBar.add(mnLekovi);
				{
					JMenuItem mntmPrikazLekova = new JMenuItem("Prikaz lekova");
					mnLekovi.add(mntmPrikazLekova);
				}
				{
					JMenuItem mntmPretragaLekova = new JMenuItem("Pretraga lekova");
					mnLekovi.add(mntmPretragaLekova);
				}
				{
					JMenuItem mntmDodajLek = new JMenuItem("Dodaj lek");
					mnLekovi.add(mntmDodajLek);
				}
				{
					JMenuItem mntmIzmeniLek = new JMenuItem("Izmeni lek");
					mnLekovi.add(mntmIzmeniLek);
				}
			}
			{
				JMenu mnKorisnik = new JMenu("Korisnik");
				menuBar.add(mnKorisnik);
				{
					JMenuItem mntmPrikazKorisnika = new JMenuItem("Prikaz korisnika");
					mnKorisnik.add(mntmPrikazKorisnika);
				}
				{
					JMenuItem mntmRegistracijaNovihKorisnika = new JMenuItem("Registracija novih korisnika");
					mnKorisnik.add(mntmRegistracijaNovihKorisnika);
				}
			}
			{
				JMenu mnRecepti = new JMenu("Recepti");
				menuBar.add(mnRecepti);
				{
					JMenuItem mntmPrikazSvihRecepaa = new JMenuItem("Prikaz svih recepata");
					mnRecepti.add(mntmPrikazSvihRecepaa);
				}
				{
					JMenuItem mntmPretragaRecepata = new JMenuItem("Pretraga recepata");
					mnRecepti.add(mntmPretragaRecepata);
				}
			}
			{
				JMenu mnIzvestaj = new JMenu("Izve\u0161taj");
				menuBar.add(mnIzvestaj);
				{
					JMenuItem mntmUkupnaProdajaSvih = new JMenuItem("Ukupna prodaja svih lekova");
					mnIzvestaj.add(mntmUkupnaProdajaSvih);
				}
				{
					JMenuItem mntmUkupnaProdajaSvih_1 = new JMenuItem("Ukupna prodaja svih lekova odabranog proizvo\u0111a\u010Da");
					mnIzvestaj.add(mntmUkupnaProdajaSvih_1);
				}
				{
					JMenuItem mntmUkupnaProdajaSvih_2 = new JMenuItem("Ukupna prodaja svih lekova odabranog apotekara");
					mnIzvestaj.add(mntmUkupnaProdajaSvih_2);
				}
			}
		}
		{
			JButton btnOdustani = new JButton("Odustani");
			btnOdustani.setBounds(289, 193, 89, 23);
			contentPanel.add(btnOdustani);
		}
	}

}
