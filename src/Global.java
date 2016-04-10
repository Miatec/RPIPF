import java.awt.Font;
import java.io.FileInputStream;
import java.io.InputStream;

import com.sun.xml.internal.fastinfoset.sax.Properties;

public class Global {
//	public static String dossier_photo_racine = "/home/miatec/rpipf/";
	
	public static String dossier_miniatures = "/run/shm/rpipf/";
	public static char separateur_dossier = '/';
	public static String dossier_photo_racine = System.getProperty("user.dir") + separateur_dossier  + "rpipf" + separateur_dossier;
	public static Font police_titre = new Font("Lucida Sans Regular", Font.BOLD, 60);
	public static Font police_texte = new Font("Lucida Sans Regular", Font.BOLD, 30);
	public static boolean reglage_luminosite_actif = false;
	public static String commande_extinction="shutdown -h now";
	
	public static String getPWD()
	{
		String res =Global.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		String[] sous_parties = res.split(new Character(separateur_dossier).toString());
		String resFinal = "";
		for (int i = 0; i < sous_parties.length ; i++) {
			resFinal += sous_parties[i] + separateur_dossier;
		}
		System.out.println("PWD : " + resFinal + "   from  "  + res);
		
		System.out.println("user dir : " + System.getProperty("user.dir"));
		return resFinal;
		
	}
	
}
