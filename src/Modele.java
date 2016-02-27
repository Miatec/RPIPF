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

public class Modele extends Observable {
	private boolean existe;
	private Color color = Color.YELLOW;
	
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
			afficherTimer();
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
			afficherTimer();
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
			afficherTimer();
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
			afficherTimer();
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
	
	public String getDecompte(DateTime occasion)
	{
		String res = "<html><center>";
		DateTime maintenant = new DateTime();
		
		
		int nbJours = Days.daysBetween(occasion, maintenant).getDays();
		int nbSemaines = Weeks.weeksBetween(occasion, maintenant).getWeeks();
		int nbmois = Months.monthsBetween(occasion, maintenant).getMonths();
		int nbAnnee = Years.yearsBetween(occasion, maintenant).getYears();
		int nbHeures = Hours.hoursBetween(occasion, maintenant).getHours();
		int nbMinutes = Minutes.minutesBetween(occasion, maintenant).getMinutes();
		int nbSecondes = Seconds.secondsBetween(occasion, maintenant).getSeconds();
		
		if(nbAnnee > 0) 
			if(nbAnnee == 1) res += nbAnnee + " ann�e ";
			else res += nbAnnee + " ann�es ";
		
		

		
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
		
		
		int nbHeuresReeles = nbHeures % 24;
		if(nbHeuresReeles > 0) 
			if(nbHeuresReeles == 1) res += nbHeuresReeles + " heure ";
			else res += nbHeuresReeles + " heures ";
		
		
		int nbMinutesReelles = nbMinutes % 60;
		if(nbMinutesReelles > 0) 
			if(nbMinutesReelles == 1) res += nbMinutesReelles + " minute ";
			else res += nbMinutesReelles + " minutes ";
		
		int nbSecondesReelles = nbSecondes % 60;
		if(nbMinutes > 0) 
			if(nbSecondesReelles == 1) res += nbSecondesReelles + " seconde ";
			else res += nbSecondesReelles + " Secondes ";
		
		res += "</center></html>";
		
		return res;
	}
	
	public void afficherTimer()
	{
		System.out.println(vueCourante + " --> " + EVueCourante.Timer);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// get current date time with Date()
		Date date = new Date();
		LocalDate aujourdhui = new LocalDate();
		Date date_origine = new Date(2011, 11, 04, 20, 0);
		LocalDate origine = new LocalDate(date_origine);
		
		
		DateTime start = new DateTime(2011, 11, 04, 20, 0, 0);
		DateTime end = new DateTime();
		
		
		Days years = Days.daysBetween(origine, aujourdhui);
		System.out.println("Jours : " + Days.daysBetween(start, end).getDays());
		System.out.println("Semaines : " + Weeks.weeksBetween(start, end).getWeeks());
		System.out.println("Mois : " + Months.monthsBetween(start, end).getMonths());
		System.out.println("Ann�es : " + Years.yearsBetween(start, end).getYears());
		
		
		System.out.println(getDecompte(start));
		//texteTimer = dateFormat.format(date);
		texteTimer = getDecompte(start);
		vueCourante = EVueCourante.Timer;
		setChanged();
		notifyObservers();
	}
}
