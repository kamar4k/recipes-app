package io.kamae.family.recipes.application.port.outbound

import io.kamae.family.recipes.domain.model.Recipe
import io.kamae.family.recipes.domain.model.RecipeShortInfo
import java.util.UUID

interface RecipeRepositoryPort {
    fun getRecipeInfoList(): List<RecipeShortInfo>

    fun getRecipeById(id: UUID): Recipe?

    fun saveRecipe(recipe: Recipe): Recipe
}