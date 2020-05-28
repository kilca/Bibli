package et3.java.projet.application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import et3.java.projet.application.documents.*;
import et3.java.projet.application.exceptions.BibliothequeNotFoundException;
import et3.java.projet.application.exceptions.DocumentNotFoundException;
import et3.java.projet.application.exceptions.SerieNotFoundException;
import et3.java.projet.application.exceptions.UtilisateurNotFoundException;
import et3.java.projet.application.exceptions.command.CommandException;
import et3.java.projet.application.exceptions.command.WrongArgumentFormatException;
import et3.java.projet.application.exceptions.command.WrongArgumentLogicException;
import et3.java.projet.application.exceptions.command.WrongArgumentNumberException;
import et3.java.projet.application.exceptions.command.WrongAttributeException;

/**
 * classe réalisant la gestion de la console de commande, interface entre l'utilisateur et l'application 
 */

public class ConsoleCommand {

	
	/**
	 * permet d'interpretter la commande d'ajout d'utilisateur dans la console avec la fonction addUser de Systeme
	 * @param args         liste d'arguments tapé dans la console.
	 * @throws WrongArgumentNumberException
	 * @throws WrongArgumentFormatException est envoye lorsque le type du quota n'est pas un entier
	 * @throws BibliothequeNotFoundException
	 */
	private static void addUser(String[] args) throws WrongArgumentNumberException, WrongArgumentFormatException, BibliothequeNotFoundException {
		
		if (args.length != 3) {
			throw new WrongArgumentNumberException("wrong argument number");
		}
		
		int quota = -1;
		
		try {
			quota = Integer.parseInt(args[1]);
		}
		catch (NumberFormatException e)
		{
			throw new WrongArgumentFormatException("error user quota not a int");
		}
		
	
		
		Bibliotheque b = Systeme.getBibliothequeByName(args[0]);
		
		if (b == null)
			throw new BibliothequeNotFoundException("librairy was not found",args[0]);
		
		
		Utilisateur u = new Utilisateur(quota,args[2]);
		if (Systeme.ajouterUtilisateur(u, b)) {
			System.out.println("user added with maxQuota :"+u.getQuota());
		}
		
	}
	
	/**
	 * permet d'interpretter la commande d'ajout de bibliotheque dans la console avec la fonction ajouterBibliotheque de Systeme
	 * @param args        liste d'arguments tapé dans la console.
	 * @throws WrongArgumentNumberException
	 */
	
	private static void addBibli(String[] args) throws WrongArgumentNumberException {
		
		if (args.length != 1) {
			throw new WrongArgumentNumberException("wrong argument number");
		}
		if(Systeme.ajouterBibliotheque(new Bibliotheque(args[0]))) {
			System.out.println("Librairy added");
		}
		
	}
	
	/**
	 * permet d'interpreter la commande d'ajout de bibliotheque dans la console avec la fonction ajouterBibliotheque de Systeme
	 * @param args        liste d'arguments tapé dans la console.
	 * @throws WrongArgumentNumberException
	 * @throws BibliothequeNotFoundException
	 * @throws WrongArgumentFormatException
	 * @throws SerieNotFoundException
	 * @throws WrongAttributeException est envoye lorsque l'ISBN ou l'EAN existe deja
	 */

	private static void addDoc(String[] args) throws WrongArgumentNumberException, BibliothequeNotFoundException, WrongArgumentFormatException, SerieNotFoundException, WrongAttributeException {
		
		if (args.length != 7 && args.length != 8 ) {
			throw new WrongArgumentNumberException("wrong argument number");
		}
		
		Bibliotheque b = Systeme.getBibliothequeByName(args[0]);
		
		if (b == null) {
			throw new BibliothequeNotFoundException("bibli does not exist",args[0]);
		}
		
		System.out.println("Please Enter the title of the document you want to create");
		Scanner scan = new Scanner(System.in);
		
		String docTitle = "";
		
		if (scan.hasNextLine())
			docTitle = scan.nextLine();
		
		
		System.out.println("Pleasene enter serie title (empty if nonexistent)");
		//scan = new Scanner(System.in);
		String serieTitle = "";
		if (scan.hasNextLine())
			serieTitle = scan.nextLine();
		
		int numSerie = 0;
		
		Serie serie = null;
		if (!serieTitle.equals(""))
			serie = Systeme.getSerieByName(serieTitle);
		
		if (serie != null) {
			System.out.println("Please enter serie number");
			try {
			numSerie = scan.nextInt();
			}catch (InputMismatchException e) {
				throw new WrongArgumentFormatException("You should enter a number");
			}
		}else if (!serieTitle.equals("")) {
			throw new SerieNotFoundException("serie does not exist",serieTitle);
		}
		
		//docTitle
		//serieTitle
		//numSerie
		//serie
		
		//type ean, publisher, date, authorName, authorSurname, ISBN(fac)
		String type = args[1];
		String ean = args[2];
		String publisher = args[3];
		String date = args[4];
		String authorPrenom = args[5];
		String authorNom = args[6];
		
		String isbn="";
		if (args.length == 8) {
			isbn = args[7];
		}
		
		Document d = null;
		
		switch(type) {
			case "Autre":
				d = Systeme.ajouterDocument(new Autre(ean, docTitle, publisher, date, authorNom, authorPrenom));
				break;
			case "BD":
				d = Systeme.ajouterDocument(new BD(ean, docTitle, publisher, date, authorNom, authorPrenom,isbn));
				break;
			case "Carte":
				d = Systeme.ajouterDocument(new Carte(ean, docTitle, publisher, date, authorNom, authorPrenom,isbn));
				break;
			case "CD":
				d = Systeme.ajouterDocument(new CD(ean, docTitle, publisher, date, authorNom, authorPrenom));
				break;
			case "JeuDeSociete":
				d = Systeme.ajouterDocument(new JeuDeSociete(ean, docTitle, publisher, date, authorNom, authorPrenom));
				break;
			case "JeuVideo":
				d = Systeme.ajouterDocument(new JeuVideo(ean, docTitle, publisher, date, authorNom, authorPrenom));
				break;
			case "Livre":
				d = Systeme.ajouterDocument(new Livre(ean, docTitle, publisher, date, authorNom, authorPrenom,isbn));
				break;
			case "Partition":
				d = Systeme.ajouterDocument(new Partition(ean, docTitle, publisher, date, authorNom, authorPrenom,isbn));
				break;
			case "Revue":
				d = Systeme.ajouterDocument(new Revue(ean, docTitle, publisher, date, authorNom, authorPrenom));
				break;
			case "Vinyle":
				d = Systeme.ajouterDocument(new Vinyle(ean, docTitle, publisher, date, authorNom, authorPrenom));
				break;
			default:
				throw new WrongArgumentFormatException("invalid type of document");
		
		}
		
		if (d!= null)
		{
			b.ajouterDocument(d, 1);
			
			if (serie != null) {
				d.setSerie(serie, numSerie);
			}
			System.out.println("document created ..");
		}else {
			throw new WrongAttributeException("a document with this ISBN/EAN already exist");
		}
		//On ajoute un user
		
	}
	
	/**
	 * permet d'interpretter toutes les commandes d'affichage(show) dans la console.
	 * @param args        liste d'arguments tapé dans la console.
	 * @throws WrongArgumentNumberException
	 * @throws BibliothequeNotFoundException
	 * @throws WrongArgumentFormatException
	 * @throws WrongArgumentLogicException
	 * @throws SerieNotFoundException
	 */

	private static void showValues(String[] args) throws WrongArgumentNumberException, BibliothequeNotFoundException, WrongArgumentFormatException, WrongArgumentLogicException, SerieNotFoundException {
		
		Bibliotheque b = null;
		if (args.length > 2 && args[0].equals("-b")) {
			b = Systeme.getBibliothequeByName(args[1]);
			if (b == null) {
				throw new BibliothequeNotFoundException("librairy not found",args[1]);
			}
			args = Arrays.copyOfRange(args, 2, args.length);
		}
		
		if (args.length <1) {
			throw new WrongArgumentNumberException("missing argument");
		}
		
		switch (args[0]) {
		
		//Pour debuggage
		case "allbibli":
			for(Bibliotheque bi : Systeme.bibliotheques) {
				System.out.println(bi.name+","+bi.documentsHeberge.size());
			}
			break;
		case "alluser":
			for(Bibliotheque bi : Systeme.bibliotheques) {
				System.out.println(bi.name+":");
				for(Utilisateur u : bi.utilisateurs) {
					System.out.println("	"+u.getNom());
				}
			}
			break;
		
		
		//Commande de l'exercice
			case "alldocs":
				
				if (b != null) {
					b.afficherDocs();
				}else {
					Systeme.afficherDocs();
				}
				//Todo 4
				break;
			case "docsbyserie":
				
				System.out.println("Veuillez entrer le titre de la serie ");
				
				Scanner scan = new Scanner(System.in);
				String serieTitle = "";
				if (scan.hasNextLine())
					serieTitle = scan.nextLine();
				
				if (b == null)
					Systeme.afficherSerie(serieTitle);
				else
					Systeme.afficherBibliSerie(b,serieTitle);
				
				//Todo 5
				break;
			case "docsbyauthor":
				
				String authorPrenom = null; // prenom
				String authorNom = null;//nom
				args = Arrays.copyOfRange(args, 1, args.length);
				
				if (args.length >= 2 && args[0].equals("-p")) {
					authorPrenom = args[1];
					args = Arrays.copyOfRange(args, 2, args.length);
				}
				if (args.length >= 2 && args[0].equals("-n")) {
					authorNom = args[1];
					args = Arrays.copyOfRange(args, 2, args.length);
				}
				
				if (authorNom == null && authorPrenom == null){
					throw new WrongArgumentNumberException("you must specify the author name or surname");
				}
				
				if (b == null) {
					if (authorNom != null && authorPrenom != null)
						Systeme.afficherDocAuteur(authorPrenom, authorNom);
					else if (authorNom != null) {//dans le cas où que name
						Systeme.afficherDocAuteuravecNom(authorNom);
					}else {//dans le cas où que prenom
						Systeme.afficherDocAuteuravecPrenom(authorPrenom);
					}
					
				
				}else {
					if (authorNom != null && authorPrenom != null)
						b.afficherDocAuteur(authorPrenom, authorNom);
					else if (authorNom != null) {//dans le cas où que name
						b.afficherDocAuteuravecNom(authorNom);
					}else {//dans le cas où que prenom
						b.afficherDocAuteuravecPrenom(authorPrenom);
					}
					
					
				}
				//Todo 6
				break;
			case "docbyisbn":
				
				if (args.length != 2) {
					throw new WrongArgumentNumberException("wrong argument number");
				}
				//System.out.println("on test pour l'ISBN:"+args[1]);
				if (b == null)
					Systeme.afficherDocISBN(args[1]);
				else
					b.afficherDocISBN(args[1]);
				//Todo 7
				break;
			case "docbyean":
				if (args.length != 2) {
					throw new WrongArgumentNumberException("wrong argument number");
				}
				
				if (b == null)
					Systeme.afficherDocEAN(args[1]);
				else
					b.afficherDocEAN(args[1]);
				//Todo 8
				break;
			case "nbdoc":
				if (args.length != 3) {
					throw new WrongArgumentNumberException("wrong argument number");
				}
				if (b == null)
					Systeme.NbDocuments(args[1], args[2]);
				else
					b.NbDocuments(args[1], args[2]);
				//Todo 9
				break;
			case "userdoc":
				if(args.length != 3) {
					throw new WrongArgumentNumberException("wrong argument number");
				}
				b = Systeme.getBibliothequeByName(args[2]);
				if(b == null) {
					throw new BibliothequeNotFoundException("librairy not found",args[2]);
				}
				Utilisateur u = Systeme.getUtilisateur(args[1], b);
				if(u == null) {
					System.err.println("error : user not found");
					break;
				}
				u.userDoc();
				break;
			default:
				System.err.println("invalid argument");
		
		
		}
		
	}
	
	/**
	 * 
	 * @param inputs	les arguments de la commande
	 * @param transmitType		le type de transmission (borrow ou remit)
	 * @throws UtilisateurNotFoundException
	 * @throws BibliothequeNotFoundException
	 * @throws WrongArgumentNumberException
	 * @throws DocumentNotFoundException
	 */
	private static void RemitOrBorrow(String[] inputs, String transmitType) throws UtilisateurNotFoundException, BibliothequeNotFoundException, WrongArgumentNumberException, DocumentNotFoundException {
		
		if (inputs.length != 6 && inputs.length != 4) {
			throw new WrongArgumentNumberException("error, missing argument");
		}
		Bibliotheque b = Systeme.getBibliothequeByName(inputs[3]);
		if(b == null) {
			throw new BibliothequeNotFoundException("error : librairy not found",inputs[3]);
		}
		Utilisateur u = Systeme.getUtilisateur(inputs[2],b);
		if(u == null) {
			throw new UtilisateurNotFoundException("error : user not found",inputs[2]);
		}
		Document d = null;
		if (inputs.length == 6 && inputs[4].equals("-e")) {
			d = Systeme.docsEAN.get(inputs[5]);
		}else {
			System.out.println("Please Enter the title of the document you want to "+transmitType);
			Scanner scan = new Scanner(System.in);
		
			String docTitle = scan.nextLine();
		
			scan.close();
		
			d = Systeme.getDocumentByTitle(docTitle);
			if(d == null) {
				throw new DocumentNotFoundException("error : document not found",docTitle);
			}
		}
		
	
		if (transmitType.equals("remit")) 	//si on remit
		{
			if(u.rendre(d)) {
				System.out.println("remit done");
			}else
				System.err.println("error : remit impossible");
		}else if (transmitType.equals("borrow"))	//si on borrow
		{
			if(u.emprunter(d)) {
				System.out.println("borrowing done");
			} else
				System.err.println("error : borrowing impossible");
		}else {
			//ne devrait pas arriver
			System.err.println("Code error, transmitType not handled in RemitOrBorrow");
		}
	
	}
	
	/**
	 * permet d'interpretter toutes les commandes d'ajout(add) et de modification(transmit) dans la console. réalise l'appel de la fonction showValues
	 * @param inputs        liste d'arguments tapé dans la console.
	 *  @throws BibliothequeNotFoundException
	 * @throws SerieNotFoundException
	 * @throws DocumentNotFoundException
	 * @throws UtilisateurNotFoundException
	 * @throws CommandException
	*/
	private static void checkInput(String[] inputs) throws BibliothequeNotFoundException, SerieNotFoundException, DocumentNotFoundException, UtilisateurNotFoundException, CommandException {
		
		switch (inputs[0]) {
			case "clear":
				for (int i = 0; i < 50; ++i) System.out.println();
			    break;
		    
			case "add":
				if (inputs.length < 2) {
					throw new WrongArgumentNumberException("error, missing argument");
				}
				switch (inputs[1]) {
					case "user":
						addUser(Arrays.copyOfRange(inputs, 2, inputs.length));
						break;
					case "bibli":
						addBibli(Arrays.copyOfRange(inputs, 2, inputs.length));
						break;
					case "doc":
						addDoc(Arrays.copyOfRange(inputs, 2, inputs.length));
						break;
					default:
						throw new WrongArgumentNumberException("error, wrong argument");
				
				}
				break;
			case "show":
				if (inputs.length < 2) {
					throw new WrongArgumentNumberException("error, missing argument");
				}
				
				showValues(Arrays.copyOfRange(inputs, 1, inputs.length));				
				break;

			case"transmit":
				switch (inputs[1]) {
				case "borrow":
					RemitOrBorrow(inputs,"borrow");
					break;
				case "remit":
					RemitOrBorrow(inputs,"remit");
					break;
				case "exchange":
					if(inputs.length != 6 && inputs.length != 4) {
						throw new WrongArgumentNumberException("error, missing argument");
					}
					Bibliotheque b1 = Systeme.getBibliothequeByName(inputs[2]);
					Bibliotheque b2 = Systeme.getBibliothequeByName(inputs[3]);
					Document d3 = null;
					if (inputs.length == 6 && inputs[4].equals("-e")) {
						d3 = Systeme.docsEAN.get(inputs[5]);
					}else {
						System.out.println("Please Enter the title of the document you want to borrow");
						Scanner scan = new Scanner(System.in);
					
						String docTitle = scan.nextLine();
					
						d3 = Systeme.getDocumentByTitle(docTitle);
					}
					if(b1 != null && b2 != null && d3 != null) {
						if(b1.donnerDocumentBibli(d3, b2)){
							System.out.println("exchange performed");
						}
					}else
						System.err.println("error, wrong argument, one data was not found");
					break;
				default :
					throw new WrongArgumentFormatException("error, this argument does not exist");
				}
			case "help":
				if (inputs.length ==1) {
					System.out.println("type 'help add','help show' or 'help transmit' specific command");
					System.out.println("cmd [facultative] (value) ...Full sentence");
					break;
				}
				switch(inputs[1]) {
					case "add":
						System.out.println("add user (bibli) (quota) (nom)");
						System.out.println("add bibli (nom)");
						System.out.println("add doc (bibli) (type) (ean) (publisher) (date) (authorName) (authorSurname) [(isbn)] ... title/serie");
						break;
					case "show":
						System.out.println("show allbibli");
						System.out.println("show alluser");
						System.out.println("");
						System.out.println("show [-b (bibli)] alldocs");
						System.out.println("show [-b (bibli)] docsbyserie ... nomSerie");
						System.out.println("show [-b (bibli)] docsbyauthor [-p prenom] [-n nom]");
						System.out.println("show [-b (bibli)] docbyisbn (ISBN)");
						System.out.println("show [-b (bibli)] docbyean (EAN)");
						System.out.println("show [-b (bibli)] nbdoc (beginDate) (endDate)");
						System.out.println("show userdoc (username) (bibli)");
						break;
					case "transmit":
						System.out.println("transmit borrow (username)(bibli)[-e (ean)]");
						System.out.println("transmit remit (username)(bibli)[-e (ean)]");
						System.out.println("transmit exchange (Bibliotheque transmitting) (Bibliotheque receiving) [-e (ean)]");
					default:
					
				
				}
				break;
			default:
				throw new CommandException("error command not found, type 'help' for help");
		
		
		}
		
	}
	
	/**
	 * tant que le programme est lancé, lit dans la console les instructions de l'utilisateur
	 */
	
	public static void readConsole() {
		while(true) {
			Scanner scan = new Scanner(System.in);
			
			String[] inputs = scan.nextLine().split(" ");
			
			try {
				checkInput(inputs);
				
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
}
