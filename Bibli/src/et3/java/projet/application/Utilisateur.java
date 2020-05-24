package et3.java.projet.application;

import java.util.List;
import et3.java.projet.application.documents.*;

public class Utilisateur {

	public final int MAX_EMPRUNT = 5;
	
	private int quota = 5;
	
	public List<Document> documentsEmprunte;
	
	public Utilisateur(int quota) {
		
		if (quota <= MAX_EMPRUNT)
			this.quota = quota;
		
	}
	
}
