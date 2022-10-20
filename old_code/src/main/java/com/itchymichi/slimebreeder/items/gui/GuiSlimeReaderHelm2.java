package com.itchymichi.slimebreeder.items.gui;

import org.lwjgl.opengl.GL11;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.entity.monster.EntityLivingSlimeBase;
import com.itchymichi.slimebreeder.lists.EnumItemColor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiSlimeReaderHelm2 extends Gui {
	
	private final ResourceLocation heart = new ResourceLocation(SlimeBreeder.MODID, "textures/gui/heartfilled.png");
	private final ResourceLocation chicken = new ResourceLocation(SlimeBreeder.MODID, "textures/gui/chickenbar.png");
	

    private final int tex_width = 256, tex_height = 256, bar_width = 100, bar_height = 100;
    
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
	
	float domestication = 0;
	int Hunger = 0;
	int HungerMax = 0;
	
	private static ResourceLocation slider;
	private static ResourceLocation rainCloud;
	
	public ItemStack rightItemStack = new ItemStack(Item.getItemFromBlock(Blocks.IRON_ORE));
	public ItemStack leftItemStack = new ItemStack(Item.getItemFromBlock(Blocks.IRON_ORE));
	


	@SubscribeEvent(priority = EventPriority.LOWEST)
    public void renderOverlay(RenderGameOverlayEvent.Pre event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
        	
        	GL11.glPushMatrix();
        	GlStateManager.scale(0.65, 0.65, 0);
        	GlStateManager.translate(250, 30, 0);
        	drawSlimeHUD();

        	GL11.glPopMatrix();

        }
    }
	
	@SubscribeEvent
	public void DrawBlockHighlightEvent(DrawBlockHighlightEvent event)
	{
		////System.out.println(event.getTarget().entityHit.getClass());
		
		if(event.getTarget().entityHit instanceof EntityLivingSlimeBase)
		{
			
			EntityLivingSlimeBase slime = (EntityLivingSlimeBase) event.getTarget().entityHit;
			
			if(slime instanceof EntityColorSlimeBase) {
				
				
				this.domestication = ((float) ((slime.getServerDomestication())/5000.0F));
				
				if(this.domestication > 1.0) {
					this.domestication = 1.0F;
				}
				
				this.Hunger = slime.getServerHunger();
				this.HungerMax = ((int)(slime.getServerBreedingSpeed()/2));
				
				if(this.HungerMax == 0) {
					this.HungerMax = this.Hunger;
					
					slime.setBreedingSpeed(Hunger*2);
				}
				
				//System.out.println(slime.getServerDomestication());

						
			}
			

			
			
		}else {
			this.domestication = 0.0F;
			this.Hunger = 0;
			this.HungerMax = 0;
		}
	}
	
   
    public void drawSlimeHUD() {
    	
    		Minecraft mc = Minecraft.getMinecraft();
    		EntityPlayer player = mc.world.getPlayerEntityByUUID(mc.player.getUniqueID());
    		ItemStack helm = new ItemStack(SlimeBreederItems.SLIMEINFUSEDHELM); 
    		
    		
    			Iterable<ItemStack> armour = player.getArmorInventoryList();		
    			
    			for(ItemStack stack : armour)
    			{
    				if(stack.getItem() instanceof ItemArmor)
    	    		{	
    					if(((ItemArmor) stack.getItem()) == ((ItemArmor) stack.getItem())) {
    						
    						ScaledResolution scaled = new ScaledResolution(mc);
    			    		int width = scaled.getScaledWidth();
    			    		int height = scaled.getScaledHeight();
    			    		
    			    		int posX = (int) (width/1.2);
    			    		int posY = height;
    			    		
    				    	this.rightItemStack = player.getHeldItemOffhand();
    						this.leftItemStack = player.getHeldItemMainhand();
    						
    				
    						setcolor(this.rightItemStack, 0);
    						setcolor(this.leftItemStack, 1);
    						
    						
    						
    						//System.out.println(domestication);
    						

    			        //float oneUnit = (float)bar_width / mc.player.getMaxHealth();
    			        //int currentWidth = (int)(oneUnit * mc.player.getHealth());
    			        //System.out.println((int)mc.player.getHealth());
    			        GL11.glPushMatrix();
    			        GlStateManager.enableAlpha();
    			        GlStateManager.disableTexture2D();
    			        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
    			        GL11.glEnable(GL11.GL_BLEND);
    			    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    			        //GL11.glShadeModel(GL11.GL_SMOOTH);
    			        GlStateManager.translate(posX-20, 0, 0);
    			        	drawBackground();
    			        GlStateManager.translate(20, 0, 0);
    			        
    			        
    			        	
    				    	drawLine();
    						
    				        if(wave1 > 0) {
    				        //drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor);

    						drawWave1(redF, greenF, blueF, wave1);
    						drawBright1(redF, greenF, blueF, bright1);
    						drawSat1(redF, greenF, blueF, sat1);
    				        }
    				        if(wave2 > 0 ) {
    						drawWave2(red2F, green2F, blue2F, wave2);
    						drawBright2(red2F, green2F, blue2F, bright2);
    						drawSat2(red2F, green2F, blue2F, sat2);
    				        }
    				        
    				    //GlStateManager.translate(posX-15, 0, 0);
    				    
    				    GlStateManager.translate(-20, 75, 0);
    			        drawBackground();
    			        GlStateManager.translate(20, -75, 0);
    				        
    				    drawHeart();
    				    
    				    GlStateManager.translate(-20, 150, 0);
    			        drawBackground();
    			        drawHunger();
    			        GlStateManager.translate(20, -150, 0);
    			        
    			        
    				    //drawHunger();
    				        
    					
    					GlStateManager.enableTexture2D();
    				    GlStateManager.disableBlend();
    				    //GlStateManager.disableAlpha();
    				    //GL11.glEnable(GL11.GL_LIGHTING);
    				    GL11.glPopMatrix();
    					}
    	    		}
    					
    			}	
    		
    		

	    }

    
	public void drawWave1(float red1, float blue1, float green1, float wave)
    {

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
       
        
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
		
		wr.pos(0, 32, 0).color(0.5f, 0.5f, 0.5f, 0.5f).endVertex();
		wr.pos(8, 32, 0).color(0.5f, 0.5f, 0.5f, 0.5f).endVertex();
		wr.pos(8, 32*wave, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(0, 32*wave, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		tessellator.draw();
		


    	
	    
    }
	
	public void drawBright1(float red1, float blue1, float green1, float bright)
    {
		
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
       
        
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
		wr.pos(12, 32, 0).color(0.5f, 0.5f, 0.5f, 0.5f).endVertex();
		wr.pos(20, 32, 0).color(0.5f, 0.5f, 0.5f, 0.5f).endVertex();
		wr.pos(20, 32*bright, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(12, 32*bright, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		tessellator.draw();
		

    	
	    
    }

	public void drawSat1(float red1, float blue1, float green1, float sat)
    {


    	
		//WorldRenderer wr = Tessellator.getInstance().getWorldRenderer();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
       
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
		wr.pos(24, 32, 0).color(0.5f, 0.5f, 0.5f, 0.5f).endVertex();
		wr.pos(32, 32, 0).color(0.5f, 0.5f, 0.5f, 0.5f).endVertex();
		wr.pos(32, 32*sat, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(24, 32*sat, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		tessellator.draw();

    	
	    
    }
	
	public void drawWave2(float red1, float blue1, float green1, float wave)
    {
		
		
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
       
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
		int guiCentreYvalue = (int) (36+((int)(32*(1-wave))));
	
		
		wr.pos(0, guiCentreYvalue, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(8, guiCentreYvalue, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(8, 36, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(0, 36, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		tessellator.draw();

    	
	    
    }
	
	public void drawBright2(float red1, float blue1, float green1, float bright)
    {

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
       
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
		
		int guiCentreYvalue = (int) (36+((int)(32*(1-bright))));
		
		
		wr.pos(12, guiCentreYvalue, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(20, guiCentreYvalue, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(20, 36, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(12, 36, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		tessellator.draw();


    	
	    
    }

	public void drawSat2(float red1, float blue1, float green1, float sat)
    {

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
       
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
		
		int guiCentreYvalue = (int) (36+((int)(32*(1-sat))));
		
		
		wr.pos(24, guiCentreYvalue, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(32, guiCentreYvalue, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(32, 36, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		wr.pos(24, 36, 0).color(red1/255, blue1/255, green1/255, 0.5f).endVertex();
		tessellator.draw();
	    
    }
	
	public void drawLine() {
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
        
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
        
        wr.pos(0, 35, 0).color(1.0f, 1.0f, 1.0f, 0.7f).endVertex();
		wr.pos(32, 35, 0).color(1.0f, 1.0f, 1.0f, 0.7f).endVertex();
		wr.pos(32, 33, 0).color(1.0f, 1.0f, 1.0f, 0.7f).endVertex();
		wr.pos(0, 33, 0).color(1.0f, 1.0f, 1.0f, 0.7f).endVertex();
		tessellator.draw();
	}
	
	public void drawBackground() {
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
        
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
        
        wr.pos(0, 70, 0).color(0.2f, 0.2f, 0.2f, 0.2f).endVertex();
		wr.pos(73, 70, 0).color(0.2f, 0.2f, 0.2f, 0.2f).endVertex();
		wr.pos(73, 0, 0).color(0.2f, 0.2f, 0.2f, 0.2f).endVertex();
		wr.pos(0, 0, 0).color(0.2f, 0.2f, 0.2f, 0.2f).endVertex();
		tessellator.draw();
	}
	
	public void drawHeart(){
		
		float love = this.domestication;
		
		GL11.glPushMatrix();
		GlStateManager.enableTexture2D();
		GlStateManager.scale(0.25, 0.25, 0);
		GlStateManager.translate(-64, 375, 0);
		GlStateManager.rotate(180, 0, 0, 0);
		GlStateManager.translate(-256, -128, 0);
        Minecraft mc = Minecraft.getMinecraft();
        mc.renderEngine.bindTexture(heart);
        GlStateManager.translate(+9, +4, 0);
        mc.ingameGUI.drawTexturedModalRect(0, 0, 0, 0, 240, (int) ((120*love)));
        GlStateManager.translate(-9, -4, 0);
        //GlStateManager.translate(-1000, 0, 0);
        mc.ingameGUI.drawTexturedModalRect(0, 0, 0, 123, 252, 131);
		//mc.ingameGUI.drawTexturedModalRect(0, 0, 0, 159, 193, 171);
		GlStateManager.disableTexture2D();
		GL11.glPopMatrix();
	}
	
	public void drawHunger(){
		

		
		float hungry = ((float)this.Hunger/this.HungerMax);
		
		//System.out.println(this.Hunger + " / " + this.HungerMax);
		
		GL11.glPushMatrix();
		GlStateManager.enableTexture2D();
		GlStateManager.scale(0.25, 0.25, 0);
		GlStateManager.translate(25, 75, 0);
		GlStateManager.rotate(180, 0, 0, 0);
		GlStateManager.translate(-256, -128, 0);
        Minecraft mc = Minecraft.getMinecraft();
        mc.renderEngine.bindTexture(chicken);
        GlStateManager.translate(+0, +4, 0);
        mc.ingameGUI.drawTexturedModalRect(0, 0, 0, 0, 240, (int) ((120*hungry)));
        GlStateManager.translate(-0, -4, 0);
        //GlStateManager.translate(-1000, 0, 0);
        mc.ingameGUI.drawTexturedModalRect(0, 0, 0, 125, 252, 131);
		//mc.ingameGUI.drawTexturedModalRect(0, 0, 0, 159, 193, 171);
		GlStateManager.disableTexture2D();
		GL11.glPopMatrix();
	}
	
    
	public void setcolor(ItemStack stack, int id)
	{
		Item item = stack.getItem();
		

		
		if(id == 0) {
			EnumItemColor.listOfObjects.clear();
			EnumItemColor.initColorList();
		int  w = this.getItemWave(item, 0);
		double w1 = w - 380;
	    double p1 = (w1/401);
		 this.wave1 = (float) p1;
		 this.bright1 = this.getItemBright(item, 0);
		 this.sat1 = this.getItemSat(item,  0);
		}
		
		if(id == 1) {
			EnumItemColor.listOfObjects.clear();
			EnumItemColor.initColorList();
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
		//int length = EnumItemColor.listOfObjects.size();
		EnumItemColor.listOfObjects.clear();
		EnumItemColor.initColorList();
		
		//System.out.println("Main = " + rightItemStack.getItem() + " Offhand = " + leftItemStack.getItem() );
		
		for (int i = 0; i < 85; i++) 
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
		//int length = EnumItemColor.listOfObjects.size();
		EnumItemColor.listOfObjects.clear();
		EnumItemColor.initColorList();
		
		for (int i = 0; i < 85; i++) 
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
		//int length = EnumItemColor.listOfObjects.size();
		EnumItemColor.listOfObjects.clear();
		EnumItemColor.initColorList();
		
		for (int i = 0; i < 85; i++) 
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
