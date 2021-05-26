package com.example.servlet.entity;

public class ProductType implements Cloneable {

    public ProductType(String type) {
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new ProductType(this.type);
    }
}
