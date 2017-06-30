package betterwithmods.module.compat.jei.wrapper;

import betterwithmods.common.registry.steelanvil.SteelShapedRecipe;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class SteelShapedRecipeWrapper extends BlankRecipeWrapper implements IShapedCraftingRecipeWrapper {

    private final SteelShapedRecipe recipe;
    private final IJeiHelpers jeiHelpers;
    private int width, height;

    public SteelShapedRecipeWrapper(IJeiHelpers jeiHelpers, SteelShapedRecipe recipe) {
        this.jeiHelpers = jeiHelpers;
        this.recipe = recipe;
        for (Object obj : this.recipe.getInput()) {
            if (obj instanceof ItemStack) {
                ItemStack itemStack = (ItemStack) obj;
                if (!itemStack.isEmpty() && itemStack.getCount() != 1) {
                    itemStack.setCount(1);
                }
            }
        }
        width = recipe.width;
        height = recipe.height;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        IStackHelper stackHelper = jeiHelpers.getStackHelper();
        ItemStack recipeOutput = recipe.getRecipeOutput();

        List<List<ItemStack>> inputs = stackHelper.expandRecipeItemStackInputs(Arrays.asList(recipe.getInput()));

        ingredients.setInputLists(ItemStack.class, inputs);
        ingredients.setOutput(ItemStack.class, recipeOutput);

    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}