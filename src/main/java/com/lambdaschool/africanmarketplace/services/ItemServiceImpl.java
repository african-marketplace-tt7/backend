package com.lambdaschool.africanmarketplace.services;

import com.lambdaschool.africanmarketplace.exceptions.ResourceNotFoundException;
import com.lambdaschool.africanmarketplace.models.Item;
import com.lambdaschool.africanmarketplace.models.MarketLocation;
import com.lambdaschool.africanmarketplace.models.User;
import com.lambdaschool.africanmarketplace.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service(value = "itemService")
public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemRepository itemrepos;

    @Autowired
    MarketLocationService marketLocationService;

    @Autowired
    UserService userService;

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

        for(MarketLocation ml : item.getMarketsSold())
        {
            MarketLocation newMarketLocation = marketLocationService.findById(ml.getMarketlocationid());
            item.getMarketsSold().add(newMarketLocation);
        }
        return itemrepos.save(newItem);
    }
}
