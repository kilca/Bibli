package et3.java.projet.application;

import java.util.ArrayList;
import java.util.List;

import et3.java.projet.application.documents.*;

public class Serie {

	private String titre;
	
	private List<Document> documents;
	

	public Serie(String titre) {
		
		this.titre = titre;
		documents = new ArrayList<Document>();
		
	}
	
	public String getTitre() {
		return titre;
	}
	
	/**
	 * permet l'affichage d'une s�rie
	 * @return la description de la s�rie
	 */
	@Override
	public String toString() {
		return titre+":"+documents.size();
	}
	
	/**
	 * permet l'ajout d'un document � la s�rie
	 * @return vrai (true) si l'ajout a fonctionn�, faux (false) sinon
	 */
	public boolean ajouterDocument(Document d) {
		return documents.add(d);	
	}
	
	public List<Document> getDocuments(){
		return documents;
		
	}
	
}
