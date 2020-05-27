package et3.java.projet.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import et3.java.projet.application.documents.*;

public class Bibliotheque {

	//Limite stock ?
	
	public String name;
	
	//A Mettre en private ?
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
		
	}
	
	public void ajouterDocument(Document d, int nbDocument) {
		if (!documentsHeberge.contains(d)) {
			documentsHeberge.add(d);
			if(d.getEAN() != null) {
				docsEAN.put(d.getEAN(), d);
			}
			if(d instanceof Livre) {
				if(((Livre)d).getISBN() != "") {
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
	
	//-----------Affichage------------------
	
	public void afficherDocs() {
		
		for(Document d : documentsHeberge) {
			System.out.println(d);
			
		}
		
	}
	//----------Fin Affichage ----------------
	
	
}
