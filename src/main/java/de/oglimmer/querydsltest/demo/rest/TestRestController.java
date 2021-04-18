package de.oglimmer.querydsltest.demo.rest;

import com.querydsl.core.types.Predicate;
import de.oglimmer.querydsltest.demo.dao.PersonRepository;
import de.oglimmer.querydsltest.demo.entity.Person;
import lombok.AllArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestRestController {

    private PersonRepository personRepository;

    @GetMapping("/test")
    public Iterable<Person> getData(@QuerydslPredicate(root = Person.class) Predicate predicate) {
        return personRepository.findAll(predicate);
    }

}
