package models;

public class UserBuilder{
    private String firstName;
    private String lastName;
    private String eMail;
    private String password;


    public UserBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public UserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public UserBuilder eMail(String eMail) {
        this.eMail = eMail;
        return this;
    }
    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }
    public User build(){
        return new User(firstName, lastName, eMail, password);
    }
}
