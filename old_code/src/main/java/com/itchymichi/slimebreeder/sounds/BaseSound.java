package com.itchymichi.slimebreeder.sounds;

import java.util.ArrayList;
import java.util.List;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederSounds;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class BaseSound extends SoundEvent
{
	
	private static int size = 0;
	

	public BaseSound(String name, ResourceLocation soundNameIn) {
		super(soundNameIn);
		
		size = SoundEvent.REGISTRY.getKeys().size();
		
		//String finalName = ":" + name;
		

		//System.out.println("Registering Sounds " + soundNameIn);
		
		this.setRegistryName(new ResourceLocation(SlimeBreeder.MODID, name));
		
		SlimeBreederSounds.SOUNDS.add(this);
		
		
		// TODO Auto-generated constructor stub
	}

	
	
}
