package com.itchymichi.slimebreeder.items;

import java.util.List;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.SlimeBreederTab;
import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.inventory.InventorySlimeReader;
import com.itchymichi.slimebreeder.utility.CommonProxy;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class Slimalyser extends Item implements IHasModel
{
	//private String name = " ";
	//Item setCreativeTab;
	 
	public Slimalyser(String name, boolean inCreativeTab, CreativeTabs tab, int size)
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
	public void registerModels() 
	{
		
		SlimeBreeder.proxy.registerItemRenderer(this, 0, "inventory");
		
	}

	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1; // return any value greater than zero
	}
	
	
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		ItemStack itemStackIn = playerIn.getHeldItem(hand);
		
		if (!worldIn.isRemote && playerIn.isSneaking())
	    {
			
			
	        /*playerIn.openGui(
	        		
	        		SlimeBreeder.INSTANCE,
	        		SlimeBreeder.GUI_ENUM.READER.ordinal(),
	        		worldIn, (int) 0, (int) 0, (int) 0
					
					);*/
	        //System.out.println("Gui Open ?");
	        ;
	        
	        
	    }
		
		return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}
	


	
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {

        return true;
    }

	@Override
	public boolean getTop() {
		// TODO Auto-generated method stub
		return false;
	}
	



}
