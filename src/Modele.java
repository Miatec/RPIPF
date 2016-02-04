import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import javax.imageio.ImageIO;



public class Modele extends Observable {
	private boolean existe;
	private Color color  = Color.YELLOW;

	public enum EVueCourante { Photo, Timer };
	private EVueCourante  vueCourante = EVueCourante.Photo;

	private ArrayList<String> listePhotos;
	private int posPhotoCourante = 0;

	String texteTimer;


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
		return posPhotoCourante;
	}


	public void setPosPhotoCourante(int posPhotoCourante) {
		this.posPhotoCourante = posPhotoCourante;
	}


	Modele(String chemin_dossierPhoto)
	{

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


		vueCourante= EVueCourante.Photo;
		listePhotos = new ArrayList<String>();
		File dossierPhoto = new File(chemin_dossierPhoto);
		if(dossierPhoto.isDirectory())
		{
			for (String f : dossierPhoto.list()) {
				/*System.out.println(dossierPhoto.getAbsolutePath());
				System.out.println(f);
				System.out.println(dossierPhoto.getAbsolutePath() + "\\" + f);*/
				System.out.println(f);
				String[] pre_ext = f.split("\\.");
				if(pre_ext.length > 0){
					String ext = pre_ext[pre_ext.length -1];
					System.out.println(ext);
					if(extensions.contains(ext))
					{
						String chemin_image_petit = resizeImage(dossierPhoto.getAbsolutePath(), f);
						if(chemin_image_petit != null) listePhotos.add(chemin_image_petit);
					}
				}
			}
		}
	}




	String resizeImage(String path, String fileName) { //BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT) {
		BufferedImage tmp_grand;
		try {
			tmp_grand = ImageIO.read(new File(path + "\\" + fileName));

			Image tmp_petit = tmp_grand.getScaledInstance(800, 480,Image.SCALE_SMOOTH);
			int type = tmp_grand.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : tmp_grand.getType();
			BufferedImage resizedImage = new BufferedImage(800, 480, type);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(tmp_grand, 0, 0, 800, 480, null);
			g.dispose();

			ImageIO.write(resizedImage, "jpg", new File("c:\\dmz\\img\\"+ fileName + ".rfp.jpg"));
			return "c:\\dmz\\img\\"+ fileName + ".rfp.jpg";
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
		System.out.println("GlisserPhotoDroite " + posPhotoCourante);
		int pos_tmp = posPhotoCourante +1;

		if(pos_tmp < listePhotos.size())
			posPhotoCourante = pos_tmp;
		else
			posPhotoCourante = 0;

		System.out.println(posPhotoCourante);
		setChanged();
		notifyObservers();
	} 

	void glisserPhotoGauche() {
		System.out.println("GlisserPhotoGauche " + posPhotoCourante);
		int pos_tmp = posPhotoCourante -1;

		if(pos_tmp > 0)
			posPhotoCourante = pos_tmp;
		else
			posPhotoCourante = listePhotos.size() -1;

		System.out.println(posPhotoCourante);
		setChanged();
		notifyObservers();
	} 

	void glisserHaut()
	{
		
		if(vueCourante == EVueCourante.Photo )
		{
			System.out.println("GlisserHaut");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			//get current date time with Date()
			Date date = new Date();
			texteTimer = dateFormat.format(date);
			vueCourante = EVueCourante.Timer;
			setChanged();
			notifyObservers();
		}
	}

	void glisserBas()
	{

		if(vueCourante == EVueCourante.Timer )
		{
			System.out.println("GlisserBas");
			vueCourante = EVueCourante.Photo;
			setChanged();
			notifyObservers();
		}
	}

	boolean getExiste() {
		return existe;
	}

	public void changerPhoto()
	{
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
}
