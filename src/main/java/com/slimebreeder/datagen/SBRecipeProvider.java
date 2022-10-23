package com.slimebreeder.datagen;

import com.slimebreeder.block.SBBlocks;
import com.slimebreeder.item.SBItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class SBRecipeProvider extends RecipeProvider {

    public SBRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ShapedRecipeBuilder.shaped(
                        SBBlocks.JAR_BLOCK.get(), 4).
                pattern("XXX").
                pattern("X X").
                pattern("XXX").
                define('X', Items.GLASS).
                unlockedBy("has_glass", has(Items.GLASS)).
                save(pFinishedRecipeConsumer);

        smeltingSlimeBalls(SBItems.AQUA_SLIME_BALL.get(), SBItems.AQUA_SLIME_GEL.get(), pFinishedRecipeConsumer);
        smeltingSlimeBalls(SBItems.LUNAR_SLIME_BALL.get(), SBItems.LUNAR_SLIME_GEL.get(), pFinishedRecipeConsumer);
        smeltingSlimeBalls(SBItems.FLAME_SLIME_BALL.get(), SBItems.FLAME_SLIME_GEL.get(), pFinishedRecipeConsumer);
        smeltingSlimeBalls(SBItems.JUNGLE_SLIME_BALL.get(), SBItems.JUNGLE_SLIME_GEL.get(), pFinishedRecipeConsumer);
        smeltingSlimeBalls(SBItems.BLACK_SLIME_BALL.get(), SBItems.BLACK_SLIME_GEL.get(), pFinishedRecipeConsumer);
        smeltingSlimeBalls(SBItems.PURPLE_SLIME_BALL.get(), SBItems.PURPLE_SLIME_GEL.get(), pFinishedRecipeConsumer);
        smeltingSlimeBalls(SBItems.CORRUPT_SLIME_BALL.get(), SBItems.CORRUPT_SLIME_GEL.get(), pFinishedRecipeConsumer);
        recipeSlimeSandwich(SBItems.AQUA_SLIME_GEL.get(), SBItems.AQUA_SLIME_SANDWICH.get(), pFinishedRecipeConsumer);
        recipeSlimeSandwich(SBItems.LUNAR_SLIME_GEL.get(), SBItems.LUNAR_SLIME_SANDWICH.get(), pFinishedRecipeConsumer);
        recipeSlimeSandwich(SBItems.JUNGLE_SLIME_GEL.get(), SBItems.JUNGLE_SLIME_SANDWICH.get(), pFinishedRecipeConsumer);
        recipeSlimeSandwich(SBItems.FLAME_SLIME_GEL.get(), SBItems.FLAME_SLIME_SANDWICH.get(), pFinishedRecipeConsumer);
        recipeSlimeJam(SBItems.AQUA_SLIME_GEL.get(), SBItems.AQUA_SLIME_JAM.get(), pFinishedRecipeConsumer);
        recipeSlimeJam(SBItems.LUNAR_SLIME_GEL.get(), SBItems.LUNAR_SLIME_JAM.get(), pFinishedRecipeConsumer);
        recipeSlimeJam(SBItems.JUNGLE_SLIME_GEL.get(), SBItems.JUNGLE_SLIME_JAM.get(), pFinishedRecipeConsumer);
        recipeSlimeJam(SBItems.FLAME_SLIME_GEL.get(), SBItems.FLAME_SLIME_JAM.get(), pFinishedRecipeConsumer);
        recipeSlimeBlock(SBItems.AQUA_SLIME_GEL.get(), SBBlocks.AQUA_SLIME_BLOCK.get(), pFinishedRecipeConsumer);
        recipeSlimeBlock(SBItems.LUNAR_SLIME_GEL.get(), SBBlocks.LUNAR_SLIME_BLOCK.get(), pFinishedRecipeConsumer);
        recipeSlimeBlock(SBItems.JUNGLE_SLIME_GEL.get(), SBBlocks.JUNGLE_SLIME_BLOCK.get(), pFinishedRecipeConsumer);
        recipeSlimeBlock(SBItems.FLAME_SLIME_GEL.get(), SBBlocks.FLAME_SLIME_BLOCK.get(), pFinishedRecipeConsumer);
        recipeSlimeBlock(SBItems.BLACK_SLIME_GEL.get(), SBBlocks.BLACK_SLIME_BLOCK.get(), pFinishedRecipeConsumer);
        recipeSlimeBlock(SBItems.PURPLE_SLIME_GEL.get(), SBBlocks.PURPLE_SLIME_BLOCK.get(), pFinishedRecipeConsumer);
        recipeSlimeBlock(SBItems.CORRUPT_SLIME_GEL.get(), SBBlocks.CORRUPT_SLIME_BLOCK.get(), pFinishedRecipeConsumer);
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

    protected void recipeSlimeBlock(Item itemNeed, Block finalGet, Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ShapedRecipeBuilder.shaped(finalGet.asItem(), 6).
                define('#', itemNeed).
                pattern("##").
                pattern("##").
                unlockedBy("has_" + itemNeed.getDescriptionId(), has(itemNeed)).save(pFinishedRecipeConsumer);
    }
}
