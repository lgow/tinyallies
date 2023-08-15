package net.tinyallies.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.tinyallies.entity.Creepy;
import net.tinyallies.entity.EnderBoy;
import net.tinyallies.entity.ModEntities;

import java.util.Map;

public class ModUtil {
	private static final Map<EntityType<? extends PathfinderMob>, EntityType<? extends PathfinderMob>> babyficationList = Map.ofEntries(
			Map.entry(EntityType.CREEPER, ModEntities.CREEPY.get()),
			Map.entry(EntityType.SKELETON, ModEntities.SKELLY.get()),
			Map.entry(EntityType.ENDERMAN, ModEntities.ENDERBOY.get()),
			Map.entry(EntityType.SPIDER, ModEntities.SPIDEY.get()),
			Map.entry(EntityType.ZOMBIE, ModEntities.ZOMBY.get()));

	public static Mob babifyMob(Mob mob) {
		EntityType<?> type = mob.getType();
		Mob baby = null;
		if (babyficationList.containsKey(type)) {
			baby = mob.convertTo(babyficationList.get(mob.getType()), true);
			baby.setHealth(mob.getHealth());
			if (type.equals(EntityType.CREEPER)) {
				((Creepy) baby).setPowered(((Creeper) mob).isPowered());
				((Creepy) baby).setSwellDir(((Creeper) mob).getSwellDir());
			}
			else if (type.equals(EntityType.ENDERMAN)) {
				((EnderBoy) baby).setCarriedBlock(((EnderMan) mob).getCarriedBlock());
				baby.setTarget(mob.getTarget());
			}
			else if (type.equals(ModEntities.SKELLY.get()) && baby.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
				baby.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BOW));
			}
		}
		else {
			mob.setBaby(true);
		}
		return baby;
	}

	public static InteractionResult spawnBabyFromEgg(Mob mob, ItemStack itemStack, EntityType<?> type) {
		ItemStack pickResult = mob.getPickResult();
		Level level = mob.level();
		if (!level.isClientSide && pickResult != null && itemStack.is(pickResult.getItem())) {
			Mob baby = (Mob) type.create(level);
			baby.setPos(mob.position());
			if (type.equals(ModEntities.SKELLY.get())) {
				baby.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BOW));
			}
			level.addFreshEntity(baby);
			return InteractionResult.sidedSuccess(true);
		}
		return InteractionResult.PASS;
	}

	public static void babyfyModel(Iterable<ModelPart> headParts, Iterable<ModelPart> bodyParts, float headY, float headZ, PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
		pPoseStack.pushPose();
		pPoseStack.scale(0.75F, 0.75F, 0.75F);
		pPoseStack.translate(0.0F, headY / 16.0F, headZ / 16.0F);
		headParts.forEach((modelPart) -> {
			modelPart.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
		});
		pPoseStack.popPose();
		pPoseStack.pushPose();
		pPoseStack.scale(0.5F, 0.5F, 0.5F);
		pPoseStack.translate(0.0F, 1.5F, 0.0F);
		bodyParts.forEach((modelPart) -> {
			modelPart.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
		});
		pPoseStack.popPose();
	}
}
