package betterwithmods.integration.jei.wrapper;

import betterwithmods.craft.TurntableInteraction;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

/**
 * Purpose:
 *
 * @author Tyler Marshall
 * @version 11/11/16
 */
public class TurntableRecipeWrapper extends BlockMetaWrapper {
    private ItemStack result;
    public TurntableRecipeWrapper(TurntableInteraction.TurntableRecipe recipe) {
        super(recipe);
        result = recipe.getResult();
        outputs.add(0,result);
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(ItemStack.class, input);
        ingredients.setOutputs(ItemStack.class,outputs);
    }
}