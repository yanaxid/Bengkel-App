package com.bengkel.booking.services;

import java.util.ArrayList;
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
	protected static Customer customer = null;
	public static Scanner sc = new Scanner(System.in);

	public static void run() {
		boolean isLooping = true;
		do {
			ArrayList<Boolean> listStatus = BengkelService.login(listAllCustomers);
			isLooping = listStatus.get(0);
			if (listStatus.get(1)) {
				isLooping = mainMenu();
			}
		} while (isLooping);
	}

	public static boolean mainMenu() {

		boolean status = true;

		String[] listMenu = { "Informasi Customer", "Booking Bengkel", "Top Up Bengkel Coin", "Informasi Booking", "Logout" };
		boolean isLooping = true;
		
		do {

			PrintService.printMenu(listMenu, "BOOKING BENGKEL MENU");
			int x = Validation.validateInputNumber("");
			switch (x) {
				case 1:
					BengkelService.showCutomers(customer);
					break;
				case 2:
					BengkelService.showBookingMenu(customer, listAllItemService, bookingOrders);
					break;
				case 3:
					BengkelService.showTopupCoin(customer);
					break;
				case 4:
					BengkelService.showBookingOrderMenu(bookingOrders);
					break;
				case 0:
					List<Boolean> isBool = BengkelService.logOut(customer);
					isLooping = isBool.get(0);
					status = isBool.get(1);
					break;
				default:
					System.out.println("Pilihan tidak ada dalam daftar menu, ulangi lagi");
					break;
			}
		} while (isLooping);
		return status;
	}

}
