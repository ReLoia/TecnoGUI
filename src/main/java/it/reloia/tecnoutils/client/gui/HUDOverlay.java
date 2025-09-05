package it.reloia.tecnoutils.client.gui;

import it.reloia.tecnoutils.client.TecnoUtilsClient;
import it.reloia.tecnoutils.client.gui.hudcomponents.CustomBars;
import it.reloia.tecnoutils.client.gui.hudcomponents.InfoBar;
import it.reloia.tecnoutils.dataparsing.TecnoData;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.Identifier;

public class HUDOverlay implements HudRenderCallback {
    private static final Identifier ICONS = new Identifier("minecraft", "textures/gui/icons.png");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        
        if (client == null)
            return;
        if (!TecnoData.INSTANCE.isInTecnoRoleplay || !TecnoData.INSTANCE.isHUDEnabled)
            return;
        
        InfoBar.draw(drawContext, client);

        if (TecnoUtilsClient.CONFIG.isReplaceBars())
            CustomBars.renderBars(drawContext, ICONS);
        
        if (TecnoUtilsClient.CONFIG.isVehicleSpeedAsEXPLevel()) {
            assert client.player != null;
            Entity vehicle = client.player.getVehicle();
            if (vehicle instanceof ArmorStandEntity) {
                client.player.setExperience(0, 0, TecnoData.INSTANCE.speed);
            } else {
                client.player.setExperience(0, 0, 0);
            }
        }
    }
}
