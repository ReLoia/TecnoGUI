package it.reloia.tecnoutils.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import it.reloia.tecnoutils.dataparsing.TecnoData;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class ToggleHUDCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("tecnoutils")
                .then(ClientCommandManager.literal("gui")
                        .executes(context -> {
                            TecnoData.INSTANCE.isHUDEnabled = !TecnoData.INSTANCE.isHUDEnabled;
                            return 1;
                        })
                )
        );
    }
}
