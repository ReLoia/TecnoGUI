package it.reloia.tecnogui.dataparsing;

import it.reloia.tecnogui.dataparsing.data.ScoreboardData;
import net.minecraft.client.MinecraftClient;

import java.util.Collections;
import java.util.List;

public class TecnoData {
    public static final TecnoData INSTANCE = new TecnoData();

    public ScoreboardData scoreboardData = null;

    private TecnoData() { }

    private List<String> sidebarLines = Collections.emptyList();

    private void fetchSidebarLines() {
        sidebarLines = Utils.getSidebarLines();
    }

    public boolean loadingBalance = false;
    public String fullBalance = "";
    private void loadBalance() {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client == null || client.getNetworkHandler() == null)
            return;

        loadingBalance = true;
        client.getNetworkHandler().sendCommand("balance");
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
                scoreboardData = ScoreboardData.fromLines(sidebarLines);
        }

        if (t % 200 == 0) {
            if (inAServer && isInTecnoRoleplay) {
                loadBalance();
            }
        }

        if (t > 1000)
            t = 0;
    }


}
