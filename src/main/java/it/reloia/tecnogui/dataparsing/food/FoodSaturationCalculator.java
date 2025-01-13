package it.reloia.tecnogui.dataparsing.food;

import it.reloia.tecnogui.dataparsing.strings.Transliterator;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

public class FoodSaturationCalculator {
    private static final int FAVORITE_FOOD_SLOT = 4;
    
    private static final float LOVED_FOOD_SATURATION = 8F;
    private static final float LOVED_CATEGORY_SATURATION = 6F;
    private static final float DEFAULT_FOOD_SATURATION = 4F;
    private static final float INGREDIENT_SATURATION = 2F;
    
    /**
     * Calculate the saturation value based on the lore of the held item.
     *
     * @return the calculated saturation value
     */
    public static float calculateSaturation(ItemStack itemStack) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (itemStack == null || !itemStack.hasNbt()) {
            return 0F;
        }

        List<Text> lore = itemStack.getTooltip(client.player, TooltipContext.BASIC);
        if (lore.isEmpty()) {
            return 0F;
        }
        List<String> loreString = lore.stream().map(Text::getString).toList();
        
        if (client.player == null)
            return 0F;
        
        if (client.player.currentScreenHandler == null || client.player.currentScreenHandler.slots.size() < 4)
            return 0F;

        ItemStack favoriteFoodStack = client.player.currentScreenHandler.slots.get(FAVORITE_FOOD_SLOT).getStack();
        if (favoriteFoodStack.isEmpty())
            return 0F;
        List<Text> favoriteFoodLore = favoriteFoodStack.getTooltip(client.player, TooltipContext.BASIC);
        if (favoriteFoodLore.isEmpty())
            return 0F;
        List<String> favoriteFoodLoreString = favoriteFoodLore.stream().map(Text::getString).map(Transliterator::transliterate).toList();
        
        String favoriteFood = favoriteFoodLoreString.get(1).replace("CIBO PREFERITO » ", "");
        String favoriteCategory = favoriteFoodLoreString.get(2).replace("CATEGORIA PREFERITA » ", "");

        boolean isLovedFood = loreString.get(0).toLowerCase().contains(favoriteFood.toLowerCase());
        boolean isLovedCategory = loreString.get(1).toLowerCase().contains(favoriteCategory.toLowerCase());

        if (isLovedFood) {
            return LOVED_FOOD_SATURATION;
        } else if (isLovedCategory) {
            return LOVED_CATEGORY_SATURATION;
        } else if (loreString.get(1).toLowerCase().contains("ingredient")) {
            return INGREDIENT_SATURATION;
        } else {
            return DEFAULT_FOOD_SATURATION;
        }
    }
}
