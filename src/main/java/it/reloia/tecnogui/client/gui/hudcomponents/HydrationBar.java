package it.reloia.tecnogui.client.gui.hudcomponents;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class HydrationBar {
    /**
     *
     * @param thirst - 0.0F to 10.0F (0.0F is empty, 10.0F is full)
     */
    public static void draw(int x, int y, float thirst, DrawContext drawContext, Identifier ICONS) {
        drawContext.setShaderColor(0.25F, 0.35F, .75F, 1.0F);
        drawContext.drawTexture(ICONS, x, y, 0, 69, 182, 5);
        if (thirst > 0.0F) {
            int p = (int) ((thirst / 10) * 182.0F);
            drawContext.setShaderColor(0.1F, 0.3F, 1.0F, 1.0F);
            drawContext.drawTexture(ICONS, x, y-10, 0, 69, p, 5);
        }
        drawContext.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
