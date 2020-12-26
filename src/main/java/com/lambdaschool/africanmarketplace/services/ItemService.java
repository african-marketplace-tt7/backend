package com.lambdaschool.africanmarketplace.services;

import com.lambdaschool.africanmarketplace.models.Item;

import java.util.List;

public interface ItemService {
    Item save(Item item);
    Item findById(long id);
    List<Item> findAll();
    List<Item> findAllProduct(String product);
    void update(Item item, long id);
    void delete(long id);
}
