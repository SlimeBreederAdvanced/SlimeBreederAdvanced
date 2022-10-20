package com.itchymichi.slimebreeder.items;

import javax.annotation.Nullable;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CraftingNote extends Item implements IHasModel
{

	private String name = "";
	Item setCreativeTab;
	
	
	public CraftingNote(String name, Boolean inCreativeTab, @Nullable CreativeTabs tab, int size)
	{
		super();
		
		
		setUnlocalizedName(SlimeBreeder.MODID + "_"+name);
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
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean getTop() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if(stack.getTagCompound() == null && isSelected)
		{
			NBTTagCompound nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
			
			
		}
		
		if(stack.getTagCompound() != null && isSelected)
		{
			NBTTagCompound nbt = stack.getTagCompound();
			
			if(nbt.hasKey("recipeName")){
				
				//System.out.println(nbt.getString("recipeName"));
				
				ResourceLocation recipelocation = new ResourceLocation(nbt.getString("recipeName"));
				
				IRecipe recipe = CraftingManager.getRecipe(recipelocation);
				
				
				//System.out.println(item1 + " " + item2 );

			}
			
		}
		
		
	}
	
	
	

}
