package de.oglimmer.querydsltest.demo;

import de.oglimmer.querydsltest.demo.dao.AddressRepository;
import de.oglimmer.querydsltest.demo.dao.ContactRepository;
import de.oglimmer.querydsltest.demo.dao.ContactTypeRepository;
import de.oglimmer.querydsltest.demo.dao.PersonRepository;
import de.oglimmer.querydsltest.demo.entity.Address;
import de.oglimmer.querydsltest.demo.entity.Contact;
import de.oglimmer.querydsltest.demo.entity.ContactType;
import de.oglimmer.querydsltest.demo.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactTypeRepository contactTypeRepository;

    private Person savedPerson;

    @BeforeEach
    void setup() {
        Contact contact = new Contact();
        contact.setDetails("foo@bar.com");
        contactRepository.save(contact);

        ContactType contactType = new ContactType();
        contactType.setTypeValue("email");
        contactType.setContact(contact);
        contactTypeRepository.save(contactType);

        Address address = new Address();
        address.setStreetCity("Im Niergendwo 19, Frankfurt");
        address.setContact(contact);
        addressRepository.save(address);

        Person person = new Person();
        person.setName("test");
        person.setAddress(address);
        personRepository.save(person);

        contact.setContactType(contactType);
        savedPerson = person;
    }

    @Test
    void test() {
        var listOfPersons = restTemplate.getForObject("/test", Person[].class);
        assertThat(listOfPersons, arrayWithSize(1));
        assertThat(listOfPersons, arrayContaining(allOf(
                hasProperty("id", is(savedPerson.getId())),
                hasProperty("name", is(savedPerson.getName())),
                hasProperty("address", allOf(
                        hasProperty("id", is(savedPerson.getAddress().getId())),
                        hasProperty("streetCity", is(savedPerson.getAddress().getStreetCity())),
                        hasProperty("contact", allOf(
                                hasProperty("id", is(savedPerson.getAddress().getContact().getId())),
                                hasProperty("details", is(savedPerson.getAddress().getContact().getDetails())),
                                hasProperty("contactType", allOf(
                                        hasProperty("typeValue",
                                                is(savedPerson.getAddress().getContact().getContactType().getTypeValue())),
                                        hasProperty("id",
                                                is(savedPerson.getAddress().getContact().getContactType().getId()))
                                ))
                        ))
                ))
        )));
    }
}


