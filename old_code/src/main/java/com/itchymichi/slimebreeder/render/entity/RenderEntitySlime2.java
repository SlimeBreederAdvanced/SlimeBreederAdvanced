package com.itchymichi.slimebreeder.render.entity;

import org.lwjgl.opengl.GL11;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.models.ModelSlime2;
import com.itchymichi.slimebreeder.render.entity.layers.EntitySlime2.LayerSlimeGel2;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.client.renderer.entity.layers.LayerSlimeGel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityDispatcher;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntitySlime2  extends RenderLiving<EntityColorSlimeBase>
{
	
	

	//static final ResourceLocation slimeTexture = new ResourceLocation(SlimeBreeder.MODID+":textures/models/Slime.png");
	static final ResourceLocation slimeTexture = new ResourceLocation(SlimeBreeder.MODID+":textures/models/Slime2.png");

	//ResourceLocation texture = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/SlimeReader.png");
	public static final RenderEntitySlime2Factory SLIMEFACTORY = new RenderEntitySlime2Factory();
	private ResourceLocation texture;


	 public RenderEntitySlime2(RenderManager p_i46186_1_, ModelBase ModelSlime2, float f, ResourceLocation texture) 
 	 {
 		   super(p_i46186_1_, new ModelSlime2(16), 0.25F);
 		   this.texture = slimeTexture;
 		   this.layerRenderers.clear();
 		   this.addLayer(new LayerSlimeGel2(this));
 		   System.out.println("Custom Colour Slime Render");
 		   
 	 }
	 
	    /*public void doRender(EntityColorSlimeBase entity, double x, double y, double z, float entityYaw, float partialTicks)
	    {
	        this.shadowSize = 0.25F * (float)entity.getSlimeSize();
	        
	        NBTTagCompound entityTag = entity.getDataManager().get(entity.ENTITY);
	        
	        Entity entityD = EntityList.createEntityFromNBT(entityTag, entity.getEntityWorld());
	        
	        
	        Render living = (Render)Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(entityD.getClass());
	        
	        
	        RenderLivingBase livingBase = ((RenderLivingBase)living);
	        
	        this.mainModel = livingBase.getMainModel();
	        
	        super.doRender(entity, x, y, z, entityYaw, partialTicks);
	    }*/

	  protected void preRenderCallback(EntityColorSlimeBase slime2, float partialTick) {    
	    int i = 2;
	    float f1 = (slime2.prevSquishFactor + (slime2.squishFactor - slime2.prevSquishFactor) * partialTick) / (i * 0.5F + 1.0F);
	    float f2 = 1.0F / (f1 + 1.0F); // 1.0F
	    float f3 = i;
	    GL11.glScalef(f2 * f3 * 1, (1.0F / f2 * f3) * 1, (f2 * f3) * 1);// * 4
	  }
	 

		protected ResourceLocation getEntityTexture(EntityColorSlimeBase entity) 
		{
			
			return slimeTexture;
		}
		
		

		
	 public static class RenderEntitySlime2Factory implements IRenderFactory<EntityColorSlimeBase>
	{

		protected ModelBase mainModel;
		protected float shadowOpaque;
		ModelRenderer slimeBodies;
		private RenderEntitySlime2 slimeRenderer;
		
		public ModelBase getMainModel()
		 {
			return mainModel;
			 
		 }
		
		public float TestMethod3()
		{
			return shadowOpaque;
		}
		
		
		
				@Override
				public RenderEntitySlime2 createRenderFor(RenderManager manager) 
				{
					return new RenderEntitySlime2(manager, mainModel, shadowOpaque, slimeTexture);

				}

				

	}




}


	
	
	

	 
	 


