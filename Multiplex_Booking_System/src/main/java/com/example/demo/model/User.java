package com.example.demo.model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@Entity
@Table(name="users")
public class User implements Serializable {

	public User() {
	}
	@Id
    @GeneratedValue(generator = "user_seq")
	@SequenceGenerator(name="user_seq",sequenceName = "user_seq",allocationSize = 1)
	@Column(name="userid")
	@ApiModelProperty(notes = "The database generated bookingId")
	private long id;
	
	@Column(name="username")
	@ApiModelProperty(notes = "The user name")
	@NotNull(message="Please Enter user name")
	@NotEmpty(message="Please Enter user Name. Name can not be blank.")
	private String username;
	
	
/*	@ApiModelProperty(notes = "The user type")
	@NotNull(message="Please Enter usertype")
	@NotEmpty(message="Please Enter usertype. usertype can not be blank.")
	*/
	@Column(name="usertype")
	private char usertype;
	
	@Column(name="emailid", unique=true)
	@ApiModelProperty(notes = "The Email")
	@NotNull(message="Please Enter Email")
	@NotEmpty(message="Please Enter Email. Email can not be blank.")
	@Email
	private String emailid;
	
	@Column(name="password")
	@ApiModelProperty(notes = "The  password")
	@Size(min = 6, message = "Password must be a greater than 6 characters")
	@Pattern(regexp="[a-zA-z0-9]{6,}",message="must match proper format.")
	private String password;
	
	@Column(name="mobilenumber",unique=true)
	@Size(min = 11, max = 11, message = "Mobile number must be a 11-digit string.")
	@Pattern(regexp="0[0-9]{10}",message="Contact should start with 0 and enter proper contact.") 
	@ApiModelProperty(notes = "The  mobile number")
	private String mobileno;
	
//	 @OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
//		      CascadeType.REFRESH })
//	 @JsonIgnore
//	 private List<Booking> booking;
//	 
	
	 
	 

//	public User(String username, char usertype, String emailid, String password, String mobileno, List<Booking> booking
//			) {
//		super();
//		this.username = username;
//		this.usertype = usertype;
//		this.emailid = emailid;
//		this.password = password;
//		this.mobileno = mobileno;
//		this.booking = booking;
//		
//	}



//	public User(long id, String username, char usertype, String emailid, String password, String mobileno,
//			List<Booking> booking) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.usertype = usertype;
//		this.emailid = emailid;
//		this.password = password;
//		this.mobileno = mobileno;
//		this.booking = booking;
//	}


//	public List<Booking> getBooking() {
//		return booking;
//	}
//
//
//
//	public void setBooking(List<Booking> booking) {
//		this.booking = booking;
//	}



	public long getUserid() {
		return id;
	}
	
	public void setUserid(Long id) {
		this.id=id;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char getUsertype() {
		return usertype;
	}

	public void setUsertype(char usertype) {
		this.usertype = usertype;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public User(long id, String username, char usertype, String emailid, String password, String mobileno) {
		super();
		this.id = id;
		this.username = username;
		this.usertype = usertype;
		this.emailid = emailid;
		this.password = password;
		this.mobileno = mobileno;
	}

	public User(String username, char usertype, String emailid, String password, String mobileno) {
		super();
		this.username = username;
		this.usertype = usertype;
		this.emailid = emailid;
		this.password = password;
		this.mobileno = mobileno;
	}
}