package io.kamae.family.recipes.application.port.inbound

import io.kamae.family.recipes.domain.model.Recipe
import java.util.UUID

interface GetRecipeUseCase {
    fun getRecipeById(recipeId: UUID): Recipe
}