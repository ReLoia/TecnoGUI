package it.reloia.tecnogui.client.gui.hudcomponents;

import it.reloia.tecnogui.dataparsing.TecnoData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class HudBar {
    private static final int WIDTH = 182; // Default width

    private final DrawContext drawContext;
    private final Identifier texture;

    private float fillPercentage;
    private final float[] backColor;
    private float[] firstFillColor;
    private float[] secondFillColor;
    private float[] saturationFillColor;

    private float secondFillPercentage;
    private float saturationFillPercentage;
    private int offsetY;
    private Anchor horizontalAnchor;

    private String tickID;

    public enum Anchor {
        LEFT,
        CENTER,
        RIGHT
    }

    public void setFirstFill(float fillPercentage, float[] firstFillColor) {
        this.fillPercentage = fillPercentage;
        this.firstFillColor = firstFillColor;
    }

    public void setSecondFill(float secondFillPercentage, float[] secondFillColor) {
        this.secondFillColor = secondFillColor;
        this.secondFillPercentage = secondFillPercentage;
    }

    public void setSaturation(float saturationFillPercentage, float[] saturationFillColor) {
        this.saturationFillColor = saturationFillColor;
        this.saturationFillPercentage = saturationFillPercentage;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public void setHorizontalAnchor(Anchor horizontalAnchor) {
        this.horizontalAnchor = horizontalAnchor;
    }

    public void setTickID(String tick) {
        this.tickID = tick;
        if (TecnoData.INSTANCE.getTick(tick) == null) {
            TecnoData.INSTANCE.setTick(tick, 0);
        }
    }

    public HudBar(DrawContext drawContext, Identifier texture, float[] backColor) {
        this.drawContext = drawContext;
        this.texture = texture;
        this.fillPercentage = 0;
        this.backColor = backColor;
        this.firstFillColor = new float[]{1.0F, 1.0F, 1.0F, 1.0F}; // Default white
        this.secondFillColor = null;
        this.saturationFillColor = null;
        this.secondFillPercentage = 0;
        this.saturationFillPercentage = 0;
        this.offsetY = 0;
        this.horizontalAnchor = Anchor.CENTER;
        this.tickID = null;
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
            drawContext.drawTexture(texture, (int) (x * (1 / scaleX)), y, 0, 64, (int) (fillPercentage * WIDTH), 5);
        }
    }


    public void render() {
        MinecraftClient client = MinecraftClient.getInstance();
        final int BASE_X = (client.getWindow().getScaledWidth() / 2) - WIDTH / 2;

        int x = BASE_X;
        float scaleX = 1.0F;

        switch (horizontalAnchor) {
            case LEFT:
                scaleX = 0.48F;
                break;
            case RIGHT:
                scaleX = 0.48F;
                x = BASE_X + WIDTH - (int) (WIDTH * scaleX);
                break;
        }

        int y = client.getWindow().getScaledHeight() - 40 - offsetY;

        // Render outline if enabled
        if (tickID != null && TecnoData.INSTANCE.getTick(tickID) > 0) {
            drawContext.fill(x - 1, y - 1, x + WIDTH + 1, y + 6, 0xFFa88222);
        }

        // Render background
        drawContext.setShaderColor(backColor[0], backColor[1], backColor[2], backColor[3]);
        drawContext.getMatrices().push();
        drawContext.getMatrices().scale(scaleX, 1F, 1F);
        drawContext.drawTexture(texture, (int) (x * (1 / scaleX)), y, 0, 64, WIDTH, 5);

        // Render secondary fill
        if (secondFillPercentage > 0.0F) {
            drawContext.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            renderBarSegment(x, y, scaleX, Math.min(fillPercentage + secondFillPercentage, 1F), secondFillColor);
        }

        // Render primary fill
        renderBarSegment(x, y, scaleX, fillPercentage, firstFillColor);

        // Render saturation fill
        if (saturationFillPercentage > 0.0F) {
            renderBarSegment(x, y, scaleX, saturationFillPercentage, saturationFillColor);
        }

        if (tickID != null) {
            System.out.println(tickID + " " + TecnoData.INSTANCE.getTick(tickID));
        }

        drawContext.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        drawContext.getMatrices().pop();
    }
}