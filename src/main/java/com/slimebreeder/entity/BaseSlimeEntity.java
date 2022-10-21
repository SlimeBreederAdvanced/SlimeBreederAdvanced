package com.slimebreeder.entity;

import com.slimebreeder.SlimeBreederHooks;
import com.slimebreeder.api.AbsorberAPI;
import com.slimebreeder.api.HungerAPI;
import com.slimebreeder.api.SlimeTypeAPI;
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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

@SuppressWarnings("unchecked")
public abstract class BaseSlimeEntity extends TamableAnimal implements HungerAPI, SlimeTypeAPI, AbsorberAPI {

    public float targetSquish;
    public float squish;
    public float oSquish;
    private boolean wasOnGround;
    public int hungerChangeTime;
    private static final EntityDataAccessor<Float> HUNGER = SynchedEntityData.defineId(BaseSlimeEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> MAX_HUNGER = SynchedEntityData.defineId(BaseSlimeEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> SLIME_SIZE = SynchedEntityData.defineId(BaseSlimeEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<ItemStack> DATA_ABSORBED = SynchedEntityData.defineId(BaseSlimeEntity.class, EntityDataSerializers.ITEM_STACK);

    public BaseSlimeEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new CustomSlimeMoveControl(this);
        this.setSize(2, false);
    }

    @Override
    protected void registerGoals() {

    }

    public void setSize(int pSize, boolean pResetHealth) {
        int i = Mth.clamp(pSize, 1, 127);
        this.entityData.set(SLIME_SIZE, i);
        this.reapplyPosition();
        this.refreshDimensions();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double)(i * i));
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)(0.2F + 0.1F * (float)i));
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double)i);
        if (pResetHealth) {
            this.setHealth(this.getMaxHealth());
        }

        this.xpReward = i;
    }

    public int getSize() {
        return this.entityData.get(SLIME_SIZE);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("wasOnGround", this.wasOnGround);
        pCompound.putFloat("Health", this.getHealth());
        pCompound.putFloat("Speed", this.getSpeed());
        pCompound.putFloat("MaxHunger", this.getMaxHunger());
        pCompound.putFloat("Hunger", this.getHunger());
        pCompound.putInt("HungerChangeTime", this.hungerChangeTime);
        pCompound.putInt("Size", this.getSize());
        pCompound.put("slimebreeder:absorbed_item", getAbsorbedItem().save(new CompoundTag()));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.wasOnGround = pCompound.getBoolean("wasOnGround");
        this.setHealth(pCompound.getFloat("Health"));
        this.setSpeed(pCompound.getFloat("Speed"));
        this.setMaxHunger(pCompound.getFloat("MaxHunger"));
        this.setHunger(pCompound.getFloat("Hunger"));
        if (pCompound.contains("HungerChangeTime")) {
            this.hungerChangeTime = pCompound.getInt("HungerChangeTime");
        }
        if (pCompound.contains("Size")) {
            this.setSize(2, false);
        }
        this.setAbsorbedItem(ItemStack.of(pCompound.getCompound("slimebreeder:absorbed_item")));
    }

    //SlimeBreeder - Custom Mob AI , Hunger API

    @Override
    public void setHunger(float hunger) {
        this.entityData.set(HUNGER, Math.min(getMaxHunger(), hunger));
    }

    @Override
    public void setMaxHunger(float maxHunger) {
        this.entityData.set(HUNGER, Mth.clamp(maxHunger, 0, this.getMaxHunger()));
    }

    @Override
    public void regenHunger(float hunger) {
        this.setHunger(this.getHunger() + hunger);
    }

    @Override
    public float getHunger() {
        return this.entityData.get(HUNGER);
    }

    @Override
    public float getMaxHunger() {
        return (float) this.getAttributeValue(Attributes.MAX_HEALTH);
    }

    @Override
    public void reduceHunger(float hunger) {
        this.setHunger(this.getHunger() - hunger);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SLIME_SIZE, 1);
        this.entityData.define(MAX_HUNGER, 20.0F);
        this.entityData.define(HUNGER, 12.0F);
        this.entityData.define(DATA_ABSORBED, ItemStack.EMPTY);
    }

    //SlimeBreeder - end

    protected ParticleOptions getParticleType() {
        return ParticleTypes.ITEM_SLIME;
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return this.getSize() > 0;
    }

    @Override
    public void tick() {
        this.squish += (this.targetSquish - this.squish) * 0.5F;
        this.oSquish = this.squish;
        super.tick();
        if (this.onGround && !this.wasOnGround) {
            int i = this.getSize();

            if (spawnCustomParticles()) i = 0; // don't spawn particles if it's handled by the implementation itself
            for(int j = 0; j < i * 8; ++j) {
                float f = this.random.nextFloat() * ((float)Math.PI * 2F);
                float f1 = this.random.nextFloat() * 0.5F + 0.5F;
                float f2 = Mth.sin(f) * (float)i * 0.5F * f1;
                float f3 = Mth.cos(f) * (float)i * 0.5F * f1;
                this.level.addParticle(this.getParticleType(), this.getX() + (double)f2, this.getY(), this.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
            }

            this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            this.targetSquish = -0.5F;
        } else if (!this.onGround && this.wasOnGround) {
            this.targetSquish = 1.0F;
        }

        this.wasOnGround = this.onGround;
        this.decreaseSquish();
    }

    /**
     * Called when the slime spawns particles on landing, see onUpdate.
     * Return true to prevent the spawning of the default particles.
     */
    protected boolean spawnCustomParticles() {
        return false;
    }

    protected void decreaseSquish() {
        this.targetSquish *= 0.6F;
    }

    public int getJumpDelay() {
        return this.random.nextInt(20) + 10;
    }

    @Override
    public void refreshDimensions() {
        double d0 = this.getX();
        double d1 = this.getY();
        double d2 = this.getZ();
        super.refreshDimensions();
        this.setPos(d0, d1, d2);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (SLIME_SIZE.equals(pKey)) {
            this.refreshDimensions();
            this.setYRot(this.yHeadRot);
            this.yBodyRot = this.yHeadRot;
            if (this.isInWater() && this.random.nextInt(20) == 0) {
                this.doWaterSplashEffect();
            }
        }

        super.onSyncedDataUpdated(pKey);
    }

    @Override
    public EntityType<? extends BaseSlimeEntity> getType() {
        return (EntityType<? extends BaseSlimeEntity>) super.getType();
    }

    @Override
    public void push(Entity pEntity) {
        super.push(pEntity);
    }

    @Override
    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 0.625F * pSize.height;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SLIME_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SLIME_DEATH;
    }

    protected SoundEvent getSquishSound() {
        return SoundEvents.SLIME_SQUISH;
    }

    @Override
    public float getSoundVolume() {
        return 0.4F * (float)this.getSize();
    }

    @Override
    public int getMaxHeadXRot() {
        return 0;
    }

    /**
     * Returns {@code true} if the slime makes a sound when it jumps (based upon the slime's size)
     */
    public boolean doPlayJumpSound() {
        return this.getSize() > 0;
    }

    @Override
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

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return super.getDimensions(pPose).scale(0.255F * (float)this.getSize());
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return Monster.createMonsterAttributes().
                add(Attributes.MAX_HEALTH, 20.0D).
                add(Attributes.MOVEMENT_SPEED, 0.28D).
                add(Attributes.FOLLOW_RANGE, 48.0D).
                add(Attributes.ARMOR, 8.0D).
                add(Attributes.KNOCKBACK_RESISTANCE, 0.85D);
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        return super.mobInteract(pPlayer, pHand);
    }

    @Override
    public void aiStep() {
        SlimeBreederHooks.handleHunger(this);
        SlimeBreederHooks.handleAbsorber(this);
        super.aiStep();
    }

    @Override
    protected void dropAllDeathLoot(DamageSource pDamageSource) {
      SlimeBreederHooks.handleDropDeath(this);
        super.dropAllDeathLoot(pDamageSource);
    }

    @Override
    public ItemStack getAbsorbedItem() {
        return this.entityData.get(DATA_ABSORBED);
    }

    @Override
    public void setAbsorbedItem(ItemStack stack) {
        this.entityData.set(DATA_ABSORBED, stack);
    }

}