package com.lambdaschool.africanmarketplace.services;

import com.lambdaschool.africanmarketplace.exceptions.ResourceNotFoundException;
import com.lambdaschool.africanmarketplace.models.Item;
import com.lambdaschool.africanmarketplace.models.MarketLocation;
import com.lambdaschool.africanmarketplace.repository.MarketLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "marketLocationService")
public class MarketLocationServiceImpl implements MarketLocationService{
    @Autowired
    private MarketLocationRepository marketlocationrepos;

    @Autowired
    private ItemService itemService;

    @Transactional
    @Override
    public MarketLocation save(MarketLocation marketLocation) {
        MarketLocation newMarketLocation = new MarketLocation();
        newMarketLocation.setName(marketLocation.getName());
        newMarketLocation.setCity(marketLocation.getCity());
        newMarketLocation.setCountry(marketLocation.getCountry());
        newMarketLocation.setStreet(marketLocation.getStreet());
        newMarketLocation.setUser(marketLocation.getUser());


        for(Item i : marketLocation.getItems())
        {
            i.setUser(newMarketLocation.getUser());
            Item newItem = itemService.save(i);
            newMarketLocation.getItems().add(newItem);
        }

        return marketlocationrepos.save(newMarketLocation);
    }

    @Override
    public MarketLocation findByName(String name) {
        MarketLocation ml = marketlocationrepos.findMarketLocationByName(name);
        if(ml == null)
        {
            throw new ResourceNotFoundException("Market Location" + name + " not found!");
        }
        return ml;
    }

    @Override
    public MarketLocation findById(long id) {
        return marketlocationrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Market with id of " + id + " not found!"));
    }

    @Override
    public List<MarketLocation> findAll() {
        List<MarketLocation> myList = new ArrayList<>();
        marketlocationrepos.findAll().iterator().forEachRemaining(myList::add);
        return myList;
    }
}
