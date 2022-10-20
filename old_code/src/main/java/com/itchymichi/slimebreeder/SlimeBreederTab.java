package com.itchymichi.slimebreeder;

import com.itchymichi.slimebreeder.utility.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlimeBreederTab extends CreativeTabs 
{
	public SlimeBreederTab(String tabName)
	{
		super(tabName);
		
	}

	@Override
	public ItemStack getTabIconItem() {
		// TODO Auto-generated method stub
		return new ItemStack(SlimeBreederItems.FILLEDSLIMECAPSULE);
	}
	
	
	
	
	
}