package com.slimebreeder.item;

import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.SlimeBreederTab;
import com.slimebreeder.entity.SBEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

public class SBItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SlimeBreeder.MODID);

    public static final RegistryObject<Item> LUNAR_SLIME_SPAWN_EGG = registerSpawnEgg(SBEntityTypes.LUNAR_SLIME_ENEITY, 16167425, 16775294);
    public static final RegistryObject<Item> AQUA_SLIME_SPAWN_EGG = registerSpawnEgg(SBEntityTypes.AQUA_SLIME_ENEITY, 56063, 44543);
    public static final RegistryObject<Item> JUNGLE_SLIME_SPAWN_EGG = registerSpawnEgg(SBEntityTypes.JUNGLE_SLIME_ENEITY, 3232308, 9945732);
    public static final RegistryObject<Item> FLAME_SLIME_SPAWN_EGG = registerSpawnEgg(SBEntityTypes.FLAME_SLIME_ENEITY, 0xff0000, 0x00ff00);
    public static final RegistryObject<Item> BALCK_SLIME_SPAWN_EGG = registerSpawnEgg(SBEntityTypes.BLACK_SLIME_ENEITY, 1447446, 0);
    public static final RegistryObject<Item> PURPLE_SLIME_SPAWN_EGG = registerSpawnEgg(SBEntityTypes.PURPLE_SLIME_ENEITY, 16499171, 10890612);
    public static final RegistryObject<Item> CORRUPT_SLIME_SPAWN_EGG = registerSpawnEgg(SBEntityTypes.CORRUPT_SLIME_ENEITY, 9725844, 5060690);
    public static final RegistryObject<Item> HUNGER_CHECK_STICK = register("hunger_check_stick", HungerCheckStick::new);

    //Slime Balls
    public static final RegistryObject<Item> LUNAR_SLIME_BALL = register("lunar_slime_ball");
    public static final RegistryObject<Item> AQUA_SLIME_BALL = register("aqua_slime_ball");
    public static final RegistryObject<Item> FLAME_SLIME_BALL = register("flame_slime_ball");
    public static final RegistryObject<Item> JUNGLE_SLIME_BALL = register("jungle_slime_ball");
    public static final RegistryObject<Item> BLACK_SLIME_BALL = register("black_slime_ball");
    public static final RegistryObject<Item> PURPLE_SLIME_BALL = register("purple_slime_ball");
    public static final RegistryObject<Item> CORRUPT_SLIME_BALL = register("corrupt_slime_ball");

    //Slime Gels
    public static final RegistryObject<Item> LUNAR_SLIME_GEL = register("lunar_slime_gel");
    public static final RegistryObject<Item> AQUA_SLIME_GEL = register("aqua_slime_gel");
    public static final RegistryObject<Item> FLAME_SLIME_GEL = register("flame_slime_gel");
    public static final RegistryObject<Item> JUNGLE_SLIME_GEL = register("jungle_slime_gel");
    public static final RegistryObject<Item> BLACK_SLIME_GEL = register("black_slime_gel");
    public static final RegistryObject<Item> PURPLE_SLIME_GEL = register("purple_slime_gel");
    public static final RegistryObject<Item> CORRUPT_SLIME_GEL = register("corrupt_slime_gel");

    //Food
    public static final RegistryObject<Item> LUNAR_SLIME_JAM = registerJam("lunar_slime_jam");
    public static final RegistryObject<Item> AQUA_SLIME_JAM = registerJam("aqua_slime_jam");
    public static final RegistryObject<Item> FLAME_SLIME_JAM = registerJam("flame_slime_jam");
    public static final RegistryObject<Item> JUNGLE_SLIME_JAM = registerJam("jungle_slime_jam");
    public static final RegistryObject<Item> AQUA_SLIME_SANDWICH = registerSandwich("aqua_slime_sandwich");
    public static final RegistryObject<Item> LUNAR_SLIME_SANDWICH = registerSandwich("lunar_slime_sandwich");
    public static final RegistryObject<Item> FLAME_SLIME_SANDWICH = registerSandwich("flame_slime_sandwich");
    public static final RegistryObject<Item> JUNGLE_SLIME_SANDWICH = registerSandwich("jungle_slime_sandwich");

    private static <T extends EntityType<? extends Mob>> RegistryObject<Item> registerSpawnEgg(RegistryObject<T> entity, int color1, int color2) {
        return register("spawn_egg/" + entity.getId().getPath(), () -> new ForgeSpawnEggItem(entity, color1, color2, new Item.Properties().tab(SlimeBreederTab.TAB)));
    }

    private static RegistryObject<Item> register(String name) {
        return register(name, () -> new Item(new Item.Properties().tab(SlimeBreederTab.TAB)));
    }

    private static RegistryObject<Item> registerJam(String name) {
        return register(name, () -> new Item(new Item.Properties().tab(SlimeBreederTab.TAB).food(Foods.BEETROOT_SOUP)));
    }

    private static RegistryObject<Item> registerSandwich(String name) {
        return register(name, () -> new Item(new Item.Properties().tab(SlimeBreederTab.TAB).food(Foods.COOKED_BEEF)));
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item)
    {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }

}
