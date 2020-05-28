package et3.java.projet.application.exceptions;


import et3.java.projet.application.Bibliotheque;


/**
 * Exception gerant si la bibliotheque n'a pas ete trouve
 * Peut etre du a une comparaison qui n'a pas eu de resultat ou plus generalement un
 * mauvais nom de bibliotheque donnee en parametre
 * @see et3.java.projet.application.exceptions.DonneeNotFoundException for details
 */
public class BibliothequeNotFoundException extends DonneeNotFoundException{

	public BibliothequeNotFoundException(String msg, String s){
		
		super(msg,s);
		
	}
	
	public BibliothequeNotFoundException(String msg, Bibliotheque b){
		
		super(msg,b);
		
	}
	public BibliothequeNotFoundException(String msg){
		
		super(msg);	
	}
	
}
