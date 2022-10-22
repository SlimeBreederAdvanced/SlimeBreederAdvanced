package com.slimebreeder;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class SlimeBreederConfig {

    public final ForgeConfigSpec.BooleanValue enableHungerReduction;
    public final ForgeConfigSpec.BooleanValue enableSlimeAbsorbing;

    static final ForgeConfigSpec configSpec;
    public static final SlimeBreederConfig CONFIG;
    static {
        final Pair<SlimeBreederConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(SlimeBreederConfig::new);
        configSpec = specPair.getRight();
        CONFIG = specPair.getLeft();
    }

    SlimeBreederConfig(ForgeConfigSpec.Builder builder) {

        builder.comment("Slimebreeder Mod configuration settings")
                .push("common");

        enableHungerReduction = builder
                .comment("Set this to true to enbale Custom slime's hunger reduction.")
                .translation(SlimeBreeder.MODID + ".configgui.enableHungerReduction")
                .define("enableHungerReduction", true);

        enableSlimeAbsorbing = builder
                .comment("Set this to true to enable Slime Absorbing items")
                .define("enableSlimeAbsorbing", false);
    }
}
