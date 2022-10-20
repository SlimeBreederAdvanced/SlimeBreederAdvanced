package com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeRainbowCrystal;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Maps;
import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeRainbowCrystal;
import com.itchymichi.slimebreeder.lists.EnumRainbowColor;
import com.itchymichi.slimebreeder.models.ModelArtificalRainbowSlime;
import com.itchymichi.slimebreeder.models.ModelSlime2;
import com.itchymichi.slimebreeder.render.entity.RenderEntityArtificalSlimeRainbowCrystal;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
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
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class LayerSlimeCoating implements LayerRenderer<EntityArtificalSlimeRainbowCrystal>
{
//private static final ResourceLocation slimeTexture = new ResourceLocation(SlimeBreeder.MODID + ":textures/models/artificalslime.png");

//private static final ResourceLocation slimeTexture = new ResourceLocation(SlimeBreeder.MODID + ":textures/models/Slime2.png");

	private final RenderEntityArtificalSlimeRainbowCrystal customslimebaseRenderer;
	private final ModelBase customslimebaseModel = new ModelArtificalRainbowSlime(0);
	private static final ResourceLocation CRYSTAL_TEXTURE = new ResourceLocation(SlimeBreeder.MODID + ":textures/entity/artificalslime_coating.png");

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



	public LayerSlimeCoating(RenderEntityArtificalSlimeRainbowCrystal renderEntityArtificalSlimeGel)
	{
		this.customslimebaseRenderer = renderEntityArtificalSlimeGel;
	}


public void doRenderLayer(EntityArtificalSlimeRainbowCrystal slime2, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
{
	
	//Entity compressed = EntityList.createEntityFromNBT(slime2.getCompressedType(), slime2.getEntityWorld());
	Entity compressed = EntityList.createEntityByIDFromName(new ResourceLocation(slime2.getCompressedType()), slime2.getEntityWorld());

	Render living = (Render)Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(compressed.getClass());
	RenderLivingBase livingBase = ((RenderLivingBase)living);
	
	ResourceLocation texture = new ResourceLocation(EntityRegistry.getEntry(compressed.getClass()).getRegistryName().getResourceDomain() + ":textures/entity/"+ compressed.getName()+".png");
	ResourceLocation texture2 = new ResourceLocation(EntityRegistry.getEntry(compressed.getClass()).getRegistryName().getResourceDomain() + ":textures/entity/"+ compressed.getName() + "/"+ compressed.getName()+".png");
	
	ResourceLocation compressedTexture;
	
	Entity slime = new EntitySlime(slime2.getEntityWorld());
	
	if(Minecraft.getMinecraft().getTextureManager().getTexture(texture) == null) {
    	   //System.out.println(texture);
    	  compressedTexture = texture2;
    	  //System.out.println("Alternate texture");
       }else {
    	  //System.out.println(texture2);
    	  compressedTexture = texture;
       }
	
	//this.customslimebaseRenderer.bindTexture(CRYSTAL_TEXTURE);
	
	
	if(compressed.getClass() != slime.getClass()) {
		this.customslimebaseRenderer.bindTexture(CRYSTAL_TEXTURE);
		
	}
	
	
    if (!slime2.isInvisible() && compressed.getClass() != slime.getClass())
    {
    	/*GlStateManager.pushMatrix();
    	//System.out.println(customslimebaseTexture);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableNormalize();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        this.customslimebaseModel.setModelAttributes(this.customslimebaseRenderer.getMainModel());
        this.customslimebaseModel.render(slime2, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        
        livingBase.getMainModel().setModelAttributes(livingBase.getMainModel());
        livingBase.getMainModel().render(compressed, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        
        GlStateManager.disableBlend();
        GlStateManager.disableNormalize();
        GlStateManager.popMatrix();*/
    //}else{
    	
    	layerOne(slime2, compressed, livingBase,limbSwing,limbSwingAmount,partialTicks,ageInTicks,netHeadYaw, headPitch, scale);
    	layerTwo(slime2, compressed, livingBase,limbSwing,limbSwingAmount,partialTicks,ageInTicks,netHeadYaw, headPitch, scale);
    	layerThree(slime2, compressed, livingBase,limbSwing,limbSwingAmount,partialTicks,ageInTicks,netHeadYaw, headPitch, scale);
    	layerFour(slime2,compressed, livingBase,limbSwing,limbSwingAmount,partialTicks,ageInTicks,netHeadYaw, headPitch, scale);
    	//layerFive(slime2,compressed, livingBase,limbSwing,limbSwingAmount,partialTicks,ageInTicks,netHeadYaw, headPitch, scale);
    	//layerSix(slime2,compressed, livingBase,limbSwing,limbSwingAmount,partialTicks,ageInTicks,netHeadYaw, headPitch, scale);
 
    	
    }

}

public void layerOne(EntityArtificalSlimeRainbowCrystal slime2, Entity compressed, RenderLivingBase livingBase, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale){
	
	EnumRainbowColor.listOfObjects.clear();
	EnumRainbowColor.initColorList();
	
	
	//////////////////////////////////////////////////
	
	GlStateManager.pushMatrix();
	//System.out.println(customslimebaseTexture);
    GlStateManager.color(((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).red)/255),((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).blue)/255),((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).green)/255));
    GlStateManager.enableNormalize();
    GlStateManager.enableBlend();
    GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    //this.customslimebaseModel.setModelAttributes(this.customslimebaseRenderer.getMainModel());
    //this.customslimebaseModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    
    GlStateManager.scale(1.00, 1.00, 1.00);
    GlStateManager.translate(0.0675, -0.0015, -0.0015);
    
    livingBase.getMainModel().setModelAttributes(livingBase.getMainModel());
    livingBase.getMainModel().render(compressed, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    
 
    
    GlStateManager.disableBlend();
    GlStateManager.disableNormalize();
    GlStateManager.popMatrix();
}

public void layerTwo(EntityArtificalSlimeRainbowCrystal slime2, Entity compressed, RenderLivingBase livingBase, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale){
	
   	GlStateManager.pushMatrix();
	//System.out.println(customslimebaseTexture);
    GlStateManager.color(((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).red)/255),((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).blue)/255),((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).green)/255));
    GlStateManager.enableNormalize();
    GlStateManager.enableBlend();
    GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    //this.customslimebaseModel.setModelAttributes(this.customslimebaseRenderer.getMainModel());
    //this.customslimebaseModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    
    GlStateManager.scale(1.00, 1.00, 1.00);
    GlStateManager.translate(-0.0675, -0.002, -0.002);
    
    livingBase.getMainModel().setModelAttributes(livingBase.getMainModel());
    livingBase.getMainModel().render(compressed, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    
 
    
    GlStateManager.disableBlend();
    GlStateManager.disableNormalize();
    GlStateManager.popMatrix();
}

public void layerThree(EntityArtificalSlimeRainbowCrystal slime2, Entity compressed, RenderLivingBase livingBase, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale){
	
   	GlStateManager.pushMatrix();
	//System.out.println(customslimebaseTexture);
    GlStateManager.color(((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).red)/255),((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).blue)/255),((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).green)/255));
    GlStateManager.enableNormalize();
    GlStateManager.enableBlend();
    GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    //this.customslimebaseModel.setModelAttributes(this.customslimebaseRenderer.getMainModel());
    //this.customslimebaseModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    
    GlStateManager.scale(1.00, 1.00, 1.00);
    GlStateManager.translate(0, 0.0675, 0.003);
    
    livingBase.getMainModel().setModelAttributes(livingBase.getMainModel());
    livingBase.getMainModel().render(compressed, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    
 
    
    GlStateManager.disableBlend();
    GlStateManager.disableNormalize();
    GlStateManager.popMatrix();
}

public void layerFour(EntityArtificalSlimeRainbowCrystal slime2,Entity compressed, RenderLivingBase livingBase, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale){
	
   	GlStateManager.pushMatrix();
	//System.out.println(customslimebaseTexture);
    GlStateManager.color(((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).red)/255),((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).blue)/255),((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).green)/255));
    GlStateManager.enableNormalize();
    GlStateManager.enableBlend();
    GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    //this.customslimebaseModel.setModelAttributes(this.customslimebaseRenderer.getMainModel());
    //this.customslimebaseModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    
    GlStateManager.scale(1.00, 1.00, 1.00);
    GlStateManager.translate(0.004, -0.0675, 0.004);
    
    livingBase.getMainModel().setModelAttributes(livingBase.getMainModel());
    livingBase.getMainModel().render(compressed, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    
 
    
    GlStateManager.disableBlend();
    GlStateManager.disableNormalize();
    GlStateManager.popMatrix();
}

public void layerFive(EntityArtificalSlimeRainbowCrystal slime2,Entity compressed, RenderLivingBase livingBase, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale){
	
   	GlStateManager.pushMatrix();
	//System.out.println(customslimebaseTexture);
    GlStateManager.color(((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).red)/255),((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).blue)/255),((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).green)/255));
    GlStateManager.enableNormalize();
    GlStateManager.enableBlend();
    GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    //this.customslimebaseModel.setModelAttributes(this.customslimebaseRenderer.getMainModel());
    //this.customslimebaseModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    
    GlStateManager.scale(1.00, 1.00, 1.00);
    GlStateManager.translate(0.001, 0.001, 0.0675);
    
    livingBase.getMainModel().setModelAttributes(livingBase.getMainModel());
    livingBase.getMainModel().render(compressed, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    
 
    
    GlStateManager.disableBlend();
    GlStateManager.disableNormalize();
    GlStateManager.popMatrix();
}

public void layerSix(EntityArtificalSlimeRainbowCrystal slime2,Entity compressed, RenderLivingBase livingBase, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale){
	
   	GlStateManager.pushMatrix();
	//System.out.println(customslimebaseTexture);
    GlStateManager.color(((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).red)/255),((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).blue)/255),((float)(EnumRainbowColor.listOfObjects.get(slime2.getTimer()).green)/255));
    GlStateManager.enableNormalize();
    GlStateManager.enableBlend();
    GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    //this.customslimebaseModel.setModelAttributes(this.customslimebaseRenderer.getMainModel());
    //this.customslimebaseModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    
    GlStateManager.scale(1.00, 1.00, 1.00);
    GlStateManager.translate(-0.002, -0.002, -0.0675);
    
    livingBase.getMainModel().setModelAttributes(livingBase.getMainModel());
    livingBase.getMainModel().render(compressed, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    
 
    
    GlStateManager.disableBlend();
    GlStateManager.disableNormalize();
    GlStateManager.popMatrix();
}

public void layerSeven(Entity compressed, RenderLivingBase livingBase, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale){
	
   	GlStateManager.pushMatrix();
	//System.out.println(customslimebaseTexture);
    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    GlStateManager.enableNormalize();
    GlStateManager.enableBlend();
    GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    //this.customslimebaseModel.setModelAttributes(this.customslimebaseRenderer.getMainModel());
    //this.customslimebaseModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    
    GlStateManager.scale(1.00, 1.00, 1.00);
    GlStateManager.translate(-0.125, -0.002, -0.002);
    
    livingBase.getMainModel().setModelAttributes(livingBase.getMainModel());
    livingBase.getMainModel().render(compressed, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    
 
    
    GlStateManager.disableBlend();
    GlStateManager.disableNormalize();
    GlStateManager.popMatrix();
}

	public boolean shouldCombineTextures()
	{
			return false;
	}
}