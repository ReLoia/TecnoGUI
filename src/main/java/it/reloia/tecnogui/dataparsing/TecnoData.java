package it.reloia.tecnogui.dataparsing;

import it.reloia.tecnogui.dataparsing.data.SidebarData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;

import java.util.Collections;
import java.util.List;

public class TecnoData {
    public static final TecnoData INSTANCE = new TecnoData();

    public SidebarData sidebarData = null;
    public float hydration = 0;

    private TecnoData() { }

    private List<String> sidebarLines = Collections.emptyList();

    private void fetchSidebarLines() {
        sidebarLines = Utils.getSidebarLines();
    }

    public boolean loadingBalance = false;
    public String fullBalance = "Loading...";
    private void loadBalance() {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client == null || client.getNetworkHandler() == null)
            return;

        loadingBalance = true;
        client.getNetworkHandler().sendCommand("balance");
    }

    public String healthStatus = "Loading...";
    private void loadHealth() {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client == null || client.player == null)
            return;

        if (client.player.currentScreenHandler == null || client.player.currentScreenHandler.slots.size() < 4)
            return;

        ItemStack healthStack = client.player.currentScreenHandler.slots.get(3).getStack();

        if (healthStack.isEmpty())
            healthStatus = "Loading...";
        else if (healthStack.getTooltip(client.player, TooltipContext.BASIC).size() > 1)
            healthStatus = healthStack.getTooltip(client.player, TooltipContext.BASIC).get(1).getString();
        else System.out.println("Error while parsing health status. + " + healthStack.getTooltip(client.player, TooltipContext.BASIC));
    }

    public boolean inAServer = false;
    public boolean isInTecnoRoleplay = false;
    public boolean isHUDEnabled = true;

    private void checkIfInTecnoRoleplay() {
        if (sidebarLines.size() >= 3) {
            isInTecnoRoleplay = sidebarLines.get(3).contains("ᴄᴏᴏʀᴅɪɴᴀᴛᴇ");
        } else {
            isInTecnoRoleplay = false;
        }
    }

    private int t = 0;
    public void tick() {
        t++;

        // 1 sec
        if (t % 20 == 0) {
            fetchSidebarLines();
            checkIfInTecnoRoleplay();
            if (inAServer && isInTecnoRoleplay && sidebarLines.size() >= 14)
                sidebarData = SidebarData.fromLines(sidebarLines);
        }

        if (t % 200 == 0) {
            if (inAServer && isInTecnoRoleplay) {
                loadBalance();
                loadHealth();
            }
        }

        if (t > 1000)
            t = 0;
    }


}
