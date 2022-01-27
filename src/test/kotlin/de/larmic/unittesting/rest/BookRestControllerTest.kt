package de.larmic.unittesting.rest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(BookRestController::class)
internal class BookRestControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    internal fun `store new book`() {
        this.mockMvc.postJson(
            url = "/api/book",
            json = """ 
            {
               "title": "Reinventing Organizations",
               "createDate": "18.10.2014",
               "author": {
                   "firstName": "Frederic",
                   "lastName": "Laloux"
               }
            } """
        )
    }
}

private fun MockMvc.postJson(url: String, json: String) = this.perform(
    post("/api/book")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json.trimIndent()))
    .andDo(print())
    .andExpect(status().isOk)