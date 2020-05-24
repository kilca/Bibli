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
	
	private static void showValues(String[] args, Bibliotheque b) {
		
		switch (args[0]) {
			case "alldocs":
				//Todo 4
				break;
			case "docsbyserie":
				//Todo 5
				break;
			case "docsbyauthor":
				//Todo 6
				break;
			case "docbyisbn":
				//Todo 7
				break;
			case "docbyean":
				//Todo 8
				break;
			case "nbdoc":
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
				if (inputs.length < 3) {
					System.err.println("error, missing arguments");
					break;
				}
				Bibliotheque bibliShown = Systeme.getBibliothequeByName(inputs[1]);
				
				if (bibliShown == null && !inputs[1].equals("systeme")) {
					System.err.println("error bibli not found");
				}
				showValues(Arrays.copyOfRange(inputs, 2, inputs.length),bibliShown);

				
				break;
			case "help":
				System.out.println("add user bibli quota");
				System.out.println("add bibli nom");
				System.out.println("add doc bibli type ean title publisher date authorName authorSurname isbn(fac)");
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
