package it.reloia.tecnogui.client.gui.hudcomponents;

import it.reloia.tecnogui.dataparsing.TecnoData;
import it.reloia.tecnogui.dataparsing.data.SidebarData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class InfoBar {
    private static final float scale = 0.8F;
    private static final float scaleRatio = 1 / scale;
    private static final int height = 15;
    private static final int x = 0;

    private static int toScale(int x) {
        return (int) (x * scaleRatio);
    }
    private static int toScale(double x) {
        return (int) (x * scaleRatio);
    }

    public static void draw(DrawContext drawContext, MinecraftClient client) {
        drawContext.getMatrices().push();
        drawContext.getMatrices().translate(0.0F, 0.0F, -280.0F);


        SidebarData sidebarData = TecnoData.INSTANCE.sidebarData;
        if (sidebarData == null)
            return;

        int width = client.getWindow().getScaledWidth();
        int y = client.getWindow().getScaledHeight() - 15;

        drawContext.fill(x, y, width, y + height, 0x80000000);

        int padding = (int) (width * 0.01);
        int textY = toScale(y + 5.5 * scale);
        int textGap = padding + 4;

        drawContext.getMatrices().push();
        drawContext.getMatrices().scale(scale, scale, scale);

        // toScale(width - textGap - padding) - textRenderer.getWidth(scoreboardData.job()) - textRenderer.getWidth(scoreboardData.coordinates())
//        drawContext.drawText(textRenderer, scoreboardData.job(), toScale(padding + 2 * textGap) + textRenderer.getWidth(balance) + textRenderer.getWidth(scoreboardData.clubCoins()), textY, 0xFFFFFF, false);
        // scoreboardData.coordinates()
        TextRenderer textRenderer = client.textRenderer;
        String balance = "ᮐ " + TecnoData.INSTANCE.fullBalance;

        drawContext.drawText(textRenderer, balance, toScale(padding), textY, 0xFCAF31, false);
        drawContext.drawText(textRenderer, sidebarData.clubCoins(), toScale(padding + textGap) + textRenderer.getWidth(balance), textY, 0xf29b22, false);

        drawContext.drawText(textRenderer, sidebarData.job(), toScale(width - padding) - textRenderer.getWidth(sidebarData.job()), textY, 0xFFFFFF, false);
        drawContext.getMatrices().pop();

        drawContext.getMatrices().pop();
    }
}
