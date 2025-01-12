package it.reloia.tecnogui.client.gui.hudcomponents;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class HudBar {
    private static final int width = 182; // Default width
    private final DrawContext drawContext;
    private final Identifier texture;

    public enum Anchor {
        LEFT,
        CENTER,
        RIGHT
    }

    public HudBar(DrawContext drawContext, Identifier texture) {
        this.drawContext = drawContext;
        this.texture = texture;
    }
    
    /**
     * Common logic for rendering a bar segment.
     * @param x - The x-coordinate for rendering.
     * @param y - The y-coordinate for rendering.
     * @param scaleX - The horizontal scale factor.
     * @param fillPercentage - The fill percentage of the bar.
     * @param color - The color (RGBA array).
     */
    private void renderBarSegment(int x, int y, float scaleX, float fillPercentage, float[] color) {
        if (fillPercentage > 0.0F) {
            drawContext.setShaderColor(color[0], color[1], color[2], color[3]);
            drawContext.drawTexture(texture, (int) (x * (1 / scaleX)), y, 0, 64, (int) (fillPercentage * width), 5);
        }
    }

    /**
     * Calculates the base rendering properties and renders the bar.
     * @param fillPercentage - Primary fill percentage.
     * @param horizontalAnchor - Horizontal anchor for the bar.
     * @param offsetY - Vertical offset from the bottom of the screen.
     * @param color - Primary shader color (RGBA array).
     * @param fillColor - Primary fill color (RGBA array).
     * @param secondFillPercentage - Secondary fill percentage (optional).
     * @param secondFillColor - Secondary fill color (optional).
     * @param saturationFillPercentage - Saturation fill percentage (optional).
     * @param saturationFillColor - Saturation fill color (optional).
     */
    public void renderBar(float fillPercentage, Anchor horizontalAnchor, int offsetY, float[] color, float[] fillColor,
                          Float secondFillPercentage, float[] secondFillColor,
                          Float saturationFillPercentage, float[] saturationFillColor) {
        MinecraftClient client = MinecraftClient.getInstance();
        final int BASE_X = (client.getWindow().getScaledWidth() / 2) - width / 2;

        int x = BASE_X;
        float scaleX = 1.0F;

        switch (horizontalAnchor) {
            case LEFT:
                scaleX = 0.48F;
                break;
            case RIGHT:
                scaleX = 0.48F;
                x = BASE_X + width - (int) (width * scaleX);
                break;
        }

        int y = client.getWindow().getScaledHeight() - 40 - offsetY;

        drawContext.setShaderColor(color[0], color[1], color[2], color[3]);
        drawContext.getMatrices().push();
        drawContext.getMatrices().scale(scaleX, 1F, 1F);
        drawContext.drawTexture(texture, (int) (x * (1 / scaleX)), y, 0, 64, width, 5);
        
        // Render the secondary fill segment after the primary fill segment.
        if (secondFillPercentage != null && secondFillPercentage > 0.0F) 
            renderBarSegment(x, y, scaleX, Math.min(fillPercentage + secondFillPercentage, 1F), secondFillColor);

        // Render the primary fill segment.
        renderBarSegment(x, y, scaleX, fillPercentage, fillColor);

        // Saturation bar will be rendered on top of all other bars.
        if (saturationFillPercentage != null && saturationFillPercentage > 0.0F) {
            renderBarSegment(x, y, scaleX, saturationFillPercentage, saturationFillColor);
        }

        drawContext.getMatrices().pop();
        drawContext.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void renderBar(float fillPercentage, Anchor horizontalAnchor, int offsetY, float[] color, float[] fillColor,
                          Float secondaryFillPercentage, float[] secondaryFillColor) {
        renderBar(fillPercentage, horizontalAnchor, offsetY, color, fillColor, secondaryFillPercentage, secondaryFillColor, null, null);
    }

    public void renderBar(float fillPercentage, Anchor horizontalAnchor, int offsetY, float[] color, float[] fillColor) {
        renderBar(fillPercentage, horizontalAnchor, offsetY, color, fillColor, null, null, null, null);
    }
}