package it.reloia.tecnogui.dataparsing.data;

// [   ᴍᴄ.ᴛᴇᴄɴᴏʀᴏʟᴇᴘʟᴀʏ.ᴄᴏᴍ   §1§d, 0
// §1§c, 1
// -558 61 360§1§b, 2
// ઑ ᴄᴏᴏʀᴅɪɴᴀᴛᴇ§1§a, 3
// §1§9, 4
// 1 club coins§1§8, 5
// € 1.17k (Standard)§1§7, 6
// ᮐ ʙɪʟᴀɴᴄɪᴏ§1§6, 7
// §1§5, 8
// Minatore§1§4, 9
//  ᮒ ʟᴀᴠᴏʀᴏ§1§3, 10
// §1§2, 11
// DF_zXRennyXz ♂ §1§1, 12
// ᮖ  ᴄɪᴛᴛᴀᴅɪɴᴏ§1§0, 13
// §1§r] 14

import java.util.List;

import static it.reloia.tecnogui.dataparsing.Utils.extendBalance;

public record ScoreboardData(
        String coordinates,
        String clubCoins,
        String job
) {
    public static ScoreboardData fromLines(List<String> lines) {
        return new ScoreboardData(
                "ઑ " + lines.get(2).trim(),
                lines.get(5).trim(),
                "ᮒ " + lines.get(9).trim()
        );
    }
}
