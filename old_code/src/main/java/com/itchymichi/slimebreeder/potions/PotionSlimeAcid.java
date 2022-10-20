package com.itchymichi.slimebreeder.potions;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionSlimeAcid extends Potion
{

public PotionSlimeAcid(boolean isBadEffectIn, int liquidColorIn) 
{
super(isBadEffectIn, liquidColorIn);


}

public Potion setIconIndex(int par1, int par2) 
{
super.setIconIndex(par1, par2);
return this;
}

public boolean shouldRenderHUD(PotionEffect effect)
{
    return false;
}

public boolean shouldRender(PotionEffect effect) 
{ 
	return false; 
}

}