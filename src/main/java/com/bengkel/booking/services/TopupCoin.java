package com.bengkel.booking.services;

import java.util.Scanner;

import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.MemberCustomer;

public class TopupCoin {
	
	public static void showTopupCoin(Customer customer, Scanner sc) {
		
		if(customer instanceof MemberCustomer) {
			System.out.println("   Masukan besaran Top Up: ");
			int addedSaldo = Validation.validateInputNumber("Input topup berupa angka", sc);
			((MemberCustomer)customer).setSaldoCoin(((MemberCustomer)customer).getSaldoCoin()+addedSaldo);
		}else {
			System.out.println("   Maaf anda bukan member");
		}
	}

}
