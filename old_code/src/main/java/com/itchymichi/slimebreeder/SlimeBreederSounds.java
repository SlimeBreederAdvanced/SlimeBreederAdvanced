package com.itchymichi.slimebreeder;

import java.util.ArrayList;
import java.util.List;

import com.itchymichi.slimebreeder.sounds.BaseSound;


import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SlimeBreederSounds 
{
	
	public static final List<SoundEvent> SOUNDS = new ArrayList<SoundEvent>();
	
	
	static //size = SoundEvent.REGISTRY.getKeys().size();
	String prefix = SlimeBreeder.MODID + ":";
	
	//public static ResourceLocation soundEvent = new ResourceLocation(" ");

	public static SoundEvent SLIMALYSER_ZAP = new BaseSound("slimalyser_zap", new ResourceLocation(prefix + "slimalyser_zap"));

	
	
}
