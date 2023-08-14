package net.tinyallies.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.tinyallies.TinyAlliesCommon;
import net.tinyallies.items.ModItems;

public class FabricTinyAllies implements ModInitializer {
	@Override
	public void onInitialize() {
		TinyAlliesCommon.init();
		ItemGroupEvents.modifyEntriesEvent(ModItems.TINY_TAB.getKey()).register(this::addItemsToTab);
	}

	private void addItemsToTab(FabricItemGroupEntries fabricItemGroupEntries) {
		fabricItemGroupEntries.accept(ModItems.BABYFIER.get());

		fabricItemGroupEntries.accept(Items.CREEPER_SPAWN_EGG);
		fabricItemGroupEntries.accept(Items.ENDERMAN_SPAWN_EGG);
		fabricItemGroupEntries.accept(Items.SKELETON_SPAWN_EGG);
		fabricItemGroupEntries.accept(Items.SPIDER_SPAWN_EGG);
		fabricItemGroupEntries.accept(Items.ZOMBIE_SPAWN_EGG);

		fabricItemGroupEntries.accept(Items.GUNPOWDER);
		fabricItemGroupEntries.accept(Items.CHORUS_FRUIT);
		fabricItemGroupEntries.accept(Items.BONE_MEAL);
		fabricItemGroupEntries.accept(Items.BEEF);
	}
}
