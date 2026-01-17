package io.kamae.family.recipes.application.service


import io.kamae.family.recipes.application.port.outbound.RecipeRepositoryPort
import io.kamae.family.recipes.domain.exception.RecipeNotFoundException
import io.kamae.family.recipes.domain.model.Recipe
import io.kamae.family.recipes.domain.model.RecipeShortInfo
import io.kamae.family.recipes.application.port.inbound.AddRecipeUseCase
import io.kamae.family.recipes.application.port.inbound.GetRecipeUseCase
import io.kamae.family.recipes.application.port.inbound.ListRecipesUseCase
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RecipeService(
    private val recipeRepositoryPort: RecipeRepositoryPort
): AddRecipeUseCase, GetRecipeUseCase, ListRecipesUseCase {
    override fun addRecipe(recipe: Recipe): Recipe {
       return recipeRepositoryPort.saveRecipe(recipe)
    }

    override fun getRecipeById(recipeId: UUID): Recipe {
        return recipeRepositoryPort.getRecipeById(recipeId)?: throw RecipeNotFoundException(recipeId.toString())
    }

    override fun getRecipeInfoList(): List<RecipeShortInfo> {
        return recipeRepositoryPort.getRecipeInfoList()
    }
}