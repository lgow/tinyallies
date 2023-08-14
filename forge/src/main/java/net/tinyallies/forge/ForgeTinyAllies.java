package net.tinyallies.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tinyallies.TinyAlliesCommon;
import net.tinyallies.items.ModItems;

@Mod(TinyAlliesCommon.MODID)
public class ForgeTinyAllies {
	public ForgeTinyAllies() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		EventBuses.registerModEventBus(TinyAlliesCommon.MODID, modEventBus);
		modEventBus.addListener(this::addItemsToTab);
		TinyAlliesCommon.init();
	}

	public void addItemsToTab(BuildCreativeModeTabContentsEvent event) {
		if (event.getTab().equals(ModItems.TINY_TAB.get())) {
			event.accept(ModItems.BABYFIER.get());

			event.accept(Items.CREEPER_SPAWN_EGG);
			event.accept(Items.ENDERMAN_SPAWN_EGG);
			event.accept(Items.SKELETON_SPAWN_EGG);
			event.accept(Items.SPIDER_SPAWN_EGG);
			event.accept(Items.ZOMBIE_SPAWN_EGG);

			event.accept(Items.GUNPOWDER);
			event.accept(Items.CHORUS_FRUIT);
			event.accept(Items.BONE_MEAL);
			event.accept(Items.BEEF);
		}
	}
}
