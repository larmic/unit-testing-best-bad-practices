package de.larmic.unittesting.testcontainers

import de.larmic.unittesting.database.BookJpaRepository
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(initializers = [PostgresContextInitializer::class])
abstract class PostgreSQLTest {

    @Autowired
    protected lateinit var bookJpaRepository: BookJpaRepository

    @BeforeEach
    internal fun setUp() {
        bookJpaRepository.deleteAll()
    }
}