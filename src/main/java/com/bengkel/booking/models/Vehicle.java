package com.bengkel.booking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Setter
@Getter

public abstract class Vehicle {
	private String vehiclesId;
	private String color;
	private String brand;
	private String transmisionType;
	private int yearRelease;
	private String vehicleType;

	
}
