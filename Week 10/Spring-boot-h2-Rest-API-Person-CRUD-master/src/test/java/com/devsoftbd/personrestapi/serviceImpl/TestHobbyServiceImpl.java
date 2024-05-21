package com.devsoftbd.personrestapi.serviceImpl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.devsoftbd.personrestapi.model.HobbyModel;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HobbyServiceImpl.class)
public class TestHobbyServiceImpl {

	@Autowired
	private HobbyServiceImpl hobbyService;
	private String[] hobby;

	@Before
	public void init() {
		hobby = new String[] { "Football", "Shopping" };
	}

	@Test
	public void getHobbyListFromHobbyStringArrayTest() {
		List<HobbyModel> hobbyList = hobbyService.getHobbyListFromHobbyStringArray(hobby);
		assertEquals(2, hobbyList.size());
		assertEquals("Football", hobbyList.get(0).getHobby());
		assertEquals("Shopping", hobbyList.get(1).getHobby());

	}
}
