package com.devsoftbd.personrestapi.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.devsoftbd.personrestapi.PersonRestApiApplication;
import com.devsoftbd.personrestapi.model.HobbyModel;
import com.devsoftbd.personrestapi.model.PersonModel;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersonRestApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testCreatePerson() {
		PersonModel personModel = new PersonModel();
		personModel.setFirstName("Palash Kumar");
		personModel.setLastName("Nath");
		personModel.setAge(31);
		personModel.setFavouriteColour("blue");
		personModel.setCreatedAt(new Date());

		List<HobbyModel> hobbyList = new ArrayList<>();
		HobbyModel shopping = new HobbyModel("shopping");
		HobbyModel football = new HobbyModel("football");
		hobbyList.add(shopping);
		hobbyList.add(football);
		personModel.setHobbyList(hobbyList);

		ResponseEntity<?> postResponse = restTemplate.postForEntity(getRootUrl() + "/person", personModel,
				PersonModel.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testGetAllPersons() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/persons", HttpMethod.GET, entity,
				String.class);
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetPersonById() {
		PersonModel person = restTemplate.getForObject(getRootUrl() + "/persons/1", PersonModel.class);
		System.out.println(person.getFirstName());
		assertNotNull(person);
	}

	@Test
	public void testUpdatePerson() {
		int id = 1;
		PersonModel person = restTemplate.getForObject(getRootUrl() + "/persons/" + id, PersonModel.class);
		person.setFirstName("palash");
		person.setLastName("nath");
		restTemplate.put(getRootUrl() + "/persons/" + id, person);
		PersonModel updatedPerson = restTemplate.getForObject(getRootUrl() + "/persons/" + id, PersonModel.class);
		assertNotNull(updatedPerson);
	}

	@Test
	public void testDeletePerson() {
		int id = 1;
		PersonModel person = restTemplate.getForObject(getRootUrl() + "/persons/" + id, PersonModel.class);
		assertNotNull(person);
		restTemplate.delete(getRootUrl() + "/persons/" + id);
		try {
			person = restTemplate.getForObject(getRootUrl() + "/persons/" + id, PersonModel.class);
		} catch (HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
