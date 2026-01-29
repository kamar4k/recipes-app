package io.kamae.family.recipes.infrastructure.web.rest.controller

import io.kamae.family.recipes.AbstractTest.Companion.TEST_RECIPE_ID
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

object RecipesControllerTestConstants {
    val LIST_RECIPES_GET_RQ = get("/v1/recipes")
    val RECIPE_GET_RQ = get("/v1/recipes/{recipeId}", TEST_RECIPE_ID.toString())
    val RECIPE_POST_RQ = MockMvcRequestBuilders.post("/v1/recipes")
}