package it.reloia.tecnogui.client.gui;

import it.reloia.tecnogui.client.gui.hudcomponents.HydrationBar;
import it.reloia.tecnogui.dataparsing.TecnoData;
import it.reloia.tecnogui.dataparsing.data.SidebarData;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class HUDOverlay implements HudRenderCallback {
    private static final Identifier ICONS = new Identifier("minecraft", "textures/gui/icons.png");

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
        int y = client.getWindow().getScaledHeight() - 15;
        int height = 15;
        int width = client.getWindow().getScaledWidth();

        renderInfoRectangle(x, y, width, height, client, drawContext);
        HydrationBar.draw(x, y, TecnoData.INSTANCE.hydration, drawContext, ICONS);

        drawContext.getMatrices().pop();
    }

    /**
     * To render stuff in the correct position, we need to scale the coordinates
     */
    private void renderInfoRectangle(int x, int y, int width, int height, MinecraftClient client, DrawContext drawContext) {
        SidebarData sidebarData = TecnoData.INSTANCE.sidebarData;
        if (sidebarData == null)
            return;

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
    }
}
