package com.itchymichi.slimebreeder.handlers;

import com.itchymichi.slimebreeder.SlimeBreeder;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;

public class MeshHandler implements ItemMeshDefinition
{

	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack) 
	{
		//System.out.println("Run Custom Mesh");
		
		if(stack.getSubCompound(SlimeBreeder.MODID) != null)
		{
			NBTTagCompound nbt = stack.getSubCompound(SlimeBreeder.MODID);
			NBTTagList coreitems = nbt.getTagList("core", 10);
			NBTTagCompound serialItem = coreitems.getCompoundTagAt(0);
			
			ItemStack appearance = new ItemStack(serialItem);
			Item itemIn = appearance.getItem();

			

			
			ModelResourceLocation variantModel = new ModelResourceLocation(itemIn.getRegistryName().toString(), "inventory");
			//System.out.println(" Location = " + variantModel);
			return variantModel;
		}
		//System.out.println("Found no model location");
		 return null;
	}

	
	
	
}
