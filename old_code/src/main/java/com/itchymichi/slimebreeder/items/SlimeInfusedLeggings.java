package com.itchymichi.slimebreeder.items;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SlimeInfusedLeggings extends ItemArmor implements IHasModel
{
	private NBTTagCompound coreArmor;
	
	
	
	
	public SlimeInfusedLeggings(String name, Boolean inCreativeTab, @Nullable CreativeTabs tab, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, int size) 
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);
		
		setUnlocalizedName(SlimeBreeder.MODID + "_"+name);
		setRegistryName(name);
		SlimeBreederItems.ITEMS.add(this);
		
		if(inCreativeTab)
		{
			setCreativeTab(tab);
		}
		
		setMaxStackSize(size);
		// TODO Auto-generated constructor stub
		
		
	}
	
	@Override
	public void registerModels() {
		
		SlimeBreeder.proxy.registerItemRendererCustom(this, 0, "inventory");
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1; // return any value greater than zero
	}
	
	
	
	/*@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		
		
		if (playerIn.isSneaking())
	    {
			
			
			System.out.println("Enchantment Set");
			
			//NBTTagList core = itemStackIn.getTagCompound().getTagList("core", 10);
			//core.set(0, new ItemStack(Items.DIAMOND_HELMET).serializeNBT());
			
			Item newcore = new ItemStack(Items.DIAMOND_LEGGINGS).getItem();
			ItemStack newcoreStack = new ItemStack(Items.DIAMOND_LEGGINGS);
			
			NBTTagCompound nbt = itemStackIn.getOrCreateSubCompound(SlimeBreeder.MODID, false);
			
			NBTTagList coreitems = nbt.getTagList("core", 10);
			System.out.println("NBT = " + itemStackIn.getTagCompound().getKeySet());
			System.out.println("Items Before = " + coreitems.getCompoundTagAt(0));
			coreitems.set(0, newcoreStack.serializeNBT());
			System.out.println("Items After = " + coreitems.getCompoundTagAt(0));
			
			
			
			//String test = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(newcore, worldIn, playerIn).toString();
			String test = newcore.getRegistryName().getResourceDomain() + (String)":\\texture\\models\\armor\\"+((ItemArmor) newcore).getArmorMaterial().toString().toLowerCase()+"_layer_1.png";
			
			System.out.println(test);
			
			
			//itemStackIn.getTagCompound().getTagList("corelist", 10).set(0, new ItemStack(Items.DIAMOND_HELMET).serializeNBT());
			/*ItemStack core = new ItemStack(Items.DIAMOND_HELMET);
			core.addEnchantment(Enchantment.getEnchantmentByLocation("protection"), 2);
			coreArmor = core.serializeNBT();*/
	    //}
		//return new ActionResult(EnumActionResult.PASS, itemStackIn);
		
    //}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		
		
			if(itemStack.getItemDamage() >= itemStack.getMaxDamage()/2)
			{
				System.out.println("Transform");
				itemStack.damageItem(itemStack.getMaxDamage(), player);
				
				NBTTagCompound nbt = itemStack.getOrCreateSubCompound(SlimeBreeder.MODID);
				
				NBTTagList coreitems = nbt.getTagList("core", 10);
				
				player.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(coreitems.getCompoundTagAt(0)));
			}
			//System.out.println("Damage = " + itemStack.getItemDamage());

	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if(stack.getTagCompound() == null)
		{
			System.out.println("Creating Tag");
		stack.getOrCreateSubCompound(SlimeBreeder.MODID);
		NBTTagCompound nbt = stack.getOrCreateSubCompound(SlimeBreeder.MODID);
		
		
		NBTTagList corelist = new NBTTagList();
		
		ItemStack coreItemStack = new ItemStack(Items.GOLDEN_LEGGINGS, 1, 50);
		this.coreArmor = coreItemStack.serializeNBT();
		
		corelist.appendTag(coreArmor);
		corelist.set(0, coreArmor);
		
		nbt.setTag("core", corelist);		
		
		}
		
		//stack.getOrCreateSubCompound(SlimeBreeder.MODID, true);
		
		

		/*if(stack.getTagCompound() != null)
		{
			//System.out.println("Compound");
			NBTTagList coreItem = stack.getTagCompound().getTagList("core", 10);
			
	    	//this.coreArmor = coreItem.getCompoundTagAt(0);
	    	
	    	System.out.println("Value = " + coreItem.getCompoundTagAt(0).toString());
		}*/
	}
	
	/*@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		int Lshift = Keyboard.KEY_LSHIFT;
		
		if(Keyboard.isKeyDown(Lshift) && stack.hasTagCompound())
		{
			NBTTagCompound nbt = stack.getOrCreateSubCompound(SlimeBreeder.MODID, false);
			
			NBTTagList coreitems = nbt.getTagList("core", 10);
			
			tooltip.add("Core Armour = " + coreitems.getCompoundTagAt(0));
		}
		else
		{
			tooltip.add("Hold LEFT SHIFT for more info");
		}
	 
    }*/
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		NBTTagCompound nbt = stack.getOrCreateSubCompound(SlimeBreeder.MODID);
		NBTTagList coreitems = nbt.getTagList("core", 10);
		NBTTagCompound serialItem = coreitems.getCompoundTagAt(0);
		ItemStack appearance = new ItemStack(serialItem);
		Item itemIn = appearance.getItem();
		if(itemIn == Items.CHAINMAIL_LEGGINGS)
		{
			
		}
		ResourceLocation texture = new ResourceLocation(itemIn.getRegistryName().getResourceDomain(), "textures\\models\\armor\\"+((ItemArmor) itemIn).getArmorMaterial().toString().toLowerCase()+"_layer_1.png");
		//System.out.println(texture.getResourcePath());
		//return itemIn.getRegistryName().getResourceDomain() + (String)":textures\\models\\armor\\"+((ItemArmor) itemIn).getArmorMaterial().toString().toLowerCase()+"_layer_1.png";
		if(slot == EntityEquipmentSlot.LEGS)
        {
			if(itemIn == Items.CHAINMAIL_LEGGINGS)
			{
				return itemIn.getRegistryName().getResourceDomain() + ":textures/models/armor/" + ((ItemArmor) itemIn).getArmorMaterial().toString().toLowerCase()+"mail" + "_layer_2.png";
			}else{
		return itemIn.getRegistryName().getResourceDomain() + ":textures/models/armor/" + ((ItemArmor) itemIn).getArmorMaterial().toString().toLowerCase() + "_layer_2.png";
			}//return "minecraft:textures/models/armor/gold_layer_1.png";
        }else{
		return null;
		//return "textures\\models\\armor\\gold_layer_1.png";
        }
        }
	
	
	public void setCore(NBTTagCompound newCore)
	{
		this.coreArmor = newCore; 
	}
	
	public NBTTagCompound getCore()
	{
		return coreArmor; 
	}

	@Override
	public boolean getTop() {
		// TODO Auto-generated method stub
		return false;
	}


	
	
}
