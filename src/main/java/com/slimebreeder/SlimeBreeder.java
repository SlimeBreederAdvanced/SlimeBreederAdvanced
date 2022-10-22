package com.slimebreeder;

import com.mojang.logging.LogUtils;
import com.slimebreeder.block.SBBlocks;
import com.slimebreeder.event.SlimeBreederEventSubscriber;
import com.slimebreeder.entity.SBEntityTypes;
import com.slimebreeder.item.SBItems;
import com.slimebreeder.util.Configuration;
import com.slimebreeder.util.SlimeBreederConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SlimeBreeder.MODID)
public class SlimeBreeder {

    public static final String MODID = "slimebreeder";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SlimeBreeder() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        SBEntityTypes.ENTITIES.register(modEventBus);
        SBBlocks.BLOCKS.register(modEventBus);
        SBItems.ITEMS.register(modEventBus);
        SlimeBreederEventSubscriber.init();

        new Configuration(SlimeBreederConfig.class, MODID);
    }

}
