package it.reloia.tecnogui.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import it.reloia.tecnogui.dataparsing.TecnoData;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class ToggleHUDCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("tecnogui")
                .executes(context -> {
                    TecnoData.INSTANCE.isHUDEnabled = !TecnoData.INSTANCE.isHUDEnabled;
                    return 1;
                })
        );
    }
}
