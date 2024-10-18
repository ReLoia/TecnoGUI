package it.reloia.tecnogui.mixin;

import it.reloia.tecnogui.dataparsing.TecnoData;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ChatPacketMixin {
    @Inject(method = "onGameMessage", at = @At("HEAD"), cancellable = true)
    private void onGameMessageMixin(GameMessageS2CPacket packet, CallbackInfo ci) {
        String message = packet.content().getString();

        if (message.contains("Bilancio conto")) {
            if (TecnoData.INSTANCE.loadingBalance)
                ci.cancel();

            TecnoData.INSTANCE.fullBalance = message.substring(message.indexOf(":") + 2).replace("€", "").trim();
            TecnoData.INSTANCE.loadingBalance = false;
        }

    }
}
