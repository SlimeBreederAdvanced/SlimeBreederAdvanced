package com.slimebreeder.registry;

import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.entity.BaseSlimeEntity;
import com.slimebreeder.entity.LunarSlimeEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SBEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SlimeBreeder.MODID);

    //Just use for attribute creates
    public static RegistryObject<EntityType<BaseSlimeEntity>> BASE_SLIME_ENEITY = ENTITY_TYPES.register("base_slime", () -> EntityType.Builder.of(BaseSlimeEntity::new, MobCategory.CREATURE)
            .clientTrackingRange(8)
            .setShouldReceiveVelocityUpdates(false)
            .sized( 0.51f, 0.51f)
            .build("base_slime"));

    public static RegistryObject<EntityType<LunarSlimeEntity>> LUNAR_SLIME_ENEITY = ENTITY_TYPES.register("lunar_slime", () -> EntityType.Builder.of(LunarSlimeEntity::new, MobCategory.CREATURE)
            .clientTrackingRange(8)
            .setShouldReceiveVelocityUpdates(false)
            .sized( 0.51f, 0.51f)
            .build("lunar_slime"));

}
