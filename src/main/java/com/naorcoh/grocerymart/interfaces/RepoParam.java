package com.naorcoh.grocerymart.interfaces;

import com.naorcoh.grocerymart.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RepoParam {

    public List<Product> getProductsByFilter(List<String> categories, List<String> brandsList, Integer min_price, Integer max_price);

    List<Product> getProductsByFilter(List<String> categories, List<String> brandsList);

    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
}
