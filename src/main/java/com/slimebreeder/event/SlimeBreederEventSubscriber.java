package com.slimebreeder.event;

import com.slimebreeder.block.SBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class SlimeBreederEventSubscriber {

    public static void init() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(SlimeBreederEventSubscriber::onEntityDamage);
    }

    public static void onEntityDamage(LivingDamageEvent event) {
        DamageSource source = event.getSource();
        Entity entity = event.getEntity();
        if (source == DamageSource.ANVIL){
            if (entity instanceof Cow) {
                BlockPos pos = entity.getOnPos();
                Level level = entity.getLevel();
                BlockState blockBelow = level.getBlockState(pos);
                if (blockBelow.getBlock() == SBBlocks.JAR_BLOCK.get()) {
                    level.setBlock(pos, SBBlocks.COW_JAR_BLOCK.get().defaultBlockState(), 1);
                    entity.remove(Entity.RemovalReason.KILLED);
                }
            }
        }
    }
}
