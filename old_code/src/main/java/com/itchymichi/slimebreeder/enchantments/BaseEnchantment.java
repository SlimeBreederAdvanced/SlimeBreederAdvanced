package com.itchymichi.slimebreeder.enchantments;

import com.itchymichi.slimebreeder.SlimeBreederEnchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class BaseEnchantment extends Enchantment
{

	public BaseEnchantment(String name, Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots) {
		super(rarityIn, typeIn, slots);
		
		setRegistryName(name);
		setName(name);
		
		SlimeBreederEnchantments.ENCHANTMENT.add(this);
		
		// TODO Auto-generated constructor stub
	}

}
