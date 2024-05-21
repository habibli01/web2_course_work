package com.devsoftbd.personrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsoftbd.personrestapi.model.PersonModel;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long> {

	public PersonModel findByFirstName(String firstName);
}
