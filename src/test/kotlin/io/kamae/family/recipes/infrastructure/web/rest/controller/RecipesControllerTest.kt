package io.kamae.family.recipes.infrastructure.web.rest.controller

import io.kamae.family.recipes.AbstractControllerTest
import io.kamae.family.recipes.infrastructure.web.rest.controller.RecipesControllerTestConstants.LIST_RECIPES_GET_RQ
import io.kamae.family.recipes.infrastructure.web.rest.controller.RecipesControllerTestConstants.RECIPE_GET_RQ
import io.kamae.family.recipes.infrastructure.web.rest.controller.RecipesControllerTestConstants.RECIPE_POST_RQ
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class RecipesControllerTest : AbstractControllerTest() {

    @Test
    fun getRecipesList_success() {
        every { listRecipesUseCase.getRecipeInfoList() } returns TEST_RECIPE_SHORT_INFO_LIST

        mockMvc.perform(LIST_RECIPES_GET_RQ)
            .andExpect(
                status().isOk()
            ).andExpect(
                content().json(getTestResourcesAsString("getRecipesListRs.json"))
            )

        verify { listRecipesUseCase.getRecipeInfoList() }
    }

    @Test
    fun getRecipe_success() {
        every { getRecipeUseCase.getRecipeById(TEST_RECIPE_ID) } returns TEST_RECIPE_WITH_ID

        mockMvc.perform(RECIPE_GET_RQ).andExpect(
            status().isOk
        ).andExpect(
            content().json(getTestResourcesAsString("recipeRs.json"))
        )

        verify { getRecipeUseCase.getRecipeById(TEST_RECIPE_ID) }
    }

    @Test
    fun addRecipe_success() {
        every { addRecipesUseCase.addRecipe(any()) } returns TEST_RECIPE_WITH_ID

        mockMvc.perform(
            RECIPE_POST_RQ
                .content(getTestResourcesAsString("recipeRq.json"))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            status().isCreated
        )

        verify { addRecipesUseCase.addRecipe(TEST_RECIPE) }
    }
}