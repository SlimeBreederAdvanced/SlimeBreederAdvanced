package com.itchymichi.slimebreeder.items;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.SlimeBreederTab;
import com.itchymichi.slimebreeder.lists.EnumCraftingIngredients;
import com.itchymichi.slimebreeder.lists.EnumEntityTune;
import com.itchymichi.slimebreeder.lists.EnumTune;
import com.itchymichi.slimebreeder.utility.CommonProxy;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundList;
import net.minecraft.client.audio.SoundListSerializer;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.client.event.sound.SoundEvent.SoundSourceEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Slimetuner extends Item implements IHasModel
{

	public int Tune;
	
	public Slimetuner(String name, Boolean inCreativeTab, @Nullable CreativeTabs tab, int size)
	{
		super();
		
		
		setUnlocalizedName(SlimeBreeder.MODID + "_"+name);
		setRegistryName(name);
		SlimeBreederItems.ITEMS.add(this);
		
		if(inCreativeTab)
		{
			setCreativeTab(tab);
		}
		
		setMaxStackSize(size);
			
	}
	
	@Override
	public void registerModels() 
	{
		
		SlimeBreeder.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
	
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1; // return any value greater than zero
	}
	
	
	
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if(stack.getTagCompound() != null && stack.getTagCompound().getInteger("tune") == 0)
		{
			if(Tune != 0) {
		       stack.getTagCompound().setInteger("tune", Tune);
			}
		}
		
		if(stack.getTagCompound() != null && stack.getTagCompound().getInteger("tune") != 0 && isSelected)
		{
			if(Tune != 0) {
		       stack.getTagCompound().setInteger("tune", Tune);
			}
		}
	}
	
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		ItemStack stack = player.inventory.getCurrentItem();
		System.out.println(stack);
		
		if(stack.getTagCompound() == null)
		{
			Tune = 0;
			System.out.println("Creating Tag");
		NBTTagCompound nbt = new NBTTagCompound();
		stack.setTagCompound(nbt);
		nbt.setInteger("tune", Tune);
		
		}
		

		
		
		
		if(!(stack.isEmpty()))
    	{
    	if(stack.getItem() == SlimeBreederItems.SLIMETUNER)
	    	{
	    		if(stack.getTagCompound() != null){
	    			NBTTagCompound nbt = stack.getTagCompound();
	    			System.out.println("tune = " + nbt.getInteger("tune"));
	    			if(nbt.getInteger("tune") != 0) {
	    				player.swingArm(hand);
	    				player.playSound(getTune(nbt.getInteger("tune")), 1.0F, 1.0F);
	    				System.out.println("custom tune");
	    			}else {
	    				player.swingArm(hand);
	    				player.playSound(SoundEvents.BLOCK_NOTE_XYLOPHONE, 1.0F, 2.0F);
	    				System.out.println("defult tune");
	    			}
	    			
	    		
		    	}
	    	}
		
    	}
		
		
		
        return EnumActionResult.PASS;
    }
	
	@Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
    {
		if(stack.getTagCompound() == null)
		{
			Tune = 0;
			System.out.println("Creating Tag");
		NBTTagCompound nbt = new NBTTagCompound();
		stack.setTagCompound(nbt);
		nbt.setInteger("tune", Tune);

		}
		
		
		System.out.println(stack);
		playerIn.swingArm(hand);
		
		
		if(!(stack.isEmpty()))
    	{
    	if(stack.getItem() == SlimeBreederItems.SLIMETUNER)
	    	{
	    		if(stack.getTagCompound() != null){
	    			
	    			ItemStack newFork = new ItemStack(SlimeBreederItems.SLIMETUNER);
	    			
	    			
	    			
	    			NBTTagCompound newNbt = new NBTTagCompound();
	    			
	    			String Name = target.getCustomNameTag();
	    			target.setCustomNameTag("");
	    			
	    			if(getTuneID(target.getName()) != 0) {
	    				System.out.println("id = " + getTuneID(target.getName()));
	    				newNbt.setInteger("tune", getTuneID(target.getName()));
	    				newFork.setTagCompound(newNbt);
	    				newFork.setStackDisplayName(target.getName() + "-Tuned Fork");
	    				//Tune = getTuneID(target.getName());
	    				playerIn.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 0.5F);
	    				//System.out.println("tune = " + Tune);
	    				
	    				playerIn.setHeldItem(hand, newFork);
	    				
	    			}
	    			
	    			target.setCustomNameTag(Name);

	    		}
	    		
	    	}
    	}
	
		
        return true;
    }

	private net.minecraft.util.SoundEvent getTune(int integer) {
		
		EnumEntityTune.listOfObjects.clear();
    	EnumEntityTune.initTuneList();
    	
		 int length = EnumEntityTune.listOfObjects.size();
		 
		 for (int i = 0; i < length; i++) 
	    	{
			 Integer tuneID = EnumEntityTune.listOfObjects.get(i).getTune();
			 
			 if(tuneID == integer) {
				 
				 return EnumEntityTune.listOfObjects.get(i).getEntitySound();
			 			}
	    	}

		return null;
	}
	
	private int getTuneID(String name) {
		
		EnumTune.listOfObjects.clear();
    	EnumTune.initTuneList();
    	
		 int length = EnumTune.listOfObjects.size();
		 
		 for (int i = 0; i < length; i++) 
	    	{
			 //String entityName = ((EntityList.createEntityFromNBT((EnumTune.listOfObjects.get(i).getEntityEntry().newInstance(Minecraft.getMinecraft().world).serializeNBT()), Minecraft.getMinecraft().world))).getName();
			 String entityName = (EnumTune.listOfObjects.get(i).getEntityName());

			 
			 if(entityName.equals(name)) {
				 
				 return EnumTune.listOfObjects.get(i).getTune();
			 			}
	    	}

		return 0;
	}
	
	public String getTuneName(int id) {
		
		EnumTune.listOfObjects.clear();
    	EnumTune.initTuneList();
    	
		 int length = EnumTune.listOfObjects.size();
		 
		 for (int i = 0; i < length; i++) 
	    	{
			 Integer tuneid = EnumTune.listOfObjects.get(i).getTune();
			 
			 if(tuneid == id) {
				 
				 return (EnumTune.listOfObjects.get(i).getEntityName());
			 			}
	    	}

		return "null";
	}
	
	public void setTune(int tune) {
		this.Tune = tune;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		int Lshift = Keyboard.KEY_LSHIFT;
		//System.out.println("Running");
		//System.out.println("Tag ? " + stack.hasTagCompound());
		ITextComponent tooltiptext = new TextComponentTranslation("tooltip.general.lshift");
		
		if(stack.hasTagCompound())
		{
			//tooltip.add(getTuneName(stack.getTagCompound().getInteger("Tune")) + "Tuned Fork");
			//System.out.println("Health = " + stack.getTagCompound().getDouble("Health"));
		}
		else
		{

				 tooltip.add(tooltiptext.getFormattedText());

		}
	 
    }

	@Override
	public boolean getTop() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
	
