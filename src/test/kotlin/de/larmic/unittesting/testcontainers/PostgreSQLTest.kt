package de.larmic.unittesting.testcontainers

import de.larmic.unittesting.database.BookJpaRepository
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration

@DataJpaTest
@ContextConfiguration(initializers = [PostgresContextInitializer::class])
abstract class PostgreSQLTest {

    @Autowired
    protected lateinit var bookJpaRepository: BookJpaRepository

    @BeforeEach
    internal fun setUp() {
        bookJpaRepository.deleteAll()
    }
}