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

public class EnumRainbowColor 
{
	public static CopyOnWriteArrayList<SlimeRainbowColor> listOfObjects = new CopyOnWriteArrayList<SlimeRainbowColor>();
	
	public static CopyOnWriteArrayList<SlimeRainbowColor> initColorList() {

		for (int r=0; r<100; r++)listOfObjects.add(new SlimeRainbowColor(r*255/100,       255,         0));
		for (int g=100; g>0; g--)listOfObjects.add(new SlimeRainbowColor(      255, g*255/100,         0));	
		for (int b=0; b<100; b++)listOfObjects.add(new SlimeRainbowColor(      255,         0, b*255/100));
		for (int r=100; r>0; r--)listOfObjects.add(new SlimeRainbowColor(r*255/100,         0,       255));
		for (int g=0; g<100; g++)listOfObjects.add(new SlimeRainbowColor(        0, g*255/100,       255));
		for (int b=100; b>0; b--)listOfObjects.add(new SlimeRainbowColor(        0,       255, b*255/100));
		                         listOfObjects.add(new SlimeRainbowColor(        0,       255,         0));


	
	return listOfObjects;

	}

	public static int getSize() 
	{
		int length = listOfObjects.size();
		
		return length;
	}
	
	
}


