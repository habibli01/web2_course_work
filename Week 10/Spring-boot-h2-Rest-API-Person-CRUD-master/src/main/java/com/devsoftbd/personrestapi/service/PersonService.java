package com.devsoftbd.personrestapi.service;

import com.devsoftbd.personrestapi.model.PersonModel;

public interface PersonService {

	/**
	 * This method implementation should set person hobbylist from hobby array and
	 * inject person to hobby object
	 * 
	 * @param person
	 * @param hobby
	 */
	public PersonModel setPersonHobbyListFromHobbyStringArray(PersonModel person, String[] hobby);

	/**
	 * This method should set hobby array from hobbylist
	 * 
	 * @param personList
	 */
	public void setHobbyArrayFromHobbyList(PersonModel person);

	/**
	 * This method should delete all hobbies from hobby table according to personId
	 * 
	 * @param personId
	 */
	public void deleteHobbyByPersonId(Long personId);
}
