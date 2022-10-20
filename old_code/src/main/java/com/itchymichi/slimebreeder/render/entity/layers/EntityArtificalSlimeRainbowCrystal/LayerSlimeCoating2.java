package com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeRainbowCrystal;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Maps;
import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeGel;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class LayerSlimeCoating2 implements LayerRenderer<EntityArtificalSlimeGel>
{
private static final ResourceLocation slimeTexture = new ResourceLocation(SlimeBreeder.MODID + ":textures/models/artificalslime.png");

//private static final ResourceLocation slimeTexture = new ResourceLocation(SlimeBreeder.MODID + ":textures/models/Slime2.png");

private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation(SlimeBreeder.MODID + ":textures/entity/artificalslime_coating.png");

private final RenderEntityArtificalSlimeRainbowCrystal slimeRenderer;
private final ModelBase slimeModel = new ModelSlime2(16);

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







public LayerSlimeCoating2(RenderEntityArtificalSlimeRainbowCrystal renderEntityArtificalSlimeGel)
{
this.slimeRenderer = renderEntityArtificalSlimeGel;
}


public void doRenderLayer(EntityArtificalSlimeGel slime2, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
{
	 ModelBase slimeModelC;
	
	 NBTTagCompound entityTag = slime2.getDataManager().get(slime2.COMPRESSED);
     
     //System.out.println(entityTag);
     
     Entity entityD = EntityList.createEntityFromNBT(entityTag, slime2.getEntityWorld());
     Entity slime = new EntitySlime(slime2.getEntityWorld());
 
     
     Render living = (Render)Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(entityD.getClass());
     
     
     RenderLivingBase livingBase = ((RenderLivingBase)living);
     
     slimeModelC = livingBase.getMainModel();
     
     
	

	if (entityD.getClass() != slime.getClass())
    {
		//System.out.println("running coating");
        boolean flag = slime2.isInvisible();
        GlStateManager.depthMask(!flag);
        this.slimeRenderer.bindTexture(LIGHTNING_TEXTURE);
        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        float f = (float)slime2.ticksExisted + partialTicks;
        //GlStateManager.translate(f * 0.01F, f * 0.01F, 0.0F);
        GlStateManager.matrixMode(5888);
        GlStateManager.enableBlend();
        float f1 = 0.5F;
        GlStateManager.color(0.7F, 0.7F, 0.7F, 1.0F);


        GlStateManager.disableLighting();
    	GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.DestFactor.SRC_ALPHA); // 771
    	
        
    	
        slimeModelC.setModelAttributes(this.slimeRenderer.getMainModel());
        
        GlStateManager.scale(1.00, 1.00, 1.00);
        GlStateManager.translate(-0.125, -0.002, -0.002);
        
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        slimeModelC.render(slime2, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);

        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(flag);
    }else {
    	GlStateManager.enableNormalize();
    	GlStateManager.enableBlend();
    	GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA); // 771

    	//this.slimeModel.setModelAttributes(this.slimeRenderer.getMainModel());
    	this.slimeModel.setModelAttributes(this.slimeRenderer.getMainModel());


    	this.slimeModel.render(slime2, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    	////renderItemSlime(slime2);
    	//renderInvSlime2(slime2);
    	//Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, new ItemStack(Items.APPLE), ItemCameraTransforms.TransformType.NONE);
    	GlStateManager.disableBlend();
    	GlStateManager.disableNormalize();

    }
	
	
	
}




public boolean shouldCombineTextures()
{
return false;
}
}