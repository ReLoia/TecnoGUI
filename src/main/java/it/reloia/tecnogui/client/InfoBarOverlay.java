package it.reloia.tecnogui.client;

import com.mojang.blaze3d.systems.RenderSystem;
import it.reloia.tecnogui.dataparsing.TecnoData;
import it.reloia.tecnogui.dataparsing.Utils;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.util.List;

public class InfoBarOverlay implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null)
            return;
        if (!TecnoData.INSTANCE.isInTecno)
            return;

        int x = 0;
        int height = 15;
        int y = client.getWindow().getScaledHeight() - 15;
        int width = client.getWindow().getScaledWidth();

        drawContext.fill(x, y, width, y + height, 0x80000000);

        List<String> lines = TecnoData.INSTANCE.getSidebarLines();

        TextRenderer textRenderer = client.textRenderer;
        drawContext.drawText(textRenderer, "Hello, World!", x + 2, y + 2, 0xFFFFFF, false);
    }
}
