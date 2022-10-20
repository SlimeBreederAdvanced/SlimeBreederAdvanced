package com.itchymichi.slimebreeder.items;

import javax.annotation.Nullable;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SlimeTapItem extends ItemBlock implements IHasModel
{

	public SlimeTapItem(String name, Boolean inCreativeTab, @Nullable CreativeTabs tab, int size, Block slimeCore)
	{
		super(slimeCore);
		
		
		
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
		
		SlimeBreeder.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		
		//System.out.println("no tag");
		if(stack.getTagCompound() == null){
			stack.getOrCreateSubCompound("accessory");
		}
		
		if(stack.getTagCompound() != null){
			
			NBTTagCompound nbt = stack.getTagCompound();
			
			nbt.setBoolean("top", false);
			nbt.setBoolean("front", true);
			nbt.setBoolean("back", true);
			nbt.setBoolean("lside", true);
			nbt.setBoolean("rside", true);
			
		}
		
	}



	@Override
	public boolean getTop() {
		// TODO Auto-generated method stub
		return true;
	}
}
