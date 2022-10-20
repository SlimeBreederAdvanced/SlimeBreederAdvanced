package com.itchymichi.slimebreeder.items;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.SlimeBreederTab;
import com.itchymichi.slimebreeder.utility.CommonProxy;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FilledSlimeCapsule extends Item implements IHasModel
{

	int DropW;
	float DropS;
	float DropB;
	private int DropBreedingSpeed;
	private double DropMoveSpeed;
	private double DropHealth;
	private float DropAttackDamage;
	private float DropSticky;
	
	
	public FilledSlimeCapsule(String name, Boolean inCreativeTab, @Nullable CreativeTabs tab, int size)
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
	
	public void setDropW(int dw)
	{
		this.DropW = dw;
		//System.out.println(dw);
		//stack.getTagCompound().setInteger("Wave", dw);
		
		
	}
	
	public void setDropB(float db)
	{
		this.DropB = db;
	}
	
	public void setDropS(float ds)
	{
		this.DropS = ds;
	}
	
	public void setDropBreedingSpeed(int dw)
	{
		this.DropBreedingSpeed = dw;
		//System.out.println(dw);
		//stack.getTagCompound().setInteger("Wave", dw);
		
		
	}
	
	public void setDropMoveSpeed(double db)
	{
		this.DropMoveSpeed = db;
	}
	
	public void setDropHealth(double ds)
	{
		this.DropHealth = ds;
	}
	
	public void setDropAttackDamage(float db)
	{
		this.DropAttackDamage = db;
	}
	
	public void setDropSticky(float ds)
	{
		this.DropSticky = ds;
	}
	
	public String getFlavor(int BS, double dropMoveSpeed2, double dropHealth2, float AD, float STY)
	{
		String A;
		String B;
		String C;
		String D;
		String E;
		
		String level1 = "requirement.general.level1";
		String level2 = "requirement.general.level2";
		String level3 = "requirement.general.level3";
		String level4 = "requirement.general.level4";
		
		ITextComponent Translevel1 = new TextComponentTranslation(level1);
		ITextComponent Translevel2 = new TextComponentTranslation(level2);
		ITextComponent Translevel3 = new TextComponentTranslation(level3);
		ITextComponent Translevel4 = new TextComponentTranslation(level4);
		
		
		ITextComponent flavor1 = new TextComponentTranslation("guiSlimepedia.book.flavor1");
		ITextComponent flavor2 = new TextComponentTranslation("guiSlimepedia.book.flavor2");
		ITextComponent flavor3 = new TextComponentTranslation("guiSlimepedia.book.flavor3");
		ITextComponent flavor4 = new TextComponentTranslation("guiSlimepedia.book.flavor4");
		ITextComponent flavor5 = new TextComponentTranslation("guiSlimepedia.book.flavor5");
		ITextComponent flavor6 = new TextComponentTranslation("guiSlimepedia.book.flavor6");
		
		
		
        if (BS > 45856 && BS <= 91712) 
        {
            D = TextFormatting.WHITE+ Translevel1.getFormattedText();
        } else if (BS > 22928 && BS <= 45856) {
        	 D = TextFormatting.AQUA+ Translevel2.getFormattedText();
        } else if (BS > 11464 && BS <= 22928) {
        	 D = TextFormatting.BLUE+ Translevel3.getFormattedText();
        } else if (BS >= 5732 && BS <= 11464) {
        	 D = TextFormatting.DARK_PURPLE+ Translevel4.getFormattedText();
        } else {
        	 D = " ";
        }
        
        if (dropMoveSpeed2 >= 4 && dropMoveSpeed2 < 5) 
        {
            E = TextFormatting.WHITE+ Translevel1.getFormattedText();
        } else if (dropMoveSpeed2 >= 5 && dropMoveSpeed2 < 6) {
        	 E = TextFormatting.AQUA+ Translevel2.getFormattedText();
        } else if (dropMoveSpeed2 >= 6 && dropMoveSpeed2 < 7) {
        	 E = TextFormatting.BLUE+ Translevel3.getFormattedText();
        } else if (dropMoveSpeed2 >= 7 && dropMoveSpeed2 <= 8) {
        	 E = TextFormatting.DARK_PURPLE+ Translevel4.getFormattedText();
        } else {
        	 E = " ";
        }
        
        if (dropHealth2 >= 4 && dropHealth2 < 6) 
        {
            C = TextFormatting.WHITE+ Translevel1.getFormattedText();
        } else if (dropHealth2 >= 6 && dropHealth2 < 8) {
        	 C = TextFormatting.AQUA+ Translevel2.getFormattedText();
        } else if (dropHealth2 >= 8 && dropHealth2 < 12) {
        	 C = TextFormatting.BLUE+ Translevel3.getFormattedText();
        } else if (dropHealth2 >= 12 && dropHealth2 <= 16) {
        	 C = TextFormatting.DARK_PURPLE+ Translevel4.getFormattedText();
        } else {
        	 C = " ";
        }
        
        if (AD >= 2 && AD < 4) 
        {
            B = TextFormatting.WHITE + Translevel1.getFormattedText();
        } else if (AD >= 4 && AD < 6) {
        	 B = TextFormatting.AQUA + Translevel2.getFormattedText();
        } else if (AD >= 6 && AD < 8) {
        	 B = TextFormatting.BLUE + Translevel3.getFormattedText();
        } else if (AD >= 8 && AD <= 10) {
        	 B = TextFormatting.DARK_PURPLE+ Translevel4.getFormattedText();
        } else {
        	 B = " ";
        }
		
        if (STY >= 0 && STY < 1) 
        {
            A = TextFormatting.WHITE + Translevel1.getFormattedText();
        } else if (STY >= 1 && STY < 2) {
        	 A = TextFormatting.AQUA + Translevel2.getFormattedText();
        } else if (STY >= 2 && STY < 3) {
        	 A = TextFormatting.BLUE + Translevel3.getFormattedText();
        } else if (STY >= 3 && STY <= 4) {
        	 A = TextFormatting.DARK_PURPLE+ Translevel4.getFormattedText();
        } else {
        	 A = " ";
        }
		
        String flavour;
		

		flavour = TextFormatting.GRAY + flavor1.getFormattedText()+ E + TextFormatting.GRAY + " " + flavor2.getFormattedText() +" "+ C + TextFormatting.GRAY +" " + flavor3.getFormattedText()+ B + TextFormatting.GRAY +" " + flavor4.getFormattedText() + A + TextFormatting.GRAY +" " + flavor5.getFormattedText() + D +TextFormatting.GRAY +" " + flavor6.getFormattedText();
        
		//System.out.println(flavour);
		
		return flavour;
	}
	
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		
		//stack.getOrCreateSubCompound(SlimeBreeder.MODID);
		//System.out.println("Tag ? " + stack.hasTagCompound());
		
		

		if(stack.getTagCompound() != null && stack.getTagCompound().getInteger("Wave") == 0)
		{
		       stack.getTagCompound().setInteger("Wave", DropW);
		       stack.getTagCompound().setFloat("Sat", DropS);
		       stack.getTagCompound().setFloat("Bright", DropB);
		       
		       stack.getTagCompound().setInteger("BreedingSpeed", DropBreedingSpeed);
		       stack.getTagCompound().setDouble("MoveSpeed", DropMoveSpeed);
		       stack.getTagCompound().setDouble("Health", DropHealth);
		       stack.getTagCompound().setFloat("AttackDamage", DropAttackDamage);
		       stack.getTagCompound().setFloat("Sticky", DropSticky);
		}
		
	}
	
	
	
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		int Lshift = Keyboard.KEY_LSHIFT;
		//System.out.println("Running");
		//System.out.println("Tag ? " + stack.hasTagCompound());
		ITextComponent tooltiptext = new TextComponentTranslation("tooltip.general.lshift");
		
		if(Keyboard.isKeyDown(Lshift) && stack.hasTagCompound())
		{
			tooltip.add(getFlavor(stack.getTagCompound().getInteger("BreedingSpeed"), stack.getTagCompound().getDouble("MoveSpeed"), stack.getTagCompound().getDouble("Health"), stack.getTagCompound().getFloat("AttackDamage"), stack.getTagCompound().getFloat("Sticky")));
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
