package com.itchymichi.slimebreeder.lists;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlimeColor 
{
	public  ItemStack item;
	public  int wavelength;
    public  float saturation;
    public  float brightness;
    


	public SlimeColor(ItemStack itemStack, int wavelength, float saturation, float brightness) 
	{
		this.item = itemStack;
		this.wavelength = wavelength;
		this.saturation = saturation;
		this.brightness = brightness;
		
	}
	
	public ItemStack getItemStack()
	{
		return this.item;
	}
	
	public int getWave()
	{
		return this.wavelength;
	}
	
	public float getSat()
	{
		return this.saturation;
	}
 
	public float getBright()
	{
		return this.brightness;
	}
 
	
	
}
