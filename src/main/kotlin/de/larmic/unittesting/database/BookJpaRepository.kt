package de.larmic.unittesting.database

import jakarta.persistence.Column
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Repository
interface BookJpaRepository : JpaRepository<BookEntity, UUID> {

    @Query(value = "SELECT b FROM book b WHERE b.title = ?1 LIMIT 1", nativeQuery = true)
    fun findByTitle(title: String) : BookEntity?

}

@Entity
@Table(name = "book")
data class BookEntity(
    @Id val id: UUID,
    @Column(name = "title", nullable = false) val title: String,
    @Column(name = "create_date", nullable = false) val createDate: LocalDate,
    @Column(name = "author_first_name", nullable = false) val authorFirstName: String,
    @Column(name = "author_last_name", nullable = false) val authorLastName: String,
)