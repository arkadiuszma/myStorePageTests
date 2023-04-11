package models;

public record User(String firstName, String lastName, String state, String city, String postCode, String address) {
    public String getUserAddressAssertionText() {
        return firstName + " " + lastName + "\n" +
                address + "\n" +
                city + ", " + state + " " + postCode + "\n" +
                "United States";
    }
}
