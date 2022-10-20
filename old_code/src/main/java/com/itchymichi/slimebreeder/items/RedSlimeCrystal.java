package com.itchymichi.slimebreeder.items;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.utility.CommonProxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RedSlimeCrystal extends Item
{

	private String name = "";
	Item setCreativeTab;
	
	public RedSlimeCrystal() 
	{
		super();
		//name = uname;
		//this.setRegistryName(name);
		//GameRegistry.register(this);
		
		//GameRegistry.registerItem(this, uname);
		//name = uname;
		//setUnlocalizedName(SlimeBreeder.MODID + "_" + name);
		//this.setCreativeTab(CommonProxy.slimetab);
		setMaxStackSize(1);
	}



	

	public String getName()
	{
		return name;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
