package com.itchymichi.slimebreeder.utility;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederEnchantments;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.SlimeBreederTab;
import com.itchymichi.slimebreeder.brewingrecipies.SlimeCorePotionRecipe;
import com.itchymichi.slimebreeder.crafting.recipe.CoreCrafting;
import com.itchymichi.slimebreeder.entity.monster.EntityCustomSlimeBase;
import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeBase;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeRainbowCrystal;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeCrystal;
import com.itchymichi.slimebreeder.events.SlimeBreederEventHooks;
//import com.itchymichi.slimebreeder.inventory.TileEntitySlimeReader;
import com.itchymichi.slimebreeder.items.FilledSlimeCapsule;
import com.itchymichi.slimebreeder.items.BreedingCatalystIron;
import com.itchymichi.slimebreeder.items.Slimalyser;
import com.itchymichi.slimebreeder.items.SlimeCapsule;
import com.itchymichi.slimebreeder.items.gui.GuiSlimeReaderHelm;
import com.itchymichi.slimebreeder.items.gui.GuiSlimeReaderHelm2;
import com.itchymichi.slimebreeder.potions.PotionSlimeAcid;
import com.itchymichi.slimebreeder.potions.PotionSlimeBreedable;
import com.itchymichi.slimebreeder.potions.PotionSlimeCrafting;
import com.itchymichi.slimebreeder.potions.PotionSlimeDomesticated;
import com.itchymichi.slimebreeder.potions.PotionSlimeDomesticatedFollow;
import com.itchymichi.slimebreeder.potions.PotionSlimeDomesticatedWait;
import com.itchymichi.slimebreeder.potions.PotionSlimeHeatToLiquid;
import com.itchymichi.slimebreeder.potions.PotionSlimeHunger;
import com.itchymichi.slimebreeder.potions.PotionSlimeResult;
import com.itchymichi.slimebreeder.potions.PotionSlimeTransform;
import com.itchymichi.slimebreeder.potions.PotionSlimeTransformComplete;
import com.itchymichi.slimebreeder.render.entity.RenderEntityArtificalSlimeCrystal;
import com.itchymichi.slimebreeder.render.entity.RenderEntityArtificalSlimeRainbowCrystal;
import com.itchymichi.slimebreeder.render.entity.RenderEntityCustomSlimeBase;
import com.itchymichi.slimebreeder.render.entity.RenderEntitySlime2;
import com.itchymichi.slimebreeder.utility.handlers.RegistryHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

public class CommonProxy
{
		//CreativeTabs
		public static SlimeBreederTab slimetab;
		
		//Entities
		public static EntityCustomSlimeBase EntityCustomSlimeBase;
		public static EntityColorSlimeBase EntitySlime2;
		public static EntityArtificalSlimeRainbowCrystal EntityArtificalSlimeRainbowCrystal;
		public static EntityArtificalSlimeCrystal EntityArtificalSlimeCrystal;
		
		//Instances
		
		public static RenderEntityCustomSlimeBase CUSTOMSLIMEBASEFACTORY;
		public static RenderEntitySlime2 SLIMEFACTORY;
		public static RenderEntityArtificalSlimeRainbowCrystal ARTSLIMERAINBOWCRYSTALFACTORY;
		public static RenderEntityArtificalSlimeCrystal ARTSLIMECRYSTALFACTORY;

		
		//Potions
		public static Potion slimeBreedable = (new PotionSlimeBreedable(false, -40000)).setIconIndex(0, 0).setPotionName("potion.slimeBreedable");
		public static Potion slimeHunger = (new PotionSlimeHunger(false, -80000)).setIconIndex(0, 0).setPotionName("potion.slimeHunger");
		public static Potion slimeResult = (new PotionSlimeResult(false, -200)).setIconIndex(0, 0).setPotionName("potion.slimeResult");
		public static Potion slimeCrafting = (new PotionSlimeCrafting(false, -20)).setIconIndex(0, 0).setPotionName("potion.slimeCrafting");
		public static Potion slimeTransform = (new PotionSlimeTransform(false, 100000)).setIconIndex(0, 0).setPotionName("potion.slimeTransform");
		public static Potion slimeTransformComplete = (new PotionSlimeTransformComplete(false, 100000)).setIconIndex(0, 0).setPotionName("potion.slimeTransformComplete");
		public static Potion slimeDomesticated = (new PotionSlimeDomesticated(false, 0)).setIconIndex(0, 0).setPotionName("potion.slimeDomesticated");
		public static Potion slimeDomesticatedFollow = (new PotionSlimeDomesticatedFollow(false, 0)).setIconIndex(0, 0).setPotionName("potion.slimeDomesticatedFollow");
		public static Potion slimeDomesticatedWait = (new PotionSlimeDomesticatedWait(false, 0)).setIconIndex(0, 0).setPotionName("potion.slimeDomesticatedWait");
		public static Potion slimeAcid = (new PotionSlimeAcid(false, 0)).setIconIndex(0, 0).setPotionName("potion.slimeAcid");
		public static Potion slimeHeatToLiquid = (new PotionSlimeHeatToLiquid(false, 100000)).setIconIndex(0, 0).setPotionName("potion.slimeHeatToLiquid");
		
		
		//ResourceLocations
		ResourceLocation customSlimeBase = new ResourceLocation(SlimeBreeder.MODID + ":" + "entitycustomslimebase");
		ResourceLocation colouredSlime = new ResourceLocation(SlimeBreeder.MODID + ":" + "entityslime2");
		ResourceLocation artRainbowCrystalSlime = new ResourceLocation(SlimeBreeder.MODID + ":" + "entityartificalslimerainbowcrystal");
		ResourceLocation artCrystalSlime = new ResourceLocation(SlimeBreeder.MODID + ":" + "entityartificalslimecrystal");

		
		public void registerItemRenderer(Item item, int meta, String id){}
		public void registerItemRendererCustom(Item item, int meta, String id){}
		
		
		
		public void preInit() 
		{
			slimetab = new SlimeBreederTab("tabSlimeBreederTab");
			
			EntityRegistry.registerModEntity(customSlimeBase, EntityCustomSlimeBase.class, "entitycustomslimebase", 0, SlimeBreeder.INSTANCE, 80, 3, true, -210, -20);
			EntityRegistry.registerModEntity(colouredSlime, EntityColorSlimeBase.class, "entityslime2", 1, SlimeBreeder.INSTANCE, 80, 3, true, -210, -20);
			EntityRegistry.registerModEntity(artRainbowCrystalSlime, EntityArtificalSlimeRainbowCrystal.class, "entityartificalslimerainbowcrystal", 2, SlimeBreeder.INSTANCE, 80, 3, true, -210, -20);
			EntityRegistry.registerModEntity(artCrystalSlime, EntityArtificalSlimeCrystal.class, "entityartificalslimecrystal", 4, SlimeBreeder.INSTANCE, 80, 3, true, -210, -20);

			
			
	    	
			//EntityRegistry.addSpawn(EntitySlime2.class, 4, 1, 2, EnumCreatureType.MONSTER,Biomes.EXTREME_HILLS, Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.MUTATED_TAIGA, Biomes.PLAINS, Biomes.MUTATED_PLAINS, Biomes.FOREST, Biomes.MUTATED_FOREST, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.ROOFED_FOREST, Biomes.MUTATED_ROOFED_FOREST, Biomes.SWAMPLAND, Biomes.MUTATED_SWAMPLAND, Biomes.RIVER);
	    	
			
			
			//EntityRegistry.addSpawn(EntitySlime2.class, 4, 1, 2, EnumCreatureType.MONSTER, getSpawnBiome());
			
			//System.out.println(getSpawnBiome());

	    	potionEffects();
		    
		    
		    MinecraftForge.EVENT_BUS.register(new SlimeBreederEventHooks());
		    MinecraftForge.EVENT_BUS.register(new GuiSlimeReaderHelm2());
			
		}

		public void init() 
		{
			RegistryHandler.initRegistries();
			NetworkRegistry.INSTANCE.registerGuiHandler(SlimeBreeder.INSTANCE,  new GuiHandler());
			registerRecipes();
			    	
			
		}

		public void load() 
		{
			
		}
		
		public void potionEffects()
		{
			Potion[] potionTypes = null;

		    for (Field f : Potion.class.getDeclaredFields()) {
		    f.setAccessible(true);
		    try {
		    if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
		    Field modfield = Field.class.getDeclaredField("modifiers");
		    modfield.setAccessible(true);
		    modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

		    potionTypes = (Potion[])f.get(null);
		    final Potion[] newPotionTypes = new Potion[256];
		    //System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
		    f.set(null, newPotionTypes);
		    }
		    }
		    catch (Exception e) {
		    //System.err.println("Severe error, please report this to the mod author:");
		    //System.err.println(e);
		    }
		    }
		}
		

		
		public Biome[] getSpawnBiome()
		{
			


			Set<Biome> biomes = new HashSet<Biome>();
			biomes.addAll(BiomeDictionary.getBiomes(Type.FOREST));
			biomes.addAll(BiomeDictionary.getBiomes(Type.WET));
			biomes.addAll(BiomeDictionary.getBiomes(Type.PLAINS));
			biomes.addAll(BiomeDictionary.getBiomes(Type.SWAMP));
			biomes.addAll(BiomeDictionary.getBiomes(Type.LUSH));
			biomes.removeAll(BiomeDictionary.getBiomes(Type.HOT));
			biomes.removeAll(BiomeDictionary.getBiomes(Type.DRY));
			biomes.removeAll(BiomeDictionary.getBiomes(Type.SANDY));
			biomes.removeAll(BiomeDictionary.getBiomes(Type.SAVANNA));
			
			Biome[] arr = biomes.toArray(new Biome[biomes.size()]);

			
			return arr;
			
		}
		
		public void registerRecipes()
		{
			
			IBrewingRecipe recipe1 = new SlimeCorePotionRecipe();
			
			BrewingRecipeRegistry.addRecipe(recipe1);
			
		}
		

	
}
