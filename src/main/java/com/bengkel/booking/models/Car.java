package com.bengkel.booking.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Car extends Vehicle {
	private int numberOfDoor;

	public Car(String vehiclesId, String color, String brand, String transmisionType, int yearRelease, int numberOfDoor) {
		super(vehiclesId, color, brand, transmisionType, yearRelease, "Car");
		this.numberOfDoor = numberOfDoor;
	}

}
