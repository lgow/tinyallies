package net.tinyallies;

public class CommonTinyAllies {
    public static final String MOD_ID = "tinyallies";
    // We can use this if we don't want to use DeferredRegister
//    public static final Supplier<Registries> REGISTRIES = Suppliers.memoize(() -> Registries.get(MOD_ID));
//    // Registering a new creative tab
//    public static final CreativeModeTab EXAMPLE_TAB = CreativeTabRegistry.create(new ResourceLocation(MOD_ID, "example_tab"), () ->
//            new ItemStack(CommonTinyAllies.EXAMPLE_ITEM.get()));
//
//    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.);
//    public static final RegistrySupplier<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () ->
//            new Item(new Item.Properties().tab(CommonTinyAllies.EXAMPLE_TAB)));

    public static void init() {
//        ITEMS.register();
        
        System.out.println(ExampleExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}