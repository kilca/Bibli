package et3.java.projet.application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import et3.java.projet.application.documents.*;

public class ConsoleCommand {

	//bibliotheque quota username
	private static void addUser(String[] args) {
		
		if (args.length != 3) {
			System.err.println("wrong argument number");
			return;
		}
		
		int quota = -1;
		
		try {
			quota = Integer.parseInt(args[1]);
		}
		catch (NumberFormatException e)
		{
			System.err.println("error user quota not a int");
		}
		
	
		
		Bibliotheque b = Systeme.getBibliothequeByName(args[0]);
		
		if (b == null)
			System.err.println("error bibliotheque not found");
		
		
		
		Systeme.ajouterUtilisateur(new Utilisateur(quota,args[2]), b);
		
	}
	
	//nom
	private static void addBibli(String[] args) {
		
		if (args.length != 1) {
			System.err.println("wrong argument number");
			return;
		}
		Systeme.ajouterBibliotheque(new Bibliotheque(args[0]));
		
	}
	
	//type ean, title, publisher, date, authorName, authorSurname, ISBN(fac)
	//type ean, publisher, date, authorName, authorSurname, ISBN(fac)
	private static void addDoc(String[] args) {
		
		if (args.length != 7 && args.length != 8 ) {
			System.err.println("wrong argument number");
			return;
		}
		
		Bibliotheque b = Systeme.getBibliothequeByName(args[0]);
		
		if (b == null) {
			System.err.println("bibli :"+args[0]+"does not exist");
			return;
		}
		
		System.out.println("Please Enter the title of the document you want to create");
		Scanner scan = new Scanner(System.in);
		
		String docTitle = "";
		
		if (scan.hasNextLine())
			docTitle = scan.nextLine();
		
		
		System.out.println("Veuillez entrer le titre de la serie (vide si non existe)");
		//scan = new Scanner(System.in);
		String serieTitle = "";
		if (scan.hasNextLine())
			serieTitle = scan.nextLine();
		
		int numSerie = 0;
		
		Serie serie = null;
		if (!serieTitle.equals(""))
			serie = Systeme.getSerieByName(serieTitle);
		
		if (serie != null) {
			System.out.println("Veuillez entrer le numero de serie");
			try {
			numSerie = scan.nextInt();
			}catch (InputMismatchException e) {
				System.err.println("vous devez rentrer un nombre");
				return;
			}
		}else if (!serieTitle.equals("")) {
			System.err.println("Serie indique non exitente, creation annulee");
			return;
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
				System.err.println("invalid type of Document");
				break;
		
		}
		
		if (d!= null)
		{
			b.ajouterDocument(d, 1);
			
			if (serie != null) {
				d.setSerie(serie, numSerie);
			}
			System.out.println("le document a bien ete cree");
		}else {
			System.err.println("il existe deja un document avec cet ISBN/EAN");
		}
		//On ajoute un user
		
	}
	
	
	private static void showValues(String[] args) {
		
		Bibliotheque b = null;
		if (args.length > 2 && args[0].equals("-b")) {
			b = Systeme.getBibliothequeByName(args[1]);
			args = Arrays.copyOfRange(args, 2, args.length);
		}
		
		if (args.length <1) {
			System.err.println("missing argument");
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
					
					System.err.println("you must give the author name or prenom");
					break;
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
					System.err.println("wrong argument number");
					break;
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
					System.err.println("wrong argument number");
					break;
				}
				
				if (b == null)
					Systeme.afficherDocEAN(args[1]);
				else
					b.afficherDocEAN(args[1]);
				//Todo 8
				break;
			case "nbdoc":
				if (args.length != 3) {
					System.err.println("wrong argument number");
					break;
				}
				if (b == null)
					Systeme.NbDocuments(args[1], args[2]);
				else
					b.NbDocuments(args[1], args[2]);
				//Todo 9
				break;
			default:
				System.err.println("invalid argument");
		
		
		
		}
		
	}
	
	private static void checkInput(String[] inputs) {
		
		switch (inputs[0]) {
			case "clear":
				for (int i = 0; i < 50; ++i) System.out.println();
			    break;
		    
			case "add":
				if (inputs.length < 2) {
					System.err.println("error, missing arguments");
					break;
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
						System.err.println("error wrong argument");
						break;	
				
				}
				break;
			case "show":
				if (inputs.length < 2) {
					System.err.println("error, missing arguments");
					break;
				}
				
				showValues(Arrays.copyOfRange(inputs, 1, inputs.length));

				
				break;
			//borrow (username) -e 0404147857
			//borrow (username) Rien (demande l'entree du titre)
			case"transmit":
				switch (inputs[1]) {
				case "borrow":
					if (inputs.length != 4 && inputs.length != 2) {
						System.err.println("must give right argument number");
						break;
					}
					Utilisateur u = Systeme.getUtilisateur(inputs[1]);
					Document d = null;
					if (inputs.length == 4 && inputs[2].equals("-e")) {
						d = Systeme.docsEAN.get(inputs[3]);
					}else {
						System.out.println("Please Enter the title of the document you want to borrow");
						Scanner scan = new Scanner(System.in);
						String docTitle = scan.nextLine();
					
						scan.close();
					
					
						d = Systeme.getDocumentByTitle(docTitle);
					}
				
					//Appeler la fonction d'emprunt
					System.out.println("todo (appeler la fonction d'emprunt)");
				
					break;
				
				case "remit":
				
					if (inputs.length != 4 && inputs.length != 2) {
						System.err.println("must give right argument number");
						break;
					}
					Utilisateur u2 = Systeme.getUtilisateur(inputs[1]);
					Document d2 = null;
					if (inputs.length == 4 && inputs[2].equals("-e")) {
						d2 = Systeme.docsEAN.get(inputs[3]);
					}else {
						System.out.println("Please Enter the title of the document you want to borrow");
						Scanner scan = new Scanner(System.in);
					
						String docTitle = scan.nextLine();
					
						scan.close();
					
						d2 = Systeme.getDocumentByTitle(docTitle);
					}
					//Appeler la fonction d'emprunt
					System.out.println("todo (appeler la fonction de remise)");
				
					break;
				case "exchange":
					if(inputs.length != 6 && inputs.length != 4) {
						System.err.println("must give right argument number");
						break;
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
					}else System.err.println("error, wrong argument");
					break;
				default :
					System.err.println("error, transmit must be followed by borrow, remit or exchange");
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
						break;
					case "transmit":
						System.out.println("transmit borrow [-e (ean)]");
						System.out.println("transmit remit [-e (ean)]");
						System.out.println("transmit exchange (Bibliotheque transmitting) (Bibliotheque receiving) [-e (ean)]");
					default:
					
				
				}
				break;
			default:
				System.err.print("error command not found, type help");
				break;
		
		
		}
		
	}
	
	public static void readConsole() {
		while(true) {
			Scanner scan = new Scanner(System.in);
			
			String[] inputs = scan.nextLine().split(" ");
			
			checkInput(inputs);
		}
	}
	
}
