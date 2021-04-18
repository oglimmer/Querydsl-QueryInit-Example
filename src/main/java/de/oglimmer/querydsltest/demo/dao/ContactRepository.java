package de.oglimmer.querydsltest.demo.dao;

import de.oglimmer.querydsltest.demo.entity.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {

}
