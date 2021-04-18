package de.oglimmer.querydsltest.demo.dao;

import de.oglimmer.querydsltest.demo.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
