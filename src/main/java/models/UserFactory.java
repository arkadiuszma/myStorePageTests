package models;

import com.github.javafaker.Faker;

import java.util.Locale;

public class UserFactory {
    public User getRandomUser() {
        Faker faker = new Faker(new Locale("en-EN"));
        return new UserBuilder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .city(faker.address().city())
                .state(faker.address().state())
                .postCode(faker.address().zipCode())
                .address(faker.address().streetAddress())
                .build();
    }

    public User getAlreadyRegisteredUser() {
        return new UserBuilder()
                .firstName(System.getProperty("name"))
                .lastName(System.getProperty("lastName"))
                .state(System.getProperty("state"))
                .city(System.getProperty("city"))
                .postCode(System.getProperty("postCode"))
                .address(System.getProperty("address"))
                .build();
    }
}
