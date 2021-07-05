package com.sp.uc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user")
@Entity(name="nuser")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;
	@Size(min=3, max=30, message="Name should have atlease 2 chars") ////It is not working
	@ApiModelProperty(notes="name should have atleast 3 characters")
	@Column  	@NonNull
	private String name;
	@Column
	private String city;
	@Column
	private String state;
	@Column
	private String country;
	@Column  //	@Email
	private String email;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
