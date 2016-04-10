import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



public class VDate extends JPanel {

	JLabel date;
	JLabel heure;

	
	public VDate() {

		this.setLayout(new BorderLayout());
		
		int position_centre = (int) JLabel.CENTER_ALIGNMENT;
		date = new JLabel("");
		date.setForeground(Color.white);
		date.setFont(Global.police_titre);
		date.setBackground(Color.RED);
		date.setHorizontalAlignment(position_centre);
		date.setVerticalAlignment(position_centre);
		date.setVerticalTextPosition(position_centre);
		date.setAlignmentY(position_centre);
		date.setPreferredSize(new Dimension(800, 240));

		heure = new JLabel("");
		heure.setForeground(Color.white);
		heure.setFont(Global.police_titre);
		heure.setBackground(Color.YELLOW);
		heure.setHorizontalAlignment(position_centre);
		heure.setVerticalAlignment(position_centre);
		heure.setVerticalTextPosition(position_centre);
		heure.setAlignmentY(position_centre);
		heure.setPreferredSize(new Dimension(800, 240));


		this.add(date, BorderLayout.PAGE_START);
		this.add(heure, BorderLayout.PAGE_END);
		this.setBackground(Color.BLACK);


	}

	void ChangerTexte(String loc_date, int loc_annee, String loc_heure) {
		//System.out.println("Changer texte : " + loc_date + " " + loc_heure);


		String loc_textedatedate_concatenation = "";
		for(int i=0; (i<3 && i < loc_date.split(" ").length); i++){
			loc_textedatedate_concatenation += loc_date.split(" ")[i] + " ";
		}
		//System.out.println(loc_textedatedate_concatenation);
		
		date.setText("<html><center>" + loc_textedatedate_concatenation + "<br/>" + loc_annee + "</center></html>" );
		heure.setText(loc_heure);

		tout_raffraichir();
		

	}
	
	void actualiserColeurFond()
	{
		Date loc_date = new Date();
		
		int loc_heures = loc_date.getHours();
		int loc_minutes = loc_date.getMinutes();
		int loc_secondes = loc_date.getSeconds();
		
		int loc_rouge = (int) (loc_heures * 11.08);
		int loc_vert = (int) (loc_minutes * 4.25);
		int loc_bleu = (int) (loc_secondes * 4.25);
		
		
		Color c = new Color(loc_rouge, loc_vert, loc_bleu);
		this.setBackground(c);
		
		String loc_desc_couleur= "RGB = ("+ loc_rouge + ", " + loc_vert + ", " + loc_bleu +")";
		
		//System.out.println("Couleur : " + loc_desc_couleur + "  " + c);
		
		
		double yiq = ((loc_rouge*299)+(loc_vert*587)+(loc_bleu*114))/1000;
		
		if(yiq >= 128)
		{
			//System.out.println(yiq + " ==> noir");
			date.setForeground(Color.BLACK);
			heure.setForeground(Color.BLACK);
		}
		else
		{
			//System.out.println(yiq + " ==> blanc");
			date.setForeground(Color.WHITE);
			heure.setForeground(Color.WHITE);

		}
		
	
		tout_raffraichir();		
	
	}
	
	void tout_raffraichir()
	{
		date.invalidate();
		date.repaint();
		heure.invalidate();
		heure.repaint();
		invalidate();
		repaint();
	}
}
