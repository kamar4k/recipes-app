package io.kamae.family.recipes

import io.kamae.family.recipes.domain.model.Recipe
import io.kamae.family.recipes.domain.model.RecipeShortInfo
import io.mockk.clearAllMocks
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.TestInstance
import org.springframework.test.context.ActiveProfiles
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
abstract class AbstractTest {
    companion object {
        const val TEST_AUTHOR: String = "user"
        val TEST_RECIPE_ID: UUID = UUID.fromString("e07446c5-b71b-4c48-a483-1f8eefd80f6e")
        const val TEST_RECIPE_TITLE = "Recipe Title"
        const val TEST_RECIPE_INSTRUCTIONS = "Step1\nStep2\nStep3\nStep4"
        private val TEST_RECIPE_INGREDIENTS = listOf("ingridient1", "ingridient2 3", "ing3 200g")
        val TEST_ANOTHER_RECIPE_ID: UUID = UUID.fromString("2be1cc47-3b78-422c-b388-44b8be04eab1")
        const val TEST_ANOTHER_RECIPE_TITLE = "Another Recipe Title"

        val TEST_RECIPE = Recipe(
            null,
            TEST_RECIPE_TITLE,
            TEST_RECIPE_INGREDIENTS,
            TEST_RECIPE_INSTRUCTIONS,
            TEST_AUTHOR
        )

        val TEST_RECIPE_WITH_ID = TEST_RECIPE.copy(id = TEST_RECIPE_ID)

        val TEST_RECIPE_SHORT_INFO_LIST = listOf(
            RecipeShortInfo(TEST_RECIPE_ID, TEST_RECIPE_TITLE),
            RecipeShortInfo(TEST_ANOTHER_RECIPE_ID, TEST_ANOTHER_RECIPE_TITLE)
        )
    }

    @AfterEach
    fun clearMocks() {
        clearAllMocks()
    }

    protected fun getTestResourcesAsString(fileName: String): String {
        return TestUtils.getTestResourcesAsString(this.javaClass, fileName)
    }
}