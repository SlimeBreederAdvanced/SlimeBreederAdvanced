package com.itchymichi.slimebreeder.utility;

import java.util.Locale;

import net.minecraft.util.ResourceLocation;

public class Utily 
{
	public static final String MODID = "slimebreeder";
	public static final String RESOURCE = MODID.toLowerCase(Locale.US);
	
	 public static String resource(String res) {
		    return String.format("%s:%s", RESOURCE, res);
		  }

		  public static ResourceLocation getResource(String res) {
		    return new ResourceLocation(RESOURCE, res);
		  }
		  
		  public static ResourceLocation getModifierResource(String res) {
			    return getResource("models/item/modifiers/" + res);
			  }
	
}


