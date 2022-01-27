package de.larmic.unittesting.database

import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
class BookRepository {

    fun store(book: Book) {

    }

}

data class Book(val id: UUID = UUID.randomUUID(), val title: String, val author: Author, val createDate: LocalDate)
data class Author(val firstName: String, val lastName: String)