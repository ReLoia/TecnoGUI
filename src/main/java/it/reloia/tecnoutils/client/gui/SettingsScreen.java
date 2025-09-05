package it.reloia.tecnoutils.client.gui;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import it.reloia.tecnoutils.client.TecnoUtilsClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;

public class SettingsScreen {
    public static Screen create(Screen currentScreen) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("TecnoUtils Settings"))
                .category(createHudCategory())
                .save(TecnoUtilsClient.CONFIG::save)
                .build().generateScreen(currentScreen);
    }
    
    private static ConfigCategory createHudCategory() {
        return ConfigCategory.createBuilder()
                .name(Text.literal("HUD"))
                .option(createShouldReplaceBars())
                .option(createHighlightExpiredFood())
                .option(createHighlightExpiredFoodColor())
                .option(createVehicleSpeedAsEXPLevel())
                .group(
                        OptionGroup.createBuilder()
                                .name(Text.literal("Hide user interface elements"))
                                .option(createHideVoteAds())
                                .option(createHideEnteredPlotMsg())
                                .option(createHideScoreboard())
                                .build()
                )
                .option(createDebugToggle())
                .build();
    }

    private static Option<Boolean> createDebugToggle() {
        return Option.<Boolean>createBuilder()
                .name(Text.literal("Debug Mode"))
                .description(OptionDescription.of(Text.literal("Enables debug mode.")))
                .binding(
                        false,
                        TecnoUtilsClient.CONFIG::isDebug,
                        TecnoUtilsClient.CONFIG::setDebug
                )
                .controller(BooleanControllerBuilder::create)
                .build();
    }

    private static Option<Boolean> createHideVoteAds() {
        return Option.<Boolean>createBuilder()
                .name(Text.literal("Hide Vote Ads"))
                .description(OptionDescription.of(Text.literal("Hides the vote ads in the HUD.")))
                .binding(
                        false,
                        TecnoUtilsClient.CONFIG::isHideVoteAds,
                        TecnoUtilsClient.CONFIG::setHideVoteAds
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
                        TecnoUtilsClient.CONFIG::isHideEnteredPlotMsg,
                        TecnoUtilsClient.CONFIG::setHideEnteredPlotMsg
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
                        TecnoUtilsClient.CONFIG::isHighlightExpiredFood,
                        TecnoUtilsClient.CONFIG::setHighlightExpiredFood
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
                        TecnoUtilsClient.CONFIG::isHideScoreboard,
                        TecnoUtilsClient.CONFIG::setHideScoreboard
                )
                .controller(BooleanControllerBuilder::create)
                .build();
    }
    
    private static Option<Color> createHighlightExpiredFoodColor() {
        return Option.<Color>createBuilder()
                .name(Text.literal("Highlight Expired Food Color"))
                .description(OptionDescription.of(Text.literal("The color of the overlay that highlights expired food and ingredients.")))
                .binding(
                        new Color(0x8090EE90),
                        TecnoUtilsClient.CONFIG::getHighlightExpiredFoodColor,
                        TecnoUtilsClient.CONFIG::setHighlightExpiredFoodColor
                )
                .controller(option -> {
                    ColorControllerBuilder builder = ColorControllerBuilder.create(option);
                    builder.allowAlpha(true);
                    return builder;
                })
                .build();
    }
    
    private static Option<Boolean> createVehicleSpeedAsEXPLevel() {
        return Option.<Boolean>createBuilder()
                .name(Text.literal("Vehicle Speed as EXP Level"))
                .description(OptionDescription.of(Text.literal("Shows the vehicle speed as experience level.")))
                .binding(
                        false,
                        TecnoUtilsClient.CONFIG::isVehicleSpeedAsEXPLevel,
                        TecnoUtilsClient.CONFIG::setVehicleSpeedAsEXPLevel
                )
                .controller(BooleanControllerBuilder::create)
                .build();
    }
    
    private static Option<Boolean> createShouldReplaceBars() {
        return Option.<Boolean>createBuilder()
                .name(Text.literal("Replace Bars"))
                .description(OptionDescription.of(Text.literal("Replace the default health and hunger bars with custom ones.")))
                .binding(
                        false,
                        TecnoUtilsClient.CONFIG::isReplaceBars,
                        TecnoUtilsClient.CONFIG::setReplaceBars
                )
                .controller(BooleanControllerBuilder::create)
                .build();
    }
}
