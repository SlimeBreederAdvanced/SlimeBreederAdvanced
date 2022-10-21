package com.slimebreeder.event;

import com.slimebreeder.entity.LunarSlimeEntity;
import com.slimebreeder.entity.renderer.LunarSlimeRenderer;
import com.slimebreeder.entity.SBEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;

@OnlyIn(Dist.CLIENT)
public class ClientEventSubscriber {

    public static void init() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(ClientEventSubscriber::onAttributeCreate);
        bus.addListener(ClientEventSubscriber::onRegisterRenderer);
    }

    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(SBEntityTypes.LUNAR_SLIME_ENEITY.get(), LunarSlimeEntity.prepareAttributes().build());
    }

    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SBEntityTypes.LUNAR_SLIME_ENEITY.get(), LunarSlimeRenderer::new);
    }
}
