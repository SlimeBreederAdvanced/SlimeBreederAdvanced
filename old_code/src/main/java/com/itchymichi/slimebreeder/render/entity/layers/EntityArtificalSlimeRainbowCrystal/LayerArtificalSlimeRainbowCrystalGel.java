package com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeRainbowCrystal;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Maps;
import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeBase;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeRainbowCrystal;
import com.itchymichi.slimebreeder.entity.monster.EntityCustomSlimeBase;
import com.itchymichi.slimebreeder.lists.EnumRainbowColor;
import com.itchymichi.slimebreeder.lists.EnumTune;
import com.itchymichi.slimebreeder.models.ModelArtificalRainbowSlime;
import com.itchymichi.slimebreeder.models.ModelCustomSlimeBase;
import com.itchymichi.slimebreeder.models.ModelSlime2;
import com.itchymichi.slimebreeder.render.entity.RenderEntityArtificalSlimeRainbowCrystal;
import com.itchymichi.slimebreeder.render.entity.RenderEntityCustomSlimeBase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager.FogMode;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityHorse;
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
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class LayerArtificalSlimeRainbowCrystalGel implements LayerRenderer<EntityArtificalSlimeRainbowCrystal>
{
	
	private final RenderEntityArtificalSlimeRainbowCrystal customslimebaseRenderer;
	private final ModelBase customslimebaseModel = new ModelArtificalRainbowSlime(0);
	private static final ResourceLocation customslimebaseTexture = new ResourceLocation(SlimeBreeder.MODID + ":textures/models/artificalslime.png");

	
	public LayerArtificalSlimeRainbowCrystalGel(RenderEntityArtificalSlimeRainbowCrystal slimeRendererIn)
    {
        this.customslimebaseRenderer = slimeRendererIn;
    }
	
	
	public void doRenderLayer(EntityArtificalSlimeRainbowCrystal entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) 
	{
		
		//Entity compressed = EntityList.createEntityFromNBT(entitylivingbaseIn.getCompressedType(), entitylivingbaseIn.getEntityWorld());
		Entity compressed = EntityList.createEntityByIDFromName(new ResourceLocation(entitylivingbaseIn.getCompressedType()), entitylivingbaseIn.getEntityWorld());

		Render living = (Render)Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(compressed.getClass());
		RenderLivingBase livingBase = ((RenderLivingBase)living);
		
		ResourceLocation texture = new ResourceLocation(EntityRegistry.getEntry(compressed.getClass()).getRegistryName().getResourceDomain() + ":textures/entity/"+ compressed.getName()+".png");
		ResourceLocation texture2 = new ResourceLocation(EntityRegistry.getEntry(compressed.getClass()).getRegistryName().getResourceDomain() + ":textures/entity/"+ compressed.getName() + "/"+ compressed.getName()+".png");
		
		ResourceLocation compressedTexture;
		
		Entity slime = new EntitySlime(entitylivingbaseIn.getEntityWorld());
		
		/*int R = (int)(Math.random()*256);
		int G = (int)(Math.random()*256);
		int B= (int)(Math.random()*256);
		Color color = new Color(R, G, B); //random color, but can be bright or dull

		//to get rainbow, pastel colors
		Random random = new Random();
		final float hue = random.nextFloat();
		final float saturation = 0.9f;//1.0 for brilliant, 0.0 for dull
		final float luminance = 1.0f; //1.0 for brighter, 0.0 for black
		color = Color.getHSBColor(hue, saturation, luminance);*/

		                          
		
		if(Minecraft.getMinecraft().getTextureManager().getTexture(texture) == null) {
	    	   //System.out.println(texture);
	    	  compressedTexture = texture2;
	    	  //System.out.println("Alternate texture");
	       }else {
	    	  //System.out.println(texture2);
	    	  compressedTexture = texture;
	       }
		
		this.customslimebaseRenderer.bindTexture(compressedTexture);
		
		
		if(compressed.getClass() == slime.getClass()) {
			this.customslimebaseRenderer.bindTexture(customslimebaseTexture);
		}
		
		
        if (!entitylivingbaseIn.isInvisible() && compressed.getClass() == slime.getClass())
        {

			EnumRainbowColor.listOfObjects.clear();
			EnumRainbowColor.initColorList();
        	
        	
        	//////////////////////////////////////////////////
        	
        	GlStateManager.pushMatrix();
        	//System.out.println(customslimebaseTexture);
            GlStateManager.color(((float)(EnumRainbowColor.listOfObjects.get(entitylivingbaseIn.getTimer()).red)/255),((float)(EnumRainbowColor.listOfObjects.get(entitylivingbaseIn.getTimer()).blue)/255),((float)(EnumRainbowColor.listOfObjects.get(entitylivingbaseIn.getTimer()).green)/255));
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.customslimebaseModel.setModelAttributes(this.customslimebaseRenderer.getMainModel());
            this.customslimebaseModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            
            livingBase.getMainModel().setModelAttributes(livingBase.getMainModel());
            livingBase.getMainModel().render(compressed, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
            GlStateManager.popMatrix();
	    }else{
	    	int i = 1;
        	
	    	
	    		GlStateManager.pushMatrix();
	        	//System.out.println(customslimebaseTexture);

	            GlStateManager.color(1.0F,1.0F,1.0F);

	            
	            GlStateManager.enableNormalize();
	            GlStateManager.enableBlend();
	            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	            //this.customslimebaseModel.setModelAttributes(this.customslimebaseRenderer.getMainModel());
	            //this.customslimebaseModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	            
	            livingBase.getMainModel().setModelAttributes(livingBase.getMainModel());
	            livingBase.getMainModel().render(compressed, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	            
	            GlStateManager.disableBlend();
	            GlStateManager.disableNormalize();
	            GlStateManager.popMatrix();
	            
	            
	    	}
	    	
	    	
	    	
	    }
	
	
	
	
	

	
	/*public void doRenderLayer(EntityArtificalSlimeRainbowCrystal entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale){
		
		Entity compressed = EntityList.createEntityFromNBT(entitylivingbaseIn.getCompressedType(), entitylivingbaseIn.getEntityWorld());
		Render living = (Render)Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(compressed.getClass());
		RenderLivingBase livingBase = ((RenderLivingBase)living);
		
		ResourceLocation texture = new ResourceLocation(EntityRegistry.getEntry(compressed.getClass()).getRegistryName().getResourceDomain() + ":textures/entity/"+ compressed.getName()+".png");
		ResourceLocation texture2 = new ResourceLocation(EntityRegistry.getEntry(compressed.getClass()).getRegistryName().getResourceDomain() + ":textures/entity/"+ compressed.getName() + "/"+ compressed.getName()+".png");
		
		ResourceLocation compressedTexture;
		
		if(Minecraft.getMinecraft().getTextureManager().getTexture(texture) == null) {
	    	   //System.out.println(texture);
	    	  compressedTexture = texture2;
	    	  //System.out.println("Alternate texture");
	       }else {
	    	  //System.out.println(texture2);
	    	  compressedTexture = texture;
	       }
		
		this.customslimebaseRenderer.bindTexture(compressedTexture);
		
        //if (entitylivingbaseIn.isInvisible())
		if (entitylivingbaseIn.isWet())
        {
        	
 		   
        	
        	GlStateManager.pushMatrix();
        	//System.out.println(customslimebaseTexture);
            GlStateManager.color(0.5F, 0.5F, 1.0F, 1.0F);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            //this.customslimebaseModel.setModelAttributes(this.customslimebaseRenderer.getMainModel());
            //this.customslimebaseModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            
            livingBase.getMainModel().setModelAttributes(livingBase.getMainModel());
            livingBase.getMainModel().render(compressed, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
            GlStateManager.popMatrix();
        }

    }*/

    public boolean shouldCombineTextures()
    {
        return true;
    }

}
