package it.reloia.tecnogui.client.keybindings;

import it.reloia.tecnogui.dataparsing.TecnoData;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class GUIKeyBinding {
    public static final String TOGGLE_HUD = "key.tecnogui.toggle_hud";
    public static final String TOGGLE_HUD_CATEGORY = "key.categories.tecnogui";

    public static KeyBinding toggleHUD;
    public static void register() {
        toggleHUD = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                TOGGLE_HUD,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_CONTROL,
                TOGGLE_HUD_CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleHUD.wasPressed())
                TecnoData.INSTANCE.isHUDEnabled = !TecnoData.INSTANCE.isHUDEnabled;
        });
    }
}
