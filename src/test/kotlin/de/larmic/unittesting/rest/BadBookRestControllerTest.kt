package de.larmic.unittesting.rest

import de.larmic.unittesting.database.Author
import de.larmic.unittesting.database.Book
import de.larmic.unittesting.database.BookRepository
import de.larmic.unittesting.rest.mapper.DtoToDomainMapper
import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month

// 1. Kein IT bedeutet "keine Abhängigkeiten" bedeutet "nur mocking ist erlaubt"
//      -> aufwendiges Mocking
//      -> schlechte Lesbarkeit
//
// 2. dtoToDomainMapperMock durch echte Implementierung ersetzen
//      -> weniger Whitebox-Testing
//      -> bessere Lesbarkeit
//      -> Warum ist es kein Unit-Test mehr? Zu dogmatisch?
internal class BadBookRestControllerTest {

    private val dtoToDomainMapperMock = mockk<DtoToDomainMapper>()
    private val bookRepositoryMock = mockk<BookRepository>(relaxed = true)
    private val controller = BookRestController(dtoToDomainMapper = dtoToDomainMapperMock, bookRepository = bookRepositoryMock)

    @Test
    internal fun `store new book`() {
        val bookDto = BookDto(
            title = "Reinventing Organizations",
            author = AuthorDto(firstName = "Frederic", lastName = "Laloux"),
            createDate = LocalDate.of(2014, Month.OCTOBER, 18),
        )

        val dtoSlot = slot<BookDto>()

        every { dtoToDomainMapperMock.map(capture(dtoSlot)) } answers {
            val capturedDto = dtoSlot.captured
            Book(
                title = capturedDto.title,
                author = Author(firstName = capturedDto.author.firstName, lastName = capturedDto.author.lastName),
                createDate = capturedDto.createDate
            )
        }

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