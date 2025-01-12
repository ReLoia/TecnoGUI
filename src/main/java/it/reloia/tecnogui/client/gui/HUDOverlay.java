package it.reloia.tecnogui.client.gui;

import it.reloia.tecnogui.client.gui.hudcomponents.CustomBars;
import it.reloia.tecnogui.client.gui.hudcomponents.InfoBar;
import it.reloia.tecnogui.dataparsing.TecnoData;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class HUDOverlay implements HudRenderCallback {
    private static final Identifier ICONS = new Identifier("minecraft", "textures/gui/icons.png");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client == null)
            return;
        if (!TecnoData.INSTANCE.isInTecnoRoleplay || !TecnoData.INSTANCE.isHUDEnabled)
            return;

        InfoBar.draw(drawContext, client);

        CustomBars.renderBars(drawContext, ICONS);
    }
}
