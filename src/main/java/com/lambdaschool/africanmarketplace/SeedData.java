package com.lambdaschool.africanmarketplace;

import com.lambdaschool.africanmarketplace.models.MarketLocation;
import com.lambdaschool.africanmarketplace.models.Role;
import com.lambdaschool.africanmarketplace.models.User;
import com.lambdaschool.africanmarketplace.models.UserRoles;
import com.lambdaschool.africanmarketplace.services.UserService;
import com.lambdaschool.africanmarketplace.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        userService.deleteAll();
        roleService.deleteAll();
        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);

        User u1 = new User("admin", "password", "admin@gmail.com", "Test Fname", "Test Lname", "New York", "USA", "English", "USD");
        u1.getRoles().add(new UserRoles(u1, r1));

        MarketLocation mk1 = new MarketLocation("Test Market 1", "Main Street", "New York", "USA", u1);
        u1.getMarketLocations().add(mk1);
        userService.save(u1);

    }
}