package com.example.demo.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@Entity
@Table(name="booking")
public class Booking implements Serializable {
	@Id
    @GeneratedValue(generator = "booking_seq")
	@SequenceGenerator(name="booking_seq",sequenceName = "booking_seq",allocationSize = 1)
	@Column(name="bookingid")
	@ApiModelProperty(notes = "The database generated bookingId")
	private long bookingId;
	
	@Column(name = "bookeddate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "dd-MM-yyyy HH:mm:ss")
	@NotNull
    private Calendar createdDate;
	
	@Column(name="seatcount")
	@ApiModelProperty(notes = "The  seatcount")
	@NotNull(message="Please Enter seatcount")
	@Min(value = 1, message = "seatcount can't be zero or null")
	private int seatcount;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "seattypeid")
	//@JsonIgnore
	private List<SeatType> seattype;
	 
	 
	@Column(name="showdate")
	@ApiModelProperty(notes = "The show Date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="Please provide show Date.")
	private String showdate;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hallid")
	//@JsonIgnore
    private Hall hall;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "showid")
	//@JsonIgnore
    private Show show;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	//@JsonIgnore
	private User user;
	

	public int getSeatcount() {
		return seatcount;
	}

	public void setSeatcount(int seatcount) {
		this.seatcount = seatcount;
	}

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	public List<SeatType> getSeattype() {
		return seattype;
	}

	public void setSeattype(List<SeatType> seattype) {
		this.seattype = seattype;
	}

	public String getShowdate() {
		return showdate;
	}

	public void setShowdate(String showdate) {
		this.showdate = showdate;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Booking(@NotNull Calendar createdDate, String showdate) {
		super();
		this.createdDate = createdDate;
		this.showdate = showdate;
	}

	public Booking(@NotNull Calendar createdDate) {
		super();
		this.createdDate = createdDate;
	}

	public Booking(@NotNull Calendar createdDate, @NotNull int seatcount, String showdate) {
		super();
		this.createdDate = createdDate;
		this.seatcount = seatcount;
		this.showdate = showdate;
	}

	public Booking() {
		super();
	}

	public Booking(long bookingId, @NotNull Calendar createdDate,
			@NotNull(message = "Please Enter seatcount") @Min(value = 1, message = "seatcount can't be zero or null") int seatcount,
			List<SeatType> seattype, @NotNull(message = "Please provide show Date.") String showdate, Hall hall,
			Show show, User user) {
		super();
		this.bookingId = bookingId;
		this.createdDate = createdDate;
		this.seatcount = seatcount;
		this.seattype = seattype;
		this.showdate = showdate;
		this.hall = hall;
		this.show = show;
		this.user = user;
	}

	

}
