package net.toasteropera.algaedelight.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.toasteropera.algaedelight.item.ModItems;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

import java.util.concurrent.CompletableFuture;

public class FarmersDelightRecipes extends RecipeProvider {
    public FarmersDelightRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        //Result, Count, Time, XP, Container
        //      .addIngredient()
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.ALGAE_NOODLES.get(), 1, 200, 1.0F, Items.BOWL)
                .addIngredient(ModItems.RAW_ALGAE_NOODLES.get());
    }
}
