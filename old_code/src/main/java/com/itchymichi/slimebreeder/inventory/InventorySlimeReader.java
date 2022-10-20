package com.itchymichi.slimebreeder.inventory;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.items.Slimalyser;
import com.itchymichi.slimebreeder.items.gui.GuiSlimeReader;
import com.itchymichi.slimebreeder.lists.EnumItemColor;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.Constants;

public class InventorySlimeReader extends TileEntityLockable implements IInventory
{

	
	
	
	private String name = "Inventory Slime Reader";
	
	public enum slotEnum 
    {
        LEFT_SLOT, RIGHT_SLOT
    }
	
	public Item leftItem;
	public Item rightItem;
	public ItemStack leftItemStack = new ItemStack(Item.getItemFromBlock(Blocks.IRON_ORE));
	public ItemStack rightItemStack = new ItemStack(Item.getItemFromBlock(Blocks.IRON_ORE));
	
	
	/** Provides NBT Tag Compound to reference */
	private final ItemStack invSlimeReader;

	/** Defining your inventory size this way is handy */
	public static final int INV_SIZE = 2;

	/** Inventory's size must be same as number of slots you add to the Container class */
	private ItemStack[] inventory = new ItemStack[INV_SIZE];
	
	
	private String customName;
	
	
	
	public InventorySlimeReader(ItemStack stack)
	{
		invSlimeReader = stack;
		
		if (!stack.hasTagCompound()) 
		{
			stack.setTagCompound(new NBTTagCompound());
			//System.out.println("Has no Tag Compound");
		}
			readFromNBT(stack.getTagCompound());
			
	}
	
	@Override
	public String getName() {
		
		return this.hasCustomName() ? this.customName : "container.slimereader";
	}
	

@Override
public boolean hasCustomName() {
	
	return this.customName != null && !this.customName.equals("");
}

	public String getCustomName() {
		
		return customName;
	}
	
	public void setCustomName(String customName) {
		
		this.customName = customName;
	}
	

	

	
	
	@Override
	public ITextComponent getDisplayName() {
		
		// TODO Auto-generated method stub
		return null;
	}//old

	@Override
	public int getSizeInventory() {
		
		// TODO Auto-generated method stub
		return 2;
	}//old

	@Override
	public ItemStack getStackInSlot(int slot) {
		// TODO Auto-generated method stub
		
		
		if(inventory[slot] == null)
		{
			inventory[slot] = ItemStack.EMPTY;
		}
		
		return inventory[slot];
	}//old

	@Override
	public ItemStack decrStackSize(int slot, int amount) 
	{
		
		ItemStack stack = getStackInSlot(slot);
		if(stack != ItemStack.EMPTY)
		{
			if(stack.getCount() > amount)
			{
				stack = stack.splitStack(amount);
				// Don't forget this line or your inventory will not be saved!
				//onInventoryChanged();
				markDirty();
			}
			else
			{
				// this method also calls onInventoryChanged, so we don't need to call it again
				setInventorySlotContents(slot, ItemStack.EMPTY);
			}
		}
		return stack;
	}

	@Override
	public ItemStack removeStackFromSlot(int slot) 
	{
		
		ItemStack stack = getStackInSlot(slot);
		setInventorySlotContents(slot, ItemStack.EMPTY);
		if(slot == slotEnum.LEFT_SLOT.ordinal())
		{
			this.leftItem = ItemStack.EMPTY.getItem();
			
		}
		
		if(slot == slotEnum.RIGHT_SLOT.ordinal())
		{
			this.rightItem = ItemStack.EMPTY.getItem();
			
		}
		
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) 
	{
		
		inventory[slot] = stack;

		if (stack != ItemStack.EMPTY && stack.getCount() > getInventoryStackLimit())
		{
			stack.setCount(this.getInventoryStackLimit());
		}
		
		if(slot == slotEnum.LEFT_SLOT.ordinal() && stack != ItemStack.EMPTY)
		{
			this.leftItem = stack.getItem();
			this.leftItemStack = stack;
			////System.out.println("LeftItem = " + leftItem);
		}
		
		if(slot == slotEnum.RIGHT_SLOT.ordinal() && stack != ItemStack.EMPTY)
		{
			this.rightItem = stack.getItem();
			this.rightItemStack = stack;
			////System.out.println("RightItem = " + rightItem);
		}
		
		if(slot == slotEnum.LEFT_SLOT.ordinal() && stack == ItemStack.EMPTY)
		{
			this.leftItem = ItemStack.EMPTY.getItem();
			////System.out.println("LeftItem = " + leftItem);
		}
		
		if(slot == slotEnum.RIGHT_SLOT.ordinal() && stack == ItemStack.EMPTY)
		{
			this.rightItem = ItemStack.EMPTY.getItem();
			//////System.out.println("RightItem = " + rightItem);
		}
		
		
		
		//this.onInventoryChanged();
		this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		////System.out.println("getInventoryStackLimit");
		// TODO Auto-generated method stub
		return 1;
	}//old

	@Override
	public void markDirty() {
		
		////System.out.println("markDirty");
		
		for (int i = 0; i < getSizeInventory(); ++i)
		{
			if (getStackInSlot(i) != ItemStack.EMPTY && getStackInSlot(i).getCount() == 0) 
			{
				inventory[i] = null;
			}
		}
		
		// This line here does the work:
		
		////System.out.println("Write to " + invSlimeReader.getTagCompound()) ;
		writeToNBT(invSlimeReader.getTagCompound());
		
		
	}


	@Override
	public void openInventory(EntityPlayer player) {
		////System.out.println("openInventory");
		// TODO Auto-generated method stub
		
	}//old

	@Override
	public void closeInventory(EntityPlayer player) {
		////System.out.println("closeInventory");
		// TODO Auto-generated method stub
		
	}//old


	@Override
	public int getField(int id) {
		////System.out.println("getField");
		// TODO Auto-generated method stub
		return 0;
	}//old

	@Override
	public void setField(int id, int value) {
		////System.out.println("setField");
		// TODO Auto-generated method stub
		
	}//old

	@Override
	public int getFieldCount() {
		////System.out.println("getFieldCount");
		// TODO Auto-generated method stub
		return 0;
	}//old

	@Override
	public void clear() {
		////System.out.println("clear");
		// TODO Auto-generated method stub
		
	}//old

	public ItemStack getStackInSlotOnClosing(int slot)
	{
		////System.out.println("getStackInSlotOnClosing");
		ItemStack stack = getStackInSlot(slot);
		setInventorySlotContents(slot, ItemStack.EMPTY);
		return stack;
	}

	
	public String getInventoryName()
	{
		////System.out.println("getInventoryName");
		return name;
	}
	
	
	public boolean hasCustomInventoryName()
	{
		////System.out.println("hasCustomInventoryName");
		return name.length() > 0;
	}
	
	
	public void onInventoryChanged()
	{
		////System.out.println("onInventoryChanged");
		for (int i = 0; i < getSizeInventory(); ++i)
		{
			if (getStackInSlot(i) != ItemStack.EMPTY && getStackInSlot(i).getCount() == 0) 
			{
				inventory[i] = null;
				
				
			}
		}
		
		// This line here does the work:		
		writeToNBT(invSlimeReader.getTagCompound());
		
	}
	
	public void openChest() {}
	
	public void closeChest() {}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack)
	{
		// Don't want to be able to store the inventory item within itself
		// Bad things will happen, like losing your inventory
		// Actually, this needs a custom Slot to work
		////System.out.println("isValidForSlot");
		return !(itemstack.getItem() instanceof Slimalyser);
	}
	

	
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		////System.out.println("readFromNBTTagCompound");
		// Gets the custom taglist we wrote to this compound, if any
		// 1.7.2+ change to compound.getTagList("ItemInventory", Constants.NBT.TAG_COMPOUND);
		
		NBTTagList list = nbt.getTagList("Items", 10);
	    for (int i = 0; i < list.tagCount(); ++i) {
	        NBTTagCompound stackTag = list.getCompoundTagAt(i);
	        int slot = stackTag.getByte("Slot") & 255;
	        this.setInventorySlotContents(slot, new ItemStack(stackTag));
	    }

	    if (nbt.hasKey("CustomName", 8)) {
	        this.setCustomName(nbt.getString("CustomName"));
	    }
	    
	   
	}
	
	/*@Override
	public void writeToNBT(NBTTagCompound nbt) {
	    super.writeToNBT(nbt);

	    NBTTagList list = new NBTTagList();
	    for (int i = 0; i < this.getSizeInventory(); ++i) {
	        if (this.getStackInSlot(i) != null) {
	            NBTTagCompound stackTag = new NBTTagCompound();
	            stackTag.setByte("Slot", (byte) i);
	            this.getStackInSlot(i).writeToNBT(stackTag);
	            list.appendTag(stackTag);
	        }
	    }
	    nbt.setTag("Items", list);

	    if (this.hasCustomName()) {
	        nbt.setString("CustomName", this.getCustomName());
	    }
	}*/
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		////System.out.println("WritetoNBTTagCompound");
		// Create a new NBT Tag List to store itemstacks as NBT Tags
		 NBTTagList list = new NBTTagList();
		    for (int i = 0; i < this.getSizeInventory(); ++i) {
		        if (this.getStackInSlot(i) != ItemStack.EMPTY) {
		            NBTTagCompound stackTag = new NBTTagCompound();
		            stackTag.setByte("Slot", (byte) i);
		            this.getStackInSlot(i).writeToNBT(stackTag);
		            list.appendTag(stackTag);
		            //GuiSlimeReader.getColor(this.getStackInSlot(i));
		            //setColor(this.getStackInSlot(0));
		        }
		    }
		    nbt.setTag("Items", list);

		    if (this.hasCustomName()) {
		        nbt.setString("CustomName", this.getCustomName());
		        return nbt;
		    }
		// Add the TagList to the ItemStack's Tag Compound with the name "ItemInventory"
		nbt.setTag("Items", list);
		
		return nbt;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		////System.out.println("createContainer");
		// TODO Auto-generated method stub
		return null;
	}//old

	@Override
	public String getGuiID() {
		////System.out.println("getGuiID");
		// TODO Auto-generated method stub
		return null;
	}//old

	/*public void setColor(ItemStack itemStack)
	{
		GuiSlimeReader.getColorL(itemStack);
	}*/
	
	public Item getLeftItem()
	{
		////System.out.println("getLeftItem");
		return leftItem;
	}
	
	public Item getRightItem()
	{
		////System.out.println("getRightItem");
		return rightItem;
	}
	
	public ItemStack getLeftItemStack()
	{
		////System.out.println("getLeftItemStack");
		return leftItemStack;
	}
	
	public ItemStack getRightItemStack()
	{
		////System.out.println("getRightItemStack");
		return rightItemStack;
	}
	
	public int getRightItemWave(Item Rightitem)
	{
		////System.out.println("getRightItemWave");
		int length = EnumItemColor.listOfObjects.size();
		EnumItemColor.listOfObjects.clear();
		EnumItemColor.initColorList();
		
		for (int i = 0; i < length; i++) 
		{
			ItemStack listItem = EnumItemColor.listOfObjects.get(i).getItemStack();
		
			if(listItem.getItem() == Rightitem)
			{
				if(listItem.getItem() == SlimeBreederItems.FILLEDSLIMECAPSULE)
				{
					return rightItemStack.getTagCompound().getInteger("Wave");
				}else
			
			return EnumItemColor.listOfObjects.get(i).wavelength;
			}
		
			
		}
		return 575;
	}
	
	public float getRightItemSat(Item Rightitem)
	{
		////System.out.println("getRightItemSat");
		int length = EnumItemColor.listOfObjects.size();
		EnumItemColor.listOfObjects.clear();
		EnumItemColor.initColorList();
		
		for (int i = 0; i < length; i++) 
		{
			ItemStack listItem = EnumItemColor.listOfObjects.get(i).getItemStack();
		
			if(listItem.getItem() == Rightitem)
			{
				if(listItem.getItem() == SlimeBreederItems.FILLEDSLIMECAPSULE)
				{
					return rightItemStack.getTagCompound().getFloat("Sat");
				}else
			
			return EnumItemColor.listOfObjects.get(i).saturation;
			}
		
			
		}
		return 0.5F;
	}
	
	public float getRightItemBright(Item Rightitem)
	{
		////System.out.println("getRightItemBright");
		int length = EnumItemColor.listOfObjects.size();
		EnumItemColor.listOfObjects.clear();
		EnumItemColor.initColorList();
		
		for (int i = 0; i < length; i++) 
		{
			ItemStack listItem = EnumItemColor.listOfObjects.get(i).getItemStack();
		
			if(listItem.getItem() == Rightitem)
			{
				if(listItem.getItem() == SlimeBreederItems.FILLEDSLIMECAPSULE)
				{
					return rightItemStack.getTagCompound().getFloat("Bright");
				}else
			
			return EnumItemColor.listOfObjects.get(i).brightness;
			}
		
			
		}
		return 0.5F;
	}
	
	public int getLeftItemWave(Item Leftitem)
	{
		int length = EnumItemColor.listOfObjects.size();
		EnumItemColor.listOfObjects.clear();
		EnumItemColor.initColorList();
		
		for (int i = 0; i < length; i++) 
		{
			ItemStack listItem = EnumItemColor.listOfObjects.get(i).getItemStack();
		
			if(listItem.getItem() == Leftitem)
			{
				if(listItem.getItem() == SlimeBreederItems.FILLEDSLIMECAPSULE)
				{
					return leftItemStack.getTagCompound().getInteger("Wave");
				}else
			
			return EnumItemColor.listOfObjects.get(i).wavelength;
			}
		
			
		}
		return 575;
	}
	
	public float getLeftItemSat(Item Leftitem)
	{
		int length = EnumItemColor.listOfObjects.size();
		EnumItemColor.listOfObjects.clear();
		EnumItemColor.initColorList();
		
		for (int i = 0; i < length; i++) 
		{
			ItemStack listItem = EnumItemColor.listOfObjects.get(i).getItemStack();
		
			if(listItem.getItem() == Leftitem)
			{
				if(listItem.getItem() == SlimeBreederItems.FILLEDSLIMECAPSULE)
				{
					return leftItemStack.getTagCompound().getFloat("Sat");
				}else
			
			return EnumItemColor.listOfObjects.get(i).saturation;
			}
		
			
		}
		return 0.5F;
	}
	
	public float getLeftItemBright(Item Leftitem)
	{
		int length = EnumItemColor.listOfObjects.size();
		EnumItemColor.listOfObjects.clear();
		EnumItemColor.initColorList();
		
		for (int i = 0; i < length; i++) 
		{
			ItemStack listItem = EnumItemColor.listOfObjects.get(i).getItemStack();
		
			if(listItem.getItem() == Leftitem)
			{
				if(listItem.getItem() == SlimeBreederItems.FILLEDSLIMECAPSULE)
				{
					return leftItemStack.getTagCompound().getFloat("Bright");
				}else
			
			return EnumItemColor.listOfObjects.get(i).brightness;
			}
		
			
		}
		return 0.5F;
	}
	
	
	public Item getRightItemColor()
	{
		return rightItem;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
