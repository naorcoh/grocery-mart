package com.naorcoh.grocerymart.model.enums;


public enum Category {
    CHOCOLATES(false, ""),
    KITCHEN_TOOLS(false, ""),
    DENTAL_CHEWS(false, ""),
    BONES(false, "");

    boolean isActive;
    String itValue;


    Category(boolean isActive, String itValue) {
        this.isActive = isActive;
        this.itValue = itValue;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public String getItValue() {
        return itValue;
    }

    public void setItValue(String itValue) {
        this.itValue = itValue;
    }
}
