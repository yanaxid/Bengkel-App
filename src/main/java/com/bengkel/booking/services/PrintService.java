package com.bengkel.booking.services;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.ItemService;

import com.bengkel.booking.models.Vehicle;

public class PrintService {

	public static void printMenu(String[] listMenu, String title) {

		int number = 1;
		System.out.println();
		System.out.println(title);

		for (String data : listMenu) {
			if (number < listMenu.length) {
				System.out.println(number + ". " + data);

			} else {
				System.out.println("0. " + data);
				System.out.print("-> ");
			}
			number++;
		}
	}

	

	public static void createTableKendaraan(List<Vehicle> listKendaraan) {
		List<String[]> dataTable = new ArrayList<String[]>();

		//
		String[] heading = { "No", "ID", "Warna", "Tipe Kendaraan", "Tahun" };
		int no = 1;
		for (Vehicle e : listKendaraan) {
			if (dataTable.size() == 0) {
				dataTable.add(heading);
			}
			String[] body = { String.valueOf(no), e.getVehiclesId(), e.getColor(), e.getVehicleType(), String.valueOf(e.getYearRelease()) };
			dataTable.add(body);
			no++;
		}

		//
		int[] listColumnWidht = getColumnWidth(dataTable);
		String lines = createLines(listColumnWidht);

		String columnWidth = "";
		for (int i = 0; i < listColumnWidht.length; i++) {
			
			if(i == 4) {
				columnWidth += "|" + " %" + listColumnWidht[i] + "s ";
			}else {
				columnWidth += "|" + " %-" + listColumnWidht[i] + "s ";
			}
		}
		columnWidth += "|";

		//
		System.out.println("   " + lines);
		for (int i = 0; i < dataTable.size(); i++) {
			if (i == 0) {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3],
						dataTable.get(i)[4]);
				System.out.println();
				System.out.println("   " + lines);
			} else {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3],
						dataTable.get(i)[4]);
				System.out.println();
			}
		}
		System.out.println("   " + lines);
	}

	public static void createTableService(List<ItemService> services) {
		List<String[]> dataTable = new ArrayList<String[]>();

		//
		String[] heading = { "No", "Service ID", "Service Name", "Vehicle Type", "Price" };
		int no = 1;
		for (ItemService e : services) {

			if (dataTable.size() == 0) {
				dataTable.add(heading);
			}
			String[] body = { String.valueOf(no), e.getServiceId(), e.getServiceName(), e.getVehicleType(),
					String.format("%,.0f", (double) e.getPrice()) };
			dataTable.add(body);
			no++;

		}

		//
		int[] listColumnWidht = getColumnWidth(dataTable);
		String lines = createLines(listColumnWidht);

		String columnWidth = "";
		for (int i = 0; i < listColumnWidht.length; i++) {
			
			if(i == 4) {
				columnWidth += "|" + " %" + listColumnWidht[i] + "s ";
			}else {
				columnWidth += "|" + " %-" + listColumnWidht[i] + "s ";
			}
		}
		columnWidth += "|";

		//
		System.out.println("   " + lines);
		for (int i = 0; i < dataTable.size(); i++) {
			if (i == 0) {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3],
						dataTable.get(i)[4]);
				System.out.println();
				System.out.println("   " + lines);
			} else {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3],
						dataTable.get(i)[4]);
				System.out.println();
			}
		}
		System.out.println("   " + lines);
	}

	public static void createTableBookingOrder(List<BookingOrder> bookingOrders) {
		List<String[]> dataTable = new ArrayList<String[]>();

		//
		String[] heading = { "No", "Book ID", "Customer Name", "Payment Method", "Total Service", "Total Payment", "Service List", "Booking Date" };
		int no = 1;
		for (BookingOrder e : bookingOrders) {

			if (dataTable.size() == 0) {
				dataTable.add(heading);
			}
			String services = "";
			for (int i = 0; i < e.getServices().size(); i++) {
				services += e.getServices().get(i).getServiceName() + ", ";
			}
			
		
			
			services = services.substring(0, services.length()-2);
			
			
			String[] body = { String.valueOf(no), e.getBookingId(), e.getCustomer().getName(), e.getPaymentMethod(),
					String.format("%,.0f", (double) e.getTotalServicePrice()), String.format("%,.0f", (double) e.getTotalPayment()), services,
					String.valueOf(LocalDate.now() )};
			dataTable.add(body);
			no++;

		}

		//
		int[] listColumnWidht = getColumnWidth(dataTable);
		String lines = createLines(listColumnWidht);

		String columnWidth = "";
		for (int i = 0; i < listColumnWidht.length; i++) {
			
			if(i == 4 || i == 5 || i == 7) {
				columnWidth += "|" + " %" + listColumnWidht[i] + "s ";
			}else {
				columnWidth += "|" + " %-" + listColumnWidht[i] + "s ";
			}
		}
		columnWidth += "|";

		//
		System.out.println("   " + lines);
		for (int i = 0; i < dataTable.size(); i++) {
			if (i == 0) {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3],
						dataTable.get(i)[4], dataTable.get(i)[5], dataTable.get(i)[6], dataTable.get(i)[7]);
				System.out.println();
				System.out.println("   " + lines);
			} else {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3],
						dataTable.get(i)[4], dataTable.get(i)[5], dataTable.get(i)[6], dataTable.get(i)[7]);
				System.out.println();
			}
		}
		System.out.println("   " + lines);
	}

	public static int[] getColumnWidth(List<String[]> dataTable) {
		int[] listColumnWidht = new int[dataTable.get(0).length];

		for (int x = 0; x < dataTable.get(0).length; x++) {

			int[] columnConten = new int[dataTable.size()];
			for (int i = 0; i < dataTable.size(); i++) {
				columnConten[i] = dataTable.get(i)[x].length();
			}
			int max = 0;
			for (int i = 0; i < columnConten.length; i++) {
				if (max < columnConten[i] || max == 0) {
					max = columnConten[i];
				}
			}
			listColumnWidht[x] = max;
		}
		return listColumnWidht;
	}

	public static String createLines(int[] listColumnWidht) {
		String lines = "+";

		for (int i = 0; i < listColumnWidht.length; i++) {
			String line = "--";
			for (int j = 0; j < listColumnWidht[i]; j++) {
				line += "-";
			}
			lines += line + "+";
		}
		return lines;
	}

}
