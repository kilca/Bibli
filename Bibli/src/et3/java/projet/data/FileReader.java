package et3.java.projet.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import et3.java.projet.application.Bibliotheque;
import et3.java.projet.application.Serie;
import et3.java.projet.application.Systeme;
import et3.java.projet.application.documents.*;
import et3.java.projet.application.exceptions.SerieNotFoundException;

public class FileReader 
{
	/**
	 * recupere les donnee d'un fichier csv et les mets dans les donnees
	 * ne les prend pas en compte si leur identifiant existent deja
	 * Ici on recupere le csv transmit avec des bibliotheques existantes
	 * 
	 * @param csvFilePath		Le repertoire du fichier csv
	 */
	public static void getDataFromCSVFile(String csvFilePath)
	{
        String line = "";
        String[] data = null;
        String separator = ";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        
        //Document data
        String isbn;
        String ean;
        String title;
        String publisher;
        String date;
        String seriesTitle;
        Integer seriesNumber;
        String authorName;
        String authorSurname;
        String type;
        int totalCopies;
        int numberCopyAimeCesaire;
        int numberCopyEdmondRostand;
        int numberCopyJeanPierreMelville;
        int numberCopyOscarWilde;
        int numberCopySaintSimon;
        
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(csvFilePath), StandardCharsets.ISO_8859_1)) 
        {
        	//Read the first line
        	line = bufferedReader.readLine();
        	
        	//Get data from the line
        	data = line.split(separator, -1);
        	
        	if(data.length != 16)
        	{
        		System.out.println("[FileReader] The file at " + csvFilePath + " does not contain the right number of columns.");
        		return;
        	}
        	
        	int i = 1;
        	
        	//Read the file line by line
            while ((line = bufferedReader.readLine()) != null)
            {
            	//Get data from the line
            	data = line.split(separator, -1);
            	
            	//Sort data
            		
            		//Get the ISBN number
            		isbn = data[0];
            		
            		//Get the EAN number
            		ean = data[1];
            		
            		//Get the title of the document
            		title = data[2];

            		//Get the name of the publisher
            		publisher = data[3];
            		
            		//Get the publication date
            		try
            		{
            			int dateInt = Integer.parseInt(data[4].replaceAll("[^0-9]", ""));
            			
            			if(dateInt%10000 >= 2021 || dateInt%10000 < 0)
            			{
            				date = "?";
            			}
            			else if(dateInt/10000 == 0)
            			{
            				date = Integer.toString(dateInt%10000);
            			}
            			else
            			{
            				date = dateInt%10000 + "-" + dateInt/10000;
            			}
            		}
            		catch (Exception exception)
            		{
            			date = "?";
            		}
            		
            		//Get the title of the series
            		seriesTitle = data[5];
            		
            		//Get the number of this document in the series
            		try
            		{
            			seriesNumber = Integer.parseInt(data[6]);
            		}
            		catch (Exception exception)
            		{
            			seriesNumber = null;
            		}
            		
            		//Get the name of the author
            		authorSurname = data[7];
            		
            		//Get the surname of the author
            		authorName = data[8];
            		
            		//Get the type of the document
            		type = data[9];
            		
            		//Get the total number of copy in Paris for this document 
            		try
            		{
            			totalCopies = Integer.parseInt(data[10]);
            		}
            		catch (Exception exception)
            		{
            			totalCopies = 0;
            		}
            		
            		//Get the number of copy in the library "Aime Cesaire"
            		try
            		{
            			numberCopyAimeCesaire = Integer.parseInt(data[11]);
            		}
            		catch (Exception exception)
            		{
            			numberCopyAimeCesaire = 0;
            		}
            		
            		//Get the number of copy in the library "Edmond Rostand"
            		try
            		{
            			numberCopyEdmondRostand = Integer.parseInt(data[12]);
            		}
            		catch (Exception exception)
            		{
            			numberCopyEdmondRostand = 0;
            		}
            		
            		//Get the number of copy in the library "Jean-Pierre Melville"
            		try
            		{
            			numberCopyJeanPierreMelville = Integer.parseInt(data[13]);
            		}
            		catch (Exception exception)
            		{
            			numberCopyJeanPierreMelville = 0;
            		}
            		
            		//Get the number of copy in the library "Oscar Wilde"
            		try
            		{
            			numberCopyOscarWilde = Integer.parseInt(data[14]);
            		}
            		catch (Exception exception)
            		{
            			numberCopyOscarWilde = 0;
            		}
            		
            		//Get the number of copy in the library "Saint-Simon"
            		try
            		{
            			numberCopySaintSimon = Integer.parseInt(data[15]);
            		}
            		catch (Exception exception)
            		{
            			numberCopySaintSimon = 0;
            		}
                
                //TODO Do something with data
            	
            	//TODO Changer pour respecter chacune des classes
            		//Certain livre peuvent etre des autre
            	
            		//On pourrait mettre dans switch case aussi
            		
            		
            		
            		
            		Document d;
            			switch (type) {
            			
            			//------------Livre-------
            			case "Livre jeunesse":
            			case "Livre pour adulte":
            			case "Livre de Fonds specialises":
            			case "Livre en langue etrangere":
            			case "Livre de section jeunesse > 12 ans":
            			case "Livre en gros caracteres":
            			case "Livre sonore pour adulte":
            			case "Livre sonore jeunesse":
            			case "Livres et periodiques DAiSY":
	              		  case "Usuels":
	              		  case "Methode de langue":
		              			d = Systeme.ajouterDocument(new Livre(ean.toString(), title, publisher, date, authorName, authorSurname,isbn));
		              			  break; 
	              		
	              		  case "Bande dessinee pour jeunesse":
	              		  case "Bande dessinee jeunesse":
	              		  case "Bande dessinee pour adulte":
	              		  case "Bande dessinee jeunesse >12 ans":
	              		  case "BD adulte non reservable":
		              			d = Systeme.ajouterDocument(new BD(ean.toString(), title, publisher, date, authorName, authorSurname,isbn));
	              			  break;
	              			  
	              		  case "Partition":
	              		  case "Methode musicale":
	              			
		              			d = Systeme.ajouterDocument(new Partition(ean.toString(), title, publisher, date, authorName, authorSurname,isbn));
	              			  break;
	              			  
	              		  case "Carte ou plan":
		              			d = Systeme.ajouterDocument(new Carte(ean.toString(), title, publisher, date, authorName, authorSurname,isbn));
	              			  
	              			  break;
	              			  
	              			  //-----------------Pas livre----------
	              			  
	              		  case "Vinyle":
	              			d=Systeme.ajouterDocument(new Vinyle(ean.toString(), title, publisher, date, authorName, authorSurname));
	              			  break;
	              			  
	              		  case "Revue de Fonds specialises":
	              		  case "Revue jeunesse":
	              		  case "Revue pour adulte":
	              			d=Systeme.ajouterDocument(new Revue(ean.toString(), title, publisher, date, authorName, authorSurname));
	              			  break;
	              			  
	              		  case "Enregistrement musical pour la jeunesse":
	              		  case "DVD-video tous publics":
	              		  case "DVD jeunesse":
	              		  case "Disque compact":
	              		  case "DVD- video > 12 ans":
	              		  case "DVD-video > 16 ans":
	              		  case "DVD- video > 18 ans":
	              			  d=Systeme.ajouterDocument(new CD(ean.toString(), title, publisher, date, authorName, authorSurname));
	              			  break;
	              			  
	              		  case "Jeux Videos tous publics":
	              		  case "Jeux videos > 18 ans":
	              			  d=Systeme.ajouterDocument(new JeuVideo(ean.toString(), title, publisher, date, authorName, authorSurname));
	              			  break;
	              			  
	              		  case "Jeux de societe":
	              			  d=Systeme.ajouterDocument(new JeuDeSociete(ean.toString(), title, publisher, date, authorName, authorSurname));
	              			  break;
	              			  
	              		  // --------------- Autre --------
	              			  //Autre : Diapositive jeunesse, Non empruntable, Documents numeriques, nouveaute
	              		  default:
	              			  d=Systeme.ajouterDocument(new Autre(ean.toString(), title, publisher, date, authorName, authorSurname));
	              			  //System.out.println("type not found :"+type);
            			
            			}
            		
            		if (d == null) {//si le document a deja un ISBN ou EAN
            			continue;
            		}
            			
            		if (seriesTitle != null && !seriesTitle.equals("")) {
            			Serie serie = Systeme.getSerieByName(seriesTitle);
            			if (serie == null) {
            				serie = new Serie(seriesTitle);
                			try {
								Systeme.ajouterSerie(serie);
							} catch (SerieNotFoundException e) {
								System.err.println(e.getMessage());
							}
            			}
            			int numSerie=0;
            			if (seriesNumber != null)
            				numSerie = seriesNumber.intValue();
            			
            			d.setSerie(serie,numSerie);
            			
            		}
            			
            			
            		Bibliotheque AimeCesaire = Systeme.getBibliothequeByName("AimeCesaire");
    				Bibliotheque EdmondRostand = Systeme.getBibliothequeByName("EdmondRostand");
    				Bibliotheque JeanPierreMelville = Systeme.getBibliothequeByName("JeanPierreMelville");
    				Bibliotheque OscarWilde = Systeme.getBibliothequeByName("OscarWilde");
    				Bibliotheque SaintSimon = Systeme.getBibliothequeByName("SaintSimon");
            	
            		
    				if (numberCopyAimeCesaire > 0) {
    					AimeCesaire.ajouterDocument(d,numberCopyAimeCesaire);
    				}
    				if (numberCopyEdmondRostand > 0) {
    					EdmondRostand.ajouterDocument(d,numberCopyEdmondRostand);
    				}
    				if (numberCopyJeanPierreMelville > 0) {
    					JeanPierreMelville.ajouterDocument(d,numberCopyJeanPierreMelville);
    				}
    				if (numberCopyOscarWilde > 0) {
    					OscarWilde.ajouterDocument(d,numberCopyOscarWilde);
    				}
    				if (numberCopySaintSimon > 0) {
    					SaintSimon.ajouterDocument(d,numberCopySaintSimon);
    				}
    				   				
            	/*
                System.out.println(
                		isbn + ";" +
                		ean + ";" +
                		title + ";" +
                		publisher + ";" +
                		date + ";" +
                		seriesTitle + ";" +
                		seriesNumber + ";" +
                		authorName + ";" +
                		authorSurname + ";" +
                		type + ";" +
                		totalCopies + ";" +
                		numberCopyAimeCesaire + ";" +
                		numberCopyEdmondRostand + ";" +
                		numberCopyJeanPierreMelville + ";" +
                		numberCopyOscarWilde + ";" +
                		numberCopySaintSimon);
                */
                
            }
        } 
        catch (IOException exception) 
        {
            System.err.println(exception);
        }
	}
}
