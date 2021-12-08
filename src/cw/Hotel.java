package cw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Hotel {

    private final Room[] hotel;
    private Queue<Person> waitingList = new LinkedList<>();
    private Queue<Integer> waitingListNumOfGuests = new LinkedList<>();


    public Hotel(int numRooms) {
        hotel = new Room[numRooms];

        for (int i = 0; i < numRooms; i++) {
            Person tempPerson = new Person("empty", "empty", 0);
            hotel[i] = new Room(0, tempPerson);
        }
    }

    /**
     * Displays all the rooms with all the information within them.
     */
    public void viewAllRooms() {
        for (int i = 0; i < hotel.length; i++) {
            System.out.print("Room number: " + (i + 1) + " First Name: " + hotel[i].getPerson().getFirstName() + " Last Name: " +
                    hotel[i].getPerson().getLastName() + " Card Number: " + hotel[i].getPerson().getCardNum()
                    + " Number of Guests: " + hotel[i].getNumofGuests() + "\n");
        }
    }

    /**
     * Adds the name of the costumer to the first empty room
     * It checks if the room is empty and if it doesn't find one it adds the person to the que.
     */
    public void addCustomer(String fName, String lName, long cardNum, int numGuests) {
        boolean exit = false;
        int i = 0;
        while (!exit) {
            if (hotel[i].getPerson().getFirstName().equals("empty")) {
                Person tempPerson = new Person(fName, lName, cardNum);
                hotel[i].setPerson(tempPerson);
                hotel[i].setNumofGuests(numGuests);
                exit = true;
            } else if (i == 7) {
                System.out.println("The hotel was full costumer was added to the waiting list!");
                addCostumerToWaitingList(fName, lName, cardNum, numGuests);
                exit = true;
            }
            i++;
        }
    }

    /**
     * If the hotel was full this function adds that person and the corresponding data to the que.
     */
    private void addCostumerToWaitingList(String fName, String lName, long cNum, int guestNum) {
        Person tempPerson = new Person(fName, lName, cNum);
        waitingList.add(tempPerson);
        waitingListNumOfGuests.add(guestNum);
    }

    //Adds the person found in the beginning of the room to the empty room
    private void addFromWaitingList(int roomNum) {
        Person tempPerson = waitingList.remove();
        hotel[roomNum].setPerson(tempPerson);
        hotel[roomNum].setNumofGuests(waitingListNumOfGuests.remove());
    }

    //Displays the empty room numbers or if the hotel is full
    public void viewEmpty() {
        for (int i = 0; i < hotel.length; i++) {
            if (hotel[i].getPerson().getFirstName().equals("empty")) {
                System.out.print((i + 1) + ", ");
            } else if (i == 7) {
                System.out.println("The hotel is full!");
            }
        }
    }

    /**
     * Deletes the name of the costumer from the room it was found in
     * if hotel was full then the person from the que will be added to the now free room.
     */
    public void deleteCustomer(String fName, String lName) {
        boolean exit = false;
        int i = 0;
        while (!exit) {
            if (hotel[i].getPerson().getFirstName().equals(fName) && hotel[i].getPerson().getLastName().equals(lName)) {
                if (!(hotel[7].getPerson().getFirstName().equals("empty")) && waitingList.size() > 0) {
                    addFromWaitingList(i);
                } else {
                    Person tempPerson = new Person("empty", "empty", 0);
                    hotel[i].setPerson(tempPerson);
                    hotel[i].setNumofGuests(0);

                }
                exit = true;
            } else if (i == 7) {
                System.out.println("Please enter a valid First and last name!");
                exit = true;
            }
            i++;
        }
    }

    /**
     * Looks up the specified name and returns the corresponding room number
     */
    public void findRoomByName(String fName, String lName) {
        boolean exit = false;
        int i = 0;
        while (!exit) {
            if (hotel[i].getPerson().getFirstName().equals(fName) && hotel[i].getPerson().getLastName().equals(lName)) {
                System.out.println(hotel[i].getPerson().getFirstName() + " " +
                        hotel[i].getPerson().getLastName() + " is in room: " + (i + 1));
                exit = true;
            } else if (i == 7) {
                System.out.println("Please enter a valid First and Last name!");
                exit = true;
            }
            i++;
        }
    }

    /**
     * Saves file into a .txt file then returns if it was successfully
     * Requires a filename, in case of an IO error it catches it.
     */
    public void saveFile(String fileName) {
        try {
            File myfile = new File(fileName);
            if (myfile.createNewFile()) {
                FileWriter myWriter = new FileWriter(fileName);
                for (Room room : hotel) {
                    myWriter.write(room.getPerson().getFirstName() + " " + room.getPerson().getLastName() +
                            " " + room.getPerson().getCardNum() + " " + room.getNumofGuests() + "\n");
                }
                System.out.println("The " + fileName + " was successfully created!");
                myWriter.close();
            } else {
                myfile.delete();
                saveFile(fileName);
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Loads the initialized array with the elements found in the specified .txt file
     * In case of a file not found error it asks again until the correct file name is entered.
     */
    public void loadFile(String fileName) {
        try {
            File myFile = new File(fileName);
            Scanner f = new Scanner(myFile);
            int i = 0;
            while (f.hasNext()) {
                String tempFname = f.next();
                String tempLname = f.next();
                long tempCnum = f.nextLong();
                int tempNumGuests = f.nextInt();
                Person tempPerson = new Person(tempFname, tempLname, tempCnum);
                hotel[i].setPerson(tempPerson);
                hotel[i].setNumofGuests(tempNumGuests);
                i++;
            }
        } catch (FileNotFoundException e) {
            Scanner sCopy = new Scanner(System.in);

            System.out.println("The file could not be found");
            System.out.println("What is the name of the file you would like to load?");
            String loadFileAgain = sCopy.next();

            loadFile(loadFileAgain);
        }
    }

    /**
     * Start of the quick sort algorithm
     * Before the Quicksort part is invoked it will make a new array to put the elements' init which are not empty
     * Displaying also happens here.
     */
    public void sort() {
        int numToSub = 0;
        for (Room room : hotel) {
            if (room.getPerson().getFirstName().equals("empty")) {
                numToSub++;
            }
        }
        int newArrayLength = (hotel.length) - numToSub;
        String[] display = new String[newArrayLength];
        for (int i = 0; i < newArrayLength; i++) {
            display[i] = "empty";
        }
        int j = 0;
        for (int i = 0; i < 8; i++) {
            if (!(hotel[i].getPerson().getFirstName().equals("empty")) && !(j == newArrayLength)) {
                display[j] = hotel[i].getPerson().getFirstName();
                j++;
            }
        }
        int length = display.length;
        quickSort(display, 0, length - 1);
        for (int i = 0; i < display.length; i++) {
            System.out.print(display[i] + " ");
        }
    }

    /**
     * Reorganizes the array so that its in alphabetical order
     * It first makes a pivot where it will dived the array and compares the first and last number to it
     * Then invokes the swap method which changes the 'i'th element with the 'j' th element
     * Calls itself recursive modifying the start and end numbers
     */
    private void quickSort(String[] display, int start, int end) {
        int i = start;
        int j = end;

        String pivot = display[start + (end - start) / 2];

        while (i <= j) {
            while (display[i].compareToIgnoreCase(pivot) < 0) {
                i++;
            }

            while (display[j].compareToIgnoreCase(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                swap(display, i, j);
                i++;
                j--;
            }
        }
        if (start < j) {
            quickSort(display, start, j);
        }
        if (i < end) {
            quickSort(display, i, end);
        }
    }

    private void swap(String[] display, int i, int j) {
        String temp = display[i];
        display[i] = display[j];
        display[j] = temp;
    }
}