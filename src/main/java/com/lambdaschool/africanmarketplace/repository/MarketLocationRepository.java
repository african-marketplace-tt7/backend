package com.lambdaschool.africanmarketplace.repository;

import com.lambdaschool.africanmarketplace.models.MarketLocation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarketLocationRepository extends CrudRepository<MarketLocation, Long> {
    MarketLocation findMarketLocationByName(String name);
    List<MarketLocation> findAllByName(String name);
}
