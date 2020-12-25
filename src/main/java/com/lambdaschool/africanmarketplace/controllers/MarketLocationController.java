package com.lambdaschool.africanmarketplace.controllers;

import com.lambdaschool.africanmarketplace.models.MarketLocation;
import com.lambdaschool.africanmarketplace.services.MarketLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/marketlocations")
public class MarketLocationController {
    @Autowired
    private MarketLocationService marketLocationService;

    /**
     * Returns a list of all market locations
     *
     * @return JSON list of all market locations with a status of OK
     */
    @GetMapping(value = "/marketlocations", produces = "application/json")
    public ResponseEntity<?> listAllMarketLocations() {
        List<MarketLocation> myMarketLocations = marketLocationService.findAll();
        return new ResponseEntity<>(myMarketLocations, HttpStatus.OK);
    }

    /**
     * Returns a single market location based on a marker location id number
     *
     * @param marketLocationId The primary key of a a market location you seek
     * @return JSON object of the market location you seek
     */
    @GetMapping(value = "/marketlocation/{marketLocationId}", produces = "application/json")
    public ResponseEntity<?> getMarketLocationById(@PathVariable long marketLocationId) {
        MarketLocation ml = marketLocationService.findById(marketLocationId);
        return new ResponseEntity<>(ml, HttpStatus.OK);
    }

    /**
     * Given a complete Market Location Object, create a new market location record.
     *
     * @param newmarketlocation A Market Location object.
     * @return A location header with the URI to the new created user and a status of CREATED
     */

    @PostMapping(value = "marketlocation", consumes = "application/json")
    public ResponseEntity<?> addNewMarketLocation(@Valid @RequestBody MarketLocation newmarketlocation) {
        newmarketlocation.setMarketlocationid(0);
        newmarketlocation = marketLocationService.save(newmarketlocation);

        HttpHeaders responseHeader = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{marketlocationid}")
                .buildAndExpand(newmarketlocation.getMarketlocationid())
                .toUri();
        responseHeader.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeader, HttpStatus.CREATED);
    }

    /**
     * Retruns a list of market locations with the given name
     *
     * @param marketLocationName
     * @return JSON list of market locations you seek
     */
    @GetMapping(value = "/marketlocation/name/{marketLocationName}", produces = "application/json")
    public ResponseEntity<?> getMarketLocationsByName(@PathVariable String marketLocationName) {
        List<MarketLocation> rtnlist = marketLocationService.findByName(marketLocationName);
        return new ResponseEntity<>(rtnlist, HttpStatus.OK);
    }

    @PutMapping(value = "marketlocation/{marketlocationid}")
    public ResponseEntity<?> updateFullMarketLocation(@Valid @RequestBody MarketLocation updateMarketLocation,
                                                      @PathVariable long marketlocationid) {
        updateMarketLocation.setMarketlocationid(marketlocationid);
        marketLocationService.save(updateMarketLocation);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "marketlocation/{marketlocationid}")
    public ResponseEntity<?> updateMarketLocation(@RequestBody MarketLocation updateMarketLocation,
                                                  @PathVariable long marketlocationid)
    {
        marketLocationService.update(updateMarketLocation, marketlocationid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/marketlocation/{marketlocationid}")
    public ResponseEntity<?> deleteMarketLocationById(@PathVariable long marketlocationid)
    {
        marketLocationService.delete(marketlocationid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


