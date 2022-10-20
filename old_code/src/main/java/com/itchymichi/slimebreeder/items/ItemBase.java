package com.itchymichi.slimebreeder.items;

import javax.annotation.Nullable;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{

	public ItemBase(String name, Boolean inCreativeTab,CreativeTabs tab, int size)
	{
		
		setUnlocalizedName(SlimeBreeder.MODID + "_"+name);
		//setUnlocalizedName(name);
		setRegistryName(name);
		
		SlimeBreederItems.ITEMS.add(this);
		
		
		if(inCreativeTab)
		{
			setCreativeTab(tab);
		}
		
		setMaxStackSize(size);
		
	}
	
	
	
	@Override
	public void registerModels() {
		
		SlimeBreeder.proxy.registerItemRenderer(this, 0, "inventory");
	}



	@Override
	public boolean getTop() {
		// TODO Auto-generated method stub
		return false;
	}
}
