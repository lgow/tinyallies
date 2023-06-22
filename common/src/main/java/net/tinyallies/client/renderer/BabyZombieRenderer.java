package net.tinyallies.client.renderer;

import net.tinyallies.client.layer.BabyArmorLayer;
import net.tinyallies.client.layer.BabyHeldItemLayer;
import net.tinyallies.client.model.BabyZombieModel;
import net.tinyallies.entity.BabyZombie;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.tinyallies.util.TinyAlliesResLoc;

//@OnlyIn(Dist.CLIENT)
public class BabyZombieRenderer extends AbstractZombieRenderer<BabyZombie, ZombieModel<BabyZombie>> {
	public BabyZombieRenderer(EntityRendererProvider.Context p_174456_) {
		this(p_174456_, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR);
	}

	public BabyZombieRenderer(EntityRendererProvider.Context pContext, ModelLayerLocation pZombieLayer, ModelLayerLocation pInnerArmor, ModelLayerLocation pOuterArmor) {
		super(pContext, new BabyZombieModel(pContext.bakeLayer(pZombieLayer)),
				new ZombieModel<>(pContext.bakeLayer(pInnerArmor)), new ZombieModel<>(pContext.bakeLayer(pOuterArmor)));
		this.layers.clear();
		this.addLayer(new BabyArmorLayer<>(this, new BabyZombieModel(pContext.bakeLayer(pInnerArmor)),
				new BabyZombieModel(pContext.bakeLayer(pOuterArmor)), pContext.getModelManager(), 0.3F));
		this.addLayer(new CustomHeadLayer<>(this, pContext.getModelSet(), 1.0F, 1.0F, 1.0F,
				pContext.getItemInHandRenderer()));
		//		this.addLayer(new ElytraLayer<>(this, pContext.getModelSet()));
		this.addLayer(new BabyHeldItemLayer<>(this, pContext.getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(Zombie pEntity) {
		return pEntity.getName().getString().equals("PhoenixSC") ? new TinyAlliesResLoc("textures/entity/zombie/phoenixsc.png") : super.getTextureLocation(pEntity);
	}
}