package de.larmic.unittesting.database

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month
import java.util.*

internal class BadBookRepositoryTest {

    private val bookJpaRepositoryMock = mockk<BookJpaRepository>()
    private val bookRepository = BookRepository(bookJpaRepository = bookJpaRepositoryMock)

    @Test
    internal fun `find by id`() {
        val id = UUID.randomUUID()

        every { bookJpaRepositoryMock.existsById(id) } returns true
        every { bookJpaRepositoryMock.getById(id) } returns BookEntity(
            id = id,
            title = "Reinventing Organizations",
            createDate = LocalDate.of(2014, Month.OCTOBER, 18),
            authorFirstName = "Frederic",
            authorLastName = "Laloux",
        )

        val loadedBook = bookRepository.findById(id)!!

        assertThat(loadedBook.id).isEqualTo(id)
        assertThat(loadedBook.title).isEqualTo("Reinventing Organizations")
        assertThat(loadedBook.author.firstName).isEqualTo("Frederic")
        assertThat(loadedBook.author.lastName).isEqualTo("Laloux")
        assertThat(loadedBook.createDate).isEqualTo(LocalDate.of(2014, Month.OCTOBER, 18))
    }

    @Test
    internal fun `find by title`() {
        val id = UUID.randomUUID()

        every { bookJpaRepositoryMock.findByTitle(title = "Reinventing Organizations") } returns BookEntity(
            id = id,
            title = "Reinventing Organizations",
            createDate = LocalDate.of(2014, Month.OCTOBER, 18),
            authorFirstName = "Frederic",
            authorLastName = "Laloux",
        )

        val loadedBook = bookRepository.findByTitle(title = "Reinventing Organizations")!!

        assertThat(loadedBook.id).isEqualTo(id)
        assertThat(loadedBook.title).isEqualTo("Reinventing Organizations")
        assertThat(loadedBook.author.firstName).isEqualTo("Frederic")
        assertThat(loadedBook.author.lastName).isEqualTo("Laloux")
        assertThat(loadedBook.createDate).isEqualTo(LocalDate.of(2014, Month.OCTOBER, 18))
    }
}