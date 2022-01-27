package de.larmic.unittesting.rest

import de.larmic.unittesting.database.BookRepository
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month

internal class BadBookRestControllerTest {

    private val bookRepositoryMock = mockk<BookRepository>(relaxed = true)
    private val controller = BookRestController(bookRepository = bookRepositoryMock)

    @Test
    internal fun `store new book`() {
        val bookDto = BookDto(
            title = "Reinventing Organizations",
            author = AuthorDto(firstName = "Frederic", lastName = "Laloux"),
            createDate = LocalDate.of(2014, Month.OCTOBER, 18),
        )

        controller.createBook(bookDto)

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