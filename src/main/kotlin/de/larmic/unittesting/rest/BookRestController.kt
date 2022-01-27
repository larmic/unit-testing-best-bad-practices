package de.larmic.unittesting.rest

import com.fasterxml.jackson.annotation.JsonFormat
import de.larmic.unittesting.database.Author
import de.larmic.unittesting.database.Book
import de.larmic.unittesting.database.BookRepository
import de.larmic.unittesting.rest.mapper.DtoToDomainMapper
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

// Beispiel für "Tested eure Frameworks"

// wie das mapping geschieht ist whitebox?!
// beispiel mit mocking framework injecten (z.B. mapstruct oder selbst schreiben) -> dann ein Integrationstests?
@RestController
class BookRestController(private val dtoToDomainMapper: DtoToDomainMapper, private val bookRepository: BookRepository) {

    @PostMapping(value = ["/api/book"], consumes = ["application/json"])
    fun createBook(@RequestBody bookDto: BookDto) {
        val book = dtoToDomainMapper.map(bookDto)
        bookRepository.store(book)
    }

}

data class BookDto(val title: String, val author: AuthorDto, @JsonFormat(pattern="dd.MM.yyyy") val createDate: LocalDate)
data class AuthorDto(val firstName: String, val lastName: String)