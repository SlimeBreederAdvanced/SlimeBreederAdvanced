package com.itchymichi.slimebreeder.items.gui;

import org.lwjgl.opengl.GL11;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.inventory.InventorySlimeReader;
import com.itchymichi.slimebreeder.items.FilledSlimeCapsule;
import com.itchymichi.slimebreeder.items.containers.ContainerSlimeReader;
import com.itchymichi.slimebreeder.lists.EnumItemColor;
import com.itchymichi.slimebreeder.lists.SlimeColor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class GuiSlimeReader extends GuiContainer 
{
	private static final ResourceLocation texture = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/SlimeReader.png");

	
	private int currentScreen = 0;
	private int xSize = 227;
	private int ySize = 163;
	

	
	
	
	private int Lwave;
	private float Lsat;
	private float Lbrightness;
	
	private int Rwave;
	private float Rsat;
	private float Rbrightness;
	
	private Slot theSlot;
	
	
	float redF;
	float greenF;
	float blueF;
	
	float red2F;
	float green2F;
	float blue2F;
	
	int similarity;

	public static int readerTotalPages = 1;
	
	private static ResourceLocation[] slimeReaderTextures = new ResourceLocation[readerTotalPages];

	
	public static final int GUI_ID = 0;

	
	 /** The old x position of the mouse pointer */
    private float oldMouseX;
    /** The old y position of the mouse pointer */
    private float oldMouseY;

	
    private final InventoryPlayer inventoryPlayer;
    private final IInventory inventorySlimeReader;

    
	public GuiSlimeReader(InventoryPlayer invPlayer, IInventory invSlimeReader)
	{
		super(new ContainerSlimeReader(invPlayer, invSlimeReader));
		this.inventoryPlayer = invPlayer;
		this.inventorySlimeReader = invSlimeReader;
		
		
		slimeReaderTextures[0] = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/SlimeReader.png");
		
	}
	
	
	
	
	
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui() 
    {
    	super.initGui();
    	
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.allowUserInput = true;
        
        getColor(inventorySlimeReader);
        
        
    	
    }
    
    /**
     * Called from the main game loop to update the screen.
     */
   
    
    
    @Override
    public void drawScreen(int parWidth, int parHeight, float p_73863_3_)
    {
    	
    	
    	
    	 super.drawScreen(parWidth, parHeight, p_73863_3_);

    }
    
    
    public void drawSat(float red1, float blue1, float green1)
    {
    	
    	
    	
    	
    	double guiCentreX = (this.width - this.xSize) / 2;
    	double guiCentreY = (this.height - this.ySize) / 2;
    	
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_LIGHTING);

    	
		//WorldRenderer wr = Tessellator.getInstance().getWorldRenderer();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
       
        
		wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
		
		wr.pos(guiCentreX+59, guiCentreY+53, 0).color(0.5f, 0.5f, 0.5f, 1.0f).endVertex();
		wr.pos(guiCentreX+111, guiCentreY+53, 0).color(0.5f, 0.5f, 0.5f, 1.0f).endVertex();
		wr.pos(guiCentreX+111, guiCentreY+7, 0).color(red1/255, blue1/255, green1/255, 1.0f).endVertex();
		wr.pos(guiCentreX+59, guiCentreY+7, 0).color(red1/255, blue1/255, green1/255, 1.0f).endVertex();
		

		
		Tessellator.getInstance().draw();
		
		GlStateManager.enableTexture2D();
	    GlStateManager.disableBlend();
	    GL11.glEnable(GL11.GL_LIGHTING);
	    
	    
    }
    
    
    public void similarity()
    {
    	
    	
    	
    	int Bwave;
    	int Swave;
    	
    	double Bsat;
    	double Ssat;
    	
    	double Bbright;
    	double Sbright;
    	
    	
    	if(Rwave > Lwave)
    	{
    		Bwave = Rwave + 1;
    		Swave = Lwave + 1;
    	}else
    	{
    		Bwave = Lwave + 1;
    		Swave = Rwave + 1;
    	}
    	
    	if(Rsat > Lsat)
    	{
    		Bsat = Rsat + 0.01;
    		Ssat = Lsat + 0.01;
    	}else
    	{
    		Bsat = Lsat + 0.01;
    		Ssat = Rsat + 0.01;
    	}
    	
    	if(Rbrightness > Lbrightness)
    	{
    		Bbright = Rbrightness + 0.01;
    		Sbright = Lbrightness + 0.01;
    	}else
    	{
    		Bbright = Lbrightness + 0.01;
    		Sbright = Rbrightness + 0.01;
    	}
    	
    	int Swavelength = (1-((Bwave-Swave)/Bwave));
    	double Ssaturation = (1-((Bsat-Ssat)/Bsat));;
    	double Sbrightness = (1-((Bbright-Sbright)/Bbright));

    	
    	
    	this.similarity = (int) ((((Swavelength + Ssaturation + Sbrightness)/3)*100));

    	
    }
    
    

    public void drawSat2(float red2, float blue2, float green2)
{
    	

    	
    	double guiCentreX = (this.width - this.xSize) / 2;
    	double guiCentreY = (this.height - this.ySize) / 2;
    	
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_LIGHTING);
        
    	
		//WorldRenderer wr = Tessellator.getInstance().getWorldRenderer();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
        
		wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
		
		wr.pos((guiCentreX+59)+57, (guiCentreY+53), 0).color(0.5f, 0.5f, 0.5f, 1.0f).endVertex();
		wr.pos((guiCentreX+111)+57, (guiCentreY+53), 0).color(0.5f, 0.5f, 0.5f, 1.0f).endVertex();
		wr.pos((guiCentreX+111)+57, (guiCentreY+7), 0).color(red2/255, blue2/255, green2/255, 1.0f).endVertex();
		wr.pos((guiCentreX+59)+57, (guiCentreY+7), 0).color(red2/255, blue2/255, green2/255, 1.0f).endVertex();
		
		
		Tessellator.getInstance().draw();
		
		GlStateManager.enableTexture2D();
	    GlStateManager.disableBlend();
	    GL11.glEnable(GL11.GL_LIGHTING);
    }





	public void drawGreyL()
    {
		

    	
    	double guiCentreX = (this.width - this.xSize) / 2;
    	double guiCentreY = (this.height - this.ySize) / 2;
    	
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_LIGHTING);
        

    	
		//WorldRenderer wr = Tessellator.getInstance().getWorldRenderer();
        
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
        
		wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
		
		wr.pos(guiCentreX+59, guiCentreY+53, 0).color(0.0f, 0.0f, 0.0f, 1.0f).endVertex();
		wr.pos(guiCentreX+111, guiCentreY+53, 0).color(0.5f, 0.5f, 0.5f, 0.2f).endVertex();
		wr.pos(guiCentreX+111, guiCentreY+7, 0).color(0.5f, 0.5f, 0.5f, 0.2f).endVertex();
		wr.pos(guiCentreX+59, guiCentreY+7, 0).color(0.0f, 0.0f, 0.0f, 1.0f).endVertex();
		
		
		Tessellator.getInstance().draw();
		
		GlStateManager.enableTexture2D();
	    GlStateManager.disableBlend();
	    GL11.glEnable(GL11.GL_LIGHTING);
		
    }
    
    public void drawGreyR()
    {
    	double guiCentreX = (this.width - this.xSize) / 2;
    	double guiCentreY = (this.height - this.ySize) / 2;
    	
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_LIGHTING);
    	
		//WorldRenderer wr = Tessellator.getInstance().getWorldRenderer();
        
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
        
		wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
		
		wr.pos((guiCentreX+59)+57, guiCentreY+53, 0).color(0.0f, 0.0f, 0.0f, 1.0f).endVertex();
		wr.pos((guiCentreX+111)+57, guiCentreY+53, 0).color(0.5f, 0.5f, 0.5f, 0.2f).endVertex();
		wr.pos((guiCentreX+111)+57, guiCentreY+7, 0).color(0.5f, 0.5f, 0.5f, 0.2f).endVertex();
		wr.pos((guiCentreX+59)+57, guiCentreY+7, 0).color(0.0f, 0.0f, 0.0f, 1.0f).endVertex();
		

		
		Tessellator.getInstance().draw();
		
		GlStateManager.enableTexture2D();
	    GlStateManager.disableBlend();
	    GL11.glEnable(GL11.GL_LIGHTING);
    }
    
    
    public void drawCrosshairL()
    {
    	double guiCentreX = (this.width - this.xSize) / 2;
    	double guiCentreY = (this.height - this.ySize) / 2;
    	
    	double x = 52 * Lsat;
    	double y = 46 * Lbrightness;
    	
    	
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_LIGHTING);
        
    	
		//WorldRenderer wr = Tessellator.getInstance().getWorldRenderer();\
        
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
        
		wr.begin(1, DefaultVertexFormats.POSITION_COLOR);
		
		wr.pos((guiCentreX+59), (guiCentreY+7)+y, 0).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
		wr.pos((guiCentreX+111), (guiCentreY+7)+y, 0).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
		
		wr.pos((guiCentreX+59)+x, guiCentreY+7, 0).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
		wr.pos((guiCentreX+59)+x, guiCentreY+53, 0).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
		
		
		Tessellator.getInstance().draw();
		
		GlStateManager.enableTexture2D();
	    GlStateManager.disableBlend();	
	    GL11.glEnable(GL11.GL_LIGHTING);
    		
    }
    
    public void drawCrosshairR()
    {
    	double guiCentreX = (this.width - this.xSize) / 2;
    	double guiCentreY = (this.height - this.ySize) / 2;
    	
    	double x = 52 * Rsat;
    	double y = 46 * Rbrightness;
    	
    	
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_LIGHTING);
        
    	
		//WorldRenderer wr = Tessellator.getInstance().getWorldRenderer();
        
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();
        
		wr.begin(1, DefaultVertexFormats.POSITION_COLOR);
		
		wr.pos((guiCentreX+59)+57, (guiCentreY+7)+y, 0).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
		wr.pos((guiCentreX+111)+57, (guiCentreY+7)+y, 0).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
		
		wr.pos(((guiCentreX+59)+57)+x, guiCentreY+7, 0).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
		wr.pos(((guiCentreX+59)+57)+x, guiCentreY+53, 0).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
		

		
		Tessellator.getInstance().draw();
		
		GlStateManager.enableTexture2D();
	    GlStateManager.disableBlend();	
	    GL11.glEnable(GL11.GL_LIGHTING);
    		
    }
    
    public void drawText()
    {
    	float guiCentreX = (this.width - this.xSize) / 2;
    	float guiCentreY = (this.height - this.ySize) / 2;
    	String Similarity = "Similarity : " + similarity+"%";
    
    	GL11.glDisable(GL11.GL_LIGHTING);
    	
		this.mc.fontRenderer.drawStringWithShadow(Similarity, guiCentreX + 73, guiCentreY + 63, 0xffffffff);
		
		GL11.glEnable(GL11.GL_LIGHTING);
    }


	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	 {
		
		
		
   	 this.mc.player.openContainer = this.inventorySlots;
   	 getColor(inventorySlimeReader);
   	 
   	 
   	 	drawBack();
    	drawSat(redF, greenF, blueF);
    	drawSat2(red2F, green2F, blue2F);
    	drawGreyL();
    	drawGreyR();
    	drawCrosshairL();
    	drawCrosshairR();
    	similarity();
    	drawText();
    
        
    }
	
	public void drawBack()
    {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		
		
        this.mc.getTextureManager().bindTexture(slimeReaderTextures[0]);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
	
	@Override
	public void updateScreen()
    {
        super.updateScreen();

    }
    
	
	
	public void getColor(IInventory invSlimeReader)
	{
		invSlimeReader = inventorySlimeReader;
		
		Item leftSlot = ((InventorySlimeReader) invSlimeReader).getLeftItem();
		Item rightSlot = ((InventorySlimeReader) invSlimeReader).getRightItem();
		ItemStack leftSlotStack = ((InventorySlimeReader) invSlimeReader).getLeftItemStack();
		ItemStack rightSlotStack = ((InventorySlimeReader) invSlimeReader).getRightItemStack();
		
		this.Lwave = ((InventorySlimeReader) invSlimeReader).getLeftItemWave(((InventorySlimeReader) invSlimeReader).getLeftItem());
		this.Lsat = ((InventorySlimeReader) invSlimeReader).getLeftItemSat(((InventorySlimeReader) invSlimeReader).getLeftItem());
		this.Lbrightness = ((InventorySlimeReader) invSlimeReader).getLeftItemBright(((InventorySlimeReader) invSlimeReader).getLeftItem());
		
		this.Rwave = ((InventorySlimeReader) invSlimeReader).getRightItemWave(((InventorySlimeReader) invSlimeReader).getRightItem());
		this.Rsat = ((InventorySlimeReader) invSlimeReader).getRightItemSat(((InventorySlimeReader) invSlimeReader).getRightItem());
		this.Rbrightness = ((InventorySlimeReader) invSlimeReader).getRightItemBright(((InventorySlimeReader) invSlimeReader).getRightItem());
		
		setcolor(Lwave, 0);
		setcolor(Rwave, 1);
	}
	
	

	
	
	public void setcolor(int w, int id)
	{

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

	@Override
	public void drawDefaultBackground()
	{
		
	}
	

	
	
	
}