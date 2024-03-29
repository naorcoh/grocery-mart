package com.naorcoh.grocerymart.model;

import com.naorcoh.grocerymart.model.enums.Brand;
import com.naorcoh.grocerymart.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    int productID;
    String productName;
    Brand brand;
    String description;
    Category category;
    double currentPrice;
    double oldPrice;
    String frontImg;
    String backImg;

}
