package models;

public class UserBuilder{
    private String firstName;
    private String lastName;
    private String city;
    private String postCode;
    private String state;


    public UserBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public UserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public UserBuilder city(String city) {
        this.city = city;
        return this;
    }
    public UserBuilder postCode(String postCode) {
        this.postCode = postCode;
        return this;
    }
    public UserBuilder state(String state) {
        this.state = state;
        return this;
    }
    public User build(){
        return new User(firstName, lastName, state, city, postCode);
    }
}
