package com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeCrystal;
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
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeCrystal;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeRainbowCrystal;
import com.itchymichi.slimebreeder.entity.monster.EntityCustomSlimeBase;
import com.itchymichi.slimebreeder.lists.EnumRainbowColor;
import com.itchymichi.slimebreeder.lists.EnumTune;
import com.itchymichi.slimebreeder.models.ModelArtificalRainbowSlime;
import com.itchymichi.slimebreeder.models.ModelCustomSlimeBase;
import com.itchymichi.slimebreeder.models.ModelSlime2;
import com.itchymichi.slimebreeder.render.entity.RenderEntityArtificalSlimeCrystal;
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
import net.minecraft.item.ItemBlock;
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
public class LayerArtificalSlimeCrystalGel implements LayerRenderer<EntityArtificalSlimeCrystal>
{
	
	private final RenderEntityArtificalSlimeCrystal customslimebaseRenderer;
	private final ModelBase customslimebaseModel = new ModelSlime2(0);
	private static final ResourceLocation customslimebaseTexture = new ResourceLocation(SlimeBreeder.MODID + ":textures/models/artificalslime.png");

	
	public LayerArtificalSlimeCrystalGel(RenderEntityArtificalSlimeCrystal slimeRendererIn)
    {
        this.customslimebaseRenderer = slimeRendererIn;
    }
	
	
	public void doRenderLayer(EntityArtificalSlimeCrystal entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) 
	{
		
		this.customslimebaseRenderer.bindTexture(customslimebaseTexture);
		

		
        if (!entitylivingbaseIn.isInvisible())
        {
        	
        	float red = 1;
        	
        	float green = 1;
        	
        	float blue = 1;
        	
        	switch (entitylivingbaseIn.getRGB2()) 
			{
	        case 0:
	        	red = 1F;
	        	green = 1F;
	        	blue = 1F;

	        	break;
			case 1:
				red = 1F;
	        	green = 0.5F;
	        	blue = 0.5F;
				break;
			case 2:
				red = 0.5F;
	        	green = 0.5F;
	        	blue = 1F;
				break;	
			case 3:
				red = 0.5F;
	        	green = 1F;
	        	blue = 0.5F;
				break;
			default:
				red = 1F;
	        	green = 1F;
	        	blue = 1F;
				break;
			}	
        	
        	
        	//////////////////////////////////////////////////
        	
        	GlStateManager.pushMatrix();
        	//System.out.println(customslimebaseTexture);
            GlStateManager.color(red, green, blue);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.customslimebaseModel.setModelAttributes(this.customslimebaseRenderer.getMainModel());
            this.customslimebaseModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
            GlStateManager.popMatrix();
	    }
        
	}


    public boolean shouldCombineTextures()
    {
        return true;
    }

}
