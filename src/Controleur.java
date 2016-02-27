import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class  Controleur implements ActionListener { 
	Modele modele;
	Vue vue;

	Controleur(Modele modele, Vue vue) {
		this.modele = modele;
		this.vue = vue;
	}

	public void actionPerformed(ActionEvent e) {
		/*if (e.getSource() == vue.trace) modele.setExiste(true);
		else if (e.getSource() == vue.efface ) modele.setExiste(false);*/
	}

}