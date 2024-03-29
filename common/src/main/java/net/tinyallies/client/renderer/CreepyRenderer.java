package net.tinyallies.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.tinyallies.client.layer.CreepyPowerLayer;
import net.tinyallies.client.model.CreepyModel;
import net.tinyallies.entity.Creepy;

public class CreepyRenderer extends MobRenderer<Creepy, CreepyModel> {
	private static final ResourceLocation CREEPER_LOCATION = new ResourceLocation(
			"textures/entity/creeper/creeper.png");

	public CreepyRenderer(EntityRendererProvider.Context context) {
		super(context, new CreepyModel(context.bakeLayer(ModelLayers.CREEPER)), 0.25F);
		this.addLayer(new CreepyPowerLayer(this, context.getModelSet()));
	}

	protected void scale(Creepy pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
		float f = pLivingEntity.getSwelling(pPartialTickTime);
		float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
		f = Mth.clamp(f, 0.0F, 1.0F);
		f *= f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		pMatrixStack.scale(f2, f3, f2);
	}

	protected float getWhiteOverlayProgress(Creepy pLivingEntity, float pPartialTicks) {
		float f = pLivingEntity.getSwelling(pPartialTicks);
		return (int) (f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
	}

	@Override
	public Vec3 getRenderOffset(Creepy entity, float f) {
		return entity.isInSittingPose() ? new Vec3(0, -0.126, 0) : super.getRenderOffset(entity, f);
	}

	public ResourceLocation getTextureLocation(Creepy pEntity) {
		return CREEPER_LOCATION;
	}
}