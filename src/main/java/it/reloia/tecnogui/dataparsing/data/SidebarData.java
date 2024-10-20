package it.reloia.tecnogui.dataparsing.data;

import java.util.List;

public record SidebarData(
        String coordinates,
        String clubCoins,
        String job
) {
    public static SidebarData fromLines(List<String> lines) {
        return new SidebarData(
                "ઑ " + lines.get(2).trim(),
                "\uE0E1 " + lines.get(5).replace("club ", "").trim(),
                "ᮒ " + lines.get(9).trim()
        );
    }
}
