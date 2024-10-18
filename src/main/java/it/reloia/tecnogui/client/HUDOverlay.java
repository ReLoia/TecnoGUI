package it.reloia.tecnogui.client;

import it.reloia.tecnogui.dataparsing.TecnoData;
import it.reloia.tecnogui.dataparsing.data.ScoreboardData;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class HUDOverlay implements HudRenderCallback {
    private int toScale(int x) {
        return (int) (x * 1.11);
    }
    private int toScale(double x) {
        return (int) (x * 1.11);
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

        ScoreboardData scoreboardData = TecnoData.INSTANCE.scoreboardData;
        if (scoreboardData == null)
            return;

        drawContext.fill(x, y, width, y + height, 0x80000000);

        TextRenderer textRenderer = client.textRenderer;

        int textY = toScale(y + 5);
        int quarter = width / 5;
        int leftPadding = quarter / 3;

        drawContext.getMatrices().push();
        drawContext.getMatrices().scale(0.9F, 0.9F, 0.9F);
        drawContext.drawText(textRenderer, "ᮐ " + TecnoData.INSTANCE.fullBalance, toScale(leftPadding), textY, 0xFCAF31, false);
        drawContext.drawText(textRenderer, scoreboardData.clubCoins(), toScale(leftPadding + quarter), textY, 0xFFFFFF, false);
        drawContext.drawText(textRenderer, scoreboardData.job(), toScale(leftPadding + quarter * 3), textY, 0xFFFFFF, false);
        drawContext.drawText(textRenderer, scoreboardData.coordinates(), toScale(leftPadding + quarter * 4), textY, 0xFFFFFF, false);

        drawContext.getMatrices().pop();
        drawContext.getMatrices().pop();
    }
}
