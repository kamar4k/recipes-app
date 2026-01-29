package io.kamae.family.recipes.application.service

import com.ninjasquad.springmockk.MockkBean
import io.kamae.family.recipes.AbstractIntegrationTest
import io.kamae.family.recipes.application.port.outbound.RecipeRepositoryPort
import io.kamae.family.recipes.domain.exception.RecipeNotFoundException
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals

class RecipeServiceTest : AbstractIntegrationTest() {
    @Autowired
    private lateinit var recipeService: RecipeService

    @MockkBean
    private lateinit var recipeRepositoryPort: RecipeRepositoryPort

    @Test
    fun addRecipe_success() {
        every { recipeRepositoryPort.saveRecipe(TEST_RECIPE) } returns TEST_RECIPE_WITH_ID

        val result = recipeService.addRecipe(TEST_RECIPE)

        verify { recipeRepositoryPort.saveRecipe(TEST_RECIPE) }
        assertEquals(TEST_RECIPE_WITH_ID, result)
    }

    @Test
    fun getRecipeById_success() {
        every { recipeRepositoryPort.getRecipeById(TEST_RECIPE_ID) } returns TEST_RECIPE_WITH_ID

        val result = recipeService.getRecipeById(TEST_RECIPE_ID)

        verify { recipeRepositoryPort.getRecipeById(TEST_RECIPE_ID) }
        assertEquals(TEST_RECIPE_WITH_ID, result)
    }

    @Test
    fun getRecipeById_notFound() {
        every { recipeRepositoryPort.getRecipeById(TEST_RECIPE_ID) } returns null

        val error = assertThrows<RecipeNotFoundException> { recipeService.getRecipeById(TEST_RECIPE_ID) }

        verify { recipeRepositoryPort.getRecipeById(TEST_RECIPE_ID) }
        assertEquals("Рецепт с идентификатором '$TEST_RECIPE_ID' не найден", error.message)
    }

    @Test
    fun getRecipeInfoList_success() {
        every { recipeRepositoryPort.getRecipeInfoList() } returns TEST_RECIPE_SHORT_INFO_LIST

        val result = recipeService.getRecipeInfoList()

        verify { recipeRepositoryPort.getRecipeInfoList() }

        assertEquals(TEST_RECIPE_SHORT_INFO_LIST, result)
    }
}