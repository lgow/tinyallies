package net.tinyallies.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.tinyallies.client.TinyAlliesClientCommon;

public class FabricTinyAlliesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TinyAlliesClientCommon.preClientInit();
    }
}