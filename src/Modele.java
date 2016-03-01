import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SecondaryLoop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.TimerTask;
import java.util.Timer;

import javax.imageio.ImageIO;


import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Seconds;
import org.joda.time.Weeks;
import org.joda.time.Years;

public class Modele  extends Observable {
	private boolean existe;
	private Color color = Color.YELLOW;
	
	
	Timer timer_secondes;
	
	private Date dateDebutRelation;

	public enum EVueCourante {
		Photo, Timer, Date
	};

	private EVueCourante vueCourante = EVueCourante.Photo;

	private ArrayList<String> listePhotos;
	private int posPhotoCourante = 0;

	String texteTimer;
	String texteDateDate;
	String texteDateHeure;
	
	
	class Occasion{
		public DateTime debut;
		public String description;
		
		Occasion(int annee, int mois, int jour, int heure, int minute, int seconde, String description)
		{
			this.debut = new DateTime(annee, mois, jour, heure, minute, seconde);
			this.description = description;
					
		}
	}
	
	public ArrayList<Occasion> occasions;
	private int posOccasionCourante = 0;

	public String getTexteDateDate() {
		return texteDateDate;
	}

	public void setTexteDateDate(String texteDateDate) {
		this.texteDateDate = texteDateDate;
	}

	public String getTexteDateHeure() {
		return texteDateHeure;
	}

	public void setTexteDateHeure(String texteDateHeure) {
		this.texteDateHeure = texteDateHeure;
	}

	public String getTexteTimer() {
		return texteTimer;
	}

	public void setTexteTimer(String texteTimer) {
		this.texteTimer = texteTimer;
	}

	private ArrayList<String> extensions;

	public ArrayList<String> getListePhotos() {
		return listePhotos;
	}

	public void setListePhotos(ArrayList<String> listePhotos) {
		this.listePhotos = listePhotos;
	}

	public int getPosPhotoCourante() {
		if(posPhotoCourante >= listePhotos.size())
			posPhotoCourante = listePhotos.size() - 1;
		else if(posPhotoCourante < 0)
			posPhotoCourante = 0;
		return posPhotoCourante;
	}

	public int getPosOccasionCourante() {
		if(posOccasionCourante >= occasions.size())
			posOccasionCourante = occasions.size() - 1;
		else if(posOccasionCourante < 0)
			posOccasionCourante = 0;
		return posOccasionCourante;
	}
	
	
	public Occasion getOccasionCourante() {

		return occasions.get(getPosOccasionCourante());
	}
	
	public void setPosPhotoCourante(int posPhotoCourante) {
		this.posPhotoCourante = posPhotoCourante;
	}

	Modele(String chemin_dossierPhoto) {


		extensions = new ArrayList<String>();
		extensions.add("JPG");
		extensions.add("jpg");
		extensions.add("png");
		extensions.add("PNG");
		extensions.add(".JPG");
		extensions.add(".jpg");
		extensions.add(".png");
		extensions.add(".PNG");
		
		
		occasions= new ArrayList<Occasion>();
//		occasions.add(new Occasion(2011, 4, 12, 20, 0, 0, "depuis notre mise en couple"));
//		occasions.add(new Occasion(2011, 3, 27, 20, 0, 0, "depuis notre rencontre"));
//		occasions.add(new Occasion(2014, 9, 1, 0, 0, 0, "depuis notre emmenagement"));
		
		occasions.add(new Occasion(2013, 1, 10, 0, 0, 0, "test a"));
		occasions.add(new Occasion(2014, 2, 20, 0, 0, 0, "test b"));
		occasions.add(new Occasion(2015, 3, 30, 0, 0, 0, "test c"));
		

		texteTimer = "ReCoucou";

		vueCourante = EVueCourante.Photo;
		listePhotos = new ArrayList<String>();
		File dossierPhoto = new File(chemin_dossierPhoto);
		if (dossierPhoto.isDirectory()) {
			for (String f : dossierPhoto.list()) {
				/*
				 * System.out.println(dossierPhoto.getAbsolutePath());
				 * System.out.println(f);
				 * System.out.println(dossierPhoto.getAbsolutePath() + "\\" +
				 * f);
				 */
				System.out.println(f);
				String[] pre_ext = f.split("\\.");
				if (pre_ext.length > 0) {
					String ext = pre_ext[pre_ext.length - 1];
					System.out.println(ext);
					if (extensions.contains(ext)) {
						String chemin_image_petit = resizeImage(dossierPhoto.getAbsolutePath(), f);
						if (chemin_image_petit != null)
							listePhotos.add(chemin_image_petit);
					}
				}
			}
		}
		
		dateDebutRelation = new Date(2011, 4, 12, 21,12);
		
		
		timer_secondes = new Timer();
		TimerTask timertask_secondes = new TimerTask() {
			  @Override
			  public void run() {
				  maj_vues();				
			  }
			};
		timer_secondes.scheduleAtFixedRate(timertask_secondes, 0, 1000);
	}

	String resizeImage(String path, String fileName) { // BufferedImage
														// originalImage, int
														// type, int IMG_WIDTH,
														// int IMG_HEIGHT) {
		BufferedImage tmp_grand;
		try {
			tmp_grand = ImageIO.read(new File(path + "\\" + fileName));

			Image tmp_petit = tmp_grand.getScaledInstance(800, 480, Image.SCALE_SMOOTH);
			int type = tmp_grand.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : tmp_grand.getType();
			BufferedImage resizedImage = new BufferedImage(800, 480, type);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(tmp_grand, 0, 0, 800, 480, null);
			g.dispose();

			ImageIO.write(resizedImage, "jpg", new File("c:\\dmz\\img\\" + fileName + ".rfp.jpg"));
			return "c:\\dmz\\img\\" + fileName + ".rfp.jpg";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	void setExiste(boolean existe) {
		this.existe = existe;
		setChanged();
		notifyObservers();
	}

	void glisserPhotoDroite() {
		if (vueCourante == EVueCourante.Photo) {
			System.out.println("GlisserPhotoDroite " + posPhotoCourante);
			
			afficherPhoto(1);
			/*
			int pos_tmp = posPhotoCourante + 1;

			if (pos_tmp < listePhotos.size())
				posPhotoCourante = pos_tmp;
			else
				posPhotoCourante = 0;

			System.out.println(posPhotoCourante);*/
		}
		else if (vueCourante == EVueCourante.Timer) {
			afficherTimer(1);
		}
		
		else if (vueCourante == EVueCourante.Date) {
			afficherDate();
		}
		setChanged();
		notifyObservers();
	}

	void glisserPhotoGauche() {
		if (vueCourante == EVueCourante.Photo) {
			System.out.println("GlisserPhotoGauche " + posPhotoCourante);
			afficherPhoto(-1);
			/*
			int pos_tmp = posPhotoCourante - 1;

			if (pos_tmp > 0)
				posPhotoCourante = pos_tmp;
			else
				posPhotoCourante = listePhotos.size() - 1;

			System.out.println(posPhotoCourante);
			*/
		}
		else if (vueCourante == EVueCourante.Timer) {
			afficherTimer(-1);
		}
		
		else if (vueCourante == EVueCourante.Date) {
			afficherDate();
		}

	}

	void glisserHaut() {

		System.out.println("GlisserHaut");
		if (vueCourante == EVueCourante.Photo) {
			/*
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			// get current date time with Date()
			Date date = new Date();
			texteTimer = dateFormat.format(date);
			vueCourante = EVueCourante.Timer;
			setChanged();
			notifyObservers();
			*/
			afficherTimer(0);
		}
		else if (vueCourante == EVueCourante.Timer) {
			afficherDate();
		}
		
		else if (vueCourante == EVueCourante.Date) {
			afficherPhoto(0);
		}
	}

	void glisserBas() {

		System.out.println("GlisserBas");
		if (vueCourante == EVueCourante.Timer) {
			afficherPhoto(0);

		}
		else if (vueCourante == EVueCourante.Photo) {
			afficherDate();
		}
		
		else if (vueCourante == EVueCourante.Date) {
			afficherTimer(0);
		}
		
	}

	boolean getExiste() {
		return existe;
	}

	public void changerPhoto() {
		System.out.println("Changement photo");
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		setChanged();
		notifyObservers();
	}

	public EVueCourante getVueCourante() {
		return vueCourante;
	}

	public void setVueCourante(EVueCourante vueCourante) {
		this.vueCourante = vueCourante;
	}
	
	
	public void afficherPhoto(int pas)
	{
		System.out.println(vueCourante + " --> " + EVueCourante.Photo);
		System.out.println("GlisserPhotoGauche " + posPhotoCourante);
		vueCourante = EVueCourante.Photo;
		int pos_tmp = posPhotoCourante + pas;

		if (pos_tmp > 0)
			posPhotoCourante = pos_tmp;
		else
			posPhotoCourante = listePhotos.size() - pas;

		System.out.println(posPhotoCourante);
	}
	
	public void afficherDate()
	{
		System.out.println(vueCourante + " --> " + EVueCourante.Date);
		vueCourante = EVueCourante.Date;
		
		Date loc_date = new Date();
		SimpleDateFormat loc_dateFormat;

		loc_dateFormat = new SimpleDateFormat("EEEE dd MMMMM yyyy");
		texteDateDate = loc_dateFormat.format(loc_date);
		loc_dateFormat = new SimpleDateFormat("kk:mm:ss");
		texteDateHeure = loc_dateFormat.format(loc_date);
		
		setChanged();
		notifyObservers();
		
	}
	
	public String getDecompte(Occasion occasion)
	{
		String res = "<html><center>";
		DateTime maintenant = new DateTime();
		
		
		
		int nbJours = Days.daysBetween(occasion.debut, maintenant).getDays();
		int nbSemaines = Weeks.weeksBetween(occasion.debut, maintenant).getWeeks();
		int nbmois = Months.monthsBetween(occasion.debut, maintenant).getMonths();
		int nbAnnee = Years.yearsBetween(occasion.debut, maintenant).getYears();
		int nbHeures = Hours.hoursBetween(occasion.debut, maintenant).getHours();
		int nbMinutes = Minutes.minutesBetween(occasion.debut, maintenant).getMinutes();
		int nbSecondes = Seconds.secondsBetween(occasion.debut, maintenant).getSeconds();
		
		if(nbAnnee > 0) 
			if(nbAnnee == 1) res += nbAnnee + " année ";
			else res += nbAnnee + " années ";
		
		

		
		int nbMoisReels= nbSemaines % 12;
		if(nbMoisReels > 0)
			 res += nbMoisReels + " mois ";
			
		
		int nbSemainesReelles= nbSemaines % 4;
		if(nbSemainesReelles > 0) 
			if(nbSemainesReelles == 1) res += nbSemainesReelles + " semaine ";
			else res += nbSemainesReelles + " semaines ";
		
		int nbJoursReels= nbSemaines % 30;
		if(nbJoursReels > 0) 
			if(nbJoursReels == 1) res += nbJoursReels + " jour ";
			else res += nbJoursReels + " jours ";
		
//		res += "<br />";
		
		int nbHeuresReeles = nbHeures % 24;
//		if(nbHeuresReeles > 0) 
//			if(nbHeuresReeles == 1) res += nbHeuresReeles + " heure ";
//			else res += nbHeuresReeles + " heures ";
//		
		
		int nbMinutesReelles = nbMinutes % 60;
//		if(nbMinutesReelles > 0) 
//			if(nbMinutesReelles == 1) res += nbMinutesReelles + " minute ";
//			else res += nbMinutesReelles + " minutes ";
		
		int nbSecondesReelles = nbSecondes % 60;
//		if(nbMinutes > 0) 
//			if(nbSecondesReelles == 1) res += nbSecondesReelles + " seconde ";
//			else res += nbSecondesReelles + " Secondes ";
		
		
		res += "<br />";
		
		res += String.format("%02d", nbHeuresReeles) + ":" +String.format("%02d", nbMinutesReelles)  + ":" +String.format("%02d", nbSecondesReelles) ;
		
		res += "<br />";
		res += occasion.description;
		res += "</center></html>";
		
		return res;
	}
	
	public void afficherTimer(int pas)
	{
		System.out.println(vueCourante + " --> " + EVueCourante.Timer);
		vueCourante = EVueCourante.Timer;
		int pos_tmp = posOccasionCourante + pas;		
		if(pos_tmp >= occasions.size())
			posOccasionCourante = occasions.size() - 1;
		else if (pos_tmp >= 0)
			posOccasionCourante = pos_tmp;
		else
			posOccasionCourante = occasions.size() - pas;


		texteTimer = getDecompte( getOccasionCourante());
		vueCourante = EVueCourante.Timer;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Appelé toutes les secondes par timer_secondes
	 * Met à jour les textes contenant des dates.
	 */
	public void maj_vues()
	{
		
		texteTimer = getDecompte( getOccasionCourante());
		Date loc_date = new Date();
		SimpleDateFormat loc_dateFormat;

		loc_dateFormat = new SimpleDateFormat("EEEE dd MMMMM yyyy");
		texteDateDate = loc_dateFormat.format(loc_date);
		loc_dateFormat = new SimpleDateFormat("kk:mm:ss");
		texteDateHeure = loc_dateFormat.format(loc_date);
		
		
		
		
		setChanged();
		notifyObservers();
	}
	
}



