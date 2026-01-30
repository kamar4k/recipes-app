package io.kamae.family.recipes.infrastructure.store.adapter.mapper

import io.kamae.family.recipes.domain.model.Recipe
import io.kamae.family.recipes.domain.model.RecipeShortInfo
import io.kamae.family.recipes.infrastructure.store.entity.RecipeEntity
import io.kamae.family.recipes.infrastructure.store.entity.RecipeSummaryProjection
import io.kamae.family.recipes.infrastructure.util.UniqueValueGenerator
import org.springframework.stereotype.Component

@Component
class RecipeStoreMapper(private val uniqueValueGenerator: UniqueValueGenerator) {

    fun mapEntityToDto(entity: RecipeEntity?): Recipe? = entity?.let {
        Recipe(
            it.id,
            it.title,
            parseIngredients(it.ingredients),
            it.instructions,
            it.author
        )
    }

    fun mapDtoToEntityWithGeneratedId(dto: Recipe): RecipeEntity = dto.let {
        RecipeEntity(
            uniqueValueGenerator.generateId(),
            it.title,
            serializeIngredients(it.ingredients),
            it.instructions,
            uniqueValueGenerator.currentDateTime(),
            it.author
        )
    }

    fun mapSummaryListToDto(entityList: List<RecipeSummaryProjection>): List<RecipeShortInfo> =
        entityList.map { mapSummaryToShortInfoDto(it) }

    private fun mapSummaryToShortInfoDto(entity: RecipeSummaryProjection): RecipeShortInfo = entity.let {
        RecipeShortInfo(it.getId(), it.getTitle())
    }

    fun parseIngredients(ingredientsStr: String) = ingredientsStr.split("\n")

    fun serializeIngredients(ingredients: List<String>) = ingredients.joinToString(separator = "\n")
}