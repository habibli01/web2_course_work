package com.devsoftbd.personrestapi.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.devsoftbd.personrestapi.model.HobbyModel;
import com.devsoftbd.personrestapi.model.PersonModel;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private PersonRepository personRepository;

	private PersonModel persistPerson = null;
	private List<HobbyModel> hobbyList = null;

	@Before
	public void init() {
		hobbyList = new ArrayList<>();
		HobbyModel shopping = new HobbyModel("shopping");
		HobbyModel football = new HobbyModel("football");
		hobbyList.add(shopping);
		hobbyList.add(football);
		persistPerson = new PersonModel("Palash Kumar", "Nath", 31, "blue", hobbyList);
		hobbyList.forEach(hobby -> hobby.setPerson(persistPerson));
		persistPerson.setCreatedAt(new Date());
		entityManager.persist(persistPerson);
		entityManager.flush();
	}

	@Test
	public void whenFindByFirstName_thenReturnPerson() {
		PersonModel fetchedPerson = personRepository.findByFirstName(persistPerson.getFirstName());
		assertEquals(persistPerson.getLastName(), fetchedPerson.getLastName());
		assertEquals(hobbyList.size(), persistPerson.getHobbyList().size());
	}
}
