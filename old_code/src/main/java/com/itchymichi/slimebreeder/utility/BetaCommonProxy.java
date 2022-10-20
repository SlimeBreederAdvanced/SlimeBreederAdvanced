package com.itchymichi.slimebreeder.utility;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.Lists;
import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederBlocks;
import com.itchymichi.slimebreeder.SlimeBreederEnchantments;
import com.itchymichi.slimebreeder.SlimeBreederFluids;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.SlimeBreederMaterials;
import com.itchymichi.slimebreeder.SlimeBreederTab;
import com.itchymichi.slimebreeder.brewingrecipies.SlimeCorePotionRecipe;
import com.itchymichi.slimebreeder.entity.monster.EntitySlime2;
import com.itchymichi.slimebreeder.events.SlimeBreederEventHooks;
//import com.itchymichi.slimebreeder.inventory.TileEntitySlimeReader;
import com.itchymichi.slimebreeder.items.FilledSlimeCapsule;
import com.itchymichi.slimebreeder.items.BreedingCatalystIron;
import com.itchymichi.slimebreeder.items.Slimalyser;
import com.itchymichi.slimebreeder.items.SlimeCapsule;
import com.itchymichi.slimebreeder.potions.PotionSlimeAcid;
import com.itchymichi.slimebreeder.potions.PotionSlimeBreedable;
import com.itchymichi.slimebreeder.potions.PotionSlimeCrafting;
import com.itchymichi.slimebreeder.potions.PotionSlimeDomesticated;
import com.itchymichi.slimebreeder.potions.PotionSlimeDomesticatedFollow;
import com.itchymichi.slimebreeder.potions.PotionSlimeDomesticatedWait;
import com.itchymichi.slimebreeder.potions.PotionSlimeHunger;
import com.itchymichi.slimebreeder.potions.PotionSlimeResult;
import com.itchymichi.slimebreeder.potions.PotionSlimeTransform;
import com.itchymichi.slimebreeder.render.entity.RenderEntitySlime2;
import com.itchymichi.slimebreeder.smelteryrecipies.SlimeCastingRecipe;
import com.itchymichi.slimebreeder.smelteryrecipies.SlimeCoreCastingRecipe;
import com.itchymichi.slimebreeder.utility.handlers.RegistryHandler;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class test
{
	public static Class recipematchTC;
	public static Class integrationTC;
	public static Class registryTC;;
	public static Class fluidcoloredTC;
	public static Class fluidmoltenTC;
	public static Class materialTC;
	public static Class castrecipeTC;
	public static Class IcastrecipeTC;
	public static Class meltingrecipeTC;
	public static Class tinkerfluidsTC;
	
	
		//CreativeTabs
		public static SlimeBreederTab slimetab;
		
		//Entities
		public static EntitySlime2 EntitySlime2;
		
		//Instances
		public static RenderEntitySlime2 FACTORY;
		
		//Potions
		public static Potion slimeBreedable = (new PotionSlimeBreedable(false, -40000)).setIconIndex(0, 0).setPotionName("potion.slimeBreedable");
		public static Potion slimeHunger = (new PotionSlimeHunger(false, -80000)).setIconIndex(0, 0).setPotionName("potion.slimeHunger");
		public static Potion slimeResult = (new PotionSlimeResult(false, -200)).setIconIndex(0, 0).setPotionName("potion.slimeResult");
		public static Potion slimeCrafting = (new PotionSlimeCrafting(false, -20)).setIconIndex(0, 0).setPotionName("potion.slimeCrafting");
		public static Potion slimeTransform = (new PotionSlimeTransform(false, 100000)).setIconIndex(0, 0).setPotionName("potion.slimeTransform");
		public static Potion slimeDomesticated = (new PotionSlimeDomesticated(false, 0)).setIconIndex(0, 0).setPotionName("potion.slimeDomesticated");
		public static Potion slimeDomesticatedFollow = (new PotionSlimeDomesticatedFollow(false, 0)).setIconIndex(0, 0).setPotionName("potion.slimeDomesticatedFollow");
		public static Potion slimeDomesticatedWait = (new PotionSlimeDomesticatedWait(false, 0)).setIconIndex(0, 0).setPotionName("potion.slimeDomesticatedWait");
		public static Potion slimeAcid = (new PotionSlimeAcid(false, 0)).setIconIndex(0, 0).setPotionName("potion.slimeAcid");

		//ResourceLocations
		ResourceLocation colouredSlime = new ResourceLocation(SlimeBreeder.MODID + ":" + "entityslime2");
		
		public void registerItemRenderer(Item item, int meta, String id){}
		public void registerItemRendererCustom(Item item, int meta, String id){}
		
		public void loadClassTC(){
			
			ClassLoader classLoader = BetaCommonProxy.class.getClassLoader();
			
			
			classLoader.loadClass("slimeknights.mantle.util.RecipeMatch");	
			classLoader.loadClass("slimeknights.tconstruct.TinkerIntegration");
			classLoader.loadClass("slimeknights.tconstruct.library.TinkerRegistry");
			classLoader.loadClass("slimeknights.tconstruct.library.fluid.FluidColored");
			classLoader.loadClass("slimeknights.tconstruct.library.fluid.FluidMolten");
			classLoader.loadClass("slimeknights.tconstruct.library.materials.Material");
			classLoader.loadClass("slimeknights.tconstruct.library.smeltery.CastingRecipe");
			classLoader.loadClass("slimeknights.tconstruct.library.smeltery.ICastingRecipe");
			classLoader.loadClass("slimeknights.tconstruct.library.smeltery.MeltingRecipe");
			classLoader.loadClass("slimeknights.tconstruct.shared.TinkerFluids");
			
			
		}
		
		public void preInit() 
		{
			slimetab = new SlimeBreederTab("tabSlimeBreederTab");
			
			EntityRegistry.registerModEntity(colouredSlime, EntitySlime2.class, "entityslime2", 0, SlimeBreeder.INSTANCE, 80, 3, true, -210, -20);
			
			if(Loader.isModLoaded("tconstruct")){
				
				loadClassTC();
				
			}
			
			
			if(Loader.isModLoaded("tconstruct")){
				
			SlimeBreederFluids.FLUIDMOLTEN.toArray(new FluidMolten[0]);
			}


			
			
			
	    	
			//EntityRegistry.addSpawn(EntitySlime2.class, 4, 1, 2, EnumCreatureType.MONSTER,Biomes.EXTREME_HILLS, Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.MUTATED_TAIGA, Biomes.PLAINS, Biomes.MUTATED_PLAINS, Biomes.FOREST, Biomes.MUTATED_FOREST, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.ROOFED_FOREST, Biomes.MUTATED_ROOFED_FOREST, Biomes.SWAMPLAND, Biomes.MUTATED_SWAMPLAND, Biomes.RIVER);
	    	
			
			
			//EntityRegistry.addSpawn(EntitySlime2.class, 4, 1, 2, EnumCreatureType.MONSTER, getSpawnBiome());
			
			//System.out.println(getSpawnBiome());

	    	potionEffects();
		    
		    
		    MinecraftForge.EVENT_BUS.register(new SlimeBreederEventHooks());
			
		}

		public void init() 
		{
			RegistryHandler.initRegistries();
			NetworkRegistry.INSTANCE.registerGuiHandler(SlimeBreeder.INSTANCE,  new GuiHandler());
			
			if(Loader.isModLoaded("tconstruct")){
			SlimeBreederMaterials.MATERIALS.toArray(new Material[0]);
			}
			//TinkerRegistry.registerEntityMelting(EntitySlime2.class, new FluidStack(SlimeBreederFluids.LIQUIDCRYSTALSLIME, 1));
	
			if(Loader.isModLoaded("tconstruct")){
			registerTCRecipes();
			}
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
		
		public void registerTCRecipes()
		{
			
			
			
			
			//MeltingRecipe Mrecipe;
			
			TinkerRegistry.registerMelting(SlimeBreederItems.CLEARSLIMECRYSTAL, SlimeBreederFluids.LIQUIDCRYSTALSLIME, Material.VALUE_Ingot * 4);
			
			//TinkerRegistry.registerAlloy(recipe);
			//TinkerRegistry.registerMelting(Mrecipe);
		    //TinkerRegistry.registerBasinCasting(new ItemStack(SlimeBreederBlocks.SOLIDSLIME), ItemStack.EMPTY, SlimeBreederFluids.LIQUIDCRYSTALSLIME, Material.VALUE_Block);
		    
		    
		    ICastingRecipe recipe1 = new SlimeCoreCastingRecipe();
		    ICastingRecipe recipe2 = new SlimeCastingRecipe();
		    
		    //TinkerRegistry.registerBasinCasting(recipe);
		    
		    TinkerRegistry.registerTableCasting(recipe1);
		    TinkerRegistry.registerBasinCasting(recipe2);
		    
		    
		    TinkerRegistry.registerEntityMelting(EntitySlime2.class, new FluidStack(TinkerFluids.blood, 1));
		    
		    
		    
		    
		    	/*Map<String, Fluid> fluids = FluidRegistry.getRegisteredFluids();
		    		for (Map.Entry<String, Fluid> entry : fluids.entrySet())
		    		{
		    		    System.out.println(entry.getKey() + "/" + entry.getValue());
		    		    ItemStack cast = new ItemStack(SlimeBreederItems.CLEARSLIMECRYSTAL);
		    		    ItemStack output = new ItemStack(SlimeBreederItems.CLEARSLIMECRYSTAL2);
		    		    output.getOrCreateSubCompound(SlimeBreeder.MODID);
		    		    output.getTagCompound().setString("fluid", entry.getKey());
		    		    
		    		    
		    		    ICastingRecipe recipe = new CastingRecipe(output, cast, entry.getValue(), 1, true, false);
		    		    
						//TinkerRegistry.registerBasinCasting(output, cast, entry.getValue(), 1);
		    		    TinkerRegistry.registerBasinCasting(recipe);
		    		    
		    		    //System.out.println(TinkerRegistry.getAllTableCastingRecipes().toString());
		    		    
		    		}*/
		    
		    //FluidRegistry.getRegisteredFluids();
		    
		    //TinkerRegistry.registerBasinCasting(cast.getTagCompound().setString(fluid, fluid., cast, fluid, amount);
		}
	
		
		
		
		
		
}
