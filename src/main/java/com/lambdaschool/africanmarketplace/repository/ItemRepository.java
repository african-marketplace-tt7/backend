package com.lambdaschool.africanmarketplace.repository;

import com.lambdaschool.africanmarketplace.models.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findAllByCommodityProduct(String product);
    void deleteByItemid(long id);
}
