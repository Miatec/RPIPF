import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VTimer extends JPanel{

	JLabel duree;
	JButton BDuree;
	public VTimer() {
	
		duree = new JLabel("Coucou");
		duree.setForeground(Color.white);
		duree.setFont(new Font("Calibri", Font.BOLD, 50));
		duree.setBackground(Color.BLACK);
		duree.setHorizontalAlignment(JLabel.CENTER);
		duree.setVerticalAlignment(JLabel.CENTER);
		duree.setPreferredSize(new Dimension(800, 480));
//		BDuree = new JButton("Cocuou?");
		this.add(duree, BorderLayout.CENTER);
		this.setBackground(Color.BLACK);
//		this.add(BDuree, BorderLayout.PAGE_START);
		
	}
	
	
	void ChangerTexte(String t)
	{
		System.out.println("Changer texte : " +  t);

		duree.setText(t);
//		BDuree.setText(t);
		
		invalidate();
		repaint();
		
	}
}
