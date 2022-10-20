package com.itchymichi.slimebreeder.handlers;

import com.itchymichi.slimebreeder.SlimeBreeder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SBASoundHandler 
{

	private static int size = 0;
	
	public static SoundEvent SLIMALYSER_ZAP;
	public static SoundEvent COLOURED_SLIME_JUMP;
	public static SoundEvent COLOURED_SLIME_SQUISH;
	
	public static void init()
	{
		////System.out.println("init Sounds");
		size = SoundEvent.REGISTRY.getKeys().size();
		SLIMALYSER_ZAP = register("slimalyser_zap");
		COLOURED_SLIME_JUMP = register("coloured_slime_jump");
		COLOURED_SLIME_SQUISH = register("coloured_slime_squish");
	}
	
	public static SoundEvent register(String name)
	{
		String prefix = SlimeBreeder.MODID + ":";
		
		ResourceLocation location = new ResourceLocation(prefix +  name);
		SoundEvent e = new SoundEvent(location);
		////System.out.println("Registering Sound at "+location);
		
		SoundEvent.REGISTRY.register(size, location, e);
		size++;
		return e; 
		
	}
	
}
