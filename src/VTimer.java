import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VTimer extends JPanel{

	JLabel duree;
	JButton BDuree;
	public VTimer() {
	
		duree = new JLabel("Coucou");
		BDuree = new JButton("Cocuou?");
		this.add(duree, BorderLayout.CENTER);
		this.add(BDuree, BorderLayout.PAGE_START);
		
	}
	
	
	void ChangerTexte(String t)
	{
		System.out.println("Changer texte : " +  t);

		duree.setText(t);
		BDuree.setText(t);
		
		invalidate();
		repaint();
		
	}
}
