package com.slimebreeder.entity.slime;

import com.slimebreeder.api.SlimeType;
import com.slimebreeder.entity.goal.CustomSlimeFloatGoal;
import com.slimebreeder.entity.goal.CustomSlimeKeepOnJumpingGoal;
import com.slimebreeder.entity.goal.CustomSlimeRandomDirectionGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class JungleSlimeEntity extends BaseSlimeEntity {

    public JungleSlimeEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new CustomSlimeFloatGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new CustomSlimeRandomDirectionGoal(this));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new CustomSlimeKeepOnJumpingGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public SlimeType getSlimeType() {
        return this.setSlimeType();
    }

    @Override
    public SlimeType setSlimeType() {
        return SlimeType.JUNGLE_SLIME;
    }
}
