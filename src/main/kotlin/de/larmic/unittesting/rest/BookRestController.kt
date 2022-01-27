package de.larmic.unittesting.rest

import com.fasterxml.jackson.annotation.JsonFormat
import de.larmic.unittesting.database.Author
import de.larmic.unittesting.database.Book
import de.larmic.unittesting.database.BookRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class BookRestController(private val bookRepository: BookRepository) {

    @PostMapping(value = ["/api/book"], consumes = ["application/json"])
    fun createBook(@RequestBody bookDto: BookDto) {
        bookRepository.store(bookDto.toDomain())
    }

}

data class BookDto(val title: String, val author: AuthorDto, @JsonFormat(pattern="dd.MM.yyyy") val createDate: LocalDate)
data class AuthorDto(val firstName: String, val lastName: String)

private fun BookDto.toDomain() = Book(title = this.title, createDate = this.createDate, author = this.author.toDomain())
private fun AuthorDto.toDomain() = Author(firstName = this.firstName, lastName = this.lastName)
