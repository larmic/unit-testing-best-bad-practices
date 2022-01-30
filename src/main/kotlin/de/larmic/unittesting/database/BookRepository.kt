package de.larmic.unittesting.database

import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

// Warum ist nutzen wir kein IT? -> Messbarkeit (Testabdeckung)
// TODO replace it by entity manager -> mock test will fail
// TODO h2 vs postgres -> find a breaking change
@Repository
class BookRepository(private val bookJpaRepository: BookJpaRepository) {

    fun findById(id: UUID): Book? {
        return if (bookJpaRepository.existsById(id)) {
            bookJpaRepository.getById(id).toDomain()
        } else null
    }

    // TODO findByLastName(...) : List<Book>

    fun store(book: Book) {
        TODO("implement me")
    }

}

data class Book(val id: UUID = UUID.randomUUID(), val title: String, val author: Author, val createDate: LocalDate)
data class Author(val firstName: String, val lastName: String)

private fun Book.toEntity() = BookEntity(
    id = this.id,
    title = this.title,
    createDate = this.createDate,
    authorFirstName = this.author.firstName,
    authorLastName = this.author.lastName
)

private fun BookEntity.toDomain() =
    Book(id = this.id, title = this.title, createDate = this.createDate, author = this.toAuthor())

private fun BookEntity.toAuthor() = Author(firstName = this.authorFirstName, lastName = this.authorLastName)