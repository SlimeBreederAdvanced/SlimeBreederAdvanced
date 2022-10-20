package com.itchymichi.slimebreeder;

import com.itchymichi.slimebreeder.items.FilledSlimeCapsule;
import com.itchymichi.slimebreeder.items.GoldSlimeChunk;
import com.itchymichi.slimebreeder.items.GreenSlimeCrystal;
import com.itchymichi.slimebreeder.items.IronSlimeChunk;
import com.itchymichi.slimebreeder.items.PrecursorResinMix;
import com.itchymichi.slimebreeder.items.RainbowSlimeCrystal;
import com.itchymichi.slimebreeder.items.RedSlimeCrystal;

import java.util.ArrayList;
import java.util.List;

import com.itchymichi.slimebreeder.enchantments.BaseEnchantment;
import com.itchymichi.slimebreeder.enchantments.EnchantmentAcidic;
import com.itchymichi.slimebreeder.enchantments.EnchantmentAdhesive;
import com.itchymichi.slimebreeder.enchantments.EnchantmentBouncy;
import com.itchymichi.slimebreeder.enchantments.EnchantmentBreakable;
import com.itchymichi.slimebreeder.enchantments.EnchantmentFlammable;
import com.itchymichi.slimebreeder.enchantments.EnchantmentSticky;
import com.itchymichi.slimebreeder.items.BlueSlimeCrystal;
import com.itchymichi.slimebreeder.items.BreedingCatalystIron;
import com.itchymichi.slimebreeder.items.ClearSlimeCrystal;
import com.itchymichi.slimebreeder.items.DiamondSlimeChunk;
import com.itchymichi.slimebreeder.items.Slimalyser;
import com.itchymichi.slimebreeder.items.SlimeCapsule;
import com.itchymichi.slimebreeder.items.SlimeInfusedAxe;
import com.itchymichi.slimebreeder.items.SlimeInfusedBoots;
import com.itchymichi.slimebreeder.items.SlimeInfusedChest;
import com.itchymichi.slimebreeder.items.SlimeInfusedHelm;
import com.itchymichi.slimebreeder.items.SlimeInfusedLeggings;
import com.itchymichi.slimebreeder.items.SlimeInfusedPickaxe;
import com.itchymichi.slimebreeder.items.SlimeInfusedSpade;
import com.itchymichi.slimebreeder.items.SlimeInfusedSword;
import com.itchymichi.slimebreeder.items.SlimeResin;
import com.itchymichi.slimebreeder.items.Slimepedia;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class SlimeBreederEnchantments 
{
	public static final List<Enchantment> ENCHANTMENT = new ArrayList<Enchantment>();
	
	static EntityEquipmentSlot[] armor = new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
	static EntityEquipmentSlot[] tools = new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND};
	
	public static final Enchantment ACIDIC = new BaseEnchantment("acidic", Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR, armor);
	public static final Enchantment ADHESIVE = new BaseEnchantment("adhesive", Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR, armor);
	public static final Enchantment BOUNCY = new BaseEnchantment("bouncy", Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR, armor);
	public static final Enchantment BREAKABLE = new BaseEnchantment("breakable", Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR, armor);
	public static final Enchantment FLAMMABLE = new BaseEnchantment("flammable", Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR, armor);
	public static final Enchantment STICKY = new BaseEnchantment("sticky", Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR, armor);

}
