package io.kamae.family.recipes

import com.ninjasquad.springmockk.MockkBean
import io.kamae.family.recipes.application.port.inbound.AddRecipeUseCase
import io.kamae.family.recipes.application.port.inbound.GetRecipeUseCase
import io.kamae.family.recipes.application.port.inbound.ListRecipesUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc

@EnableAutoConfiguration
@AutoConfigureMockMvc
abstract class AbstractControllerTest: AbstractIntegrationTest() {
    @Autowired
    protected lateinit var mockMvc: MockMvc

    @MockkBean
    protected lateinit var listRecipesUseCase: ListRecipesUseCase
    @MockkBean
    protected lateinit var addRecipesUseCase: AddRecipeUseCase
    @MockkBean
    protected lateinit var getRecipeUseCase: GetRecipeUseCase
}