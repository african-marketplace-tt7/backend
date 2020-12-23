package com.lambdaschool.africanmarketplace.services;

import com.lambdaschool.africanmarketplace.models.MarketLocation;

public interface MarketLocationService {
    MarketLocation save (MarketLocation marketLocation);
    MarketLocation findByName(String name);
    MarketLocation findById(long id);
}
