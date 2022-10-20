package com.itchymichi.slimebreeder.items;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Sets;
import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class SlimeInfusedAxe extends ItemTool implements IHasModel
{
	private NBTTagCompound coreTool;
	
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] {Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE});
    /*private static final float[] ATTACK_DAMAGES = new float[] {6.0F, 8.0F, 8.0F, 8.0F, 6.0F};
    private static final float[] ATTACK_SPEEDS = new float[] { -3.2F, -3.2F, -3.1F, -3.0F, -3.0F};*/
	
	
	
	public SlimeInfusedAxe(String name, Boolean inCreativeTab, @Nullable CreativeTabs tab, float attackDamageIn, float attackSpeedIn, Item.ToolMaterial materialIn, Set<Block> effectiveBlocksIn, int size) 
	{
		super(materialIn, EFFECTIVE_ON);
		
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
			
			
			System.out.println("Core = " + this.getCore());
			
			//NBTTagList core = itemStackIn.getTagCompound().getTagList("core", 10);
			//core.set(0, new ItemStack(Items.DIAMOND_HELMET).serializeNBT());
			
			/*Item newcore = new ItemStack(Items.DIAMOND_HELMET).getItem();
			ItemStack newcoreStack = new ItemStack(Items.DIAMOND_HELMET);
			
			NBTTagCompound nbt = itemStackIn.getSubCompound(SlimeBreeder.MODID, false);
			
			NBTTagList coreitems = nbt.getTagList("core", 10);
			System.out.println("NBT = " + itemStackIn.getTagCompound().getKeySet());
			System.out.println("Items Before = " + coreitems.getCompoundTagAt(0));
			coreitems.set(0, newcoreStack.serializeNBT());
			System.out.println("Items After = " + coreitems.getCompoundTagAt(0));
			
			
			
			//String test = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(newcore, worldIn, playerIn).toString();
			String test = newcore.getRegistryName().getResourceDomain() + (String)":\\texture\\models\\armor\\"+((ItemArmor) newcore).getArmorMaterial().toString().toLowerCase()+"_layer_1.png";
			
			System.out.println(test);*/
			
			
			//itemStackIn.getTagCompound().getTagList("corelist", 10).set(0, new ItemStack(Items.DIAMOND_HELMET).serializeNBT());
			/*ItemStack core = new ItemStack(Items.DIAMOND_HELMET);
			core.addEnchantment(Enchantment.getEnchantmentByLocation("protection"), 2);
			coreArmor = core.serializeNBT();*/
	    //}
		//return new ActionResult(EnumActionResult.PASS, itemStackIn);
		
    //}
	
	
	
	/*@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		
		
			if(itemStack.getItemDamage() >= itemStack.getMaxDamage()/2)
			{
				System.out.println("Transform");
				itemStack.damageItem(itemStack.getMaxDamage(), player);
				
				player.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack.loadItemStackFromNBT(coreTool));
			}
			//System.out.println("Damage = " + itemStack.getItemDamage());

	}*/

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		
		if(stack.getItemDamage() >= stack.getMaxDamage()/2)
		{
			System.out.println("Transform");
			
			NBTTagCompound nbt = stack.getSubCompound(SlimeBreeder.MODID);
			
			
    		NBTTagList coreitems = nbt.getTagList("core", 10);
			
			entityIn.replaceItemInInventory(itemSlot, new ItemStack(coreitems.getCompoundTagAt(0)));
			
		}
		
		
		if(stack.getTagCompound() == null)
		{
			System.out.println("Creating Tag");
		stack.getSubCompound(SlimeBreeder.MODID);
		NBTTagCompound nbt = stack.getSubCompound(SlimeBreeder.MODID);
		
		
		NBTTagList corelist = new NBTTagList();
		
		ItemStack coreItemStack = new ItemStack(Items.GOLDEN_AXE, 1, 50);
		this.coreTool = coreItemStack.serializeNBT();
		
		corelist.appendTag(coreTool);
		corelist.set(0, coreTool);
		
		nbt.setTag("core", corelist);		
		
		}
		
		
		//stack.getSubCompound(SlimeBreeder.MODID, true);
		
		

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
			NBTTagCompound nbt = stack.getSubCompound(SlimeBreeder.MODID, false);
			
			NBTTagList coreitems = nbt.getTagList("core", 10);
			
			tooltip.add("Core Tool = " + coreitems.getCompoundTagAt(0));
		}
		else
		{
			tooltip.add("Hold LEFT SHIFT for more info");
		}
	 
    }*/
	
	/*@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		NBTTagCompound nbt = stack.getSubCompound(SlimeBreeder.MODID, false);
		NBTTagList coreitems = nbt.getTagList("core", 10);
		NBTTagCompound serialItem = coreitems.getCompoundTagAt(0);
		ItemStack appearance = ItemStack.loadItemStackFromNBT(serialItem);
		Item itemIn = appearance.getItem();
		
		ResourceLocation texture = new ResourceLocation(itemIn.getRegistryName().getResourceDomain(), "textures\\models\\armor\\"+((ItemArmor) itemIn).getArmorMaterial().toString().toLowerCase()+"_layer_1.png");
		//System.out.println(texture.getResourcePath());
		//return itemIn.getRegistryName().getResourceDomain() + (String)":textures\\models\\armor\\"+((ItemArmor) itemIn).getArmorMaterial().toString().toLowerCase()+"_layer_1.png";
		
		if(slot == EntityEquipmentSlot.HEAD)
        {
		return itemIn.getRegistryName().getResourceDomain() + ":textures/models/armor/" + ((ItemArmor) itemIn).getArmorMaterial().toString().toLowerCase() + "_layer_1.png";
		//return "minecraft:textures/models/armor/gold_layer_1.png";
        }else{
		return null;
		//return "textures\\models\\armor\\gold_layer_1.png";
        }
        }*/
	
	
	public void setCore(NBTTagCompound newCore, ItemStack itemStackIn)
	{
		NBTTagCompound nbt = itemStackIn.getSubCompound(SlimeBreeder.MODID);
		
		NBTTagList coreitems = nbt.getTagList("core", 10);
		System.out.println("NBT = " + itemStackIn.getTagCompound().getKeySet());
		System.out.println("Items Before = " + coreitems.getCompoundTagAt(0));
		coreitems.set(0, newCore);
		System.out.println("Items After = " + coreitems.getCompoundTagAt(0)); 
	}
	
	public NBTTagCompound getCore()
	{
		return coreTool; 
	}

	@Override
	public boolean getTop() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
