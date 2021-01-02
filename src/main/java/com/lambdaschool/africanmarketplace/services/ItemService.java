package com.lambdaschool.africanmarketplace.services;

import com.lambdaschool.africanmarketplace.models.Item;
import com.lambdaschool.africanmarketplace.views.CountryProductAverage;
import com.lambdaschool.africanmarketplace.views.ProductAverage;

import java.util.List;

public interface ItemService {
    Item save(Item item);
    Item findById(long id);
    List<Item> findAll();
    List<Item> findAllProduct(String product);
    ProductAverage findAveragePrice(String commodityProduct);
    CountryProductAverage findAveragePriceByCountry(String country, String commodityProduct);
    Item update(Item item, long id);
    void delete(long id);
}
