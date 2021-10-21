package com.techelevator;

import javax.sql.DataSource;

import jdbc.JDBCVenueDAO;
import model.VenueDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class ExcelsiorCLI {
	private Menu menu;
	private VenueDAO venueDAO;


	public static void main(String[] args) {

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior_venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		venueDAO = new JDBCVenueDAO(dataSource);


		ExcelsiorCLI application = new ExcelsiorCLI(dataSource);
		application.run();
	}

	public ExcelsiorCLI(DataSource datasource) {
		//this.menu = new Menu(System.in, System.out);
		// instantiate your DAOs here
		menu = new Menu();

	}

	public void run() {
		while(true){
			String userMainMenuSelection = menu.showMainMenu();

			if (userMainMenuSelection.equalsIgnoreCase("1")) {
				System.out.println("1");
			}
		}
	}
	private void handleListAllVenues() {
		printHeading("All Venues");
		List<Venue> allVenues = venueDAO.getAllVenues();
		listVenues(allVenues);
	}

}
