package com.lambdaschool.africanmarketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
public class Item extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemid;

    @NotNull
    private String commodityCat;

    @NotNull
    private String subCat;

    @NotNull
    private String commodityProduct;

    @NotNull
    @NotNull
    private String description;

    private double salePrice;

    private double quantity;

    @ManyToMany(mappedBy = "items")
    @JsonIgnoreProperties(value = {"user", "items"})
    private List<MarketLocation> marketsSold = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user")
//    @JsonIgnoreProperties("user")
    private User user;

    public Item() {
    }

    public Item(@NotNull String commodityCat,
                @NotNull String subCat,
                @NotNull String commodityProduct,
                @NotNull String description,
                double salePrice,
                double quantity)
    {
        this.commodityCat = commodityCat;
        this.subCat = subCat;
        this.commodityProduct = commodityProduct;
        this.description = description;
        this.salePrice = salePrice;
        this.quantity = quantity;
    }

    public long getItemid() {
        return itemid;
    }

    public void setItemid(long itemid) {
        this.itemid = itemid;
    }

    public String getCommodityCat() {
        return commodityCat;
    }

    public void setCommodityCat(String commodityCat) {
        this.commodityCat = commodityCat;
    }

    public String getSubCat() {
        return subCat;
    }

    public void setSubCat(String subCat) {
        this.subCat = subCat;
    }

    public String getCommodityProduct() {
        return commodityProduct;
    }

    public void setCommodityProduct(String commodityProduct) {
        this.commodityProduct = commodityProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public List<MarketLocation> getMarketsSold() {
        return marketsSold;
    }

    public void setMarketsSold(List<MarketLocation> marketsSold) {
        this.marketsSold = marketsSold;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
