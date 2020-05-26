package et3.java.projet.application.documents;

import java.util.Date;

public class Partition extends Livre{
	public Partition(String eAN, String titre, String editeur, String date, String nomAuteur, String prenomAuteur) {
		super(eAN, titre, editeur, date, nomAuteur, prenomAuteur);
		// TODO Auto-generated constructor stub
	}
	public Partition(String eAN, String titre, String editeur, String date, String nomAuteur, String prenomAuteur, String ISBN) {
		super(eAN, titre, editeur, date, nomAuteur, prenomAuteur,ISBN);
		// TODO Auto-generated constructor stub
	}

}
