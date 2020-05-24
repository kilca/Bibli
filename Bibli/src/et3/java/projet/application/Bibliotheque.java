package et3.java.projet.application;

import java.util.List;
import et3.java.projet.application.documents.*;

public class Bibliotheque {

	//Limite stock ?
	
	public String name;
	
	public List<Utilisateur> utilisateurs;
	public List<Document> documentsHeberge;
	
	public Bibliotheque(String n) {
		this.name = n;
		
	}
	
	//-----------Affichage------------------
	
	public void afficherDocs() {
		
		for(Document d : documentsHeberge) {
			System.out.println(d);
			
		}
		
	}
	//----------Fin Affichage ----------------
	
	
}
