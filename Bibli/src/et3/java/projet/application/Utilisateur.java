package et3.java.projet.application;

import java.util.List;
import et3.java.projet.application.documents.*;

public class Utilisateur {

	public final int MAX_EMPRUNT = 5;
	
	private int quota = 5;
	
	public List<Document> documentsEmprunte;
	
	private String nom;//obligatoire pour la remise
	
	public Utilisateur(int quota, String nom) {
		
		if (quota <= MAX_EMPRUNT)
			this.quota = quota;
		
	}
	
	public String getNom() {
		return nom;
	}
	
}
