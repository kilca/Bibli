package et3.java.projet.application.exceptions;

//Utilise pour indique qu'on n'a pas trouve l'objet
/**
 * Classe parente indiquant qu'une donnee de l'application n'a pas ete trouve
 * Elle recupere le message d'erreur et aussi la cause du probleme que ce soit le string
 * Voir la doc du constructeur et des variable pour comprendre leur interet :

 */
public class DonneeNotFoundException extends Exception {

	/**
	 * la bibliotheque ou alors l'objet avec lequel on compare
	 */
	private Object donnee = null;
	
	/**
     * constructeur recuperant le message d'erreur et la cause (généralement l'argument de la commande)
     * @param msg       le message d'erreur
     * @param s         la cause de l'erreur (généralement le nom via la commande)
     */
	public DonneeNotFoundException(String msg, String s){
		
		super(msg);
		this.donnee = s;
		
	}
	
	/**
     * constructeur recuperant le message d'erreur et la cause (généralement l'objet comparee)
     * @param msg       le message d'erreur
     * @param s         la cause de l'erreur (généralement l'objet comparee)
     */
	public DonneeNotFoundException(String msg, Object o){
		
		super(msg);
		this.donnee = o;
		
	}
	
	public DonneeNotFoundException(String msg){
		
		super(msg);	
	}
	
	/**
	*indique si l'objet qu'on a recupere est l'argument dont on tente de recup l'objet ou l'objet compare
     */
	public boolean isDonneeString() {
		return (donnee instanceof String);
	}
	
	public Object getDonneNotFound() {
		return donnee;
	}
	
}
