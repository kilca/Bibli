package et3.java.projet.application;

import java.io.File;

import et3.java.projet.data.FileReader;

public class Main 
{
	
	/*TODO
	 * 
	 * On met ici tout ce qu'on aura a revoir/faire
	 * 
	 * (Attention dans les commandes il peut y avoir un probleme si un objet a le meme nom que la cmd)
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
			ConsoleCommand.readConsole();
			if(args.length > 0)
			{
				Systeme.chargerBiblio(args[0]);
				
				Systeme.consulterDocuments();
				
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
