package et3.java.projet.application.exceptions;

import et3.java.projet.application.Utilisateur;

/**
 * Exception gerant si l'utilisateur n'a pas ete trouve
 * Peut etre du a une comparaison qui n'a pas eu de resultat ou plus generalement un
 * mauvais nom d'utilisateur donnee en parametre
 * @see et3.java.projet.application.exceptions.DonneeNotFoundException for details
 */
public class UtilisateurNotFoundException extends DonneeNotFoundException {

	public UtilisateurNotFoundException(String msg, String s){
		
		super(msg,s);
		
	}
	
	public UtilisateurNotFoundException(String msg, Utilisateur u){
		
		super(msg,u);
		
	}
	public UtilisateurNotFoundException(String msg){
		
		super(msg);	
	}
	
}
