package com.itchymichi.slimebreeder.render.entity;

import java.io.File;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeGel;
import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.models.ModelArtificalRainbowSlime;
import com.itchymichi.slimebreeder.render.entity.layers.EntityArtificalSlimeRainbowCrystal.LayerArtificalSlimeGel;
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
public class RenderEntityArtificalSlimeGel2Backup  extends RenderLiving<EntityArtificalSlimeGel>
{
	
	private ModelBase playerModel = new ModelBiped();

	//static final ResourceLocation slimeTexture = new ResourceLocation(SlimeBreeder.MODID+":textures/models/Slime.png");
	static ResourceLocation slimeTexture = new ResourceLocation(SlimeBreeder.MODID+":textures/models/artificalslime.png");
	
	//ResourceLocation texture = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/SlimeReader.png");
	public static final RenderEntityArtificalSlimeGelFactory ARTSLIMEGELFACTORY = new RenderEntityArtificalSlimeGelFactory();
	private ResourceLocation texture;


	 public RenderEntityArtificalSlimeGel2Backup(RenderManager p_i46186_1_, ModelBase ModelSlime2, float f, ResourceLocation texture) 
 	 {
 		   super(p_i46186_1_, new ModelArtificalRainbowSlime(16), 0.25F);
 		   //System.out.println("Render Artifical Slime");
 		   this.texture = slimeTexture;
 		   this.layerRenderers.clear();
 		   
 		   
 		   
 		   //this.addLayer(new LayerArtificalSlimeGel(this));
 		   

 		   
 	 }
	 
	    /*public void doRender(EntityArtificalSlimeGel entity, double x, double y, double z, float entityYaw, float partialTicks)
	    {
	        this.shadowSize = 0.25F * (float)entity.getSlimeSize();

	        if (entity instanceof EntityArtificalSlimeGel)
	    	{
	        	
	        	 this.shadowSize = 0.25F * (float)entity.getSlimeSize();
	 	        
	        	 ModelBase slimeModelC;
	        		
	        	 NBTTagCompound entityTag = entity.getDataManager().get(entity.COMPRESSED);
	             
	             //System.out.println(entityTag);
	             
	             Entity entityD = EntityList.createEntityFromNBT(entityTag, entity.getEntityWorld());
	             Entity slime = new EntitySlime(entity.getEntityWorld());
	         
	             
	             Render living = (Render)Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(entityD.getClass());
	             
	             
	             RenderLivingBase livingBase = ((RenderLivingBase)living);
	             
	             slimeModelC = livingBase.getMainModel();
	 	        
	 	       //System.out.println(living.toString());
	 	     // System.out.println(livingBase.toString());
	 	        
	 	        
	 	        
	 	        
	 	        this.mainModel = this.getMainModel();
	 	        
	 	        //this.mainModel = livingBase.getMainModel();
	 	        
	 	        //System.out.println(EntityRegistry.getEntry(entityD.getClass()).toString());
	 	        if(entityD instanceof EntitySlime) {
	 	        	this.slimeTexture = texture;
	 	        	this.layerRenderers.clear();
		 	    	//System.out.println("Slime");
		 	    	this.addLayer(new LayerArtificalSlimeGel(this));
	 	        	
	 	        	 //System.out.println(entityD);
	 	        	
	 	        }else{
	 	        ResourceLocation texture; 
	 	        ResourceLocation texture2;
	 	        ResourceLocation texture3;
	 	        
	 	        texture = new ResourceLocation(EntityRegistry.getEntry(entityD.getClass()).getRegistryName().getResourceDomain() + ":textures/entity/"+ entityD.getName()+".png");
	 	        texture2 = new ResourceLocation(EntityRegistry.getEntry(entityD.getClass()).getRegistryName().getResourceDomain() + ":textures/entity/"+ entityD.getName() + "/"+ entityD.getName()+".png");

	 	        //texture3 = new ResourceLocation(livingBase.getRenderManager().entityRenderMap. + ":textures/entity/"+ entityD.getName()+".png");
	 	        
	 	       if(Minecraft.getMinecraft().getTextureManager().getTexture(texture) == null) {
	 	    	   //System.out.println(texture);
	 	    	  this.slimeTexture = texture2;
	 	    	  //System.out.println("Alternate texture");
	 	       }else {
	 	    	  //System.out.println(texture2);
	 	    	  this.slimeTexture = texture;
	 	       }
	 	       
	 	      

	 		   
	 	     if(entityD.getClass() != slime.getClass()){
	 	    	 
	 	    	this.layerRenderers.clear();
	 	    	//System.out.println("Something else");
	 	    	this.addLayer(new LayerSlimeCoating(this));
	 	    	this.addLayer(new LayerSlimeCoating2(this));
	 	    	this.addLayer(new LayerSlimeCoating3(this));
	 	    	this.addLayer(new LayerSlimeCoating4(this));
	 	    	this.addLayer(new LayerSlimeCoating5(this));
	 	    	this.addLayer(new LayerSlimeCoating6(this));
	 	    	
	 	    	
	 	    	 
	 	    	 
	 	     }
	 	    if(entityD instanceof EntitySlime){
	 	     
	 	    	this.layerRenderers.clear();
	 	    	System.out.println("Slime");
	 	    	this.addLayer(new LayerArtificalSlimeGel(this));
	 	     }
	 	       
	 	       

	 	        
	 	        super.doRender(entity, x, y, z, entityYaw, partialTicks);
	    	 }
	    	}
	        
	        super.doRender(entity, x, y, z, entityYaw, partialTicks);
	    }

		  protected void preRenderCallback(EntityArtificalSlimeGel slime2, float partialTick) {    
			    int i = slime2.getSlimeSize();
			    float f1 = (slime2.prevSquishFactor + (slime2.squishFactor - slime2.prevSquishFactor) * partialTick) / (i * 0.5F + 1.0F);
			    float f2 = 1.0F / (f1 + 1.0F); // 1.0F
			    float f3 = i;
			    GL11.glScalef(f2 * f3 * 1, (1.0F / f2 * f3) * 1, (f2 * f3) * 1);// * 4
			  }*/
	 
	    public void doRender(EntityArtificalSlimeGel entity, double x, double y, double z, float entityYaw, float partialTicks)
	    {
	        this.shadowSize = 0.25F * (float)entity.getSlimeSize();
	        super.doRender(entity, x, y, z, entityYaw, partialTicks);
	    }
		  
		  
		  
	 

		protected ResourceLocation getEntityTexture(EntityArtificalSlimeGel entity) 
		{
			
			return slimeTexture;
		}
		
		

		
	 public static class RenderEntityArtificalSlimeGelFactory implements IRenderFactory<EntityArtificalSlimeGel>
	{

		protected ModelBase mainModel;
		protected float shadowOpaque;
		ModelRenderer slimeBodies;
		private RenderEntityArtificalSlimeGel2Backup slimeRenderer;
		
		public ModelBase getMainModel()
		 {
			return mainModel;
			 
		 }
		
		public float TestMethod3()
		{
			return shadowOpaque;
		}
		
		
		
				@Override
				public RenderEntityArtificalSlimeGel2Backup createRenderFor(RenderManager manager) 
				{
					return new RenderEntityArtificalSlimeGel2Backup(manager, mainModel, shadowOpaque, slimeTexture);

				}

				

	}




}


	
	
	

	 
	 


