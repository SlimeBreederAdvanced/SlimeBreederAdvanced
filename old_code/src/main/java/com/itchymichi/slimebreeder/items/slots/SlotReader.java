package com.itchymichi.slimebreeder.items.slots;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.lists.EnumItemColor;
import com.itchymichi.slimebreeder.lists.SlimeColor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotReader extends Slot
{


	
	
	public SlotReader(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		
		// TODO Auto-generated constructor stub
	}
	
	
@Override
    public boolean isItemValid(ItemStack stack)
	{
		//System.out.println(stack);
		//System.out.println(stack.getItem());
		
		if(stack.getItem() == SlimeBreederItems.FILLEDSLIMECAPSULE)
		{
			return true;
		}else
		
		
		EnumItemColor.listOfObjects.clear();
		EnumItemColor.initColorList();
		
		int length = EnumItemColor.listOfObjects.size();
		List<Item> stackList = new CopyOnWriteArrayList<Item>();
		
	for (int i = 0; i < length; i++) 
	{
		
		stackList.add(EnumItemColor.listOfObjects.get(i).item.getItem());
		
	}
		
		ItemStack itemToFind = stack;
		
		if(stackList.contains(itemToFind.getItem()))
		{
			//System.out.println("Found " + itemToFind.getItem());
			stackList.clear();
			return true;
		}else
		
		stackList.clear();
		return false;
		
			
	}
			


}
