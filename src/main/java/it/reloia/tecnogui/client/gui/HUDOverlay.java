package it.reloia.tecnogui.client.gui;

import it.reloia.tecnogui.dataparsing.TecnoData;
import it.reloia.tecnogui.dataparsing.data.ScoreboardData;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class HUDOverlay implements HudRenderCallback {
    float scale = 0.8F;
    float scaleRatio = 1 / scale;

    private int toScale(int x) {
        return (int) (x * scaleRatio);
    }
    private int toScale(double x) {
        return (int) (x * scaleRatio);
    }

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        drawContext.getMatrices().push();
        drawContext.getMatrices().translate(0.0F, 0.0F, -280.0F);

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null)
            return;
        if (!TecnoData.INSTANCE.isInTecnoRoleplay || !TecnoData.INSTANCE.isHUDEnabled)
            return;

        int x = 0;
        int height = 15;
        int y = client.getWindow().getScaledHeight() - 15;
        int width = client.getWindow().getScaledWidth();
        System.out.println("x: " + x + ", y: " + y + ", width: " + width + ", height: " + height);

        ScoreboardData scoreboardData = TecnoData.INSTANCE.scoreboardData;
        if (scoreboardData == null)
            return;

        drawContext.fill(x, y, width, y + height, 0x80000000);

//        TextRenderer textRenderer = client.textRenderer;

        int textY = toScale(y + 5.5 * scale);
        int quarter = width / 5;
        int leftPadding = quarter / 3;

        drawContext.getMatrices().push();
        drawContext.getMatrices().scale(scale, scale, scale);
        drawContext.drawText(client.textRenderer, "ᮐ " + TecnoData.INSTANCE.fullBalance, toScale(4), textY, 0xFCAF31, false);
        drawContext.drawText(client.textRenderer, scoreboardData.clubCoins(), toScale(100), textY, 0x11a8ad, false);
        drawContext.drawText(client.textRenderer, scoreboardData.job(), toScale(width - 85), textY, 0xFFFFFF, false);
        drawContext.drawText(client.textRenderer, scoreboardData.coordinates(), toScale(width - 40), textY, 0xFFFFFF, false);

        drawContext.getMatrices().pop();
        drawContext.getMatrices().pop();
    }
}
