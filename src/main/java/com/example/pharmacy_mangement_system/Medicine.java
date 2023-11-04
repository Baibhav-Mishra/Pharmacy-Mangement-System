package com.example.pharmacy_mangement_system;


public class Medicine {
    String name = "";
    double cost;
    int currentStock, id;
    String manufacturer = "", type = "", expiry = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Medicine(int id, String name, double cost, int currentStock, String manufacturer, String type, String expiry)
    {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.currentStock = currentStock;
        this.manufacturer = manufacturer;
        this.type = type;
        this.expiry = expiry;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpiry() {
        return expiry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
}
