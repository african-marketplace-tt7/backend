package com.lambdaschool.africanmarketplace.services;

import com.lambdaschool.africanmarketplace.models.Item;
import com.lambdaschool.africanmarketplace.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service(value = "itemService")
public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemRepository itemrepos;

    @Transactional
    @Override
    public Item save(Item item) {
        Item newItem = itemrepos.save(item);
        return newItem;
    }
}
