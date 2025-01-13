package it.reloia.tecnogui.mixin;

import it.reloia.tecnogui.dataparsing.TecnoData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Features of this mixin:<br><br>
 *
 * - Move <b>Chat Render</b> a little bit up
 */
@Mixin(ChatHud.class)
public class ChatHudMixin {
    @Inject(method = "render", at = @At("HEAD"))
    protected void tecnogui$moveChatRender(DrawContext context, int currentTick, int mouseX, int mouseY, CallbackInfo ci) {
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay) {
            context.getMatrices().push();
            context.getMatrices().translate(0.0F, -8.0F, 0.0F);
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    protected void tecnogui$restoreChatRender(DrawContext context, int currentTick, int mouseX, int mouseY, CallbackInfo ci) {
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay) {
            context.getMatrices().pop();
        }
    }
    
    @ModifyVariable(method = "toChatLineY", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    protected double tecnogui$chatLineYMoved(double y) {
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay) {
            y += 8.0D;
        }
        return y;
    }
}
