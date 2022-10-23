package com.slimebreeder.datagen;

import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.block.SBBlocks;
import com.slimebreeder.item.SBItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class SBLanguageZhProvider extends LanguageProvider {
    public SBLanguageZhProvider(DataGenerator gen, String locale) {
        super(gen, SlimeBreeder.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.slimebreedertab", "史莱姆饲养主模组");
        add(SBBlocks.JAR_BLOCK.get(), "罐子");
        add(SBBlocks.COW_JAR_BLOCK.get(), "奶牛罐子");
        add(SBBlocks.AQUA_SLIME_BLOCK.get(), "水生史莱姆粘液块");
        add(SBBlocks.FLAME_SLIME_BLOCK.get(), "火焰史莱姆粘液块");
        add(SBBlocks.LUNAR_SLIME_BLOCK.get(), "月光史莱姆粘液块");
        add(SBBlocks.JUNGLE_SLIME_BLOCK.get(), "森林史莱姆粘液块");
        add(SBBlocks.BLACK_SLIME_BLOCK.get(), "黑色史莱姆粘液块");
        add(SBBlocks.PURPLE_SLIME_BLOCK.get(), "紫色史莱姆粘液块");
        add(SBBlocks.CORRUPT_SLIME_BLOCK.get(), "腐化史莱姆粘液块");
        add(SBItems.AQUA_SLIME_SPAWN_EGG.get(), "水生史莱姆生成蛋");
        add(SBItems.LUNAR_SLIME_SPAWN_EGG.get(), "月光史莱姆生成蛋");
        add(SBItems.LUNAR_SLIME_BALL.get(), "月光史莱姆黏液球");
        add(SBItems.LUNAR_SLIME_GEL.get(), "月光史莱姆凝胶");
        add(SBItems.HUNGER_CHECK_STICK.get(), "饥饿值检测棒");
        add(SBItems.LUNAR_SLIME_JAM.get(), "月光史莱姆凝胶酱");
        add(SBItems.LUNAR_SLIME_SANDWICH.get(), "月光史莱姆三明治");
        add(SBItems.AQUA_SLIME_BALL.get(), "水生史莱姆黏液球");
        add(SBItems.AQUA_SLIME_GEL.get(), "水生史莱姆凝胶");
        add(SBItems.AQUA_SLIME_JAM.get(), "水生史莱姆凝胶酱");
        add(SBItems.AQUA_SLIME_SANDWICH.get(), "水生史莱姆三明治");
        add(SBItems.FLAME_SLIME_SPAWN_EGG.get(), "火焰史莱姆生成蛋");
        add(SBItems.FLAME_SLIME_JAM.get(), "火焰史莱姆凝胶酱");
        add(SBItems.FLAME_SLIME_BALL.get(), "火焰史莱姆黏液球");
        add(SBItems.FLAME_SLIME_GEL.get(), "火焰史莱姆凝胶");
        add(SBItems.FLAME_SLIME_SANDWICH.get(), "火焰史莱姆三明治");
        add(SBItems.JUNGLE_SLIME_JAM.get(), "森林史莱姆凝胶酱");
        add(SBItems.JUNGLE_SLIME_BALL.get(), "森林史莱姆黏液球");
        add(SBItems.JUNGLE_SLIME_GEL.get(), "森林史莱姆凝胶");
        add(SBItems.JUNGLE_SLIME_SANDWICH.get(), "森林史莱姆三明治");
        add(SBItems.JUNGLE_SLIME_SPAWN_EGG.get(), "森林史莱姆生成蛋");
        add(SBItems.BLACK_SLIME_BALL.get(), "黑色史莱姆黏液球");
        add(SBItems.BLACK_SLIME_GEL.get(), "黑色史莱姆凝胶");
        add(SBItems.PURPLE_SLIME_BALL.get(), "紫色史莱姆黏液球");
        add(SBItems.PURPLE_SLIME_GEL.get(), "紫色史莱姆黏液球");
        add(SBItems.BALCK_SLIME_SPAWN_EGG.get(), "黑色史莱姆生成蛋");
        add(SBItems.PURPLE_SLIME_SPAWN_EGG.get(), "紫色史莱姆生成蛋");
        add(SBItems.CORRUPT_SLIME_BALL.get(), "腐化史莱姆黏液球");
        add(SBItems.CORRUPT_SLIME_GEL.get(), "腐化史莱姆凝胶");
        add(SBItems.CORRUPT_SLIME_SPAWN_EGG.get(), "腐化史莱姆生成蛋");
        add("slimebreeder.check.info", "饥饿值检测信息:");
        add("slimebreeder.configgui.enableHungerReduction", "启用史莱姆饥饿值锐减");
    }
}
