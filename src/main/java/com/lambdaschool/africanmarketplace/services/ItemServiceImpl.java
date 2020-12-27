package com.lambdaschool.africanmarketplace.services;

import com.lambdaschool.africanmarketplace.exceptions.ResourceNotFoundException;
import com.lambdaschool.africanmarketplace.models.Item;
import com.lambdaschool.africanmarketplace.models.MarketLocation;
import com.lambdaschool.africanmarketplace.models.MarketLocationItems;
import com.lambdaschool.africanmarketplace.models.User;
import com.lambdaschool.africanmarketplace.repository.ItemRepository;
import com.lambdaschool.africanmarketplace.views.CountryProductAverage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "itemService")
public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemRepository itemrepos;

    @Autowired
    MarketLocationService marketLocationService;

    @Autowired
    UserService userService;

    @Autowired
    HelperFunctions helperFunctions;

    @Transactional
    @Override
    public Item save(Item item) {
        Item newItem = new Item();

        if(item.getItemid() != 0)
        {
            itemrepos.findById(item.getItemid())
                    .orElseThrow(() -> new ResourceNotFoundException("Item " + item.getItemid() + " not found!"));
            newItem.setItemid(item.getItemid());
        }

        newItem.setCommodityCat(item.getCommodityCat());
        newItem.setSubCat(item.getSubCat());
        newItem.setCommodityProduct(item.getCommodityProduct());
        newItem.setSalePrice(item.getSalePrice());
        newItem.setDescription(item.getDescription());
        newItem.setQuantity(item.getQuantity());

        User user = userService.findUserById(item.getUser().getUserid());
        newItem.setUser(user);

        for(MarketLocationItems mli : item.getMarketsSold())
        {
            MarketLocation newMarketLocation = marketLocationService.findById(mli.getMarketLocation().getMarketlocationid());
            newItem.getMarketsSold().add(new MarketLocationItems(newMarketLocation, newItem));
        }
        return itemrepos.save(newItem);
    }

    @Override
    public Item findById(long id) {
        return itemrepos.findById((id))
                .orElseThrow(() -> new ResourceNotFoundException("Item with id of " + id + " not found!"));
    }

    @Override
    public List<Item> findAll() {
        List<Item> myList = new ArrayList<>();
        itemrepos.findAll().iterator().forEachRemaining(myList::add);
        return myList;

    }

    @Override
    public void update(Item item, long id) {
        Item currentItem = findById(id);

        if(helperFunctions.isAuthorizedToMakeChange(currentItem.getUser().getUsername()))
        {
            if(item.getCommodityCat() != null){
                currentItem.setCommodityCat(item.getCommodityCat());
            }
            if(item.getSubCat() != null){
                currentItem.setSubCat(item.getSubCat());
            }
            if(item.getCommodityProduct() != null){
                currentItem.setCommodityProduct(item.getCommodityProduct());
            }
            if(item.getDescription() != null){
                currentItem.setDescription(item.getDescription());
            }
            if(item.getQuantity() != 0.0){
                currentItem.setQuantity(item.getQuantity());
            }
            if(item.getSalePrice() != 0.0){
                currentItem.setSalePrice(item.getSalePrice());
            }
            if(item.getMarketsSold().size() != 0){
                currentItem.getMarketsSold().clear();
                for(MarketLocationItems ml : item.getMarketsSold())
                {
                    MarketLocation marketLocation = marketLocationService.findById(ml.getMarketLocation().getMarketlocationid());
                    currentItem.getMarketsSold().add( new MarketLocationItems(marketLocation, currentItem));
                }
            }
            itemrepos.save(currentItem);
        } else
        {
            throw new ResourceNotFoundException("This user is not authorized to make change");
        }
    }

    @Override
    public void delete(long id) {
        Item currentItem = findById(id);
        if(helperFunctions.isAuthorizedToMakeChange(currentItem.getUser().getUsername())) {
            itemrepos.deleteByItemid(id);
        }
    }

    @Override
    public List<Item> findAllProduct(String product) {
        List<Item> rtnlist = itemrepos.findAllByCommodityProduct(product);
        return rtnlist;
    }

    @Override
    public List<CountryProductAverage> findAveragePriceByCountry(String country, String commodityProduct) {
        return itemrepos.findCountryProductAver(country, commodityProduct);
    }
}
