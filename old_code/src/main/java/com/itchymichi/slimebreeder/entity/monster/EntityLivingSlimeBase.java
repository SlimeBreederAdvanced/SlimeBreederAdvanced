package com.itchymichi.slimebreeder.entity.monster;

import static com.itchymichi.slimebreeder.entity.monster.EntityLivingSlimeBase.TIMER;

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


public class EntityLivingSlimeBase extends EntityCustomSlimeBase
{
	int counter;
	protected int wave;
	protected float sat;
	protected float brightness;
	protected float Hue;
	protected int breedinggroup;
	protected int domesticated;
	protected int interactTimer;
	protected int varient;
	protected int hunger;
	protected int breedingspeed;

	/////////////////////////////////////////////Data Parameters///////////////////////////////////
	
	protected static final DataParameter<Integer> TIMER = EntityDataManager.<Integer>createKey(EntityLivingSlimeBase.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> VARIENT = EntityDataManager.<Integer>createKey(EntityLivingSlimeBase.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> HUNGER = EntityDataManager.<Integer>createKey(EntityLivingSlimeBase.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> DOMESTICATION = EntityDataManager.<Integer>createKey(EntityLivingSlimeBase.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> BREEDINGSPEED = EntityDataManager.<Integer>createKey(EntityLivingSlimeBase.class, DataSerializers.VARINT);
	
	public EntityLivingSlimeBase(World worldIn) {
		super(worldIn);
        this.moveHelper = new CustomSlimeMoveHelper(this);
		
	}
	
	protected void entityInit()
    {
    	
        super.entityInit();
        
        
        this.dataManager.register(VARIENT, Integer.valueOf(varient));
        this.dataManager.register(this.TIMER, Integer.valueOf(25));
        this.dataManager.register(HUNGER, Integer.valueOf(hunger));
        this.dataManager.register(DOMESTICATION, Integer.valueOf(domesticated));
        this.dataManager.register(BREEDINGSPEED, Integer.valueOf(breedingspeed));
    }
	
	@Override
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
    	super.writeEntityToNBT(tagCompound);
    	
    	tagCompound.setInteger("BreedingGroup", this.getBreedingGroup());
    	tagCompound.setInteger("Wave", this.getWave());
    	tagCompound.setFloat("Sats", this.getSat());
    	tagCompound.setFloat("Bright", this.getBrightness());
    	tagCompound.setInteger("Varient", this.getVarient());
    	this.dataManager.set(VARIENT, tagCompound.getInteger("Varient"));
    	tagCompound.setInteger("Hunger", this.getHunger());
    	this.dataManager.set(HUNGER, tagCompound.getInteger("Hunger"));
    	tagCompound.setInteger("Timer", this.getTimer());
    	
    	tagCompound.setInteger("BreedingGroup", this.getBreedingGroup());
       	tagCompound.setInteger("BreedingSpeed", this.getBreedingSpeed());
    	this.dataManager.set(BREEDINGSPEED, tagCompound.getInteger("BreedingSpeed"));
       	tagCompound.setDouble("MoveSpeed", this.getMoveSpeed());
       	tagCompound.setDouble("Health", this.getSlimeHealth());
       	tagCompound.setInteger("AttackDmg", this.getAttackDmg());
       	tagCompound.setInteger("Sticky", this.getSticky());
       	tagCompound.setInteger("Domesticated", this.getDomestication());
       	this.dataManager.set(DOMESTICATION, tagCompound.getInteger("Domesticated"));

    }



	/**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        super.readEntityFromNBT(tagCompound);

        this.wasOnGround = tagCompound.getBoolean("wasOnGround");
        this.setWave(tagCompound.getInteger("Wave"));
        this.setSat(tagCompound.getFloat("Sats"));
        this.setBrightness(tagCompound.getFloat("Bright"));
    	this.setGenetics(tagCompound.getInteger("BreedingGroup"), tagCompound.getInteger("BreedingSpeed"), tagCompound.getDouble("MoveSpeed"), tagCompound.getDouble("Health"), tagCompound.getInteger("AttackDmg"), tagCompound.getInteger("Sticky"), tagCompound.getInteger("Domesticated") );

    	this.dataManager.set(DOMESTICATION, tagCompound.getInteger("Domesticated"));
    	this.dataManager.set(BREEDINGSPEED, tagCompound.getInteger("BreedingSpeed"));

    	
    	this.setVarient(tagCompound.getInteger("Varient"));
    	this.dataManager.set(VARIENT, tagCompound.getInteger("Varient"));
    	
    	this.setTimer(tagCompound.getInteger("Timer"));
    	this.dataManager.set(TIMER, tagCompound.getInteger("Timer"));
    	
    	this.setHunger(tagCompound.getInteger("Hunger"));
    	this.dataManager.set(HUNGER, tagCompound.getInteger("hunger"));
    	
    	this.setOwner(tagCompound.getUniqueId("Owner"));
    }
	
	
	 /////////////////////////////Setters & Getters ///////////////////////////////////////
	public int getWave()
    {
    	return this.wave;
    }
	
	public void setWave(int wave)
    {
		this.wave = wave;
    }
	
	public float getSat()
    {
    	return this.sat;
    }
	
	public void setSat(float sat)
    {
		this.sat = sat;
    }
	
	public float getBrightness()
    {
    	return this.brightness;
    }
	
	public void setBrightness(float brightness)
    {
		this.brightness = brightness;
    }
	
	public int getHue(int red, int green, int blue) {

        float min = Math.min(Math.min(red, green), blue);
        float max = Math.max(Math.max(red, green), blue);

        float hue = 0f;
        if (max == red) {
            hue = (green - blue) / (max - min);

        } else if (max == green) {
            hue = 2f + (blue - red) / (max - min);

        } else {
            hue = 4f + (red - green) / (max - min);
        }

        hue = hue * 60;
        if (hue < 0) hue = hue + 360;
        ////System.out.println("Set hue = " + Math.round(hue));

        return Math.round(hue);
    }

    public void setHue(int h)
    {
    	this.Hue = h;
    	
    }
    
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
    	
    	this.dataManager.set(BREEDINGSPEED, Breedingspeed);
    	
    	this.setBreedingSpeed(Breedingspeed);
		this.setMoveSpeed(Movespeed);
		this.setSlimeHealth(Health);
		this.setSlimeAttack(Attackdmg);
		this.setSticky(Sticky);
    	
    }

    public int getDomestication()
    {
    	return this.domesticated;
    }
    
    public int getServerDomestication()
    {
    	return ((Integer)this.dataManager.get(DOMESTICATION)).intValue();
    }
    
    public int getServerBreedingSpeed()
    {
    	return ((Integer)this.dataManager.get(BREEDINGSPEED)).intValue();
    }
    
    public void setDomestication(int domesticate) 
    {
    	if(this.getBreedingGroup() != 0)
    	{
    	this.domesticated = domesticate;
    	this.dataManager.set(DOMESTICATION, Integer.valueOf(domesticate));
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
    
    public int getServerHunger()
    {
    	return ((Integer)this.dataManager.get(HUNGER)).intValue();
    }
    
    public void setHunger(int newHunger)
    {
    	this.hunger = newHunger;
       	this.dataManager.set(HUNGER, Integer.valueOf(newHunger));
    }
    
   
}