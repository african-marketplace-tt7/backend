package com.lambdaschool.africanmarketplace.services;

import com.lambdaschool.africanmarketplace.exceptions.ResourceNotFoundException;
import com.lambdaschool.africanmarketplace.models.Item;
import com.lambdaschool.africanmarketplace.models.MarketLocation;
import com.lambdaschool.africanmarketplace.models.MarketLocationItems;
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

    @Autowired
    private HelperFunctions helperFunctions;

    @Transactional
    @Override
    public MarketLocation save(MarketLocation marketLocation) {
        MarketLocation newMarketLocation = new MarketLocation();

        if(marketLocation.getMarketlocationid() != 0)
        {
            marketlocationrepos.findById(marketLocation.getMarketlocationid())
                    .orElseThrow(() -> new ResourceNotFoundException("Market Location id " + marketLocation.getMarketlocationid() + " not found!"));
            newMarketLocation.setMarketlocationid(marketLocation.getMarketlocationid());
        }

        newMarketLocation.setName(marketLocation.getName());
        newMarketLocation.setCity(marketLocation.getCity());
        newMarketLocation.setCountry(marketLocation.getCountry());
        newMarketLocation.setStreet(marketLocation.getStreet());
        newMarketLocation.setUser(marketLocation.getUser());

        newMarketLocation = marketlocationrepos.save(newMarketLocation);

        newMarketLocation.getItems().clear();
        for(MarketLocationItems mli : marketLocation.getItems())
        {
            mli.getItem().setUser(newMarketLocation.getUser());
            Item newItem = itemService.save(mli.getItem());
            newMarketLocation.getItems().add(new MarketLocationItems(newMarketLocation, newItem));
        }

        return marketlocationrepos.save(newMarketLocation);
    }

    @Transactional
    @Override
    public MarketLocation update(MarketLocation marketLocation, long id) {
        MarketLocation currentMarketLocation = findById(id);

        if(helperFunctions.isAuthorizedToMakeChange(currentMarketLocation.getUser().getUsername())) {
            if (marketLocation.getName() != null) {
                currentMarketLocation.setName(marketLocation.getName());
            }
            if (marketLocation.getCity() != null) {
                currentMarketLocation.setCity(marketLocation.getCity());
            }
            if (marketLocation.getStreet() != null) {
                currentMarketLocation.setStreet(marketLocation.getStreet());
            }
            if (marketLocation.getCountry() != null) {
                currentMarketLocation.setCountry(marketLocation.getCountry());
            }
            if (marketLocation.getItems().size() != 0)
            {
                currentMarketLocation.getItems().clear();
                for(MarketLocationItems ml : currentMarketLocation.getItems())
                {
                    Item item = itemService.findById(ml.getItem().getItemid());
                    currentMarketLocation.getItems().add(new MarketLocationItems(currentMarketLocation, item));
                }
            }
            return marketlocationrepos.save(currentMarketLocation);
        } else
        {
            throw new ResourceNotFoundException("This user is not authorized to make change");
        }


    }

    @Override
    public List<MarketLocation> findByName(String name) {
        List<MarketLocation> ml = marketlocationrepos.findAllByName(name);
        if(ml.size() == 0)
        {
            throw new ResourceNotFoundException("Market Location " + name + " not found!");
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

    @Override
    public void delete(long id) {
        MarketLocation currentMarketLocation = findById(id);
        if(helperFunctions.isAuthorizedToMakeChange(currentMarketLocation.getUser().getUsername()))
        {
            marketlocationrepos.deleteByMarketlocationid(id);
        }
    }
}
