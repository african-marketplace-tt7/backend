package com.lambdaschool.africanmarketplace.repository;

import com.lambdaschool.africanmarketplace.models.Item;
import com.lambdaschool.africanmarketplace.views.CountryProductAverage;
import com.lambdaschool.africanmarketplace.views.ProductAverage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findAllByCommodityProduct(String product);
    void deleteByItemid(long id);
    @Query(value = "SELECT i.commodity_product AS commodityproduct, SUM(sale_price) / SUM(quantity) AS averageprice  " +
            "FROM items i " +
            "WHERE i.commodity_product = :commodityProduct " +
            "GROUP BY i.commodity_product", nativeQuery = true)
    ProductAverage findProductAverage(String commodityProduct);
    @Query(value = "SELECT i.commodity_product AS commodityproduct, ml.country, SUM(i.sale_price) / SUM(i.quantity) AS averageprice " +
            "FROM items i JOIN marketlocationitems mli ON i.itemid = mli.itemid " +
            "JOIN marketlocations ml ON ml.marketlocationid = mli.marketlocationid " +
            "WHERE ml.country = :country AND i.commodity_product = :commodityProduct " +
            "GROUP BY i.commodity_product, ml.country", nativeQuery = true)
    CountryProductAverage findCountryProductAverage(String country, String commodityProduct);
}
