# unit-testing-best-bad-practices

[![CI with Maven](https://github.com/larmic/unit-testing-best-bad-practices/actions/workflows/maven.yml/badge.svg)](https://github.com/larmic/unit-testing-best-bad-practices/actions/workflows/maven.yml)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Codebeispiele für meinen Vortrag **Das untere Ende der Testpyramide**

### Beispiel für ```Mocking ist 💩``` und ```Whitebox-Testing ist 💥```

1. [BookRepository](src/main/kotlin/de/larmic/unittesting/database/BookRepository.kt)
   1. Erklären
2. [BadBookRepositoryTest](src/test/kotlin/de/larmic/unittesting/database/BadBookRepositoryTest.kt)
   1. Kein IT → _keine Abhängigkeiten_ → _nur mocking ist erlaubt_
   2. `find by id` erklären und Test ausführen
      1. `find by id` durch Alternative ersetzen `fun findById(id: UUID) = bookJpaRepository.findById(id).orElseGet { null }.toDomain()`
         * Test schlägt fehl 💥
         * **Verstoß** gegen _Unit-Test sollen Refactoringsicher sein_
         * **Beispiel** für _Das passiert bei Whitebox-Testing_
   3. `find by title` erklären und Test ausführen
      1. Öffne [BookJpaRepository](src/main/kotlin/de/larmic/unittesting/database/BookJpaRepository.kt)
         1. Query anpassen (z.B. `*` durch `b` ersetzen)
         2. Test schlägt fehl 💥
         3. **Frage**: Was ist eigentlich die Aufgabe eines Repositories?
3. [GoodBookRepositoryTest](src/test/kotlin/de/larmic/unittesting/database/GoodBookRepositoryTest.kt)
   1. `AbstractPostgreSQLTest` stellt die PostgreSQL über Docker bereit
   2. `find by id` Test ausführen ✅
   3. `find by id` Implementierung anpassen und Test ausführen ✅
   4. `find by title`Test ausführen ✅
   5. Query in [BookJpaRepository](src/main/kotlin/de/larmic/unittesting/database/BookJpaRepository.kt) anpassen und Test ausführen 💥

#### Zusammenfassung

* Datenbanktests können schnell sein
* Datenbanktests sind kurz und übersichtlich
* Datenbanktests testen die Wirklichkeit
* **Frage**: Was wäre mit H2?

### Beispiel für ```Testet eure Frameworks```

Siehe [BookRestController](src/main/kotlin/de/larmic/unittesting/rest/BookRestController.kt) und die 
zugehörigen [BadBookRestControllerTest](src/test/kotlin/de/larmic/unittesting/rest/BadBookRestControllerTest.kt) und
[GoodBookRestControllerTest](src/test/kotlin/de/larmic/unittesting/rest/GoodBookRestControllerTest.kt) Tests.