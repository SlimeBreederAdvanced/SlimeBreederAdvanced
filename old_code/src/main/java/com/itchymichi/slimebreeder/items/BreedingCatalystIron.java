package com.itchymichi.slimebreeder.items;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.utility.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BreedingCatalystIron extends Item
{

	private String name = "";
	Item setCreativeTab;
	
	public BreedingCatalystIron() 
	{
		super();
		/*name = uname;
		this.setRegistryName(name);
		GameRegistry.register(this);
		
		//GameRegistry.registerItem(this, uname);
		setUnlocalizedName(SlimeBreeder.MODID + "_" + name);*/
		//this.setCreativeTab(CreativeTabs.MISC);
		setMaxStackSize(16);
	}



	

	public String getName()
	{
		return name;
	}
	
}
