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
        add("slimebreeder.check.info", "Hunger Check Value:");
        add("slimebreeder.configgui.enableHungerReduction", "Enable slime hunger reduction");
    }

}

