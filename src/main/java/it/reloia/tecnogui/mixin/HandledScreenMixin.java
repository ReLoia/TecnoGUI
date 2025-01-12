package it.reloia.tecnogui.mixin;

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

import static it.reloia.tecnogui.dataparsing.Utils.isExpired;

/**
 * Features of this mixin:<br>
 * 
 * - Expired Food Highlight
 */
@Mixin(HandledScreen.class)
public class HandledScreenMixin {
    @Inject(method = "drawSlot", at = @At("HEAD"))
    private void tecnogui$drawSlotExpired(DrawContext context, Slot slot, CallbackInfo ci) {
        if (!TecnoData.INSTANCE.isHUDEnabled || !TecnoData.INSTANCE.isInTecnoRoleplay)
            return;
        
        // TODO: add settings support
        
        ItemStack stack = slot.getStack();
        
        if (stack.isEmpty())
            return;
        
        boolean expired = isExpired(stack);

        if (expired) {
            int x = slot.x;
            int y = slot.y;
            int z = 0;
            
            context.fillGradient(RenderLayer.getGuiOverlay(), x, y, x + 16, y + 16, 0x8090EE90, 0x8090EE90, z);
        }
    }
}
