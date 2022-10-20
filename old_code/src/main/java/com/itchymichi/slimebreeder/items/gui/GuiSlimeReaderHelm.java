package com.itchymichi.slimebreeder.items.gui;

import org.lwjgl.opengl.GL11;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.lists.EnumItemColor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiSlimeReaderHelm extends Gui {
	
	float redF;
	float greenF;
	float blueF;
	
	float red2F;
	float green2F;
	float blue2F;
	
	float wave1;
	float bright1;
	float sat1;
	
	float wave2;
	float bright2;
	float sat2;
	
	private static ResourceLocation slider;
	private static ResourceLocation rainCloud;
	
	public ItemStack rightItemStack = new ItemStack(Item.getItemFromBlock(Blocks.IRON_ORE));
	public ItemStack leftItemStack = new ItemStack(Item.getItemFromBlock(Blocks.IRON_ORE));
	
	Minecraft mc = Minecraft.getMinecraft();
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void RenderGameOverlayEvent(net.minecraftforge.client.event.RenderGameOverlayEvent.Pre event)
	{
		
		if (event.getType() == ElementType.ALL) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.world.getPlayerEntityByUUID(mc.player.getUniqueID());
			/*World world = player.worldObj;*/
		//System.out.println(player.getHeldItemMainhand().getItem());
		this.rightItemStack = player.getHeldItemOffhand();
		this.leftItemStack = player.getHeldItemMainhand();
	    rainCloud = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/raincloud.png");
		

		setcolor(this.rightItemStack, 0);
		setcolor(this.leftItemStack, 1);
		
		//drawSat(redF, greenF, blueF, event.getResolution());
		//System.out.println(wave1);
		
		
		//drawBars();
		
		drawIcons();
		
    	
		
		
		
		}
	}
	
	public void drawBars() {
		
		Minecraft mc = Minecraft.getMinecraft();
		
		ScaledResolution scaled = new ScaledResolution(mc);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
    	
    	
    	GlStateManager.pushAttrib();
    	
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GlStateManager.translate(width/1.1, height/20, 0);
        
        drawLine();
		
        if(wave1 > 0) {
        	
		drawWave1(redF, greenF, blueF, wave1);
		drawBright1(redF, greenF, blueF, bright1);
		drawSat1(redF, greenF, blueF, sat1);
        }
        if(wave2 > 0 ) {
		drawWave2(red2F, green2F, blue2F, wave2);
		drawBright2(red2F, green2F, blue2F, bright2);
		drawSat2(red2F, green2F, blue2F, sat2);
        }
        
		
        
		Tessellator.getInstance().draw();
		
		GlStateManager.enableTexture2D();
	    GlStateManager.disableBlend();
	    GlStateManager.disableAlpha();
	    //GL11.glEnable(GL11.GL_LIGHTING);
		GlStateManager.popAttrib();
	}
	
	public void drawWave1(float red1, float blue1, float green1, float wave)
    {

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
       
        

		
		wr.pos(0, 32, 0).color(0.5f, 0.5f, 0.5f, 0.7f).endVertex();
		wr.pos(8, 32, 0).color(0.5f, 0.5f, 0.5f, 0.7f).endVertex();
		wr.pos(8, 32*wave, 0).color(red1/255, blue1/255, green1/255, 0.7f).endVertex();
		wr.pos(0, 32*wave, 0).color(red1/255, blue1/255, green1/255, 0.7f).endVertex();
		
		


    	
	    
    }
	
	public void drawBright1(float red1, float blue1, float green1, float bright)
    {
		
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
       
        
		
		wr.pos(12, 32, 0).color(0.5f, 0.5f, 0.5f, 0.7f).endVertex();
		wr.pos(20, 32, 0).color(0.5f, 0.5f, 0.5f, 0.7f).endVertex();
		wr.pos(20, 32*bright, 0).color(red1/255, blue1/255, green1/255, 0.7f).endVertex();
		wr.pos(12, 32*bright, 0).color(red1/255, blue1/255, green1/255, 0.7f).endVertex();
		
		

    	
	    
    }

	public void drawSat1(float red1, float blue1, float green1, float sat)
    {


    	
		//WorldRenderer wr = Tessellator.getInstance().getWorldRenderer();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
       
		
		wr.pos(24, 32, 0).color(0.5f, 0.5f, 0.5f, 0.7f).endVertex();
		wr.pos(32, 32, 0).color(0.5f, 0.5f, 0.5f, 0.7f).endVertex();
		wr.pos(32, 32*sat, 0).color(red1/255, blue1/255, green1/255, 0.7f).endVertex();
		wr.pos(24, 32*sat, 0).color(red1/255, blue1/255, green1/255, 0.7f).endVertex();
		

    	
	    
    }
	
	public void drawWave2(float red1, float blue1, float green1, float wave)
    {
		
		
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
       
		
		int guiCentreYvalue = (int) (36+((int)(32*(1-wave))));
	
		
		wr.pos(0, guiCentreYvalue, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(8, guiCentreYvalue, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(8, 36, 0).color(0.5f, 0.5f, 0.5f, 0.7f).endVertex();
		wr.pos(0, 36, 0).color(0.5f, 0.5f, 0.5f, 0.7f).endVertex();
		

    	
	    
    }
	
	public void drawBright2(float red1, float blue1, float green1, float bright)
    {

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
       
        
		
		int guiCentreYvalue = (int) (36+((int)(32*(1-bright))));
		
		
		wr.pos(12, guiCentreYvalue, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(20, guiCentreYvalue, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(20, 36, 0).color(0.5f, 0.5f, 0.5f, 0.7f).endVertex();
		wr.pos(12, 36, 0).color(0.5f, 0.5f, 0.5f, 0.7f).endVertex();
		


    	
	    
    }

	public void drawSat2(float red1, float blue1, float green1, float sat)
    {

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
       
        
		
		int guiCentreYvalue = (int) (36+((int)(32*(1-sat))));
		
		
		wr.pos(24, guiCentreYvalue, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(32, guiCentreYvalue, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(32, 36, 0).color(0.5f, 0.5f, 0.5f, 0.7f).endVertex();
		wr.pos(24, 36, 0).color(0.5f, 0.5f, 0.5f, 0.7f).endVertex();
    	
	    
    }
	
	public void drawLine() {
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
        
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
        
        wr.pos(0, 35, 0).color(1.0f, 1.0f, 1.0f, 0.7f).endVertex();
		wr.pos(32, 35, 0).color(1.0f, 1.0f, 1.0f, 0.7f).endVertex();
		wr.pos(32, 33, 0).color(1.0f, 1.0f, 1.0f, 0.7f).endVertex();
		wr.pos(0, 33, 0).color(1.0f, 1.0f, 1.0f, 0.7f).endVertex();
        
	}
	
	public void drawIcons() {

		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution scaled = new ScaledResolution(mc);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();

    	
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        drawSlider();
		
		
	
	}
	

	public void drawSlider() {

		mc.renderEngine.bindTexture(rainCloud);
		drawTexturedModalRect(0, 0, 0, 0, 16, 16);
        
	}
	
	
	public void setcolor(ItemStack stack, int id)
	{
		Item item = stack.getItem();
		

		
		if(id == 0) {
		int  w = this.getItemWave(item, 0);
		double w1 = w - 380;
	    double p1 = (w1/401);
		 this.wave1 = (float) p1;
		 this.bright1 = this.getItemBright(item, 0);
		 this.sat1 = this.getItemSat(item,  0);
		}
		
		if(id == 1) {
		int Lw = this.getItemWave(item, 1);
		double w3 = Lw - 380;
	    double p2 = (w3/401);
		 this.wave2 = (float) p2;
		 this.bright2 = this.getItemBright(item, 1);
		 this.sat2 = this.getItemSat(item, 1);
		}
		

		 
	     
	     //System.out.println(p);
		 
	
		 //System.out.println(p + " " + wave1 );
	
		int w = this.getItemWave(item, id);
		
		setColor(w, id);
		
	
	}
	
	public void setColor(int w, int id) {
		
		 int c = 255;
		 
		 int Hue1 = 0;
		 int red1 = 0;
		 int blue1 = 0;
		 int green1 = 0;
		 
		 
		 int Hue2 = 0;
		 int red2 = 0;
		 int blue2 = 0;
		 int green2 = 0;

		 if((w >= 380) && (w<440)){
		    	float a = (-(w - 440)/(((float)440) - ((float)380)));
		    	float a2 = (-(w - 440)/(((float)440) - ((float)380)));

		    	if(id == 0)
		    	{
		        red1 = (int) (c * a);
		        green1 = 0;
		        blue1 = 1 * c;
		        Hue1 = getHue(red1, green1, blue1);
		        this.redF = (float)red1;
		        this.greenF = (float)green1;
		        this.blueF = (float)blue1;


		    	}else if(id == 1){
		    		red2 = (int) (c * a);
			        green2 = 0;
			        blue2 = 1 * c;
			        this.red2F = (float)red2;
			        this.green2F = (float)green2;
			        this.blue2F = (float)blue2;
		    	}
		        

		        
		        
		        
		    }else if((w >= 440) && (w<490)){
		    	float b = ((w - 440)/(((float)490 - ((float)440))));

		    	if(id == 0)
		    	{
		        red1 = 0;
		        green1 = (int) (c * b);
		        blue1 = 1 * c;
		        Hue1 = getHue(red1, green1, blue1);
		        this.redF = (float)red1;
		        this.greenF = (float)green1;
		        this.blueF = (float)blue1;
		        
		    	}else if(id == 1){
		    		red2 = 0;
			        green2 = (int) (c * b);
			        blue2 = 1 * c;
			        this.red2F = (float)red2;
			        this.green2F = (float)green2;
			        this.blue2F = (float)blue2;
		    	}

		        
		        
		    }else if((w >= 490) && (w<510)){
		    	float c2 = (-(w - 510)/(((float)510 - ((float)490))));

		    	if(id == 0)
		    	{
		        red1 = 0;
		        green1 = 1 * c;
		        blue1 = (int) (c * c2);
		        Hue1 = getHue(red1, green1, blue1);
		        this.redF = (float)red1;
		        this.greenF = (float)green1;
		        this.blueF = (float)blue1;
		    	}else if(id == 1){
		    		red2 = 0;
			        green2 = 1 * c;
			        blue2 = (int) (c * c2);
			        this.red2F = (float)red2;
			        this.green2F = (float)green2;
			        this.blue2F = (float)blue2;
		    	}

		        
		    }else if((w >= 510) && (w<580)){
		    	float d = ((w - 510)/(((float)580 - ((float)510))));

		    	if(id == 0)
		    	{
		        red1 = (int) (c * d);
		        green1 = 1 * c;
		        blue1 = 0;
		        Hue1 = getHue(red1, green1, blue1);
		        this.redF = (float)red1;
		        this.greenF = (float)green1;
		        this.blueF = (float)blue1;
		        
		    	}else if(id == 1){
		    		red2 = (int) (c * d);
			        green2 = 1 * c;
			        blue2 = 0;
			        this.red2F = (float)red2;
			        this.green2F = (float)green2;
			        this.blue2F = (float)blue2;
		    	}

		        
		        
		    }else if((w >= 580) && (w<645)){
		    	float e = (-(w - 645)/(((float)645 - 580)));

		    	if(id == 0)
		    	{
		        red1 = 1 * c;
		        green1 = (int) (c * e);
		        blue1 = 0;
		        Hue1 = getHue(red1, green1, blue1);
		        this.redF = (float)red1;
		        this.greenF = (float)green1;
		        this.blueF = (float)blue1;
		        
		    	}else if(id == 1){
		    		red2 = 1 * c;
			        green2 = (int) (c * e);
			        blue2 = 0;
			        this.red2F = (float)red2;
			        this.green2F = (float)green2;
			        this.blue2F = (float)blue2;
		    	}

		        
		    }else if((w >= 645) && (w<781)){
		    	if(id == 0)
		    	{
		        red1 = 1 * c;
		        green1 = 0;
		        blue1 = 0;
		        Hue1 = getHue(red1, green1, blue1);
		        this.redF = (float)red1;
		        this.greenF = (float)green1;
		        this.blueF = (float)blue1;
		        
		    	}else if(id == 1){
		    		red2 = 1 * c;
			        green2 = 0;
			        blue2 = 0;
			        this.red2F = (float)red2;
			        this.green2F = (float)green2;
			        this.blue2F = (float)blue2;
		    	}

		       
		        
		    }else{
		    	if(id == 0)
		    	{
		        red1 = 0;
		        green1 = 0;
		        blue1 = 0;
		        Hue1 = getHue(red1, green1, blue1);
		        this.redF = (float)red1;
		        this.greenF = (float)green1;
		        this.blueF = (float)blue1;
		        
		    	}else if(id == 1){
		    		red2 = 0;
			        green2 = 0;
			        blue2 = 0;
			        this.red2F = (float)red2;
			        this.green2F = (float)green2;
			        this.blue2F = (float)blue2;
		    	}

		    	
		    }
	}
	
	public int getItemWave(Item item, int id)
	{
		int length = EnumItemColor.listOfObjects.size();
		EnumItemColor.listOfObjects.clear();
		EnumItemColor.initColorList();
		
		System.out.println("Main = " + rightItemStack.getItem() + " Offhand = " + leftItemStack.getItem() );
		
		for (int i = 0; i < length; i++) 
		{
			ItemStack listItem = EnumItemColor.listOfObjects.get(i).getItemStack();
		
			if(listItem.getItem() == item)
			{
				if(listItem.getItem() == SlimeBreederItems.FILLEDSLIMECAPSULE)
				{
					if(id  == 0 && rightItemStack.getItem() == SlimeBreederItems.FILLEDSLIMECAPSULE ) {
					return rightItemStack.getTagCompound().getInteger("Wave");
					}else {
						if(id  == 1 && leftItemStack.getItem() == SlimeBreederItems.FILLEDSLIMECAPSULE ) {
						return leftItemStack.getTagCompound().getInteger("Wave");
						}
					}
				}else
			
			return EnumItemColor.listOfObjects.get(i).wavelength;
			}
		
			
		}
		return 380;
	}
	
	public float getItemBright(Item item, int id)
	{
		int length = EnumItemColor.listOfObjects.size();
		EnumItemColor.listOfObjects.clear();
		EnumItemColor.initColorList();
		
		for (int i = 0; i < length; i++) 
		{
			ItemStack listItem = EnumItemColor.listOfObjects.get(i).getItemStack();
		
			if(listItem.getItem() == item)
			{
				if(listItem.getItem() == SlimeBreederItems.FILLEDSLIMECAPSULE)
				{
					if(id  == 0) {
					return rightItemStack.getTagCompound().getFloat("Bright");
					}else {
						return leftItemStack.getTagCompound().getFloat("Bright");
					}
				}else
			
			return EnumItemColor.listOfObjects.get(i).brightness;
			}
		
			
		}
		return 1.0F;
	}
	
	public float getItemSat(Item item, int id)
	{
		int length = EnumItemColor.listOfObjects.size();
		EnumItemColor.listOfObjects.clear();
		EnumItemColor.initColorList();
		
		for (int i = 0; i < length; i++) 
		{
			ItemStack listItem = EnumItemColor.listOfObjects.get(i).getItemStack();
		
			if(listItem.getItem() == item)
			{
				if(listItem.getItem() == SlimeBreederItems.FILLEDSLIMECAPSULE)
				{
					if(id  == 0) {
					return rightItemStack.getTagCompound().getFloat("Sat");
					}else {
						return leftItemStack.getTagCompound().getFloat("Sat");
					}
				}else
			
			return EnumItemColor.listOfObjects.get(i).saturation;
			}
		
			
		}
		return 1.0F;
	}
	
	public int getHue(int red, int green, int blue) {

	    float min = Math.min(Math.min(red, green), blue);
	    float max = Math.max(Math.max(red, green), blue);

	    float hue = 0f;
	    if (max == red) {
	        hue = (green - blue) / (max - min);

	    } else if (max == green) {
	        hue = 2f + (blue - red) / (max - min);

	    } else {
	        hue = 4f + (red - green) / (max - min);
	    }

	    hue = hue * 60;
	    if (hue < 0) hue = hue + 360;


	    return Math.round(hue);
	}

	

}
