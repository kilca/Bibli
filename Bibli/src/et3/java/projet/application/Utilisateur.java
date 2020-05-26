package et3.java.projet.application;

import java.util.List;
import et3.java.projet.application.documents.*;

public class Utilisateur {

	public final int MAX_EMPRUNT = 5;
	private Bibliotheque inscription;
	private int quota = 5;
	
	public List<Document> documentsEmprunte;
	
	private String nom;//obligatoire pour la remise
	
	public Utilisateur(int quota, String nom) {
		
		if (quota <= MAX_EMPRUNT)
			this.quota = quota;
		
	}
	public void inscription(Bibliotheque bibli) {
		this.inscription = bibli;
		bibli.utilisateurs.add(this);
	}
	
<<<<<<< HEAD
	public String getNom() {
		return nom;
	}
	
=======
	public void emprunter(Document doc) {
		if(inscription != null && inscription.documentsHeberge.contains(doc)) {
			if(inscription.nbDocuments.get(doc) > 0 && quota > 0) {
				inscription.RetirerUniteDocument(doc);
				quota--;
				documentsEmprunte.add(doc);
			}
		}
	}
	
	public void rendre(Document doc) {
		if(documentsEmprunte.contains(doc)) {
			inscription.ajouterDocument(doc, 1);
			documentsEmprunte.remove(doc);
			quota++;
		}
	}
>>>>>>> branch 'master' of https://github.com/super-cokil/Bibli.git
}
