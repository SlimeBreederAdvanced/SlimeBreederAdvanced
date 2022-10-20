package com.itchymichi.slimebreeder.utility;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.inventory.InventorySlimeReader;
import com.itchymichi.slimebreeder.items.containers.ContainerSlimeReader;
import com.itchymichi.slimebreeder.items.containers.ContainerSlimepedia;
import com.itchymichi.slimebreeder.items.gui.GuiSlimeReader;
import com.itchymichi.slimebreeder.items.gui.GuiSlimepedia;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;


public class GuiHandler implements IGuiHandler
{

	@Override
    public Object getServerGuiElement(int ID, EntityPlayer player, 
          World world, int x, int y, int z) 
    { 

        
            if (ID == SlimeBreeder.GUI_ENUM.READER.ordinal())
            {
            	//System.out.println("Server Gui ?");
            	return new ContainerSlimeReader(player.inventory, new InventorySlimeReader(player.getHeldItemMainhand()));
            }
            
            if (ID == SlimeBreeder.GUI_ENUM.BOOK.ordinal())
            {
            	//System.out.println("Server Gui ?");
            	
            	return null;
            }
            
        
        
        //System.out.println("Server Gui no");
        return null;
    }
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		
		if (ID == SlimeBreeder.GUI_ENUM.READER.ordinal())
		{
        	//System.out.println("Client Gui ?");
        	return new GuiSlimeReader(player.inventory, new InventorySlimeReader(player.getHeldItemMainhand()));
        }
		
		if (ID == SlimeBreeder.GUI_ENUM.BOOK.ordinal())
		{
        	//System.out.println("Client Gui ?");
        	//Minecraft.getMinecraft().displayGuiScreen(new GuiSlimepedia());
        	return new GuiSlimepedia(player, world);
        }
		
		if (ID == SlimeBreeder.GUI_ENUM.BACKPACK.ordinal())
		{
        	//System.out.println("Client Gui ?");
        	//Minecraft.getMinecraft().displayGuiScreen(new GuiSlimepedia());
        	return new GuiChest(player.inventory, player.inventory);
        }

		//System.out.println("Client Gui no");
    return null;
	}

	
}
