package com.techelevator;

import javax.sql.DataSource;

import jdbc.JDBCVenueDAO;
//import model.Reservation;
import model.ReservationDAO;
import model.Venue;
import model.VenueDAO;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public class ExcelsiorCLI {
	private Menu menu;
	private VenueDAO venueDAO;
	//private ReservationDAO reservationDAO;
	private BasicDataSource dataSource;
//	private VenueMenu venueMenu;


	public static void main(String[] args) {

		ExcelsiorCLI application = new ExcelsiorCLI();
		application.run();
	}

	public ExcelsiorCLI() {
		dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior_venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");//this.menu = new Menu(System.in, System.out);
		// instantiate your DAOs here
		menu = new Menu();
		venueDAO = new JDBCVenueDAO(dataSource);
		//reservationDAO = new JDBCReservationDAO(dataSource);
	}

	public void run() {

		while(true){
			String userMainMenuSelection = menu.showMainMenu();

			if (userMainMenuSelection.equalsIgnoreCase("1")) {
				System.out.println("Which venue would you like to view?");
				handleListAllVenues();
				System.out.println("R) Return to previous Screen");
				System.out.println();
				System.out.println();
			}else if (userMainMenuSelection.equalsIgnoreCase("Q")){
				System.exit(0);
			}break;
		}

		while(true) {
			String userVenueMenuSelection = menu.showVenueMenu();


			if (userVenueMenuSelection.equalsIgnoreCase("1")) {
				System.out.println();
			}
		}
	}



	private void handleListAllVenues() {
		printHeading();
		List<Venue> allVenues = venueDAO.getAllVenues();
		listVenues(allVenues);
	}
//	private void handleListAllReservation() {
//		printHeading("All Reservations");
//		List<Reservation> allReservations = reservationDAO.getAllReservations();
//		listReservations(allReservations);
//	}
	private void printHeading() {
	}

	private void listVenues(List<Venue> venues) {
		System.out.println();
		if (venues.size() > 0) {
			for (Venue venue : venues) {

				System.out.println(venue.getId() + ") " + venue.getName());
			}
		}else {
			System.out.println("\n*** No result ***");
		}
	}



}
