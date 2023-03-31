package models;

import com.github.javafaker.Faker;

import java.util.Locale;

public class UserFactory {
    public User getRandomUser(){
        Faker faker = new Faker(new Locale("en-EN"));
        return new UserBuilder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .eMail(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();
    }
    public User getAlreadyRegisteredUser(){
        return new UserBuilder()
                .firstName(System.getProperty("name"))
                .lastName(System.getProperty("lastName"))
                .eMail(System.getProperty("eMail"))
                .password(System.getProperty("password"))
                .build();
    }
}
