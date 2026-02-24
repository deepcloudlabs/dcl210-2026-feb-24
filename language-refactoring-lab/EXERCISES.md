Refactor Backlog (mapped to the "Language Changes in Java SE 7 → 25" deck)
===========================================================================

Keep tests green at every step.

Java SE 7 (Project Coin)
------------------------
- String switch: replace PaymentParser if/else chain with switch on String.
- Numeric literals: underscores + binary literals in MaskUtil.
- Multi-catch: collapse repeated catch blocks in CsvIO and AsyncOrderProcessor.
- try-with-resources: remove manual close patterns (CsvIO, ReportWriter).
- Diamond operator: simplify verbose generics (DiscountEngine, stores).

Java SE 8 (Functional + Lambdas)
--------------------------------
- Replace anonymous classes with lambdas/method references (DiscountEngine, AsyncOrderProcessor).
- Replace loops with Stream API (OrderStatistics).
- Convert Schedule annotations to @Repeatable.

Java SE 9+
----------
- JPMS modularization (module-info.java) + private interface methods for PricingFunctions.

Java SE 10–25
-------------
- var, new String APIs, switch expressions, text blocks, records, sealed classes,
  pattern matching, virtual threads/structured concurrency, sequenced collections,
  String templates, stream gatherers, Markdown Javadoc, etc.
