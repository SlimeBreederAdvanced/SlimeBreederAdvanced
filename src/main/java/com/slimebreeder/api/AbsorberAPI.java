package com.slimebreeder.api;

import net.minecraft.world.item.ItemStack;

public interface AbsorberAPI {

    ItemStack getAbsorbedItem();

    void setAbsorbedItem(ItemStack stack);
}
