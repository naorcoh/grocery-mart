package com.naorcoh.grocerymart.model.enums;


public enum Brand {
    FARRERO(false),
    KINDER(false),
    GHIRARDELLI(false),
    HERSHEY(false),
    HOME_BASICS(false),
    MITSUMOTO_SAKARI(false),
    PURINA(false);

    private boolean  isActive;

    Brand(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
