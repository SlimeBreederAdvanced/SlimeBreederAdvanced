package com.itchymichi.slimebreeder.lists;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlimeRainbowColor 
{

	public  int red;
    public  int green;
    public  int blue;
    


	public SlimeRainbowColor(int red, int blue, int green) 
	{

		this.red = red;
		this.blue = blue;
		this.green = green;
		
	}
	
	public int getRed()
	{
		return this.red;
	}
	
	public int getBlue()
	{
		return this.blue;
	}
	
	public int getGreen()
	{
		return this.green;
	}
 
}
