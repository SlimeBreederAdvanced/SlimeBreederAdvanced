package com.itchymichi.slimebreeder.utility;

import java.util.ArrayList;
import java.util.Collections;

import com.itchymichi.slimebreeder.items.gui.GuiSlimeReader;

import net.minecraft.client.gui.GuiScreen;



public class WaveTranformer 
{
	/*static int wavelength = 580;
	static int spdLight = 300000000;
	static double plank = 6.63 * Math.pow(10, -34);
	static double freq = spdLight/wavelength;
	static double energy = plank * freq;
	static double amp = Math.sqrt(energy);
	static double angular = 2 * Math.PI * freq;
	
	public double coordx;
	public double coordy;
	
	
	public WaveTranformer(double x, double y)
	{
		this.coordx = x;
		this.coordy = y;
		
	}
	
	public double getCoordx()
	{
		return this.coordx;
	}

	public double getCoordy()
	{
		return this.coordy;
	}


	
	
	 public  ArrayList<Double> listOfCoords = new ArrayList<Double>();
	    
	    public  ArrayList<Double> initCoordsList() {

	    	for (int i = 0; i < 42; i++) 
	    	{

	    	//double x = i * Math.pow(10, -7);
	    	double y = amp * Math.sin((angular * (i * Math.pow(10, -7))));
	    	
	    	listOfCoords.add(y);
	    	System.out.println("Y = " + y);
	    	//listOfCoords.add(y);
	    	}
	    	
	    	Double minY = Collections.min(listOfCoords);
	    	Double maxY = Collections.max(listOfCoords);
	    	
	    	// Old Y range
	    	double oldYrange = maxY-minY;
	    	
	    	// New Y range
	    	//int newYrange = GuiSlimeReader.currHeight - GuiSlimeReader.currHeight/2;
	    	
	    	// Old X range
	    	//double oldXrange = 0.0000041;
	    	
	    	// New X range
	    	//int newXrange = GuiSlimeReader.currWidth - GuiSlimeReader.currWidth/2;


	    	
	    	for (int i = 0; i < 42; i++) 
	    	{

		   // double x2 = i * Math.pow(10, -7);
	    	//double y2 = (((listOfCoords.get(i)-minY)*newYrange)/oldYrange)+newYrange;
	    	System.out.println("New Y = " + y2);
	    	listOfCoords.set( i, y2 );
	    	}
	    	
			return listOfCoords;

			}

		public void setWavefunction() 
		{
			initCoordsList();
		}*/
	
	
}
