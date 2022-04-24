package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@Entity
@Table(name="admin")
public class Admin implements Serializable {

	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
    @GeneratedValue(generator = "admin_seq")
	@SequenceGenerator(name="admin_seq",sequenceName = "admin_seq",allocationSize = 1)
	@Column(name="id")
	@ApiModelProperty(notes = "The database generated adminId")
	private long id;
	
	@Column(name="name")
	@ApiModelProperty(notes = "The Admin name")
	@NotNull(message="Please Enter Admin name")
	@NotEmpty(message="Please Enter Admin Name. Admin name can not be blank.")
	private String name;
	
	@Column
	@ApiModelProperty(notes = "The  password")
	@Size(min = 6, message = "Password must be a greater than 6 characters")
	@Pattern(regexp="[a-zA-z0-9]{6,}",message="must match proper format.")
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Admin(@NotNull String name, @NotNull String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public Admin(long id, @NotNull String name, @NotNull String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
	
	
	

}

