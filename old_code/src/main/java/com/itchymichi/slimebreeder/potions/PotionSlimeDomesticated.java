package com.itchymichi.slimebreeder.potions;

import net.minecraft.potion.Potion;

public class PotionSlimeDomesticated extends Potion
{

	public PotionSlimeDomesticated(boolean isBadEffectIn, int liquidColorIn) 
	{
	super(isBadEffectIn, liquidColorIn);


	}
public Potion setIconIndex(int par1, int par2) 
{
super.setIconIndex(par1, par2);
return this;
}
}
