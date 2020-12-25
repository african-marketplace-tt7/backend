package com.lambdaschool.africanmarketplace.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MarketLocationItemsId implements Serializable {
    private long marketLocation;
    private long item;

    public MarketLocationItemsId() {
    }

    public long getMarketLocation() {
        return marketLocation;
    }

    public void setMarketLocation(long marketlocation) {
        this.marketLocation = marketlocation;
    }

    public long getItem() {
        return item;
    }

    public void setItem(long item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        {
            return true;
        }
        if(obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        MarketLocationItemsId that = (MarketLocationItemsId) obj;
        return marketLocation == that.marketLocation && item == that.item;
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
