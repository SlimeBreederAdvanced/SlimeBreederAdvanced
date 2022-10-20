package com.itchymichi.slimebreeder.lists;

import java.util.concurrent.CopyOnWriteArrayList;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnumAccessory 
{
	
public static CopyOnWriteArrayList<SlimeAccessories> listOfItems = new CopyOnWriteArrayList<SlimeAccessories>();
	
	public static CopyOnWriteArrayList<SlimeAccessories> initItemList()
	{
		listOfItems.add(new SlimeAccessories(new ItemStack(Item.getItemFromBlock(Blocks.GLASS_PANE), 1, 0)));

		
		return listOfItems;
		
		
	}
	

}
