package de.larmic.unittesting.database

import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
class BookRepository(private val bookJpaRepository: BookJpaRepository) {

    fun findById(id: UUID) = if (bookJpaRepository.existsById(id)) {
        bookJpaRepository.getById(id).toDomain()
    } else null

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