package com.itchymichi.slimebreeder.lists;

import java.util.concurrent.CopyOnWriteArrayList;

import com.itchymichi.slimebreeder.SlimeBreederItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnumEdibleItems 
{
	

public static CopyOnWriteArrayList<SlimeEdibleItems> listOfEdibleItems = new CopyOnWriteArrayList<SlimeEdibleItems>();
	
	public static CopyOnWriteArrayList<SlimeEdibleItems> initEdibleItemsList() 
	{
		listOfEdibleItems.add(new SlimeEdibleItems(new ItemStack(SlimeBreederItems.BREEDINGCATALYSTIRON)));
		listOfEdibleItems.add(new SlimeEdibleItems(new ItemStack(Items.CHICKEN)));


		return listOfEdibleItems;

		}

		public static int getSize() 
		{
			int length = listOfEdibleItems.size();
			
			return length;
		}
		
		
		//listOfRecipes.add(new SlimeRecipes(new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Item.getItemFromBlock(Blocks.DIAMOND_BLOCK)), 0.0D, 0.0D, 0, 0, 0, false));

		
}
