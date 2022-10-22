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


    public static final RegistryObject<Item> LUNAR_SLIME_SPAWN_EGG = registerSpawnEgg(SBEntityTypes.LUNAR_SLIME_ENEITY, 0xff0000, 0x00ff00);
    public static final RegistryObject<Item> AQUA_SLIME_SPAWN_EGG = registerSpawnEgg(SBEntityTypes.AQUA_SLIME_ENEITY, 0xff0000, 0x00ff00);
    public static final RegistryObject<Item> HUNGER_CHECK_STICK = register("hunger_check_stick", HungerCheckStick::new);

    //Slime Balls
    public static final RegistryObject<Item> LUNAR_SLIME_BALL = register("lunar_slime_ball");
    public static final RegistryObject<Item> AQUA_SLIME_BALL = register("aqua_slime_ball");

    //Slime Gels
    public static final RegistryObject<Item> LUNAR_SLIME_GEL = register("lunar_slime_gel");
    public static final RegistryObject<Item> AQUA_SLIME_GEL = register("aqua_slime_gel");

    //Food
    public static final RegistryObject<Item> LUNAR_SLIME_JAM = registerJam("lunar_slime_jam");
    public static final RegistryObject<Item> AQUA_SLIME_JAM = registerJam("aqua_slime_jam");
    public static final RegistryObject<Item> AQUA_SLIME_SANDWICH = registerSandwich("aqua_slime_sandwich");
    public static final RegistryObject<Item> LUNAR_SLIME_SANDWICH = registerSandwich("lunar_slime_sandwich");

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
