package et3.java.projet.application;

import java.util.List;
import et3.java.projet.application.documents.*;

public class Bibliotheque {

	public String name;
	
	public List<Utilisateur> utilisateurs;
	public List<Document> documentsHerberge;
	
	public Bibliotheque(String n) {
		this.name = n;
		
	}
	
}
