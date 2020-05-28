package et3.java.projet.application;

import java.util.ArrayList;
import java.util.List;
import et3.java.projet.application.documents.*;

public class Utilisateur{

	public final int MAX_EMPRUNT = 5;
	private Bibliotheque inscription;
	private int quota = 5;
	
	public List<Document> documentsEmprunte = new ArrayList<Document>();
	
	private String nom;//obligatoire pour la remise
	
	public Utilisateur(int quota, String nom) {
		this.nom=nom;
		
		if (quota <= MAX_EMPRUNT)
			this.quota = quota;
		
	}
	
	public String getNom() {
		return nom;
	}
	
	/**
	 * verifie si les deux utilisateurs sont identique par leurs noms
	 * @param o		l'objet à tester
	 * @return vrai(true) si l'objet est un utilisateur et a le meme nom que l'utilisateur appelant la fonction, faux(false) sinon
	 */
	@Override 
	public boolean equals(Object o) {
		if(o != null && o instanceof Utilisateur) {
			return this.nom.equals(((Utilisateur)o).nom);
		}
		return false;
	}
	
	/**
	 * permet l'emprunt d'un document par l'utilisateur à sa bibliotheque
	 * @param doc		le document à emprunter
	 * @return vrai(true) si l'emprunt du document a fonctionné, faux(false) sinon
	 */
	
	public boolean emprunter(Document doc) {
		if(inscription != null && inscription.documentsHeberge.contains(doc)) {
			if(inscription.nbDocuments.get(doc) > 0 && quota > 0) {
				inscription.RetirerUniteDocument(doc);
				quota--;
				System.out.println("the user can now borrow " + quota + " documents");
				documentsEmprunte.add(doc);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * permet la remise d'un document par l'utilisateur à sa bibliotheque
	 * @param doc		le document à emprunter
	 * @return vrai(true) si la remise du document a fonctionné, faux(false) sinon
	 */
	
	public Boolean rendre(Document doc) {
		if(documentsEmprunte.contains(doc)) {
			inscription.ajouterDocument(doc, 1);
			documentsEmprunte.remove(doc);
			quota++;
			System.out.println("the user can now borrow " + quota + " documents");
			return true;
		}
		return false;
	}

	public void setInscription(Bibliotheque inscription) {
		this.inscription = inscription;
	}
	
	//-----------Affichage------------------
	
	/**
	 * affiche la liste des documents empruntés par l'utilisateur
	 */
	
	public void userDoc() {
		System.out.println(nom + " documents :");
		if(documentsEmprunte.size() > 0) {
			for(Document d : documentsEmprunte) {
				System.out.println("	" + d);
			}
		}else System.out.println("no document found");
	}
}
