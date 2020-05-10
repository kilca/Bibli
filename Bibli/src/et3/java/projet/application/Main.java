package et3.java.projet.application;

import java.io.File;

import et3.java.projet.data.FileReader;

public class Main 
{
	public static void main(String[] args) 
	{
		
		if(args.length > 0)
		{
			Systeme.chargerBiblio(args[0]);
		}
		else
		{
			System.out.println("[Main] You should enter the CSV file path as a parameter.");
		}
		
		//TODO Project :)
	}
}
