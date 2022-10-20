package com.itchymichi.slimebreeder.lists;

import net.minecraft.item.ItemStack;

public class SlimeAccessories 
{
	public ItemStack item;

	
	public SlimeAccessories(ItemStack itemStack) 
	{
		this.item = itemStack;
		
	}
	
	
	public ItemStack getAccessoryItemStack()
	{
		return this.item;
	}
	

	
	
}
