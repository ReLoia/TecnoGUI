package it.reloia.tecnogui.client.gui.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ImageButton extends ButtonWidget {

    private final Identifier imageTexture;
//    private final int textureWidth;
//    private final int textureHeight;


    public ImageButton(int x, int y, int width, int height, Identifier imageTexture, PressAction onPress) {
        super(x, y, width, height, Text.literal(""), onPress, ButtonWidget.DEFAULT_NARRATION_SUPPLIER);
        this.imageTexture = imageTexture;
//        this.textureWidth = textureWidth;
//        this.textureHeight = textureHeight;
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        super.renderWidget(context, mouseX, mouseY, delta);

        RenderSystem.setShaderTexture(0, imageTexture);

        int inPadding = 2;

        int textureWidth = width - inPadding * 2;
        int textureHeight = height - inPadding * 2;
        context.drawTexture(imageTexture, this.getX() + inPadding, this.getY() + inPadding, 0, 0, textureWidth, textureHeight, textureWidth, textureHeight);
    }
}
