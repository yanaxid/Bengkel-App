package com.bengkel.booking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.Vehicle;

public class Validation {

	public static int validateInputNumber(String message, Scanner sc) {
		boolean isValid = true;

		String numberInput = "";

		while (isValid) {
			numberInput = sc.next();
			if (numberInput.matches("[0-9]+")) {
				isValid = false;
			} else {
				isValid = true;

				if (message.equalsIgnoreCase("")) {
					message += "Inputan harus angka!";
				}
				System.out.println(message);
				System.out.print("-> ");
			}
		}
		return Integer.valueOf(numberInput);
	}

	public static Customer validateLogin(List<Customer> listAllCustomers, Scanner sc) {
		Customer customer = null;

		int counter = 1;

		do {
			System.out.print("Customer ID: ");
			String username = sc.next();
			System.out.print("Password: ");
			String password = sc.next();
			String message = "";

			for (int i = 0; i < listAllCustomers.size(); i++) {
				if (username.equalsIgnoreCase(listAllCustomers.get(i).getCustomerId())) {

					if (password.equals(listAllCustomers.get(i).getPassword())) {
						message = "login ... as " + listAllCustomers.get(i).getName();

						customer = listAllCustomers.get(i);
						break;
					} else {
						if (counter == 3) {
							message = "!pass salah, kesempatan habis";

						} else {
							message = "!pass salah, kesempatan " + (3 - counter) + "x lagi";

						}
						break;
					}

				} else {
					if (counter == 3) {
						message = "!user tidak ada, kesempatan habis";

					} else {
						message = "!user tidak ada, kesempatan " + (3 - counter) + "x lagi";

					}

				}
			}
			System.out.println(message);
			if (customer != null) {
				break;
			}
			counter++;
		} while (counter <= 3);

		if (counter == 3) {
			System.out.println("*kesempatan hanya 3x");
		}

		return customer;
	}

	public static Vehicle validateVehicle(List<Vehicle> vehicles, Scanner sc) {
		String id = "";
		Vehicle vehicle = null;

		//
		boolean status = true;
		System.out.print("   Masukan Vehicle ID: ");
		id = sc.next();

		for (int i = 0; i < vehicles.size(); i++) {
			if (id.equalsIgnoreCase(vehicles.get(i).getVehiclesId())) {
				status = false;
				vehicle = vehicles.get(i);
			}
		}

		while (status) {
			System.out.println("   Vehicle ID tidak terdaftar");
			System.out.print("   Masukan Vehicle ID: ");
			id = MenuService.sc.next();

			for (int i = 0; i < vehicles.size(); i++) {
				if (id.equalsIgnoreCase(vehicles.get(i).getVehiclesId())) {
					status = false;
					vehicle = vehicles.get(i);
				}
			}
		}

		return vehicle;
	}

	public static List<ItemService> validateService(List<ItemService> services, Scanner sc) {

		List<ItemService> listItemServices = new ArrayList<ItemService>();

		String id = "";

		boolean isNext = true;
		do {
			//
			boolean status = true;
			System.out.print("   Masukan Service ID: ");
			id = sc.next();

			for (int i = 0; i < services.size(); i++) {

				if (id.equalsIgnoreCase(services.get(i).getServiceId())) {
					status = false;
					listItemServices.add(services.get(i));
				}
			}

			while (status) {
				System.out.println("   Service ID tidak terdaftar");
				System.out.print("   Masukan Service Id: ");
				id = MenuService.sc.next();

				for (int i = 0; i < services.size(); i++) {

					if (id.equalsIgnoreCase(services.get(i).getServiceId())) {
						status = false;
						listItemServices.add(services.get(i));
					}
				}

			}
			System.out.print("   Ingin tambah service yang lain (Y/T)? ");

			String x = sc.next();
			if (x.equalsIgnoreCase("y")) {
				isNext = true;
			} else if (x.equalsIgnoreCase("t")) {
				isNext = false;

			}
		} while (isNext);

		return listItemServices;
	}

//	public static int validasiNumberWithRange(String question, String errorMessage, String regex, int max, int min) {
//		int result;
//		boolean isLooping = true;
//		do {
//			result = Integer.valueOf(validasiInput(question, errorMessage, regex));
//			if (result >= min && result <= max) {
//				isLooping = false;
//			} else {
//				System.out.println("Pilihan angka " + min + " s.d " + max);
//			}
//		} while (isLooping);
//
//		return result;
//	}
}
