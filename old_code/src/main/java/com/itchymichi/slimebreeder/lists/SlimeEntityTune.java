package com.itchymichi.slimebreeder.lists;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.sound.SoundEvent;

public class SlimeEntityTune 
{
	public net.minecraft.util.SoundEvent targetSound;
	public int tune;

	
	public SlimeEntityTune(net.minecraft.util.SoundEvent sound, int tuneid) 
	{
		this.targetSound = sound;
		this.tune = tuneid;

	}
	
	


	public net.minecraft.util.SoundEvent getEntitySound()
	{
		return this.targetSound;
	}
	
	public Integer getTune()
	{
		return this.tune;
	}
	
	
}
