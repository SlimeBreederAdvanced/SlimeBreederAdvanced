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
import net.minecraft.inventory.EntityEquipmentSlot;
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
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry.EntityRegistration;
import net.minecraftforge.items.ItemHandlerHelper;
import scala.reflect.internal.Trees.This;


public class EntityArtificalSlimeCrystalBackup extends EntityArtificalSlimeBase
{
	
	protected static final DataParameter<Integer> ARGB = EntityDataManager.<Integer>createKey(EntityArtificalSlimeCrystalBackup.class, DataSerializers.VARINT);
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
	

	
	 private static final DataParameter<Boolean> FRONTOBS = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystalBackup.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> BACKOBS = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystalBackup.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> LSIDEOBS = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystalBackup.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> RSIDEOBS = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystalBackup.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> TOPOBS = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystalBackup.class, DataSerializers.BOOLEAN);
	 private static final DataParameter<Boolean> ISPLAYERNEAR = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeCrystalBackup.class, DataSerializers.BOOLEAN);

	 private static final DataParameter<ItemStack> ACTOP = EntityDataManager.<ItemStack>createKey(EntityArtificalSlimeCrystalBackup.class, DataSerializers.ITEM_STACK);
	 private static final DataParameter<ItemStack> ACLSIDE = EntityDataManager.<ItemStack>createKey(EntityArtificalSlimeCrystalBackup.class, DataSerializers.ITEM_STACK);
	 private static final DataParameter<ItemStack> ACRSIDE = EntityDataManager.<ItemStack>createKey(EntityArtificalSlimeCrystalBackup.class, DataSerializers.ITEM_STACK);
	 private static final DataParameter<ItemStack> ACBACK = EntityDataManager.<ItemStack>createKey(EntityArtificalSlimeCrystalBackup.class, DataSerializers.ITEM_STACK);
	 private static final DataParameter<ItemStack> ACFRONT = EntityDataManager.<ItemStack>createKey(EntityArtificalSlimeCrystalBackup.class, DataSerializers.ITEM_STACK);
	
	
	
	 
	 public EntityArtificalSlimeCrystalBackup(World worldIn) {
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

	        
	        this.dataManager.register(ACTOP, ItemStack.EMPTY);
	        this.dataManager.register(ACLSIDE, ItemStack.EMPTY);
	        this.dataManager.register(ACRSIDE, ItemStack.EMPTY);
	        this.dataManager.register(ACBACK, ItemStack.EMPTY);
	        this.dataManager.register(ACFRONT, ItemStack.EMPTY);
	        


		   
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
   	   



    	
    	this.targetTasks.taskEntries.clear();
		 this.initEntityAI();
		 this.updateAITasks();
       
   }
 
 public void doJump() {
	 super.doJump();
	 
	
	
	 if(this.getTimer() > 590) {
		 this.setTimer(0);
	 }
	 
	 this.setTimer(this.getTimer()+1);
	 
	 
	 
 }
 
 public void onUpdate(){
	 super.onUpdate();
	 slimeSelection();
	 
	 /*if(this.getIsPlayerNear()) {
		 
		 this.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2, 30));
		 
	 }*/
	
	 
	 //System.out.println(this.getLSideObs());
	 //System.out.println(this.getACFront());
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
				    	System.out.println("Player Near");
				    	
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
    						
    					}else {
    						this.setFrontObs(false);
    					}
    					
    					if(lengthToPlayerFront+SlimeLeftlengthToPlayerFront < (1.20) && lengthToPlayerFront+SlimeLeftlengthToPlayerFront > (0.90) && !this.getLSideObs() && !this.getRSideObs() && !this.getTopObs() && !player.isSneaking() && tag.getBoolean("back") || lengthToPlayerFront+SlimeLeftlengthToPlayerFront > (1.20) && !this.getLSideObs() && !this.getRSideObs() && !this.getTopObs() && !player.isSneaking()&& tag.getBoolean("back")){
    						//System.out.println("Looking at Back");
    						//System.out.println("Looking at Front, Front = " + lengthToFront + "			Looking at Right, Right = " + lengthToRSide );
    						this.setisPlayerNear(true);
    						this.setBackObs(true);
    						
    					}else {
    						this.setBackObs(false);
    					}
    					
    					if(lengthToPlayerFront+SlimeLeftlengthToPlayerFront > (-0.75) && lengthToPlayerFront+SlimeLeftlengthToPlayerFront < (-0.10) && !this.getFrontObs() && !this.getBackObs() && !this.getTopObs() && SlimeLeftlengthToPlayerFront < 0 && !player.isSneaking() && tag.getBoolean("rside")){
    						//System.out.println("Looking at rside , " + lengthToPlayerFront + " . " + SlimeLeftlengthToPlayerFront  + " = " + (lengthToPlayerFront+SlimeLeftlengthToPlayerFront) );
    						//System.out.println("Looking at Front, Front = " + lengthToFront + "			Looking at Right, Right = " + lengthToRSide );
    						this.setisPlayerNear(true);
    						this.setRSideObs(true);
    						
    					}else {
    						this.setRSideObs(false);
    					}
    					
    					if(lengthToPlayerFront+SlimeLeftlengthToPlayerFront < (0.75) && lengthToPlayerFront+SlimeLeftlengthToPlayerFront > (0.10) && !this.getFrontObs() && !this.getBackObs() && !this.getTopObs() && SlimeLeftlengthToPlayerFront > 0 && !player.isSneaking() && tag.getBoolean("lside")){
    						//System.out.println("Looking at lside, " + lengthToPlayerFront + " . " + SlimeRightlengthToPlayerFront + " = " + (lengthToPlayerFront+SlimeRightlengthToPlayerFront) );
    						//System.out.println("Looking at Front, Front = " + lengthToFront + "			Looking at Right, Right = " + lengthToRSide );
    						this.setisPlayerNear(true);
    						this.setLSideObs(true);
    						
    					}else {
    						this.setLSideObs(false);
    					}
    					
    					if(player.isSneaking() && tag.getBoolean("top")) {
    						
    						this.setisPlayerNear(true);
    						 this.setTopObs(true);
    						 
    						
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
 
	 
	 if(!(itemstack.isEmpty()))
 	{
 	if(itemstack.getItem() != SlimeBreederItems.SLIMALYSER)
	    	{
 		player.swingArm(hand);
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
    				 this.setServerACFront(itemstack.copy());
    				 this.setACFront(itemstack.copy());
    				 
    				 //this.setACFront(new ItemStack(itemstack.getItem()));
    				 //this.dataManager.set(ACFRONT, new ItemStack(itemstack.getItem()));
    				 
    				 //this.ACSlotFront = itemstack.serializeNBT();
    				
    				 System.out.println("Set Front " + itemstack + " into " + this.dataManager.get(ACFRONT));
    				 player.inventory.clearMatchingItems(itemstack.getItem(), -1, 1, null);
    			 }
    			 
    	
    			 
    			 if(this.getBackObs()&& tag.getBoolean("back")){
    				 this.setServerACBack(itemstack.copy());
    				 this.setACBack(itemstack.copy());
    				 
    				 
    				 
    				 System.out.println("Set Back");
    				 player.inventory.clearMatchingItems(itemstack.getItem(), -1, 1, null);
    			 }
    			 
    			 if(this.getLSideObs()&& tag.getBoolean("lside")){
    				 this.setServerACLSide(itemstack.copy());
    				 this.setACLSide(itemstack.copy());
    				 
    				 
    				 System.out.println("Set LSide");
    				 player.inventory.clearMatchingItems(itemstack.getItem(), -1, 1, null);
    			 }
    			 
    			 if(this.getRSideObs()&& tag.getBoolean("rside")){
    				 this.setServerACRSide(itemstack.copy());
    				 this.setACRSide(itemstack.copy());
    				 
    				 
    				 
    				 System.out.println("Set RSide");
    				 player.inventory.clearMatchingItems(itemstack.getItem(), -1, 1, null);
    			 }
    			 //if(this.getTopObs() && ((IHasModel)itemstack.getItem()).getTop()){
    			 if(this.getTopObs()&& tag.getBoolean("top")){
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
	


}