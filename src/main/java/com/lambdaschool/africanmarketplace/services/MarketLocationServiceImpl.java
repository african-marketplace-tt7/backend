package com.lambdaschool.africanmarketplace.services;

import com.lambdaschool.africanmarketplace.models.Item;
import com.lambdaschool.africanmarketplace.models.MarketLocation;
import com.lambdaschool.africanmarketplace.repository.MarketLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            Item newItem = itemService.save(i);
            newMarketLocation.getItems().add(newItem);
        }
        return marketlocationrepos.save(newMarketLocation);
    }
}
