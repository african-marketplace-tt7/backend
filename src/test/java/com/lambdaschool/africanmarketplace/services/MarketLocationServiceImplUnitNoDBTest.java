package com.lambdaschool.africanmarketplace.services;

import com.lambdaschool.africanmarketplace.AfricanMarketplaceApplicationTest;
import com.lambdaschool.africanmarketplace.exceptions.ResourceNotFoundException;
import com.lambdaschool.africanmarketplace.models.*;
import com.lambdaschool.africanmarketplace.repository.MarketLocationRepository;
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
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AfricanMarketplaceApplicationTest.class, properties = {"command.line.runner.enabled=false"})
public class MarketLocationServiceImplUnitNoDBTest {

    @Autowired
    private MarketLocationService marketLocationService;

    @MockBean
    private MarketLocationRepository marketlocationrepos;

    @MockBean
    private UserService userService;

    @MockBean
    private ItemService itemService;

    @MockBean
    private HelperFunctions helperFunctions;

    List<MarketLocation> marketLocationList = new ArrayList<>();
    List<User> userList = new ArrayList<>();

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

        mk1.setUser(userList.get(0));
        mk2.setUser(userList.get(0));
        mk3.setUser(userList.get(1));
        mk4.setUser(userList.get(1));

        marketLocationList.add(mk1);
        marketLocationList.add(mk2);
        marketLocationList.add(mk3);
        marketLocationList.add(mk4);

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() {
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
        mk1.setMarketlocationid(0);
        mk1.setUser(u1);
        Item i1 = new Item("Beans", "Beans", "Red Beans", "Delicious red beans!", 10.00, 50.00);
        mk1.getItems().add(new MarketLocationItems(mk1, i1));

        Mockito.when(userService.findUserById(1L))
                .thenReturn(u1);

        Mockito.when(marketlocationrepos.save(any(MarketLocation.class)))
                .thenReturn(mk1);

        Mockito.when(itemService.save(any(Item.class)))
                .thenReturn(i1);

        MarketLocation newMarketLocation = marketLocationService.save(mk1);

        assertNotNull(newMarketLocation);
        assertEquals(mk1.getName(), newMarketLocation.getName());

    }

    @Test
    public void savePut() {
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
        mk1.setMarketlocationid(1);
        mk1.setUser(u1);
        Item i1 = new Item("Beans", "Beans", "Red Beans", "Delicious red beans!", 10.00, 50.00);
        i1.setItemid(0);
        mk1.getItems().add(new MarketLocationItems(mk1, i1));

        Mockito.when(marketlocationrepos.findById(1L))
                .thenReturn(Optional.of(mk1));

        Mockito.when(userService.findUserById(1L))
                .thenReturn(u1);

        Mockito.when(marketlocationrepos.save(any(MarketLocation.class)))
                .thenReturn(mk1);

        Mockito.when(itemService.save(any(Item.class)))
                .thenReturn(i1);

        MarketLocation newMarketLocation = marketLocationService.save(mk1);

        assertNotNull(newMarketLocation);
        assertEquals(mk1.getName(), newMarketLocation.getName());

    }

    @Test
    public void update() {
    }

    @Test
    public void findByName() {
        Mockito.when(marketlocationrepos.findAllByName("market"))
                .thenReturn(marketLocationList);

        assertEquals(4, marketLocationService.findByName("market").size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findByNameNotFound() {
        Mockito.when(marketlocationrepos.findAllByName("turtle"))
                .thenReturn(new ArrayList<>());

        assertEquals(4, marketLocationService.findByName("market").size());
    }

    @Test
    public void findById() {
        Mockito.when(marketlocationrepos.findById(1L))
                .thenReturn(Optional.of(marketLocationList.get(0)));
        assertEquals("Test Market 1", marketLocationService.findById(1L).getName());
    }

    @Test
    public void findAll() {
        Mockito.when(marketlocationrepos.findAll())
                .thenReturn(marketLocationList);
        assertEquals(4, marketLocationService.findAll().size());
    }

    @Test
    public void delete() {
        Mockito.when(marketlocationrepos.findById(1L))
                .thenReturn(Optional.of(marketLocationList.get(0)));
        Mockito.when(helperFunctions.isAuthorizedToMakeChange("admin"))
                .thenReturn(true);
        Mockito.doNothing().when(marketlocationrepos).deleteByMarketlocationid(1L);

        marketLocationService.delete(1L);

        assertEquals(4, marketLocationList.size());
    }
}