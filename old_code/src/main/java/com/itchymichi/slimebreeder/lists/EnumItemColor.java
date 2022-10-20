package com.itchymichi.slimebreeder.lists;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnumItemColor 
{
	public static CopyOnWriteArrayList<SlimeColor> listOfObjects = new CopyOnWriteArrayList<SlimeColor>();
	
	public static CopyOnWriteArrayList<SlimeColor> initColorList() {

	listOfObjects.add(new SlimeColor(new ItemStack(Items.PORKCHOP, 1, 0), 646, 0.80F, 0.69F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.IRON_INGOT, 1, 0), 646, 0.90F, 0.90F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.FISH, 1, 1), 645, 0.37F, 0.45F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.MUTTON, 1, 0), 646, 0.95F, 0.69F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.BEEF, 1, 0), 643, 0.70F, 0.52F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.RED_FLOWER), 1, 4), 633, 0.84F, 0.44F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.RED_MUSHROOM), 1, 0), 646, 0.85F, 0.48F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.REDSTONE, 1, 0), 646, 1.00F, 0.50F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.CACTUS), 1, 0), 507, 0.77F, 0.11F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.RABBIT, 1, 0), 624, 1.00F, 0.92F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.CHICKEN, 1, 0), 628, 0.49F, 0.69F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.LEATHER, 1, 0), 628, 0.58F, 0.39F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.STONE), 1, 1), 646, 0.56F, 0.90F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.HARDENED_CLAY), 1, 0), 626, 0.37F, 0.41F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.ROTTEN_FLESH, 1, 0), 627, 0.59F, 0.29F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.FISH, 1, 2), 621, 0.91F, 0.54F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.SOUL_SAND), 1, 0), 619, 0.31F, 0.18F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.BROWN_MUSHROOM), 1, 0), 619, 0.25F, 0.29F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.SAND), 1, 1), 620, 0.69F, 0.38F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.DIRT), 1, 0), 617, 0.35F, 0.35F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.RED_FLOWER), 1, 5), 619, 0.83F, 0.44F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.QUARTZ, 1, 0), 601, 0.2F, 0.86F));
	//listOfObjects.add(new SlimeColor(new ItemStack(Items.lava, 1, 0), 614, 0.75F, 0.57F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.RABBIT_FOOT, 1, 0), 612, 0.5F, 0.6F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.LOG), 1, 1), 607, 0.37F, 0.29F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.RABBIT_HIDE, 1, 0), 610, 0.37F, 0.53F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.CARROT, 1, 0), 611, 1F, 0.55F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.LOG2) , 1, 1), 611, 0.55F, 0.16F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.PUMPKIN), 1, 0), 609, 0.82F, 0.45F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.LOG2), 1, 0), 622, 0.55F, 0.44F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.GLOWSTONE_DUST, 1, 0), 594, 1F, 0.89F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.BLAZE_POWDER, 1, 0), 617, 1F, 0.33F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.POTATO, 1, 0), 602, 0.75F, 0.65F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.BREAD, 1, 0), 602, 0.62F, 0.35F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.LOG) , 1, 0), 601, 0.35F, 0.46F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.LOG) , 1, 2), 597, 0.25F, 0.53F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.LOG), 1, 3), 614, 0.36F, 0.46F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.BLAZE_ROD, 1, 0), 595, 0.74F, 0.42F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.SAND), 1, 0), 587, 0.44F, 0.72F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.WATERLILY), 1, 0), 510, 0.33F, 0.3F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.SANDSTONE), 1, 0), 589, 0.42F, 0.73F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.FISH, 1, 3), 590, 1F, 0.44F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.DOUBLE_PLANT), 1, 0), 583, 0.88F, 0.55F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.GOLD_INGOT, 1, 0), 584, 0.97F, 0.64F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.RED_FLOWER), 1, 8), 583, 0.75F, 0.47F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.GOLDEN_CARROT, 1, 0), 588, 1F, 0.6F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.RED_FLOWER), 1, 3), 461, 0.35F, 0.92F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.MELON, 1, 0), 579, 0.62F, 0.42F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.GOLD_NUGGET, 1, 0), 579, 1F, 0.44F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.GOLDEN_APPLE, 1, 0), 576, 0.84F, 0.64F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.WHEAT, 1, 0), 576, 0.67F, 0.56F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.YELLOW_FLOWER), 1, 0), 585, 0.73F, 0.49F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.END_STONE), 1, 0), 579, 0.81F, 0.87F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.POISONOUS_POTATO, 1, 0), 571, 0.61F, 0.55F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.COAL, 1, 1), 605, 0.16F, 0.18F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.SAPLING), 1, 4), 564, 0.69F, 0.29F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.MOSSY_COBBLESTONE), 1, 0), 510, 0.27F, 0.32F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.REEDS, 1, 0), 547, 0.42F, 0.58F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.SAPLING), 1, 2), 546, 0.43F, 0.63F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.SAPLING), 1, 3), 527, 0.64F, 0.26F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.VINE), 1, 0), 532, 0.77F, 0.17F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.SKULL, 1, 2), 530, 0.38F, 0.27F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.GRASS), 1, 0), 529, 0.27F, 0.4F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.SKULL, 1, 4), 521, 0.5F, 0.54F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.SAPLING), 1, 5), 510, 0.67F, 0.19F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.SAPLING), 1, 1), 510, 0.22F, 0.17F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.SAPLING), 1, 0), 510, 0.67F, 0.19F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.EMERALD, 1, 0), 502, 0.81F, 0.48F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.FISH, 1, 0), 495, 0.2F, 0.44F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.ENDER_PEARL, 1, 0), 494, 0.75F, 0.17F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.DIAMOND, 1, 0), 489, 0.88F, 0.66F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.GHAST_TEAR, 1, 0), 492, 0.12F, 0.82F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.RED_FLOWER), 1, 1), 472, 0.96F, 0.57F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.PACKED_ICE) , 1, 0), 458, 0.62F, 0.75F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.ICE), 1, 0), 458, 0.49F, 0.66F));
	//listOfObjects.add(new SlimeColor(new ItemStack(Items.water, 1, 0), 452, 0.69F, 0.53F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.DYE, 1, 4), 459, 0.71F, 0.38F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.OBSIDIAN), 1, 0), 421, 0.25F, 0.13F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.RED_FLOWER), 1, 2), 407, 0.95F, 0.72F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.MYCELIUM), 1, 0), 397, 0.05F, 0.41F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.RED_FLOWER), 1, 7), 383, 0.42F, 0.76F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.SPIDER_EYE, 1, 0), 734, 0.46F, 0.57F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.NETHER_WART, 1, 0), 733, 0.64F, 0.4F));
	listOfObjects.add(new SlimeColor(new ItemStack(Items.APPLE, 1, 0), 732, 0.81F, 0.48F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.RED_FLOWER), 1, 0), 730, 0.95F, 0.38F));
	listOfObjects.add(new SlimeColor(new ItemStack(Item.getItemFromBlock(Blocks.NETHERRACK), 1, 0), 646, 0.3F, 0.44F));
	listOfObjects.add(new SlimeColor(new ItemStack(SlimeBreederItems.FILLEDSLIMECAPSULE, 1, 0), 0, 0, 0));



	
	return listOfObjects;

	}

	public static int getSize() 
	{
		int length = listOfObjects.size();
		
		return length;
	}
	
	
}


