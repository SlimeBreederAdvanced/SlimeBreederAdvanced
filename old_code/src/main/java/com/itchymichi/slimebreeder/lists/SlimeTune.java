package com.itchymichi.slimebreeder.lists;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class SlimeTune 
{
	public String targetName;
	public int tune;

	
	public SlimeTune(String entityName, int tuneid) 
	{
		this.targetName = entityName;
		this.tune = tuneid;

	}
	
	
	public String getEntityName()
	{
		return this.targetName;
	}
	
	
	public Integer getTune()
	{
		return this.tune;
	}
	
	
}
