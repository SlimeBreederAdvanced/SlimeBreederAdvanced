package com.itchymichi.slimebreeder.items;

import javax.annotation.Nullable;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemBaseArmour extends ItemArmor implements IHasModel
{

	public ItemBaseArmour(String name, Boolean inCreativeTab, @Nullable CreativeTabs tab, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);
		
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
