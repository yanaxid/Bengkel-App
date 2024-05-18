package com.bengkel.booking.models;

import java.util.List;
import com.bengkel.booking.interfaces.IBengkelPayment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookingOrder implements IBengkelPayment {
	private String bookingId;
	private Customer customer;
	private List<ItemService> services;
	private String paymentMethod;
	private double totalServicePrice;
	private double totalPayment;

	@Override
	public void calculatePayment() {
		double discount = 0;
		if (paymentMethod.equalsIgnoreCase("Coin")) {
			discount = getTotalServicePrice() * RATES_DISCOUNT_SALDO_COIN;
		} else {
			discount = getTotalServicePrice() * RATES_DISCOUNT_CASH;
		}

		setTotalPayment(getTotalServicePrice() - discount);
	}

	public BookingOrder(String bookingId, Customer customer, List<ItemService> services, String paymentMethod, double totalServicePrice) {
		this.bookingId = bookingId;
		this.customer = customer;
		this.services = services;
		this.paymentMethod = paymentMethod;
		this.totalServicePrice = totalServicePrice;
		
		calculatePayment();
	}

	


	

}
