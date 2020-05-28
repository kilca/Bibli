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
	
	public void ajouterDocument(Document d, int nbDocument) {
		if (!documentsHeberge.contains(d)) {
			documentsHeberge.add(d);
			if(d.getEAN() != null && d.getEAN() != "") {
				docsEAN.put(d.getEAN(), d);
			}
			if(d instanceof Livre) {
				if(((Livre)d).getISBN() != null && ((Livre)d).getISBN() != "") {
					livreISBN.put(((Livre) d).getISBN(), (Livre) d);
				}
			}
			
		}

		if (!nbDocuments.containsKey(d)) {
			nbDocuments.put(d, nbDocument);
		}else {
			nbDocuments.put(d, nbDocuments.get(d) + nbDocument);
		}
	}
	
	public boolean RetirerUniteDocument(Document d) { // permet de retirer un document dans nbDocuments
		if(this.documentsHeberge.contains(d) && nbDocuments.get(d) > 0) {
				Integer t = nbDocuments.get(d) - 1;
				if(t < 1) {
					nbDocuments.remove(d);
					documentsHeberge.remove(d);
				}
				else{ 
					nbDocuments.replace(d, t);
				}
				return true;
		}
		return false;
		
	}
	
	public boolean donnerDocumentBibli(Document d, Bibliotheque b) throws DocumentNotFoundException {
		if(!documentsHeberge.contains(d)) {
			throw new DocumentNotFoundException("the librairy does not contains this document",d);
		}
		else {
			RetirerUniteDocument(d);
			b.ajouterDocument(d, 1);
			return true;
		}
	}
	//-----------Affichage------------------
	
	public void afficherDocs() {
		
		for(Document d : documentsHeberge) {
			System.out.println(d);
			
		}
		
	}
	
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
	
	public boolean afficherDocISBN(String ISBN) {

		Document d = livreISBN.get(ISBN);
		if (d == null) {
			System.out.println("this document is not in the librairy");
			return false;
		}
		System.out.println(d);
		return true;
	}
	
	public boolean afficherDocEAN(String EAN) {

		Document d = docsEAN.get(EAN);
		if (d == null) {
			System.out.println("this document is not in the librairy");
			return false;
		}
		System.out.println(d);
		return true;
	}
	
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
