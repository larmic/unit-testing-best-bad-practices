package de.larmic.unittesting.testcontainers

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

class PostgresContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(ctx: ConfigurableApplicationContext) {
        postgreSQLContainer.start()

        TestPropertyValues.of(
            "spring.datasource.url=" + postgreSQLContainer.jdbcUrl,
            "spring.datasource.username=" + postgreSQLContainer.username,
            "spring.datasource.password=" + postgreSQLContainer.password,
        ).applyTo(ctx.getEnvironment())
    }

    companion object {
        val postgreSQLContainer = PostgreSQLContainer(DockerImageName.parse("postgres:16.4"))
    }
}