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
		return "Document [nNotice=" + nNotice + ", EAN=" + EAN + ", titre=" + titre + ", editeur=" + editeur + ", date="
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
	
	public void setSerie(Serie s, int numSerie) {
		
		this.serie = s;
		this.numSerie = numSerie;
		
	}
	
	public Integer dateToInt() {
		String intValue = date.replaceAll("[^0-9]", "");
		Integer retour = null;
		try {
			retour = Integer.parseInt(intValue);
			
		}finally {
			
		}
		return retour;
	}
	
}
