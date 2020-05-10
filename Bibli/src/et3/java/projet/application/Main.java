package et3.java.projet.application;

import java.io.File;

import et3.java.projet.data.FileReader;

public class Main 
{
	
	
	
	public static void main(String[] args) 
	{
		try {
			if(args.length > 0)
			{
				Systeme.chargerBiblio(args[0]);
				
				Systeme.consulterDocuments();
				
			}
			else
			{
				System.out.println("[Main] You should enter the CSV file path as a parameter.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//TODO Project :)
	}
}
