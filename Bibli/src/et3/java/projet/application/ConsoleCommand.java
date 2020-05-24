package et3.java.projet.application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import et3.java.projet.application.documents.*;

public class ConsoleCommand {

	//bibliotheque quota
	private static void addUser(String[] args) {
		
		if (args.length != 2) {
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
		
		
		
		Systeme.ajouterUtilisateur(new Utilisateur(quota), b);
		
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
	private static void addDoc(String[] args) {
		
		if (args.length != 7 && args.length != 8 ) {
			System.err.println("wrong argument number");
			return;
		}
		switch(args[0]) {
			case "Autre":
				Systeme.ajouterDocument(new Autre(args[1], args[2], args[3], args[4], args[5], args[6]),false);
				break;
			case "BD":
				Systeme.ajouterDocument(new BD(args[1], args[2], args[3], args[4], args[5], args[6]),false);
				break;
			case "Carte":
				Systeme.ajouterDocument(new Carte(args[1], args[2], args[3], args[4], args[5], args[6]),false);
				break;
			case "CD":
				Systeme.ajouterDocument(new CD(args[1], args[2], args[3], args[4], args[5], args[6]),false);
				break;
			case "JeuDeSociete":
				Systeme.ajouterDocument(new JeuDeSociete(args[1], args[2], args[3], args[4], args[5], args[6]),false);
				break;
			case "JeuVideo":
				Systeme.ajouterDocument(new JeuVideo(args[1], args[2], args[3], args[4], args[5], args[6]),false);
				break;
			case "Livre":
				Systeme.ajouterDocument(new Livre(args[1], args[2], args[3], args[4], args[5], args[6]),false);
				break;
			case "Partition":
				Systeme.ajouterDocument(new Partition(args[1], args[2], args[3], args[4], args[5], args[6]),false);
				break;
			case "Revue":
				Systeme.ajouterDocument(new Revue(args[1], args[2], args[3], args[4], args[5], args[6]),false);
				break;
			case "Vinyle":
				Systeme.ajouterDocument(new Vinyle(args[1], args[2], args[3], args[4], args[5], args[6]),false);
				break;
			default:
				System.err.println("invalid type of Document");
				break;
		
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
			case "alldocs":
				
				if (b != null) {
					b.afficherDocs();
				}else {
					Systeme.afficherDocs();
				}
				//Todo 4
				break;
			case "docsbyserie":
				
				if (args.length < 2) {
					System.err.println("missing value");
					break;
				}
				
				if (b == null)
					Systeme.afficherSerie(args[1]);
				
				//Todo 5
				break;
			case "docsbyauthor":
				
				String authorName = null;
				String authorPrenom = null;
				args = Arrays.copyOfRange(args, 1, args.length);
				
				if (args.length >= 2 && args[0].equals("-p")) {
					authorPrenom = args[1];
					args = Arrays.copyOfRange(args, 2, args.length);
				}
				if (args.length >= 2 && args[1].equals("-n")) {
					authorName = args[1];
					args = Arrays.copyOfRange(args, 2, args.length);
				}
				
				if (authorName == null && authorPrenom == null){
					
					System.err.println("you must give the author name or prenom");
					break;
				}
				
				if (b == null)
					Systeme.afficherDocAuteur(authorPrenom, authorName);
				
				//Todo 6
				break;
			case "docbyisbn":
				
				if (args.length != 2) {
					System.err.println("wrong argument number");
					break;
				}
				
				if (b == null)
					Systeme.afficherDocEAN(args[1]);
				
				//Todo 7
				break;
			case "docbyean":
				if (args.length != 2) {
					System.err.println("wrong argument number");
					break;
				}
				
				if (b == null)
					Systeme.afficherDocEAN(args[1]);
				
				//Todo 8
				break;
			case "nbdoc":
				if (args.length != 3) {
					System.err.println("wrong argument number");
					break;
				}
				//Todo 9
				break;
			default:
				System.err.println("invalid argument");
		
		
		
		}
		
	}
	
	private static void checkInput(String[] inputs) {
		
		switch (inputs[0]) {
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
			case "help":
				if (inputs.length ==1) {
					System.out.println("type 'help add' or 'help show' for specific command");
					System.out.println("cmd [facultative] (value)");
					break;
				}
				switch(inputs[1]) {
					case "add":
						System.out.println("add user (bibli) (quota)");
						System.out.println("add (bibli) (nom)");
						System.out.println("add doc (bibli) (type) (ean) (title) (publisher) (date) (authorName) (authorSurname) [(isbn)]");
						break;
					case "show":
						System.out.println("show [-b (bibli)] alldocs");
						System.out.println("show [-b (bibli)] docsbyserie");
						System.out.println("show [-b (bibli)] docsbyauthor [-p prenom] [-n nom]");
						System.out.println("show [-b (bibli)] docbyisbn (ISBN)");
						System.out.println("show [-b (bibli)] docbyean (EAN)");
						System.out.println("show [-b (bibli)] nbdoc (beginDate) (endDate)");
						break;
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
