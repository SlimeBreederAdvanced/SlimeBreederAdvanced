package com.itchymichi.slimebreeder.render.entity;

import java.io.File;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeBase;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeRainbowCrystal;
import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.entity.monster.EntityCustomSlimeBase;
import com.itchymichi.slimebreeder.models.ModelArtificalRainbowSlime;
import com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeRainbowCrystal.LayerArtificalSlimeRainbowCrystalGel;
import com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeRainbowCrystal.LayerSlimeCoating;
import com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeRainbowCrystal.LayerSlimeCoating2;
import com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeRainbowCrystal.LayerSlimeCoating3;
import com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeRainbowCrystal.LayerSlimeCoating4;
import com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeRainbowCrystal.LayerSlimeCoating5;
import com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeRainbowCrystal.LayerSlimeCoating6;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCreeperCharge;
import net.minecraft.client.renderer.entity.layers.LayerSlimeGel;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourceIndexFolder;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityArtificalSlimeRainbowCrystal  extends RenderLiving<EntityArtificalSlimeRainbowCrystal>
{
	

	//static final ResourceLocation slimeTexture = new ResourceLocation(SlimeBreeder.MODID+":textures/models/Slime.png");
	static ResourceLocation slimeTexture = new ResourceLocation(SlimeBreeder.MODID+":textures/models/artificalslime.png");
	
	//ResourceLocation texture = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/SlimeReader.png");
	public static final RenderEntityArtificalSlimeClearFactory ARTSLIMERAINBOWCRYSTALFACTORY = new RenderEntityArtificalSlimeClearFactory();
	private ResourceLocation texture;


	 public RenderEntityArtificalSlimeRainbowCrystal(RenderManager p_i46186_1_, ModelBase ModelSlime2, float f, ResourceLocation texture) 
 	 {
 		   super(p_i46186_1_, new ModelArtificalRainbowSlime(16), 0.25F);
 		   //System.out.println("Render Artifical Slime");
 		   this.texture = slimeTexture;
 		   this.layerRenderers.clear();
 		   this.addLayer(new LayerArtificalSlimeRainbowCrystalGel(this));
 		   this.addLayer(new LayerSlimeCoating(this));
 		   

 		   
 	 }
	 
	   
	 /*protected void preRenderCallback(EntityArtificalSlimeRainbowCrystal slime2, float partialTick) {    
		    //int i = slime2.getSlimeSize();
			int i = 1;

		    float f1 = (slime2.prevSquishFactor + (slime2.squishFactor - slime2.prevSquishFactor) * partialTick) / (i * 0.5F + 1.0F);
		    float f2 = 1.0F / (f1 + 1.0F); // 1.0F
		    float f3 = i;
		    GL11.glScalef(f2 * f3 * 1, (1.0F / f2 * f3) * 1, (f2 * f3) * 1);// * 4
		  }*/
	 
	 protected void preRenderCallback(EntityArtificalSlimeRainbowCrystal slime2, float partialTick) {    
		    //int i = slime2.getSlimeSize();
		 
		 if(slime2.getTransform()) {
			 
			 
			 	int i = 1;
			 	float c = ((float)(slime2.ticksExisted/1000));

			    float f1 = (slime2.prevSquishFactor + (slime2.squishFactor - slime2.prevSquishFactor) * partialTick) / (i * 0.5F + 1.0F);
			    float f2 = 1.0F / (f1 + 1.0F); // 1.0F
			    float f3 = i;
			    GL11.glScalef((f2 * f3 * 1)-(f1*10), ((1.0F / f2 * f3) * 1)+(f1*3), ((f2 * f3) * 1)-(f1*10)); 
		 }
			// * 4
		  }
		 

		protected ResourceLocation getEntityTexture(EntityArtificalSlimeRainbowCrystal entity) 
		{
			
			return slimeTexture;
		}
		
		
		

		
	 public static class RenderEntityArtificalSlimeClearFactory implements IRenderFactory<EntityArtificalSlimeRainbowCrystal>
	{

		protected ModelBase mainModel;
		protected float shadowOpaque;
		ModelRenderer slimeBodies;
		private RenderEntityArtificalSlimeRainbowCrystal slimeRenderer;
		
		public ModelBase getMainModel()
		 {
			return mainModel;
			 
		 }
		
		public float TestMethod3()
		{
			return shadowOpaque;
		}
		
		
		
				@Override
				public RenderEntityArtificalSlimeRainbowCrystal createRenderFor(RenderManager manager) 
				{
					return new RenderEntityArtificalSlimeRainbowCrystal(manager, mainModel, shadowOpaque, slimeTexture);

				}

				

	}









}


	
	
	

	 
	 


