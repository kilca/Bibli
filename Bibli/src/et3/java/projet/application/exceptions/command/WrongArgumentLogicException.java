package et3.java.projet.application.exceptions.command;

/**
 * Exception si l'argument donne ne respecte pas la logique demande (par exemple si il doit etre superieur a l'argument d'avant)
 *
 */
public class WrongArgumentLogicException extends CommandException {

	public WrongArgumentLogicException(String msg) {
		super(msg);
	}

}
