package de.larmic.unittesting.rest

import com.ninjasquad.springmockk.MockkBean
import de.larmic.unittesting.database.BookRepository
import de.larmic.unittesting.rest.mapper.DtoToDomainMapper
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate
import java.time.Month

@WebMvcTest(BookRestController::class, DtoToDomainMapper::class)
internal class GoodBookRestControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean(relaxed = true)
    private lateinit var bookRepositoryMock: BookRepository

    @Test
    internal fun `store new book`() {
        this.mockMvc.postJson(json = """ 
            {
               "title": "Reinventing Organizations",
               "createDate": "18.10.2014",
               "author": {
                   "firstName": "Frederic",
                   "lastName": "Laloux"
               }
            } """
        )

        verify {
            bookRepositoryMock.store(withArg {
                assertThat(it.id).isNotNull
                assertThat(it.title).isEqualTo("Reinventing Organizations")
                assertThat(it.author.firstName).isEqualTo("Frederic")
                assertThat(it.author.lastName).isEqualTo("Laloux")
                assertThat(it.createDate).isEqualTo(LocalDate.of(2014, Month.OCTOBER, 18))
            })
        }
    }
}

private fun MockMvc.postJson(json: String) = this.perform(
    post("/api/book")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json.trimIndent()))
    .andDo(print())
    .andExpect(status().isOk)