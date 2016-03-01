import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VDate extends JPanel {

	JLabel date;
	JLabel heure;

	public VDate() {

		this.setLayout(new BorderLayout());
//		JPanel haut = new JPanel(new BorderLayout());
//		JPanel bas = new JPanel(new BorderLayout());
		
		
		
		int position_centre = (int) JLabel.CENTER_ALIGNMENT;
		date = new JLabel("");
		date.setForeground(Color.white);
		date.setFont(new Font("Calibri", Font.BOLD, 70));
		date.setBackground(Color.RED);
		date.setHorizontalAlignment(position_centre);
		date.setVerticalAlignment(position_centre);
		date.setVerticalTextPosition(position_centre);
		date.setAlignmentY(position_centre);
		date.setPreferredSize(new Dimension(800, 240));

		heure = new JLabel("");
		heure.setForeground(Color.white);
		heure.setFont(new Font("Calibri", Font.BOLD, 70));
		heure.setBackground(Color.YELLOW);
		heure.setHorizontalAlignment(position_centre);
		heure.setVerticalAlignment(position_centre);
		heure.setVerticalTextPosition(position_centre);
		heure.setAlignmentY(position_centre);
		heure.setPreferredSize(new Dimension(800, 240));

		// BDuree = new JButton("Cocuou?");
		
//		haut.add(date, BorderLayout.CENTER);
//		bas.add(heure, BorderLayout.CENTER);
		this.add(date, BorderLayout.PAGE_START);
		this.add(heure, BorderLayout.PAGE_END);
		this.setBackground(Color.BLACK);
		
		
//		JPanel();
//		this.add(new JLabel("gauche"), BorderLayout.LINE_START);
//		this.add(new JLabel("droite"), BorderLayout.LINE_END);
//		this.add(new JLabel("centre"), BorderLayout.CENTER);
		// this.add(BDuree, BorderLayout.PAGE_START);

	}

	void ChangerTexte(String loc_date, String loc_heure) {
		System.out.println("Changer texte : " + loc_date + " " + loc_heure);

		date.setText(loc_date);
		heure.setText(loc_heure);
/*
		date.setForeground(Color.YELLOW);
		date.setFont(new Font("Calibri", Font.BOLD, 70));
		date.setBackground(Color.RED);*/
//		date.setHorizontalAlignment(JLabel.CENTER);
//		date.setVerticalAlignment(JLabel.CENTER);
/*
		heure.setForeground(Color.RED);
		heure.setFont(new Font("Calibri", Font.BOLD, 70));
		heure.setBackground(Color.YELLOW);*/
//		heure.setHorizontalAlignment(JLabel.CENTER);
//		heure.setVerticalAlignment(JLabel.CENTER);


		/*
		 * this.add(date, BorderLayout.NORTH); this.add(heure,
		 * BorderLayout.SOUTH); this.setBackground(Color.BLACK);
		 */

		invalidate();
		repaint();

	}
}
