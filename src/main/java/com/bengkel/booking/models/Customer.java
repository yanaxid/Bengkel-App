package com.bengkel.booking.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;






@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter


public class Customer {
	
	private String customerId;
	private String name;
	private String address;
	private String password;
	private List<Vehicle> vehicles;
	private int maxNumberOfService;

	public Customer(String customerId, String name, String address, String password, List<Vehicle> vehicles) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.password = password;
		this.vehicles = vehicles;
		this.maxNumberOfService = 1;
	}

}
