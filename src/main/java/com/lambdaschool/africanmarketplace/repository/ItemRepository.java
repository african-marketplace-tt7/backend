package com.lambdaschool.africanmarketplace.repository;

import com.lambdaschool.africanmarketplace.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
    void deleteByItemid(long id);
}
