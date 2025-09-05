package it.reloia.tecnoutils.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import it.reloia.tecnoutils.dataparsing.TecnoData;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;


public class InfoCommand {
    private static String getModVersion() {
        return FabricLoader.getInstance()
                .getModContainer("tecnoutils")
                .map(modContainer -> modContainer.getMetadata().getVersion().getFriendlyString())
                .orElse("?.?.?");
    }
    
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("tecnoutils")
                .executes(context -> {
                    MinecraftClient mc = MinecraftClient.getInstance();

                    assert mc.player != null;

                    MutableText message = Text.literal("\n");
                    
                    message.append(Text.literal("[TecnoUtils] + [TecnoMap]").formatted(Formatting.GREEN));
                    message.append(Text.literal("v" + getModVersion()).formatted(Formatting.RESET));
                    message.append(Text.literal(" | ").formatted(Formatting.RESET));
                    MutableText githubLink = Text.literal("GitHub")
                            .setStyle(Style.EMPTY
                                    .withColor(Formatting.DARK_GRAY)
                                    .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/ReLoia/TecnoMap"))
                                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Visit the GitHub repository")))
                            );
                    message.append(githubLink);
                    message.append(Text.literal(" | ").formatted(Formatting.RESET));
                    MutableText reloiaLink = Text.literal("ReLoia")
                            .setStyle(Style.EMPTY
                                    .withColor(Formatting.DARK_GRAY)
                                    .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://reloia.ddns.net/"))
                                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Visit my website")))
                            );
                    message.append(reloiaLink);
                    message.append(Text.literal("\n").formatted(Formatting.RESET));
                    if (!TecnoData.INSTANCE.isInTecnoRoleplay) {
                        message.append(Text.literal("\nRemember to join the TecnoRoleplay server!\n\n").formatted(Formatting.RED));
                    }
                    message.append(Text.literal("Open the map in your browser ").formatted(Formatting.RESET));
                    MutableText mapWebsiteLink = Text.literal("by clicking here")
                            .setStyle(Style.EMPTY
                                    .withColor(Formatting.DARK_RED)
                                    .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://mappa.tecnocraft.net/"))
                                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Visit TecnoCraft map")))
                            );
                    message.append(mapWebsiteLink);
                    message.append(Text.literal("\n").formatted(Formatting.RESET));
                    message.append(Text.literal("Open the mod server ").formatted(Formatting.RESET));
                    MutableText websiteLink = Text.literal("by clicking here")
                            .setStyle(Style.EMPTY
                                    .withColor(Formatting.DARK_GREEN)
                                    .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://localhost:8998/"))
                                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Visit the TecnoMap website")))
                            );
                    message.append(websiteLink);
                    message.append(Text.literal("\n").formatted(Formatting.RESET));
                    
                    mc.player.sendMessage(message, false);
                    
                    return 1;
                })
        );
    }
}
