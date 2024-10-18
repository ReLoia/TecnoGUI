package it.reloia.tecnogui.mixin;

import it.reloia.tecnogui.dataparsing.TecnoData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud {
    @Final
    @Shadow
    private static Identifier ICONS;

    @Final
    @Shadow
    private MinecraftClient client;

    @Shadow
    private int scaledWidth;

    @Shadow
    private int scaledHeight;

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
            // 14.0F
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
            // 14.0F
            context.getMatrices().translate(0.0F, -4.0F, 0.0F);
        }
    }

    @Inject(method = "renderHotbar", at = @At("TAIL"))
    protected void tecnogui$restoreHotbarPositionOnRenderHotbar(float tickDelta, DrawContext context, CallbackInfo ci) {
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay) {
            context.getMatrices().pop();
        }
    }
}
