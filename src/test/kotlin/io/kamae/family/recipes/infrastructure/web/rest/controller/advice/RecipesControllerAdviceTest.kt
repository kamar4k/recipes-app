package io.kamae.family.recipes.infrastructure.web.rest.controller.advice

import io.kamae.family.recipes.AbstractControllerTest
import io.kamae.family.recipes.domain.exception.RecipeNotFoundException
import io.kamae.family.recipes.infrastructure.web.rest.controller.RecipesControllerTestConstants
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class RecipesControllerAdviceTest : AbstractControllerTest() {

    companion object {
        private val COMMON_EXCEPTION = IllegalStateException("common exception")
        private val COMMON_EXCEPTION_WRAPPER = RuntimeException("wrapper exception", COMMON_EXCEPTION)
        private val NOT_FOUND_EXCEPTION = RecipeNotFoundException(TEST_RECIPE_ID.toString())
    }

    @Test
    fun getRecipesList_commonError() {
        every { listRecipesUseCase.getRecipeInfoList() } throws COMMON_EXCEPTION

        mockMvc.perform(RecipesControllerTestConstants.LIST_RECIPES_GET_RQ)
            .andExpect(
                status().isInternalServerError()
            ).andExpect(
                content().string("${COMMON_EXCEPTION.javaClass.name} ${COMMON_EXCEPTION.message}")
            )

        verify { listRecipesUseCase.getRecipeInfoList() }
    }

    @Test
    fun getRecipe_wrappedError() {
        every { getRecipeUseCase.getRecipeById(TEST_RECIPE_ID) } throws COMMON_EXCEPTION_WRAPPER

        mockMvc.perform(RecipesControllerTestConstants.RECIPE_GET_RQ).andExpect(
            status().isInternalServerError
        ).andExpect(
            content().string(
                "${COMMON_EXCEPTION_WRAPPER.javaClass.name} ${COMMON_EXCEPTION_WRAPPER.message}: " +
                        "${COMMON_EXCEPTION.javaClass.name} ${COMMON_EXCEPTION.message}"
            )
        )

        verify { getRecipeUseCase.getRecipeById(TEST_RECIPE_ID) }
    }

    @Test
    fun getRecipe_notFoundError() {
        every { getRecipeUseCase.getRecipeById(TEST_RECIPE_ID) } throws NOT_FOUND_EXCEPTION

        mockMvc.perform(RecipesControllerTestConstants.RECIPE_GET_RQ).andExpect(
            status().isNotFound
        ).andExpect(
            content().string(
                "${NOT_FOUND_EXCEPTION.javaClass.name} ${NOT_FOUND_EXCEPTION.message}"
            )
        )

        verify { getRecipeUseCase.getRecipeById(TEST_RECIPE_ID) }
    }

    @Test
    fun addRecipe_commonError() {
        every { addRecipesUseCase.addRecipe(any()) } throws COMMON_EXCEPTION

        mockMvc.perform(
            RecipesControllerTestConstants.RECIPE_POST_RQ
                .content(getTestResourcesAsString("recipeRq.json"))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            status().isInternalServerError
        ).andExpect(
            content().string("${COMMON_EXCEPTION.javaClass.name} ${COMMON_EXCEPTION.message}")
        )

        verify { addRecipesUseCase.addRecipe(TEST_RECIPE) }
    }
}