package com.slimebreeder;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SlimeBreederTab extends CreativeModeTab {

    public static final SlimeBreederTab TAB = new SlimeBreederTab();

    public SlimeBreederTab() {
        super(SlimeBreeder.MODID + "tab");
    }

    @Override
    public ItemStack makeIcon() {
        return Items.SLIME_BALL.getDefaultInstance();
    }
}
