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
import com.itchymichi.slimebreeder.entity.ai.CustomSlimeMoveHelper;
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


public class EntityArtificalSlimeBase extends EntityCustomSlimeBase
{
	int counter;
	protected int breedinggroup;
	protected int domesticated;
	protected int interactTimer;
	protected int varient;
	protected int hunger;
	
	public float squishAmount;
	public float squishFactor;
	public float prevSquishFactor;
	protected boolean wasOnGround;
	protected int jumpDelay = 1;

	/////////////////////////////////////////////Data Parameters///////////////////////////////////
	
	protected static final DataParameter<Integer> TIMER = EntityDataManager.<Integer>createKey(EntityArtificalSlimeBase.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> VARIENT = EntityDataManager.<Integer>createKey(EntityArtificalSlimeBase.class, DataSerializers.VARINT);
	
	private static final DataParameter<Integer> TEMPERATURE = EntityDataManager.<Integer>createKey(EntityArtificalSlimeBase.class, DataSerializers.VARINT);
	protected static final DataParameter<NBTTagCompound> COREFLUID = EntityDataManager.<NBTTagCompound>createKey(EntityArtificalSlimeBase.class, DataSerializers.COMPOUND_TAG);
	protected static final DataParameter<NBTTagCompound> MASTERCOREFLUID = EntityDataManager.<NBTTagCompound>createKey(EntityArtificalSlimeBase.class, DataSerializers.COMPOUND_TAG);

	
	private NBTTagCompound CoreFluid;
	private NBTTagCompound MasterCoreFluid;
	private int temperature;
	
	public EntityArtificalSlimeBase(World worldIn) {
		super(worldIn);
        this.moveHelper = new CustomSlimeMoveHelper(this);
		
	}
	
	protected void entityInit()
    {
    	
        super.entityInit();
        this.dataManager.register(VARIENT, Integer.valueOf(varient));
        this.dataManager.register(this.TIMER, Integer.valueOf(25));
	    this.dataManager.register(TEMPERATURE, Integer.valueOf(298));
	    this.dataManager.register(COREFLUID, (this.CoreFluid));
	    this.dataManager.register(MASTERCOREFLUID, (this.MasterCoreFluid));
    }
	
	/*public void initWildSlime(int varient, int wave, float saturation, float brightness, int breedingspeed, double movespeed, double health, int attackdmg, int sticky)
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
        
       
        
    }*/
	
	@Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {


		this.movespeed = 6;
		this.health = 8;
		this.attackdmg = 6;
		this.sticky = 2;
		
    	
	    this.varient = 1;



	    this.breedinggroup = 0;
	    this.domesticated = 0;
	    

	    

	    //this.setSlimeSize(1);

	    ////System.out.println("Growing Age = " + this.getGrowingAge());

	    System.out.println("Gen breedingspeed = " + this.breedingspeed);
	    System.out.println("Gen movespeed = " + this.movespeed);
	    System.out.println("Gen health = " + this.health);
	    System.out.println("Gen attackdmg = " + this.attackdmg);
	    


	    //this.setGenetics(this.breedinggroup, this.breedingspeed, this.movespeed, this.health, this.attackdmg, this.sticky, this.domesticated);
	    
	    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.health);
	    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1*this.movespeed);
	    

		
		   return super.onInitialSpawn(difficulty, livingdata);
    }
	
	
	
	 public void initArtificalSlime(@Nullable int varient, @Nullable double movespeed, @Nullable double health, @Nullable int attackdmg, @Nullable int sticky)
	    {
	    	
	    
	    	
	    this.varient = 1;



	    this.breedinggroup = 0;
	    this.domesticated = 0;
	    
	    this.movespeed = movespeed;
	    this.health = health;
	    this.attackdmg = attackdmg;
	    this.sticky = sticky;
	    

	    //this.setSlimeSize(1);

	    ////System.out.println("Growing Age = " + this.getGrowingAge());

	    System.out.println("Gen breedingspeed = " + this.breedingspeed);
	    System.out.println("Gen movespeed = " + this.movespeed);
	    System.out.println("Gen health = " + this.health);
	    System.out.println("Gen attackdmg = " + this.attackdmg);
	    


	    //this.setGenetics(this.breedinggroup, this.breedingspeed, this.movespeed, this.health, this.attackdmg, this.sticky, this.domesticated);
	    
	    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.health);
	    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1*this.movespeed);
	    this.setHealth(this.getMaxHealth());



	    //this.dataWatcher.updateObject(16, Byte.valueOf((byte) 1));

	    //this.setColor(wavelength);
	    //this.red = this.getRed();
	    //this.green = this.getGreen();
	    //this.blue = this.getBlue();
	    }
	
	 @Override
	    public void onUpdate()
	    {
	    	
	    	


	        
	        
	        doJump();
	        //this.customSetSize(this.getSizeX()*(float)2, this.getSizeY()*(float)2);
	    }
	    
	    public void doJump() {
	    	super.doJump();
	    }
	
	 public void setTemperature(int newTemp)
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
			return this.dataManager.get(COREFLUID);
		}
		
	
	 public NBTTagCompound getMasterCoreFluid() {
			// TODO Auto-generated method stub
			return this.dataManager.get(MASTERCOREFLUID);
		}
		
	
	@Override
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
    	super.writeEntityToNBT(tagCompound);
    	
    	tagCompound.setInteger("BreedingGroup", this.getBreedingGroup());
    	tagCompound.setInteger("Varient", this.getVarient());
    	this.dataManager.set(VARIENT, tagCompound.getInteger("Varient"));
    	tagCompound.setInteger("Hunger", this.getHunger());
    	tagCompound.setInteger("Timer", this.getTimer());
    	tagCompound.setInteger("Temperature", this.getTemperature());
    	tagCompound.setTag("corefluid", this.getCoreFluid());
    	this.dataManager.set(COREFLUID, tagCompound.getCompoundTag(("corefluid")));
    	tagCompound.setTag("mastercorefluid", this.getMasterCoreFluid());
    	this.dataManager.set(MASTERCOREFLUID, tagCompound.getCompoundTag(("mastercorefluid")));

    }



	/**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        super.readEntityFromNBT(tagCompound);

        this.wasOnGround = tagCompound.getBoolean("wasOnGround");
    	this.setGenetics(tagCompound.getInteger("BreedingGroup"), tagCompound.getInteger("BreedingSpeed"), tagCompound.getDouble("MoveSpeed"), tagCompound.getDouble("Health"), tagCompound.getInteger("AttackDmg"), tagCompound.getInteger("Sticky"), tagCompound.getInteger("Domesticated") );

    	this.setVarient(tagCompound.getInteger("Varient"));
    	this.dataManager.set(VARIENT, tagCompound.getInteger("Varient"));
    	
    	this.setTimer(tagCompound.getInteger("Timer"));
    	this.dataManager.set(TIMER, tagCompound.getInteger("Timer"));
    	
    	this.setHunger(tagCompound.getInteger("Hunger"));
    	
    	this.setOwner(tagCompound.getUniqueId("Owner"));
    	
      	this.setTemperature(tagCompound.getInteger("Temperature"));
    	this.dataManager.set(TEMPERATURE, tagCompound.getInteger("Temperature"));
    	
    	this.setCoreFluid(tagCompound.getCompoundTag(("corefluid")));
    	this.dataManager.set(COREFLUID, tagCompound.getCompoundTag(("corefluid")));
    	
    	this.setMasterCoreFluid(tagCompound.getCompoundTag(("mastercorefluid")));
    	this.dataManager.set(MASTERCOREFLUID, tagCompound.getCompoundTag(("mastercorefluid")));
    }
    
    
	
	
	 /////////////////////////////Setters & Getters ///////////////////////////////////////

    
	public int getBreedingGroup()
	{
	   return this.breedinggroup;
	}
	
	public void setBreedingGroup(int group)
    {
		this.breedinggroup = group;
    }
	
	public void setGenetics(int Breedinggroup, int Breedingspeed, double Movespeed, double Health, int Attackdmg, int Sticky, int Domesticated)
    {
    	this.breedinggroup = Breedinggroup;
    	this.breedingspeed = Breedingspeed;
    	this.movespeed = Movespeed;
    	this.health = Health;
    	this.attackdmg = Attackdmg;
    	this.sticky = Sticky;
    	this.domesticated = Domesticated;
    }

    public int getDomestication()
    {
    	return this.domesticated;
    }
    
    public void setDomestication(int domesticate) 
    {
    	if(this.getBreedingGroup() != 0)
    	{
    	this.domesticated = domesticate;
    	}
    }
    
    public void setTimer(int newTime)
    {
    	this.dataManager.set(TIMER, Integer.valueOf(newTime));
    	this.interactTimer = newTime;
    }
    
    
    public int getTimer()
    {
    	return ((Integer)this.dataManager.get(TIMER)).intValue();
    }
    
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
    
    public int getHunger()
    {
    	return this.hunger;
    }
    
    public void setHunger(int newHunger)
    {
    	this.hunger = newHunger;
    }
    
	public void setCoreFluid(NBTTagCompound fluid) {
		this.dataManager.set(COREFLUID, fluid);
		
		
	}
	
	public void setMasterCoreFluid(NBTTagCompound fluid) {
		this.dataManager.set(MASTERCOREFLUID, fluid);
		
	}
   
}