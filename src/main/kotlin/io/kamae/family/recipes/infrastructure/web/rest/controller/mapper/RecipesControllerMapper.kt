package io.kamae.family.recipes.infrastructure.web.rest.controller.mapper

import io.kamae.family.recipes.domain.model.Recipe
import io.kamae.family.recipes.domain.model.RecipeShortInfo
import io.kamae.family.recipes.infrastructure.web.rest.dto.RecipeRsDto
import io.kamae.family.recipes.infrastructure.web.rest.dto.ListRecipesRsDto
import io.kamae.family.recipes.infrastructure.web.rest.dto.PostRecipeRqDto
import io.kamae.family.recipes.infrastructure.web.rest.dto.RecipeShortInfoDto
import org.mapstruct.*

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
abstract class RecipesControllerMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mappings(
        Mapping(target = "id"),
        Mapping(target = "title"),
        Mapping(target = "ingredients"),
        Mapping(target = "instructions"),
        Mapping(target = "author")
    )
    abstract fun mapRqDtoToRecipe(postRecipeRqDto: PostRecipeRqDto): Recipe

    @BeanMapping(ignoreByDefault = true)
    @Mappings(
        Mapping(target = "id"),
        Mapping(target = "title"),
        Mapping(target = "ingredients"),
        Mapping(target = "instructions"),
        Mapping(target = "author")
    )
    abstract fun mapRecipeToRsDto(recipe: Recipe): RecipeRsDto

    fun mapRecipeShortInfoListToRsDto(recipeShortInfoList: List<RecipeShortInfo>): ListRecipesRsDto =
        ListRecipesRsDto(data = mapRecipeShortInfoListToListRecipesRsDto(recipeShortInfoList))

    abstract fun mapRecipeShortInfoListToListRecipesRsDto(recipeShortInfoList: List<RecipeShortInfo>): List<RecipeShortInfoDto>

    @BeanMapping(ignoreByDefault = true)
    @Mappings(
        Mapping(target = "id"),
        Mapping(target = "title")
    )
    abstract fun mapRecipeShortInfoToRsDto(recipeShortInfo: RecipeShortInfo): RecipeShortInfoDto
}