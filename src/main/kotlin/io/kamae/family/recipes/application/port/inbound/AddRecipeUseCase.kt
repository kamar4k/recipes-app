package io.kamae.family.recipes.application.port.inbound

import io.kamae.family.recipes.domain.model.Recipe

interface AddRecipeUseCase {
    fun addRecipe(recipe: Recipe): Recipe
}