package de.oglimmer.querydsltest.demo.dao;

import de.oglimmer.querydsltest.demo.entity.ContactType;
import org.springframework.data.repository.CrudRepository;

public interface ContactTypeRepository extends CrudRepository<ContactType, Long> {

}
