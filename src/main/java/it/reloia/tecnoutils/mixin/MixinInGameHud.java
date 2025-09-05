package it.reloia.tecnoutils.mixin;

import it.reloia.tecnoutils.client.TecnoUtilsClient;
import it.reloia.tecnoutils.dataparsing.TecnoData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

import static it.reloia.tecnoutils.dataparsing.Utils.isExpired;
import static it.reloia.tecnoutils.dataparsing.Utils.parseHydrationBar;

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
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay && TecnoUtilsClient.CONFIG.isHideScoreboard())
            ci.cancel();
    }

    /**
     * Features of this injection:<br><br>
     * 
     * - Hide <b>Status Bars</b><br> (Hunger, Armor, Air)
     *  - If `isReplaceBars()` is enabled, it will cancel the rendering of the status bars<br>
     *    else it will move the status bars a little bit up
     */
    @Inject(method = "renderStatusBars", at = @At("HEAD"), cancellable = true)
    protected void tecnogui$toggleStatusBarsOnRenderStatusBars(DrawContext context, CallbackInfo ci) {
        // TODO: add support for air bar

        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay) {
            if (TecnoUtilsClient.CONFIG.isReplaceBars())
                ci.cancel();
            else {
                context.getMatrices().push();
                context.getMatrices().translate(0.0F, -3.0F, 0.0F);
            }
        }
    }
    
    @Inject(method = "renderStatusBars", at = @At("TAIL"))
    protected void tecnogui$restoreStatusBarsOnRenderStatusBars(DrawContext context, CallbackInfo ci) {
        if (!TecnoUtilsClient.CONFIG.isReplaceBars() && TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay) {
            context.getMatrices().pop();
        }
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

        if (!TecnoUtilsClient.CONFIG.isHighlightExpiredFood())
            return;

        if (stack.isEmpty())
            return;

        boolean expired = isExpired(stack);

        Color color = TecnoUtilsClient.CONFIG.getHighlightExpiredFoodColor();
        int rgb = color.getRGB();

        if (expired)
            context.fillGradient(x, y, x + 16, y + 16, rgb, rgb);
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

    @Inject(method = "renderMountHealth", at = @At("HEAD"), cancellable = true)
    protected void tecnogui$cancelRenderMountHealth(DrawContext context, CallbackInfo ci) {
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay)
            ci.cancel();
    }    
    
    /**
     * Features of this injection:<br><br>
     * 
     * - Hide `Sei entrato nel lotto di` message<br>
     * - Parse <b>Vehicle Speed</b> as Experience Level<br>
     * - Parse <b>Hydration Bar</b><br>
     */
    @Inject(method = "setOverlayMessage", at = @At("HEAD"), cancellable = true)
    protected void tecnogui$cancelSetOverlayMessage(Text message, boolean tinted, CallbackInfo ci) {
        if (TecnoData.INSTANCE.isHUDEnabled && TecnoData.INSTANCE.isInTecnoRoleplay) {
            String msg = message.getString();
            if (msg.contains("Sei entrato ne") && TecnoUtilsClient.CONFIG.isHideEnteredPlotMsg())
                ci.cancel();
            else if (msg.contains("Velocità") && TecnoUtilsClient.CONFIG.isVehicleSpeedAsEXPLevel()) {
                TecnoData.INSTANCE.speed = Integer.parseInt(msg.substring(msg.indexOf(":") + 2).toUpperCase().replace("KM/H", "").trim());
                ci.cancel();
            }
            else if (TecnoUtilsClient.CONFIG.isReplaceBars() && msg.length() > 15 && "\uE120\uE121\uE122\uE123\uE124\uE125".indexOf(msg.charAt(15)) != -1) {
                // TODO: add settings support
                TecnoData.INSTANCE.hydration = parseHydrationBar(msg);
                ci.cancel();
            } else {
                if (TecnoUtilsClient.CONFIG.isDebug())
                    System.out.println(msg);
            }
        }
    }
}
