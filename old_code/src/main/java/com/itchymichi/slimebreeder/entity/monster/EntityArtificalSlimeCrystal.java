package com.itchymichi.slimebreeder.entity.monster;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Nullable;

import org.apache.http.impl.entity.EntityDeserializer;
import org.lwjgl.input.Keyboard;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederBlocks;
import com.itchymichi.slimebreeder.SlimeBreederEnchantments;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.SlimeBreederSounds;
import com.itchymichi.slimebreeder.entity.ai.CustomSlimeMoveHelper;
import com.itchymichi.slimebreeder.entity.ai.EntityArtificalSlimeRainbowCrystal.EntityAIFindNearestCompressed;
import com.itchymichi.slimebreeder.entity.ai.EntityColourSlime.EntityAIFindNearestBreeder;
import com.itchymichi.slimebreeder.entity.ai.EntityColourSlime.EntityAIFindNearestBreeder2;
import com.itchymichi.slimebreeder.entity.ai.EntityColourSlime.EntityAIFindNearestFood;
import com.itchymichi.slimebreeder.entity.ai.EntityColourSlime.EntityAIFindNearestSlime;
import com.itchymichi.slimebreeder.entity.ai.EntityColourSlime.EntityAISlimeMate;
import com.itchymichi.slimebreeder.handlers.SBASoundHandler;
import com.itchymichi.slimebreeder.inventory.ContainerSlimeChest;
import com.itchymichi.slimebreeder.items.FilledSlimeCapsule;
import com.itchymichi.slimebreeder.items.SlimeInfusedBoots;
import com.itchymichi.slimebreeder.items.SlimeInfusedChest;
import com.itchymichi.slimebreeder.items.SlimeInfusedHelm;
import com.itchymichi.slimebreeder.items.SlimeInfusedLeggings;
import com.itchymichi.slimebreeder.items.Slimepedia;
import com.itchymichi.slimebreeder.items.Slimetuner;
import com.itchymichi.slimebreeder.lists.EnumAccessory;
import com.itchymichi.slimebreeder.lists.EnumCraftingIngredients;
import com.itchymichi.slimebreeder.lists.EnumEdibleItems;
import com.itchymichi.slimebreeder.lists.EnumItemColor;
import com.itchymichi.slimebreeder.lists.EnumRecipes;
import com.itchymichi.slimebreeder.lists.EnumTune;
import com.itchymichi.slimebreeder.lists.SlimeColor;
import com.itchymichi.slimebreeder.lists.SlimeItems;
import com.itchymichi.slimebreeder.lists.SlimeRecipes;
import com.itchymichi.slimebreeder.utility.IHasModel;

import akka.util.Collections;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.AbstractChestHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ContainerHorseChest;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockSpecial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry.EntityRegistration;
import net.minecraftforge.items.ItemHandlerHelper;
import scala.reflect.internal.Trees.This;


public class EntityArtificalSlimeCrystal extends EntityArtificalSlimeBase implements IInventoryChangedListener
{
	
	protected static final DataParameter<Integer> ARGB = EntityDataManager.<Integer>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.VARINT);
	private int rgb;
	
	private boolean topobs;
	private boolean backobs;
	private boolean frontobs;
	private boolean lsideobs;
	private boolean rsideobs;
	
	private boolean isPlayerNear;
	

	
	private NBTTagCompound ACSlotTop;
	private NBTTagCompound ACSlotBack;
	private NBTTagCompound ACSlotFront;
	private NBTTagCompound ACSlotLSide;
	private NBTTagCompound ACSlotRSide;
	
	

	
	 private static final DataParameter<Boolean> FRONTOBS = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> BACKOBS = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> LSIDEOBS = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> RSIDEOBS = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> TOPOBS = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> ISPLAYERNEAR = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.BOOLEAN);
	 
	 private static final DataParameter<Boolean> FURNACE = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> COLLECTOR = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> CRAFTER = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> SPOUT = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> VENT = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> BREEDER = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> BREWING = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.BOOLEAN);

	 
	 private static final DataParameter<ItemStack> ACTOP = EntityDataManager.<ItemStack>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.ITEM_STACK);
	 private static final DataParameter<ItemStack> ACLSIDE = EntityDataManager.<ItemStack>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.ITEM_STACK);
	 private static final DataParameter<ItemStack> ACRSIDE = EntityDataManager.<ItemStack>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.ITEM_STACK);
	 private static final DataParameter<ItemStack> ACBACK = EntityDataManager.<ItemStack>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.ITEM_STACK);
	 private static final DataParameter<ItemStack> ACFRONT = EntityDataManager.<ItemStack>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.ITEM_STACK);
	
	 private static final DataParameter<Boolean> DATA_ID_CHEST = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystal.class, DataSerializers.BOOLEAN);

	 
	 protected ContainerSlimeChest slimeChest;

	 private net.minecraftforge.items.IItemHandler itemHandler = null; // Initialized by initHorseChest above.
	 
	 public EntityArtificalSlimeCrystal(World worldIn) {
		super(worldIn);
        this.moveHelper = new CustomSlimeMoveHelper(this);
	}
	
	 protected void entityInit()
		{
			 
			super.entityInit(); 
			
	        this.dataManager.register(this.ARGB, Integer.valueOf(rgb));
	        
	        this.dataManager.register(FRONTOBS, Boolean.valueOf(false));
	        this.dataManager.register(BACKOBS, Boolean.valueOf(false));
	        this.dataManager.register(LSIDEOBS, Boolean.valueOf(false));
	        this.dataManager.register(RSIDEOBS, Boolean.valueOf(false));
	        this.dataManager.register(TOPOBS, Boolean.valueOf(false));
	        this.dataManager.register(ISPLAYERNEAR, Boolean.valueOf(false));
	        
	        this.dataManager.register(FURNACE, Boolean.valueOf(false));
	        this.dataManager.register(COLLECTOR, Boolean.valueOf(false));
	        this.dataManager.register(CRAFTER, Boolean.valueOf(false));
	        this.dataManager.register(SPOUT, Boolean.valueOf(false));
	        this.dataManager.register(VENT, Boolean.valueOf(false));
	        this.dataManager.register(BREEDER, Boolean.valueOf(false));
	        this.dataManager.register(BREWING, Boolean.valueOf(false));
	        
	        this.dataManager.register(ACTOP, ItemStack.EMPTY);
	        this.dataManager.register(ACLSIDE, ItemStack.EMPTY);
	        this.dataManager.register(ACRSIDE, ItemStack.EMPTY);
	        this.dataManager.register(ACBACK, ItemStack.EMPTY);
	        this.dataManager.register(ACFRONT, ItemStack.EMPTY);
	        

	        this.dataManager.register(DATA_ID_CHEST, Boolean.valueOf(false));
		   
	        initItemSlots();

		}
	 

	public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
    	super.writeEntityToNBT(tagCompound);
    	
    	tagCompound.setInteger("RGB", this.getRGB2());
    	


    	
    	NBTTagList nbttaglist = new NBTTagList();
    	
    	nbttaglist.appendTag(ACSlotTop);
    	nbttaglist.set(0, ACSlotTop);
    	
    	nbttaglist.appendTag(ACSlotBack);
    	nbttaglist.set(1, ACSlotBack);
    	
    	nbttaglist.appendTag(ACSlotFront);
    	nbttaglist.set(2, ACSlotFront);
    	
    	nbttaglist.appendTag(ACSlotLSide);
    	nbttaglist.set(3, ACSlotLSide);
    	
    	nbttaglist.appendTag(ACSlotRSide);
    	nbttaglist.set(4, ACSlotRSide);
    	
    	
    	tagCompound.setTag("modItems", nbttaglist);
    	
    	NBTTagList itemList = tagCompound.getTagList("modItems", 10);
    	
    	tagCompound.setBoolean("ChestedSlime", this.hasChest());
    	tagCompound.setBoolean("FurnaceSlime", this.hasFurnace());
    	tagCompound.setBoolean("CollectorSlime", this.hasCollector());
    	tagCompound.setBoolean("CrafterSlime", this.hasCrafter());
    	tagCompound.setBoolean("SpoutSlime", this.hasSpout());
    	tagCompound.setBoolean("VentSlime", this.hasVent());
    	tagCompound.setBoolean("BreederSlime", this.hasBreeder());
    	tagCompound.setBoolean("BrewingSlime", this.hasBrewingTank());

    	
    	tagCompound.setInteger("VolumeSlime", this.getLiquidVolume());

        if (this.hasChest())
        {
            NBTTagList nbttaglist2 = new NBTTagList();

            for (int i = 2; i < this.slimeChest.getSizeInventory(); ++i)
            {
                ItemStack itemstack = this.slimeChest.getStackInSlot(i);

                if (!itemstack.isEmpty())
                {
                    NBTTagCompound nbttagcompound = new NBTTagCompound();
                    nbttagcompound.setByte("Slot", (byte)i);
                    itemstack.writeToNBT(nbttagcompound);
                    nbttaglist2.appendTag(nbttagcompound);
                }
            }

            tagCompound.setTag("Items", nbttaglist2);
        }
    	
    	
    }
 
 
 
 public void readEntityFromNBT(NBTTagCompound tagCompound)
   {
       super.readEntityFromNBT(tagCompound);
      	
       this.setFRGB(tagCompound.getInteger("RGB"));
       
       NBTTagList itemList = tagCompound.getTagList("modItems", 10);
       
       this.ACSlotTop = itemList.getCompoundTagAt(0);
   	   this.ACSlotBack = itemList.getCompoundTagAt(1);
   	   this.ACSlotFront = itemList.getCompoundTagAt(2);
   	   this.ACSlotLSide = itemList.getCompoundTagAt(3);
   	   this.ACSlotRSide = itemList.getCompoundTagAt(4);
   	   

   	   this.setServerACTop(new ItemStack(itemList.getCompoundTagAt(0)));
   	   this.setServerACBack(new ItemStack(itemList.getCompoundTagAt(1)));
   	   this.setServerACFront(new ItemStack(itemList.getCompoundTagAt(2)));
   	   this.setServerACLSide(new ItemStack(itemList.getCompoundTagAt(3)));
   	   this.setServerACRSide(new ItemStack(itemList.getCompoundTagAt(4)));
   	   

   	this.setChested(tagCompound.getBoolean("ChestedSlime"));
   	this.setFurnace(tagCompound.getBoolean("FurnaceSlime"));
   	this.setCollector(tagCompound.getBoolean("CollectorSlime"));
   	this.setSpout(tagCompound.getBoolean("SpoutSlime"));
   	this.setVent(tagCompound.getBoolean("VentSlime"));
   	this.setBreeder(tagCompound.getBoolean("BreederSlime"));
   	this.setBrewing(tagCompound.getBoolean("BrewingSlime"));
   	
   	this.setLiquidVolume(tagCompound.getInteger("VolumeSlime"));


    if (this.hasChest())
    {
    	 //System.out.println(" Has Chest");
        NBTTagList nbttaglist2 = tagCompound.getTagList("Items", 10);
        this.initSlimeChest();

        for (int i = 0; i < nbttaglist2.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist2.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;

            if (j >= 2 && j < this.slimeChest.getSizeInventory())
            {
                this.slimeChest.setInventorySlotContents(j, new ItemStack(nbttagcompound));
                //System.out.println(new ItemStack(nbttagcompound));
            }
        }
    }

    this.updateSlimeSlots();

    	
    	this.targetTasks.taskEntries.clear();
		 this.initEntityAI();
		 this.updateAITasks();
       
   }
 
 public void doJump() {
	 super.doJump();
	 
	 int smeltFactor = 0;
	 
	 if(this.hasFurnace()) {
		 if(this.getACRSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON)) {
			 smeltFactor = 1;
		 }
		 
		 if(this.getACLSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON)) {
			 smeltFactor = 1;
		 }
		 
		 if(this.getACLSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON) && this.getACRSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON)) {
			 smeltFactor = 2;
		 }
	 }
	 
	 //int smeltTimer = this.getTimer() + smeltFactor;
	 

	//590
	//System.out.println("Timer = " + this.getTimer());
	 if(this.getTimer() > 59) {
		 
		 if(this.hasFurnace()) {
			 if(smeltFactor == 1) {
				 smeltItems();
			 }
			 if(smeltFactor == 2) {
				 smeltItems();
				 smeltItems();
			 }

		 }
		 
		 if(this.hasBrewingTank()) {
				 brewItems();
		 }
		 
		 if(this.hasCrafter()) {
			 craftItems();
	 }
		 
		 this.setTimer(0);

		 

		 
		 if(this.getLiquidVolume() < 1000)
	     {
			 //System.out.println(this.getPotion().getDisplayName() + "    " + this.getMasterCoreFluid().getString("FluidName").toString());
			 if(this.getPotion().getDisplayName() == this.getMasterPotion().getDisplayName()) {
			 NBTTagCompound fluid = this.getCoreFluid();
			 
			 System.out.println(this.getLiquidVolume());
			 int oldAmount = this.getLiquidVolume();
			 int newAmount = this.getLiquidVolume() + 100;
			 
			 this.setLiquidVolume(newAmount);
				 
			 //NBTTagCompound newFluid = fluid.copy();
			 //newFluid.setInteger("Amount",newAmount);
			 //this.setCoreFluid(newFluid);
				
			//System.out.println(this.getLiquidVolume());
			 }
	     }
		 
		 //System.out.println(this.getServerCoreFluid());
		 

		 
	 }
	 
	 this.setTimer(this.getTimer()+1);
	 
//System.out.println(this.getCoreFluid().getInteger("Amount"));
	 
 }
 

public void onUpdate(){
	 super.onUpdate();
	 slimeSelection();
	 checkFloor();
	 
	 if(this.getLiquidVolume() == 0) {
		 System.out.println(this.getCoreFluid() + " " + this.getMasterCoreFluid());
		 
		 NBTTagCompound masterFluid = this.getMasterCoreFluid().copy();
		 masterFluid.setInteger("Amount", 100);
		 this.setCoreFluid(masterFluid);
		 System.out.println(this.getCoreFluid() + " " + this.getMasterCoreFluid());
		 
	 }
	 
	 
	 
	 //slimeIndicatorConstantArtifical();
	 /*if(this.getIsPlayerNear()) {
		 
		 this.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2, 30));
		 
	 }*/
	
	 /*if(this.getCoreFluid().getInteger("Amount") < 1000) {
		 
		 NBTTagCompound fluid = this.getCoreFluid();
		 
		 System.out.println(this.getCoreFluid().getInteger("Amount"));
		 int oldAmount = this.getCoreFluid().getInteger("Amount");
		 int newAmount = this.getCoreFluid().getInteger("Amount") + 100;
		 
		 
			 
		 NBTTagCompound newFluid = fluid.copy();
		 newFluid.setInteger("Amount",newAmount);
		 this.setCoreFluid(newFluid);
			
		System.out.println(this.getCoreFluid().getInteger("Amount"));
	 }*/
	 
	 //System.out.println(this.getLSideObs());
	 //System.out.println(this.getACFront());
 }
 
 public void checkFloor()
 {
 	
 	if(this.hasCollector() && this.onGround)
 	{
 		
	    	List<EntityItem> list = this.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().grow(5.0D));
	    	
 	
				for (EntityItem entityitem : list)
		        {
					
					
						//System.out.println("collector");
				    	//entityliving.startRiding(this, true);
				    	//this.setVelocity(0D, 0.0D, 0D);
				    	////System.out.println("Tracking");
				    	
						this.getNavigator().tryMoveToXYZ(entityitem.posX, entityitem.posY, entityitem.posZ, 2);
						
						this.faceEntity(entityitem, 10.0F, 10.0F);
		                ((CustomSlimeMoveHelper)this.getMoveHelper()).setDirection(this.rotationYaw, true);
		            
						
						//this.startRiding(entityliving, true);
		    	
				}
		
		        
 	}else{
 		if(this.hasCollector())
     	{
 			List<EntityItem> list = this.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().grow(-5.0D));
 	    	
 	    	
				for (EntityItem entityitem : list)
		        {
					
					
				    	//entityliving.startRiding(this, true);
				    	//this.setVelocity(0D, 0.0D, 0D);
				    	////System.out.println("Tracking Air");
				    	
						this.getNavigator().tryMoveToXYZ(entityitem.posX, entityitem.posY, entityitem.posZ, 2);
						
						//this.startRiding(entityliving, true);
		    	
				    
		
		        }
     	}
 	}
 	
 	if(this.hasCollector() && this.hasChest())
	{
    	List<EntityItem> list = this.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().grow(1.0D));
    	
	
			for (EntityItem entityitem : list)
	        {
				
				if (entityitem instanceof EntityItem)
			    {
			    	////System.out.println("Item found");
			    	EntityItem item = (EntityItem) entityitem;
			    	
			    	
			    	////System.out.println(itemstack);
			    	
			    	if(!this.world.isRemote)
			    	{
			    		System.out.println("Found Item");
			    		ItemStack dropitemstack = ((EntityItem) item).getItem();
			    		entityitem.setDead();
			    		this.addToSlimeChest(dropitemstack);
			    		//entityitem.setDead();
	
			    	}
	    	
			    }
	
	        }
	}
 	
 }
 
 
 public void slimeIndicatorConstantArtifical() {
	 
	 
	 
	 
 }
 
 public void initItemSlots()
 {
 	

 	
 	this.setACTop(new ItemStack(Items.SLIME_BALL));
 	this.setACBack(new ItemStack(Items.SLIME_BALL));
 	this.setACFront(new ItemStack(Items.SLIME_BALL));
 	this.setACLSide(new ItemStack(Items.SLIME_BALL));
 	this.setACRSide(new ItemStack(Items.SLIME_BALL));
 	

 	
 	

	
 }
 
 @Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
 {
	 dropSlimeChest();
     ResourceLocation resourcelocation = this.getLootTable();
     

     NBTTagCompound slimeItem = this.serializeNBT();
     
     ItemStack slimeBlock = new ItemStack(Item.getItemFromBlock(SlimeBreederBlocks.RAINBOWARTIFICALSLIMEBLOCK));
     
     
     NBTTagCompound nbt = new NBTTagCompound();
     
     
     
     NBTTagList nbttaglist = new NBTTagList();
 	
 	nbttaglist.appendTag(slimeItem);
 	nbttaglist.set(0, slimeItem);
      
 	nbt.setTag("slimes", nbttaglist);
 	
 	slimeBlock.setTagCompound(nbt);

 	System.out.println("NBT = " + slimeBlock);
 	
 	this.entityDropItem(slimeBlock, 0.0F);
     

    
 }
 
 public void slimeSelection(){
 	
	 
 	List<EntityLivingBase> list2 = this.getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().grow(3.0D).offset(-1D, 0, 0));
 	//System.out.println("Running");
	this.setisPlayerNear(false);
		 /*this.setFrontObs(false);
		 this.setBackObs(false);
		 this.setLSideObs(false);
		 this.setRSideObs(false);
		 this.setTopObs(false);*/
 	for (EntityLivingBase entitylivingbase : list2)
     {
 		//System.out.println("Running");
			if (entitylivingbase instanceof EntityPlayer)
		    {
				
				EntityPlayer player = (EntityPlayer) entitylivingbase;
		    	
		    	if(player.getHeldItemMainhand().hasTagCompound()) {
		    		
		    		ItemStack stack = player.getHeldItemMainhand();
		    		
		    		NBTTagCompound tag = stack.getTagCompound();
		    		
		    		if(tag.hasKey("accessory")) {
				    	this.setisPlayerNear(true);
				    	//System.out.println("Player Near");
				    	
				    	double distance = this.getEntityBoundingBox().getCenter().distanceTo(player.getPositionVector());

    					//System.out.println("Player Found");
    					
    					Vec3d Front = this.getLookVec().normalize();
    					Vec3d PlayerFront = player.getLookVec().normalize();
    					
    					double lengthToPlayerFront = Front.dotProduct(PlayerFront);
    					
    					
    					Vec3d SlimeRight = this.getLookVec().normalize().rotateYaw(0.5f);
    					double SlimeRightlengthToPlayerFront = SlimeRight.dotProduct(PlayerFront);
    					
    					Vec3d SlimeLeft = this.getLookVec().normalize().rotateYaw(-0.5f);
    					double SlimeLeftlengthToPlayerFront = SlimeLeft.dotProduct(PlayerFront);
    					
    					
    					Vec3d Back = new Vec3d(Front.x*-1, Front.y*-1, Front.z*-1);
    					
    					Vec3d LSide = new Vec3d(Front.z*-1, Front.y, Front.x*-1);
    					Vec3d RSide = new Vec3d(Front.z, Front.y*-1, Front.x);
    					
    					Vec3d playereyeheight = player.getLookVec();
    					
    					//Vec3d newheight = new Vec3d(playereyeheight.x, playereyeheight.y*10, playereyeheight.z);
    					Vec3d newheight = new Vec3d(playereyeheight.x, playereyeheight.y-1, playereyeheight.z);

    					double lengthToFront = Front.dotProduct(newheight);
    					double lengthToBack = Front.dotProduct(newheight);
    					
    					double lengthToLSide = LSide.dotProduct(newheight);
    					double lengthToRSide = RSide.dotProduct(newheight);
    					
    					
    					
    					
    					
    					//System.out.println(lengthToPlayerFront+SlimeLeftlengthToPlayerFront);
    					
    					if(lengthToPlayerFront+SlimeLeftlengthToPlayerFront > (-1.20) && lengthToPlayerFront+SlimeLeftlengthToPlayerFront < (-0.90) && !this.getLSideObs() && !this.getRSideObs() && !this.getTopObs() && !player.isSneaking() && tag.getBoolean("front") || lengthToPlayerFront+SlimeLeftlengthToPlayerFront < (-1.20) && !this.getLSideObs() && !this.getRSideObs() && !this.getTopObs() && !player.isSneaking() && tag.getBoolean("front")){
    						//System.out.println("Looking at Front");
    						//System.out.println("Looking at Front, Front = " + lengthToFront + "			Looking at Right, Right = " + lengthToRSide );
    						this.setisPlayerNear(true);
    						 this.setFrontObs(true);
    						 this.setBackObs(false);
    						 this.setRSideObs(false);
    						 this.setLSideObs(false);
    						 this.setTopObs(false);
    						 
    						
    					}else {
    						this.setFrontObs(false);
    					}
    					
    					if(lengthToPlayerFront+SlimeLeftlengthToPlayerFront < (1.20) && lengthToPlayerFront+SlimeLeftlengthToPlayerFront > (0.90) && !this.getLSideObs() && !this.getRSideObs() && !this.getTopObs() && !player.isSneaking() && tag.getBoolean("back") || lengthToPlayerFront+SlimeLeftlengthToPlayerFront > (1.20) && !this.getLSideObs() && !this.getRSideObs() && !this.getTopObs() && !player.isSneaking()&& tag.getBoolean("back")){
    						//System.out.println("Looking at Back");
    						//System.out.println("Looking at Front, Front = " + lengthToFront + "			Looking at Right, Right = " + lengthToRSide );
    						this.setisPlayerNear(true);
    						this.setBackObs(true);
	   						 this.setFrontObs(false);
	   						 this.setRSideObs(false);
	   						 this.setLSideObs(false);
	   						 this.setTopObs(false);
    						
    					}else {
    						this.setBackObs(false);
    					}
    					
    					if(lengthToPlayerFront+SlimeLeftlengthToPlayerFront > (-0.75) && lengthToPlayerFront+SlimeLeftlengthToPlayerFront < (-0.10) && !this.getFrontObs() && !this.getBackObs() && !this.getTopObs() && SlimeLeftlengthToPlayerFront < 0 && !player.isSneaking() && tag.getBoolean("rside")){
    						//System.out.println("Looking at rside , " + lengthToPlayerFront + " . " + SlimeLeftlengthToPlayerFront  + " = " + (lengthToPlayerFront+SlimeLeftlengthToPlayerFront) );
    						//System.out.println("Looking at Front, Front = " + lengthToFront + "			Looking at Right, Right = " + lengthToRSide );
    						this.setisPlayerNear(true);
    						this.setRSideObs(true);
   						 this.setFrontObs(false);
   						 this.setBackObs(false);
   						 this.setLSideObs(false);
   						 this.setTopObs(false);
    						
    					}else {
    						this.setRSideObs(false);
    					}
    					
    					if(lengthToPlayerFront+SlimeLeftlengthToPlayerFront < (0.75) && lengthToPlayerFront+SlimeLeftlengthToPlayerFront > (0.10) && !this.getFrontObs() && !this.getBackObs() && !this.getTopObs() && SlimeLeftlengthToPlayerFront > 0 && !player.isSneaking() && tag.getBoolean("lside")){
    						//System.out.println("Looking at lside, " + lengthToPlayerFront + " . " + SlimeRightlengthToPlayerFront + " = " + (lengthToPlayerFront+SlimeRightlengthToPlayerFront) );
    						//System.out.println("Looking at Front, Front = " + lengthToFront + "			Looking at Right, Right = " + lengthToRSide );
    						this.setisPlayerNear(true);
    						this.setLSideObs(true);
      						 this.setFrontObs(false);
       						 this.setBackObs(false);
       						 this.setRSideObs(false);
       						 this.setTopObs(false);
    						
    					}else {
    						this.setLSideObs(false);
    					}
    					
    					if(player.isSneaking() && tag.getBoolean("top")) {
    						
    						this.setisPlayerNear(true);
    						 this.setTopObs(true);
       						 this.setFrontObs(false);
       						 this.setBackObs(false);
       						 this.setRSideObs(false);
       						 this.setLSideObs(false);
    						 
    						
    					}else {
    						this.setTopObs(false);
    					}
    					
				    	
		    		}
		    		
		    	}
	    			 
		    }
		}
	
	 }

 
 public boolean processInteract(EntityPlayer player, EnumHand hand){
	 
	 ItemStack itemstack = player.inventory.getCurrentItem();
 
	 if((itemstack.isEmpty()))
	 	{
		 
		 if(this.hasChest()) {
	 			
	 			System.out.println("Open gui? ");
	 			
	 			 this.openGUI(player, player.getEntityWorld());
	 			/*Minecraft mc = Minecraft.getMinecraft();
	 			this.initSlimeChest();
	 			mc.displayGuiScreen(new GuiChest(this.slimeChest, player.inventory));*/
	             return true;
	 			
	 		}
		 
		 
	 	}
	 
	 if(!(itemstack.isEmpty()))
 	{
 	if(itemstack.getItem() != SlimeBreederItems.SLIMALYSER)
	    	{
 		player.swingArm(hand);
	    	}
 	
 		
 	
 	}
	 
	 if(!(itemstack.isEmpty()) && !world.isRemote)
	 	{
	 	if(itemstack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null))
		    	{
	 		
	 		NBTTagCompound fluid = this.getCoreFluid();
	 		FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(fluid);
	 		System.out.println(itemstack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null).getTankProperties()[0].getCapacity() + " = " + fluidStack.amount);
	 		if(this.getLiquidVolume() >= itemstack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null).getTankProperties()[0].getCapacity())
	 		{
	 			
	 			if(itemstack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null).getTankProperties()[0].canDrainFluidType(fluidStack)) {
	 				itemstack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null).fill(fluidStack, true);

	 				ItemStack newContainter = itemstack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null).getContainer();
	 				 player.inventory.setInventorySlotContents(player.inventory.getSlotFor(itemstack), newContainter);

	 				int newAmount = this.getLiquidVolume()-itemstack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null).getTankProperties()[0].getCapacity();
	 				 this.setLiquidVolume(newAmount);

	 				 /*int newAmount = fluidStack.amount-itemstack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null).getTankProperties()[0].getCapacity();
	 				fluidStack.amount = newAmount;
	 				NBTTagCompound newFluidStack = fluidStack.serializeNBT();
	 				this.setCoreFluid(fluid);*/
	 			}
	 			
	 		}
	 		
	 		
	 		
	 		//FluidActionResult emptiedReal = FluidUtil.tryFillContainer(itemstack, (IFluidHandler) fluidStack, 1000, player, true);
	 		
	 		/*NBTTagCompound fluid = this.getCoreFluid();
	 		FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(fluid);
	 		System.out.println(fluid);
	 		itemstack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null).fill(fluidStack, false);
	 		System.out.println(itemstack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null).fill(fluidStack, false));*/
		    	}
	 	
	 	if(itemstack.getItem() == Items.GLASS_BOTTLE && this.hasSpout() && !player.isCreative())
    	{
	 	
	 		NBTTagCompound fluid = this.getCoreFluid();
	 		FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(fluid);
	 		
	 		if(fluid.hasKey("POTION")) {
	 			NBTTagList potionList = fluid.getTagList("POTION", 10).copy();
				NBTTagCompound Potion2 = potionList.getCompoundTagAt(0).copy();
				this.entityDropItem(new ItemStack(Potion2), 1);
				player.inventory.decrStackSize(player.inventory.getSlotFor(itemstack), 1);
				//player.inventory.setInventorySlotContents(player.inventory.getSlotFor(itemstack), new ItemStack(Potion2));
				
				int newAmount = this.getLiquidVolume()-250;
				
				this.setLiquidVolume(newAmount);
				
				 /*NBTTagCompound newFluid = fluid.copy();
				newFluid.setInteger("Amount",newAmount);
				this.setCoreFluid(newFluid);
				System.out.println(this.getMasterCoreFluid());*/
	 		}
	 		
	 	}
	 	

	 	
	
	 	
	 	}
	 	
	 if (!(itemstack.isEmpty()))
     {
		 
		 float volume = 0.4F * 1.0F;
		 
         this.playSound(SoundEvents.BLOCK_GLASS_HIT, volume, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 2.0F);
		 

	    	if(player.getHeldItemMainhand().hasTagCompound()) {
	    		
	    		ItemStack stack = player.getHeldItemMainhand();
	    		
	    		NBTTagCompound tag = stack.getTagCompound();
	    		
	    		if(tag.hasKey("accessory")) {
    			 
    			 if(this.getFrontObs() && tag.getBoolean("front")){
    				 
    				 if(this.getACFront() != ItemStack.EMPTY && this.getACFront().getItem() != Items.SLIME_BALL ) {
    					 if(!this.getEntityWorld().isRemote) {
    					 this.entityDropItem(this.getACFront(), 1);
    					 }
    				 } 
    				 
    				 this.setServerACFront(itemstack.copy());
    				 this.setACFront(itemstack.copy());
    				 
    				 //this.setACFront(new ItemStack(itemstack.getItem()));
    				 //this.dataManager.set(ACFRONT, new ItemStack(itemstack.getItem()));
    				 
    				 //this.ACSlotFront = itemstack.serializeNBT();
    				
    				 System.out.println("Set Front " + itemstack + " into " + this.dataManager.get(ACFRONT));
    				 player.inventory.clearMatchingItems(itemstack.getItem(), -1, 1, null);
    			 }
    			 
    	
    			 
    			 if(this.getBackObs()&& tag.getBoolean("back")){
    				 
    				 if(this.hasChest()) {
    					 if(this.slimeChest != null) {
    					 ContainerSlimeChest containerslimechest = this.slimeChest;
    					 if(containerslimechest.getSizeInventory() != 0) {
    					 for (int j = 0; j < containerslimechest.getSizeInventory(); ++j)
    			            {
    						 ItemStack invItem = containerslimechest.getStackInSlot(j);
    						 if(!invItem.isEmpty()) {
    							 this.entityDropItem(invItem, invItem.getCount());
    						 }
    						 
    						 
    			            }
    					 containerslimechest.clear();
    					 containerslimechest.markDirty();
    					 this.setChested(false);
    					 }
    				 }
    			}
    				 if(this.getACBack() != ItemStack.EMPTY && this.getACBack().getItem() != Items.SLIME_BALL) {
    					 if(!this.getEntityWorld().isRemote) {
    					 this.entityDropItem(this.getACBack(), 1);
    					 }
    				 }
    					 
    					 
    				 this.setServerACBack(itemstack.copy());
    				 this.setACBack(itemstack.copy());
    				 
    				 if(itemstack.getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEBACKPACK)) {
    					 
    					 this.setChested(true);
    					 this.initSlimeChest();
    					 System.out.println("Chested");
    				 }
    				 
    				 System.out.println("Set Back");
    				 player.inventory.clearMatchingItems(itemstack.getItem(), -1, 1, null);
    			 }
    			 
    			 if(this.getLSideObs()&& tag.getBoolean("lside")){
    				 
    				 if(this.getACLSide() != ItemStack.EMPTY && this.getACLSide().getItem() != Items.SLIME_BALL ) {
    					 if(!this.getEntityWorld().isRemote) {
    					 this.entityDropItem(this.getACLSide(), 1);
    					 }
    				 }
    				 this.setServerACLSide(itemstack.copy());
    				 this.setACLSide(itemstack.copy());
    				 
    				 
    				 System.out.println("Set LSide");
    				 player.inventory.clearMatchingItems(itemstack.getItem(), -1, 1, null);
    			 }
    			 
    			 if(this.getRSideObs()&& tag.getBoolean("rside")){
    				 
    				 if(this.getACRSide() != ItemStack.EMPTY && this.getACRSide().getItem() != Items.SLIME_BALL ) {
    					 if(!this.getEntityWorld().isRemote) {
    					 this.entityDropItem(this.getACRSide(), 1);
    					 }
    				 }
    				 
    				 this.setServerACRSide(itemstack.copy());
    				 this.setACRSide(itemstack.copy());
    				 
    				 
    				 
    				 System.out.println("Set RSide");
    				 player.inventory.clearMatchingItems(itemstack.getItem(), -1, 1, null);
    			 }
    			 //if(this.getTopObs() && ((IHasModel)itemstack.getItem()).getTop()){
    			 if(this.getTopObs()&& tag.getBoolean("top")){
    				 
    				 if(this.getACTop() != ItemStack.EMPTY && this.getACTop().getItem() != Items.SLIME_BALL ) {
    					 if(!this.getEntityWorld().isRemote) {
    					 this.entityDropItem(this.getACTop(), 1);
    					 }
    				 }
    				 
    				 this.setServerACTop(itemstack.copy());
    				 this.setACTop(itemstack.copy());
    				 
    				 
    				 System.out.println("Set Top");
    				 player.inventory.clearMatchingItems(itemstack.getItem(), -1, 1, null);
    			 }
    			 
    			 return true;
    		 }
    	 
    		}
         
    	 return true;
         
     }
	 return true;
	 
 }

 public int getRGB2()
 {
 	//return this.dataWatcher.getWatchableObjectInt(20);
 	return ((Integer)this.dataManager.get(ARGB)).intValue();
 	
 }
 
 public void setFRGB(int f)
 {
 	this.dataManager.set(ARGB, Integer.valueOf(f));
 }
 
    
 ////////////////Getter & Setters for accessories//////////////////////////////
 
 public void setFrontObs(Boolean obs) {
		this.dataManager.set(FRONTOBS, Boolean.valueOf(obs));
		
		
	}
	
	public Boolean getFrontObs() {
		return this.dataManager.get(FRONTOBS);
		
	}
	
	public Boolean getLocalFrontObs() {
		return frontobs;
		
	}
	
	public void setBackObs(Boolean obs) {
		this.dataManager.set(BACKOBS, Boolean.valueOf(obs));
		
		
	}
	
	public Boolean getBackObs() {
		return this.dataManager.get(BACKOBS);
		
	}
	
	public Boolean getLocalBackObs() {
		return backobs;
		
	}
	
	public void setLSideObs(Boolean obs) {
		this.dataManager.set(LSIDEOBS, Boolean.valueOf(obs));
		
		
	}
	
	public Boolean getLSideObs() {
		return this.dataManager.get(LSIDEOBS);
		
	}
	
	public Boolean getLocalLSideObs() {
		return lsideobs;
		
	}

	public void setRSideObs(Boolean obs) {
		this.dataManager.set(RSIDEOBS, Boolean.valueOf(obs));
		
		
	}
	
	public Boolean getRSideObs() {
		return this.dataManager.get(RSIDEOBS);
		
	}
	
	public Boolean getLocalRSideObs() {
		return rsideobs;
		
	}
	
	public void setTopObs(Boolean obs) {
		this.dataManager.set(TOPOBS, Boolean.valueOf(obs));
		
		
	}
	
	public Boolean getTopObs() {
		return this.dataManager.get(TOPOBS);
		
	}
	
	public Boolean getLocalTopObs() {
		return topobs;
		
	}
	
	public void setisPlayerNear(Boolean near) {
		this.dataManager.set(ISPLAYERNEAR, Boolean.valueOf(near));
		
		
	}
	
	public Boolean getIsPlayerNear() {
		return this.dataManager.get(ISPLAYERNEAR);
		
	}
	
	public Boolean getLocalIsPlayerNear() {
		return isPlayerNear;
		
	}
	
	////////////////////////Top//////////////////////////////
	
	public void setACTop(ItemStack stack) {
		
		this.ACSlotTop = stack.serializeNBT();
		
	}
	
	public void setServerACTop(ItemStack stack) {
			
			this.dataManager.set(ACTOP, stack);
			
		}
	
	public ItemStack getACTop() {
		return new ItemStack(this.ACSlotTop);
		
	}
	
	public ItemStack getServerACTop() {
		return this.dataManager.get(ACTOP);
		
	}
	
	/////////////////////////////////////////////////////////////////
	
	/////////////////////////Back////////////////////////////////////
	
	public void setACBack(ItemStack stack) {
		
		this.ACSlotBack = stack.serializeNBT();
		
	}
	
	public void setServerACBack(ItemStack stack) {
			
			this.dataManager.set(ACBACK, stack);
			
		}
	
	public ItemStack getACBack() {
		return new ItemStack(this.ACSlotBack);
		
	}
	
	public ItemStack getServerACBack() {
		return this.dataManager.get(ACBACK);
		
	}
	
	////////////////////////////////////////////////////////////////////
	
	////////////////////LSIDE//////////////////////////////////////////
	
	public void setACLSide(ItemStack stack) {
		
		this.ACSlotLSide = stack.serializeNBT();
		
	}
	
	public void setServerACLSide(ItemStack stack) {
			
			this.dataManager.set(ACLSIDE, stack);
			
		}
	
	public ItemStack getACLSide() {
		return new ItemStack(this.ACSlotLSide);
		
	}
	
	public ItemStack getServerACLSide() {
		return this.dataManager.get(ACLSIDE);
		
	}
	
	/////////////////////////////////////////////////////////////////////
	
	//////////////////////////RSIDE/////////////////////////////////////
	public void setACRSide(ItemStack stack) {
		
		this.ACSlotRSide = stack.serializeNBT();
		
	}
	
	public void setServerACRSide(ItemStack stack) {
			
			this.dataManager.set(ACRSIDE, stack);
			
		}
	
	public ItemStack getACRSide() {
		return new ItemStack(this.ACSlotRSide);
		
	}
	
	public ItemStack getServerACRSide() {
		return this.dataManager.get(ACRSIDE);
		
	}
	
	//////////////////////////////////////////////////////////////////////
	
	//////////////////////////FRONT///////////////////////////////////////	
	
public void setACFront(ItemStack stack) {
		
		this.ACSlotFront = stack.serializeNBT();
		
	}
	
	public void setServerACFront(ItemStack stack) {
			
			this.dataManager.set(ACFRONT, stack);
			
		}
	
	public ItemStack getACFront() {
		return new ItemStack(this.ACSlotFront);
		
	}
	
	public ItemStack getServerACFront() {
		return ((ItemStack)this.dataManager.get(ACFRONT)).copy();
		
		
		
	}

	@Override
	public void onInventoryChanged(IInventory invBasic) {
		this.updateSlimeSlots();
		
		if(this.hasFurnace() && this.hasChest()) {
			this.checksmeltItems();
		}
		
		if(this.hasBrewingTank() && this.hasChest()) {
			//this.checkBrewingItems();
		}
		
	}
	
	
	public void checksmeltItems(){
		
		   ContainerSlimeChest containerslimechest = this.slimeChest;
		   Boolean hasSmeltable = false;

	        if (containerslimechest != null)
	        {
	            
	            int i = Math.min(containerslimechest.getSizeInventory(), this.slimeChest.getSizeInventory());

	            for (int j = 0; j < i; ++j)
	            {
	                ItemStack itemstack = containerslimechest.getStackInSlot(j);

	                if (!itemstack.isEmpty())
	                {
	                	ItemStack result = FurnaceRecipes.instance().getSmeltingResult(itemstack);
	                	System.out.println(this.getACLSide().getItem());
	                	if(result.getItem() != Items.AIR) {
		                	if(this.getACLSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEOFF)) {
		                		System.out.println("furnace on");
		                		this.setACLSide(new ItemStack(Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON)));
		                		this.setServerACLSide(new ItemStack(Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON)));
		                	}
		                	
		                	if(this.getACRSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEOFF)) {
		                		System.out.println("furnace on");
		                		this.setACRSide(new ItemStack(Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON)));
		                		this.setServerACRSide(new ItemStack(Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON)));
		                	}
		                	hasSmeltable = true;
	                	}

	                	
	                	
	                	
	                    //this.entityDropItem(itemstack, 0.0f);
	                    //this.slimeChest.removeStackFromSlot(j);
	                    //this.slimeChest.setInventorySlotContents(j, this.slimeChest.getStackInSlot(j));
	                    //this.slimeChest.markDirty();
	                }
	                
	                if(!hasSmeltable && j == 26) {
                		System.out.println("furnace off");
                		if(this.getACLSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON)) {
	                		System.out.println("furnace off L");
	                		this.setACLSide(new ItemStack(Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEOFF)));
	                		this.setServerACLSide(new ItemStack(Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEOFF)));
	                	}
                		
                		if(this.getACRSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON)) {
	                		System.out.println("furnace off R");
	                		this.setACRSide(new ItemStack(Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEOFF)));
	                		this.setServerACRSide(new ItemStack(Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEOFF)));
	                	}
                		System.out.println(j);
                	}
	                
	            }
	        }
	}
	
	public void smeltItems(){
		
		   ContainerSlimeChest containerslimechest = this.slimeChest;
		   Boolean hasSmeltable = false;

	        if (containerslimechest != null)
	        {
	            
	            int i = Math.min(containerslimechest.getSizeInventory(), this.slimeChest.getSizeInventory());

	            for (int j = 0; j < i; ++j)
	            {
	                ItemStack itemstack = containerslimechest.getStackInSlot(j);

	                if (!itemstack.isEmpty())
	                {
	                	ItemStack result = FurnaceRecipes.instance().getSmeltingResult(itemstack);
	                	if(result.getItem() != Items.AIR) {
	                		
	                	ItemStack drop = result.copy();
	                	drop.setCount(1);
	                        this.entityDropItem(drop, 0.0f);
	                        this.slimeChest.decrStackSize(j, 1);
	                        j = i;

		                	hasSmeltable = true;
	                	}

	                	
	                	
	                	
	                    //this.entityDropItem(itemstack, 0.0f);
	                    //this.slimeChest.removeStackFromSlot(j);
	                    //this.slimeChest.setInventorySlotContents(j, this.slimeChest.getStackInSlot(j));
	                    //this.slimeChest.markDirty();
	                }      
	            }
	        }
	}

	public void brewItems(){
		
	
		 ContainerSlimeChest containerslimechest = this.slimeChest;
		   Boolean hasSmeltable = false;

	        if (containerslimechest != null)
	        {
	            
	            int i = Math.min(containerslimechest.getSizeInventory(), this.slimeChest.getSizeInventory());

	            for (int j = 0; j < i; ++j)
	            {
	                ItemStack itemstack = containerslimechest.getStackInSlot(j);

	                if (!itemstack.isEmpty())
	                {

		                	if(BrewingRecipeRegistry.isValidIngredient(itemstack) && itemstack.getCount() > 2)
		                	{
		            	 		
		            	 		ItemStack storedPotion = this.getPotion();
		            	 		ItemStack newPotion = BrewingRecipeRegistry.getOutput(storedPotion, itemstack);
		            	 		System.out.println("Core Fluid = " + this.getCoreFluid() + "   Master Fluid = " + this.getMasterCoreFluid());
		            	 		if(!newPotion.isEmpty() && this.getLiquidVolume() == 1000) {
		            	 			NBTTagCompound newPotionNBT = newPotion.serializeNBT();
		            	 			FluidStack stackFluid = new FluidStack(FluidRegistry.WATER, 1000);
		            	 			NBTTagList nbtpotlist = new NBTTagList();
		            	       		nbtpotlist.appendTag(newPotionNBT);
		            	       		nbtpotlist.set(0, newPotionNBT);
		            	       		NBTTagCompound newCoreFluid = new NBTTagCompound();
		            	       		newCoreFluid.setTag("POTION", nbtpotlist);
		            	       		
		            	       		stackFluid.writeToNBT(newCoreFluid);
		            	       		this.setCoreFluid(newCoreFluid);
		            		 		System.out.println("Core Fluid = " + this.getCoreFluid() + "   Master Fluid = " + this.getMasterCoreFluid());
		            		 		
		            		 		this.slimeChest.decrStackSize(j, 2);
		            		 		
		            	 		}
		                	
	                	}

	                	
	                	
	                	
	                    //this.entityDropItem(itemstack, 0.0f);
	                    //this.slimeChest.removeStackFromSlot(j);
	                    //this.slimeChest.setInventorySlotContents(j, this.slimeChest.getStackInSlot(j));
	                    //this.slimeChest.markDirty();
	                }      
	            }
	        }
	}
	
	public void craftItems(){
		
		   ContainerSlimeChest containerslimechest = this.slimeChest;

	        if (containerslimechest != null)
	        {
	            
	            int i = Math.min(containerslimechest.getSizeInventory(), this.slimeChest.getSizeInventory());
	            ItemStack note;
	            NBTTagCompound nbt;	
                ResourceLocation recipelocation;
                IRecipe recipe;
                
				Ingredient item1 = Ingredient.EMPTY;
				Ingredient item2 = Ingredient.EMPTY;
				Ingredient item3 = Ingredient.EMPTY;
				Ingredient item4 = Ingredient.EMPTY;
				Ingredient item5 = Ingredient.EMPTY;
				Ingredient item6 = Ingredient.EMPTY;
				Ingredient item7 = Ingredient.EMPTY;
				Ingredient item8 = Ingredient.EMPTY;
				Ingredient item9 = Ingredient.EMPTY;
				
				ItemStack invItem = ItemStack.EMPTY;

				
				
				
				boolean item1correct = false;
				boolean item2correct = false;
				boolean item3correct = false;
				boolean item4correct = false;
				boolean item5correct = false;
				boolean item6correct = false;
				boolean item7correct = false;
				boolean item8correct = false;
				boolean item9correct = false;
				
	            
	            for (int j = 0; j < i; ++j)
	            {
	                ItemStack itemstack = containerslimechest.getStackInSlot(j);

	                if (!itemstack.isEmpty())
	                {
	                	
	                	if(itemstack.getItem() == SlimeBreederItems.CRAFTINGNOTE) {
	                		note = itemstack;
	                		
	                		if(note.getTagCompound() != null) {
	    			            nbt = note.getTagCompound();	
	    		                recipelocation = new ResourceLocation(nbt.getString("recipeName"));
	    		                recipe = CraftingManager.getRecipe(recipelocation);
	    		                
	    		                int i1 = recipe.getIngredients().size();
	    		                
	    		                for(int k1 = 0; k1 < i1; ++k1) {
	    		                	
	    		                	if(k1 == 0) {
	    		                	item1 = recipe.getIngredients().get(0);
	    		                	}
	    		                	
	    		                	if(k1 == 1) {
		    		                	item2 = recipe.getIngredients().get(1);
		    		                	}
	    		                	
	    		                	if(k1 == 2) {
		    		                	item3 = recipe.getIngredients().get(2);
		    		                	}
	    		                	
	    		                	if(k1 == 3) {
		    		                	item4 = recipe.getIngredients().get(3);
		    		                	}
	    		                	
	    		                	if(k1 == 4) {
		    		                	item5 = recipe.getIngredients().get(4);
		    		                	}
	    		                	
	    		                	if(k1 == 5) {
		    		                	item6 = recipe.getIngredients().get(5);
		    		                	}
	    		                	
	    		                	if(k1 == 6) {
		    		                	item7 = recipe.getIngredients().get(6);
		    		                	}
	    		                	
	    		                	if(k1 == 7) {
		    		                	item8 = recipe.getIngredients().get(7);
		    		                	}
	    		                	
	    		                	if(k1 == 8) {
		    		                	item9 = recipe.getIngredients().get(8);
		    		                	}

	    		                }
	    		                
	    						
	    				    	List<ItemStack> inventoryList = new ArrayList<ItemStack>();
	    				    	
	    				    	inventoryList.clear();




	    						for (int k = 0; k < i; ++k)
	    			            {
	    							
	    							ItemStack invitemstack = containerslimechest.getStackInSlot(k);
	    							if(!invitemstack.isEmpty()) {
	    								inventoryList.add(invitemstack);
	    							}
	    							
	    							
	    			            }
	    						
	    						int a = inventoryList.size();
	    						
	    						for (int f = 0; f < a; ++f)
	    			            {
	    							if(!item1.test(ItemStack.EMPTY) && !item1correct) {
	    							item1correct = item1.test(inventoryList.get(f));
	    							
	    							if(item1correct && inventoryList.get(f).getCount() > 0) {
	    								invItem = inventoryList.get(f).copy();
	    								invItem.setCount(inventoryList.get(f).getCount()-1);
	    								inventoryList.set(f, invItem);
	    							}else {
	    								if(item1correct && inventoryList.get(f).getCount() <= 0) {
	    									item1correct = false;
	    								}
	    							}

	    							}else {
	    								item1correct = true;
	    							}
	    							
	    							
	    							if(!item2.test(ItemStack.EMPTY) && !item2correct) {
	    							item2correct = item2.test(inventoryList.get(f));

	    							if(item2correct && inventoryList.get(f).getCount() > 0) {
	    								invItem = inventoryList.get(f).copy();
	    								invItem.setCount(inventoryList.get(f).getCount()-1);
	    								inventoryList.set(f, invItem);
	    							}else {
	    								if(item2correct && inventoryList.get(f).getCount() <= 0) {
	    									item2correct = false;
	    								}
	    							}

	    							}else {
	    								item2correct = true;
	    							}
	    							
	    							if(!item3.test(ItemStack.EMPTY) && !item3correct) {
	    							item3correct = item3.test(inventoryList.get(f));


	    							if(item3correct && inventoryList.get(f).getCount() > 0) {
	    								invItem = inventoryList.get(f).copy();
	    								invItem.setCount(inventoryList.get(f).getCount()-1);
	    								inventoryList.set(f, invItem);
	    							}else {
	    								if(item3correct && inventoryList.get(f).getCount() <= 0) {
	    									item3correct = false;
	    								}
	    							}

	    							
	    							}else {
	    								item3correct = true;
	    							}
	    							
	    							if(!item4.test(ItemStack.EMPTY) && !item4correct) {
	    							item4correct = item4.test(inventoryList.get(f));


	    							if(item4correct && inventoryList.get(f).getCount() > 0) {
	    								invItem = inventoryList.get(f).copy();
	    								invItem.setCount(inventoryList.get(f).getCount()-1);
	    								inventoryList.set(f, invItem);
	    							}else {
	    								if(item4correct && inventoryList.get(f).getCount() <= 0) {
	    									item4correct = false;
	    								}
	    							}

	    							
	    							}else {
	    								item4correct = true;
	    							}
	    							
	    							if(!item5.test(ItemStack.EMPTY) && !item5correct) {
	    							item5correct = item5.test(inventoryList.get(f));


	    							if(item5correct && inventoryList.get(f).getCount() > 0) {
	    								invItem = inventoryList.get(f).copy();
	    								invItem.setCount(inventoryList.get(f).getCount()-1);
	    								inventoryList.set(f, invItem);
	    							}else {
	    								if(item5correct && inventoryList.get(f).getCount() <= 0) {
	    									item5correct = false;
	    								}
	    							}

	    							
	    							}else {
	    								item5correct = true;
	    							}
	    							
	    							if(!item6.test(ItemStack.EMPTY) && !item6correct) {
	    							item6correct = item6.test(inventoryList.get(f));


	    							if(item6correct && inventoryList.get(f).getCount() > 0) {
	    								invItem = inventoryList.get(f).copy();
	    								invItem.setCount(inventoryList.get(f).getCount()-1);
	    								inventoryList.set(f, invItem);
	    							}else {
	    								if(item6correct && inventoryList.get(f).getCount() <= 0) {
	    									item6correct = false;
	    								}
	    							}

	    							
	    							}else {
	    								item6correct = true;
	    							}
	    							
	    							if(!item7.test(ItemStack.EMPTY) && !item7correct) {
	    							item7correct = item7.test(inventoryList.get(f));


	    							if(item7correct && inventoryList.get(f).getCount() > 0) {
	    								invItem = inventoryList.get(f).copy();
	    								invItem.setCount(inventoryList.get(f).getCount()-1);
	    								inventoryList.set(f, invItem);
	    							}else {
	    								if(item7correct && inventoryList.get(f).getCount() <= 0) {
	    									item7correct = false;
	    								}
	    							}

	    							
	    							}else {
	    								item7correct = true;
	    							}
	    							
	    							if(!item8.test(ItemStack.EMPTY) && !item8correct) {
	    							item8correct = item8.test(inventoryList.get(f));


	    							if(item8correct && inventoryList.get(f).getCount() > 0) {
	    								invItem = inventoryList.get(f).copy();
	    								invItem.setCount(inventoryList.get(f).getCount()-1);
	    								inventoryList.set(f, invItem);
	    							}else {
	    								if(item8correct && inventoryList.get(f).getCount() <= 0) {
	    									item8correct = false;
	    								}
	    							}

	    							
	    							}else {
	    								item8correct = true;
	    							}
	    							
	    							if(!item9.test(ItemStack.EMPTY) && !item9correct) {
	    							item9correct = item9.test(inventoryList.get(f));


	    							if(item9correct && inventoryList.get(f).getCount() > 0) {
	    								invItem = inventoryList.get(f).copy();
	    								invItem.setCount(inventoryList.get(f).getCount()-1);
	    								inventoryList.set(f, invItem);
	    							}else {
	    								if(item9correct && inventoryList.get(f).getCount() <= 0) {
	    									item9correct = false;
	    								}
	    							}

	    							
	    							}else {
	    								item9correct = true;
	    							}
	  
	    							if(item1correct && item2correct && item3correct && item4correct && item5correct && item6correct && item7correct && item8correct && item9correct) {
	    								System.out.println("Ingrediants found!");
	    								int a1 = inventoryList.size();
	    								boolean craft = false;
	    								
	    								for (int f1 = 0; f1 < i; ++f1)
	    	    			            {
	    									if(containerslimechest.getStackInSlot(f1).getItem() == recipe.getRecipeOutput().getItem() && containerslimechest.getStackInSlot(f1).getMaxStackSize() != containerslimechest.getStackInSlot(f1).getCount() || containerslimechest.getStackInSlot(f1).isEmpty()) {
	    										craft = true;
	    										f1 = i;
	    									}
	    	    			            }
	    								
	    								
	    								if(craft) {
	    	    						for (int f1 = 0; f1 < i; ++f1)
	    	    			            {
	    	    							ItemStack invItemstack = containerslimechest.getStackInSlot(f1);
	    	    							
	    	    							if(!item1.test(ItemStack.EMPTY)) {
	    	    								if(item1.test(invItemstack)){
	    	    									containerslimechest.decrStackSize(f1, 1);
	    	    							}
	    	    							}
	    	    							
		    	    						if(!item2.test(ItemStack.EMPTY)) {
		    	    								if(item2.test(invItemstack)){
		    	    									containerslimechest.decrStackSize(f1, 1);
		    	    							}
		    	    						}
			    	    					if(!item3.test(ItemStack.EMPTY)) {
			    	    								if(item3.test(invItemstack)){
			    	    									containerslimechest.decrStackSize(f1, 1);
			    	    							}
			    	    					}
				    	    				if(!item4.test(ItemStack.EMPTY)) {
				    	    								if(item4.test(invItemstack)){
				    	    									containerslimechest.decrStackSize(f1, 1);
				    	    							}
				    	    				}
					    	    			if(!item5.test(ItemStack.EMPTY)) {
					    	    								if(item5.test(invItemstack)){
					    	    									containerslimechest.decrStackSize(f1, 1);
					    	    							}
					    	    			}
						    	    		if(!item6.test(ItemStack.EMPTY)) {
						    	    								if(item6.test(invItemstack)){
						    	    									containerslimechest.decrStackSize(f1, 1);
						    	    							}
						    	    		}
							    	    	if(!item7.test(ItemStack.EMPTY)) {
							    	    								if(item7.test(invItemstack)){
							    	    									containerslimechest.decrStackSize(f1, 1);
							    	    							}
							    	    	}
								    	    if(!item8.test(ItemStack.EMPTY)) {
								    	    								if(item8.test(invItemstack)){
								    	    									containerslimechest.decrStackSize(f1, 1);
								    	    							}
								    	    }
									    	if(!item9.test(ItemStack.EMPTY)) {
									    	    								if(item9.test(invItemstack)){
									    	    									containerslimechest.decrStackSize(f1, 1);
									    	    							}
									    	}
	    	    							
	    	    			            }
	    	    						
	    	    						containerslimechest.addItem(recipe.getRecipeOutput());
	    								f = a;
	    							}
	    							}
	    							
	    			            }
	    						
	                		}
	                		
	                		
	                		
	                	}
	                }      
	            }
	        }
	}


	
	
	
    protected void initSlimeChest()
    {
        ContainerSlimeChest containerslimechest = this.slimeChest;
        this.slimeChest = new ContainerSlimeChest("SlimeChest", this.getInventorySize());
        this.slimeChest.setCustomName(this.getName());

        if (containerslimechest != null)
        {
            containerslimechest.removeInventoryChangeListener(this);
            int i = Math.min(containerslimechest.getSizeInventory(), this.slimeChest.getSizeInventory());

            for (int j = 0; j < i; ++j)
            {
                ItemStack itemstack = containerslimechest.getStackInSlot(j);
                
                

                if (!itemstack.isEmpty())
                {
                    this.slimeChest.setInventorySlotContents(j, itemstack.copy());
                }
            }
        }

        this.slimeChest.addInventoryChangeListener(this);
        this.updateSlimeSlots();
        this.itemHandler = new net.minecraftforge.items.wrapper.InvWrapper(this.slimeChest);
    }
    
    protected void addToSlimeChest(ItemStack floorItemStack) {
    	
    	ContainerSlimeChest containerslimechest = this.slimeChest;

        if (containerslimechest != null)
        {
            
            int i = Math.min(containerslimechest.getSizeInventory(), this.slimeChest.getSizeInventory());

            for (int j = 0; j < i; ++j)
            {
                ItemStack itemstack = containerslimechest.getStackInSlot(j);

                if(this.slimeChest.isItemValidForSlot(j, floorItemStack))
                {
                	j = i;
                		
                		//this.entityDropItem(itemstack, 0.0f);
                		//int itemCount = itemstack.getCount() + floorItemStack.getCount();
                	
                        this.slimeChest.addItem(floorItemStack);
                        //this.slimeChest.setInventorySlotContents(j, this.slimeChest.getStackInSlot(j));
                        //this.slimeChest.markDirty();
                		
                	
                    
                }
                
                
                
            }
        }

        
        this.updateSlimeSlots();
        //this.itemHandler = new net.minecraftforge.items.wrapper.InvWrapper(this.slimeChest);
    	
    }
    
    protected void dropSlimeChest()
    {
        ContainerSlimeChest containerslimechest = this.slimeChest;
        this.slimeChest = new ContainerSlimeChest("SlimeChest", this.getInventorySize());
        this.slimeChest.setCustomName(this.getName());

        if (containerslimechest != null)
        {
            
            int i = Math.min(containerslimechest.getSizeInventory(), this.slimeChest.getSizeInventory());

            for (int j = 0; j < i; ++j)
            {
                ItemStack itemstack = containerslimechest.getStackInSlot(j);

                if (!itemstack.isEmpty())
                {
                    this.entityDropItem(itemstack, 0.0f);
                    this.slimeChest.removeStackFromSlot(j);
                    //this.slimeChest.setInventorySlotContents(j, this.slimeChest.getStackInSlot(j));
                    //this.slimeChest.markDirty();
                }
            }
        }

        
        this.updateSlimeSlots();
        this.itemHandler = new net.minecraftforge.items.wrapper.InvWrapper(this.slimeChest);
    }
	
    ///////////add check for backpack accessory
    protected void updateSlimeSlots()
    {
        if (!this.world.isRemote)
        {
            this.setChested(this.getACBack().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEBACKPACK));
            this.setFurnace(this.getACLSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEOFF) || this.getACRSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEOFF) || this.getACLSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON) || this.getACRSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON));
            this.setCollector(this.getACLSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEGRATE) || this.getACRSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEGRATE) || this.getACFront().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEGRATE));
            this.setCrafter(this.getACTop().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMETOPHAT));
            this.setSpout(this.getACLSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMETAP) || this.getACRSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMETAP));
            //this.setVent(this.getACLSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEOFF) || this.getACRSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEOFF) || this.getACLSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON) || this.getACRSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON));
            //this.setBreeder(this.getACLSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEOFF) || this.getACRSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEOFF) || this.getACLSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON) || this.getACRSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEFURNACEON));
            this.setBrewing(this.getACLSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEBREWINGTANK) || this.getACRSide().getItem() == Item.getItemFromBlock(SlimeBreederBlocks.SLIMEBREWINGTANK));

        }
    }
    
    public boolean hasChest()
    {
        return ((Boolean)this.dataManager.get(DATA_ID_CHEST)).booleanValue();
    }
    
    public boolean hasFurnace()
    {
        return ((Boolean)this.dataManager.get(FURNACE)).booleanValue();
    }
    
    public boolean hasBrewingTank()
    {
        return ((Boolean)this.dataManager.get(BREWING)).booleanValue();
    }
    
    public boolean hasCollector()
    {
        return ((Boolean)this.dataManager.get(COLLECTOR)).booleanValue();
    }
    
    public boolean hasCrafter()
    {
        return ((Boolean)this.dataManager.get(CRAFTER)).booleanValue();
    }
    
    public boolean hasSpout()
    {
        return ((Boolean)this.dataManager.get(SPOUT)).booleanValue();
    }
    
    public boolean hasVent()
    {
        return ((Boolean)this.dataManager.get(VENT)).booleanValue();
    }
    
    public boolean hasBreeder()
    {
        return ((Boolean)this.dataManager.get(BREEDER)).booleanValue();
    }

    public int getLiquidVolume()
    {
        return (this.dataManager.get(COREFLUID)).getInteger("Amount");
    }
    
    
    public ItemStack getPotion()
    {
    	NBTTagList potionList = (this.dataManager.get(COREFLUID)).getTagList("POTION", 10).copy();
    	NBTTagCompound StoredPotion = potionList.getCompoundTagAt(0).copy();

        return new ItemStack(StoredPotion);
    }
    
    public ItemStack getMasterPotion()
    {
    	NBTTagList potionList = (this.dataManager.get(MASTERCOREFLUID)).getTagList("POTION", 10).copy();
    	NBTTagCompound StoredPotion = potionList.getCompoundTagAt(0).copy();

        return new ItemStack(StoredPotion);
    }
    
    
    public void setChested(boolean chested)
    {
        this.dataManager.set(DATA_ID_CHEST, Boolean.valueOf(chested));
    }
    
    public void setFurnace(boolean furnace)
    {
        this.dataManager.set(FURNACE, Boolean.valueOf(furnace));
       
    }
    
    public void setCollector(boolean collector)
    {
        this.dataManager.set(COLLECTOR, Boolean.valueOf(collector));
       
    }
    
    public void setCrafter(boolean crafter)
    {
        this.dataManager.set(CRAFTER, Boolean.valueOf(crafter));
       
    }
    
    public void setSpout(boolean spout)
    {
        this.dataManager.set(SPOUT, Boolean.valueOf(spout));
       
    }
    
    public void setVent(boolean vent)
    {
        this.dataManager.set(VENT, Boolean.valueOf(vent));
       
    }
    
    public void setBreeder(boolean breeder)
    {
        this.dataManager.set(BREEDER, Boolean.valueOf(breeder));
       
    }
    
    public void setBrewing(boolean furnace)
    {
        this.dataManager.set(BREWING, Boolean.valueOf(furnace));
       
    }
    
    public void setLiquidVolume(int volume)
    {
    	NBTTagCompound newVolume = this.getCoreFluid().copy();
    	newVolume.setInteger("Amount", volume);
    	this.setCoreFluid(newVolume);
       // (this.dataManager.get(COREFLUID)).setInteger("Amount", volume);
       
    }

    protected int getInventorySize()
    {
        return 27;
    }

    protected void playChestEquipSound()
    {
        this.playSound(SoundEvents.ENTITY_DONKEY_CHEST, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
    }

    public int getInventoryColumns()
    {
        return 9;
    }
    
    public void openGUI(EntityPlayer playerEntity, World worldIn)
    {
        if (!this.world.isRemote && this.hasChest())
        {
        	this.initSlimeChest();
            this.slimeChest.setCustomName(this.getName());
            Minecraft mc = Minecraft.getMinecraft();
            /*playerEntity.openGui(
	        		
	        		SlimeBreeder.INSTANCE,
	        		SlimeBreeder.GUI_ENUM.BACKPACK.ordinal(),
	        		worldIn, (int) 0, (int) 0, (int) 0
					
					);*/
            //mc.displayGuiScreen(new GuiChest(this.slimeChest, playerEntity.inventory));
            playerEntity.displayGUIChest(this.slimeChest);
        }
    }
    

}