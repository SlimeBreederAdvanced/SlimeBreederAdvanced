package com.itchymichi.slimebreeder.lists;

import net.minecraft.item.ItemStack;

public class SlimeEdibleItems 
{
	public ItemStack item;

	
	public SlimeEdibleItems(ItemStack itemStack) 
	{
		this.item = itemStack;

	}
	
	
	public ItemStack getItemStack()
	{
		return this.item;
	}
	
	
}
