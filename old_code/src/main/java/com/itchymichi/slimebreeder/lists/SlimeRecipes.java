package com.itchymichi.slimebreeder.lists;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlimeRecipes 
{
	public ItemStack item1;
	public ItemStack item2;
	public ItemStack item3;
	public ItemStack item4;
	public ItemStack item5;
	public ItemStack item6;
	public ItemStack item7;
	
	public double movespeed;
	public double health;
	public int attackdmg;
	public int sticky;
	public int maxPT;
	
	public String flavorText;
	
	public boolean nbtcraft;
	
	public SlimeRecipes(ItemStack item1, ItemStack item2, ItemStack item3, ItemStack item4, ItemStack item5, ItemStack item6, ItemStack item7, double movespeed, double health, int attackdmg, int sticky, int maxPT, boolean nbtcraft, String text) 
	{
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
		this.item4 = item4;
		this.item5 = item5;
		this.item6 = item6;
		this.item7 = item7;
		
		this.movespeed = movespeed;
		this.health = health;
		this.attackdmg = attackdmg;
		this.sticky = sticky;
		this.maxPT = maxPT;
		
		this.nbtcraft = nbtcraft;
		this.flavorText = text;
	}
	
	
	public ItemStack getItem1()
	{
		return this.item1;
	}
	
	public ItemStack getItem2()
	{
		return this.item2;
	}
	
	public ItemStack getItem3()
	{
		return this.item3;
	}
	
	public ItemStack getItem4()
	{
		return this.item4;
	}
	
	public ItemStack getItem5()
	{
		return this.item5;
	}
	
	public ItemStack getItem6()
	{
		return this.item6;
	}
	
	public ItemStack getItem7()
	{
		return this.item7;
	}
	
	
	public Double getFlow()
	{
		return this.movespeed;
	}

	public Double getHealth()
	{
		return this.health;
	}
	
	public Integer getAcidity()
	{
		return this.attackdmg;
	}
	
	public Integer getSticky()
	{
		return this.sticky;
	}
	
	public Integer getmaxProcessTime()
	{
		return this.maxPT;
	}
	
	public Boolean getNBTCrafting()
	{
		return this.nbtcraft;
	}
	
	public String getText()
	{
		return this.flavorText;
	}
	
	
}
