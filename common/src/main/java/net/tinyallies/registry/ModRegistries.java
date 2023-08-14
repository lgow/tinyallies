package net.tinyallies.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.tinyallies.TinyAlliesCommon;
import net.tinyallies.entity.ModEntities;
import net.tinyallies.items.ModItems;

public class ModRegistries {
	public static final DeferredRegister<Item> MOD_ITEMS = DeferredRegister.create(TinyAlliesCommon.MODID,
			Registries.ITEM);
	public static final DeferredRegister<CreativeModeTab> TABS =
			DeferredRegister.create(TinyAlliesCommon.MODID, Registries.CREATIVE_MODE_TAB);


	public static void register() {
		MOD_ITEMS.register();
		TABS.register();
		ModEntities.register();
		ModItems.register();
	}
}
