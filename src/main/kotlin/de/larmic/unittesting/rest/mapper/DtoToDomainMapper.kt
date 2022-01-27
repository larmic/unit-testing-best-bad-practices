package de.larmic.unittesting.rest.mapper

import de.larmic.unittesting.database.Author
import de.larmic.unittesting.database.Book
import de.larmic.unittesting.rest.AuthorDto
import de.larmic.unittesting.rest.BookDto
import org.springframework.stereotype.Service

@Service
class DtoToDomainMapper {

    fun map(dto: BookDto) = Book(title = dto.title, createDate = dto.createDate, author = dto.author.toDomain())

}

private fun AuthorDto.toDomain() = Author(firstName = this.firstName, lastName = this.lastName)
