package com.bengkel.booking.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.MemberCustomer;
import com.bengkel.booking.models.Vehicle;

public class BengkelService {

	// Silahkan tambahkan fitur-fitur utama aplikasi disini

	// Login

	// Info Customer
	public static void showAllCutomers(Customer customer) {
		List<Vehicle> listKendaraan = new ArrayList<Vehicle>();
		listKendaraan = customer.getVehicles();
		String stausCustomer = "Non Member";
		double saldoCoin = 0;
		if (customer instanceof MemberCustomer) {
			stausCustomer = "Member";
			saldoCoin = ((MemberCustomer) customer).getSaldoCoin();
		}

		System.out.println("   Customer Profile");
		System.out.println("   Customer ID ....... " + customer.getCustomerId());
		System.out.println("   Nama .............. " + customer.getName());
		System.out.println("   Customer Status ... " + stausCustomer);
		System.out.println("   Alamat ............ " + customer.getAddress());
		if (stausCustomer.equalsIgnoreCase("Member")) {
			System.out.println("   Saldo Koin ........ " + String.format("%,.0f", (double) saldoCoin));
		}

		System.err.println();

		PrintService.createTableKendaraan(listKendaraan);
	}

	// show booking order
	public static void showBookingOrderMenu(List<BookingOrder> bookingOrders) {

		if (bookingOrders.size() == 0) {
			System.out.printf("   +-------------------+");
			System.out.println();
			System.out.printf("   | Data masih kosong |");
			System.out.println();
			System.out.printf("   +-------------------+\n");
		} else {
			PrintService.createTableBookingOrder(bookingOrders);
		}

	}

	// Booking atau Reservation
	public static void showBookingMenu(Customer customer, List<ItemService> listItemServices, List<BookingOrder> bookingOrders, Scanner sc) {
		List<Vehicle> vehicles = customer.getVehicles();
		Vehicle vehicle = Validation.validateVehicle(vehicles, sc);

		String vehicleType = vehicle.getClass().getSimpleName();

		List<ItemService> services = listItemServices.stream().filter(o -> o.getVehicleType().equalsIgnoreCase(vehicleType))
				.collect(Collectors.toList());

		PrintService.createTableService(services);

		List<ItemService> service = Validation.validateService(services, sc);
		double totalServicePrice = 0;

		for (int i = 0; i < service.size(); i++) {
			totalServicePrice += service.get(i).getPrice();
		}

		boolean bool = true;
		String paymentMethod = "";
		do {
			System.out.print("   Pilih Metode Pembayaran (Coin atau Cash) :");
			String metodeBayar = sc.next();

			if (metodeBayar.equalsIgnoreCase("coin")) {
				if (customer instanceof MemberCustomer) {
					double saldoCoin = ((MemberCustomer) customer).getSaldoCoin();

					if (saldoCoin < totalServicePrice) {
						System.out.println("   Saldo coin anda tidak cukup, saldo anda :" + saldoCoin);
					} else {
						// update saldo koin
						((MemberCustomer) customer).setSaldoCoin(saldoCoin - totalServicePrice);
						System.out.println("   Terimaksih telah membayar, sisa saldo coin anda :" + ((MemberCustomer) customer).getSaldoCoin());
						bool = false;
						paymentMethod = metodeBayar;
					}
				} else {
					System.out.println("   Maaf, metode ini hanya untuk member");
				}
			} else if (metodeBayar.equalsIgnoreCase("cash")) {
				System.out.println("   Terimaksih telah membayar cash");
				bool = false;
				paymentMethod = metodeBayar;
			} else {
				System.out.println("   Inputan anda salah, ulangi lagi");
			}

		} while (bool);

		String bookingId = "";

		if (bookingOrders.size() == 0) {
			bookingId = "Book-001";

		} else {

			int lastIndex = Integer.valueOf(bookingOrders.get(bookingOrders.size() - 1).getBookingId().substring(5)) + 1;

			if (lastIndex <= 9) {
				bookingId = "Book-00" + String.valueOf(lastIndex);
			} else {
				bookingId = "Book-" + String.valueOf(lastIndex);
			}

		}

		bookingOrders.add(new BookingOrder(bookingId, customer, service, paymentMethod, totalServicePrice, totalServicePrice));

		System.out.println("   Booking Berhasil!!!");
		System.out.println("   Total Harga Service : " + totalServicePrice);
		System.out.println("   Total Pembayaran : " + totalServicePrice);
	}

	// Top Up Saldo Coin Untuk Member Customer
	public static void showTopupCoin(Customer customer, Scanner sc) {

		if (customer instanceof MemberCustomer) {
			System.out.println("   Masukan besaran Top Up: ");
			int addedSaldo = Validation.validateInputNumber("Input topup berupa angka", sc);
			((MemberCustomer) customer).setSaldoCoin(((MemberCustomer) customer).getSaldoCoin() + addedSaldo);
		} else {
			System.out.println("   Maaf anda bukan member");
		}
	}

	// Logout

	public static List<Boolean> logOut(Customer customer, Scanner sc) {
		List<Boolean> isBool = new ArrayList<Boolean>();

		boolean isLoop = true;
		do {

			System.out.print("   Anda yakin mau logout? (Y/T) : ");
			String x = sc.next();
			if (x.equalsIgnoreCase("y")) {
				isLoop = false;
				System.out.println(customer.getName() + " ... Logout");
				isBool.addAll(Arrays.asList(false, false));
				
			} else if (x.equalsIgnoreCase("t")) {
				isBool.addAll(Arrays.asList(true, false));
				isLoop = false;

			} else {
				System.out.println("   Inputkan hurup y/t");
			}

		} while (isLoop);

		return isBool;

	}

}
