package com.itchymichi.slimebreeder.utility.handlers;

import com.itchymichi.slimebreeder.SlimeBreederBlocks;
import com.itchymichi.slimebreeder.SlimeBreederEnchantments;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.SlimeBreederSounds;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler 
{

	
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		//System.out.println("Registering Items");
		event.getRegistry().registerAll(SlimeBreederItems.ITEMS.toArray(new Item[0]));
		
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		//System.out.println("Registering Models");
		for(Item item : SlimeBreederItems.ITEMS)
		{
			if(item instanceof IHasModel)
			{
				//System.out.println("Registering Item Models");
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : SlimeBreederBlocks.BLOCKS)
		{
			if(block instanceof IHasModel)
			{
				//System.out.println("Registering Block Models");
				((IHasModel)block).registerModels();
			}
		}
		
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		//System.out.println("Registering Blocks");
		event.getRegistry().registerAll(SlimeBreederBlocks.BLOCKS.toArray(new Block[0]));
	}
	
	
	
	@SubscribeEvent
	public static void onEnchantmentRegister(RegistryEvent.Register<Enchantment> event)
	{
		event.getRegistry().registerAll(SlimeBreederEnchantments.ENCHANTMENT.toArray(new Enchantment[0]));
	}
	
	@SubscribeEvent
	public static void onSoundRegister(RegistryEvent.Register<SoundEvent> event)
	{
		
		////System.out.println(SlimeBreederSounds.SOUNDS.toArray(new SoundEvent[0]).toString());
		event.getRegistry().registerAll(SlimeBreederSounds.SOUNDS.toArray(new SoundEvent[0]));
	}
	
	public static void initRegistries()
	{
		registerSmelting();
	}
	
	public static void registerSmelting()
	{
		GameRegistry.addSmelting(SlimeBreederItems.IRONSLIMECHUNK, new ItemStack(Items.IRON_INGOT, 1), 0.25F);
    	GameRegistry.addSmelting(SlimeBreederItems.GOLDSLIMECHUNK, new ItemStack(Items.GOLD_INGOT, 1), 0.5F);
    	GameRegistry.addSmelting(SlimeBreederItems.DIAMONDSLIMECHUNK, new ItemStack(Items.DIAMOND, 1), 1F);
	}

	
}
