package et3.java.projet.application.exceptions.command;

/**
 * Classe parente permettant d'indiquer une erreur dans le systeme de commande
 * Utilise pour indique que la commande generale est fausse
 */
public class CommandException extends Exception{

	public CommandException(String msg){
		
		super(msg);
		
	}
	
}
