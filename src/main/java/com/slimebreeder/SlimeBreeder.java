package com.slimebreeder;

import com.mojang.logging.LogUtils;
import com.slimebreeder.registry.ClientEventHandler;
import com.slimebreeder.registry.SBEntityTypes;
import com.slimebreeder.registry.SBItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SlimeBreeder.MODID)
public class SlimeBreeder {

    public static final String MODID = "slimebreeder";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    public SlimeBreeder() {
        ClientEventHandler.INSTANCE.initClient();
        SBEntityTypes.ENTITY_TYPES.register(modEventBus);
        SBItems.ITEMS.register(modEventBus);
    }

}
