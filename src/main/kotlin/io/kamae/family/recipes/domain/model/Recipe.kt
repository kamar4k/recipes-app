package io.kamae.family.recipes.domain.model

import java.util.UUID

data class Recipe(
    val id: UUID?,
    val title: String,
    val ingredients: List<String>,
    val instructions: String,
    val author: String?
)