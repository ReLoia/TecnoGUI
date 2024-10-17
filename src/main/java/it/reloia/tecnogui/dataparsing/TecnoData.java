package it.reloia.tecnogui.dataparsing;

import java.util.Collections;
import java.util.List;

public class TecnoData {

    public static TecnoData INSTANCE;

    private List<String> sidebarLines = Collections.emptyList();

    public List<String> getSidebarLines() {
        return sidebarLines;
    }
    private void fetchSidebarLines() {
        sidebarLines = Utils.getSidebarLines();
    }

    public boolean isInTecno = false;

    private void checkIfInTecno() {
        if (!sidebarLines.isEmpty() && sidebarLines.get(0) != null) {
            isInTecno = sidebarLines.get(0).contains("ᴍᴄ.ᴛᴇᴄɴᴏʀᴏʟᴇᴘʟᴀʏ.ᴄᴏᴍ");
        } else {
            isInTecno = false;
        }
    }

    private int t = 0;
    public void tick() {
        t++;

        if (t % 20 == 0) {
            fetchSidebarLines();
            checkIfInTecno();
        }
    }
}
