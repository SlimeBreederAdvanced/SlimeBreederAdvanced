package com.itchymichi.slimebreeder.entity.monster;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederEnchantments;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.SlimeBreederSounds;
import com.itchymichi.slimebreeder.entity.ai.EntityColourSlime.EntityAIFindNearestBreeder;
import com.itchymichi.slimebreeder.entity.ai.EntityColourSlime.EntityAIFindNearestBreeder2;
import com.itchymichi.slimebreeder.entity.ai.EntityColourSlime.EntityAIFindNearestFood;
import com.itchymichi.slimebreeder.entity.ai.EntityColourSlime.EntityAIFindNearestSlime;
import com.itchymichi.slimebreeder.handlers.SBASoundHandler;
import com.itchymichi.slimebreeder.items.FilledSlimeCapsule;
import com.itchymichi.slimebreeder.items.SlimeInfusedBoots;
import com.itchymichi.slimebreeder.items.SlimeInfusedChest;
import com.itchymichi.slimebreeder.items.SlimeInfusedHelm;
import com.itchymichi.slimebreeder.items.SlimeInfusedLeggings;
import com.itchymichi.slimebreeder.items.Slimepedia;
import com.itchymichi.slimebreeder.lists.EnumCraftingIngredients;
import com.itchymichi.slimebreeder.lists.EnumEdibleItems;
import com.itchymichi.slimebreeder.lists.EnumItemColor;
import com.itchymichi.slimebreeder.lists.EnumRecipes;
import com.itchymichi.slimebreeder.lists.SlimeColor;
import com.itchymichi.slimebreeder.lists.SlimeItems;
import com.itchymichi.slimebreeder.lists.SlimeRecipes;

import akka.util.Collections;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.entity.Render;
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
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySkeletonHorse;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
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
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.items.ItemHandlerHelper;


public class EntityArtificalSlimeGel2Backup extends EntityColorSlimeBase
{

	//EntitySlime slime = new EntitySlime(this.getEntityWorld());
	
	public int slimeAge;
	public float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;
    public boolean wasOnGround;
	int counter;
	private int wave;
	private float sat;
	private float brightness;
	private float Hue;
	
	private int breedinggroup;
	private int breedingspeed;
	private double movespeed;
	private double health;
	private int attackdmg;
	private int sticky;
	private int domesticated;
	
	
	private int interactTimer;
	private int varient;
	//private int temperature;
	
	private int capacity;
	float[] vs = {Hue, sat, brightness};
	private int rgb;
	
	private int hunger;
	
	
	private UUID owner;
	private UUID blankOwner;
	 
	 NBTTagCompound SlotSlime =  new ItemStack(Items.SLIME_BALL).serializeNBT();
	
	
	 protected static final DataParameter<Integer> SLIME_SIZE2 = EntityDataManager.<Integer>createKey(EntityArtificalSlimeGel2Backup.class, DataSerializers.VARINT);
	 private static final DataParameter<Integer> RGB = EntityDataManager.<Integer>createKey(EntityArtificalSlimeGel2Backup.class, DataSerializers.VARINT);
	 private static final DataParameter<Integer> VARIENT = EntityDataManager.<Integer>createKey(EntityArtificalSlimeGel2Backup.class, DataSerializers.VARINT);
	 private static final DataParameter<Integer> TIMER = EntityDataManager.<Integer>createKey(EntityArtificalSlimeGel2Backup.class, DataSerializers.VARINT);
	
	
	public static final DataParameter<NBTTagCompound> COMPRESSED = EntityDataManager.<NBTTagCompound>createKey(EntityArtificalSlimeGel2Backup.class, DataSerializers.COMPOUND_TAG);
	NBTTagCompound compressedTag;
	
	
	//public static final DataParameter<Integer> ENTITY = EntityDataManager.<Integer>createKey(EntityArtificalSlimeGel.class, DataSerializers.VARINT);


	 
	public EntityArtificalSlimeGel2Backup(World worldIn) {
		super(worldIn);

		// TODO Auto-generated constructor stub
	}
	
	 protected void entityInit()
	    {
	        super.entityInit();
	        
	        this.dataManager.register(SLIME_SIZE2, Integer.valueOf(2));
	        this.dataManager.register(RGB, Integer.valueOf(rgb));
	        this.dataManager.register(VARIENT, Integer.valueOf(varient));
	        this.dataManager.register(TIMER, Integer.valueOf(25));
	        
	        EntitySlime slimeStart = new EntitySlime(this.getEntityWorld());
	    	NBTTagCompound entityTagstart = slimeStart.serializeNBT();
	    	
	    	entityTagstart.setInteger("Size", 1);
	    	
	    	//System.out.println(entityTagstart);
	    this.compressedTag = entityTagstart;	
	    this.dataManager.register(COMPRESSED, this.compressedTag);
	   
	        
	    }
	
	 @Override
	 protected void initEntityAI()
	    {
		 	
	        this.tasks.addTask(1, new EntityArtificalSlimeGel2Backup.AISlimeFloat(this));
	        this.tasks.addTask(2, new EntityArtificalSlimeGel2Backup.AISlimeAttack(this));
	        //this.tasks.addTask(2, new EntitySlime2.AISlimeBreed(this));
	        this.tasks.addTask(3, new EntityArtificalSlimeGel2Backup.AISlimeFaceRandom(this));
	        this.tasks.addTask(5, new EntityArtificalSlimeGel2Backup.AISlimeHop(this));
	        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
	        //this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
	        //this.targetTasks.addTask(1, new EntityAIFindNearestBreeder2(this, EntitySlime2.class));
	        this.targetTasks.addTask(3, new EntityAIFindEntityNearest(this, EntityIronGolem.class));
	        //this.targetTasks.addTask(3, new EntityAIFindEntityNearest(this, EntityCreeper.class));
	        this.targetTasks.addTask(3, new EntityAIFindNearestSlime(this, EntityColorSlimeBase.class));
	        this.targetTasks.addTask(1, new EntityAIFindNearestBreeder(this));
	        //this.targetTasks.addTask(1, new EntityAIFindNearestFood(this));
	        //this.tasks.addTask(9, new EntityAIWatchClosestFood(this, EntityItem.class, 3.0F));
	        
	        //this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
	        
	        
	        
	    }
	 

    /*public void onUpdate(){
		super.onUpdate();
		//System.out.println("Testing Super");
	}*/
	@Override
	public void onUpdate()
    {
		this.customSetSize(this.getSizeX(), this.getSizeY());
		doJump();
		
    	//this.width = this.getSizeX();
    	//this.height = this.getSizeY();


    			
    		
    	
		
		

		
		//super.onUpdate();
    }
	
	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
    {
        ResourceLocation resourcelocation = this.getLootTable();
        
        int compressed = 10;


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
 	   Entity compressed = EntityList.createEntityFromNBT(this.getDataManager().get(COMPRESSED), this.getEntityWorld());

 	   LootTableManager loottablemanager = new LootTableManager((File)null);
 	  
 	   ResourceLocation lootResource; 
       
 	   lootResource = new ResourceLocation(EntityRegistry.getEntry(compressed.getClass()).getRegistryName().getResourceDomain() + ":entities/"+ compressed.getName());
 	   
 	   LootTable lootList = loottablemanager.getLootTableFromLocation(lootResource);
    	
        return lootResource;
    }
    

	
   @Override 
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
	   Entity zombie = new EntityHorse(this.getEntityWorld());
	   Entity slime = new EntitySlime(this.getEntityWorld());
	   Entity compressed = EntityList.createEntityFromNBT(this.getDataManager().get(COMPRESSED), this.getEntityWorld());
	   
	   /*if(compressed instanceof EntityAgeable) {
	   ((EntityAgeable)compressed).processInteract(player, hand);}*/
	   
	   
	   /*ItemStack itemstack = player.getHeldItem(hand);
	   if(compressed.getClass() == EntityCow.class) {
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
	   
	   }*/
	   
	   
	   System.out.println("Testing");
	  
	   
	   NBTTagCompound slimeSerial = slime.serializeNBT();
	   NBTTagCompound zombieSerial = zombie.serializeNBT();
	   
	   slimeSerial.setInteger("Size", 1);
	   
	   System.out.println(compressed);
	   
	   
	   
	   if(!this.isJumping) {
	   
		   if(compressed.getClass() == zombie.getClass()){
			   
			   this.setCompressedType(slimeSerial, 2);
			   
			   //this.jumpHelper.setJumping();
			   //this.resetPositionToBB();
			   //this.setSlimeSize(2);
	
			   
			   
			   
			   
		   }
		   
		   if(compressed.getClass() == slime.getClass()){
			   
			   this.setCompressedType(zombieSerial, 1);
			   //this.setSlimeSize(1);
			   
			   
			   
			   
	
		   }
	   }
	   
	   
	   
		return true;
		
	}
   

   
   
   @Override
   public void checkFloor()
   {
	   
   }
   
 
   

   @Override
   public void writeEntityToNBT(NBTTagCompound tagCompound)
   {
   	super.writeEntityToNBT(tagCompound);
   	
   	
   	//tagCompound.setInteger("Size", this.getSlimeSize() - 1);
   	tagCompound.setInteger("Size", this.getSlimeSize());
   	tagCompound.setBoolean("wasOnGround", this.wasOnGround);
   	
   	tagCompound.setInteger("Wave", this.getWave());
   	tagCompound.setInteger("RGB", this.getRGB2());
   	tagCompound.setFloat("Sats", this.getSat());
   	tagCompound.setFloat("Bright", this.getBright());
   	tagCompound.setInteger("Varient", this.getVarient());
   	this.dataManager.set(VARIENT, tagCompound.getInteger("Varient"));
   	this.dataManager.set(SLIME_SIZE2, tagCompound.getInteger("Size"));
   	
   	
   	
   	tagCompound.setInteger("Hunger", this.getHunger());
   	tagCompound.setInteger("Timer", this.getTimer());
   	//tagCompound.setInteger("Temperature", this.getTemperature());
   	
   	tagCompound.setInteger("BreedingGroup", this.getBreedingGroup());
   	tagCompound.setInteger("BreedingSpeed", this.getBreedingSpeed());
   	tagCompound.setDouble("MoveSpeed", this.getMoveSpeed());
   	tagCompound.setDouble("Health", this.getSlimeHealth());
   	tagCompound.setInteger("AttackDmg", this.getAttackDmg());
   	tagCompound.setInteger("Sticky", this.getSticky());
   	tagCompound.setInteger("Domesticated", this.getDomestication());
   	
   	NBTTagList complist = new NBTTagList();
	
	complist.appendTag(this.getCompressedType());
	complist.set(0, this.getCompressedType());
	
	tagCompound.setTag("compressedList", complist);
	
   	
	tagCompound.setFloat("Width", this.getSizeX());
	tagCompound.setFloat("Height", this.getSizeY());

   	
   
	this.customSetSize(this.getSizeX(), this.getSizeY());
   	
   }



	/**
    * (abstract) Protected helper method to read subclass entity data from NBT.
    */
   public void readEntityFromNBT(NBTTagCompound tagCompound)
   {
       super.readEntityFromNBT(tagCompound);
       int i = tagCompound.getInteger("Size");


       //this.setSlimeSize(i + 1);
       this.setSlimeSize(i);
       this.wasOnGround = tagCompound.getBoolean("wasOnGround");
   	this.setWave(tagCompound.getInteger("Wave"), tagCompound.getFloat("Sats"), tagCompound.getFloat("Bright"));
   	this.setGenetics(tagCompound.getInteger("BreedingGroup"), tagCompound.getInteger("BreedingSpeed"), tagCompound.getDouble("MoveSpeed"), tagCompound.getDouble("Health"), tagCompound.getInteger("AttackDmg"), tagCompound.getInteger("Sticky"), tagCompound.getInteger("Domesticated") );
   	this.setFRGB(tagCompound.getInteger("RGB"));
   	this.setVarient(tagCompound.getInteger("Varient"));
   	this.dataManager.set(VARIENT, tagCompound.getInteger("Varient"));
   	
   	//this.setTemperature(tagCompound.getInteger("Temperature"));
   	//this.dataManager.set(TEMPERATURE, tagCompound.getInteger("Temperature"));
   	
   	this.setTimer(tagCompound.getInteger("Timer"));
   	this.dataManager.set(TIMER, tagCompound.getInteger("Timer"));
   	
   	this.setHunger(tagCompound.getInteger("Hunger"));
   	
   	this.setOwner(tagCompound.getUniqueId("Owner"));
   	
   	
   	
   	
   	NBTTagList compressedTypeList = tagCompound.getTagList("compressedList", Constants.NBT.TAG_COMPOUND);
   	System.out.println("Tag List = " + compressedTypeList);
   	
   	if(compressedTypeList.hasNoTags()) {
   		
   		compressedTypeList.appendTag(this.getCompressedType());
   		compressedTypeList.set(0, this.getCompressedType());
   		
   		tagCompound.setTag("compressedList", compressedTypeList);
   		System.out.println("Appending Tag");
   	}
   	System.out.println("Tag List = " + compressedTypeList);
   	System.out.println("Tag ind 0 = " + compressedTypeList.getCompoundTagAt(0));
	
	this.setCompressedType(compressedTypeList.getCompoundTagAt(0), tagCompound.getInteger("Size"));
	this.customSetSize(this.getSizeX(), this.getSizeY());
	
	//this.width = tagCompound.getFloat("Width");
	//this.height = tagCompound.getFloat("Height");
   	
   }
   

	
    @Override 
    public void doJump(){
    	
    	//System.out.println(this.getDataManager().getAll().size());
    	
    	this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
        this.prevSquishFactor = this.squishFactor;
        

        if (this.onGround && !this.wasOnGround)
        {
            int i = this.getSlimeSize();
            if (spawnCustomParticles()) { i = 0; } // don't spawn particles if it's handled by the implementation itself
            for (int j = 0; j < i * 8; ++j)
            {
                float f = this.rand.nextFloat() * ((float)Math.PI * 2F);
                float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float)i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float)i * 0.5F * f1;
                World world = this.world;
                EnumParticleTypes enumparticletypes = this.getParticleType();
                double d0 = this.posX + (double)f2;
                double d1 = this.posZ + (double)f3;
               // world.spawnParticle(enumparticletypes, d0, this.getEntityBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D, new int[0]);
                
                
                world.spawnParticle(EnumParticleTypes.REDSTONE, d0, this.getEntityBoundingBox().minY, d1, ((double)1F), ((double)1F), ((double)0.89F), new int[0]);

                double d2 = this.rand.nextGaussian() * 0.02D;
               }

            float volume = 0.1F * 1.0F;
            
            this.playSound(SoundEvents.ENTITY_SLIME_SQUISH, volume, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            this.squishAmount = -0.5F;
        }
        else if (!this.onGround && this.wasOnGround)
        {
            this.squishAmount = 1.0F;
        }

        this.wasOnGround = this.onGround;
        this.alterSquishAmount();
        
        //ReflectionHelper.getPrivateValue(entityD.getClass(), null, "MOOSHROOM_TEXTURES");
     	
     	
     	//ResourceLocation info = ReflectionHelper.getPrivateValue(entityD.getClass(), null, "MOOSHROOM_TEXTURES");
        
     	//System.out.println(livingBase);
     	
        super.doJump();
    }
    
    @Override
    protected void alterSquishAmount()
    {
        this.squishAmount *= 0.6F;
    }
    
    @Override
    public float getSizeX() {
 	   
 	NBTTagCompound entityTag = this.getDataManager().get(COMPRESSED);
 	Entity entityD = EntityList.createEntityFromNBT(entityTag, this.getEntityWorld());
 	
 	//System.out.println("Width = " + entityD.width);
 	//System.out.println("Size = " + this.getSlimeSize());
 	
 	   
    	return entityD.width;
    }
    
    @Override
    public float getSizeY() {
 	   
 	   NBTTagCompound entityTag = this.getDataManager().get(COMPRESSED);
 		Entity entityD = EntityList.createEntityFromNBT(entityTag, this.getEntityWorld());
 		//System.out.println("Height = " + entityD.height);
 		
 		   
 	   	return entityD.height;
    }
    
    public void setSizeX(float sizex) {
    	this.width = sizex;
    }
    
    public void setSizeY(float sizey) {
    	this.height = sizey;
    }
    
    public NBTTagCompound getCompressedType() {
    	return this.dataManager.get(COMPRESSED);
    }
    
    public void setCompressedType(NBTTagCompound entityTag, int size) {
    	

    	
    	//this.setSlimeSize(2);
    	this.dataManager.set(COMPRESSED, entityTag);
    	this.compressedTag = entityTag;
    	this.setSlimeSize(size);
		this.customSetSize(this.getSizeX(), this.getSizeY());
    	

    	

    	
    	
    	
    	
    	/*System.out.println("DM = " + this.getDataManager().get(COMPRESSED));
    	System.out.println("NBT = " + this.compressedTag);
    	System.out.println("Width before = " + this.width);
    	System.out.println("height before = " + this.height);
    	
    	this.width = this.getSizeX();
    	this.height = this.getSizeY();
    	
    	System.out.println("Width after = " + this.width);
    	System.out.println("height after = " + this.height);*/
    	
    	
    }
 
}