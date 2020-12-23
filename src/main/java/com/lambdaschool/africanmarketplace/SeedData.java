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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        List<String> kenyaCities = new ArrayList<>();
        kenyaCities.add("Nairobi");
        kenyaCities.add("Mombasa");
        kenyaCities.add("Kisumu");
        kenyaCities.add("Nakuru");
        kenyaCities.add("Ruiru");

        List<String> rwandaCities = new ArrayList<>();
        rwandaCities.add("Kigali");
        rwandaCities.add("Butare");
        rwandaCities.add("Muhanga");
        rwandaCities.add("Ruhengeri");
        rwandaCities.add("Gisenyi");

        List<String> ugandaCities = new ArrayList<>();
        ugandaCities.add("Kampala");
        ugandaCities.add("Nansana");
        ugandaCities.add("Kira");
        ugandaCities.add("Ssabagabo");
        ugandaCities.add("Mbarara");

        List<String> commoditycat = new ArrayList<>();
        commoditycat.add("Animal Products");
        commoditycat.add("Beans");
        commoditycat.add("Cereals - Maize");
        commoditycat.add("Cereals - Other");
        commoditycat.add("Cereals - Rice");
        commoditycat.add("Fruits");
        commoditycat.add("Other");
        commoditycat.add("Peas");
        commoditycat.add("Roots & Tubers");
        commoditycat.add("Seeds & Nuts");
        commoditycat.add("Vegetables");

        List<String> animalProductsSub = new ArrayList<>();
        animalProductsSub.add("Animal Products");
        animalProductsSub.add("Livestock");
        animalProductsSub.add("Poultry");

        List<String> cerealsOtherSub = new ArrayList<>();
        cerealsOtherSub.add("Barley");
        cerealsOtherSub.add("Millet");
        cerealsOtherSub.add("Sorghum");
        cerealsOtherSub.add("Wheat");

        List<String> fruitsSub = new ArrayList<>();
        fruitsSub.add("Avocado");
        fruitsSub.add("Bananas");
        fruitsSub.add("Fruits");
        fruitsSub.add("Lemons");
        fruitsSub.add("Limes");
        fruitsSub.add("Mangoes");
        fruitsSub.add("Oranges");
        fruitsSub.add("Pawpaw");
        fruitsSub.add("Pineapples");

        List<String> otherSub = new ArrayList<>();
        otherSub.add("Coffee");
        otherSub.add("Tea");
        otherSub.add("Tobacco");
        otherSub.add("Vanilla");

        List<String> rootsAndTubersSub = new ArrayList<>();
        rootsAndTubersSub.add("Cassava");
        rootsAndTubersSub.add("Potatoes");

        List<String> seedsAndNutsSub = new ArrayList<>();
        seedsAndNutsSub.add("Nuts");
        seedsAndNutsSub.add("Simsim");
        seedsAndNutsSub.add("Sunflowers");

        List<String> vegetablesSub = new ArrayList<>();
        vegetablesSub.add("Brinjals");
        vegetablesSub.add("Cabbages");
        vegetablesSub.add("Capsicums");
        vegetablesSub.add("Carrots");
        vegetablesSub.add("Cauliflower");
        vegetablesSub.add("Chillies");
        vegetablesSub.add("Cucumber");
        vegetablesSub.add("Ginger");
        vegetablesSub.add("Kales");
        vegetablesSub.add("Lettuce");
        vegetablesSub.add("Onions");
        vegetablesSub.add("Tomatoes");

        String cpAnimalProducts[] = new String[]{"Eggs", "Exotic Eggs", "Local Eggs", "Milk", "Nile Perch", "Processed Honey", "Tilapia", "Unprocessed Honey"};
        String cpLivestock[] = new String[]{"Beef", "Goat Meat", "Pork"};
        String cpPoultry[] = new String[]{"Exotic Chicken", "Local Chicken", "Turkey"};
        String cpBeans[] = new String[]{"Agwedde Beans", "Beans", "Beans (K132)", "Beans Canadian", "Beans Mwitemania", "Beans Rosecoco", "Black Beans (Dolichos)",
        "Dolichos (Njahi)", "Green Gram", "Kidney Beans", "Mixed Beans", "Mwezi Moja", "Nambale Beans", "Old Beans", "Red Beans", "Soya Beans", "White Beans", "Yellow Beans"};
        String cpMaize[] = new String[]{"Dry Maize", "Green Maize", "Maize", "Maize Bran", "Maize Flour"};
        String cpMillet[] = new String[]{"Bulrush Millet", "Finger Millet", "Millet Flour", "Millet Grain", "Pearl Millet", "White Millet"};
        String cpSorghum[] = new String[]{"Red Sorghum", "Sorghum", "Sorghum Flour", "Sorghum Grain", "White Sorghum"};
        String cpWheat[] = new String[]{"Wheat", "Wheat Bran", "Wheat Flour"};
        String cpRice[] = new String[]{"Imported Rice", "Kahama Rice", "Kayiso Rice", "Kilombero Rice", "Mbeya Rice", "Morogoro Rice", "Paddy Rice", "Rice", "Rice Bran",
        "Super Rice", "Unprocessed/husked rice", "Upland Rice"};
        String cpBananas[] = new String[]{"Apple Bananas", "Cavendish (Bogoya)", "Cooking Bananas", "Ripe Bananas"};
        String cpMangoes[] = new String[]{"Mangoes Local", "Mangoes Ngowe"};
        String cpCoffee[] = new String[]{"Coffee (Arabica)", "Coffee (Robusta)"};
        String cpPeas[] = new String[]{"Chic Pea", "Cowpeas", "Dry Peas", "Fresh Peas", "Green Peas", "Peas", "Pigeon Peas"};
        String cpCassava[] = new String[]{"Cassava Chips", "Cassava Flour", "Cassava Fresh", "Dry Fermented Cassava", "Sun Dried Cassava"};
        String cpPotatoes[] = new String[]{"Red Irish Potatoes", "Round Potatoes", "Sweet Potatoes", "White Fleshed Sweet Potatoes", "White Irish Potatoes"};
        String cpNuts[] = new String[]{"Ground Nuts", "Groundnuts"};
        String cpSunflowers[] = new String[]{"Sunflower Seed", "Sunflower Seed Cake"};



        userService.deleteAll();
        roleService.deleteAll();
        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);

        User u1 = new User("admin",
                "password",
                "admin@gmail.com",
                "Test Fname",
                "Test Lname",
                "New York",
                "USA",
                "English",
                "USD");
        u1.getRoles().add(new UserRoles(u1, r1));
        u1.getRoles().add(new UserRoles(u1, r2));

        MarketLocation mk1 = new MarketLocation("Test Market 1", "Main Street", "New York", "USA");
        MarketLocation mk2 = new MarketLocation("Test Market 2", "Main Street", "Chicago", "USA");

        Item i1 = new Item("Beans", "Beans", "Red Beans", "Delicious red beans!", 10.00, 50.00);
        mk1.getItems().add(i1);

        u1.getMarketLocations().add(mk1);
        u1.getMarketLocations().add(mk2);
        userService.save(u1);

        for(int i = 0; i < 100; i++)
        {
            String city;
            String country;
            String language;
            String currency;

            int randomNumber = (int)(Math.random() * 100 + 1);

            if(randomNumber <= 50)
            {
                country = "Kenya";
                currency = "KES";
            } else if (randomNumber <= 80)
            {
                country = "Rwanda";
                currency = "RWF";
            }
            else
            {
                country = "Uganda";
                currency = "UGX";
            }

            randomNumber = (int)(Math.random() * 100 + 1);

            if(randomNumber <= 65)
            {
                language = "English";
            } else if (randomNumber <= 85)
            {
                language = "Kinyarwanda";
            } else
            {
                language = "Swahili";
            }


            switch(country){
                case "Kenya":
                    city = kenyaCities.get((int)(Math.random() * 5));
                    break;
                case "Rwanda":
                    city = rwandaCities.get((int)(Math.random() * 5));
                    break;
                case "Uganda":
                    city = ugandaCities.get((int)(Math.random() * 5));
                    break;
                default:
                    city = "Unknown";
            }

            User newUser = new User(nameFaker.name().username(),
                    "password",
                    nameFaker.internet().emailAddress(),
                    nameFaker.name().firstName(),
                    nameFaker.name().lastName(),
                    city,
                    country,
                    language,
                    currency);

            for(int j = 0; j < (int)(Math.random() * 3 + 1); j++)
            {
                MarketLocation newMarketLocation = new MarketLocation(nameFaker.leagueOfLegends().location(), nameFaker.address().streetAddress(), city, country);
                for(int k = 0; k < (int)(Math.random() * 3); k++)
                {
                    String category = commoditycat.get((int)(Math.random() * 11));
                    String subCat;
                    String cp;

                    switch(category){
                        case "Animal Products":
                            subCat = animalProductsSub.get((int)(Math.random() * animalProductsSub.size()));
                            break;
                        case "Beans":
                            subCat = "Beans";
                            break;
                        case "Cereals - Maize":
                            subCat = "Maize";
                            break;
                        case "Cereals - Other":
                            subCat = cerealsOtherSub.get((int)(Math.random() * cerealsOtherSub.size()));
                            break;
                        case "Cereals - Rice":
                            subCat = "Rice";
                            break;
                        case "Fruits":
                            subCat = fruitsSub.get((int)(Math.random() * fruitsSub.size()));
                            break;
                        case "Other":
                            subCat = otherSub.get((int)(Math.random() * otherSub.size()));
                            break;
                        case "Peas":
                            subCat = "Peas";
                            break;
                        case "Roots & Tubers":
                            subCat = rootsAndTubersSub.get((int)(Math.random() * rootsAndTubersSub.size()));
                            break;
                        case "Seeds & Nuts":
                            subCat = seedsAndNutsSub.get((int)(Math.random() * seedsAndNutsSub.size()));
                            break;
                        case "Vegetables":
                            subCat = vegetablesSub.get((int)(Math.random() * vegetablesSub.size()));
                            break;
                        default:
                            subCat = "Error";
                    }

                    switch(subCat){
                        default:
                            cp = "Test";
                    }
                    Item newItem = new Item(category, subCat, cp, nameFaker.lorem().sentence(), (Math.random() * 50.00), (Math.random() * 50.00));
                    newMarketLocation.getItems().add(newItem);
                }
                newUser.getMarketLocations().add(newMarketLocation);
            }

            userService.save(newUser);
        }
    }
}