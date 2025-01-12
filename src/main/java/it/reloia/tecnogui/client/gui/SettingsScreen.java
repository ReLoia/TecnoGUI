package it.reloia.tecnogui.client.gui;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SettingsScreen {
    public static Screen create(Screen currentScreen) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("TecnoGUI Settings"))
                .category(createGeneralCategory())
                .build().generateScreen(currentScreen);
    }
    
    private static ConfigCategory createGeneralCategory() {
        return ConfigCategory.createBuilder()
                .name(Text.literal("General"))
                .build();
    }
}
