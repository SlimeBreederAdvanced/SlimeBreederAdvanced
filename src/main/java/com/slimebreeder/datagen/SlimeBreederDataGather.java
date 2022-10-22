package com.slimebreeder.datagen;

import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.block.SBBlocks;
import com.slimebreeder.item.SBItems;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SlimeBreederDataGather {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(
                event.includeClient(),
                new SBLanguageProvider(event.getGenerator(), "en_us"));
        event.getGenerator().addProvider(
                event.includeClient(),
                new SBLanguageZhProvider(event.getGenerator(), "zh_cn"));
        event.getGenerator().addProvider(
                event.includeClient(),
                new SBLootTableProvider(event.getGenerator(), SlimeBreeder.MODID));
        event.getGenerator().addProvider(
                event.includeClient(),
                new SBRecipeProvider(event.getGenerator()));
        event.getGenerator().addProvider(
                event.includeClient(),
                new SBBlockStateProvider(event.getGenerator(), event.getExistingFileHelper(), SBBlocks.BLOCKS));
        event.getGenerator().addProvider(
                event.includeClient(),
                new SBItemModelProvider(event.getGenerator(), event.getExistingFileHelper(), SBItems.ITEMS));
    }
}
