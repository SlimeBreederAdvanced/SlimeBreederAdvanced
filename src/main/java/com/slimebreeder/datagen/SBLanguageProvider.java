package com.slimebreeder.datagen;

import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.block.SBBlocks;
import com.slimebreeder.item.SBItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class SBLanguageProvider extends LanguageProvider {

    public SBLanguageProvider(DataGenerator gen, String locale) {
        super(gen, SlimeBreeder.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.slimebreedertab", "SlimeBreeder Tab");
        add(SBBlocks.JAR_BLOCK.get(), "Jar");
        add(SBBlocks.COW_JAR_BLOCK.get(), "Cow Jar");
        add(SBBlocks.AQUA_SLIME_BLOCK.get(), "Aqua Slime Block");
        add(SBBlocks.FLAME_SLIME_BLOCK.get(), "Flame Slime Block");
        add(SBBlocks.LUNAR_SLIME_BLOCK.get(), "Lunar Slime Block");
        add(SBBlocks.JUNGLE_SLIME_BLOCK.get(), "Jungle Slime Block");
        add(SBItems.AQUA_SLIME_SPAWN_EGG.get(), "Aqua Slime Spawn Egg");
        add(SBItems.LUNAR_SLIME_SPAWN_EGG.get(), "Lunar Slime Spawn Egg");
        add(SBItems.LUNAR_SLIME_BALL.get(), "Lunar Slime Ball");
        add(SBItems.LUNAR_SLIME_GEL.get(), "Lunar Slime Gel");
        add(SBItems.HUNGER_CHECK_STICK.get(), "Hunger Check Stick");
        add(SBItems.LUNAR_SLIME_JAM.get(), "Lunar Slime Jam");
        add(SBItems.LUNAR_SLIME_SANDWICH.get(), "Lunar Slime Sandwich");
        add(SBItems.AQUA_SLIME_BALL.get(), "Aqua Slime Ball");
        add(SBItems.AQUA_SLIME_GEL.get(), "Aqua Slime Gel");
        add(SBItems.AQUA_SLIME_JAM.get(), "Auqa Slime Jam");
        add(SBItems.AQUA_SLIME_SANDWICH.get(), "Aqua Slime Sandwich");
        add(SBItems.FLAME_SLIME_SPAWN_EGG.get(), "Flame Slime Spawn Egg");
        add(SBItems.FLAME_SLIME_JAM.get(), "Flame Slime Jam");
        add(SBItems.FLAME_SLIME_BALL.get(), "Flame Slime Ball");
        add(SBItems.FLAME_SLIME_GEL.get(), "Flame Slime Gel");
        add(SBItems.FLAME_SLIME_SANDWICH.get(), "Flame Slime Sandwich");
        add(SBItems.JUNGLE_SLIME_JAM.get(), "Jungle Slime Jam");
        add(SBItems.JUNGLE_SLIME_BALL.get(), "Jungle Slime Ball");
        add(SBItems.JUNGLE_SLIME_GEL.get(), "Jungle Slime Gel");
        add(SBItems.JUNGLE_SLIME_SANDWICH.get(), "Jungle Slime Sandwich");
        add(SBItems.JUNGLE_SLIME_SPAWN_EGG.get(), "Jungle Slime Spawn Egg");
        add("slimebreeder.check.info", "Hunger Check Value:");
        add("slimebreeder.configgui.enableHungerReduction", "Enable slime hunger reduction");
    }

}

