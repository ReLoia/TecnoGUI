package it.reloia.tecnogui.mixin;

import it.reloia.tecnogui.client.TecnoGUIClient;
import it.reloia.tecnogui.dataparsing.TecnoData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

import static it.reloia.tecnogui.dataparsing.Utils.isExpired;

/**
 * Features of this mixin:<br><br>
 * 
 * - <b>Highlight Expired Items</b>
 */
@Mixin(HandledScreen.class)
public class HandledScreenMixin {
    @Inject(method = "drawSlot", at = @At("HEAD"))
    private void tecnogui$drawSlotExpired(DrawContext context, Slot slot, CallbackInfo ci) {
        if (!TecnoData.INSTANCE.isHUDEnabled || !TecnoData.INSTANCE.isInTecnoRoleplay)
            return;
        
        if (!TecnoGUIClient.CONFIG.isHighlightExpiredFood())
            return;
        
        ItemStack stack = slot.getStack();
        
        if (stack.isEmpty())
            return;
        
        boolean expired = isExpired(stack);

        if (expired) {
            int x = slot.x;
            int y = slot.y;
            int z = 0;
            
            Color color = TecnoGUIClient.CONFIG.getHighlightExpiredFoodColor();
            int rgb = color.getRGB();
            
            context.fillGradient(RenderLayer.getGuiOverlay(), x, y, x + 16, y + 16, rgb, rgb, z);
        }
    }
}
