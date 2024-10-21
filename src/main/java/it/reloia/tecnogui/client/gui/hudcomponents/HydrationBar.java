package it.reloia.tecnogui.client.gui.hudcomponents;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class HydrationBar {
    // TODO: read x and y from settings to make it editable by user
    private static final int width = 182;
    private static int x = 0;
    private static final int y = 10;

    /**
     *
     * @param thirst - 0.0F to 10.0F (0.0F is empty, 10.0F is full)
     */
    public static void draw(float thirst, DrawContext drawContext, Identifier ICONS, MinecraftClient client) {
        x = client.getWindow().getScaledWidth() - 10 - width;

        drawContext.setShaderColor(0.3F, 0.35F, .7F, 1.0F);
        drawContext.drawTexture(ICONS, x, y, 0, 69, width, 5);
        if (thirst > 0.0F) {
            int p = (int) ((thirst / 10) * (float) width);
            drawContext.setShaderColor(0.0F, 0.2F, 1.0F, 1.0F);
            drawContext.drawTexture(ICONS, x, y, 0, 69, p, 5);
        }
        drawContext.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
