package it.reloia.tecnogui.client.gui.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ImageButton extends ButtonWidget {

    private final Identifier imageTexture;
//    private final int textureWidth;
//    private final int textureHeight;


    public ImageButton(int x, int y, int width, int height, Identifier imageTexture, PressAction onPress, String tooltip) {
        super(x, y, width, height, Text.literal(""), onPress, ButtonWidget.DEFAULT_NARRATION_SUPPLIER);
        this.imageTexture = imageTexture;
        this.setTooltip(Tooltip.of(Text.of(tooltip)));
//        this.textureWidth = textureWidth;
//        this.textureHeight = textureHeight;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        RenderSystem.setShaderTexture(0, imageTexture);

        int inPadding = 2;

        int textureWidth = width - inPadding * 2;
        int textureHeight = height - inPadding * 2;
        context.drawTexture(imageTexture, this.getX() + inPadding, this.getY() + inPadding, 0, 0, textureWidth, textureHeight, textureWidth, textureHeight);
    }
}
