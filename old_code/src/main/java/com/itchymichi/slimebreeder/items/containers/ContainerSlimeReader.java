package com.itchymichi.slimebreeder.items.containers;





import com.itchymichi.slimebreeder.items.slots.SlotReader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSlimeReader extends Container
{
    private final IInventory inventorySlimeReader;
    private final int sizeInventory;
	
    public ContainerSlimeReader(InventoryPlayer invPlayer, IInventory invSlimeReader) 
	{
    	inventorySlimeReader = invSlimeReader;
    	sizeInventory = invSlimeReader.getSizeInventory();
    	
    	
    	
    	for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(invPlayer, j1 + (l + 1) * 9, (8 + j1 * 18)+26, (84 + l * 18)-3));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(invPlayer, i1, (8 + i1 * 18)+26, (142)-3));
            ////System.out.println(containterLeft);
            ////System.out.println(containterTop);
        }
        
        for (int i1 = 0; i1 < 2; ++i1)
        {
            this.addSlotToContainer(new SlotReader(invSlimeReader, i1, (8 + i1 * 166)+15, (142)-95));
            ////System.out.println(containterLeft);
            ////System.out.println(containterTop);
        }
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = ItemStack.EMPTY;
	    Slot slot = (Slot) this.inventorySlots.get(fromSlot);
	    
	    

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	        // [...] Custom behaviour

	        if (current.getCount() == 0)
	            slot.putStack((ItemStack) ItemStack.EMPTY);
	        else
	            slot.onSlotChanged();

	        if (current.getCount() == previous.getCount())
	            return ItemStack.EMPTY;
	        slot.onTake(playerIn, current);
	    }
	    return previous;
	}
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 /*int x = GuiSlimeReader.leftX;
	int y = GuiSlimeReader.topY;*/
	
	//public final IInventory inventorySlimeReader;
	//public InventoryPlayer playerInventory;
	//public final InventorySlimeReader inventory;
	
	//private final int sizeInventory;
	
	//private static int guiWidth = 227;
	//private static int guiHeight = 163;
	
	//private int guiLeft = GuiScreen.width;
	
	/*static int x = (Minecraft.getMinecraft().displayWidth - guiWidth)/2;
	static int y = (Minecraft.getMinecraft().displayHeight - guiHeight)/2;*/
	
	//public int containterLeft;
	//public int containterTop;
	
	
	/*public static void setScreenSize(int Left, int Top)
	{
		containterLeft = Left;
		containterTop = Top;
	}*/
	
		//this.inventory = inventorySlimeReader;
		
		// TODO Auto-generated constructor stub
	//}
	
	//public ContainerSlimeReader(EntityPlayer player, InventoryPlayer invPlayer, InventorySlimeReader inventorySlimeReader)
	//{
		
		//this.sizeInventory = playerInventory.getSizeInventory();
		//this.inventory = inventorySlimeReader;

		
		/*for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(invPlayer, j1 + (l + 1) * 9, (8 + j1 * 18)+26, (84 + l * 18)-3));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(invPlayer, i1, (8 + i1 * 18)+26, (142)-3));
            ////System.out.println(containterLeft);
            ////System.out.println(containterTop);
        }
		
	}
	
    

	
	




	public void updateScreen()
    {
       
    }


	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return true;
	}


	/*@Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, 
          int slotIndex)
    {
        ItemStack itemStack1 = null;
        Slot slot = (Slot)inventorySlots.get(slotIndex);

       
		if (slot != null && slot.getHasStack())
        {
            ItemStack itemStack2 = slot.getStack();
            itemStack1 = itemStack2.copy();


        }
                else if (slotIndex >= sizeInventory 
                     && slotIndex < sizeInventory+27) // player inventory slots
                {
                    if (!mergeItemStack(itemStack1, sizeInventory+27, 
                          sizeInventory+36, false))
                    {
                        return null;
                    }
                }
                else if (slotIndex >= sizeInventory+27 
                      && slotIndex < sizeInventory+36 
                      && !mergeItemStack(itemStack1, sizeInventory+1, 
                      sizeInventory+27, false)) // hotbar slots
                {
                    return null;
                }


        return itemStack1;
    	}*/
    
    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
     * is null for the initial slot that was double-clicked.
     */
	
    /*public boolean canMergeSlot(ItemStack stack, Slot p_94530_2_)
    {
        return p_94530_2_.inventory && super.canMergeSlot(stack, p_94530_2_);
    }*/
	
	/*@Override
    public ItemStack slotClick(
          int parSlotId, 
          int parMouseButtonId, 
          int parClickMode, 
          EntityPlayer parPlayer
          )
    {
        ItemStack clickItemStack = super.slotClick(parSlotId, parMouseButtonId, 
              parClickMode, parPlayer);
        if(inventorySlots.size() > parSlotId && parSlotId >= 0)
        {
            if(inventorySlots.get(parSlotId) != null)
            {
                
            }
        }
        return clickItemStack;
    }
	


    @Override
    public Slot getSlot(int parSlotIndex)
    {
        if(parSlotIndex >= inventorySlots.size())
            parSlotIndex = inventorySlots.size() - 1;
        return super.getSlot(parSlotIndex);
    }*/
}
