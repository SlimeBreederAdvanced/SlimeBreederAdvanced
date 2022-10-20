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
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerSlimeLiquid implements LayerRenderer<EntityArtificalSlimeCrystal>
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





public LayerSlimeLiquid(RenderEntityArtificalSlimeCrystal renderEntityArtificalSlimeCrystal)
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

	renderLiquid2(slime2);

	GlStateManager.disableBlend();
	GlStateManager.disableNormalize();
	








}
}



public void renderLiquid2(EntityArtificalSlimeCrystal slime2)
{
	
NBTTagCompound fluidNBT = slime2.getCoreFluid();
	
	Block fluidBlock = Blocks.AIR;
	
	
	float red = 0.5F;
	float green = 1F;
	float blue = 0F;
	
	//ResourceLocation slimeLiquid = new ResourceLocation("minecraft" + ":textures/blocks/water_still.png");
	
	//ResourceLocation slimeLiquid = new ResourceLocation("minecraft" + ":textures/blocks/water_still.png");
	//ResourceLocation slimeLiquid = new ResourceLocation(SlimeBreeder.MODID + ":textures/blocks/fluidliquidcrystal.png");
	ResourceLocation slimeMolten = new ResourceLocation(SlimeBreeder.MODID + ":textures/blocks/fluids/moltenslime.png");
	ResourceLocation slimeLiquid = new ResourceLocation(SlimeBreeder.MODID + ":textures/blocks/fluids/liquidslime.png");
	
		
				
				int liquidColour = FluidStack.loadFluidStackFromNBT(fluidNBT).getFluid().getColor();
				
				
				
				red = (float)((liquidColour>>16)&0xFF)/255;
				green = (float)((liquidColour>>8)&0xFF)/255;
				blue = (float)(liquidColour&0xFF)/255;
				
				if(FluidStack.loadFluidStackFromNBT(fluidNBT).getFluid() == FluidRegistry.WATER){
					red = 0.2F;
					green = 0.2F;
					blue = 1F;
					
					if(fluidNBT.hasKey("POTION")){
						NBTTagList potionList = fluidNBT.getTagList("POTION", 10).copy();
						
						NBTTagCompound Potion2 = potionList.getCompoundTagAt(0).copy();
						
						ItemStack potion = new ItemStack(Potion2);
						
						int colour = PotionUtils.getColor(potion);
						
						red = (float)((colour>>16)&0xFF)/255;
						green = (float)((colour>>8)&0xFF)/255;
						blue = (float)(colour&0xFF)/255;
						
						
					}
					
				}

			
		
		
		
		
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(slimeLiquid);
		GlStateManager.enableBlend();
		//GlStateManager.disableTexture2D();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
	    
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
	
		Tessellator t = Tessellator.getInstance();
		BufferBuilder b = t.getBuffer();
		b.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		//Minecraft.getMinecraft().getTextureManager().bindTexture(slimeTexture);
		
		
		//System.out.println("Health = " + slime2.getHealth() + " / " + slime2.getMaxHealth());
		
		//drawMask2(b, 0.7D, 0.1D, -1.1D, -0.2D, (-0.15 - (0.6*(slime2.getHealth()/slime2.getMaxHealth()))), -0.2D, red, green, blue, 1.0F);
		drawMask2(b, 0.7D, 0.15D, -1.1D, -0.2D, (0.15 - (0.9*((double)slime2.getLiquidVolume()/1000))), -0.2D, red, green, blue, 1.0F);

		//System.out.println(slime2.getLiquidVolume()/1000);
		
		GL11.glScaled(0.50, 0.50, 0.50);

	    GL11.glTranslated(-0.25D, 2.8D, 0.65D);
	   // GL11.glRotated(180, 0, 0, 0);
	    
	    t.draw();
		//GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();

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