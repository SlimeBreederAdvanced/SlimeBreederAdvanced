package com.itchymichi.slimebreeder.items;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.handlers.SBASoundHandler;
import com.itchymichi.slimebreeder.utility.CommonProxy;

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

public class ClearSlimeCrystal2 extends Item
{

	private String name = "";
	Item setCreativeTab;
	
	public Minecraft mc;
	private static ResourceLocation purpleSlime;
    
	
	
	
	public ClearSlimeCrystal2() 
	{
		super();
		
		purpleSlime = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/purpleslime.png");
		//name = uname;
		//this.setRegistryName(name);
		//GameRegistry.register(this);
		
		//GameRegistry.registerItem(this, uname);
		//name = uname;
		//setUnlocalizedName(SlimeBreeder.MODID + "_" + name);
		//this.setCreativeTab(CommonProxy.slimetab);
		setMaxStackSize(1);
	}
	
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		stack.getSubCompound(SlimeBreeder.MODID);
		
		

	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		NBTTagCompound entity = target.serializeNBT();
		
		UUID id = target.getPersistentID();
		
		EntityColorSlimeBase slime = (EntityColorSlimeBase) target;
		
		System.out.println("Hunger = " + slime.getHunger() + " Domesticated = " + slime.getDomestication());
		
		stack.getTagCompound().setUniqueId("Entity", id);
		
		
		
		
		/*((EntitySlime2) target).setGlowing(false);
		
		((EntitySlime2) target).setHSB(300);;*/
		
		//target.setDead();
		return true;
		
		
		
    }
	
	
	public boolean onEntityItemUpdate(net.minecraft.entity.item.EntityItem entityItem)
    {
		World worldObj = entityItem.getEntityWorld();
		Random rand = new Random();
		
		/*if(worldObj.isRemote)
		{
			//System.out.println(worldObj.isRemote);
			float width = 0.5F;
			float height = 0.5F;
			//System.out.println(worldObj.isRemote);
			double d0 = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			
            worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, entityItem.posX +  (double)(rand.nextFloat() * width * 2.0F) - (double)width,
					entityItem.posY+ 0.5D + (double)(rand.nextFloat() * height),
					entityItem.posZ+ + (double)(rand.nextFloat() * width * 2.0F) - (double)width, 1.0D, 0.5D, 0.5D, new int[]{3,0});

		}*/
		
		if(!worldObj.isRemote) 
    	{
			float width = 0.5F;
			float height = 0.5F;
			//System.out.println(worldObj.isRemote);
			double d0 = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			if(rand.nextFloat() > 0.7F){
    	WorldServer worldserver = (WorldServer) worldObj;
    	worldserver.spawnParticle(EnumParticleTypes.REDSTONE, false,
    								entityItem.posX +  (double)(rand.nextFloat() * width * 2.0F) - (double)width,
    								entityItem.posY+ 0.5D + (double)(rand.nextFloat() * height),
    								entityItem.posZ+ + (double)(rand.nextFloat() * width * 2.0F) - (double)width,
    								1,
    								0.0D, 0.0D, 0.0D,
    								0.5D, new int[]{0, 5, 0});
    	//worldserver.spawn
			}}
    	//worldserver.spawnParticle(EnumParticleTypes.HEART, entityItem.posX + (double)(rand.nextFloat() * width * 2.0F) - (double)width, entityItem.posY + 0.5D + (double)(rand.nextFloat() * height), entityItem.posZ + (double)(rand.nextFloat() * width * 2.0F) - (double)width, d0, d1, d2, new int[0]);
    	
    	
		
		
        return false;
    }
	
	@Override
	public ItemStack onItemUseFinish(ItemStack itemStackIn, World worldIn, EntityLivingBase entityLiving)
    {
		
		System.out.println(itemStackIn.getTagCompound().getUniqueId("Entity").toString());

		if(itemStackIn.hasTagCompound() && itemStackIn.getTagCompound().getUniqueId("Entity") != null && !(worldIn.isRemote) )
		{
			System.out.println("Checked");
			//NBTTagCompound entity = (NBTTagCompound) itemStackIn.getTagCompound().getTag("Entity");
			//System.out.println(entity.toString());
			//System.out.println(entity.getInteger("Amount"));
			
			for (Entity e : worldIn.getLoadedEntityList())
			{
				UUID stored = itemStackIn.getTagCompound().getUniqueId("Entity");
				
				//System.out.println(e.getPersistentID().toString() + " equals " + itemStackIn.getTagCompound().getUniqueId("Entity"));
				if (e.getPersistentID().equals(stored))
				{
					System.out.println("Found");
					e.setFire(60);
					
				}
				
			}
			
			//Entity target = 
			
			//EntityList.getEntityID(entityIn)
			
			//worldIn.getEntityByID(entity.getInteger("id")).setFire(60);
			
			//EntitySlime2 entitySpawn = (EntitySlime2) EntityList.createEntityFromNBT(entity, worldIn);
			
			//entitySpawn.setFire(60);
			
			/*Entity entitySlime = EntityList.createEntityFromNBT(entity.copy(), worldIn);
			
			entitySlime.setLocationAndAngles(entitySpawn.posX, entitySpawn.posY, entitySpawn.posZ, 0.0F, 0.0F);
			
			//entitySpawn.setLocationAndAngles(playerIn.posX, playerIn.posY, playerIn.posZ, 0.0F, 0.0F);
			
			worldIn.spawnEntityInWorld(entitySlime);*/
			
			
		}
		
		return itemStackIn;
		
    }
	
	
	/*@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		
		System.out.println(itemStackIn.getTagCompound().getUniqueId("Entity").toString());

		if(itemStackIn.hasTagCompound() && itemStackIn.getTagCompound().getUniqueId("Entity") != null && !(worldIn.isRemote) )
		{
			System.out.println("Checked");
			//NBTTagCompound entity = (NBTTagCompound) itemStackIn.getTagCompound().getTag("Entity");
			//System.out.println(entity.toString());
			//System.out.println(entity.getInteger("Amount"));
			
			for (Entity e : worldIn.getLoadedEntityList())
			{
				UUID stored = itemStackIn.getTagCompound().getUniqueId("Entity");
				
				//System.out.println(e.getPersistentID().toString() + " equals " + itemStackIn.getTagCompound().getUniqueId("Entity"));
				if (e.getPersistentID().equals(stored))
				{
					System.out.println("Found");
					e.setFire(60);
					
				}
				
			}
			
			//Entity target = 
			
			//EntityList.getEntityID(entityIn)
			
			//worldIn.getEntityByID(entity.getInteger("id")).setFire(60);
			
			//EntitySlime2 entitySpawn = (EntitySlime2) EntityList.createEntityFromNBT(entity, worldIn);
			
			//entitySpawn.setFire(60);
			
			/*Entity entitySlime = EntityList.createEntityFromNBT(entity.copy(), worldIn);
			
			entitySlime.setLocationAndAngles(entitySpawn.posX, entitySpawn.posY, entitySpawn.posZ, 0.0F, 0.0F);
			
			//entitySpawn.setLocationAndAngles(playerIn.posX, playerIn.posY, playerIn.posZ, 0.0F, 0.0F);
			
			worldIn.spawnEntityInWorld(entitySlime);*/
			
			
		/*}
		
		return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		
    }*/

public void drawSlime(EntityPlayer thePlayer)
{
System.out.println("Drawn");
	double doubleX = thePlayer.posX - 0.5;
	double doubleY = thePlayer.posY + 0.1;
	double doubleZ = thePlayer.posZ - 0.5;

	
	GlStateManager.pushMatrix();
	GlStateManager.depthFunc(519);
	GL11.glTranslated(-doubleX, -doubleY, -doubleZ);
	GL11.glColor3ub((byte)255,(byte)0,(byte)0);
	float mx = 9;
	float my = 9;
	float mz = 9;
	GlStateManager.glBegin(GL11.GL_LINES);
	GlStateManager.glVertex3f(mx+0.4f,my,mz+0.4f);
	GlStateManager.glVertex3f(mx-0.4f,my,mz-0.4f);
	GlStateManager.glVertex3f(mx+0.4f,my,mz-0.4f);
	GlStateManager.glVertex3f(mx-0.4f,my,mz+0.4f);
	GlStateManager.glEnd();
	GlStateManager.depthFunc(515);
	GlStateManager.popMatrix();

	/*float scale = 0.25F;
	
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scale, scale, scale);
	mc.getTextureManager().bindTexture(purpleSlime);
	
	
	

	
	drawTexturedModalRect(Math.round((int)LeftguiLeft/scale)+800, Math.round((int)guiTop/scale)+180, 0, 0, 255, 255);
	GL11.glPopMatrix();*/
	
	/*  GL11.glScaled(100.0, 100.0, 100.0);
    GL11.glTranslated(1.5D, 1.75D, 0.0D);
    GL11.glRotatef(200, 0.0F, 0.0F, 1.0F);
    //itemRender.renderItemIntoGUI(new ItemStack(Items.APPLE), (int)LeftguiLeft+55 , (int)guiTop+150);
    //GuiInventory.drawEntityOnScreen((int)LeftguiLeft+55 , (int)guiTop+150, 100, Mouse.getEventX(), Mouse.getEventY(), playerBreeder);
    RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
    rendermanager.setPlayerViewY(180.0F);
    rendermanager.setRenderShadow(false);
    rendermanager.renderEntityStatic(slime, 1.0F, false);
    rendermanager.setRenderShadow(true);*/
	
}



	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		int Lshift = Keyboard.KEY_LSHIFT;
		
		if(Keyboard.isKeyDown(Lshift) && stack.hasTagCompound() && stack.getTagCompound().getTag("Entity") != null )
		{
			//System.out.println(stack.getTagCompound().getTag("Entity"));
			tooltip.add(stack.getTagCompound().getUniqueId("Entity").toString());
			//tooltip.add(stack.getTagCompound().getTag("Entity"));
			//System.out.println("Health = " + stack.getTagCompound().getDouble("Health"));
		}
		else
		{
			tooltip.add("Hold LEFT SHIFT for more info");
		}
	 
    }
	

	public String getName()
	{
		return name;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
