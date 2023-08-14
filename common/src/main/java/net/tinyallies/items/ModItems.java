package net.tinyallies.items;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.tinyallies.registry.ModRegistries;
import net.tinyallies.util.TinyAlliesResLoc;

import java.util.function.Supplier;

public class ModItems {
	public static final RegistrySupplier<CreativeModeTab> TINY_TAB = ModRegistries.TABS.register(
			"tiny_tab", // Tab ID
			() -> CreativeTabRegistry.create(
					Component.translatable("category.tiny_tab"), // Tab Name
					() -> new ItemStack(ModItems.BABYFIER.get()) // Icon
			)
	);
	public static final Registrar<Item> ITEM_REGISTRAR = ModRegistries.MOD_ITEMS.getRegistrar();
	public static final RegistrySupplier<Item> BABYFIER = registerItem("babyfier",
			() -> new BabyfierItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).arch$tab(TINY_TAB)));

	private static <T extends Item> RegistrySupplier<T> registerItem(String path, Supplier<T> item) {
		final ResourceLocation id = new TinyAlliesResLoc(path);
		return ITEM_REGISTRAR.register(id, item);
	}

	public static void register() {

	}
}
