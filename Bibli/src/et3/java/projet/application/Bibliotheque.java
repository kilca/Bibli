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
				else{ nbDocuments.replace(d, t);
				return true;
			}
		}
		return false;
		
	}
	
	public boolean donnerDocumentBibli(Document d, Bibliotheque b) {
		if(!documentsHeberge.contains(d)) {
			System.err.println("la bibliotheque ne possede pas ce document");
			return false;
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
			System.out.println("aucun document de cet auteur n'est stocke dans cette bibliotheque.");
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
			System.out.println("aucun document de cet auteur n'est stocke dans cette bibliotheque.");
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
			System.out.println("aucun document de cet auteur n'est stocke dans cette bibliotheque.");
		}
		return exist;
	}
	
	public boolean afficherDocISBN(String ISBN) {

		Document d = livreISBN.get(ISBN);
		if (d == null) {
			System.out.println("Ce document n'est pas dans la bibliotheque");
			return false;
		}
		System.out.println(d);
		return true;
	}
	
	public boolean afficherDocEAN(String EAN) {

		Document d = docsEAN.get(EAN);
		if (d == null) {
			System.out.println("Ce document n'est pas dans la bibliotheque");
			return false;
		}
		System.out.println(d);
		return true;
	}
	
	public boolean NbDocuments(String sBegin, String sEnd) {
		int begin = Integer.parseInt(sBegin);
		int end = Integer.parseInt(sEnd);
		boolean exist = false;
		if(begin > end) {
			System.err.println("l'annee initial doit etre inferieur ou egal a l'annee final");
			return false;
		}
		for(Document doc : documentsHeberge) {
			if(doc.dateToInt() >= begin && doc.dateToInt() <= end) {
				System.out.println(doc);
				exist = true;
			}
		}
		if(!exist) {
			System.out.println("aucun document n'est inclu dans cette interval de temps");
		}
		return exist;
	}
	//----------Fin Affichage ----------------
	
	
}
