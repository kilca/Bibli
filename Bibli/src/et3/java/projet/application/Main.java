package et3.java.projet.application;

import java.io.File;

import et3.java.projet.data.FileReader;
/**
 * classe principale du projet
 */
public class Main 
{
	
	/*TODO
	 * 
	 * ***On met ici tout ce qu'on aura a revoir/faire***
	 * 
	 * 
	 * Il faut etre certain que les types de stockage soient les bon
	 * 
	 * Tester 
	 * 
	 * Factoriser le code (les [-truc opt]) et autre
	 * 
	 * Est ce qu'il serait préférable de mettre pleins de hashmap pour faciliter les questions 5 à 8?
	 * 
	 * Ajouter ISBN
	 * Ajouter les exception dans la console
	 * 
	 * Emprunt et remise de doc
	 * 
	 * */
	/**
	 * fonction de demarage du programme, elle fait l'appel à l'importation des donnée du document, puis lance la lecture de la console.
	 * @param args	chemin du projet (Bibli\src\et3\java\projet\data\data.csv)
	 */
	public static void main(String[] args) 
	{
		try {
			if(args.length > 0)
			{
				Systeme.ajouterBibliotheque(new Bibliotheque("AimeCesaire"));
				Systeme.ajouterBibliotheque(new Bibliotheque("EdmondRostand"));
				Systeme.ajouterBibliotheque(new Bibliotheque("JeanPierreMelville"));
				Systeme.ajouterBibliotheque(new Bibliotheque("OscarWilde"));
				Systeme.ajouterBibliotheque(new Bibliotheque("SaintSimon"));
				
				//on accepte les document sans EAN depuis le csv mais pas depuis la console.
				Systeme.chargerBiblio(args[0]);
				
				
				ConsoleCommand.readConsole();
				
			}
			else
			{
				System.out.println("[Main] You should enter the CSV file path as a parameter.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//TODO Project :)
	}
}
