package it.reloia.tecnogui.client;

import it.reloia.tecnogui.dataparsing.TecnoData;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class TecnoGUIClient implements ClientModInitializer {

    TecnoData tecnoData = new TecnoData();

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new InfoBarOverlay());

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            tecnoData.tick();
        });

    }
}
