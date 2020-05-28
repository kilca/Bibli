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
	 * permet l'affichage, trié par date, des documents d'une série
	 * @param titre		le titre de la serie
	 * @return faux (false) si la série n'est pas trouvé dans le systeme, vrai (true) sinon
	 */
	
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
	
	/**
	 * permet l'affichage, trié par date, des documents d'une série stocké dans une bibliotheque
	 * @param titre		le titre de la serie
	 * @param bibli		la bibliotheque dans laquel on fait la recherche
	 * @return faux (false) si la série n'est pas trouvé dans le systeme ou si aucun document de la serie n'est stocké dans la bibliotheque, vrai (true) sinon
	 */
	
	public static boolean afficherBibliSerie(Bibliotheque bibli, String titre) {
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
		boolean isPresent = false;
		for(Document d : docs) {
			if(bibli.documentsHeberge.contains(d)) {
				System.out.println(d);
				isPresent = true;
			}
		}
		if(!isPresent) {
			System.err.println("la bibliotheque ne contient aucun document de cette serie");
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
			System.out.println("aucun document de cet auteur n'est stocke dans la base de donnee.");
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
			System.out.println("aucun document de cet auteur n'est stocke dans la base de donnee.");
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
			System.out.println("aucun document de cet auteur n'est stocke dans la base de donnee.");
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
			System.out.println("Ce document n'est pas dans le systeme");
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
			System.out.println("Ce document n'est pas dans le systeme");
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
	 */
	
	public static boolean NbDocuments(String sBegin, String sEnd) {
		int begin = Integer.parseInt(sBegin);
		int end = Integer.parseInt(sEnd);
		int nb = 0;
		boolean exist = false;
		if(begin > end) {
			System.err.println("l'annee initial doit etre inferieur ou egal a l'annee final");
			return false;
		}
		for(Document doc : documents) {
			if(doc.dateToInt() >= begin && doc.dateToInt() <= end) {
				nb++;
				exist = true;
			}
		}
		System.out.println(nb + " documents");
		if(!exist) {
			System.out.println("aucun document n'est inclu dans cette interval de temps");
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
	 */
	
	public static void ajouterSerie(Serie serie) {
		String serieName = serie.getTitre();
		if (serieName == null) {
			System.err.println("error serie title null");
			return;
		}
		if (!series.containsKey(serieName))
			series.put(serieName, serie);
		
	}
	
	/**
	 * réaliser l'ajout d'une Bibliotheque dans le systeme
	 * @param bibli		la Bibliotheque à ajouter dans le systeme
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
		if(bibli.utilisateurs.contains(user)) {
			System.err.println("this user already exist in this bibliotheque");
			return false;
		}
		if(user != null && bibli != null) {
			user.setInscription(bibli);
			System.out.println("user added");
			return bibli.utilisateurs.add(user);
		}
		System.err.println("cannot add utilisateur");
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
