package com.itchymichi.slimebreeder.entity.monster;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.itchymichi.slimebreeder.entity.ai.EntityColourSlime.EntityAISlimeMate;
import com.itchymichi.slimebreeder.entity.ai.CustomSlimeMoveHelper;
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
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
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
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.ItemHandlerHelper;


public class EntityColorSlimeBase extends EntityLivingSlimeBase 
{
	private int[] bufferInventory;
	private int[] craftingInventory;
	private int[] slimeInventory;
	private int[] modInventory;
	
	 
	NBTTagCompound SlotSlime =  new ItemStack(Items.SLIME_BALL).serializeNBT();
	
	
	private NBTTagCompound SlotOne;
	private NBTTagCompound SlotTwo;
	private NBTTagCompound SlotThree;
	private NBTTagCompound SlotFour;
	private NBTTagCompound SlotFive;
	
	private NBTTagCompound SlotDrop;
	
	private NBTTagCompound SlotFutureResult;
	private NBTTagCompound SlotResult;
	
	
	int length = EnumCraftingIngredients.listOfItems.size();
	
	private int processItemSlot1;
	private int processItemSlot1Max;
	private int itemId1;
	
	private int processItemSlot2;
	private int processItemSlot2Max;
	private int itemId2;
	
	private int processItemSlot3;
	private int processItemSlot3Max;
	private int itemId3;
	
	private int processItemSlot4;
	private int processItemSlot4Max;
	private int itemId4;
	
	private int processItemSlot5;
	private int processItemSlot5Max;
	private int itemId5;
	
	private int capacity;

	float[] vs = {Hue, sat, brightness};
	private int rgb;
	
	protected static final DataParameter<Integer> RGB = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 
	 private static final DataParameter<Integer> ITEMSLOT1 = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 private static final DataParameter<Integer> ITEMSLOT2 = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 private static final DataParameter<Integer> ITEMSLOT3 = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 private static final DataParameter<Integer> ITEMSLOT4 = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 private static final DataParameter<Integer> ITEMSLOT5 = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 
	 private static final DataParameter<Integer> ITEMPROCESSSLOT1 = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 private static final DataParameter<Integer> ITEMPROCESSSLOT2 = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 private static final DataParameter<Integer> ITEMPROCESSSLOT3 = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 private static final DataParameter<Integer> ITEMPROCESSSLOT4 = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 private static final DataParameter<Integer> ITEMPROCESSSLOT5 = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 
	 private static final DataParameter<Integer> ITEMPROCESSSLOT1MAX = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 private static final DataParameter<Integer> ITEMPROCESSSLOT2MAX = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 private static final DataParameter<Integer> ITEMPROCESSSLOT3MAX = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 private static final DataParameter<Integer> ITEMPROCESSSLOT4MAX = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 private static final DataParameter<Integer> ITEMPROCESSSLOT5MAX = EntityDataManager.<Integer>createKey(EntityColorSlimeBase.class, DataSerializers.VARINT);
	 
	 
	 
    public EntityColorSlimeBase(World worldIn)
    {
        super(worldIn);
        this.moveHelper = new CustomSlimeMoveHelper(this);
        
    }

    protected void initEntityAI()
    {
    	super.initEntityAI();
        this.tasks.addTask(2, new EntityAISlimeMate(this, 1.0D));
        //this.targetTasks.addTask(3, new EntityAIFindEntityNearest(this, EntityIronGolem.class));
        this.targetTasks.addTask(3, new EntityAIFindNearestSlime(this, EntityColorSlimeBase.class));
        this.targetTasks.addTask(1, new EntityAIFindNearestBreeder(this));  
    }

    protected void entityInit()
    {
        super.entityInit();
        
        this.dataManager.register(this.RGB, Integer.valueOf(rgb));
        
        this.dataManager.register(ITEMSLOT1, Integer.valueOf(this.itemId1));
        this.dataManager.register(ITEMPROCESSSLOT1, Integer.valueOf(this.processItemSlot1));
        this.dataManager.register(ITEMPROCESSSLOT1MAX, Integer.valueOf(this.processItemSlot1Max));
        
        this.dataManager.register(ITEMSLOT2, Integer.valueOf(this.itemId2));
        this.dataManager.register(ITEMPROCESSSLOT2, Integer.valueOf(this.processItemSlot2));
        this.dataManager.register(ITEMPROCESSSLOT2MAX, Integer.valueOf(this.processItemSlot2Max));
        
        this.dataManager.register(ITEMSLOT3, Integer.valueOf(this.itemId3));
        this.dataManager.register(ITEMPROCESSSLOT3, Integer.valueOf(this.processItemSlot3));
        this.dataManager.register(ITEMPROCESSSLOT3MAX, Integer.valueOf(this.processItemSlot3Max));
        
        this.dataManager.register(ITEMSLOT4, Integer.valueOf(this.itemId4));
        this.dataManager.register(ITEMPROCESSSLOT4, Integer.valueOf(this.processItemSlot4));
        this.dataManager.register(ITEMPROCESSSLOT4MAX, Integer.valueOf(this.processItemSlot4Max));
        
        this.dataManager.register(ITEMSLOT5, Integer.valueOf(this.itemId5));
        this.dataManager.register(ITEMPROCESSSLOT5, Integer.valueOf(this.processItemSlot5));
        this.dataManager.register(ITEMPROCESSSLOT5MAX, Integer.valueOf(this.processItemSlot5Max));
        
        initItemSlots();
        bufferInventory = new int[5];
   	 	craftingInventory = new int[5];
   	 	slimeInventory = new int[5];
        
    }
    
    public void initWildSlime(@Nullable int varient, int wave, float saturation, float brightness, @Nullable int breedingspeed, @Nullable double movespeed, @Nullable double health, @Nullable int attackdmg, @Nullable int sticky, boolean customslime)
    {
    	
        /*int i = this.rand.nextInt(3);

        if (i < 2 && this.rand.nextFloat() < 0.5F * difficulty.getClampedAdditionalDifficulty())
        {
            ++i;
        }

        int j = 1 << i;
        this.setSlimeSize(j);*/
    //this.setColor(red, green, blue);
    
    	
    this.varient = 1;
    this.dataManager.set(VARIENT, Integer.valueOf(1));
    

    
    ////System.out.println(this.enablePersistence());
    
    //this.wave = (int) (380 + Math.abs(rand.nextGaussian()*200));
    //this.sat = rand.nextFloat();
    //this.brightness = rand.nextFloat();
    
    /*this.wave = (int) (380 + Math.abs(rand.nextGaussian()*200));
    this.sat = Math.abs((float) ((rand.nextGaussian()*50)/100));
    this.brightness = Math.abs((float) ((rand.nextGaussian()*50)/100));*/
    
    /*System.out.println("Wave = " + this.wave);
    System.out.println("Sat = " + this.sat);
    System.out.println("Bright = " + this.brightness);*/
    
    setSlimeItemSlot1(-1);
    setItemProcessSlot1(1000);
    setItemProcessSlot1Max(1000);
    
    setSlimeItemSlot2(-1);
    setItemProcessSlot2(1000);
    setItemProcessSlot2Max(1000);
    
    setSlimeItemSlot3(-1);
    setItemProcessSlot3(1000);
    setItemProcessSlot3Max(1000);
    
    setSlimeItemSlot4(-1);
    setItemProcessSlot4(1000);
    setItemProcessSlot4Max(1000);
    
    setSlimeItemSlot5(-1);
    setItemProcessSlot5(1000);
    setItemProcessSlot5Max(1000);
    
    initItemSlots();
    
    NBTTagCompound slotSerial = setDrop().serializeNBT();
	this.SlotDrop = slotSerial;
	
	


    this.breedinggroup = 0;
    this.domesticated = 0;
    
    this.breedingspeed = breedingspeed;
    this.movespeed = movespeed;
    this.health = health;
    this.attackdmg = attackdmg;
    this.sticky = sticky;
    

    
    //this.targetTasks.addTask(1, new EntityAIFindNearestBreeder(this, this.domesticated));
    //this.targetTasks.addTask(1, new EntityAIFindNearestOwnaer(this, this.domesticated));
    
    System.out.println("Wave = " + wave);

    if(!customslime)
    {
    	setStatsByWave(wave);
    }
    //this.setSlimeSize(1);

    ////System.out.println("Growing Age = " + this.getGrowingAge());

    System.out.println("Gen breedingspeed = " + this.breedingspeed);
    System.out.println("Gen movespeed = " + this.movespeed);
    System.out.println("Gen health = " + this.health);
    System.out.println("Gen attackdmg = " + this.attackdmg);
    
    System.out.println("Gen Wave = " + wave);
    System.out.println("Gen Sats = " + saturation);
    System.out.println("Gen Brightness = " + brightness);
    System.out.println("Gen rgb = " + rgb);
    
    setHSB(this.wave);
    this.setWave(this.wave);
    this.setSat(this.sat);
    this.setBrightness(this.brightness);
    this.setHunger(this.breedingspeed/2);
    this.setGenetics(this.breedinggroup, this.breedingspeed, this.movespeed, this.health, this.attackdmg, this.sticky, this.domesticated);
    
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.health);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1*this.movespeed);
    this.setHealth(this.getMaxHealth());



    //this.dataWatcher.updateObject(16, Byte.valueOf((byte) 1));

    //this.setColor(wavelength);
    //this.red = this.getRed();
    //this.green = this.getGreen();
    //this.blue = this.getBlue();
    }
    
    
    public void setStatsByWave(int wave) {
    	
    	if(wave > 0)
        {
        	double w1 = wave - 380;
        	double p = (w1/401)*100;
        	System.out.println("Percentage = " + p);
        	
        	if(p >= 0 && p < 20)
        	{
        		this.breedingspeed = 91712;
        		this.movespeed = 4;
        		this.health = 16;
        		this.attackdmg = 10;
        		this.sticky = 4;
        		
        		this.setBreedingSpeed(91712);
        		this.setMoveSpeed(4);
        		this.setSlimeHealth(16);
        		this.setSlimeAttack(10);
        		this.setSticky(4);
        		
        		this.setHunger(breedingspeed/2);
        		
        		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
                this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1*movespeed);
        		
        	}else
        	if(p >= 20 && p < 40)
        	{
        		this.breedingspeed = 45856;
        		this.movespeed = 5;
        		this.health = 12;
        		this.attackdmg = 8;
        		this.sticky = 3;
        		
        		this.setBreedingSpeed(45856);
        		this.setMoveSpeed(5);
        		this.setSlimeHealth(12);
        		this.setSlimeAttack(8);
        		this.setSticky(3);
        		
        		this.setHunger(breedingspeed/2);
        		
        		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
                this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1*movespeed);
        		
        	}else

        	if(p >= 40 && p < 60)
        	{
        		this.breedingspeed = 22928;
        		this.movespeed = 6;
        		this.health = 8;
        		this.attackdmg = 6;
        		this.sticky = 2;
        		
        		this.setBreedingSpeed(22928);
        		this.setMoveSpeed(6);
        		this.setSlimeHealth(8);
        		this.setSlimeAttack(6);
        		this.setSticky(2);
        		
        		this.setHunger(breedingspeed/2);
        		
        		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
                this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1*movespeed);
        		
        	}else

        	if(p >= 60 && p < 80)
        	{
        		this.breedingspeed = 11464;
        		this.movespeed = 7;
        		this.health = 6;
        		this.attackdmg = 4;
        		this.sticky = 1;
        		
        		this.setBreedingSpeed(11464);
        		this.setMoveSpeed(7);
        		this.setSlimeHealth(6);
        		this.setSlimeAttack(4);
        		this.setSticky(1);
        		
        		this.setHunger(breedingspeed/2);
        		
        		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
                this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1*movespeed);
        		
        	}else

        	if(p >= 80 && p < 100)
        	{
        		this.breedingspeed = 5732;
        		this.movespeed = 8;
        		this.health = 4;
        		this.attackdmg = 2;
        		this.sticky = 0;
        		
        		this.setBreedingSpeed(5732);
        		this.setMoveSpeed(8);
        		this.setSlimeHealth(4);
        		this.setSlimeAttack(2);
        		this.setSticky(0);
        		
        		this.setHunger(breedingspeed/2);
        		
        		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
                this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1*movespeed);
        		
        	}
        }
    	
    	
    }


    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
    	super.writeEntityToNBT(tagCompound);
    	
    	tagCompound.setInteger("RGB", this.getRGB2());
    	
    	tagCompound.setInteger("BreedingGroup", this.getBreedingGroup());
       	tagCompound.setInteger("BreedingSpeed", this.getBreedingSpeed());
       	tagCompound.setDouble("MoveSpeed", this.getMoveSpeed());
       	tagCompound.setDouble("Health", this.getSlimeHealth());
       	tagCompound.setInteger("AttackDmg", this.getAttackDmg());
       	tagCompound.setInteger("Sticky", this.getSticky());
       	tagCompound.setInteger("Domesticated", this.getDomestication());
    	
    	tagCompound.setIntArray("craftingInventory", this.craftingInventory);
    	tagCompound.setIntArray("slimeInventory", this.slimeInventory);

    	tagCompound.setInteger("ItemId1", this.getSlimeItemSlot1());
    	this.dataManager.set(ITEMSLOT1, tagCompound.getInteger("ItemId1"));
    	
    	tagCompound.setInteger("ItemProcessSlot1", this.getItemProcessSlot1());
    	this.dataManager.set(ITEMPROCESSSLOT1, tagCompound.getInteger("ItemProcessSlot1"));
    	
    	tagCompound.setInteger("ItemProcessSlot1Max", this.getItemProcessSlot1Max());
    	this.dataManager.set(ITEMPROCESSSLOT1MAX, tagCompound.getInteger("ItemProcessSlot1Max"));
    	
    	tagCompound.setInteger("ItemId2", this.getSlimeItemSlot2());
    	this.dataManager.set(ITEMSLOT2, tagCompound.getInteger("ItemId2"));
    	
    	tagCompound.setInteger("ItemProcessSlot2", this.getItemProcessSlot2());
    	this.dataManager.set(ITEMPROCESSSLOT2, tagCompound.getInteger("ItemProcessSlot2"));
    	
    	tagCompound.setInteger("ItemProcessSlot2Max", this.getItemProcessSlot2Max());
    	this.dataManager.set(ITEMPROCESSSLOT2MAX, tagCompound.getInteger("ItemProcessSlot2Max"));
    	
    	tagCompound.setInteger("ItemId3", this.getSlimeItemSlot3());
    	this.dataManager.set(ITEMSLOT3, tagCompound.getInteger("ItemId3"));
    	
    	tagCompound.setInteger("ItemProcessSlot3", this.getItemProcessSlot3());
    	this.dataManager.set(ITEMPROCESSSLOT3, tagCompound.getInteger("ItemProcessSlot3"));
    	
    	tagCompound.setInteger("ItemProcessSlot3Max", this.getItemProcessSlot3Max());
    	this.dataManager.set(ITEMPROCESSSLOT3MAX, tagCompound.getInteger("ItemProcessSlot3Max"));
    	
    	tagCompound.setInteger("ItemId4", this.getSlimeItemSlot4());
    	this.dataManager.set(ITEMSLOT4, tagCompound.getInteger("ItemId4"));
    	
    	tagCompound.setInteger("ItemProcessSlot4", this.getItemProcessSlot4());
    	this.dataManager.set(ITEMPROCESSSLOT4, tagCompound.getInteger("ItemProcessSlot4"));
    	
    	tagCompound.setInteger("ItemProcessSlot4Max", this.getItemProcessSlot4Max());
    	this.dataManager.set(ITEMPROCESSSLOT4MAX, tagCompound.getInteger("ItemProcessSlot4Max"));
    	
    	tagCompound.setInteger("ItemId5", this.getSlimeItemSlot5());
    	this.dataManager.set(ITEMSLOT5, tagCompound.getInteger("ItemId5"));
    	
    	tagCompound.setInteger("ItemProcessSlot5", this.getItemProcessSlot5());
    	this.dataManager.set(ITEMPROCESSSLOT5, tagCompound.getInteger("ItemProcessSlot5"));
    	
    	tagCompound.setInteger("ItemProcessSlot5Max", this.getItemProcessSlot5Max());
    	this.dataManager.set(ITEMPROCESSSLOT5MAX, tagCompound.getInteger("ItemProcessSlot5Max"));
    	
    	NBTTagList nbttaglist = new NBTTagList();
    	
    	nbttaglist.appendTag(SlotOne);
    	nbttaglist.set(0, SlotOne);
    	
    	nbttaglist.appendTag(SlotTwo);
    	nbttaglist.set(1, SlotTwo);
    	
    	nbttaglist.appendTag(SlotThree);
    	nbttaglist.set(2, SlotThree);
    	
    	nbttaglist.appendTag(SlotFour);
    	nbttaglist.set(3, SlotFour);
    	
    	nbttaglist.appendTag(SlotFive);
    	nbttaglist.set(4, SlotFive);
    	
    	nbttaglist.appendTag(SlotResult);
    	nbttaglist.set(5, SlotResult);
    	
    	nbttaglist.appendTag(SlotDrop);
    	nbttaglist.set(6, SlotDrop);
    	
    	nbttaglist.appendTag(SlotFutureResult);
    	nbttaglist.set(7, SlotFutureResult);
    	
    	tagCompound.setTag("modItems", nbttaglist);

    	
    }



	/**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        super.readEntityFromNBT(tagCompound);

        
    	this.setFRGB(tagCompound.getInteger("RGB"));

    	this.setGenetics(tagCompound.getInteger("BreedingGroup"), tagCompound.getInteger("BreedingSpeed"), tagCompound.getDouble("MoveSpeed"), tagCompound.getDouble("Health"), tagCompound.getInteger("AttackDmg"), tagCompound.getInteger("Sticky"), tagCompound.getInteger("Domesticated") );
    	
    	this.slimeInventory = tagCompound.getIntArray("slimeInventory");
    	this.craftingInventory = tagCompound.getIntArray("craftingInventory");
    	
    	
    	this.dataManager.set(ITEMSLOT1, tagCompound.getInteger("ItemId1"));
    	this.setSlimeItemSlot1(tagCompound.getInteger("ItemId1"));

    	this.setItemProcessSlot1(tagCompound.getInteger("ItemProcessSlot1"));
    	this.dataManager.set(ITEMPROCESSSLOT1, tagCompound.getInteger("ItemProcessSlot1"));
    	
    	this.setItemProcessSlot1Max(tagCompound.getInteger("ItemProcessSlot1Max"));
    	this.dataManager.set(ITEMPROCESSSLOT1MAX, tagCompound.getInteger("ItemProcessSlot1Max"));
    	
    	
    	
    	this.dataManager.set(ITEMSLOT2, tagCompound.getInteger("ItemId2"));
    	this.setSlimeItemSlot2(tagCompound.getInteger("ItemId2"));
    	
    	this.setItemProcessSlot2(tagCompound.getInteger("ItemProcessSlot2"));
    	this.dataManager.set(ITEMPROCESSSLOT2, tagCompound.getInteger("ItemProcessSlot2"));
    	
    	this.setItemProcessSlot2Max(tagCompound.getInteger("ItemProcessSlot2Max"));
    	this.dataManager.set(ITEMPROCESSSLOT2MAX, tagCompound.getInteger("ItemProcessSlot2Max"));
    	
    	
    	
    	this.dataManager.set(ITEMSLOT3, tagCompound.getInteger("ItemId3"));
    	this.setSlimeItemSlot3(tagCompound.getInteger("ItemId3"));
    	
    	this.setItemProcessSlot3(tagCompound.getInteger("ItemProcessSlot3"));
    	this.dataManager.set(ITEMPROCESSSLOT3, tagCompound.getInteger("ItemProcessSlot3"));
    	
    	this.setItemProcessSlot3Max(tagCompound.getInteger("ItemProcessSlot3Max"));
    	this.dataManager.set(ITEMPROCESSSLOT3MAX, tagCompound.getInteger("ItemProcessSlot3Max"));
    	
    	
    	
    	this.dataManager.set(ITEMSLOT4, tagCompound.getInteger("ItemId4"));
    	this.setSlimeItemSlot4(tagCompound.getInteger("ItemId4"));
    	
    	this.setItemProcessSlot4(tagCompound.getInteger("ItemProcessSlot4"));
    	this.dataManager.set(ITEMPROCESSSLOT4, tagCompound.getInteger("ItemProcessSlot4"));
    	
    	this.setItemProcessSlot4Max(tagCompound.getInteger("ItemProcessSlot4Max"));
    	this.dataManager.set(ITEMPROCESSSLOT4MAX, tagCompound.getInteger("ItemProcessSlot4Max"));
    	
    	
    	
    	this.dataManager.set(ITEMSLOT5, tagCompound.getInteger("ItemId5"));
    	this.setSlimeItemSlot5(tagCompound.getInteger("ItemId5"));
    	
    	this.setItemProcessSlot5(tagCompound.getInteger("ItemProcessSlot5"));
    	this.dataManager.set(ITEMPROCESSSLOT5, tagCompound.getInteger("ItemProcessSlot5"));
    	
    	this.setItemProcessSlot5Max(tagCompound.getInteger("ItemProcessSlot5Max"));
    	this.dataManager.set(ITEMPROCESSSLOT5MAX, tagCompound.getInteger("ItemProcessSlot5Max"));
    	
    	
    	//tagCompound.setTag(", value);
    	
    	NBTTagList itemList = tagCompound.getTagList("modItems", 10);
    	
    	this.SlotOne = itemList.getCompoundTagAt(0);
    	this.SlotTwo = itemList.getCompoundTagAt(1);
    	this.SlotThree = itemList.getCompoundTagAt(2);
    	this.SlotFour = itemList.getCompoundTagAt(3);
    	this.SlotFive = itemList.getCompoundTagAt(4);
    	this.SlotResult = itemList.getCompoundTagAt(5);
    	this.SlotDrop = itemList.getCompoundTagAt(6);
    	this.SlotFutureResult = itemList.getCompoundTagAt(7);
    	
    	
    }
    
    

	public boolean isSmallSlime()
    {
        return false;
    }



    /**
     * Called to update the entity's position/logic.
     */
    
    
    @Override
    public void onUpdate()
    {
    	int slimeHunger = this.getHunger();
    	int maxHunger = this.getBreedingSpeed()/2;
    	
    	
    	processItemSlot1(this.itemId1);
    	processItemSlot2(this.itemId2);
    	processItemSlot3(this.itemId3);
    	processItemSlot4(this.itemId4);
    	processItemSlot5(this.itemId5);
    	
    	
    	slimeIndicatorConstant();
    	checkFloor();

        if(this.getGrowingAge() < 0)
        {
        	this.addGrowth(1500);
        	this.setGrowingAge(this.getBreedingSpeed());
        }
        
        
        doJump();
        //this.customSetSize(this.getSizeX()*(float)2, this.getSizeY()*(float)2);
    }
    
    public void doJump() {
    	super.doJump();
    }
    
    /*public void doJump(){
    	 this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
         this.prevSquishFactor = this.squishFactor;
         super.onUpdate();

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
                 
                 int rgb2 = this.getRGB2();
                 float red = (float)((rgb2>>16)&0xFF)/255;
                 float green = (float)((rgb2>>8)&0xFF)/255;
                 float blue = (float)(rgb2&0xFF)/255;
                 
                 world.spawnParticle(EnumParticleTypes.REDSTONE, d0, this.getEntityBoundingBox().minY, d1, ((double)red), ((double)green), ((double)blue), new int[0]);

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
    }*/
    
    public void checkFloor()
    {
    	
    	if(this.getActivePotionEffect(SlimeBreeder.proxy.slimeHunger) != null && this.onGround)
    	{
	    	List<EntityItem> list = this.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().grow(10.0D));
	    	
    	
				for (EntityItem entityitem : list)
		        {
					
					if (entityitem.getItem().getItem() == Items.CHICKEN)
				    {
				    	//entityliving.startRiding(this, true);
				    	//this.setVelocity(0D, 0.0D, 0D);
				    	////System.out.println("Tracking");
				    	
						this.getNavigator().tryMoveToXYZ(entityitem.posX, entityitem.posY, entityitem.posZ, 2);
						
						this.faceEntity(entityitem, 10.0F, 10.0F);
		                ((CustomSlimeMoveHelper)this.getMoveHelper()).setDirection(this.rotationYaw, true);
		            }
						
						//this.startRiding(entityliving, true);
		    	
				}
		
		        
    	}else{
    		if(this.getActivePotionEffect(SlimeBreeder.proxy.slimeHunger) != null)
        	{
    			List<EntityItem> list = this.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().grow(-10.0D));
    	    	
    	    	
				for (EntityItem entityitem : list)
		        {
					
					if (entityitem.getItem().getItem() == Items.CHICKEN)
				    {
				    	//entityliving.startRiding(this, true);
				    	//this.setVelocity(0D, 0.0D, 0D);
				    	////System.out.println("Tracking Air");
				    	
						this.getNavigator().tryMoveToXYZ(entityitem.posX, entityitem.posY, entityitem.posZ, 2);
						
						//this.startRiding(entityliving, true);
		    	
				    }
		
		        }
        	}
    	}
    	
    	
    	if(this.getActivePotionEffect(SlimeBreeder.proxy.slimeHunger) != null)
    	{
	    	List<EntityItem> list = this.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().grow(1.0D));
	    	
    	
				for (EntityItem entityitem : list)
		        {
					
					if (entityitem instanceof EntityItem)
				    {
				    	////System.out.println("Item found");
				    	EntityItem item = (EntityItem) entityitem;
				    	
				    	
				    	////System.out.println(itemstack);
				    	
				    	if(((EntityItem) entityitem).getItem().getItem() == Items.CHICKEN && !this.world.isRemote)
				    	{
		
				    		this.addItemSlimeFloor(entityitem);
		
				    	}
		    	
				    }
		
		        }
    	}
    }
    
    public void slimeIndicatorConstant()
    {
    	
    	Item slotEmpty =  new ItemStack(Items.SLIME_BALL).getItem();
    	
    	if(this.getGrowingAge() == 0 && this.breedinggroup != 0)
        {
    		this.addPotionEffect(new PotionEffect(SlimeBreeder.proxy.slimeBreedable, 20, 1));
    		////System.out.println("Breedable");
        }
    	
    	if(this.getHunger() < (((this.getBreedingSpeed()/2)*0.5)))
        {
    		this.addPotionEffect(new PotionEffect(SlimeBreeder.proxy.slimeHunger, 20, 1));
    		////System.out.println("Hungry");
        }
    	
    	if(this.getDomestication() > 5000 && this.getActivePotionEffect(SlimeBreeder.proxy.slimeDomesticatedFollow) == null && this.getActivePotionEffect(SlimeBreeder.proxy.slimeDomesticatedWait) == null )
        {
    		this.addPotionEffect(new PotionEffect(SlimeBreeder.proxy.slimeDomesticated, 20, 1));
    		////System.out.println("Domesticated");
        }
    	
    	if(this.getActivePotionEffect(SlimeBreeder.proxy.slimeDomesticatedFollow) != null)
    	{
    		this.addPotionEffect(new PotionEffect(SlimeBreeder.proxy.slimeDomesticatedFollow, 20, 1));
    	}
    	
    	if(this.getActivePotionEffect(SlimeBreeder.proxy.slimeDomesticatedWait) != null)
    	{
    		this.addPotionEffect(new PotionEffect(SlimeBreeder.proxy.slimeDomesticatedWait, 20, 1));
    	}
    	
    	

    	
    }
  
    
    public void slimeIndicatorJump()
    {
    	
    	Item slotEmpty =  new ItemStack(Items.SLIME_BALL).getItem();
    	
    	if(new ItemStack(this.SlotOne).getItem() != slotEmpty || new ItemStack(this.SlotTwo).getItem() != slotEmpty || new ItemStack(this.SlotThree).getItem() != slotEmpty || new ItemStack(this.SlotFour).getItem() != slotEmpty || new ItemStack(this.SlotFive).getItem() != slotEmpty )
        {
    		////System.out.println("Crafting");
    		
    		this.addPotionEffect(new PotionEffect(SlimeBreeder.proxy.slimeCrafting, 20, 1));
        }
    	
    	if(new ItemStack(this.SlotFutureResult).getItem() != slotEmpty || new ItemStack(this.SlotResult).getItem() != slotEmpty)
        {
    		////System.out.println("Result");
    		
    		this.addPotionEffect(new PotionEffect(SlimeBreeder.proxy.slimeResult, 20, 1));
        }
    }
    
    
    public void varientEffect()
    {
    	if(this.getVarient() == 1)
    	{
    		
    		int time = this.ticksExisted;
    		int amp = 50;
    		double angular = 0.02D;
    		
    		double sin = Math.sin((double)time * angular);
    		double progress = (double)amp * sin;
    		
    		float differance = (float)(((progress+-50)+100)/100);
    		double change = 340*differance;
    		
    		this.wave = 420 + (int)change;
			setHSB(wave);
			this.setWave(wave);
	        this.setSat(sat);
	        this.setBrightness(brightness);
    		
    		/*if(this.wave < 780)
    		{
    			//direction = true;
    			this.wave++;
    			setHSB(wave);
    		    setWave(wave, sat, brightness);
    		}
    		
    		if(this.wave == 780)
    		{
    			down = true;
    			this.wave = 779;
    			setHSB(wave);
    		    setWave(wave, sat, brightness);
    		}*/
    		

    	}
    	
    	if(this.getVarient() == 2)
    	{
    		
    	}
    	
    	
    }



    /*protected EntitySlime2 createInstance()
    {
        return new EntitySlime2(this.world);
    }*/


    /**
     * Will get destroyed next tick.
     * @return 
     */
    
    @Override
    public boolean canDespawn(){
		
    	if(this.breedinggroup == 0)
    	{
    		////System.out.println("True despawn group");
    		return true;
    	}else if(this.getOwner() == blankOwner){
    		////System.out.println("True despawn owner");
    		return true;
    	}else{
    		////System.out.println("false despawn");
    		return false;
    	}
    	
    }
    


    /**
     * Applies a velocity to the entities, to push them away from eachother.
     */
    public void applyEntityCollision(Entity entityIn)
    {
        super.applyEntityCollision(entityIn);
        
        ////System.out.println("Hit?");
        ////System.out.println(entityIn);

        if (entityIn instanceof EntityIronGolem && this.canDamagePlayer())
        {
            this.dealDamage((EntityLivingBase)entityIn);
        }
        
	    	
        
        if (entityIn instanceof EntityPlayer)
        {
        	
        	////System.out.println("Hit");
        	
        	EntityPlayer player = (EntityPlayer) entityIn;
    		Iterable<ItemStack> armour = entityIn.getArmorInventoryList();
    		
    		List<Item> armourList = new ArrayList<Item>();
        	List<Item> fullarmourList = new ArrayList<Item>();
        	List<Item> comparmourList = new ArrayList<Item>();
        	
        	armourList.clear();
    		fullarmourList.clear();
    		comparmourList.clear();
    		
    		fullarmourList.add(SlimeBreederItems.SLIMEINFUSEDHELM);
    		fullarmourList.add(SlimeBreederItems.SLIMEINFUSEDCHEST);
    		fullarmourList.add(SlimeBreederItems.SLIMEINFUSEDLEGGINGS);
    		fullarmourList.add(SlimeBreederItems.SLIMEINFUSEDBOOTS);
    		
    		for(ItemStack stack : armour)
    		{
    			if(stack != null)
    			{
    			armourList.add(stack.getItem());
    			}
    		}
        	fullarmourList.removeAll(armourList);
        	
        	////System.out.println("No of Armour Pieces = " + (4-fullarmourList.size()));
        	
        	if(fullarmourList.size() != 0)
        	{
        		((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30, this.getSticky()));
        		System.out.println(new PotionEffect(MobEffects.SLOWNESS, 30, this.getSticky()));
            	//this.dealDamage((EntityLivingBase)entityIn);
        		this.dealDamage((EntityLivingBase)entityIn);
        		
                //player.attackEntityFrom(DamageSource.GENERIC, this.getAttackDmg());

        	}
        	
        	if(fullarmourList.size() == 0)
        	{
        		
        		for(ItemStack stack : armour)
        		{
        			Integer integer = Integer.valueOf((int) Math.round(((stack.getItemDamage()*0.10)-((stack.getItemDamage()*0.10)*(stack.getItemDamage()/stack.getMaxDamage())))));
        			
        			stack.setItemDamage(integer);
        			
        		}
        		
        	}
        	

        }
        
        
        
        if (entityIn instanceof EntityColorSlimeBase && this.getGrowingAge() == 0 && ((EntityColorSlimeBase) entityIn).getGrowingAge() == 0 && this.breedinggroup != 0 && this.breedinggroup == ((EntityColorSlimeBase) entityIn).breedinggroup && this.getActivePotionEffect(SlimeBreeder.proxy.slimeHunger) == null && ((EntityColorSlimeBase) entityIn).getActivePotionEffect(SlimeBreeder.proxy.slimeHunger) == null)
        {
        	double x1 = entityIn.posX;
        	double y1 = entityIn.posY;
        	double z1 = entityIn.posZ;
        	
        	this.setInLove(null);
        	this.setPosition(x1, y1, z1);
        	this.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30, 5));
        	((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30, 5));
        	//EntityAgeable slimeBaby = createChild((EntityAgeable) this);
        	//((EntityColorSlimeBase) slimeBaby).customSetSize(((EntityColorSlimeBase) slimeBaby).getSizeX()*(float)2, ((EntityColorSlimeBase) slimeBaby).getSizeY()*(float)2);
        	//slimeBaby.setPosition(this.posX, this.posY, this.posZ);
        	
        	this.setHunger(this.getHunger()-700);
        	((EntityColorSlimeBase) entityIn).setHunger(((EntityColorSlimeBase) entityIn).getHunger()-700);
        	
        }
        
       
    }
    
    public void addItemSlimeFloor(EntityItem itemstack)
    {
    	EnumCraftingIngredients.listOfItems.clear();
    	EnumCraftingIngredients.initItemList();
    	
    	
    	
    	ItemStack dropitemstack = ((EntityItem) itemstack).getItem();
    	ItemStack dropitemstackcopy = itemstack.getItem().copy();
		 
		 NBTTagCompound SerialDrop =  dropitemstack.serializeNBT();
		 NBTTagCompound SerialCopy =  dropitemstackcopy.serializeNBT();
		 
		 
		 
		 SerialCopy.setByte("Count", (byte)1);
		 ItemStack newitemstack = new ItemStack(SerialCopy);
		 
		 
		 
		 
		 ////System.out.println("ItemStack before = " + itemstack);
		 ////System.out.println("ItemStack after  = " + newitemstack);
		 //newitemstack.getTagCompound().setByte("Count",(byte)1);
		 
		
		 
		 int length = EnumCraftingIngredients.listOfItems.size();
		 ////System.out.println("length = " + length);
 for (int i = 0; i < length; i++) 
	{
		 ////System.out.println("length = " + length);
		 if(getSlimeItemSlot1() == -1 || getSlimeItemSlot1() == -1 && newitemstack.getItem() instanceof ItemBlock )
		 {
			 //if(part1.compareTo("minecraft") == 0)
			 //{
			 this.itemId1 =  Item.getIdFromItem(newitemstack.getItem());
			 this.processItemSlot1 = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
			 this.processItemSlot1Max = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
			 
			 setSlimeItemSlot1(this.itemId1);
			 this.dataManager.set(ITEMSLOT1, this.itemId1);
			 
			 setItemProcessSlot1(this.processItemSlot1);
			 this.dataManager.set(ITEMPROCESSSLOT1, this.processItemSlot1);
			 
			 setItemProcessSlot1Max(this.processItemSlot1Max);
			 this.dataManager.set(ITEMPROCESSSLOT1MAX, this.processItemSlot1Max);
			 
			 this.setSlot(newitemstack, 0);
			 
			 ////System.out.println("added Item 1");
			 byte Count = SerialDrop.getByte("Count");
			 SerialDrop.setByte("Count", (byte) ((byte)Count-1));
			 itemstack.setItem((new ItemStack(SerialDrop)));
			 
			 
			 i = length;
			 
			 
			 /*NBTTagCompound slotSerial = itemstack.serializeNBT();

			 this.SlotOne = slotSerial;*/
				 
			 
			 
			 //this.SlotOne = slotSerial;
			 
			 
			 
		}else if(getSlimeItemSlot2() == -1 && newitemstack.getItem() instanceof ItemBlock == false && new ItemStack(SlotOne).getItem() instanceof ItemBlock == false)
		 {
			 
			 this.itemId2 =  Item.getIdFromItem(newitemstack.getItem());
			 this.processItemSlot2 = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
			 this.processItemSlot2Max = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
			 
			 setSlimeItemSlot2(this.itemId2);
			 this.dataManager.set(ITEMSLOT2, this.itemId2);
			 
			 setItemProcessSlot2(this.processItemSlot2);
			 this.dataManager.set(ITEMPROCESSSLOT2, this.processItemSlot2);
			 
			 setItemProcessSlot2Max(this.processItemSlot2Max);
			 this.dataManager.set(ITEMPROCESSSLOT2MAX, this.processItemSlot2Max);

			 this.setSlot(newitemstack, 1);
			 ////System.out.println("added Item 2");
			 
			 byte Count = SerialDrop.getByte("Count");
			 SerialDrop.setByte("Count", (byte) ((byte)Count-1));
			 itemstack.setItem((new ItemStack(SerialDrop)));
			 
			 i = length;
			
			  
			 
		 }else if(getSlimeItemSlot3() == -1 && newitemstack.getItem() instanceof ItemBlock == false && new ItemStack(SlotOne).getItem() instanceof ItemBlock == false)
		 {
			 
			 this.itemId3 =  Item.getIdFromItem(newitemstack.getItem());
			 this.processItemSlot3 = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
			 this.processItemSlot3Max = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
			 
			 setSlimeItemSlot3(this.itemId3);
			 this.dataManager.set(ITEMSLOT3, this.itemId3);
			 
			 setItemProcessSlot3(this.processItemSlot3);
			 this.dataManager.set(ITEMPROCESSSLOT3, this.processItemSlot3);
			 
			 setItemProcessSlot3Max(this.processItemSlot3Max);
			 this.dataManager.set(ITEMPROCESSSLOT3MAX, this.processItemSlot3Max);
			 
			 this.setSlot(newitemstack, 2);
			 ////System.out.println("added Item 3");
			 
			 byte Count = SerialDrop.getByte("Count");
			 SerialDrop.setByte("Count", (byte) ((byte)Count-1));
			 itemstack.setItem((new ItemStack(SerialDrop)));
			 
			 i = length;
			 
			 
			 
			 
			 
		 }else if(getSlimeItemSlot4() == -1 && newitemstack.getItem() instanceof ItemBlock == false && new ItemStack(SlotOne).getItem() instanceof ItemBlock == false)
		 {

			 this.itemId4 =  Item.getIdFromItem(newitemstack.getItem());
			 this.processItemSlot4 = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
			 this.processItemSlot4Max = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
			 
			 setSlimeItemSlot4(this.itemId4);
			 this.dataManager.set(ITEMSLOT4, this.itemId4);
			 
			 setItemProcessSlot4(this.processItemSlot4);
			 this.dataManager.set(ITEMPROCESSSLOT4, this.processItemSlot4);
			 
			 setItemProcessSlot4Max(this.processItemSlot4Max);
			 this.dataManager.set(ITEMPROCESSSLOT4MAX, this.processItemSlot4Max);
			 

			 this.setSlot(newitemstack, 3);
			////System.out.println("added Item 4");
			 
			 byte Count = SerialDrop.getByte("Count");
			 SerialDrop.setByte("Count", (byte) ((byte)Count-1));
			 itemstack.setItem((new ItemStack(SerialDrop)));
			 
			 i = length;
			
			 
			 
		 }else if(getSlimeItemSlot5() == -1 && newitemstack.getItem() instanceof ItemBlock == false && new ItemStack(SlotOne).getItem() instanceof ItemBlock == false)
		 {
			 this.itemId5 =  Item.getIdFromItem(newitemstack.getItem());
			 this.processItemSlot5 = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
			 this.processItemSlot5Max = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
			 
			 setSlimeItemSlot5(this.itemId5);
			 this.dataManager.set(ITEMSLOT5, this.itemId5);
			 
			 setItemProcessSlot5(this.processItemSlot5);
			 this.dataManager.set(ITEMPROCESSSLOT5, this.processItemSlot5);
			 
			 setItemProcessSlot5Max(this.processItemSlot5Max);
			 this.dataManager.set(ITEMPROCESSSLOT5MAX, this.processItemSlot5Max);
			 

			 this.setSlot(newitemstack, 4);
			 ////System.out.println("added Item 5");
			 
			 byte Count = SerialDrop.getByte("Count");
			 SerialDrop.setByte("Count", (byte) ((byte)Count-1));
			 itemstack.setItem((new ItemStack(SerialDrop)));
			 
			 i = length;
		 }
		 
		}
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    public void onCollideWithPlayer(EntityPlayer entityIn)
    {
    	/*int i = this.getSlimeSize();
    	
    	EntityPlayer player = (EntityPlayer) entityIn;
		Iterable<ItemStack> armour = entityIn.getArmorInventoryList();
		
		List<Item> armourList = new ArrayList<Item>();
    	List<Item> fullarmourList = new ArrayList<Item>();
    	List<Item> comparmourList = new ArrayList<Item>();
    	
    	armourList.clear();
		fullarmourList.clear();
		comparmourList.clear();
		
		fullarmourList.add(SlimeBreederItems.SLIMEINFUSEDHELM);
		fullarmourList.add(SlimeBreederItems.SLIMEINFUSEDCHEST);
		fullarmourList.add(SlimeBreederItems.SLIMEINFUSEDLEGGINGS);
		fullarmourList.add(SlimeBreederItems.SLIMEINFUSEDBOOTS);
		
		for(ItemStack stack : armour)
		{
			if(stack != null)
			{
			armourList.add(stack.getItem());
			}
		}
    	fullarmourList.removeAll(armourList);
    	
    	//System.out.println("No of Armour Pieces = " + (4-fullarmourList.size()));
    	
    	if(fullarmourList.size() != 0)
    	{
    		if (this.canDamagePlayer() && this.getDistanceSqToEntity(entityIn) < 0.9D * (double)i * 0.9D * (double)i)
            {
            	entityIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30, this.getSticky()));
            	////System.out.println("Damage Player");
                this.dealDamage(entityIn);
            }	
    	}
    	
    	if(fullarmourList.size() == 0)
    	{
    		
    		for(ItemStack stack : armour)
    		{
    			Integer integer = Integer.valueOf((int) Math.round(((stack.getItemDamage()*0.10)-((stack.getItemDamage()*0.10)*(stack.getItemDamage()/stack.getMaxDamage())))));
    			
    			stack.setItemDamage(integer);
    			
    		}
    		
    	}*/
    	
        
    }

    protected void dealDamage(EntityLivingBase entityIn)
    {

        
        //System.out.println("Deal damage ?");

        if (this.getDistanceSq(entityIn) < 0.9D * (double)2 * 0.9D * (double)2)
        {
            this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackDmg());
            //System.out.println("Deal damage!");
        }
    }


    /**
     * Indicates weather the slime is able to damage the player (based upon the slime's size)
     */

    /**
     * Gets the amount of damage dealt to the player when "attacked" by the slime.
     */
    protected int getAttackStrength()
    {
        return this.getAttackDmg();
    }


    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
    	
    	
    	
    	////System.out.println("drop few items Drops");
    	ItemStack Slot1 = new ItemStack(SlotOne).copy();
    	ItemStack Slot2 = new ItemStack(SlotTwo).copy();
    	ItemStack Slot3 = new ItemStack(SlotThree).copy();
    	ItemStack Slot4 = new ItemStack(SlotFour).copy();
    	ItemStack Slot5 = new ItemStack(SlotFive).copy();
    	
    	ItemStack SlotD = new ItemStack(SlotDrop).copy();
    	ItemStack SlotR = new ItemStack(SlotResult).copy();
    	
    	
    	////System.out.println("Drop 1 = " + SlotD);
    	if(Slot1 != new ItemStack(Items.SLIME_BALL))
    	{
    		if(Slot1.getItem() == new ItemStack(SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL).getItem()) {
    			NBTTagCompound tag = Slot1.getTagCompound();
    			tag.setFloat("charge",(tag.getFloat("charge")+0.10F));
				tag.setString("chargecolor", "green");
    		}
    		
    	entityDropItem(Slot1, 1.0F);
    	}
    	
    	if(Slot2 != new ItemStack(Items.SLIME_BALL))
    	{
    		if(Slot2.getItem() == new ItemStack(SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL).getItem()) {
    			NBTTagCompound tag = Slot2.getTagCompound();
    			tag.setFloat("charge",(tag.getFloat("charge")+0.10F));
				tag.setString("chargecolor", "green");
    		}
    	entityDropItem(Slot2, 1.0F);
    	}
    	
    	if(Slot3 != new ItemStack(Items.SLIME_BALL))
    	{
    		if(Slot3.getItem() == new ItemStack(SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL).getItem()) {
    			NBTTagCompound tag = Slot3.getTagCompound();
    			tag.setFloat("charge",(tag.getFloat("charge")+0.10F));
				tag.setString("chargecolor", "green");
    		}
    	entityDropItem(Slot3, 1.0F);
    	}
    	
    	if(Slot4 != new ItemStack(Items.SLIME_BALL))
    	{
    		if(Slot4.getItem() == new ItemStack(SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL).getItem()) {
    			NBTTagCompound tag = Slot4.getTagCompound();
    			tag.setFloat("charge",(tag.getFloat("charge")+0.10F));
				tag.setString("chargecolor", "green");
    		}
    	entityDropItem(Slot4, 1.0F);
    	}
    	
    	if(Slot5 != new ItemStack(Items.SLIME_BALL))
    	{
    		if(Slot5.getItem() == new ItemStack(SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL).getItem()) {
    			NBTTagCompound tag = Slot5.getTagCompound();
    			tag.setFloat("charge",(tag.getFloat("charge")+0.10F));
				tag.setString("chargecolor", "green");
    		}
    	entityDropItem(Slot5, 1.0F);
    	}
    	
    	if(SlotR != new ItemStack(Items.SLIME_BALL))
    	{
    		if(SlotR.getItem() == new ItemStack(SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL).getItem()) {
    			NBTTagCompound tag = SlotR.getTagCompound();
    			tag.setFloat("charge",(tag.getFloat("charge")+0.10F));
				tag.setString("chargecolor", "green");
    		}
    	entityDropItem(SlotR, 1.0F);
    	}
    	
    	if(SlotD.getItem() == new ItemStack(SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL).getItem()) {
			NBTTagCompound tag = Slot1.getTagCompound();
			tag.setFloat("charge",(tag.getFloat("charge")+0.10F));
			tag.setString("chargecolor", "green");
		}
    	entityDropItem(SlotD, 1.0F);
	
    }
    
    protected Item getDropItem()
    {
    	////System.out.println("drop items Drops");
    	////System.out.println("Drops");
    	EnumItemColor.listOfObjects.clear();
    	EnumItemColor.initColorList();
    	if(!world.isRemote)
    	{
    	int length = EnumItemColor.listOfObjects.size();
    	//////System.out.println("indexl1 = " + length);
    	for (int i = 0; i < length; i++) {
    		
    		int Iwave = EnumItemColor.listOfObjects.get(i).getWave();
    		float Isat = EnumItemColor.listOfObjects.get(i).getSat();
    		float Ibrightness = EnumItemColor.listOfObjects.get(i).getBright();
    		/*//System.out.println("indexl1 = " + EnumItemColor.getSize());
    		//System.out.println("indexl2 = " +length);
    		//System.out.println("index = " + i);
    		//System.out.println("Lwave = " + (Lwave-10) + " " + this.wave + " " + (Lwave+10));
    		//System.out.println("Lsat = " + (Lsat-0.1F) + " " + this.sat + " " + (Lsat+0.1F));
    		//System.out.println("Lbrightness = " + (Lbrightness-0.1F) +  " " + this.brightness + " " + (Lbrightness+0.1F));*/
    		
    	if((this.wave > (Iwave-10) && (this.wave < (Iwave+10))))
    		if((this.sat > (Isat-0.1F)) && (this.sat < (Isat+0.1F)))
    			if((this.brightness > (Ibrightness-0.1F)) && (this.brightness < (Ibrightness+0.1F)))
    				if(!world.isRemote)
    				{
    				entityDropItem(EnumItemColor.listOfObjects.get(i).getItemStack().copy(), 1F);
    				////System.out.println("item called = " + EnumItemColor.listOfObjects.get(i).getItemStack());
    				////System.out.println(EnumItemColor.getSize());
    				i = EnumItemColor.listOfObjects.size();
    				
    				}
    				
    			}
    		
    	////System.out.println("finished");
    	EnumItemColor.listOfObjects.clear();
    	}
    	return Items.SLIME_BALL;
    	}

    @Nullable
    protected ResourceLocation getLootTable()
    {
    	////System.out.println("LootTable Drops");
    	setCraftResult();
    	//stackSlots();
    	
    	/*if(SlotResult != SlotSlime)
    	{
    		//System.out.println("Got Result");
    		
    		this.SlotOne = SlotSlime;
    		this.SlotTwo = SlotSlime;
    		this.SlotThree = SlotSlime;
    		this.SlotFour = SlotSlime;
    		this.SlotFive = SlotSlime;
    		
    	}*/
    	

    	
    	ItemStack Slot1 = new ItemStack(SlotOne).copy();
    	
    	ItemStack Slot2 = new ItemStack(SlotTwo).copy();
    	ItemStack Slot3 = new ItemStack(SlotThree).copy();
    	ItemStack Slot4 = new ItemStack(SlotFour).copy();
    	ItemStack Slot5 = new ItemStack(SlotFive).copy();
    	
    	ItemStack SlotD = new ItemStack(SlotDrop).copy();
    	ItemStack SlotR = new ItemStack(SlotResult).copy();
    	
    	
    	NBTTagCompound SlotCrystal =  new ItemStack(SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL).serializeNBT();
    	
    	String slimeColor = this.getChargeColor();
    	Double chargeValue;
    	
    	
    	
    	////System.out.println("Drop 1 = " + SlotD);
    	if(SlotOne != SlotSlime)
    	{
    		if(Slot1.getItem() == SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL) {
    			
    			NBTTagCompound nbt = Slot1.getTagCompound();
    			
    			
    			
    			if(nbt.getString("chargecolor") == "clear") {
    				nbt.setString("chargecolor", slimeColor);
    			}
    			chargeValue = setChargeValue(nbt.getString("chargecolor"));
    			System.out.println("Charge Value = " + chargeValue);
    			nbt.setInteger("charge", (int) (nbt.getInteger("charge")+chargeValue));
    			nbt.setBoolean("isCharging", true);
    			
    			if(Slot1.getItemDamage()-chargeValue <= 0) {
    				if(nbt.getString("chargecolor") == "Red") {
    					Slot1 = new ItemStack(SlimeBreederItems.REDSLIMECRYSTAL);
    				}
    				if(nbt.getString("chargecolor") == "Blue") {
    					Slot1 = new ItemStack(SlimeBreederItems.BLUESLIMECRYSTAL);
    				}
    				if(nbt.getString("chargecolor") == "Green") {
    					Slot1 = new ItemStack(SlimeBreederItems.GREENSLIMECRYSTAL);
    				}
    				
    			} 
    			
    		}
    	entityDropItem(Slot1, 1.0F);
    	}
    	
    	if(SlotTwo != SlotSlime)
    	{
				if(Slot2.getItem() == SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL) {
				    			
				    			NBTTagCompound nbt = Slot2.getTagCompound();
				    			
				    			if(nbt.getString("chargecolor") == "clear") {
				    				nbt.setString("chargecolor", slimeColor);
				    			}
				    			chargeValue = setChargeValue(nbt.getString("chargecolor"));
				    			System.out.println("Charge Value = " + chargeValue);
				    			nbt.setInteger("charge", (int) (nbt.getInteger("charge")+chargeValue));
				    			nbt.setBoolean("isCharging", true);	
				    			
				    			if(Slot2.getItemDamage()-chargeValue <= 0) {
				    				if(nbt.getString("chargecolor") == "Red") {
				    					Slot2 = new ItemStack(SlimeBreederItems.REDSLIMECRYSTAL);
				    				}
				    				if(nbt.getString("chargecolor") == "Blue") {
				    					Slot2 = new ItemStack(SlimeBreederItems.BLUESLIMECRYSTAL);
				    				}
				    				if(nbt.getString("chargecolor") == "Green") {
				    					Slot2 = new ItemStack(SlimeBreederItems.GREENSLIMECRYSTAL);
				    				}
				    				
				    			} 
				    		}
    	entityDropItem(Slot2, 1.0F);
    	}
    	
    	if(SlotThree != SlotSlime)
    	{
				if(Slot3.getItem() == SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL) {
				    			
				    			NBTTagCompound nbt = Slot3.getTagCompound();
				    			
				    			if(nbt.getString("chargecolor") == "clear") {
				    				nbt.setString("chargecolor", slimeColor);
				    			}
				    			chargeValue = setChargeValue(nbt.getString("chargecolor"));
				    			System.out.println("Charge Value = " + chargeValue);
				    			nbt.setInteger("charge", (int) (nbt.getInteger("charge")+chargeValue));
				    			nbt.setBoolean("isCharging", true);	
				    			
				    			if(Slot3.getItemDamage()-chargeValue <= 0) {
				    				if(nbt.getString("chargecolor") == "Red") {
				    					Slot3 = new ItemStack(SlimeBreederItems.REDSLIMECRYSTAL);
				    				}
				    				if(nbt.getString("chargecolor") == "Blue") {
				    					Slot3 = new ItemStack(SlimeBreederItems.BLUESLIMECRYSTAL);
				    				}
				    				if(nbt.getString("chargecolor") == "Green") {
				    					Slot3 = new ItemStack(SlimeBreederItems.GREENSLIMECRYSTAL);
				    				}
				    				
				    			} 
				    		}
    	entityDropItem(Slot3, 1.0F);
    	}
    	
    	if(SlotFour != SlotSlime)
    	{
				if(Slot4.getItem() == SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL) {
				    			
				    			NBTTagCompound nbt = Slot4.getTagCompound();
				    			
				    			if(nbt.getString("chargecolor") == "clear") {
				    				nbt.setString("chargecolor", slimeColor);
				    			}
				    			chargeValue = setChargeValue(nbt.getString("chargecolor"));
				    			System.out.println("Charge Value = " + chargeValue);
				    			nbt.setInteger("charge", (int) (nbt.getInteger("charge")+chargeValue));
				    			nbt.setBoolean("isCharging", true);	
				    			
				    			if(Slot4.getItemDamage()-chargeValue <= 0) {
				    				if(nbt.getString("chargecolor") == "Red") {
				    					Slot4 = new ItemStack(SlimeBreederItems.REDSLIMECRYSTAL);
				    				}
				    				if(nbt.getString("chargecolor") == "Blue") {
				    					Slot4 = new ItemStack(SlimeBreederItems.BLUESLIMECRYSTAL);
				    				}
				    				if(nbt.getString("chargecolor") == "Green") {
				    					Slot4 = new ItemStack(SlimeBreederItems.GREENSLIMECRYSTAL);
				    				}
				    				
				    			} 
				    		}
    	entityDropItem(Slot4, 1.0F);
    	}
    	
    	if(SlotFive != SlotSlime)
    	{
    		
				if(Slot5.getItem() == SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL) {
				    			
				    			NBTTagCompound nbt = Slot5.getTagCompound();
				    			
				    			if(nbt.getString("chargecolor") == "clear") {
				    				nbt.setString("chargecolor", slimeColor);
				    			}
				    			chargeValue = setChargeValue(nbt.getString("chargecolor"));
				    			System.out.println("Charge Value = " + chargeValue);
				    			nbt.setInteger("charge", (int) (nbt.getInteger("charge")+chargeValue));
				    			nbt.setBoolean("isCharging", true);	
				    			
				    			if(Slot5.getItemDamage()-chargeValue <= 0) {
				    				if(nbt.getString("chargecolor") == "Red") {
				    					Slot5 = new ItemStack(SlimeBreederItems.REDSLIMECRYSTAL);
				    				}
				    				if(nbt.getString("chargecolor") == "Blue") {
				    					Slot5 = new ItemStack(SlimeBreederItems.BLUESLIMECRYSTAL);
				    				}
				    				if(nbt.getString("chargecolor") == "Green") {
				    					Slot5 = new ItemStack(SlimeBreederItems.GREENSLIMECRYSTAL);
				    				}
				    				
				    			} 
				    		}
    	entityDropItem(Slot5, 1.0F);
    	}
    	
    	if(SlotResult != SlotSlime)
    	{
			if(SlotR.getItem() == SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL) {
			    			
			    			NBTTagCompound nbt = SlotR.getTagCompound();
			    			
			    			if(nbt.getString("chargecolor") == "clear") {
			    				nbt.setString("chargecolor", slimeColor);
			    			}
			    			chargeValue = setChargeValue(nbt.getString("chargecolor"));
			    			System.out.println("Charge Value = " + chargeValue);
			    			nbt.setInteger("charge", (int) (nbt.getInteger("charge")+chargeValue));
			    			nbt.setBoolean("isCharging", true);	
			    			
			    			if(SlotR.getItemDamage()-chargeValue <= 0) {
			    				if(nbt.getString("chargecolor") == "Red") {
			    					SlotR = new ItemStack(SlimeBreederItems.REDSLIMECRYSTAL);
			    				}
			    				if(nbt.getString("chargecolor") == "Blue") {
			    					SlotR = new ItemStack(SlimeBreederItems.BLUESLIMECRYSTAL);
			    				}
			    				if(nbt.getString("chargecolor") == "Green") {
			    					SlotR = new ItemStack(SlimeBreederItems.GREENSLIMECRYSTAL);
			    				}
			    				
			    			} 
			    			
			    		}
    	entityDropItem(SlotR, 1.0F);
    	}
		    	if(SlotD.getItem() == SlimeBreederItems.UNCHARGEDCLEARSLIMECRYSTAL) {
					
					NBTTagCompound nbt = SlotD.getTagCompound();
	    			
	    			if(nbt.getString("chargecolor") == "clear") {
	    				nbt.setString("chargecolor", slimeColor);
	    			}
	    			chargeValue = setChargeValue(nbt.getString("chargecolor"));
	    			System.out.println("Charge Value = " + chargeValue);
	    			nbt.setInteger("charge", (int) (nbt.getInteger("charge")+chargeValue));
	    			nbt.setBoolean("isCharging", true);	
	    			
	    			if(SlotD.getItemDamage()-chargeValue <= 0) {
	    				if(nbt.getString("chargecolor") == "Red") {
	    					SlotD = new ItemStack(SlimeBreederItems.REDSLIMECRYSTAL);
	    				}
	    				if(nbt.getString("chargecolor") == "Blue") {
	    					SlotD = new ItemStack(SlimeBreederItems.BLUESLIMECRYSTAL);
	    				}
	    				if(nbt.getString("chargecolor") == "Green") {
	    					SlotD = new ItemStack(SlimeBreederItems.GREENSLIMECRYSTAL);
	    				}
	    				
	    			} 
		}
    	entityDropItem(SlotD, 1.0F);
    	
        return LootTableList.ENTITIES_SLIME;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {


        if (this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.getEntityWorld().isRaining() || this.world.getDifficulty() != EnumDifficulty.PEACEFUL && SlimeBreeder.weather2)
        {
            
        	List<EntityLiving> list = this.getEntityWorld().getEntitiesWithinAABB(EntityLiving.class, this.getEntityBoundingBox().grow(1280.0D));
        	List<EntityLiving> slimelist = new ArrayList<EntityLiving>();
        	double d0 = 1.0D;
        	
        	BlockPos pos0 = new BlockPos(this.posX, (this.posY-1), this.posZ);
			IBlockState iblockstate = this.getEntityWorld().getBlockState(pos0);
			Block block = iblockstate.getBlock();
        	
        	for (EntityLiving entityliving : list)
            {
        		if(entityliving instanceof EntityColorSlimeBase)
        		{
        			EntityColorSlimeBase slime = (EntityColorSlimeBase)entityliving;
        			if(slime.getBreedingGroup() == 0)
        			{
        				slimelist.add(slime);
        			}
        		}
        			
        		////System.out.println(list.size());
                if (slimelist.size() < 3)
                {
                	////System.out.println("Spawned " + this.posX + " " + this.posY + " " + this.posZ + " Group Size= " + slimelist.size());
                	if(block == Blocks.GRASS)
                	{
                		return super.getCanSpawnHere();	
                	}
                }
            }
        	
        }

            /*if (this.getEntityWorld().isRaining())
            {
            	
            	
            	
                return super.getCanSpawnHere();
            }

        }*/
        ////System.out.println("Too Close");
        return false;
    }

    public String getChargeColor() {
    	
    	 int red;
    	 int green;
    	 int blue;
    	 int c = 255;
    	 
    	 int w = this.getWave();
    	 
    	
    	
    	 if((w >= 380) && (w<440)){
    	    	float a = (-(w - 440)/(((float)440) - ((float)380)));
    	    	////System.out.println(a);
    	    	////System.out.println(wave);
    	        red = (int) (c * a);
    	        green = 0;
    	        blue = 1 * c;
    	        ////System.out.println("Set Red = " + red);
    	        ////System.out.println("Set Green = " + green);
    	       ////System.out.println("Set Blue = " + blue);
    	        //float vs[]={this.Hue, this.sat, this.brightness};
    	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
    	        
    	        
    	        
    	    }else if((w >= 440) && (w<490)){
    	    	float b = ((w - 440)/(((float)490 - ((float)440))));
    	    	////System.out.println(b);
    	    	////System.out.println(wave);
    	        red = 0;
    	        green = (int) (c * b);
    	        blue = 1 * c;
    	        ////System.out.println("Set Red = " + red);
    	        ////System.out.println("Set Green = " + green);
    	        ////System.out.println("Set Blue = " + blue);
    	        //float vs[]={this.Hue, this.sat, this.brightness};
    	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
    	        
    	        
    	    }else if((w >= 490) && (w<510)){
    	    	float c2 = (-(w - 510)/(((float)510 - ((float)490))));
    	    	////System.out.println(c);
    	    	////System.out.println(wave);
    	        red = 0;
    	        green = 1 * c;
    	        blue = (int) (c * c2);
    	        ////System.out.println("Set Red = " + red);
    	        ////System.out.println("Set Green = " + green);
    	        ////System.out.println("Set Blue = " + blue);
    	        //float vs[]={this.Hue, this.sat, this.brightness};
    	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
    	        
    	    }else if((w >= 510) && (w<580)){
    	    	float d = ((w - 510)/(((float)580 - ((float)510))));
    	    	////System.out.println(d);
    	    	////System.out.println(wave);
    	        red = (int) (c * d);
    	        green = 1 * c;
    	        blue = 0;
    	        /////System.out.println("Set Red = " + red);
    	        ////System.out.println("Set Green = " + green);
    	       ////System.out.println("Set Blue = " + blue););
    	        //float vs[]={this.Hue, this.sat, this.brightness};
    	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
    	        
    	        
    	    }else if((w >= 580) && (w<645)){
    	    	float e = (-(w - 645)/(((float)645 - 580)));
    	    	////System.out.println(e);
    	    	////System.out.println(wave);
    	        red = 1 * c;
    	        green = (int) (c * e);
    	        blue = 0;
    	        ////System.out.println("Set Red = " + red);
    	        ////System.out.println("Set Green = " + green);
    	        ////System.out.println("Set Blue = " + blue);
    	        //float vs[]={this.Hue, this.sat, this.brightness};
    	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
    	        
    	    }else if((w >= 645) && (w<781)){
    	        red = 1 * c;
    	        green = 0;
    	        blue = 0;
    	        ////System.out.println("Set Red = " + red);
    	        ////System.out.println("Set Green = " + green);
    	        ////System.out.println("Set Blue = " + blue);
    	        //float vs[]={this.Hue, this.sat, this.brightness};
    	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
    	        ////System.out.println(wave);
    	       
    	        
    	    }else{
    	        red = 0;
    	        green = 0;
    	        blue = 0;
    	        ////System.out.println("Set Red = " + red);
    	        ////System.out.println("Set Green = " + green);
    	        ////System.out.println("Set Blue = " + blue);

    	        //this.vs={this.Hue, this.sat, this.brightness};
    	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
    	        ////System.out.println(wave);
    	      
    	    }
    	 
    	 if(red >= green && red >= blue) {
    		 return "Red";
    		 
    	 }
    	 
    	 if(green >= red && green >= blue) {
    		 return "Green";
    		 
    	 }
    	 
    	 if(blue >= green && blue >= red) {
    		 return "Blue";
    		 
    	 }
		return "clear";
    	    
    		
    	    //float[] HSB = Color.RGBtoHSB(red, green, blue, this.hsbvals);
    	    ////System.out.println("Red = " + red);
            ////System.out.println("Green = " + green);
            ////System.out.println("Blue = " + blue);
            
    	    /*float[] hsb = Color.RGBtoHSB(red, green, blue, null);

    	    
    	    this.Hue = hsb[0];
    	    this.sat = hsb[1];
    	    this.brightness = hsb[2];*/
    	
    }
    
    
    public Double setChargeValue(String color) {
    	
		
		double chargeValue = 0;
		
		
		
		
   	 int red;
   	 int green;
   	 int blue;
   	 int c = 255;
   	 
   	 int w = this.getWave();
   	 
   	
   	
   	 if((w >= 380) && (w<440)){
   	    	float a = (-(w - 440)/(((float)440) - ((float)380)));
   	    	////System.out.println(a);
   	    	////System.out.println(wave);
   	        red = (int) (c * a);
   	        green = 0;
   	        blue = 1 * c;
   	        ////System.out.println("Set Red = " + red);
   	        ////System.out.println("Set Green = " + green);
   	       ////System.out.println("Set Blue = " + blue);
   	        //float vs[]={this.Hue, this.sat, this.brightness};
   	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
   	        
   	        
   	        
   	    }else if((w >= 440) && (w<490)){
   	    	float b = ((w - 440)/(((float)490 - ((float)440))));
   	    	////System.out.println(b);
   	    	////System.out.println(wave);
   	        red = 0;
   	        green = (int) (c * b);
   	        blue = 1 * c;
   	        ////System.out.println("Set Red = " + red);
   	        ////System.out.println("Set Green = " + green);
   	        ////System.out.println("Set Blue = " + blue);
   	        //float vs[]={this.Hue, this.sat, this.brightness};
   	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
   	        
   	        
   	    }else if((w >= 490) && (w<510)){
   	    	float c2 = (-(w - 510)/(((float)510 - ((float)490))));
   	    	////System.out.println(c);
   	    	////System.out.println(wave);
   	        red = 0;
   	        green = 1 * c;
   	        blue = (int) (c * c2);
   	        ////System.out.println("Set Red = " + red);
   	        ////System.out.println("Set Green = " + green);
   	        ////System.out.println("Set Blue = " + blue);
   	        //float vs[]={this.Hue, this.sat, this.brightness};
   	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
   	        
   	    }else if((w >= 510) && (w<580)){
   	    	float d = ((w - 510)/(((float)580 - ((float)510))));
   	    	////System.out.println(d);
   	    	////System.out.println(wave);
   	        red = (int) (c * d);
   	        green = 1 * c;
   	        blue = 0;
   	        /////System.out.println("Set Red = " + red);
   	        ////System.out.println("Set Green = " + green);
   	       ////System.out.println("Set Blue = " + blue);
   	        //float vs[]={this.Hue, this.sat, this.brightness};
   	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
   	        
   	        
   	    }else if((w >= 580) && (w<645)){
   	    	float e = (-(w - 645)/(((float)645 - 580)));
   	    	////System.out.println(e);
   	    	////System.out.println(wave);
   	        red = 1 * c;
   	        green = (int) (c * e);
   	        blue = 0;
   	        ////System.out.println("Set Red = " + red);
   	        ////System.out.println("Set Green = " + green);
   	        ////System.out.println("Set Blue = " + blue);
   	        //float vs[]={this.Hue, this.sat, this.brightness};
   	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
   	        
   	    }else if((w >= 645) && (w<781)){
   	        red = 1 * c;
   	        green = 0;
   	        blue = 0;
   	        ////System.out.println("Set Red = " + red);
   	        ////System.out.println("Set Green = " + green);
   	        ////System.out.println("Set Blue = " + blue);
   	        //float vs[]={this.Hue, this.sat, this.brightness};
   	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
   	        ////System.out.println(wave);
   	       
   	        
   	    }else{
   	        red = 0;
   	        green = 0;
   	        blue = 0;
   	        ////System.out.println("Set Red = " + red);
   	        ////System.out.println("Set Green = " + green);
   	        ////System.out.println("Set Blue = " + blue);
   	        //this.vs={this.Hue, this.sat, this.brightness};
   	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
   	        ////System.out.println(wave);
   	      
   	    }
		

			if(color == "Red"){
				chargeValue = (((double)red/(double)255))*25;
				System.out.println("red = " + red);
				System.out.println("red CV = " + chargeValue);
			}
			
			if(color == "Blue"){
				chargeValue =(((double)blue/(double)255))*25;
				System.out.println("blue = " + blue);
			}
			
			if(color == "Green"){
				chargeValue = (((double)green/(double)255))*25;
				System.out.println("green = " + green);
			}
			System.out.println("CV = " + chargeValue);
				return chargeValue;
    	
    }
    
    
    
    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F * 1.0F;
    }

    /**
     * The speed it takes to move the entityliving's rotationPitch through the faceEntity method. This is only currently
     * use in wolves.
     */
    public int getVerticalFaceSpeed()
    {
        return 0;
    }

    /**
     * Returns true if the slime makes a sound when it jumps (based upon the slime's size)
     */
    protected boolean makesSoundOnJump()
    {
        return true;
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    protected void jump()
    {
        this.motionY = 0.41999998688697815D;
        this.isAirBorne = true;
        finishProcess();
        /*//System.out.println("Hunger = " + this.getHunger());
        //System.out.println("Max Hunger = " + (this.getBreedingSpeed()/2));
        //System.out.println("Domestication = " + this.getDomestication());*/
        
        if(this.getHunger() > 0 && this.getBreedingGroup() != 0)
        {
        this.setHunger(hunger-5);
        }
        
        if(this.getTimer() < 50)
        {
        this.setTimer(interactTimer + 1);
        }
        ////System.out.println("Despawn ? = " + this.canDespawn());
        ////System.out.println("Wave = " + this.wave);
        //}
        
        if(this.getDomestication() >= 5000 && this.getTimer() >= 36)
        {
        	
        	////System.out.println("Hearts!");
        	
	        	
        	if(!world.isRemote) 
        	{
        	WorldServer worldserver = (WorldServer) world;
        	worldserver.spawnParticle(EnumParticleTypes.HEART, false, posX + 0.5D, posY+ 1.0D, posZ+ 0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D, new int[0]); 
        	}
	        	
	        this.setTimer(26);
        	
        }
        
        if(this.getHunger() < (this.getBreedingSpeed()/2)*0.25)
        {
        	if(this.getDomestication() > 10)
        	{
        	this.setDomestication(domesticated-1);
        	}
        }
        
        
        
        
        slimeIndicatorJump();
        
        
        /*if(this.getDomestication() > 500)
        {
        	this.targetTasks.addTask(1, new EntityAIFindNearestBreeder(this, this.getDomestication()));
        }*/
        
        //this.targetTasks.removeTask(new EntityAIFindNearestBreeder(this, this.getDomestication()));
        //this.targetTasks.addTask(1, new EntityAIFindNearestBreeder(this, this.getDomestication()));
       
        
    }

    private void finishProcess() 
    {
    	/*//System.out.println("buffer Slot 1 = " + bufferInventory[0]);
    	//System.out.println("buffer Slot 2 = " + bufferInventory[1]);
    	//System.out.println("buffer Slot 3 = " + bufferInventory[2]);
    	//System.out.println("buffer Slot 4 = " + bufferInventory[3]);
    	//System.out.println("buffer Slot 5 = " + bufferInventory[4]);*/
    	
    	Item FutureResult = new ItemStack(SlotFutureResult).getItem();
    	Item NoResult = new ItemStack(SlotSlime).getItem();
    	
    	////System.out.println("Finish Process");
    	if(this.getServerItemProcessSlot1() < 0)
        {
    		/*if(this.itemId1 == Item.getIdFromItem(SlimeBreederItems.BREEDINGCATALYSTIRON))
    		{
    			this.breedinggroup = 1;
    		}*/
    		
    		
    		////System.out.println("Finish Slot One");
    		
    		bufferInventory[0] = this.itemId1;
        	this.itemId1 = -1;
        	this.dataManager.set(ITEMSLOT1, this.itemId1);
        	
        	processBuffer();
        	setCraftResult();
        	
        	
        	
        	
        	
        }
    	
    	if(this.getServerItemProcessSlot2() < 0)
        {
    		
    		
    		
    		bufferInventory[1] = this.itemId2;
        	this.itemId2 = -1;
        	this.dataManager.set(ITEMSLOT2, this.itemId2);
        	
        	processBuffer();
        	setCraftResult();
        	
        }
    	
    	if(this.getServerItemProcessSlot3() < 0)
        {
    		
    		
    		
    		bufferInventory[2] = this.itemId3;
        	this.itemId3 = -1;
        	this.dataManager.set(ITEMSLOT3, this.itemId3);
        	
        	processBuffer();
        	setCraftResult();
        	
        }
    	
    	if(this.getServerItemProcessSlot4() < 0)
        {
    		
    		
    		
    		bufferInventory[3] = this.itemId4;
        	this.itemId4 = -1;
        	this.dataManager.set(ITEMSLOT4, this.itemId4);
        	
        	processBuffer();
        	setCraftResult();
        	
        }
    	
    	if(this.getServerItemProcessSlot5() < 0)
        {
    		
    		
    		
    		bufferInventory[4] = this.itemId5;
        	this.itemId5 = -1;
        	this.dataManager.set(ITEMSLOT5, this.itemId5);
        	
        	processBuffer();
        	setCraftResult();
        	
        }
    	
    	if(FutureResult != NoResult && this.itemId1 == -1 && this.itemId2 == -1 && this.itemId3 == -1 && this.itemId4 == -1 && this.itemId5 == -1)
    	{
    		////System.out.println("Pass Through");
    		
    		
    		
    		
    		if(new ItemStack(SlotResult).getItem() == new ItemStack(SlotFutureResult).getItem())
    		{
    			////System.out.println("Duplicate");
    			NBTTagCompound SerialAddResult =  SlotResult.copy();

    			
    			EnumRecipes.listOfRecipes.clear();
		    	EnumRecipes.initRecipeList();
		    	
		    	////System.out.println(new ItemStack(SlotResult).getItem());
		    	
		    	int multiFactor = 1;
		    	
		    	if(SlotResult != null)
		    	{
		    		int index = SlotResult.getInteger("Recipe");
		    		    int length = EnumRecipes.listOfRecipes.size();
		        		
		        		if(new ItemStack(SlotResult).getItem() == EnumRecipes.listOfRecipes.get(index).getItem7().getItem())
		        		{
		        			
		        			byte currentResult = SlotFutureResult.getByte("Count");
		                	byte recipeResult = EnumRecipes.listOfRecipes.get(index).getItem7().serializeNBT().getByte("Count");
		                	
		                	multiFactor = currentResult/recipeResult;
		                	
		                	////System.out.println("Factor = " + multiFactor);
		        			
		        		}
		    		
		        	
		    	}
            	
            	int a = SlotFutureResult.getByte("Count");
            	int b = SlotResult.getByte("Count");
            	int c = a+b;
            	
            	////System.out.println("Additons = "  + a + " + " + b +  " + " + " = " + SerialAddResult.getByte("Count"));
    			 
    			 SerialAddResult.setByte("Count", (byte)c);
    			 SlotResult = SerialAddResult;
    			 
    			 this.SlotFutureResult = this.SlotSlime;
    			 
    		    	
    			 ////System.out.println("Factor = " + multiFactor);
    			 stackSlots(multiFactor);
    		}else
    		{
    			
    			EnumRecipes.listOfRecipes.clear();
		    	EnumRecipes.initRecipeList();
		    	
		    	////System.out.println("Not duplicate");
		    	////System.out.println(new ItemStack(SlotFutureResult));
		    	////System.out.println(new ItemStack(SlotResult));
		    	
		    	int multiFactor2 = 1;
		    	
		    	if(SlotResult != null)
		    	{

		    		addInfused(SlotFutureResult);
		    		addInfused(SlotResult);
		    		
		    		int index = SlotResult.getInteger("Recipe");
		    		
		    		
		        			int currentResult = SlotFutureResult.getByte("Count");
		                	int recipeResult = EnumRecipes.listOfRecipes.get(index).getItem7().serializeNBT().getByte("Count");
		                	
		                	multiFactor2 = currentResult/recipeResult;
		                	////System.out.println("Factor = " + multiFactor2);
		        			
		        		
		    		
		        	
		    	}
    			if(new ItemStack(SlotResult).getItem() == new ItemStack(SlotSlime).getItem())
    			{
    				////System.out.println("Result Empty");
	    			this.SlotResult = this.SlotFutureResult;
	        		this.SlotFutureResult = this.SlotSlime;	
	        		stackSlots(multiFactor2);
    			}else{
    				
    				
    				////System.out.println("Result Not Empty");
    				
    				/*//System.out.println(SlotResult.getKeySet());
    				
    				decompResult(SlotResult, 7);
    				this.SlotResult = this.SlotFutureResult;
	        		this.SlotFutureResult = this.SlotSlime;	*/
    				
    			}
    		}
    		
    		
    		
        			
        			if(SlotOne.getByte("Count") <= 0)
        			{
        				SlotOne = SlotSlime;
        			}
        			
        			if(SlotTwo.getByte("Count") <= 0)
        			{
        				SlotTwo = SlotSlime;
        			}
        			
        			if(SlotThree.getByte("Count") <= 0)
        			{
        				SlotThree = SlotSlime;
        			}
        			
        			if(SlotFour.getByte("Count") <= 0)
        			{
        				SlotFour = SlotSlime;
        			}
        			
        			if(SlotFive.getByte("Count") <= 0)
        			{
        				SlotFive = SlotSlime;
        			}
        			
        			//EnumRecipes.listOfRecipes.clear();
        		}
    		 }	
        		
    
    public void addInfused(NBTTagCompound slotInfused)
    {
    	if(new ItemStack(slotInfused).getItem() instanceof ItemArmor || new ItemStack(slotInfused).getItem() instanceof ItemSword || new ItemStack(slotInfused).getItem() instanceof ItemPickaxe || new ItemStack(slotInfused).getItem() instanceof ItemSpade || new ItemStack(slotInfused).getItem() instanceof ItemTool)
 		 {
 			 
 			 NBTTagCompound nbt = new ItemStack(slotInfused).getTagCompound();

 			 
 			 //////System.out.println("Coatable " + nbt.toString());
 			if(nbt != null)
 			{
	    			 if((nbt.getCompoundTag("slimebreeder").getKeySet().contains("core")))
	    			 {
	    				 
	    				 //System.out.println("Slime Infused");
	    				 
	    					NBTTagCompound nbtInfused = new ItemStack(slotInfused).getSubCompound(SlimeBreeder.MODID);
	    					
	    					NBTTagList coreitems = nbtInfused.getTagList("core", 10);
	    					
	    					
	    				 
	    				 ItemStack stack = new ItemStack(coreitems.getCompoundTagAt(0));
	    				 String blank = "Blank Text";
	    				 EnumRecipes.listOfRecipes.addIfAbsent(new SlimeRecipes(new ItemStack(SlimeBreederItems.CLEARSLIMECRYSTAL), stack, new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(slotInfused), 0.0D, 0.0D, 0, 0, 0, true, blank));
	    				 //this.setLastCraft(0);
	    			 }
 			}
 		 }
    }
    	
    public void stackSlots(int multiplier)
    {
    	EnumRecipes.listOfRecipes.clear();
    	EnumRecipes.initRecipeList();
    	
    	
    	addInfused(SlotFutureResult);
		addInfused(SlotResult);
		
		int index = SlotResult.getInteger("Recipe");
    		
    		if(new ItemStack(SlotResult).getItem() == EnumRecipes.listOfRecipes.get(index).getItem7().getItem())
    		{
    			//System.out.println("Result Final =  " + EnumRecipes.listOfRecipes.get(index).getItem7().getItem().getUnlocalizedName() + " @ " + index);
    			int result = index;
    			
    			//System.out.println("Recipe Result = " + EnumRecipes.listOfRecipes.get(result).getItem1() + " SlotOne = " + new ItemStack(SlotOne));
    			
    			for (int j = 0; j < 5; j++) 
    			{
    				////System.out.println("j  =  " + j );
    				switch (j) {
    		        case 0:
    		            if(EnumRecipes.listOfRecipes.get(result).getItem1().getItem() == new ItemStack(SlotOne).getItem())
    		            	{

    		            	NBTTagCompound SerialOne =  SlotOne.copy();
    		            	NBTTagCompound SerialCraftOne =  EnumRecipes.listOfRecipes.get(result).getItem1().serializeNBT();
    		            	
    		            	int a = SerialOne.getByte("Count");
    		            	int b = SerialCraftOne.getByte("Count");
    		            	
    		            	//System.out.println("Count SlotOne =  " + a);
    		            	//System.out.println("Count ResultOne =  " + b);
    		            	
    		            	////System.out.println("Count Multiplyer =  " + multiplier);
    		            	
    		            	
    		            	int c = a-(b*multiplier);
    		    			 
    		            	////System.out.println("Count Result =  " + c);
    		            	
    		    			 SerialOne.setByte("Count", (byte)c);
    		    			 SlotOne = SerialOne;
    		            	
    		            	
    		            	};
    		            	
    		            	if(EnumRecipes.listOfRecipes.get(result).getItem1().getItem() == new ItemStack(SlotTwo).getItem())
    		            	{

    		            	NBTTagCompound SerialTwo =  SlotTwo.copy();
    		            	NBTTagCompound SerialCraftTwo =  EnumRecipes.listOfRecipes.get(result).getItem1().serializeNBT();
    		            	
    		            	int a = SerialTwo.getByte("Count");
    		            	int b = SerialCraftTwo.getByte("Count");
    		            	int c = a-(b*multiplier);
    		    			 
    		    			 SerialTwo.setByte("Count", (byte)c);
    		    			 SlotTwo = SerialTwo;
    		            	
    		            	
    		            	};
    		            	
    		            	if(EnumRecipes.listOfRecipes.get(result).getItem1().getItem() == new ItemStack(SlotThree).getItem())
    		            	{

    		            	NBTTagCompound SerialThree =  SlotThree.copy();
    		            	NBTTagCompound SerialCraftThree =  EnumRecipes.listOfRecipes.get(result).getItem1().serializeNBT();
    		            	
    		            	int a = SerialThree.getByte("Count");
    		            	int b = SerialCraftThree.getByte("Count");
    		            	int c = a-(b*multiplier);
    		    			 
    		    			 SerialThree.setByte("Count", (byte)c);
    		    			 SlotThree = SerialThree;
    		            	
    		            	
    		            	};
    		            	
    		            	if(EnumRecipes.listOfRecipes.get(result).getItem1().getItem() == new ItemStack(SlotFour).getItem())
    		            	{

    		            	NBTTagCompound SerialFour =  SlotFour.copy();
    		            	NBTTagCompound SerialCraftFour =  EnumRecipes.listOfRecipes.get(result).getItem1().serializeNBT();
    		            	
    		            	int a = SerialFour.getByte("Count");
    		            	int b = SerialCraftFour.getByte("Count");
    		            	int c = a-(b*multiplier);
    		    			 
    		    			 SerialFour.setByte("Count", (byte)c);
    		    			 SlotFour = SerialFour;
    		            	
    		            	
    		            	};
    		            	
    		            	if(EnumRecipes.listOfRecipes.get(result).getItem1().getItem() == new ItemStack(SlotFive).getItem())
    		            	{

    		            	NBTTagCompound SerialFive =  SlotFive.copy();
    		            	NBTTagCompound SerialCraftFive =  EnumRecipes.listOfRecipes.get(result).getItem1().serializeNBT();
    		            	
    		            	int a = SerialFive.getByte("Count");
    		            	int b = SerialCraftFive.getByte("Count");
    		            	int c = a-(b*multiplier);
    		    			 
    		    			 SerialFive.setByte("Count", (byte)c);
    		    			 SlotFive = SerialFive;
    		            	
    		            	
    		            	};
    		            	
    		            	
    		            break;
    		        case 1:
    		        	if(EnumRecipes.listOfRecipes.get(result).getItem2().getItem() == new ItemStack(SlotOne).getItem())
		            	{

		            	NBTTagCompound SerialOne =  SlotOne.copy();
		            	NBTTagCompound SerialCraftOne =  EnumRecipes.listOfRecipes.get(result).getItem2().serializeNBT();
		            	
		            	int a = SerialOne.getByte("Count");
		            	int b = SerialCraftOne.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialOne.setByte("Count", (byte)c);
		    			 SlotOne = SerialOne;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem2().getItem() == new ItemStack(SlotTwo).getItem())
		            	{

		            	NBTTagCompound SerialTwo =  SlotTwo.copy();
		            	NBTTagCompound SerialCraftTwo =  EnumRecipes.listOfRecipes.get(result).getItem2().serializeNBT();
		            	
		            	int a = SerialTwo.getByte("Count");
		            	int b = SerialCraftTwo.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialTwo.setByte("Count", (byte)c);
		    			 SlotTwo = SerialTwo;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem2().getItem() == new ItemStack(SlotThree).getItem())
		            	{

		            	NBTTagCompound SerialThree =  SlotThree.copy();
		            	NBTTagCompound SerialCraftThree =  EnumRecipes.listOfRecipes.get(result).getItem2().serializeNBT();
		            	
		            	int a = SerialThree.getByte("Count");
		            	int b = SerialCraftThree.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialThree.setByte("Count", (byte)c);
		    			 SlotThree = SerialThree;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem2().getItem() == new ItemStack(SlotFour).getItem())
		            	{

		            	NBTTagCompound SerialFour =  SlotFour.copy();
		            	NBTTagCompound SerialCraftFour =  EnumRecipes.listOfRecipes.get(result).getItem2().serializeNBT();
		            	
		            	int a = SerialFour.getByte("Count");
		            	int b = SerialCraftFour.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialFour.setByte("Count", (byte)c);
		    			 SlotFour = SerialFour;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem2().getItem() == new ItemStack(SlotFive).getItem())
		            	{

		            	NBTTagCompound SerialFive =  SlotFive.copy();
		            	NBTTagCompound SerialCraftFive =  EnumRecipes.listOfRecipes.get(result).getItem2().serializeNBT();
		            	
		            	int a = SerialFive.getByte("Count");
		            	int b = SerialCraftFive.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialFive.setByte("Count", (byte)c);
		    			 SlotFive = SerialFive;
		            	
		            	
		            	};
		            	
    		            break;
    		        case 2:
    		        	if(EnumRecipes.listOfRecipes.get(result).getItem3().getItem() == new ItemStack(SlotOne).getItem())
		            	{

		            	NBTTagCompound SerialOne =  SlotOne.copy();
		            	NBTTagCompound SerialCraftOne =  EnumRecipes.listOfRecipes.get(result).getItem3().serializeNBT();
		            	
		            	int a = SerialOne.getByte("Count");
		            	int b = SerialCraftOne.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialOne.setByte("Count", (byte)c);
		    			 SlotOne = SerialOne;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem3().getItem() == new ItemStack(SlotTwo).getItem())
		            	{

		            	NBTTagCompound SerialTwo =  SlotTwo.copy();
		            	NBTTagCompound SerialCraftTwo =  EnumRecipes.listOfRecipes.get(result).getItem3().serializeNBT();
		            	
		            	int a = SerialTwo.getByte("Count");
		            	int b = SerialCraftTwo.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialTwo.setByte("Count", (byte)c);
		    			 SlotTwo = SerialTwo;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem3().getItem() == new ItemStack(SlotThree).getItem())
		            	{

		            	NBTTagCompound SerialThree =  SlotThree.copy();
		            	NBTTagCompound SerialCraftThree =  EnumRecipes.listOfRecipes.get(result).getItem3().serializeNBT();
		            	
		            	int a = SerialThree.getByte("Count");
		            	int b = SerialCraftThree.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialThree.setByte("Count", (byte)c);
		    			 SlotThree = SerialThree;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem3().getItem() == new ItemStack(SlotFour).getItem())
		            	{

		            	NBTTagCompound SerialFour =  SlotFour.copy();
		            	NBTTagCompound SerialCraftFour =  EnumRecipes.listOfRecipes.get(result).getItem3().serializeNBT();
		            	
		            	int a = SerialFour.getByte("Count");
		            	int b = SerialCraftFour.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialFour.setByte("Count", (byte)c);
		    			 SlotFour = SerialFour;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem3().getItem() == new ItemStack(SlotFive).getItem())
		            	{

		            	NBTTagCompound SerialFive =  SlotFive.copy();
		            	NBTTagCompound SerialCraftFive =  EnumRecipes.listOfRecipes.get(result).getItem3().serializeNBT();
		            	
		            	int a = SerialFive.getByte("Count");
		            	int b = SerialCraftFive.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialFive.setByte("Count", (byte)c);
		    			 SlotFive = SerialFive;
		            	
		            	
		            	};
		            	
    		            break;
    		        case 3:
    		        	if(EnumRecipes.listOfRecipes.get(result).getItem4().getItem() == new ItemStack(SlotOne).getItem())
		            	{

		            	NBTTagCompound SerialOne =  SlotOne.copy();
		            	NBTTagCompound SerialCraftOne =  EnumRecipes.listOfRecipes.get(result).getItem4().serializeNBT();
		            	
		            	int a = SerialOne.getByte("Count");
		            	int b = SerialCraftOne.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialOne.setByte("Count", (byte)c);
		    			 SlotOne = SerialOne;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem4().getItem() == new ItemStack(SlotTwo).getItem())
		            	{

		            	NBTTagCompound SerialTwo =  SlotTwo.copy();
		            	NBTTagCompound SerialCraftTwo =  EnumRecipes.listOfRecipes.get(result).getItem4().serializeNBT();
		            	
		            	int a = SerialTwo.getByte("Count");
		            	int b = SerialCraftTwo.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialTwo.setByte("Count", (byte)c);
		    			 SlotTwo = SerialTwo;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem4().getItem() == new ItemStack(SlotThree).getItem())
		            	{

		            	NBTTagCompound SerialThree =  SlotThree.copy();
		            	NBTTagCompound SerialCraftThree =  EnumRecipes.listOfRecipes.get(result).getItem4().serializeNBT();
		            	
		            	int a = SerialThree.getByte("Count");
		            	int b = SerialCraftThree.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialThree.setByte("Count", (byte)c);
		    			 SlotThree = SerialThree;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem4().getItem() == new ItemStack(SlotFour).getItem())
		            	{

		            	NBTTagCompound SerialFour =  SlotFour.copy();
		            	NBTTagCompound SerialCraftFour =  EnumRecipes.listOfRecipes.get(result).getItem4().serializeNBT();
		            	
		            	int a = SerialFour.getByte("Count");
		            	int b = SerialCraftFour.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialFour.setByte("Count", (byte)c);
		    			 SlotFour = SerialFour;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem4().getItem() == new ItemStack(SlotFive).getItem())
		            	{

		            	NBTTagCompound SerialFive =  SlotFive.copy();
		            	NBTTagCompound SerialCraftFive =  EnumRecipes.listOfRecipes.get(result).getItem4().serializeNBT();
		            	
		            	int a = SerialFive.getByte("Count");
		            	int b = SerialCraftFive.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialFive.setByte("Count", (byte)c);
		    			 SlotFive = SerialFive;
		            	
		            	
		            	};
		            	
    		            break;
    		        case 4:
    		        	if(EnumRecipes.listOfRecipes.get(result).getItem5().getItem() == new ItemStack(SlotOne).getItem())
		            	{

		            	NBTTagCompound SerialOne =  SlotOne.copy();
		            	NBTTagCompound SerialCraftOne =  EnumRecipes.listOfRecipes.get(result).getItem5().serializeNBT();
		            	
		            	int a = SerialOne.getByte("Count");
		            	int b = SerialCraftOne.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialOne.setByte("Count", (byte)c);
		    			 SlotOne = SerialOne;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem5().getItem() == new ItemStack(SlotTwo).getItem())
		            	{

		            	NBTTagCompound SerialTwo =  SlotTwo.copy();
		            	NBTTagCompound SerialCraftTwo =  EnumRecipes.listOfRecipes.get(result).getItem5().serializeNBT();
		            	
		            	int a = SerialTwo.getByte("Count");
		            	int b = SerialCraftTwo.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialTwo.setByte("Count", (byte)c);
		    			 SlotTwo = SerialTwo;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem5().getItem() == new ItemStack(SlotThree).getItem())
		            	{

		            	NBTTagCompound SerialThree =  SlotThree.copy();
		            	NBTTagCompound SerialCraftThree =  EnumRecipes.listOfRecipes.get(result).getItem5().serializeNBT();
		            	
		            	int a = SerialThree.getByte("Count");
		            	int b = SerialCraftThree.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialThree.setByte("Count", (byte)c);
		    			 SlotThree = SerialThree;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem5().getItem() == new ItemStack(SlotFour).getItem())
		            	{

		            	NBTTagCompound SerialFour =  SlotFour.copy();
		            	NBTTagCompound SerialCraftFour =  EnumRecipes.listOfRecipes.get(result).getItem5().serializeNBT();
		            	
		            	int a = SerialFour.getByte("Count");
		            	int b = SerialCraftFour.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialFour.setByte("Count", (byte)c);
		    			 SlotFour = SerialFour;
		            	
		            	
		            	};
		            	
		            	if(EnumRecipes.listOfRecipes.get(result).getItem5().getItem() == new ItemStack(SlotFive).getItem())
		            	{

		            	NBTTagCompound SerialFive =  SlotFive.copy();
		            	NBTTagCompound SerialCraftFive =  EnumRecipes.listOfRecipes.get(result).getItem5().serializeNBT();
		            	
		            	int a = SerialFive.getByte("Count");
		            	int b = SerialCraftFive.getByte("Count");
		            	int c = a-(b*multiplier);
		    			 
		    			 SerialFive.setByte("Count", (byte)c);
		    			 SlotFive = SerialFive;
		            	
		            	
		            	};
		            	
    		            break;

    		    	}
    			
    			}
    		}
    		EnumRecipes.listOfRecipes.clear();
    	}
    	
    

	
	/**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        /*int i = this.rand.nextInt(3);

        if (i < 2 && this.rand.nextFloat() < 0.5F * difficulty.getClampedAdditionalDifficulty())
        {
            ++i;
        }

        int j = 1 << i;
        this.setSlimeSize(j);*/
    //this.setColor(red, green, blue);
    
    	
    this.varient = 1;
    this.dataManager.set(VARIENT, Integer.valueOf(1));
    
    	
    //this.wave = 380 + rand.nextInt(401);
    int waveAdd;
    do{
    	waveAdd = (int) Math.abs(rand.nextGaussian()*70 + 200);
    	this.wave = 380 + waveAdd;
    }while(waveAdd < 0 && waveAdd > 400);
    
    
    do{
    	this.sat = (float) Math.abs(rand.nextGaussian()*0.17 + 0.505);
    	
    }while(this.sat < 0F && this.sat > 1F);
    
    do{
    	this.brightness = (float) Math.abs(rand.nextGaussian()*0.17 + 0.505);
    	
    }while(this.brightness < 0F && this.brightness > 1F);
    
    ////System.out.println(this.enablePersistence());
    
    //this.wave = (int) (380 + Math.abs(rand.nextGaussian()*200));
    //this.sat = rand.nextFloat();
    //this.brightness = rand.nextFloat();
    
    /*this.wave = (int) (380 + Math.abs(rand.nextGaussian()*200));
    this.sat = Math.abs((float) ((rand.nextGaussian()*50)/100));
    this.brightness = Math.abs((float) ((rand.nextGaussian()*50)/100));*/
    
    /*System.out.println("Wave = " + this.wave);
    System.out.println("Sat = " + this.sat);
    System.out.println("Bright = " + this.brightness);*/
    
    setSlimeItemSlot1(-1);
    setItemProcessSlot1(1000);
    setItemProcessSlot1Max(1000);
    
    setSlimeItemSlot2(-1);
    setItemProcessSlot2(1000);
    setItemProcessSlot2Max(1000);
    
    setSlimeItemSlot3(-1);
    setItemProcessSlot3(1000);
    setItemProcessSlot3Max(1000);
    
    setSlimeItemSlot4(-1);
    setItemProcessSlot4(1000);
    setItemProcessSlot4Max(1000);
    
    setSlimeItemSlot5(-1);
    setItemProcessSlot5(1000);
    setItemProcessSlot5Max(1000);
    
    initItemSlots();
    
    NBTTagCompound slotSerial = setDrop().serializeNBT();
	this.SlotDrop = slotSerial;
	
	


    this.breedinggroup = 0;
    this.domesticated = 0;
    

    
    //this.targetTasks.addTask(1, new EntityAIFindNearestBreeder(this, this.domesticated));
    //this.targetTasks.addTask(1, new EntityAIFindNearestOwnaer(this, this.domesticated));
    
    System.out.println("Wave = " + this.wave);

    if(this.wave > 0)
    {
    	setStatsByWave(wave);
    }
    //this.setSlimeSize(1);

    ////System.out.println("Growing Age = " + this.getGrowingAge());

    System.out.println("Gen breedingspeed = " + this.breedingspeed);
    System.out.println("Gen movespeed = " + this.movespeed);
    System.out.println("Gen health = " + this.health);
    System.out.println("Gen attackdmg = " + this.attackdmg);
    
    System.out.println("Gen Wave = " + wave);
    System.out.println("Gen Sats = " + sat);
    System.out.println("Gen Brightness = " + brightness);
    System.out.println("Gen rgb = " + rgb);
    
    setHSB(wave);
    this.setWave(wave);
    this.setSat(sat);
    this.setBrightness(brightness);
    this.setHunger(this.breedingspeed/2);
    this.setGenetics(breedinggroup, breedingspeed, movespeed, health, attackdmg, sticky, domesticated);
    
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.health);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1*this.movespeed);
    this.setHealth(this.getMaxHealth());



    //this.dataWatcher.updateObject(16, Byte.valueOf((byte) 1));

    //this.setColor(wavelength);
    //this.red = this.getRed();
    //this.green = this.getGreen();
    //this.blue = this.getBlue();

    return super.onInitialSpawn(difficulty, livingdata);
    }

    protected SoundEvent getJumpSound()
    {
        return this.isSmallSlime() ? SoundEvents.ENTITY_SMALL_SLIME_JUMP : SoundEvents.ENTITY_SLIME_JUMP;
    }

    
    /* ======================================== Custom Code Start =====================================*/
    

    public void setColorGenetics(int Breedinggroup, int Breedingspeed, int Movespeed, int Health, int Attackdmg, int Sticky, int Domesticated)
    {
    	
    	this.breedingspeed = Breedingspeed;
		this.movespeed = Movespeed;
		this.health = Health;
		this.attackdmg = Attackdmg;
		this.sticky = Sticky;
		
		this.setBreedingSpeed(Breedingspeed);
		this.setMoveSpeed(Movespeed);
		this.setSlimeHealth(Health);
		this.setSlimeAttack(Attackdmg);
		this.setSticky(Sticky);
    	this.setDomestication(Domesticated);
    	
    	
    	
    	
    	
    	
    	
    	//this.breedinggroup = Breedinggroup;
    	//this.breedingspeed = Breedingspeed;
    	
    	//this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1*Movespeed);
    	//this.movespeed = Movespeed;
    	//this.health = Health;
    	
    	
    	
    	//this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Health);
    	//this.health = Health;
    	//this.attackdmg = Attackdmg;
    	//this.sticky = Sticky;
    	//this.domesticated = Domesticated;
    	
    	NBTTagCompound genetagCompound = this.getEntityData();
    	
    	genetagCompound.setInteger("BreedingSpeed", this.getBreedingSpeed());
    	genetagCompound.setDouble("MoveSpeed", this.getMoveSpeed());
    	genetagCompound.setDouble("Health", this.getSlimeHealth());
    	genetagCompound.setInteger("AttackDmg", this.getAttackDmg());
    	genetagCompound.setInteger("Sticky", this.getSticky());
    	
    }

    public void setHSB(int w)
    {
    	 int red;
    	 int green;
    	 int blue;
    	 int c = 255;
    	 
    	
    	
    	 if((w >= 380) && (w<440)){
    	    	float a = (-(w - 440)/(((float)440) - ((float)380)));
    	    	////System.out.println(a);
    	    	////System.out.println(wave);
    	        red = (int) (c * a);
    	        green = 0;
    	        blue = 1 * c;
    	        ////System.out.println("Set Red = " + red);
    	        ////System.out.println("Set Green = " + green);
    	       ////System.out.println("Set Blue = " + blue);
    	        this.Hue = getHue(red, green, blue);
    	        //float vs[]={this.Hue, this.sat, this.brightness};
    	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
    	        
    	        
    	        
    	    }else if((w >= 440) && (w<490)){
    	    	float b = ((w - 440)/(((float)490 - ((float)440))));
    	    	////System.out.println(b);
    	    	////System.out.println(wave);
    	        red = 0;
    	        green = (int) (c * b);
    	        blue = 1 * c;
    	        ////System.out.println("Set Red = " + red);
    	        ////System.out.println("Set Green = " + green);
    	        ////System.out.println("Set Blue = " + blue);
    	        this.Hue = getHue(red, green, blue);
    	        //float vs[]={this.Hue, this.sat, this.brightness};
    	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
    	        
    	        
    	    }else if((w >= 490) && (w<510)){
    	    	float c2 = (-(w - 510)/(((float)510 - ((float)490))));
    	    	////System.out.println(c);
    	    	////System.out.println(wave);
    	        red = 0;
    	        green = 1 * c;
    	        blue = (int) (c * c2);
    	        ////System.out.println("Set Red = " + red);
    	        ////System.out.println("Set Green = " + green);
    	        ////System.out.println("Set Blue = " + blue);
    	        this.Hue = getHue(red, green, blue);
    	        //float vs[]={this.Hue, this.sat, this.brightness};
    	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
    	        
    	    }else if((w >= 510) && (w<580)){
    	    	float d = ((w - 510)/(((float)580 - ((float)510))));
    	    	////System.out.println(d);
    	    	////System.out.println(wave);
    	        red = (int) (c * d);
    	        green = 1 * c;
    	        blue = 0;
    	        /////System.out.println("Set Red = " + red);
    	        ////System.out.println("Set Green = " + green);
    	       ////System.out.println("Set Blue = " + blue);
    	        this.Hue = getHue(red, green, blue);
    	        //float vs[]={this.Hue, this.sat, this.brightness};
    	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
    	        
    	        
    	    }else if((w >= 580) && (w<645)){
    	    	float e = (-(w - 645)/(((float)645 - 580)));
    	    	////System.out.println(e);
    	    	////System.out.println(wave);
    	        red = 1 * c;
    	        green = (int) (c * e);
    	        blue = 0;
    	        ////System.out.println("Set Red = " + red);
    	        ////System.out.println("Set Green = " + green);
    	        ////System.out.println("Set Blue = " + blue);
    	        this.Hue = getHue(red, green, blue);
    	        //float vs[]={this.Hue, this.sat, this.brightness};
    	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
    	        
    	    }else if((w >= 645) && (w<781)){
    	        red = 1 * c;
    	        green = 0;
    	        blue = 0;
    	        ////System.out.println("Set Red = " + red);
    	        ////System.out.println("Set Green = " + green);
    	        ////System.out.println("Set Blue = " + blue);
    	        this.Hue = getHue(red, green, blue);
    	        //float vs[]={this.Hue, this.sat, this.brightness};
    	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
    	        ////System.out.println(wave);
    	       
    	        
    	    }else{
    	        red = 0;
    	        green = 0;
    	        blue = 0;
    	        ////System.out.println("Set Red = " + red);
    	        ////System.out.println("Set Green = " + green);
    	        ////System.out.println("Set Blue = " + blue);
    	        this.Hue = getHue(red, green, blue);
    	        //this.vs={this.Hue, this.sat, this.brightness};
    	        //this.HSB = Color.RGBtoHSB(red, green, blue, vs);
    	        ////System.out.println(wave);
    	      
    	    };
    	    
    		
    	    //float[] HSB = Color.RGBtoHSB(red, green, blue, this.hsbvals);
    	    ////System.out.println("Red = " + red);
            ////System.out.println("Green = " + green);
            ////System.out.println("Blue = " + blue);
            
    	    /*float[] hsb = Color.RGBtoHSB(red, green, blue, null);

    	    
    	    this.Hue = hsb[0];
    	    this.sat = hsb[1];
    	    this.brightness = hsb[2];*/

    	    
    	    this.rgb = Color.HSBtoRGB(((this.Hue)/360), this.sat, this.brightness);
    	    
    	    float red2 = (rgb>>16)&0xFF;
    	    float green2 = (rgb>>8)&0xFF;
    	    float blue2 = rgb&0xFF;
    	    float red3 = (rgb>>23)&0xFF;
    	    float green3 = (rgb>>15)&0xFF;
    	    float blue3 = (rgb>>7)&0xFF;
    	    float red4 = (rgb>>18)&0xFF;
    	    float green4 = (rgb>>10)&0xFF;
    	    float blue4 = (rgb>>2)&0xFF;
    	    float red5 = (rgb>>19)&0xFF;
    	    float green5 = (rgb>>11)&0xFF;
    	    float blue5 = (rgb>>3)&0xFF;
    	    EnumItemColor.initColorList();
    	    ////System.out.println(EnumItemColor.listOfObjects);
    	   ////System.out.println(EnumItemColor.listOfObjects.get(0));

    	    
    	    
    	    


    	    setFRGB(this.rgb);
    		/*//System.out.println("Local hue2 = " + Hue);
    		//System.out.println("Local saturation2 = " + sat);
    		//System.out.println("Local Brightness2 = " + brightness);
    		//System.out.println("new rgb2 = " + rgb);
    		//System.out.println("new red = " + red2);
    		//System.out.println("new green = " + green2);
    		//System.out.println("new blue = " + blue2);
    		//System.out.println("new red2 = " + red3);
    		//System.out.println("new green2 = " + green3);
    		//System.out.println("new blue2 = " + blue3);
    		//System.out.println("new red3 = " + red4);
    		//System.out.println("new green3 = " + green4);
    		//System.out.println("new blue3 = " + blue4);
    		//System.out.println("new red4 = " + red5);
    		//System.out.println("new green4 = " + green5);
    		//System.out.println("new blue4 = " + blue5);
    		//System.out.println("read rgb = " + getRGB2());*/
    		
    	    //setRGB(this.vs);
    	    
    	    
    	//this.dataWatcher.updateObject(20, Integer.valueOf(w));
    	 
    }


    

    public void setRGB(float[] g)
    {
    	this.rgb = Color.HSBtoRGB(g[0], g[1], g[2]);
    	/*//System.out.println("hue = " + g[0]);
    	//System.out.println("Local hue = " + Hue);
    	//System.out.println("saturation = " + g[1]);
    	//System.out.println("Brightness = " + g[2]);
    	//System.out.println("new rgb = " + rgb);*/
    	setFRGB(rgb);
    }


    
    //////////////SLOT ONE
    
    public int getSlimeItemSlot1()
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	return this.itemId1;
    	
    }
    
    public NBTTagCompound getSlimeSlotOne()
    {
    	return SlotOne;
    }
    
    public int getServerItemSlot1()
    {
    	
    	////System.out.println("Server Id = "  + this.dataManager.get(ITEMSLOT1));
    	
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	if(this.dataManager.get(ITEMSLOT1) == 0)
    	{
    		return -1;
    	}else
    	
    	return this.dataManager.get(ITEMSLOT1);
    	
    }
    
    public void setSlimeItemSlot1(int Item)
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	this.itemId1 = Item;
    }
    
    public int getItemProcessSlot1()
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	return this.processItemSlot1;
    	
    }
    
    public int getServerItemProcessSlot1()
    {
    	return this.dataManager.get(ITEMPROCESSSLOT1);
    }
    
    
    public void setItemProcessSlot1(int process)
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	this.processItemSlot1 = process;
    }
    
    public void setItemProcessSlot1Max(int process)
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	this.processItemSlot1Max = process;
    }
    
    public int getItemProcessSlot1Max()
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	return this.processItemSlot1Max;
    	
    }
    
    public int getServerItemProcessSlot1Max()
    {
    	return this.dataManager.get(ITEMPROCESSSLOT1MAX );
    }
    
    
    public void processItemSlot1(int Item)
    {
    	if(Item != -1)
    	{
    		
    		if(processItemSlot1 >= 0)
    		{
    			 int j = this.processItemSlot1 - 1;
    			 this.processItemSlot1 = j;
    			 this.dataManager.set(ITEMPROCESSSLOT1, Integer.valueOf(j));
    			 
    			 ////System.out.println("Processing client.. = " + this.processItemSlot1);
    			 ////System.out.println("Processing server.. = " + this.dataManager.get(ITEMPROCESSSLOT1));
    			
    			/*if(processItemSlot1 <= 0 && this.getServerItemProcessSlot1() <= 0 && this.getServerItemSlot1() != 0)
    			{
    				//System.out.println("done");
    				this.itemId1 = -1;
    				this.dataManager.set(ITEMSLOT1, Integer.valueOf(-1));
    				
    				this.processItemSlot1 = 1000;
    				this.dataManager.set(ITEMPROCESSSLOT1, 1000);
    				
    				this.processItemSlot1Max = 1000;
    				this.dataManager.set(ITEMPROCESSSLOT1MAX, 1000);
    				
    				
    			}*/
    			
    		}
    	}
    	
    	if(Item == -1)
    	{
    		
    		this.processItemSlot1 = this.dataManager.get(ITEMPROCESSSLOT1MAX);
    	}
    }
    
    
    //////////////////////////
    
    
    //////////////SLOT TWO
    
    public int getSlimeItemSlot2()
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	return this.itemId2;
    	
    }
    
    public int getServerItemSlot2()
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	if(this.dataManager.get(ITEMSLOT2) == 0)
    	{
    		return -1;
    	}else
    	
    	return this.dataManager.get(ITEMSLOT2);
    	
    }
    
    public void setSlimeItemSlot2(int Item)
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	this.itemId2 = Item;
    }
    
    public int getItemProcessSlot2()
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	return this.processItemSlot2;
    	
    }
    
    public int getServerItemProcessSlot2()
    {
    	return this.dataManager.get(ITEMPROCESSSLOT2);
    }
    
    
    public void setItemProcessSlot2(int process)
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	this.processItemSlot2 = process;
    }
    
    public void setItemProcessSlot2Max(int process)
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	this.processItemSlot2Max = process;
    }
    
    public int getItemProcessSlot2Max()
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	return this.processItemSlot2Max;
    	
    }
    
    public int getServerItemProcessSlot2Max()
    {
    	return this.dataManager.get(ITEMPROCESSSLOT2MAX );
    }
    
    
    public void processItemSlot2(int Item)
    {
    	if(Item != -1)
    	{
    		
    		if(processItemSlot2 >= 0)
    		{
    			 int j = this.processItemSlot2 - 1;
    			 this.processItemSlot2 = j;
    			 this.dataManager.set(ITEMPROCESSSLOT2, Integer.valueOf(j));
    			 
    			 ////System.out.println("Processing client.. = " + this.processItemSlot1);
    			 ////System.out.println("Processing server.. = " + this.dataManager.get(ITEMPROCESSSLOT1));
    			
    			/*if(processItemSlot2 <= 0)
    			{
    				this.itemId2 = -1;
    				this.dataManager.set(ITEMSLOT2, Integer.valueOf(-1));
    				
    				this.processItemSlot2 = 1000;
    				this.dataManager.set(ITEMPROCESSSLOT2, 1000);
    				
    				this.processItemSlot2Max = 1000;
    				this.dataManager.set(ITEMPROCESSSLOT2MAX, 1000);
    				
    				
    			}*/
    			
    		}
    	}
    	
    	if(Item == -1)
    	{
    		this.processItemSlot2 = this.dataManager.get(ITEMPROCESSSLOT2MAX);
    	}
    }
    
    
    //////////////////////////
    
    //////////////SLOT THREE
    
    public int getSlimeItemSlot3()
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	return this.itemId3;
    	
    }
    
    public int getServerItemSlot3()
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	if(this.dataManager.get(ITEMSLOT3) == 0)
    	{
    		return -1;
    	}else
    	
    	return this.dataManager.get(ITEMSLOT3);
    	
    }
    
    public void setSlimeItemSlot3(int Item)
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	this.itemId3 = Item;
    }
    
    public int getItemProcessSlot3()
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	return this.processItemSlot3;
    	
    }
    
    public int getServerItemProcessSlot3()
    {
    	return this.dataManager.get(ITEMPROCESSSLOT3);
    }
    
    
    public void setItemProcessSlot3(int process)
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	this.processItemSlot3 = process;
    }
    
    public void setItemProcessSlot3Max(int process)
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	this.processItemSlot3Max = process;
    }
    
    public int getItemProcessSlot3Max()
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	return this.processItemSlot3Max;
    	
    }
    
    public int getServerItemProcessSlot3Max()
    {
    	return this.dataManager.get(ITEMPROCESSSLOT3MAX );
    }
    
    
    public void processItemSlot3(int Item)
    {
    	if(Item != -1)
    	{
    		
    		if(processItemSlot3 >= 0)
    		{
    			 int j = this.processItemSlot3 - 1;
    			 this.processItemSlot3 = j;
    			 this.dataManager.set(ITEMPROCESSSLOT3, Integer.valueOf(j));
    			 
    			 ////System.out.println("Processing client.. = " + this.processItemSlot1);
    			 ////System.out.println("Processing server.. = " + this.dataManager.get(ITEMPROCESSSLOT1));
    			
    			/*if(processItemSlot3 <= 0)
    			{
    				this.itemId3 = -1;
    				this.dataManager.set(ITEMSLOT3, Integer.valueOf(-1));
    				
    				this.processItemSlot3 = 1000;
    				this.dataManager.set(ITEMPROCESSSLOT3, 1000);
    				
    				this.processItemSlot3Max = 1000;
    				this.dataManager.set(ITEMPROCESSSLOT3MAX, 1000);
    				
    				
    			}*/
    			
    		}
    	}
    	
    	if(Item == -1)
    	{
    		this.processItemSlot3 = this.dataManager.get(ITEMPROCESSSLOT3MAX);
    	}
    }
    
    
    //////////////////////////
    
//////////////SLOT FOUR
    
public int getSlimeItemSlot4()
{
//return this.dataWatcher.getWatchableObjectInt(20);
return this.itemId4;

}

public int getServerItemSlot4()
{
//return this.dataWatcher.getWatchableObjectInt(20);
if(this.dataManager.get(ITEMSLOT4) == 0)
{
	return -1;
}else

return this.dataManager.get(ITEMSLOT4);

}

public void setSlimeItemSlot4(int Item)
{
//return this.dataWatcher.getWatchableObjectInt(20);
this.itemId4 = Item;
}

public int getItemProcessSlot4()
{
//return this.dataWatcher.getWatchableObjectInt(20);
return this.processItemSlot4;

}

public int getServerItemProcessSlot4()
{
return this.dataManager.get(ITEMPROCESSSLOT4);
}


public void setItemProcessSlot4(int process)
{
//return this.dataWatcher.getWatchableObjectInt(20);
this.processItemSlot4 = process;
}

public void setItemProcessSlot4Max(int process)
{
//return this.dataWatcher.getWatchableObjectInt(20);
this.processItemSlot4Max = process;
}

public int getItemProcessSlot4Max()
{
//return this.dataWatcher.getWatchableObjectInt(20);
return this.processItemSlot4Max;

}

public int getServerItemProcessSlot4Max()
{
return this.dataManager.get(ITEMPROCESSSLOT4MAX );
}


public void processItemSlot4(int Item)
{
if(Item != -1)
{
	
	if(processItemSlot4 >= 0)
	{
		 int j = this.processItemSlot4 - 1;
		 this.processItemSlot4 = j;
		 this.dataManager.set(ITEMPROCESSSLOT4, Integer.valueOf(j));
		 
		 ////System.out.println("Processing client.. = " + this.processItemSlot1);
		 ////System.out.println("Processing server.. = " + this.dataManager.get(ITEMPROCESSSLOT1));
		
		/*if(processItemSlot4 <= 0)
		{
			this.itemId4 = -1;
			this.dataManager.set(ITEMSLOT4, Integer.valueOf(-1));
			
			this.processItemSlot4 = 1000;
			this.dataManager.set(ITEMPROCESSSLOT4, 1000);
			
			this.processItemSlot4Max = 1000;
			this.dataManager.set(ITEMPROCESSSLOT4MAX, 1000);
			
			
		}*/
		
	}
}

if(Item == -1)
{
	this.processItemSlot4 = this.dataManager.get(ITEMPROCESSSLOT4MAX);
}
}


//////////////////////////

//////////////SLOT FIVE

public int getSlimeItemSlot5()
{
//return this.dataWatcher.getWatchableObjectInt(20);
return this.itemId5;

}

public int getServerItemSlot5()
{
//return this.dataWatcher.getWatchableObjectInt(20);
if(this.dataManager.get(ITEMSLOT5) == 0)
{
return -1;
}else

return this.dataManager.get(ITEMSLOT5);

}

public void setSlimeItemSlot5(int Item)
{
//return this.dataWatcher.getWatchableObjectInt(20);
this.itemId5 = Item;
}

public int getItemProcessSlot5()
{
//return this.dataWatcher.getWatchableObjectInt(20);
return this.processItemSlot5;

}

public int getServerItemProcessSlot5()
{
return this.dataManager.get(ITEMPROCESSSLOT5);
}


public void setItemProcessSlot5(int process)
{
//return this.dataWatcher.getWatchableObjectInt(20);
this.processItemSlot5 = process;
}

public void setItemProcessSlot5Max(int process)
{
//return this.dataWatcher.getWatchableObjectInt(20);
this.processItemSlot5Max = process;
}

public int getItemProcessSlot5Max()
{
//return this.dataWatcher.getWatchableObjectInt(20);
return this.processItemSlot5Max;

}

public int getServerItemProcessSlot5Max()
{
return this.dataManager.get(ITEMPROCESSSLOT5MAX );
}


public void processItemSlot5(int Item)
{
if(Item != -1)
{

if(processItemSlot5 >= 0)
{
int j = this.processItemSlot5 - 1;
this.processItemSlot5 = j;
this.dataManager.set(ITEMPROCESSSLOT5, Integer.valueOf(j));

////System.out.println("Processing client.. = " + this.processItemSlot1);
////System.out.println("Processing server.. = " + this.dataManager.get(ITEMPROCESSSLOT1));

/*if(processItemSlot5 <= 0)
{
this.itemId5 = -1;
this.dataManager.set(ITEMSLOT5, Integer.valueOf(-1));

this.processItemSlot5 = 1000;
this.dataManager.set(ITEMPROCESSSLOT5, 1000);

this.processItemSlot5Max = 1000;
this.dataManager.set(ITEMPROCESSSLOT5MAX, 1000);


}*/

}
}

if(Item == -1)
{
this.processItemSlot5 = this.dataManager.get(ITEMPROCESSSLOT5MAX);
}
}


//////////////////////////
    

    
    
    public int getVarient()
    {
    	return this.varient;
    }
    
    public int getVarientServer()
    {
    return ((Integer)this.dataManager.get(VARIENT)).intValue();
    }
    
    public void setVarient(int var)
    {
    	this.varient = var;
    }

    public float getSat()
    {
    	return this.sat;
    }
    
    

    public float getBright()
    {
    	return this.brightness;
    }



   

    

    
    
    public void setOwner(UUID newOwner)
    {
    	this.owner = newOwner;
    }
    


    public int getslimeInventory(int index)
    {
    	return this.slimeInventory[index];
    }
    
    public void setslimeInventory(int item)
    {
    	this.slimeInventory[length] = item;
    }

    public int getSlimeChildBreedingSpeed(EntityAnimal father, EntityAnimal mother)
    {
    	int i1 = ((EntityColorSlimeBase)father).getBreedingSpeed();
        int j1 = ((EntityColorSlimeBase)mother).getBreedingSpeed();
        
        int k3 = (i1 + j1)/2;
        
        System.out.println("Breedingspeed = " + k3);

    	return k3;
    }

    public double getSlimeChildMoveSpeed(EntityAnimal father, EntityAnimal mother)
    {
    	double i1 = ((EntityColorSlimeBase)father).getMoveSpeed();
        double j1 = ((EntityColorSlimeBase)mother).getMoveSpeed();
        
        double k3 = (i1 + j1)/2;
        
        System.out.println("movespeed = " + k3);

    	return k3;
    }

    public double getSlimeChildSlimeHealth(EntityAnimal father, EntityAnimal mother)
    {
    	//double i1 = ((EntityColorSlimeBase)father).getSlimeHealth();
    	//double i1 = ((EntityColorSlimeBase)father).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue();
    	//double j1 = ((EntityColorSlimeBase)mother).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue();
    	
    	double i1 = ((EntityColorSlimeBase)father).getEntityData().getDouble("Health");
    	double j1 = ((EntityColorSlimeBase)mother).getEntityData().getDouble("Health");
    	
        //double j1 = ((EntityColorSlimeBase)mother).getSlimeHealth();
        
        double k3 = Math.round((i1 + j1)/2);
        
        System.out.println("Health = " + i1 + "/" + j1 + "=" +  k3);

    	return k3;
    }

    public int getSlimeChildAttackDmg(EntityAnimal father, EntityAnimal mother)
    {
    	int i1 = ((EntityColorSlimeBase)father).getAttackDmg();
        int j1 = ((EntityColorSlimeBase)mother).getAttackDmg();
        
        int k3 = (i1 + j1)/2;

        System.out.println("AttackDmg = " + k3);
        
    	return k3;
    }

    public int getSlimeChildSticky(EntityAnimal father, EntityAnimal mother)
    {
    	int i1 = ((EntityColorSlimeBase)father).getSticky();
        int j1 = ((EntityColorSlimeBase)mother).getSticky();
        
        int r = rand.nextInt(100);
        
        if(r >= 50)
        {
        	System.out.println("Sticky = " + i1);
        	return i1;
        }else

        	 System.out.println("Sticky = " + j1);
        	
    	return j1;
    }
    
    public int getSlimeChildDomestication(EntityAnimal father, EntityAnimal mother)
    {
    	int i1 = ((EntityColorSlimeBase)father).getDomestication();
        int j1 = ((EntityColorSlimeBase)mother).getDomestication();
        int k1 = ((i1+ j1)/2)+1;
        
        System.out.println("Domestication = " + k1);

    	return k1;
    }
    
    public int getSlimeChildAge(EntityAnimal father, EntityAnimal mother)
    {
    	int i1 = ((EntityColorSlimeBase)father).getBreedingSpeed();
        int j1 = ((EntityColorSlimeBase)mother).getBreedingSpeed();
        int k1 = ((i1+ j1)/2);

        System.out.println("Age = " + k1);
    	return k1;
    }


    private int getSlimeChildWave(EntityAnimal father, EntityAnimal mother)
    {
    	int i1 = ((EntityColorSlimeBase)father).getWave();
        int j1 = ((EntityColorSlimeBase)mother).getWave();
        int k1 = (i1 + j1)/2;
        
        System.out.println("Wave = " + k1);
    	return k1;
    }

    private float getSlimeChildSat(EntityAnimal father, EntityAnimal mother)
    {
        
        float i2 = ((EntityColorSlimeBase)father).getSat();
        float j2 = ((EntityColorSlimeBase)mother).getSat();
        float k2 = (i2 + j2)/2;
        
        System.out.println("Sat = " + k2);
    	return k2;
    }

    private float getSlimeChildBright(EntityAnimal father, EntityAnimal mother)
    {
        
        float i3 = ((EntityColorSlimeBase)father).getBright();
        float j3 = ((EntityColorSlimeBase)mother).getBright();
        float k3 = (i3 + j3)/2;
        System.out.println("Bright = " + k3);
    	return k3;
    }



@Override
    public EntityAgeable createChild(EntityAgeable targetMate)
        {
    	
	 EntityColorSlimeBase targetSlime = (EntityColorSlimeBase)targetMate;
	 
	
	 EntityColorSlimeBase babySlime = new EntityColorSlimeBase(this.world);
	 
	 
	 babySlime.initWildSlime(1, getSlimeChildWave(this, targetSlime), getSlimeChildSat(this, targetSlime), getSlimeChildBright(this, targetSlime),  getSlimeChildBreedingSpeed(this, targetSlime), getSlimeChildMoveSpeed(this, targetSlime),  getSlimeChildSlimeHealth(this, targetSlime), getSlimeChildAttackDmg(this, targetSlime), getSlimeChildSticky(this, targetSlime), true);
	
	
	
	
	
	
	
	
	
	
	/*
	
	
    	////System.out.println("Breeding?");
            EntityColorSlimeBase targetSlime = (EntityColorSlimeBase)targetMate;
            //EntitySlime2 entityslime22 = (EntitySlime2)riddenByEntity;
            EntityColorSlimeBase offspring = new EntityColorSlimeBase(this.world);
            
            offspring.setVarient(1);
            offspring.dataManager.set(VARIENT, Integer.valueOf(1));
            
            System.out.println("Target = " + ((EntityLivingSlimeBase) targetMate).getWave());
            
            offspring.setWave(getSlimeChildWave(this, targetSlime));
            offspring.setSat(getSlimeChildSat(this, targetSlime));
            offspring.setBrightness(getSlimeChildBright(this, targetSlime));
            System.out.println("offspring pre health = " + offspring.getHealth());
            
            offspring.setGenetics(targetSlime.breedinggroup, getSlimeChildBreedingSpeed(this, targetSlime), getSlimeChildMoveSpeed(this, targetSlime), getSlimeChildSlimeHealth(this, targetSlime), getSlimeChildAttackDmg(this, targetSlime), getSlimeChildSticky(this, targetSlime), getSlimeChildDomestication(this, targetSlime));
            System.out.println("offspring post health = " + offspring.getHealth());
            offspring.setHSB(getSlimeChildWave(this, targetSlime));
            
            offspring.breedinggroup = targetSlime.breedinggroup;
            
            //offspring.setSlimeSize(getSlimeSize());
            offspring.setGrowingAge(offspring.getBreedingSpeed());
            offspring.setDomestication(this.domesticated + Math.abs((int)(this.domesticated/10)));
            
            offspring.targetTasks.addTask(1, new EntityAIFindNearestBreeder(this));
            
            offspring.bufferInventory = new int[5];
            offspring.craftingInventory = new int[5];
            offspring.slimeInventory = new int[5];
            offspring.modInventory = new int[5];
            
            offspring.setSlimeItemSlot1(-1);
            offspring.setItemProcessSlot1(1000);
            offspring.setItemProcessSlot1Max(1000);
            
            offspring.setSlimeItemSlot2(-1);
            offspring.setItemProcessSlot2(1000);
            offspring.setItemProcessSlot2Max(1000);
            
            offspring.setSlimeItemSlot3(-1);
            offspring.setItemProcessSlot3(1000);
            offspring.setItemProcessSlot3Max(1000);
            
            offspring.setSlimeItemSlot4(-1);
            offspring.setItemProcessSlot4(1000);
            offspring.setItemProcessSlot4Max(1000);
            
            offspring.setSlimeItemSlot5(-1);
            offspring.setItemProcessSlot5(1000);
            offspring.setItemProcessSlot5Max(1000);
            
            
            
            
            NBTTagCompound slotSerial =  offspring.setDrop().serializeNBT();
            offspring.SlotDrop = slotSerial;
            
            
            
            offspring.initItemSlots();
            
            NBTTagCompound offspringNBT = offspring.getEntityData();
            
            offspringNBT.setDouble("Health", 22D);
            
            offspring.dat
            
            
            System.out.println("Mother health = " + this.getHealth());
            System.out.println("Father health = " + targetSlime.getHealth());

            System.out.println("offspring final health = " + offspring.getHealth());
            
            */
            
            return babySlime;
        }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    	{
    	ItemStack itemstack = player.inventory.getCurrentItem();
    	EnumCraftingIngredients.listOfItems.clear();
    	EnumCraftingIngredients.initItemList();
    	
    	if(this.getOwner() != player.getPersistentID())
		{
			this.setOwner(player.getPersistentID());
			this.enablePersistence();
			////System.out.println("New Owner = " + this.owner);
			
		}
    	
    	/*NBTTagCompound slimetag = player.getEntityData();

    	NBTBase modeTag = slimetag.getTag("SlimeBreeder");
    	if (modeTag != null) {
    	player.sendMessage("slime Breeder?" + ((NBTTagInt)modeTag).data);
    	}

    	slimetag.setInteger("SlimeBreeder", 150);*/
    	
    	
    	
    	//player.swingArm(hand);
    	
    	
    	////System.out.println("Timer = " + this.getTimer());
    	
    	if(!(itemstack.isEmpty()))
    	{
    	if(itemstack.getItem() != SlimeBreederItems.SLIMALYSER)
	    	{
    		player.swingArm(hand);
	    	}
    	}
    	

    	
    	
    	
    	if (itemstack.isEmpty() && player.isSneaking())
        {
    		int stage = 3;
    		
    		if(this.getActivePotionEffect(SlimeBreeder.proxy.slimeDomesticated) != null && stage == 3)
    		{
    			stage = 1;
    			this.addPotionEffect(new PotionEffect(SlimeBreeder.proxy.slimeDomesticatedFollow,20, 1));
    			this.removeActivePotionEffect(SlimeBreeder.proxy.slimeDomesticated);
    			
    			player.sendMessage(new TextComponentString("Follow Me"));
    			////System.out.println("Follow");
    		}
    		
    		if(this.getActivePotionEffect(SlimeBreeder.proxy.slimeDomesticatedFollow) != null && stage == 3)
    		{
    			stage = 2;
    			this.addPotionEffect(new PotionEffect(SlimeBreeder.proxy.slimeDomesticatedWait,20, 1));
    			this.removeActivePotionEffect(SlimeBreeder.proxy.slimeDomesticatedFollow);
    			////System.out.println("Wait");
    			player.sendMessage(new TextComponentString("Wait Here"));
    		}
    		
    		if(this.getActivePotionEffect(SlimeBreeder.proxy.slimeDomesticatedWait) != null && stage == 3)
    		{
    			stage = 3;
    			this.addPotionEffect(new PotionEffect(SlimeBreeder.proxy.slimeDomesticated,20, 1));
    			this.removeActivePotionEffect(SlimeBreeder.proxy.slimeDomesticatedWait);
    			player.sendMessage(new TextComponentString("Roam Free"));
    		}
    		
    		
    		
    		////System.out.println("Stage = " + stage);
    		
        }
    	
    	 
    	if (itemstack.isEmpty() && this.getTimer() >= 25)
        {

    			
    			this.setDomestication(domesticated + 200);
    			player.swingArm(hand);
    			
    			
    			if(this.getOwner() != player.getPersistentID())
    			{
    				this.setOwner(player.getPersistentID());
    				////System.out.println("New Owner = " + this.owner);
    				
    			}
    			
    			
    			
    			for (int i = 0; i < 7; ++i)
    			{
    				double d0 = this.rand.nextGaussian() * 0.02D;
    				double d1 = this.rand.nextGaussian() * 0.02D;
    				double d2 = this.rand.nextGaussian() * 0.02D;
    				this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2, new int[0]);
    				
    			}
    			
        		Iterable<ItemStack> armour = player.getArmorInventoryList();
        		
        		List<Item> armourList = new ArrayList<Item>();
            	List<Item> fullarmourList = new ArrayList<Item>();
            	List<Item> comparmourList = new ArrayList<Item>();
            	
            	armourList.clear();
        		fullarmourList.clear();
        		comparmourList.clear();
        		
        		fullarmourList.add(SlimeBreederItems.SLIMEINFUSEDHELM);
        		fullarmourList.add(SlimeBreederItems.SLIMEINFUSEDCHEST);
        		fullarmourList.add(SlimeBreederItems.SLIMEINFUSEDLEGGINGS);
        		fullarmourList.add(SlimeBreederItems.SLIMEINFUSEDBOOTS);
        		
        		for(ItemStack equip : armour)
        		{
        			if(equip != null)
        			{
        			armourList.add(equip.getItem());
        			}
        		}
            	fullarmourList.removeAll(armourList);
            	
            	////System.out.println("No of Armour Pieces = " + (4-fullarmourList.size()));
            	
            	if(fullarmourList.size() != 0)
            	{
            		player.attackEntityFrom(DamageSource.GENERIC, 2.0F);
            	}
    			
    			this.setTimer(0);
    			
    			float volume = 0.2F * 1.0F;
       		 
                this.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, volume, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.2F);
                //this.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_TOUCH, volume, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.6F);
                //this.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_TOUCH, volume, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.1F);

    			
    			return true;

        }
    	
    	 if (!(itemstack.isEmpty()))
         {
    		 
    		 /*if (itemstack.getItem() == SlimeBreederItems.BREEDINGCATALYSTIRON && !this.worldObj.isRemote)
    	    	{
    			 int stackid1 = player.inventory.getSlotFor(itemstack);
    			 //player.inventory.consumeInventoryItem(SlimeBreeder.proxy.BreedingCatalystIron);
    			 //player.inventory.deleteStack(new ItemStack(SlimeBreederItems.BREEDINGCATALYSTIRON));
    			 player.inventory.decrStackSize(stackid1, 1);
    			 //player.inventory.addItemStackToInventory(new ItemStack(SlimeBreeder.proxy.SlimeCapsule, 1));
    			 //worldObj.spawnParticle(EnumParticleTypes.VILLAGER_ANGRY, this.posX, this.posY, this.posZ, 1.00, 1.00, 1.00);
    			 this.breedinggroup = 1;
    			 return true;
    	    	}*/
    		 
    		 float volume = 0.4F * 1.0F;
    		 
             this.playSound(SoundEvents.ENTITY_SLIME_SQUISH, volume, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 2.0F);

    		 
    		 
		    		 if(itemstack.getItem() instanceof ItemArmor || itemstack.getItem() instanceof ItemSword || itemstack.getItem() instanceof ItemPickaxe || itemstack.getItem() instanceof ItemSpade || itemstack.getItem() instanceof ItemTool)
		    		 {
		    			 
		    			 NBTTagCompound nbt = itemstack.getTagCompound();
		    			 
		    			 if(nbt == null)
		    			 {
		    				 EnumCraftingIngredients.listOfItems.addIfAbsent((new SlimeItems(itemstack, 0D, 0D, 0, 0, 0, 60)));
		    			 }
		    			 
		    			 ////System.out.println("Coatable " + nbt.toString());
		    			if(nbt != null)
		    			{
			    			 if(!(nbt.getCompoundTag("slimebreeder").getKeySet().contains("core")))
			    			 {
			    				 
			    				 ////System.out.println("Not Slime Infused");
			    				 EnumCraftingIngredients.listOfItems.addIfAbsent((new SlimeItems(itemstack, 0D, 0D, 0, 0, 0, 60)));
			    			 }
		    			}
		    		 }
		    		 
		    		 int length = EnumCraftingIngredients.listOfItems.size();
    		 
    		 for (int i = 0; i < length; i++) 
    	    	{
    			 
    			 ////System.out.println("adding");
    			 Item listItem = EnumCraftingIngredients.listOfItems.get(i).getItemStack().getItem();

    		 if (listItem == itemstack.getItem()  && !this.world.isRemote)
    		 {
    			 /*double minMove = EnumCraftingIngredients.listOfItems.get(i).movespeed;
    			 double minHealth = EnumCraftingIngredients.listOfItems.get(i).health;
    			 int minAcidic = EnumCraftingIngredients.listOfItems.get(i).attackdmg;
    			 int minSticky = EnumCraftingIngredients.listOfItems.get(i).sticky;
    			 
    			 double slimeMove = this.getMoveSpeed();
    			 double slimeHealth = this.getSlimeHealth();
    			 int slimeAcidic = this.getAttackDmg();
    			 int slimeSticky = this.getSticky();
    			 
    			 if(slimeMove >= minMove && slimeHealth >= minHealth && slimeAcidic >= minAcidic && slimeSticky >= minSticky)
    			 {*/
    			 
    			 ItemStack copyitemstack = itemstack.copy();
    			 
    			 NBTTagCompound Serial =  copyitemstack.serializeNBT();
    			 
    			 Serial.setByte("Count", (byte)1);
    			 ItemStack newitemstack = new ItemStack(Serial);
    			 
    			 
    			 
    			 
    			 ////System.out.println("ItemStack before = " + itemstack);
    			 ////System.out.println("ItemStack after  = " + newitemstack);
    			 //newitemstack.getTagCompound().setByte("Count",(byte)1);
    			 
    			 //int handStack = player.inventory.getSlotFor(itemstack);
    			 
    			
    			if(player.inventory.hasItemStack(itemstack))
    			{
    				player.inventory.clearMatchingItems(itemstack.getItem(), -1, 1, null);
    				
    				//player.inventory.decrStackSize(handStack, 1);
    			}
    			 
    			
     		 
    			 
    			 
    			 if(getSlimeItemSlot1() == -1 || getSlimeItemSlot1() == -1 && itemstack.getItem() instanceof ItemBlock )
    			 {
    				 //if(part1.compareTo("minecraft") == 0)
    				 //{
    				 this.itemId1 =  Item.getIdFromItem(newitemstack.getItem());
    				 this.processItemSlot1 = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
    				 this.processItemSlot1Max = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
    				 
    				 setSlimeItemSlot1(this.itemId1);
    				 this.dataManager.set(ITEMSLOT1, this.itemId1);
    				 
    				 setItemProcessSlot1(this.processItemSlot1);
    				 this.dataManager.set(ITEMPROCESSSLOT1, this.processItemSlot1);
    				 
    				 setItemProcessSlot1Max(this.processItemSlot1Max);
    				 this.dataManager.set(ITEMPROCESSSLOT1MAX, this.processItemSlot1Max);
    				 
    				 this.setSlot(newitemstack, 0);
    				 
    				 
    				 /*NBTTagCompound slotSerial = itemstack.serializeNBT();

    				 this.SlotOne = slotSerial;*/
    					 
    				 
    				 
    				 //this.SlotOne = slotSerial;
    				 
    				 
    				 
    			}else if(getSlimeItemSlot2() == -1 && itemstack.getItem() instanceof ItemBlock == false && Item.getItemById(getSlimeItemSlot1()) instanceof ItemBlock == false)
    			 {
    				 
    				 this.itemId2 =  Item.getIdFromItem(newitemstack.getItem());
    				 this.processItemSlot2 = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
    				 this.processItemSlot2Max = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
    				 
    				 setSlimeItemSlot2(this.itemId2);
    				 this.dataManager.set(ITEMSLOT2, this.itemId2);
    				 
    				 setItemProcessSlot2(this.processItemSlot2);
    				 this.dataManager.set(ITEMPROCESSSLOT2, this.processItemSlot2);
    				 
    				 setItemProcessSlot2Max(this.processItemSlot2Max);
    				 this.dataManager.set(ITEMPROCESSSLOT2MAX, this.processItemSlot2Max);

    				 this.setSlot(newitemstack, 1);
    				
    				  
    				 
    			 }else if(getSlimeItemSlot3() == -1 && itemstack.getItem() instanceof ItemBlock == false && Item.getItemById(getSlimeItemSlot1()) instanceof ItemBlock == false)
    			 {
    				 
    				 this.itemId3 =  Item.getIdFromItem(newitemstack.getItem());
    				 this.processItemSlot3 = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
    				 this.processItemSlot3Max = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
    				 
    				 setSlimeItemSlot3(this.itemId3);
    				 this.dataManager.set(ITEMSLOT3, this.itemId3);
    				 
    				 setItemProcessSlot3(this.processItemSlot3);
    				 this.dataManager.set(ITEMPROCESSSLOT3, this.processItemSlot3);
    				 
    				 setItemProcessSlot3Max(this.processItemSlot3Max);
    				 this.dataManager.set(ITEMPROCESSSLOT3MAX, this.processItemSlot3Max);
    				 
    				 this.setSlot(newitemstack, 2);
    				 
    				 
    				 
    				 
    				 
    			 }else if(getSlimeItemSlot4() == -1 && itemstack.getItem() instanceof ItemBlock == false && Item.getItemById(getSlimeItemSlot1()) instanceof ItemBlock == false)
    			 {

    				 this.itemId4 =  Item.getIdFromItem(newitemstack.getItem());
    				 this.processItemSlot4 = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
    				 this.processItemSlot4Max = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
    				 
    				 setSlimeItemSlot4(this.itemId4);
    				 this.dataManager.set(ITEMSLOT4, this.itemId4);
    				 
    				 setItemProcessSlot4(this.processItemSlot4);
    				 this.dataManager.set(ITEMPROCESSSLOT4, this.processItemSlot4);
    				 
    				 setItemProcessSlot4Max(this.processItemSlot4Max);
    				 this.dataManager.set(ITEMPROCESSSLOT4MAX, this.processItemSlot4Max);
    				 

    				 this.setSlot(newitemstack, 3);
    				
    				 
    				 
    			 }else if(getSlimeItemSlot5() == -1 && itemstack.getItem() instanceof ItemBlock == false && Item.getItemById(getSlimeItemSlot1()) instanceof ItemBlock == false)
    			 {
    				 this.itemId5 =  Item.getIdFromItem(newitemstack.getItem());
    				 this.processItemSlot5 = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
    				 this.processItemSlot5Max = EnumCraftingIngredients.listOfItems.get(i).getmaxProcessTime();
    				 
    				 setSlimeItemSlot5(this.itemId5);
    				 this.dataManager.set(ITEMSLOT5, this.itemId5);
    				 
    				 setItemProcessSlot5(this.processItemSlot5);
    				 this.dataManager.set(ITEMPROCESSSLOT5, this.processItemSlot5);
    				 
    				 setItemProcessSlot5Max(this.processItemSlot5Max);
    				 this.dataManager.set(ITEMPROCESSSLOT5MAX, this.processItemSlot5Max);
    				 

    				 this.setSlot(newitemstack, 4);
    				 
    			 }else{
    				 entityDropItem(newitemstack.copy(), 1F);
    			 }
    			 
    			 }
    			 
    	    	}
    		 
    		 if (itemstack.getItem() == SlimeBreederItems.UNKNOWNITEM && !this.world.isRemote)
    		 {
    			 
    			 
    			 
    			/* if(this.varient == 1)
    			 {
    				 ////System.out.println("Varient = " + this.varient);
    				 this.varient = 2; 
    				 return false;
    			 }
    			
    			 if(this.varient == 2)
    			 {
    				 ////System.out.println("Varient = " + this.varient);
    				 this.varient = 3;
    				 return false;
    			 }
    			 
    			 if(this.varient == 3)
    			 {
    				 ////System.out.println("Varient = " + this.varient);
    				 this.varient = 4;
    				 return false;
    			 }
    			 
    			 if(this.varient == 4)
    			 {
    				 ////System.out.println("Varient = " + this.varient);
    				 this.varient = 1;
    				 return false;
    			 }*/
    			 
    			 //this.setDomestication(domesticated + 1000);
    			 
    			 Render living = (Render)Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(EntityChicken.class);
    			 
    			 System.out.println((Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(EntityChicken.class).getClass()));
    			 
    			 //System.out.println(Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(this).getRenderManager().);

    			 RenderLivingBase livingBase = ((RenderLivingBase)living);
    			 
    			 System.out.println(livingBase.getMainModel().toString());
    			 
    			 //System.out.println(this.getDataManager().get(ENTITY).toString());
    			 
    			 //Entity cow = new EntityCow(this.getEntityWorld());
    			 //NBTTagCompound entitycowTag = cow.serializeNBT();
    			 
    			 //this.getDataManager().set(ENTITY, entitycowTag);
    			 
    			 //Entity entityD = EntityList.createEntityFromNBT(entityTag, this.getEntityWorld());
    			 
    			 //System.out.println(((EntityLiving)entityD).tasks);
    			 
    			 //entityD.get
    			 
    			 ITextComponent component1 = new TextComponentString(TextFormatting.AQUA + "Domestication = " + TextFormatting.WHITE + this.domesticated);
    			 ITextComponent component2 = new TextComponentString(TextFormatting.GOLD + "Wave = " + TextFormatting.WHITE + this.getWave());
    			 ITextComponent component3 = new TextComponentString(TextFormatting.GREEN + "Breeding Speed = " + TextFormatting.WHITE + this.getBreedingSpeed());
    			 //ITextComponent component4 = new TextComponentString(TextFormatting.GREEN + "Temperature = " + TextFormatting.WHITE + this.getTemperature());
    			 
    			 ITextComponent component5 = new TextComponentString(TextFormatting.GOLD + "MoveSpeed = " + TextFormatting.WHITE + this.movespeed);
    			 ITextComponent component6 = new TextComponentString(TextFormatting.GREEN + "Health = " + TextFormatting.WHITE + this.getHealth());
    			 ITextComponent component7 = new TextComponentString(TextFormatting.AQUA + "Attack = " + TextFormatting.WHITE + this.getAttackDmg());
    			 ITextComponent component8 = new TextComponentString(TextFormatting.GREEN + "Sticky = " + TextFormatting.WHITE + this.getSticky());
    			 
    			 
    			 ITextComponent drop = new TextComponentString(TextFormatting.GREEN + "Drop = " + TextFormatting.WHITE + new ItemStack(this.SlotDrop).getItem().getUnlocalizedName());
    			 ITextComponent despawn = new TextComponentString(TextFormatting.GREEN + "Owner ID = " + TextFormatting.WHITE + this.getOwner());
    			 
    			 ITextComponent slot1 = new TextComponentString(TextFormatting.GREEN + "Slot 1 = " + TextFormatting.WHITE + new ItemStack(this.SlotOne).getItem().getUnlocalizedName() + " x " + (this.SlotOne).getByte("Count") );
    			 ITextComponent slot2 = new TextComponentString(TextFormatting.GREEN + "Slot 2 = " + TextFormatting.WHITE + new ItemStack(this.SlotTwo).getItem().getUnlocalizedName() + " x " + (this.SlotTwo).getByte("Count") );
    			 ITextComponent slot3 = new TextComponentString(TextFormatting.GREEN + "Slot 3 = " + TextFormatting.WHITE + new ItemStack(this.SlotThree).getItem().getUnlocalizedName() + " x " + (this.SlotThree).getByte("Count") );
    			 ITextComponent slot4 = new TextComponentString(TextFormatting.GREEN + "Slot 4 = " + TextFormatting.WHITE + new ItemStack(this.SlotFour).getItem().getUnlocalizedName() + " x " + (this.SlotFour).getByte("Count") );
    			 ITextComponent slot5 = new TextComponentString(TextFormatting.GREEN + "Slot 5 = " + TextFormatting.WHITE + new ItemStack(this.SlotFive).getItem().getUnlocalizedName() + " x " + (this.SlotFive).getByte("Count") );
    			 
    			 ITextComponent FResult = new TextComponentString(TextFormatting.GREEN + "Future = " + TextFormatting.WHITE + new ItemStack(this.SlotFutureResult).getItem().getUnlocalizedName() + " x " + (this.SlotFutureResult).getByte("Count") );
    			 ITextComponent Result = new TextComponentString(TextFormatting.GREEN + "Result = " + TextFormatting.WHITE + new ItemStack(this.SlotResult).getItem().getUnlocalizedName() + " x " + (this.SlotResult).getByte("Count") );
    			 ITextComponent FutureResultID = new TextComponentString(TextFormatting.GREEN + "Result id = " + TextFormatting.WHITE + SlotFutureResult.getInteger("Recipe"));
    			 ITextComponent ResultID = new TextComponentString(TextFormatting.GREEN + "Past Result id = " + TextFormatting.WHITE + SlotResult.getInteger("Recipe"));

    			 
    			 //ITextComponent space = new TextComponentString(TextFormatting.AQUA + "==================");
    			 
    			 ITextComponent space = new TextComponentTranslation("test.my_message.hello");
    			 
    			 System.out.println(space.getFormattedText());

    			 
    			 //player.sendMessage(space);
    			 player.sendMessage(component1);
    			 player.sendMessage(component2);
    			 player.sendMessage(component3);
    			 //player.sendMessage(component4);
    			 //player.sendMessage(space);
    			 player.sendMessage(component5);
    			 player.sendMessage(component6);
    			 player.sendMessage(component7);
    			 player.sendMessage(component8);
    			 /*player.sendMessage(space);
    			 player.sendMessage(drop);
    			 player.sendMessage(despawn);
    			 player.sendMessage(space);
    			 player.sendMessage(slot1);
    			 player.sendMessage(slot2);
    			 player.sendMessage(slot3);
    			 player.sendMessage(slot4);
    			 player.sendMessage(slot5);
    			 player.sendMessage(FResult);
    			 player.sendMessage(Result);
    			 player.sendMessage(ResultID);
    			 player.sendMessage(FutureResultID);*/

    			 
    			 //addItemSlimeFloor(new ItemStack(Items.CHICKEN));
    			 
    			 
    			 return false;
    			
    			 
    			 
    			 
    		 }
    		 
    		 /*if (itemstack.getItem() == SlimeBreederItems.CLEARSLIMECRYSTAL)
    		 {
    			 
    			 
    			 NBTTagCompound storedSlime = this.serializeNBT();
    			 this.despawnEntity();
    			 
    			 //System.out.println(storedSlime);
    			 
    			 EntityList.createEntityFromNBT(storedSlime.copy(), this.worldObj);
    			 
    			 
    			 /*ItemStack enchItem = new ItemStack(Items.STICK);
    			 enchItem.addEnchantment(Enchantment.getEnchantmentByLocation("knockback"), 100);
    			 
    			 player.inventory.addItemStackToInventory(enchItem);
    			 
    			 player.addVelocity(100D, 0D, 0D);*/
    			 	
    			 
    			 
    			////System.out.println("Slime hunger = " + this.getHunger());
    			
    			 
    			 ////System.out.println("Processing...... " + this.dataManager.get(ITEMPROCESSSLOT1));
     	    	////System.out.println("Processing Max...... " + this.dataManager.get(ITEMPROCESSSLOT1MAX));
    			 //setSlimeItemSlot1(-1);
    			 
    			
    			 
    			 
    		 //}
    		 
        
    		 
    		 
    		 //if (itemstack.getItem() == SlimeBreederItems.SLIMALYSER && !this.worldObj.isRemote && player.inventory.hasItemStack(new ItemStack(SlimeBreederItems.SLIMECAPSULE)) )
	    		 if(itemstack.getItem() == SlimeBreederItems.SLIMALYSER && player.inventory.hasItemStack(new ItemStack(SlimeBreederItems.SLIMECAPSULE)))
	    		 {
	    		 player.playSound(SlimeBreederSounds.SLIMALYSER_ZAP, 1.0F, 1.0F);
	    		 }
    	if(itemstack.getItem() == SlimeBreederItems.SLIMALYSER && player.inventory.hasItemStack(new ItemStack(SlimeBreederItems.SLIMECAPSULE)) && !(this.world.isRemote) )
    	{

        	Item slimedrop =  SlimeBreederItems.FILLEDSLIMECAPSULE;
        	Item slimeBook = SlimeBreederItems.SLIMEPEDIA;
        	Item slimeHelm = SlimeBreederItems.SLIMEINFUSEDHELM;
        	
        	//Item drop = (Item)slimedrop;
        		
        	int sdw = this.getWave();
        	float sdb = this.getBright();
        	float sds = this.getSat();
        	
        	ItemStack capsule = new ItemStack(SlimeBreederItems.FILLEDSLIMECAPSULE);
        	
        	NBTTagCompound tagCompound = new NBTTagCompound();
        	
        	capsule.setTagCompound(tagCompound);
        	
        	capsule.getTagCompound().setInteger("Wave", sdw);
        	capsule.getTagCompound().setFloat("Sat", sdb);
        	capsule.getTagCompound().setFloat("Bright", sds);
        	capsule.getTagCompound().setInteger("BreedingSpeed", this.getBreedingSpeed());
        	capsule.getTagCompound().setDouble("MoveSpeed", this.getMoveSpeed());
        	capsule.getTagCompound().setDouble("Health", getSlimeHealth());
        	capsule.getTagCompound().setFloat("AttackDamage", this.getAttackDmg());
        	capsule.getTagCompound().setFloat("Sticky", this.getSticky());
        	
        	//NBTTagCompound slimeDrop = capsule.serializeNBT();
        	
	        	for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
	            {
	        		if(player.inventory.getStackInSlot(i) != null)
	        		{
	
		                if (Item.getIdFromItem(player.inventory.getStackInSlot(i).getItem()) == Item.getIdFromItem(SlimeBreederItems.SLIMECAPSULE))
		                {
		                	ItemStack ammo = player.inventory.getStackInSlot(i);
		                   
		                       player.inventory.clearMatchingItems(ammo.getItem(), -1, 1, null);
		                       
		                  	   java.util.Random rand = new java.util.Random();
		                  	   
		                       net.minecraft.entity.item.EntityItem ent = this.entityDropItem(capsule, 1.0F);
		                       ent.motionY += rand.nextFloat() * 0.05F;
		                       ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
		                       ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
		                   
		                      
		          			   this.attackEntityFrom(DamageSource.GENERIC, 5);
		                   
		                   
		                   return true;
		                }
	        		}
	            }

        			return true;
        	}
         }
         
        		return true;
        			
    	}


    public void initItemSlots()
    {
    	
    	this.SlotOne = SlotSlime;
    	this.SlotTwo = SlotSlime;
    	this.SlotThree = SlotSlime;
    	this.SlotFour = SlotSlime;
    	this.SlotFive  = SlotSlime;
    	this.SlotResult  = SlotSlime;
    	this.SlotDrop  = SlotSlime;
    	this.SlotFutureResult  = SlotSlime;
    	

    }
    
    public NBTTagCompound getSlotOne()
    {
    	return SlotOne;
    }
    
    
    
    
    public void processBuffer()
    {
    	/*int slot1 = bufferInventory[0];
    	int slot2 = bufferInventory[1];
    	int slot3 = bufferInventory[2];
    	int slot4 = bufferInventory[3];
    	int slot5 = bufferInventory[4];*/
    	
    	int hungry = this.getHunger();
    	
    	int VanillaCatalyst = Item.getIdFromItem(SlimeBreederItems.BREEDINGCATALYSTIRON);
    	
    	
    	for(int i = 0; i < 5; i++)
    	{
    		int slot = bufferInventory[i];
    		
    		if(slot == 365)
    		{
    			bufferInventory[i] = 0;
        		this.setHunger(hungry + 1000);
        		//this.setTemperature(temperature + 10);
        		
        		if(this.getHunger() > (this.getBreedingSpeed()/2))
        		{
        			this.setHunger(this.getBreedingSpeed()/2);
        		}
        		
        		this.setDomestication(this.domesticated + 50);
        		
        		////System.out.println("Hunger = " + this.getHunger());
        		////System.out.println("buffer index = " + i + ", id = " + slot);
    		}
    		
    		if(slot == Item.getIdFromItem(SlimeBreederItems.BREEDINGCATALYSTIRON))
    				{
    			this.breedinggroup = 1;
    			this.enablePersistence();
    			
    				}
    		
    		
        }
    		
    		
    		
    		/*if(slot == 365 || slot == Item.getIdFromItem(SlimeBreederItems.BREEDINGCATALYSTIRON))
    		{
    			bufferInventory[i] = 0;
    			//this.setNBTSlot(i);
        		this.setHunger(hungry + 1000);
        		if(this.getHunger() > (this.getBreedingSpeed()/2))
        		{
        			this.setHunger(this.getBreedingSpeed()/2);
        		}
        		
        		//System.out.println("Hunger = " + this.getHunger());
        		//System.out.println("buffer index = " + i + ", id = " + slot);
        		
        		
        		
    		}*/
    		
    	}
    	
    public void decompResults()
    {
    	
    	//System.out.println("Start Decomp");
    	
    	EnumRecipes.listOfRecipes.clear();
    	EnumRecipes.initRecipeList();
    	
    	
    	NBTTagCompound nbtFuture = new ItemStack(SlotFutureResult).getTagCompound();
    	NBTTagCompound nbtResult = new ItemStack(SlotResult).getTagCompound();
    	
    	if(SlotFutureResult != SlotSlime)
    	{
    		//System.out.println("Future Result not Slime");
    		
    		addInfused(SlotFutureResult);
    		addInfused(SlotResult);
    		//System.out.println("Decomposing = " + new ItemStack(SlotFutureResult).getItem().getUnlocalizedName());
    		//decomp(SlotFutureResult);
    	}
    	
    	if(SlotResult != SlotSlime)
    	{
    		//System.out.println("Result not Slime");
    		
    		addInfused(SlotFutureResult);
    		addInfused(SlotResult);
    		//System.out.println("Decomposing = " + new ItemStack(SlotResult).getItem().getUnlocalizedName());
    		decomp(SlotResult);
    		
    	
    		
    	}
    }
    	
    	public void decomp(NBTTagCompound slotLookup)
    	{
    		
    		int i = 0;
    		
    		if(slotLookup == SlotFutureResult && slotLookup != SlotSlime)
    		{
    			i = this.SlotFutureResult.getInteger("Recipe");
    		}else if(slotLookup == SlotResult && slotLookup != SlotSlime)
    		{
    			i = this.SlotResult.getInteger("Recipe");
    		}
    		
    		if(new ItemStack(slotLookup).getItem() == EnumRecipes.listOfRecipes.get(i).getItem7().getItem())
    		{
    			
    			//System.out.println("Found Decomp Recipe =  " + EnumRecipes.listOfRecipes.get(i).getItem7().getItem().getUnlocalizedName());
    			
    			int recipeIndex = i;
    			
    			int slotLookupCount = slotLookup.getByte("Count");
    			//System.out.println("slotLookup Count = " + slotLookupCount);
    			int recipeLookupCount = EnumRecipes.listOfRecipes.get(recipeIndex).getItem7().serializeNBT().getByte("Count");
    			//System.out.println("recipeLookup Count = " + recipeLookupCount);
    			int mult = (slotLookup.getByte("Count")/EnumRecipes.listOfRecipes.get(recipeIndex).getItem7().serializeNBT().getByte("Count"));
    			
    			//System.out.println("multiplyer = " + slotLookupCount + "/" + recipeLookupCount + " = " + mult);
    			
    			NBTTagCompound sudoSlotOne = EnumRecipes.listOfRecipes.get(recipeIndex).getItem1().serializeNBT();
    			NBTTagCompound sudoSlotTwo = EnumRecipes.listOfRecipes.get(recipeIndex).getItem2().serializeNBT();
    			NBTTagCompound sudoSlotThree = EnumRecipes.listOfRecipes.get(recipeIndex).getItem3().serializeNBT();
    			NBTTagCompound sudoSlotFour = EnumRecipes.listOfRecipes.get(recipeIndex).getItem4().serializeNBT();
    			NBTTagCompound sudoSlotFive = EnumRecipes.listOfRecipes.get(recipeIndex).getItem5().serializeNBT();
    			
    			int a1 = sudoSlotOne.getByte("Count");
    			sudoSlotOne.setByte("Count", (byte)(a1*mult));
    			//System.out.println("sudoslot1 count = " + a1*mult);
    			
    			int b1 = sudoSlotTwo.getByte("Count");
    			sudoSlotTwo.setByte("Count", (byte)(b1*mult));
    			//System.out.println("sudoslot2 count = " + b1*mult);
    			
    			int c1 = sudoSlotThree.getByte("Count");
    			sudoSlotThree.setByte("Count", (byte)(c1*mult));
    			//System.out.println("sudoslot3 count = " + c1*mult);
    			
    			int d1 = sudoSlotFour.getByte("Count");
    			sudoSlotFour.setByte("Count", (byte)(d1*mult));
    			//System.out.println("sudoslot4 count = " + d1*mult);
    			
    			int e1 = sudoSlotFive.getByte("Count");
    			sudoSlotFive.setByte("Count", (byte)(e1*mult));
    			//System.out.println("sudoslot5 count = " + e1*mult);
    			////////////////////////////////////////////////
    			
    			int a2 = SlotOne.getByte("Count");
    			
    			int b2 = SlotTwo.getByte("Count");
    			
    			int c2 = SlotThree.getByte("Count");
    			
    			int d2 = SlotFour.getByte("Count");
    			
    			int e2 = SlotFive.getByte("Count");
    			
    			
    			List<NBTTagCompound> itemList = new ArrayList<NBTTagCompound>();
    			List<NBTTagCompound> removeslotList = new ArrayList<NBTTagCompound>();
    			
    			removeslotList.add(SlotSlime);
    			itemList.removeAll(removeslotList);
    			
    			
    			
    			itemList.add(sudoSlotOne);
    			itemList.add(sudoSlotTwo);
    			itemList.add(sudoSlotThree);
    			itemList.add(sudoSlotFour);
    			itemList.add(sudoSlotFive);
    			
    			////System.out.println("ItemList length = " + itemList.size());
    			
    			for (int i2 = 0; i2 < itemList.size(); i2++) 
    	    	{
    				////System.out.println("ItemList index = " + i2);
    				////System.out.println("ItemList item at index = " + itemList.get(i2).getString("id"));
    				////System.out.println("Slot = " + SlotOne.getString("id"));
    				
    				if(new ItemStack(itemList.get(i2)).getItem() == new ItemStack(SlotSlime).getItem())
    				{
    					////System.out.println("Slime Found");
    					removeslotList.add(itemList.get(i2));
    				}
    				
    				if(new ItemStack(itemList.get(i2)).getItem() == new ItemStack(SlotOne).getItem() )
    				{
    					////System.out.println("Same id");
    					////System.out.println("item at index = " + itemList.get(i2).getString("id"));
    					////System.out.println("Slot = " + SlotOne.getString("id"));
    					
    					if(new ItemStack(itemList.get(i2)).getItem() != new ItemStack(SlotSlime).getItem())
    					{
    						//////System.out.println("SlotOne = " + SlotOne);
    						//System.out.println("Found Slot One, Slot One Count = " + SlotOne.getByte("Count") + " SudoSlot One Count = " + itemList.get(i2).getByte("Count"));
    						SlotOne.setByte("Count", (byte) (itemList.get(i2).getByte("Count")+SlotOne.getByte("Count")));
    						SlotOne.removeTag("Recipe");
    						//System.out.println("New Slot One = " + SlotOne.getByte("Count"));
    						////System.out.println("SlotOne = " + SlotOne);
    						removeslotList.add(itemList.get(i2));
    						
    						
    						
    					}
    				}
    				
    				if(new ItemStack(itemList.get(i2)).getItem() == new ItemStack(SlotTwo).getItem())
    				{
    					if(new ItemStack(itemList.get(i2)).getItem() != new ItemStack(SlotSlime).getItem())
    					{
    						//System.out.println("Found Slot Two, Slot Two Count = " + SlotTwo.getByte("Count") + " SudoSlot Two Count = " + itemList.get(i2).getByte("Count"));
    						SlotTwo.setByte("Count", (byte) (itemList.get(i2).getByte("Count")+SlotTwo.getByte("Count")));
    						SlotTwo.removeTag("Recipe");
    						//System.out.println("New Slot One = " + SlotTwo.getByte("Count"));
    						
    						////System.out.println("SlotTwo = " + SlotTwo);
    						removeslotList.add(itemList.get(i2));
    					}
    				}
    				
    				if(new ItemStack(itemList.get(i2)).getItem() == new ItemStack(SlotThree).getItem())
    				{
    					//System.out.println(new ItemStack(itemList.get(i2)).getItem().getUnlocalizedName() + " x " + itemList.get(i2).getByte("Count"));
    					if(new ItemStack(itemList.get(i2)).getItem() != new ItemStack(SlotSlime).getItem())
    					{
    						//System.out.println("Found Slot Three, Slot Three Count = " + SlotThree.getByte("Count") + " SudoSlot Three Count = " + itemList.get(i2).getByte("Count"));
    						SlotThree.setByte("Count", (byte) (itemList.get(i2).getByte("Count")+SlotThree.getByte("Count")));
    						SlotThree.removeTag("Recipe");
    						//System.out.println("New Slot Three = " + SlotThree.getByte("Count"));
    						
    						////System.out.println("SlotThree = " + SlotThree);
    						removeslotList.add(itemList.get(i2));
    					}
    				}
    				
    				if(new ItemStack(itemList.get(i2)).getItem() == new ItemStack(SlotFour).getItem())
    				{
    					if(new ItemStack(itemList.get(i2)).getItem() != new ItemStack(SlotSlime).getItem())
    					{
    						//System.out.println("Found Slot Four, Slot Four Count = " + SlotFour.getByte("Count") + " SudoSlot Four Count = " + itemList.get(i2).getByte("Count"));
    						SlotFour.setByte("Count", (byte) (itemList.get(i2).getByte("Count")+SlotFour.getByte("Count")));
    						SlotFour.removeTag("Recipe");
    						//System.out.println("New Slot Four = " + SlotFour.getByte("Count"));
    						
    						
    						////System.out.println("SlotFour = " + SlotFour);
    						removeslotList.add(itemList.get(i2));
    					}
    				}
    				
    				if(new ItemStack(itemList.get(i2)).getItem() == new ItemStack(SlotFive).getItem())
    				{
    					if(new ItemStack(itemList.get(i2)).getItem() != new ItemStack(SlotSlime).getItem())
    					{
    						//System.out.println("Found Slot Five, Slot Five Count = " + SlotFive.getByte("Count") + " SudoSlot Five Count = " + itemList.get(i2).getByte("Count"));
    						SlotFive.setByte("Count", (byte) (itemList.get(i2).getByte("Count")+SlotFive.getByte("Count")));
    						SlotFive.removeTag("Recipe");
    						//System.out.println("New Slot Five = " + SlotFive.getByte("Count"));
    						
    						////System.out.println("SlotFive = " + SlotFive);
    						removeslotList.add(itemList.get(i2));
    					}
    				}
    				
    				
    	    	}
    			
    			itemList.removeAll(removeslotList);
    			
    			List<NBTTagCompound> slotList = new ArrayList<NBTTagCompound>();
    			
    			
    			slotList.add(SlotOne);
    			slotList.add(SlotTwo);
    			slotList.add(SlotThree);
    			slotList.add(SlotFour);
    			slotList.add(SlotFive);
    			
    			
    			for (int i3 = 0; i3 < slotList.size(); i3++) 
    	    	{
    				if(slotList.get(i3).getString("id") == SlotSlime.getString("id"))
    				{
    					switch (i3) {
    			        case 0:
    			        	if(!(itemList.isEmpty()))
    			        	{
    			        	SlotOne = itemList.get(0);
    			        	itemList.remove(0);
    			        	}
    			            break;
    			        case 1:
    			        	if(!(itemList.isEmpty()))
    			        	{
    			        	SlotTwo = itemList.get(0);
    			        	itemList.remove(0);
    			        	}
    			            break;
    			        case 2:
    			        	if(!(itemList.isEmpty()))
    			        	{
    			        	SlotThree = itemList.get(0);
    			        	itemList.remove(0);
    			        	}
    			            break;
    			        case 3:
    			        	if(!(itemList.isEmpty()))
    			        	{
    			        	SlotFour = itemList.get(0);
    			        	itemList.remove(0);
    			        	}
    			            break;
    			        case 4:
    			        	if(!(itemList.isEmpty()))
    			        	{
    			        	SlotFive = itemList.get(0);
    			        	itemList.remove(0);
    			        	}
    			        	break;
    			        	
    								}
    				
    					}
    	    	}
    			
    			slotList.removeAll(removeslotList);
    			

    				this.SlotResult = SlotSlime;
    			
    			
    			/*SlotTwo = sudoSlotTwo;
    			SlotThree = sudoSlotThree;
    			SlotFour = sudoSlotFour;
    			SlotFive = sudoSlotFive;*/
    			
    			
    			}
    	}
    
    public void setNBTSlot(int index)
    {
    	////System.out.println("Slot" + index + " Reset");
    	switch (index) {
        case 0:
            SlotOne = SlotSlime;
            break;
        case 1:
        	SlotTwo = SlotSlime;
            break;
        case 2:
        	SlotThree = SlotSlime;
            break;
        case 3:
        	SlotFour = SlotSlime;
            break;
        case 4:
        	SlotFive = SlotSlime;
        	break;
        case 5:
        	SlotResult = SlotSlime;
        	break;
        case 6:
        	SlotDrop = SlotSlime;
        	break;
    					}
    }
    
    public ItemStack setDrop()
    {
    	////System.out.println("Drops");
    	
    	EnumItemColor.listOfObjects.clear();
    	EnumItemColor.initColorList();
    	

    	int length = EnumItemColor.listOfObjects.size();
    	////System.out.println("length = " + length);
    	
		List<SlimeColor> stackList = new CopyOnWriteArrayList<SlimeColor>();
    	
    	for (int i = 0; i < length; i++) {
    		
    		int Iwave = EnumItemColor.listOfObjects.get(i).getWave();
    		float Isat = EnumItemColor.listOfObjects.get(i).getSat();
    		float Ibrightness = EnumItemColor.listOfObjects.get(i).getBright();
    		

    		/*//System.out.println("indexl1 = " + EnumItemColor.getSize());
    		//System.out.println("indexl2 = " +length);
    		//System.out.println("index = " + i);
    		//System.out.println("Lwave = " + (Lwave-10) + " " + this.wave + " " + (Lwave+10));
    		//System.out.println("Lsat = " + (Lsat-0.1F) + " " + this.sat + " " + (Lsat+0.1F));
    		//System.out.println("Lbrightness = " + (Lbrightness-0.1F) +  " " + this.brightness + " " + (Lbrightness+0.1F));
    		
    		if((this.wave > (Iwave-10) && (this.wave < (Iwave+10))))
    		if((this.sat > (Isat-0.1F)) && (this.sat < (Isat+0.1F)))
    			if((this.brightness > (Ibrightness-0.1F)) && (this.brightness < (Ibrightness+0.1F)))
    			{
    		*
    		*/
    		
    	if((this.wave > (Iwave-10) && (this.wave < (Iwave+10))))
    		if((this.sat > (Isat-0.1F)) && (this.sat < (Isat+0.1F)))
    			if((this.brightness > (Ibrightness-0.1F)) && (this.brightness < (Ibrightness+0.1F)))
    			{
    				//entityDropItem(EnumItemColor.listOfObjects.get(i).getItemStack().copy(), 1F);
    				////System.out.println("item called = " + EnumItemColor.listOfObjects.get(i).getItemStack());
    				////System.out.println(EnumItemColor.getSize());
    				//i = EnumItemColor.listOfObjects.size();
    				
    				ItemStack result = EnumItemColor.listOfObjects.get(i).getItemStack().copy();
    				
    				////System.out.println("success, ItemStack = " + result);
    				EnumItemColor.listOfObjects.clear();
    				return result;
    				
    				
    				
    			}
    									}
    	
    	////System.out.println("failed, ItemStack = " + new ItemStack(Items.SLIME_BALL));
    	EnumItemColor.listOfObjects.clear();
    	return new ItemStack(Items.SLIME_BALL);
    	}
    
    public void setCraftResult()
    {
    	List<NBTTagCompound> inventoryList = new ArrayList<NBTTagCompound>();
    	List<NBTTagCompound> recipeList = new ArrayList<NBTTagCompound>();
    	List<NBTTagCompound> comparisonList = new ArrayList<NBTTagCompound>();
    	
    	inventoryList.clear();
		recipeList.clear();
		comparisonList.clear();
		
		////System.out.println("Init inv = " + inventoryList.toString());
		////System.out.println("Init recipe = " + recipeList.toString());
		////System.out.println("Init comparison = " + comparisonList.toString());

    	
    	
    	////System.out.println("add inv = " + inventoryList.toString());
    	
    	EnumRecipes.listOfRecipes.clear();
    	EnumRecipes.initRecipeList();
    	

    	NBTSlot(SlotOne);
    	NBTSlot(SlotTwo);
    	NBTSlot(SlotThree);
    	NBTSlot(SlotFour);
    	NBTSlot(SlotFive);
    	
    	int length =EnumRecipes.listOfRecipes.size();
    	
    	
    	
    	////System.out.println("======Start Recipe=====");
    	
    	for (int i = 0; i < length; i++) 
    	{
    		ItemStack item7 = EnumRecipes.listOfRecipes.get(i).getItem7();
    		
    		if(EnumRecipes.listOfRecipes.get(i).getNBTCrafting() == false && !this.world.isRemote)
    		{
    			////System.out.println("No NBT craft");
    			
    			////System.out.println("List Add " + SlotOne);
    			////System.out.println("List Add " + SlotTwo);
    			////System.out.println("List Add " + SlotThree);
    			////System.out.println("List Add " + SlotFour);
    			////System.out.println("List Add " + SlotFive);
    	    	
    	    	NBTTagCompound detagSlotOne = SlotOne.copy();
    			NBTTagCompound detagSlotTwo = SlotTwo.copy();
    			NBTTagCompound detagSlotThree = SlotThree.copy();
    			NBTTagCompound detagSlotFour = SlotFour.copy();
    			NBTTagCompound detagSlotFive = SlotFive.copy();
    			NBTTagCompound detagSlotDrop = SlotDrop.copy();
    			
    			/*detagSlotOne.removeTag("tag");
    			detagSlotTwo.removeTag("tag");
    			detagSlotThree.removeTag("tag");
    			detagSlotFour.removeTag("tag");
    			detagSlotFive.removeTag("tag");
    			detagSlotDrop.removeTag("tag");*/
    			
    			detagSlotOne.removeTag("Count");
    			detagSlotTwo.removeTag("Count");
    			detagSlotThree.removeTag("Count");
    			detagSlotFour.removeTag("Count");
    			detagSlotFive.removeTag("Count");
    			detagSlotDrop.removeTag("Count");
    			
    			detagSlotOne.removeTag("Damage");
    			detagSlotTwo.removeTag("Damage");
    			detagSlotThree.removeTag("Damage");
    			detagSlotFour.removeTag("Damage");
    			detagSlotFive.removeTag("Damage");
    			detagSlotDrop.removeTag("Damage");
    			
    			////System.out.println("List Add detag " + detagSlotOne);
    			////System.out.println("List Add detag " + detagSlotTwo);
    			////System.out.println("List Add detag " + detagSlotThree);
    			////System.out.println("List Add detag " + detagSlotFour);
    			////System.out.println("List Add detag " + detagSlotFive);
    			
    			inventoryList.add(detagSlotOne);
    	    	inventoryList.add(detagSlotTwo);
    	    	inventoryList.add(detagSlotThree);
    	    	inventoryList.add(detagSlotFour);
    	    	inventoryList.add(detagSlotFive);
    	    	inventoryList.add(detagSlotDrop);
    			
    		NBTTagCompound item1 = EnumRecipes.listOfRecipes.get(i).getItem1().serializeNBT();
    		NBTTagCompound item2 = EnumRecipes.listOfRecipes.get(i).getItem2().serializeNBT();
    		NBTTagCompound item3 = EnumRecipes.listOfRecipes.get(i).getItem3().serializeNBT();
    		NBTTagCompound item4 = EnumRecipes.listOfRecipes.get(i).getItem4().serializeNBT();
    		NBTTagCompound item5 = EnumRecipes.listOfRecipes.get(i).getItem5().serializeNBT();
    		NBTTagCompound item6 = EnumRecipes.listOfRecipes.get(i).getItem6().serializeNBT();
    		
    		item1.removeTag("Count");
    		item2.removeTag("Count");
    		item3.removeTag("Count");
    		item4.removeTag("Count");
    		item5.removeTag("Count");
    		item6.removeTag("Count");
    		
    		item1.removeTag("Damage");
    		item2.removeTag("Damage");
    		item3.removeTag("Damage");
    		item4.removeTag("Damage");
    		item5.removeTag("Damage");
    		item6.removeTag("Damage");
    		
    		
    		
    		
    		
			////System.out.println("Recipe item = " + EnumRecipes.listOfRecipes.get(1).getItem1());
	    	////System.out.println("Inv item = " + new ItemStack(detagSlotOne).toString());
    		 
    		
    		recipeList.add(item1);
    		recipeList.add(item2);
    		recipeList.add(item3);
    		recipeList.add(item4);
    		recipeList.add(item5);
    		recipeList.add(item6);
    		
    		////System.out.println("add recipe = " + recipeList.toString());
    		
    		comparisonList.addAll(inventoryList);
    		comparisonList.addAll(recipeList);
    		
    		
    		
    		////System.out.println("add comparison = " + comparisonList.toString());
    		////System.out.println("add Inv = " + inventoryList.toString());
    		
    		comparisonList.removeAll(inventoryList);
    		
    		////System.out.println("remove comparison = " + comparisonList.toString());

    		////System.out.println("Items left = " + comparisonList.size());
    		
    		}else if(EnumRecipes.listOfRecipes.get(i).getNBTCrafting() == true && !this.world.isRemote)
    		{
    			////System.out.println("NBT craft!");
    			
    			inventoryList.add(SlotOne);
            	inventoryList.add(SlotTwo);
            	inventoryList.add(SlotThree);
            	inventoryList.add(SlotFour);
            	inventoryList.add(SlotFive);
            	inventoryList.add(SlotDrop);
        		
            	NBTTagCompound item1 = EnumRecipes.listOfRecipes.get(i).getItem1().serializeNBT();
        		NBTTagCompound item2 = EnumRecipes.listOfRecipes.get(i).getItem2().serializeNBT();
        		NBTTagCompound item3 = EnumRecipes.listOfRecipes.get(i).getItem3().serializeNBT();
        		NBTTagCompound item4 = EnumRecipes.listOfRecipes.get(i).getItem4().serializeNBT();
        		NBTTagCompound item5 = EnumRecipes.listOfRecipes.get(i).getItem5().serializeNBT();
        		NBTTagCompound item6 = EnumRecipes.listOfRecipes.get(i).getItem6().serializeNBT();
        		
    			////System.out.println("Recipe item = " + EnumRecipes.listOfRecipes.get(1).getItem1());
    	    	////System.out.println("Inv item = " + new ItemStack(SlotOne).toString());
        		
        		recipeList.add(item1);
        		recipeList.add(item2);
        		recipeList.add(item3);
        		recipeList.add(item4);
        		recipeList.add(item5);
        		recipeList.add(item6);
        		
        		comparisonList.addAll(inventoryList);
        		comparisonList.addAll(recipeList);
        		
        		comparisonList.removeAll(inventoryList);	
    		}
    		
    		////System.out.println("Items in list = " + comparisonList.toString());
    		
    		if(comparisonList.size() == 1)
    		{
    			if(comparisonList.contains(SlotDrop))
    			{
    				////System.out.println("Drop Problem");
    			}
    		}
    		
    		
    		
    		if(comparisonList.size() == 0)
    		{
    			int index = i;
    			//System.out.println("Recipe Found, Index = " + index);
    			/*this.setPreviousCraft(index);
    			if(this.SlotResult == this.SlotSlime)
    			{
    				this.setLastCraft(index);
    			}else{
    				this.setPreviousCraft(index);
    			}*/
    			
    			i = length;
    			
    			NBTTagCompound Resultitem1 = EnumRecipes.listOfRecipes.get(index).getItem1().serializeNBT();
        		NBTTagCompound Resultitem2 = EnumRecipes.listOfRecipes.get(index).getItem2().serializeNBT();
        		NBTTagCompound Resultitem3 = EnumRecipes.listOfRecipes.get(index).getItem3().serializeNBT();
        		NBTTagCompound Resultitem4 = EnumRecipes.listOfRecipes.get(index).getItem4().serializeNBT();
        		NBTTagCompound Resultitem5 = EnumRecipes.listOfRecipes.get(index).getItem5().serializeNBT();
        		
        		List<NBTTagCompound> ingredientList = new ArrayList<NBTTagCompound>();
        		List<Integer> multiList = new ArrayList<Integer>();
        		List<Integer> compList = new ArrayList<Integer>();
        		
        		ingredientList.add(Resultitem1);
        		ingredientList.add(Resultitem2);
        		ingredientList.add(Resultitem3);
        		ingredientList.add(Resultitem4);
        		ingredientList.add(Resultitem5);
        		
        		int mult1 = -1;
        		int mult2 = -1;
        		int mult3 = -1;
        		int mult4 = -1;
        		int mult5 = -1;
        		
        		int ingreLength = ingredientList.size();
        		
        		////System.out.println("List size  = " + ingredientList.size());
        		
        		for(int i1 = 0; i1 < ingreLength; i1++)
        		{
        			if(new ItemStack(SlotOne).getItem() != new ItemStack(SlotSlime).getItem())
        			{
        				if(new ItemStack(SlotOne).getItem() == new ItemStack(ingredientList.get(i1)).getItem())
        				{
        					byte numSlot1 = SlotOne.getByte("Count");
        					byte numResultitem1 = ingredientList.get(i1).getByte("Count");
        					
        					mult1 = numSlot1/numResultitem1;
        					multiList.add(mult1);
        					
        					
        				}
        			}
        			
        			if(new ItemStack(SlotTwo).getItem() != new ItemStack(SlotSlime).getItem())
        			{
        				if(new ItemStack(SlotTwo).getItem() == new ItemStack(ingredientList.get(i1)).getItem())
        				{
        					byte numSlot2 = SlotTwo.getByte("Count");
        					byte numResultitem2 = ingredientList.get(i1).getByte("Count");
        					
        					mult2 = numSlot2/numResultitem2;
        					multiList.add(mult2);
        					////System.out.println("Multiplier = " + multiList.toString());
        				}
        			}
        			
        			if(new ItemStack(SlotThree).getItem() != new ItemStack(SlotSlime).getItem())
        			{
        				if(new ItemStack(SlotThree).getItem() == new ItemStack(ingredientList.get(i1)).getItem())
        				{
        					byte numSlot3 = SlotThree.getByte("Count");
        					byte numResultitem3 = ingredientList.get(i1).getByte("Count");
        					
        					mult3 = numSlot3/numResultitem3;
        					multiList.add(mult3);
        					////System.out.println("Multiplier = " + multiList.toString());
        				}
        			}
        			
        			if(new ItemStack(SlotFour).getItem() != new ItemStack(SlotSlime).getItem())
        			{
        				if(new ItemStack(SlotFour).getItem() == new ItemStack(ingredientList.get(i1)).getItem())
        				{
        					byte numSlot4 = SlotFour.getByte("Count");
        					byte numResultitem4 = ingredientList.get(i1).getByte("Count");
        					
        					mult4 = numSlot4/numResultitem4;
        					multiList.add(mult4);
        					////System.out.println("Multiplier = " + multiList.toString());
        				}
        			}
        			
        			if(new ItemStack(SlotFive).getItem() != new ItemStack(SlotSlime).getItem())
        			{
        				if(new ItemStack(SlotFive).getItem() == new ItemStack(ingredientList.get(i1)).getItem())
        				{
        					byte numSlot5 = SlotFive.getByte("Count");
        					byte numResultitem5 = ingredientList.get(i1).getByte("Count");
        					
        					mult5 = numSlot5/numResultitem5;
        					multiList.add(mult5);
        					////System.out.println("Multiplier = " + multiList.toString());
        				}
        			}
			        	
        		}
        		//System.out.println("Multiplier = " + multiList.toString());
        		int prevSize = multiList.size();
        		
        		if(prevSize != 0)
        		{
        		compList.add(multiList.get(0));
        		multiList.removeAll(compList);
        		
        		}
        		
        		////System.out.println("Multiplier = " + multiList.toString());
    			
    			////System.out.println("ItemStack = " + item7);
    			////System.out.println("Serial = " + item7.serializeNBT());
    			
    			
    			NBTTagCompound slotSerial = item7.serializeNBT(); 
    			
    			if(multiList.isEmpty() && prevSize != 0)
        		{
    				slotSerial.setByte("Count", (byte) (slotSerial.getByte("Count")*compList.get(0)));
    				
    				if(slotSerial.getByte("Count")==0)
    				{
    					slotSerial = SlotSlime;
    				}
    				
    				multiList.clear();
    				compList.clear();
        		}
    			
    			slotSerial.setInteger("Recipe", index);
    			
    			this.SlotFutureResult = slotSerial;
    			
    			/*this.SlotOne = this.SlotSlime;
    			this.SlotTwo = this.SlotSlime;
    			this.SlotThree = this.SlotSlime;
    			this.SlotFour = this.SlotSlime;
    			this.SlotFive = this.SlotSlime;*/
    			
    			inventoryList.clear();
    			//recipeList.clear();
    			comparisonList.clear();
    			
    		}else{
    			////System.out.println("Not Found");
    			//this.SlotResult = slotSlime;
    			inventoryList.clear();
    			recipeList.clear();
    			comparisonList.clear();
    		}
    	
    	
    	}
    	
    }
    
    public void NBTSlot(NBTTagCompound slot)
    {
    	
    	if(new ItemStack(slot).getItem() instanceof ItemArmor || new ItemStack(slot).getItem() instanceof ItemSword || new ItemStack(slot).getItem() instanceof ItemPickaxe || new ItemStack(slot).getItem() instanceof ItemSpade || new ItemStack(slot).getItem() instanceof ItemTool )
    	{
    		ItemStack stack = new ItemStack(slot);
    		
    		if(stack.getItem() instanceof ItemArmor)
    		{	
		    		switch (((ItemArmor) stack.getItem()).armorType) 
		    		{
			        case HEAD:
			        	ItemStack stackHelm = new ItemStack(SlimeBreederItems.SLIMEINFUSEDHELM);
			        	initInfusedArmor(stackHelm);
			        	TransferEnchItem(stack, stackHelm, true);
			        	break;
					case CHEST:
						ItemStack stackChest = new ItemStack(SlimeBreederItems.SLIMEINFUSEDCHEST);
			        	initInfusedArmor(stackChest);
			        	TransferEnchItem(stack, stackChest, true);
						break;
					case FEET:
						ItemStack stackBoots = new ItemStack(SlimeBreederItems.SLIMEINFUSEDBOOTS);
			        	initInfusedArmor(stackBoots);
			        	TransferEnchItem(stack, stackBoots, true);
						break;
					case LEGS:
						ItemStack stackLeggings = new ItemStack(SlimeBreederItems.SLIMEINFUSEDLEGGINGS);
			        	initInfusedArmor(stackLeggings);
			        	TransferEnchItem(stack, stackLeggings, true);
						break;
					case MAINHAND:
						
						break;
					case OFFHAND:
						
						break;
					default:
						
						break;
		    		}	
    			}
    		
    		
    			if(stack.getItem() instanceof ItemSword)
    			{
    				ItemStack stackSword = new ItemStack(SlimeBreederItems.SLIMEINFUSEDSWORD);
		        	initInfusedArmor(stackSword);
		        	TransferEnchItem(stack, stackSword, false);
    			}
    			
    			if(stack.getItem() instanceof ItemPickaxe)
    			{
    				ItemStack stackPickaxe = new ItemStack(SlimeBreederItems.SLIMEINFUSEDPICKAXE);
		        	initInfusedArmor(stackPickaxe);
		        	TransferEnchItem(stack, stackPickaxe, false);
    			}
    			
    			if(stack.getItem() instanceof ItemAxe)
    			{
    				ItemStack stackAxe = new ItemStack(SlimeBreederItems.SLIMEINFUSEDAXE);
		        	initInfusedArmor(stackAxe);
		        	TransferEnchItem(stack, stackAxe, false);
    			}
    			
    			if(stack.getItem() instanceof ItemSpade)
    			{
    				ItemStack stackSpade = new ItemStack(SlimeBreederItems.SLIMEINFUSEDSPADE);
		        	initInfusedArmor(stackSpade);
		        	TransferEnchItem(stack, stackSpade, false);
    			}
    			
    			
    		
    		
    		
    		}
    	}	
    		//EnumRecipes.listOfRecipes.addIfAbsent(new SlimeRecipes(new ItemStack(SlimeBreederItems.CLEARSLIMECRYSTAL), stack, new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Blocks.SAND,1,0), 0.0D, 0.0D, 0, 0, 0, false));
			
    	
    	
    


	public void TransferEnchItem(ItemStack stack, ItemStack coated, boolean isArmour)
	{
		ItemStack resultItem;
		String blank = "Blank Text";
		if(stack.getTagCompound() != null)
		{
			if(stack.getTagCompound().hasKey("ench"))
	    	{
	    		
	    		NBTTagList enchList = stack.getEnchantmentTagList();
	    		
	    		for(int i = 0; i < enchList.tagCount(); i++)
	    		{
	    			short id = enchList.getCompoundTagAt(i).getShort("id");
	    			short lvl = enchList.getCompoundTagAt(i).getShort("lvl");
	    			coated.addEnchantment(Enchantment.getEnchantmentByID(id), lvl);
	    			
	    		}
	        	
	        	
	        	NBTTagCompound nbt = coated.getSubCompound(SlimeBreeder.MODID);
	    		NBTTagList coreitems = nbt.getTagList("core", 10);
	    		coreitems.set(0, stack.serializeNBT());
	    		////System.out.println("Items After = " + coreitems.getCompoundTagAt(0));
	    		if(isArmour)
	    		{
	    		ItemStack enchantedArmor = slimeEnchItem(coated, ((ItemArmor)stack.getItem()).armorType);
	    		resultItem = enchantedArmor.copy();
	    		EnumRecipes.listOfRecipes.addIfAbsent(new SlimeRecipes(new ItemStack(SlimeBreederItems.CLEARSLIMECRYSTAL), stack, new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), resultItem, 0.0D, 0.0D, 0, 0, 0, true, blank));
	    		}else{
	    			ItemStack enchantedArmor = slimeEnchItem(coated, EntityEquipmentSlot.MAINHAND);
	    			resultItem = enchantedArmor.copy();
	    			
		    		EnumRecipes.listOfRecipes.addIfAbsent(new SlimeRecipes(new ItemStack(SlimeBreederItems.CLEARSLIMECRYSTAL), stack, new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), resultItem, 0.0D, 0.0D, 0, 0, 0, true, blank));
	    		}
	    		
	    		
	    		
	    	}else{
	    		
	    		NBTTagCompound nbt = coated.getSubCompound(SlimeBreeder.MODID);
	    		NBTTagList coreitems = nbt.getTagList("core", 10);
	    		coreitems.set(0, stack.serializeNBT());
	    		////System.out.println("Items After = " + coreitems.getCompoundTagAt(0));
	    		
	    		if(isArmour)
	    		{
	    		ItemStack enchantedArmor = slimeEnchItem(coated, ((ItemArmor)stack.getItem()).armorType);
	    		resultItem = enchantedArmor.copy();
	    		EnumRecipes.listOfRecipes.addIfAbsent(new SlimeRecipes(new ItemStack(SlimeBreederItems.CLEARSLIMECRYSTAL), stack, new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), resultItem, 0.0D, 0.0D, 0, 0, 0, true, blank));
	    		}else{
	    			////System.out.println("Not Armour");
	    			ItemStack enchantedArmor = slimeEnchItem(coated, EntityEquipmentSlot.MAINHAND);
	    			resultItem = enchantedArmor.copy();
		    		EnumRecipes.listOfRecipes.addIfAbsent(new SlimeRecipes(new ItemStack(SlimeBreederItems.CLEARSLIMECRYSTAL), stack, new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), resultItem, 0.0D, 0.0D, 0, 0, 0, true, blank));
	    		}
	    	}
			
			////System.out.println("Final Item = " + resultItem.serializeNBT());
		}else{
			
			NBTTagCompound nbt = coated.getSubCompound(SlimeBreeder.MODID);
    		NBTTagList coreitems = nbt.getTagList("core", 10);
    		coreitems.set(0, stack.serializeNBT());
    		////System.out.println("Items After = " + coreitems.getCompoundTagAt(0));
    		
    		if(isArmour)
    		{
    		ItemStack enchantedArmor = slimeEnchItem(coated, ((ItemArmor)stack.getItem()).armorType);
    		resultItem = enchantedArmor.copy();
    		EnumRecipes.listOfRecipes.addIfAbsent(new SlimeRecipes(new ItemStack(SlimeBreederItems.CLEARSLIMECRYSTAL), stack, new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), resultItem, 0.0D, 0.0D, 0, 0, 0, true,blank));
    		}else{
    			////System.out.println("Not Armour");
    			ItemStack enchantedArmor = slimeEnchItem(coated, EntityEquipmentSlot.MAINHAND);
    			resultItem = enchantedArmor.copy();
	    		EnumRecipes.listOfRecipes.addIfAbsent(new SlimeRecipes(new ItemStack(SlimeBreederItems.CLEARSLIMECRYSTAL), stack, new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), resultItem, 0.0D, 0.0D, 0, 0, 0, true, blank));
    		}
    		
    		////System.out.println("Final Item = " + resultItem.serializeNBT());
			
		}
		
			
			
		
		
		
		
	}
	
    
    public ItemStack slimeEnchItem(ItemStack result, EntityEquipmentSlot type)
    {
    	int BS = this.getBreedingSpeed(); 
    	double dropMoveSpeed2 = this.getMoveSpeed(); 
    	double dropHealth2 = this.getSlimeHealth(); 
    	float AD = this.getAttackDmg();
    	float STY = this.getSticky();
    	
    	////System.out.println("Start = " + result.serializeNBT());
    	////System.out.println("Type = " + type);
    	
    	////System.out.println("Test");
    	
    	////System.out.println("BS = " + BS);
    	////System.out.println("dropMoveSpeed2 = " + dropMoveSpeed2);
    	////System.out.println("dropHealth2 = " + dropHealth2);
    	////System.out.println("AD = " + AD);
    	////System.out.println("STY = " + STY);
    	
    	
    	NBTTagList coatingEnch;
    	//coatingEnch.appendTag(Enchantment.getEnchantmentByLocation("));
    	if(type == EntityEquipmentSlot.FEET)
    		{
    		
    			result.addEnchantment(SlimeBreederEnchantments.FLAMMABLE, 1);
    		
	    		if (BS > 45856 && BS <= 91712) 
	            {
	    			
	            } else if (BS > 22928 && BS <= 45856) {
	            	result.addEnchantment(SlimeBreederEnchantments.BOUNCY, 1);
	            } else if (BS > 11464 && BS <= 22928) {
	            	result.addEnchantment(SlimeBreederEnchantments.BOUNCY, 3);
	            } else if (BS > 5732 && BS <= 11464) {
	            	result.addEnchantment(SlimeBreederEnchantments.BOUNCY, 5);
	            } else {
	            	////System.out.println("Error Applying Boots BreedSpeed");
	            }
    		
		    	if (dropHealth2 >= 4 && dropHealth2 < 6) 
		        {
		    		result.addEnchantment(SlimeBreederEnchantments.BREAKABLE, 2);
		        } else if (dropHealth2 >= 6 && dropHealth2 < 8) {
		        	result.addEnchantment(SlimeBreederEnchantments.BREAKABLE, 1);
		        } else if (dropHealth2 >= 8 && dropHealth2 < 12) {
		        	result.addEnchantment(Enchantment.getEnchantmentByLocation("unbreaking"), 1);
		        } else if (dropHealth2 > 12 && dropHealth2 <= 16) {
		        	result.addEnchantment(Enchantment.getEnchantmentByLocation("unbreaking"), 2);
		        } else {
		        	 ////System.out.println("Error Applying Boots Health");
		        }
		    	
		    	if (AD >= 2 && AD < 4) 
		        {
		    		
		        } else if (AD >= 4 && AD < 6) {
		        	 
		        } else if (AD >= 6 && AD < 8) {
		        	result.addEnchantment(SlimeBreederEnchantments.ACIDIC, 1);
		        } else if (AD > 8 && AD <= 100) {
		        	result.addEnchantment(SlimeBreederEnchantments.ACIDIC, 2);
		        } else {
		        	////System.out.println("Error Applying Boots AttackDamage");
		        }
		    	
		    	
		    	
    		}
    	
    	if(type == EntityEquipmentSlot.LEGS)
		{

			result.addEnchantment(SlimeBreederEnchantments.FLAMMABLE, 1);
    		
		
	    	if (dropHealth2 >= 4 && dropHealth2 < 6) 
	        {
	    		result.addEnchantment(SlimeBreederEnchantments.BREAKABLE, 2);
	    		
	        } else if (dropHealth2 >= 6 && dropHealth2 < 8) {
	        	result.addEnchantment(SlimeBreederEnchantments.BREAKABLE, 1);
	        	
	        }
	        
	        else if (dropHealth2 >= 8 && dropHealth2 < 12) {
	        	
	        }
	    	
	        else if (dropHealth2 >= 12 && dropHealth2 <= 16) {
	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("unbreaking"), 1);
	        	
	        } else if (dropHealth2 > 16 && dropHealth2 <= 50) {
	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("unbreaking"), 2);
	        	
	        } else {
	        	 ////System.out.println("Error Applying Leggings Health");
	        }
	    	
	    	if (AD >= 2 && AD < 4) 
	        {
	    		
	        } else if (AD >= 4 && AD < 6) {
	        	 
	        } else if (AD > 6 && AD <= 8) {
	        	result.addEnchantment(SlimeBreederEnchantments.ACIDIC, 1);
	        } else if (AD > 8 && AD <= 10) {
	        	result.addEnchantment(SlimeBreederEnchantments.ACIDIC, 2);
	        } else {
	        	////System.out.println("Error Applying Leggings AttackDamage");
	        }
	    	
	    	if (STY >= 0 && STY <= 1) 
	        {
	    		
	        } else if (STY > 1 && STY <= 2) {
	        	result.addEnchantment(SlimeBreederEnchantments.ADHESIVE, 1);
	        } else if (STY > 2 && STY <= 3) {
	        	result.addEnchantment(SlimeBreederEnchantments.ADHESIVE, 3);
	        } else if (STY > 3 && STY <= 4) {
	        	result.addEnchantment(SlimeBreederEnchantments.ADHESIVE, 5);
	        } else {
	        	////System.out.println("Error Applying Leggings Sticky");
	        }
	    	
		}
    	
    	if(type == EntityEquipmentSlot.CHEST)
		{
		

			result.addEnchantment(SlimeBreederEnchantments.FLAMMABLE, 1);
		
	    	if (dropHealth2 >= 4 && dropHealth2 < 6) 
	        {
	    		result.addEnchantment(SlimeBreederEnchantments.BREAKABLE, 2);
	    		
	        } else if (dropHealth2 >= 6 && dropHealth2 < 8) {
	        	result.addEnchantment(SlimeBreederEnchantments.BREAKABLE, 1);
	        	
	        } else if (dropHealth2 >= 8 && dropHealth2 < 12) {
	        	
	        	
	        } else if (dropHealth2 >= 12 && dropHealth2 <= 16) {
	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("unbreaking"), 1);
	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("protection"), 1);
	        } else if (dropHealth2 > 16 && dropHealth2 <= 50) {
	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("unbreaking"), 2);
	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("protection"), 2);
	        } else {
	        	 ////System.out.println("Error Applying Chest Health");
	        }
	    	
	    	if (AD >= 2 && AD <= 4) 
	        {
	    		
	        } else if (AD > 4 && AD <= 6) {
	        	 
	        } else if (AD > 6 && AD <= 8) {
	        	result.addEnchantment(SlimeBreederEnchantments.ACIDIC, 1);
	        } else if (AD > 8 && AD <= 10) {
	        	result.addEnchantment(SlimeBreederEnchantments.ACIDIC, 2);
	        } else {
	        	////System.out.println("Error Applying Chest AttackDamage");
	        }
	    	
	    	
	    	if (STY >= 0 && STY <= 1) 
	        {
	    		
	        } else if (STY > 1 && STY <= 2) {
	        	result.addEnchantment(SlimeBreederEnchantments.ADHESIVE, 1);
	        } else if (STY > 2 && STY <= 3) {
	        	result.addEnchantment(SlimeBreederEnchantments.ADHESIVE, 3);
	        } else if (STY > 3 && STY <= 4) {
	        	result.addEnchantment(SlimeBreederEnchantments.ADHESIVE, 5);
	        } else {
	        	////System.out.println("Error Applying Chest Sticky");
	        }
	    	
	    	////System.out.println("Finish = " + result.serializeNBT());
	    	
		}
    	
    	
    	if(type == EntityEquipmentSlot.HEAD)
		{
		

			result.addEnchantment(SlimeBreederEnchantments.FLAMMABLE, 1);
		
	    	if (dropHealth2 >= 4 && dropHealth2 < 6) 
	        {
	    		result.addEnchantment(SlimeBreederEnchantments.BREAKABLE, 2);
	    		
	        } else if (dropHealth2 >= 6 && dropHealth2 < 8) {
	        	result.addEnchantment(SlimeBreederEnchantments.BREAKABLE, 1);
	        	
	        } else if (dropHealth2 >= 8 && dropHealth2 < 12) {
	        	
	        	
	        } else if (dropHealth2 >= 12 && dropHealth2 <= 16) {
	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("unbreaking"), 1);
	        	
	        } else if (dropHealth2 > 16 && dropHealth2 <= 50) {
	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("unbreaking"), 2);
	        	
	        } else {
	        	 ////System.out.println("Error Applying Head Health");
	        }
	    	
	    	if (AD >= 2 && AD <= 4) 
	        {
	    		
	        } else if (AD > 4 && AD <= 6) {
	        	 
	        } else if (AD > 6 && AD <= 8) {
	        	result.addEnchantment(SlimeBreederEnchantments.ACIDIC, 1);
	        } else if (AD > 8 && AD <= 10) {
	        	result.addEnchantment(SlimeBreederEnchantments.ACIDIC, 2);
	        } else {
	        	////System.out.println("Error Applying Head AttackDamage");
	        }
	    	
	    	
	    	if (STY >= 0 && STY <= 1) 
	        {
	    		
	        } else if (STY > 1 && STY <= 2) {
	        	result.addEnchantment(SlimeBreederEnchantments.ADHESIVE, 1);
	        } else if (STY > 2 && STY <= 3) {
	        	result.addEnchantment(SlimeBreederEnchantments.ADHESIVE, 3);
	        } else if (STY > 3 && STY <= 4) {
	        	result.addEnchantment(SlimeBreederEnchantments.ADHESIVE, 5);
	        } else {
	        	////System.out.println("Error Applying Head Sticky");
	        }
	    	
	    	
	    	
		}
    	
    	if(type == EntityEquipmentSlot.MAINHAND)
		{
    		////System.out.println("Main hand");
    		
    		if(result.getItem() instanceof ItemSword)
    		{
    			////System.out.println("Sword");
    			if (AD >= 2 && AD <= 4) 
    	        {
    				////System.out.println("AD 2-4");
    	        } else if (AD > 4 && AD <= 6) {
    	        	////System.out.println("AD 4-6");
    	        } else if (AD > 6 && AD <= 8) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), 1);
    	        } else if (AD > 8 && AD <= 10) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), 2);
    	        } else {
    	        	////System.out.println("Error Applying Sword AttackDamage");
    	        }
    			
    			if (dropMoveSpeed2 >= 3 && dropMoveSpeed2 <= 4) 
    	        {
    	           
    	        } else if (dropMoveSpeed2 > 4 && dropMoveSpeed2 <= 5) {
    	        	
    	        } else if (dropMoveSpeed2 > 5 && dropMoveSpeed2 <= 6) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("knockback"), 1);
    	        } else if (dropMoveSpeed2 > 6 && dropMoveSpeed2 <= 7) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("knockback"), 2);
    	        } else {
    	        	////System.out.println("Error Applying Sword MoveSpeed");
    	        }
    			
    			
    		}
    		
    		if(result.getItem() instanceof ItemSpade)
    		{
    			if (AD >= 2 && AD <= 4) 
    	        {
    	    		
    	        } else if (AD > 4 && AD <= 6) {
    	        	 
    	        } else if (AD > 6 && AD <= 8) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), 1);
    	        } else if (AD > 8 && AD <= 10) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), 2);
    	        } else {
    	        	////System.out.println("Error Applying Spade AttackDamage");
    	        }
    			
    			if (dropMoveSpeed2 >= 3 && dropMoveSpeed2 <= 4) 
    	        {
    	           
    	        } else if (dropMoveSpeed2 > 4 && dropMoveSpeed2 <= 5) {
    	        	
    	        } else if (dropMoveSpeed2 > 5 && dropMoveSpeed2 <= 6) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("knockback"), 1);
    	        } else if (dropMoveSpeed2 > 6 && dropMoveSpeed2 <= 7) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("knockback"), 2);
    	        } else {
    	        	////System.out.println("Error Applying Spade MoveSpeed");
    	        }
    		}
    		
    		if(result.getItem() instanceof ItemPickaxe)
    		{
    			if (AD >= 2 && AD <= 4) 
    	        {
    	    		
    	        } else if (AD > 4 && AD <= 6) {
    	        	 
    	        } else if (AD > 6 && AD <= 8) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), 1);
    	        } else if (AD > 8 && AD <= 10) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), 2);
    	        } else {
    	        	////System.out.println("Error Applying Pick AttackDamage");
    	        }
    			
    			if (dropMoveSpeed2 >= 3 && dropMoveSpeed2 <= 4) 
    	        {
    	           
    	        } else if (dropMoveSpeed2 > 4 && dropMoveSpeed2 <= 5) {
    	        	
    	        } else if (dropMoveSpeed2 > 5 && dropMoveSpeed2 <= 6) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("knockback"), 1);
    	        } else if (dropMoveSpeed2 > 6 && dropMoveSpeed2 <= 7) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("knockback"), 2);
    	        } else {
    	        	////System.out.println("Error Applying Pick MoveSpeed");
    	        }
    			
    			if (STY >= 0 && STY <= 1) 
    	        {
    	    		
    	        } else if (STY > 1 && STY <= 2) {
    	        	
    	        } else if (STY > 2 && STY <= 3) {
    	        	
    	        } else if (STY > 3 && STY <= 4) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("silk_touch"), 5);
    	        } else {
    	        	////System.out.println("Error Applying Pick Sticky");
    	        }
    			
    			
    		}
    		
    		if(result.getItem() instanceof ItemTool)
    		{
    			if (AD >= 2 && AD <= 4) 
    	        {
    	    		
    	        } else if (AD > 4 && AD <= 6) {
    	        	 
    	        } else if (AD > 6 && AD <= 8) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), 1);
    	        } else if (AD > 8 && AD <= 10) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), 2);
    	        } else {
    	        	////System.out.println("Error Applying Axe AttackDamage");
    	        }
    			
    			if (dropMoveSpeed2 >= 3 && dropMoveSpeed2 <= 4) 
    	        {
    	           
    	        } else if (dropMoveSpeed2 > 4 && dropMoveSpeed2 <= 5) {
    	        	
    	        } else if (dropMoveSpeed2 > 5 && dropMoveSpeed2 <= 6) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("knockback"), 1);
    	        } else if (dropMoveSpeed2 > 6 && dropMoveSpeed2 <= 7) {
    	        	result.addEnchantment(Enchantment.getEnchantmentByLocation("knockback"), 2);
    	        } else {
    	        	////System.out.println("Error Applying Axe MoveSpeed");
    	        }
    		}
    		
    		
		}
    	
    	ItemStack enchantedResult = result.copy();
    	
    	return enchantedResult;
    	
    }
    
    public void initInfusedArmor(ItemStack stackArmor)
    {
    	if(stackArmor.getTagCompound() == null)
		{
			////System.out.println("Creating Tag");
		stackArmor.getOrCreateSubCompound(SlimeBreeder.MODID);
		NBTTagCompound nbt = stackArmor.getSubCompound(SlimeBreeder.MODID);
		
		
		NBTTagList corelist = new NBTTagList();
		
		ItemStack coreItemStack = new ItemStack(Items.GOLDEN_HELMET, 1, 50);
		NBTTagCompound coreArmor = coreItemStack.serializeNBT();
		
		corelist.appendTag(coreArmor);
		corelist.set(0, coreArmor);
		
		nbt.setTag("core", corelist);		
		
		}
    }
    
    
    public void setSlot(ItemStack newItem, int slotId)
    {

    	EnumEdibleItems.listOfEdibleItems.clear();
    	EnumEdibleItems.initEdibleItemsList();
    	
    	EnumCraftingIngredients.listOfItems.clear();
    	EnumCraftingIngredients.initItemList();
    	
    	int listLength = EnumCraftingIngredients.listOfItems.size();
    	
    	 double minMove;
    	 double minHealth;
    	 int minAcidic;
    	 int minSticky;
    	 
    	 double slimeMove = this.getMoveSpeed();
		 double slimeHealth = this.getSlimeHealth();
		 int slimeAcidic = this.getAttackDmg();
		 int slimeSticky = this.getSticky();
		 
		 boolean reject = false;
    	
   	 for (int a = 0; a < listLength; a++) 
	    	{
			 
			 ////System.out.println("adding");
			 Item listItem = EnumCraftingIngredients.listOfItems.get(a).getItemStack().getItem();

				 if (listItem == newItem.getItem()  && !this.world.isRemote)
				 {
					 minMove = EnumCraftingIngredients.listOfItems.get(a).movespeed;
					 minHealth = EnumCraftingIngredients.listOfItems.get(a).health;
					 minAcidic = EnumCraftingIngredients.listOfItems.get(a).attackdmg;
					 minSticky = EnumCraftingIngredients.listOfItems.get(a).sticky;
					 
					 /*//System.out.println("moveMin = " + minMove);
					 //System.out.println("moveSlime = " + slimeMove);
					 
					 //System.out.println("healthMin = " + minHealth);
					 //System.out.println("healthSlime = " + slimeHealth);
					 
					 //System.out.println("acidicMin = " + minAcidic);
					 //System.out.println("acidicSlime = " + slimeAcidic);
					 
					 //System.out.println("stickyMin = " + minSticky);
					 //System.out.println("stickySlime = " + slimeSticky);*/
					 
					 
					 
					 if(slimeMove < minMove || slimeHealth < minHealth || slimeAcidic < minAcidic || slimeSticky < minSticky)
						 {
							 reject = true;
							 
						 }
		    	
				 }
	    	}
    	
   	 ////System.out.println(reject);
    	
    	int length =EnumEdibleItems.getSize();
    	
    	
    	for (int i = 0; i < length; i++) 
    	{
    		Item Edible = EnumEdibleItems.listOfEdibleItems.get(i).getItemStack().getItem();
    		Item Input = newItem.getItem();
    		
    		if(Input == Edible)
    		{
    			float volume = 0.4F * 1.0F;
    			
    			newItem = new ItemStack(Items.SLIME_BALL);
				 this.playSound(SoundEvents.ENTITY_GENERIC_EAT, volume, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);

    			////System.out.println("Eaten");
    		}
    		
    	}
    	
    	List<Item> currInv = new ArrayList<Item>();
    	List<NBTTagCompound> addInv = new ArrayList<NBTTagCompound>();
    	List<NBTTagCompound> compareInv = new ArrayList<NBTTagCompound>();
    	
    	////System.out.println("Current Inv before clear = " + currInv.toString());
    	
    	currInv.clear();
    	
    	////System.out.println("Current Inv after clear = " + currInv.toString());
    	
    	//setCraftResult();
    	decompResults();
    	
    	
    	currInv.add((new ItemStack(SlotOne).getItem()));
    	currInv.add((new ItemStack(SlotTwo).getItem()));
    	currInv.add((new ItemStack(SlotThree).getItem()));
    	currInv.add((new ItemStack(SlotFour).getItem()));
    	currInv.add((new ItemStack(SlotFive).getItem()));
    	
    	////System.out.println("Current Inv after add = " + currInv.toString());
    	
    	
    	////System.out.println("==========Start============");
    	
    	////System.out.println("Item and Id = " + newItem + " " + slotId );
    	
    	////System.out.println("Current inv Size = " + currInv.size());
    	
    	
    	
    	////System.out.println(currInv.toString());
    	
    	for(int i = 0; i < currInv.size(); i++)
    	{
    		////System.out.println("Compare " + currInv.get(i) + " and  newItem " + newItem.getItem() );
    		
    		if(reject == true)
    		{
    			entityDropItem(newItem.copy(), 1F);
				 rejectItem(slotId);
				 currInv.clear();
				 setCraftResult();
				 
				 this.playSound(SoundEvents.ENTITY_ITEM_PICKUP, this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
				 
				 
				 
    		}
    		
    		else if(currInv.get(i) == newItem.getItem() && newItem.getItem() != Items.SLIME_BALL)
    		{
    			////System.out.println("Same");
    			switch (i) {
    	        case 0:
    	            SlotOne.setByte("Count", (byte) (SlotOne.getByte("Count") + (byte)1));
    	            
    	            
    	            
    	            i = currInv.size();
    	            currInv.clear();
    	            break;
    	        case 1:
    	        	SlotTwo.setByte("Count", (byte) (SlotTwo.getByte("Count") + (byte)1));
    	        	i = currInv.size();
    	        	currInv.clear();
    	            break;
    	        case 2:
    	        	SlotThree.setByte("Count", (byte) (SlotThree.getByte("Count") + (byte)1));
    	        	i = currInv.size();
    	        	currInv.clear();
    	            break;
    	        case 3:
    	        	SlotFour.setByte("Count", (byte) (SlotFour.getByte("Count") + (byte)1));
    	        	i = currInv.size();
    	        	currInv.clear();
    	            break;
    	        case 4:
    	        	SlotFive.setByte("Count", (byte) (SlotFive.getByte("Count") + (byte)1));
    	        	i = currInv.size();
    	        	currInv.clear();
    	        	break;
    						}
    		}else if(currInv.get(i) == new ItemStack(this.SlotSlime).getItem() && newItem.getItem() != Items.SLIME_BALL)
			 {
    			////System.out.println("Slime");
    			switch (i) {
    	        case 0:
    	            SlotOne =  newItem.serializeNBT();
    	            i = currInv.size();
    	            currInv.clear();
    	            break;
    	        case 1:
    	        	SlotTwo  =  newItem.serializeNBT();
    	        	i = currInv.size();
    	        	currInv.clear();
    	            break;
    	        case 2:
    	        	SlotThree  =  newItem.serializeNBT();
    	        	i = currInv.size();
    	        	currInv.clear();
    	            break;
    	        case 3:
    	        	SlotFour  =  newItem.serializeNBT();
    	        	i = currInv.size();
    	        	currInv.clear();
    	            break;
    	        case 4:
    	        	SlotFive  =  newItem.serializeNBT();
    	        	i = currInv.size();
    	        	currInv.clear();
    	        	break;
    						}
			 }else if(currInv.get(i) != new ItemStack(this.SlotSlime).getItem() && currInv.get(i) != newItem.getItem() && i == 4  && newItem.getItem() != Items.SLIME_BALL)
			 {
				 
				 ////System.out.println("No Room");
				 
				 entityDropItem(newItem.copy(), 1F);
				 rejectItem(slotId);
				 currInv.clear();
				 setCraftResult();
				 
				 this.playSound(SoundEvents.ENTITY_ITEM_PICKUP, this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);

			 }
	
    	}
    	
    }
    
    public void rejectItem(int id)
    {
    	switch (id) {
        case 0:
   		this.itemId1 = -1;
    	this.dataManager.set(ITEMSLOT1, this.itemId1);
            break;
        case 1:
        	this.itemId2 = -1;
        	this.dataManager.set(ITEMSLOT2, this.itemId2);
                break;

        case 2:
        	this.itemId3 = -1;
        	this.dataManager.set(ITEMSLOT3, this.itemId3);
                break;

        case 3:
        	this.itemId4 = -1;
        	this.dataManager.set(ITEMSLOT4, this.itemId4);
                break;
        case 4:
        	this.itemId5 = -1;
        	this.dataManager.set(ITEMSLOT5, this.itemId5);
                break;
    				}	
    }
    
    public NBTTagCompound stripSlot(NBTTagCompound slot)
    {
    	List<String> keyList = new ArrayList<String>();
    	
    	NBTTagCompound strippedSlot = slot.copy();
    	////System.out.println(new ItemStack(strippedSlot));
    	
    	keyList.addAll(slot.getKeySet());
    	keyList.remove("Count");
    	keyList.remove("Damage");
    	
    	for(int i = 0; i < keyList.size(); i++)
    	{
    		strippedSlot.removeTag(keyList.get(i));
    		////System.out.println(strippedSlot.getKeySet());
    		////System.out.println(new ItemStack(strippedSlot));
    	}
    	
    	return strippedSlot;
    }
    
    
    ////////////////////////////////////Getters and Setters//////////////////////////////////////////
    public int getRGB2()
    {
    	//return this.dataWatcher.getWatchableObjectInt(20);
    	return ((Integer)this.dataManager.get(RGB)).intValue();
    	
    }
    
    public void setFRGB(int f)
    {
    	this.dataManager.set(RGB, Integer.valueOf(f));
    }
    
    
    
    /* ======================================== FORGE END   =====================================*/
    
    
    
    /* ======================================== FORGE START =====================================*/
    /**
     * Called when the slime spawns particles on landing, see onUpdate.
     * Return true to prevent the spawning of the default particles.
     */
    /* ======================================== FORGE END   =====================================*/

   
}