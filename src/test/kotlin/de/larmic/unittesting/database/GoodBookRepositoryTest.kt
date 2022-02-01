package de.larmic.unittesting.database

import de.larmic.unittesting.testcontainers.AbstractPostgreSQLTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import java.time.LocalDate
import java.time.Month
import java.util.*

@Import(BookRepository::class)
// erklären, dass für die PostgreSQL Docker verwendet
internal class GoodBookRepositoryTest : AbstractPostgreSQLTest() {

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Test
    internal fun `find by id`() {
        val id = UUID.randomUUID()
        val bookEntity = BookEntity(
            id = id,
            title = "Reinventing Organizations",
            createDate = LocalDate.of(2014, Month.OCTOBER, 18),
            authorFirstName = "Frederic",
            authorLastName = "Laloux",
        )

        bookJpaRepository.save(bookEntity)

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
        val bookEntity = BookEntity(
            id = id,
            title = "Reinventing Organizations",
            createDate = LocalDate.of(2014, Month.OCTOBER, 18),
            authorFirstName = "Frederic",
            authorLastName = "Laloux",
        )

        bookJpaRepository.save(bookEntity)

        val loadedBook = bookRepository.findByTitle(title = "Reinventing Organizations")!!

        assertThat(loadedBook.id).isEqualTo(id)
        assertThat(loadedBook.title).isEqualTo("Reinventing Organizations")
        assertThat(loadedBook.author.firstName).isEqualTo("Frederic")
        assertThat(loadedBook.author.lastName).isEqualTo("Laloux")
        assertThat(loadedBook.createDate).isEqualTo(LocalDate.of(2014, Month.OCTOBER, 18))
    }
}
