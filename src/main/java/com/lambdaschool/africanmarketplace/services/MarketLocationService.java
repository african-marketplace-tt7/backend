package com.lambdaschool.africanmarketplace.services;

import com.lambdaschool.africanmarketplace.models.MarketLocation;

import java.util.List;

public interface MarketLocationService {
    MarketLocation save (MarketLocation marketLocation);
    List<MarketLocation> findByName(String name);
    MarketLocation findById(long id);
    List<MarketLocation> findAll();
    MarketLocation update(MarketLocation marketLocation, long id);
    void delete(long id);
}
