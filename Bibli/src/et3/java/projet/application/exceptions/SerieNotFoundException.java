package et3.java.projet.application.exceptions;

import et3.java.projet.application.Serie;
/**
 * Exception gerant si la serie n'a pas ete trouve
 * Peut etre du a une comparaison qui n'a pas eu de resultat ou plus generalement un
 * mauvais nom de serie donnee en parametre
 * @see et3.java.projet.application.exceptions.DonneeNotFoundException for details
 */
public class SerieNotFoundException extends DonneeNotFoundException {

	
	public SerieNotFoundException(String msg, String s){
		
		super(msg,s);
		
	}
	
	public SerieNotFoundException(String msg, Serie s){
		
		super(msg,s);
		
	}
	public SerieNotFoundException(String msg){
		
		super(msg);	
	}
	
}
