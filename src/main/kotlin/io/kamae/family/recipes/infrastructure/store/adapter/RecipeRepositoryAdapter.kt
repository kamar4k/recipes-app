package io.kamae.family.recipes.infrastructure.store.adapter

import io.kamae.family.recipes.domain.model.Recipe
import io.kamae.family.recipes.domain.model.RecipeShortInfo
import io.kamae.family.recipes.application.port.outbound.RecipeRepositoryPort
import io.kamae.family.recipes.infrastructure.store.adapter.mapper.RecipeStoreMapper
import io.kamae.family.recipes.infrastructure.store.repository.RecipeJpaRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Component
class RecipeRepositoryAdapter(
    private val recipeJpaRepository: RecipeJpaRepository,
    private val recipeStoreMapper: RecipeStoreMapper
) : RecipeRepositoryPort {
    override fun getRecipeInfoList(): List<RecipeShortInfo> {
        val resultList = recipeJpaRepository.findAllSummary()

        return recipeStoreMapper.mapSummaryListToDto(resultList)
    }

    override fun getRecipeById(id: UUID): Recipe? {
        val result = recipeJpaRepository.findById(id).getOrNull()

        return recipeStoreMapper.mapEntityToDto(result)
    }

    @Transactional
    override fun saveRecipe(recipe: Recipe): Recipe {
        val entity = recipeStoreMapper.mapDtoToEntityWithGeneratedId(recipe)
        val saved = recipeJpaRepository.save(entity)

        return recipe.copy(id = saved.id)
    }
}