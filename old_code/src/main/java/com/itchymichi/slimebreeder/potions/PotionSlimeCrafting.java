package com.itchymichi.slimebreeder.potions;

import net.minecraft.potion.Potion;

public class PotionSlimeCrafting extends Potion
{

public PotionSlimeCrafting(boolean isBadEffectIn, int liquidColorIn) 
{
super(isBadEffectIn, liquidColorIn);


}

public Potion setIconIndex(int par1, int par2) 
{
super.setIconIndex(par1, par2);
return this;
}
}
