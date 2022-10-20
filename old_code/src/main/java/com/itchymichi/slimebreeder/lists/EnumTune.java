package com.itchymichi.slimebreeder.lists;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityAgeable;
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
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EnumTune 
{
	public static CopyOnWriteArrayList<SlimeTune> listOfObjects = new CopyOnWriteArrayList<SlimeTune>();
	
	public static CopyOnWriteArrayList<SlimeTune> initTuneList() {
	
	
	//cow.setGrowingAge(1);
	
	
	//((EntityCow)(cow)).getDataManager().set(BABY, Boolean.FALSE);
	int tuneNo;
	

	listOfObjects.add(new SlimeTune("Zombie", 1));
	listOfObjects.add(new SlimeTune("Skeleton", 2));
	listOfObjects.add(new SlimeTune("Creeper", 3));
	listOfObjects.add(new SlimeTune("Spider", 4));
	listOfObjects.add(new SlimeTune("Enderman", 5));	
	listOfObjects.add(new SlimeTune("Cow", 6));
	listOfObjects.add(new SlimeTune("Chicken", 7));
	listOfObjects.add(new SlimeTune("Pig", 8));
	listOfObjects.add(new SlimeTune("VillagerGolem", 9));
	listOfObjects.add(new SlimeTune("Squid", 10));
	listOfObjects.add(new SlimeTune("Sheep", 11));
	
	return listOfObjects;

	}

	public static int getSize() 
	{
		int length = listOfObjects.size();
		
		return length;
	}
	
	
}


