package net.tinyallies.common.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.level.Level;
import net.tinyallies.common.entity.BabyfiableEntity;
import net.tinyallies.common.registry.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AbstractGolem.class)
public abstract class MixinAbstractGolem extends PathfinderMob implements BabyfiableEntity {
	@Unique private static EntityDataAccessor<Boolean> DATA_BABY_ID = SynchedEntityData.defineId(MixinAbstractGolem.class,
			EntityDataSerializers.BOOLEAN);
	@Unique private static EntityDataAccessor<Boolean> DATA_BABYFIED_ID = SynchedEntityData.defineId(
			MixinAbstractGolem.class, EntityDataSerializers.BOOLEAN);

	protected MixinAbstractGolem(EntityType<? extends PathfinderMob> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(DATA_BABY_ID, false);
		this.getEntityData().define(DATA_BABYFIED_ID, false);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.putBoolean("IsBaby", this.getEntityData().get(DATA_BABY_ID));
		compoundTag.putBoolean("IsBabyfied", this.getEntityData().get(DATA_BABYFIED_ID));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);
		this.setBaby(compoundTag.getBoolean("IsBaby"));
		this.$setBabyfied(compoundTag.getBoolean("IsBabyfied"));
	}

	public void onSyncedDataUpdated(EntityDataAccessor<?> entityDataAccessor) {
		if (DATA_BABY_ID.equals(entityDataAccessor)) {
			this.refreshDimensions();
		}
		if (DATA_BABYFIED_ID.equals(entityDataAccessor)) {
			this.refreshDimensions();
		}
		super.onSyncedDataUpdated(entityDataAccessor);
	}

	@Override
	public boolean $isBabyfied() {
		return this.getEntityData().get(DATA_BABYFIED_ID);
	}

	@Override
	public void $setBabyfied(boolean b) {
		this.getEntityData().set(DATA_BABYFIED_ID, b);
		if (this.level != null && !this.level.isClientSide) {
			AttributeInstance attributeInstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
			attributeInstance.removeModifier(SPEED_MODIFIER_BABY);
			if (b) {
				attributeInstance.addTransientModifier(SPEED_MODIFIER_BABY);
			}
		}
	}

	@Override
	public boolean isBaby() {
		return this.getEntityData().get(DATA_BABY_ID) || $isBabyfied();
	}

	@Override
	public void setBaby(boolean b) {
		this.getEntityData().set(DATA_BABY_ID, b);
		if (this.level != null && !this.level.isClientSide) {
			AttributeInstance attributeInstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
			attributeInstance.removeModifier(SPEED_MODIFIER_BABY);
			if (b) {
				attributeInstance.addTransientModifier(SPEED_MODIFIER_BABY);
			}
		}
	}

	public int getExperienceReward() {
		if (isBaby()) {
			this.xpReward = (int) ((double) this.xpReward * 2.5);
		}
		return super.getExperienceReward();
	}

	@Override
	protected void customServerAiStep() {
		this.$setBabyfied(this.hasEffect(ModEffects.BABYFICATION.get()));
		super.customServerAiStep();
	}

	@Override
	public double getMyRidingOffset() {
		return this.isBaby() ? 0.0 : -0.45;
	}


}