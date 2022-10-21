package com.slimebreeder.entity;

import com.slimebreeder.SlimeBreeder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;

public class SBEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SlimeBreeder.MODID);

    public static RegistryObject<EntityType<LunarSlimeEntity>> LUNAR_SLIME_ENEITY = register("lunar_slime",
                    EntityType.Builder.of(LunarSlimeEntity::new, MobCategory.CREATURE)
                            .sized(0.5F, 0.7F)
                            .clientTrackingRange(10));

    public static <E extends Entity> RegistryObject<EntityType<E>> register(String name, EntityType.Builder<E> builder) {
        return register(name, builder, true);
    }

    public static <E extends Entity> RegistryObject<EntityType<E>> register(String name, EntityType.Builder<E> builder, boolean serialize) {
        final String id = name.toLowerCase(Locale.ROOT);
        return ENTITIES.register(id, () -> {
            if (!serialize) builder.noSave();
            return builder.build(SlimeBreeder.MODID + ":" + id);
        });
    }
}
