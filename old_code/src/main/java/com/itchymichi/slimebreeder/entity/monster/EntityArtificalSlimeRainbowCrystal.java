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
import com.itchymichi.slimebreeder.lists.EnumCraftingIngredients;
import com.itchymichi.slimebreeder.lists.EnumEdibleItems;
import com.itchymichi.slimebreeder.lists.EnumItemColor;
import com.itchymichi.slimebreeder.lists.EnumRecipes;
import com.itchymichi.slimebreeder.lists.EnumTune;
import com.itchymichi.slimebreeder.lists.SlimeColor;
import com.itchymichi.slimebreeder.lists.SlimeItems;
import com.itchymichi.slimebreeder.lists.SlimeRecipes;

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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry.EntityRegistration;
import net.minecraftforge.items.ItemHandlerHelper;


public class EntityArtificalSlimeRainbowCrystal extends EntityArtificalSlimeBase
{
	
	//public static final DataParameter<NBTTagCompound> COMPRESSED = EntityDataManager.<NBTTagCompound>createKey(EntityArtificalSlimeRainbowCrystal.class, DataSerializers.COMPOUND_TAG);
	private static final DataParameter<String> COMPRESSEDNAME = EntityDataManager.<String>createKey(EntityArtificalSlimeRainbowCrystal.class, DataSerializers.STRING);
	private static final DataParameter<Integer> COMPRESSEDVALUE = EntityDataManager.<Integer>createKey(EntityArtificalSlimeRainbowCrystal.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> COMPRESSEDTRANSFORM = EntityDataManager.<Boolean>createKey(EntityArtificalSlimeRainbowCrystal.class, DataSerializers.BOOLEAN);
	//private static final DataParameter<Integer> TEMPERATURE = EntityDataManager.<Integer>createKey(EntityArtificalSlimeRainbowCrystal.class, DataSerializers.VARINT);
	//private static final DataParameter<NBTTagCompound> COREFLUID = EntityDataManager.<NBTTagCompound>createKey(EntityArtificalSlimeRainbowCrystal.class, DataSerializers.COMPOUND_TAG);
	 //Entity compressed = EntityList.createEntityByIDFromName(new ResourceLocation(this.getCompressedType()), this.getEntityWorld());

	
	//NBTTagCompound compressedTag;
	String compressedString;
	int compressedNumber;
	
	
	//private NBTTagCompound CoreFluid;
	//private int temperature;

	public EntityArtificalSlimeRainbowCrystal(World worldIn) {
		super(worldIn);
        this.moveHelper = new CustomSlimeMoveHelper(this);
		
	}

	
	 protected void entityInit()
	{
		 
		super.entityInit(); 
		
		/*EntitySlime slimeStart = new EntitySlime(this.getEntityWorld());
	    NBTTagCompound entityTagstart = slimeStart.serializeNBT();
	    	
	    entityTagstart.setInteger("Size", 1);*/
	    	
	    	//System.out.println(entityTagstart);
	    //this.compressedTag = entityTagstart;	
	   //this.dataManager.register(COMPRESSED, this.compressedTag);
		
		this.compressedString = "Slime";
		this.compressedNumber = 0;
		
	    this.dataManager.register(COMPRESSEDNAME, this.compressedString);
	    this.dataManager.register(COMPRESSEDVALUE, this.compressedNumber);
	    this.dataManager.register(COMPRESSEDTRANSFORM, false);
	    //this.dataManager.register(TEMPERATURE, Integer.valueOf(25));
	   //this.dataManager.register(COREFLUID, (this.CoreFluid));
	    
	    System.out.println("Slime Health  = " + this.health);

	}
	 
	 public void initWildSlime(int varient, int wave, float saturation, float brightness, int breedingspeed, double movespeed, double health, int attackdmg, int sticky)
	    {

	    	
	        this.varient = varient;
	        this.dataManager.set(VARIENT, Integer.valueOf(varient));
	        
	        
	        this.breedingspeed = breedingspeed;
			this.movespeed = movespeed;
			this.health = health;
			this.attackdmg = attackdmg;
			this.sticky = sticky;
	        

	        this.breedinggroup = 0;
	        this.domesticated = 0;
	        

	        
	        //this.targetTasks.addTask(1, new EntityAIFindNearestBreeder(this, this.domesticated));
	        //this.targetTasks.addTask(1, new EntityAIFindNearestOwnaer(this, this.domesticated));


	        this.setHunger(this.breedingspeed/2);
	        
	        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.health);
	        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1*this.movespeed);
	        this.setHealth(this.getMaxHealth());
	        
	       
	        
	    }
	 
	 /*public void setTemperature(int newTemp)
	    {
	    	this.dataManager.set(TEMPERATURE, Integer.valueOf(newTemp));
	    	this.temperature = newTemp;
	    }
	    
	    public int getTemperature()
	    {
	    	return ((Integer)this.dataManager.get(TEMPERATURE)).intValue();
	    }
	 
	    
		public NBTTagCompound getCoreFluid() {
			// TODO Auto-generated method stub
			return this.CoreFluid;
		}
		
		public NBTTagCompound getServerCoreFluid() {
			// TODO Auto-generated method stub
			return this.dataManager.get(COREFLUID);
			
		}*/
	    
	    
	 public void onUpdate()
	    {
	    super.onUpdate();
	    //Entity compressed = EntityList.createEntityFromNBT(this.getDataManager().get(COMPRESSED), this.getEntityWorld());
	    
	    this.setCustomSize((float)(this.getCompressedEntity(this.getDataManager().get(COMPRESSEDNAME)).width), (float)(this.getCompressedEntity(this.getDataManager().get(COMPRESSEDNAME)).height));
	    
	    if(this.getActivePotionEffect(SlimeBreeder.proxy.slimeTransform) != null) {
	    	this.getDataManager().set(COMPRESSEDNAME, "Slime");
	    	this.getDataManager().set(COMPRESSEDTRANSFORM, true);
	    	
	    	
	    	if((this.ticksExisted)/4 % 2 == 0){
		        this.playSound(SoundEvents.BLOCK_SAND_BREAK, 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);

	    	}
	    	if((this.ticksExisted)/6 % 2 == 0){
		        this.playSound(SoundEvents.BLOCK_NOTE_CHIME, 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
		        
		        if(!world.isRemote) 
		     	{
		     	WorldServer worldserver = (WorldServer) world;
		     	//worldserver.spawnParticle(EnumParticleTypes.HEART, false, target.posX + 0.5D, target.posY+ 1.0D, target.posZ+ 0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 
		     	
		     	for (int j = 0; j < 2 * 8; ++j)
		        {
		     		
		            float f = this.rand.nextFloat() * ((float)Math.PI * 2F);
		            float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
		            float f2 = MathHelper.sin(f) * (float)2 * 0.5F * f1;
		            float f3 = MathHelper.cos(f) * (float)2 * 0.5F * f1;
		            EnumParticleTypes enumparticletypes = this.getParticleType();
		            double d0 = this.posX + (double)f2;
		            double d1 = this.posZ + (double)f3;
		           // world.spawnParticle(enumparticletypes, d0, this.getEntityBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D, new int[0]);
		            
		            
		            //worldserver.spawnParticle(EnumParticleTypes.HEART, false, target.posX + target.getEntityBoundingBox().minY, d1, ((double)1F), ((double)1F), ((double)0.89F), new int[0]);
		         	//worldserver.spawnParticle(EnumParticleTypes.HEART, false, target.posX + 0.5D, target.posY+ 1.0D, target.posZ+ 0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 
		            worldserver.spawnParticle(EnumParticleTypes.SLIME, false, d0, this.posY+ 1.0D, d1, 1, 0.0D, 0.5D, 0.0D, 2.0D, new int[0]); 
		            //worldserver.spawnParticle(EnumParticleTypes.NOTE, false, d0, this.posY+ 1.0D, d1, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 

		           }
		     	
		     	}
	    	}

	    	//this.prevSquishFactor = (this.squishFactor + (float)(this.ticksExisted)/1000);
	    }
	    
	    if(this.getActivePotionEffect(SlimeBreeder.proxy.slimeTransformComplete) != null) {
	    	
	    	
	    	this.setCompressedType(compressedString);
	    	this.getDataManager().set(COMPRESSEDTRANSFORM, false);
	    	//this.setCompressedType(this.getEntityData().getString("compressedName"));
	    	
	    	if((this.getActivePotionEffect(SlimeBreeder.proxy.slimeTransformComplete).getDuration() < 2)){
		        this.playSound(SoundEvents.BLOCK_NOTE_CHIME, 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
	    	if(!world.isRemote) 
	     	{
	     	WorldServer worldserver = (WorldServer) world;
	     	//worldserver.spawnParticle(EnumParticleTypes.HEART, false, target.posX + 0.5D, target.posY+ 1.0D, target.posZ+ 0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 
	     	
	     	for (int j = 0; j < 2 * 8; ++j)
	        {
	     		
	            float f = this.rand.nextFloat() * ((float)Math.PI * 2F);
	            float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
	            float f2 = MathHelper.sin(f) * (float)2 * 0.5F * f1;
	            float f3 = MathHelper.cos(f) * (float)2 * 0.5F * f1;
	            EnumParticleTypes enumparticletypes = this.getParticleType();
	            double d0 = this.posX + (double)f2;
	            double d1 = this.posZ + (double)f3;
	           // world.spawnParticle(enumparticletypes, d0, this.getEntityBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D, new int[0]);
	            
	            
	            //worldserver.spawnParticle(EnumParticleTypes.HEART, false, target.posX + target.getEntityBoundingBox().minY, d1, ((double)1F), ((double)1F), ((double)0.89F), new int[0]);
	         	//worldserver.spawnParticle(EnumParticleTypes.HEART, false, target.posX + 0.5D, target.posY+ 1.0D, target.posZ+ 0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 
	            worldserver.spawnParticle(EnumParticleTypes.NOTE, false, d0, this.posY+ 1.0D, d1, 1, 0.0D, 0.5D, 0.0D, 2.0D, new int[0]); 
	            //worldserver.spawnParticle(EnumParticleTypes.NOTE, false, d0, this.posY+ 1.0D, d1, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 

	           }
	     	
	     	}
	    }
	    }
	    
	    }
	 
	 protected void initEntityAI()
	    {
		 
		 super.initEntityAI();
		 //System.out.println("AI init");
		 
		 //Entity compressed = EntityList.createEntityFromNBT(this.getDataManager().get(COMPRESSED), this.getEntityWorld());
		 //Entity compressedServer = EntityList.createEntityByIDFromName(new ResourceLocation(this.getDataManager().get(COMPRESSEDNAME)), this.getEntityWorld());

		 this.targetTasks.addTask(3, new EntityAIFindNearestCompressed(this, (Class<? extends EntityLivingBase>) this.getCompressedEntity(this.getDataManager().get(COMPRESSEDNAME)).getClass()));
		 
	    }
	 
	 
	 public void writeEntityToNBT(NBTTagCompound tagCompound)
	    {
	    	super.writeEntityToNBT(tagCompound);
	    	
	    	/*NBTTagList complist = new NBTTagList();
	    	
	    	complist.appendTag(this.getCompressedType());
	    	complist.set(0, this.getCompressedType());
	    	
	    	tagCompound.setTag("compressedList", complist);
	    	tagCompound.setInteger("compression", this.compression);*/
	    	
	    	//tagCompound.setInteger("Temperature", this.getTemperature());
	    	
	    	//tagCompound.setTag("corefluid", this.getCoreFluid());
	    	//this.dataManager.set(COREFLUID, tagCompound.getCompoundTag(("corefluid")));
	    	
	    	tagCompound.setString("compressedName", this.compressedString);
	    	this.dataManager.set(COMPRESSEDNAME, tagCompound.getString("compressedName"));
	    	
	    	System.out.println("write tag name = " + tagCompound.getString("compressedName"));
	    	System.out.println("write datamanger name = " + this.dataManager.get(COMPRESSEDNAME));
	    	
	    	tagCompound.setInteger("compressedValue", this.compressedNumber);
	    	this.dataManager.set(COMPRESSEDVALUE, tagCompound.getInteger("compressedValue"));
	    	
	    }
	 
	 
	 
	 public void readEntityFromNBT(NBTTagCompound tagCompound)
	   {
	       super.readEntityFromNBT(tagCompound);
	    
	       /*NBTTagList compressedTypeList = tagCompound.getTagList("compressedList", Constants.NBT.TAG_COMPOUND);
	      	System.out.println("Tag List = " + compressedTypeList);
	      	
	      	if(compressedTypeList.hasNoTags()) {
	      		
	      		compressedTypeList.appendTag(this.getCompressedType());
	      		compressedTypeList.set(0, this.getCompressedType());
	      		
	      		tagCompound.setTag("compressedList", compressedTypeList);
	      		//System.out.println("Appending Tag");
	      	}*/
	       
	       if(!(tagCompound.hasKey("compressedName"))){
	    	   System.out.println("name tag not found");
	    	   tagCompound.setString("compressedName", "Slime");
	       }
	      	
	      	if(!(tagCompound.hasKey("compressedValue")))
	      	{
	      		System.out.println("value tag not found");
	      		tagCompound.setInteger("compressionValue", 0);
	      	}
	      	
	      	this.compressedString = tagCompound.getString("compressedName");
	      	this.compressedNumber = tagCompound.getInteger("compressedValue");
	      	
	      //this.setTemperature(tagCompound.getInteger("Temperature"));
	    	//this.dataManager.set(TEMPERATURE, tagCompound.getInteger("Temperature"));
	    	
	    	//this.setCoreFluid(tagCompound.getCompoundTag(("corefluid")));
	    	//this.dataManager.set(COREFLUID, tagCompound.getCompoundTag(("corefluid")));
	      	
	      	
	      	
	    	this.setCompressedType(tagCompound.getString("compressedName"));
	    	this.setCompressedValue(tagCompound.getInteger("compressedValue"));
	    	
	    	//Entity compressed = EntityList.createEntityFromNBT(this.getDataManager().get(COMPRESSED), this.getEntityWorld());
	    	

	    	
	    	//System.out.println("Entity Compressed =" + this.getCompressedEntity(tagCompound.getString("compressedName")));
	    	
	    	//System.out.println("Size Y = " + (float)(this.getCompressedEntity(this.getDataManager().get(COMPRESSEDNAME)).height/this.getCompressedEntity(this.getDataManager().get(COMPRESSEDNAME)).width)); 
	    	
	    	//float sizeX = (float) (this.getCompressedEntity(this.getDataManager().get(COMPRESSEDNAME)).getEntityBoundingBox().maxX/this.getCompressedEntity(this.getDataManager().get(COMPRESSEDNAME)).getEntityBoundingBox().maxX);
			//float sizeY = (float) (this.getCompressedEntity(this.getDataManager().get(COMPRESSEDNAME)).getEntityBoundingBox().maxY/this.getCompressedEntity(this.getDataManager().get(COMPRESSEDNAME)).getEntityBoundingBox().maxX);
			 
			 this.setCustomSize((float)(this.getCompressedEntity(this.getDataManager().get(COMPRESSEDNAME)).width), (float)(this.getCompressedEntity(this.getDataManager().get(COMPRESSEDNAME)).height));
	    	
	    	//this.setCustomSize((float)(compressed.width/compressed.width), (float)(compressed.height/compressed.width));
			 
			 //this.compression = tagCompound.getInteger("compression");
	    	
	    	this.targetTasks.taskEntries.clear();
			 this.initEntityAI();
			 this.updateAITasks();
	    	
	      	//System.out.println("Tag List = " + compressedTypeList);
	      	//System.out.println("Taeg ind 0 = " + compressedTypeList.getCompoundTagAt(0));
	       
	   }
	 
	 
	 @Override 
		public boolean processInteract(EntityPlayer player, EnumHand hand)
		{
		   Entity zombie = new EntitySkeleton(this.getEntityWorld());
		   Entity slime = new EntitySlime(this.getEntityWorld());
		   
		   
		   
		   /*if(compressed instanceof EntityAgeable) {
		   ((EntityAgeable)compressed).processInteract(player, hand);}*/
		   
		   
		   ItemStack itemstack = player.getHeldItem(hand);
		   if(this.getCompressedEntity(this.getDataManager().get(COMPRESSEDNAME)).getClass() == EntityCow.class) {
	       if (itemstack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild())
	       {
	           player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
	           itemstack.shrink(1);

	           if (itemstack.isEmpty())
	           {
	               player.setHeldItem(hand, new ItemStack(Items.MILK_BUCKET));
	           }
	           else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET)))
	           {
	               player.dropItem(new ItemStack(Items.MILK_BUCKET), false);
	           }

	           return true;
	       }
		   
		   }
		   
		   
		   System.out.println("Before = " + this.getCompressedEntity(this.getDataManager().get(COMPRESSEDNAME)).getClass());
		  
		   
		   NBTTagCompound slimeSerial = slime.serializeNBT();
		   NBTTagCompound zombieSerial = zombie.serializeNBT();
		   
		   slimeSerial.setInteger("Size", 23);
		   
		   
		   
		   
		   
		   if(!this.isJumping) {
			   
			   System.out.println("not jumping");
			   
			   if(player.inventory.getCurrentItem().getItem().equals(SlimeBreederItems.SLIMETUNER)) {
				   System.out.println("tuner");
				   
				   ItemStack tuner = player.inventory.getCurrentItem();
				   
				   if(tuner.hasTagCompound() && tuner.getTagCompound().hasKey("tune") && this.getCompressedValue() == 0 && this.isServerWorld()) {
					   

						EnumTune.listOfObjects.clear();
				    	EnumTune.initTuneList();
				    	
				    	 int length = EnumTune.listOfObjects.size();
						 
						 for (int i = 0; i < length; i++) 
					    	{
							 if(tuner.getTagCompound().getInteger("tune") == EnumTune.listOfObjects.get(i).getTune()) {
								 
								 this.addPotionEffect(new PotionEffect(SlimeBreeder.proxy.slimeTransform, 100, 1));
								// Entity compressedEntity = ((EntityList.createEntityFromNBT((EnumTune.listOfObjects.get(i).getEntityEntry().newInstance(this.getEntityWorld()).serializeNBT()), this.getEntityWorld())));
								 Entity compressedEntity = ((EntityList.createEntityByIDFromName(new ResourceLocation((EnumTune.listOfObjects.get(i).getEntityName())), this.getEntityWorld())));
		
								 
								 System.out.println("compressed = " + compressedEntity);
								 
								 float sizeX = (float) (compressedEntity.getEntityBoundingBox().maxX/compressedEntity.getEntityBoundingBox().maxX);
								 float sizeY = (float) (compressedEntity.getEntityBoundingBox().maxY/compressedEntity.getEntityBoundingBox().maxX);
								 
								 this.setCustomSize((float)(this.getCompressedEntity(this.getDataManager().get(COMPRESSEDNAME)).width), (float)(this.getCompressedEntity(this.getDataManager().get(COMPRESSEDNAME)).height));

								 //this.setCompressedType((EnumTune.listOfObjects.get(i).getEntityEntry().newInstance(this.getEntityWorld())).serializeNBT());
								 this.setCompressedType((EnumTune.listOfObjects.get(i).getEntityName()));

								 System.out.println("Size X = "  + sizeX + " and "  + compressedEntity.width/compressedEntity.width + " SizeY = " + sizeY + " and " +  compressedEntity.height/compressedEntity.width );
								 
								 this.targetTasks.taskEntries.clear();
								 this.initEntityAI();
								 this.updateAITasks();

								 
							 			}
					    	}
				    	

				   }
				   

				   
			   }
		   
			   /*if(compressed.getClass() == zombie.getClass()){
				   
				   Entity slimeT = EntityList.createEntityFromNBT(slimeSerial, this.getEntityWorld());
				   float sizeX = (float) (slimeT.getEntityBoundingBox().maxX/slimeT.getEntityBoundingBox().maxX);
				   float sizeY = (float) (slimeT.getEntityBoundingBox().maxY/slimeT.getEntityBoundingBox().maxX);
				   
				   System.out.println("size X = "+ sizeX);
				   System.out.println("size Y = "+ sizeY);
				   
				   //this.setCustomSize((float)(slimeT.getEntityBoundingBox().maxX/slimeT.getEntityBoundingBox().maxX), (float)(slimeT.getEntityBoundingBox().maxY/slimeT.getEntityBoundingBox().maxX));
				   this.setCustomSize((float)(slimeT.width/slimeT.width), (float)(slimeT.height/slimeT.width));

				   this.setCompressedType(slimeSerial);
				   System.out.println(compressed);
				   
				   //this.setCustomSize((float)(slimeT.getEntityBoundingBox().maxX/slimeT.getEntityBoundingBox().maxX), (float)(slimeT.getEntityBoundingBox().maxY/slimeT.getEntityBoundingBox().maxX));
				   //this.setCustomSize(slimeT.width, slimeT.height);
				   //this.setSize(slimeT.width, slimeT.height);
				   //this.setCustomSize(slimeT.width, slimeT.height);
				   
				   System.out.println("Slime Width = " + slimeT.width);
				   System.out.println("Slime Height = " + slimeT.height);
				   
				   this.targetTasks.taskEntries.clear();
				   this.initEntityAI();
				   this.updateAITasks();


			   }
			   
			   if(compressed.getClass() == slime.getClass()){
				   Entity zombieT = EntityList.createEntityFromNBT(zombieSerial, this.getEntityWorld());
				   
				   this.setCustomSize((float)(zombieT.width/zombieT.width), (float)(zombieT.height/zombieT.width));
				   
				   this.setCompressedType(zombieSerial);
				   System.out.println(compressed);
				   //this.setCustomSize((float)(zombieT.getEntityBoundingBox().maxX/zombieT.getEntityBoundingBox().maxX), (float)(zombieT.getEntityBoundingBox().maxY/zombieT.getEntityBoundingBox().maxX));
				 

				   
				   //this.setSize(zombieT.width, zombieT.height);
				   
				   System.out.println("Zomb Width = " + (zombieT.getEntityBoundingBox().maxX/zombieT.getEntityBoundingBox().maxX));
				   System.out.println("Zomb Height = " + (zombieT.getEntityBoundingBox().maxY/zombieT.getEntityBoundingBox().maxX));
				   
				   this.targetTasks.taskEntries.clear();
				   this.initEntityAI();
				   this.updateAITasks();
				   
				   
				   //this.setSlimeSize(1);
				   
		
			   }*/
			   
		   }
		   
		   
		   
			return true;
			
		}
	 
	 
	 public void applyEntityCollision(Entity entityIn)
	    {
	        super.applyEntityCollision(entityIn);
	        
	        
	        EnumTune.listOfObjects.clear();
	    	EnumTune.initTuneList();
	    	
	    	 int length = EnumTune.listOfObjects.size();
	    	 	    	 
			 
			 for (int i = 0; i < length; i++) 
		    	{
				
				 
	        if((entityIn.getName().equals((EnumTune.listOfObjects.get(i).getEntityName())) && (!entityIn.hasCustomName()) && entityIn.getName().equals(this.getCompressedType()))){
	        	
	        	slimeSplash(entityIn);
	        	entityIn.getEntityWorld().removeEntity(entityIn);
	        	
	        	int newCompression = this.getCompressedValue();
	        	System.out.println(newCompression);
	        	newCompression++;
	        	this.setCompressedValue(newCompression);
	        	System.out.println(this.getCompressedValue());
	        	

	        	
	        	
	        	
	        }
		    	}
	        //System.out.println("collide");
	    	}
	 

	 public void doJump() {
		 super.doJump();
		
		 if(this.getTimer() > 590) {
			 this.setTimer(0);
		 }
		 
		 this.setTimer(this.getTimer()+1);
		 
		 
	 }
	 
	 public void setCustomSize(float width, float height)
	    {
	        if (width != this.width || height != this.height)
	        {
	            float f = this.width;
	            this.width = width;
	            this.height = height;

	            if (this.width < f)
	            {
	                double d0 = (double)width / 2.0D;
	                this.setEntityBoundingBox(new AxisAlignedBB(this.posX - d0, this.posY, this.posZ - d0, this.posX + d0, this.posY + (double)this.height, this.posZ + d0));
	                return;
	            }

	            AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
	            this.setEntityBoundingBox(new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double)this.width, axisalignedbb.minY + (double)this.height, axisalignedbb.minZ + (double)this.width));

	            if (this.width > f && !this.firstUpdate && !this.world.isRemote)
	            {
	                this.move(MoverType.SELF, (double)(f - this.width), 0.0D, (double)(f - this.width));
	            }
	        }
	    }
	 
	 public void slimeSplash(Entity target) {
		 if(!world.isRemote) 
     	{
     	WorldServer worldserver = (WorldServer) world;
     	//worldserver.spawnParticle(EnumParticleTypes.HEART, false, target.posX + 0.5D, target.posY+ 1.0D, target.posZ+ 0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 
     	
     	for (int j = 0; j < 2 * 8; ++j)
        {
     		
            float f = this.rand.nextFloat() * ((float)Math.PI * 2F);
            float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
            float f2 = MathHelper.sin(f) * (float)2 * 0.5F * f1;
            float f3 = MathHelper.cos(f) * (float)2 * 0.5F * f1;
            EnumParticleTypes enumparticletypes = this.getParticleType();
            double d0 = target.posX + (double)f2;
            double d1 = target.posZ + (double)f3;
           // world.spawnParticle(enumparticletypes, d0, this.getEntityBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D, new int[0]);
            
            
            //worldserver.spawnParticle(EnumParticleTypes.HEART, false, target.posX + target.getEntityBoundingBox().minY, d1, ((double)1F), ((double)1F), ((double)0.89F), new int[0]);
         	//worldserver.spawnParticle(EnumParticleTypes.HEART, false, target.posX + 0.5D, target.posY+ 1.0D, target.posZ+ 0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 
            worldserver.spawnParticle(EnumParticleTypes.SLIME, false, d0, target.posY+ 1.0D, d1, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 
           }
     	
     	}

         float volume = 0.9F * 1.0F;
         
         this.playSound(SoundEvents.ENTITY_SLIME_SQUISH, volume, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
     }
	 
	 
		@Override
		protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
	    {
	        ResourceLocation resourcelocation = this.getLootTable();
	        
	        int compressed = this.getCompressedValue();

	        NBTTagCompound slimeItem = this.serializeNBT();
	        
	        ItemStack slimeBlock = new ItemStack(Item.getItemFromBlock(SlimeBreederBlocks.RAINBOWARTIFICALSLIMEBLOCK));
	        
	        
	        NBTTagCompound nbt = new NBTTagCompound();
	        
	        
	        
	        NBTTagList nbttaglist = new NBTTagList();
	    	
	    	nbttaglist.appendTag(slimeItem);
	    	nbttaglist.set(0, slimeItem);
	         
	    	nbt.setTag("slimes", nbttaglist);
	    	
	    	slimeBlock.setTagCompound(nbt);
	    	slimeBlock.setStackDisplayName( this.getCompressedType() + "-Type Rainbow Slime");

	    	
	    	
	    	System.out.println("NBT = " + slimeBlock);
	    	
	    	this.entityDropItem(slimeBlock, 0.0F);
	        

	        if (resourcelocation != null)
	        {
	            LootTable loottable = this.world.getLootTableManager().getLootTableFromLocation(resourcelocation);
	            resourcelocation = null;
	            
	            for (int i = 0; i < compressed; i++) 
	        	{
		            LootContext.Builder lootcontext$builder = (new LootContext.Builder((WorldServer)this.world)).withLootedEntity(this).withDamageSource(source);
		
		            if (wasRecentlyHit && this.attackingPlayer != null)
		            {
		                lootcontext$builder = lootcontext$builder.withPlayer(this.attackingPlayer).withLuck(this.attackingPlayer.getLuck());
		            }
		
		            for (ItemStack itemstack : loottable.generateLootForPools(this.rand, lootcontext$builder.build()))
		            {
		                this.entityDropItem(itemstack, 0.0F);
		            }
		
		            this.dropEquipment(wasRecentlyHit, lootingModifier);
	        	}
	        
	        }
	        else
	        {
	            super.dropLoot(wasRecentlyHit, lootingModifier, source);
	        }
	    }
		
		
	    @Override
	    protected ResourceLocation getLootTable()
	    {
	 	   LootTableManager loottablemanager = new LootTableManager((File)null);
	 	  
	 	   ResourceLocation lootResource; 
	       
	 	   lootResource = new ResourceLocation(EntityRegistry.getEntry(this.getCompressedEntity(this.getCompressedType()).getClass()).getRegistryName().getResourceDomain() + ":entities/"+ this.getCompressedEntity(this.getCompressedType()).getName());
	 	   
	 	   LootTable lootList = loottablemanager.getLootTableFromLocation(lootResource);
	    	
	        return lootResource;
	    }
	 
	 
	 ///////////////////////////////////Setters and Getters/////////////////////////////////////////
	 
	 public String getCompressedType() {
	    	return this.dataManager.get(COMPRESSEDNAME);
	    }
	 
	 public Boolean getTransform() {
	    	return this.dataManager.get(COMPRESSEDTRANSFORM);
	    }
	    
	    public void setCompressedType(String entityName) {

	    	this.dataManager.set(COMPRESSEDNAME, entityName);
	    	this.compressedString = entityName;
	    }
	    
		 public Entity getCompressedEntity(String name) {
			 Entity compressedEntity = EntityList.createEntityByIDFromName(new ResourceLocation(name), this.getEntityWorld());
			 
			NBTTagCompound compressed = compressedEntity.serializeNBT();
			
			NBTTagCompound crystalSlime = this.getEntityData();
			
			Set<String> keyList = crystalSlime.getKeySet();
			
			compressed.getKeySet().addAll(keyList);
			
			if(compressedEntity.getClass() == EntitySlime.class) {
				compressed.setInteger("Size", 1);
			}
			

			 
		    	return EntityList.createEntityFromNBT(compressed, this.getEntityWorld());
		    }
	    
	    public Integer getCompressedValue() {
	    	
	    	return this.dataManager.get(COMPRESSEDVALUE);
	    }
	    
	    public void setCompressedValue(Integer multiplyer) {

	    	this.dataManager.set(COMPRESSEDVALUE, multiplyer);
	    	this.compressedNumber = multiplyer;
	    }
	    
		/*public void setCoreFluid(NBTTagCompound fluid) {
			this.CoreFluid = fluid;
			this.dataManager.set(COREFLUID, (this.CoreFluid));
			
		}*/
	    
	 
	 
}