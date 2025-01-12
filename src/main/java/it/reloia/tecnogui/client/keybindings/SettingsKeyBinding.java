package it.reloia.tecnogui.client.keybindings;

import it.reloia.tecnogui.client.gui.SettingsScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class SettingsKeyBinding {
    public static final String TOGGLE_HUD = "key.tecnogui.open_settings";
    public static final String TOGGLE_HUD_CATEGORY = "key.categories.tecnogui";

    public static KeyBinding openSettings;
    public static void register() {
        openSettings = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                TOGGLE_HUD,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_ALT,
                TOGGLE_HUD_CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Screen currentScreen = client.currentScreen;
            
            if (openSettings.wasPressed())
                client.setScreen(SettingsScreen.create(currentScreen));
        });
    }
}
