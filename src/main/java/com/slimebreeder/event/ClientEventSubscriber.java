package com.slimebreeder.event;

import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.entity.AquaSlimeEntity;
import com.slimebreeder.entity.LunarSlimeEntity;
import com.slimebreeder.entity.renderer.AquaSlimeRenderer;
import com.slimebreeder.entity.renderer.LunarSlimeRenderer;
import com.slimebreeder.entity.SBEntityTypes;
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
        event.put(SBEntityTypes.AQUA_SLIME_ENEITY.get(), AquaSlimeEntity.prepareAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SBEntityTypes.LUNAR_SLIME_ENEITY.get(), LunarSlimeRenderer::new);
        event.registerEntityRenderer(SBEntityTypes.AQUA_SLIME_ENEITY.get(), AquaSlimeRenderer::new);
    }

}
