package com.itchymichi.slimebreeder.items;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.entity.monster.EntitySlime2;
import com.itchymichi.slimebreeder.handlers.SBASoundHandler;
import com.itchymichi.slimebreeder.utility.CommonProxy;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BetaSlimeCoreIrontem extends ItemBlock implements IHasModel
{

	private String name = "";


	
	private NBTTagCompound fluidNBT;
	
	int DropW;
	float DropS;
	float DropB;
	
	private int DropBreedingSpeed;
	private double DropMoveSpeed;
	private double DropHealth;
	private int DropAttackDamage;
	private int DropSticky;
	

	
	
	
	
	public BetaSlimeCoreIrontem(String name, Boolean inCreativeTab, @Nullable CreativeTabs tab, int size, Block slimeCore)
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
			

			
			if(stack.getTagCompound().hasKey("Wave")){

				this.DropW = stack.getTagCompound().getInteger("Wave");
				this.DropS = stack.getTagCompound().getFloat("Sat");
				this.DropB = stack.getTagCompound().getFloat("Bright");
				
				this.DropBreedingSpeed = stack.getTagCompound().getInteger("BreedingSpeed");
				this.DropMoveSpeed = stack.getTagCompound().getDouble("MoveSpeed");
				this.DropHealth = stack.getTagCompound().getDouble("Health");
				this.DropAttackDamage = stack.getTagCompound().getInteger("AttackDamage");
				this.DropSticky = stack.getTagCompound().getInteger("Sticky");
				
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
        
        if(ItemBlock.getItemFromBlock(block)==ItemBlock.getItemFromBlock(Blocks.SLIME_BLOCK)){
        	
        	 
        	 
        	 if(player.getHeldItem(hand).getTagCompound().hasKey("Wave")){
        		 EntitySlime2 entitySpawn = new EntitySlime2(worldIn);
     			//entitySpawn.wildInit(entitySpawn);
        		entitySpawn.initWildSlime(3, DropW, DropS, DropB, DropBreedingSpeed, DropMoveSpeed, DropHealth, DropAttackDamage, DropSticky);
        		 
        		if(player.getHeldItem(hand).getTagCompound().hasKey("FLUID")){
        			entitySpawn.setCoreFluid(player.getHeldItem(hand).getTagCompound().getCompoundTag("FLUID"));
        		}
        		 
        		player.inventory.clearMatchingItems(this, -1, 1, null);
           	 	
     			entitySpawn.setLocationAndAngles(player.posX, player.posY, player.posZ, 0.0F, 0.0F);
     			worldIn.spawnEntity(entitySpawn);
     			
     			//System.out.println(entitySpawn.getACFront());
     		}
        }
		
        	
        	
		return EnumActionResult.SUCCESS;
		 
	    }
	
	public String getFlavor(int BS, double dropMoveSpeed2, double dropHealth2, float AD, float STY)
	{
		String A;
		String B;
		String C;
		String D;
		String E;
		
        if (BS > 11464 && BS <= 91712) 
        {
            D = TextFormatting.WHITE+"[Slightly]";
        } else if (BS > 5732 && BS <= 11464) {
        	 D = TextFormatting.AQUA+"[Somewhat]";
        } else if (BS > 2866 && BS <= 5732) {
        	 D = TextFormatting.BLUE+"[Very]";
        } else if (BS >= 1433 && BS <= 2866) {
        	 D = TextFormatting.DARK_PURPLE+"[Extremely]";
        } else {
        	 D = " ";
        }
        
        if (dropMoveSpeed2 >= 3 && dropMoveSpeed2 < 4) 
        {
            E = TextFormatting.WHITE+"[Slightly]";
        } else if (dropMoveSpeed2 >= 4 && dropMoveSpeed2 < 5) {
        	 E = TextFormatting.AQUA+"[Somewhat]";
        } else if (dropMoveSpeed2 >= 5 && dropMoveSpeed2 < 6) {
        	 E = TextFormatting.BLUE+"[Very]";
        } else if (dropMoveSpeed2 >= 6 && dropMoveSpeed2 <= 7) {
        	 E = TextFormatting.DARK_PURPLE+"[Extremely]";
        } else {
        	 E = " ";
        }
        
        if (dropHealth2 >= 3 && dropHealth2 < 6) 
        {
            C = TextFormatting.WHITE+"[Slightly]";
        } else if (dropHealth2 >= 6 && dropHealth2 < 12) {
        	 C = TextFormatting.AQUA+"[Somewhat]";
        } else if (dropHealth2 >= 12 && dropHealth2 < 25) {
        	 C = TextFormatting.BLUE+"[Very]";
        } else if (dropHealth2 >= 25 && dropHealth2 <= 50) {
        	 C = TextFormatting.DARK_PURPLE+"[Extremely]";
        } else {
        	 C = " ";
        }
        
        if (AD >= 2 && AD < 4) 
        {
            B = TextFormatting.WHITE + "[Slightly]";
        } else if (AD >= 4 && AD < 6) {
        	 B = TextFormatting.AQUA +"[Somewhat]";
        } else if (AD >= 6 && AD < 8) {
        	 B = TextFormatting.BLUE + "[Very]";
        } else if (AD >= 8 && AD <= 10) {
        	 B = TextFormatting.DARK_PURPLE+"[Extremely]";
        } else {
        	 B = " ";
        }
		
        if (STY >= 0 && STY < 1) 
        {
            A = TextFormatting.WHITE + "[Slightly]";
        } else if (STY >= 1 && STY < 2) {
        	 A = TextFormatting.AQUA +"[Somewhat]";
        } else if (STY >= 2 && STY < 3) {
        	 A = TextFormatting.BLUE +"[Very]";
        } else if (STY >= 3 && STY <= 4) {
        	 A = TextFormatting.DARK_PURPLE+"[Extremely]";
        } else {
        	 A = " ";
        }
		
		
		
		
		String flavour = TextFormatting.WHITE +"Genetics: "+ E + TextFormatting.GRAY +" free-flowing, "+ C + TextFormatting.GRAY +" rubbery, "+ B + TextFormatting.GRAY +" acidic, " + A + TextFormatting.GRAY +" sticky and "+ D +TextFormatting.GRAY +" active.";
		
		//System.out.println(flavour);
		
		return flavour;
	}





	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
		String flavor;
		
		if(!stack.hasTagCompound() ) {
			
			tooltip.add("Infused genetics: " + TextFormatting.WHITE + "None " + TextFormatting.GRAY + "Infused Liquid: " + TextFormatting.WHITE +  "None");
			this.block.addInformation(stack, worldIn, tooltip, flagIn);
		      return;
		    }
		
		
		
		
		
	
		if(stack.hasTagCompound()) {
			
			
			if(!stack.getTagCompound().hasKey("Wave") && !stack.getTagCompound().hasKey("FLUID"))
			{

					
					
					tooltip.add("Infused genetics: " + TextFormatting.WHITE + "None " + TextFormatting.GRAY + "Infused Liquid: " + TextFormatting.WHITE +  "None");
					
					this.block.addInformation(stack, worldIn, tooltip, flagIn);
				    return;
					
					
			}
			
			if(stack.getTagCompound().hasKey("Wave") && !stack.getTagCompound().hasKey("FLUID")){
				if(stack.getTagCompound().getInteger("BreedingSpeed") != 0){
					
					
					
					tooltip.add("Infused genetics: " + TextFormatting.YELLOW + "Yes " + TextFormatting.GRAY + "Infused Liquid: " + TextFormatting.WHITE +  "None");
					tooltip.add("Hold 'LEFT SHIFT' for more infomation");

					int Lshift = Keyboard.KEY_LSHIFT;
					//System.out.println("Running");
					//System.out.println("Tag ? " + stack.hasTagCompound());
					
					if(Keyboard.isKeyDown(Lshift))
					{
						flavor = getFlavor(stack.getTagCompound().getInteger("BreedingSpeed"), stack.getTagCompound().getDouble("MoveSpeed"), stack.getTagCompound().getDouble("Health"), stack.getTagCompound().getFloat("AttackDamage"), stack.getTagCompound().getFloat("Sticky"));
						
						tooltip.clear();
						tooltip.add(flavor);
					}
				      
				      this.block.addInformation(stack, worldIn, tooltip, flagIn);
				      return;
				    }
			}
			
			if(!stack.getTagCompound().hasKey("Wave") && stack.getTagCompound().hasKey("FLUID")){

					
					
					
					tooltip.add("Infused genetics: " + TextFormatting.WHITE + "None " + TextFormatting.GRAY + "Infused Liquid: " + TextFormatting.YELLOW + "Yes");
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
			
			
			if(stack.getTagCompound().hasKey("Wave") && stack.getTagCompound().hasKey("FLUID")){
				
					
					
					
				tooltip.add("Infused genetics: " + TextFormatting.YELLOW + "Yes " + TextFormatting.GRAY + "Infused Liquid: " + TextFormatting.YELLOW + "Yes");
				tooltip.add("Hold 'LEFT SHIFT' for more infomation");
				
					int Lshift = Keyboard.KEY_LSHIFT;
					//System.out.println("Running");
					//System.out.println("Tag ? " + stack.hasTagCompound());
					
					if(Keyboard.isKeyDown(Lshift))
					{
						flavor = getFlavor(stack.getTagCompound().getInteger("BreedingSpeed"), stack.getTagCompound().getFloat("Sticky"), stack.getTagCompound().getDouble("Health"), stack.getTagCompound().getFloat("AttackDamage"), stack.getTagCompound().getFloat("Sticky"));
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
						tooltip.add(flavor);
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
