package com.lambdaschool.africanmarketplace.repository;

import com.lambdaschool.africanmarketplace.models.MarketLocation;
import org.springframework.data.repository.CrudRepository;

public interface MarketLocationRepository extends CrudRepository<MarketLocation, Long> {
    MarketLocation findMarketLocationByName(String name);
}
