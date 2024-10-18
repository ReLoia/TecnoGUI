package it.reloia.tecnogui.dataparsing;

import it.reloia.tecnogui.dataparsing.data.ScoreboardData;

import java.util.Collections;
import java.util.List;

public class TecnoData {
    public static final TecnoData INSTANCE = new TecnoData();

    public ScoreboardData scoreboardData = null;

    private TecnoData() {

    }

    private List<String> sidebarLines = Collections.emptyList();

    private List<String> getSidebarLines() {
        return sidebarLines;
    }
    private void fetchSidebarLines() {
        sidebarLines = Utils.getSidebarLines();
    }

    public boolean inAServer = false;
    public boolean isInTecnoRoleplay = false;

    public boolean isHUDEnabled = true;

    private void checkIfInTecnoRoleplay() {
        if (!sidebarLines.isEmpty() && sidebarLines.get(3) != null) {
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

        if (t > 1000)
            t = 0;
    }


}
