package net.tinyallies.common.mixin;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.level.Level;
import net.tinyallies.common.entity.BabyfiableEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Piglin.class)
public abstract class MixinPiglin extends Monster implements BabyfiableEntity {
	@Shadow @Final private static EntityDataAccessor<Boolean> DATA_BABY_ID;

	protected MixinPiglin(EntityType<? extends Monster> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public boolean isBaby() {
		return this.getEntityData().get(DATA_BABY_ID) || $isBabyfied();
	}
}