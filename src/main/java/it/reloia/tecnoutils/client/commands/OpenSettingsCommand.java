package it.reloia.tecnoutils.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import it.reloia.tecnoutils.client.gui.SettingsScreen;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.gui.screen.Screen;

public class OpenSettingsCommand {
    private static boolean openNextTick = false;
    
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("tecnoutils")
                .then(ClientCommandManager.literal("settings")
                        .executes(context -> {
                            openNextTick = true;
                            return 1;
                        })
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            if (openNextTick && minecraft.currentScreen == null) {
                Screen settingsScreen = SettingsScreen.create(null);
                minecraft.setScreen(settingsScreen);
                openNextTick = false;
            }
        });
    }
}
