package io.kamae.family.recipes.infrastructure.store.adapter.mapper

import io.kamae.family.recipes.domain.model.Recipe
import io.kamae.family.recipes.domain.model.RecipeShortInfo
import io.kamae.family.recipes.infrastructure.store.entity.RecipeEntity
import io.kamae.family.recipes.infrastructure.store.entity.RecipeSummaryProjection
import io.kamae.family.recipes.infrastructure.util.UniqueValueGenerator
import org.mapstruct.*
import org.mapstruct.MappingConstants.ComponentModel
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import java.util.*

@Mapper(componentModel = ComponentModel.SPRING)
abstract class RecipeStoreMapper {

    @Autowired
    private lateinit var uniqueValueGenerator: UniqueValueGenerator

    @BeanMapping(ignoreByDefault = true, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mappings(
        Mapping(target = "id"),
        Mapping(target = "title"),
        Mapping(target = "ingredients", qualifiedByName = ["parseIngredients"]),
        Mapping(target = "instructions"),
        Mapping(target = "author")
    )
    abstract fun mapEntityToDto(entity: RecipeEntity?): Recipe

    @BeanMapping(ignoreByDefault = true)
    @Mappings(
        Mapping(target = "id", expression = "java(generateUUID())"),
        Mapping(target = "title"),
        Mapping(target = "ingredients", qualifiedByName = ["serializeIngredients"]),
        Mapping(target = "instructions"),
        Mapping(target = "createdAt", expression = "java(currentDateTime())"),
        Mapping(target = "author")
    )
    abstract fun mapDtoToEntityWithGeneratedId(dto: Recipe): RecipeEntity

    abstract fun mapSummaryListToDto(entityList: List<RecipeSummaryProjection>): List<RecipeShortInfo>

    @BeanMapping(ignoreByDefault = true)
    @Mappings(
        Mapping(target = "id"),
        Mapping(target = "title"),
    )
    abstract fun mapSummaryToShortInfoDto(entity: RecipeSummaryProjection): RecipeShortInfo

    protected fun generateUUID(): UUID = uniqueValueGenerator.generateId()

    protected fun currentDateTime(): LocalDateTime = uniqueValueGenerator.currentDateTime()

    @Named("parseIngredients")
    fun parseIngredients(ingredientsStr: String) = ingredientsStr.split("\n")

    @Named("serializeIngredients")
    fun serializeIngredients(ingredients: List<String>) = ingredients.joinToString(separator = "\n")
}