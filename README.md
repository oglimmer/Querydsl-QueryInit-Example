# Querydsl example for @QueryInit

When using the Q-classes querydsl only initializes 2 levels in the hierarchy by default.

If you need more you need to use @QueryInit on the getter to define the scanning depths.

See the [class Person](src/main/java/de/oglimmer/querydsltest/demo/entity/Person.java).
