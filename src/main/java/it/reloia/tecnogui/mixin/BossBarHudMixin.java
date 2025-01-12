package it.reloia.tecnogui.mixin;

import com.google.common.collect.Maps;
import it.reloia.tecnogui.client.TecnoGUIClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.gui.hud.ClientBossBar;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.UUID;

/**
 * Features of this mixin:<br><br>
 * 
 * - <b>Hide Vote Ads</b>
 */
@Mixin(BossBarHud.class)
public abstract class BossBarHudMixin {
    @Final
    @Shadow
    final Map<UUID, ClientBossBar> bossBars = Maps.newLinkedHashMap();

    @Inject(method = "render", at = @At("HEAD"))
    private void tecnogui$getBossBarNameOnRender(DrawContext context, CallbackInfo ci) {
        for (ClientBossBar clientBossBar : this.bossBars.values()) {
            String content = clientBossBar.getName().getString();
            if (content.equals("\uD875\uDEC2") && TecnoGUIClient.CONFIG.isHideVoteAds()) {
                clientBossBar.setName(Text.of(""));
            }
        }
    }
}
