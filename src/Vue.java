import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Vue extends JFrame implements Observer {
	Modele modele;
	JButton trace = new JButton("trace");
	JButton efface = new JButton("efface");
	VArdoise ardoise = new VArdoise();
	VPhoto photo = new VPhoto(Global.dossier_photo_racine+"default.jpg");
	VTimer timer = new VTimer();
	VReglages reglages;
	VDate 	date = new VDate();



	Vue(Modele modele) {
		this.modele = modele;
		JPanel lesBoutons = new JPanel();
		reglages = new VReglages(modele);

		modele.addObserver(this);

		/*lesBoutons.add(trace);
		lesBoutons.add(efface);
		add(lesBoutons, BorderLayout.NORTH);*/
		add(photo, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(0,0);
		setPreferredSize(new Dimension(800, 480));
		setUndecorated(true);
		pack();
		setVisible(true);
	}

	public void update(Observable o, Object arg) {
		dessiner();
		repaint();
	}
	
	public void dessiner()
	{
		//System.out.println("Dessiner");
		ardoise.setPossedeDisque(modele.getExiste());
		//ardoise.changerPhoto(modele.getColor());
		ardoise.repaint();
		if(modele.getVueCourante() == Modele.EVueCourante.Photo){
			//System.out.println("Affichage photo " +  modele.getPosPhotoCourante() +" " + modele.getListePhotos().size());
			String photoSuivante = modele.getListePhotos().get(modele.getPosPhotoCourante());
			//System.out.println("Photo Suivante : "+ photoSuivante);
			photo.changerPhoto(photoSuivante);
			
//			this.removeAll();
			
			add(photo, BorderLayout.CENTER);
			
			this.photo.validate();
			this.photo.setVisible(true);
			this.timer.validate();
			this.timer.setVisible(false);
			this.date.validate();
			this.date.setVisible(false);
			this.reglages.validate();
			this.reglages.setVisible(false);
			this.getContentPane().validate();
			this.validate();
		}
		else if(modele.getVueCourante() == Modele.EVueCourante.Timer)
		{
			//System.out.println("Affichage Timer");
			timer.ChangerTexte(modele.getTexteTimer());
			
//			this.removeAll();
			
			add(timer, BorderLayout.CENTER);
			this.photo.validate();
			this.photo.setVisible(false);
			this.timer.validate();
			this.timer.setVisible(true);
			this.date.validate();
			this.date.setVisible(false);
			this.reglages.validate();
			this.reglages.setVisible(false);
			this.getContentPane().validate();
			this.validate();
			
			
		}
		else if(modele.getVueCourante() == Modele.EVueCourante.Date)
		{
			//System.out.println("Affichage date");
			date.ChangerTexte(modele.getTexteDateDate(), modele.getAnneeDate(), modele.getTexteDateHeure());
			date.actualiserColeurFond();
			
//			this.removeAll();
			
			add(date, BorderLayout.CENTER);
			this.photo.validate();
			this.photo.setVisible(false);
			this.timer.validate();
			this.timer.setVisible(false);
			this.reglages.validate();
			this.reglages.setVisible(false);
			this.date.validate();
			this.date.setVisible(true);
			this.getContentPane().validate();
			this.validate();
		}
		else if(modele.getVueCourante() == Modele.EVueCourante.Reglage)
		{
			//System.out.println("Affichage reglages");
			date.ChangerTexte(modele.getTexteDateDate(), modele.getAnneeDate(), modele.getTexteDateHeure());
			date.actualiserColeurFond();
			
//			this.removeAll();
			
			add(reglages, BorderLayout.CENTER);
			this.photo.validate();
			this.photo.setVisible(false);
			this.timer.validate();
			this.timer.setVisible(false);
			this.date.validate();
			this.date.setVisible(false);
			this.reglages.validate();
			this.reglages.setVisible(true);
			this.getContentPane().validate();
			this.validate();
		}
		
		
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponents(g);
		dessiner();
	}
	

}