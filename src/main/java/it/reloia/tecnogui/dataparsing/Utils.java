package it.reloia.tecnogui.dataparsing;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.scoreboard.*;
import net.minecraft.text.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static it.reloia.tecnogui.dataparsing.food.ExpiryDateChecker.isDateExpired;

public class Utils {
    /**
     * WHY DID EVERYTHING CHANGE IN JUST 3 SUB VERSIONS<br>
     * 
     * for anybody search: 1.20.4 1.20.3
     *
     * @return a list of lines for a given scoreboard or empty
     *         if the worlds not loaded, scoreboard isnt present
     *         or if the sidebar objective isnt created.
     */
    public static List<String> getSidebarLines() {
        List<String> lines = new ArrayList<>();
        if (MinecraftClient.getInstance().world == null) return lines;
        Scoreboard scoreboard = MinecraftClient.getInstance().world.getScoreboard();
        if (scoreboard == null) {
            // TODO: create an error log class that sends messages in case of errors
            System.out.println("Scoreboard is null");
            return lines;
        }

        ScoreboardObjective sidebar = scoreboard.getObjectiveForSlot(ScoreboardDisplaySlot.SIDEBAR);

        if (sidebar == null) 
            return lines;

        return scoreboard.getScoreboardEntries(sidebar).stream()
                .filter(input -> input != null && input.name() != null && !input.name().getString().startsWith("#") && !input.hidden())
                .sorted(Comparator.comparing(ScoreboardEntry::value)
                        .thenComparing(ScoreboardEntry::owner, String.CASE_INSENSITIVE_ORDER))
                .map(entry -> {
                    Team team = scoreboard.getScoreHolderTeam(entry.owner());
                    return Team.decorateName(team, Text.of(entry.owner())).getString();
                })
                .toList();
    }

    public static float parseHydrationBar(String input) {
        float value = 0;

        for (char c : input.toCharArray()) {
            switch (c) {
                case '\uE122':
                case '\uE123':
                    value += 0.5F;
                    break;
                case '\uE120':
                    value += 1;
                    break;
            }
        }

        return value;
    }

    private static String addBalanceCommas(String input) {
        Float value = Float.parseFloat(input);

        DecimalFormat formatter = new DecimalFormat("#,###.##");

        return formatter.format(value);
    }

    private static String addBalanceCommas(Float input) {
        DecimalFormat formatter = new DecimalFormat("#,###.##");

        return formatter.format(input);
    }

    public static String extendBalance(String balance) {
        String cleanedInput = balance.replace("€", "").trim();

        if (cleanedInput.endsWith("k")) {
            cleanedInput = cleanedInput.replace("k", "");
            return addBalanceCommas(Float.parseFloat(cleanedInput) * 1000);
        }

        return addBalanceCommas(cleanedInput);
    }
    
    public static Boolean isExpired(ItemStack stack) {
        if (stack.getNbt() == null || !stack.hasNbt()) return false;
        
        NbtCompound tag = stack.getNbt();
        if (tag.contains("tecnogui_expired")) {
            return tag.getBoolean("tecnogui_expired");
        }

        List<Text> lore = stack.getTooltip(null, TooltipContext.BASIC);
        if (lore.isEmpty()) return false;
        
        String lastLine = lore.get(lore.size() - 1).getString();
        
        if (!lastLine.contains("Da consumare entro il")) {
            tag.putBoolean("tecnogui_expired", false);
            return false;
        }
        
        boolean expired = isDateExpired(lastLine.trim().substring(lastLine.trim().lastIndexOf(' ') + 1));
        tag.putBoolean("tecnogui_expired", expired);

        return expired;
    }
}
