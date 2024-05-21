package com.devsoftbd.personrestapi.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsoftbd.personrestapi.exception.ResourceNotFoundException;
import com.devsoftbd.personrestapi.model.PersonModel;
import com.devsoftbd.personrestapi.repository.PersonRepository;
import com.devsoftbd.personrestapi.service.PersonService;

@RestController
@RequestMapping(value = "/person-service/api/v1", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonService personService;

	@PostMapping("/persons")
	public ResponseEntity<?> createPerson(@Valid @RequestBody PersonModel person) {
		if (person.getHobby() != null && person.getHobby().length > 0)
			person = personService.setPersonHobbyListFromHobbyStringArray(person, person.getHobby());
		person.setCreatedAt(new Date());
		PersonModel createdPerson = personRepository.save(person);
		return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
	}

	@GetMapping("/persons")
	public List<PersonModel> getAllPersons() {
		List<PersonModel> list = personRepository.findAll();
		if (list != null && list.size() > 0) {
			list.forEach(person -> {
				personService.setHobbyArrayFromHobbyList(person);
			});
		}
		return personRepository.findAll();
	}

	@GetMapping("/persons/{id}")
	public ResponseEntity<PersonModel> getByPersonId(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {
		PersonModel person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person was not found for provided id :" + personId));
		personService.setHobbyArrayFromHobbyList(person);
		return ResponseEntity.ok().body(person);
	}

	/**
	 * Before update a person first delete all hobbies from hobby table to keep only
	 * last provided hobbies
	 * 
	 * @param personId
	 * @param personDetails
	 * @return updatedPerson Object
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/persons/{id}")
	public ResponseEntity<PersonModel> updatePerson(@PathVariable(value = "id") Long personId,
			@Valid @RequestBody PersonModel personDetails) throws ResourceNotFoundException {
		PersonModel person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person was not found for provided id : " + personId));
		// delete hobbies for this person//
		personService.deleteHobbyByPersonId(person.getId());
		person.setFirstName(personDetails.getFirstName());
		person.setLastName(personDetails.getLastName());
		person.setAge(personDetails.getAge());
		person.setFavouriteColour(personDetails.getFavouriteColour());
		person.setUpdatedAt(new Date());
		person = personService.setPersonHobbyListFromHobbyStringArray(person, personDetails.getHobby());
		PersonModel updatedPerson = personRepository.save(person);
		personService.setHobbyArrayFromHobbyList(updatedPerson);
		return ResponseEntity.ok().body(updatedPerson);
	}

	/**
	 * Before delete a person first delete all hobbies from hobby table by calling
	 * hobbyrepo delete as remove cascade not available
	 */
	@DeleteMapping("/persons/{id}")
	public ResponseEntity<String> deletePersonById(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {
		PersonModel person = personRepository.findById(personId).orElseThrow(
				() -> new ResourceNotFoundException("Person was not found  for provided id : " + personId));
		// delete hobbylist for this person//
		personService.deleteHobbyByPersonId(personId);
		personRepository.delete(person);
		return ResponseEntity.ok().body("deleted");
	}

}
