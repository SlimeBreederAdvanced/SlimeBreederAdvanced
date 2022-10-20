package com.itchymichi.slimebreeder.blocks;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederBlocks;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.entity.monster.EntitySlime2;
import com.itchymichi.slimebreeder.items.SlimeTapItem;
import com.itchymichi.slimebreeder.items.SlimeTopHatItem;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SlimeTopHat extends Block implements IHasModel
{

	public SlimeTopHat(String name, Material materialIn) 
	{
		super(materialIn);
		//System.out.println("Running ?");
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setLightOpacity(5);
		
		
		
		SlimeBreederBlocks.BLOCKS.add(this);
		
		SlimeTopHatItem SLIMETOPHAT = new SlimeTopHatItem("slimetophat", true, SlimeBreeder.proxy.slimetab, 1, ((Block)(this)));
		
		//SlimeCoreItem(this).setRegistryName(this.getRegistryName()));
		

	}

	@Override
	public void registerModels() 
	{
		SlimeBreeder.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	 
	 @Override
	    public boolean isFullCube(IBlockState state)
	    {
	        return true;
	    }
	 
	 @Override
	    public BlockRenderLayer getBlockLayer()
	    {
	        return BlockRenderLayer.CUTOUT_MIPPED;
	    }
	 
	 @Override
	 public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos)
	    {
	        return 100;
	    }
	 
	 @Override
	 public boolean getTop()
	    {
	        return false;
	    }
	 
	 
	 /*@Override
	 public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	    {
		 World worldObj = Minecraft.getMinecraft().world;
		 
		 EntitySlime2 slime = new EntitySlime2(worldObj);
		 if(worldObj.isRemote){
		 EntityItem entity = new EntityItem(worldObj, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.APPLE));
		 System.out.println("Slime Spawned! 1 ");
         worldObj.spawnEntity(entity);
		 }
		 System.out.println("Slime Spawned!");
		 //worldObj.spawnEntity(slime);
		 worldIn.destroyBlock(pos, false);
		 
		 
		 
	    }*/
	 
	 
	 
	 
	 
	/* public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {
		 ItemStack itemstack = playerIn.inventory.getCurrentItem();
		 if(itemstack.getItem() == SlimeBreederItems.CLEARSLIMECRYSTAL2 )
		 {
			playerIn.swingArm(hand);
		 	worldIn.destroyBlock(pos, false);
		 	if(!worldIn.isRemote){
		 		 EntitySlime2 slime = new EntitySlime2(worldIn);
		 		 slime = slime.initSpawn(slime, null, null, 2);
		 		 slime.setPosition(pos.getX(), pos.getY(), pos.getZ());
		 		 slime.setCoreFluid(itemstack.getTagCompound().getString("fluid"));
				 //EntityItem entity = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.APPLE));
				 System.out.println("Slime Spawned! 1 ");
		         worldIn.spawnEntity(slime);
				 }
		 	
		 	if(playerIn.inventory.hasItemStack(itemstack))
			{
				playerIn.inventory.clearMatchingItems(itemstack.getItem(), -1, 1, null);
				
				//player.inventory.decrStackSize(handStack, 1);
			}
		 
		 	
		 }
		 	
		 	
	        return false;
	    }*/
	 

	 
}
