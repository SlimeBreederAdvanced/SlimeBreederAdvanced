package com.itchymichi.slimebreeder.utility;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.items.gui.GuiSlimeReader;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class DedicatedServerProxy extends CommonProxy 
{


	  /**
	   * Run before anything else. Read your config.
	   */
		  @Override
		  public void preInit()
		  {
		    super.preInit();
		    // register my renderers
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
		  

	
}
