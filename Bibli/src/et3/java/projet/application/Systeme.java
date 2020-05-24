package et3.java.projet.application;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	
	
	//-----------Affichage------------------
	
	public static void afficherDocs() {
		
		for(Document d : documents) {
			System.out.println(d);
			
		}
		
	}
	
	public static boolean afficherSerie(String titre) {
		Serie s =series.get(titre);
		if (s == null) {
			System.err.println("serie not found");
			return false;
		}
		List<Document> docs = s.getDocuments();
		
		 Collections.sort(docs, new Comparator<Document>() 
         {

			@Override
			public int compare(Document d1, Document d2) {

				return d1.dateToInt() - d2.dateToInt();
				//A verifier
			}

         }    
         );
		 
		for(Document d : docs) {
			System.out.println(d);
			
		}
		
		return true;
		
	}
	
	
	public static boolean afficherDocAuteur(String prenom, String nom) {

		System.out.println("not implemented (depend si utilise hashmap ou non");
		
		return true;
	}
	
	public static boolean afficherDocISBN(String ISBN) {

		Document d = docsISBN.get(ISBN);
		if (d == null) {
			System.err.println("document not found");
			return false;
		}
		
		return true;
	}
	
	public static boolean afficherDocEAN(String EAN) {

		Document d = docsEAN.get(EAN);
		if (d == null) {
			System.err.println("document not found");
			return false;
		}
		
		return true;
	}
	
	//----------Fin Affichage ----------------
	
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
		
	public static Bibliotheque getBibliothequeByName(String n) {
		for(Bibliotheque b : bibliotheques) {
			if (b.name.equals(n)) {
				return b;
			}
			
		}
		return null;
		
	}
	
	public static boolean ajouterBibliotheque(Bibliotheque b) {
		
		if (getBibliothequeByName(b.name) != null) {
			//throw error bibliotheque already exist with name
			return false;
		}
		
		return bibliotheques.add(b);
		
	}
	
	public static void ajouterDocument(Document d, boolean isLivre) {
		
		//if (docsEAN.containsKey(d.getEAN())) 
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
	
	
	
	
}
