package com.bengkel.booking.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.MemberCustomer;
import com.bengkel.booking.models.Vehicle;

public class BengkelService {

	// Silahkan tambahkan fitur-fitur utama aplikasi disini

	// Login
	public static ArrayList<Boolean> login(List<Customer> listAllCustomers) {
		ArrayList<Boolean> listStatus = new ArrayList<Boolean>();
		
		String[] listMenu = { "Login", "Exit" };
		PrintService.printMenu(listMenu, "Booking Bengkel");
		int x = Validation.validateInputNumber("");

		switch (x) {
			case 1:
			MenuService.customer = Validation.validateLogin(listAllCustomers);
				
				if (MenuService.customer != null) {
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
	

	// Info Customer
	public static void showCutomers(Customer customer) {
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
	public static void showBookingMenu(Customer customer, List<ItemService> listItemServices, List<BookingOrder> bookingOrders) {
		List<Vehicle> vehicles = customer.getVehicles();
		Vehicle vehicle = Validation.validateVehicle(vehicles);

		String vehicleType = vehicle.getClass().getSimpleName();

		List<ItemService> services = listItemServices.stream().filter(o -> o.getVehicleType().equalsIgnoreCase(vehicleType))
				.collect(Collectors.toList());

		PrintService.createTableService(services);

		List<ItemService> service = Validation.validateService(services, customer);
		double totalServicePrice = 0;

		for (int i = 0; i < service.size(); i++) {
			totalServicePrice += service.get(i).getPrice();
		}

		boolean bool = true;
		String paymentMethod = "";
		do {
			System.out.print("   Pilih Metode Pembayaran (Coin atau Cash) :");
			String metodeBayar = MenuService.sc.next();

			if (metodeBayar.equalsIgnoreCase("coin")) {
				if (customer instanceof MemberCustomer) {
					double saldoCoin = ((MemberCustomer) customer).getSaldoCoin();

					if (saldoCoin < totalServicePrice) {
						System.out.println("   Saldo coin anda tidak cukup, saldo anda :" + saldoCoin);
					} else {
						// update saldo koin
//						((MemberCustomer) customer).setSaldoCoin(saldoCoin - totalServicePrice +);
//						System.out.println("   Terimaksih telah membayar, sisa saldo coin anda :" + ((MemberCustomer) customer).getSaldoCoin());
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
			bookingId = "Book-"+customer.getCustomerId()+"-001";
//			Book-Cust-001-003

		} else {

			int lastIndex = Integer.valueOf(bookingOrders.get(bookingOrders.size() - 1).getBookingId().substring(14)) + 1;

			if (lastIndex <= 9) {
				bookingId = "Book-"+customer.getCustomerId()+"-00" + String.valueOf(lastIndex);
			}else if (lastIndex < 100) {
				bookingId = "Book-"+customer.getCustomerId()+"-0" + String.valueOf(lastIndex);
			}else {
				bookingId = "Book-"+customer.getCustomerId()+"-" + String.valueOf(lastIndex);
			}
			
			

		}

//		double totalPayment= totalServicePrice;

		BookingOrder bookingOrder = new BookingOrder(bookingId, customer, service, paymentMethod, totalServicePrice);

		if (bookingOrder.getPaymentMethod().equalsIgnoreCase("cash")) {
			bookingOrder.setTotalPayment(totalServicePrice);
		}else {
			System.out.println("   Terimaksih telah membayar  dgn coin sebesar " +  bookingOrder.getTotalPayment());
			((MemberCustomer) customer).setSaldoCoin(((MemberCustomer) customer).getSaldoCoin() - bookingOrder.getTotalPayment() );
			
			System.out.println("   sisal saldo coin anda :" + ((MemberCustomer) customer).getSaldoCoin());
		}

		bookingOrders.add(bookingOrder);
		System.out.println("   Booking Berhasil!!!");
		System.out.println("   Total Harga Service : " + totalServicePrice);
		System.out.println("   Total Pembayaran : " + bookingOrder.getTotalPayment());
	}

	// Top Up Saldo Coin Untuk Member Customer
	public static void showTopupCoin(Customer customer) {

		if (customer instanceof MemberCustomer) {
			System.out.print("   Masukan besaran Top Up: ");
			int addedSaldo = Validation.validateInputNumber("Input topup berupa angka");
			((MemberCustomer) customer).setSaldoCoin(((MemberCustomer) customer).getSaldoCoin() + addedSaldo);
		} else {
			System.out.println("   Maaf fitur ini hanya untuk Member saja!");
		}
	}

	// Logout

	public static List<Boolean> logOut(Customer customer) {
		List<Boolean> isBool = new ArrayList<Boolean>();

		boolean isLoop = true;
		do {

			System.out.print("   Anda yakin mau logout? (Y/T) : ");
			String x = MenuService.sc.next();
			if (x.equalsIgnoreCase("y")) {
				isLoop = false;
				System.out.println("   "+customer.getName() + " ... Logout");
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
