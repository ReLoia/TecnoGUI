package it.reloia.tecnogui.mixin;

import it.reloia.tecnogui.dataparsing.TecnoData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static it.reloia.tecnogui.dataparsing.Utils.isExpired;
import static it.reloia.tecnogui.dataparsing.Utils.parseHydrationBar;

/**
 * Features of this mixin:<br><br>
 *
 * - Expired Food Highlight<br>
 * - Move Hotbar a little bit up<br>
 * - Hide Scoreboard Sidebar<br>
 * - Hide Health Bar<br>
 * - Hide Status Bars (Hunger, Armor, Air)<br>
 * - Move Experience Bar a little bit up<br>
 * - Hide `Sei entrato nel lotto di` message<br>
 * - Parse hydration bar
 */
@Mixin(InGameHud.class)
public abstract class MixinInGameHud {
    /**
     * Features of this injection:<br><br>
     * 
     * - Hide <b>Scoreboard Sidebar</b><br>
     */
    @Inject(method = "renderScoreboardSidebar", at = @At("HEAD"), cancellable = true)
    protected void tecnogui$toggleScoreboardOnRenderScoreboardSidebar(CallbackInfo ci) {
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay)
            ci.cancel();
    }

    /**
     * Features of this injection:<br><br>
     * 
     * - Hide <b>Health Bar</b><br>
     */
    @Inject(method = "renderHealthBar", at = @At("HEAD"), cancellable = true)
    protected void tecnogui$toggleHealthBarOnRenderHealthBar(CallbackInfo ci) {
        // TODO: add settings support
        
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay)
            ci.cancel();
    }

    /**
     * Features of this injection:<br><br>
     * 
     * - Hide <b>Status Bars</b><br> (Hunger, Armor, Air)
     */
    @Inject(method = "renderStatusBars", at = @At("HEAD"), cancellable = true)
    protected void tecnogui$toggleStatusBarsOnRenderStatusBars(CallbackInfo ci) {
        // TODO: add settings support
        // TODO: add support for air bar
        
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay)
            ci.cancel();
    }

    
    /**
     * Features of this injection:<br><br>
     * 
     * - Move <b>Experience Bar</b> a little bit up
     */
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

    /**
     * Features of this injection:<br><br>
     * 
     * - Expired Food Highlight
     */
    @Inject(method = "renderHotbarItem", at = @At("HEAD"))
    protected void tecnogui$renderHotbarItemExpired(DrawContext context, int x, int y, float f, PlayerEntity player, ItemStack stack, int seed, CallbackInfo ci) {
        if (!TecnoData.INSTANCE.isHUDEnabled || !TecnoData.INSTANCE.isInTecnoRoleplay)
            return;
        
        // TODO: add settings support
        
        if (stack.isEmpty()) 
            return;
        
        boolean expired = isExpired(stack);
        
        if (expired)
            context.fillGradient(x, y, x + 16, y + 16, 0x8090EE90, 0x8090EE90);
    }

    /**
     * Features of this injection:<br><br>
     * 
     * - Move <b>Hotbar</b> a little bit up
     */
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

    /**
     * Features of this injection:<br><br>
     * 
     * - Hide `Sei entrato nel lotto di` message<br>
     * - Parse hydration bar<br>
     */
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
