package com.itchymichi.slimebreeder.lists;

import net.minecraft.item.ItemStack;

public class SlimeItems 
{
	public ItemStack item;
	public double movespeed;
	public double health;
	public int attackdmg;
	public int sticky;
	public int combinedattackdmg;
	public int maxPT;
	
	public SlimeItems(ItemStack itemStack, double movespeed, double health, int attackdmg, int sticky, int combinedattackdmg, int maxPT) 
	{
		this.item = itemStack;
		this.movespeed = movespeed;
		this.health = health;
		this.attackdmg = attackdmg;
		this.sticky = sticky;
		this.combinedattackdmg = combinedattackdmg;
		this.maxPT = maxPT;
	}
	
	
	public ItemStack getItemStack()
	{
		return this.item;
	}
	
	public Integer getmaxProcessTime()
	{
		return this.maxPT;
	}
	
	
}
