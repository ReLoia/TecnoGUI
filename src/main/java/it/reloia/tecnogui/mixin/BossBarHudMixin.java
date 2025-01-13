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
 * - <b>Fix Boss Bar appearing with Resource Pack in 1.20.4</b>
 */
@Mixin(BossBarHud.class)
public abstract class BossBarHudMixin {
    @Inject(method = "renderBossBar*", at = @At("HEAD"), cancellable = true)
    private void tecnogui$hideVoteAds(DrawContext context, int x, int y, BossBar bossBar, CallbackInfo ci) {
        String content = bossBar.getName().getString();
        if (content.equals("\uD875\uDEC2") && TecnoGUIClient.CONFIG.isHideVoteAds()) {
            bossBar.setName(Text.of(""));
        }
        ci.cancel();
    }
}
