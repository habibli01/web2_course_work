package com.devsoftbd.personrestapi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsoftbd.personrestapi.model.HobbyModel;

public interface HobbyRepository extends JpaRepository<HobbyModel, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM HobbyModel h WHERE h.person.id = :personId")
	void deleteByPersonId(@Param("personId") Long personId);
}
