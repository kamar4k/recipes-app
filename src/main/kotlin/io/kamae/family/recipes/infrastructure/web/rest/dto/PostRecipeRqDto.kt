package io.kamae.family.recipes.infrastructure.web.rest.dto

import java.util.*


class PostRecipeRqDto(
    id: UUID?,
    title: String,
    ingredients: List<String>,
    instructions: String,
    author: String?
): AbstractRecipeDto(
    id, title, ingredients, instructions, author
)