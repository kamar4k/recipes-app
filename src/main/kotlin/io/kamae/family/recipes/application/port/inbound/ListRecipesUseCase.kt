package io.kamae.family.recipes.application.port.inbound

import io.kamae.family.recipes.domain.model.RecipeShortInfo

interface ListRecipesUseCase {
    fun getRecipeInfoList(): List<RecipeShortInfo>
}