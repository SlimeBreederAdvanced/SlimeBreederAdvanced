package com.itchymichi.slimebreeder.items;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederBlocks;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeCrystal;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeRainbowCrystal;
import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SlimeCoreBackup extends ItemBlock implements IHasModel
{
	
	private String name = "";
	
	private NBTTagCompound fluidNBT;
	
	int CatalystLevel;
	
	/*int DropW;
	float DropS;
	float DropB;
	
	private int DropBreedingSpeed;
	private double DropMoveSpeed;
	private double DropHealth;
	private int DropAttackDamage;
	private int DropSticky;	*/
	
	
	public SlimeCoreBackup(String name, Boolean inCreativeTab, @Nullable CreativeTabs tab, int size, Block slimeCore)
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
	public void registerModels() 
	{
		
		SlimeBreeder.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
	
	public NBTTagCompound getFluidNBT(){
		return this.fluidNBT;
	}
	
	
	/*@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
		
		if(stack.getTagCompound() != null){
			
			
			this.fluidNBT = stack.getTagCompound().getCompoundTag("FLUID");
			
			this.DropW = stack.getTagCompound().getInteger("Wave");
			this.DropS = stack.getTagCompound().getFloat("Sat");
			this.DropB = stack.getTagCompound().getFloat("Bright");
			
			this.DropBreedingSpeed = stack.getTagCompound().getInteger("BreedingSpeed");
			this.DropMoveSpeed = stack.getTagCompound().getDouble("MoveSpeed");
			this.DropHealth = stack.getTagCompound().getDouble("Health");
			this.DropAttackDamage = stack.getTagCompound().getFloat("AttackDamage");
			this.DropSticky = stack.getTagCompound().getFloat("Sticky");
			System.out.println(this.fluidNBT);
			
			}
		
		
		
    }*/
	
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		//stack.getOrCreateSubCompound(SlimeBreeder.MODID);
		if(stack.getTagCompound() != null){
			

			
			if(stack.getTagCompound().hasKey("Catalyst")){

				
				this.CatalystLevel = stack.getTagCompound().getInteger("Catalyst");
				
				/*this.DropW = stack.getTagCompound().getInteger("Wave");
				this.DropS = stack.getTagCompound().getFloat("Sat");
				this.DropB = stack.getTagCompound().getFloat("Bright");
				
				this.DropBreedingSpeed = stack.getTagCompound().getInteger("BreedingSpeed");
				this.DropMoveSpeed = stack.getTagCompound().getDouble("MoveSpeed");
				this.DropHealth = stack.getTagCompound().getDouble("Health");
				this.DropAttackDamage = stack.getTagCompound().getInteger("AttackDamage");
				this.DropSticky = stack.getTagCompound().getInteger("Sticky");*/
				
			}
			
			
		/*
		
		
		
		   /**/
			if(stack.getTagCompound().hasKey("FLUID")){
	     
				this.fluidNBT = stack.getTagCompound().getCompoundTag("FLUID");
				
			}
		//System.out.println(this.fluidNBT);
		
		}
		//System.out.println(FluidNBT);
		
		/*if(stack.getTagCompound() != null && stack.getTagCompound().getTag("FLUID") != null)
		{
			
			
		    stack.getTagCompound().setTag("FLUID", FluidNBT);

		}*/

	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1; // return any value greater than zero
	}
	
	@Override
	 public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		
		IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
        
        System.out.println("Block ?");
        System.out.println(ItemBlock.getItemFromBlock(block) + " " + ItemBlock.getItemFromBlock(SlimeBreederBlocks.CLEARSLIMECASING));
        
        if(ItemBlock.getItemFromBlock(block)==ItemBlock.getItemFromBlock(SlimeBreederBlocks.CLEARSLIMECASING)|| ItemBlock.getItemFromBlock(block)==ItemBlock.getItemFromBlock(SlimeBreederBlocks.REDSLIMECASING)|| ItemBlock.getItemFromBlock(block)==ItemBlock.getItemFromBlock(SlimeBreederBlocks.BLUESLIMECASING)|| ItemBlock.getItemFromBlock(block)==ItemBlock.getItemFromBlock(SlimeBreederBlocks.GREENSLIMECASING)|| ItemBlock.getItemFromBlock(block)==ItemBlock.getItemFromBlock(SlimeBreederBlocks.RAINBOWSLIMECASING)){
        	
        	System.out.println("Block true");
        	 if(player.getHeldItem(hand).hasTagCompound()) {
        	 if(player.getHeldItem(hand).getTagCompound().hasKey("Catalyst") && player.getHeldItem(hand).getTagCompound().hasKey("FLUID")){
        		 EntityArtificalSlimeCrystal entitySpawn = new EntityArtificalSlimeCrystal(worldIn);
        		 Boolean rainbowSpawn = false;
        		 
        		 switch (ItemBlock.getItemFromBlock(block).getUnlocalizedName()) 
					{
			        case "tile.clearslimecasing":
			        	entitySpawn.setFRGB(0);
			        	
			        	System.out.println("Clear " + entitySpawn.getRGB2());
			        	break;
					case "tile.redslimecasing":
						entitySpawn.setFRGB(1);
						System.out.println("Red " + entitySpawn.getRGB2());
						break;
					case "tile.blueslimecasing":
						entitySpawn.setFRGB(2);
						System.out.println("Blue " + entitySpawn.getRGB2());
						break;	
					case "tile.greenslimecasing":
						entitySpawn.setFRGB(3);
						System.out.println("Green " + entitySpawn.getRGB2());
						break;
					case "tile.rainbowslimecasing":
						
						 rainbowSpawn = true;
						
						
						break;
					default:
						System.out.println(ItemBlock.getItemFromBlock(block).getUnlocalizedName());		
						break;
					}	
        		//EntityArtificalSlimeCrystal entitySpawn = new EntityArtificalSlimeCrystal(worldIn);
     			//entitySpawn.wildInit(entitySpawn);
        		 System.out.println(rainbowSpawn);
        		if(!rainbowSpawn) { 
        		entitySpawn.initArtificalSlime(3, 6, 8, 6, 2);
        		
        		 
        		if(player.getHeldItem(hand).getTagCompound().hasKey("FLUID")){
        			entitySpawn.setCoreFluid(player.getHeldItem(hand).getTagCompound().getCompoundTag("FLUID"));
        		}
        		 
        		player.inventory.clearMatchingItems(this, -1, 1, null);
           	 	
     			entitySpawn.setLocationAndAngles(pos.getX(), (pos.getY()+1.00D), pos.getZ(), 0.0F, 0.0F);
     			worldIn.destroyBlock(pos, false);
     			if(!worldIn.isRemote) {
     			worldIn.spawnEntity(entitySpawn);
     			}
     			//System.out.println(entitySpawn.getACFront());
     		
        	 }else{
        		 EntityArtificalSlimeRainbowCrystal entityrainbowSpawn = new EntityArtificalSlimeRainbowCrystal(worldIn);
        		 entityrainbowSpawn.initWildSlime(3, 450, 1, 1, 22928, 5, 24, 6, 3);
         		
        		 
         		if(player.getHeldItem(hand).getTagCompound().hasKey("FLUID")){
         			entityrainbowSpawn.setCoreFluid(player.getHeldItem(hand).getTagCompound().getCompoundTag("FLUID"));
         		}
         		 
         		player.inventory.clearMatchingItems(this, -1, 1, null);
            	 	
         		entityrainbowSpawn.setLocationAndAngles(pos.getX(), (pos.getY()+1.00D), pos.getZ(), 0.0F, 0.0F);
      			worldIn.destroyBlock(pos, false);
      			if(!worldIn.isRemote) {
      			worldIn.spawnEntity(entityrainbowSpawn);
      			}
      			//System.out.println(entitySpawn.getACFront());
      		}
        	 
        
        
        }
		
        	 }
        	 } 	
		return EnumActionResult.SUCCESS;
		 
	    }
        	 
	
	





	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
		String flavor;
		
		if(!stack.hasTagCompound() ) {
			
			tooltip.add("Infused Catalyst: " + TextFormatting.WHITE + "None " + TextFormatting.GRAY + "Infused Liquid: " + TextFormatting.WHITE +  "None");
			this.block.addInformation(stack, worldIn, tooltip, flagIn);
		      return;
		    }
		
		
		
		
		
	
		if(stack.hasTagCompound()) {
			
			
			if(!stack.getTagCompound().hasKey("Catalyst") && !stack.getTagCompound().hasKey("FLUID"))
			{

					
					
					tooltip.add("Infused Catalyst: " + TextFormatting.WHITE + "None " + TextFormatting.GRAY + "Infused Liquid: " + TextFormatting.WHITE +  "None");
					
					this.block.addInformation(stack, worldIn, tooltip, flagIn);
				    return;
					
					
			}
			
			if(stack.getTagCompound().hasKey("Catalyst") && !stack.getTagCompound().hasKey("FLUID")){
				if(stack.getTagCompound().getInteger("Catalyst") != 0){
					
					
					
					tooltip.add("Infused Catalyst: " + TextFormatting.YELLOW + "Yes " + TextFormatting.GRAY + "Infused Liquid: " + TextFormatting.WHITE +  "None");
					tooltip.add("Hold 'LEFT SHIFT' for more infomation");

					int Lshift = Keyboard.KEY_LSHIFT;
					//System.out.println("Running");
					//System.out.println("Tag ? " + stack.hasTagCompound());
					
					if(Keyboard.isKeyDown(Lshift))
					{
						//flavor = getFlavor(stack.getTagCompound().getInteger("BreedingSpeed"), stack.getTagCompound().getDouble("MoveSpeed"), stack.getTagCompound().getDouble("Health"), stack.getTagCompound().getFloat("AttackDamage"), stack.getTagCompound().getFloat("Sticky"));
						
						tooltip.clear();
						tooltip.add("Catalyst Level:" + " " + stack.getTagCompound().getInteger("Catalyst"));
					}
				      
				      this.block.addInformation(stack, worldIn, tooltip, flagIn);
				      return;
				    }
			}
			
			if(!stack.getTagCompound().hasKey("Catalyst") && stack.getTagCompound().hasKey("FLUID")){

					
					
					
					tooltip.add("Infused Catalyst: " + TextFormatting.WHITE + "None " + TextFormatting.GRAY + "Infused Liquid: " + TextFormatting.YELLOW + "Yes");
					tooltip.add("Hold 'LEFT SHIFT' for more infomation");

					int Lshift = Keyboard.KEY_LSHIFT;
					//System.out.println("Running");
					//System.out.println("Tag ? " + stack.hasTagCompound());
					
					if(Keyboard.isKeyDown(Lshift))
					{
						
						String fluid;

						
						//NBTTagList potionList = tagCompound.getTagList("POTION", 10);
						
						//NBTTagCompound Potion = potionList.getCompoundTagAt(0);
						if(stack.getTagCompound().getCompoundTag("FLUID").hasKey("POTION")){
						NBTTagList potionList = stack.getTagCompound().getCompoundTag("FLUID").getTagList("POTION", 10).copy();
						
						NBTTagCompound Potion2 = potionList.getCompoundTagAt(0).copy();
						
						fluid = TextFormatting.WHITE + "Fluid: " +TextFormatting.GRAY+ new ItemStack(Potion2).getDisplayName() + ", " + FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("FLUID")).getFluid().getRarity().rarityColor + FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("FLUID")).getFluid().getRarity();
						
						}else{
							fluid = TextFormatting.WHITE + "Fluid: " +TextFormatting.GRAY+ FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("FLUID")).getLocalizedName() + FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("FLUID")).getFluid().getRarity().rarityColor + FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("FLUID")).getFluid().getRarity();

						}
						//System.out.println(stack.getTagCompound().getCompoundTag("FLUID"));
						
						
						
						//if(FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("FLUID")).getFluid() == FluidRegistry.WATER && Potion2 != null){
						//fluid = "Fluid = " + PotionUtils.getPotionFromItem(new ItemStack(Potion2)) + ", " + FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("FLUID")).getFluid().getRarity().rarityColor + FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("FLUID")).getFluid().getRarity();
							

						tooltip.clear();
						tooltip.add(fluid);
					}
				      
				      this.block.addInformation(stack, worldIn, tooltip, flagIn);
				      return;
				    
			}
			
			
			if(stack.getTagCompound().hasKey("Catalyst") && stack.getTagCompound().hasKey("FLUID")){
				
					
					
					
				tooltip.add("Infused Catalyst: " + TextFormatting.YELLOW + "Yes " + TextFormatting.GRAY + "Infused Liquid: " + TextFormatting.YELLOW + "Yes");
				tooltip.add("Hold 'LEFT SHIFT' for more infomation");
				
					int Lshift = Keyboard.KEY_LSHIFT;
					//System.out.println("Running");
					//System.out.println("Tag ? " + stack.hasTagCompound());
					
					if(Keyboard.isKeyDown(Lshift))
					{
						//flavor = getFlavor(stack.getTagCompound().getInteger("BreedingSpeed"), stack.getTagCompound().getFloat("Sticky"), stack.getTagCompound().getDouble("Health"), stack.getTagCompound().getFloat("AttackDamage"), stack.getTagCompound().getFloat("Sticky"));
						String fluid;
						//flavor = String.valueOf(DropBreedingSpeed);

						if(stack.getTagCompound().getCompoundTag("FLUID").hasKey("POTION")){
							NBTTagList potionList = stack.getTagCompound().getCompoundTag("FLUID").getTagList("POTION", 10).copy();
							
							NBTTagCompound Potion2 = potionList.getCompoundTagAt(0).copy();
							
							fluid = TextFormatting.WHITE + "Fluid: " +TextFormatting.GRAY+ new ItemStack(Potion2).getDisplayName() + ", " + FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("FLUID")).getFluid().getRarity().rarityColor + FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("FLUID")).getFluid().getRarity();
							System.out.println(stack.getTagCompound().getCompoundTag("FLUID"));
							}else{
								fluid = TextFormatting.WHITE + "Fluid: " +TextFormatting.GRAY+ FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("FLUID")).getFluid().getName() + FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("FLUID")).getFluid().getRarity().rarityColor + FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("FLUID")).getFluid().getRarity();

							}
						tooltip.clear();
						tooltip.add("Catalyst Level:" + " " + stack.getTagCompound().getInteger("Catalyst"));
						tooltip.add(fluid);
						
						//System.out.println(stack.getTagCompound().getCompoundTag("FLUID").getString("POTION"));
						
					}
				      
				      this.block.addInformation(stack, worldIn, tooltip, flagIn);
				      return;
				    
			}

	  }
	}
	

	public String getName()
	{
		return name;
	}



	@Override
	public boolean getTop() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}