package it.reloia.tecnogui.mixin;

import it.reloia.tecnogui.client.TecnoGUIClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Features of this mixin:<br><br>
 * 
 * - <b>Hide Vote Ads</b>
 */
@Mixin(BossBarHud.class)
public abstract class BossBarHudMixin {
    @Inject(method = "renderBossBar*", at = @At("HEAD"), cancellable = true)
    private void tecnogui$hideVoteAds(DrawContext context, int x, int y, BossBar bossBar, CallbackInfo ci) {
        String content = bossBar.getName().getString();
        if (TecnoGUIClient.CONFIG.isHideVoteAds()) {
            if (content.equals("\uD875\uDEC2")) {
                bossBar.setName(Text.of(""));
            }
            ci.cancel();
        }
    }
}
