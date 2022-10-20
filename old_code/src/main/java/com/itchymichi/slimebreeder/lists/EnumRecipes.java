package com.itchymichi.slimebreeder.lists;

import java.util.concurrent.CopyOnWriteArrayList;

import com.itchymichi.slimebreeder.SlimeBreederItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;


public class EnumRecipes 
{
	

public static CopyOnWriteArrayList<SlimeRecipes> listOfRecipes = new CopyOnWriteArrayList<SlimeRecipes>();



//static String r1Text1 = "By dissolving the impurites found in Diamond Ore with the acidic gel of a slime, a slimy diamond slurry can be made. This slurry can be cooked in a furnace to make Diamonds!";
static String  r1Text1 = "guiSlimepedia.recipe.r1Text1";
static String  r1Text2 = "guiSlimepedia.recipe.r1Text2";
static String  r1Text3 = "guiSlimepedia.recipe.r1Text3";
static String  r1Text4 = "guiSlimepedia.recipe.r1Text4";
static String  r1Text5 = "guiSlimepedia.recipe.r1Text5";
static String  r1Text6 = "guiSlimepedia.recipe.r1Text6";
static String  r1Text7 = "guiSlimepedia.recipe.r1Text7";
static String  r1Text8 = "guiSlimepedia.recipe.r1Text8";
static String  r1Text9 = "guiSlimepedia.recipe.r1Text9";

static String  r2Text1 = "guiSlimepedia.recipe.r2Text1";
static String  r2Text2 = "guiSlimepedia.recipe.r2Text2";




/*static String r1Text2 = "By dissolving the impurites found in Gold Ore with the acidic gel of a slime, a slimy gold slurry can be made. This slurry can be cooked in a furnace to make Gold Ingots!";
static String r1Text3 = "By dissolving the impurites found in Iron Ore with the acidic gel of a slime, a slimy iron slurry can be made. This slurry can be cooked in a furnace to make Iron Ingots!";
static String r1Text4 = "For decades the hides of animals have been treated with acidic compounds to form a more resistant material known as leather. This process is pretty much the same just with the hide of a dead human.... try not to think too much about it";
static String r1Text5 = "Sandstone is just compressed sand held together by some form of binder, turns out this binder is not resistant to acid.";
static String r1Text6 = "Similar to processing gravel in slimes, big bits of rock can dissolve to smaller bits inside a slime.";
static String r1Text7 = "Gravel is a mixture of small and large chunks of silica, amongst other things. By feeding gravel to slimes and letting them work their magic we can partially dissolve those big bits of silica leaving smaller bits of silica.";
static String r1Text8 = "Dirt is a blend of organic, siliceous and clay-like materials. Nothing destroys organic materials better than a nice strong acid wash.";
static String r1Text9 = "Sticks and feathers are easily dissolved in a slimes acidity, because of this arrows can easily be recycled.";

static String r2Text1 = "One application of the Clear Slime Crystal is its ability to allow slimes to coat tools and armours with gel.\n\nArmours and tools coated in this gel exhibit different properties depending on the slime used to coat them!";
static String r2Text2 = "By dissolving diamond and glass in a 1 : 5 ratio a new type of semi-crystaline material can be made. This crystal is neither a solid nor liquid and has practically limitless applications.";*/
	
	public static CopyOnWriteArrayList<SlimeRecipes> initRecipeList() 
	{
		
		///////////CrystalslimeNBT
		
		NBTTagCompound CrystalslimeNBT = new ItemStack(SlimeBreederItems.CLEARSLIMECRYSTAL).serializeNBT();
		CrystalslimeNBT.setBoolean("chargable", true);
		
		
		
		//Five
		//listOfRecipes.add(new SlimeRecipes(new ItemStack(Item.getItemFromBlock(Blocks.IRON_ORE), 1), new ItemStack(Item.getItemFromBlock(Blocks.GOLD_ORE), 1), new ItemStack(Items.APPLE, 1), new ItemStack(Items.ARROW, 1), new ItemStack(Item.getItemFromBlock(Blocks.DIAMOND_ORE), 1), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.CAKE, 1), 0.0D, 0.0D, 0, 0, 0, false, Text1));
		
		//Four
		
		
		//Three
		
		
		//Two
		listOfRecipes.add(new SlimeRecipes(new ItemStack(SlimeBreederItems.UNKNOWNITEM,1,0), new ItemStack(SlimeBreederItems.CLEARSLIMECRYSTAL,1,0), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(SlimeBreederItems.UNKNOWNITEM, 1), 0.0D, 0.0D, 0, 0, 0, false, r2Text1));
		listOfRecipes.add(new SlimeRecipes(new ItemStack(Items.DIAMOND,1,0), new ItemStack(Item.getItemFromBlock(Blocks.GLASS_PANE),5), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(SlimeBreederItems.CLEARSLIMECRYSTAL, 1), 0.0D, 0.0D, 0, 0, 0, false, r2Text2));
		listOfRecipes.add(new SlimeRecipes(new ItemStack(SlimeBreederItems.SLIMESEED,1,0), new ItemStack(CrystalslimeNBT), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(CrystalslimeNBT), 0.0D, 0.0D, 0, 0, 0, false, r2Text2));
		
		//One
		listOfRecipes.add(new SlimeRecipes(new ItemStack(Item.getItemFromBlock(Blocks.DIAMOND_ORE), 1), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(SlimeBreederItems.DIAMONDSLIMECHUNK, 2), 0.0D, 0.0D, 0, 0, 0, false, r1Text1));
		listOfRecipes.add(new SlimeRecipes(new ItemStack(Item.getItemFromBlock(Blocks.GOLD_ORE), 1), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(SlimeBreederItems.GOLDSLIMECHUNK, 2), 0.0D, 0.0D, 0, 0, 0, false, r1Text2));
		listOfRecipes.add(new SlimeRecipes(new ItemStack(Item.getItemFromBlock(Blocks.IRON_ORE), 1), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(SlimeBreederItems.IRONSLIMECHUNK, 2), 0.0D, 0.0D, 0, 0, 0, false, r1Text3));

		
		listOfRecipes.add(new SlimeRecipes(new ItemStack(Items.ROTTEN_FLESH,3,0), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.LEATHER,1,0), 0.0D, 0.0D, 0, 0, 0, false, r1Text4));
		listOfRecipes.add(new SlimeRecipes(new ItemStack(Blocks.SANDSTONE,1,0), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Blocks.SAND,4,0), 0.0D, 0.0D, 0, 0, 0, false, r1Text5));
		listOfRecipes.add(new SlimeRecipes(new ItemStack(Blocks.COBBLESTONE,1,0), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Blocks.GRAVEL,1,0), 0.0D, 0.0D, 0, 0, 0, false, r1Text6));
		listOfRecipes.add(new SlimeRecipes(new ItemStack(Blocks.GRAVEL,1,0), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Blocks.SAND,1,0), 0.0D, 0.0D, 0, 0, 0, false, r1Text7));
		//listOfRecipes.add(new SlimeRecipes(new ItemStack(TinkerWorld.slimeDirt,1,0), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Blocks.BONE_BLOCK,1,0), 0.0D, 0.0D, 0, 0, 0, false, r1Text8));//move(Free-flowing)3-7, health(rubbery)45, attack(acidity), sticky
		listOfRecipes.add(new SlimeRecipes(new ItemStack(Blocks.DIRT,1,0), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Blocks.SAND,1,0), 0.0D, 0.0D, 0, 0, 0, false, r1Text8));//move(Free-flowing)3-7, health(rubbery)45, attack(acidity), sticky
		listOfRecipes.add(new SlimeRecipes(new ItemStack(Items.ARROW,5,0), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.FLINT,1,0), 0.0D, 0.0D, 0, 0, 0, false, r1Text9));

																																																																				/*
																																																																		        
																																																																		        if (dropMoveSpeed2 >= 3 && dropMoveSpeed2 < 4) 
																																																																		        {
																																																																		            E = TextFormatting.WHITE+"[Slightly]";
																																																																		        } else if (dropMoveSpeed2 >= 4 && dropMoveSpeed2 < 5) {
																																																																		        	 E = TextFormatting.AQUA+"[Somewhat]";
																																																																		        } else if (dropMoveSpeed2 >= 5 && dropMoveSpeed2 < 6) {
																																																																		        	 E = TextFormatting.BLUE+"[Very]";
																																																																		        } else if (dropMoveSpeed2 >= 6 && dropMoveSpeed2 <= 7) {
																																																																		        	 E = TextFormatting.DARK_PURPLE+"[Extremely]";
																																																																		        } else {
																																																																		        	 E = " ";
																																																																		        }
																																																																		        
																																																																		        if (dropHealth2 >= 3 && dropHealth2 < 6) 
																																																																		        {
																																																																		            C = TextFormatting.WHITE+"[Slightly]";
																																																																		        } else if (dropHealth2 >= 6 && dropHealth2 < 12) {
																																																																		        	 C = TextFormatting.AQUA+"[Somewhat]";
																																																																		        } else if (dropHealth2 >= 12 && dropHealth2 < 25) {
																																																																		        	 C = TextFormatting.BLUE+"[Very]";
																																																																		        } else if (dropHealth2 >= 25 && dropHealth2 <= 50) {
																																																																		        	 C = TextFormatting.DARK_PURPLE+"[Extremely]";
																																																																		        } else {
																																																																		        	 C = " ";
																																																																		        }
																																																																		        
																																																																		        if (AD >= 2 && AD < 4) 
																																																																		        {
																																																																		            B = TextFormatting.WHITE + "[Slightly]";
																																																																		        } else if (AD >= 4 && AD < 6) {
																																																																		        	 B = TextFormatting.AQUA +"[Somewhat]";
																																																																		        } else if (AD >= 6 && AD < 8) {
																																																																		        	 B = TextFormatting.BLUE + "[Very]";
																																																																		        } else if (AD >= 8 && AD <= 10) {
																																																																		        	 B = TextFormatting.DARK_PURPLE+"[Extremely]";
																																																																		        } else {
																																																																		        	 B = " ";
																																																																		        }
																																																																				
																																																																		        if (STY >= 0 && STY < 1) 
																																																																		        {
																																																																		            A = TextFormatting.WHITE + "[Slightly]";
																																																																		        } else if (STY >= 1 && STY < 2) {
																																																																		        	 A = TextFormatting.AQUA +"[Somewhat]";
																																																																		        } else if (STY >= 2 && STY < 3) {
																																																																		        	 A = TextFormatting.BLUE +"[Very]";
																																																																		        } else if (STY >= 3 && STY <= 4) {
																																																																		        	 A = TextFormatting.DARK_PURPLE+"[Extremely]";
																																																																		        } else {
																																																																		        	 A = " ";
																																																																		        }*/
		
		
		
		
		
		//listOfRecipes.add(new SlimeRecipes(new ItemStack(SlimeBreederItems.SLIMERESIN), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Blocks.SAND,1,0), 0.0D, 0.0D, 0, 0, 0, false));
		
		
		//listOfRecipes.add(new SlimeRecipes(new ItemStack(Items.dia), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Item.getItemFromBlock(Blocks.DIAMOND_BLOCK)), 0.0D, 0.0D, 0, 0, 0, false));

		return listOfRecipes;

		}

		public static int getSize() 
		{
			int length = listOfRecipes.size();
			
			return length;
		}
		
		
		//listOfRecipes.add(new SlimeRecipes(new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.SLIME_BALL), new ItemStack(Item.getItemFromBlock(Blocks.DIAMOND_BLOCK)), 0.0D, 0.0D, 0, 0, 0, false));

		
}
