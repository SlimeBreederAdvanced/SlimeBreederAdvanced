package com.itchymichi.slimebreeder.entity.monster;

import java.util.UUID;

import javax.annotation.Nullable;

import com.itchymichi.slimebreeder.entity.ai.AICustomSlimeAttack;
import com.itchymichi.slimebreeder.entity.ai.AICustomSlimeFaceRandom;
import com.itchymichi.slimebreeder.entity.ai.AICustomSlimeFloat;
import com.itchymichi.slimebreeder.entity.ai.AICustomSlimeHop;
import com.itchymichi.slimebreeder.entity.ai.CustomSlimeMoveHelper;
import com.itchymichi.slimebreeder.entity.ai.EntityColourSlime.EntityAIFindNearestBreeder;
import com.itchymichi.slimebreeder.entity.ai.EntityColourSlime.EntityAIFindNearestSlime;
import com.itchymichi.slimebreeder.entity.ai.EntityColourSlime.EntityAISlimeMate;
import com.itchymichi.slimebreeder.lists.EnumCraftingIngredients;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityCustomSlimeBase extends EntityAnimal implements IMob {

	////////////////////////////Base Variables///////////////////////////
	
	public float squishAmount;
	public float squishFactor;
	public float prevSquishFactor;
	protected boolean wasOnGround;
	protected int jumpDelay = 1;


    protected int breedingspeed;
    protected double movespeed;
    protected double health;
    protected int attackdmg;
    protected int sticky;

    protected UUID owner;
    protected UUID blankOwner;
	
	////////////////////////////Custom Code Start///////////////////////////

	public EntityCustomSlimeBase(World worldIn) {
		super(worldIn);
		this.moveHelper = new CustomSlimeMoveHelper(this);
		this.setSize(1F, 1F);
		// TODO Auto-generated constructor stub
	}
	
	/////////////////////////////Init Slime////////////////////////////////
	
	protected void initEntityAI()
    {
        this.tasks.addTask(1, new AICustomSlimeFloat(this));
        this.tasks.addTask(2, new AICustomSlimeAttack(this));
        this.tasks.addTask(3, new AICustomSlimeFaceRandom(this));
        this.tasks.addTask(5, new AICustomSlimeHop(this));
        
        //this.targetTasks.addTask(3, new EntityAIFindEntityNearest(this, EntityIronGolem.class));
        
        //Standard additions//
        /*this.tasks.addTask(2, new EntityAISlimeMate(this, 1.0D));
        this.targetTasks.addTask(3, new EntityAIFindNearestSlime(this, EntityCustomSlimeBase.class));
        this.targetTasks.addTask(1, new EntityAIFindNearestBreeder(this));*/

    }

	protected void entityInit()
    {
        super.entityInit();
    }
	
	@Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
    		/*this.breedingspeed = 22928;
    		this.movespeed = 5;
    		this.health = 24;
    		this.attackdmg = 6;
    		this.sticky = 3;*/
    
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.health);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1*this.movespeed);
    this.setHealth(this.getMaxHealth());

    return super.onInitialSpawn(difficulty, livingdata);
    }
	
	//////////////////////////////////Core Code/////////////////////////////
	
	public void onUpdate()
    {
		this.jumpDelay = (int) (this.jumpDelay * this.movespeed);
        doJump();
    }

    
    public void doJump(){
    	 this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
         this.prevSquishFactor = this.squishFactor;
         super.onUpdate();

         if (this.onGround && !this.wasOnGround)
         {
             int i = 2;
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
                 
                 /*int rgb2 = this.getRGB2();
                 float red = (float)((rgb2>>16)&0xFF)/255;
                 float green = (float)((rgb2>>8)&0xFF)/255;
                 float blue = (float)(rgb2&0xFF)/255;*/
                 
                 world.spawnParticle(getParticleType(), d0, this.getEntityBoundingBox().minY, d1, ((double)1.0D), ((double)1.0D), ((double)1.0D), new int[0]);

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
    }
    
    protected boolean spawnCustomParticles() { return false; }
    
    protected EnumParticleTypes getParticleType()
    {
        return EnumParticleTypes.SLIME;
    }
	
    protected void alterSquishAmount()
    {
        this.squishAmount *= 0.6F;
    }
    
	public void customSetSize(float width, float height)
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
	
	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void registerFixesSlime(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityCustomSlimeBase.class);
    }
	
    @Override
    public boolean canDespawn(){
		
    	if(this.getOwner() == blankOwner){
    		////System.out.println("True despawn owner");
    		return true;
    	}else{
    		////System.out.println("false despawn");
    		return false;
    	}
    	
    }
	
    public void setDead()
    {

    	this.isDead = true;
        super.setDead();
    }
   
    public float getEyeHeight()
    {
        return 0.625F * this.height;
    }

    protected SoundEvent getHurtSound()
    {
        return SoundEvents.ENTITY_SLIME_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_SLIME_DEATH;
    }

    protected SoundEvent getSquishSound()
    {
        return SoundEvents.ENTITY_SLIME_SQUISH;
    }
    
    
    
    //////////////////////////////Reading and Writing//////////////////////////
	
	public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
    	super.writeEntityToNBT(tagCompound);
    	
    	
    	
    	tagCompound.setBoolean("wasOnGround", this.wasOnGround);
    	
    	tagCompound.setInteger("BreedingSpeed", this.getBreedingSpeed());
    	tagCompound.setDouble("MoveSpeed", this.getMoveSpeed());
    	tagCompound.setDouble("Health", this.getSlimeHealth());
    	tagCompound.setInteger("AttackDmg", this.getAttackDmg());
    	tagCompound.setInteger("Sticky", this.getSticky());

    }



	/**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        super.readEntityFromNBT(tagCompound);
        
        this.wasOnGround = tagCompound.getBoolean("wasOnGround");

    	
    	this.breedingspeed = tagCompound.getInteger("BreedingSpeed");
    	this.movespeed = tagCompound.getDouble("MoveSpeed");
    	this.health = tagCompound.getDouble("Health");
    	this.attackdmg = tagCompound.getInteger("AttackDmg");
    	this.sticky = tagCompound.getInteger("Sticky");
    	
    	this.owner = tagCompound.getUniqueId("Owner");

    }
	
	///////////////////////////////Attacking Stuff/////////////////////////////
	
    public boolean canDamagePlayer()
    {
        return true;
    }
	
	///////////////////////////////Jumping Stuff//////////////////////////////
	
    public int getCustomJumpDelay()
    {
        return (this.rand.nextInt(20) + 10) * getJumpDelay();
    }
    
    public boolean makesSoundOnJumpCustomSlime()
    {
        return true;
    }
    
    protected void jump()
    {
        this.motionY = 0.41999998688697815D;
        this.isAirBorne = true;
        
    }
    /////////////////////////////Setters & Getters ///////////////////////////////////////
    
    public int getBreedingSpeed()
    {
    	return this.breedingspeed;
    }
    
    public void setBreedingSpeed(int breedspeed)
    {
    	this.breedingspeed = breedspeed;
    	
    	NBTTagCompound tagCompound = this.getEntityData();
    	tagCompound.setInteger("BreedingSpeed", breedspeed);
	
    }

    public double getMoveSpeed()
    {
    	return this.movespeed;
    }
    
    public void setMoveSpeed(double movespeed2)
    {
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1*movespeed2);
    	this.movespeed = movespeed2;
    	
    	NBTTagCompound tagCompound = this.getEntityData();
    	tagCompound.setDouble("MoveSpeed", movespeed2);

    }

    public double getSlimeHealth()
    {
    	return this.health;
    }
    
    public void setSlimeHealth(double health2)
    {

    	
    	NBTTagCompound tagCompound = this.getEntityData();
    	tagCompound.setDouble("Health", health2);
    	
    	System.out.println("Health = " + health2);

    }

    public int getAttackDmg()
    {
    	return this.attackdmg;
    }
    
    public void setSlimeAttack(int attack)
    {
    	this.attackdmg = attack;
    	
    	NBTTagCompound tagCompound = this.getEntityData();
    	tagCompound.setInteger("AttackDmg", this.getAttackDmg());

    }

    public int getSticky()
    {
    	return this.sticky;
    }
    
    public void setSticky(int sticky)
    {
    	this.sticky = sticky;
    	
    	NBTTagCompound tagCompound = this.getEntityData();
    	tagCompound.setInteger("Sticky", this.getSticky());
    }
    
    public UUID getOwner()
    {
    	return this.owner;
    }
    
    public void setOwner(UUID owner)
    {
    	this.owner = owner;
    }

    public int getJumpDelay() {
    	return this.jumpDelay;
    }
    
    public void setJumpDelay(int delay)
    {
    	this.jumpDelay = delay;
    }
	
	
}
