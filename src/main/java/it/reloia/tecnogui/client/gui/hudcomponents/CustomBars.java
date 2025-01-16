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
        drawContext.getMatrices().translate(0.0F, 0.0F, 280.0F);


        float health = (float) Math.floor(MinecraftClient.getInstance().player.getHealth() + .7) / 20.0F;
        HudBar healthBar = new HudBar(drawContext, texture, new float[]{0.35F, 0.2F, 0.2F, 1.0F});
        healthBar.setFirstFill(health, new float[]{1.0F, 0.2F, 0.0F, 1.0F});
        if (TecnoData.INSTANCE.lastHealth > health) {
            TecnoData.INSTANCE.setTick("health", TecnoData.TWO_SECONDS);
        }
        TecnoData.INSTANCE.lastHealth = health;
        healthBar.setTickID("health");
        healthBar.render();

        float food = MinecraftClient.getInstance().player.getHungerManager().getFoodLevel() / 20.0F;
        float heldSaturation = TecnoData.INSTANCE.heldSaturation / 20.0F;
        float saturation = MinecraftClient.getInstance().player.getHungerManager().getSaturationLevel() / 20.0F;
        HudBar foodBar = new HudBar(drawContext, texture, new float[]{0.3F, 0.15F, 0.2F, 1.0F});
        foodBar.setOffsetY(5 + 2);
        foodBar.setHorizontalAnchor(HudBar.Anchor.LEFT);
        foodBar.setFirstFill(food, new float[]{0.6F, 0.27F, 0.0F, 1.0F});
        foodBar.setSecondFill(heldSaturation, new float[]{0.6F, 0.37F, 0.0F, 1.0F});
        foodBar.setSaturation(saturation, new float[]{0.6F, 0.5F, 0.0F, 1.0F});
        foodBar.render();

        float thirstPercentage = TecnoData.INSTANCE.hydration / 10.0F;
        float heldHydration = TecnoData.INSTANCE.heldHydration / 10.0F;
        HudBar thirstBar = new HudBar(drawContext, texture, new float[]{0.2F, 0.2F, 0.35F, 1.0F});
        thirstBar.setOffsetY(5 + 2);
        thirstBar.setHorizontalAnchor(HudBar.Anchor.RIGHT);
        thirstBar.setFirstFill(thirstPercentage, new float[]{0.0F, 0.2F, 1.0F, 1.0F});
        thirstBar.setSecondFill(heldHydration, new float[]{0.0F, 0.3F, 1.0F, 1.0F});
        thirstBar.render();

        float airPercentage = (float) MinecraftClient.getInstance().player.getAir() / MinecraftClient.getInstance().player.getMaxAir();
        if (airPercentage != 1) {
            HudBar airBar = new HudBar(drawContext, texture, new float[]{0.35F, 0.3F, 0.30F, 1.0F});
            airBar.setOffsetY((5 + 2)*2);
            airBar.setHorizontalAnchor(HudBar.Anchor.RIGHT);
            airBar.setFirstFill(airPercentage, new float[]{0.75F, 0.65F, 0.7F, 1.0F});
            airBar.render();
        }

        drawContext.getMatrices().pop();
    }
}
