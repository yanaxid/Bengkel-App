package com.bengkel.booking.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.repositories.CustomerRepository;
import com.bengkel.booking.repositories.ItemServiceRepository;

public class MenuService {
	private static List<Customer> listAllCustomers = CustomerRepository.getAllCustomer();
	private static List<ItemService> listAllItemService = ItemServiceRepository.getAllItemService();
	private static List<BookingOrder> bookingOrders = new ArrayList<BookingOrder>();
	private static Customer customer = null;
	public static Scanner sc = new Scanner(System.in);

	public static void run() {
		boolean isLooping = true;
		do {
			ArrayList<Boolean> listStatus = login();
			isLooping = listStatus.get(0);

			if (listStatus.get(1)) {
				isLooping = mainMenu();
			}
		} while (isLooping);

	}

	public static ArrayList<Boolean> login() {
		ArrayList<Boolean> listStatus = new ArrayList<Boolean>();

		String[] listMenu = { "Login", "Exit" };
		PrintService.printMenu(listMenu, "Booking Bengkel");
		int x = Validation.validateInputNumber("", sc);

		switch (x) {
			case 1:
				customer = Validation.validateLogin(listAllCustomers, sc);
				if (customer != null) {
					listStatus.addAll(Arrays.asList(true, true));
				} else {
					listStatus.addAll(Arrays.asList(false, false));
				}
				break;
			case 0:
				listStatus.addAll(Arrays.asList(false, false));
				break;

			default:
				System.out.println("Pilihan tidak ada dalam daftar menu, ulangi lagi");
				listStatus.addAll(Arrays.asList(true, false));
				break;
		}
		return listStatus;
	}

	public static boolean mainMenu() {

		boolean status = true;

		String[] listMenu = { "Informasi Customer", "Booking Bengkel", "Top Up Bengkel Coin", "Informasi Booking", "Logout" };
		boolean isLooping = true;

		do {

			PrintService.printMenu(listMenu, "Booking Bengkel Menu");
			int x = Validation.validateInputNumber("", sc);
			switch (x) {
				case 1:
					// panggil fitur Informasi Customer
					PrintService.showAllCutomers(customer);
					break;
				case 2:
					// panggil fitur Booking Bengkel
					BookingMenu.showBookingMenu(customer, listAllItemService, bookingOrders,sc);
					break;
				case 3:
					// panggil fitur Top Up Saldo Coin
					TopupCoin.showTopupCoin(customer, sc);
					break;
				case 4:
					// panggil fitur Informasi Booking Order
					BookingOrderMenu.showBookingOrderMenu(bookingOrders);
					break;
				case 0:
					System.out.println(customer.getName() + " Logout ...");
					isLooping = false;
					status = false;
					break;

				default:
					System.out.println("Pilihan tidak ada dalam daftar menu, ulangi lagi");
					break;

			}
		} while (isLooping);

		return status;

	}

	// Silahkan tambahkan kodingan untuk keperluan Menu Aplikasi
}
