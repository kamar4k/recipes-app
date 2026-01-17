package io.kamae.family.recipes.infrastructure.web.rest.controller

import io.kamae.family.recipes.application.port.inbound.AddRecipeUseCase
import io.kamae.family.recipes.application.port.inbound.GetRecipeUseCase
import io.kamae.family.recipes.application.port.inbound.ListRecipesUseCase
import io.kamae.family.recipes.infrastructure.web.rest.controller.mapper.RecipesControllerMapper
import io.kamae.family.recipes.infrastructure.web.rest.dto.ListRecipesRsDto
import io.kamae.family.recipes.infrastructure.web.rest.dto.PostRecipeRqDto
import io.kamae.family.recipes.infrastructure.web.rest.dto.RecipeRsDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1/recipes")
class RecipesController(
    private val listRecipesUseCase: ListRecipesUseCase,
    private val addRecipesUseCase: AddRecipeUseCase,
    private val getRecipeUseCase: GetRecipeUseCase,
    private val recipesControllerMapper: RecipesControllerMapper
) {


    @GetMapping
    fun getRecipesList(): ListRecipesRsDto {
        val listRecipes = listRecipesUseCase.getRecipeInfoList()

        val listDto = recipesControllerMapper.mapRecipeShortInfoListToRsDto(listRecipes)

        return listDto
    }

    @GetMapping("/{recipeId}")
    fun getRecipe(@PathVariable recipeId: UUID): RecipeRsDto {
        val recipe = getRecipeUseCase.getRecipeById(recipeId)

        val recipeDto = recipesControllerMapper.mapRecipeToRsDto(recipe)

        return recipeDto
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addRecipe(@RequestBody recipeRqDto: PostRecipeRqDto) {
        val recipe = recipesControllerMapper.mapRqDtoToRecipe(recipeRqDto)

        addRecipesUseCase.addRecipe(recipe)
    }
}