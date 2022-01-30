package de.larmic.unittesting.database

import de.larmic.unittesting.testcontainers.AbstractPostgreSQLTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month
import java.util.*

class BookJpaRepositoryTest : AbstractPostgreSQLTest() {

    @Test
    internal fun `verify testcontainers and spring data is correctly running`() {
        val id = UUID.randomUUID()
        val bookEntity = BookEntity(
            id = id,
            title = "Reinventing Organizations",
            createDate = LocalDate.of(2014, Month.OCTOBER, 18),
            authorFirstName = "Frederic",
            authorLastName = "Laloux",
        )

        bookJpaRepository.save(bookEntity)

        val loadedEntity = bookJpaRepository.findById(id).get()

        assertThat(loadedEntity.id).isEqualTo(id)
        assertThat(loadedEntity.title).isEqualTo("Reinventing Organizations")
        assertThat(loadedEntity.authorFirstName).isEqualTo("Frederic")
        assertThat(loadedEntity.authorLastName).isEqualTo("Laloux")
        assertThat(loadedEntity.createDate).isEqualTo(LocalDate.of(2014, Month.OCTOBER, 18))
    }
}