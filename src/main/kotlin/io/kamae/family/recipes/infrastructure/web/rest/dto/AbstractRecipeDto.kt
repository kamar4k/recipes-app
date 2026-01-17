package io.kamae.family.recipes.infrastructure.web.rest.dto

import java.util.UUID

abstract class AbstractRecipeDto(
    val id: UUID?,
    val title: String,
    val ingredients: List<String>,
    val instructions: String,
    val author: String?
)