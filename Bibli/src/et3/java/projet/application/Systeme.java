package et3.java.projet.application;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import et3.java.projet.application.documents.*;
import et3.java.projet.data.FileReader;

public class Systeme {

	public static List<Bibliotheque> bibliotheques;
	
	public List<Document> documents;
	
	//hashmap avec ISBN
	public static HashMap<String, Document> docsISBN;
	//hashmap avec EAN
	public static HashMap<String, Livre> docsEAN;
	
	public static void chargerBiblio(String dir) {
		

			File tempFile = new File(dir);
			
			if(tempFile.exists())
			{
				System.out.println("[Main] Reading the file " + dir + " ...");
						
				//We start by reading the CSV file
				FileReader.getDataFromCSVFile(dir);
				
				System.out.println("[Main] End of the file " + dir + ".");
			}
			else
			{
				System.out.println("[Main] No file " + dir);
			}
	}
		
	
	public static boolean ajouterBibliotheque(Bibliotheque b) {
		
		return bibliotheques.add(b);
		
	}
	
	public static boolean ajouterDocument(Document d) {
		return false;		
		
	}
	public static boolean ajouterUtilisateur(Utilisateur u) {
		return false;		
		
	}
	
	
	
}
