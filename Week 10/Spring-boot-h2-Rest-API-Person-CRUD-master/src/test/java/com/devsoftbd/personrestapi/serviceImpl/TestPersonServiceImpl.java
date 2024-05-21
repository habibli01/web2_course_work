package com.devsoftbd.personrestapi.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.devsoftbd.personrestapi.model.HobbyModel;
import com.devsoftbd.personrestapi.model.PersonModel;
import com.devsoftbd.personrestapi.repository.HobbyRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersonServiceImpl.class)
public class TestPersonServiceImpl {

	@Autowired
	private PersonServiceImpl personService;
	@MockBean
	private HobbyServiceImpl hobbyService;
	@MockBean
	private HobbyRepository hobbyRepository;

	private String[] hobby;
	private PersonModel person = null;

	@Before
	public void init() {
		hobby = new String[] { "football", "shopping" };
		person = new PersonModel("Palash", "Nath", 31, "blue", null);
	}

	@Test
	public void setPersonHobbyListFromHobbyStringArrayTest() {
		List<HobbyModel> hobbyList = Arrays.stream(hobby).map(hby -> new HobbyModel(hby)).collect(Collectors.toList());
		Mockito.when(hobbyService.getHobbyListFromHobbyStringArray(hobby)).thenReturn(hobbyList);
		PersonModel personWithHobbyList = personService.setPersonHobbyListFromHobbyStringArray(person, hobby);
		assertNotNull(personWithHobbyList.getHobbyList());
		assertEquals(2, personWithHobbyList.getHobbyList().size());
		hobbyList.forEach(hby -> assertEquals("Palash", hby.getPerson().getFirstName()));
	}

	@Test
	public void setHobbyArrayFromHobbyList() {
		List<HobbyModel> hobbyList = Arrays.stream(hobby).map(hby -> new HobbyModel(hby)).collect(Collectors.toList());
		person.setHobbyList(hobbyList);
		personService.setHobbyArrayFromHobbyList(person);
		assertNotNull(person.getHobby());
	}
}
