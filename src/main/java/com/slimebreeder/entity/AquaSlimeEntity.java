package com.slimebreeder.entity;

import com.slimebreeder.api.SlimeType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class AquaSlimeEntity extends BaseSlimeEntity {

    public AquaSlimeEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
    }

    @Override
    public SlimeType getSlimeType() {
        return this.setSlimeType();
    }

    @Override
    public SlimeType setSlimeType() {
        return SlimeType.AQUA_SLIME;
    }
}
