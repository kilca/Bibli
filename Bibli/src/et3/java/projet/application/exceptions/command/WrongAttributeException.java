package et3.java.projet.application.exceptions.command;

/**
 * Exception indiquant que l'attribut donne n'est pas bon, ex : mauvais ISBN ou autre
 */
public class WrongAttributeException extends CommandException {

	public WrongAttributeException(String msg){
		super(msg);
	}
}
