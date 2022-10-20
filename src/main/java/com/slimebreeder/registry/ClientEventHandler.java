package com.slimebreeder.registry;

import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.entity.BaseSlimeEntity;
import com.slimebreeder.entity.LunarSlimeEntity;
import com.slimebreeder.entity.renderer.LunarSlimeRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

public class ClientEventHandler {

    public static ClientEventHandler INSTANCE = new ClientEventHandler();

    public void initClient() {
        SlimeBreeder.modEventBus.addListener(this::onAttributeCreate);
        SlimeBreeder.modEventBus.addListener(this::onRegisterRenderer);
    }

    public void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(SBEntityTypes.BASE_SLIME_ENEITY.get(), BaseSlimeEntity.createMobAttributes().build()); //Just use for attribute creates
        event.put(SBEntityTypes.LUNAR_SLIME_ENEITY.get(), LunarSlimeEntity.createMobAttributes().build());
    }

    public void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SBEntityTypes.LUNAR_SLIME_ENEITY.get(), LunarSlimeRenderer::new);
    }
}
