package io.kamae.family.recipes.domain.model

import java.util.UUID

data class RecipeShortInfo(
    val id: UUID,
    val title: String
)