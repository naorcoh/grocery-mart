package com.naorcoh.grocerymart.service;

import com.naorcoh.grocerymart.filter.GroceryProductFilter;
import com.naorcoh.grocerymart.interfaces.RepoParam;
import com.naorcoh.grocerymart.model.Product;
import com.naorcoh.grocerymart.model.enums.Brand;
import com.naorcoh.grocerymart.model.enums.Category;
import com.naorcoh.grocerymart.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService implements RepoParam {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private GroceryProductFilter groceryProductFilter;


    public List<Product> getProductsByCategory(String category) {
       return productRepo.getProductsByCategory(category);

    }

    @Override
    public List<Product> getProductsByFilter(List<String> categories, List<String> brandsList, Integer min_price, Integer max_price) {

        return productRepo.getProductsByFilter(categories, brandsList, min_price, max_price);
    }

    @Override
    public List<Product> getProductsByFilter(List<String> categories, List<String> brandsList) {
        return null;
    }

    @Override
    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        return null;
    }


    public Set<Product> getByFilterMap(Map<String, List<String>> filterParams) {
        return productRepo.getByFilterMap(filterParams);
    }






    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        for (Category category : Category.values()) {
            if (!categories.contains(category)) {
                categories.add(category);
            }

        }

        return categories;
    }

    public List<Brand> getBrandsList() {
        List<Brand> brandsList = new ArrayList<>();

        for (Brand brand : Brand.values()) {
            if (!brandsList.contains(brand.toString())) {
                brandsList.add(brand);
            }

        }

        return brandsList;
    }

    public List<Product> getProductList() {
        return productRepo.getProductListV2();
    }

    public boolean isChecked() {

        return productRepo.isChecked();
    }


}
