package et3.java.projet.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import et3.java.projet.application.documents.*;

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
	 * Ajoute un document � la bibliotheque, s'il existe.
	 * @param doc          le document � ajouter
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
	 * Retirer une unit�e du document, si il existe. Si il n'y avait qu'une seule unit� du document cela supprime le document de la liste.
	 * @param doc          le document � supprimer
	 * @return vrai (true) si la suppression � fonctionn�e ou faux (false) sinon
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
	 * Transmettre un document � une autre bibliotheque
	 * @param doc          le document � transmettre 
	 * @param bibli        la bibliotheque qui re�oit le document
	 * @return vrai (true) si la transaction � fonctionn�e ou faux (false) sinon
	 */
	
	public boolean donnerDocumentBibli(Document doc, Bibliotheque bibli) {
		if(!documentsHeberge.contains(doc)) {
			System.err.println("la bibliotheque ne possede pas ce document");
			return false;
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
	 * @return faux (false) si aucun document de l'auteur n'est pr�sent dans la bibliotheque ou vrai (true) sinon
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
			System.out.println("aucun document de cet auteur n'est stocke dans cette bibliotheque.");
		}
		return exist;
	}
	
	/**
	 * afficher la liste des documents d'un auteur detenue par la bibliotheque en foction de son prenom
	 * @param prenom       le prenom de l'auteur
	 * @return faux (false) si aucun document de l'auteur n'est pr�sent dans la bibliotheque ou vrai (true) sinon
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
			System.out.println("aucun document de cet auteur n'est stocke dans cette bibliotheque.");
		}
		return exist;
	}
	
	/**
	 * afficher la liste des documents d'un auteur detenue par la bibliotheque en fonction de son nom
	 * @param nom          le nom de l'auteur 
	 * @return faux (false) si aucun document de l'auteur n'est pr�sent dans la bibliotheque ou vrai (true) sinon
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
			System.out.println("aucun document de cet auteur n'est stocke dans cette bibliotheque.");
		}
		return exist;
	}
	
	/**
	 * afficher un documents avec son ISBN dans la bibliotheque
	 * @param ISBN          l'ISBN du document 
	 * @return vrai (true) si le document est trouv� ou faux (false) sinon
	 */
	
	public boolean afficherDocISBN(String ISBN) {

		Document d = livreISBN.get(ISBN);
		if (d == null) {
			System.out.println("Ce document n'est pas dans la bibliotheque");
			return false;
		}
		System.out.println(d);
		return true;
	}
	
	/**
	 * afficher un documents avec son EAN dans la bibliotheque
	 * @param EAN          l'EAN du document 
	 * @return vrai (true) si le document est trouv� ou faux (false) sinon
	 */
	
	public boolean afficherDocEAN(String EAN) {

		Document d = docsEAN.get(EAN);
		if (d == null) {
			System.out.println("Ce document n'est pas dans la bibliotheque");
			return false;
		}
		System.out.println(d);
		return true;
	}
	
	/**
	 * affiche le nombre de document par type entre deux ann�es
	 * @param sBegin       l'ann�e initial
	 * @param sEnd         l'ann�e final
	 * @return faux (false) si ancun document n'est trouv� entre les deux ann�es ou si l'ann�e intial est plus grande que l'ann�e finale,vrai (true) sinon
	 */
	
	
	public boolean NbDocuments(String sBegin, String sEnd) {
		
		int begin = Integer.parseInt(sBegin);
		int end = Integer.parseInt(sEnd);
		
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
			System.err.println("l'annee initial doit etre inferieur ou egal a l'annee final");
			return false;
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
			System.out.println("aucun document n'est inclu dans cette interval de temps");
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
