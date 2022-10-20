package com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeCrystal;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Maps;
import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeCrystal;
import com.itchymichi.slimebreeder.models.ModelSlime2;
import com.itchymichi.slimebreeder.render.entity.RenderEntityArtificalSlimeCrystal;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.FogMode;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerSlimeAccessory implements LayerRenderer<EntityArtificalSlimeCrystal>
{

//private static final ResourceLocation slimeTexture = new ResourceLocation(SlimeBreeder.MODID + ":textures/models/Slime2.png");

private final RenderEntityArtificalSlimeCrystal slimeRenderer;
private final ModelBase slimeModel = new ModelSlime2(0);

double translatedDx1 = 0;
double translatedDy1 = 0;
int rotation1 = 0;

double translatedDx2 = 0;
double translatedDy2 = 0;
int rotation2 = 0;

double translatedDx3 = 0;
double translatedDy3 = 0;
int rotation3 = 0;

double translatedDx4 = 0;
double translatedDy4 = 0;
int rotation4 = 0;

double translatedDx5 = 0;
double translatedDy5 = 0;
int rotation5 = 0;





public LayerSlimeAccessory(RenderEntityArtificalSlimeCrystal renderEntityArtificalSlimeCrystal)
{
this.slimeRenderer = renderEntityArtificalSlimeCrystal;
}



public void doRenderLayer(EntityArtificalSlimeCrystal slime2, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
{
if (!slime2.isInvisible())
{
//this.slimeRenderer.bindTexture(slimeTexture);


float finalred = 1F;
float finalgreen = 1F;
float finalblue = 1F;

	GlStateManager.color(finalred, finalgreen, finalblue, 1.0F);//.getDataWatcher().getWatchableObjectFloat(20),
	GlStateManager.enableNormalize();
	GlStateManager.enableBlend();
	GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA); // 771

	//renderItemSlime2(slime2);
	if(slime2.getIsPlayerNear()) {
	renderSelection(slime2);};
	renderAccesory(slime2);

	GlStateManager.disableBlend();
	GlStateManager.disableNormalize();
	






}
}




public void renderSelection(EntityArtificalSlimeCrystal slime2)
{
	
	
	
	float red = 1F;
	float green = 1F;
	float blue = 1F;
	
	float alpha = 1F;
	
	double posX = 0;
	double posY = 0;
	double posZ = 0;
	
	double minX = 0;
	double minZ = 0;
	double minY = 0;
	
	double maxX = 0;
	double maxZ = 0;
	double maxY = 0;
	
	//ResourceLocation slimeLiquid = new ResourceLocation("minecraft" + ":textures/blocks/water_still.png");
	
	//ResourceLocation slimeLiquid = new ResourceLocation("minecraft" + ":textures/blocks/water_still.png");
	//ResourceLocation slimeLiquid = new ResourceLocation(SlimeBreeder.MODID + ":textures/blocks/fluidliquidcrystal.png");
	ResourceLocation slimeMolten = new ResourceLocation(SlimeBreeder.MODID + ":textures/blocks/fluids/moltenslime.png");
	ResourceLocation slimeLiquid = new ResourceLocation(SlimeBreeder.MODID + ":textures/blocks/fluids/liquidslime.png");
	

			int time = slime2.ticksExisted;
			float amp = 0.1F;
			double angular = 0.1D;
			//double angular = 0.03D;
			

			alpha = (((float) (Math.sin((double)time * angular) * (127) + 128)/255))*0.30F;
			
		
			
		if(slime2.getBackObs() && slime2.getACBack().getItem() == Items.SLIME_BALL){
			posX = 0.65D;
			posY = 2.8D;
			posZ = 0.71D;
			
			minX = -0.2D;
			minZ = -0.2D;
			minY = 0.1D;
			
			maxX = -1.1D;
			maxZ = -0.2D;
			maxY = -0.75D;
		}
		
		if(slime2.getFrontObs() && slime2.getACFront().getItem() == Items.SLIME_BALL){
			posX = 0.65D;
			posY = 2.8D;
			posZ = -0.31D;
			
			minX = -0.2D;
			minZ = -0.2D;
			minY = 0.1D;
			
			maxX = -1.1D;
			maxZ = -0.2D;
			maxY = -0.75D;
		}
		
		if(slime2.getLSideObs() && slime2.getACLSide().getItem() == Items.SLIME_BALL){
			
			
			posX = 0.72D;
			posY = 2.8D;
			posZ = 0.65D;
			
			minX = -0.2D;
			minZ = -1.1D;
			minY = 0.1D;
			
			maxX = -0.2D;
			maxZ = -0.2D;
			maxY = -0.75D;
			
		}
		
		if(slime2.getRSideObs() && slime2.getACRSide().getItem() == Items.SLIME_BALL){
			posX = -0.31D;
			posY = 2.8D;
			posZ = 0.65D;
			
			minX = -0.2D;
			minZ = -1.1D;
			minY = 0.1D;
			
			maxX = 0.2D;
			maxZ = -0.2D;
			maxY = -0.75D;
			
			/*posX = -0.31D;
			posY = 2.8D;
			posZ = 0.65D;
			
			minX = -0.2D;
			minZ = -1.1D;
			minY = 0.1D;
			
			maxX = -0.2D;
			maxZ = -0.2D;
			maxY = -0.75D;*/
			
			
		}
		
		if(slime2.getTopObs() && slime2.getACTop().getItem() == Items.SLIME_BALL ){
			posX = 0.65D;
			posY = 1.89D;
			posZ = 0.65D;
			
			minX = -0.2D;
			minZ = -1.1D;
			minY = 0.1D;
			
			maxX = -1.1D;
			maxZ = -0.2D;
			maxY = 0.1D;
			//System.out.println("Top Obs");
		}
		
		
		//FMLClientHandler.instance().getClient().getTextureManager().bindTexture(slimeLiquid);
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
	    
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
	
		Tessellator t = Tessellator.getInstance();
		BufferBuilder b = t.getBuffer();
		b.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		//Minecraft.getMinecraft().getTextureManager().bindTexture(slimeTexture);
		
		
		//System.out.println("Health = " + slime2.getHealth() + " / " + slime2.getMaxHealth());
		
		drawMask2(b, minX, 0.1D, minZ, maxX, maxY, maxZ, red, green, blue, alpha);
		
		
		GL11.glScaled(0.50, 0.50, 0.50);

	    //GL11.glTranslated(-0.35D, 2.8D, 0.65D);
	    
	    GL11.glTranslated(posX, posY, posZ);
	    
	    
	    //GL11.glRotated(180, 0, 0, 0);
	    
	    t.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
		GL11.glEnable(GL11.GL_LIGHTING);
		GlStateManager.enableTexture2D();
		GL11.glPopMatrix();

		//System.out.println(alpha);
			/*if(alpha < 0.05F){
				slime2.setFrontObs(false);
				slime2.setBackObs(false);
				slime2.setLSideObs(false);
				slime2.setRSideObs(false);
				slime2.setTopObs(false);
			alpha = 1F;
		}*/
		
}

public void renderAccesory(EntityArtificalSlimeCrystal slime2)
{
	
	//GlStateManager.enableBlend();
   // GlStateManager.disableTexture2D();
   // GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
    
   // GL11.glShadeModel(GL11.GL_SMOOTH);
  //  GL11.glDisable(GL11.GL_LIGHTING);
	//GL11.glPushMatrix();
	
		//Tessellator t = Tessellator.getInstance();
		//BufferBuilder b = t.getBuffer();
		//b.begin(7, DefaultVertexFormats.POSITION_COLOR);

		//drawMask(b, 0D, 1D, 0D, -3D, -3D, -3D, 0.5F, 1F, 0F, 1F);
		//t.draw();
	
	ItemStack acFront = new ItemStack(Items.SLIME_BALL);
    ItemStack acBack = new ItemStack(Items.SLIME_BALL);
    ItemStack acLSide = new ItemStack(Items.SLIME_BALL);
    ItemStack acRSide = new ItemStack(Items.SLIME_BALL);
    ItemStack acTop = new ItemStack(Items.SLIME_BALL);
	
	
	if(slime2.getEntityWorld().isRemote){

    acFront = slime2.getServerACFront();
    acBack = slime2.getServerACBack();
    acLSide = slime2.getServerACLSide();
    acRSide = slime2.getServerACRSide();
    acTop = slime2.getServerACTop();
    
    //System.out.println(acFront);
	}
    
    if(acBack.getItem() != Items.SLIME_BALL){
    	
		/*GlStateManager.enableBlend();
		//GlStateManager.disableTexture2D();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
	    
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
    	
    	
    	GL11.glScaled(0.50, 0.50, 0.50);
        //GL11.glScaled(1.00, 1.00, 1.00);
        GL11.glTranslated(0D, 2.5D, 1.01D);
        GL11.glRotated(180, 0, 0, 1);*/
        
    	///////////
    	
    	/*GL11.glPushMatrix();
        GlStateManager.enableAlpha();
        //GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    	
    	
    	GL11.glScaled(0.50, 0.50, 0.50);
        //GL11.glScaled(1.00, 1.00, 1.00);
        GL11.glTranslated(0D, 2.5D, 1.01D);
        GL11.glRotated(180, 0, 0, 1);*/
    	
    	GL11.glPushMatrix();
    	GL11.glScaled(0.50, 0.50, 0.50);
    	GL11.glTranslated(0D, 2.5D, 1.01D);
        GL11.glRotated(180, 0, 0, 1);
        
        
        if(slime2.getBackObs() && slime2.getIsPlayerNear()){
        	
        	int time = slime2.ticksExisted;
			float amp = 5F;
			double angular = 0.1D;
			//double angular = 0.03D;
			
			//System.out.println("Front OBS");
			


			float alpha = (((float) (Math.sin((double)time-(time-1) * angular) * (127) + 128)/255))*0.75F;
			
			
        	
			GL11.glScaled(1-(alpha), 1-(alpha), 1-(alpha));
			
        }

        
        
        //RenderHelper.enableStandardItemLighting();
        Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, acBack, ItemCameraTransforms.TransformType.NONE);
        //RenderHelper.disableStandardItemLighting();
        
        
        
       
        
        //GlStateManager.enableTexture2D();
	    //GlStateManager.disableBlend();
	    //GlStateManager.disableAlpha();
	    //GL11.glEnable(GL11.GL_LIGHTING);
	    //GL11.glPopMatrix();
  		
  		/*GlStateManager.disableBlend();
  		GL11.glEnable(GL11.GL_LIGHTING);
  		
  		GL11.glPopMatrix();*/
	    
        GL11.glPopMatrix();	
    
    }
    
    if(acFront.getItem() != Items.SLIME_BALL){
    	
    	GL11.glPushMatrix();
    	
    	GL11.glScaled(0.50, 0.50, 0.50);
        //GL11.glScaled(1.00, 1.00, 1.00);
        GL11.glTranslated(0D, 2.5D, -1.01D);
        GL11.glRotated(180, 0, 0, 0);
        
		if(slime2.getFrontObs() && slime2.getIsPlayerNear()){
		        	
		        	int time = slime2.ticksExisted;
					float amp = 5F;
					double angular = 0.1D;
					//double angular = 0.03D;
					
					//System.out.println("Front OBS");
					
		
		
					float alpha = (((float) (Math.sin((double)time-(time-1) * angular) * (127) + 128)/255))*0.75F;
					
					
		        	
					GL11.glScaled(1-(alpha), 1-(alpha), 1-(alpha));
					
		        }
        
        //RenderHelper.enableStandardItemLighting();
        Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, acFront, ItemCameraTransforms.TransformType.NONE);
        //RenderHelper.disableStandardItemLighting();
        

  		GL11.glPopMatrix();	
        }
    
    if(acLSide.getItem() != Items.SLIME_BALL){
    	

		GL11.glPushMatrix();
    	
    	GL11.glScaled(0.50, 0.50, 0.50);
        //GL11.glScaled(1.00, 1.00, 1.00);
        GL11.glTranslated(1.01D, 2.5D, 0.0D);
        GL11.glRotated(90, 0, 1, 0);
        GL11.glRotated(180, 0, 0, 1);
        
if(slime2.getLSideObs() && slime2.getIsPlayerNear()){
        	
        	int time = slime2.ticksExisted;
			float amp = 5F;
			double angular = 0.1D;
			//double angular = 0.03D;
			
			//System.out.println("Front OBS");
			


			float alpha = (((float) (Math.sin((double)time-(time-1) * angular) * (127) + 128)/255))*0.75F;
			
			
        	
			GL11.glScaled(1-(alpha), 1-(alpha), 1-(alpha));
			
        }
        
        //RenderHelper.enableStandardItemLighting();
        Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, acLSide, ItemCameraTransforms.TransformType.NONE);
        //RenderHelper.disableStandardItemLighting();
        

  		GL11.glPopMatrix();	
        }
    
    if(acRSide.getItem() != Items.SLIME_BALL){
    	

		GL11.glPushMatrix();
    	
    	GL11.glScaled(0.50, 0.50, 0.50);
        //GL11.glScaled(1.00, 1.00, 1.00);
        GL11.glTranslated(-1.01D, 2.5D, 0.0D);
        GL11.glRotated(270, 0, 1, 0);
        GL11.glRotated(180, 0, 0, 1);
        
					if(slime2.getRSideObs() && slime2.getIsPlayerNear()){
					        	
					        	int time = slime2.ticksExisted;
								float amp = 5F;
								double angular = 0.1D;
								//double angular = 0.03D;
								
								//System.out.println("Front OBS");
								
					
					
								float alpha = (((float) (Math.sin((double)time-(time-1) * angular) * (127) + 128)/255))*0.75F;
								
								
					        	
								GL11.glScaled(1-(alpha), 1-(alpha), 1-(alpha));
								
					        }
        
        //RenderHelper.enableStandardItemLighting();
        Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, acRSide, ItemCameraTransforms.TransformType.NONE);
        //RenderHelper.disableStandardItemLighting();
        
        
        
      

		GL11.glPopMatrix();
        }
    
    if(acTop.getItem() != Items.SLIME_BALL){
    	

		GL11.glPushMatrix();
    	
    	GL11.glScaled(0.50, 0.50, 0.50);
       // GL11.glScaled(1.00, 1.00, 1.00);
        GL11.glTranslated(0.0D, 1.5D, 0.0D);
        GL11.glRotated(180, 0, 0, 0);
        
if(slime2.getTopObs() && slime2.getIsPlayerNear()){
        	
        	int time = slime2.ticksExisted;
			float amp = 5F;
			double angular = 0.1D;
			//double angular = 0.03D;
			
			//System.out.println("Front OBS");
			


			float alpha = (((float) (Math.sin((double)time-(time-1) * angular) * (127) + 128)/255))*0.75F;
			
			
        	
			GL11.glScaled(1-(alpha), 1-(alpha), 1-(alpha));
			
        }
        
        //RenderHelper.enableStandardItemLighting();
        Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, acTop, ItemCameraTransforms.TransformType.NONE);
        //RenderHelper.disableStandardItemLighting();
        
        
  		GL11.glPopMatrix();
        }
    
	

    
    //Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, new ItemStack(Item.getItemFromBlock(fluidBlock)), ItemCameraTransforms.TransformType.NONE);

    //Minecraft.getMinecraft().effectRenderer.emitParticleAtEntity(slime2, EnumParticleTypes.FLAME);
    //Minecraft.getMinecraft().rend
    

		//GlStateManager.enableTexture2D();
		//GlStateManager.disableBlend();
		//GL11.glEnable(GL11.GL_LIGHTING);
	//GL11.glPopMatrix();
}

public static void drawMask(BufferBuilder b, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha)
{
	//up
	b.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
	b.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
	//b.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();

	//down
	b.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
	b.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
	//b.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
	
	//north
	b.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
	b.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
	//b.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
	
	//south
	b.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
	b.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
	//b.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
	
	//east
	b.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
	//b.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
	
	//west
	b.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
	b.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
	b.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
	b.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
	
	//b.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
}

public static void drawMask2(BufferBuilder b, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha)
{
	
	double u = 1;
	double v = 1;
	
	//up
	b.pos(minX, minY, minZ).tex(0, 0).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, minY, minZ).tex(0, v).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, minY, maxZ).tex(u, v).color(red, green, blue, alpha).endVertex();
	b.pos(minX, minY, maxZ).tex(u, 0).color(red, green, blue, alpha).endVertex();
	//b.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();

	//down
	b.pos(minX, maxY, minZ).tex(0, 0).color(red, green, blue, alpha).endVertex();
	b.pos(minX, maxY, maxZ).tex(0, v).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, maxY, maxZ).tex(u, v).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, maxY, minZ).tex(u, 0).color(red, green, blue, alpha).endVertex();
	//b.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
	
	//north
	b.pos(minX, minY, minZ).tex(0, 0).color(red, green, blue, alpha).endVertex();
	b.pos(minX, maxY, minZ).tex(0, v).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, maxY, minZ).tex(u, v).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, minY, minZ).tex(u, 0).color(red, green, blue, alpha).endVertex();
	//b.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
	
	//south
	b.pos(minX, minY, maxZ).tex(0, 0).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, minY, maxZ).tex(u, 0).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, maxY, maxZ).tex(u, v).color(red, green, blue, alpha).endVertex();
	b.pos(minX, maxY, maxZ).tex(0, v).color(red, green, blue, alpha).endVertex();
	//b.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
	
	//east
	b.pos(maxX, minY, minZ).tex(0, 0).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, maxY, minZ).tex(0, v).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, maxY, maxZ).tex(u, v).color(red, green, blue, alpha).endVertex();
	b.pos(maxX, minY, maxZ).tex(u, 0).color(red, green, blue, alpha).endVertex();
	//b.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
	
	//west
	b.pos(minX, minY, minZ).tex(0, 0).color(red, green, blue, alpha).endVertex();
	b.pos(minX, minY, maxZ).tex(u, 0).color(red, green, blue, alpha).endVertex();
	b.pos(minX, maxY, maxZ).tex(u, v).color(red, green, blue, alpha).endVertex();
	b.pos(minX, maxY, minZ).tex(0, v).color(red, green, blue, alpha).endVertex();
	
	//b.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
}




public boolean shouldCombineTextures()
{
return true;
}
}