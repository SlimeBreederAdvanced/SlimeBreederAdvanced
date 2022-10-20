package com.itchymichi.slimebreeder.events;

import java.awt.Event;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederArmors;
import com.itchymichi.slimebreeder.SlimeBreederBlocks;
import com.itchymichi.slimebreeder.SlimeBreederEnchantments;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.lists.EnumCraftingIngredients;
import com.itchymichi.slimebreeder.lists.EnumItemColor;
import com.itchymichi.slimebreeder.entity.ai.CustomSlimeMoveHelper;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeCrystal;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeRainbowCrystal;
import com.itchymichi.slimebreeder.entity.monster.EntityLivingSlimeBase;
import com.itchymichi.slimebreeder.items.SlimeSeed;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.management.PlayerInteractionManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class SlimeBreederEventHooks 
{

	public int blockDamage;
	int count = 0;
	double playerPos1 = 0;
	double playerPos2 = 0;
	


/*@SubscribeEvent
public void onEntity(EntityEvent event) 
{
	if (event.getEntity() instanceof EntityItem) 
	{
		//System.out.println("Slime Not Found!");
		Block block = SlimeBreederBlocks.ARTIFICALSLIMEBLOCK;
		EntityItem entityitem = (EntityItem)event.getEntity();
		
		if(entityitem.getItem().getItem() == Item.getItemFromBlock(block)) {
			World world = event.getEntity().getEntityWorld();
			
			EntityPig pig = new EntityPig(world);
			
			pig.setPosition(entityitem.posX, entityitem.posY, entityitem.posZ);
			
			world.removeEntity(entityitem);
			
			
			
			world.spawnEntity(pig);
			
		}
		
	}	
}*/
	
	@SubscribeEvent
	public void onEntityDropped(ItemExpireEvent event) 
	{
		if (event.getEntity() instanceof EntityItem) 
		{
			
			
			EntityItem entityitem = (EntityItem) event.getEntity();
			
			if(entityitem.getItem().getItem() == SlimeBreederItems.SLIMESEED) {
				
				BlockPos pos0 = new BlockPos(entityitem.getPosition().getX(), (entityitem.getPosition().getY()) , entityitem.getPosition().getZ());
				IBlockState iblockstate = entityitem.getEntityWorld().getBlockState(pos0);
				Block block = iblockstate.getBlock();
				
				if(block instanceof BlockCauldron && iblockstate.getProperties().containsValue(3)) {
					//System.out.println("Full cauldron");
					entityitem.setInfinitePickupDelay();
					

				     	WorldServer worldserver = (WorldServer) entityitem.getEntityWorld();
				     	//worldserver.spawnParticle(EnumParticleTypes.HEART, false, target.posX + 0.5D, target.posY+ 1.0D, target.posZ+ 0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 
				     	Random rand = new Random();
				     	for (int j = 0; j < 2 * 8; ++j)
				        {
				     		
				            float f = rand.nextFloat() * ((float)Math.PI * 2F);
				            float f1 = rand.nextFloat() * 0.5F + 0.5F;
				            float f2 = MathHelper.sin(f) * (float)2 * 0.5F * f1;
				            float f3 = MathHelper.cos(f) * (float)2 * 0.5F * f1;
				            EnumParticleTypes enumparticletypes = EnumParticleTypes.NOTE;
				            double d0 = entityitem.posX + (double)f2;
				            double d1 = entityitem.posZ + (double)f3;
				           // world.spawnParticle(enumparticletypes, d0, this.getEntityBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D, new int[0]);
				            
				            
				            //worldserver.spawnParticle(EnumParticleTypes.HEART, false, target.posX + target.getEntityBoundingBox().minY, d1, ((double)1F), ((double)1F), ((double)0.89F), new int[0]);
				         	//worldserver.spawnParticle(EnumParticleTypes.HEART, false, target.posX + 0.5D, target.posY+ 1.0D, target.posZ+ 0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 
				            worldserver.spawnParticle(EnumParticleTypes.SLIME, false, d0, entityitem.posY+ 1.0D, d1, 1, 0.0D, 0.5D, 0.0D, 2.0D, new int[0]); 
				            //worldserver.spawnParticle(EnumParticleTypes.NOTE, false, d0, this.posY+ 1.0D, d1, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 
				            //entityItem.playSound(SoundEvents.ENTITY_SLIME_SQUISH, 0.1F, 0.5F);
				           }
				     	
				     	
						
						
						
					
				
					
					
					entityitem.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 0.5F);
					
					int prevcharge;
					NBTTagCompound nbtitem = entityitem.getEntityData();
					
					
					if(!nbtitem.hasKey("charge")) {
						nbtitem.setInteger("charge", 0);
					}
					
					if(!nbtitem.hasKey("absorbed")) {
						nbtitem.setBoolean("absorbed", false);
					}
					
					
					if(nbtitem.hasKey("absorbed") && nbtitem.getBoolean("absorbed")) {
						nbtitem.setBoolean("absorbed", false);
					}
					
					if(!nbtitem.hasKey("ItemHistory")) {
						NBTTagList itemhistory = new NBTTagList(); 
						nbtitem.setTag("ItemHistory", itemhistory);
					}
					
					/*NBTTagCompound ItemSlotOne = new ItemStack(Item.getItemFromBlock(Blocks.GRASS), 1, 0).serializeNBT();
					NBTTagCompound ItemSlotTwo = new ItemStack(Item.getItemFromBlock(Blocks.GRASS), 1, 0).serializeNBT();
					NBTTagCompound ItemSlotThree = new ItemStack(Item.getItemFromBlock(Blocks.GRASS), 1, 0).serializeNBT();
					NBTTagCompound ItemSlotFour = new ItemStack(Item.getItemFromBlock(Blocks.GRASS), 1, 0).serializeNBT();
					
					if(!nbtitem.hasKey("itemslotone")) {
						nbtitem.setTag("itemslotone", ItemSlotOne);
					}
					if(!nbtitem.hasKey("itemslottwo")) {
						nbtitem.setTag("itemslotone", ItemSlotTwo);
					}
					if(!nbtitem.hasKey("itemslotthree")) {
						nbtitem.setTag("itemslotone", ItemSlotThree);
					}
					if(!nbtitem.hasKey("itemslotfour")) {
						nbtitem.setTag("itemslotone", ItemSlotFour);
					}*/
					
					
					
					AbsorbItems(entityitem);
					

					
					
					if(nbtitem.hasKey("charge") && nbtitem.getInteger("charge") < 100) {
						nbtitem.setInteger("charge", nbtitem.getInteger("charge")+10);
						
						double speed =  ((double)(nbtitem.getInteger("charge"))/((double)(100)));
						
						//System.out.println(speed);
						
						event.setExtraLife((int) ((60)*(1-speed)));
						event.setCanceled(true);
						
					}else if(nbtitem.hasKey("charge") && nbtitem.getInteger("charge") >= 100) {
						
						World worldIn = entityitem.getEntityWorld();
						
						EntityColorSlimeBase entitySpawn = new EntityColorSlimeBase(worldIn);
						
						setColorfromItems(entitySpawn, entityitem);
						
						entitySpawn.initWildSlime(1, entitySpawn.getWave(), entitySpawn.getSat(), entitySpawn.getBright(), 22928, 5, 24, 6, 3, false);
						
						entitySpawn.setLocationAndAngles(entityitem.getPosition().getX(), (entityitem.getPosition().getY()+1.00D), entityitem.getPosition().getZ(), 0.0F, 0.0F);
		     			
						if(!worldIn.isRemote) {
							
							entityitem.playSound(SoundEvents.ENTITY_SLIME_SQUISH, 3.0F, 0.1F);
							//System.out.println(entitySpawn.getHunger());
			     			worldIn.spawnEntity(entitySpawn);
			     			
			     			
			    			if(!entityitem.getEntityWorld().isRemote) {


			    			     	//worldserver.spawnParticle(EnumParticleTypes.HEART, false, target.posX + 0.5D, target.posY+ 1.0D, target.posZ+ 0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 

			    			     	for (int j = 0; j < 2 * 8; ++j)
			    			        {
			    			     		
			    			            float f = rand.nextFloat() * ((float)Math.PI * 2F);
			    			            float f1 = rand.nextFloat() * 0.5F + 0.5F;
			    			            float f2 = MathHelper.sin(f) * (float)2 * 0.5F * f1;
			    			            float f3 = MathHelper.cos(f) * (float)2 * 0.5F * f1;
			    			            EnumParticleTypes enumparticletypes = EnumParticleTypes.NOTE;
			    			            double d0 = entityitem.posX + (double)f2;
			    			            double d1 = entityitem.posZ + (double)f3;
			    			           // world.spawnParticle(enumparticletypes, d0, this.getEntityBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D, new int[0]);
			    			            
			    			            
			    			            //worldserver.spawnParticle(EnumParticleTypes.HEART, false, target.posX + target.getEntityBoundingBox().minY, d1, ((double)1F), ((double)1F), ((double)0.89F), new int[0]);
			    			         	//worldserver.spawnParticle(EnumParticleTypes.HEART, false, target.posX + 0.5D, target.posY+ 1.0D, target.posZ+ 0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 
			    			            worldserver.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, false, d0, entityitem.posY+ 1.0D, d1, 1, 0.0D, 0.5D, 0.0D, 2.0D, new int[0]); 
			    			            //worldserver.spawnParticle(EnumParticleTypes.NOTE, false, d0, this.posY+ 1.0D, d1, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 

			    			           }
			    			     	
			    			     	
			    			}
			     			
			     			
			     			
			     			
			     			
			     			}
						
						event.setCanceled(false);
						
					}
					
					
				}
				
				//System.out.println(iblockstate.getProperties().containsValue(3));
				
				
				
				
				
				
			}
			
		}	
		
	}
	
	
	
	
	public void AbsorbItems(EntityItem entityitem) {
		
		List<EntityItem> list = entityitem.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, entityitem.getEntityBoundingBox().grow(1.0D));
    	EnumItemColor.listOfObjects.clear();
    	EnumItemColor.initColorList();
		NBTTagCompound level; //tag in list on "i" index
		level = new NBTTagCompound(); // each "i" index has one NBT
		
		NBTTagList itemhistory = (NBTTagList) entityitem.getEntityData().getTag("ItemHistory"); 
    	
		for (EntityItem entityitems : list)
        {
			
		int length = EnumItemColor.listOfObjects.size();
   		 
	   		 for (int i = 0; i < length; i++) 
	   	    	{
	   			 
	   			 ////System.out.println("adding");
	   			 Item listItem = EnumItemColor.listOfObjects.get(i).getItemStack().getItem();
	
	   		 if (listItem == entityitems.getItem().getItem()  && !entityitem.world.isRemote && entityitems.getItem().getItem() != SlimeBreederItems.FILLEDSLIMECAPSULE)
	   		 {
				
	   			//NBTTagCompound mainNBT = new NBTTagCompound(); //pareent nbt
	   			 
		   			World entityworld = entityitem.world;
		   			
		   			
		   			ItemStack dropitemstack = ((EntityItem) entityitems).getItem();
		   	    	ItemStack dropitemstackcopy = entityitems.getItem().copy();
		   			 
		   			 NBTTagCompound SerialDrop =  dropitemstack.serializeNBT();
		   			 NBTTagCompound SerialCopy =  dropitemstackcopy.serializeNBT();
		   			 
		   			 
		   			 
		   			 SerialCopy.setByte("Count", (byte)1);
		   			 ItemStack newitemstack = new ItemStack(SerialCopy);
		   			
		   			byte Count = SerialDrop.getByte("Count");
					 SerialDrop.setByte("Count", (byte) ((byte)Count-1));
					 entityitems.setItem((new ItemStack(SerialDrop)));
		   			
	   			
	   			if(listItem instanceof ItemBlock) {
	   			level.setTag(listItem.getUnlocalizedName(), new ItemStack(Item.getItemFromBlock(((ItemBlock) listItem).getBlock()), 1, 0).serializeNBT());
	   			}else {
	   			level.setTag(listItem.getUnlocalizedName(), new ItemStack(listItem).serializeNBT());
	   			}
	   			
	   			itemhistory.appendTag(level); // apend index to list
	   			

	   			
	   			//entityworld.removeEntity(entityitems);
	   			
	   			}
	   			
	   			//System.out.println(itemhistory.toString());
	   			entityitem.getEntityData().setTag("ItemHistory", itemhistory); // write list to main NBT
				
	   		 }
				
				//this.startRiding(entityliving, true);
    	
		}

        
}
		
		
		
	
	
	public void setColorfromItems(EntityColorSlimeBase entitySpawn, EntityItem entityitem){
		
		NBTTagCompound entityitemNBT = entityitem.getEntityData();
		NBTTagList itemhistory = entityitem.getEntityData().getTagList("ItemHistory", 10);
		


    	EnumItemColor.listOfObjects.clear();
    	EnumItemColor.initColorList();

		int length = itemhistory.tagCount();
		
		
		
		int listlength = EnumItemColor.listOfObjects.size();
		

		
		int defaultcolor = 580;
		float defaultsat = 1.0F;
		float defaultbright = 1.0F;
		
		int color1 = defaultcolor;
		float sat1 = defaultsat;
		float bright1 = defaultbright;
		int color1count = 0;
		
		int color2 = defaultcolor;
		float sat2 = defaultsat;
		float bright2 = defaultbright;
		int color2count = 0;
		
		int color3 = defaultcolor;
		float sat3 = defaultsat;
		float bright3 = defaultbright;
		int color3count = 0;
		
		int color4 = defaultcolor;
		float sat4 = defaultsat;
		float bright4 = defaultbright;
		int color4count = 0;
		
		int color5 = defaultcolor;
		float sat5 = defaultsat;
		float bright5 = defaultbright;
		int color5count = 0;
		
		int color6 = defaultcolor;
		float sat6 = defaultsat;
		float bright6 = defaultbright;
		int color6count = 0;
		
		int color7 = defaultcolor;
		float sat7 = defaultsat;
		float bright7 = defaultbright;
		int color7count = 0;
		
		int color8 = defaultcolor;
		float sat8 = defaultsat;
		float bright8 = defaultbright;
		int color8count = 0;
		
		int color9 = defaultcolor;
		float sat9 = defaultsat;
		float bright9 = defaultbright;
		int color9count = 0;
		
		//System.out.println("Tag List Lenght = " + length);
		
		
		for(int i = 0; i < listlength; i++) 
	    	{
			
			ItemStack listItem = EnumItemColor.listOfObjects.get(i).getItemStack();
			
			
			for (int j = 0; j < itemhistory.tagCount(); j++) {
			
				
				NBTTagCompound absItemNBT;
				absItemNBT = itemhistory.getCompoundTagAt(j);
				
				if(absItemNBT.toString().contains(listItem.getItem().getUnlocalizedName())) {
					
					ItemStack absStackItem = new ItemStack(absItemNBT.getCompoundTag((listItem.getItem().getUnlocalizedName())));
					
					if(!absStackItem.isEmpty()) {
						
						//System.out.println("item = " + absItemNBT.getCompoundTag((listItem.getItem().getUnlocalizedName())));
						
							if(absStackItem.isItemEqual(listItem)) {
								
								
								int Iwave = EnumItemColor.listOfObjects.get(i).getWave();
					    		float Isat = EnumItemColor.listOfObjects.get(i).getSat();
					    		float Ibrightness = EnumItemColor.listOfObjects.get(i).getBright();
					    		
					    		
					    	
					    		
					    		//System.out.println("Average Wave = " + Iwave);
					    		count++;

					    		if(color1 == defaultcolor) {
					    			color1 = Iwave;
					    			sat1 = Isat;
					    			bright1 = Ibrightness;
					    			color1count++;
					    		}
					    		
					    		if(color2 == defaultcolor) {
					    			color2 = Iwave;
					    			sat2 = Isat;
					    			bright2 = Ibrightness;

					    		}
					    		
					    		if(color3 == defaultcolor) {
					    			color3 = Iwave;
					    			sat3 = Isat;
					    			bright3 = Ibrightness;

					    		}
					    		
					    		if(color4 == defaultcolor) {
					    			color4 = Iwave;
					    			sat4 = Isat;
					    			bright4 = Ibrightness;

					    		}
					    		
					    		if(color5 == defaultcolor) {
					    			color5 = Iwave;
					    			sat5 = Isat;
					    			bright5 = Ibrightness;

					    		}
					    		
					    		if(color6 == defaultcolor) {
					    			color6 = Iwave;
					    			sat6 = Isat;
					    			bright6 = Ibrightness;

					    		}
					    		
					    		if(color7 == defaultcolor) {
					    			color7 = Iwave;
					    			sat7 = Isat;
					    			bright7 = Ibrightness;

					    		}
					    		
					    		if(color8 == defaultcolor) {
					    			color8 = Iwave;
					    			sat8 = Isat;
					    			bright8 = Ibrightness;

					    		}
					    		
					    		if(color9 == defaultcolor) {
					    			color9 = Iwave;
					    			sat9 = Isat;
					    			bright9 = Ibrightness;

					    		}		
					    		
					    		
					    		if(color2 != defaultcolor && color2count < 1 && Iwave != color1  && Iwave != color3 && Iwave != color4 && Iwave != color5 && Iwave != color6 && Iwave != color7 && Iwave != color8 && Iwave != color9) {
					    			color2 = Iwave;
					    			sat2 = Isat;
					    			bright2 = Ibrightness;
					    			color2count++;
					    		}
					    		
					    		if(color3 != defaultcolor && color3count < 1  && Iwave != color1  && Iwave != color2 && Iwave != color4 && Iwave != color5 && Iwave != color6 && Iwave != color7 && Iwave != color8 && Iwave != color9) {
					    			color3 = Iwave;
					    			sat3 = Isat;
					    			bright3 = Ibrightness;
					    			color3count++;
					    		}
					    		
					    		if(color4 != defaultcolor && color4count < 1 && Iwave != color1  && Iwave != color3 && Iwave != color2 && Iwave != color5 && Iwave != color6 && Iwave != color7 && Iwave != color8 && Iwave != color9) {
					    			color4 = Iwave;
					    			sat4 = Isat;
					    			bright4 = Ibrightness;
					    			color4count++;
					    		}
					    		
					    		if(color5 != defaultcolor && color5count < 1 && Iwave != color1  && Iwave != color3 && Iwave != color4 && Iwave != color2 && Iwave != color6 && Iwave != color7 && Iwave != color8 && Iwave != color9) {
					    			color5 = Iwave;
					    			sat5 = Isat;
					    			bright5 = Ibrightness;
					    			color5count++;
					    		}
					    		
					    		if(color6 != defaultcolor && color6count < 1 && Iwave != color1  && Iwave != color3 && Iwave != color4 && Iwave != color5 && Iwave != color2 && Iwave != color7 && Iwave != color8 && Iwave != color9) {
					    			color6 = Iwave;
					    			sat6 = Isat;
					    			bright6 = Ibrightness;
					    			color6count++;
					    		}
					    		
					    		if(color7 != defaultcolor && color7count < 1 && Iwave != color1  && Iwave != color3 && Iwave != color4 && Iwave != color5 && Iwave != color6 && Iwave != color2 && Iwave != color8 && Iwave != color9) {
					    			color7 = Iwave;
					    			sat7 = Isat;
					    			bright7 = Ibrightness;
					    			color7count++;
					    		}
					    		
					    		if(color8 != defaultcolor && color8count < 1  && Iwave != color1  && Iwave != color3 && Iwave != color4 && Iwave != color5 && Iwave != color6 && Iwave != color7 && Iwave != color2 && Iwave != color9) {
					    			color8 = Iwave;
					    			sat8 = Isat;
					    			bright8 = Ibrightness;
					    			color8count++;
					    		}
					    		
					    		if(color9 != defaultcolor && color9count < 1 && Iwave != color1  && Iwave != color3 && Iwave != color4 && Iwave != color5 && Iwave != color6 && Iwave != color7 && Iwave != color8 && Iwave != color2) {
					    			color9 = Iwave;
					    			sat9 = Isat;
					    			bright9 = Ibrightness;
					    			color9count++;
					    		}
					    			    		
					    		if(color1 == Iwave) {
					    			color1count++;
					    		}
					    		
					    		if(color2 == Iwave && Iwave != color1  && Iwave != color3 && Iwave != color4 && Iwave != color5 && Iwave != color6 && Iwave != color7 && Iwave != color8 && Iwave != color9) {
					    			color2count++;
					    		}
					    		
					    		if(color3 == Iwave && Iwave != color1  && Iwave != color2 && Iwave != color4 && Iwave != color5 && Iwave != color6 && Iwave != color7 && Iwave != color8 && Iwave != color9) {
					    			color3count++;
					    		}
					    		
					    		if(color4 == Iwave && Iwave != color1  && Iwave != color3 && Iwave != color2 && Iwave != color5 && Iwave != color6 && Iwave != color7 && Iwave != color8 && Iwave != color9) {
					    			color4count++;
					    		}
					    		
					    		if(color5 == Iwave && Iwave != color1  && Iwave != color3 && Iwave != color4 && Iwave != color2 && Iwave != color6 && Iwave != color7 && Iwave != color8 && Iwave != color9) {
					    			color5count++;
					    		}
					    		
					    		if(color6 == Iwave && Iwave != color1  && Iwave != color3 && Iwave != color4 && Iwave != color5 && Iwave != color2 && Iwave != color7 && Iwave != color8 && Iwave != color9) {
					    			color6count++;
					    		}
					    		
					    		if(color7 == Iwave && Iwave != color1  && Iwave != color3 && Iwave != color4 && Iwave != color5 && Iwave != color6 && Iwave != color2 && Iwave != color8 && Iwave != color9) {
					    			color7count++;
					    		}
					    		
					    		if(color8 == Iwave && Iwave != color1  && Iwave != color3 && Iwave != color4 && Iwave != color5 && Iwave != color6 && Iwave != color7 && Iwave != color2 && Iwave != color9) {
					    			color8count++;
					    		}
					    		
					    		if(color9 == Iwave && Iwave != color1  && Iwave != color3 && Iwave != color4 && Iwave != color5 && Iwave != color6 && Iwave != color7 && Iwave != color8 && Iwave != color2) {
					    			color9count++;
					    		}
					    		
					    		

					    		System.out.println("List = " + color1 + " : " + color1count + " , " + color2 + " : " + color2count + " , " + color3 + " : " + color3count + " , "  + color4 + " : " + color4count);

								
							}
						
						
					}

					

		    		
		    	
		    		
		    		//System.out.println(absItemNBT.toString() + " =  " + listItem + " ? " + absItemNBT.toString().contains(listItem.getItem().getUnlocalizedName()));
		    		
		    		
		    			
		    			
		    		}

				
			}
			

	    	
		
	    	}
		
		/*if(color1count != 0) {
			
			color1count--;
			
		}*/
		

		
		int totalcount = color1count + color2count + color3count + color4count + color5count + color6count + color7count + color8count + color9count;

		
		//System.out.println(color1count + " / " + totalcount);
		
		//System.out.println("List = " + color1 + " : " + color1count + " , "  + color1weight  + " , " + color2 + " : " + color2count + " , " + color3 + " : " + color3count + " , "  + color4 + " : " + color4count);
		//System.out.println("Count = " + count);
		
		int finalWave = defaultcolor;
		float finalSat = defaultsat;
		float finalBright = defaultbright;
		
		if(color1 !=defaultcolor && color1count != 0) {
		finalWave = (((color1*color1count)+(color2*color2count)+(color3*color3count)+(color4*color4count)+(color5*color5count)+(color6*color6count)+(color7*color7count)+(color8*color8count)+(color9*color9count))/totalcount);
		finalSat = (((sat1*color1count)+(sat2*color2count)+(sat3*color3count)+(sat4*color4count)+(sat5*color5count)+(sat6*color6count)+(sat7*color7count)+(sat8*color8count)+(sat9*color9count))/totalcount);
		finalBright = (((bright1*color1count)+(bright2*color2count)+(bright3*color3count)+(bright4*color4count)+(bright5*color5count)+(bright6*color6count)+(bright7*color7count)+(bright8*color8count)+(bright9*color9count))/totalcount);
		}
		
		int waveVarience = 80;
		int satVarience = 20;
		int brightVarience = 20;
		
		int calcwaveVarience = waveVarience-(totalcount);
		int calcsatVarience = satVarience - (totalcount);
		int calcbrightVarience = brightVarience - (totalcount);
		
		if(calcwaveVarience < 20) {
			calcwaveVarience = 20;
		}
		
		if(calcsatVarience < 5) {
			calcsatVarience = 5;
		}
		
		if(calcbrightVarience < 5) {
			calcbrightVarience = 5;
		}
		
		Random rand = new Random();
		
		int finalWaveCalc = finalWave - (rand.nextInt(calcwaveVarience));
		float finalSatCalc = finalSat - ((rand.nextInt(calcsatVarience))*0.01F);
		float finalBrightCalc = finalBright - ((rand.nextInt(calcbrightVarience))*0.01F);
		
		if(finalWaveCalc < 380) {
			finalWaveCalc = 380;
		}
		
		if(finalSatCalc < 0.0) {
			finalSatCalc = 0.0f;
		}
		
		if(finalBrightCalc < 0.0) {
			finalBrightCalc = 0.0F;
		}
		
		if(finalSatCalc > 1.0) {
			finalSatCalc = 1.0f;
		}
		
		if(finalBrightCalc > 1.0) {
			finalBrightCalc = 1.0F;
		}
		
		
		//System.out.println("Wave = " + finalWaveCalc);
		//System.out.println("Sat = " + finalSatCalc);
		//System.out.println("Bright = " + finalBrightCalc);
		
		//System.out.println("Varience Wave= " + calcwaveVarience);
		
		entitySpawn.setWave(finalWaveCalc);
		entitySpawn.setSat(finalSatCalc);
		entitySpawn.setBrightness(finalBrightCalc);
		
		
	}
	

	@SubscribeEvent
	public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) 
	{
		
		EntityPlayer player = event.player;
		IInventory recipeInventory = event.craftMatrix;
		ItemStack result = event.crafting;
		
		if(player.getHeldItemMainhand().getItem() == SlimeBreederItems.CRAFTINGNOTE){
			
			
			IRecipe recipe = CraftingManager.findMatchingRecipe((InventoryCrafting) recipeInventory, player.getEntityWorld());
			ResourceLocation recipeName = recipe.getRegistryName();
			
			if(player.getHeldItemMainhand().getTagCompound() != null){
				
				ItemStack heldItem = player.getHeldItemMainhand();
				NBTTagCompound nbt = heldItem.getTagCompound();
				
				nbt.setString("recipeName", recipeName.getResourcePath());
			}
			
			
		}
		
		
		
		
	}

@SubscribeEvent
public void onEntityUpdate(LivingUpdateEvent event) 
{
	
	
	
	if (event.getEntityLiving() instanceof EntityPlayer) 
	{
		EntityPlayer player = (EntityPlayer) event.getEntityLiving();
		Iterable<ItemStack> armour = event.getEntityLiving().getArmorInventoryList();		
		
		for(ItemStack stack : armour)
		{
			
			////System.out.println("Player FoundU" + " " + stack);
			adhesiveCheck(player, stack);
			//stickyCheck(player, stack);
			//slipperyCheck(player, stack);
			
			acidicCheck(player, stack);
			breakingCheck(player, stack);
			flammableCheck(player, stack);
		}
	}
	
	/*if (event.getEntityLiving() instanceof EntitySlime2) 
		{
		
		EntitySlime2 slime = (EntitySlime2) event.getEntityLiving();
		
		List<EntityItem> list = slime.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, slime.getEntityBoundingBox().expandXyz(1.0D));
		for (EntityItem entityitem : list)
        {
			
			if (entityitem instanceof EntityItem)
		    {
		    	//System.out.println("Item found");
		    	EntityItem item = (EntityItem) entityitem;
		    	
		    	
		    	////System.out.println(itemstack);
		    	
		    	if(((EntityItem) entityitem).getEntityItem().getItem() == Items.ARROW){
		    	{
		    		
		    	ItemStack itemDrop = ((EntityItem) entityitem).getEntityItem();
		    		
		    		//System.out.println("Item arrow");
		    		slime.addItemSlime(itemDrop);
		    		entityitem.setDead();
		    	}
    	
		    }
		  }
		 }
		}*/
	
	/*if (event.getEntityLiving() instanceof EntityPlayer) 
	{
		EntityPlayer player = (EntityPlayer) event.getEntityLiving();
		Iterable<ItemStack> armour = event.getEntityLiving().getArmorInventoryList();		
		
		for(ItemStack stack : armour)
		{
			
			
			
			int stickyLevel = EnchantmentHelper.getEnchantmentLevel(SlimeBreederEnchantments.STICKY, stack);
			
			if(stickyLevel>0)
			{
				EntityPlayer player = (EntityPlayer) event.getEntityLiving();
				
				if(player.isCollidedHorizontally)
				{
					player.addVelocity(0.0, 0.1, 0.0);
					//player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 1, 4));
				}
				
				if(player.isCollidedVertically)
				{
					player.fallDistance = 0.0F;

				}
				
				/*if(player.motionY < -0.3 )
				{
					//System.out.println("Activate");
					//player.addVelocity(0.0, 0.5, 0.0);
				}*/
				
				////System.out.println("Enchantment Found, Level = " + stickyLevel);
				
				/*double x = player.posX;
				double y = player.posY;
				double z = player.posZ;

				BlockPos pos0 = new BlockPos(x, (y-1) , z);
				IBlockState iblockstate = player.getEntityWorld().getBlockState(pos0);
				Block block = iblockstate.getBlock();
				
				
				float curBlockDamageMP = iblockstate.getPlayerRelativeBlockHardness(player, player.getEntityWorld(), pos0);
				int BlockDamage = (int)(curBlockDamageMP*10.0F)+1;
				
				int timer = player.getActivePotionEffect(Potion.getPotionFromResourceLocation("night_vision")).getDuration();
				//System.out.println("Timer = " + timer/100);

				player.getEntityWorld().sendBlockBreakProgress(player.getEntityId(), pos0, timer/100);*/
				


				
				/*if(curBlockDamageMP < 1.0F)
				{
					this.blockDamage = ((int)(curBlockDamageMP*10.0F)+1);
					blockDamage++;
					////System.out.println("Unbroken");
					player.getEntityWorld().sendBlockBreakProgress(player.getEntityId(), pos0, blockDamage);
					
				}*/
				
				
				/*for (int i = 0; i < 100; ++i)
				{
					player.getEntityWorld().sendBlockBreakProgress(player.getEntityId(), pos0, i);
					//System.out.println("Block = " + block.toString() + " Durability = " + i);
				}*/
				
				////System.out.println("Block = " + block.toString() + " Durability = " + curBlockDamageMP);

					//player.getEntityWorld().sendBlockBreakProgress(player.getEntityId(), pos0, (int)(curBlockDamageMP * 10.0F) - 1);

				////System.out.println("BlockPos pos0="+pos0.toString());
				
				
				//player.getEntityWorld().destroyBlock(pos0, true);
				
				//player.getEntityWorld().sendBlockBreakProgress(player.getEntityId(), pos0, PlayerInteractionManager.durabilityRemainingOnBlock);
				
			//}
		//}
	
	//}
	
	if (event.getEntityLiving().isPotionActive(SlimeBreeder.proxy.slimeBreedable)) 
	{
			if (event.getEntityLiving().world.rand.nextInt(20) == 0) 
			{
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 5, 5));
			////System.out.println("Hooked");
			}
			
			if (event.getEntityLiving().getActivePotionEffect(SlimeBreeder.proxy.slimeBreedable).getDuration()==0) 
			{
			event.getEntityLiving().removePotionEffect(SlimeBreeder.proxy.slimeBreedable);
			return;
			}
			
	}
		
		
	if (event.getEntityLiving().isPotionActive(SlimeBreeder.proxy.slimeDomesticated)) 
	{
		////System.out.println("Domesticated");
		if (event.getEntityLiving().getActivePotionEffect(SlimeBreeder.proxy.slimeDomesticated).getDuration()==0) 
		{
		event.getEntityLiving().removePotionEffect(SlimeBreeder.proxy.slimeDomesticated);
		return;
		}
		
		

	}
	
	if (event.getEntityLiving().isPotionActive(SlimeBreeder.proxy.slimeDomesticatedFollow)) 
	{
		////System.out.println("Domesticated Follow");
		if (event.getEntityLiving().getActivePotionEffect(SlimeBreeder.proxy.slimeDomesticatedFollow).getDuration()==0) 
		{
		event.getEntityLiving().removePotionEffect(SlimeBreeder.proxy.slimeDomesticatedFollow);
		return;
		}
		
		

	}
	
	if (event.getEntityLiving().isPotionActive(SlimeBreeder.proxy.slimeDomesticatedWait)) 
	{
		////System.out.println("Domesticated Wait");
		
		event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30, 30));
		
		if (event.getEntityLiving().getActivePotionEffect(SlimeBreeder.proxy.slimeDomesticatedWait).getDuration()==0) 
		{
		event.getEntityLiving().removePotionEffect(SlimeBreeder.proxy.slimeDomesticatedWait);
		return;
		}
		
		

	}
		

	
	if (event.getEntityLiving().isPotionActive(SlimeBreeder.proxy.slimeCrafting)) 
	{
		
		if (event.getEntityLiving().getActivePotionEffect(SlimeBreeder.proxy.slimeCrafting).getDuration()==0) 
		{
		event.getEntityLiving().removePotionEffect(SlimeBreeder.proxy.slimeCrafting);
		return;
		}

	}
	
	if (event.getEntityLiving().isPotionActive(SlimeBreeder.proxy.slimeResult)) 
	{
		
		if (event.getEntityLiving().getActivePotionEffect(SlimeBreeder.proxy.slimeResult).getDuration()==0) 
		{
		event.getEntityLiving().removePotionEffect(SlimeBreeder.proxy.slimeResult);
		return;
		}

	}
	
	if (event.getEntityLiving().isPotionActive(SlimeBreeder.proxy.slimeTransform)) 
	{

		if (event.getEntityLiving().getActivePotionEffect(SlimeBreeder.proxy.slimeTransform).getDuration()<=10) 
		{
			event.getEntityLiving().addPotionEffect(new PotionEffect(SlimeBreeder.proxy.slimeTransformComplete, 10, 1));

		}
		
		if (event.getEntityLiving().getActivePotionEffect(SlimeBreeder.proxy.slimeTransform).getDuration()==0) 
		{

		event.getEntityLiving().removePotionEffect(SlimeBreeder.proxy.slimeTransform);

		return;
		}

	}
	
	if (event.getEntityLiving().isPotionActive(SlimeBreeder.proxy.slimeTransformComplete)) 
	{
		
		if (event.getEntityLiving().getActivePotionEffect(SlimeBreeder.proxy.slimeTransformComplete).getDuration()==0) 
		{
		
		event.getEntityLiving().removePotionEffect(SlimeBreeder.proxy.slimeTransformComplete);
		
		return;
		}

	}
	
	if (event.getEntityLiving().isPotionActive(SlimeBreeder.proxy.slimeHeatToLiquid)) 
	{
		
		if (event.getEntityLiving().getActivePotionEffect(SlimeBreeder.proxy.slimeHeatToLiquid).getDuration()==0) 
		{
		
		event.getEntityLiving().removePotionEffect(SlimeBreeder.proxy.slimeHeatToLiquid);
		
		return;
		}

	}
	
	
	
	/*if (event.getEntityLiving().isPotionActive(SlimeBreeder.proxy.slimeAcid)) 
	{
		////System.out.println("Domesticated");
		
		double x = player.posX;
		double y = player.posY;
		double z = player.posZ;
	
		BlockPos pos0 = new BlockPos(x, (y-1) , z);
		IBlockState iblockstate = player.getEntityWorld().getBlockState(pos0);
		Block block = iblockstate.getBlock();
		float blockHardness = block.getBlockHardness(iblockstate, player.getEntityWorld(), pos0);
		
		

		
		

	}*/
	
	}


@SubscribeEvent
public void onLivingFallEvent(LivingFallEvent event) 
{
	////System.out.println("Player Found1");

		////System.out.println("Player Found2");
	
	
	
		if (event.getEntityLiving() instanceof EntityPlayer) 
		{
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			Iterable<ItemStack> armour = event.getEntityLiving().getArmorInventoryList();		
			
			for(ItemStack stack : armour)
			{
			int bouncyLevel = EnchantmentHelper.getEnchantmentLevel(SlimeBreederEnchantments.BOUNCY, stack);
			
			float modifierD = 0.6F - (float)(0.1f * bouncyLevel);
			double modifierF = 1.8D - 0.1D*(double)(bouncyLevel);
			
			////System.out.println("Player Found3" + " " + stack);
			if(bouncyLevel>0)
			{
				////System.out.println("Player Found4");
				//EntityPlayer player = (EntityPlayer) event.getEntityLiving();
				
				/*float fall = player.fallDistance;
				player.addVelocity(0.0, 0.7, 0.0);
				//event.setCanceled(true);*/
				
				
				
				//event.setDistance(event.getDistance()/8.0f);
				
				double acceleration = 0.094;
                double drag = 0.0028;
                double velocity = Math.sqrt(2 * acceleration * event.getDistance());
                velocity = velocity * (1 + drag * event.getDistance());
                if(event.getDistance() > 0.5)
                {
                //Propel player upward with that velocity
                player.motionY = velocity/modifierF;
                player.velocityChanged = true;
                }
                event.setDamageMultiplier(modifierD);
				
			}
			
		}
	}
}

public void adhesiveCheck(EntityPlayer player, ItemStack stack)
{
	
	int adhesiveLevel = EnchantmentHelper.getEnchantmentLevel(SlimeBreederEnchantments.ADHESIVE, stack);
	double modifier = 0.005*adhesiveLevel;
	
	
	if(!(stack.isEmpty()))
	{
		////System.out.println("Stack Found");
		if(stack.getItem() instanceof ItemArmor)
		{
			switch (((ItemArmor) stack.getItem()).armorType) 
			{
			case HEAD:
				if(adhesiveLevel>0)
				{
					if(player.collidedHorizontally)
					{
						player.addVelocity(0.0, 0.02+modifier, 0.0);
						
					}
				}
	        	break;
			case CHEST:
				if(adhesiveLevel>0)
				{
					if(player.collidedHorizontally)
					{
						player.addVelocity(0.0, 0.02+modifier, 0.0);
						
					}
				}
				break;
			case LEGS:
				if(adhesiveLevel>0)
				{
					if(player.collidedHorizontally)
					{
						player.addVelocity(0.0, 0.02+modifier, 0.0);
						
					}
				}
				break;
			case FEET:
				if(adhesiveLevel>0)
				{
					if(player.collidedHorizontally)
					{
						player.addVelocity(0.0, 0.02+modifier, 0.0);
						
					}
				}
				break;
			case MAINHAND:
				//System.out.println("Main hand");			
				break;
			case OFFHAND:
				//System.out.println("Sword");			
				break;
			default:
				//System.out.println("Not Found");		
				break;
			}
		}
	}
}

public void acidicCheck(EntityPlayer player, ItemStack stack)
{
	
		int acidicLevel = EnchantmentHelper.getEnchantmentLevel(SlimeBreederEnchantments.ACIDIC, stack);
		
		////System.out.println(acidicLevel);
		
		////System.out.println("move forward = " + player.);
		
		
		if(!(stack.isEmpty()))
		{
			if(acidicLevel>0)
			{
			
				if(stack.getItem() instanceof ItemArmor)
				{
					List<EntityLiving> list = player.getEntityWorld().getEntitiesWithinAABB(EntityLiving.class, player.getEntityBoundingBox().grow(8.0D));
					double d0 = 1.0D;
					
					//switch (((ItemArmor) stack.getItem()).getEquipmentSlot())
					switch (((ItemArmor) stack.getItem()).armorType) 
					{
			        case HEAD:
			        	for (EntityLiving entityliving : list)
			            {
			                if (!(entityliving instanceof EntityColorSlimeBase)  && player.getDistanceSq(entityliving) < d0)
			                {
			                	//entityliving.applyEntityCollision(player);
			                	entityliving.attackEntityFrom(DamageSource.GENERIC, 1.0F*acidicLevel);
			                	
			                }
			            }
			        	////System.out.println("Head");
			        	break;
					case CHEST:
						
						for (EntityLiving entityliving : list)
			            {
			                if (!(entityliving instanceof EntityColorSlimeBase)  && player.getDistanceSq(entityliving) < d0)
			                {
			                	//entityliving.applyEntityCollision(player);
			                	entityliving.attackEntityFrom(DamageSource.GENERIC, 1.0F*acidicLevel);
			                	
			                }
			            }
								
						////System.out.println("Chest");
						break;
					case FEET:
						for (EntityLiving entityliving : list)
			            {
			                if (!(entityliving instanceof EntityColorSlimeBase)  && player.getDistanceSq(entityliving) < d0)
			                {
			                	//entityliving.applyEntityCollision(player);
			                	entityliving.attackEntityFrom(DamageSource.GENERIC, 1.0F*acidicLevel);
			                	
			                }
			            }
						////System.out.println("Feet");
						break;
					case LEGS:
						for (EntityLiving entityliving : list)
			            {
			                if (!(entityliving instanceof EntityColorSlimeBase)  && player.getDistanceSq(entityliving) < d0)
			                {
			                	//entityliving.applyEntityCollision(player);
			                	entityliving.attackEntityFrom(DamageSource.GENERIC, 1.0F*acidicLevel);
			                	
			                }
			            }
						////System.out.println("Legs");
						break;
					case MAINHAND:
						//System.out.println("Main hand");			
						break;
					case OFFHAND:
						//System.out.println("Sword");			
						break;
					default:
						//System.out.println("Not Found");		
						break;
		    		}	
				}
			}
		}
}

public void degrade(int BposX, int BposY, int BposZ, int stickyLevel, EntityPlayer player, EntityEquipmentSlot slot)
{
	int potionDurationMax = 900;
	
		if(stickyLevel>0)
		{
			count = count + 1;
			
			double x = player.posX;
			double y = player.posY;
			double z = player.posZ;
			
			//System.out.println(player.getHorizontalFacing());
	
			BlockPos pos0 = new BlockPos(x+BposX, (y+BposY) , z+BposZ);
			IBlockState iblockstate = player.getEntityWorld().getBlockState(pos0);
			Block block = iblockstate.getBlock();
			float blockHardness = block.getBlockHardness(iblockstate, player.getEntityWorld(), pos0);
			
			if(blockHardness == 0.0)
			{
				blockHardness = 1.0F;
			}
			
			if(!(blockHardness < 0.0F))
			{
			
			potionDurationMax = (int) ((potionDurationMax*blockHardness)/stickyLevel);
			
			if(player.getActivePotionEffect(SlimeBreeder.proxy.slimeAcid) == null)
			{
				player.addPotionEffect(new PotionEffect(SlimeBreeder.proxy.slimeAcid, potionDurationMax, 1));
			}
			
			if(player.getActivePotionEffect(SlimeBreeder.proxy.slimeAcid).getDuration() > potionDurationMax)
			{
				player.removePotionEffect(SlimeBreeder.proxy.slimeAcid);
				player.removeActivePotionEffect(SlimeBreeder.proxy.slimeAcid);
				player.addPotionEffect(new PotionEffect(SlimeBreeder.proxy.slimeAcid, potionDurationMax, 1));
			}
	
				double timer = ((double)(player.getActivePotionEffect(SlimeBreeder.proxy.slimeAcid).getDuration()) / (double)(potionDurationMax))*10.0D;
				player.getEntityWorld().sendBlockBreakProgress(player.getEntityId(), pos0, (int) (9-timer));
				////System.out.println("Timer = " + timer + " " + "Duration =" + (player.getActivePotionEffect(SlimeBreeder.proxy.slimeAcid).getDuration()) + "/" + potionDurationMax );
			
			
				if (player.getActivePotionEffect(SlimeBreeder.proxy.slimeAcid).getDuration()<=10) 
				{
					player.getEntityWorld().destroyBlock(pos0, true);
					player.removePotionEffect(SlimeBreeder.proxy.slimeAcid);
					player.removeActivePotionEffect(SlimeBreeder.proxy.slimeAcid);
					return;
				}
		
					if(count <= 10)
					{
						playerPos1 = (player.prevChasingPosX);
						////System.out.println(count + " " + playerPos1);
					}
					
					if(count >= 11)
					{
						playerPos2 = (player.prevChasingPosX);
						////System.out.println(count + " " + playerPos2);
					}
					
					if(count >= 21)
					{
						////System.out.println((double)Math.round((playerPos1*100.0D)) + " " + (double)Math.round((playerPos2*100.0D)));
						if((double)Math.round((playerPos1*100.0D)) != (double)Math.round((playerPos2*100.0D)))
						{
							player.removePotionEffect(SlimeBreeder.proxy.slimeAcid);
							player.removeActivePotionEffect(SlimeBreeder.proxy.slimeAcid);
							player.addPotionEffect(new PotionEffect(SlimeBreeder.proxy.slimeAcid, (int) potionDurationMax, 1));
							////System.out.println("removing effect");
						}else{
							
						}
						
						count = 0;
					}	
				}
			}
}






public void breakingCheck(EntityPlayer player, ItemStack stack)
{
	
int breakableLevel = EnchantmentHelper.getEnchantmentLevel(SlimeBreederEnchantments.BREAKABLE, stack);
	
	if(breakableLevel>0)
	{
		if(stack.isItemDamaged())
		{
			stack.damageItem(1*breakableLevel, player);
		}

		
	}
	
}



public void flammableCheck(EntityPlayer player, ItemStack stack)
{

int flammableLevel = EnchantmentHelper.getEnchantmentLevel(SlimeBreederEnchantments.FLAMMABLE, stack);
	
	if(flammableLevel>0)
	{
		if(player.isBurning())
		{
			if(stack.getItemDamage() > 0 )
			{
				stack.damageItem(6, player);
				player.setFire(10);
			}else{
				player.extinguish();
			}

		}
	}
	
}

public void slipperyCheck(EntityPlayer player, ItemStack stack)
{
	
int stickyLevel = EnchantmentHelper.getEnchantmentLevel(SlimeBreederEnchantments.STICKY, stack);
	
	if(stickyLevel>0)
	{
		if(player.collidedHorizontally)
		{
			player.addVelocity(0.0, 0.1, 0.0);
			
		}
	}
	
}


		
		/*NBTTagCompound SlotOneRead = itemList.getCompoundTagAt(0);
		NBTTagCompound SlotTwoRead = itemList.getCompoundTagAt(1);
		NBTTagCompound SlotThreeRead = itemList.getCompoundTagAt(2);
		NBTTagCompound SlotFourRead = itemList.getCompoundTagAt(3);
		NBTTagCompound SlotFiveRead = itemList.getCompoundTagAt(4);
		NBTTagCompound SlotResultRead = itemList.getCompoundTagAt(5);
		NBTTagCompound SlotDropRead = itemList.getCompoundTagAt(6);
		NBTTagCompound SlotFutureResultRead = itemList.getCompoundTagAt(7);*/
		
		////System.out.println(Item.getItemById(15).getUnlocalizedName());
		
	//}
	

	
}

