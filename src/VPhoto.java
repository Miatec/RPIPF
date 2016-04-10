import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class VPhoto extends JPanel {


	private Image backgroundImage;

	public void changerPhoto(Color c)
	{
		setBackground(c);
	}





	// Some code to initialize the background image.
	// Here, we use the constructor to load the image. This
	// can vary depending on the use case of the panel.
	public VPhoto(String fileName) {



		Image tmp = null;
		try {
			System.out.println("Nom fichier " + fileName);
			tmp = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		backgroundImage = tmp.getScaledInstance(800, 480,Image.SCALE_SMOOTH);     
	}

	public void changerPhoto(String chemin_photo)
	{
		//System.out.println("Changer photo :" + chemin_photo);
		Image tmp = null;
		try {
			tmp = ImageIO.read(new File(chemin_photo));
			backgroundImage = tmp.getScaledInstance(800, 480,Image.SCALE_SMOOTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//invalidate();
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw the background image.
		g.drawImage(backgroundImage, 0, 0, this);
	}
}
