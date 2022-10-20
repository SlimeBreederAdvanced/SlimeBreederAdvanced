package com.itchymichi.slimebreeder.items;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.SlimeBreederTab;
import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.inventory.InventorySlimeReader;
import com.itchymichi.slimebreeder.items.gui.GuiSlimepedia;
import com.itchymichi.slimebreeder.utility.CommonProxy;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Slimepedia extends Item implements IHasModel
{
	//private String name = "";
	//Item setCreativeTab;
	EntityPlayer Breeder;
	int signed;

	
	 
	public Slimepedia(String name, boolean inCreativeTab, CreativeTabs tab, int size)
	{
		super();
		
		
		setUnlocalizedName(SlimeBreeder.MODID + "_" + name);
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
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{

		
		
	}
	
	@SideOnly(Side.CLIENT)
	 public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {

		if(stack.hasTagCompound())
		{
			tooltip.add(stack.getTagCompound().getString("Owner")+"'s Manual");
		}
		else
		{
			tooltip.add("Press 'SHIFT' & 'RIGHT-CLICK' to open the manual");
		}
	 
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		ItemStack itemStackIn = playerIn.getHeldItem(hand);
		
		if (playerIn.isSneaking())
	    {
			if(itemStackIn.getTagCompound() == null)
			{
				
				itemStackIn.setTagCompound(new NBTTagCompound());
				itemStackIn.getTagCompound().setString("Owner", playerIn.getName());
			}
			
			
	        
			playerIn.openGui(
	        		
	        		SlimeBreeder.INSTANCE,
	        		SlimeBreeder.GUI_ENUM.BOOK.ordinal(),
	        		worldIn, (int) 0, (int) 0, (int) 0
	        							
	        							);
			
	        //System.out.println("Gui Open ?");
	        ;
	        
	        
	    }
		
			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}
		
		
    

	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
		
        return true;
    }
	

	public EntityPlayer getBreeder()
	{
		return Breeder; 
	}
	public void setSigned(int signed)
	{
		this.signed = signed;
	}

	public int getSigning(ItemStack stack) {
		
		return stack.getTagCompound().getInteger("Owned");
	}

	@Override
	public boolean getTop() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}