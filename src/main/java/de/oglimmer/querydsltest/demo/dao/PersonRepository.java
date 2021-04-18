package de.oglimmer.querydsltest.demo.dao;

import de.oglimmer.querydsltest.demo.entity.Person;
import de.oglimmer.querydsltest.demo.entity.QPerson;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

public interface PersonRepository extends JpaRepository<Person, Long>, QuerydslPredicateExecutor<Person>,
        QuerydslBinderCustomizer<QPerson> {

    @Override
    default void customize(QuerydslBindings bindings, QPerson qTransactionDao) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

}
