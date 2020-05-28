package et3.java.projet.application.exceptions.command;

/**
 * Exception si l'argument donne a un mauvais format (g�n�ralement pas un entier)
 *
 */
public class WrongArgumentFormatException extends CommandException {

	public WrongArgumentFormatException(String msg){
		
		super(msg);
		
	}
}
