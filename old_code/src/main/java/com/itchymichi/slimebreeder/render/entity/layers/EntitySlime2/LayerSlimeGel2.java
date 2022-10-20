package com.itchymichi.slimebreeder.render.entity.layers.EntitySlime2;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Maps;
import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.models.ModelSlime2;
import com.itchymichi.slimebreeder.render.entity.RenderEntitySlime2;

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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class LayerSlimeGel2 implements LayerRenderer<EntityColorSlimeBase>
{
private static final ResourceLocation slimeTexture = new ResourceLocation(SlimeBreeder.MODID + ":textures/models/slime.png");
//private static final ResourceLocation slimeTexture = new ResourceLocation(SlimeBreeder.MODID + ":textures/models/Slime2.png");

private final RenderEntitySlime2 slimeRenderer;
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





public LayerSlimeGel2(RenderEntitySlime2 renderEntitySlime2)
{
this.slimeRenderer = renderEntitySlime2;
}

public void setColor(int rgb)
{
	/*double factor;

    if((wave >= 380) && (wave<420)){
        factor = 0.3 + 0.7*(wave - 380) / (420 - 380);
    }else if((wave >= 420) && (wave<701)){
        factor = 1.0;
    }else if((wave >= 701) && (wave<781)){
        factor = 0.3 + 0.7*(780 - wave) / (780 - 700);
    }else{
        factor = 0.0;
    };
    
    if((wave >= 380) && (wave<440)){
    	float a = (-(wave - 440)/(((float)440) - ((float)380)));
    	////System.out.println(a);
    	////System.out.println(wave);
        this.red = a * ((float)factor);
        this.green = 0;
        this.blue = 1;
        
        
        
    }else if((wave >= 440) && (wave<490)){
    	float b = ((wave - 440)/(((float)490 - ((float)440))));
    	////System.out.println(b);
    	////System.out.println(wave);
        this.red = 0;
        this.green = b * ((float)factor);
        this.blue = 1;
        
        
    }else if((wave >= 490) && (wave<510)){
    	float c = (-(wave - 510)/(((float)510 - ((float)490))));
    	////System.out.println(c);
    	////System.out.println(wave);
        this.red = 0;
        this.green = 1;
        this.blue = c * ((float)factor);
     
        
    }else if((wave >= 510) && (wave<580)){
    	float d = ((wave - 510)/(((float)580 - ((float)510))));
    	////System.out.println(d);
    	////System.out.println(wave);
        this.red = d * ((float)factor);
        this.green = 1;
        this.blue = 0;
        
        
    }else if((wave >= 580) && (wave<645)){
    	float e = (-(wave - 645)/(((float)645 - 580)));
    	////System.out.println(e);
    	////System.out.println(wave);
        this.red = 1;
        this.green = e * ((float)factor);
        this.blue = 0;
        
        
    }else if((wave >= 645) && (wave<781)){
        this.red = 1;
        this.green = 0;
        this.blue = 0;
        ////System.out.println(wave);
       
        
    }else{
        this.red = 0;
        this.green = 0;
        this.blue = 0;
        ////System.out.println(wave);
      
    };*/
    
	

	

}


public void doRenderLayer(EntityColorSlimeBase slime2, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
{
if (!slime2.isInvisible())
{
this.slimeRenderer.bindTexture(slimeTexture);

//System.out.println("Test Slime Normal");

int rgb2 = slime2.getRGB2();
float red = (float)((rgb2>>16)&0xFF)/255;
float green = (float)((rgb2>>8)&0xFF)/255;
float blue = (float)(rgb2&0xFF)/255;

float finalred = 1F;
float finalgreen = 1F;
float finalblue = 1F;

////System.out.println("varient = " + slime2.getVarientServer() );

if(slime2.getVarientServer() == 1)
{

	finalred = (float) (red);
	finalgreen = (float) (green);
	finalblue = (float) (blue);
	
}

if(slime2.getVarientServer() == 2)
{
	////System.out.println("Pulse Slime");
	int time = slime2.ticksExisted;
	float amp = red/2;
	double angular = 0.03D;

	double sin = Math.sin((double)time * angular);
	float progress = amp * (float)sin;

	float differance1 = (float)(((progress+-50)+100)/100);
	double change1 = 1*differance1;

	
	finalred = (float) (red)+(red-progress);
	finalgreen = (float) (green);
	finalblue = (float) (blue);
	
}

if(slime2.getVarientServer() == 3)
{
	
	int time = slime2.ticksExisted;
	float amp = blue/2;
	double angular = 0.03D;

	double sin = Math.sin((double)time * angular);
	float progress = amp * (float)sin;

	float differance1 = (float)(((progress+-50)+100)/100);
	double change1 = 1*differance1;

	
	finalred = (float) (red);
	finalgreen = (float) (green);
	finalblue = (float) (blue)+(blue-progress);
	
}

if(slime2.getVarientServer() == 4)
{
	
	int time = slime2.ticksExisted;
	float amp = green/2;
	double angular = 0.03D;

	double sin = Math.sin((double)time * angular);
	float progress = amp * (float)sin;

	float differance1 = (float)(((progress+-50)+100)/100);
	double change1 = 1*differance1;

	
	finalred = (float) (red);
	finalgreen = (float) (green)+(green-progress);
	finalblue = (float) (blue);
	
	
}

if(slime2.getVarientServer() == 5)
{
	
	int time = slime2.ticksExisted;
	float amp = green/2;
	double angular = 0.001D;
	//double angular = 0.03D;
	
	finalred = (float) (Math.sin((double)time * angular + 0) * (127) + 128)/255;
	finalgreen = (float) (Math.sin((double)time * angular + 1) * (127) + 128)/255;
	finalblue = (float) (Math.sin((double)time * angular + 3) * (127) + 128)/255;
	
	
	
}





GlStateManager.color(finalred, finalgreen, finalblue, 1.0F);//.getDataWatcher().getWatchableObjectFloat(20),
//GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//GlStateManager.color(red, green, blue, 1.0F);
////System.out.println(this.red + "  " + this.green + "  " + this.blue);

GlStateManager.enableNormalize();
GlStateManager.enableBlend();
GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA); // 771
this.slimeModel.setModelAttributes(this.slimeRenderer.getMainModel());
this.slimeModel.render(slime2, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
renderItemSlime2(slime2);
////renderItemSlime(slime2);
//renderInvSlime2(slime2);
//Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, new ItemStack(Items.APPLE), ItemCameraTransforms.TransformType.NONE);
GlStateManager.disableBlend();
GlStateManager.disableNormalize();


}
}

public void renderItemSlime(EntityColorSlimeBase slime2)
{
	
	GlStateManager.enableBlend();
    GlStateManager.disableTexture2D();
    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
    
    GL11.glShadeModel(GL11.GL_SMOOTH);
    GL11.glDisable(GL11.GL_LIGHTING);
	//GL11.glPushMatrix();
	
	Tessellator t = Tessellator.getInstance();
    BufferBuilder b = t.getBuffer();
    b.begin(7, DefaultVertexFormats.POSITION_COLOR);

    drawMask(b, 0D, 1D, 0D, -3D, -3D, -3D, 0.5F, 1F, 0F, 1F);
    t.draw();
    //GL11.glScaled(0.50, 0.50, 0.50);
    //GL11.glScaled(1.00, 1.00, 1.00);

    //GL11.glTranslated(0.82D, 2.5D, 0.0D);
    //GL11.glRotated(180, 0, 0, 0);
    //Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, new ItemStack(Item.getItemFromBlock(Blocks.ANVIL)), ItemCameraTransforms.TransformType.NONE);
    //Minecraft.getMinecraft().effectRenderer.emitParticleAtEntity(slime2, EnumParticleTypes.FLAME);
    //Minecraft.getMinecraft().rend
    

	GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();
    GL11.glEnable(GL11.GL_LIGHTING);
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

public void renderItemSlime2(EntityColorSlimeBase slime2)
{
	int i = slime2.ticksExisted;
	
	//int stackIdSlot1 = slime2.getSlimeItemSlot1();
	
	int stackIdSlot1 = slime2.getServerItemSlot1();
	int stackIdSlot2 = slime2.getServerItemSlot2();
	int stackIdSlot3 = slime2.getServerItemSlot3();
	int stackIdSlot4 = slime2.getServerItemSlot4();
	int stackIdSlot5 = slime2.getServerItemSlot5();
	
	
	Random random;
	random = new Random();
	
	double d1;
	double d2;
	
	if(stackIdSlot1 == -1)
	{
		translatedDx1 = 0.5*random.nextDouble();
		translatedDy1 = 0.5*random.nextDouble();
		rotation1 = random.nextInt(45);
		
		////System.out.println("Randoms generate = " + translatedD1 + " " + translatedD2 + " " + rotation );
		
	}
	
	if(stackIdSlot2 == -1)
	{
		translatedDx2 = 0.5*random.nextDouble();
		translatedDy2 = 0.5*random.nextDouble();
		rotation2 = random.nextInt(45);
		
		////System.out.println("Randoms generate = " + translatedD1 + " " + translatedD2 + " " + rotation );
		
	}
	
	if(stackIdSlot3 == -1)
	{
		translatedDx3 = 0.5*random.nextDouble();
		translatedDy3 = 0.5*random.nextDouble();
		rotation3 = random.nextInt(45);
		
		////System.out.println("Randoms generate = " + translatedD1 + " " + translatedD2 + " " + rotation );
		
	}
	
	if(stackIdSlot4 == -1)
	{
		translatedDx4 = 0.5*random.nextDouble();
		translatedDy4 = 0.5*random.nextDouble();
		rotation4 = random.nextInt(45);
		
		////System.out.println("Randoms generate = " + translatedD1 + " " + translatedD2 + " " + rotation );
		
	}
	
	if(translatedDx5 == 0 && stackIdSlot5 == -1)
	{
		translatedDx5 = 0.5*random.nextDouble();
		translatedDy5 = 0.5*random.nextDouble();
		rotation5 = random.nextInt(45);
		
		////System.out.println("Randoms generate = " + translatedD1 + " " + translatedD2 + " " + rotation );
		
	}

	
	
	
if(stackIdSlot1 != -1)
{
		

		ItemStack stack = new ItemStack(Item.getItemById(stackIdSlot1));
		
		double percentProcessed = 1.0 - (((double)slime2.getServerItemProcessSlot1()/(double)slime2.getServerItemProcessSlot1Max()));
		////System.out.println((double)slime2.getServerItemProcessSlot1() + "/" + (double)slime2.getServerItemProcessSlot1Max() + " + " + percentProcessed);
		
		double z = 4.0D+(0.33D * percentProcessed);
		/*if(slime2.getServerItemProcessSlot1() == -1)
		{
			slime2.setItemProcessSlot1(1000);;
			////System.out.println("done");
		}*/
		
		GL11.glPushMatrix();
	    GL11.glScaled(0.25, 0.25, 0.25);
	    GL11.glTranslated(0, z, 0);
	    GL11.glRotated(50, 1, 1, 1);
	    
	    Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, stack, ItemCameraTransforms.TransformType.NONE);
	    
		GL11.glPopMatrix();	

}

if (stackIdSlot2 != -1)
{
	

	ItemStack stack = new ItemStack(Item.getItemById(stackIdSlot2));
	
	double percentProcessed = 1.0 - (((double)slime2.getServerItemProcessSlot2()/(double)slime2.getServerItemProcessSlot2Max()));
	////System.out.println((double)slime2.getServerItemProcessSlot2() + "/" + (double)slime2.getServerItemProcessSlot2Max() + " + " + percentProcessed);
	
	double z = 4.0D+(0.33D * percentProcessed);
	
	
	GL11.glPushMatrix();
    GL11.glScaled(0.25, 0.25, 0.25);
    GL11.glTranslated(0.35, z, 0.35);
    GL11.glRotated(-45, 1, 1, 1);
    
    Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, stack, ItemCameraTransforms.TransformType.NONE);
    
	GL11.glPopMatrix();	
}

if (stackIdSlot3 != -1)
{
	

	ItemStack stack = new ItemStack(Item.getItemById(stackIdSlot3));
	
	double percentProcessed = 1.0 - (((double)slime2.getServerItemProcessSlot3()/(double)slime2.getServerItemProcessSlot3Max()));
	////System.out.println((double)slime2.getServerItemProcessSlot3() + "/" + (double)slime2.getServerItemProcessSlot3Max() + " + " + percentProcessed);
	
	double z = 4.0D+(0.33D * percentProcessed);
	
	
	GL11.glPushMatrix();
    GL11.glScaled(0.25, 0.25, 0.25);
    GL11.glTranslated(-0.45, z, 0.45);
    GL11.glRotated(30, 1, 1, 1);
    
    Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, stack, ItemCameraTransforms.TransformType.NONE);
    
	GL11.glPopMatrix();	
}

if (stackIdSlot4 != -1)
{
	
	ItemStack stack = new ItemStack(Item.getItemById(stackIdSlot4));
	
	double percentProcessed = 1.0 - (((double)slime2.getServerItemProcessSlot4()/(double)slime2.getServerItemProcessSlot4Max()));
	////System.out.println((double)slime2.getServerItemProcessSlot4() + "/" + (double)slime2.getServerItemProcessSlot4Max() + " + " + percentProcessed);
	
	double z = 4.0D+(0.33D * percentProcessed);
	
	
	GL11.glPushMatrix();
    GL11.glScaled(0.25, 0.25, 0.25);
    GL11.glTranslated(0.45, z, -0.45);
    GL11.glRotated(-30, 1, 1, 1);
    
    Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, stack, ItemCameraTransforms.TransformType.NONE);
    
	GL11.glPopMatrix();	
}

if (stackIdSlot5 != -1)
{
	
	ItemStack stack = new ItemStack(Item.getItemById(stackIdSlot5));
	
	double percentProcessed = 1.0 - (((double)slime2.getServerItemProcessSlot5()/(double)slime2.getServerItemProcessSlot5Max()));
	////System.out.println((double)slime2.getServerItemProcessSlot5() + "/" + (double)slime2.getServerItemProcessSlot5Max() + " + " + percentProcessed);
	
	double z = 4.0D+(0.33D * percentProcessed);
	
	
	GL11.glPushMatrix();
    GL11.glScaled(0.25, 0.25, 0.25);
    GL11.glTranslated(-0.35, z, -0.35);
    GL11.glRotated(0, 1, 1, 1);
    
    Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, stack, ItemCameraTransforms.TransformType.NONE);
    
	GL11.glPopMatrix();	
}

if(stackIdSlot1 == -1)
{
	this.translatedDx1 = 0;
}

if(stackIdSlot2 == -1)
{
	this.translatedDx2 = 0;
}

if(stackIdSlot3 == -1)
{
	this.translatedDx3 = 0;
}

if(stackIdSlot4 == -1)
{
	this.translatedDx4 = 0;
}

if(stackIdSlot5 == -1)
{
	this.translatedDx5 = 0;
}

}

public void renderInvSlime2(EntityColorSlimeBase slime2)
{
	int i = slime2.ticksExisted;
	
	NBTTagList invList = slime2.getEntityData().getTagList("modItems", 10);
	
	//int stackIdSlot1 = slime2.getSlimeItemSlot1();
	
	int stackIdSlot1 = slime2.getServerItemSlot1();
	int stackIdSlot2 = slime2.getServerItemSlot2();
	int stackIdSlot3 = slime2.getServerItemSlot3();
	int stackIdSlot4 = slime2.getServerItemSlot4();
	int stackIdSlot5 = slime2.getServerItemSlot5();
	
	
	Random random;
	random = new Random();
	
	double d1;
	double d2;
	
	if(stackIdSlot1 == -1)
	{
		translatedDx1 = 0.5*random.nextDouble();
		translatedDy1 = 0.5*random.nextDouble();
		rotation1 = random.nextInt(45);
		
		////System.out.println("Randoms generate = " + translatedD1 + " " + translatedD2 + " " + rotation );
		
	}
	
	if(stackIdSlot2 == -1)
	{
		translatedDx2 = 0.5*random.nextDouble();
		translatedDy2 = 0.5*random.nextDouble();
		rotation2 = random.nextInt(45);
		
		////System.out.println("Randoms generate = " + translatedD1 + " " + translatedD2 + " " + rotation );
		
	}
	
	if(stackIdSlot3 == -1)
	{
		translatedDx3 = 0.5*random.nextDouble();
		translatedDy3 = 0.5*random.nextDouble();
		rotation3 = random.nextInt(45);
		
		////System.out.println("Randoms generate = " + translatedD1 + " " + translatedD2 + " " + rotation );
		
	}
	
	if(stackIdSlot4 == -1)
	{
		translatedDx4 = 0.5*random.nextDouble();
		translatedDy4 = 0.5*random.nextDouble();
		rotation4 = random.nextInt(45);
		
		////System.out.println("Randoms generate = " + translatedD1 + " " + translatedD2 + " " + rotation );
		
	}
	
	if(translatedDx5 == 0 && stackIdSlot5 == -1)
	{
		translatedDx5 = 0.5*random.nextDouble();
		translatedDy5 = 0.5*random.nextDouble();
		rotation5 = random.nextInt(45);
		
		////System.out.println("Randoms generate = " + translatedD1 + " " + translatedD2 + " " + rotation );
		
	}

	
	
	

		

		ItemStack stack = new ItemStack(invList.getCompoundTagAt(0));
		
		double percentProcessed = 1.0 - (((double)slime2.getServerItemProcessSlot1()/(double)slime2.getServerItemProcessSlot1Max()));
		////System.out.println((double)slime2.getServerItemProcessSlot1() + "/" + (double)slime2.getServerItemProcessSlot1Max() + " + " + percentProcessed);
		
		double z = 4.0D+(0.33D * percentProcessed);
		/*if(slime2.getServerItemProcessSlot1() == -1)
		{
			slime2.setItemProcessSlot1(1000);;
			////System.out.println("done");
		}*/
		
		GL11.glPushMatrix();
	    GL11.glScaled(1, 1, 1);
	    GL11.glTranslated(0, 0, 0);
	    GL11.glRotated(50, 1, 1, 1);
	    
	    ////System.out.println("Stack = " + stack);
	    
	    Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, stack, ItemCameraTransforms.TransformType.NONE);
	    
		GL11.glPopMatrix();	



/*if (stackIdSlot2 != -1)
{
	

	ItemStack stack = new ItemStack(Item.getItemById(stackIdSlot2));
	
	double percentProcessed = 1.0 - (((double)slime2.getServerItemProcessSlot2()/(double)slime2.getServerItemProcessSlot2Max()));
	////System.out.println((double)slime2.getServerItemProcessSlot2() + "/" + (double)slime2.getServerItemProcessSlot2Max() + " + " + percentProcessed);
	
	double z = 4.0D+(0.33D * percentProcessed);
	
	
	GL11.glPushMatrix();
    GL11.glScaled(0.25, 0.25, 0.25);
    GL11.glTranslated(0.35, z, 0.35);
    GL11.glRotated(-45, 1, 1, 1);
    
    Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, stack, ItemCameraTransforms.TransformType.NONE);
    
	GL11.glPopMatrix();	
}

if (stackIdSlot3 != -1)
{
	

	ItemStack stack = new ItemStack(Item.getItemById(stackIdSlot3));
	
	double percentProcessed = 1.0 - (((double)slime2.getServerItemProcessSlot3()/(double)slime2.getServerItemProcessSlot3Max()));
	////System.out.println((double)slime2.getServerItemProcessSlot3() + "/" + (double)slime2.getServerItemProcessSlot3Max() + " + " + percentProcessed);
	
	double z = 4.0D+(0.33D * percentProcessed);
	
	
	GL11.glPushMatrix();
    GL11.glScaled(0.25, 0.25, 0.25);
    GL11.glTranslated(-0.45, z, 0.45);
    GL11.glRotated(30, 1, 1, 1);
    
    Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, stack, ItemCameraTransforms.TransformType.NONE);
    
	GL11.glPopMatrix();	
}

if (stackIdSlot4 != -1)
{
	
	ItemStack stack = new ItemStack(Item.getItemById(stackIdSlot4));
	
	double percentProcessed = 1.0 - (((double)slime2.getServerItemProcessSlot4()/(double)slime2.getServerItemProcessSlot4Max()));
	////System.out.println((double)slime2.getServerItemProcessSlot4() + "/" + (double)slime2.getServerItemProcessSlot4Max() + " + " + percentProcessed);
	
	double z = 4.0D+(0.33D * percentProcessed);
	
	
	GL11.glPushMatrix();
    GL11.glScaled(0.25, 0.25, 0.25);
    GL11.glTranslated(0.45, z, -0.45);
    GL11.glRotated(-30, 1, 1, 1);
    
    Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, stack, ItemCameraTransforms.TransformType.NONE);
    
	GL11.glPopMatrix();	
}

if (stackIdSlot5 != -1)
{
	
	ItemStack stack = new ItemStack(Item.getItemById(stackIdSlot5));
	
	double percentProcessed = 1.0 - (((double)slime2.getServerItemProcessSlot5()/(double)slime2.getServerItemProcessSlot5Max()));
	////System.out.println((double)slime2.getServerItemProcessSlot5() + "/" + (double)slime2.getServerItemProcessSlot5Max() + " + " + percentProcessed);
	
	double z = 4.0D+(0.33D * percentProcessed);
	
	
	GL11.glPushMatrix();
    GL11.glScaled(0.25, 0.25, 0.25);
    GL11.glTranslated(-0.35, z, -0.35);
    GL11.glRotated(0, 1, 1, 1);
    
    Minecraft.getMinecraft().getItemRenderer().renderItem(slime2, stack, ItemCameraTransforms.TransformType.NONE);
    
	GL11.glPopMatrix();	
}*/

if(stackIdSlot1 == -1)
{
	this.translatedDx1 = 0;
}

if(stackIdSlot2 == -1)
{
	this.translatedDx2 = 0;
}

if(stackIdSlot3 == -1)
{
	this.translatedDx3 = 0;
}

if(stackIdSlot4 == -1)
{
	this.translatedDx4 = 0;
}

if(stackIdSlot5 == -1)
{
	this.translatedDx5 = 0;
}

}


public boolean shouldCombineTextures()
{
return true;
}
}