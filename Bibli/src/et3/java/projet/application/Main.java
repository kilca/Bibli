package et3.java.projet.application;

import java.io.File;

import et3.java.projet.data.FileReader;

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
