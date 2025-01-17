package it.reloia.tecnogui.dataparsing;

import com.google.common.collect.Iterables;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.Text;
import org.apache.commons.compress.utils.Lists;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static it.reloia.tecnogui.dataparsing.food.ExpiryDateChecker.isDateExpired;

public class Utils {
    /**
     * I LOVE aaron1998ish
     * <a href="https://gist.github.com/aaron1998ish/33c4e1836bd5cf79501d163a1b5c8304">...</a>
     * +
     * I LOVE Apec
     * <p>
     * Fetching lines are based on how they're visually seen on your sidebar
     * and not based on the actual value score.
     * <p>
     * Written around Minecraft 1.8 Scoreboards, modify to work with your
     * current version of Minecraft.
     * <p>
     * <3 aaron1998ish
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

        ScoreboardObjective objective = scoreboard.getObjectiveForSlot(1);

        if (objective == null) {
            System.out.println("Objective is null");
            return lines;
        }

        Collection<ScoreboardPlayerScore> scores = scoreboard.getAllPlayerScores(objective);
        List<ScoreboardPlayerScore> list = scores.stream()
                .filter(input -> input != null && input.getPlayerName() != null && !input.getPlayerName().startsWith("#"))
                .toList();

        if (list.size() > 15) {
            scores = Lists.newArrayList(Iterables.skip(list, scores.size() - 15).iterator());
        } else {
            scores = list;
        }

        for (ScoreboardPlayerScore score : scores) {
            Team team = scoreboard.getPlayerTeam(score.getPlayerName());
            lines.add(Team.decorateName(team, Text.of(score.getPlayerName())).getString());
        }

        return lines;
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
