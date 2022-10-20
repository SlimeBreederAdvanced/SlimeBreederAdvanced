package com.itchymichi.slimebreeder.items.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.SlimeBreederItems;
import com.itchymichi.slimebreeder.inventory.InventorySlimeReader;
import com.itchymichi.slimebreeder.items.FilledSlimeCapsule;
import com.itchymichi.slimebreeder.items.Slimepedia;
import com.itchymichi.slimebreeder.items.containers.ContainerSlimeReader;
import com.itchymichi.slimebreeder.lists.EnumCraftingIngredients;
import com.itchymichi.slimebreeder.lists.EnumItemColor;
import com.itchymichi.slimebreeder.lists.EnumRecipes;
import com.itchymichi.slimebreeder.lists.SlimeColor;
import com.itchymichi.slimebreeder.render.entity.RenderEntitySlime2.RenderEntitySlime2Factory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class GuiSlimepedia  extends GuiScreen
{
private final int bookImageHeight = 192;
private final int bookImageWidth = 192;
private int currPage = 0;
private int currPageSub = 0;
private static int bookTotalPages = 10;

float guiCentreX = (mc.getMinecraft().displayWidth/25);
float guiCentreY = (mc.getMinecraft().displayHeight/40);

float LeftguiLeft = (guiCentreX);
float RightguiLeft = LeftguiLeft + 145;
float guiTop=(guiCentreY);

int startWidth;
int startHeight;

private ScaledResolution res = new ScaledResolution(mc.getMinecraft());

int scaledWidth = res.getScaledWidth();
int scaledHeight = res.getScaledHeight();

int timer = 0;
boolean added = false;

int factorW = (scaledWidth - 480)/res.getScaleFactor();
int factorH = (scaledHeight - 255)/res.getScaleFactor();

private static ResourceLocation[] bookPageTexturesLeft = new ResourceLocation[bookTotalPages];
private static ResourceLocation[] bookPageTexturesRight = new ResourceLocation[bookTotalPages];

private static ResourceLocation purpleSlime;

private static ResourceLocation forestIco;
private static ResourceLocation swampIco;
private static ResourceLocation taigaIco;

private static ResourceLocation heart;
private static ResourceLocation chicken;


private static ResourceLocation greenSlime;
private static ResourceLocation greenSlimePop;

private static ResourceLocation craftArrow;
private static ResourceLocation craftArrowComplete;

private static ResourceLocation nameBar;
private static ResourceLocation frame;
private static ResourceLocation rainCloud;
private static ResourceLocation dna;

private static ResourceLocation grid;
private static ResourceLocation result;



private static String[] stringPageTextLefta = new String[bookTotalPages];
private static String[] stringPageTextLeftb = new String[bookTotalPages];
private static String[] stringPageTextRighta = new String[bookTotalPages];
private static String[] stringPageTextRightb = new String[bookTotalPages];

//private GuiButton buttonDone;
private GuiButton buttonIntroduction;
private GuiButton buttonSlimesFind;
private GuiButton buttonSlimeCare;
private GuiButton buttonSlimeBreeding;
private GuiButton buttonSlimeGenetics;
private GuiButton buttonSlimeCrafting;
private GuiButton buttonSlimeWarnings;


private NextPageButton buttonNextPage;
private NextPageButton buttonPreviousPage;
private HomeButton buttonHome;
//private NextSubPageButton buttonNextSubPage;
//private SignButton buttonSign;
private String playerName;
private EntityPlayer playerBreeder;
private EnumHand breederHand;
private World playerWorld;
private ItemStack playerBook;
private NBTTagCompound bookNBT;


private EntitySlime slime;

TextComponentTranslation title1 = new TextComponentTranslation("guiSlimepedia.book.title1");
TextComponentTranslation title2 = new TextComponentTranslation("guiSlimepedia.book.title2");
TextComponentTranslation title3 = new TextComponentTranslation("guiSlimepedia.book.title3");
TextComponentTranslation title4 = new TextComponentTranslation("guiSlimepedia.book.title4");
TextComponentTranslation title5 = new TextComponentTranslation("guiSlimepedia.book.title5");
TextComponentTranslation title6 = new TextComponentTranslation("guiSlimepedia.book.title6");
TextComponentTranslation title7 = new TextComponentTranslation("guiSlimepedia.book.title7");

TextComponentTranslation stat1 = new TextComponentTranslation("guiSlimepedia.book.stat1");
TextComponentTranslation stat2 = new TextComponentTranslation("guiSlimepedia.book.stat2");
TextComponentTranslation stat3 = new TextComponentTranslation("guiSlimepedia.book.stat3");
TextComponentTranslation stat4 = new TextComponentTranslation("guiSlimepedia.book.stat4");
TextComponentTranslation stat5 = new TextComponentTranslation("guiSlimepedia.book.stat5");

TextComponentTranslation left1 = new TextComponentTranslation("guiSlimepedia.book.left1");
TextComponentTranslation left2 = new TextComponentTranslation("guiSlimepedia.book.left2");
TextComponentTranslation left3 = new TextComponentTranslation("guiSlimepedia.book.left3");
TextComponentTranslation left4 = new TextComponentTranslation("guiSlimepedia.book.left4");
TextComponentTranslation left5 = new TextComponentTranslation("guiSlimepedia.book.left5");
TextComponentTranslation left6 = new TextComponentTranslation("guiSlimepedia.book.left6");
TextComponentTranslation left7 = new TextComponentTranslation("guiSlimepedia.book.left7");
TextComponentTranslation left8 = new TextComponentTranslation("guiSlimepedia.book.left8");
TextComponentTranslation left9 = new TextComponentTranslation("guiSlimepedia.book.left9");
TextComponentTranslation left10 = new TextComponentTranslation("guiSlimepedia.book.left10");
TextComponentTranslation left11 = new TextComponentTranslation("guiSlimepedia.book.left11");
TextComponentTranslation left12 = new TextComponentTranslation("guiSlimepedia.book.left12");
TextComponentTranslation left13 = new TextComponentTranslation("guiSlimepedia.book.left13");
TextComponentTranslation left14 = new TextComponentTranslation("guiSlimepedia.book.left14");
TextComponentTranslation left15 = new TextComponentTranslation("guiSlimepedia.book.left15");
TextComponentTranslation left16 = new TextComponentTranslation("guiSlimepedia.book.left16");
TextComponentTranslation left17 = new TextComponentTranslation("guiSlimepedia.book.left17");
TextComponentTranslation left18 = new TextComponentTranslation("guiSlimepedia.book.left18");
TextComponentTranslation left19 = new TextComponentTranslation("guiSlimepedia.book.left19");
TextComponentTranslation left20 = new TextComponentTranslation("guiSlimepedia.book.left20");
TextComponentTranslation left21 = new TextComponentTranslation("guiSlimepedia.book.left21");
TextComponentTranslation left22 = new TextComponentTranslation("guiSlimepedia.book.left22");
TextComponentTranslation left23 = new TextComponentTranslation("guiSlimepedia.book.left23");
TextComponentTranslation left24 = new TextComponentTranslation("guiSlimepedia.book.left24");
TextComponentTranslation left25 = new TextComponentTranslation("guiSlimepedia.book.left25");
TextComponentTranslation left26 = new TextComponentTranslation("guiSlimepedia.book.left26");
TextComponentTranslation left27 = new TextComponentTranslation("guiSlimepedia.book.left27");
TextComponentTranslation left28 = new TextComponentTranslation("guiSlimepedia.book.left28");
TextComponentTranslation left29 = new TextComponentTranslation("guiSlimepedia.book.left29");
TextComponentTranslation left30 = new TextComponentTranslation("guiSlimepedia.book.left30");
TextComponentTranslation left31 = new TextComponentTranslation("guiSlimepedia.book.left31");
TextComponentTranslation left32 = new TextComponentTranslation("guiSlimepedia.book.left32");
TextComponentTranslation left33 = new TextComponentTranslation("guiSlimepedia.book.left33");
TextComponentTranslation left34 = new TextComponentTranslation("guiSlimepedia.book.left34");
TextComponentTranslation left35 = new TextComponentTranslation("guiSlimepedia.book.left35");
TextComponentTranslation left36 = new TextComponentTranslation("guiSlimepedia.book.left36");


TextComponentTranslation right1 = new TextComponentTranslation("guiSlimepedia.book.right1");
TextComponentTranslation right2 = new TextComponentTranslation("guiSlimepedia.book.right2");
TextComponentTranslation right3 = new TextComponentTranslation("guiSlimepedia.book.right3");
TextComponentTranslation right4 = new TextComponentTranslation("guiSlimepedia.book.right4");
TextComponentTranslation right5 = new TextComponentTranslation("guiSlimepedia.book.right5");
TextComponentTranslation right6 = new TextComponentTranslation("guiSlimepedia.book.right6");
TextComponentTranslation right7 = new TextComponentTranslation("guiSlimepedia.book.right7");
TextComponentTranslation right8 = new TextComponentTranslation("guiSlimepedia.book.right8");
TextComponentTranslation right9 = new TextComponentTranslation("guiSlimepedia.book.right9");
TextComponentTranslation right10 = new TextComponentTranslation("guiSlimepedia.book.right10");
TextComponentTranslation right11 = new TextComponentTranslation("guiSlimepedia.book.right11");
TextComponentTranslation right12 = new TextComponentTranslation("guiSlimepedia.book.right12");
TextComponentTranslation right13 = new TextComponentTranslation("guiSlimepedia.book.right13");
TextComponentTranslation right14 = new TextComponentTranslation("guiSlimepedia.book.right14");
TextComponentTranslation right15 = new TextComponentTranslation("guiSlimepedia.book.right15");
TextComponentTranslation right16 = new TextComponentTranslation("guiSlimepedia.book.right16");
TextComponentTranslation right17 = new TextComponentTranslation("guiSlimepedia.book.right17");
TextComponentTranslation right18 = new TextComponentTranslation("guiSlimepedia.book.right18");
TextComponentTranslation right19 = new TextComponentTranslation("guiSlimepedia.book.right19");
TextComponentTranslation right20 = new TextComponentTranslation("guiSlimepedia.book.right20");
TextComponentTranslation right21 = new TextComponentTranslation("guiSlimepedia.book.right21");
TextComponentTranslation right22 = new TextComponentTranslation("guiSlimepedia.book.right22");
TextComponentTranslation right23 = new TextComponentTranslation("guiSlimepedia.book.right23");
TextComponentTranslation right24 = new TextComponentTranslation("guiSlimepedia.book.right24");
TextComponentTranslation right25 = new TextComponentTranslation("guiSlimepedia.book.right25");
TextComponentTranslation right26 = new TextComponentTranslation("guiSlimepedia.book.right26");
TextComponentTranslation right27 = new TextComponentTranslation("guiSlimepedia.book.right27");
TextComponentTranslation right28 = new TextComponentTranslation("guiSlimepedia.book.right28");
TextComponentTranslation right29 = new TextComponentTranslation("guiSlimepedia.book.right29");
TextComponentTranslation right30 = new TextComponentTranslation("guiSlimepedia.book.right30");
TextComponentTranslation right31 = new TextComponentTranslation("guiSlimepedia.book.right31");
TextComponentTranslation right32 = new TextComponentTranslation("guiSlimepedia.book.right32");  



public GuiSlimepedia(EntityPlayer player, World world)
{
	
	
	this.playerBreeder = player;
	this.playerWorld = world;
	

	
	if(player.getHeldItemMainhand() == null)
	{
		this.breederHand = EnumHand.OFF_HAND;
	}else
	{
		this.breederHand = EnumHand.MAIN_HAND;
	}
	ItemStack book = playerBreeder.getHeldItem(breederHand);
	playerBook = book;
	bookNBT = playerBook.getTagCompound();
	
	
	
    bookPageTexturesLeft[0] = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/1slimepedia.png");
    bookPageTexturesRight[0] = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/2slimepedia.png");
    bookPageTexturesLeft[1] = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/3slimepedia.png");
    bookPageTexturesRight[1] = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/4slimepedia.png");
    bookPageTexturesLeft[2] = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/5slimepedia.png");
    bookPageTexturesRight[2] = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/6slimepedia.png");
    bookPageTexturesLeft[3] = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/7slimepedia.png");
    bookPageTexturesRight[3] = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/8slimepedia.png");
    bookPageTexturesLeft[4] = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/9slimepedia.png");
    bookPageTexturesRight[4] = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/10slimepedia.png");
    bookPageTexturesLeft[5] = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/leftendslimepedia.png");
    bookPageTexturesRight[5] = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/rightendslimepedia.png");
    bookPageTexturesLeft[6] = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/leftendslimepedia.png");
    bookPageTexturesRight[6] = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/rightendslimepedia.png");
    
    purpleSlime = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/purpleslime.png");
    
    forestIco = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/sunflowerico.png");
    swampIco = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/swampico.png");
    taigaIco = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/taigaico.png");
    
    heart = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/heart.png");
    chicken = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/chicken.png");
    
    
    greenSlime = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/greenslime.png");
    greenSlimePop = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/greenslimepop.png");
    
    craftArrow = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/craftarrow.png");
    craftArrowComplete = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/craftarrowcomplete.png");

    nameBar = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/namebar.png");
    rainCloud = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/raincloud.png");
    frame = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/frame.png");
    dna = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/dna.png");
    
    grid = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/grid.png");
    result = new ResourceLocation(SlimeBreeder.MODID+":textures/gui/result.png");
    
    
    
    //stringPageTextLefta[3] = TextFormatting.DARK_AQUA + "===================\n" + TextFormatting.ITALIC + "      Slime Care" + TextFormatting.DARK_AQUA + "\n===================\n\n" + TextFormatting.BLACK + "Coloured Slimes are aggressive creatures which wont hesitate to try and make you their next meal.\n\nBut theres a catch, over time with continous feeding you can prove to your slimes that they no longer need to eat players to get that essential nutrients." + TextFormatting.ITALIC + TextFormatting.DARK_GREEN +"\n\n''Right-Click a Coloured Slime with some raw chicken to feed it!''\n\n" + TextFormatting.BLACK +"It doesn't stop there, alongside these feedings a slimebreeder can also pet his slime periodically to further domesticate his new found friend." + TextFormatting.ITALIC + TextFormatting.DARK_GREEN +"\n\n''To pet a Coloured Slime Right-Click them with an empty hand''"+ TextFormatting.BLACK + "\n\nBe careful though, petting slimes without the proper equipment can and will hurt you!" ;

    stringPageTextLefta[0] = I18n.format(SlimeBreeder.VERSION + "v");
    stringPageTextLefta[1] = TextFormatting.LIGHT_PURPLE + "===================\n" + TextFormatting.ITALIC + "      " + left1.getFormattedText() + TextFormatting.LIGHT_PURPLE + "\n===================\n\n" + TextFormatting.BLACK + left2.getFormattedText();
    stringPageTextLefta[2] = TextFormatting.DARK_PURPLE + "===================\n" + TextFormatting.ITALIC + "      " + left3.getFormattedText() + TextFormatting.DARK_PURPLE + "\n===================\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + TextFormatting.DARK_GREEN +left4.getFormattedText()+ TextFormatting.BLACK + left5.getFormattedText()+ TextFormatting.DARK_GREEN + " " + left6.getFormattedText() + TextFormatting.BLACK + " " + left7.getFormattedText() + TextFormatting.DARK_GREEN + " " + left8.getFormattedText() + TextFormatting.BLACK + " " + left9.getFormattedText() + TextFormatting.DARK_GREEN +  " " + left10.getFormattedText() + TextFormatting.BLACK + left11.getFormattedText();
    stringPageTextLefta[3] = TextFormatting.DARK_AQUA + "===================\n" + TextFormatting.ITALIC + "      " + left12.getFormattedText() + TextFormatting.DARK_AQUA + "\n===================\n\n" + TextFormatting.BLACK + left13.getFormattedText() + TextFormatting.ITALIC + TextFormatting.DARK_GREEN + left14.getFormattedText() + TextFormatting.BLACK + left15.getFormattedText();
    stringPageTextLefta[4] = TextFormatting.DARK_AQUA+ "===================\n" + TextFormatting.ITALIC + "      " +  left16.getFormattedText() + TextFormatting.DARK_AQUA + "\n===================\n\n" + TextFormatting.BLACK + left17.getFormattedText() + TextFormatting.DARK_GREEN + " " + left18.getFormattedText() + TextFormatting.BLACK + left19.getFormattedText() + TextFormatting.ITALIC + " " + left20.getFormattedText() + TextFormatting.BLACK + left21.getFormattedText() + TextFormatting.DARK_GREEN + left22.getFormattedText();
    stringPageTextLefta[5] = TextFormatting.DARK_BLUE + "===================\n" + TextFormatting.ITALIC + "   " + left23.getFormattedText() + TextFormatting.DARK_BLUE + "\n===================\n\n" + TextFormatting.BLACK + left24.getFormattedText();
    stringPageTextLefta[6] = TextFormatting.DARK_BLUE + "===================\n" + TextFormatting.ITALIC + "   " + left25.getFormattedText() + TextFormatting.DARK_BLUE + "\n===================\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + TextFormatting.BLACK + left26.getFormattedText() + TextFormatting.ITALIC + TextFormatting.DARK_GREEN + left27.getFormattedText();
    stringPageTextLefta[7] = TextFormatting.DARK_GREEN + "===================\n" + TextFormatting.ITALIC + "   " + left28.getFormattedText() + TextFormatting.DARK_GREEN + "\n===================\n\n" + TextFormatting.BLACK +  left29.getFormattedText() + TextFormatting.DARK_RED + left30.getFormattedText() + TextFormatting.DARK_PURPLE + left31.getFormattedText() + TextFormatting.BLACK + left32.getFormattedText();
    stringPageTextLefta[8] = TextFormatting.GOLD + "===================\n" + TextFormatting.ITALIC + "   " + left33.getFormattedText() + TextFormatting.GOLD+ "\n===================\n\n" + TextFormatting.BLACK + left34.getFormattedText() + TextFormatting.ITALIC + TextFormatting.DARK_GREEN + left35.getFormattedText() + TextFormatting.BLACK +  left36.getFormattedText();

    
    
    stringPageTextLeftb[0] = I18n.format(" ");
    
    stringPageTextRighta[0] = " ";
    stringPageTextRighta[1] = TextFormatting.LIGHT_PURPLE + "===================\n" + TextFormatting.ITALIC + "      " + right1.getFormattedText() + TextFormatting.LIGHT_PURPLE + "\n===================\n\n" + TextFormatting.BLACK + TextFormatting.ITALIC + right2.getFormattedText() ;
    stringPageTextRighta[2] = TextFormatting.DARK_PURPLE + "===================\n" + TextFormatting.ITALIC + "      " + right3.getFormattedText() + TextFormatting.DARK_PURPLE + "\n===================\n\n" + TextFormatting.BLACK + right4.getFormattedText();
    stringPageTextRighta[3] = TextFormatting.DARK_AQUA + "===================\n" + TextFormatting.ITALIC + "      " + right5.getFormattedText() + TextFormatting.DARK_AQUA + "\n===================\n\n" + TextFormatting.BLACK + TextFormatting.ITALIC + right6.getFormattedText() + TextFormatting.BLACK + right7.getFormattedText() + TextFormatting.ITALIC + TextFormatting.DARK_GREEN + right8.getFormattedText() + TextFormatting.LIGHT_PURPLE + right9.getFormattedText()  + TextFormatting.BLACK + TextFormatting.ITALIC + right10.getFormattedText() + TextFormatting.DARK_RED + right11.getFormattedText()  + TextFormatting.BLACK + TextFormatting.ITALIC + right12.getFormattedText() + TextFormatting.BLUE + right13.getFormattedText()  + TextFormatting.BLACK + TextFormatting.ITALIC + right14.getFormattedText() + TextFormatting.DARK_GRAY + right15.getFormattedText()  + TextFormatting.BLACK + TextFormatting.ITALIC + right16.getFormattedText() + TextFormatting.GOLD + right17.getFormattedText()  + TextFormatting.BLACK + TextFormatting.ITALIC + right18.getFormattedText();
    stringPageTextRighta[4] = TextFormatting.DARK_AQUA + "===================\n" + TextFormatting.ITALIC + "      " + right19.getFormattedText() + TextFormatting.DARK_AQUA + "\n===================\n\n" + TextFormatting.BLACK + right20.getFormattedText() + TextFormatting.DARK_GREEN + right21.getFormattedText() + TextFormatting.BLACK + right22.getFormattedText() + TextFormatting.DARK_GREEN + right23.getFormattedText() + TextFormatting.BLACK + right24.getFormattedText();
    stringPageTextRighta[5] = TextFormatting.DARK_BLUE + "===================\n" + TextFormatting.ITALIC + "   " + right25.getFormattedText() + TextFormatting.DARK_BLUE + "\n===================\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + TextFormatting.BLACK + right26.getFormattedText() + TextFormatting.DARK_GREEN + right27.getFormattedText();
    stringPageTextRighta[6] = TextFormatting.DARK_BLUE + "===================\n" + TextFormatting.ITALIC + "   " + right28.getFormattedText() + TextFormatting.DARK_BLUE + "\n===================\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + TextFormatting.BLACK +  right29.getFormattedText() + TextFormatting.ITALIC + TextFormatting.DARK_GREEN + right30.getFormattedText();
    stringPageTextRighta[7] = TextFormatting.DARK_GREEN + "===================\n" + TextFormatting.ITALIC + "   " + right31.getFormattedText() + TextFormatting.DARK_GREEN+ "\n===================\n\n" + TextFormatting.BLACK + right32.getFormattedText();
    stringPageTextRighta[8] = "";
    
    stringPageTextRightb[0] = " ";

    
    startWidth = mc.getMinecraft().displayWidth;
    startHeight = mc.getMinecraft().displayHeight;
    
    //System.out.println(Minecraft.getMinecraft().gameSettings.language);
    
    
    
}



/**
 * Adds the buttons (and other controls) to the screen in question.
 */
@Override
public void initGui() 
{
 // DEBUG
	
    buttonList.clear();
    Keyboard.enableRepeatEvents(true);
    
    

    //buttonDone = new GuiButton(0, (int)RightguiLeft+45, (int)guiTop+150, 98, 20, I18n.format("gui.done", new Object[0]));
    
    addRecipes();
    
    buttonIntroduction = new GuiButton(3, (int)RightguiLeft+45, (int)guiTop+24,  100, 15, I18n.format(TextFormatting.LIGHT_PURPLE+ this.title1.getFormattedText(), new Object[0]));
    buttonSlimesFind = new GuiButton(3, (int)RightguiLeft+45, (int)guiTop+44,  100, 15, I18n.format(TextFormatting.DARK_PURPLE+ this.title2.getFormattedText(), new Object[0]));
    buttonSlimeCare = new GuiButton(3, (int)RightguiLeft+45, (int)guiTop+64,  100, 15, I18n.format(TextFormatting.DARK_AQUA+this.title3.getFormattedText(), new Object[0]));
    buttonSlimeBreeding = new GuiButton(3, (int)RightguiLeft+45, (int)guiTop+84,  100, 15, I18n.format(TextFormatting.BLUE+this.title4.getFormattedText(), new Object[0]));
    buttonSlimeGenetics = new GuiButton(3, (int)RightguiLeft+45, (int)guiTop+104,  100, 15, I18n.format(TextFormatting.DARK_GREEN+this.title5.getFormattedText(), new Object[0]));
    buttonSlimeCrafting = new GuiButton(3, (int)RightguiLeft+45, (int)guiTop+124,  100, 15, I18n.format(TextFormatting.GOLD+this.title6.getFormattedText(), new Object[0]));
    //buttonSlimeWarnings = new GuiButton(3, (int)RightguiLeft+45, (int)guiTop+134,  100, 15, I18n.format(TextFormatting.DARK_RED+"Health & Safety", new Object[0]));
    
    
    //buttonList.add(buttonDone);
    int offsetFromScreenLeft = (width - bookImageWidth) / 2;
    
    buttonList.add(buttonNextPage = new NextPageButton(1, (int)RightguiLeft+40, (int)guiTop+150, true));
    buttonList.add(buttonPreviousPage = new NextPageButton(2, (int)RightguiLeft-25, (int)guiTop+150, false));
    buttonList.add(buttonHome = new HomeButton(4, (int)LeftguiLeft+45, (int)guiTop+150));//38
    //buttonList.add(buttonNextSubPage = new NextSubPageButton(4, (int)LeftguiLeft+45, (int)guiTop+150, true));
    
    buttonList.add(buttonIntroduction);
    buttonList.add(buttonSlimesFind);
    buttonList.add(buttonSlimeCare);
    buttonList.add(buttonSlimeBreeding);
    buttonList.add(buttonSlimeGenetics);
    buttonList.add(buttonSlimeCrafting);
    //buttonList.add(buttonSlimeWarnings);
    
    //buttonList.add(buttonSign = new SignButton(5, offsetFromScreenLeft+60, 150));
    
    
    
    //System.out.println("ScreenWidth = " + LeftguiLeft);
    //System.out.println("DisplayWidth = " + (mc.getMinecraft().displayWidth));
    
    //System.out.println("ScreenHeight = " + guiTop);
    //System.out.println("DisplayHeight = " + (mc.getMinecraft().displayHeight));
    
    float finalWidth = ((float)mc.getMinecraft().displayWidth/(float)1920);
    float finalHeight = guiTop + 250;
    
    //System.out.println("Scale Factor = " + res.getScaleFactor());
    
    //System.out.println("Final Width = " + scaledWidth);
    //System.out.println("Final Height = " + scaledHeight);
    
    //System.out.println("Screen Ratio Height = " + LeftguiLeft + " + " + "1050");
    //System.out.println("Screen Ratio Width = " + guiTop + " + " + "250");
    

    
    if(startWidth != mc.getMinecraft().displayWidth || startHeight != mc.getMinecraft().displayHeight)
	{
		//System.out.println("Changed Screen Size");
		GuiScreen screen = mc.currentScreen;
		mc.currentScreen = null;
		
		
		
		playerBreeder.openGui(
        		
        		SlimeBreeder.INSTANCE,
        		SlimeBreeder.GUI_ENUM.BOOK.ordinal(),
        		playerWorld, (int) 0, (int) 0, (int) 0
        							
        							);
		
		
		
		
	}
    
}


public void addRecipes()
{
	int pages = 10;
	
	EnumRecipes.listOfRecipes.clear();
	EnumRecipes.initRecipeList();
	
	int addPages = EnumRecipes.listOfRecipes.size();
	
	this.bookTotalPages = pages + addPages;
	
	//System.out.println("Pages = " + pages);
	//System.out.println("Recipes found = " + addPages);
	//System.out.println("New Total = " + this.bookTotalPages);
	
}



/**
 * Called from the main game loop to update the screen.
 */
@Override
public void updateScreen() 
{
	
	
	if(bookNBT != null)
	{
		stringPageTextLeftb[0] = I18n.format(bookNBT.getString("Owner"));
	}
	
	//ItemStack book = playerBreeder.getHeldItem(breederHand);
	
	
	
    //buttonDone.visible = (currPage == bookTotalPages - 1);
	//buttonIntroduction.packedFGColour = 500;
    buttonIntroduction.visible = (currPage == 0);
    buttonSlimesFind.visible = (currPage == 0);
    buttonSlimeCare.visible = (currPage == 0);
    buttonSlimeBreeding.visible = (currPage == 0);
    buttonSlimeGenetics.visible = (currPage == 0);
    buttonSlimeCrafting.visible = (currPage == 0);
    //buttonSlimeWarnings.visible = (currPage == 0);
    
    //buttonSign.visible = (currPage == 0);
    buttonHome.visible = (currPage > 0);
    //buttonIntroduction.visible = (currPage == 0);
    //buttonIntroduction.visible = (currPage == 0);
    //buttonIntroduction.visible = (currPage == 0);
    
    buttonNextPage.visible = (currPage < bookTotalPages - 2);
    buttonPreviousPage.visible = currPage > 0;
    /*if(book.getTagCompound().getInteger("Signed") == 1)
    {
    stringPageTextLeft[0] = I18n.format(SlimeBreeder.VERSION + "v") + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+playerBreeder.getName();
    }*/
    
    //this.playerName = this.name;
}

/**
 * Draws the screen and all the components in it.
 */
@Override
public void drawScreen(int parWidth, int parHeight, float p_73863_3_)
{
    /*GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    if (currPage == 0)
 {
     mc.getTextureManager().bindTexture(bookPageTextures[0]);
 }
    else
    {
     mc.getTextureManager().bindTexture(bookPageTextures[1]);
    }*/
    int offsetFromScreenLeft = ((width - bookImageWidth ) / 2);
    
    
	
	
    int offsetFromScreenTop = ((height - bookImageHeight ) / 2);
    int offsetFromScreenLeft1 = offsetFromScreenLeft-(offsetFromScreenLeft/6);
    int offsetFromScreenTop1 = offsetFromScreenTop-(offsetFromScreenTop/10);
    int offsetFromScreenRight = offsetFromScreenLeft1 + 143;
    drawScreenL();
    drawScreenR();
    
    
    int widthOfString;
    
    String stringPageIndicator = I18n.format("book.pageIndicator", new Object[] {Integer.valueOf(currPage + 1), bookTotalPages});
    widthOfString = fontRenderer.getStringWidth(stringPageIndicator);
    
    
    
    drawTextL();
    drawTextR();
    
    //fontRendererObj.drawString(stringPageIndicator, offsetFromScreenLeft - widthOfString + bookImageWidth - 44, 18, 0);
    //fontRendererObj.drawString(stringPageTextLeft[currPage], centerX, centerY, 0);
    //fontRendererObj.drawSplitString(stringPageTextLefta[currPage], (int) guiCentreX+75, (int) guiCentreY, 116, 0);//+ 36, 34, // 116
    
    
    super.drawScreen(parWidth, parHeight, p_73863_3_);

}

public void drawTextL()
{
	
    float guiCentreX = (this.width - this.bookImageWidth) / 2;
	float guiCentreY = (this.height - this.bookImageHeight) / 2;	
    
    switch (currPage) {
    case 0:   drawVersion(); drawOwner(); 
    			
    break;
    
             
    case 1:  
    	if(currPage == 1)
		{
    	float scale = 0.5F;
    	GL11.glPushMatrix();
        GL11.glScaled(scale, scale, scale);
    	fontRenderer.drawSplitString(stringPageTextLefta[currPage], Math.round((int)LeftguiLeft/scale)+82, Math.round((int)guiTop/scale)+30, 216, 0);
    	GL11.glPopMatrix();
		}
    	
    break;
    
    case 2:    drawRain();
    	if(currPage == 2)
		{
    	float scale = 0.5F;
        GL11.glPushMatrix();
        GL11.glScaled(scale, scale, scale);
        fontRenderer.drawSplitString(stringPageTextLefta[currPage], Math.round((int)LeftguiLeft/scale)+82, Math.round((int)guiTop/scale)+30, 216, 0);
    	GL11.glPopMatrix();
		}
    break;
    
    case 3:   drawChicken();
    	
    	if(currPage == 3)
		{
    	float scale = 0.5F;
        GL11.glPushMatrix();
        GL11.glScaled(scale, scale, scale);
        fontRenderer.drawSplitString(stringPageTextLefta[currPage], Math.round((int)LeftguiLeft/scale)+82, Math.round((int)guiTop/scale)+30, 216, 0);
    	GL11.glPopMatrix();
		}
    	
    break;
    
    case 4:   drawHeart(0.10F, 0.10F, 0.10F, 750, 600); drawHeart(0.07F, 0.07F, 0.07F, 1500, 1200); drawHeart(0.06F, 0.06F, 0.06F, 1350, 1700);
    	
    	if(currPage == 4)
		{
    	float scale = 0.5F;
        GL11.glPushMatrix();
        GL11.glScaled(scale, scale, scale);
        fontRenderer.drawSplitString(stringPageTextLefta[currPage], Math.round((int)LeftguiLeft/scale)+82, Math.round((int)guiTop/scale)+30, 216, 0);
    	GL11.glPopMatrix();
		}
    	
    break;
    
    case 5:   
    	
    	if(currPage == 5)
		{
    	float scale = 0.5F;
    	GL11.glPushMatrix();
        GL11.glScaled(scale, scale, scale);
    	fontRenderer.drawSplitString(stringPageTextLefta[currPage], Math.round((int)LeftguiLeft/scale)+82, Math.round((int)guiTop/scale)+30, 216, 0);
    	GL11.glPopMatrix();
		}
    	
    break;
    
    case 6:
    
	    if(currPage == 6)
		{
		float scale = 0.5F;
		GL11.glPushMatrix();
	    GL11.glScaled(scale, scale, scale);
		fontRenderer.drawSplitString(stringPageTextLefta[currPage], Math.round((int)LeftguiLeft/scale)+82, Math.round((int)guiTop/scale)+30, 216, 0);
		GL11.glPopMatrix();
		}
		
	break;
    
    case 7:   drawDna();
    	
    	if(currPage == 7)
		{
    	float scale = 0.5F;
    	GL11.glPushMatrix();
        GL11.glScaled(scale, scale, scale);
    	fontRenderer.drawSplitString(stringPageTextLefta[currPage], Math.round((int)LeftguiLeft/scale)+82, Math.round((int)guiTop/scale)+30, 216, 0);
    	GL11.glPopMatrix();
		}
    	
    break;
    
    case 8:   
    	
    	if(currPage == 8) drawNameBar(); drawArrowComplete(); 	if(this.timer < 255){renderItemsSlime(new ItemStack(SlimeBreederItems.UNKNOWNITEM), new ItemStack(SlimeBreederItems.UNKNOWNITEM),  new ItemStack(SlimeBreederItems.UNKNOWNITEM), new ItemStack(SlimeBreederItems.UNKNOWNITEM), new ItemStack(SlimeBreederItems.UNKNOWNITEM), new ItemStack(SlimeBreederItems.UNKNOWNITEM), false);}else{renderItemsSlime(new ItemStack(SlimeBreederItems.UNKNOWNITEM), new ItemStack(SlimeBreederItems.UNKNOWNITEM),  new ItemStack(SlimeBreederItems.UNKNOWNITEM), new ItemStack(SlimeBreederItems.UNKNOWNITEM), new ItemStack(SlimeBreederItems.UNKNOWNITEM), new ItemStack(SlimeBreederItems.UNKNOWNITEM), true);};

		{
    	float scale = 0.5F;
    	GL11.glPushMatrix();
        GL11.glScaled(scale, scale, scale);
    	fontRenderer.drawSplitString(stringPageTextLefta[currPage], Math.round((int)LeftguiLeft/scale)+82, Math.round((int)guiTop/scale)+30, 216, 0);
    	GL11.glPopMatrix();
		}
    	
    break;
    
    

    

    
    default: drawCraftRight();
    break;
    }
    
    
}

public void drawTextR()
{
	
    float guiCentreX = (this.width - this.bookImageWidth) / 2;
	float guiCentreY = (this.height - this.bookImageHeight) / 2;	

    
    switch (currPage) {
    case 0:   fontRenderer.drawSplitString(stringPageTextRighta[currPage], (int)RightguiLeft+37, (int)guiTop+20, 116, 0);
    break;
             
    case 1:
    	if(currPage == 1)
    		{
    	drawSlimePurple();
    	float scale = 0.5F;
    	GL11.glPushMatrix();
        GL11.glScaled(scale, scale, scale);
    	fontRenderer.drawSplitString(stringPageTextRighta[currPage], Math.round((int)RightguiLeft/scale)+80, Math.round((int)guiTop/scale)+29, 216, 0);
    	GL11.glPopMatrix();
    		}
    break;
    
    case 2: drawForest(); drawTaiga(); drawSwamp(); drawFlavorText("[Plains]", 220, 69, 0.5F, 0.5F, 0.85F, 1.0D, 1.0D, 1.0D); drawFlavorText("[Swamp]", 220, 93, 0.5F, 0.5F, 0.85F, 1.0D, 1.0D, 1.0D); drawFlavorText("[Taiga]", 220, 117, 0.5F, 0.5F, 0.85F, 1.0D, 1.0D, 1.0D);
    	if(currPage == 2)
		{
    	float scale = 0.5F;
    	GL11.glPushMatrix();
        GL11.glScaled(scale, scale, scale);
    	fontRenderer.drawSplitString(stringPageTextRighta[currPage], Math.round((int)RightguiLeft/scale)+80, Math.round((int)guiTop/scale)+29, 216, 0);
    	GL11.glPopMatrix();
		}
    	
    break;
    
    case 3:   
    	
    	if(currPage == 3)
		{
    	float scale = 0.5F;
    	GL11.glPushMatrix();
        GL11.glScaled(scale, scale, scale);
    	fontRenderer.drawSplitString(stringPageTextRighta[currPage], Math.round((int)RightguiLeft/scale)+80, Math.round((int)guiTop/scale)+29, 216, 0);
    	GL11.glPopMatrix();
		}
    	
    break;
    
    case 4:   
    	
    	if(currPage == 4)
		{
    	float scale = 0.5F;
    	GL11.glPushMatrix();
        GL11.glScaled(scale, scale, scale);
    	fontRenderer.drawSplitString(stringPageTextRighta[currPage], Math.round((int)RightguiLeft/scale)+80, Math.round((int)guiTop/scale)+29, 216, 0);
    	GL11.glPopMatrix();
		}
    	
    	
    break;
    
    case 5:
    	
    	if(currPage == 5)
		{

    		if((((Math.round((float)(playerBreeder.ticksExisted/8)))/8) & 1) == 0)
    		{
    		drawgrid(-100,-45, null,Items.IRON_INGOT,null,null,Item.getItemFromBlock(Blocks.GLASS_PANE),null,null,Items.IRON_INGOT, null, SlimeBreederItems.SLIMECAPSULE);
    		}
    		if((((Math.round((float)(playerBreeder.ticksExisted/8)))/8) & 1) == 1)
    		{
    		drawgrid(-100, -45, SlimeBreederItems.SLIMECAPSULE,Items.SPIDER_EYE,null,Items.ROTTEN_FLESH,Items.REDSTONE,null,null,null, null, SlimeBreederItems.BREEDINGCATALYSTIRON);
    		}
	    	float scale = 0.5F;
	    	GL11.glPushMatrix();
	        GL11.glScaled(scale, scale, scale);
	    	fontRenderer.drawSplitString(stringPageTextRighta[currPage], Math.round((int)RightguiLeft/scale)+80, Math.round((int)guiTop/scale)+29, 216, 0);
	    	GL11.glPopMatrix();
		}
    	
    	
    break;
    
    case 6:
    	
    	if(currPage == 6) drawItem(SlimeBreederItems.FILLEDSLIMECAPSULE, 39, 6, 5.0F, 5.0F); drawgrid(-680,-45, Items.IRON_INGOT,Items.REDSTONE,Items.IRON_INGOT,null,SlimeBreederItems.SLIMECAPSULE,null,null,Items.IRON_INGOT, null, SlimeBreederItems.SLIMALYSER);

		{

	    	float scale = 0.5F;
	    	GL11.glPushMatrix();
	        GL11.glScaled(scale, scale, scale);
	    	fontRenderer.drawSplitString(stringPageTextRighta[currPage], Math.round((int)RightguiLeft/scale)+80, Math.round((int)guiTop/scale)+29, 216, 0);
	    	GL11.glPopMatrix();
		}
    	
    	
    break;
    
    case 7:
    	
    	if(currPage == 7) drawFlavorText("Sticky", 230, 56, 0.2F, 0.95F, 0.2F, 0.8D, 0.8D, 0.8D); drawFlavorText("Acidic", 230, 85, 0.2F, 0.2F, 0.95F, 0.8D, 0.8D, 0.8D); drawFlavorText("Rubbery", 230, 119, 0.95F, 0.2F, 0.2F, 0.8D, 0.8D, 0.8D); drawFlavorText("Active", 230, 141, 0.95F, 0.2F, 0.85F, 0.8D, 0.8D, 0.8D); drawFlavorText("Free-Flowing", 230, 163, 0.2F, 0.95F, 0.85F, 0.8D, 0.8D, 0.8D);
    	{
        	float scale = 0.5F;
        	GL11.glPushMatrix();
            GL11.glScaled(scale, scale, scale);
        	fontRenderer.drawSplitString(stringPageTextRighta[currPage], Math.round((int)RightguiLeft/scale)+80, Math.round((int)guiTop/scale)+29, 216, 0);
        	GL11.glPopMatrix();
    		}
    	
    	
    break;
    
    case 8:
    	
    	if(currPage == 8)
    	{
        	float scale = 0.5F;
        	GL11.glPushMatrix();
            GL11.glScaled(scale, scale, scale);
        	fontRenderer.drawSplitString(stringPageTextRighta[currPage], Math.round((int)RightguiLeft/scale)+80, Math.round((int)guiTop/scale)+29, 216, 0);
        	GL11.glPopMatrix();
    		}
    	
    	
    break;
    
    
    default: fontRenderer.drawSplitString(stringPageTextRighta[0], (int)RightguiLeft+37, (int)guiTop+20, 116, 0);
    break;
    }
    
    
}

public void drawVersion()
{
	float scale = 0.5F;
	GL11.glPushMatrix();
    GL11.glScaled(scale, scale, scale);
    
    
	fontRenderer.drawSplitString(stringPageTextLefta[currPage], Math.round((int)LeftguiLeft/scale)+73, Math.round((int)guiTop/scale)+32, 116, 0);
	GL11.glPopMatrix();
	
	
}

public void drawOwner()
{
	GL11.glPushMatrix();
    GL11.glScaled(1.0, 1.0, 1.0);
	fontRenderer.drawSplitString(stringPageTextLeftb[currPage],(int)LeftguiLeft+55 , (int)guiTop+150, 116, 0);
	GL11.glPopMatrix();
	
}

public void drawFlavorText(String text, int offsetX, int offsetY, float red, float blue, float green, double scaleX, double scaleY, double scaleZ)
{
	GL11.glPushMatrix();
    GL11.glScaled(scaleX, scaleY, scaleZ);
    GL11.glColor3f(red, green, blue);
	fontRenderer.drawSplitString(text,(int)(LeftguiLeft/scaleX)+offsetX , (int)(guiTop/scaleY)+offsetY, 116, 0);
	GL11.glPopMatrix();
	
}

public void drawgrid(int x, int y, Item item1, Item item2, Item item3, Item item4, Item item5, Item item6, Item item7, Item item8, Item item9, Item item10)
{
	float scale = 0.25F;
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scale, scale, scale);
	mc.getTextureManager().bindTexture(grid);
	drawTexturedModalRect(Math.round((int)LeftguiLeft/scale)+850+x , Math.round((int)guiTop/scale)+180+y, 0, 0, 255, 255);
	
	GL11.glPopMatrix();
	
	renderItems(x, y, item1,item2, item3,item4,item5,item6,item7,item8,item9, item10);
	drawResult(x, y);
	
	
}

public void drawCraftRight()
{
	EnumRecipes.listOfRecipes.clear();
	EnumRecipes.initRecipeList();
	
	drawNameBar();
	drawArrowComplete();
	
	int usedPages = 8;
	
	/*if(this.timer < 255)
	{
	renderItemsSlime(EnumRecipes.listOfRecipes.get((currPage-7)).item1, EnumRecipes.listOfRecipes.get((currPage-7)).item2,  EnumRecipes.listOfRecipes.get((currPage-7)).item3, EnumRecipes.listOfRecipes.get((currPage-7)).item4, EnumRecipes.listOfRecipes.get((currPage-7)).item5, EnumRecipes.listOfRecipes.get((currPage-7)).item7, false);
	}else{
		renderItemsSlime(EnumRecipes.listOfRecipes.get((currPage-7)).item1, EnumRecipes.listOfRecipes.get((currPage-7)).item2,  EnumRecipes.listOfRecipes.get((currPage-7)).item3, EnumRecipes.listOfRecipes.get((currPage-7)).item4, EnumRecipes.listOfRecipes.get((currPage-7)).item5, EnumRecipes.listOfRecipes.get((currPage-7)).item7, true);

	}*/
	
	if(this.timer < 255)
	{
	renderItemsSlime(EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item1, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item2,  EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item3, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item4, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item5, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item7, false);
	
	drawRecipeText(EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).flavorText);
	drawRequirements(EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item1, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item2, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item3, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item4, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item5);
	
	}else{
		renderItemsSlime(EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item1, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item2,  EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item3, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item4, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item5, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item7, true);
		
		drawRecipeText(EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).flavorText);
		drawRequirements(EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item1, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item2, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item3, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item4, EnumRecipes.listOfRecipes.get(EnumRecipes.listOfRecipes.size()-(currPage-usedPages)).item5);

		
		
	}

}

public void drawRecipeText(String text)
{
	float scale = 0.5F;
	GL11.glPushMatrix();
    GL11.glScaled(scale, scale, scale);

    String title;

       title = TextFormatting.GOLD + title7.getFormattedText();

    
    
    fontRenderer.drawSplitString(title, (int)((RightguiLeft+-106)/scale), (int)((guiTop+20)/scale), 230, 0);
    
    //text.replaceAll("§r", "");
    
    String newText = text.replace("§r", "");
    
	 ITextComponent transtext = new TextComponentTranslation(newText);
	 
	 
	 //System.out.println(newText);

    
    fontRenderer.drawSplitString(transtext.getFormattedText(), (int)((RightguiLeft+-106)/scale), (int)((guiTop+40)/scale), 230, 0);
	
	GL11.glPopMatrix();
}

public void drawRequirements(ItemStack item1, ItemStack item2, ItemStack item3, ItemStack item4, ItemStack item5)
{
	
	double[] movespeedR;
	double[] healthR;
	
	int[] acidicR;
	int[] stickyR;
	
	movespeedR = new double[5];
	healthR = new double[5];
	acidicR = new int[5];
	stickyR = new int[5];
	
	double maxMove = 0;
	double maxHealth = 0;
	int maxAcidic = 0;
	int maxSticky = 0;
	
	EnumCraftingIngredients.listOfItems.clear();
	EnumCraftingIngredients.initItemList();
	
	int length = EnumCraftingIngredients.listOfItems.size();
	
	String level1 = "requirement.general.level1";
	String level2 = "requirement.general.level2";
	String level3 = "requirement.general.level3";
	String level4 = "requirement.general.level4";
	
	ITextComponent Translevel1 = new TextComponentTranslation(level1);
	ITextComponent Translevel2 = new TextComponentTranslation(level2);
	ITextComponent Translevel3 = new TextComponentTranslation(level3);
	ITextComponent Translevel4 = new TextComponentTranslation(level4);

	
	//System.out.println(Translevel1);
	 
	 for (int i = 0; i < length; i++) 
   	{
		 
		 
		 if(EnumCraftingIngredients.listOfItems.get(i).getItemStack().getItem() == item1.getItem())
		 {

		 
		 movespeedR[0] = EnumCraftingIngredients.listOfItems.get(i).movespeed;
		 healthR[0] = EnumCraftingIngredients.listOfItems.get(i).health;
		 
		 acidicR[0] = EnumCraftingIngredients.listOfItems.get(i).attackdmg;
		 stickyR[0] = EnumCraftingIngredients.listOfItems.get(i).sticky;
		 
		 /*//System.out.println("Found");
		 //System.out.println(movespeedR[0]);
		 //System.out.println(EnumCraftingIngredients.listOfItems.get(i).movespeed);*/
		 
		 
		 }
		 
		 if(EnumCraftingIngredients.listOfItems.get(i).getItemStack().getItem() == item2.getItem())
		 {

		 
		 movespeedR[1] = EnumCraftingIngredients.listOfItems.get(i).movespeed;
		 healthR[1] = EnumCraftingIngredients.listOfItems.get(i).health;
		 
		 acidicR[1] = EnumCraftingIngredients.listOfItems.get(i).attackdmg;
		 stickyR[1] = EnumCraftingIngredients.listOfItems.get(i).sticky;
		 }
		 
		 if(EnumCraftingIngredients.listOfItems.get(i).getItemStack().getItem() == item3.getItem())
		 {

		 
		 movespeedR[2] = EnumCraftingIngredients.listOfItems.get(i).movespeed;
		 healthR[2] = EnumCraftingIngredients.listOfItems.get(i).health;
		 
		 acidicR[2] = EnumCraftingIngredients.listOfItems.get(i).attackdmg;
		 stickyR[2] = EnumCraftingIngredients.listOfItems.get(i).sticky;
		 }
		 
		 if(EnumCraftingIngredients.listOfItems.get(i).getItemStack().getItem() == item4.getItem())
		 {

		 
		 movespeedR[3] = EnumCraftingIngredients.listOfItems.get(i).movespeed;
		 healthR[3] = EnumCraftingIngredients.listOfItems.get(i).health;
		 
		 acidicR[3] = EnumCraftingIngredients.listOfItems.get(i).attackdmg;
		 stickyR[3] = EnumCraftingIngredients.listOfItems.get(i).sticky;
		 }
		 
		 if(EnumCraftingIngredients.listOfItems.get(i).getItemStack().getItem() == item5.getItem())
		 {

		 
		 movespeedR[4] = EnumCraftingIngredients.listOfItems.get(i).movespeed;
		 healthR[4] = EnumCraftingIngredients.listOfItems.get(i).health;
		 
		 acidicR[4] = EnumCraftingIngredients.listOfItems.get(i).attackdmg;
		 stickyR[4] = EnumCraftingIngredients.listOfItems.get(i).sticky;
		 }
		  
   	}
	 
	 
	 

	 for (int counter = 0; counter < movespeedR.length; counter++)
	 {
		 ////System.out.println("No. = " + movespeedR[counter]);
	      if (movespeedR[counter] > maxMove)
	      {
	       maxMove = movespeedR[counter];
	      }
	 }
	 
	 for (int counter = 0; counter < healthR.length; counter++)
	 {
	      if (healthR[counter] > maxHealth)
	      {
	       maxHealth = healthR[counter];
	      }
	 }
	 
	 for (int counter = 0; counter < acidicR.length; counter++)
	 {
	      if (acidicR[counter] > maxAcidic)
	      {
	       maxAcidic = acidicR[counter];
	      }
	 }
	 
	 for (int counter = 0; counter < stickyR.length; counter++)
	 {
	      if (stickyR[counter] > maxSticky)
	      {
	       maxSticky = stickyR[counter];
	      }
	 }
	 
	 
	 
	 String E;
	 String C;
	 String B;
	 String A;

	 if (maxMove >= 3 && maxMove < 4) 
     {
         E = TextFormatting.BLACK+Translevel1.getFormattedText();
     } else if (maxMove >= 4 && maxMove < 5) {
     	 E = TextFormatting.AQUA+Translevel2.getFormattedText();
     } else if (maxMove >= 5 && maxMove < 6) {
     	 E = TextFormatting.BLUE+Translevel3.getFormattedText();
     } else if (maxMove >= 6 && maxMove <= 7) {
     	 E = TextFormatting.DARK_PURPLE+Translevel4.getFormattedText();
     } else {
     	 E = " ";
     }
	 

	 
	 if (maxHealth >= 3 && maxHealth < 6) 
     {
         C = TextFormatting.BLACK+Translevel1.getFormattedText();
     } else if (maxHealth >= 6 && maxHealth < 12) {
     	 C = TextFormatting.AQUA+Translevel2.getFormattedText();
     } else if (maxHealth >= 12 && maxHealth < 25) {
     	 C = TextFormatting.BLUE+Translevel3.getFormattedText();
     } else if (maxHealth >= 25 && maxHealth <= 50) {
     	 C = TextFormatting.DARK_PURPLE+Translevel4.getFormattedText();
     } else {
     	 C = " ";
     }
	 
	 
	 
	 
	 if (maxAcidic >= 2 && maxAcidic < 4) 
     {
         B = TextFormatting.BLACK + Translevel1.getFormattedText();
     } else if (maxAcidic >= 4 && maxAcidic < 6) {
     	 B = TextFormatting.AQUA + Translevel2.getFormattedText();
     } else if (maxAcidic >= 6 && maxAcidic < 8) {
     	 B = TextFormatting.BLUE + Translevel3.getFormattedText();
     } else if (maxAcidic >= 8 && maxAcidic <= 10) {
     	 B = TextFormatting.DARK_PURPLE+ Translevel4.getFormattedText();
     } else {
     	 B = " ";
     }
	 
	 
	 
	 if (maxSticky >= 0 && maxSticky < 1) 
     {
         A = TextFormatting.BLACK + Translevel1.getFormattedText();
     } else if (maxSticky >= 1 && maxSticky < 2) {
     	 A = TextFormatting.AQUA + Translevel2.getFormattedText();
     } else if (maxSticky >= 2 && maxSticky < 3) {
     	 A = TextFormatting.BLUE + Translevel3.getFormattedText();
     } else if (maxSticky >= 3 && maxSticky <= 4) {
     	 A = TextFormatting.DARK_PURPLE+ Translevel4.getFormattedText();
     } else {
     	 A = " ";
     }
	 
	 
	 	float scale = 0.8F;
		GL11.glPushMatrix();
	    GL11.glScaled(scale, scale, scale);
		

			fontRenderer.drawSplitString("    " + stat1.getFormattedText() + E + TextFormatting.AQUA+"  " + stat2.getFormattedText()  +C+ TextFormatting.RED+"  " + stat3.getFormattedText()  +B+ TextFormatting.GREEN+"  " + stat4.getFormattedText()  +A+ TextFormatting.DARK_BLUE+"  " + stat5.getFormattedText(), (int)((RightguiLeft+-106)/scale), (int)((guiTop+74)/scale), 230, 0);

	    
		GL11.glPopMatrix();
	 
	 
	 
	 
	 
	 /*drawFlavorText("==Min. Requirements==", 40, 84, 0.0F, 0.00F, 0.00F, 0.8D, 0.8D, 0.8D);
	 
	 drawFlavorText(E, 35, 99, 0.0F, 0.0F, 0.0F, 0.8D, 0.8D, 0.8D);
	 drawFlavorText("[Free-Flowing]", 85, 99, 0.2F, 0.95F, 0.85F, 0.8D, 0.8D, 0.8D);
	 
	 drawFlavorText(C, 45, 112, 0.0F, 0.0F, 0.0F, 0.8D, 0.8D, 0.8D);
	 drawFlavorText("[Rubbery]", 95, 112, 0.95F, 0.2F, 0.2F, 0.8D, 0.8D, 0.8D);
	 
	 drawFlavorText(B, 50, 125, 0.0F, 0.0F, 0.0F, 0.8D, 0.8D, 0.8D);
	 drawFlavorText("[Acidic]", 100, 125, 0.2F, 0.2F, 0.95F, 0.8D, 0.8D, 0.8D);
	 
	 drawFlavorText(A, 50, 138, 0.0F, 0.0F, 0.0F, 0.8D, 0.8D, 0.8D);
	 drawFlavorText("[Sticky]", 100, 138, 0.2F, 0.95F, 0.2F, 0.8D, 0.8D, 0.8D);*/
	 
	   	
	 
}

public void drawNameBar()
{
	
	
	float scale = 0.20F;
	float scaleX = 0.35F;
	float scaleY = 0.04F;
	
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scaleX, scaleY, scale);
	
	mc.getTextureManager().bindTexture(nameBar);
	

	
	drawTexturedModalRect(Math.round((int)LeftguiLeft/scaleX)+550, Math.round((int)guiTop/scaleY)+500, 0, 0, 255, 255);
	GL11.glPopMatrix();

}

public void drawHeart(float scaleZ, float scaleX, float scaleY, int posX, int posY)
{
	
	
	/*float scaleZ = 0.10F;
	float scaleX = 0.10F;
	float scaleY = 0.10F;*/
	
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scaleX, scaleY, scaleZ);
	
	mc.getTextureManager().bindTexture(heart);
	

	
	drawTexturedModalRect(Math.round((int)LeftguiLeft/scaleX)+posX, Math.round((int)guiTop/scaleY)+posY, 0, 0, 255, 255);
	GL11.glPopMatrix();

}

public void drawArrow()
{
	
	int scaleF = res.getScaleFactor();
	float scale = 0.05F;
	float scaleX = 0.05F;
	float scaleY = 0.06F;
	
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scaleX, scaleY, scale);
	
	mc.getTextureManager().bindTexture(craftArrow);
	

	
	drawTexturedModalRect(Math.round((int)LeftguiLeft/scale)+4630, Math.round((int)guiTop/scale)+1190, 0, 0, 255, 255);
	GL11.glPopMatrix();

}

public void drawArrowComplete()
{
	if(this.timer < 255)
	{
		drawSlimeGreen(false);
	}else{
		drawSlimeGreen(true);
	}
	
	drawArrow();
	
	
	
	if(((((Math.round((float)(playerBreeder.ticksExisted/1)))/1) & 1) == 0) && added == false)
	{
	
		this.timer = timer +5;
		////System.out.println(timer);
		this.added = true;
		////System.out.println("Adding");
		
	}
	
	if(((((Math.round((float)(playerBreeder.ticksExisted/1)))/1) & 1) == 1)&& added == true && timer < 365)
	{
	
		//this.timer = timer +1;
		
		this.added = false;
		////System.out.println("Not adding");
		////System.out.println((((Math.round((float)(playerBreeder.ticksExisted/200)))/200)) + " " +  added + " " + timer);
		
	}
	
		if(timer > 350)
		{
			timer = 0;
			this.added = true;
		}
	
	int scaleF = res.getScaleFactor();
	float scale = 0.05F;
	float scaleX = 0.05F;
	float scaleY = 0.06F;
	
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	
	GL11.glScalef(scaleX, scaleY, scale);
	
	mc.getTextureManager().bindTexture(craftArrowComplete);
	
	if(timer < 255)
	{
		drawTexturedModalRect(Math.round((int)LeftguiLeft/scale)+4630, Math.round((int)guiTop/scale)+1190, 0, 0, 255, this.timer);
		
	}else{
		drawTexturedModalRect(Math.round((int)LeftguiLeft/scale)+4630, Math.round((int)guiTop/scale)+1190, 0, 0, 255, 255);
		
	}
	
	
	
	GL11.glPopMatrix();
	
}
	






public void drawResult(int x, int y)
{
	float scale = 0.125F;
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scale, scale, scale);
	mc.getTextureManager().bindTexture(result);
	drawTexturedModalRect(Math.round((int)LeftguiLeft/scale)+2250+(x*2) , Math.round((int)guiTop/scale)+180+(y*2)+315, 0, 0, 255, 255);
	
	GL11.glPopMatrix();
}


public void renderItemsSlime(ItemStack item1, ItemStack item2, ItemStack item3, ItemStack item4, ItemStack item5, ItemStack item6, boolean pop)
{
	List<ItemStack> recipe = new ArrayList<ItemStack>();
	recipe.clear();
	
	if(item1.getItem() != Items.SLIME_BALL)
	{
	recipe.add(item1);
	}
	
	if(item2.getItem() != Items.SLIME_BALL)
	{
	recipe.add(item2);
	}
	
	if(item3.getItem() != Items.SLIME_BALL)
	{
	recipe.add(item3);
	}
	
	if(item4.getItem() != Items.SLIME_BALL)
	{
	recipe.add(item4);
	}
	
	if(item5.getItem() != Items.SLIME_BALL)
	{
	recipe.add(item5);
	}

	
	int x1 = 0;
	int y1 = 0;
	
	int x2 = 0;
	int y2 = 0;
	
	int x3 = 0;
	int y3 = 0;
	
	int x4 = 0;
	int y4 = 0;
	
	int x5 = 0;
	int y5 = 0;
	
	
	if(recipe.size() == 1)
	{
		x1 = 60;
		y1 = -10;
		
		////System.out.println("Size 1");
		
	}
	
	if(recipe.size() == 2)
	{
		x1 = -5;
		y1 = -10;
		
		x2 = 75;
		y2 = -10;
		
		////System.out.println("Size 2");
	}
	
	if(recipe.size() == 3)
	{
		x1 = 60;
		y1 = -10;
		
		x2 = -80;
		y2 = -80;
		
		x3 = -25;
		y3 = -80;
		
		////System.out.println("Size 3");
		
	}
	
	if(recipe.size() == 4)
	{
		x1 = 90;
		y1 = -10;
		
		x2 = -55;
		y2 = -80;
		
		x3 = 5;
		y3 = -80;
		
		x4 = -80;
		y4 = -99;
		
		////System.out.println("Size 4");
	}
	
	if(recipe.size() == 5)
	{
		x1 = 60;
		y1 = -10;
		
		x2 = -85;
		y2 = -80;
		
		x3 = -25;
		y3 = -80;
		
		x4 = -120;
		y4 = -99;
		
		x5 = 150;
		y5 = -95;
		
		////System.out.println("Size 5");
	}
	
	float scale = 1.25F;
	GL11.glPushMatrix();
	RenderHelper.disableStandardItemLighting();
	RenderHelper.enableGUIStandardItemLighting();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scale, scale, scale);
	
	int xa = Math.round((int)LeftguiLeft/scale)+170+(x1/5);
	int ya = Math.round((int)guiTop/scale)+44+(y1/5);
	
	int xb = Math.round((int)LeftguiLeft/scale)+181+(x2/5);
	int yb = Math.round((int)guiTop/scale)+44+(y2/5);
	
	int xc = Math.round((int)LeftguiLeft/scale)+204+(x3/5);
	int yc = Math.round((int)guiTop/scale)+44+(y3/5);
	
	int xd = Math.round((int)LeftguiLeft/scale)+170+(x4/5);
	int yd = Math.round((int)guiTop/scale)+61+(y4/5);
	
	int xe = Math.round((int)LeftguiLeft/scale)+187+(x5/5);
	int ye = Math.round((int)guiTop/scale)+61+(y5/5);
	
	int xf = Math.round((int)LeftguiLeft/scale)+170+(61/5);
	int yf = Math.round((int)guiTop/scale)+83+(24/5);
	
	
	
	//int mouseX = (int) (((Mouse.getEventX()*this.width)/this.mc.displayWidth)/scale);
	//int mouseY = ((int) ((((Mouse.getEventY())*this.height)/this.mc.displayHeight)/scale));
	int mouseX = Mouse.getX() * res.getScaledWidth() / mc.displayWidth;
	int mouseY = res.getScaledHeight() - Mouse.getY() * res.getScaledHeight() / mc.displayHeight - 1;
	
	int scaledMouseX = (int)(mouseX/scale);
	int scaledMouseY = (int)(mouseY/scale);
	
	int iconX = 16 * res.getScaledWidth() / mc.displayWidth;
	int iconY = res.getScaledHeight() - 16 * res.getScaledHeight() / mc.displayHeight - 1;
	
	int scaledIconX = (int)(iconX/scale);
	int scaledIconY = (int)(iconY/scale);
	
	int count1 = item1.getCount();
	int count2 = item2.getCount();
	int count3 = item3.getCount();
	int count4 = item4.getCount();
	int count5 = item5.getCount();
	
	int count6 = item6.getCount();
	
	
	
	
	
	////System.out.println((float)(x/Mouse.getEventX()) + " " + (float)(y/Mouse.getEventY()));
	
	////System.out.println((Math.round(((int)LeftguiLeft)+170+(x1/5))+14)*((2.3)) + " " + Mouse.getEventX() + " " + (Math.round((int)guiTop)+36+(y1/5)+12)*((6.1)) + " " + Mouse.getEventY() );
	
	if((item1.getItem() != Items.SLIME_BALL) && !(pop)){
	itemRender.renderItemIntoGUI(item1, Math.round((int)LeftguiLeft/scale)+170+(x1/5) , Math.round((int)guiTop/scale)+44+(y1/5));
	number(Math.round(((int)LeftguiLeft/scale)+170+(x1/5))+14, (Math.round((int)guiTop/scale)+44+(y1/5)+12), count1);
			if( scaledMouseX > xa && scaledMouseX < (xa + 16) && scaledMouseY < (ya + 16) && scaledMouseY > ya)
			{
				////System.out.println("Moused Over");
				name(Math.round((int)LeftguiLeft/scale)+145+(60/5), Math.round((int)guiTop/scale)+20+(-10/5), item1.getDisplayName());			
			}
	}
	
	if((item2.getItem() != Items.SLIME_BALL) && !(pop)){
	itemRender.renderItemIntoGUI(item2, Math.round((int)LeftguiLeft/scale)+181+(x2/5) , Math.round((int)guiTop/scale)+44+(y2/5));
	number(Math.round((int)LeftguiLeft/scale)+181+(x2/5)+14 , Math.round((int)guiTop/scale)+36+(y2/5)+20, count2);
		if( scaledMouseX > xb && scaledMouseX < (xb + 16) && scaledMouseY < (yb + 16) && scaledMouseY > yb)
		{
			////System.out.println("Moused Over");
			name(Math.round((int)LeftguiLeft/scale)+145+(60/5), Math.round((int)guiTop/scale)+20+(-10/5), item2.getDisplayName());
		}
}
	
	if((item3.getItem() != Items.SLIME_BALL) && !(pop)){
	itemRender.renderItemIntoGUI(item3, Math.round((int)LeftguiLeft/scale)+204+(x3/5) , Math.round((int)guiTop/scale)+44+(y3/5));
	number(Math.round((int)LeftguiLeft/scale)+181+(x3/5)+37 , Math.round((int)guiTop/scale)+36+(y3/5)+20, count3);
		if( scaledMouseX > xc && scaledMouseX < (xc + 16) && scaledMouseY < (yc + 16) && scaledMouseY > yc)
		{
			////System.out.println("Moused Over");
			name(Math.round((int)LeftguiLeft/scale)+145+(60/5), Math.round((int)guiTop/scale)+20+(-10/5), item3.getDisplayName());
		}
	}
	
	if((item4.getItem() != Items.SLIME_BALL) && !(pop)){
	itemRender.renderItemIntoGUI(item4, Math.round((int)LeftguiLeft/scale)+170+(x4/5) , Math.round((int)guiTop/scale)+61+(y4/5));
	number(Math.round((int)LeftguiLeft/scale)+181+(x4/5)+3 , Math.round((int)guiTop/scale)+36+(y4/5)+37, count4);
		if( scaledMouseX > xd && scaledMouseX < (xd + 16) && scaledMouseY < (yd + 16) && scaledMouseY > yd)
		{
			////System.out.println("Moused Over");
			name(Math.round((int)LeftguiLeft/scale)+145+(60/5), Math.round((int)guiTop/scale)+20+(-10/5), item4.getDisplayName());
		}
	}
	
	if((item5.getItem() != Items.SLIME_BALL) && !(pop)){
	itemRender.renderItemIntoGUI(item5, Math.round((int)LeftguiLeft/scale)+187+(x5/5) , Math.round((int)guiTop/scale)+61+(y5/5));
	number(Math.round((int)LeftguiLeft/scale)+181+(x5/5)+20 , Math.round((int)guiTop/scale)+36+(y5/5)+37, count5);
	if( scaledMouseX > xe && scaledMouseX < (xe + 16) && scaledMouseY < (ye + 16) && scaledMouseY > ye)
	{
		////System.out.println("Moused Over");
		name(Math.round((int)LeftguiLeft/scale)+145+(60/5), Math.round((int)guiTop/scale)+20+(-10/5), item5.getDisplayName());
	}
	}
	
	if(item6 != null && pop){
	itemRender.renderItemIntoGUI(item6, Math.round((int)LeftguiLeft/scale)+170+(61/5) , Math.round((int)guiTop/scale)+83+(24/5));
	number(Math.round((int)LeftguiLeft/scale)+170+(61/5)+14 , Math.round((int)guiTop/scale)+83+(24/5)+12, count6);
		if( scaledMouseX > xf && scaledMouseX < (xf + 16) && scaledMouseY < (yf + 16) && scaledMouseY > yf)
		{
			////System.out.println("Moused Over");
			name(Math.round((int)LeftguiLeft/scale)+145+(60/5), Math.round((int)guiTop/scale)+20+(-10/5), item6.getDisplayName());
		}
	}
	
	
	GL11.glPopMatrix();
}

public void number(int x, int y, int count)
{
	
	float scale = 0.5F;
	GL11.glPushMatrix();
    GL11.glScaled(scale, scale, scale);
	fontRenderer.drawSplitString("x" + count, (int)(x/scale), (int)(y/scale), 216, 0);
	GL11.glPopMatrix();
	
}

public void name(int x, int y, String item)
{
	
	float scale = 0.5F;
	GL11.glPushMatrix();
    GL11.glScaled(scale, scale, scale);
	fontRenderer.drawSplitString(item, (int)(x/scale), (int)(y/scale), 216, -1);
	GL11.glPopMatrix();
	
}

public void renderItems(int x, int y, Item item1, Item item2, Item item3, Item item4, Item item5, Item item6, Item item7, Item item8, Item item9, Item item10)
{
	float scale = 1.25F;
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scale, scale, scale);
	

	
	if(item1 != null){
	itemRender.renderItemIntoGUI(new ItemStack(item1), Math.round((int)LeftguiLeft/scale)+170+(x/5) , Math.round((int)guiTop/scale)+36+(y/5));
	}
	
	if(item2 != null){
	itemRender.renderItemIntoGUI(new ItemStack(item2), Math.round((int)LeftguiLeft/scale)+187+(x/5) , Math.round((int)guiTop/scale)+36+(y/5));
	}
	
	if(item3 != null){
	itemRender.renderItemIntoGUI(new ItemStack(item3), Math.round((int)LeftguiLeft/scale)+204+(x/5) , Math.round((int)guiTop/scale)+36+(y/5));
	}
	
	if(item4 != null){
	itemRender.renderItemIntoGUI(new ItemStack(item4), Math.round((int)LeftguiLeft/scale)+170+(x/5) , Math.round((int)guiTop/scale)+53+(y/5));
	}
	
	if(item5 != null){
	itemRender.renderItemIntoGUI(new ItemStack(item5), Math.round((int)LeftguiLeft/scale)+187+(x/5) , Math.round((int)guiTop/scale)+53+(y/5));
	}
	
	if(item6 != null){
	itemRender.renderItemIntoGUI(new ItemStack(item6), Math.round((int)LeftguiLeft/scale)+204+(x/5) , Math.round((int)guiTop/scale)+53+(y/5));
	}
	
	if(item7 != null){
	itemRender.renderItemIntoGUI(new ItemStack(item7), Math.round((int)LeftguiLeft/scale)+170+(x/5) , Math.round((int)guiTop/scale)+70+(y/5));
	}
	
	if(item8 != null){
	itemRender.renderItemIntoGUI(new ItemStack(item8), Math.round((int)LeftguiLeft/scale)+187+(x/5) , Math.round((int)guiTop/scale)+70+(y/5));
	}
	
	if(item9 != null){
	itemRender.renderItemIntoGUI(new ItemStack(item9), Math.round((int)LeftguiLeft/scale)+204+(x/5) , Math.round((int)guiTop/scale)+70+(y/5));
	}
	
	if(item10 != null){
		itemRender.renderItemIntoGUI(new ItemStack(item10), Math.round((int)LeftguiLeft/scale)+230+(x/5) , Math.round((int)guiTop/scale)+53+(y/5));
		}
	
	GL11.glPopMatrix();
}

public void drawSlimePurple()
{

	int scaleF = res.getScaleFactor();
	float scale = 0.25F;
	
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scale, scale, scale);
	mc.getTextureManager().bindTexture(purpleSlime);
	

	
	drawTexturedModalRect(Math.round((int)LeftguiLeft/scale)+800, Math.round((int)guiTop/scale)+180, 0, 0, 255, 255);
	GL11.glPopMatrix();
	
	/*  GL11.glScaled(100.0, 100.0, 100.0);
    GL11.glTranslated(1.5D, 1.75D, 0.0D);
    GL11.glRotatef(200, 0.0F, 0.0F, 1.0F);
    //itemRender.renderItemIntoGUI(new ItemStack(Items.APPLE), (int)LeftguiLeft+55 , (int)guiTop+150);
    //GuiInventory.drawEntityOnScreen((int)LeftguiLeft+55 , (int)guiTop+150, 100, Mouse.getEventX(), Mouse.getEventY(), playerBreeder);
    RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
    rendermanager.setPlayerViewY(180.0F);
    rendermanager.setRenderShadow(false);
    rendermanager.renderEntityStatic(slime, 1.0F, false);
    rendermanager.setRenderShadow(true);*/
	
}

public void drawRain()
{

	int scaleF = res.getScaleFactor();
	float scale = 0.20F;
	
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scale, scale, scale);
	mc.getTextureManager().bindTexture(rainCloud);
	

	
	drawTexturedModalRect(Math.round((int)LeftguiLeft/scale)+350, Math.round((int)guiTop/scale)+180, 0, 0, 255, 255);
	GL11.glPopMatrix();

	
}

public void drawItem(Item item1, int x, int y, float scaleX, float scaleY)
{
	GL11.glPushMatrix();
	GL11.glScalef(scaleX, scaleY, 1.0F);
	itemRender.renderItemIntoGUI(new ItemStack(item1), Math.round((int)LeftguiLeft/scaleX)+x , Math.round((int)guiTop/scaleY)+y);
	GL11.glPopMatrix();
}

public void drawDna()
{

	int scaleF = res.getScaleFactor();
	float scaleX = 0.24F;
	float scaleY = 0.12F;
	float scaleZ = 0.15F;
	
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scaleX, scaleY, scaleZ);
	mc.getTextureManager().bindTexture(dna);
	

	
	drawTexturedModalRect(Math.round((int)LeftguiLeft/scaleX)+260, Math.round((int)guiTop/scaleY)+950, 0, 0, 255, 255);
	GL11.glPopMatrix();

	
}

public void drawChicken()
{

	int scaleF = res.getScaleFactor();
	float scale = 0.20F;
	
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scale, scale, scale);
	mc.getTextureManager().bindTexture(chicken);
	

	
	drawTexturedModalRect(Math.round((int)LeftguiLeft/scale)+350, Math.round((int)guiTop/scale)+250, 0, 0, 255, 255);
	GL11.glPopMatrix();

	
}

public void drawForest()
{

	int scaleF = res.getScaleFactor();
	float scale = 0.08F;
	
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scale, scale, scale);
	mc.getTextureManager().bindTexture(forestIco);
	

	
	drawTexturedModalRect(Math.round((int)LeftguiLeft/scale)+2400, Math.round((int)guiTop/scale)+775, 0, 0, 255, 255);
	GL11.glPopMatrix();

	
}

public void drawSwamp()
{

	int scaleF = res.getScaleFactor();
	float scale = 0.08F;
	
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scale, scale, scale);
	mc.getTextureManager().bindTexture(swampIco);
	

	
	drawTexturedModalRect(Math.round((int)LeftguiLeft/scale)+2400, Math.round((int)guiTop/scale)+1075, 0, 0, 255, 255);
	GL11.glPopMatrix();

	
}

public void drawTaiga()
{

	int scaleF = res.getScaleFactor();
	float scale = 0.08F;
	
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scale, scale, scale);
	mc.getTextureManager().bindTexture(taigaIco);
	

	
	drawTexturedModalRect(Math.round((int)LeftguiLeft/scale)+2400, Math.round((int)guiTop/scale)+1375, 0, 0, 255, 255);
	GL11.glPopMatrix();

	
}

public void drawFrame()
{

	int scaleF = res.getScaleFactor();
	float scale = 0.06F;
	
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scale, scale, scale);
	mc.getTextureManager().bindTexture(frame);
	

	
	drawTexturedModalRect(Math.round((int)LeftguiLeft/scale)+1900, Math.round((int)guiTop/scale)+180, 0, 0, 255, 260);
	GL11.glPopMatrix();

	
}

public void drawSlimeGreen(boolean pop)
{
	
	int scaleF = res.getScaleFactor();
	float scale = 0.25F;
	
	GL11.glPushMatrix();
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glScalef(scale, scale, scale);
	if(!(pop))
	{
		mc.getTextureManager().bindTexture(greenSlime);
	}else{
		mc.getTextureManager().bindTexture(greenSlimePop);
	}
	
	

	
	drawTexturedModalRect(Math.round((int)LeftguiLeft/scale)+825, Math.round((int)guiTop/scale)+350, 0, 0, 255, 255);
	GL11.glPopMatrix();
	
}


public void drawScreenL()
{
	 GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	 switch (currPage) {
     case 0:  mc.getTextureManager().bindTexture(bookPageTexturesLeft[0]);
     break;
              
     case 1:  mc.getTextureManager().bindTexture(bookPageTexturesLeft[1]);
     break;
     
     case 2:  mc.getTextureManager().bindTexture(bookPageTexturesLeft[2]);
     break;
     
     case 3:  mc.getTextureManager().bindTexture(bookPageTexturesLeft[3]);
     break;
     
     case 4:  mc.getTextureManager().bindTexture(bookPageTexturesLeft[4]);
     break;
     
     default: mc.getTextureManager().bindTexture(bookPageTexturesLeft[5]);
    break;
     
	 }        
              
	//mc.getTextureManager().bindTexture(bookPageTextures[1]);

	drawTexturedModalRect((int)LeftguiLeft, (int)guiTop, 0, 0, bookImageWidth, bookImageHeight);
	
	
}

public void drawScreenR()
{
	 GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	 switch (currPage) {
     case 0:  mc.getTextureManager().bindTexture(bookPageTexturesRight[0]);
     break;
              
     case 1:  mc.getTextureManager().bindTexture(bookPageTexturesRight[1]);
     break;
     
     case 2:  mc.getTextureManager().bindTexture(bookPageTexturesRight[2]);
     break;
     
     case 3:  mc.getTextureManager().bindTexture(bookPageTexturesRight[3]);
     break;
     
     case 4:  mc.getTextureManager().bindTexture(bookPageTexturesRight[4]);
     break;
     
     default: mc.getTextureManager().bindTexture(bookPageTexturesRight[5]);
     break;
     
	 }  
     
	//mc.getTextureManager().bindTexture(bookPageTextures[0]);

    drawTexturedModalRect((int)RightguiLeft, (int)guiTop, 0, 0, bookImageWidth, bookImageHeight);
    
}


/**
 * Called when a mouse button is pressed and the mouse is moved around. 
 * Parameters are : mouseX, mouseY, lastButtonClicked & 
 * timeSinceMouseClick.
 */
@Override
protected void mouseClickMove(int parMouseX, int parMouseY, 
      int parLastButtonClicked, long parTimeSinceMouseClick) 
{
 
}

@Override
protected void actionPerformed(GuiButton parButton) 
{
 if (parButton == buttonNextPage)
    {
        if (currPage < bookTotalPages - 1)
        {
            ++currPage;
            this.timer = 0;
        }
    }
    else if (parButton == buttonPreviousPage)
    {
        if (currPage > 0)
        {
            --currPage;
            this.timer = 0;
        }
    }
    else if (parButton == buttonIntroduction)
    {
        currPage = 1;
    }
 
    else if (parButton == buttonSlimesFind)
    {
        currPage = 2;
    }
 
    else if (parButton == buttonSlimeCare)
    {
        currPage = 3;
    }
 
    else if (parButton == buttonSlimeBreeding)
    {
        currPage = 5;
    }
 
    else if (parButton == buttonSlimeGenetics)
    {
        currPage = 7;
    }
 
    else if (parButton == buttonSlimeCrafting)
    {
        currPage = 8;
    }
 
    else if (parButton == buttonHome)
    {
        currPage = 0;
    }
    /*else if (parButton == buttonSign)
    {
    	//System.out.println(playerBook.hasTagCompound());
    	//System.out.println("value = " +playerBook.getTagCompound().getInteger("Owned"));
    	
    	NBTTagCompound bookNBT = playerBook.getTagCompound();
    	
    	bookNBT.setInteger("Owned", 3);
    	
		
    	
    	playerBook.setTagCompound(bookNBT);
    	//playerBook.readFromNBT(bookNBT);*/
    	
			
		
    	
    	
    	//SLIMEBOOK.setSigned(1);
    	/*ItemStack book = playerBreeder.getHeldItem(breederHand);
    	if(book.getTagCompound().getInteger("Signed") == 0)
    	{
    		
    	
    	book.getTagCompound().setInteger("Signed", 1);
    	//System.out.println("Written");
    	}*/
    	
    
 
}

/**
 * Called when the screen is unloaded. Used to disable keyboard repeat 
 * events
 */
@Override
public void onGuiClosed() 
{
 
}

/**
 * Returns true if this GUI should pause the game when it is displayed in 
 * single-player
 */
@Override
public boolean doesGuiPauseGame()
{
    return false;
}



@SideOnly(Side.CLIENT)
static class NextPageButton extends GuiButton
{
    private final boolean isNextButton;

    public NextPageButton(int parButtonId, int parPosX, int parPosY, 
          boolean parIsNextButton)
    {
        super(parButtonId, parPosX, parPosY, 23, 13, "");
        isNextButton = parIsNextButton;
    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void drawButton(Minecraft mc, int parX, int parY, float partialTicks)
    {
        if (visible)
        {
            boolean isButtonPressed = (parX >= x 
                  && parY >= y 
                  && parX < x + width 
                  && parY < y + height);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(bookPageTexturesLeft[1]);
            int textureX = 0;
            int textureY = 192;

            if (isButtonPressed)
            {
                textureX += 23;
            }

            if (!isNextButton)
            {
                textureY += 13;
            }

            drawTexturedModalRect(x, y, textureX, textureY, 23, 13);
        }
    }
}

@SideOnly(Side.CLIENT)
static class NextSubPageButton extends GuiButton
{
    private final boolean isNextButton;

    public NextSubPageButton(int parButtonId, int parPosX, int parPosY, 
          boolean parIsNextButton)
    {
        super(parButtonId, parPosX, parPosY, 23, 13, "");
        isNextButton = parIsNextButton;
    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void drawButton(Minecraft mc, int parX, int parY, float partialTicks)
    {
        if (visible)
        {
            boolean isButtonPressed = (parX >= x 
                  && parY >= y 
                  && parX < x + width 
                  && parY < y + height);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(bookPageTexturesLeft[0]);
            int textureX = 51;
            int textureY = 206;

            if (isButtonPressed)
            {
                textureX += 23;
            }

            if (!isNextButton)
            {
                textureY += 13;
            }

            drawTexturedModalRect(x, y, textureX, textureY, 11, 10);
        }
    }
}

@SideOnly(Side.CLIENT)
static class HomeButton extends GuiButton
{

    public HomeButton(int parButtonId, int parPosX, int parPosY)
    {
        super(parButtonId, parPosX, parPosY, 12, 11, "");
        
    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void drawButton(Minecraft mc, int parX, int parY, float partialTicks)
    {
        if (visible)
        {
            boolean isButtonPressed = (parX >= x 
                  && parY >= y 
                  && parX < x + width 
                  && parY < y + height);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            //GL11.glPushMatrix();
            //GL11.glScaled(1.0, 1.1, 1.0);
            mc.getTextureManager().bindTexture(bookPageTexturesLeft[1]);
            int textureX = 51;
            int textureY = 193;

            if (isButtonPressed)
            {
                textureX += 15;
            }


            drawTexturedModalRect(x, y, textureX, textureY, 12, 13);
            //GL11.glPopMatrix();
        }
    }
}

@SideOnly(Side.CLIENT)
static class SignButton extends GuiButton
{

    public SignButton(int parButtonId, int parPosX, int parPosY)
    {
        super(parButtonId, parPosX, parPosY, 14, 16, "");
        
    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void drawButton(Minecraft mc, int parX, int parY, float partialTicks)
    {
        if (visible)
        {
            boolean isButtonPressed = (parX >= x 
                  && parY >= y 
                  && parX < x + width 
                  && parY < y + height);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            //GL11.glPushMatrix();
            //GL11.glScaled(1.0, 1.1, 1.0);
            mc.getTextureManager().bindTexture(bookPageTexturesLeft[0]);
            int textureX = 83;
            int textureY = 191;

            if (isButtonPressed)
            {
                textureX += 15;
            }


            drawTexturedModalRect(x, y, textureX, textureY, 12, 13);
            //GL11.glPopMatrix();
        }
    }
}



}