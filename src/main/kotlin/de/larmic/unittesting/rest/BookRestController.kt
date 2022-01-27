package de.larmic.unittesting.rest

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class BookRestController {

    @PostMapping(value = ["/api/book"], consumes = ["application/json"])
    fun createBook(@RequestBody bookDto: BookDto) {

    }

}

data class BookDto(val title: String, val author: AuthorDto, @JsonFormat(pattern="dd.MM.yyyy") val createDate: LocalDate)
data class AuthorDto(val firstName: String, val lastName: String)
