package com.lambdaschool.africanmarketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "marketlocations")
public class MarketLocation extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long marketlocationid;

    @NotNull
    private String name;

    @NotNull
    private String street;

    @NotNull
    private String city;

    @NotNull
    private String country;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private User user;

    @ManyToMany()
    @JoinTable(name = "marketlocationitem",
            joinColumns = @JoinColumn(name = "marketlocationid"),
            inverseJoinColumns = @JoinColumn(name = "itemid"))
    @JsonIgnoreProperties(value = "items")
    private List<Item> items = new ArrayList<>();

    public MarketLocation() {
    }

    public MarketLocation(@NotNull String name,
                          @NotNull String street,
                          @NotNull String city,
                          @NotNull String country)
    {
        this.name = name;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public long getMarketlocationid() {
        return marketlocationid;
    }

    public void setMarketlocationid(long marketlocationid) {
        this.marketlocationid = marketlocationid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
