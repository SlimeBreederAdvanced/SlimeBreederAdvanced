package com.slimebreeder.util;

public class SlimeBreederConfig {

    @Config(config = "general", key = "enableHungerReduction", comment = "Set this to true to enbale Custom slime's hunger reduction.")
    public static boolean enableHungerReduction = true;

    @Config(config = "general", key = "enableSlimeAbsorbing", comment = "Set this to true to enable Slime Absorbing items.")
    public static boolean enableSlimeAbsorbing = false;
}
