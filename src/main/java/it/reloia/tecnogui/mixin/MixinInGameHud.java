package it.reloia.tecnogui.mixin;

import it.reloia.tecnogui.dataparsing.TecnoData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static it.reloia.tecnogui.dataparsing.Utils.parseHydrationBar;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud {
    @Inject(method = "renderScoreboardSidebar", at = @At("HEAD"), cancellable = true)
    protected void tecnogui$toggleScoreboardOnRenderScoreboardSidebar(CallbackInfo ci) {
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay)
            ci.cancel();
    }

    @Inject(method = "renderHealthBar", at = @At("HEAD"), cancellable = true)
    protected void tecnogui$toggleHealthBarOnRenderHealthBar(CallbackInfo ci) {
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay)
            ci.cancel();
    }

    @Inject(method = "renderStatusBars", at = @At("HEAD"), cancellable = true)
    protected void tecnogui$toggleStatusBarsOnRenderStatusBars(CallbackInfo ci) {
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay)
            ci.cancel();
    }

    // experience bar

    @Inject(method = "renderExperienceBar", at = @At("HEAD"))
    protected void tecnogui$moveExperienceBarOnExperienceBar(DrawContext context, int x, CallbackInfo ci) {
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay) {
            context.getMatrices().push();
            context.getMatrices().translate(0.0F, -4.0F, 0.0F);
        }
    }

    @Inject(method = "renderExperienceBar", at = @At("TAIL"))
    protected void tecnogui$restoreExperienceBarOnExperienceBar(DrawContext context, int x, CallbackInfo ci) {
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay) {
            context.getMatrices().pop();
        }
    }

    @Inject(method = "renderHotbar", at = @At("HEAD"))
    protected void tecnogui$moveHotbarOnRenderHotbar(float tickDelta, DrawContext context, CallbackInfo ci) {
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay) {
            context.getMatrices().push();
            context.getMatrices().translate(0.0F, -4.0F, 0.0F);
        }
    }

    @Inject(method = "renderHotbar", at = @At("TAIL"))
    protected void tecnogui$restoreHotbarPositionOnRenderHotbar(float tickDelta, DrawContext context, CallbackInfo ci) {
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay) {
            context.getMatrices().pop();
        }
    }

    @Inject(method = "setOverlayMessage", at = @At("HEAD"), cancellable = true)
    protected void tecnogui$cancelSetOverlayMessage(Text message, boolean tinted, CallbackInfo ci) {
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay) {
            String msg = message.getString();
            if (msg.contains("Sei entrato nel lotto di"))
                ci.cancel();
            else if (msg.length() > 15 && "\uE120\uE121\uE122\uE123\uE124\uE125".indexOf(msg.charAt(15)) != -1) {
                TecnoData.INSTANCE.hydration = parseHydrationBar(msg);
                ci.cancel();
            }
            else {
                System.out.println(msg);
            }
        }

    }

}
