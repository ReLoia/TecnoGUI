package it.reloia.tecnogui.client;

import it.reloia.tecnogui.client.commands.ToggleHUDCommand;
import it.reloia.tecnogui.dataparsing.TecnoData;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class TecnoGUIClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new HUDOverlay());

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            TecnoData.INSTANCE.inAServer = true;
        });

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            TecnoData.INSTANCE.inAServer = false;
            TecnoData.INSTANCE.isInTecnoRoleplay = false;
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (TecnoData.INSTANCE.inAServer)
                TecnoData.INSTANCE.tick();
        });

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            ToggleHUDCommand.register(dispatcher);
        });
    }
}
