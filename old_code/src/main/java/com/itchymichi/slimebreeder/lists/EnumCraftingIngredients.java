package com.itchymichi.slimebreeder.lists;

import java.util.concurrent.CopyOnWriteArrayList;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnumCraftingIngredients 
{
	
public static CopyOnWriteArrayList<SlimeItems> listOfItems = new CopyOnWriteArrayList<SlimeItems>();
	
	public static CopyOnWriteArrayList<SlimeItems> initItemList()
	{
		listOfItems.add(new SlimeItems(new ItemStack(Items.CHICKEN, 1, 0), 3D, 3D, 2, 0, 0, 300));
		listOfItems.add(new SlimeItems(new ItemStack(Items.DIAMOND, 1, 0), 3D, 3D, 8, 0, 0, 120));
		//listOfItems.add(new SlimeItems(new ItemStack(Items.DIAMOND_SWORD, 1, 0), 3D, 3D, 2, 0, 0, 50));
		listOfItems.add(new SlimeItems(new ItemStack(Items.GOLD_INGOT, 1, 0), 3D, 3D, 6, 0, 0, 150));
		//listOfItems.add(new SlimeItems(new ItemStack(SlimeBreederItems.SLIMECAPSULE, 1, 0), 3D, 3D, 2, 0, 0, 20));
		//listOfItems.add(new SlimeItems(new ItemStack(SlimeBreederItems.FILLEDSLIMECAPSULE, 1, 0), 3D, 3D, 2, 0, 0, 20));
		listOfItems.add(new SlimeItems(new ItemStack(SlimeBreederItems.BREEDINGCATALYSTIRON, 1, 0), 3D, 3D, 2, 0, 0, 150));
		listOfItems.add(new SlimeItems(new ItemStack(SlimeBreederItems.CLEARSLIMECRYSTAL, 1, 0), 3D, 3D, 2, 0, 0, 150));
		listOfItems.add(new SlimeItems(new ItemStack(SlimeBreederItems.SLIMESEED, 1, 0), 3D, 3D, 2, 0, 0, 150));
		listOfItems.add(new SlimeItems(new ItemStack(SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL, 1, 0), 3D, 3D, 2, 0, 0, 150));

		//listOfItems.add(new SlimeItems(new ItemStack(Items.APPLE, 1, 0), 3D, 3D, 2, 0, 0, 20));
		listOfItems.add(new SlimeItems(new ItemStack(Items.ARROW, 1, 0), 3D, 3D, 2, 0, 0, 20));
		listOfItems.add(new SlimeItems(new ItemStack(Items.ROTTEN_FLESH, 1, 0), 3D, 3D, 6, 2, 0, 300));
		listOfItems.add(new SlimeItems(new ItemStack(Item.getItemFromBlock(Blocks.GLASS_PANE), 1, 0), 5D, 3D, 4, 2, 0, 400));

		//listOfItems.add(new SlimeItems(new ItemStack(SlimeBreederItems.SLIMERESIN, 1, 0), 3D, 3D, 2, 0, 0, 20));
		listOfItems.add(new SlimeItems(new ItemStack(Item.getItemFromBlock(Blocks.IRON_ORE), 1, 0), 3D, 12D, 4, 0, 0, 400));
		listOfItems.add(new SlimeItems(new ItemStack(Item.getItemFromBlock(Blocks.GOLD_ORE), 1, 0), 3D, 12D, 6, 0, 0, 500));
		listOfItems.add(new SlimeItems(new ItemStack(Item.getItemFromBlock(Blocks.DIAMOND_ORE), 1, 0), 3D, 12D, 8, 0, 0, 600));
		listOfItems.add(new SlimeItems(new ItemStack(Item.getItemFromBlock(Blocks.SANDSTONE), 1, 0), 5D, 3D, 4, 0, 0, 400));
		listOfItems.add(new SlimeItems(new ItemStack(Item.getItemFromBlock(Blocks.COBBLESTONE), 1, 0), 5D, 3D, 4, 2, 0, 400));
		listOfItems.add(new SlimeItems(new ItemStack(Item.getItemFromBlock(Blocks.GRAVEL), 1, 0), 5D, 3D, 4, 2, 0, 400));
		listOfItems.add(new SlimeItems(new ItemStack(Item.getItemFromBlock(Blocks.DIRT), 1, 0), 5D, 3D, 2, 2, 0, 400));//move(Free-flowing)3-7, health(rubbery)45, attack(acidity), sticky

		/*
        
        if (dropMoveSpeed2 >= 3 && dropMoveSpeed2 < 4) 
        {
            E = TextFormatting.WHITE+"[Slightly]";
        } else if (dropMoveSpeed2 >= 4 && dropMoveSpeed2 < 5) {
        	 E = TextFormatting.AQUA+"[Somewhat]";
        } else if (dropMoveSpeed2 >= 5 && dropMoveSpeed2 < 6) {
        	 E = TextFormatting.BLUE+"[Very]";
        } else if (dropMoveSpeed2 >= 6 && dropMoveSpeed2 <= 7) {
        	 E = TextFormatting.DARK_PURPLE+"[Extremely]";
        } else {
        	 E = " ";
        }
        
        if (dropHealth2 >= 3 && dropHealth2 < 6) 
        {
            C = TextFormatting.WHITE+"[Slightly]";
        } else if (dropHealth2 >= 6 && dropHealth2 < 12) {
        	 C = TextFormatting.AQUA+"[Somewhat]";
        } else if (dropHealth2 >= 12 && dropHealth2 < 25) {
        	 C = TextFormatting.BLUE+"[Very]";
        } else if (dropHealth2 >= 25 && dropHealth2 <= 50) {
        	 C = TextFormatting.DARK_PURPLE+"[Extremely]";
        } else {
        	 C = " ";
        }
        
        if (AD >= 2 && AD < 4) 
        {
            B = TextFormatting.WHITE + "[Slightly]";
        } else if (AD >= 4 && AD < 6) {
        	 B = TextFormatting.AQUA +"[Somewhat]";
        } else if (AD >= 6 && AD < 8) {
        	 B = TextFormatting.BLUE + "[Very]";
        } else if (AD >= 8 && AD <= 10) {
        	 B = TextFormatting.DARK_PURPLE+"[Extremely]";
        } else {
        	 B = " ";
        }
		
        if (STY >= 0 && STY < 1) 
        {
            A = TextFormatting.WHITE + "[Slightly]";
        } else if (STY >= 1 && STY < 2) {
        	 A = TextFormatting.AQUA +"[Somewhat]";
        } else if (STY >= 2 && STY < 3) {
        	 A = TextFormatting.BLUE +"[Very]";
        } else if (STY >= 3 && STY <= 4) {
        	 A = TextFormatting.DARK_PURPLE+"[Extremely]";
        } else {
        	 A = " ";
        }*/
		
		
		
		//listOfItems.add(new SlimeItems(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 0), 0D, 0D, 0, 0, 0, 60));
		
		
		return listOfItems;
		
		
	}
	

}
