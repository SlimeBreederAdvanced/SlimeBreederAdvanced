package com.itchymichi.slimebreeder.items;

import java.util.Random;

import javax.annotation.Nullable;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.utility.CommonProxy;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SlimeSeed extends Item implements IHasModel
{

	private String name = "";
	Item setCreativeTab;
	
	int Charge;
	boolean Charged;
	boolean Absorbed;
	boolean inCauldron;
	
	
	public SlimeSeed(String name, Boolean inCreativeTab, @Nullable CreativeTabs tab, int size)
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
		public int getEntityLifespan(ItemStack itemStack, World world) {
			

			// TODO Auto-generated method stub
			return 300;
		}
		
		@Override
		public boolean onEntityItemUpdate(EntityItem entityItem) {
			//System.out.println("Dropped");
			
			NBTTagCompound nbt = entityItem.getEntityData();
			
			if(!entityItem.getEntityWorld().isRemote) {
				
				
				if(entityItem.getItem().getItem() == SlimeBreederItems.SLIMESEED) {
					
					BlockPos pos0 = new BlockPos(entityItem.getPosition().getX(), (entityItem.getPosition().getY()) , entityItem.getPosition().getZ());
					IBlockState iblockstate = entityItem.getEntityWorld().getBlockState(pos0);
					Block block = iblockstate.getBlock();
					
					if(block instanceof BlockCauldron && iblockstate.getProperties().containsValue(3)) {
						
						this.inCauldron = true;
						
						if(!nbt.hasKey("cauldron")) {
							nbt.setBoolean("cauldron", inCauldron);
						}
						/*if(entityItem.getAge() > 120) {
							
							entityItem.lifespan = 300;
						}*/
						
					}

				}
			}
				
			
			return false;
		}
		
		@Override
		public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
		{
			
		}
		
		
		
	

	public String getName()
	{
		return name;
	}




	@Override
	public void registerModels() 
	{
		
		SlimeBreeder.proxy.registerItemRenderer(this, 0, "inventory");
		
	}




	@Override
	public boolean getTop() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
