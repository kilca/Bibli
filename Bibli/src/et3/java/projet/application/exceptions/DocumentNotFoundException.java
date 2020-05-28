package et3.java.projet.application.exceptions;

import et3.java.projet.application.documents.*;

/**
 * Exception gerant si le document n'a pas ete trouve
 * Peut etre du a une comparaison qui n'a pas eu de resultat ou plus generalement un
 * mauvais nom de document donnee en parametre
 * @see et3.java.projet.application.exceptions.DonneeNotFoundException for details
 */
public class DocumentNotFoundException extends DonneeNotFoundException {
	
	public DocumentNotFoundException(String msg, String s){
		
		super(msg,s);
		
	}
	
	public DocumentNotFoundException(String msg, Document d){
		
		super(msg,d);
		
	}
	public DocumentNotFoundException(String msg){
		
		super(msg);	
	}
}
