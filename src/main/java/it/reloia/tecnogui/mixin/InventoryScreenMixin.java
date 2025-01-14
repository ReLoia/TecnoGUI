package it.reloia.tecnogui.mixin;

import it.reloia.tecnogui.client.gui.widgets.ImageButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Features of this mixin:<br><br>
 * 
 * - <b>InventoryButtons</b><br>
 * <br>
 * This mixin will be reworked in the future to support more custom buttons.
 */
@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends AbstractInventoryScreen<PlayerScreenHandler> implements RecipeBookProvider {
    @Unique
    private ButtonWidget customButton;

    public InventoryScreenMixin(PlayerScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        // todo: make customButtons configurable
        this.customButton = new ImageButton(
                this.x - 20,
                this.y + 2,
                20, 20,
                new Identifier("tecnogui",  "textures/gui/garage.png"),
                (button) -> {
                    if (MinecraftClient.getInstance().getNetworkHandler() != null)
                        MinecraftClient.getInstance().getNetworkHandler().sendCommand("garage");
                },
                "Garage"
        );

        this.addDrawableChild(customButton);
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.customButton.render(context, mouseX, mouseY, delta);
    }
}

