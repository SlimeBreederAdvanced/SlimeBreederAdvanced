package com.itchymichi.slimebreeder.items;

import java.util.Set;

import javax.annotation.Nullable;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemTool;

public class ItemBaseTool extends ItemTool implements IHasModel
{

	public ItemBaseTool(String name, Boolean inCreativeTab, @Nullable CreativeTabs tab, ToolMaterial materialIn, Set<Block> effectiveBlocksIn)
	{
		super(materialIn, effectiveBlocksIn);
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
