package it.reloia.tecnogui.mixin;

import it.reloia.tecnogui.dataparsing.TecnoData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
}
