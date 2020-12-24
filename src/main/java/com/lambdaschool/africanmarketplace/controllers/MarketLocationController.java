package com.lambdaschool.africanmarketplace.controllers;

import com.lambdaschool.africanmarketplace.models.MarketLocation;
import com.lambdaschool.africanmarketplace.services.MarketLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/marketlocations")
public class MarketLocationController {
    @Autowired
    private MarketLocationService marketLocationService;

    @GetMapping(value = "/marketlocations", produces = "application/json")
    public ResponseEntity<?> listAllMarketLocations()
    {
        List<MarketLocation> myMarketLocations = marketLocationService.findAll();
        return new ResponseEntity<>(myMarketLocations, HttpStatus.OK);
    }

    @GetMapping(value = "/marketlocation/{marketLocationId}", produces = "application/json")
    public ResponseEntity<?> getMarketLocationById(@PathVariable long marketLocationId)
    {
        MarketLocation ml = marketLocationService.findById(marketLocationId);
        return new ResponseEntity<>(ml, HttpStatus.OK);
    }

    @GetMapping(value = "/marketlocation/name/{marketLocationName}", produces = "application/json")
    public ResponseEntity<?> getMarketLocationsByName(@PathVariable String marketLocationName)
    {
        List<MarketLocation> rtnlist = marketLocationService.findByName(marketLocationName);
        return new ResponseEntity<>(rtnlist, HttpStatus.OK);
    }
}


