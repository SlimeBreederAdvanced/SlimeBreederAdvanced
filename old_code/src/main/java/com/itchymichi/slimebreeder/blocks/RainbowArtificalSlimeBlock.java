package com.itchymichi.slimebreeder.blocks;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederBlocks;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeRainbowCrystal;
import com.itchymichi.slimebreeder.items.ArtificalSlimeBlockItem;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class RainbowArtificalSlimeBlock extends Block implements IHasModel
{

	public RainbowArtificalSlimeBlock(String name, Material materialIn) 
	{
		super(materialIn);
		//System.out.println("Running ?");
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		

		
		SlimeBreederBlocks.BLOCKS.add(this);
		//SlimeBreederItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		ArtificalSlimeBlockItem ARTIFICALSLIMEBLOCKITEM = new ArtificalSlimeBlockItem("rainbowartificalslimeblock", true, SlimeBreeder.proxy.slimetab, 1, ((Block)(this)));
		
		

	}
	
	
	
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
		

	//EntityArtificalSlimeRainbowCrystal rainbowslime = EntityList.createEntityFromNBT(slimeTag, worldIn);
		
	//rainbowslime.setPosition(pos.getX(), pos.getY(), pos.getZ());

	worldIn.destroyBlock(pos, false);
	//worldIn.spawnEntity(rainbowslime);
	 
		
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
	        return BlockRenderLayer.TRANSLUCENT;
	    }
	 
	 @Override
	 public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos)
	    {
		 //System.out.println("Running Light Opac");
	        return 100;
	    }

	 

	@Override
	public void registerModels() 
	{
		SlimeBreeder.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
		System.out.println("register model slime block");
	}




	@Override
	public boolean getTop() {
		// TODO Auto-generated method stub
		return false;
	}

}
