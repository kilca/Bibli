package et3.java.projet.application.documents;

import java.util.Date;
import et3.java.projet.application.*;

public class Document {

	protected int nNotice;
	protected String EAN;
	protected String titre;
	protected String editeur;
	protected String date;
	protected String nomAuteur;
	protected String prenomAuteur;
	protected boolean estDisponible;
	
	public Document(String eAN, String titre, String editeur, String date, String nomAuteur, String prenomAuteur) {
		EAN = eAN;
		this.titre = titre;
		this.editeur = editeur;
		this.date = date;
		this.nomAuteur = nomAuteur;
		this.prenomAuteur = prenomAuteur;
	}
	protected int numSerie = 0;
	protected Serie serie;
	
}
