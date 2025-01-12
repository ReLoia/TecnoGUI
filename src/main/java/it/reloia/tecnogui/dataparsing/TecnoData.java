package it.reloia.tecnogui.dataparsing;

import it.reloia.tecnogui.dataparsing.data.SidebarData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;

import java.util.Collections;
import java.util.List;

/**
 * Singleton class that holds all the data parsed from the game.
 */
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
    private void loadHealthStatus() {
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
                loadHealthStatus();
            }
        }

        if (t > 1000)
            t = 0;
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

        if (client == null || client.player == null)
            return;

        if (client.player.getInventory() == null || client.player.getInventory().getStack(slot).isEmpty())
            return;

        Item heldItem = client.player.getInventory().getStack(slot).getItem();

        heldHydration = 0;
        heldSaturation = 0;

        if (heldItem.isFood()) {
            // TODO: make a method that returns the saturation value of the food item - because the server has a different saturation value for each food item
//            float saturation = heldItem.getFoodComponent().getSaturationModifier();
//            System.out.println("Saturation: " + saturation);
        }
        if (heldItem instanceof PotionItem) {
            heldHydration = 4.0F;
        }       
    }
}
