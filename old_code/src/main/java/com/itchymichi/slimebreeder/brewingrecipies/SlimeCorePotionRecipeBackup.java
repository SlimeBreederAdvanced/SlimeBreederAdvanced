package com.itchymichi.slimebreeder.brewingrecipies;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederBlocks;
import com.itchymichi.slimebreeder.SlimeBreederItems;


import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
//import slimeknights.tconstruct.library.fluid.FluidColored;

public class SlimeCorePotionRecipeBackup implements IBrewingRecipe {
	
	Item slimesample = new ItemStack(SlimeBreederItems.FILLEDSLIMECAPSULE).getItem();
	Item bone = new ItemStack(Items.BONE).getItem();
	Item redstone = new ItemStack(Items.REDSTONE).getItem();
	Item glowstone = new ItemStack(Items.GLOWSTONE_DUST).getItem();
	
	Item potion = Items.POTIONITEM;
	Item slimecore = new ItemStack(SlimeBreederBlocks.SLIMECORE).getItem();

	@Override
	public boolean isInput(ItemStack input) {
		if(input.getItem() == slimecore){
			return true;
		}else{
		return false;}
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		if(ingredient.getItem() == bone || ingredient.getItem() == redstone || ingredient.getItem() == glowstone || ingredient.getItem() == potion || ingredient.getItem() == Items.WATER_BUCKET || ingredient.getItem() == Items.LAVA_BUCKET){
			return true;
		}else{
		return false;}
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		
		ItemStack output = new ItemStack(SlimeBreederBlocks.SLIMECORE);
		
		output.getOrCreateSubCompound(SlimeBreeder.MODID);
		
		NBTTagCompound fluidTag = new NBTTagCompound();
		
		if(ingredient.getItem() == potion){
			
			FluidStack stackFluid = new FluidStack(FluidRegistry.WATER, 1000);
			
			NBTTagCompound Potion = ingredient.copy().serializeNBT();
			
			NBTTagList nbtpotlist = new NBTTagList();
	    	   
	    	   
	       	
       		nbtpotlist.appendTag(Potion);
       		nbtpotlist.set(0, Potion);
       		
       		
       		fluidTag.setTag("POTION", nbtpotlist);
			
			stackFluid.writeToNBT(fluidTag);
			
			
			output.getTagCompound().setTag("FLUID", fluidTag);
			
		}
		
			if(ingredient.getItem() == bone){
	    
	       //output.getOrCreateSubCompound(SlimeBreeder.MODID);
				
		    output.getTagCompound().setInteger("Catalyst", 1);
	    
	       /*output.getTagCompound().setInteger("Wave", (ingredient.getTagCompound().getInteger("Wave")));
	       output.getTagCompound().setFloat("Sat", (ingredient.getTagCompound().getFloat("Sat")));
	       output.getTagCompound().setFloat("Bright", (ingredient.getTagCompound().getFloat("Bright")));
	       
	       output.getTagCompound().setInteger("BreedingSpeed", (ingredient.getTagCompound().getInteger("BreedingSpeed")));
	       output.getTagCompound().setDouble("MoveSpeed", (ingredient.getTagCompound().getDouble("MoveSpeed")));
	       output.getTagCompound().setDouble("Health", (ingredient.getTagCompound().getDouble("Health")));
	       output.getTagCompound().setFloat("AttackDamage", (ingredient.getTagCompound().getFloat("AttackDamage")));
	       output.getTagCompound().setFloat("Sticky", (ingredient.getTagCompound().getFloat("Sticky")));*/
	       
			}
			
			if(ingredient.getItem() == redstone){
				
				 output.getTagCompound().setInteger("Catalyst", 2);
				 
			}
			
			if(ingredient.getItem() == glowstone){
				
				 output.getTagCompound().setInteger("Catalyst", 3);
				 
			}
			
			if(ingredient.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)){
				//System.out.println(FluidRegistry.getFluidName(ingredient.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null).drain(1000, false)));
				
				FluidStack stackFluid = ingredient.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null).drain(1000, false);
				stackFluid.writeToNBT(fluidTag);
				output.getTagCompound().setTag("FLUID", fluidTag);
			}
			
			if(input.hasTagCompound()){
			    if(input.getTagCompound().hasKey("Catalyst")){
			    	 if(input.getTagCompound().getInteger("Catalyst") > output.getTagCompound().getInteger("Catalyst"))
			    		 output.getTagCompound().setInteger("Catalyst", (input.getTagCompound().getInteger("Catalyst")));
			    }
			    	
			    if(input.getTagCompound().hasKey("FLUID") && !output.getTagCompound().hasKey("FLUID")){
			    	output.getTagCompound().setTag("FLUID", input.getTagCompound().getTag("FLUID"));
			    }
			    
			    if(input.getTagCompound().hasKey("FLUID") && output.getTagCompound().hasKey("FLUID")){
			    	output.getTagCompound().setTag("FLUID", output.getTagCompound().getTag("FLUID"));
			    }
			}
			
			
			 /*if(input.hasTagCompound()){
				    if(input.getTagCompound().hasKey("Wave")){
				    output.getTagCompound().setInteger("Wave", (input.getTagCompound().getInteger("Wave")));
				    output.getTagCompound().setFloat("Sat", (input.getTagCompound().getFloat("Sat")));
				    output.getTagCompound().setFloat("Bright", (input.getTagCompound().getFloat("Bright")));
				       
				    output.getTagCompound().setInteger("BreedingSpeed", (input.getTagCompound().getInteger("BreedingSpeed")));
				    output.getTagCompound().setDouble("MoveSpeed", (input.getTagCompound().getDouble("MoveSpeed")));
				    output.getTagCompound().setDouble("Health", (input.getTagCompound().getDouble("Health")));
				    output.getTagCompound().setFloat("AttackDamage", (input.getTagCompound().getFloat("AttackDamage")));
				    output.getTagCompound().setFloat("Sticky", (input.getTagCompound().getFloat("Sticky")));
				    	}
				    if(input.getTagCompound().hasKey("FLUID") && !output.getTagCompound().hasKey("FLUID")){
				    	output.getTagCompound().setTag("FLUID", input.getTagCompound().getTag("FLUID"));
				    }
			
			
			 }*/
	       
	       
		       
		      
	       
	       
		return output;
	}

}
