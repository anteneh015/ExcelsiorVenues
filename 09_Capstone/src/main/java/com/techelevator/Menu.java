package com.techelevator;

import java.util.Scanner;

public class Menu {

    private static final Scanner in = new Scanner(System.in);
    public String showMainMenu(){
            System.out.println("What would you like to do?");
            System.out.println("(1) List of Venues: ");
            System.out.println("(Q) Quit");

        return in.nextLine();
    }

    public String showViewVenue() {
        return in.nextLine();
    }


    public String showVenueMenu(){
        System.out.println("What would you like to do?");
        System.out.println("(1) View Spaces ");
        System.out.println("(2) Search For Reservations ");
        System.out.println("(R) Return to Previous Screen");

        return in.nextLine();
    }
}
