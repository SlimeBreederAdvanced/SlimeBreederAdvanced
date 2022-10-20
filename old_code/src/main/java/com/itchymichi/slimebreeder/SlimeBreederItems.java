package com.itchymichi.slimebreeder;

import com.itchymichi.slimebreeder.items.FilledSlimeCapsule;
import com.itchymichi.slimebreeder.items.GoldSlimeChunk;
import com.itchymichi.slimebreeder.items.GreenSlimeCrystal;
import com.itchymichi.slimebreeder.items.IronSlimeChunk;
import com.itchymichi.slimebreeder.items.ItemBase;
import com.itchymichi.slimebreeder.items.ItemBaseArmour;
import com.itchymichi.slimebreeder.items.ItemBasePickaxe;
import com.itchymichi.slimebreeder.items.ItemBaseSpade;
import com.itchymichi.slimebreeder.items.ItemBaseSword;
import com.itchymichi.slimebreeder.items.ItemBaseTool;
import com.itchymichi.slimebreeder.items.PrecursorResinMix;
import com.itchymichi.slimebreeder.items.RainbowSlimeCrystal;
import com.itchymichi.slimebreeder.items.RedSlimeCrystal;

import java.util.ArrayList;
import java.util.List;

import com.itchymichi.slimebreeder.items.BlueSlimeCrystal;
import com.itchymichi.slimebreeder.items.BreedingCatalystIron;
import com.itchymichi.slimebreeder.items.ClearSlimeCrystal;
import com.itchymichi.slimebreeder.items.CraftingNote;
import com.itchymichi.slimebreeder.items.DiamondSlimeChunk;
import com.itchymichi.slimebreeder.items.Slimalyser;
import com.itchymichi.slimebreeder.items.SlimeCapsule;
import com.itchymichi.slimebreeder.items.SlimeCore;
import com.itchymichi.slimebreeder.items.SlimeInfusedAxe;
import com.itchymichi.slimebreeder.items.SlimeInfusedBoots;
import com.itchymichi.slimebreeder.items.SlimeInfusedChest;
import com.itchymichi.slimebreeder.items.SlimeInfusedHelm;
import com.itchymichi.slimebreeder.items.SlimeInfusedLeggings;
import com.itchymichi.slimebreeder.items.SlimeInfusedPickaxe;
import com.itchymichi.slimebreeder.items.SlimeInfusedSpade;
import com.itchymichi.slimebreeder.items.SlimeInfusedSword;
import com.itchymichi.slimebreeder.items.SlimeResin;
import com.itchymichi.slimebreeder.items.SlimeSeed;
import com.itchymichi.slimebreeder.items.Slimepedia;
import com.itchymichi.slimebreeder.items.Slimetuner;
import com.itchymichi.slimebreeder.items.UnchargedClearSlimeCrystal;
import com.itchymichi.slimebreeder.items.UnknownItem;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class SlimeBreederItems 
{
	
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item FILLEDSLIMECAPSULE = new FilledSlimeCapsule("filledslimecapsule", true, SlimeBreeder.proxy.slimetab, 1);
	public static final Item SLIMALYSER = new Slimalyser("slimalyser", true, SlimeBreeder.proxy.slimetab, 1);
	public static final Item SLIMETUNER = new Slimetuner("slimetuner", true, SlimeBreeder.proxy.slimetab, 1);
	public static final Item SLIMECAPSULE = new ItemBase("slimecapsule", true, SlimeBreeder.proxy.slimetab, 64);
	public static final Item BREEDINGCATALYSTIRON = new ItemBase("breedingcatalystiron", true, SlimeBreeder.proxy.slimetab, 64);
	public static final Item SLIMEPEDIA = new Slimepedia("slimepedia", true, SlimeBreeder.proxy.slimetab, 1);
	//public static final Item SLIMECORE = new SlimeCore("slimecore", true, SlimeBreeder.proxy.slimetab, 1);
	public static final Item COMPRESSEDSLIMEBALL = new ItemBase("compressed_slimeball", true, SlimeBreeder.proxy.slimetab, 64);
	
	public static final Item IRONSLIMECHUNK = new ItemBase("ironslimechunk", true, SlimeBreeder.proxy.slimetab, 64);
	public static final Item GOLDSLIMECHUNK = new ItemBase("goldslimechunk", true, SlimeBreeder.proxy.slimetab, 64);
	public static final Item DIAMONDSLIMECHUNK = new ItemBase("diamondslimechunk", true, SlimeBreeder.proxy.slimetab, 64);
	
	public static final Item SLIMESEED = new SlimeSeed("slimeseed", true, SlimeBreeder.proxy.slimetab, 1);
	public static final Item GREENSLIMECRYSTAL = new ItemBase("greenslimecrystal", true, SlimeBreeder.proxy.slimetab, 64);
	public static final Item REDSLIMECRYSTAL = new ItemBase("redslimecrystal", true, SlimeBreeder.proxy.slimetab, 64);
	public static final Item BLUESLIMECRYSTAL = new ItemBase("blueslimecrystal", true, SlimeBreeder.proxy.slimetab, 64);
	public static final Item RAINBOWSLIMECRYSTAL = new ItemBase("rainbowslimecrystal", true, SlimeBreeder.proxy.slimetab, 64);
	public static final Item CLEARSLIMECRYSTAL = new ClearSlimeCrystal("clearslimecrystal", true, SlimeBreeder.proxy.slimetab, 1);
	public static final Item UNCHARGEDCLEARSLIMECRYSTAL = new UnchargedClearSlimeCrystal("unchargedclearslimecrystal", true, SlimeBreeder.proxy.slimetab, 1);
	public static final Item CRAFTINGNOTE = new CraftingNote("craftingnote", true, SlimeBreeder.proxy.slimetab, 1);

	
	public static final Item PRECURSORRESINMIX = new ItemBase("precursorresinmix", false, SlimeBreeder.proxy.slimetab, 64);
	public static final Item SLIMERESIN = new ItemBase("slimeresin", false, SlimeBreeder.proxy.slimetab, 64);
	public static final Item UNKNOWNITEM = new ItemBase("unknownitem", true, SlimeBreeder.proxy.slimetab, 64);
	
	public static final Item SLIMEINFUSEDHELM = new SlimeInfusedHelm("slimeinfusedhelm", true, null, SlimeBreederArmors.SLIMEINFUSED, 1, EntityEquipmentSlot.HEAD, 1);
	public static final Item SLIMEINFUSEDCHEST = new SlimeInfusedChest("slimeinfusedchest", true, null, SlimeBreederArmors.SLIMEINFUSED, 1, EntityEquipmentSlot.CHEST, 1);
	public static final Item SLIMEINFUSEDLEGGINGS = new SlimeInfusedLeggings("slimeinfusedleggings", true, null, SlimeBreederArmors.SLIMEINFUSED, 2, EntityEquipmentSlot.LEGS, 1);
	public static final Item SLIMEINFUSEDBOOTS = new SlimeInfusedBoots("slimeinfusedboots", true, null, SlimeBreederArmors.SLIMEINFUSED, 1, EntityEquipmentSlot.FEET, 1);
	
	public static final Item SLIMEINFUSEDSWORD = new SlimeInfusedSword("slimeinfusedsword", true, null, SlimeBreederTools.SLIMEINFUSED, 1);
	public static final Item SLIMEINFUSEDPICKAXE = new SlimeInfusedPickaxe("slimeinfusedpickaxe", true, null, SlimeBreederTools.SLIMEINFUSED, 1);
	public static final Item SLIMEINFUSEDSPADE = new SlimeInfusedSpade("slimeinfusedspade", true, null, SlimeBreederTools.SLIMEINFUSED, 1);
	public static final Item SLIMEINFUSEDAXE = new SlimeInfusedAxe("slimeinfusedaxe", true, null, 1, 1, SlimeBreederTools.SLIMEINFUSED, null, 1);
	
	
}
