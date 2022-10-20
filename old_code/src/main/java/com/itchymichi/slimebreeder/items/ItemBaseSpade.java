package com.itchymichi.slimebreeder.items;

import javax.annotation.Nullable;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;

public class ItemBaseSpade extends ItemSpade implements IHasModel
{

	public ItemBaseSpade(String name, Boolean inCreativeTab, @Nullable CreativeTabs tab, ToolMaterial materialIn)
	{
		super(materialIn);
		setUnlocalizedName(SlimeBreeder.MODID + "_"+name);
		setRegistryName(name);
		
		if(inCreativeTab)
		{
			setCreativeTab(tab);
		}
	}
	
	
	
	@Override
	public void registerModels() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean getTop() {
		// TODO Auto-generated method stub
		return false;
	}
}
