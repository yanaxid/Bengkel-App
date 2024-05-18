package com.bengkel.booking.services;

import java.util.List;

import com.bengkel.booking.models.BookingOrder;

public class BookingOrderMenu {
	
	public static void showBookingOrderMenu(List<BookingOrder> bookingOrders) {
		
		if(bookingOrders.size() == 0) {
			System.out.printf("   +-------------------+");
			System.out.println();
			System.out.printf("   | Data masih kosong |");
			System.out.println();
			System.out.printf("   +-------------------+\n");
		}else {
			PrintService.createTableBookingOrder(bookingOrders);
		}
		
	}

}
