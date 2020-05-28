package et3.java.projet.application.documents;

import java.util.Date;
import et3.java.projet.application.*;
/**
 * Classe document parente de tous les document
 * 
 *
 */
public class Document {

	protected int nNotice;//pourrait etre utilise pour d'autre csv
	protected String EAN;
	protected String titre;
	protected String editeur;
	protected String date;
	protected String nomAuteur;
	protected String prenomAuteur;
	protected boolean estDisponible;
	
	protected Serie serie;
	protected int numSerie = 0;
	
	public Document(String eAN, String titre, String editeur, String date, String prenomAuteur, String nomAuteur) {
		EAN = eAN;
		this.titre = titre;
		this.editeur = editeur;
		this.date = date;
		this.nomAuteur = nomAuteur;
		this.prenomAuteur = prenomAuteur;
	}
	
	@Override
	public String toString() {
		return "Document [EAN=" + EAN + ", titre=" + titre + ", editeur=" + editeur + ", date="
				+ date + ", nomAuteur=" + nomAuteur + ", prenomAuteur=" + prenomAuteur + ", estDisponible="
				+ estDisponible + ", numSerie=" + numSerie + ", serie=" + serie + "]";
	}
	
	public Serie getSerie() {
		return serie;
	}
	
	public String getEAN() {
		return EAN;
	}
	
	public String getNomAuteur() {
		return nomAuteur;
	}
	public String getPrenomAuteur() {
		return prenomAuteur;
	}
	public boolean isLivre() {
		return (this instanceof Livre);//Marche avec heritage
	}
	
	public String getTitle() {
		return titre;
	}
	
	/**
	 * Assigne une serie au document en l'ajoutant a la liste des document de la serie
	 * @param s		La serie qu'on ajoute
	 * @param numSerie		Le numero de serie du document (attention ne prend pas en compte les doublons de num)
	 */
	public void setSerie(Serie s, int numSerie) {
		
		this.serie = s;
		this.numSerie = numSerie;
		s.ajouterDocument(this);
	}
	
	/**
	 * retourne la date convertie en entier car
	 * certaines date ont l'annee dans leur texte mais aussi
	 * des mots ex : 2017 impr.
	 * @return		partie int de la date
	 */
	public int dateToInt() {
		String intValue = date.replaceAll("[^0-9]", "");
		int retour = -100000;
		if(intValue.equals("")) return retour;
		try {
			retour = Integer.parseInt(intValue);
			
		}finally {
			
		}
		return retour;
	}
	
}
