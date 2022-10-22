package com.slimebreeder.datagen;

import com.slimebreeder.block.SBBlocks;
import com.slimebreeder.item.SBItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class SBRecipeProvider extends RecipeProvider {

    public SBRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        smeltingSlimeBalls(SBItems.AQUA_SLIME_BALL.get(), SBItems.AQUA_SLIME_GEL.get(), pFinishedRecipeConsumer);
        smeltingSlimeBalls(SBItems.LUNAR_SLIME_BALL.get(), SBItems.LUNAR_SLIME_GEL.get(), pFinishedRecipeConsumer);
        smeltingSlimeBalls(SBItems.FLAME_SLIME_BALL.get(), SBItems.FLAME_SLIME_GEL.get(), pFinishedRecipeConsumer);
        smeltingSlimeBalls(SBItems.JUNGLE_SLIME_BALL.get(), SBItems.JUNGLE_SLIME_GEL.get(), pFinishedRecipeConsumer);
        recipeSlimeSandwich(SBItems.AQUA_SLIME_GEL.get(), SBItems.AQUA_SLIME_SANDWICH.get(), pFinishedRecipeConsumer);
        recipeSlimeSandwich(SBItems.LUNAR_SLIME_GEL.get(), SBItems.LUNAR_SLIME_SANDWICH.get(), pFinishedRecipeConsumer);
        recipeSlimeSandwich(SBItems.JUNGLE_SLIME_GEL.get(), SBItems.JUNGLE_SLIME_SANDWICH.get(), pFinishedRecipeConsumer);
        recipeSlimeSandwich(SBItems.FLAME_SLIME_GEL.get(), SBItems.FLAME_SLIME_SANDWICH.get(), pFinishedRecipeConsumer);
        recipeSlimeJam(SBItems.AQUA_SLIME_GEL.get(), SBItems.AQUA_SLIME_JAM.get(), pFinishedRecipeConsumer);
        recipeSlimeJam(SBItems.LUNAR_SLIME_GEL.get(), SBItems.LUNAR_SLIME_JAM.get(), pFinishedRecipeConsumer);
        recipeSlimeJam(SBItems.JUNGLE_SLIME_GEL.get(), SBItems.JUNGLE_SLIME_JAM.get(), pFinishedRecipeConsumer);
        recipeSlimeJam(SBItems.FLAME_SLIME_GEL.get(), SBItems.FLAME_SLIME_JAM.get(), pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(
                SBBlocks.JAR_BLOCK.get()).
                pattern("XXX").
                pattern("X X").
                pattern("XXX").
                define('X', Items.GLASS).
                unlockedBy("has_glass", has(Items.GLASS)).
                save(pFinishedRecipeConsumer);
    }

    protected void smeltingSlimeBalls(Item originItem, Item finalItem, Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(originItem), finalItem, 0.35F, 200).unlockedBy("has_" + originItem.getDescriptionId(), has(originItem)).save(pFinishedRecipeConsumer);
    }

    protected void recipeSlimeSandwich(Item itemNeed, Item finalItem, Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ShapelessRecipeBuilder.shapeless(finalItem).requires(itemNeed).requires(Items.BREAD).unlockedBy("has_bread", has(Items.BREAD)).save(pFinishedRecipeConsumer);
    }

    protected void recipeSlimeJam(Item itemNeed, Item finalItem, Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ShapelessRecipeBuilder.shapeless(finalItem).requires(itemNeed).requires(Items.GLASS_BOTTLE).unlockedBy("has_glass_bottle", has(Items.GLASS_BOTTLE)).save(pFinishedRecipeConsumer);
    }
}
