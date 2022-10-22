package com.slimebreeder.event;

import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.block.SBBlocks;
import com.slimebreeder.entity.renderer.FlameSlimeRenderer;
import com.slimebreeder.entity.renderer.JungleSlimeRenderer;
import com.slimebreeder.entity.slime.AquaSlimeEntity;
import com.slimebreeder.entity.slime.FlameSlimeEntity;
import com.slimebreeder.entity.slime.JungleSlimeEntity;
import com.slimebreeder.entity.slime.LunarSlimeEntity;
import com.slimebreeder.entity.renderer.AquaSlimeRenderer;
import com.slimebreeder.entity.renderer.LunarSlimeRenderer;
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
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SBEntityTypes.LUNAR_SLIME_ENEITY.get(), LunarSlimeRenderer::new);
        event.registerEntityRenderer(SBEntityTypes.AQUA_SLIME_ENEITY.get(), AquaSlimeRenderer::new);
        event.registerEntityRenderer(SBEntityTypes.JUNGLE_SLIME_ENEITY.get(), JungleSlimeRenderer::new);
        event.registerEntityRenderer(SBEntityTypes.FLAME_SLIME_ENEITY.get(), FlameSlimeRenderer::new);
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
