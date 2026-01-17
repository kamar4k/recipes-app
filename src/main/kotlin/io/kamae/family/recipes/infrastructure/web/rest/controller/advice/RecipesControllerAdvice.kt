package io.kamae.family.recipes.infrastructure.web.rest.controller.advice

import io.kamae.family.recipes.domain.exception.RecipeNotFoundException
import io.kamae.family.recipes.infrastructure.web.rest.controller.RecipesController
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(assignableTypes = [RecipesController::class])
class RecipesControllerAdvice {

    @ExceptionHandler(RecipeNotFoundException::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleRecipeNotFound(ex: RecipeNotFoundException): String = ex.describe()

    @ExceptionHandler(Throwable::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleCommon(ex: Throwable) = ex.describe()

    fun Throwable.describe(): String {
        val msg = "${this.javaClass.name} ${this.message}" + (this.cause?.let { ": " + it.describe() } ?: "")

        return msg
    }
}