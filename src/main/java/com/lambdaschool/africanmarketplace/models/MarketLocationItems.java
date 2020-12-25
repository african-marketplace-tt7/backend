package com.lambdaschool.africanmarketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "marketlocationitems")
@IdClass(MarketLocationItemsId.class)
public class MarketLocationItems extends Auditable implements Serializable
{
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "marketlocationid")
    @JsonIgnoreProperties(value ={"user", "items"}, allowSetters = true)
    private MarketLocation marketLocation;

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "itemid")
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private Item item;

    public MarketLocationItems() {
    }

    public MarketLocationItems(@NotNull MarketLocation marketLocation, @NotNull Item item) {
        this.marketLocation = marketLocation;
        this.item = item;
    }

    public MarketLocation getMarketLocation() {
        return marketLocation;
    }

    public void setMarketLocation(MarketLocation marketLocation) {
        this.marketLocation = marketLocation;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarketLocationItems that = (MarketLocationItems) o;
        return ((marketLocation == null) ? 0 : marketLocation.getMarketlocationid()) == ((that.marketLocation == null) ? 0 : that.marketLocation.getMarketlocationid()) &&
        ((item == null) ? 0 : item.getItemid()) == ((that.item == null) ? 0 : that.item.getItemid());
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
