package com.slimebreeder.event;

import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.SlimeBreederConfig;
import com.slimebreeder.block.SBBlocks;
import com.slimebreeder.entity.BaseSlimeEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class SlimeBreederEventSubscriber {

    public static void init() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(SlimeBreederEventSubscriber::onEntityDamage);
        bus.addListener(SlimeBreederEventSubscriber::onEntityTick);
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

    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        final float REDUCTION_AMOUNT = 0.1f;
        Entity entity = event.getEntity();
        if (entity instanceof BaseSlimeEntity && ((BaseSlimeEntity) entity).getHunger() > 0 && SlimeBreederConfig.CONFIG.enableHungerReduction.get()) {
            if (!entity.getLevel().isClientSide() && entity.isAlive() && --((BaseSlimeEntity) entity).hungerChangeTime <= 0) {
                entity.playSound(SoundEvents.TURTLE_LAY_EGG, 1.0F, 1.0F);
                entity.spawnAtLocation(Items.SLIME_BALL);
                ((BaseSlimeEntity) entity).hungerChangeTime = ((BaseSlimeEntity) entity).getRandom().nextInt(6000) + 6000;
            }
            ((BaseSlimeEntity) entity).reduceHunger(REDUCTION_AMOUNT);
            if (((BaseSlimeEntity) entity).getHunger() == 0) {
                entity.getLevel().addParticle(ParticleTypes.ANGRY_VILLAGER, entity.getX(), entity.getY(), entity.getZ(), 0.0D, 0.0D, 0.0D);
                entity.sendSystemMessage(Component.translatable(SlimeBreeder.MODID + "slime.nohungervalue"));
            }
        }
    }
}
