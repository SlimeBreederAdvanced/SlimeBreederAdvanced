package com.itchymichi.slimebreeder.lists;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EnumEntityTune 
{
	public static CopyOnWriteArrayList<SlimeEntityTune> listOfObjects = new CopyOnWriteArrayList<SlimeEntityTune>();
	
	public static CopyOnWriteArrayList<SlimeEntityTune> initTuneList() {
	
	EntityZombie zombie = null;
	
	int tuneNo;
	

	listOfObjects.add(new SlimeEntityTune(SoundEvents.E_PARROT_IM_ZOMBIE, 1));
	listOfObjects.add(new SlimeEntityTune(SoundEvents.E_PARROT_IM_SKELETON, 2));
	listOfObjects.add(new SlimeEntityTune(SoundEvents.E_PARROT_IM_CREEPER, 3));
	listOfObjects.add(new SlimeEntityTune(SoundEvents.E_PARROT_IM_SPIDER, 4));
	listOfObjects.add(new SlimeEntityTune(SoundEvents.E_PARROT_IM_ENDERMAN, 5));	
	listOfObjects.add(new SlimeEntityTune(SoundEvents.ENTITY_COW_AMBIENT, 6));
	listOfObjects.add(new SlimeEntityTune(SoundEvents.ENTITY_CHICKEN_AMBIENT, 7));
	listOfObjects.add(new SlimeEntityTune(SoundEvents.ENTITY_PIG_AMBIENT, 8));
	listOfObjects.add(new SlimeEntityTune(SoundEvents.ENTITY_IRONGOLEM_HURT, 9));
	listOfObjects.add(new SlimeEntityTune(SoundEvents.ENTITY_SQUID_AMBIENT, 10));
	listOfObjects.add(new SlimeEntityTune(SoundEvents.ENTITY_SHEEP_AMBIENT, 11));
		
	
	return listOfObjects;

	}

	public static int getSize() 
	{
		int length = listOfObjects.size();
		
		return length;
	}
	
	
}


