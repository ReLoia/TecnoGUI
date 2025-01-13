package it.reloia.tecnogui.client.gui.hudcomponents;

import it.reloia.tecnogui.dataparsing.TecnoData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class CustomBars {

    /**
     * Renders all custom bars.
     * @param drawContext - Context used to draw.
     * @param texture - Texture Identifier for the bar icons.
     */
    public static void renderBars(DrawContext drawContext, Identifier texture) {
        assert MinecraftClient.getInstance().player != null;

        drawContext.getMatrices().push();
        drawContext.getMatrices().translate(0.0F, 0.0F, -280.0F);

        HudBar hudBar = new HudBar(drawContext, texture);

        float health = (float) Math.floor(MinecraftClient.getInstance().player.getHealth() + .7) / 20.0F;
        hudBar.renderBar(health, HudBar.Anchor.CENTER, 0, new float[]{0.35F, 0.2F, 0.2F, 1.0F}, new float[]{1.0F, 0.2F, 0.0F, 1.0F});

        float food = MinecraftClient.getInstance().player.getHungerManager().getFoodLevel() / 20.0F;
        float heldSaturation = TecnoData.INSTANCE.heldSaturation / 20.0F;
        float saturation = MinecraftClient.getInstance().player.getHungerManager().getSaturationLevel() / 20.0F;
        hudBar.renderBar(food,
                HudBar.Anchor.LEFT,
                7,
                new float[]{0.3F, 0.15F, 0.2F, 1.0F},
                new float[]{0.6F, 0.27F, 0.0F, 1.0F},
                heldSaturation,
                new float[]{0.6F, 0.37F, 0.0F, 1.0F},
                saturation,
                new float[]{0.6F, 0.5F, 0.0F, 1.0F});

        float thirstPercentage = TecnoData.INSTANCE.hydration / 10.0F;
        float heldHydration = TecnoData.INSTANCE.heldHydration / 10.0F;
        hudBar.renderBar(thirstPercentage, HudBar.Anchor.RIGHT,7, new float[]{0.2F, 0.2F, 0.35F, 1.0F}, new float[]{0.0F, 0.2F, 1.0F, 1.0F}, heldHydration, new float[]{0.0F, 0.3F, 1.0F, 1.0F});

        drawContext.getMatrices().pop();
    }
}
