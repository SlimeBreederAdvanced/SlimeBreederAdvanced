package com.slimebreeder.datagen;

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
                new SBItemModelProvider(event.getGenerator(), event.getExistingFileHelper(), SBItems.ITEMS));
    }
}
