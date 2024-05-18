package com.bengkel.booking.services;

import java.util.ArrayList;
import java.util.List;

import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.MemberCustomer;
import com.bengkel.booking.models.Vehicle;

public class Validation {

	public static int validateInputNumber(String message) {
		boolean isValid = true;

		String numberInput = "";

		while (isValid) {
			numberInput = MenuService.sc.next();
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

	public static Customer validateLogin(List<Customer> listAllCustomers) {

		Customer customer = null;

		int counter = 1;

		do {
			System.out.print("Customer ID: ");
			String username = MenuService.sc.next();
			System.out.print("Password: ");
			String password = MenuService.sc.next();
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

		if (customer == null) {
			System.out.println("*kesempatan hanya 3x");
		}

		return customer;
	}

	public static Vehicle validateVehicle(List<Vehicle> vehicles) {
		String id = "";
		Vehicle vehicle = null;

		//
		boolean status = true;
		System.out.print("   Masukan Vehicle ID: ");
		id = MenuService.sc.next();

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

	public static List<ItemService> validateService(List<ItemService> services, Customer customer) {
		List<ItemService> listItemServices = new ArrayList<ItemService>();

		int serviceEvail = 0;
		String customerType = "";

		if (customer instanceof MemberCustomer) {
			serviceEvail = 2;
			customerType = "member";
		} else {
			serviceEvail = 1;
			customerType = "non member";
		}

		String id = "";

		boolean isNext = true;

		do {
			//
			boolean status = true;
			System.out.print("   Masukan Service ID: ");
			id = MenuService.sc.next();

			for (int i = 0; i < services.size(); i++) {

				if (id.equalsIgnoreCase(services.get(i).getServiceId())) {
					status = false;

					if (listItemServices.size() != 0) {
						boolean isSame = true;

						for (int j = 0; j < listItemServices.size(); j++) {

							if (id.equalsIgnoreCase(listItemServices.get(j).getServiceId())) {
								System.out.println("   Gagal! servise sudah ditambahkan sebelumnya");
								isSame = false;
								break;
							}
						}
						if (isSame) {
							listItemServices.add(services.get(i));
						}
					} else {
						listItemServices.add(services.get(i));
					}
				}
			}

			while (status) {
				System.out.println("   Service ID tidak terdaftar");
				System.out.print("   Masukan Service Id: ");
				id = MenuService.sc.next();

				for (int i = 0; i < services.size(); i++) {

					if (id.equalsIgnoreCase(services.get(i).getServiceId())) {
						status = false;

						if (listItemServices.size() != 0) {
							boolean isSame = true;

							for (int j = 0; j < listItemServices.size(); j++) {

								if (id.equalsIgnoreCase(listItemServices.get(j).getServiceId())) {
									System.out.println("   Gagal! servise sudah ditambahkan sebelumnya");
									isSame = false;
									break;
								}
							}
							if (isSame) {
								listItemServices.add(services.get(i));
							}
						} else {
							listItemServices.add(services.get(i));
						}
					}
				}

			}
			
			
			if(customerType.equalsIgnoreCase("member") && listItemServices.size() == 2) {
				isNext = false;
			}if(customerType.equalsIgnoreCase("non member") && listItemServices.size() == 1) {
				isNext = false;
				
			}else {
				System.out.print("   Ingin tambah service yang lain (Y/T)? ");

				String x = MenuService.sc.next();
				if (x.equalsIgnoreCase("y")) {
					isNext = true;
				} else if (x.equalsIgnoreCase("t")) {
					isNext = false;

				}
			}

			
		} while (isNext);

		return listItemServices;
	}

}
