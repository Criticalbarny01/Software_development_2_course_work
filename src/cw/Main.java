package cw;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Hotel myHotel = new Hotel(8);
        boolean run = true;

        while (run) {
            System.out.println("""

                    What would you like to select?
                    V: View All Rooms
                    A: Add customer to room
                    E: Display empty rooms
                    D: Delete customer from room
                    F: Find room from customer name
                    S: Store program data into file
                    L: Load program data from file
                    O: View guests
                    X: Exit the program""");

            Scanner s = new Scanner(System.in);
            String choice = s.next();
            choice = choice.toUpperCase();

            switch (choice) {
                case "V" -> myHotel.viewAllRooms();
                case "A" -> {
                    long cNum = 0;
                    int guestNum = 0;
                    System.out.println("Please enter the first name of the customer:");
                    String fname = s.next();
                    System.out.println("Please enter the last name of the customer:");
                    String lname = s.next();
                    System.out.println("Please enter the credit card number of the customer:");
                    if (s.hasNextLong()) {
                        cNum = s.nextLong();
                    } else {
                        System.out.println("Please enter a number!");
                    }
                    System.out.println("Please enter the number of guests that will be staying in the room:");
                    if (s.hasNextInt()) {
                        guestNum = s.nextInt();
                    } else {
                        System.out.println("Please enter a number!");
                    }
                    myHotel.addCustomer(fname, lname, cNum, guestNum);
                    myHotel.viewAllRooms();
                }
                case "E" -> myHotel.viewEmpty();
                case "D" -> {
                    System.out.println("Please enter the first name of the customer you would like to remove from their room?");
                    String delPersonf = s.next();
                    System.out.println("Please enter the last name");
                    String delPersonl = s.next();
                    myHotel.deleteCustomer(delPersonf, delPersonl);
                    myHotel.viewAllRooms();
                }
                case "F" -> {
                    System.out.println("The first name of the person's room would you like to know?");
                    String lookfname = s.next();
                    System.out.println("The last name of the person's room would you like to know?");
                    String looklname = s.next();
                    myHotel.findRoomByName(lookfname, looklname);
                }
                case "S" -> {
                    System.out.println("Under what name would you like to save your work?");
                    String file = s.next();
                    myHotel.saveFile(file);
                }
                case "L" -> {
                    System.out.println("What is the name of the file you would like to load?");
                    String loadFile = s.next();
                    myHotel.loadFile(loadFile);
                    myHotel.viewAllRooms();
                }
                case "O" -> {
                    myHotel.sort();
                }
                case "X" -> {
                    System.out.println("Exiting the program!");
                    run = false;
                }
                default -> System.out.println("Please enter a valid option form the given letters!");
            }
        }

    }
}
