package com.slimebreeder.event;

import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.block.SBBlocks;
import com.slimebreeder.entity.renderer.*;
import com.slimebreeder.entity.slime.*;
import com.slimebreeder.entity.SBEntityTypes;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SlimeBreeder.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventSubscriber {

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(SBEntityTypes.LUNAR_SLIME_ENEITY.get(), LunarSlimeEntity.prepareAttributes().build());
        event.put(SBEntityTypes.AQUA_SLIME_ENEITY.get(), AquaSlimeEntity.prepareAttributes().build());
        event.put(SBEntityTypes.JUNGLE_SLIME_ENEITY.get(), JungleSlimeEntity.prepareAttributes().build());
        event.put(SBEntityTypes.FLAME_SLIME_ENEITY.get(), FlameSlimeEntity.prepareAttributes().build());
        event.put(SBEntityTypes.BLACK_SLIME_ENEITY.get(), BlackSlimeEntity.prepareAttributes().build());
        event.put(SBEntityTypes.PURPLE_SLIME_ENEITY.get(), BlackSlimeEntity.prepareAttributes().build());
        event.put(SBEntityTypes.CORRUPT_SLIME_ENEITY.get(), CorruptSlimeEntity.prepareAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SBEntityTypes.LUNAR_SLIME_ENEITY.get(), LunarSlimeRenderer::new);
        event.registerEntityRenderer(SBEntityTypes.AQUA_SLIME_ENEITY.get(), AquaSlimeRenderer::new);
        event.registerEntityRenderer(SBEntityTypes.JUNGLE_SLIME_ENEITY.get(), JungleSlimeRenderer::new);
        event.registerEntityRenderer(SBEntityTypes.FLAME_SLIME_ENEITY.get(), FlameSlimeRenderer::new);
        event.registerEntityRenderer(SBEntityTypes.BLACK_SLIME_ENEITY.get(), BlackSlimeRenderer::new);
        event.registerEntityRenderer(SBEntityTypes.PURPLE_SLIME_ENEITY.get(), PurpleSlimeRenderer::new);
        event.registerEntityRenderer(SBEntityTypes.CORRUPT_SLIME_ENEITY.get(), CorruptSlimeRenderer::new);
    }

    @SubscribeEvent
    @SuppressWarnings("removal")
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(SBBlocks.JAR_BLOCK.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(SBBlocks.COW_JAR_BLOCK.get(), RenderType.translucent());
        });
    }
}
