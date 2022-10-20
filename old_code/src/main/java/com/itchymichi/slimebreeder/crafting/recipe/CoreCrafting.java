package com.itchymichi.slimebreeder.crafting.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;

public class CoreCrafting extends ShapelessRecipes
{
    /** Is the ItemStack that you get when craft the recipe. */
    private ItemStack recipeOutput;
    /** Is a List of ItemStack that composes the recipe. */
    public final NonNullList<Ingredient> recipeItems;
    private final String group;
    private final boolean isSimple;
    public NBTTagCompound nbttag;

    public CoreCrafting(String group, ItemStack output, NonNullList<Ingredient> ingredients)
    {
    	
    	super(group, output, ingredients);
        this.group = group;
        this.recipeOutput = output;
        this.recipeItems = ingredients;
        boolean simple = false;
        for (Ingredient i : ingredients)
            simple &= i.isSimple();
        this.isSimple = simple;
        System.out.println("Running Constructor? ");
    }
    
    @Override
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        int ingredientCount = 0;
        net.minecraft.client.util.RecipeItemHelper recipeItemHelper = new net.minecraft.client.util.RecipeItemHelper();
        List<ItemStack> inputs = Lists.newArrayList();

        for (int i = 0; i < inv.getHeight(); ++i)
        {
            for (int j = 0; j < inv.getWidth(); ++j)
            {
                ItemStack itemstack = inv.getStackInRowAndColumn(j, i);
                
                if(itemstack.hasTagCompound()) {
                	nbttag = itemstack.getTagCompound();
                }

                if (!itemstack.isEmpty())
                {
                    ++ingredientCount;
                    if (this.isSimple)
                        recipeItemHelper.accountStack(itemstack, 1);
                    else
                        inputs.add(itemstack);
                }
            }
        }

        if (ingredientCount != this.recipeItems.size())
            return false;

        if (this.isSimple)
            return recipeItemHelper.canCraft(this, null);

        return net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs, this.recipeItems) != null;
    }
    
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
    	ItemStack outStack = recipeOutput.copy();
    	
    	
    	System.out.println(nbttag);
    	
    	if(nbttag != null) {
    		String name = nbttag.getCompoundTag("display").getString("Name").toString();
    		System.out.println(nbttag.getCompoundTag("display").getString("Name").toString());
    		
    		
    		outStack.getOrCreateSubCompound("display");
    		outStack.getTagCompound().getCompoundTag("display").setString("Name", name);

    		System.out.println("out name = "  + outStack.getTagCompound().getCompoundTag("display").getString("Name").toString());
    		
    	}
    	
    	this.recipeOutput = outStack;
    	
    	
    	return this.recipeOutput.copy();
    }
    
    public static class Factory implements IRecipeFactory {
    	
    	
    	
        /*@Override
        public IRecipe parse(JsonContext context, JsonObject json) {
            return deserialize(json);
        }*/
    	
    	private static NonNullList<Ingredient> deserializeIngredients(JsonArray array)
        {
            NonNullList<Ingredient> nonnulllist = NonNullList.<Ingredient>create();

            for (int i = 0; i < array.size(); ++i)
            {
                Ingredient ingredient = ShapedRecipes.deserializeIngredient(array.get(i));

                if (ingredient != Ingredient.EMPTY)
                {
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }
        
        @Override
        public IRecipe parse(JsonContext context, JsonObject json) {
        	
        	 String s = JsonUtils.getString(json, "group", "");
        	 NonNullList<Ingredient> nonnulllist = deserializeIngredients(JsonUtils.getJsonArray(json, "ingredients"));
        	 
        	 if (nonnulllist.isEmpty())
             {
                 throw new JsonParseException("No ingredients for shapeless recipe");
             }
             else if (nonnulllist.size() > 9)
             {
                 throw new JsonParseException("Too many ingredients for shapeless recipe");
             }
             else
             {
                 ItemStack itemstack = ShapedRecipes.deserializeItem(JsonUtils.getJsonObject(json, "result"), true);
                 return new CoreCrafting(s, itemstack, nonnulllist);
             }
        }
        
    }  
}