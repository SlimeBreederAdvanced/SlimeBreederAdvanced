package com.itchymichi.slimebreeder.utility;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.entity.monster.EntitySlime2;
import com.itchymichi.slimebreeder.events.SlimeBreederEventHooks;
import com.itchymichi.slimebreeder.handlers.MeshHandler;
import com.itchymichi.slimebreeder.items.FilledSlimeCapsule;
import com.itchymichi.slimebreeder.items.Slimalyser;
import com.itchymichi.slimebreeder.items.SlimeCapsule;
import com.itchymichi.slimebreeder.items.BreedingCatalystIron;
import com.itchymichi.slimebreeder.items.gui.GuiSlimeReader;
import com.itchymichi.slimebreeder.potions.PotionSlimeHunger;
import com.itchymichi.slimebreeder.render.entity.RenderEntitySlime2;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class test 
{
	
	/**
	   * Run before anything else. Read your config, register renderers
	   */
	@Override
	  public void preInit()
	  {
	    super.preInit();
	    RenderingRegistry.registerEntityRenderingHandler(EntitySlime2.class, RenderEntitySlime2.FACTORY);
	  }

	  /**
	   * Do your mod setup. Build whatever data structures you care about. 
	   * send FMLInterModComms messages to other mods.
	   */

	@Override
	  public void init()
	  {
	    super.init();
	  }

	  /**
	   * Handle interaction with other mods, complete your setup based on this.
	   */
	  
	  @Override
	  public void load()
	  {
	    super.load();
	  }
	  
	  public void registerItemRenderer(Item item, int meta, String id)
	  {
		  ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	  }
	  
	  public void registerItemRendererCustom(Item item, int meta, String id)
	  {
		  ModelLoader.setCustomMeshDefinition(item, new MeshHandler());	  
	  }
	}
	
	
