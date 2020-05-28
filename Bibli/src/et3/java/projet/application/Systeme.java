package et3.java.projet.application;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import et3.java.projet.application.documents.*;
import et3.java.projet.application.exceptions.SerieNotFoundException;
import et3.java.projet.application.exceptions.command.WrongArgumentFormatException;
import et3.java.projet.application.exceptions.command.WrongArgumentLogicException;
import et3.java.projet.data.FileReader;

public class Systeme {

	public static List<Bibliotheque> bibliotheques = new ArrayList<Bibliotheque>();
	
	public static List<Document> documents = new ArrayList<Document>();
	
	//hashmap avec ISBN
	public static HashMap<String, Livre> livreISBN = new HashMap<String,Livre>();
	//hashmap avec EAN
	public static HashMap<String, Document> docsEAN = new HashMap<String,Document>();
	
	public static HashMap<String, Serie> series = new HashMap<String,Serie>();
	
	
	//-----------Affichage------------------
	
	public static void afficherDocs() {
		
		for(Document d : documents) {
			System.out.println(d);
			
		}
		
	}
	
	public static boolean afficherSerie(String titre) throws SerieNotFoundException {
		Serie s =series.get(titre);
		if (s == null) {
			throw new SerieNotFoundException("serie not found");
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
	
	public static boolean afficherBibliSerie(Bibliotheque b, String titre) throws SerieNotFoundException {
		Serie s =series.get(titre);
		if (s == null) {
			throw new SerieNotFoundException("serie not found");
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
		boolean isPresent = false;
		for(Document d : docs) {
			if(b.documentsHeberge.contains(d)) {
				System.out.println(d);
				isPresent = true;
			}
		}
		if(!isPresent) {
			System.out.println("the librairy does not contain any document of this serie");
		}
		return isPresent;
	}
	
	public static boolean afficherDocAuteur(String prenom, String nom) {
		boolean exist = false;
		for(Document doc : documents) {
			if(prenom.equals(doc.getPrenomAuteur()) && nom.equals(doc.getNomAuteur())) {
				System.out.println(doc);
				exist = true;
			}
		}
		if(!exist) {
			System.out.println("there is no document from this author in the database.");
		}
		return exist;
	}
	
	public static boolean afficherDocAuteuravecPrenom(String prenom) {
		boolean exist = false;
		for(Document doc : documents) {
			if(prenom.equals(doc.getPrenomAuteur())) {
				System.out.println(doc);
				exist = true;
			}
		}
		if(!exist) {
			System.out.println("there is no document from this author in the database.");
		}
		return exist;
	}
	
	public static boolean afficherDocAuteuravecNom(String nom) {
		boolean exist = false;
		for(Document doc : documents) {
			if(nom.equals(doc.getNomAuteur())) {
				System.out.println(doc);
				exist = true;
			}
		}
		if(!exist) {
			System.out.println("there is no document from this author in the database.");
		}
		return exist;
	}
	
	public static boolean afficherDocISBN(String ISBN) {

		Document d = livreISBN.get(ISBN);
		if (d == null) {
			System.out.println("This document is not in the librairy");
			return false;
		}
		System.out.println(d);
		return true;
	}
	
	public static boolean afficherDocEAN(String EAN) {

		Document d = docsEAN.get(EAN);
		if (d == null) {
			System.out.println("This document is not in the librairy");
			return false;
		}
		System.out.println(d);
		return true;
	}
	
	
	public static boolean NbDocuments(String sBegin, String sEnd) throws WrongArgumentFormatException, WrongArgumentLogicException {
		
		int begin = 0;
		int end= 0;
		
		try {
			begin = Integer.parseInt(sBegin);
			end = Integer.parseInt(sEnd);
					
		}catch (NumberFormatException e) {
			throw new WrongArgumentFormatException("wrong date format");
		}
		
		int nbAutre = 0;
		int nbBD = 0;
		int nbCarte = 0;
		int nbCD = 0;
		int nbJeuDeSociete = 0;
		int nbJeuVideo = 0;
		int nbLivre = 0;
		int nbPartition =0;
		int nbRevue = 0;
		int nbVinyle = 0;
		
		boolean exist = false;
		if(begin > end) {
			throw new WrongArgumentLogicException("The intial date must be before the final date");
		}
		for(Document doc : documents) {
			if(doc.dateToInt() >= begin && doc.dateToInt() <= end) {
				
				//marcherais aussi d'utiliser un enum dans les classes mais reste plus opti que class.getSimpleName()
				if (!exist)
					exist = true;
				
				if (doc instanceof Autre)
					nbAutre++;
				else if (doc instanceof BD)
					nbBD++;
				else if (doc instanceof Carte)
					nbCarte++;
				else if (doc instanceof CD)
					nbCD++;
				else if (doc instanceof JeuDeSociete)
					nbJeuDeSociete++;
				else if (doc instanceof JeuVideo)
					nbJeuVideo++;
				else if (doc instanceof Partition)
					nbPartition++;
				else if (doc instanceof Revue)
					nbRevue++;
				else if (doc instanceof Vinyle)
					nbVinyle++;
				else if (doc instanceof Livre)//a mettre en dernier car reconnait ses enfants
					nbLivre++;
				
			}
		}
		if(!exist) {
			System.out.println("there is no document in the time interval");
		}else {
			System.out.println("Autre : "+nbAutre);
			System.out.println("BD : "+nbBD);
			System.out.println("Carte : "+nbCarte);
			System.out.println("CD : "+nbCD);
			System.out.println("JeuDeSociete : "+nbJeuDeSociete);
			System.out.println("JeuVideo : "+nbJeuVideo);
			System.out.println("Livre : "+nbLivre);
			System.out.println("Partition : "+nbPartition);
			System.out.println("Revue : "+nbRevue);
			System.out.println("Vinyle : "+nbVinyle);
		}
		return exist;
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
	
	public static void ajouterSerie(Serie serie) throws SerieNotFoundException {
		String serieName = serie.getTitre();
		if (serieName == null) {
			throw new SerieNotFoundException("error serie title null");
		}
		if (!series.containsKey(serieName))
			series.put(serieName, serie);
		
	}
	
	public static boolean ajouterBibliotheque(Bibliotheque b) {
		
		if (getBibliothequeByName(b.name) != null) {
			//throw error bibliotheque already exist with name
			return false;
		}
		
		return bibliotheques.add(b);
		
	}
	
	public static boolean documentHasISBNorEAN(Document d) {
		boolean hasISBN = false;
		
		if (d.isLivre()) {
			Livre L = (Livre) d;
			if (L.getISBN() != null && !L.getISBN().equals(""))
				hasISBN = livreISBN.containsKey(L.getISBN());
		}
		
		
		
		boolean hasEAN = docsEAN.containsKey(d.getEAN());
		
		return (hasISBN || hasEAN);
		
	}
	
	public static Document ajouterDocument(Document d) {
		
		//if (docsEAN.containsKey(d.getEAN())) 
			//throw new DocumentExistException("le document existe deja");
		
		if (documentHasISBNorEAN(d)) {
			return null;
		}
		
		documents.add(d);
		
		if (d.getEAN() != null && !d.getEAN().equals(""))
			docsEAN.put(d.getEAN(), d);	
			
		if (d.isLivre()) {
			Livre L = (Livre) d;
			if (L.getISBN() != null && !L.getISBN().equals("")) {
				livreISBN.put(L.getISBN(), L);
			}
		}
		
		return d;
		
	}
	
	
	public static boolean ajouterUtilisateur(Utilisateur u, Bibliotheque b) {
		if(u != null && b != null) {
			u.setInscription(b);
			return b.utilisateurs.add(u);
		}
		System.err.println("cannot add user");
		return false;		
		
		//todo (attention verifie qu'il n'y en ai pas qui aient le meme nom)
	}

	
	//sera a utiliser pour l'emprunt et la remise
	public static Utilisateur getUtilisateur(String nom, Bibliotheque b) {
		for(Utilisateur u : b.utilisateurs) {
			if (u.getNom().equals(nom))
				return u;
		}
		return null;

	}
	
	public static Document getDocumentByTitle(String title) {
		for(Document d : documents) {
			if (d.getTitle().equals(title))
				return d;
		}
		return null;
	}
	
	public static Serie getSerieByName(String name) {
		return series.get(name);
	}
	
	
	
	
}
