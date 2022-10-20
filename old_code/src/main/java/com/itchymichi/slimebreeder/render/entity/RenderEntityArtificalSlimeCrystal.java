package com.itchymichi.slimebreeder.render.entity;

import java.io.File;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeBase;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeCrystal;
import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.entity.monster.EntityCustomSlimeBase;
import com.itchymichi.slimebreeder.models.ModelArtificalRainbowSlime;
import com.itchymichi.slimebreeder.models.ModelArtificalSlime;
import com.itchymichi.slimebreeder.models.ModelCustomSlimeBase;
import com.itchymichi.slimebreeder.models.ModelSlime2;
import com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeCrystal.LayerArtificalSlimeCrystalGel;
import com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeCrystal.LayerSlimeAccessory;
import com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeCrystal.LayerSlimeLiquid;

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
public class RenderEntityArtificalSlimeCrystal  extends RenderLiving<EntityArtificalSlimeCrystal>
{
	

	//static final ResourceLocation slimeTexture = new ResourceLocation(SlimeBreeder.MODID+":textures/models/Slime.png");
	static ResourceLocation slimeTexture = new ResourceLocation(SlimeBreeder.MODID+":textures/models/artificalslime.png");
	
	//ResourceLocation texture = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/SlimeReader.png");
	public static final RenderEntityArtificalSlimeClearFactory ARTSLIMECRYSTALFACTORY = new RenderEntityArtificalSlimeClearFactory();
	private ResourceLocation texture;


	 public RenderEntityArtificalSlimeCrystal(RenderManager p_i46186_1_, ModelBase ModelSlime2, float f, ResourceLocation texture) 
 	 {
 		   super(p_i46186_1_, new ModelSlime2(16), 0.25F);
 		   //System.out.println("Render Artifical Slime");
 		   this.texture = slimeTexture;
 		   this.layerRenderers.clear();
 		   this.addLayer(new LayerSlimeLiquid(this)); 	
 		   this.addLayer(new LayerArtificalSlimeCrystalGel(this));
 		   this.addLayer(new LayerSlimeAccessory(this));
 		   	   
 		   

 		   
 	 }
	 
	   
	 /*protected void preRenderCallback(EntityArtificalSlimeRainbowCrystal slime2, float partialTick) {    
		    //int i = slime2.getSlimeSize();
			int i = 1;

		    float f1 = (slime2.prevSquishFactor + (slime2.squishFactor - slime2.prevSquishFactor) * partialTick) / (i * 0.5F + 1.0F);
		    float f2 = 1.0F / (f1 + 1.0F); // 1.0F
		    float f3 = i;
		    GL11.glScalef(f2 * f3 * 1, (1.0F / f2 * f3) * 1, (f2 * f3) * 1);// * 4
		  }*/
	 
	 protected void preRenderCallback(EntityArtificalSlimeCrystal slime2, float partialTick) {    
		    int i = 2;
		    float f1 = (slime2.prevSquishFactor + (slime2.squishFactor - slime2.prevSquishFactor) * partialTick) / (i * 0.5F + 1.0F);
		    float f2 = 1.0F / (f1 + 1.0F); // 1.0F
		    float f3 = i;
		    GL11.glScalef(f2 * f3 * 1, (1.0F / f2 * f3) * 1, (f2 * f3) * 1);// * 4
		  }
		 

		protected ResourceLocation getEntityTexture(EntityArtificalSlimeCrystal entity) 
		{
			
			return slimeTexture;
		}
		
		
		

		
	 public static class RenderEntityArtificalSlimeClearFactory implements IRenderFactory<EntityArtificalSlimeCrystal>
	{

		protected ModelBase mainModel;
		protected float shadowOpaque;
		ModelRenderer slimeBodies;
		private RenderEntityArtificalSlimeCrystal slimeRenderer;
		
		public ModelBase getMainModel()
		 {
			return mainModel;
			 
		 }
		
		public float TestMethod3()
		{
			return shadowOpaque;
		}
		
		
		
				@Override
				public RenderEntityArtificalSlimeCrystal createRenderFor(RenderManager manager) 
				{
					return new RenderEntityArtificalSlimeCrystal(manager, mainModel, shadowOpaque, slimeTexture);

				}

				

	}













}


	
	
	

	 
	 


