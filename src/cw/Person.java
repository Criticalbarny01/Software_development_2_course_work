package cw;

public class Person {
    private String firstName;
    private String lastName;
    private long cardNum;

    public Person(String fName, String lName, long cNum) {
        this.firstName = fName;
        this.lastName = lName;
        this.cardNum = cNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getCardNum() {
        return cardNum;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }
}
