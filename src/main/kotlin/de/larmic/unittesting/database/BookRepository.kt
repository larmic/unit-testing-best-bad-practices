package de.larmic.unittesting.database

import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

// Beispiel für "Mocking ist 💩"
// 1. Siehe BadBookRepositoryTest.kt
// 2. Siehe GoodBookRepositoryTest.kt
//
// Warum ist nutzen wir kein IT? -> Messbarkeit (Testabdeckung)
// Was ist die Aufgabe dieses Repositories? Speichern in die DB!
@Repository
class BookRepository(private val bookJpaRepository: BookJpaRepository) {

    fun findById(id: UUID): Book? {
        return if (bookJpaRepository.existsById(id)) {
            bookJpaRepository.getById(id).toDomain()
        } else null
    }

    // ❤️ alternative findById()-implementation
    //fun findById(id: UUID) = bookJpaRepository.findById(id).orElseGet { null }.toDomain()

    fun findByTitle(title: String) = bookJpaRepository.findByTitle(title).toDomain()

    fun store(book: Book) {
        TODO("not implemented. just used by BookRestController.kt example")
    }

}

data class Book(val id: UUID = UUID.randomUUID(), val title: String, val author: Author, val createDate: LocalDate)
data class Author(val firstName: String, val lastName: String)

private fun BookEntity?.toDomain() = if (this == null) null
else Book(id = this.id, title = this.title, createDate = this.createDate, author = this.toAuthor())

private fun BookEntity.toAuthor() = Author(firstName = this.authorFirstName, lastName = this.authorLastName)