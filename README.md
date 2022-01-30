# unit-testing-best-bad-practices

[![CI with Maven](https://github.com/larmic/unit-testing-best-bad-practices/actions/workflows/maven.yml/badge.svg)](https://github.com/larmic/unit-testing-best-bad-practices/actions/workflows/maven.yml)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Codebeispiele für meinen Vortrag "Das untere Ende der Testpyramide"

## Beispiel für ```Mocking ist 💩```
Siehe [BookRepository](src/main/kotlin/de/larmic/unittesting/database/BookRepository.kt) und die zugehörigen
[BadBookRepositoryTest](src/test/kotlin/de/larmic/unittesting/database/BadBookRepositoryTest.kt) und
[GoodBookRepositoryTest](src/test/kotlin/de/larmic/unittesting/database/GoodBookRepositoryTest.kt) Tests.

## Beispiel für ```Testet eure Frameworks```

Siehe [BookRestController](src/main/kotlin/de/larmic/unittesting/rest/BookRestController.kt) und die 
zugehörigen [BadBookRestControllerTest](src/test/kotlin/de/larmic/unittesting/rest/BadBookRestControllerTest.kt) und
[GoodBookRestControllerTest](src/test/kotlin/de/larmic/unittesting/rest/GoodBookRestControllerTest.kt) Tests.