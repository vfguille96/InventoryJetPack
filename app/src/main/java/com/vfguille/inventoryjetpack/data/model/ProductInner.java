package com.vfguille.inventoryjetpack.data.model;

public class ProductInner extends Product {

    private String categorieName, typeName, sectorName;

    public ProductInner(int _id, String name, String serial, String seller, String model, int sector, int categorie, int type, String description, float price, String buyDate, String url, String notes, String categorieName, String typeName, String sectorName) {
        super(_id, name, serial, seller, model, sector, categorie, type, description, price, buyDate, url, notes);
        this.categorieName = categorieName;
        this.typeName = typeName;
        this.sectorName = sectorName;
    }

    public String getCategorieName() {
        return categorieName;
    }

    public void setCategorieName(String categorieName) {
        this.categorieName = categorieName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }
}