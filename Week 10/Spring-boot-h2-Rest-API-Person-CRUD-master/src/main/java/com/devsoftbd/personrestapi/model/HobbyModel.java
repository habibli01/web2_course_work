package com.devsoftbd.personrestapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "hobbies")
public class HobbyModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String hobby;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "persons_id", nullable = false)
	@JsonIgnore
	private PersonModel person;

	public HobbyModel() {
	}

	public HobbyModel(String hobby) {
		this.hobby = hobby;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public PersonModel getPerson() {
		return person;
	}

	public void setPerson(PersonModel person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "HobbyModel [id=" + id + ", hobby=" + hobby + ", person=" + person + "]";
	}

}
