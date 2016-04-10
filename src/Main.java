import javax.swing.JFrame;

import javax.swing.JPanel;



import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Observer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;









/*
class VoirFenetre {
	public static void main(String[] arg) {	
		JFrame cadre = new javax.swing.JFrame("Un disque");
		cadre.setContentPane(new VArdoise());
		cadre.setLocation(400, 300);
		cadre.pack();
		cadre.setVisible(true);
		cadre.setPreferredSize(new Dimension(800,480));
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}


*/

public class Main {

	public static void main(String[] args) {

		// TODO Param : Cr�er une vue Param�tres
		// TODO Param : activer/d�sactiver coloration auto de la vue date
		// TODO Param : arreter proprement le rasberry PI
		// TODO Param : regler luminosit�
		// TODO VuePhoto : Stocker les photos retaill�es dans /run/shm/
		
		/*JFrame frame = new JFrame();
    		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    		frame.setUndecorated(true);
    		frame.setVisible(true);*/

		File chemin_miniatures = new File(Global.dossier_miniatures);
		chemin_miniatures.getParentFile().mkdirs();
		chemin_miniatures.mkdir();
		
		Modele modele = new Modele(Global.dossier_photo_racine);
		Vue vue = new Vue(modele);
		Controleur controleur =  new Controleur(modele, vue);
		CSwipe cswipe = new CSwipe(modele, vue);

		vue.trace.addActionListener(controleur);
		vue.efface.addActionListener(controleur);
		vue.addMouseListener(cswipe);


	}

}

