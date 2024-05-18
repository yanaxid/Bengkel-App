package com.bengkel.booking.services;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.MemberCustomer;
import com.bengkel.booking.models.Vehicle;

public class BookingMenu {

	public static void showBookingMenu(Customer customer, List<ItemService> listItemServices, List<BookingOrder> bookingOrders, Scanner sc) {
		List<Vehicle> vehicles = customer.getVehicles();
		Vehicle vehicle = Validation.validateVehicle(vehicles, sc);

		String vehicleType = vehicle.getClass().getSimpleName();

		List<ItemService> services =listItemServices.stream().filter(o -> o.getVehicleType().equalsIgnoreCase(vehicleType))
				.collect(Collectors.toList());

		PrintService.createTableService(services);

		List<ItemService> service = Validation.validateService(services, sc);
		double  totalServicePrice = 0;

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
						//update saldo koin
						((MemberCustomer) customer).setSaldoCoin(saldoCoin - totalServicePrice);
						System.out.println("   Terimaksih telah membayar, sisa saldo coin anda :" + ((MemberCustomer) customer).getSaldoCoin());
						bool=false;
						paymentMethod = metodeBayar;
					}
				} else {
					System.out.println("   Maaf, metode ini hanya untuk member");
				}
			} else if (metodeBayar.equalsIgnoreCase("cash")) {
				System.out.println("   Terimaksih telah membayar cash");
				bool=false;
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
		System.out.println("   Total Harga Service : "+ totalServicePrice);
		System.out.println("   Total Pembayaran : "+ totalServicePrice );
	}

}
