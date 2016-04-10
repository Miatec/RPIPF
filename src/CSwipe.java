import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

public class CSwipe implements MouseListener {
	Modele modele;
	Vue vue;
	boolean actionEnCours;
	int posXOrigine;
	int posYOrigine;

	long debut_click;
	long dernier_click;

	CSwipe(Modele modele, Vue vue) {
		this.modele = modele;
		this.vue = vue;
		actionEnCours = false;
	}

	public void actionPerformed(ActionEvent e) {
		actionEnCours = false;
		System.out.println("Action performed");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		actionEnCours = false;
		System.out.println("Clicked");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		actionEnCours = false;
		System.out.println("Entered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		actionEnCours = false;
		System.out.println("Exited");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("pressed");
		actionEnCours = true;
		posXOrigine = e.getX();
		posYOrigine = e.getY();
		

		Date d = new Date();
		debut_click = d.getTime();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Release");

		actionEnCours = false;
		int posY = e.getY(), posX = e.getX();

		if (posX < posXOrigine)
			System.out.println("Vers la gauche");
		if (posX >= posXOrigine)
			System.out.println("Vers la droite");

		SwipeDirection d = getDirectionSwipe(posXOrigine, posYOrigine, posX, posY);
		// //System.out.println("Coef Directeur : " +
		// getCoefficientDirecteur(posXOrigine, posYOrigine, posX, posY));
		System.out.println("Direction globale : " + d);

		if (d == SwipeDirection.DROITE) {
			modele.glisserPhotoDroite();

		} else if (d == SwipeDirection.GAUCHE) {
			modele.glisserPhotoGauche();

		} else if (d == SwipeDirection.HAUT) {
			modele.glisserHaut();
		} else if (d == SwipeDirection.BAS) {
			modele.glisserBas();
		}

		Date date = new Date();

		if (date.getTime() - debut_click > 500) {
			System.out.println("Click long");
		}
	}

	/**
	 * 
	 * @param xa
	 *            POsition en X du premier point
	 * @param ya
	 *            position en y du premier point
	 * @param xb
	 *            position en x du deuxieme point
	 * @param yb
	 *            position en y du deuxieme
	 * @return le coef directeur de la droite (ab) NB : Si 0 ==> Horizontal Si
	 *         MAX_VALUE ==> vertical
	 * 
	 */
	Double getCoefficientDirecteur(int xa, int ya, int xb, int yb) {
		return getCoefficientDirecteur(new Double(xa), new Double(ya), new Double(xb), new Double(yb));
	}

	Double getCoefficientDirecteur(Double xa, Double ya, Double xb, Double yb) {

		if (xb != xa) {
			return (ya - yb) / (xa - xb);
		}

		return Double.MAX_VALUE;

	}

	enum SwipeDirection {
		HAUT, BAS, GAUCHE, DROITE
	};

	SwipeDirection getDirectionSwipe(int xa, int ya, int xb, int yb) {
		if (Math.abs(xa - xb) > Math.abs(ya - yb)) { // Sens horizontal

			if (xa > xb)
				return SwipeDirection.GAUCHE;
			else
				return SwipeDirection.BAS;
		} else {
			if (ya > yb)
				return SwipeDirection.HAUT;
			else
				return SwipeDirection.DROITE;
		}
	}

}
