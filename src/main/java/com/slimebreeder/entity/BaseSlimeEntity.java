package com.slimebreeder.entity;

import com.slimebreeder.entity.control.CustomSlimeMoveControl;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public abstract class BaseSlimeEntity extends Animal implements Enemy {

    public float targetSquish;
    public float squish;
    public float oSquish;
    private boolean wasOnGround;

    private static final EntityDataAccessor<Integer> HUNGER = SynchedEntityData.defineId(BaseSlimeEntity.class, EntityDataSerializers.INT);

    public BaseSlimeEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new CustomSlimeMoveControl(this);
    }

    protected void registerGoals() {

    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("wasOnGround", this.wasOnGround);
        pCompound.putFloat("Health", this.getHealth());
        pCompound.putFloat("Speed", this.getSpeed());
        pCompound.putInt("Hunger", this.getHunger());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.wasOnGround = pCompound.getBoolean("wasOnGround");
        this.setHealth(pCompound.getFloat("Health"));
        this.setSpeed(pCompound.getFloat("Speed"));
        this.setHunger(pCompound.getInt("Hunger"));
    }

    //SlimeBreeder - Custom Mob AI

    public void setHunger(int newHunger) {
        this.entityData.set(HUNGER, newHunger);
    }

    public int getHunger() {
        return (Integer) this.entityData.get(HUNGER);
    }

    //SlimeBreeder - end

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HUNGER, 10);
    }

    protected ParticleOptions getParticleType() {
        return ParticleTypes.ITEM_SLIME;
    }

    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    public void tick() {
        this.squish += (this.targetSquish - this.squish) * 0.5F;
        this.oSquish = this.squish;
        super.tick();
        if (this.onGround && !this.wasOnGround) {

            for (int j = 0; j < 8; ++j) {
                float f = this.random.nextFloat() * ((float) Math.PI * 2F);
                float f1 = this.random.nextFloat() * 0.5F + 0.5F;
                float f2 = Mth.sin(f) * 0.5F * f1;
                float f3 = Mth.cos(f) * 0.5F * f1;
                this.level.addParticle(this.getParticleType(), this.getX() + (double) f2, this.getY(), this.getZ() + (double) f3, 0.0D, 0.0D, 0.0D);
            }

            this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            this.targetSquish = -0.5F;
        } else if (!this.onGround && this.wasOnGround) {
            this.targetSquish = 1.0F;
        }

        this.wasOnGround = this.onGround;
        this.decreaseSquish();
    }

    protected void decreaseSquish() {
        this.targetSquish *= 0.6F;
    }

    public int getJumpDelay() {
        return this.random.nextInt(20) + 10;
    }

    public void refreshDimensions() {
        double d0 = this.getX();
        double d1 = this.getY();
        double d2 = this.getZ();
        super.refreshDimensions();
        this.setPos(d0, d1, d2);
    }

    public EntityType<? extends BaseSlimeEntity> getType() {
        return (EntityType<? extends BaseSlimeEntity>) super.getType();
    }

    public void push(Entity pEntity) {
        super.push(pEntity);
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 0.625F * pSize.height;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SLIME_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SLIME_DEATH;
    }

    protected SoundEvent getSquishSound() {
        return SoundEvents.SLIME_SQUISH;
    }

    public float getSoundVolume() {
        return 0.4F;
    }

    public int getMaxHeadXRot() {
        return 0;
    }

    /**
     * Returns {@code true} if the slime makes a sound when it jumps (based upon the slime's size)
     */
    public boolean doPlayJumpSound() {
        return true;
    }

    protected void jumpFromGround() {
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.x, (double) this.getJumpPower(), vec3.z);
        this.hasImpulse = true;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    public float getSoundPitch() {
        float f = 0.8F;
        return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * f;
    }

    public SoundEvent getJumpSound() {
        return SoundEvents.SLIME_JUMP;
    }

    public EntityDimensions getDimensions(Pose pPose) {
        return super.getDimensions(pPose).scale(0.255F);
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 10.0D).
                add(Attributes.MOVEMENT_SPEED, 0.28D).
                add(Attributes.FOLLOW_RANGE, 48.0D).
                add(Attributes.ARMOR, 8.0D).
                add(Attributes.KNOCKBACK_RESISTANCE, 0.85D);
    }

}