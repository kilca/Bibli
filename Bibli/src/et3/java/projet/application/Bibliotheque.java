package et3.java.projet.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import et3.java.projet.application.documents.*;
import et3.java.projet.application.exceptions.DocumentNotFoundException;
import et3.java.projet.application.exceptions.command.WrongArgumentFormatException;
import et3.java.projet.application.exceptions.command.WrongArgumentLogicException;

public class Bibliotheque {

	//Limite stock ?
	
	public String name;
	
	//A Mettre en private ...non
	public List<Utilisateur> utilisateurs;
	public List<Document> documentsHeberge;
	
	public HashMap<Document, Integer> nbDocuments;
	
	public HashMap<String, Livre> livreISBN = new HashMap<String,Livre>();
	//hashmap avec EAN
	public HashMap<String, Document> docsEAN = new HashMap<String,Document>();

	public Bibliotheque(String n) {
		this.name = n;
		
		documentsHeberge = new ArrayList<Document>();
		nbDocuments = new HashMap<Document,Integer>();
		utilisateurs = new ArrayList<Utilisateur>();
		
	}
	
	/**
	 * Ajoute un document à la bibliotheque, s'il existe.
	 * @param doc          le document à ajouter
	 * @param nbDocument   le nombre de document du meme type. 
	 */
	public void ajouterDocument(Document doc, int nbDocument) {
		if (!documentsHeberge.contains(doc)) {
			documentsHeberge.add(doc);
			if(doc.getEAN() != null && doc.getEAN() != "") {
				docsEAN.put(doc.getEAN(), doc);
			}
			if(doc instanceof Livre) {
				if(((Livre)doc).getISBN() != null && ((Livre)doc).getISBN() != "") {
					livreISBN.put(((Livre) doc).getISBN(), (Livre) doc);
				}
			}
			
		}

		if (!nbDocuments.containsKey(doc)) {
			nbDocuments.put(doc, nbDocument);
		}else {
			nbDocuments.put(doc, nbDocuments.get(doc) + nbDocument);
		}
	}
	
	/**
	 * Retirer une unitée du document, si il existe. Si il n'y avait qu'une seule unité du document cela supprime le document de la liste.
	 * @param doc          le document à supprimer
	 * @return vrai (true) si la suppression à fonctionnée ou faux (false) sinon
	 */
	
	public boolean RetirerUniteDocument(Document doc) { // permet de retirer un document dans nbDocuments
		if(this.documentsHeberge.contains(doc) && nbDocuments.get(doc) > 0) {
				Integer t = nbDocuments.get(doc) - 1;
				if(t < 1) {
					nbDocuments.remove(doc);
					documentsHeberge.remove(doc);
				}
				else{ 
					nbDocuments.replace(doc, t);
				}
				return true;
		}
		return false;
		
	}
	
	/**
	 * Transmettre un document à une autre bibliotheque
	 * @param doc          le document à transmettre 
	 * @param bibli        la bibliotheque qui reçoit le document
	 * @return vrai (true) si la transaction à fonctionnée ou faux (false) sinon
	 * @throws DocumentNotFoundException est renvoye lorsque la librairy ne contient pas le document
	 */
	
	public boolean donnerDocumentBibli(Document doc, Bibliotheque bibli) throws DocumentNotFoundException {
		if(!documentsHeberge.contains(doc)) {
			throw new DocumentNotFoundException("the librairy does not contains this document",doc);
		}
		else {
			RetirerUniteDocument(doc);
			bibli.ajouterDocument(doc, 1);
			return true;
		}
	}
	//-----------Affichage------------------
	
	/**
	 * afficher la liste des documents possedes par la bibliotheque
	 */

	public void afficherDocs() {
		
		for(Document d : documentsHeberge) {
			System.out.println(d);
			
		}
		
	}
	
	/**
	 * afficher la liste des documents d'un auteur detenue par la bibliotheque
	 * @param nom          le nom de l'auteur 
	 * @param prenom       le prenom de l'auteur
	 * @return faux (false) si aucun document de l'auteur n'est présent dans la bibliotheque ou vrai (true) sinon
	 */
	
	public boolean afficherDocAuteur(String prenom, String nom) {
		boolean exist = false;
		for(Document doc : documentsHeberge) {
			if(prenom.equals(doc.getPrenomAuteur()) && nom.equals(doc.getNomAuteur())) {
				System.out.println(doc);
				exist = true;
			}
		}
		if(!exist) {
			System.out.println("there is not document from this author in the librairy.");
		}
		return exist;
	}
	
	/**
	 * afficher la liste des documents d'un auteur detenue par la bibliotheque en foction de son prenom
	 * @param prenom       le prenom de l'auteur
	 * @return faux (false) si aucun document de l'auteur n'est présent dans la bibliotheque ou vrai (true) sinon
	 */
	
	public boolean afficherDocAuteuravecPrenom(String prenom) {
		boolean exist = false;
		for(Document doc : documentsHeberge) {
			if(prenom.equals(doc.getPrenomAuteur())) {
				System.out.println(doc);
				exist = true;
			}
		}
		if(!exist) {
			System.out.println("there is not document from this author in the librairy.");
		}
		return exist;
	}
	
	/**
	 * afficher la liste des documents d'un auteur detenue par la bibliotheque en fonction de son nom
	 * @param nom          le nom de l'auteur 
	 * @return faux (false) si aucun document de l'auteur n'est présent dans la bibliotheque ou vrai (true) sinon
	 */
	
	public boolean afficherDocAuteuravecNom(String nom) {
		boolean exist = false;
		for(Document doc : documentsHeberge) {
			if(nom.equals(doc.getNomAuteur())) {
				System.out.println(doc);
				exist = true;
			}
		}
		if(!exist) {
			System.out.println("there is not document from this author in the librairy.");
		}
		return exist;
	}
	
	/**
	 * afficher un documents avec son ISBN dans la bibliotheque
	 * @param ISBN          l'ISBN du document 
	 * @return vrai (true) si le document est trouvé ou faux (false) sinon
	 */
	
	public boolean afficherDocISBN(String ISBN) {

		Document d = livreISBN.get(ISBN);
		if (d == null) {
			System.out.println("this document is not in the librairy");
			return false;
		}
		System.out.println(d);
		return true;
	}
	
	/**
	 * afficher un documents avec son EAN dans la bibliotheque
	 * @param EAN          l'EAN du document 
	 * @return vrai (true) si le document est trouvé ou faux (false) sinon
	 */
	
	public boolean afficherDocEAN(String EAN) {

		Document d = docsEAN.get(EAN);
		if (d == null) {
			System.out.println("this document is not in the librairy");
			return false;
		}
		System.out.println(d);
		return true;
	}
	
	/**
	 * affiche le nombre de document par type entre deux années
	 * @param sBegin       l'année initial
	 * @param sEnd         l'année final
	 * @return faux (false) si ancun document n'est trouvé entre les deux années ou si l'année intial est plus grande que l'année finale,vrai (true) sinon
	 * @throws WrongArgumentFormatException est envoye lorsque le format de la date est incorrect
	 * @throws WrongArgumentLogicException est envoye lorsque la grandeur des date est incorrect
	 */
	
	public boolean NbDocuments(String sBegin, String sEnd) throws WrongArgumentFormatException, WrongArgumentLogicException {
		
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
		for(Document doc : documentsHeberge) {
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
	
	
}
