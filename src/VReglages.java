import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class VReglages extends JPanel {
	
	JLabel titre;
	
	JPanel luminosite;
	JLabel luminosite_texte;
	JButton luminosite_plus;
	JButton luminosite_moins;
	
	JButton extinction;
	
	JToggleButton activer_diaporama;
	
	JPanel delaiDiaporama;
	JLabel delaiDiaporama_texte;
	JLabel delaiDiaporama_valeur;
	JButton delaiDiaporama_plus;
	JButton delaiDiaporama_moins;

	Modele modele;
	
	
	VReglages(final Modele loc_modele)
	{
		this.modele = loc_modele;  
		
		setLayout(new GridLayout(5, 0));
		
		GridLayout l;
		
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		setTitre();
		this.add(titre);
		
		
		
		if(Global.reglage_luminosite_actif){
			luminosite = new JPanel(new GridLayout(0,3));
			luminosite.setBorder(getBorder());
			luminosite_texte = new JLabel("Luminosité"); 	setStyleJLabel(luminosite_texte);
			luminosite_plus = new JButton("+");				setStyleJButton(luminosite_plus);
			luminosite_moins = new JButton("-");			setStyleJButton(luminosite_moins);
			luminosite.add(luminosite_texte);
			luminosite.add(luminosite_moins);
			luminosite.add(luminosite_plus);		
			this.add(luminosite);
		}
		
		activer_diaporama = new JToggleButton("Activer diaporama");
		setStyleJToggleButton(activer_diaporama);
		activer_diaporama.setSelected(modele.isDiaporamaActif());
		activer_diaporama.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.changerDiaporamaActif();
				activer_diaporama.setSelected(modele.isDiaporamaActif());
				
			}
		});
		this.add(activer_diaporama);
		
		
		
		delaiDiaporama = new JPanel(new GridLayout(0,4));
		
		delaiDiaporama.setBorder(getBorder());
		delaiDiaporama_texte = new JLabel("Delais"); 	setStyleJLabel(delaiDiaporama_texte);
		delaiDiaporama_valeur = new JLabel("5s");		setStyleJLabel(delaiDiaporama_valeur);
		delaiDiaporama_plus = new JButton("+");			setStyleJButton(delaiDiaporama_plus);
		delaiDiaporama_moins = new JButton("-");		setStyleJButton(delaiDiaporama_moins);
		
		delaiDiaporama_plus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.add_delaiDiaporama();;
				delaiDiaporama_valeur.setText(modele.getDelai_diaporama() + " s");
				delaiDiaporama_moins.setEnabled(modele.getDelai_diaporama() > 1);
			}
		});
		delaiDiaporama_moins.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.substract_delaiDiaporama();
				delaiDiaporama_valeur.setText(modele.getDelai_diaporama() + " s");
				
				delaiDiaporama_moins.setEnabled(modele.getDelai_diaporama() > 1);
				
			}
		});
		
		
		delaiDiaporama.add(delaiDiaporama_texte);
		delaiDiaporama.add(delaiDiaporama_moins);
		delaiDiaporama.add(delaiDiaporama_valeur);
		delaiDiaporama.add(delaiDiaporama_plus);		
		this.add(delaiDiaporama);
		
		
		
		
		
		
		extinction = new JButton("Eteindre");	setStyleJButton(extinction);
		extinction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec(Global.commande_extinction);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		this.add(extinction);
		
	}
	
	
	void setTitre()
	{
		titre =  new JLabel("Paramètres");
		titre.setFont(Global.police_titre);
		titre.setBackground(Color.BLACK);
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setVerticalAlignment(JLabel.CENTER);
		titre.setBorder(getBorderLabel());
		
		
	}
	
	void setStyleJLabel(JLabel j)
	{
		
		j.setFont(Global.police_texte);
		j.setBackground(Color.BLACK);
		j.setHorizontalAlignment(JLabel.LEFT);
		j.setVerticalAlignment(JLabel.CENTER);
		j.setBorder(getBorderLabel());
		
		
	}
	
	void setStyleJButton(JButton j)
	{
		
		j.setFont(Global.police_texte);
		j.setHorizontalAlignment(JLabel.CENTER);
		j.setVerticalAlignment(JLabel.CENTER);
		j.setMargin(getInsetsSansBord());
		j.setBorder(getBorderBouton());
		
	}
	
	void setStyleJToggleButton(JToggleButton j)
	{
		
		j.setFont(Global.police_texte);
		j.setHorizontalAlignment(JLabel.CENTER);
		j.setVerticalAlignment(JLabel.CENTER);
		j.setMargin(getInsets());
		j.setBorder(getBorderBouton());
		
		
		
	}
	
	public Insets getInsets() {
		 return new Insets(10,10,10,10);
	} 
	
	public Insets getInsetsSansBord() {
		 return new Insets(10,10,10,0);
	} 
	
	public CompoundBorder getBorderBouton()
	{
		return BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder());
	}
	
	public Border getBorderLabel()
	{
		return new EmptyBorder(10, 10, 10, 10);
	}
	
}
