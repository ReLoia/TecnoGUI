package it.reloia.tecnogui.client.gui;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import it.reloia.tecnogui.client.TecnoGUIClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

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
                .option(createHideVoteAds())
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
}