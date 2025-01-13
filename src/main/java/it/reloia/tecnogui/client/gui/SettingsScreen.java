package it.reloia.tecnogui.client.gui;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import it.reloia.tecnogui.client.TecnoGUIClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;

public class SettingsScreen {
    public static Screen create(Screen currentScreen) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("TecnoGUI Settings"))
                .category(createHudCategory())
                .save(TecnoGUIClient.CONFIG::save)
                .build().generateScreen(currentScreen);
    }
    
    private static ConfigCategory createHudCategory() {
        return ConfigCategory.createBuilder()
                .name(Text.literal("HUD"))
                .option(createHighlightExpiredFood())
                .option(createHighlightExpiredFoodColor())
                .group(
                        OptionGroup.createBuilder()
                                .name(Text.literal("Hide user interface elements"))
                                .option(createHideVoteAds())
                                .option(createHideEnteredPlotMsg())
                                .option(createHideScoreboard())
                                .build()
                )
                .build();
    }
    
    private static Option<Boolean> createHideVoteAds() {
        return Option.<Boolean>createBuilder()
                .name(Text.literal("Hide Vote Ads"))
                .description(OptionDescription.of(Text.literal("Hides the vote ads in the HUD.")))
                .binding(
                        false,
                        TecnoGUIClient.CONFIG::isHideVoteAds,
                        TecnoGUIClient.CONFIG::setHideVoteAds
                )
                .controller(BooleanControllerBuilder::create)
                .build();
    }
    
    private static Option<Boolean> createHideEnteredPlotMsg() {
        return Option.<Boolean>createBuilder()
                .name(Text.literal("Hide Joined Plot Message"))
                .description(OptionDescription.of(Text.literal("Hides the annoying \"Sei entrato nel lotto di ...\" message that appears when you enter a plot.")))
                .binding(
                        false,
                        TecnoGUIClient.CONFIG::isHideEnteredPlotMsg,
                        TecnoGUIClient.CONFIG::setHideEnteredPlotMsg
                )
                .controller(BooleanControllerBuilder::create)
                .build();
    }
    
    private static Option<Boolean> createHighlightExpiredFood() {
        return Option.<Boolean>createBuilder()
                .name(Text.literal("Highlight Expired Food"))
                .description(OptionDescription.of(Text.literal("Highlights with a green overlay the expired food and ingredients in your inventory and chests.")))
                .binding(
                        true,
                        TecnoGUIClient.CONFIG::isHighlightExpiredFood,
                        TecnoGUIClient.CONFIG::setHighlightExpiredFood
                )
                .controller(BooleanControllerBuilder::create)
                .build();
    }
    
    private static Option<Boolean> createHideScoreboard() {
        return Option.<Boolean>createBuilder()
                .name(Text.literal("Hide Scoreboard"))
                .description(OptionDescription.of(Text.literal("Hides the scoreboard in the HUD.")))
                .binding(
                        true,
                        TecnoGUIClient.CONFIG::isHideScoreboard,
                        TecnoGUIClient.CONFIG::setHideScoreboard
                )
                .controller(BooleanControllerBuilder::create)
                .build();
    }
    
    private static Option<Color> createHighlightExpiredFoodColor() {
        return Option.<Color>createBuilder()
                .name(Text.literal("Highlight Expired Food Color"))
                .description(OptionDescription.of(Text.literal("The color of the overlay that highlights expired food and ingredients.")))
                .binding(
                        Color.GREEN,
                        TecnoGUIClient.CONFIG::getHighlightExpiredFoodColor,
                        TecnoGUIClient.CONFIG::setHighlightExpiredFoodColor
                )
                .controller(ColorControllerBuilder::create)
                .build();
    }
}
