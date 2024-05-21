package com.devsoftbd.personrestapi.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "persons")
@JsonIgnoreProperties(value = { "createdAt", "updatedAt", "hobbyList" }, ignoreUnknown = true)
public class PersonModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "persons_id")
	private Long id;
	@NotNull(message = "person's first name can not be null")
	@NotEmpty(message = "person's first name is required")
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private int age;
	@Column(name = "favourite_colour")
	private String favouriteColour;
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, mappedBy = "person")
	private List<HobbyModel> hobbyList;
	@Transient
	private String[] hobby;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreatedDate
	private Date createdAt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", insertable = false, updatable = true)
	@LastModifiedDate
	private Date updatedAt;

	public PersonModel() {
	}

	public PersonModel(String firstName, String lastName, int age, String favouriteColour, List<HobbyModel> hobbyList) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.favouriteColour = favouriteColour;
		this.hobbyList = hobbyList;
	}

	public PersonModel(Long id, String firstName, String lastName, int age, String favouriteColour, String[] hobby) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.favouriteColour = favouriteColour;
		this.hobby = hobby;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFavouriteColour() {
		return favouriteColour;
	}

	public void setFavouriteColour(String favouriteColour) {
		this.favouriteColour = favouriteColour;
	}

	public List<HobbyModel> getHobbyList() {
		return hobbyList;
	}

	public void setHobbyList(List<HobbyModel> hobbyList) {
		this.hobbyList = hobbyList;
	}

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "PersonModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", favouriteColour=" + favouriteColour + ", hobby=" + Arrays.toString(hobby) + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
