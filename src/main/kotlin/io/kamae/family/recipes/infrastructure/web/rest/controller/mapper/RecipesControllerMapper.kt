package io.kamae.family.recipes.infrastructure.web.rest.controller.mapper

import io.kamae.family.recipes.domain.model.Recipe
import io.kamae.family.recipes.domain.model.RecipeShortInfo
import io.kamae.family.recipes.infrastructure.web.rest.dto.ListRecipesRsDto
import io.kamae.family.recipes.infrastructure.web.rest.dto.PostRecipeRqDto
import io.kamae.family.recipes.infrastructure.web.rest.dto.RecipeRsDto
import io.kamae.family.recipes.infrastructure.web.rest.dto.RecipeShortInfoDto
import org.springframework.stereotype.Component

@Component
class RecipesControllerMapper {

    fun mapRqDtoToRecipe(postRecipeRqDto: PostRecipeRqDto): Recipe = postRecipeRqDto.let {
        Recipe(
            it.id,
            it.title,
            it.ingredients,
            it.instructions,
            it.author
        )
    }

    fun mapRecipeToRsDto(recipe: Recipe): RecipeRsDto = recipe.let {
        RecipeRsDto(
            it.id!!,
            it.title,
            it.ingredients,
            it.instructions,
            it.author
        )
    }

    fun mapRecipeShortInfoListToRsDto(recipeShortInfoList: List<RecipeShortInfo>): ListRecipesRsDto =
        ListRecipesRsDto(data = mapRecipeShortInfoListToListRecipesRsDto(recipeShortInfoList))

    private fun mapRecipeShortInfoListToListRecipesRsDto(recipeShortInfoList: List<RecipeShortInfo>): List<RecipeShortInfoDto> =
        recipeShortInfoList.map { mapRecipeShortInfoToRsDto(it) }

    private fun mapRecipeShortInfoToRsDto(recipeShortInfo: RecipeShortInfo): RecipeShortInfoDto = recipeShortInfo.let {
        RecipeShortInfoDto(
            it.id,
            it.title
        )
    }
}