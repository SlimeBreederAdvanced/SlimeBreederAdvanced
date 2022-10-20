package com.slimebreeder.registry;

import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.entity.LunarSlimeEntity;
import com.slimebreeder.entity.renderer.LunarSlimeRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SlimeBreeder.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventSubscriber {

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(SBEntityTypes.LUNAR_SLIME_ENEITY.get(), LunarSlimeEntity.prepareAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SBEntityTypes.LUNAR_SLIME_ENEITY.get(), LunarSlimeRenderer::new);
    }
}
