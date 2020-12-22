package com.lambdaschool.africanmarketplace;

import com.github.javafaker.Faker;
import com.lambdaschool.africanmarketplace.models.*;
import com.lambdaschool.africanmarketplace.services.MarketLocationService;
import com.lambdaschool.africanmarketplace.services.UserService;
import com.lambdaschool.africanmarketplace.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    MarketLocationService marketLocationService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception {
        Faker nameFaker = new Faker(new Locale("en-US"));

        userService.deleteAll();
        roleService.deleteAll();
        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);

        User u1 = new User("admin", "password", "admin@gmail.com", "Test Fname", "Test Lname", "New York", "USA", "English", "USD");
        u1.getRoles().add(new UserRoles(u1, r1));
        u1.getRoles().add(new UserRoles(u1, r2));

        MarketLocation mk1 = new MarketLocation("Test Market 1", "Main Street", "New York", "USA");
        MarketLocation mk2 = new MarketLocation("Test Market 2", "Main Street", "Chicago", "USA");

        Item i1 = new Item("Beans", "Beans", "Red Beans", "Delicious red beans!", 10.00);
        mk1.getItems().add(i1);

        u1.getMarketLocations().add(mk1);
        u1.getMarketLocations().add(mk2);
        userService.save(u1);

        User u2 = new User(nameFaker.name().username(),
                "password",
                nameFaker.internet().emailAddress(),
                nameFaker.name().firstName(),
                nameFaker.name().lastName(),
                nameFaker.address().city(),
                nameFaker.address().country(),
                "English",
                "USD");
        u2.getRoles().add(new UserRoles(u2, r2));
        MarketLocation mk3 = new MarketLocation(nameFaker.pokemon().name(), nameFaker.address().streetAddress(), nameFaker.address().city(), nameFaker.address().country());

        u2.getMarketLocations().add(mk3);
        userService.save(u2);

    }
}