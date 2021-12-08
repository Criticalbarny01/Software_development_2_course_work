package cw;

public class Room {

    private int numOfGuests;
    private Person person;

    public Room(int numGuests, Person person) {
        this.numOfGuests = numGuests;
        this.person = person;
    }

    public void setNumofGuests(int numofGuests) {
        this.numOfGuests = numofGuests;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getNumofGuests() {
        return numOfGuests;
    }

    public Person getPerson() {
        return person;
    }
}
