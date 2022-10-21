package com.slimebreeder.api;

public interface HungerAPI {

    void setHunger(float hunger);
    void setMaxHunger(float maxHunger);

    void regenHunger(float hunger);

    float getHunger();
    float getMaxHunger();

    void reduceHunger(float hunger);

}
