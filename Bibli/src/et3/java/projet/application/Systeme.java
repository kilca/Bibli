package et3.java.projet.application;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import et3.java.projet.application.documents.*;
import et3.java.projet.application.exceptions.DocumentExistException;
import et3.java.projet.data.FileReader;

public class Systeme {

	public static List<Bibliotheque> bibliotheques = new ArrayList<Bibliotheque>();
	
	public static List<Document> documents = new ArrayList<Document>();
	
	//hashmap avec ISBN
	public static HashMap<String, Livre> docsISBN = new HashMap<String,Livre>();
	//hashmap avec EAN
	public static HashMap<String, Document> docsEAN = new HashMap<String,Document>();
	
	public static HashMap<String, Serie> series;
	
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
	
	public static void ajouterDocument(Document d, boolean isLivre) {
		
		if (docsEAN.containsKey(d.getEAN())) 
			//throw new DocumentExistException("le document existe deja");
		
		
		documents.add(d);
		docsEAN.put(d.getEAN(), d);	
		if (isLivre) {
			Livre L = (Livre) d;
			docsISBN.put(L.getISBN(), L);
		}
		
	}
	
	
	public static boolean ajouterUtilisateur(Utilisateur u, Bibliotheque b) {
		return false;		
		
	}
	
	public static void consulterDocuments() {
		
		for(Document d : documents) {
			
			System.out.println(d);
		}
		
	}
	
	
	
}
