package de.larmic.unittesting.database

import de.larmic.unittesting.testcontainers.AbstractPostgreSQLTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.testcontainers.shaded.org.bouncycastle.asn1.x500.style.RFC4519Style.title
import java.time.LocalDate
import java.time.Month
import java.util.*

@Import(BookRepository::class)
internal class GoodBookRepositoryTest : AbstractPostgreSQLTest() {

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Test
    internal fun `find by id`() {
        val id = bookJpaRepository.save(
            BookEntity(
                title = "Reinventing Organizations",
                createDate = LocalDate.of(2014, Month.OCTOBER, 18),
                authorFirstName = "Frederic",
                authorLastName = "Laloux",
            )
        ).id

        val loadedBook = bookRepository.findById(id)!!

        assertThat(loadedBook.id).isEqualTo(id)
        assertThat(loadedBook.title).isEqualTo("Reinventing Organizations")
        assertThat(loadedBook.author.firstName).isEqualTo("Frederic")
        assertThat(loadedBook.author.lastName).isEqualTo("Laloux")
        assertThat(loadedBook.createDate).isEqualTo(LocalDate.of(2014, Month.OCTOBER, 18))
    }

    @Test
    internal fun `find by title`() {
        val id = bookJpaRepository.save(
            BookEntity(
                title = "Reinventing Organizations",
                createDate = LocalDate.of(2014, Month.OCTOBER, 18),
                authorFirstName = "Frederic",
                authorLastName = "Laloux",
            )
        ).id

        val loadedBook = bookRepository.findByTitle(title = "Reinventing Organizations")!!

        assertThat(loadedBook.id).isEqualTo(id)
        assertThat(loadedBook.title).isEqualTo("Reinventing Organizations")
        assertThat(loadedBook.author.firstName).isEqualTo("Frederic")
        assertThat(loadedBook.author.lastName).isEqualTo("Laloux")
        assertThat(loadedBook.createDate).isEqualTo(LocalDate.of(2014, Month.OCTOBER, 18))
    }
}