Legacy Java SE 7 Language Refactoring Kata (refactor to Java 8 → 25)
======================================================

This is a single-module Java SE 7 codebase designed as a refactoring kata.
It intentionally uses pre-modern idioms: verbose generics, loops instead of streams,
manual resource management, nested if/else chains, instanceof casting, ExecutorService+Future, etc.

Build & Run (baseline)
----------------------
mvn -q test
mvn -q package
java -jar target/legacy-java7-language-kata-1.0.0.jar src/main/resources/input/orders.csv

See EXERCISES.md for a version-by-version refactor backlog.
