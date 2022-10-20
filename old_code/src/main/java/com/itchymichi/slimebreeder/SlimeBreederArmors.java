package com.itchymichi.slimebreeder;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class SlimeBreederArmors 
{

	public static final ArmorMaterial SLIMEINFUSED;
	
	static
	{
		SLIMEINFUSED = EnumHelper.addArmorMaterial("slimeinfused", SlimeBreeder.MODID + ":slimeinfused", 7, new int[] {2, 5, 3, 1}, 0, SoundEvents.ENTITY_SLIME_SQUISH, 5.0F);
	}
	
	
	
	
}




