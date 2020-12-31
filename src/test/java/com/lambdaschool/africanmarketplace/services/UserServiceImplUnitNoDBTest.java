package com.lambdaschool.africanmarketplace.services;

import com.lambdaschool.africanmarketplace.AfricanMarketplaceApplicationTest;
import com.lambdaschool.africanmarketplace.models.*;
import com.lambdaschool.africanmarketplace.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AfricanMarketplaceApplicationTest.class, properties = {"command.line.runner.enabled=false"})
public class UserServiceImplUnitNoDBTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userrepos;

    @MockBean
    private MarketLocationService marketLocationService;

    @MockBean
    private RoleService roleService;

    @MockBean
    private HelperFunctions helperFunctions;

    private List<User> userList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        Role r1 = new Role("ADMIN");
        Role r2 = new Role("USER");

        r1.setRoleid(1);
        r2.setRoleid(2);

        User u1 = new User("admin",
                "password",
                "admin@gmail.com",
                "Test Fname",
                "Test Lname",
                "New York",
                "USA",
                "English",
                "USD");
        u1.setUserid(1);
        u1.getRoles().add(new UserRoles(u1, r1));
        u1.getRoles().add(new UserRoles(u1, r2));

        MarketLocation mk1 = new MarketLocation("Test Market 1", "Main Street", "New York", "USA");
        MarketLocation mk2 = new MarketLocation("Test Market 2", "Main Street", "Chicago", "USA");

        mk1.setMarketlocationid(1);
        mk2.setMarketlocationid(2);

        Item i1 = new Item("Beans", "Beans", "Red Beans", "Delicious red beans!", 10.00, 50.00);
        Item i2 = new Item("Beans", "Beans", "Red Beans", "Delicious red beans!", 20.00, 100.00);

        i1.setItemid(1);
        i2.setItemid(2);

        mk1.getItems().add(new MarketLocationItems(mk1, i1));
        mk1.getItems().add(new MarketLocationItems(mk1, i2));

        u1.getMarketLocations().add(mk1);
        u1.getMarketLocations().add(mk2);

        userList.add(u1);

        User u2 = new User("test.user",
                "password",
                "test.user@gmail.com",
                "Larry",
                "Test",
                "New York",
                "USA",
                "English",
                "USD");
        u2.setUserid(2);
        u2.getRoles().add(new UserRoles(u2, r2));

        MarketLocation mk3 = new MarketLocation("Test Market 3", "Main Street", "New York", "USA");
        MarketLocation mk4 = new MarketLocation("Test Market 4", "Main Street", "Chicago", "USA");

        mk3.setMarketlocationid(3);
        mk4.setMarketlocationid(4);

        Item i3 = new Item("Beans", "Beans", "Red Beans", "Delicious red beans!", 10.00, 50.00);
        Item i4 = new Item("Beans", "Beans", "Red Beans", "Delicious red beans!", 20.00, 100.00);

        i3.setItemid(3);
        i4.setItemid(4);

        mk3.getItems().add(new MarketLocationItems(mk3, i3));
        mk4.getItems().add(new MarketLocationItems(mk4, i4));

        u2.getMarketLocations().add(mk1);
        u2.getMarketLocations().add(mk2);

        userList.add(u2);

        MockitoAnnotations.initMocks(this);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findUserById() {
        Mockito.when(userrepos.findById(1L))
                .thenReturn(Optional.of(userList.get(0)));

        assertEquals("admin", userService.findUserById(1L).getUsername());
    }

    @Test
    public void findByNameContaining() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findByName() {
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteAll() {
    }
}