package com.itchymichi.slimebreeder;

import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.handlers.SBASoundHandler;
import com.itchymichi.slimebreeder.render.entity.RenderEntitySlime2;

import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(modid = "slimebreeder", name = "Slime Breeding Advanced", version = "3.0.5")
public class SlimeBreeder 
{
	@Mod.Instance("SBA")
	public static final String MODID = "slimebreeder";
	public static SlimeBreeder instance;
	public static final String VERSION = "3.0.5";
	//private World worldIn;


	//Instances
	@Instance(MODID)
	public static SlimeBreeder INSTANCE;


	 @SidedProxy(clientSide="com.itchymichi.slimebreeder.utility.CombinedClientProxy", serverSide="com.itchymichi.slimebreeder.utility.DedicatedServerProxy")
	  public static com.itchymichi.slimebreeder.utility.CommonProxy proxy;
	 
	 //Loaded Mods
	 public static boolean weather2 = false;

	 
	 
	 
	//Getters

	
	//Enums
	 
	 public enum GUI_ENUM 
	 {
	     READER, BOOK, BACKPACK
	 }
	 
	@EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		proxy.preInit();
		
	}
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init();
    	//SBASoundHandler.init();
    	
    }
    
    @EventHandler
    public void postinit(FMLInitializationEvent event)
    {
    	ismodLoaded();
		 EntityRegistry.addSpawn(EntityColorSlimeBase.class, 4, 1, 2, EnumCreatureType.MONSTER, this.proxy.getSpawnBiome());
			
		//System.out.println(this.proxy.getSpawnBiome());
    	
    }
    
    public void load(FMLInitializationEvent event)
    {
    	proxy.load();
    }
    
    public void ismodLoaded()
    {
    	//System.out.println("Checking Loaded Mods");
    	if(Loader.isModLoaded("weather2")){
    		this.weather2 = true;
    	}
    	
    	//System.out.println("Loaded Mods:");
    	//if(weather2){System.out.println("Weather2");}


    	
    	
    }
////////////////////////////////////////////////Start Custom Code//////////////////////////////////////////////////////////////////////
    /*private void registerEntitys() {
    	EntityRegistry.registerModEntity(EntitySlime2.class, "entityslime2", 0, SlimeBreeder.INSTANCE, 80, 3, true);*/

		///////RenderingRegistry.registerEntityRenderingHandler(EntitySlime2.class, new RenderEntitySlime2(Minecraft.getMinecraft().getRenderManager()));///// WIP

    }
