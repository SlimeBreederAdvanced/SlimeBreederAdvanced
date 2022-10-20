package com.itchymichi.slimebreeder.items;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.handlers.SBASoundHandler;
import com.itchymichi.slimebreeder.utility.CommonProxy;
import com.itchymichi.slimebreeder.utility.IHasModel;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class UnchargedClearSlimeCrystal extends Item implements IHasModel
{


	private String name = "";
	Item setCreativeTab;
	
	public UnchargedClearSlimeCrystal(String name, Boolean inCreativeTab, @Nullable CreativeTabs tab, int size){
		super();
		setUnlocalizedName(SlimeBreeder.MODID + "_"+name);
		setRegistryName(name);
		SlimeBreederItems.ITEMS.add(this);
		
		if(inCreativeTab)
		{
			setCreativeTab(tab);
		}
		
		setMaxStackSize(size);
		setMaxDamage(100);
			
	}
	
	@Override
	public void registerModels() 
	{
		
		SlimeBreeder.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
	
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1; // return any value greater than zero
	}
	
	

	@Override
	public boolean getTop() {
		// TODO Auto-generated method stub
		return false;
	}
	

	
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		
		//System.out.println("no tag");
		if(stack.getTagCompound() == null){
			stack.getOrCreateSubCompound("chargable");
		}
		
	if(stack.getTagCompound() != null){

		
		//System.out.println("running");
		if(stack.hasTagCompound()) {
			//System.out.println("has tag");
			if(stack.getTagCompound().hasKey("chargable")) {
				//System.out.println("chargable");
				
				NBTTagCompound nbt = stack.getTagCompound();
				if(!nbt.hasKey("charge") && !nbt.hasKey("chargecolor")&& !nbt.hasKey("isCharging")) {
				nbt.setInteger("charge",0);
				nbt.setString("chargecolor", "clear");
				nbt.setBoolean("isCharging", false);
				}
				if(nbt.hasKey("charge") && nbt.hasKey("chargecolor") && !stack.isItemDamaged()) {
					
					//System.out.println(stack.isItemStackDamageable());
					
					stack.setItemDamage(99);
					//nbt.setString("chargecolor", "clear");
					
					
				}
				if(nbt.hasKey("isCharging")) {
						if(stack.isItemDamaged() && nbt.getBoolean("isCharging")) {
							stack.setItemDamage((int) ((99- (nbt.getInteger("charge")))));
							nbt.setBoolean("isCharging", false);
							/*if(stack.getItemDamage()>=25) {
								stack.setItemDamage(24);
							}*/
						}
				}
				if(nbt.hasKey("charge") && nbt.hasKey("chargecolor")){
					
					stack.setStackDisplayName(nbt.getString("chargecolor") + "-Tuned Crystal");
					//System.out.println(nbt.getString("chargecolor"));
					//System.out.println(nbt.getInteger("charge"));
				}
				
			}

		}
	}
	
	}	
	
	
	
	
	
}
