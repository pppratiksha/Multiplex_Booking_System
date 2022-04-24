package com.example.demo.model;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;


@SuppressWarnings("serial")
@Entity
@Table(name="seattype")
public class SeatType implements Serializable {

	public SeatType() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	@Id
	@ApiModelProperty(notes = "The  seattype Id")
	@NotNull(message="Please Enter seattype Id")	
	@Min(value = 1, message = "ID can't be zero or null")
    private Long id;
	
	@Column(name="seattypedesc")
	@ApiModelProperty(notes = "The  seattypedesc")
	@NotNull(message="Please Enter seattypedesc")
	@NotEmpty(message="Please Enter seat type description. seat type description can not be blank.")
	private String seattypedesc;
	
	@Column(name="seatfare")
	@ApiModelProperty(notes = "The  seatfare")
	@NotNull(message="Please Enter seatfare")
	@Min(value = (long) 1.00, message = "seatfare can't be zero or null")
	private float seatfare;
	
	@Column(name="seatcount")
	@ApiModelProperty(notes = "The  seatcount")
	@NotNull(message="Please Enter seatcount")
	@Min(value = 1, message = "seatcount can't be zero or null")
	private int seatcount;
		
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name ="HallId")
	private Hall hall;
	

	

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public long getSeattypeid() {
		return id;
	}

	public void setSeattypeid(long seattypeid) {
		this.id = seattypeid;
	}

	public String getSeattypedesc() {
		return seattypedesc;
	}

	public void setSeattypedesc(String seattypedesc) {
		this.seattypedesc = seattypedesc;
	}

	public float getSeatfare() {
		return seatfare;
	}

	public void setSeatfare(float seatfare) {
		this.seatfare = seatfare;
	}
	

	public int getSeatcount() {
		return seatcount;
	}

	public void setSeatcount(int seatcount) {
		this.seatcount = seatcount;
	}

	
	public SeatType(long seattypeid, @NotNull String seattypedesc, @NotNull float seatfare, @NotNull int seatcount) {
		super();
		this.id = seattypeid;
		this.seattypedesc = seattypedesc;
		this.seatfare = seatfare;
		this.seatcount = seatcount;
	}
	

	public SeatType(long seattypeid,@NotNull String seattypedesc, @NotNull float seatfare, @NotNull int seatcount, Hall hall) {
		super();
		this.id = seattypeid;
		this.seattypedesc = seattypedesc;
		this.seatfare = seatfare;
		this.seatcount = seatcount;
		this.hall = hall;
	}

	@Override
	public String toString() {
		return "SeatType [seattypeid=" + id + ", seattypedesc=" + seattypedesc + ", seatfare=" + seatfare + "]";
	}
	
	
	
	

}
