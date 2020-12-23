package com.lambdaschool.africanmarketplace.services;

import com.lambdaschool.africanmarketplace.models.Item;
import com.lambdaschool.africanmarketplace.models.MarketLocation;
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

    @Transactional
    @Override
    public Item save(Item item) {
//        Item newItem = new Item();
//        newItem.setCommodityCat(item.getCommodityCat());
//        newItem.setSubCat(item.getSubCat());
//        newItem.setCommodityProduct(item.getCommodityProduct());
//        newItem.setSalePrice(item.getSalePrice());
//        newItem.setDescription(item.getDescription());
//        newItem.setUser(item.getUser());
//        for(MarketLocation ml : item.getMarketsSold())
//        {
//
//        }
        return itemrepos.save(item);
    }
}
