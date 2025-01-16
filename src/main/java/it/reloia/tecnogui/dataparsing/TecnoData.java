package it.reloia.tecnogui.dataparsing;

import it.reloia.tecnogui.dataparsing.data.SidebarData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.reloia.tecnogui.dataparsing.food.FoodSaturationCalculator.calculateSaturation;

/**
 * Singleton class that holds all the data parsed from the game.
 */
public class TecnoData {
    private static final Logger LOGGER = LogManager.getLogger(TecnoData.class);

    public static final TecnoData INSTANCE = new TecnoData();

    public static final int ONE_SECOND = 20;
    public static final int TWO_SECONDS = 40;
    public static final int TEN_SECONDS = 200;
    private static final int HEALTH_SLOT = 3;
    private static final int TICK_THRESHOLD = 1000;

    public float lastHealth = 0;

    private final Map<String, Integer> tickCounter = new HashMap<>();

    private TecnoData() {
        tickCounter.put("main", TICK_THRESHOLD);
    }

    public void tickAll() {
        tickCounter.forEach((key, value) -> tickCounter.put(key, Math.max(value - 1, 0)));
    }

    public Integer getTick(String id) {
        return tickCounter.get(id);
    }

    public void setTick(String id, int value) {
        tickCounter.put(id, value);
    }

    public void tick() {
        Integer mainTick = tickCounter.get("main");

        if ((mainTick - TICK_THRESHOLD) % ONE_SECOND == 0) {
            fetchSidebarLines();
            checkIfInTecnoRoleplay();
            if (inAServer && isInTecnoRoleplay && sidebarLines.size() >= 14)
                sidebarData = SidebarData.fromLines(sidebarLines);
        }

        if ((mainTick - TICK_THRESHOLD) % TEN_SECONDS == 0) {
            if (inAServer && isInTecnoRoleplay) {
                loadBalance();
                loadHealthStatus();
            }
        }

        if (mainTick == 0)
            tickCounter.put("main", TICK_THRESHOLD);

        tickAll();
    }

    private SidebarData sidebarData = null;
    public SidebarData getSidebarData() {
        return sidebarData;
    }
    public float hydration = 0;

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
    private void loadHealthStatus() {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client == null || client.player == null)
            return;

        if (client.player.currentScreenHandler == null || client.player.currentScreenHandler.slots.size() < 4)
            return;

        ItemStack healthStack = client.player.currentScreenHandler.slots.get(HEALTH_SLOT).getStack();

        if (healthStack.isEmpty())
            healthStatus = "Loading...";
        else if (healthStack.getTooltip(client.player, TooltipContext.BASIC).size() > 1)
            healthStatus = healthStack.getTooltip(client.player, TooltipContext.BASIC).get(1).getString();
        else
            LOGGER.error("Error while parsing health status. + {}", healthStack.getTooltip(client.player, TooltipContext.BASIC));
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

    public float heldHydration = 0;
    public float heldSaturation = 0;

    /**
     * A method that loads the held item properties.
     * If it is drinkable it will load the hydration value of the drink. (a value that is summed to the current hydration and shows how much the player will be hydrated after drinking the item)
     * If it is a food item it will load the saturation value of the food. (a value that is summed to the current saturation and shows how much the player will be saturated after eating the item)
     */
    public void loadHeldStatus(int slot) {
        MinecraftClient client = MinecraftClient.getInstance();

        heldHydration = 0;
        heldSaturation = 0;

        if (client == null || client.player == null)
            return;

        if (client.player.getInventory() == null || client.player.getInventory().getStack(slot).isEmpty())
            return;

        Item heldItem = client.player.getInventory().getStack(slot).getItem();

        if (heldItem.isFood()) {
            heldSaturation = calculateSaturation(client.player.getInventory().getStack(slot));
        }
        if (heldItem instanceof PotionItem) {
            heldHydration = 4.0F;
        }
    }
}
