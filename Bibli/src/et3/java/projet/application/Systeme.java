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

/**
 * classe static réalisant la partie global des opérations de modification et d'affichage sur les données.
 */

public class Systeme {

	public static List<Bibliotheque> bibliotheques = new ArrayList<Bibliotheque>();
	
	public static List<Document> documents = new ArrayList<Document>();
	
	//hashmap avec ISBN
	public static HashMap<String, Livre> livreISBN = new HashMap<String,Livre>();
	//hashmap avec EAN
	public static HashMap<String, Document> docsEAN = new HashMap<String,Document>();
	
	public static HashMap<String, Serie> series = new HashMap<String,Serie>();
	
	
	//-----------Affichage------------------
	
	/**
	 * permet l'affichage de tout les documents stocké dans le systeme
	 */
	public static void afficherDocs() {
		
		for(Document d : documents) {
			System.out.println(d);
			
		}
		
	}
	
	/**
	 * affiche les documents d'une serie 
	 * @param titre		le titre de la serie
	 * @return vrai(true) si la serie est trouvée, faux(false) sinon
	 * @throws SerieNotFoundException
	 */
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
	
	/**
	 * permet l'affichage, trié par date, des documents d'une série stocké dans une bibliotheque
	 * @param titre		le titre de la serie
	 * @param bibli		la bibliotheque dans laquel on fait la recherche
	 * @return faux (false) si la série n'est pas trouvé dans le systeme ou si aucun document de la serie n'est stocké dans la bibliotheque, vrai (true) sinon
	 * @throws SerieNotFoundException
	 */
	
	public static boolean afficherBibliSerie(Bibliotheque bibli, String titre) throws SerieNotFoundException {
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
			if(bibli.documentsHeberge.contains(d)) {
				System.out.println(d);
				isPresent = true;
			}
		}
		if(!isPresent) {
			System.out.println("the librairy does not contain any document of this serie");
		}
		return isPresent;
	}
	
	/**
	 * afficher la liste des documents d'un auteur dans le systeme
	 * @param nom			le nom de l'auteur 
	 * @param prenom		le prenom de l'auteur
	 * @return faux (false) si aucun document de l'auteur n'est présent dans la bibliotheque ou vrai (true) sinon
	 */
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
	
	/**
	 * afficher la liste des documents d'un auteur dans le systeme, en fonction de sont prénom
	 * @param prenom		le prenom de l'auteur
	 * @return faux (false) si aucun document de l'auteur n'est présent dans la bibliotheque ou vrai (true) sinon
	 */
	
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
	
	/**
	 * afficher la liste des documents d'un auteur dans le systeme, en fonction de sont nom
	 * @param nom		le nom de l'auteur
	 * @return faux (false) si aucun document de l'auteur n'est présent dans la bibliotheque ou vrai (true) sinon
	 */
	
	
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
	
	/**
	 * afficher un documents avec son ISBN dans le systeme
	 * @param ISBN		l'ISBN du document 
	 * @return vrai (true) si le document est trouvé ou faux (false) sinon
	 */
	
	public static boolean afficherDocISBN(String ISBN) {

		Document d = livreISBN.get(ISBN);
		if (d == null) {
			System.out.println("This document is not in the librairy");
			return false;
		}
		System.out.println(d);
		return true;
	}
	
	/**
	 * afficher un documents avec son EAN dans le systeme
	 * @param EAN		l'EAN du document 
	 * @return vrai (true) si le document est trouvé ou faux (false) sinon
	 */
	
	public static boolean afficherDocEAN(String EAN) {

		Document d = docsEAN.get(EAN);
		if (d == null) {
			System.out.println("This document is not in the librairy");
			return false;
		}
		System.out.println(d);
		return true;
	}
	
	/**
	 * afficher le nombre de document par type dans le systeme entre deux dates
	 * @param sBegin       l'année initial
	 * @param sEnd         l'année final
	 * @return faux (false) si ancun document n'est trouvé entre les deux années ou si l'année intial est plus grande que l'année finale,vrai (true) sinon
	 * @throws WrongArgumentFormatException lorsque le format de la date est incorrect
	 * @throws WrongArgumentLogicException lorsque la grandeur des date est incorrect
	 */
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
	
	/**
	 * réaliser le chargement du document csv
	 * @param dir       le lien vers le document csv
	 */
	
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
	
	/**
	 * réaliser l'ajout d'une serie dans le systeme
	 * @param serie		la serie à ajouter dans le systeme
	 * @throws SerieNotFoundException
	 */
	public static void ajouterSerie(Serie serie) throws SerieNotFoundException {
		String serieName = serie.getTitre();
		if (serieName == null) {
			throw new SerieNotFoundException("error serie title null");
		}
		if (!series.containsKey(serieName))
			series.put(serieName, serie);
		
	}
	
	/**
	 * réaliser l'ajout d'une Bibliotheque dans le systeme
	 * @param bibli		la Bibliotheque à ajouter dans le systeme
	 * @return vrai si il l'a bien ajoute, faux sinon
	 */
	public static boolean ajouterBibliotheque(Bibliotheque bibli) {
		
		if (getBibliothequeByName(bibli.name) != null) {
			//throw error bibliotheque already exist with name
			return false;
		}
		
		return bibliotheques.add(bibli);
		
	}
	
	/**
	 * vérifier si un document possede un EAN ou un ISBN deja existant dans le systeme
	 * @param doc		le document à vérifier
	 * @return vrai(true) si le document possede un EAN ou un ISBN, faux(false) sinon
	 */
	
	private static boolean documentISBNorEANalreadyExist(Document doc) {
		boolean hasISBN = false;
		
		if (doc.isLivre()) {
			Livre L = (Livre) doc;
			if (L.getISBN() != null && !L.getISBN().equals(""))
				hasISBN = livreISBN.containsKey(L.getISBN());
		}
		
		
		
		boolean hasEAN = docsEAN.containsKey(doc.getEAN());
		
		return (hasISBN || hasEAN);
		
	}
	
	
	/**
	 * ajouter un document dans le systeme
	 * @param doc		le document à ajouter
	 * @return vrai si il l'a bien ajoute, faux sinon
	 */
	
	public static Document ajouterDocument(Document doc) {
		
		//if (docsEAN.containsKey(d.getEAN())) 
			//throw new DocumentExistException("le document existe deja");
		
		if (documentISBNorEANalreadyExist(doc)) {
			return null;
		}
		
		documents.add(doc);
		
		if (doc.getEAN() != null && !doc.getEAN().equals(""))
			docsEAN.put(doc.getEAN(), doc);	
			
		if (doc.isLivre()) {
			Livre L = (Livre) doc;
			if (L.getISBN() != null && !L.getISBN().equals("")) {
				livreISBN.put(L.getISBN(), L);
			}
		}
		
		return doc;
		
	}
	
	/**
	 * ajouter un utilisateur dans une bibliotheque du systeme
	 * @param user		l'utilisateur à ajouter
	 * @param bibli		la bibliotheque dans laquelle il est ajouté
	 * @return vrai(true) si l'utilisateur a bien été ajouté, faux(false) sinon
	 */
	public static boolean ajouterUtilisateur(Utilisateur user, Bibliotheque bibli) {
		if(user != null && bibli != null) {
			user.setInscription(bibli);
			System.out.println("user added");
			return bibli.utilisateurs.add(user);
		}
		System.err.println("cannot add user");
		return false;		
		
	}

	
	/**
	 * trouver un utilisateur dans une bibliotheque du systeme
	 * @param nom		le nom de l'utilisateur à trouver
	 * @param bibli		la bibliotheque dans laquelle il est ajouté
	 * @return l'utilisateur s'il a été trouvé, null sinon
	 */
	
	
	public static Utilisateur getUtilisateur(String nom, Bibliotheque bibli) {
		for(Utilisateur u : bibli.utilisateurs) {
			if (u.getNom().equals(nom))
				return u;
		}
		return null;

	}
	
	/**
	 * trouver un Document par sont titre dans le systeme
	 * @param title		le titre du document à trouver
	 * @return le document s'il a été trouvé, null sinon
	 */
	
	public static Document getDocumentByTitle(String title) {
		for(Document d : documents) {
			if (d.getTitle().equals(title))
				return d;
		}
		return null;
	}
	
	/**
	 * trouver une Serie par sont titre dans le systeme
	 * @param name		le nom de la série à trouver
	 * @return la série si elle a été trouvé, null sinon
	 */
	
	public static Serie getSerieByName(String name) {
		return series.get(name);
	}
	
	
	
	
}
