package net.tinyallies;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.tinyallies.items.ModItems;
import net.tinyallies.registry.ModRegistries;
import net.tinyallies.util.TinyAlliesResLoc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TinyAlliesCommon {
	public static final String MODID = "tinyallies";
	public static final RegistrarManager REGISTRIES = RegistrarManager.get(MODID);
	public static final Logger LOGGER = LogManager.getLogger(MODID);


	public static void init() {
		ModRegistries.register();
	}

	public static void commonSetup() {
	}
}