package com.lambdaschool.africanmarketplace.controllers;

import com.lambdaschool.africanmarketplace.models.Item;
import com.lambdaschool.africanmarketplace.services.ItemService;
import com.lambdaschool.africanmarketplace.views.CountryProductAverage;
import com.lambdaschool.africanmarketplace.views.ProductAverage;
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
@RequestMapping("items")
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping(value = "/items", produces = "application/json")
    public ResponseEntity<?> listAllItems() {
        List<Item> myItems = itemService.findAll();
        return new ResponseEntity<>(myItems, HttpStatus.OK);
    }

    @GetMapping(value = "/item/{itemid}", produces = "application/json")
    public ResponseEntity<?> getItemById(@PathVariable long itemid){
        Item i = itemService.findById(itemid);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    @GetMapping(value = "/averageprice/{commodityProduct}", produces = "application/json")
    public ResponseEntity<?> getAveragePrice(@PathVariable String commodityProduct)
    {
        ProductAverage productAverage = itemService.findAveragePrice(commodityProduct);
        return new ResponseEntity<>(productAverage, HttpStatus.OK);
    }

    @GetMapping(value = "/averageprice/{commodityProduct}/{country}", produces = "application/json")
    public ResponseEntity<?> getAveragePriceByCountry(@PathVariable String commodityProduct, @PathVariable String country)
    {
        CountryProductAverage countryProductAverage = itemService.findAveragePriceByCountry(country, commodityProduct);
        return new ResponseEntity<>(countryProductAverage, HttpStatus.OK);
    }

    @PostMapping(value = "/item", consumes = "application/json")
    public ResponseEntity<?> addNewItem(@Valid @RequestBody Item newItem){
        newItem.setItemid(0);
        newItem = itemService.save(newItem);

        HttpHeaders responseHeader = new HttpHeaders();
        URI newItemURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{itemid}")
                .buildAndExpand(newItem.getItemid())
                .toUri();
        responseHeader.setLocation(newItemURI);

        return new ResponseEntity<>(null, responseHeader, HttpStatus.CREATED);
    }

    @PutMapping(value = "item/{itemid}", consumes = "application/json")
    public ResponseEntity<?> updateFullItem(@Valid @RequestBody Item updateItem,
                                            @PathVariable long itemid){
        updateItem.setItemid(itemid);
        itemService.save(updateItem);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/item/{itemid}", consumes = "application/json")
    public ResponseEntity<?> updateItem(@RequestBody Item item, @PathVariable long itemid)
    {
        itemService.update(item, itemid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/item/{itemid}")
    public ResponseEntity<?> deleteItemById(@PathVariable long itemid)
    {
        itemService.delete(itemid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
